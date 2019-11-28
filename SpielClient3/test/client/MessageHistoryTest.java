//S.P.I.E.L. Chat Application
//Programmers: Frank Serdenia, Jordan Bradshaw, Hongsen Yang, Kenneth Woo,
//             Joseph Olympia, and Gaven Grantz
//Course: COMP 380 Virginia Mushkatblat
package client;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author gafaa
 */
public class MessageHistoryTest {

    public MessageHistoryTest() {
    }

    @Test
    public void testAddMessage() {
        MessageHistory messageHistory = new MessageHistory();
        messageHistory.addMessage("TEST1");
        String expected = "TEST1";
        String actual = messageHistory.getMessage(0);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetMessage() {
        MessageHistory messageHistory = new MessageHistory();
        messageHistory.addMessage("TEST1");
        String expected = "TEST1";
        String actual = messageHistory.getMessage(0);
        assertEquals(expected, actual);
    }

    @Test
    public void testSetMaxStorage() {
        MessageHistory messageHistory = new MessageHistory();
        messageHistory.setMaxStorage(5);
        int expected = 5;
        int actual = messageHistory.getMaxStorage();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetMaxStorage() {
        MessageHistory messageHistory = new MessageHistory();
        messageHistory.setMaxStorage(15);
        int expected = 15;
        int actual = messageHistory.getMaxStorage();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetMessageCount() {
        MessageHistory messageHistory = new MessageHistory();
        messageHistory.addMessage("test message");
        int expected = 1;
        int actual = messageHistory.getMessageCount();
        assertEquals(expected, actual);
    }
}
