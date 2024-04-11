package src.Entities.Players;
import src.Entities.Entities;
import src.Inventory.Inventory;
import src.Inventory.Items.*;
import src.Messages.ErrorMessage;
import src.Messages.QuestionMessage;
import src.Misc.Input;
import src.Misc.RandomSelection;
import src.World.Coordinate;

import java.util.*;

// java object to represent each heros

public class Heros implements Entities {

    private String name;
    
    private int level;

    public HashMap<String, Integer> stats;
    // Stats include: 
    //      hp, mp, str, dex, def, agility

    private int gold;
    private String role;

    private Items armor;
    private Items weapon;

    private Inventory inventory;
    private boolean fightable;

    private double currExp;
    private double lvlupExp; 

    private Coordinate beginSpace;

    public Heros(String name, String role) {

        this.level = 1;
        this.name = name;

        stats = new HashMap<>();
        stats.put("hp", 100);
        stats.put("mp", 100);
        stats.put("str", 8);
        stats.put("def", 8);
        stats.put("dex", 8);
        stats.put("agility", 5);

        gold = 0;
        this.role = role;
        currExp = 0;
        lvlupExp = 100;

        armor = new Armors("Begginer Armor", 100, 3, 0);
        weapon = new Weapons("Begginer Weapon", 100, 3, 0);

        inventory = new Inventory();
        fightable = true;

    }

    public Heros(String name, String role, Coordinate beginSpace){
        this(name, role);
        this.beginSpace = beginSpace;
    }

    // ----------------------------------------------------
    // ROLE & LEVELUP
    // ----------------------------------------------------

    // method for leveling up hero
    public void levelUp() {

        level += 1;
        // double tmp = currExp - lvlupExp;
        lvlupExp =  lvlupExp * 1.2;
        currExp = 0;
        System.out.println(getName() + " leveled up to level " + getLevel() + "!");
        changeStatsRole();

    }

    // method for changing the role of the hero
    public boolean changeRole(String newrole) {

        return true;

    }

    // accessor methods
    public int getLevel() { return level; }
    public int getHP() { return stats.get("hp"); }
    public int getMP() { return stats.get("mp"); }
    public int getGold() { return gold; }
    public String getName() { return name; }
    public boolean fightable() { return fightable; }
    public HashMap<String, Integer> getStats() { return stats; }
    public Items getArmor() { return armor; }
    public Items getWeapon() { return weapon; }
    public Coordinate getBeginSpace() { return beginSpace; }

    public void displayStats() {

        System.out.println("=======================");
        System.out.println("Hero Stats {");
        System.out.println("Name: '" + name + "'");
        System.out.println("Level: " + level);
        System.out.println("Role: '" + role + "'");
        System.out.println("HP: " + stats.get("hp"));
        System.out.println("MP: " + stats.get("mp"));
        System.out.println("Strength: " + stats.get("str"));
        System.out.println("Defence: " + stats.get("def"));
        System.out.println("Dexterity: " + stats.get("dex"));
        System.out.println("Agility: " + stats.get("agility"));
        System.out.println("Armor: " +  armor.getName());
        System.out.println("Weapon: " + weapon.getName());
        System.out.println();
        System.out.println("Gold: " + gold);
        System.out.println("% to next level: " + 100 * (double)(currExp/lvlupExp));
        System.out.println("=======================");

    }


    // ----------------------------------------------------
    // STATS STUFF
    // ----------------------------------------------------

    // mutator method for the stats
    public void changeStats(int hp, int mp, int str, int def, int dex, int agility) {

        stats.put("hp", stats.get("hp") + hp);
        stats.put("mp", stats.get("mp") + mp);
        stats.put("str", stats.get("str") + str);
        stats.put("def", stats.get("def") + def);
        stats.put("dex", stats.get("dex") + dex);
        stats.put("agility", stats.get("agility") + agility);

    }
    // changes the stats depending on the player role
    public void changeStatsRole() {

        int base = RandomSelection.getRandomNumberInRange(4, 7);
        int hpIncrease = RandomSelection.getRandomNumberInRange(12, 18);;
        int mpIncrease = RandomSelection.getRandomNumberInRange(17, 23);;
        int strIncrease = base;
        int defIncrease = base;
        int dexIncrease = base;
        int agilityIncrease = base - 4;

        switch (role) {
            case "Warrior":
                strIncrease += RandomSelection.getRandomNumberInRange(5, 8);
                agilityIncrease += RandomSelection.getRandomNumberInRange(1, 3);
                hpIncrease += RandomSelection.getRandomNumberInRange(5, 10);
                break;

            case "Sorcerer":
                dexIncrease += RandomSelection.getRandomNumberInRange(7, 13); 
                mpIncrease += RandomSelection.getRandomNumberInRange(11, 17); 
                break;

            case "Paladin":
                strIncrease +=  RandomSelection.getRandomNumberInRange(2, 5); 
                dexIncrease +=  RandomSelection.getRandomNumberInRange(2, 5);
                hpIncrease += RandomSelection.getRandomNumberInRange(10, 13);
                break;

            case "Beast Tamer":
                hpIncrease += RandomSelection.getRandomNumberInRange(10, 20);
                agilityIncrease += RandomSelection.getRandomNumberInRange(3, 5);
                break;
        }

        changeStats(hpIncrease, mpIncrease, strIncrease, defIncrease, dexIncrease, agilityIncrease);

    }

