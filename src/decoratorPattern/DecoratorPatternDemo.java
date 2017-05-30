package decoratorPattern;

import tile.RegularTile;

public class DecoratorPatternDemo {
	public static void main(String[] args) {
		//regular tile has normal value = 1
		RegularTile regularTile = new RegularTile('t',1);
		System.out.println("Regular tile name is "+regularTile.getName());
		System.out.println("Regular tile value is "+regularTile.getValue());
		
		//cheat tile has value = 2
		CheatTile cheatTile = new CheatTile(regularTile);
		cheatTile.cheatDoubleTileValue();
	
		System.out.println("Cheat tile name is " + cheatTile.getTile().getName());
		System.out.println("Cheat tile value is "+cheatTile.getTile().getValue());
	}
}
