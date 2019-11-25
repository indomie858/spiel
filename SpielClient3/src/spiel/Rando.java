//S.P.I.E.L. Chat Application
//Programmers: Frank Serdenia, Jordan Bradshaw, Hongsen Yang, Kenneth Woo,
//             Joseph Olympia, and Gaven Grantz
//Course: COMP 380 Virginia Mushkatblat

package spiel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author serde
 */
public class Rando
{

  private String name = null;
  private ArrayList<String> list = new ArrayList<String>();
  private Random randomNumber = new Random();

  public Rando()
  {
    name = randomName();
  }
  
  public String getName()
  {
    return name;
  }

  public String randomName() 
	{
        // do you want to pick from the random list of just let the program decide
		
        list.add("Ben Dover"); list.add("Bill Clinton");
        list.add("Awimba Weh"); list.add("George Washington");
        list.add("Kash Register"); list.add("Oprah");
        list.add("Shay Kitoff"); list.add("Dank Memes");
        list.add("Back Cardi"); list.add("Your husband");
        list.add("Its Britney B."); list.add("Your wife");
        list.add("Dirty Dan"); list.add("Obama");
        list.add("Pinhead Larry"); list.add("Gandhi");
        list.add("Sheik Yerbouti"); list.add("Uranus");
        list.add("Jesus"); list.add("Corn Hub");
        list.add("He"); 
        list.add("She");
        list.add("Debt Collector");
        list.add("Dianne Harrison");
        list.add("Your Bills");
        list.add("Hillary Clinton");
        list.add("THAT MEAT");
        list.add("Damn Daniel");
        list.add("Lil Hennessy");
        list.add("Mother Teresa");
        list.add("Okay Boomers");
        list.add("THICC Forest");
        list.add("Tyga");
        list.add("Tupac");
        list.add("Sandra Lee");
        list.add("P.E.T.A.");
        list.add("I.R.S.");
        list.add("N.R.A.");
        list.add("Rainfurrest");
        list.add("Mia Khalifa");
        list.add("Pepe");
        list.add("Depress champ");
        list.add("Winnie the Pooh");
        list.add("Mei");
        list.add("Yo mamama");
        list.add("Donald Trump");
   
        
           
        int arraySize = list.size();
        
        int number = randomNumber.nextInt(arraySize);
        name = list.get(number);
        Collections.shuffle(list);
        return name;
	}

}