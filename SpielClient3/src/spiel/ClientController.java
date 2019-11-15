/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spiel;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

import client.Client;
import client.ClientThread;
import client.Message;
import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import javafx.concurrent.*;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author gafaa
 */
public class ClientController implements Initializable {  //client controller

    private Client clientObj;
    private ClientThread clientThread;
    private boolean clientIsConnected = false;
    private ArrayList<String> update;
    private boolean disconnect = false;
    private int randomNum = (int) (Math.random() * 100);
    private boolean quitButtonCheck = true;
    private Rando nameGenerator;
    
    

    private Message messageObj = new Message();

    @FXML
    private TextField usernameInputBox = new TextField();
    @FXML
    private TextArea chatBoxOutput = new TextArea();
    @FXML
    private TextArea userLobbyArea = new TextArea();
    @FXML
    private TextField chatBoxInput = new TextField();
    @FXML
    private TextField portField = new TextField();
    @FXML
    private TextField ipField = new TextField();
    @FXML
    private Button sendButton = new Button();
    @FXML
    private Button connectButton = new Button();
    @FXML
    private Button disconnectButton = new Button();

    @FXML
    private void handleSendButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");       
    }
    
    //this is for when the "Connect" button is clicked
    @FXML
    private void handleConnectButtonAction(ActionEvent event) throws IOException {
        if (ipField.getText().length() != 0 && portField.getText().length() != 0){
            chatBoxOutput.clear();
            clientObj = new Client(this);
            clientThread = clientObj.getClientThread();
            clientThread.setguiController(this);
            sendButton.setDisable(false);
            connectButton.setDisable(true);
            disconnectButton.setDisable(false);
            usernameInputBox.setDisable(true);
            portField.setDisable(true);
            ipField.setDisable(true);
            chatBoxInput.setDisable(false);
     
            String username = usernameInputBox.getText();
        
            if (username.length() == 0) {
                nameGenerator = new Rando();
                clientThread.setUsername(nameGenerator.getName());
                usernameInputBox.setText(nameGenerator.getName());
            } else if (username.length() > 15) {
                String subString = username.substring(0, 15);
                clientThread.setUsername(subString);
            } else {
                clientThread.setUsername(username);
            }
            clientIsConnected = true;
        
       
            messageObj.setMessage("BEGIN ");
            //clientThread.sendStringToServer
        }
        else {
            chatBoxOutput.appendText("One or more data is invalid... Check IP Address and Port# \n");
        }
    }
    //this is for when the "disconnect" button is clicked
    @FXML
    private void handleDisconnectButtonAction(ActionEvent event) throws IOException {
        clientIsConnected = false;
        quitButtonCheck = false;
        chatBoxOutput.clear();       
        sendButton.setDisable(true);
        disconnectButton.setDisable(true);
        usernameInputBox.clear();
        usernameInputBox.setDisable(false);
        ipField.setDisable(false);
        portField.setDisable(false);
        chatBoxInput.setDisable(true);
        userLobbyArea.clear();
        usernameInputBox.clear();
        chatBoxOutput.appendText("You are now disconnected...  \n");
        disconnect = true;
        removeOnlineUserTab();
        try {
            
            //this will give the program enough time to process things before the client disconnects
            Thread.sleep(1000);
            clientObj.closeSocket();
            connectButton.setDisable(false);
        } catch (InterruptedException ex) {
            
        }
    }
    
    //this will show a pop-up menu box when the "help" button is clicked
    @FXML
    private void helpButtonAction() {
        JOptionPane optionPane = new JOptionPane(
                "How to use chatbox: \n"
                + "Step 1: Enter a username.      **Empty username will generate a random username. \n"
                + "Step 2: Enter a port number.  **Leave it if you are confuse \n"
                + "Step 3: Enter an IP address.   **Only enter an IP address if you are trying to connect to a different computer. \n"
                + "Step 4: Click connect.              **Assuming that the server is online; offline server will lead to no connection \n"
                + "Step 5: Type message.            **Enter any message you like to send \n"
                + "Step 6: Click send.                    **Sends the message to the other client \n"
                + "\n"
                + "Button function: \n"
                + "Quit = closes the chat box \n"
                + "Connect = connects client to server \n"
                + "Disconnect = disconnect client from server \n"
                + "Send = send message to other client \n",
                JOptionPane.WARNING_MESSAGE);
      
        optionPane.setFont(new Font("Arial", Font.PLAIN, 36));
        JDialog dialog = optionPane.createDialog("Help");
        dialog.setAlwaysOnTop(true); // to show top of all other application
        dialog.setVisible(true); // to visible the dialog
    }
    
    //this exits the entire program
    @FXML
    private void quitButtonAction() {
      if (quitButtonCheck == false){
        disconnect = true;
        removeOnlineUserTab();
      }
        try {
            
            //this will give enough time for the program to process things before the client leaves
            Thread.sleep(1000);
            
        } catch (InterruptedException ex) {
           // ex.printStackTrace();
        }
        System.exit(0);
    }
    
    // this handles chat display
    @FXML
    private void handleChatDisplay() {
        Platform.runLater(() -> {
            chatBoxOutput.appendText(clientThread.getMessage() + "\n");
        });
    }

    //this updates the area were messages are seen
    @FXML
    private void handleChatBox() throws IOException {
        if (disconnect == false){
            String input = chatBoxInput.getText();
            String username = clientThread.getUsername();
            clientThread.sendStringToServer(username + " : " + input);
            String newMessage = clientThread.getMessage();
        
            messageObj.setMessage(newMessage);
            chatBoxInput.clear();
        }
        else {
            disconnect = true;
            removeOnlineUserTab();
        }
    }

    @FXML
    private void handleUsernameInputbox() {

    }
    
    //Displays text in the chat box text-area
    public void updateChatBoxOutput(String text) {
        chatBoxOutput.appendText(text + "\n");

    }
    
    //when a new client connects, this help add the client to the online user tab
    public void updateOnlineUserTab(String user){
        if (user.length() > 15){
            userLobbyArea.appendText(user.substring(0, 15) + "\n");
        }
        else {
            userLobbyArea.appendText(user + "\n");
        }
    }
    
    //clears the online user tab
    public void clearOnlineUserTab(){
        userLobbyArea.clear();
    }
    
    //when a client disconnect, this removes the client from the online user tab
    public void removeOnlineUserTab() {
        String input = clientThread.getUsername() + "> is now offline...";
            clientThread.sendStringToServer(input);
            String newMessage = clientThread.getMessage();
            messageObj.setMessage(newMessage);
            chatBoxInput.clear();
            disconnect = false;
    }
    
    //Get username from username text-field
    public String getUsername() {
        return usernameInputBox.getText();
    }
    
    //Get IPAddress from IPAddress text-field
    public String getIPAddress() {
        return ipField.getText();
    }
    //Get Port number from Port text-field
    public String getPort() {
        return portField.getText();
    }
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
