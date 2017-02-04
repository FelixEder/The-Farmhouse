import java.util.HashMap;

/**
 * This class is part of "The Farmhouse" application. 
 * "The Farmhouse" is a simple, text based adventure game.  
 * 
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognize commands as they are typed in.
 *
 * @author  (Felix Eder)
 * @version (2015-11-11)
 */

public class CommandWords
{
    // A mapping between a command word and the CommandWord
    // associated with it.
    private HashMap<String, CommandWord> validCommands;

    /**
     * Constructor - initialize the command words.
     */
    public CommandWords()
    {
        validCommands = new HashMap<String, CommandWord>();
        for(CommandWord command : CommandWord.values()) {
            if(command != CommandWord.UNKNOWN) {
                if(command.getTranslation() != null) {
                validCommands.put(command.toString(), command);
                validCommands.put(command.getTranslation(), command);
            }
            else {
                validCommands.put(command.toString(), command);
            }
         }
      }
    }

    /**
     * Find the CommandWord associated with a command word.
     * @param commandWord The word to look up.
     * @return The CommandWord corresponding to commandWord, or UNKNOWN
     *         if it is not a valid command word.
     */
    public CommandWord getCommandWord(String commandWord)
    {
        CommandWord command = validCommands.get(commandWord);
        if(command != null) {
            return command;
        }
        else {
            return CommandWord.UNKNOWN;
        }
    }
    
    /**
     * Check whether a given String is a valid command word. 
     * @return true if it is, false if it isn't.
     */
    public boolean isCommand(String aString)
    {
        return validCommands.containsKey(aString);
    }

    /**
     * Print all valid commands to System.out.
     */
    public void showAll() 
    {
        for(String command : validCommands.keySet()) {
        	Main.printGameInfo(command + "  ");
        }
        Main.printGameInfo("/n");
    }
    
    /**
     * @return The command associated with the HELP-command.
     */
    public String getHelpString() {
        return CommandWord.HELP.toString();
    }
}
