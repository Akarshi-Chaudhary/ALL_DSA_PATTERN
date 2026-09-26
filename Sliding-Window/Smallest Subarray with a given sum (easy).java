// https://leetcode.com/problems/minimum-size-subarray-sum/
/*
This is a Variable-Size Sliding Window problem. sum >= target
      ↓
calculate answer
      ↓
remove left
      ↓
keep shrinking
  high → expand
low  → shrink

sum < S  → expand
sum >= S → shrink
Here we expand using high, and whenever the sum becomes >= S, we shrink from the left
Time  → O(n)
Space → O(1).
*/
  import java.util.*;

public class SmallestSubarray {

    public static int minSubArrayLen(int[] a, int s) {
        int n = a.length;
        int low = 0;
        int high = 0;
        int sum = 0;
        int res = Integer.MAX_VALUE;

        while (high < n) {
            sum += a[high];
            // Shrink while sum is enough
            while (sum >= s) {
                res = Math.min(res, high - low + 1);
               sum -= a[low];
                low++;
            }
           high++;
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }
    public static void main(String[] args) {
        int[] a = {2, 1, 5, 2, 3, 2};
        int s = 7;

        System.out.println(minSubArrayLen(a, s));
    }
}

/*
brute force O(n^2)
*/
public class SmallestSubarray {
    public static int minSubArrayLen(int[] a, int s) {
        int n = a.length;
        int res = Integer.MAX_VALUE;
      
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
               sum += a[j];
                if (sum >= s) {
                  res = Math.min(res, j - i + 1);
                    break;
                }
            }
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }
    public static void main(String[] args) {
        int[] a = {2, 1, 5, 2, 3, 2};
        int s = 7;
        System.out.println(minSubArrayLen(a, s));
    }
}
