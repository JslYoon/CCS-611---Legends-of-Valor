package src.Messages;

import java.util.*;

import src.Entities.Entities;
import src.Misc.Input;
import src.Misc.RandomSelection;
import src.World.Spaces.Spaces;

// Class that contains all the print statements

public class printStatement {
    
    public static void listActions(ArrayList<?> al) {
        for(int i = 0; i < al.size(); i++) {
            System.out.println( i + ": " + al.get(i));
        }
    }


    public static int battlePrint(Entities e) {
        System.out.println(e + "'s turn. Choose an action");
        System.out.println("0. Attack");
        System.out.println("1. Inventory");
        int i = Input.untilNumberInput(0, 1);
        if ( i == -1 ) {
            System.out.println("You cannot run from a battle");
            return -1;
        }
        return i;
    }

    // returns the return option number from the given arraylist
    public static int chooseFromOption(ArrayList<?> al) {
        listActions(al);
        System.out.println("Which option would you like to choose?");
        return Input.untilNumberInput(0, al.size()-1);

    }

    public static int playerMoveOption(Spaces s) {

        System.out.println("0. Quit game");
        System.out.println("1. Move party");
        System.out.println("2. Open inventory");
        System.out.println("3. View party");
        return Input.untilNumberInput(0, 3);
    }

    public static boolean uponCommonSpace() {

        System.out.println("===============");
        System.out.println("This is common space");
        if (RandomSelection.isSuccess(35)) {
            System.out.println("Monster encounter!!!");
            return true;
        }
        return false;

    }

    public static boolean uponMarketSpace() {

        System.out.println("===============");
        System.out.println("This is market space");

        System.out.println("Would you like to enther this space?");
        return Input.untilBooleanInput();

    }

    public static boolean uponNexusSpace() {

        System.out.println("===============");
        System.out.println("This is nexus space");
        
        System.out.println("Would you like to enther this space?");

        return Input.untilBooleanInput();

    }




}
