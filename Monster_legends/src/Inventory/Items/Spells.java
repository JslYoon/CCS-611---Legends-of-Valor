package src.Inventory.Items;

import javax.swing.text.html.parser.Element;

// Object that represents spells in the game

public class Spells implements Items{



    private String name;
    private int price;
    private int numuse;
    private int value;

    private Element element;
    private int level;


    public Spells(String name, int price, int value, int level, int numuse) {
        this.price = price;
        this.value = value;
        this.numuse = numuse;
        this.level = level;
        this.name = name;
    }

    public int getPrice() { return price; }
    public String getName() { return name; }
    public int getLevel() { return level; }
    public String toString() {  
        return "Spell " + name + ", price: " + price + ", damage value: " + value + ", level: " + level + " usage left: " + numuse;
    }
    public String getType() { return "Spell";}

    public int getValue() { return value; }

    public int hashCode() {
        return name.hashCode();
    }

    public int getnumuse() { return numuse; }
    public void use() { numuse--; }
}


