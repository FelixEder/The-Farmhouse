import javafx.application.Application;


public class ConsoleThread extends Thread{
	private Console console;
		public ConsoleThread() {
			console = new Console();
			Application.launch(Console.class);
		}
		
		public void printToConsole(String text) {
			console.printGameInfo(text);
		}
		
		public String textFromConsole() {
			return console.getTextField();
		}
	
}
