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
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private boolean isRunning = true;
    private boolean firstTime = true;
    private ArrayList<String> messages;
    private ObjectInputStream objectInputStream;
    private Object object;

    public ClientThread(Socket socket, Client client) {

        socketObject = socket;

        clientObject = client;

    }

    public void run() {

        try {
            if (firstTime) {
                messages = new ArrayList<String>();
                objectInputStream = new ObjectInputStream(socketObject.getInputStream());
                try {
                    object = objectInputStream.readObject();
                    messages = (ArrayList<String>) object;
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }

                for (int i = 0; i < messages.size(); i++) {
                    System.out.println(messages.get(i));
                }
                firstTime = false;
            }

            dataInputStream = new DataInputStream(socketObject.getInputStream());
            dataOutputStream = new DataOutputStream(socketObject.getOutputStream());

            while (isRunning) {

                try {
                    while (dataInputStream.available() == 0) {
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
                    String message = dataInputStream.readUTF();
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
            dataOutputStream.writeUTF(message);
            dataOutputStream.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
            close();
        }
    }

    public void close() {
        try {
            dataInputStream.close();
            dataOutputStream.close();
            socketObject.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
