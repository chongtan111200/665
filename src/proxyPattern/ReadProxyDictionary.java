package proxyPattern;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class has a proxy dictionary that stores all the words in an arrayList.
 * It only reads the file at the first time.
 * 
 * @author chong tian
 *
 */
public class ReadProxyDictionary {
	static List<String> proxyList = new ArrayList<>();

	public static void challenge(String formedString) {
		if (proxyList.isEmpty()) {
			//empty proxy, read word from file
			try {
				System.out.println("Read dictionary file");
				ReadDictionaryFile.challenge(formedString,"words.txt");

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			proxyList = ReadDictionaryFile.getList();
		} else {
			//proxy is loaded, use the proxy
			if (proxyList.contains(formedString))
				System.out.println("Challenge invalid");
			else
				System.out.println("Challenge valid");
		}
	}
}
