package src.World;

import src.Entities.Players.Party;
import src.World.Spaces.Spaces;

public interface World {
    
    public boolean moveParty(Party p);
    public Spaces currPartySpace(Party p);
    public String worldType();
}
