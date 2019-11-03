package client;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {

    private ArrayList<String> messages;
    private Socket socket;
    private boolean firstTime = true;
    private String username = null;
    private ClientThread clientThread;

    public static void main(String[] args) {
        new Client();
    }

    public Client() {
        try {
            socket = new Socket("localhost", 55555);
            clientThread = new ClientThread(socket, this);
            clientThread.start();

            listenForInput();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void listenForInput() {
        Scanner input = new Scanner(System.in);
        while (true) {
            while (!input.hasNextLine()) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            String message = input.nextLine();
            if (message.toLowerCase().equals("quit")) {
                break;
            }
            clientThread.sendStringToServer(message);
            //System.out.println("checkpoint 1");
        }
    }

}
