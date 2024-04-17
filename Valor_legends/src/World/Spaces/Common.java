package src.World.Spaces;

import java.util.ArrayList;

import src.Behaviors.Behaviors;
import src.Entities.Entities;
import src.Entities.Enemies.Monsters;
import src.Entities.Players.Heros;
import src.Entities.Players.Party;
import src.World.Coordinate;

// java object to indicate common tile

public class Common implements Spaces{

    private Party occupied;
    private Coordinate coord = null;

    public Common() {
        occupied = null;
    }

    public Common(Party p) {
        occupied = p;
    }

    public void setOccupied(Party p) { occupied = p; }
    public Party getOccupied() { return occupied; }

    public void beginAction(Party p) {

        System.out.println("You met enemies");
        Behaviors.PlayerVsMonster(p);

    }

    public String spaceType() {
        return "Common";
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

    public boolean hasEnemy() { 
        if(occupied != null) {
            return !occupied.isGood(); 
        }    
        return false;
    }
     public void setCoord(Coordinate c) {
        coord = c;
    }

    public Coordinate getCoord() { return coord; }

 
    public String toString() {
        String s = "";
        s += "|\t" + "common" + "\t|";
        return s;
    }
    
    public String[] getRepr() {
        String[] r =  {"|\t \t|", "|\t\t|", "|\t\t|"};
        return r;
    }

}
