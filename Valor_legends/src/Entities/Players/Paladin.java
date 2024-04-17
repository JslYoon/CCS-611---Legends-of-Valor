package src.Entities.Players;

import src.World.Coordinate;

// Java object for Paladins

public class Paladin extends Heros {

    public Paladin(String name) {
        super(name, "Paladin");
        enhanceStats();
    }

    private void enhanceStats() {
        changeStats(10, 10, 3, 3, 3, 3); 
    }

    @Override
    public void changeStatsRole() {
        super.changeStatsRole(); 
        enhanceStats(); 
    }
    
}
