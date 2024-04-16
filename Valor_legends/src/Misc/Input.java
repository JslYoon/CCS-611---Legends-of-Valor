// A class with static methods that are associated with scanner input functionalities

package src.Misc;

import java.util.ArrayList;
import java.util.Scanner;

public class Input {
    private static final Scanner action = new Scanner(System.in); // global scanner used for all user input

    

    public static boolean inRange(int input, int min, int max) {
        return input >= min && input <= max;
    }

    public static boolean isIntString(String s) {
        try {
            Integer.parseInt(s);
         }
         catch (NumberFormatException e) {
            return false;
         }
         return true;
    } 

    public static boolean stringNumberInput(String game_num, int min, int max) { 
        return Input.isIntString(game_num) && Input.inRange(Integer.parseInt(game_num), min, max);
    }

    public static String scannerInput() {
        return action.nextLine();
    }

    public static int untilNumberInput(int min, int max) {
        System.out.println("Enter a number. Enter q to quit");
        while (true) {
            String s = scannerInput();
            if (stringNumberInput(s, min, max)){
                return Integer.parseInt(s);
            }
            if(s.equals("q")) { return -1; }

            System.out.println("Invalid Option");
        }
    }

    public static boolean untilBooleanInput() {
        System.out.println("t/f for true or false. Enter q to quit");
        while (true) {
            String s = scannerInput();

            if(s.equals("t")) { return true; }
            if(s.equals("f")) { return false; }
            if(s.equals("q")) { return false; }

            System.out.println("Invalid Option");
        }
    }


    public static int untilGivenNumerInput(ArrayList<Integer> al) {
        while(true) {
            String s = scannerInput();

            for(int i:al){
                if (stringNumberInput(s, i, i)){
                    return Integer.parseInt(s);
                }
            }
            System.out.println("Invalid Option");
        }
    }

    public static String untilGivenStringInput(ArrayList<String> al) {
        while(true) {
            String s = scannerInput();

            
            if (al.contains(s)) {
                return s;
            }
            
            System.out.println("Invalid Option");
        }
    }

    public static char getMovementInput() {
        System.out.println("Enter 'w' (up), 'a' (left), 's' (down), 'd' (right) to move. Enter 'q' to quit.");
        while (true) {
            String input = scannerInput().toLowerCase();
            if (input.equals("w") || input.equals("a") || input.equals("s") || input.equals("d") || input.equals("q")) {
                return input.charAt(0); 
            } 
            System.out.println("Invalid Option");
            
        }
    }

    public static char getValorMovementInput() {
        System.out.println("Enter 'w' (up), 'a' (left), 's' (down), 'd' (right) to move. Enter 't' to teleport, 'r' to recall. Enter 'q' to quit.");
        while (true) {
            String input = scannerInput().toLowerCase();
            if (input.equals("w") || input.equals("a") || input.equals("s") || input.equals("d") || input.equals("q") || input.equals("t") || input.equals("r")) {
                return input.charAt(0); 
            } 
            System.out.println("Invalid Option");
            
        }
    }


    public static char getTeleportMovement() {
        System.out.println("Enter 'a' (left of hero), 's' (below hero), 'd' (right of hero) to move.");
        while (true) {
            String input = scannerInput().toLowerCase();
            if (input.equals("a") || input.equals("s") || input.equals("d")) {
                return input.charAt(0); 
            } 
            System.out.println("Invalid Option");
            
        }
    }

    public static String stringInput() {
        while (true) {
            System.out.println("Enter input. Enter 'q' to quit :( ");
            String input = scannerInput();
            
            if ("q".equalsIgnoreCase(input)) {
                System.out.println("Quitting...");
                return null; 
            } else {
                return input;
            }
            
        }
    }
}
