// https://www.geeksforgeeks.org/problems/max-sum-subarray-of-size-k5313/1
/*
his is the basic Fixed-Size Sliding Window problem.
Time  → O(n × k)
Space → O(1)
*/

public class MaximumSumSubarray {

    public static int maxSum(int[] arr, int k) {

        int maxSum = Integer.MIN_VALUE;

        for (int i = 0; i <= arr.length - k; i++) {

            int sum = 0;

            for (int j = i; j < i + k; j++) {
                sum += arr[j];
            }

            maxSum = Math.max(maxSum, sum);
        }

        return maxSum;
    }

    public static void main(String[] args) {

        int[] arr = {2, 1, 5, 1, 3, 2};
        int k = 3;

        System.out.println(maxSum(arr, k));
    }
}

/*
Approach 2 — Sliding Window ⭐
Instead of calculating the entire sum again, reuse the previous window's sum.
  newSum = oldSum - arr[left] + arr[right]

  right →
Add arr[right]

Window size == k?
       ↓
   Calculate answer
       ↓
 Remove arr[left]
       ↓
    left++
    
Time  → O(n) ⭐
Space → O(1)
  */

public class MaximumSumSubarray {

    public static int maxSum(int[] arr, int k) {

        int windowSum = 0;
        int maxSum = Integer.MIN_VALUE;

        int left = 0;

        for (int right = 0; right < arr.length; right++) {

            // Add current element
            windowSum += arr[right];

            // Window size becomes k
            if (right - left + 1 == k) {

                maxSum = Math.max(maxSum, windowSum);

                // Remove left element
                windowSum -= arr[left];
                left++;
            }
        }

        return maxSum;
    }

    public static void main(String[] args) {

        int[] arr = {2, 1, 5, 1, 3, 2};
        int k = 3;

        System.out.println(maxSum(arr, k));
    }
}

/* right moves → add
size == k   → calculate
left moves  → remove */

public class MaximumSumSubarray {

    public static int maxSum(int[] arr, int k) {

        int low = 0;
        int high = 1;

        int sum = arr[0];
        int res = arr[0];

        while (high < arr.length) {

            sum += arr[high];

            if (high - low + 1 == k) {
                res = Math.max(res, sum);

                sum -= arr[low];
                low++;
            }

            high++;
        }

        return res;
    }

    public static void main(String[] args) {

        int[] arr = {2, 1, 5, 1, 3, 2};
        int k = 3;

        System.out.println(maxSum(arr, k));
    }
}
