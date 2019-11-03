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
public class ServerThread extends Thread {

    private Socket socket;
    private Server server;
    private OutputStream outputStream;
    private ObjectOutputStream objectOutputStream;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private boolean isRunning = true;
    private boolean firstConnectionForClient = true;
    private MessageHistory messages;

    public ServerThread(Socket socket, Server server) {
        super("ServerThread");
        this.socket = socket;
        this.server = server;
    }

    public void run() {
        messages = new MessageHistory();
        messages.testing();

        if (firstConnectionForClient) {
            try {
                outputStream = socket.getOutputStream();
                objectOutputStream = new ObjectOutputStream(outputStream);
                objectOutputStream.writeObject(messages.getArrayObject());
                firstConnectionForClient = false;
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        try {
            dataInputStream = new DataInputStream(socket.getInputStream());     //receives data in bytes from client
            dataOutputStream = new DataOutputStream(socket.getOutputStream());   //sends data in bytes to client

            while (isRunning) {     //keeps that shit running
                while (dataInputStream.available() == 0) {       //if the client doesnt send anything, the program gets trapped here
                    try {
                        Thread.sleep(1);    //
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }

                String message = dataInputStream.readUTF(); //reading message from server datainputstream (dis) -- client output
                Time time = new Time();
                String completeTime = time.getTime();
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
            dataOutputStream.writeUTF(message);
            dataOutputStream.flush();
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
