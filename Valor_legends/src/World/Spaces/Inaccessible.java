package src.World.Spaces;

import src.Entities.Players.Party;
import src.World.Coordinate;

// java object to indicate inaccessible tile

public class Inaccessible implements Spaces{

    private Party occupied;
    private Coordinate coord = null;
    
    public Inaccessible() {
        occupied = null;
    }

    public void setOccupied(Party p) { occupied = p; }
    public Party getOccupied() { return occupied; }

    public void beginAction(Party p) {
        System.out.println("But nothing happened");
    }

    public boolean canMoveto() {
        return false;
    }

    public boolean isPartyHere() {
        return false;
    }

    public void setCoord(Coordinate c) {
        coord = c;
    }

    public String spaceType() {
        return "Inaccessible";
    }

    public Coordinate getCoord() { return coord; }

    public String[] getRepr() {
        String[] r =  {"|\t  /\\\t|", "|\t /XX\\\t|", "|\t/XXXX\\\t|"};
        return r;
    }
    public boolean hasEnemy() { return false; }

}
