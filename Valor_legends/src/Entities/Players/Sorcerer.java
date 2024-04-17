package src.Entities.Players;


// Java object for Sorcerers
public class Sorcerer extends Heros{
    
    public Sorcerer(String name) {
        super(name, "Sorcerer");
        enhanceStats();
    }

    private void enhanceStats() {
        changeStats(0, 25, 0, 0, 10, 0); 
    }

    @Override
    public void changeStatsRole() {
        super.changeStatsRole();
        enhanceStats(); 
    }
}
