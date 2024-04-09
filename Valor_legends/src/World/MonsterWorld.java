package src.World;
import java.util.*;
import src.World.Spaces.*;
import src.Entities.Players.*;
import src.Misc.Input;
import src.Misc.RandomSelection;

public class MonsterWorld {

    private ArrayList<ArrayList<Spaces>> world;
    private int W_rows;
    private int W_cols;
    private Coordinate partyCoordinate;
    private Party myParty;

    private final String[] PARTYREPR = {"|\t O\t|", "|\t/|\\\t|", "|\t/ \\\t|"};
    
    public MonsterWorld(int rows, int cols, Party party) {
        W_rows = rows;
        W_cols = cols;

        world = new ArrayList<>();

        myParty = party;

        for(int i = 0; i < rows; i++) {
            ArrayList<Spaces> temp = new ArrayList<>();
            for (int j = 0; j < cols; j++) {
                Spaces tempSpace = RandomTiles();
                tempSpace.setCoord(new Coordinate(i, j));
                temp.add(tempSpace);
            }
            world.add(temp);
        }
        world.get(0).set(0, new Common(party));
        partyCoordinate = new Coordinate(0, 0);

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
        hs.put(new Common(), 70);
        hs.put(new MarketSpace(), 10);
        hs.put(new Inaccessible(), 20);
        
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