package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import spiel.ServerController;


public class Server {
    
    private Socket socket;
    private int portNumber = 55555;
    private ServerSocket serverSocket;
    private ArrayList<ServerThread> connections = new ArrayList<ServerThread>();
    private boolean isRunning = true;
    private ServerController guiController;
    private ServerThread serverThread;
    
    public static void main(String[] args) {
      //  new Server();
    }
    
    public Server() {
        System.out.println("Server is now online...");
        
        try {          
            serverSocket = new ServerSocket(portNumber);
            while (isRunning) {
                socket = serverSocket.accept();
                
                System.out.println("Client Accepted");
                ServerThread stObject = new ServerThread(socket, this);
                
                stObject.start();
               
                connections.add(stObject);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    
    
}