package abstractFactory;

public class FactoryProducer {
	public static AbstractFactory getFactory(String choice) {

		if (choice.equalsIgnoreCase("PLAYER")) {
			return new PlayerFactory();

		} else if (choice.equalsIgnoreCase("TILE")) {
			return new TileFactory();
		}
		return null;
	}
}