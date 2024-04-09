package src.Inventory.Items;

import java.util.HashMap;

// java object that represents weapons

public class Weapons implements Items {

    private String name;
    private int price;
    private int offence;
    private int level;
    private String roleReq;


    public Weapons(String name, int price, int offence, int level) {

        this.name = name;
        this.price = price;
        this.offence = offence;
        this.level = level;

    }

    // accessor methods
    public int getPrice() {return price;}
    public String getName() {return name;}
    public int getLevel() {return level;}
    public String getType() { return "Weapon"; }
    public int getValue() { 
        return offence;
    }

    public String toString() {
        return "Weapon " + name + ", price: " + price + ", offence value: " + offence + ", level: " + level;
    }

    public int hashCode() {
        return name.hashCode();
    }
}
