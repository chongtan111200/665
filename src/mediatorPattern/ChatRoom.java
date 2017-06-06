package mediatorPattern;

import java.util.Date;

/**
 * a chat room in the game allows players to communicate
 * This is the mediator
 * @author chong tian
 *
 */
public class ChatRoom {
   public static void showMessage(ChatPlayer player, String message){
      System.out.println(new Date().toString() + " [" + player.getName() + "] : " + message);
   }
}