package abstractFactory;

/**
 * concrete class for the factory
 * it constructs the factory according to the choice in the run time
 * @author chong tian
 *
 */
public class FactoryProducer {
	public static AbstractFactory getFactory(String choice) {

		//produce player
		if (choice.equalsIgnoreCase("PLAYER")) {
			return new PlayerFactory();
			
		//produce tile
		} else if (choice.equalsIgnoreCase("TILE")) {
			return new TileFactory();
		}
		return null;
	}
}