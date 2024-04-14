package src.World.Spaces;

import src.Behaviors.Behaviors;
import src.Entities.Players.Party;
import src.World.Coordinate;

public class Koulou implements StatSpace {
    
    private Coordinate coord;
    private Party occupied;

    public Koulou() {
        occupied = null;
    }

    public String statsIncrease() {return "str"; }
    public void setOccupied(Party p) { occupied = p; }
    public Party getOccupied() { return occupied; }

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
        String[] r =  {"|\tv v v\t|", "|\tv[K]v\t|", "|\tv v v\t|"};
        return r;
    }

}
