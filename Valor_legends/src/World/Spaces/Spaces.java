package src.World.Spaces;

import src.Entities.Players.Party;
import src.World.Coordinate;

// java interface to indicate spaces(tiles) of the board

public interface Spaces {
    
    public void beginAction(Party p);

    public boolean canMoveto();

    public String toString();

    public boolean isPartyHere();

    public void setCoord(Coordinate c);

    public Coordinate getCoord();

    public void setOccupied(Party p);
    public Party getOccupied();

    public String spaceType();

    public String[] getRepr();

    public boolean hasEnemy();

    @Override
    public int hashCode();

}
