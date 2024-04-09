package src.Misc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class RandomSelection {

     // Method to select a key based on its weighted probability
     public static <K> K KeyProbability(HashMap<K, Integer> map) {
        int totalWeight = map.values().stream().mapToInt(Integer::intValue).sum();

        Random rand = new Random();
        int randomNumber = rand.nextInt(totalWeight);

        for (Map.Entry<K, Integer> entry : map.entrySet()) {
            randomNumber -= entry.getValue();
            if (randomNumber < 0) {
                return entry.getKey();
            }
        }
        return null;
    }

    // Returns a random number in range
    public static int getRandomNumberInRange(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    // Returns a random double in range
    public static double getRandomMultiplier() {
        Random random = new Random();
        return random.nextDouble() + 1;
    }

    public static boolean isSuccess(int percentage) {
        if (percentage < 0 || percentage > 100) {
            throw new IllegalArgumentException("Percentage must be between 0 and 100.");
        }
        Random random = new Random();
        return random.nextInt(100) < percentage;
    }

    public static <T> ArrayList<T> selectRandomly(ArrayList<T> list, int n) {

        ArrayList<T> shuffledList = new ArrayList<>(list);
        Collections.shuffle(shuffledList);
        
        if (n >= list.size()) {
            return shuffledList;
        }
        
        return new ArrayList<>(shuffledList.subList(0, n));
    }
    

}
