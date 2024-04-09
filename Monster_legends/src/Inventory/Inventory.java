package src.Inventory;

import java.util.*;
import src.Inventory.Items.*;
import src.Messages.ErrorMessage;
import src.Messages.printStatement;
import src.Misc.*;

// Java object that represents the inventory

public class Inventory {
    
    private ArrayList<Items> potions;
    private ArrayList<Items> armors;
    private ArrayList<Items> weapons;
    private ArrayList<Items> allItems;


    public Inventory() {
        potions = new ArrayList<>();
        armors = new ArrayList<>();
        weapons = new ArrayList<>();
        allItems = new ArrayList<>();

       
    }

    // getters
    public ArrayList<Items> getAllpotions() {return potions;}
    public ArrayList<Items> getAllArmors() {return armors;} 
    public ArrayList<Items> getAllWeapons() {return weapons;}
    public ArrayList<Items> getAllItems() { return allItems; }

    // viewers
    public void viewAllPotions() {printStatement.listActions(potions);}
    public void viewAllArmors() {printStatement.listActions(armors);}
    public void viewAllWeapons() {printStatement.listActions(weapons);}


    // mutators
    public void deletePotions(int i) { 
        potions.remove(i);
        allItems.remove(i);    
    }
    public void deleteArmors(int i) { armors.remove(i); allItems.remove(i);  }
    public void deleteWeapons(int i) { weapons.remove(i); allItems.remove(i);  }
    public void deletePotions(Object i) { potions.remove(i); allItems.remove(i);  }
    public void deleteArmors(Object i) { armors.remove(i); allItems.remove(i);  }
    public void deleteWeapons(Object i) { weapons.remove(i); allItems.remove(i);  }

    public void deleteItem(Items i) {
        switch (i.getType()) {
            case "Weapon":
                deleteWeapons(i);
                break;
            case "Armor":
                deleteArmors(i);
                break;
            case "Potion":
                deletePotions(i);
                break;
        }

    }

    public void addItem(Items i) {
        switch (i.getType()) {
            case "Weapon":
                weapons.add(i);
                break;
            case "Armor":
                armors.add(i);
                break;
            case "Potion":
                potions.add(i);
                break;
        }
        allItems.add(i);
    }

    // returns the indicated item index of the itemlist in the Inventory
    public int itemList() {
        if(getAllItems().size() == 0) {
            ErrorMessage.allItemsSoldError();
            return -1;
        }
        printStatement.listActions(getAllItems());
        return Input.untilNumberInput(0, getAllItems().size());
    }


}