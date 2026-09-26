// problems involving 0, 1, 2 / three categories / partitioning.

//brute 
import java.util.*;

class Main {
    public static void main(String[] args) {

        int[] arr = {2, 0, 2, 1, 1, 0};

        Arrays.sort(arr);

        System.out.println(Arrays.toString(arr));
    }
}
/*
Time:  O(n log n)
Space: O(log n) approximately due to sorting implementation
  */

//optimise Approach 2 — Counting / Frequency --> best approch bcz 2 passes of array 
//Because we know there are only 0, 1, and 2, count each one.

class Main {

    public static void sortColors(int[] arr) {

        int count0 = 0;
        int count1 = 0;
        int count2 = 0;

        for (int x : arr) {
            if (x == 0)
                count0++;
            else if (x == 1)
                count1++;
            else
                count2++;
        }

        int index = 0;

        while (count0-- > 0)
            arr[index++] = 0;

        while (count1-- > 0)
            arr[index++] = 1;

        while (count2-- > 0)
            arr[index++] = 2;
    }
}
/*Time:  O(n)
Space: O(1)*/

/*Approach 3 — Dutch National Flag ⭐ best bcoz single pass of array 
This is the classic interview solution.
low = 0
mid = 0
high = n - 1

[0 ... low-1]       → all 0
[low ... mid-1]     → all 1
[mid ... high]      → unknown --> this is what we need to sort 
[high+1 ... n-1]    → all 2

Time:  O(n)
Space: O(1)
  */

class Main {

    public static void sortColors(int[] arr) {

        int low = 0;
        int mid = 0;
        int high = arr.length - 1;

        while (mid <= high) {

            if (arr[mid] == 0) {

                swap(arr, low, mid);

                low++;
                mid++;

            } else if (arr[mid] == 1) {

                mid++;

            } else { // arr[mid] == 2

                swap(arr, mid, high);

                high--;
            }
        }
    }

    static void swap(int[] arr, int i, int j) {

        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}


// ques use this 
/*
Sort Colors
0/1/2 sorting
Partition arrays
QuickSort 3-way partition
Grouping elements into 3 categories
Problems involving < pivot, == pivot, > pivot
  */
