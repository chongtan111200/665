package proxyPattern;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 
 * Read the dictionary from a txt file.
 * Compare with the passed string, if the file has string, challenge valid.
 * Otherwise, challenge is not valid.
 * The string would be stored to the ArrayList as the proxy.
 * The file is "words.txt"
 * @author chong tian
 *
 */
public class ReadDictionaryFile {
	static List<String> resultList;
	
	public static void challenge(String formedString, String fileName) throws FileNotFoundException {
		try {
			//read file
			Scanner input = new Scanner(new File(fileName));
			int hasWordCount = 0;
			resultList = new ArrayList<>();
			while (input.hasNext()) {
				String compareString = input.nextLine();
				resultList.add(compareString);
				if (compareString.equals(formedString))
					hasWordCount++;
			}
			
			//show result
			if (hasWordCount > 0)
				System.out.println("Challenge invalid");
			else
				System.out.println("Challenge valid");
			input.close();

		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
	}
	
	/**
	 * 
	 * @return the list from reading the file
	 */
	public static List<String> getList(){
		return resultList;
	}
}
