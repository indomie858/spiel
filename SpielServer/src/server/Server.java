//S.P.I.E.L. Chat Application
//Programmers: Frank Serdenia, Jordan Bradshaw, Hongsen Yang, Kenneth Woo,
//             Joseph Olympia, and Gaven Grantz
//Course: COMP 380 Virginia Mushkatblat
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import spiel.ServerController;

public class Server {

    static private Socket socket;
    static private int portNumber = 55555;
    static private ServerSocket serverSocket;
    static private ArrayList<ServerThread> connections = new ArrayList<ServerThread>();
    static private boolean isRunning = true;
    static private ServerController guiController;
    static private ServerThread serverThread;

    public static void main(String[] args) {
        //  new Server();
    }

    public Server() {
        System.out.println("Server is now online...");
        this.startListening(this);
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public ArrayList<ServerThread> getConnections() {
        return connections;
    }

    public boolean getIsRunning() {
        return isRunning;
    }

    public static void startListening(Server tempServer) {
        Server server = tempServer;
        Thread listeningThread = new Thread() {
            @Override
            public void run() {
                try {
                    serverSocket = new ServerSocket(portNumber);

                    while (isRunning) {
                        
                                socket = serverSocket.accept();

                                System.out.println("Client Accepted");
                                ServerThread stObject = new ServerThread(socket, server);

                                stObject.start();

                                connections.add(stObject);
                            
                        }
                    
                } catch (IOException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }}};
                listeningThread.start();
    
        }
}