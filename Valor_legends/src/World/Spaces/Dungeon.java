package src.World.Spaces;

import javax.swing.text.html.parser.Entity;

import src.Entities.Players.Party;
import src.World.Coordinate;
import src.Misc.Color;

// java object to indicate dungeon tile

public class Dungeon implements Spaces{

    private Party occupied;
    private Coordinate coord = null;
    
    public Dungeon() {
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
    public boolean hasEnemy() { 
        if(occupied == null) {
            return false;
        }    
        return !occupied.isGood(); 
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
        String[] r = {"|" + Color.PURPLE + "\t  /\\\t" + Color.RESET + "|", "|" + Color.PURPLE + "\t /XX\\\t" + Color.RESET + "|", "|" + Color.PURPLE + "\t/XXXX\\\t" + Color.RESET + "|"};
        return r;
    }
 
}
