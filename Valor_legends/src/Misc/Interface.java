package src.Misc;

import java.util.ArrayList;
import java.util.HashMap;

import src.Entities.Entities;
import src.Entities.Players.Heros;
import src.Entities.Players.Party;
import src.Messages.printStatement;
import src.World.MonsterWorld;
import src.World.ValorWorld;
import src.World.World;
import src.World.Spaces.Spaces;
import src.World.Spaces.StatSpace;

// Game interface object

public class Interface {

    private World world;
    private int world_r = 8;
    private int world_c = 8;
    private int valor_lanes = 2;
    private int valor_lane_size = 2;
    private Party myParty = null;
    private HashMap<Integer, Party> ValorParty = null;

    private ArrayList<String> Roles = new ArrayList<>();
    
    public Interface() {
        
        Roles.add("Warrior");
        Roles.add("Sorcerer");
        Roles.add("Paladin");
        while(true)
        {
            switch (startPage()) {
                case 0:
                    System.out.println("Welcome to monsters and legends!");
                    while(true) {
                        switch(startGamePage()) {
                            case 0:
                                System.out.println("Goodbye!");
                                return;
                            case 1:
                                Party p = myParty; 
                                if(myParty == null) {
                                    p = createParty();
                                    myParty = p;
                                }
                                if (p == null){
                                    continue;
                                }
                                startMonsterGame(p);
                                break;
                            case 2:
                                createMonsterWorld();
                                break;
                            case 3:
                                break;
                        }
                    }
                    
                case 1:
                    System.out.println("Welcome to Legends of Valor!");
                    while(true) {
                        switch(startGamePage()) {
                            case 0:
                                System.out.println("Goodbye!");
                                return;
                            case 1:
                                HashMap<Integer, Party> p = ValorParty; 
                                if(p == null) {
                                    p = createValorParty();
                                    ValorParty = p;
                                }
                                if (p == null){
                                    continue;
                                }
                                startValorGame();
                                break;
                            case 2:
                                createValorWorld();
                                break;
                            case 3:
                                break;
                        }
                    }
            }
        }
    }


    private void startMonsterGame(Party p) {
        world = new MonsterWorld(world_r, world_c, p);
        while(true) {
            System.out.println(world);
            int nums = playerInputs(p);
            if (nums == -1) {
                return;
            }
        }
    }

    private void startValorGame() {
        System.out.println();
        world = new ValorWorld(8, ValorParty, valor_lanes, valor_lane_size);
        while(true) {
            for(int i = 0; i < ValorParty.size(); i++) {
                Party currparty = ValorParty.get(0);
                System.out.println(world);
                System.out.println(currparty.getPartyMembers().get(0).getName() + "'s turn");
                int nums = playerInputs(currparty);
                if (nums == -1) {
                    return;
                }
                
            }
        }
    }

    // Player input interface for the actual game
    private int playerInputs(Party p) {
        int playerEntry = printStatement.playerMoveOption(world.currPartySpace(p));
        switch(playerEntry) {
            case 0:
                return -1;
            case 1: // Move party
                world.moveParty(p);
                uponplayerMove(p);
                return 1;
            case 2: // View inventory
                p.viewPartyInventory();
                return 2;
            case 3: // View party stats
                p.viewPartyStats();
                System.out.println("Enter any key to continue");
                Input.scannerInput();
                return 3;
        }
        return -1;
    }



