package src.Messages;

import src.Inventory.Items.Items;
import src.Misc.Input;

// java class that holds non static methods that print out error messages

public class ErrorMessage {
    
    public static void itemLevelError(Items i) {
        System.out.println("==== UNABLE TO USE ITEM BECAUSE PLAYER LEVEL IS LOWER THAN THE ITEM LEVEL OF "+ i.getLevel() + " ====");
        System.out.println("Enter any key to continue");
        Input.scannerInput();
    }

    public static void allItemsSoldError() {
        System.out.println("==== NO ITEMS EXIST HERE :( ====");
        System.out.println("Enter any key to continue");
        Input.scannerInput();

    }
}
