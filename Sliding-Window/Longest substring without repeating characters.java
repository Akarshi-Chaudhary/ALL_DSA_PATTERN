/*          LONGEST SUBSTRING
                  │
                  ▼
        ┌───────────────────┐
        │ left = 0          │
        │ maxLen = 0        │
        │ HashMap<Character │
        │ → last index      │
        └─────────┬─────────┘
                  │
                  ▼
          for right = 0 → n-1
                  │
                  ▼
          Is s[right] seen?
             /          \
           NO            YES
           │              │
           │       left = max(
           │         left,
           │         lastIndex + 1
           │       )
           │              │
           └──────┬───────┘
                  ▼
       map.put(char, right)
                  │
                  ▼
       maxLen = max(
          maxLen,
          right - left + 1
       )

Time  → O(n)
Space → O(min(n, character_set))

Duplicate?
    ↓
Jump LEFT
    ↓
Update LAST INDEX
    ↓
Update MAX LENGTH
*/
public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int left = 0, maxLen = 0;
        Map<Character, Integer> lastIndex = new HashMap<>();

        for (int right = 0; right < n; right++) {
            char c = s.charAt(right);

            // if char already seen, move left pointer after its last occurrence
            if (lastIndex.containsKey(c)) {
                left = Math.max(left, lastIndex.get(c) + 1);
            }

            lastIndex.put(c, right);
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }

/*Approach 2 — Sliding Window + HashSet
  high → add character
        ↓
already present?
    YES → remove from left
    NO  → expand
        ↓
update maxLen
///and  
  
          Sliding Window
              │
              ▼
        right → expand
              │
              ▼
       character exists?
          /          \
        YES           NO
         │             │
    remove left      add char
    left++              │
         │              │
         └──────┬───────┘
                ▼
          update maxLen

  */
public int lengthOfLongestSubstring(String s) {

    HashSet<Character> set = new HashSet<>();

    int left = 0;
    int maxLen = 0;

    for (int right = 0; right < s.length(); right++) {

        char c = s.charAt(right);

        while (set.contains(c)) {
            set.remove(s.charAt(left));
            left++;
        }

        set.add(c);

        maxLen = Math.max(maxLen, right - left + 1);
    }

    return maxLen;
}
