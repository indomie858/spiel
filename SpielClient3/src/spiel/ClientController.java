//S.P.I.E.L. Chat Application
//Programmers: Frank Serdenia, Jordan Bradshaw, Hongsen Yang, Kenneth Woo,
//             Joseph Olympia, and Gaven Grantz
//Course: COMP 380 Virginia Mushkatblat

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
import java.util.ArrayList;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
    private String serverMessageCheck = "empty";
    private ArrayList<String> token = new ArrayList<String>();
    private String clientUsername = null;
    
    

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
    private Button helpButton = new Button();
    @FXML
    private Button quitButton = new Button();

    @FXML
    private void handleSendButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");       
    }
   
    
    //this is for when the "Connect" button is clicked
    @FXML
    private void handleConnectButtonAction(ActionEvent event) throws IOException, InterruptedException {
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
            quitButtonCheck = false;
            clientUsername = usernameInputBox.getText();
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
        chatBoxInput.setDisable(true);
        usernameInputBox.clear();
        chatBoxOutput.appendText("You are now disconnected...  \n");
        disconnect = true;
        removeOnlineUserTab();
        try {
            
            //this will give the program enough time to process things before the client disconnects
            Thread.sleep(1000);
            userLobbyArea.clear();
            clientObj.closeSocket();
            connectButton.setDisable(false);
            
        } catch (InterruptedException ex) {
            
        }
    }
    
    //this will show a pop-up menu box when the "help" button is clicked
    @FXML
    private void helpButtonAction() {
        
        UIManager UI=new UIManager();
        UI.put("OptionPane.background", Color.RED);
        UI.put("Panel.background", Color.lightGray);
        
        JOptionPane optionPane = new JOptionPane(
                "***HOW TO USE CHATBOX: \n"
                + "Step 1: Enter a username.      **Empty username will generate a random username. \n"
                + "Step 2: Enter a port number.  **This feature is currently disabled until [TBA] \n"
                + "Step 3: Enter an IP address.   **Only enter an IP address if you are trying to connect to a different computer. \n"
                + "Step 4: Click connect.              **Assuming that the server is online; offline server will lead to no connection \n"
                + "Step 5: Type message.            **Enter any message you like to send \n"
                + "Step 6: Click send.                    **Sends the message to the other client \n"
               
                + "--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- \n"
                   
                + "***BUTTON FUNCTION: \n"
                + "Quit = closes the chat box \n"
                + "Connect = connects client to server \n"
                + "Disconnect = disconnect client from server \n"
                + "Send = send message to other client \n"
                + "--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- \n"
                + "***If server goes offline (fork bomb), the window will automatically close if you are CONNECTED. DISCONNECTED client will not be affected \n"
                + "***To prevent the window from closing when server goes offline(fork bomb), DISCONNECT beforehand \n"
                + "--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- \n"        
                + "***MY CLIENT WINDOW FROZE! WHAT DO I DO? \n"
                + "Step 1: Do not touch your previous client \n"
                + "Step 2: Open a new client window \n"
                + "Step 3: Notify the server to FORK BOMB due to client error \n"
                + "Step 4: Notify the other clients to disconnect for a good couple of seconds. Enough time to FORK BOMB \n"
                + "Step 5: Re-connect to the server if the server is ready to accept client\n"
                + "Step 6: If frozen client dissappears, then server has initiated FORK BOMB\n"
                
                );
      
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
            userLobbyArea.clear();
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
    public void updateChatBoxOutput(String text) throws InterruptedException {
        if ((text.contains("> is now online...") == true) && (text.contains(":") == false)){
            newUserSound();
            chatBoxOutput.appendText(text + "\n");
        }
        //when fork bomb is clicked
        else if (text.contains("Server is now offline! Disconnect client!")){
                chatBoxOutput.clear();
                chatBoxInput.setDisable(true);
                disconnectButton.setDisable(true);
                sendButton.setDisable(true);
                helpButton.setDisable(true);
                quitButton.setDisable(true);
                String input = clientThread.getUsername() + "> is now offline...";
                clientThread.sendStringToServer(input);
                String newMessage = clientThread.getMessage();
                messageObj.setMessage(newMessage);
                Thread.sleep(1);
                System.exit(0);
         
               
        }
        else if (text.contains("SERVER WANTS TO DESTROY THIS CLIENT:")){
                if (text.contains(clientUsername)){
                chatBoxOutput.clear();
                chatBoxInput.setDisable(true);
                disconnectButton.setDisable(true);
                sendButton.setDisable(true);
                helpButton.setDisable(true);
                quitButton.setDisable(true);
                String input = clientThread.getUsername() + "> is now offline...";
                clientThread.sendStringToServer(input);
                String newMessage = clientThread.getMessage();
                messageObj.setMessage(newMessage);
                Thread.sleep(1);
                System.exit(0);
                }
                else {
                    // do nothing
                }
        }
        else{
                    chatBoxOutput.appendText(text + "\n");
        }
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
    
    public void newUserSound(){
        String musicFile = "newuser.mp3";     // For example
        Media sound = new Media(new File(musicFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
