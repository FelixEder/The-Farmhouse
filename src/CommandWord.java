
/**
 * Enumeration class CommandWord 
 * Creates enumerates for commandwords, aswell as names in different languages.
 * 
 * @author (Felix Eder)
 * @version (2015-11-11)
 */
public enum CommandWord
{
    // A value for each command word along with its
    // corresponding user interface string.
    GO("go", null), QUIT("quit", null), HELP("help", null), LOOK("look", null), SCREAM("scream", null), BACK("back", null), TAKE("take", null), DROP("drop", null), ITEMS("items", null), UNKNOWN("?", null);
    
    // The command string.
    private String commandString;
    private String translation;
    
    /**
     * Initialize with the corresponding command string and translation.
     * @param commandString The command string.
     */
    CommandWord(String commandString, String translated)
    {
        this.commandString = commandString;
        translation = translated;
    }
    
    /**
     * @return The command word as a string.
     */
    public String toString()
    {
        return commandString;
    }
    
    /**
     * @return The translated word as string.
     */
    public String getTranslation() {
        return translation;
    }
}

