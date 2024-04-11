package src.Entities.Neutral;

import src.Entities.Entities;
import src.Entities.Players.Party;
import src.Inventory.Inventory;
import src.Inventory.Items.Items;

public class NexusEntity implements Entities{

    private Inventory market;
    private boolean isHeroNexus;
    private Party livingEntity;

    public NexusEntity(Party e, boolean isHeroNexus) {
        
        this.isHeroNexus = isHeroNexus;
        this.livingEntity = e;
        this.market = new Inventory();
    }

 

    public String getName() {
        if(isHeroNexus) {
            return livingEntity.getPartyMembers().get(0).getName() + "'s Nexus";
        } else {
            return "Enemy Nexus";
        }
    }


    public int getLevel() { return 1; }
    public int getHP() { return 999; }
    public int getMP() { return 999; }
    public int getGold() { return 999999; }
    public boolean fightable() { return true; }

    // getInventory here opens up the market
    public Inventory getInventory() { return market; }

    public int Attack(Items i) { return 0; }
    public int Defence(int dmg) { return 0; }

    public void displayStats() {}
    public void viewInventory() {} 
    public void gainExp(int exp) {}
    public void gainGold(int gold) { gold += 99999; }


}
