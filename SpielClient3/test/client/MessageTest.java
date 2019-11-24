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
public class MessageTest {
    
    public MessageTest() {
    }

    @Test
    public void testGetMessage() {
        Message message = new Message();
        message.setMessage("Testing");
        String expected = "Testing";
        String actual = message.getMessage();
        assertEquals(expected, actual);
    }

    @Test
    public void testSetMessage() {
        Message message = new Message();
        message.setMessage("Testing");
        boolean expected = true;
        boolean actual = message.isNewMessage();
        assertEquals(expected, actual);
    }

    @Test
    public void testIsNewMessage() {
        Message message = new Message();
        boolean expected = false;
        boolean actual = message.isNewMessage();
        assertEquals(expected, actual);
    }
    
}
