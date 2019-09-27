/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.net.Socket;

/**
 *
 * @author gafaa
 */
public interface UserRecordInterface {
    
    void setServerName(String username);
    
    String getServerName();
    
    void setServerID(int id);
    
    String getServerID();
    
    void setServerIP(Socket server);
    
    Socket getServerIP();
    
    String toString();
    
}
