/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jordanbradshaw
 */
public class MessageHistoryTest {
    
    public MessageHistoryTest() {
    }

    /**
     * Test of addMessage method, of class MessageHistory.
     */
    @Test
    public void testAddMessage() {
        MessageHistory messageHistory = new MessageHistory();
        messageHistory.addMessage("TEST1");
        String expected = "TEST1";
        String actual = messageHistory.getMessage(0);
        assertEquals(expected, actual);
        messageHistory.removeMessages(0);
    }

    /**
     * Test of getMessage method, of class MessageHistory.
     */
    @Test
    public void testGetMessage() {
        MessageHistory messageHistory = new MessageHistory();
        messageHistory.addMessage("TEST1");
        String expected = "TEST1";
        String actual = messageHistory.getMessage(0);
        assertEquals(expected, actual);
        messageHistory.removeMessages(0);
    }

    /**
     * Test of setMaxStorage method, of class MessageHistory.
     */
    @Test
    public void testSetMaxStorage() {
        MessageHistory messageHistory = new MessageHistory();
        messageHistory.setMaxStorage(5);
        int expected = 5;
        int actual = messageHistory.getMaxStorage();
        assertEquals(expected, actual);
    }

    /**
     * Test of getMaxStorage method, of class MessageHistory.
     */
    @Test
    public void testGetMaxStorage() {
        MessageHistory messageHistory = new MessageHistory();
        messageHistory.setMaxStorage(15);
        int expected = 15;
        int actual = messageHistory.getMaxStorage();
        assertEquals(expected, actual);
    }

    /**
     * Test of getMessageCount method, of class MessageHistory.
     */
    @Test
    public void testGetMessageCount() {
        MessageHistory messageHistory = new MessageHistory();
        messageHistory.addMessage("TEST1");
        int expected = 1;
        int actual = messageHistory.getMessageCount();
        assertEquals(expected, actual);
        messageHistory.removeMessages(0);
    }

    /**
     * Test of getArrayObject method, of class MessageHistory.
     */
    @Test
    public void testGetArrayObject() {
        MessageHistory messageHistory = new MessageHistory();
        messageHistory.addMessage("TEST1");
        List<String> expected = new ArrayList<>();
        expected.add("TEST1");
        assertEquals(expected,messageHistory.getArrayObject());
        messageHistory.removeMessages(0);
        
    }

    /**
     * Test of displayMessages method, of class MessageHistory.
     */
    @Test
    public void testDisplayMessages() {
    }

    /**
     * Test of removeMessages method, of class MessageHistory.
     */
    @Test
    public void testRemoveMessages() {
        MessageHistory messageHistory = new MessageHistory();
        messageHistory.addMessage("TEST1");
        messageHistory.removeMessages(0);
        List<String> expected = new ArrayList<>();
        assertEquals(expected,messageHistory.getArrayObject());
    }

    /**
     * Test of testing method, of class MessageHistory.
     */
    
}
