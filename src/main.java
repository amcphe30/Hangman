
import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.*;
import java.net.URL;
import java.io.File;


public class main {
	
	private static char[] wordArray; //target word in a character array
	private static String targetWord; 
	private static int errorNum = 0; //current errors
	private static final int MAXERRORS = 6; //game over
	private static char[] wordState; //the blanks and guess letters
	
	public static void main(String[] args) {
		System.out.println("hello! Welcome to hangman!");
		
		Scanner myScan = new Scanner(System.in);
		//System.out.println("What level would you like to play (easy, medium, or hard)?");

		//String level = myScan.nextLine();
		//System.out.println("Level " + level + ":");

		wordArray = assignWord();

		JFrame frame = new JFrame("hangman");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300,300);
		JButton button = new JButton("start");
		frame.getContentPane().add(button); // Adds Button to content pane of frame
		frame.setVisible(true);
		
		while (errorNum < MAXERRORS) {
			printGameState();
			System.out.println("guess a letter:");
			String guess = myScan.nextLine();
			if (contains(guess)) {
				updateWordState(guess);
				if (win()) {
					System.out.println("congrats!");
					break;
				} 
			} else {
				errorNum++;
			}
			
		}
		
		System.out.println("game over");
		
	}
	
	//prints the stand,hang man, and word state
	public static void printGameState() {
		if (errorNum == 0) {
			System.out.println("   —————\n"
					+ "   |   |\n"
					+ "   |\n"
					+ "   |\n"
					+ "   |\n"
					+ "   |\n"
					+ "   |\n"
					+ "  ————");
		} else if (errorNum == 1) {
			System.out.println("   —————\n"
					+ "   |   |\n"
					+ "   |   O\n"
					+ "   |\n"
					+ "   |\n"
					+ "   |\n"
					+ "   |\n"
					+ "  ————");
		} else if (errorNum == 2) {
			System.out.println("   —————\n"
					+ "   |   |\n"
					+ "   |   O\n"
					+ "   |   |\n"
					+ "   |\n"
					+ "   |\n"
					+ "   |\n"
					+ "  ————");
		} else if (errorNum == 3) {
			System.out.println("   —————\n"
					+ "   |   |\n"
					+ "   |   O\n"
					+ "   |   |\n"						
					+ "   |   /\n"
					+ "   |\n"
					+ "   |\n"
					+ "  ————");
		} else if (errorNum == 4) {
			System.out.println("   —————\n"
					+ "   |   |\n"
					+ "   |   O\n"
					+ "   |   |\n"						
					+ "   |   /\\\n"
					+ "   |\n"
					+ "   |\n"
					+ "  ————");
		} else if (errorNum == 5) {
			System.out.println("   —————\n"
					+ "   |   |\n"
					+ "   |   O\n"
					+ "   |  /|\n"						
					+ "   |   /\\\n"
					+ "   |\n"
					+ "   |\n"
					+ "  ————");
		} else if (errorNum == 6) {
			System.out.println("   —————\n"
					+ "   |   |\n"
					+ "   |   O\n"
					+ "   |  /|\\\n"						
					+ "   |   /\\\n"
					+ "   |\n"
					+ "   |\n"
					+ "  ————");
			System.out.println("loser");
			}

		System.out.print("	");
		System.out.println(wordState);
		
	}
	
	public static char[] assignWord() {
		
		assignRandomWord();
		
		char[] wordArray = new char[targetWord.length()];
		
		for (int i = 0; i < targetWord.length(); i++) {
			wordArray[i] = targetWord.charAt(i);
		}
		
		updateWordState(" ");
		
		return wordArray;
		
	}
	
	public static void updateWordState(String guess) {
		//initialize word state
		if (guess.equals(" ")) {
			wordState = new char[targetWord.length()];
			for (int i = 0; i< targetWord.length(); i++) {
				wordState[i] = '-';
			}
		}
		
		//traverse wordState and replace blanks with guess
		for (int i = 0; i < targetWord.length(); i++) {
			if (targetWord.charAt(i) == guess.charAt(0)) {
				wordState[i] = wordArray[i];
			}
		}
	}
	
	public static boolean contains(String guess) {
		for (int i = 0; i < targetWord.length(); i++) {
			if (targetWord.charAt(i) == (guess.charAt(0))) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean win() {
		for (int i = 0; i < targetWord.length(); i++) {
			if (wordState[i] == '-') {
				return false;
			}
		}
		return true;
	}
	
	public static void assignRandomWord() {
		 
		String line = "";
	    String splitBy = ",";
	    ArrayList<String> words = new ArrayList<>();


	    try {
	      //parsing a CSV file into BufferedReader class constructor  
	      // BufferedReader br = new BufferedReader(new FileReader("/Users/abigailmcphee/Desktop/nouns_list.csv"));
	      BufferedReader br = new BufferedReader(new FileReader("src/res/wordbank.csv"));
	      while ((line = br.readLine()) != null) {
	    	words.add(line);
	      }
	    }
	    catch(IOException e) {
	      e.printStackTrace();
	    }
	    
        System.out.println(words.size());
	    int numOfWords = words.size();
	    int min = 0;
	    int max = numOfWords;
	    
	    int randWord = min + (int)(Math.random() * ((max - min) + 1));
        
        System.out.println(words.get(randWord));
        targetWord = words.get(randWord);
	    
	}

	
	

}