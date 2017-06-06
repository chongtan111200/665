package mediatorPattern;

import java.util.Date;

public class ChatRoom {
   public static void showMessage(ChatPlayer player, String message){
      System.out.println(new Date().toString() + " [" + player.getName() + "] : " + message);
   }
}