/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

/**
 *
 * @author gafaa
 */
public class Message {
    private String message;
    private boolean isNewMessage = false;

    
    public String getMessage(){
        isNewMessage = false;
        return message;
    }
    
    public void setMessage(String message){
        this.message = message;
        isNewMessage = true;
    }

    public boolean isNewMessage() {
        return isNewMessage;
    }
    
    
    
}
