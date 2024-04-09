package src.Entities.Players;

import java.util.*;
import java.util.stream.Collectors;

import src.Entities.Entities;

// java object to represent player party

public class Party {

    private ArrayList<Entities> party;
    
    public Party(ArrayList<Entities> partiers) {
        party = partiers;
    }

    public int partyNum() { return party.size(); }

    public ArrayList<Entities> getPartyMembers() {
        return party;
    }

    public List<Entities> getAlivePartyMembers() {
        List<Entities> livingBeings = party.stream() 
                                        .filter(e -> e.getHP() > 0) 
                                        .collect(Collectors.toList());
        return livingBeings;
    }

    // Check if any heros are still alive. Returns true if there is at least one entity that can fight
    public boolean checkVitals() {
        for(Entities h: party) {
            if(h.getHP() > 0) {
                return true;
            }
        }
        return false;
    }

    // Get the average level of the party
    public int averageLvl() {
        int r = 0;
        for(Entities h: party) {
            r += h.getLevel();
        }
        return r/party.size();
    }

    public int numFighters() {
        int r = 0;
        for(Entities h: party) {
            if(h.fightable()) {
                r++;
            }
        }
        return r;
    }

    // View party stats
    public void viewPartyStats() {
        for(Entities e : party) {
            e.displayStats();
        }
    }

    // Viwe party inventory
    public void viewPartyInventory() {
        for(Entities e : party) {
            e.viewInventory();
        }
    }
    


}
