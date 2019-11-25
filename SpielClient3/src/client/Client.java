//S.P.I.E.L. Chat Application
//Programmers: Frank Serdenia, Jordan Bradshaw, Hongsen Yang, Kenneth Woo,
//             Joseph Olympia, and Gaven Grantz
//Course: COMP 380 Virginia Mushkatblat

package client;

import java.net.*;
import spiel.ClientController;

public class Client {

    private Socket socket;
    private String username = null;
    private ClientThread clientThread;
    public ClientController guiController = null;

    public Client () {
    
    }
    
    public Client(ClientController tempguiController) throws InterruptedException {
        guiController = tempguiController;
        
        int portNumber = Integer.parseInt(guiController.getPort()); //takes the port number from GUI
        String ipAddress = guiController.getIPAddress(); //takes IPaddress from the GUI

        try {
            socket = new Socket(ipAddress, portNumber);
            clientThread = new ClientThread(socket, this);
            clientThread.start();

            //listenForInput();
        } catch (Exception e) {
            guiController.updateChatBoxOutput("Invalid IP...");
        }
    }

    
    public static void main(String[] args) {
    }

    public ClientThread getClientThread() {
        return clientThread;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void closeSocket() {
        clientThread.close();
    }
}
