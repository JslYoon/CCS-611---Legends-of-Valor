package src.Inventory.Items;

// java object that represents potions

public class Potions implements Items {

    private String name;
    private int price;
    private int value;
    private String stat;
    private int level;
    private int numuse;

    // Constructor
    public Potions(String name, int price, int value, int level, String stat, int numuse) {
        this.name = name;
        this.price = price;
        this.value = value;
        this.level = level;
        this.stat = stat;
        this.numuse = numuse;
    }

    public int getPrice() { return price; }
    public String getName() { return name; }
    public int getLevel() { return level; }
    public String getType() { return "Potion"; }
    public int getValue() { return value; }
    public String getStat() { return stat; }
    public int getnumuse() { return numuse; }
    public boolean allUsed() { return numuse == 0; }

    public void use() { numuse -= 1; }

    public String toString() {
        return "Potion " + name + ", price: " + price + ", stat value: " + value + " on stat: " + stat + ", level: " + level + " usage left: " + numuse;
    }

    public int hashCode() {
        return name.hashCode();
    }

}