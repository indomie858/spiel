/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author serde
 */
public class ClientList implements Serializable{
    private static final ArrayList<String> clientList = new ArrayList<String>();
    private int clientCount;
    
    ClientList() {
        clientCount = 0;
    }
    
    public void addClient (String client){
        clientList.add(client);
        clientCount++;
    }
    
    public ArrayList<String> getArrayObject() {
        return clientList;
    }
    
    public int getClientCount(){
        return clientCount;
    } 
    
    public void removeClient (int i){
        clientList.remove(i);
    }
    
    public int findClient(String client){
       return clientList.indexOf(client);
    }
    
 
}

