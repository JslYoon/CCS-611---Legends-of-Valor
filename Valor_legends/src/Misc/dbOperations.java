package src.Misc;
import java.io.BufferedWriter;
import java.io.File;  
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;


// Java class that holds non static methods for database operations

public class dbOperations {


    // Create player file database (if it doens't exist)
    public static boolean createArmors() {

        try {
            File myFile = new File("./db/items.txt"); 
            if (myFile.createNewFile()) { 
                return true;
            } else {
                return false;
            }
        } catch (IOException e) { 
            e.printStackTrace();
        }
        return false;
    }


    // read items file from the txt file database
    // Item datas are formatted as follows:
    // if armor/weapon: type,name,level,price,value
    // if potion/skill: type,name,level,price,value,numuse,stat(only for potion)
    public static ArrayList<String> readItemsFile() {
        try {
            File file = new File("./db/items.txt"); 
            Scanner scanner = new Scanner(file); 
            ArrayList<String> itemlist = new ArrayList<>();

            while (scanner.hasNextLine()) { 
                String playerData = scanner.nextLine();
                itemlist.add(playerData);
            }
            scanner.close();
            return itemlist;
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred. File was not found.");
            e.printStackTrace();
        }
        return null;
    }


    // read monsters file from the txt file database
    // Monster datas are stored as formatted:
    // name,level,type,hp,damage(str),defence,dodge,element
    public static ArrayList<String> readMonstersFile() {
        try {
            File file = new File("./db/monsters.txt"); 
            Scanner scanner = new Scanner(file); 
            ArrayList<String> itemlist = new ArrayList<>();

            while (scanner.hasNextLine()) { 
                String playerData = scanner.nextLine();
                itemlist.add(playerData);
            }
            scanner.close();
            return itemlist;
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred. File was not found.");
            e.printStackTrace();
        }
        return null;
    }




}