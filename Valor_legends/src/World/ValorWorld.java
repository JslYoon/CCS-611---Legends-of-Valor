package src.World;
import java.util.*;
import src.World.Spaces.*;
import src.Entities.Players.*;
import src.Misc.Input;
import src.Misc.RandomSelection;

public class ValorWorld {

    private ArrayList<ArrayList<Spaces>> world;
    private int W_rows;
    private int W_cols;
    private Coordinate partyCoordinate; // change to hash map of key= party value = coords
    private Party myParty;
    private int lanes;
    private int laneWidth;

    private final String[] PARTYREPR = {"|\t O\t|", "|\t/|\\\t|", "|\t/ \\\t|"};
    
    public ValorWorld(int rows, Party party, int lanes, int laneWidth) {
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
                            temp2 = new Nexus(party, true);
                            hasNexus = true;
                        }
                    }
                    else
                        temp2 = RandomTiles();
                    temp2.setCoord(new Coordinate(i, j + 3 * lanes));
                    temp.add(temp2);
                }
                Spaces temp3 = new Inaccessible();
                temp3.setCoord(new Coordinate(i, 3 + 3 * lanes));
                temp.add(temp3);
                hasNexus = false;
            }

            world.add(temp);
        }
        //world.get(0).set(0, new Common(party));
        //partyCoordinate = new Coordinate(0, 0);

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
        hs.put(new Common(), 90);
        //hs.put(new StatSpace(), 10);
        hs.put(new MarketSpace(), 10);  //TODO replace with stat space bc its not done yet
        return RandomSelection.KeyProbability(hs);
    }


    


    // ----------------------------------------------------
    // PARTY MOVEMENT STUFF
    // ----------------------------------------------------


    public void setParty(Party p) {
        world.get(0).set(0, new Common(p));
        partyCoordinate = new Coordinate(0, 0);
    }

    public void moveParty() {

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
                case 'r':
                    // recall
                    break;
                case 't':
                    //tp
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
            newSpace.setOccupied(myParty);
            partyCoordinate = newCoord;
            return;
            
        }
        
    }
    
    public Spaces CoordtoSpace(Coordinate c) {
        return world.get(c.getRow()).get(c.getCol());
    }

    public Spaces currPartySpace() {

        int r = partyCoordinate.getRow();
        int c = partyCoordinate.getCol();
        return world.get(r).get(c);

    }


}