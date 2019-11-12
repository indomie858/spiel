import java.util.ArrayList; 
import java.util.List; 
import java.util.Random; 
import java.util.Arrays;




public class Rando
{

  private String name = null;

  public Rando()
  {
    this.name = randomName();
  }
  
  public String getName()
  {
    return name;
  }

  /*
  	public static void main(String args[])
	{
     can be inside main
        ArrayList<String> list = new ArrayList<String>
        (
            Arrays.asList("dog", "rat", "goat", "horse", "pig", "ox", "dragon",
            				"rabbit", "rooster", "tiger", "monkey", "snake")
        );
          
		  Random rand = new Random(); 
          String name = list.get(rand.nextInt(list.size()));
          System.out.println(name);
          
          System.out.println(name + " " + list.get(rand.nextInt(list.size())));
          list.add("table");
          System.out.println(list.get(rand.nextInt(list.size())));
    

          String name = randomName();
          String name2 = randomName();
          System.out.println(name);
          System.out.print(name2);     
    
   	}
*/
    
   
    //as a seperate function
	public String randomName() 
	{
        // do you want to pick from the random list of just let the program decide
		ArrayList<String> list = new ArrayList<String>
        (
            Arrays.asList("dog", "rat", "goat", "horse", "pig", "ox", "dragon",
            				"rabbit", "rooster", "tiger", "monkey", "snake")
        );
        Random rand = new Random();
        String name = list.get(rand.nextInt(list.size()));
        return name;
	}
    
}