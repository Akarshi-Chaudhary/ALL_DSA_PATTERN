/*
longest Substring with K Distinct Characters — Medium
This is a Variable-Size Sliding Window + HashMap problem
  approch : " low = 0, high = 0, HashMap = frequency of characters, res = maximum length" 
Time  → O(n), Space → O(k)

          Window
             |
     ┌───────┼───────┐
     ↓       ↓       ↓
   < K      == K    > K
     |       |       |
  Expand   Update   Shrink
                    low++

high → expand window
        ↓
add character to map

distinct > k
        ↓
shrink from low

distinct == k
        ↓
update maximum

  */

public int longestKSubstr(String s, int k) {
        int n = s.length();
        int low = 0, res = -1;
        Map<Character, Integer> freq = new HashMap<>();

        for (int high = 0; high < n; high++) {
            char c = s.charAt(high);
            freq.put(c, freq.getOrDefault(c, 0) + 1);

            // shrink window if more than k unique
            while (freq.size() > k) {
                char leftChar = s.charAt(low);
                freq.put(leftChar, freq.get(leftChar) - 1);
                if (freq.get(leftChar) == 0)
                    freq.remove(leftChar);
                low++;
            }
            // if exactly k unique, update answer
            if (freq.size() == k) {
                res = Math.max(res, high - low + 1);
            }
        }

        return res;

// from scratch  
  import java.util.*;
public class LongestSubstringKDistinct {
    public static int longestSubstring(String s, int k) {
        int low = 0;
        int high = 0;
        int res = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        while (high < s.length()) {
            // Add character
            char ch = s.charAt(high);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
            // Too many distinct characters
            while (map.size() > k) {
                char leftChar = s.charAt(low);
                map.put(leftChar, map.get(leftChar) - 1);
                if (map.get(leftChar) == 0) {
                    map.remove(leftChar);
                }
                low++;
            }
            // Exactly k distinct characters
            if (map.size() == k) {
                res = Math.max(res, high - low + 1);
            }
            high++;
        }
        return res;
    }
    public static void main(String[] args) {
        String s = "araaci";
        int k = 2;
        System.out.println(longestSubstring(s, k));
    }
}


// using forloop --> much best 

import java.util.*;
public class LongestSubstringKDistinct {
    public static int longest(String s, int k) {

        int low = 0;
        int res = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        for (int high = 0; high < s.length(); high++) {
            char ch = s.charAt(high);
            map.put(ch, map.getOrDefault(ch, 0) + 1);

            while (map.size() > k) {
                char left = s.charAt(low);
                map.put(left, map.get(left) - 1);

                if (map.get(left) == 0)
                    map.remove(left);
                low++;
            }
            res = Math.max(res, high - low + 1);
        }
        return res;
    }
    public static void main(String[] args) {
       String s = "araaci";
        int k = 2;
        System.out.println(longest(s, k));
    }
}
