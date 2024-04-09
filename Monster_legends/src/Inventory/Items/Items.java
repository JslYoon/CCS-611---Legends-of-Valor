package src.Inventory.Items;
import java.util.*;

// java interface for all items

public interface Items {

    public int getPrice();
    public String getName();
    public int getLevel();
    public String toString();
    public String getType();

    public int getValue();
}