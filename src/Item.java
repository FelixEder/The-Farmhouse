/**
 * This class is responsible for creating the items in the games and to store their unique information in 
 * three different instance fields.
 * 
 * @author (Felix Eder) 
 * @version (2015-11-11)
 */
public class Item
{
    // instance variables - the weigth, description and name for each item is stored here.
    private int weigth;
    private String description;
    private String name;

    /**
     * The constructor for class Item, takes three parameters.
     * @param weigth The weigth of the item.
     * @param name The name of the item.
     * @param description A description of the item.
     */
    public Item(int weigth, String name, String description) {
        this.weigth = weigth;
        this.name = name;
        this.description = description;
    }
    
     /**
      * This method returns the description of the item.
     * @return The description of the item.
     */
    public String getDescription()
    {
        return description;
    }
    
    /**
     * This method returns the weigth of the item.
     * @return The weigth of the item.
     */
    public int getWeigth() {
        return weigth;
    }
    
    /**
     * This method returns the name of the item.
     * @ The name of the item.
     */
    public String getName() {
        return name;
    }
 }
