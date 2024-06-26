package src.World;
import java.util.*;
import src.World.Spaces.*;
import src.Entities.Entities;
import src.Entities.Enemies.Monsters;
import src.Entities.Players.*;
import src.Misc.Input;
import src.Misc.RandomSelection;
import src.Misc.Color;
// Java objects that represents the valor world

public class ValorWorld implements World {

    private ArrayList<ArrayList<Spaces>> world;
    private int W_rows;
    private int W_cols;
    private HashMap<Integer, Party> myParty;
    private int lanes;
    private int laneWidth;
    private ArrayList<Party> monsters;

    private final String[] PARTYREPR = {"|" + Color.BLUE + "\t O\t" + Color.RESET + "|", "|" + Color.BLUE + "\t/|\\\t" + Color.RESET + "|","|" + Color.BLUE + "\t/ \\\t" + Color.RESET + "|"};
    private final String[] MONSTERREPR = {"|" + Color.YELLOW + "\t /-\\ \t" + Color.RESET + "|", "|" + Color.YELLOW + "\t(0_0)\t" + Color.RESET + "|", "|" + Color.YELLOW + "\t/| |\\\t" + Color.RESET + "|"};


    public ValorWorld(int rows, HashMap<Integer, Party> party, int lanes, int laneWidth) {
        W_rows = rows;
        W_cols = (lanes * (laneWidth + 1)) + 1;
        this.lanes = lanes;
        this.laneWidth = laneWidth;
        world = new ArrayList<>();

        myParty = party;
        monsters = new ArrayList<>();
        
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

    public String worldType() { return "Valor"; }

    public String toString() {
        String r = " ";
        for(int i = 0; i < world.size(); i++) {
            r += drawLine();
            r += "\n";
            for (int x = 0; x < 3; x++){
                for(int j = 0; j < world.get(0).size(); j++) {
                    Spaces currSpace = world.get(i).get(j);
                    if(currSpace.isPartyHere()) {
                        if(currSpace.getOccupied().isGood()) {
                            r += PARTYREPR[x];
                        } else {
                            r += MONSTERREPR[x];
                        }
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
    // MONSTER STUFF
    // ----------------------------------------------------

    public void enemyMove() {
        createMonster();
        if (RandomSelection.isSuccess(85)) {
            moveMonster();
        }
        
    }

    public boolean createMonster() {
        for(int i = 0; i < W_rows; i++) {
            for(int j = 0; j < W_cols; j++) {
                Spaces sp = world.get(i).get(j);
                if(sp.spaceType().equals("Nexus") && !((Nexus)(sp)).getNexus().isHeroNexus() && ((Nexus)(sp)).getNexus().minusCount() ) {
                    if(RandomSelection.isSuccess(50)) {
                        if(sp.isPartyHere()) {
                            return false;
                        }
                        ArrayList<Entities> al = new ArrayList<>();
                        al.add(new Monsters());
                        Party p = new Party(al);
                        p.setCoord(new Coordinate(i, j));
                        sp.setOccupied(p);
                        monsters.add(p);
                    }
                }
            }
        }
        return false;
    }
    public void moveMonster() {
        for (Party mp: monsters) {
            HashMap<Coordinate, Integer> hm = new HashMap<>();
            Coordinate c = mp.getCoord();
            Coordinate down = c.downCoord(W_rows, W_cols);
            Coordinate left = c.leftCoord(W_rows, W_cols);
            Coordinate right = c.rightCoord(W_rows, W_cols);
            if(down != null && !CoordtoSpace(down).spaceType().equals("Inaccessible") && !CoordtoSpace(down).isPartyHere()) {
                hm.put(down, 60);
            } 
            if(right != null && !CoordtoSpace(right).spaceType().equals("Inaccessible") && !CoordtoSpace(right).isPartyHere()) {
                hm.put(c.rightCoord(W_rows, W_cols), 20);
            }
            if(left != null && !CoordtoSpace(left).spaceType().equals("Inaccessible") && !CoordtoSpace(left).isPartyHere()) {
                hm.put(left, 20);
            }
            if(hm.isEmpty()) {
                return;
            }
            Coordinate cc = RandomSelection.KeyProbability(hm);
            Spaces newspace = CoordtoSpace(cc);
            CoordtoSpace(c).setOccupied(null);

            newspace.setOccupied(mp);
            mp.setCoord(cc);
            if(newspace.spaceType().equals("Nexus") && ((Nexus)newspace).getNexus().isHeroNexus()) {
                System.out.println(mp + " reached our base, you lost");
                System.out.println("Try again next time!");
                System.exit(0);
            }
        }
    }
    


    // ----------------------------------------------------
    // PARTY MOVEMENT STUFF
    // ----------------------------------------------------

    public boolean moveParty(Party p) {
        Coordinate partyCoordinate = p.getCoord();
        while (true) {
            char c = Input.getValorMovementInput();
            
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
                case 'r': // recall
                    newCoord = p.getHome();
                    break;
                case 't': // teleport
                    System.out.println("Which hero would you like to teleport to? Enter number between player in lanes 0 - " + (lanes-1));
                    int heroIndex = Input.untilNumberInput(0, lanes-1);
                    if(heroIndex == -1) { return false; }
                    int x = myParty.get(heroIndex).getCoord().getRow();
                    int y = myParty.get(heroIndex).getCoord().getCol();
                    if (x == partyCoordinate.getRow() && y == partyCoordinate.getCol())
                    {
                        System.out.println("Cannot teleport to current hero. Try another one.");
                        continue;
                    }
                    Coordinate temp = new Coordinate(x, y);
                    newCoord = teleport(temp);
                    if(CoordtoSpace(newCoord).isPartyHere() == true) {
                        System.out.println("Cannot move in that direction. Try another one.");
                        break;
                    }
                    break;
                case 'q': // Quit
                    return false;
            }
           
            if(newCoord == null || CoordtoSpace(newCoord).spaceType().equals("Inaccessible") ) {
                System.out.println("Cannot move in that direction. Try another one.");
                continue;
            }
            if(CoordtoSpace(newCoord).hasEnemy()) {
                System.out.println("Monster encounter!!!");
                CoordtoSpace(newCoord).beginAction(p);
                if(!p.checkVitals()) {
                    System.out.println("L");
                    System.exit(0);
                }
                monsters.remove(CoordtoSpace(newCoord).getOccupied());
            }

            Spaces newSpace = CoordtoSpace(newCoord);
            CoordtoSpace(partyCoordinate).setOccupied(null);
            newSpace.setOccupied(p);
            p.setCoord(newCoord);
            return true;
            
        }
        
    }
    
    public Spaces CoordtoSpace(Coordinate c) {
        return world.get(c.getRow()).get(c.getCol());
    }

    public Spaces currPartySpace(Party p) {
        Coordinate partyCoordinate = p.getCoord();
        int r = partyCoordinate.getRow();
        int c = partyCoordinate.getCol();
        return world.get(r).get(c);

    }

    public Coordinate teleport(Coordinate partyCoordinate)
    {
        char c = Input.getTeleportMovement();
        Coordinate newCoord = null;
        switch (c) 
        {
            case 's': // Move down
                newCoord = partyCoordinate.downCoord(W_rows, W_cols);
                break;
            case 'a': // Move left
                newCoord = partyCoordinate.leftCoord(W_rows, W_cols);
                break;
            case 'd': // Move right
                newCoord = partyCoordinate.rightCoord(W_rows, W_cols);
                break;
        }
        return newCoord;
    }
}