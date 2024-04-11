package src.World.Spaces;

import src.Entities.Players.Party;

public class Cave implements StatSpace {
    

    public Cave() {
        
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
