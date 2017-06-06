package mediatorPattern;

/**
 * a speical palyer that can talk to each other
 * @author chong tian
 *
 */
public class ChatPlayer {
	   private String name;
	   
	   public ChatPlayer(String name){
		      this.name  = name;
		   }
	   public String getName() {
	      return name;
	   }

	   public void setName(String name) {
	      this.name = name;
	   }



	   public void sendMessage(String message){
	      ChatRoom.showMessage(this,message);
	   }
	}
