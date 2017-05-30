package adapterPattern;

import java.util.List;
import tile.RegularTile;
/**
 * the file saving the the tiles is tiles.txt
 * the format is like the following
 * a 1
 * b 2
 * c 1
 * d 1
 * this class shows the result of the tiles created from the txt file
 * @author chong tian
 *
 */
public class ReadTileFileAdapterDemo {
	
	public static void main(String[] args) {
		ReadTileFileAdapter.readTileFile();
		List<RegularTile> list = ReadTileFileAdapter.getResultTile();
		for(RegularTile r: list){
			System.out.println(r);
		}
		
	}
}
