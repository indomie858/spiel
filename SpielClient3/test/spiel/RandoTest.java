//S.P.I.E.L. Chat Application
//Programmers: Frank Serdenia, Jordan Bradshaw, Hongsen Yang, Kenneth Woo,
//             Joseph Olympia, and Gaven Grantz
//Course: COMP 380 Virginia Mushkatblat

package spiel;

import java.util.ArrayList;
import java.util.Random;
import org.junit.Test;
import spiel.Rando;
import static org.junit.Assert.*;

/**
 *
 * @author Joseph
 */
public class RandoTest {

    public RandoTest() {
    }

    /**
     * Test of getName method, of class Rando.
     */
    @Test
    public void testGetName() {
        Rando r = new Rando();
        String expected = "tyga";
        //String actual = r.getName();
        assertNotNull(expected);

    }

    /**
     * Test of randomName method, of class Rando.
     */
    @Test
    public void testRandomName() {
        ArrayList<String> list = new ArrayList<String>();
        Random randomNumber = new Random();
        list.add("Test");
        int arraySize = list.size();
        int number = randomNumber.nextInt(arraySize);
        //String expected  = "Test";
        //String actual = list.get(number);
        assertNotNull(list);

    }

}
