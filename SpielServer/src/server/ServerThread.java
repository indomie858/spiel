//S.P.I.E.L. Chat Application
//Programmers: Frank Serdenia, Jordan Bradshaw, Hongsen Yang, Kenneth Woo,
//             Joseph Olympia, and Gaven Grantz
//Course: COMP 380 Virginia Mushkatblat

package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import spiel.ServerController;

/**
 *
 * @author gafaa
 */
public class ServerThread extends Thread{

    private Socket socket;
    private Server server;
    private OutputStream outputStream;
    private ObjectOutputStream objectOutputStream;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private boolean isRunning = true;
    private boolean firstConnectionForClient = true;
    private MessageHistory messages = new MessageHistory();
    private ClientList clientList = new ClientList();
    private String threadName = null;
    private String text = null;
    private ArrayList<String> token = new ArrayList<String>();
    private ServerThread stObj = null;
    private ServerController guiController = null;
   
    
    
    

    public ServerThread(Socket socket, Server server) {
        super("ServerThread");
        this.socket = socket;
        this.server = server;
        
    }

    @Override
    public void run() {
        
        //updates new client with 10 most recent message
        if (firstConnectionForClient) {  
            try {
                
                //sends old messages------------------
                outputStream = socket.getOutputStream();  
                objectOutputStream = new ObjectOutputStream(outputStream);
                objectOutputStream.writeObject(messages.getArrayObject());
                objectOutputStream.flush();
                System.out.println("New Client has been updated...");
                
                //receives client username-------------------
                dataInputStream = new DataInputStream(socket.getInputStream()); 
                threadName = dataInputStream.readUTF();
                clientList.addClient(threadName);
                
                //sends list of client-----------------
                outputStream = socket.getOutputStream();
                objectOutputStream = new ObjectOutputStream(outputStream);
                objectOutputStream.writeObject(clientList.getArrayObject());
                objectOutputStream.flush();
                
                //notifys all client who went online-----------------
                dataOutputStream = new DataOutputStream(socket.getOutputStream());
                guiController.updateChatBoxOutput(threadName + " is now online... \n");
                sendStringToAllClients(threadName + "> is now online...");
                System.out.println(threadName + "> is now online...");
                
                
                firstConnectionForClient = false;
                
            } catch (IOException ex) {
               // ex.printStackTrace();
            }
        }

        try {
            dataInputStream = new DataInputStream(socket.getInputStream());     //receives data in bytes from client
            dataOutputStream = new DataOutputStream(socket.getOutputStream());   //sends data in bytes to client

            while (isRunning) {  //keeps the program running
                
                //if the client doesnt send anything, the program gets trapped here--------
                while (dataInputStream.available() == 0) { 
                    try {
                        Thread.sleep(1);   
                    } catch (InterruptedException ex) {
                     //   ex.printStackTrace();
                    }
                }

                String message = dataInputStream.readUTF(); //reading message from server datainputstream (dis) -- client output

                String completeTime = dateTime();
                System.out.println(completeTime + " | " + message);   //printout message from client to server console
                
                
                //this if-else statements will determine if the client is disconnecting or not
                if (message.contains("> is now offline...") == true){
                    text = message;
                    
                    //splits the message into 2
                    for (String retval: message.split(">")){
                        token.add(retval);
                    }

                    int indexLocation = clientList.findClient(token.get(0));
                    clientList.removeClient(indexLocation);
                    
                }else {
                    text = completeTime + " | " + message;
                    messages.addMessage(text);    //adds message to MessageHistory object
                }
                guiController.updateChatBoxOutput(text + "\n");
                sendStringToAllClients(text);
            }

        } catch (IOException ex ) {
           
        }

    }

    //date and timestamp
    public String dateTime() { 
        Date date = new Date();     
        int hours = date.getHours();
        int minutes = date.getMinutes();
        String ampm = "AM";

        if (hours > 12) {
            
            hours %= 12;
            ampm = "PM";
        } else if (hours == 0){
            hours = 12;
        }
        else {
            // do nothing
        }
        
        String completeTime = new String();
        
        if (minutes < 10) {
            completeTime = hours + ":0" + minutes + " " + ampm;
        } else {
            completeTime = hours + ":" + minutes + " " + ampm;
        }
        return completeTime;
    }
    
    //writes string to client by dataoutputstream (dos)
    private void sendStringToClient(String message) { 
        
        try {
            dataOutputStream.writeUTF(message);
            dataOutputStream.flush();
        } catch (IOException ex) {
            //ex.printStackTrace();
        }
    }

    //sends strings to all clients in connections arraylist
    public void sendStringToAllClients(String message) {    
        for (int i = 0; i < server.getConnections().size(); i++) {
            try {
                stObj = server.getConnections().get(i);
                stObj.sendStringToClient(message);
                
            } catch (NullPointerException ex){
               // ex.printStackTrace();
            }
        }   
    }
 
    public void setGUIController(ServerController tempguiController){
        guiController = tempguiController;
    }
    
    public List<String> getMessage() {
        return messages.getArrayObject();
    }
}
