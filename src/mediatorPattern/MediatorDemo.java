package mediatorPattern;

public class MediatorDemo {
	public static void main(String[] args) {
		ChatPlayer p1 = new ChatPlayer("player1");
		ChatPlayer p2 = new ChatPlayer("player2");

		p1.sendMessage("Please surrender! player2!");
		p2.sendMessage("No way! player1!");
	}
}