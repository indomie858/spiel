//S.P.I.E.L. Chat Application
//Programmers: Frank Serdenia, Jordan Bradshaw, Hongsen Yang, Kenneth Woo,
//             Joseph Olympia, and Gaven Grantz
//Course: COMP 380 Virginia Mushkatblat

package client;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import spiel.ClientController;


/**
 *
 * @author gafaa
 */
public class ClientThread extends Thread {

    private Client clientObject;
    private Socket socketObject;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private boolean isRunning = true;
    private boolean firstTime = true;
    //private boolean firstNotify = true;
    private ArrayList<String>  messages = new ArrayList<String>();
    private ObjectInputStream objectInputStream;
    private Object object;
    private String message = null;
    private String username;
   // private MessageHistory storage = new MessageHistory();
    private ClientController guiController = null;
    private ArrayList<String>  clientList = new ArrayList<String>();
    private ArrayList<String>  token = new ArrayList<String>();

    

    public ClientThread(Socket socket, Client client) {
        socketObject = socket;
        clientObject = client;
    }
    
 

    public void run() {

        try {
            if (firstTime){    //if new client is connecting, take in arraylist from object input stream
                try {
                    
                //receives old messages------------------------------
                objectInputStream = new ObjectInputStream(socketObject.getInputStream());
                
                object = objectInputStream.readObject();
                messages = (ArrayList<String>)object;      
                
                //displays the old messages in the text area---------------------------
                for (int i = 0; i < messages.size(); i++){
                    guiController.updateChatBoxOutput(messages.get(i));               
                }
                
                //sends new client username--------------------------
                dataOutputStream = new DataOutputStream(socketObject.getOutputStream());
                dataOutputStream.writeUTF(guiController.getUsername());
                dataOutputStream.flush();
                
                //receives list of clients-----------------------------
                objectInputStream = new ObjectInputStream(socketObject.getInputStream());
                object = objectInputStream.readObject();
                clientList = (ArrayList<String>)object; 
                
                //displays all the users in the text area-------------------------------
                for (int i = 0; i < clientList.size(); i++){
                    guiController.updateOnlineUserTab(clientList.get(i));
                }     
                
                //displays in the chat box area that this client is online------------------
                dataInputStream = new DataInputStream(socketObject.getInputStream());
                String online = dataInputStream.readUTF();
                guiController.updateChatBoxOutput(online);
                
                //this will prevent the user from accessing the if-statement a second time-----------
                firstTime = false;  
                
                 } catch (ClassNotFoundException ex) {
                   // ex.printStackTrace();
                } catch (InterruptedException ex) {
                  //  Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            
            dataInputStream = new DataInputStream(socketObject.getInputStream());
            dataOutputStream = new DataOutputStream(socketObject.getOutputStream());
            
            while (isRunning) {
                try {
                    while (dataInputStream.available() == 0) {
                        try {
                            
                            //when the client is inactive, GUI updates the online user tab every 4 seconds-----------
                            guiController.clearOnlineUserTab();
                            for (int i = 0; i < clientList.size(); i++){
                                guiController.updateOnlineUserTab(clientList.get(i));
                            }
                            
                            // 1 second time frame-------------------
                            Thread.sleep(1000);
                        } catch (InterruptedException ex) {
                           //ex.printStackTrace();
                        }
                    }
                    String tempString = dataInputStream.readUTF();
                    message = tempString;
                    messages.add(message);
                   
                    updateOnlineUser(message); //this checks if the user still connected or not
                  
                    guiController.updateChatBoxOutput(message);
                    
                    System.out.println(message);
                } catch (IOException ex) {
                    //ex.printStackTrace();
                } catch (InterruptedException ex) {
                   // Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        } catch (IOException e) {
            isRunning = false;
        }
    }
    
    
    public void sendStringToServer(String message) {
        try {
            dataOutputStream.writeUTF(message);
            dataOutputStream.flush();
        } catch (IOException ex) {
            System.out.println("Client disconnected");
            close();
        }
    }
    
    public void close() {
        try {
            dataInputStream.close();
            dataOutputStream.close();
            socketObject.close();
        } catch (IOException ex) {
            //ex.printStackTrace();
        }
    }

    public String getMessage() {
        return message;
    }

    public Client getClientObject() {
        return clientObject;
    }

    public Socket getSocketObject() {       
        return socketObject;
    }

    public void setUsername(String username) {        
        this.username = username;
    }

    public String getUsername() {       
        return username;
    }

    public ArrayList<String> getMessages() {       
        return messages;
    }
    
    public void setguiController (ClientController tempguiController){        
        guiController = tempguiController;
    }
    
    public void updateOnlineUser(String message){
        
        // if their is a new user, add the new user to the clientList array list-----------------
        if ((message.contains("> is now online...") == true) && (message.contains(":") == false)){ 
            guiController.newUserSound();
            
            for (String retval: message.split(">")){                
                    token.add(retval);
            }
            if (token.get(0).length() > 15){
                clientList.add(token.get(0).substring(0, 15));
                System.out.println(token.get(0) + " has been added....");
            }
            else {
            clientList.add(token.get(0));            
            System.out.println(token.get(0) + " has been added....");
            }
        }
        
        //if the user is offline, remove the user from the clientList array list----------------
        else if (message.contains("> is now offline...") == true){            
            for (String retval: message.split(">")){                
                 token.add(retval);
            }
            
            clientList.remove(token.get(0));            
            System.out.println(token.get(0) + " has been removed...");
        }
        else {
                // do nothing
        }
        
        // clear the tempory array list-----------------------
           token.clear();     
    }
}
