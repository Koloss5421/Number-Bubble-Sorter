
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Scanner;
/**
 * Simple Number Bubble Sorter
 * 
 * Description:
 * On startup, the program the program asks for a string of Numbers.
 * Once entered, it checks if the string contains anything other than numbers, if so, it asks for another string.
 * Upon successful entry, it moves over the string by pairs of characters and converts them to Integers, then checks if
 * the first character is greater than the second, If so it moves the first to the second position and the second to
 * the first position.
 * If the first character is not greater than the second it adds to the counter. On every iteration the counter resets
 * to zero. 
 * If at the end of the iteration the counter is the same as the length of the string the sorting is over.
 * @author John "Koloss" Hardin
 *
 */
public class Main {
	
	public static long startTime = System.currentTimeMillis(); // get the time the program started

	public static void main(String[] args) {
		
		boolean stringReceived = false; //used to trigger the while statement to stop
		Scanner scanner = new Scanner(System.in);//Used to read the input
		String input = "";//Initialization of the input string
		
		while(!stringReceived) {//While the string isn't accepted continue asking
			System.out.println("Input a string of numbers(123456789) >");
			input = scanner.nextLine();//Reads the line from the scanner on enter and sets the input string
			
			if (input.matches("\\d+")) {// checks if the input string only contains numbers of any size
				stringReceived = true;// if it does allow it to leave the while loop
			}
			else {
				System.out.println("String should only contain numbers!");// if not tell the user and ask for another.
			}
		}
		
		if(stringReceived) {// if the string was accepted tell the user and run the sorting algorithm 
			System.out.println("String accepted!");
			sortString(input);

		}
		

	}

	private static void sortString(String input) {
		StringBuilder unSorted = new StringBuilder(input);// convert the input string into a StringBuilder to play with the chars.
		boolean sorted = false; // Used to leave while loop when the string comes back sorted.
		
		while(!sorted) {
			int count = 0;// used to count the number of characters in the correct position to make sure the string is sorted.
			for(int i = 0; i < input.length(); i++) {
				if(i == 0) {// Reset the counter on every new iteration.
					count = 0;
				}
				int next = i;
				if (next + 1 < input.length()) {// checks if the the next index is within bounds of the length of the string.
					next++;// if so makes next i + 1;
				}
				int first = Character.getNumericValue(unSorted.charAt(i)); // convert the character @ index i to an Int
				int second = Character.getNumericValue(unSorted.charAt(next)); // convert the character @ index i + 1 to an Int

				if (first > second) { // see if the first character is greater than the second
					unSorted.setCharAt(next, (char)(first + 48));// move the first character to the second character location
					unSorted.setCharAt(i, (char)(second + 48));// move the second character to the first location
				} else {
					count++;// if the characters are in the right location count the position as correct
				}
				
				System.out.println(unSorted); // print the current iteration of the string to see progression
			}
			if(count == unSorted.length()) { // check if the number of correct positions == the number of characters in the string.
				sorted = true; // if so end the loop by setting sorted to true.
				System.out.println("String Sorted!"); // tell the user the string was sorted.
			}
		}
		long endTime = System.currentTimeMillis(); // get the current time
		NumberFormat formatter = new DecimalFormat("#0.00000"); // create a new format for the 
		
		System.out.println("Orig. String: " + input); // tell the user the original string.
		System.out.println("Sorted String: " + unSorted); // tell the user the sorted string.
		System.out.println("Time: " + formatter.format((endTime - startTime) / 1000d) + " seconds"); // tell the user how long it took to complete
		System.out.println("# of Chars: " + input.length());
	}
}
