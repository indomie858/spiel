/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author serde
 */
public class ClientTest {
    
    public ClientTest() {
    }


    @Test
    public void testGetUsername() {
        Client client = new Client();
        client.setUsername("Testing");
        String expected = "Testing";
        String actual = client.getUsername();
    }

    @Test
    public void testSetUsername() {
        Client client = new Client();
        client.setUsername("Testing");
        String expected = "Testing";
        String actual = client.getUsername();
        
    }
    
}
