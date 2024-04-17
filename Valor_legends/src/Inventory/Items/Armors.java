package src.Inventory.Items;
import java.util.HashMap;


// Java object that represents armors

public class Armors implements Items {

    private String name;
    private int price;
    private int defence;
    private int level;


    public Armors(String name, int price, int defence, int level) {

        this.name = name;
        this.price = price;
        this.defence = defence;
        this.level = level;

    }

    // accessor methods
    public int getLevel() {return level;}
    public int getPrice() {return price;}
    public String getName() {return name;}

    public String getType() { return "Armor"; }
    public int getValue() { 
        return defence;
    }

    public String toString() {
        return "Armor " + name + ", price: " + price + ", defence value: " + defence + ", level: " + level;
    }

    public int hashCode() {
        return name.hashCode();
    }
    
}
