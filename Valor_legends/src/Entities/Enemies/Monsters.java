package src.Entities.Enemies;

import java.util.List;
import java.util.stream.Collectors;

import src.Entities.Entities;
import src.Entities.Players.Heros;
import src.Inventory.Inventory;
import src.Inventory.Items.Items;
import src.Misc.RandomSelection;
import src.Misc.dbOperations;

// java object to indicate monsters

public class Monsters implements Entities{

    private int level;
    private int str;
    private int gold;
    private int hp;
    private int dodge;
    private String name;
    private int def;
    private String element;
    
    public Monsters(Heros h) {

        
        List<String> monsterCandidates = dbOperations.readMonstersFile().stream() 
                                        .filter(monster -> Integer.parseInt(monster.split(",")[1]) > h.getLevel() - 5 &&       
                                            Integer.parseInt(  monster.split(",")[1])< h.getLevel() + 5) 
                                        .collect(Collectors.toList());

        int num = RandomSelection.getRandomNumberInRange(0, monsterCandidates.size()-1);
        String[] stats = monsterCandidates.get(num).split(",");

        name = stats[0];
        level = Integer.parseInt(stats[1]);
        hp = Integer.parseInt(stats[3]);
        str = Integer.parseInt(stats[4]);
        def = Integer.parseInt(stats[5]);
        dodge = Integer.parseInt(stats[6]);
        element = stats[6];

        gold = level * 100;
        
    }

    public int Attack(Items i) {
        return (int)(str * RandomSelection.getRandomMultiplier() * 10);
    }
    public int Defence(int dmg) {
        int damage = (int)(dmg * (1 + (def * 0.05 + dodge * 0.05)));
        hp -= damage;
        if(hp <= 0) {
            hp = 0;
        }
        return damage;
    }
    
    public String toString() {
        return name + "(lvl: " + level + ") (HP: " + hp + ")";
    }

    public void displayStats() {
        System.out.println( name + "(lvl: " + level + ") (HP: " + hp + ")");
    }

    public void gainExp(int exp) {
        // Todo in the future
    }
    public void gainGold(int gold) {
        // Todo in the future
    }

    public Inventory getInventory() { return null; }
    public void viewInventory() { System.out.println("This monster has no items :("); }

    public String getName() { return name; }

    public int getLevel() { return level; }

    public int getHP() { return hp; }

    public int getMP() { return 0; }

    public int getGold() { return gold; }

    public boolean fightable() { return true; }

}
