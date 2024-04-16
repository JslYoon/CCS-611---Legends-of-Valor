package src.World.Spaces;

import src.Behaviors.Behaviors;
import src.Entities.Players.Party;
import src.World.Coordinate;

public class Cave implements StatSpace {
    
    private Coordinate coord;
    private Party occupied;

    public Cave() {
        occupied = null;
    }

    public String statsIncrease() {return "agility"; }
    public void setOccupied(Party p) { occupied = p; }
    public Party getOccupied() { return occupied; }

    public void beginAction(Party p) {

        System.out.println("You met enemies");
        Behaviors.PlayerVsMonster(p);

    }
    public String spaceName() { return "Cave"; }

    public String spaceType() {
        return "Stats";
    }

    public boolean isPartyHere(){
        if(occupied != null) {
            return true;
        }
        return false;
    }
    public boolean hasEnemy() { 
        if(occupied == null) {
            return false;
        }    
        return !occupied.isGood(); 
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
        s += "|\t" + "cave" + "\t|";
        return s;
    }
    
    public String[] getRepr() {
        String[] r =  {"|\t_/ \\_\t|", "|\t/ C \\\t|", "|\t\\___/\t|"};
        return r;
    }

}
