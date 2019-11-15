//S.P.I.E.L. Chat Application
//Programmers: Frank Serdenia, Jordan Bradshaw, Hongsen Yang, Kenneth Woo,
//             Joseph Olympia, and Gaven Grantz
//Course: COMP 380 Virginia Mushkatblat

package client;

import java.util.*;
import java.io.*;

public class MessageHistory implements Serializable{
   private static final ArrayList<String> storage = new ArrayList<String>();
   private int messageCount;
   private int maxStorage;
    
   MessageHistory() {
      messageCount = 0;
      maxStorage = 10;
   }
   
   MessageHistory (String message){
       storage.add(message);
       //run the server
    }
   
   
   public void addMessage(String message){            //this method will store messages in the array list
        if (messageCount < maxStorage){         //if storage is not full
            storage.add(message);
            messageCount++;
        }
        else {
            storage.remove(0);                  //if storage is full, this will delete the oldest message index 0
            storage.add(message);
        }    
    }
   public String getMessage(int i) {                  //this method will be used to update new users 
        String message = storage.get(i);
        return message;
    }
   public void setMaxStorage(int i) {                 //this method can increase storage max
        maxStorage = i;
    }
    
    public int getMaxStorage() {                       //this method is for retrieving the max storage number
        return maxStorage;
    }
    
    public int getMessageCount() {
        return messageCount;
    }
    
    public List<String> getArrayObject(){ //this will return an ArrayList object
        return storage;
    }
    
    public void displayMessages() {   //this loop will display what is in the arraylist
       try{
            for (int i = 0; i < storage.size(); i++){
                System.out.println(storage.get(i));
            }
       } catch (ArrayIndexOutOfBoundsException exception){
           System.out.println("Empty Storage");
       }
       
    }
    
    public void removeMessages(int i){
        storage.remove(i);
    }
    
    public void testing() {
        storage.add("Server: Hello");
        storage.add("Server: This");
        storage.add("Server: is");
        storage.add("Server: just");
        storage.add("Server: a");
        storage.add("Server: test");
    }    
}