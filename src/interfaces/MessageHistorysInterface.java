/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

/**
 *
 * @author gafaa
 */
public interface MessageHistorysInterface {
    
    void addMessage(String i);
    
    String getMessage(int i);
    
    void setMaxStorage(int i);
    
    int getMaxStorage();
    
}
