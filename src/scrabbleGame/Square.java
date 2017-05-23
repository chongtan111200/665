package scrabbleGame;

import tile.SpecialTile;
import tile.Tile;

/**
 * This is the class for a square, each is a unit in the board
 * @author chong tian
 *
 */
public class Square {
	private String name;
	private int value;
	private Tile tile;
	private static final int MAX_VALUE=3;
	private SpecialTile spTile;
	private int tileX;
	private int tileY;
	
	/**
	 * Constructor creates a new square with name and value
	 * @param value is the value of the square
	 * @param name is the tile on the square
	 */
	public Square(int value, String name){
		if( value<=0 || value>MAX_VALUE){
			throw new IllegalArgumentException("illegal move, please make sure 0<x<15, 0<y<15"
					+ " and 0<value<=3");
		}
		this.name = name;
		this.value=value;
		this.tile=null;
		this.tileX=-1;
		this.tileY=-1;
	}


	/**
	 * 
	 * @return value
	 */
	public int getValue(){
		return value;
	}
	
	/**
	 * 
	 * @return name
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * 
	 * @return a tile
	 */
	public Tile getTile(){
		return tile;
	}
	
	/**
	 * 
	 * @return special tile
	 */
	public SpecialTile getSPTile(){
		return spTile;
	}
	
	/**
	 * check if certain square contains a tile
	 * @return true if contains a tile
	 */
	public boolean hasRegularTile(){
		return tile!=null;
	}
	
	/**
	 * check if certain square contains a special tile
	 * @param tile regular tile
	 */
	public void setRegularTile(Tile tile){
		this.tile=tile;
	}
	
	/**
	 * 
	 * @return true if has special tile
	 */
	public boolean hasSPTile(){
		return spTile!=null;
	}
	
	/**
	 * 
	 * @param x coordinate
	 */
	public void setTileX(int x){
		this.tileX=x;
	}
	
	/**
	 * 
	 * @param y coordinate
	 */
	public void setTileY(int y){
		this.tileY=y;
	}
	
	/**
	 * 
	 * @return x
	 */
	public int getTileX(){
		return tileX;
	}
	
	/**
	 * 
	 * @return y
	 */
	public int getTileY(){
		return tileY;
	}
	
	/**
	 * add special tiles on squares
	 * @param spTile speical tile
	 */
	public void setSPTile(SpecialTile spTile){
		this.spTile=spTile;
	}

	@Override
	public String toString(){
		return name + value;
	}
	
	@Override
	public boolean equals(Object o){
		if(o instanceof Square){
			Square s=(Square)o;
			return s.tileX==this.tileX && s.tileY==this.tileY;
		}
		return false;
	}
	
	@Override
	public int hashCode(){
		return this.tileX*31+this.tileY;
	}
}