    public void changeHP(int hp) {
        if(getHP() - hp <= 0) {
            System.out.println(name + " fainted :(");
            changeStats(getHP() * -1, 0, 0, 0, 0, 0);
            return;
        }
        changeStats(hp, 0, 0, 0, 0, 0);
    }
    
    public void changeMP(int mp) {
        if(getHP() - mp <= 0) {
            System.out.println(name + " doesn't have enough mana points.");
            return;
        }
        changeStats(0, mp, 0, 0, 0, 0);
    }




    // ----------------------------------------------------
    // INVENTORY STUFF
    // ----------------------------------------------------

    public void setGold(int amount) { gold += amount; }

    public int useItem(Items i) {
        if(this.getLevel() < i.getLevel()) {
            ErrorMessage.itemLevelError(i);
            return -1;
        }

        switch (i.getType()) {
            case "Potion":
                if(QuestionMessage.potionUseQuestion(this, i)) {
                    usePotion((Potions) i);
                }
                break;
            case "Weapon":
                if(QuestionMessage.itemEquipQuestion(this, i)) {
                    setWeapon(i);
                }
                break;
            case "Armor":
                if(QuestionMessage.itemEquipQuestion(this, i)) {
                    setArmor(i);
                }
                break;
            case "Spell":
                if(QuestionMessage.spellUseQuestion(this, i)) {
                    return useSpell((Spells) i);
                }
        }
        return 0;
    }

    private void setArmor(Items i) {
        stats.put("def", stats.get("def") - armor.getValue());
        inventory.addItem(armor);
        armor = i;
        inventory.deleteArmors(i);
        inventory.deleteItem(i);
        stats.put("def", stats.get("def") + armor.getValue());
    }

    private void setWeapon(Items i) {
        stats.put("str", stats.get("str") - weapon.getValue());
        stats.put("dex", stats.get("dex") - weapon.getValue());
        inventory.addItem(i);
        weapon = i;
        inventory.deleteWeapons(i);
        inventory.deleteItem(i);
        stats.put("str", stats.get("str") + weapon.getValue());
        stats.put("dex", stats.get("dex") + weapon.getValue());
    }

    private void usePotion(Potions i) {
        stats.put(i.getStat(), stats.get(i.getStat()) + i.getValue());
        i.use();
        if(i.getnumuse() <= 0) {
            System.out.println("Potion all used!");
            inventory.deletePotions(i);
        } else {
            System.out.println("Used potion " + i.getName() + ". " + i.getnumuse() + " uses left.");
        }
    }

    private int useSpell(Spells i) {
        i.use();
        if(i.getnumuse() <= 0) {
            System.out.println("Potion all used!");
            inventory.deletePotions(i);
        } else {

            System.out.println("Used Spell " + i.getName() + ". " + i.getnumuse() + " uses left.");
        }
        return i.getValue();
    }



    public void viewInventory() {
       
        System.out.println("=======================");
        System.out.println("Use an item?");
        if(Input.untilBooleanInput()) {
            int i2 = inventory.itemList();
            if(i2 == -1) {
                return;
            }
            Items itemtoUse = inventory.getAllItems().get(i2);
            useItem(itemtoUse);
        }

    }

    public Inventory getInventory() {
        return inventory;
    }

    public boolean useBehavior() { return true; }


    // ----------------------------------------------------
    // BATTLE STUFF
    // ----------------------------------------------------

    public int Attack(Items i) {
        if(i == null) {
            return (int)(stats.get("str") * RandomSelection.getRandomMultiplier() + stats.get("agility") * 0.35) * 10;
        } 
        if (i.getType() == "Spell") {
            changeMP(-150);
            return (int)(stats.get("dex") * RandomSelection.getRandomMultiplier() * RandomSelection.getRandomMultiplier() * i.getValue());
        }
        return 0;
    }

    public int Defence(int dmg) {
        int damage = (int)(dmg * (1 - (stats.get("def") * 0.005 + stats.get("agility") * 0.005)));
        changeHP(-1 * damage);
        return damage;
    }

    public void gainExp(int exp) {
        if(currExp + exp >= lvlupExp) {
            levelUp();
        } else {
            currExp += exp;
            System.out.println(name + " gained " + exp + " exp");
        }
    }

    public void gainGold(int gold) {
        setGold(gold);
        System.out.println(name + " gained " + gold + " gold");
    }

    public String toString() {
        return role + " " + name + "(lvl: " + level + ")" + "(HP: " + getHP() + ")";
    }

    
}