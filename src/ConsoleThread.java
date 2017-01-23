import javafx.application.Application;


public class ConsoleThread extends Thread{
		public ConsoleThread() {
			Application.launch(Console.class);
		}
		
		public void printToConsole(String text) {
			Console.printGameInfo(text);
		}
		
		public String textFromConsole() {
			return Console.getTextField();
		}
	
}
