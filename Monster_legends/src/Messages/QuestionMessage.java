package src.Messages;

import src.Entities.Players.Heros;
import src.Inventory.Items.Items;
import src.Misc.*;

public class QuestionMessage {
    

    public static boolean itemEquipQuestion(Heros h, Items i) {
        System.out.println("=======================");
        
        System.out.println("You have selected " + i.getType() + ": " + i.getValue() + ".");
        if(i.getType().equals("Armor")) {
            System.out.println("You are currently equipped with an armor " + h.getArmor().getName() + ": " + h.getArmor().getValue() + ".");
        }
        if(i.getType().equals("Weapon")) {
            System.out.println("You are currently equipped with a weapon " + h.getWeapon().getName() + ": " + h.getWeapon().getValue() + ".");
        }
        System.out.println("Would you like to switch?");
        System.out.println("=======================");
        return Input.untilBooleanInput();
    }

    public static boolean potionUseQuestion(Heros h, Items i) {
        System.out.println("=======================");
        System.out.println("You have selected " + i.getType() + ": " + i.getValue() + ".");
        System.out.println("Would you like to use this potion?");
        System.out.println("=======================");
        return Input.untilBooleanInput();
    }

    public static boolean spellUseQuestion(Heros h, Items i) {
        System.out.println("=======================");
        System.out.println("You have selected " + i.getType() + ": " + i.getValue() + ".");
        System.out.println("Would you like to use this Spell? Using this outside battle would not have any effect (except looking cool).");
        System.out.println("=======================");
        return Input.untilBooleanInput();
    }



}
