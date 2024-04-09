package src.Entities;

import src.Inventory.Inventory;
import src.Inventory.Items.Items;

// java interface indicating entities for the game.
// Entities range from heros to monsters and even neutral beings like markets

public interface Entities {
    
    // Name
    public String getName();

    // Stats
    public int getLevel();
    public int getHP();
    public int getMP();
    public int getGold();

    public void displayStats();

    public boolean fightable();
    
    public String toString();

    public Inventory getInventory();
    public void viewInventory();
    public void gainExp(int exp);
    public void gainGold(int gold);

    public int Attack(Items i);
    public int Defence(int dmg);
}
