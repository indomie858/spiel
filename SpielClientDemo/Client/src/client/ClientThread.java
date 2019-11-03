/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gafaa
 */
public class ClientThread extends Thread {

    private Client clientObject;
    private Socket socketObject;
    private DataInputStream dis;
    private DataOutputStream dos;
    private boolean isRunning = true;
    private boolean firstTime = true;
    private ArrayList<String> messages;
    private ObjectInputStream ois;
    private Object object;
    

    public ClientThread(Socket socket, Client client) {

        socketObject = socket;

        clientObject = client;

    }

    public void run() {

        try {

            
            if (firstTime){
                messages = new ArrayList<String>();
                ois = new ObjectInputStream(socketObject.getInputStream());
                try {
                    object = ois.readObject();
                    messages = (ArrayList<String>)object; 
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
                
                for (int i = 0; i < messages.size(); i++){
                    System.out.println(messages.get(i));
                }
                    
                firstTime = false;
                
            }
            
            dis = new DataInputStream(socketObject.getInputStream());
            dos = new DataOutputStream(socketObject.getOutputStream());

            while (isRunning) {

                try {
                    while (dis.available() == 0) {
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
                    

                    String message = dis.readUTF();
                    System.out.println(message);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
            close();
        }

    }
    
    public void sendStringToServer(String message) {
        try {
            dos.writeUTF(message);
            dos.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
            close();
        }
    }
    
    public void close() {
        try {
            dis.close();
            dos.close();
            socketObject.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
