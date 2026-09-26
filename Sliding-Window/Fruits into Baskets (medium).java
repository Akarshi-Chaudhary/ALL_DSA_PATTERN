/*
	https://leetcode.com/problems/fruit-into-baskets/
  Fruits into Baskets = Longest Subarray with At Most 2 Distinct Elements   ---Fruits + 2 baskets + contiguous
high → add fruit
       ↓
types > 2 ?
       ↓ YES
low → remove fruit
       ↓
types <= 2
       ↓
update res

Time  → O(n)
Space → O(2) ≈ O(1)
*/
import java.util.*;
public class FruitsIntoBaskets {
    public static int totalFruit(int[] fruits) {

        int low = 0;
        int res = 0;
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int high = 0; high < fruits.length; high++) {
            int fruit = fruits[high];
            map.put(fruit, map.getOrDefault(fruit, 0) + 1);
            // More than 2 types
          
            while (map.size() > 2) {
                int leftFruit = fruits[low];
                map.put(leftFruit, map.get(leftFruit) - 1);
              
                if (map.get(leftFruit) == 0) {
                    map.remove(leftFruit);
                }
                low++;
            }
            res = Math.max(res, high - low + 1);
        }
        return res;
    }
    public static void main(String[] args) {
        int[] fruits = {1, 2, 1, 2, 3};
        System.out.println(totalFruit(fruits));
    }
}


//For learning, I recommend this clean version using two variables + last occurrence:
//complicted tahn hashmap approch

public class FruitsIntoBaskets {
    public static int totalFruit(int[] fruits) {

        int low = 0;
        int res = 0;
        int type1 = -1;
        int type2 = -1;
        int lastType2 = -1;

        for (int high = 0; high < fruits.length; high++) {
            if (fruits[high] != type1 && fruits[high] != type2) {
                // New third type
                low = lastType2 + 1;
                type1 = type2;
                type2 = fruits[high];
            }
          
            if (fruits[high] == type2) {
                lastType2 = high;
            }
            res = Math.max(res, high - low + 1);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] fruits = {1, 2, 1, 2, 3};
        System.out.println(totalFruit(fruits));
    }
}
