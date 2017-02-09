/**
 *  This class is the main class of "The farmhouse" application. 
 *  "The farmhouse" is a  simple, text based adventure game.  Users 
 *  can walk around some scenery, pick up items, and scream.
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initializes all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  (Felix Eder)
 * @version (2015-11-11)
 */


public class Game {
    private Parser parser;
    private Player player;
    
    /**
     * Create the game and initialize its internal map.
     */
    public Game(String playerName) {
        player = new Player(playerName);
        parser = new Parser();
        createRooms();
    }

    /**
     * Create all the roo
     * ms and link their exits together.
     * Also creates items specific to certain rooms.
     */
    private void createRooms() {
        Room basement, kitchen, bedroom, garden, shed;
        
        // create the rooms
        basement = new Room("in an unbelivably well-lit basement.");
        kitchen = new Room("in a kitchen, the amount of cutlery is unsettling.");
        bedroom = new Room("in a bedroom, who has two king-sized beds in the same room?");
        garden = new Room("in a garden. It's in the middle of a starry night, so why is the garden lights on?");
        shed = new Room("in a fragile old shed, the inside of it seems a lot larger than it should be.");
        
         // Initialize room exits
        basement.setExit("up", kitchen);
        kitchen.setExit("down", basement);
        kitchen.setExit("up", bedroom);
        kitchen.setExit("out", garden);
        bedroom.setExit("down", kitchen);
        garden.setExit("in", kitchen);
        garden.setExit("west", shed);
        shed.setExit("east", garden);
        
         //Add unique items to rooms
        basement.createHardToSeeKey();
        kitchen.createFork();
        bedroom.createSunGlasses();
        garden.createToaster();
        
        player.setCurrentRoom(basement);  // start game in basement.
    }
     
    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        Main.printGameInfo("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
    	Main.printGameInfo("");
    	Main.printGameInfo("Welcome to The farmhouse!");
    	Main.printGameInfo("The farmhouse is a new, mysterious adventure game.");
    	Main.printGameInfo("Type '" + parser.getSpecificKey() + "' if you need help.");
    	Main.printGameInfo("Also don't forget to scream once in a while ;)");
    	Main.printGameInfo("");
        printLocationInfo();
        Main.printGameInfo("");
    }
    
    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;
   
        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) {
            case UNKNOWN:
            	Main.printGameInfo("I don't know what you mean..." + "\n");
                break;

            case HELP:
                printHelp();
                break;

            case GO:
                goRoom(command);
                break;

            case QUIT:
                wantToQuit = quit(command);
                break;
                
            case LOOK:
                look();
                break;
                
            case SCREAM:
                wantToQuit = scream();
                break;
                
            case BACK:
                back(command);
                break;
                
            case TAKE:
                pickUpItem(command);
                break;
                
            case DROP:
                dropItems(command);
                break;
                
            case ITEMS:
            	Main.printGameInfo(player.getInventoryString() + "\n");
                break;
        }
        return wantToQuit;
    }
  
    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
    	Main.printGameInfo("You are lost. You are alone. You wander around in a farmhouse.");
    	Main.printGameInfo("Your command words are:");
        parser.showCommands();
        Main.printGameInfo("\n" + "\n");
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     * Some rooms require certain items to access.
     * @param command The command typed by the player.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
        	Main.printGameInfo("Go where?" + "\n");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = player.getCurrentRoom().getExit(direction);
        if (nextRoom == null) {
        	Main.printGameInfo("There is no door!" + "\n");
        }
        else if(direction.equals("west")) {
            if(player.isItemInInventory("key")){
            	Main.printGameInfo("You use the key to unlock the mysterious door." + "\n");
                player.addRoomToStack();
                player.setCurrentRoom(nextRoom);
                printLocationInfo();
            }
            else {
            	Main.printGameInfo("You arrive at a small cabin in the woods, but its door seems to be locked!");
            	Main.printGameInfo("To scared to stay, you decide to head back to the garden" + "\n");
            }
        }
        
        else {
            player.addRoomToStack();
            player.setCurrentRoom(nextRoom);
            printLocationInfo();
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
        	Main.printGameInfo("Quit what?" + "\n");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
    
    /**
     * Prints information regarding current room.
     */
    private void printLocationInfo() {
    	Main.printGameInfo(player.getCurrentRoom().getLongDescription());
    }
    
    /**
     * Prints out the current information regarding the location.
     */
    private void look() {
        printLocationInfo();
    }
    
    /**
     * Prints out four different messages depending on player location and inventory, also quits game under right circumstances.
     * @return The boolean value true if player is located in shed and has toaster in inventory.
     */
    private boolean scream() {
        if(player.isItemInInventory("fork") && !player.hasScreamedWithFork()) {
            player.increaseMaxWeigth();
            Main.printGameInfo("With fork in hand, you scream your lungs out,");
            Main.printGameInfo("with your new-found encouragement, nothing is to heavy for you!" + "\n");
            return false;
        }
        else if(player.isItemInInventory("toaster")) {
                if(player.getCurrentRoom().inShed("east")) {
                	Main.printGameInfo("You once again perform your now famous shout");
                	Main.printGameInfo("and throw the toaster with all your might into the inner wall of the shed.");
                    Main.printGameInfo("A secret descending staircase reveals itself behind the fake-wall.");
                    Main.printGameInfo("You go down the stairs to find a family enjoying a movie in their secret underground IMAX-theatre.");
                    Main.printGameInfo("You suddenly remember that you are part of this family");
                    Main.printGameInfo("and only went to the basement to get soda, you must have fallen asleep there.");
                    Main.printGameInfo("Oh well, you sit down in a comfy seat with your family.");
                    Main.printGameInfo("Also, the sunglasses are actually 3D-glasses, enjoy the movie!");
                    Main.printGameInfo("\n" + "The game is now over, you won!");
                    return true;
                }
                else {
                    Main.printGameInfo("You scream, but this time you feel the urge to throw the toaster at something." + "\n");
                    return false;
                }
            }
                    
        else {
            Main.printGameInfo("You shout in a high-pitched tone, but in the farmhouse no one can hear you scream." + "\n");
            return false;
        }
    }
    
    /**
     * If player has left starting area, goes back to the previous room.
     * @param command The command written by the player.
     */
    private void back(Command command) {
        if(command.hasSecondWord()) {
            if(command.getSecondWord().equals("back")) {
                Main.printGameInfo("Take it easy, you shouldn't double-back through the house, might step on something!" + "\n");
            }
            else {
                Main.printGameInfo("Back where?" + "\n");
        }
       }
        
        else if(!player.checkIfEmpty()) {
            player.removeRoomFromStack();
            printLocationInfo();
        }
        
        else {
            Main.printGameInfo("You are in room you started in, you tell me how you would like to go back even further..." + "\n" );
        }
    }
    
    /**
     * Picks up an item and adds to player inventory. 
     * In some cases an other item maybe needed in inventory.
     * @param command The command written by the player.
     */
    private void pickUpItem(Command command) {
        if(!command.hasSecondWord()) {
            Main.printGameInfo("Take what?" + "\n");
        }
        else if(player.getCurrentRoom().isItemInRoom(command.getSecondWord())) {
                if(command.getSecondWord().equals("key")){
                    if(player.isItemInInventory("sunglasses")){
                        Main.printGameInfo("Since you have put on sunglasses, you can easily see the key midst all the bright light." + "\n");
                        player.addItemToInventory(command.getSecondWord());
                    }
                    else {
                        Main.printGameInfo("You fumble with your arms, but since the light is so bright, you find jack squat." + "\n");
                    }
                    }
                else {
                    player.addItemToInventory(command.getSecondWord());
                } 
        }
        else {
            Main.printGameInfo("The mentioned item is not located in the room!" + "\n");
        }
    }
    
    /**
     * Drops an item from player inventory.
     * @param command The command written by the player.
     */
    private void dropItems(Command command) {
        if(!command.hasSecondWord()) {
            Main.printGameInfo("Drop what?" + "\n");
        }
        else if(player.isItemInInventory(command.getSecondWord())) {
            player.removeItemFromInventory(command.getSecondWord());
       }
        
        else {
            Main.printGameInfo("The mentioned item is not in your inventory!" + "\n");
        }
    }
}