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
