//S.P.I.E.L. Chat Application
//Programmers: Frank Serdenia, Jordan Bradshaw, Hongsen Yang, Kenneth Woo,
//             Joseph Olympia, and Gaven Grantz
//Course: COMP 380 Virginia Mushkatblat

package client;

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
    
//    @Test
//    public void testRemoveClient() {
//        ClientList list = new ClientList();
//        list.addClient("CLIENT1");
//        list.addClient("CLIENT2");
//        list.removeClient(1);
//        
//        int expected = 1;
//        int actual = list.getClientCount();
//        assertEquals(expected, actual);
//    }

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