    // Is a method to be called after a player moves
    private void uponplayerMove(Party p) {
        Spaces sp = world.currPartySpace(p);
        switch(sp.spaceType()) {
            case "Common":
                boolean enterBattle = printStatement.uponCommonSpace();
                if (RandomSelection.isSuccess(35) || sp.hasEnemy()) {
                    System.out.println("Monster encounter!!!");
                    enterBattle = true;
                }
                p.debuff(null, 10);
                if(enterBattle) {
                    world.currPartySpace(p).beginAction(p);
                    if(!p.checkVitals()) {
                        System.out.println("L");
                        System.exit(0);
                    }
                }
                break;
            case "Market":
                boolean enterMarket = printStatement.uponMarketSpace();
                p.debuff(null, 10);
                if(enterMarket) {
                    world.currPartySpace(p).beginAction(p);
                }
                break;
            case "Stats":
                String buffstat = ((StatSpace)sp).statsIncrease();
                p.buff(buffstat, valor_lane_size);
                if(sp.hasEnemy()) {
                    System.out.println("Monster encounter!!!");
                    world.currPartySpace(p).beginAction(p);
                    if(!p.checkVitals()) {
                        System.out.println("L");
                        System.exit(0);
                    }
                }
                break;
            case "Nexus":
                break;
                
        }
    }

    private int startGamePage() {

        System.out.println("\nWelcome to the main page, pick an option! :)\n");
        System.out.println("0. Exit");
        System.out.println("1. Begin game");
        System.out.println("2. Change world size");
        return Input.untilNumberInput(0, 2);
    }

    private int startPage() {
       
        System.out.println("\nWelcome to the main page, pick a game! :)\n");
        System.out.println("0. Monsters and Legends");
        System.out.println("1. Legends of Valor");
        return Input.untilNumberInput(0, 1);
    }

    // create and return party
    public Party createParty() {
        System.out.println("How many players in the party would you like to have? (1 ~ 4)");
        int partynum = Input.untilNumberInput(1, 4);
        if(partynum == -1) {
            return null;
        }
        
        ArrayList<Entities> partiers = new ArrayList<>();
        for(int i = 0; i < partynum; i++) {
            Entities en = createHero();
            if(en == null) { return null; }
            partiers.add(en);
        }

        Party party = new Party(partiers);
        return party;
    }

    // create and return valor party
    public HashMap<Integer, Party>  createValorParty() {
        HashMap<Integer, Party> tempmap = new HashMap<>();
        ArrayList<Entities> partiers = new ArrayList<>();
        for(int i = 0; i < valor_lanes; i++) {
            System.out.println("Creating hero for lane " + (i+1));
            Entities en = createHero();
            if(en == null) { return null; }
            partiers.add(en);
            Party party = new Party(partiers);
            tempmap.put(i, party);
        }
        
        return tempmap;

    }


    private void createMonsterWorld() {
        System.out.println("======================");
        System.out.println("Choose your world row size (8 ~ 16)");
        int num1 = Input.untilNumberInput(8, 16);
        if (num1 == -1) {return;}
        System.out.println("Choose your world column size (8 ~ 16)");
        int num2 = Input.untilNumberInput(8, 16);
        if (num2 == -1) {return;}
        world_r = num1;
        world_c = num2;
    }


    private void createValorWorld() {
        // TODO change this to take in column size, row number, number of lanes
        System.out.println("======================");
        System.out.println("How many lanes would you want? (1 ~ 3)");
        int num1 = Input.untilNumberInput(1, 3);
        if (num1 == -1) {return;}
        System.out.println("What size should the lines be (1 ~ 3)");
        int num2 = Input.untilNumberInput(1, 3);
        if (num2 == -1) {return;}
        valor_lanes = num1;
        valor_lane_size = num2;
        world_r = 8;
    }

    // create hero
    private Heros createHero() {

        System.out.println("======================");
        System.out.println("Choose your hero name");
        String name = Input.stringInput();
        if(name == null) {
            return null;
        }
        
        System.out.println("Hello " + name + "! Choose your hero role (Warrior, Paladin, Sorcerer)");
        String s1 = Input.untilGivenStringInput(Roles);
        Heros h  = new Heros(name, s1);;
        switch(s1) {
            case "Warrior":
                h = new Heros(name, s1);
        }
      

        System.out.println("You chose " + s1 + ". Enjoy the game.");
        System.out.println("======================");
        return h;
    }
}
