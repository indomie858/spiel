import java.io.*; 
import java.util.*; 
import java.util.Arrays; 


public class ToArr
{
	public static void main (String args[])
	{
		System.out.println("enter 10 items");
		Scanner input = new Scanner(System.in);
		//String chat = input.nextLine();
		int arrSize = 10;
		
		ArrayList<String> chatArr = new ArrayList<String>(); //arraylist size 10
		//everytime an item is put into the list, increment to add to arraylist size
		//
		for(int i =0; i <= arrSize; i++)
		{
			String chat = input.nextLine();
			chatArr.add(chat);

		}

	System.out.println(Arrays.toString(chatArr.toArray()));
	}
}