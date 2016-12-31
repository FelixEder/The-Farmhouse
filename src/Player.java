import java.util.HashMap;
import java.util.Stack;
/**
 * The class Player stores all information important for the player in the game.
 * 
 * @author (Felix Eder) 
 * @version (2015-11-11)
 */
public class Player
{
    // instance variables - responsible for storing a variety of information important for the player.
    private Room currentRoom;
    private Stack<Room> roomsStacked;
    private String name;
    private int maxWeigth;
    private HashMap<String, Item> inventory;
    private int currentWeigth;
    private boolean screamedWithFork;

    /**
     * Constructor for for class Player, takes one parameter, all other fields have preset values.
     * @param name The name of the player.
     */
    public Player(String name)
    {
        this.name = name;
        maxWeigth = 45;
        currentWeigth = 0;
        roomsStacked = new Stack<Room> ();
        inventory = new HashMap<String, Item> ();
        screamedWithFork = false;
    }
    
    /**
     * Sets the field currentRoom to the parameter room.
     * @param room The that the player currently is in.
     */
    public void setCurrentRoom(Room room) {
        currentRoom = room;
    }
    
    /**
     * Returns the current room the player is in.
     * @return The current room the player inhabits.
     */
    public Room getCurrentRoom() {
         return currentRoom;
    }

    /**
     * Adds the player's current room to a Stack of rooms.
     */
    public void addRoomToStack() {
        roomsStacked.push(currentRoom);
    }
    
    /**
     * Sets the current room to the room on top of the stash.
     */
    public void removeRoomFromStack() {
        currentRoom = roomsStacked.pop();
    }
    
    /**
     * Checks whether the stack of rooms is empty.
     * @return The boolean value true if stack is empty, returns false if it is not.
     */
    public boolean checkIfEmpty() {
        return roomsStacked.empty();
    }
    
    /**
     * If the item doesn't exceed player maxWeigth, adds item to inventory and adds its weigth to player currentWeigth.
     * Also prints out information.
     * Adds an item to inventory, add its weigth and prints out information 
     * If item is to heavy, print error message.
     * @param roomItem The item the player wants to add to inventory.
     */
    public void addItemToInventory(String roomItem) {
        Item itemToAdd = currentRoom.getRoomItem(roomItem);
        if (itemToAdd.getWeigth() + currentWeigth <= maxWeigth) {
            inventory.put(itemToAdd.getName(), itemToAdd);
            currentWeigth += itemToAdd.getWeigth();
            currentRoom.removeItemFromRoom(itemToAdd.getName());
            System.out.println("You picked up " + itemToAdd.getName() + "." + "\n");
        }  
        else {
            System.out.println("The item is to heavy!" + "\n");
        }
    }
    
    /**
     * Checks whether an item is in inventory or not.
     * @param invItem The item the player wants to check.
     * @return The boolean value true if the item is in inventory, returns false if it is not.
     */
    public boolean isItemInInventory(String invItem) {
        return inventory.containsKey(invItem);
    }
    
    /**
     * Removes an item from inventory, removes item weight from player current weight, and prints out information.
     * @param invItem The item the player want to remove from inventory.
     */
    public void removeItemFromInventory(String invItem) {
        Item itemToDrop = inventory.remove(invItem);
        currentRoom.addItem(itemToDrop.getName(), itemToDrop);
        currentWeigth -= itemToDrop.getWeigth();
        System.out.println("You dropped " + itemToDrop.getName() + "." + "\n");
    }
    
    /**
     * Returns a String with all items currently in inventory.
     * @return A string with all items in inventory separated by space.
     */
    public String getInventoryString() {
        String returnString = "Inventory:";
        for(String key : inventory.keySet()) {
            returnString += " " + key;
        }
        return returnString;
    }
    
    /**
     * Increases maxWeigth with 300 and sets screamedWithFork to true.
     */
    public void increaseMaxWeigth() {
        maxWeigth += 300;
        screamedWithFork = true;
    }
    
    /**
     * Returns the boolean screamedWithFork.
     * @return The boolean value screamedWithFork is set to.
     */
    public boolean hasScreamedWithFork() {
        return screamedWithFork;
    }
}
