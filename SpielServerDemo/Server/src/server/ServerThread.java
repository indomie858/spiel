/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.Timestamp;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;




/**
 *
 * @author gafaa
 */
public class ServerThread extends Thread{
    
    private Socket socket;
    private Server server;
    private OutputStream outputStream;
    private ObjectOutputStream oos;
    private DataInputStream dis;
    private DataOutputStream dos;
    private boolean isRunning = true;
    private boolean firstConnectionForClient = true;
    private MessageHistory messages;
    
    
    
    public ServerThread(Socket socket, Server server){
        
        super("ServerThread");
        
        this.socket = socket;
        
        this.server = server;
        
    }
    
    public void run() {
        
        messages = new MessageHistory();
        
        messages.testing();
        
        if (firstConnectionForClient){
                    
            try {
                
                outputStream = socket.getOutputStream();
                
                oos = new ObjectOutputStream(outputStream);
                
                oos.writeObject(messages.getArrayObject());
                
                firstConnectionForClient = false;
                
            } catch (IOException ex) {
                ex.printStackTrace();
            }
                                        
        }
        
        
        
        try {
            
            dis = new DataInputStream(socket.getInputStream());     //receives data in bytes from client
            
            dos = new DataOutputStream(socket.getOutputStream());   //sends data in bytes to client
            
            while (isRunning) {     //keeps that shit running
                
                
                
                while (dis.available() == 0){       //if the client doesnt send anything, the program gets trapped here
                    
                    try {
                        
                        Thread.sleep(1);    //
                        
                    } catch (InterruptedException ex) {
                        
                        ex.printStackTrace();
                        
                    }
                    
                }
                
                String message = dis.readUTF(); //reading message from server datainputstream (dis) -- client output
                
                Date date = new Date();     //date and timestamp
                
                int hours = date.getHours();
                int minutes = date.getMinutes();
                String ampm = "AM";
                
                if (hours > 12){
                    hours %= 12;
                    ampm = "PM";
                }
                
                
                
                String completeTime = hours + ":" + minutes + "" + ampm;
                
                System.out.println("output stream: " + message + " " + completeTime);   //printout message from client to server's console
                
                String text = completeTime + " Client: " + message;
                
                messages.addMessage(text);    //adds date/timestamp and message to MessageHistory object
                
                sendStringToAllClients(text);    
            }
            
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    private void sendStringToClient(String message) {   //writes string to client by dataoutputstream (dos)
        
        try {
            
            dos.writeUTF(message);
            dos.flush();
            
        } catch (IOException ex) {
            
            ex.printStackTrace();
            
        }
        
    }
    
    public void sendStringToAllClients(String message) {    //sends strings to all clients in connections arraylist
        
        for (int i = 0; i < server.getConnections().size(); i++) {
        
            ServerThread stObj = server.getConnections().get(i);
            
            stObj.sendStringToClient(message);
        }
        
    }
    
    
    
}
