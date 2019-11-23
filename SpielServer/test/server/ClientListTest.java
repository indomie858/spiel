/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author serde
 */
public class ClientListTest {
    
    public ClientListTest() {
    }

    @Test
    public void testAddClient() {
        ClientList list = new ClientList();
        list.addClient("CLIENT1");
        int expected = 1;
        int actual = list.getClientCount();
        assertEquals(expected, actual);
    }

  
    @Test
    public void testGetClientCount() {
        ClientList list = new ClientList();
        list.addClient("CLIENT1");
        list.addClient("CLIENT2");
        int expected = 2;
        int actual = list.getClientCount();
        assertEquals(expected, actual);
    }

    @Test
    public void testRemoveClient() {
        ClientList list = new ClientList();
        list.addClient("CLIENT1");
        list.addClient("CLIENT2");
        list.removeClient(1);
        
        int expected = 1;
        int actual = list.getClientCount();
        assertEquals(expected, actual);
    }

    @Test
    public void testFindClient() {
        ClientList list = new ClientList();
        list.addClient("String1");
        list.addClient("String2");
        int expected = 3;
        System.out.println(list.findClient("String2"));
        int actual = list.findClient("String2");
        assertEquals(expected, actual);
    }
    
}
