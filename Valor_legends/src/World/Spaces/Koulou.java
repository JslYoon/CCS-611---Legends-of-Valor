package src.World.Spaces;

import src.Behaviors.Behaviors;
import src.Entities.Players.Party;
import src.World.Coordinate;
import src.Misc.Color;

// java object to indicate koulou tile

public class Koulou implements StatSpace {
    
    private Coordinate coord;
    private Party occupied;

    public Koulou() {
        occupied = null;
    }

    public String statsIncrease() {return "str"; }
    public void setOccupied(Party p) { occupied = p; }
    public Party getOccupied() { return occupied; }
    public String spaceName() { return "Koulou"; }

    public void beginAction(Party p) {

        System.out.println("You met enemies");
        Behaviors.PlayerVsMonster(p);

    }
    public boolean hasEnemy() { 
        if(occupied == null) {
            return false;
        }    
        return !occupied.isGood(); 
    }
    public String spaceType() {
        return "Stats";
    }

    public boolean isPartyHere(){
        if(occupied != null) {
            return true;
        }
        return false;
    }

    public boolean canMoveto() {
        return true;
    }


     public void setCoord(Coordinate c) {
        coord = c;
    }

    public Coordinate getCoord() { return coord; }

 
    public String toString() {
        String s = "";
        s += "|\t" + "koulou" + "\t|";
        return s;
    }
    
    public String[] getRepr() {
        String[] r = {"|" + Color.PURPLE + "\tv v v\t" + Color.RESET + "|", "|" + Color.PURPLE + "\tv[K]v\t" + Color.RESET + "|", "|" + Color.PURPLE + "\tv v v\t" + Color.RESET + "|"};

        return r;
    }

}
