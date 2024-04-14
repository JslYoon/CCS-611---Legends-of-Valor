package src.World.Spaces;

import src.Entities.Entities;
import src.Entities.Neutral.Market;
import src.Entities.Players.Heros;
import src.Entities.Players.Party;
import src.World.Coordinate;

// java object to indicate a space where market exists

public class MarketSpace implements Spaces {

    private Party occupied;
    private Coordinate coord = null;
    private Market market;

    public MarketSpace() {

        occupied = null;
    }

    public void setOccupied(Party p) { occupied = p; }
    public Party getOccupied() { return occupied; }

    public void setCoord(Coordinate c) {
        coord = c;
    }

    public Coordinate getCoord() { return coord; }


    public void beginAction(Party p) {
        market = new Market(p);
        for(Entities e: p.getPartyMembers()) {
            System.out.println(e.getName() + " entering the market");
            market.displayMarket((Heros)e);
        }
    }
    public boolean hasEnemy() { return false; }

    public String spaceType() {
        return "Market";
    }

    public boolean canMoveto() {
        return true;
    }

    public String[] getRepr() {
        String[] r =  {"|\t /\\\t|", "|\t/  \\\t|", "|\t|_M_|\t|"};
        return r;
    }

    public boolean isPartyHere(){
        if(occupied != null) {
            return true;
        }
        return false;
    }

    public String toString() {
        String s = "";
        s += "|\t" + "market" + "\t|";
        return s;
    }
 
}
