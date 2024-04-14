package src.Entities.Neutral;
import src.Entities.Entities;
import src.Entities.Players.Heros;
import src.Entities.Players.Party;
import src.Inventory.Inventory;
import src.Inventory.Items.Armors;
import src.Inventory.Items.Items;
import src.Inventory.Items.Potions;
import src.Inventory.Items.Spells;
import src.Inventory.Items.Weapons;
import src.Messages.printStatement;
import src.Misc.Input;
import src.Misc.RandomSelection;
import src.Misc.dbOperations;

import java.util.*;

// java object to represent market

public class Market implements Entities{

    private String name;
    private Inventory market;

    public Market(Party p) {
        name = "The market";
        market = new Inventory();
        ArrayList<String> itemsData = dbOperations.readItemsFile();
        if (itemsData == null) return;

        itemsData = RandomSelection.selectRandomly(itemsData, 10);
        itemsData.forEach(data -> {
            String[] parts = data.split(",");
            String type = parts[0];
            Items item = null;

            switch (type) {
                case "Armor":
                    item = new Armors(parts[1], Integer.parseInt(parts[3]), Integer.parseInt(parts[4]), Integer.parseInt(parts[2]));
                    break;
                case "Weapon":
                    item = new Weapons(parts[1], Integer.parseInt(parts[3]), Integer.parseInt(parts[4]), Integer.parseInt(parts[2]));
                    break;
                case "Potion":
                    item = new Potions(parts[1], Integer.parseInt(parts[3]), Integer.parseInt(parts[4]), Integer.parseInt(parts[2]), parts[6], Integer.parseInt(parts[5]));
                    break;
                case "Skill":
                    item = new Spells(parts[1], Integer.parseInt(parts[3]), Integer.parseInt(parts[5]), Integer.parseInt(parts[2]), Integer.parseInt(parts[5]));
                    break;
            }

            if (item != null) {
                market.addItem(item);
            }

        });
    }
    public boolean isPlayer() {return false;}

    public String getName() {
        return name;
    }
    public HashMap<String, Integer> getStats() {return null;}

    public int getLevel() { return 999; }
    public int getHP() { return 999; }
    public int getMP() { return 999; }
    public int getGold() { return 999999; }
    public boolean fightable() { return false; }

    public Inventory getInventory() { return market; }

    public int Attack(Items i) { return 0; }
    public int Defence(int dmg) { return 0; }

    public void displayStats() {}
    public void viewInventory() {} 
    public void gainExp(int exp) {}
    public void gainGold(int gold) { gold += 99999; }


    // actual display of the market object
    public void displayMarket(Heros h) {
        System.out.println("=======================");
        System.out.println("Welcome to the market " + h.getName() + "!");
        System.out.println("What would you like to do today?");
        while(true) {
            System.out.println("0. Exit market");
            System.out.println("1. Buy Items");
            System.out.println("2. Sell items");
            System.out.println("You currently have " + h.getGold() + " gold");
            int i = Input.untilNumberInput(0, 2);
            switch(i) {
                case -1: 
                    System.out.println("Thank you for shopping!");
                    System.out.println("=======================");
                    return;
                case 0:
                    System.out.println("Thank you for shopping!");
                    System.out.println("=======================");
                    return;
                case 1:
                    int it = market.itemList();
                    if (it == -1) { 
                        break;
                    } else {
                        Items selected = market.getAllItems().get(it);
                        System.out.println(selected);
                        System.out.println("Buying this item?");
                        boolean tf = Input.untilBooleanInput();
                        if(tf && h.getGold() >= selected.getPrice()) {
                            System.out.println("Successfully bought this item!");
                            h.getInventory().addItem(selected);

                            market.deleteItem(selected);
                            h.setGold(-1 * selected.getPrice());

                        } else if(h.getGold() < selected.getPrice()) {
                            System.out.println("not enough gold");
                        }
                    }
                    break;
                case 2:
                    h.setGold(h.getGold() + sellItem(h));
                    break;
            }
        }

    }

  

    // helper method to sell item
    private int sellItem(Heros h) {
        System.out.println("Choose an item to sell");
        ArrayList<Items> al = h.getInventory().getAllItems();
        if(al.size() == 0) {
            System.out.println("You have no items to sell :\"(");
            return 0;
        }
        printStatement.listActions(al);
        int i = Input.untilNumberInput(0, al.size());
        if(i == -1) {
            return 0;
        }
        System.out.println("Selling this item for " + al.get(i).getPrice()/2 + " gold?");
        if (Input.untilBooleanInput()) {
            return al.get(i).getPrice()/2;
        } else {
            return 0;
        }

    }






    
}
