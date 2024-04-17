package src.World.Spaces;

import src.Behaviors.Behaviors;
import src.Entities.Players.Party;
import src.World.Coordinate;
import src.Misc.Color;

// java object to indicate bush tile


public class Bush implements StatSpace {
    
    private Coordinate coord;
    private Party occupied;

    public Bush() {
        occupied = null;
    }

    public String statsIncrease() {return "dex"; }
    public void setOccupied(Party p) { occupied = p; }
    public Party getOccupied() { return occupied; }
    public String spaceName() { return "Bush"; }

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
        s += "|\t" + "bush" + "\t|";
        return s;
    }
    
    public String[] getRepr() {
        String[] r = {"|" + Color.GREEN + "\t ~~~\t" + Color.RESET + "|", "|" + Color.GREEN + "\t~[B]~\t" + Color.RESET + "|", "|" + Color.GREEN + "\t ~~~\t" + Color.RESET + "|"};

        return r;
    }

}
