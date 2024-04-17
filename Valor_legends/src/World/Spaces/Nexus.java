package src.World.Spaces;

import java.util.ArrayList;

import src.Behaviors.Behaviors;
import src.Entities.Entities;
import src.Entities.Enemies.Monsters;
import src.Entities.Neutral.NexusEntity;
import src.Entities.Players.Heros;
import src.Entities.Players.Party;
import src.World.Coordinate;

// java object to indicate where nexus exists in a tile format

public class Nexus implements Spaces{

    private Party occupied;
    private Coordinate coord = null;
    private NexusEntity nexus;

    public Nexus(Party p, boolean isHeroNexus) {
        occupied = p;
        nexus = new NexusEntity(occupied, isHeroNexus);
    }

    public Nexus(Party p) {
        this(p, false);
        occupied = p;
    }

    public void setOccupied(Party p) { 
        occupied = p;
    }
    public Party getOccupied() { return occupied; }
    public NexusEntity getNexus() { return nexus; }
    public void beginAction(Party p) {
        
        for(Entities e: p.getPartyMembers()) {
            System.out.println(e.getName() + " entering the market");
            nexus.getMarket().displayMarket((Heros)e);
        }
    }

    public boolean hasEnemy() { 
        if(occupied == null) {
            return false;
        }    
        return !occupied.isGood(); 
    }
    public String spaceType() {
        return "Nexus";
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
        s += "|\t" + "nexus" + "\t|";
        return s;
    }
   
   
    
   
    public String[] getRepr() {
        String[] r =  {"|\t /\\ \t|", "|\t-[N]-\t|", "|\t \\/ \t|"};
        return r;
    }

}
