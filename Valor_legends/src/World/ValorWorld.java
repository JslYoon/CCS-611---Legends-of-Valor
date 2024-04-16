package src.World;
import java.util.*;
import src.World.Spaces.*;
import src.Entities.Players.*;
import src.Misc.Input;
import src.Misc.RandomSelection;

public class ValorWorld implements World {

    private ArrayList<ArrayList<Spaces>> world;
    private int W_rows;
    private int W_cols;
    //private Coordinate partyCoordinate; // TODO change to hash map of key= party value = coords
    private HashMap<Integer, Party> myParty;
    private int lanes;
    private int laneWidth;

    private final String[] PARTYREPR = {"|\t O\t|", "|\t/|\\\t|", "|\t/ \\\t|"};
    
    public ValorWorld(int rows, HashMap<Integer, Party> party, int lanes, int laneWidth) {
        W_rows = rows;
        W_cols = (lanes * (laneWidth + 1)) + 1;
        this.lanes = lanes;
        this.laneWidth = laneWidth;
        world = new ArrayList<>();

        myParty = party;

        
        for(int i = 0; i < rows; i++) {
            boolean hasNexus = false;
            ArrayList<Spaces> temp = new ArrayList<>();
            Spaces tempSpace = new Inaccessible();
            tempSpace.setCoord(new Coordinate(i, 0));
            temp.add(tempSpace);
            int place = 0;
            for (int k = 0; k < lanes; k++)
            {
                for (int j = 1; j < laneWidth + 1; j++) {
                    Spaces temp2;
                    if (i == 0) // if enemy nexus
                        temp2 = new Nexus(null, false);
                    else if (i == rows - 1) // if our nexus
                    {
                        if (hasNexus)
                            temp2 = new Nexus(null, true);
                        else
                        {
                            temp2 = new Nexus(party.get(place), true);
                            party.get(place).setCoord(new Coordinate(i, j + (laneWidth + 1) * k));
                            party.get(place).setHome(new Coordinate(i, j + (laneWidth + 1) * k));
                            hasNexus = true;
                            place++;
                        }
                    }
                    else
                        temp2 = RandomTiles();
                    temp2.setCoord(new Coordinate(i, j + (laneWidth + 1) * k));
                    temp.add(temp2);
                }
                Spaces temp3 = new Inaccessible();
                temp3.setCoord(new Coordinate(i, (laneWidth + 1) + (laneWidth + 1) * k));
                temp.add(temp3);
                hasNexus = false;
            }

            world.add(temp);
        }
    }



    // ----------------------------------------------------
    // DISPLAY STUFF
    // ----------------------------------------------------


    public String toString() {
        String r = " ";
        for(int i = 0; i < world.size(); i++) {
            r += drawLine();
            r += "\n";
            for (int x = 0; x < 3; x++){
                for(int j = 0; j < world.get(0).size(); j++) {
                    Spaces currSpace = world.get(i).get(j);
                    if(currSpace.isPartyHere()) {
                        r += PARTYREPR[x];
                    } else {
                        r += currSpace.getRepr()[x];
                    }
                }
            r += "\n";
            }
            r += " ";
        }
        r += drawLine();
        r += "\n";
        return r;
    }


    // A helper method to draw a line to build the board
    private String drawLine() {
        String r = "";
        String border = "+--------------+";


        for (int i = 0; i < W_cols; i++) {
            r += border;
        }
        return r;
    }
    
    // private method to assign tiles randomness in getting the tile type
    private Spaces RandomTiles() {
        HashMap<Spaces, Integer> hs = new HashMap<>();

        hs.put(new Common(), 85);
        hs.put(new Cave(), 5); 
        hs.put(new Bush(), 5); 
        hs.put(new Koulou(), 5); 
        return RandomSelection.KeyProbability(hs);
    }


    


    // ----------------------------------------------------
    // PARTY MOVEMENT STUFF
    // ----------------------------------------------------

    public void moveParty(Party p) {
        Coordinate partyCoordinate = p.getCoord();

        while (true) {
            char c = Input.getMovementInput();
        
            // Calculate potential new coordinates based on the input direction
            Coordinate newCoord = null;
            switch (c) {
                case 'w': // Move up
                    newCoord = partyCoordinate.upCoord(W_rows, W_cols);
                    break;
                case 's': // Move down
                    newCoord = partyCoordinate.downCoord(W_rows, W_cols);
                    break;
                case 'a': // Move left
                    newCoord = partyCoordinate.leftCoord(W_rows, W_cols);
                    break;
                case 'd': // Move right
                    newCoord = partyCoordinate.rightCoord(W_rows, W_cols);
                    break;
                case 'q': // Quit
                    return;
            }
           
            if(newCoord == null || CoordtoSpace(newCoord).spaceType().equals("Inaccessible")) {
                System.out.println("Cannot move in that direction. Try another one.");
                continue;
            }
            Spaces newSpace = CoordtoSpace(newCoord);
            CoordtoSpace(partyCoordinate).setOccupied(null);
            newSpace.setOccupied(p);
            p.setCoord(newCoord);
            return;
            
        }
        
    }
    
    public Spaces CoordtoSpace(Coordinate c) {
        return world.get(c.getRow()).get(c.getCol());
    }

    public Spaces currPartySpace(Party p) {
        Coordinate partyCoordinate = p.getCoord();
        System.out.println("sfsssssssssssssss");
        System.out.println(partyCoordinate.getRow());
        System.out.println(partyCoordinate.getCol());
        int r = partyCoordinate.getRow();
        int c = partyCoordinate.getCol();
        return world.get(r).get(c);

    }

    public void recall(Party p)
    {
        Coordinate newCoord = p.getHome();
        Coordinate partyCoordinate = p.getCoord();
        Spaces newSpace = CoordtoSpace(newCoord);
        CoordtoSpace(partyCoordinate).setOccupied(null);
        newSpace.setOccupied(p);
        p.setCoord(newCoord);
    }
}