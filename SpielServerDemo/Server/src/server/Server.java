package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    private Socket socket;
    private ServerSocket serverSocket;
    private ArrayList<ServerThread> connections = new ArrayList<ServerThread>();
    private boolean isRunning = true;

    public static void main(String[] args) {
        new Server();
    }

    public Server() {
        try {
            serverSocket = new ServerSocket(55555);

            while (isRunning) {
                socket = serverSocket.accept();
                System.out.println("Server Start. \nClient Accepted");
                ServerThread stObj = new ServerThread(socket, this);
                stObj.start();
                connections.add(stObj);
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
