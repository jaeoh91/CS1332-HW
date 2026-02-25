import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Solve a challenge using Java's built-in PriorityQueue.
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
 *
 */
public class MaxHeapChallenge {

    /**
     * In a Heap or PriorityQueue, the "peek" operation typically returns the topmost
     * element without removing it, allowing users to see it without changing the heap contents.
     *
     * Here, we want to do the same, but for an arbitrary k-th largest value. You
     * should return this data while also maintaining the contents of the heap such
     * that the heap before and after this operation appears unchanged to the user.
     *
     * You should use Java's PriorityQueue for this method. No credit will be given for
     * solutions that do not use a PriorityQueue. Try to make it as efficient as possible.
     *
     * Example: given the MaxHeap
     *         8
     *       /   \
     *      7     5
     *     / \   / \
     *    6   3 4   2
     * kthLargestPeek(heap, 4) should return the 4-th largest data, which is 5.
     * The heap should still have all elements present in it at the end of the method.
     *
     * See the Java PriorityQueue documentation:
     * https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/PriorityQueue.html
     * 
     * Expected efficiency is O(k log n)
     * ! Piaza Notes: can assume only MaxHeaps will be passed in
     *
     * @param <T> the type of object stored in heap
     * @param heap the heap that we want to find the k-th largest in
     * @param k of k-th largest, i.e. k=3 is the third-largest value
     * @return T the k-th largest data value in the heap
     * @throws IllegalArgumentException if k < 1 or k > size, or if heap is null
     */
    public static <T extends Comparable<T>> T kthLargestPeek(PriorityQueue<T> heap, int k) {
        if (heap == null || k < 1 || k > heap.size()) {
            throw new IllegalArgumentException("Heap has invalid size for kthLargestPeek()");
        }

        ArrayList<T> removed = new ArrayList<T>(k);
        for (int i = 1; i < k; i++) { // ~k iterations
            removed.add(heap.remove()); // O(log n)
        }

        T kthLargest = heap.peek();
        for (int i = 0; i < k - 1; i++) { // ~k iterations
            heap.add(removed.get(i)); // O(log n)
        }
        return kthLargest;
    }
}
