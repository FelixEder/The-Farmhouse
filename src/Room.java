import java.util.HashMap;
/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of "The Farmhouse" application. 
 * "The Farmhouse" is a simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled up, down 
 * east, west, in, out.  For each direction, the room stores a reference
 * to the neighboring room in a HashMap.
 * 
 * @author  (Felix Eder)
 * @version (2015-11-11)
 */
public class Room 
{
    private String description;
    private HashMap<String, Room> exits;
    private HashMap<String, Item> itemsInRoom;
    
    /**
     * Creates the rooms with one parameter.
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<String, Room> ();
        itemsInRoom = new HashMap<String, Item> ();
    }

    /**
     * Define an exit from this room.
     * @direction The direction of the exit.
     * @param neighbor The room in the given direction.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }


    /**
     * @param The direction the player wants to go.
     * @return Returns the room associated with the directon for the current room the player is in.
     */
    public Room getExit(String direction) {
        return exits.get(direction);
    }
    
    /**
     * @return The current exits available for the player.
     */
    public String getExitString() {
        String returnString = "Exits:";
        for(String key : exits.keySet()) {
            returnString += "   " + key;
        }
        return returnString;
    }
    
    /**
     * @return The items currently in the room.
     */
    public String getItemString() {
        String returnString = "";
        for(Item listItems : itemsInRoom.values()) {
            returnString += listItems.getDescription() + "\n";
        }
        return returnString;
    }
    
    /**
     * Return a long description of this room, of the form:
     *  You are in the kitchen.
     *  Exits: up east
     *  Items: fork
     *  @return A description of the room, including exits and items.
     */
    public String getLongDescription() {
        return "You are " + description + "\n" + getItemString() + "\n" + getExitString() + "\n";
    }
 
    /**
     * Creates the item sunglasses and adds it to current room.
     */
    public void createSunGlasses() {
        Item sunGlasses = new Item(10, "sunglasses","After searching, you find a pair of sunglasses under one of the beds.");
        itemsInRoom.put(sunGlasses.getName(), sunGlasses);
    }
    
    /**
     * Creates the item fork and adds it to current room.
     */
    public void createFork() {
        Item fork = new Item(20, "fork", "Navigating through the kitchen, you see a shiny fork lying on the stove.");
        itemsInRoom.put(fork.getName(), fork);
    }
    
    /**
     * Creates the item hardToSeeKey and adds it to current room.
     */
    public void createHardToSeeKey() {
        Item hardToSeeKey = new Item(15, "key", "You glimpse a key, but it is very hard to see because of the high brightness.");
        itemsInRoom.put(hardToSeeKey.getName(), hardToSeeKey);
    }
    
    /**
     * Creates the item toaster and adds it to current room.
     */
    public void createToaster() {
        Item toaster = new Item(300, "toaster", "A toaster can be plainly seen lying in the grass, but is seems to be half-buried.");
        itemsInRoom.put(toaster.getName(), toaster);
    }
    
    /**
     * Checks whether item is in room.
     * @param The item the player wants to check.
     * @return The boolean value true if item is in room, return false if not.
     */
    public boolean isItemInRoom(String roomItem) {
        return itemsInRoom.containsKey(roomItem);
    }
    
    /**
     * Returns the item asked for by the player.
     * @param secondWord The item the player wants to pick up.
     * @return The item the player picked up.
     */
    public Item getRoomItem(String secondWord) {
        return itemsInRoom.get(secondWord);
    }
    
    /**
     * Removes the item asked for by the player from the room.
     * @param The item to remove from the room.
     */
    public void removeItemFromRoom(String itemName) {
        itemsInRoom.remove(itemName);
    }
    
    /**
     * Adds an item to the room.
     * @param itemName The String name of the item to add to the room.
     * @param The object item to add to the room.
     */
    public void addItem(String itemName, Item actualItem) {
        itemsInRoom.put(itemName, actualItem);
    }
    
    /**
     * Checks whether player is in the shed.
     * @param directionKey The direction associated with the different rooms.
     * @return The boolean true if player is in shed, return false if not.
     */
    public boolean inShed(String directionKey) {
        return exits.containsKey(directionKey);
    }
}
