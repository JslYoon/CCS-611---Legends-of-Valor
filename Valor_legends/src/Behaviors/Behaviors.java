package src.Behaviors;

import src.Entities.Entities;
import src.Entities.Enemies.Monsters;
import src.Entities.Players.Heros;
import src.Entities.Players.Party;
import src.Inventory.Inventory;
import src.Messages.printStatement;

import src.Misc.RandomSelection;
import src.World.World;

import java.util.*;

public class Behaviors {
    


 

    public static boolean Playerbattle(Party me, Party you) {

        List<Entities> myparty = me.getAlivePartyMembers();
        if(!me.checkVitals() || !you.checkVitals()) {
            return false;
        }
        for(Entities e: myparty) {
            List<Entities> yourparty = you.getAlivePartyMembers();
            boolean movemade = false;
            int choose;
            if(e.getHP() <= 0) {
                continue;
            }
            while(true) {
                if(!me.checkVitals() || !you.checkVitals()) {
                    return false;
                }
                switch(printStatement.battlePrint(e)) {
                    case -1: // tried to quit
                        System.out.print("Cannot quit right now");
                        break;
                    
                    case 0: // normal attack
                        System.out.println("Which enemy should " + e.getName() + " attack?");
                        choose = printStatement.chooseFromOption((ArrayList<Entities>) yourparty);
                        if(choose == -1) {
                            break;
                        } else {
                            int dmg = e.Attack(null);
                            Entities entity = yourparty.get(choose);
                            System.out.println("You are attacking " + entity + " with normal attack damage of " + dmg);
                            System.out.println(entity + " recieved " + entity.Defence(dmg) + " damage.");
                            if(entity.getHP() <= 0) {
                                System.out.println("You have defeated " + entity.getName());
                                e.gainExp(entity.getLevel() * RandomSelection.getRandomNumberInRange(18, 35));
                                e.gainGold(entity.getLevel() * RandomSelection.getRandomNumberInRange(70, 115) );
                            }
                            movemade = true;
                            break;
                        }
                    case 1: // spell atack
                        Inventory inven = e.getInventory();
                        int usageItem = inven.itemList();
                        if(usageItem == -1) {
                            movemade = false;
                            break;
                        } else {
                            Heros aHero = (Heros) e;
                            int i = aHero.useItem(inven.getAllItems().get(usageItem));
                            if(i >= 1) {
                                System.out.println("Which enemy should " + e.getName() + " attack?");
                                choose = printStatement.chooseFromOption((ArrayList<Entities>) yourparty);
                                if(choose == -1) {
                                    break;
                                } else {
                                    int dmg = e.Attack(inven.getAllItems().get(usageItem));
                                    Entities entity = yourparty.get(choose);
                                    System.out.println("You are attacking " + entity + " with normal Spell damage of " + dmg);
                                    System.out.println(entity + " recieved " + entity.Defence(dmg) + " damage.");
                                    if(entity.getHP() <= 0) {
                                        System.out.println("You have defeated " + entity.getName());
                                        e.gainExp(entity.getLevel() * RandomSelection.getRandomNumberInRange(18, 35));
                                        e.gainGold(entity.getLevel() * RandomSelection.getRandomNumberInRange(70, 115) );
                                    }
                                    movemade = true;
                                    break;
                                }

                            }
                            movemade = true;
                            break;
                        }
                }
                
                if(movemade) {
                     break;
                }
            }
        }
        return true;
    }

    public static boolean Monsterbattle(Party me, Party you) {
        for(Entities e: me.getPartyMembers()) {
            while (true) {
                int choose;
                if(you.getAlivePartyMembers().size() == 1) { 
                    choose = 0; 
                } else {
                    choose = RandomSelection.getRandomNumberInRange(0, you.getPartyMembers().size() - 1);
                }
                Entities player = you.getPartyMembers().get(choose);
                if(player.getHP() != 0) {
                    int dmg = e.Attack(null);
                    System.out.println(e + " attacked " + player + " with damage of " + dmg);
                    System.out.println(player + " recieved " + player.Defence(dmg) + " damage.");
                    return true;
                }
            }
        }
        return false;
    }

    public static void PlayerVsMonster(Party player) {

        ArrayList<Entities> entities = player.getPartyMembers();
        ArrayList<Entities> enemies = new ArrayList<>();
        
        for(int i = 0; i < entities.size(); i++) {
            enemies.add(new Monsters((Heros)entities.get(i)));
        }

        Party enemyParty = new Party(enemies);
        while(true) {
            Behaviors.Playerbattle(player, enemyParty);
            if(!player.checkVitals() || !enemyParty.checkVitals()) {
                System.out.println("Congrats you defeated the monsters");
                return;
            }
            Behaviors.Monsterbattle(enemyParty, player);
            if(!player.checkVitals() || !enemyParty.checkVitals()) {
                System.out.println("You Lost");
                return;
            }
        }
    }

    public static void monsterMove(Entities m, World w) {
        
    }

}
