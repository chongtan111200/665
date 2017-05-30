package adapterPattern;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import proxyPattern.ReadDictionaryFile;
import tile.RegularTile;

/**
 * Read the tile file from the ReadDictionaryFile class
 * @author chong tian
 *
 */
public class ReadTileFileAdapter {
	static List<RegularTile> resultList = new ArrayList<>();

	public static void readTileFile() {
		try {
			// use read dictionary file to
			ReadDictionaryFile.challenge("tile", "tiles.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		List<String> tileFileList = ReadDictionaryFile.getList();
		for (String s : tileFileList) {
			String[] temp = s.split(" ");
			RegularTile testTile = new RegularTile(temp[0].charAt(0), Integer.parseInt(temp[1]));
			resultList.add(testTile);
		}
	}

	/**
	 * 
	 * @return result list containing the regular tiles.
	 */
	public static List<RegularTile> getResultTile() {
		return resultList;
	}
}
