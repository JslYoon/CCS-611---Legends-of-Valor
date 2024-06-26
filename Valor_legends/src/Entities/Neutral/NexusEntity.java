package src.Entities.Neutral;

import java.util.HashMap;

import src.Entities.Entities;
import src.Entities.Players.Party;
import src.Inventory.Inventory;
import src.Inventory.Items.Items;

// Java object that represents the nexus entity

public class NexusEntity implements NeutralEntity{

    private Market market;
    private boolean isHeroNexus;
    private Party livingEntity;
    private int count;

    public NexusEntity(Party e, boolean isHeroNexus) {
        
        this.isHeroNexus = isHeroNexus;
        this.livingEntity = e;
        this.market = new Market(e);
        count = 1;
    }

    public HashMap<String, Integer> getStats() {return null;}

    // for monster spawn rate adjustment
    public void resetCount() { count = 1; }
    public boolean minusCount() { 
        count--; 
        if (count == 0) { 
            resetCount();
            return true;
        }
        return false;
    }


    public String getName() {
        if(isHeroNexus) {
            return livingEntity.getPartyMembers().get(0).getName() + "'s Nexus";
        } else {
            return "Enemy Nexus";
        }
    }
    public boolean isPlayer() {return false;}

    public boolean isHeroNexus() { return isHeroNexus; }
    public int getLevel() { return 1; }
    public int getHP() { return 999; }
    public int getMP() { return 999; }
    public int getGold() { return 999999; }
    public boolean fightable() { return true; }

    public Market getMarket() {return market;}    
    public Inventory getInventory() { return null; }

    public int Attack(Items i) { return 0; }
    public int Defence(int dmg) { return 0; }

    public void displayStats() {}
    public void viewInventory() {} 
    public void gainExp(int exp) {}
    public void gainGold(int gold) { gold += 99999; }


}
