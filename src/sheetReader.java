	import java.io.BufferedReader;
	import java.io.FileReader;
	import java.io.IOException;
	import java.util.ArrayList;
	
	public class sheetReader {
	  
		public static void main(String[] args) {
	 
		String line = "";
	    String splitBy = ",";
	    ArrayList<String> words = new ArrayList<>();


	    try {
	      //parsing a CSV file into BufferedReader class constructor  
	      BufferedReader br = new BufferedReader(new FileReader("/Users/abigailmcphee/Desktop/nouns_list.csv"));
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
	    
	  }
	}
