package src.Misc;

import java.util.ArrayList;

import src.Entities.Entities;
import src.Entities.Players.Heros;
import src.Entities.Players.Party;
import src.Messages.printStatement;
import src.World.MonsterWorld;

// Game interface object

public class Interface {

    private MonsterWorld world;
    private int world_r = 8;
    private int world_c = 8;
    private Party myParty = null;
    private ArrayList<String> Roles = new ArrayList<>();
    
    public Interface() {

        Roles.add("Warrior");
        Roles.add("Sorcerer");
        Roles.add("Paladin");
        System.out.println("Welcome to monsters and legends!");
        while(true) {
            switch(startPage()) {
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
                    startGame(p);
                    break;
                case 2:
                    createWorld();
                    break;
                case 3:
                    break;
            }
        }
    }

    private void startGame(Party p) {
        world = new MonsterWorld(world_r, world_c, p);
        while(true) {
            System.out.println(world);
            int nums = playerInputs(p);
            if (nums == -1) {
                return;
            }
        }
    }

    // Player input interface for the actual game
    private int playerInputs(Party p) {

        int playerEntry = printStatement.playerMoveOption(world.currPartySpace());
        switch(playerEntry) {
            case 0:
                return -1;
            case 1: // Move party
                world.moveParty();
                uponplayerMove(p);
                return 1;
            case 2: // View inventory
                myParty.viewPartyInventory();
                return 2;
            case 3: // View party stats
                myParty.viewPartyStats();
                System.out.println("Enter any key to continue");
                Input.scannerInput();
                return 3;
        }
        return -1;
    }

    // Is a method to be called after a player moves
    private void uponplayerMove(Party p) {
        switch(world.currPartySpace().spaceType()) {
            case "Common":
                boolean enterBattle = printStatement.uponCommonSpace();
                if(enterBattle) {
                    world.currPartySpace().beginAction(p);
                    if(!p.checkVitals()) {
                        System.out.println("L");
                        System.exit(0);
                    }
                }
                break;
            case "Market":
                boolean enterMarket = printStatement.uponMarketSpace();
                if(enterMarket) {
                    world.currPartySpace().beginAction(p);
                }
                break;
        }
    }

    private int startPage() {
       
        System.out.println("\nWelcome to the main page, pick an option! :)\n");
        System.out.println("0. Exit");
        System.out.println("1. Begin game");
        System.out.println("2. Change world size");
        return Input.untilNumberInput(0, 2);
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


    private void createWorld() {
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
        Heros h = new Heros(name, s1);
        System.out.println("You chose " + s1 + ". Enjoy the game.");
        System.out.println("======================");
        return h;
    }
}
