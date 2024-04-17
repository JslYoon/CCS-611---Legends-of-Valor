package src.Entities.Enemies;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import src.Entities.Entities;
import src.Entities.Players.Heros;
import src.Inventory.Inventory;
import src.Inventory.Items.Items;
import src.Misc.RandomSelection;
import src.Misc.dbOperations;

// Java object to represent monsters

public class Monsters implements Entities {

    private int level;
    private int gold;
    private String name;
    private String element;
    private HashMap<String, Integer> stats;  // HashMap to hold stats

    public Monsters(Heros h) {
        stats = new HashMap<>();
        
        List<String> monsterCandidates = dbOperations.readMonstersFile().stream()
                                        .filter(monster -> Integer.parseInt(monster.split(",")[1]) > h.getLevel() - 5 && 
                                            Integer.parseInt(monster.split(",")[1]) < h.getLevel() + 5)
                                        .collect(Collectors.toList());

        int num = RandomSelection.getRandomNumberInRange(0, monsterCandidates.size()-1);
        String[] monsterStats = monsterCandidates.get(num).split(",");

        name = monsterStats[0];
        level = Integer.parseInt(monsterStats[1]);
        stats.put("hp", Integer.parseInt(monsterStats[3]));
        stats.put("str", Integer.parseInt(monsterStats[4]));
        stats.put("def", Integer.parseInt(monsterStats[5]));
        stats.put("agility", Integer.parseInt(monsterStats[6]));
        element = monsterStats[7];

        gold = level * 100;
    }

    public Monsters() {
        stats = new HashMap<>();

        name = "dummy";
        level = 10;
        stats.put("hp", 200);
        stats.put("str", 20);
        stats.put("def", 20);
        stats.put("agility", 20);
    }

    public int Attack(Items i) {
        return (int) (stats.get("str") * RandomSelection.getRandomMultiplier() * 10);
    }

    public int Defence(int dmg) {
        int damage = (int)(dmg * (1 - (stats.get("def") * 0.05 + stats.get("agility") * 0.05)));
        int updatedHp = stats.get("hp") - damage;
        stats.put("hp", updatedHp <= 0 ? 0 : updatedHp);
        return damage;
    }

    public String toString() {
        return name + "(lvl: " + level + ") (HP: " + stats.get("hp") + ")";
    }

    public void displayStats() {
        System.out.println(name + "(lvl: " + level + ") (HP: " + stats.get("hp") + ")");
    }

    public HashMap<String, Integer> getStats() {
        return stats;
    }
    public Inventory getInventory() { return null; }
    public void viewInventory() { System.out.println("This monster has no items :("); }
    public String getName() { return name; }
    public int getLevel() { return level; }
    public int getHP() { return stats.get("hp"); }
    public int getMP() { return 0; }
    public boolean isPlayer() {return false;}
    public int getGold() { return gold; }
    public void gainExp(int exp) {
        // Todo in the future
    }
    public void gainGold(int gold) {
        // Todo in the future
    }
    public boolean fightable() { return true; }
}
