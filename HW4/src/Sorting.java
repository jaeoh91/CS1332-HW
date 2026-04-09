import java.util.Comparator;
import java.util.LinkedList;
import java.util.Random;

/**
 * Your implementation of various sorting algorithms.
 *
 * Your implementations must match what was taught in lecture and 
 * recitation to receive credit. Implementing a different sort or 
 * a different implementation for a sort will receive no credit even
 * if it passes comparison checks.
 *
 * @author Jaesang Oh
 * @version 1.0
 * @userid joh426
 * @GTID 904170848
 *
 * Collaborators: LIST ALL COLLABORATORS YOU WORKED WITH HERE
 *
 * Resources: LIST ALL NON-COURSE RESOURCES YOU CONSULTED HERE
 *
 * By typing 'I agree' below, you are agreeing that this is your
 * own work and that you are responsible for all the contents of 
 * this file. If this is left blank, you will lose points.
 * 
 * Agree Here: I agree
 */
public class Sorting {

    /**
     * Implement merge sort.
     *
     * It should be:
     * out-of-place
     * stable
     * not adaptive
     *
     * Have a worst case running time of:
     * O(n log n)
     *
     * And a best case running time of:
     * O(n log n)
     *
     * You can create more arrays to run merge sort, but at the end, everything
     * should be merged back into the original T[] which was passed in.
     *
     * When splitting the array, if there is an odd number of elements, put the
     * extra data on the right side.
     *
     * Hint: If two data are equal when merging, think about which subarray
     * you should pull from first
     *
     * @param <T>        data type to sort
     * @param arr        the array to be sorted
     * @param comparator the Comparator used to compare the data in arr
     * @throws IllegalArgumentException if the array or comparator is
     *                                            null
     */
    public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null)  {
            throw new IllegalArgumentException("array and comparator cannot be null");
        }

        // Check recursion base case: array has 1 or 0 elements
        if (arr.length <= 1)    {
            return;
        }

        int length = arr.length;
        int middleIndex = length / 2;

        // allocate bounds such that extra data ends up on right side if odd # of elements
        // arr[0:middleIndex)
        T[] leftArray = (T[]) new Object[middleIndex];
        // arr[middleIndex:length)
        T[] rightArray = (T[]) new Object[length - middleIndex];

        for (int i = 0; i < middleIndex; i++)   {
            leftArray[i] = arr[i];
        }
        for (int i = middleIndex; i < length; i++)  {
            rightArray[i - middleIndex] = arr[i];
        }

        // divide
        mergeSort(leftArray, comparator);
        mergeSort(rightArray, comparator);

        // time to conquer / merge
        int leftArrayIndex = 0; // tracks where we are on leftArray
        int rightArrayIndex = 0; // tracks where we are on rightArray
        int mainArrayIndex = 0; // // tracks where we are on arr

        while (leftArrayIndex < leftArray.length && rightArrayIndex < rightArray.length)  {
            T leftElement = leftArray[leftArrayIndex];
            T rightElement = rightArray[rightArrayIndex];

            // '<= 0' here ensures stable mergesort
            if (comparator.compare(leftElement, rightElement) <= 0)   {
                arr[mainArrayIndex] = leftElement;
                leftArrayIndex++;
            } else {
                arr[mainArrayIndex] = rightElement;
                rightArrayIndex++;
            }
            mainArrayIndex++;
        }

        // clean up stragglers
        // either leftArray or rightArray will have at least one element not added
        // bc one of them will have 'depleted first' in the above while loop
        while (leftArrayIndex < leftArray.length)   {
            arr[mainArrayIndex] = leftArray[leftArrayIndex];
            leftArrayIndex++;
            mainArrayIndex++;
        }
        while (rightArrayIndex < rightArray.length) {
            arr[mainArrayIndex] = rightArray[rightArrayIndex];
            rightArrayIndex++;
            mainArrayIndex++;
        }
    }
    /**
     * Implement kth select.
     *
     * Use the provided random object to select your pivots. For example if you
     * need a pivot between a (inclusive) and b (exclusive) where b > a, use
     * the following code:
     *
     * int pivotIndex = rand.nextInt(b - a) + a;
     *
     * If your recursion uses an inclusive b instead of an exclusive one,
     * the formula changes by adding 1 to the nextInt() call:
     *
     * int pivotIndex = rand.nextInt(b - a + 1) + a;
     *
     * It should be:
     * in-place
     * not stable
     * not adaptive
     *
     * Have a worst case running time of:
     * O(n^2)
     *
     * And a best case running time of:
     * O(n)
     *
     * You may assume that the array doesn't contain any null elements.
     *
     * Make sure you code the algorithm as you have been taught it in class.
     * There are several versions of this algorithm and you may not get full
     * credit if you do not implement the one we have taught you!
     *
     * @param <T>        data type to sort
     * @param k          the index to retrieve data from + 1 (due to
     *                   0-indexing) if the array was sorted; the 'k' in "kth
     *                   select"; e.g. if k == 1, return the smallest element
     *                   in the array
     * @param arr        the array that should be modified after the method
     *                   is finished executing as needed
     * @param comparator the Comparator used to compare the data in arr
     * @param rand       the Random object used to select pivots
     * @return the kth smallest element
     * @throws IllegalArgumentException if the array or comparator
     *                                            or rand is null or k is not
     *                                            in the range of 1 to arr
     *                                            .length
     */
    public static <T> T kthSelect(int k, T[] arr, Comparator<T> comparator,
                                    Random rand) {
        if (arr == null || comparator == null || rand == null)   {
            throw new IllegalArgumentException("kthSelect requires a non-null array, comparator, and null");
        }
        if (k < 1 || k > arr.length)    {
            throw new IllegalArgumentException("k must be in [1, arr.length]");
        }

        return kthSelectH(k, 0, arr.length - 1, arr, comparator, rand);
    }


    /**
     * Helper function for recursive implementation of kthSelect.
     * @param k the index to retrieve data from, uses 1-indexing
     * @param left inclusive lower bound for the current array section to operate on
     * @param right inclusive upper bound for the current array section
     * @param arr The array to perform kth select on
     * @param comparator Comparator used to compare data in arr
     * @param rand Random object used to generate random numbers for pivot
     * @param <T> Data type in arr
     * @return kth smallest value
     */
    private static <T> T kthSelectH(int k, int left, int right, T[] arr,
                                       Comparator<T> comparator, Random rand) {
        // use formula for exclusive end bound
        int pivotIndex = left + rand.nextInt(right - left + 1);
        T dataAtPivot = arr[pivotIndex];
        swap(arr, left, pivotIndex);

        int i = left + 1;
        int j = right;
        while (j >= i) {
            while (j >= i && comparator.compare(arr[i], dataAtPivot) <= 0) {
                i++;
            }

            while (j >= i && comparator.compare(arr[j], dataAtPivot) >= 0)  {
                j--;
            }

            if (j >= i) {
                swap(arr, i, j);
                i++;
                j--;
            }
        }

        // this moves pivot to correct position
        swap(arr, left, j);

        // check if we've found the k-th smallest element yet
        if (j == k - 1) {
            return arr[j];
        } else if (j > k - 1)   { // left
            return kthSelectH(k, left, j - 1, arr, comparator, rand);
        } else { // right
            return kthSelectH(k, j + 1, right, arr, comparator, rand);
        }
    }

    /**
     * Private helper method to swap two entries in an array.
     * @param arr The Array to modify
     * @param i Index #1
     * @param j Index #2
     * @param <T> Data type in arr
     */
    private static <T> void swap(T[] arr, int i, int j) {
        T tempData = arr[i];
        arr[i] = arr[j];
        arr[j] = tempData;
    }

    /**
     * Implement LSD (least significant digit) radix sort.
     *
     * Make sure you code the algorithm as you have been taught it in class.
     * There are several versions of this algorithm and you may not get full
     * credit if you do not implement the one we have taught you!
     *
     * Remember you CANNOT convert the ints to strings at any point in your
     * code! Doing so may result in a 0 for the implementation.
     *
     * It should be:
     * out-of-place
     * stable
     * not adaptive
     *
     * Have a worst case running time of:
     * O(kn)
     *
     * And a best case running time of:
     * O(kn)
     *
     * You are allowed to make an initial O(n) passthrough of the array to
     * determine the number of iterations you need.
     *
     * At no point should you find yourself needing a way to exponentiate a
     * number; any such method would be non-O(1). Think about how how you can
     * get each power of BASE naturally and efficiently as the algorithm
     * progresses through each digit.
     *
     * Refer to the PDF for more information on LSD Radix Sort.
     *
     * You may use ArrayList or LinkedList if you wish, but it may only be
     * used inside radix sort and any radix sort helpers. Do NOT use these
     * classes with other sorts. However, be sure the List implementation you
     * choose allows for stability while being as efficient as possible.
     *
     * Do NOT use anything from the Math class except Math.abs().
     *
     * @param arr the array to be sorted
     * @throws IllegalArgumentException if the array is null
     */
    public static void lsdRadixSort(int[] arr) {
        if (arr == null)    {
            throw new IllegalArgumentException("Cannot perform lsdRadixSort on null array");
        }

        // edge case empty array / only 1 element
        if (arr.length <= 1)    {
            return;
        }

        // go by magnitude / abs value because negative numbers can exist
        int maxMagnitude = 0;
        int k = 0;

        for (int i = 0; i < arr.length; i++)    {
            // handle overflow w/ Math.abs()
            // prob bc of twos compliment signing, bound is [2^n, 2^n)
            // Math.abs() can't represent -1 * Integer.MIN_VALUE
            // -2,147,483,648 btw
            if (arr[i] == Integer.MIN_VALUE)    {
                k = 10; // max magnitude / magnitude of Integer.MIN_VALUE
                break;
            }

            int curMagnitude = Math.abs(arr[i]);
            if (curMagnitude > maxMagnitude)   {
                maxMagnitude = curMagnitude;
            }
        }

        k = (k != 10) ? countDigits(maxMagnitude) : 10;

        // 19 buckets for [-9,9]
        LinkedList<Integer>[] buckets = new LinkedList[19];
        for (int i = 0; i < 19; i++)    {
            buckets[i] = new LinkedList<>();
        }

        long curBase = 1; // to avoid potential overflow if k is maxed out
        for (int i = 0; i < k; i++)    {
            for (int j = 0; j < arr.length; j++)    {
                int curNum = arr[j];
                int bucketIndex = (int) (curNum / curBase % 10); // -> [-9, 9]
                buckets[bucketIndex + 9].addLast(curNum); // -> [0, 18]
                // note addLast and removeFront preserves stability
                // we use the LinkedList like a queue
            }

            // dequeue all
            int curIndex = 0;
            for (int curBucket = 0; curBucket < 19; curBucket++)    {
                while (!buckets[curBucket].isEmpty())    {
                    arr[curIndex] = buckets[curBucket].removeFirst();
                    curIndex++;
                }
            }

            curBase *= 10;
        }
    }

    /**
     * Counts the number of digits in a number.
     * Time complexity of k, where k is the number of digits in n
     * @param n Number to check
     * @return The number of digits in n
     */
    private static int countDigits(int n)  {
        // special case n = 0
        if (n == 0) {
            return 1;
        }

        int count = 0;
        while (n != 0)   {
            n = n / 10;
            count++;
        }
        return count;
    }
}
