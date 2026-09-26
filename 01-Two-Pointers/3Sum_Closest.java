/*
Approach 1 — Brute Force ⭐ Understand First
Try every possible triplet.
Time:  O(n³)  3 elements → 3 loops → O(n³)
Space: O(1)
  */
//int closest = nums[0] + nums[1] + nums[2];

import java.util.*;

public class ThreeSumClosest {

    public static int threeSumClosest(int[] nums, int target) {

        int n = nums.length;

        // First possible triplet as initial answer
        int closest = nums[0] + nums[1] + nums[2];

        for (int i = 0; i < n - 2; i++) {

            for (int j = i + 1; j < n - 1; j++) {

                for (int k = j + 1; k < n; k++) {

                    int sum = nums[i] + nums[j] + nums[k];

                    // Is this sum closer to target?
                    if (Math.abs(sum - target) <
                        Math.abs(closest - target)) {

                        closest = sum;
                    }
                }
            }
        }

        return closest;
    }

    public static void main(String[] args) {

        int[] nums = {-1, 2, 1, -4};
        int target = 1;

        System.out.println(threeSumClosest(nums, target));
    }
}

/*
Approach 2 — Fix 2 + Binary Search ⭐
Sort the array.
target - nums[i] - nums[j]
Time:  O(n² log n)
Space: O(1) extra
*/

import java.util.*;

public class ThreeSumClosest {

    public static int threeSumClosest(int[] nums, int target) {

        Arrays.sort(nums);

        int n = nums.length;
        int closest = nums[0] + nums[1] + nums[2];

        for (int i = 0; i < n - 2; i++) {

            for (int j = i + 1; j < n - 1; j++) {

                int required = target - nums[i] - nums[j];

                // Binary search in remaining part
                int left = j + 1;
                int right = n - 1;

                while (left <= right) {

                    int mid = left + (right - left) / 2;

                    int sum = nums[i] + nums[j] + nums[mid];

                    if (Math.abs(sum - target) <
                        Math.abs(closest - target)) {
                        closest = sum;
                    }

                    if (nums[mid] < required) {
                        left = mid + 1;
                    } else if (nums[mid] > required) {
                        right = mid - 1;
                    } else {
                        // Exact target found
                        return target;
                    }
                }
            }
        }

        return closest;
    }

    public static void main(String[] args) {

        int[] nums = {-1, 2, 1, -4};
        int target = 1;

        System.out.println(threeSumClosest(nums, target));
    }
}

/*
Approach 3 — Fix 1 + Two Pointers ⭐⭐⭐
This is the main interview/LeetCode approach. sum = nums[i] + nums[left] + nums[right]

Sort the array.
Fix one element i.
Use two pointers:
left = i + 1
right = n - 1
Calculate the sum.
Move pointers based on the sum.
  */

import java.util.*;

public class ThreeSumClosest {

    public static int threeSumClosest(int[] nums, int target) {

        Arrays.sort(nums);

        int n = nums.length;
        int closest = nums[0] + nums[1] + nums[2];

        for (int i = 0; i < n - 2; i++) {

            int left = i + 1;
            int right = n - 1;

            while (left < right) {

                int sum = nums[i] + nums[left] + nums[right];

                // Update closest answer
                if (Math.abs(sum - target) <
                    Math.abs(closest - target)) {

                    closest = sum;
                }

                // Exact answer
                if (sum == target) {
                    return target;
                }

                // Need a bigger sum
                if (sum < target) {
                    left++;
                }

                // Need a smaller sum
                else {
                    right--;
                }
            }
        }

        return closest;
    }

    public static void main(String[] args) {

        int[] nums = {-1, 2, 1, -4};
        int target = 1;

        System.out.println(threeSumClosest(nums, target));
    }
}
