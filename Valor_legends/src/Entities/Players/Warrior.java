package src.Entities.Players;

import src.World.Coordinate;

// Java object for Warriors

public class Warrior extends Heros {
    
    public Warrior(String name) {
        super(name, "Warrior");
        enhanceStats();
    }

    private void enhanceStats() {
        changeStats(20, 0, 5, 5, 0, 2);
    }

    @Override
    public void changeStatsRole() {
        super.changeStatsRole(); 
        enhanceStats(); 
    }
}
