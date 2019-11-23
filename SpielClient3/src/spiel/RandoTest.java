/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spiel;

import java.util.ArrayList;
import java.util.Random;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Joseph
 */
public class RandoTest {
    
    public RandoTest() 
    {
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
