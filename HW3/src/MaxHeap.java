import java.util.ArrayList;

/**
 * Your implementation of a MaxHeap.
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
 * this file. If this is left blank, this homework will receive a zero.
 * 
 * Agree Here: I agree
 */
public class MaxHeap<T extends Comparable<? super T>> {

    /*
     * The initial capacity of the MaxHeap when created with the default
     * constructor.
     *
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final int INITIAL_CAPACITY = 13;

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private T[] backingArray; // items from [1, this.size]
    private int size;

    /**
     * Constructs a new MaxHeap.
     *
     * The backing array should have an initial capacity of INITIAL_CAPACITY.
     */
    public MaxHeap() {
        this.clear();
    }

    /**
     * Creates a properly ordered heap from a set of initial values.
     *
     * You must use the BuildHeap algorithm that was taught in lecture! Simply
     * adding the data one by one using the add method will not get any credit.
     * As a reminder, this is the algorithm that involves building the heap
     * from the bottom up by repeated use of downHeap operations.
     *
     * Before doing the algorithm, first copy over the data from the
     * ArrayList to the backingArray (leaving index 0 of the backingArray
     * empty). The data in the backingArray should be in the same order as it
     * appears in the passed in ArrayList before you start the BuildHeap
     * algorithm.
     *
     * The backingArray should have capacity 2n + 1 where n is the
     * number of data in the passed in ArrayList (not INITIAL_CAPACITY).
     * Index 0 should remain empty, indices 1 to n should contain the data in
     * proper order, and the rest of the indices should be empty.
     *
     * Consider how to most efficiently determine if the list contains null data.
     * 
     * @param data a list of data to initialize the heap with
     * @throws java.lang.IllegalArgumentException if data or any element in data
     *                                            is null
     */
    public MaxHeap(ArrayList<T> data) {
        if (data == null)   {
            throw new java.lang.IllegalArgumentException("Cannot construct MaxHeap with null data");
        }

        this.backingArray = (T[]) new Object[data.size() * 2 + 1];
        this.size = data.size();

        for (int i = 0; i < data.size(); i++)  {
            if (data.get(i) == null)    {
                throw new java.lang.IllegalArgumentException("Cannot construct MaxHeap with null data");
            }
            this.backingArray[i + 1]  = data.get(i);
        }

        // data.size() / 2 contains "last" node without a child
        // thus traversing [data.size()/2, 1] is the reverse level-order traversal
        for (int i = data.size() / 2; i >= 1; i--)  {
            this.downHeap(i);
        }
    }

    /**
     * Recursive implementation of buildHeap, intended to be ran on array-based heap
     * @param curIndex Current index to "build" on
     */
    private void downHeap(int curIndex) {
        int leftChildIndex = curIndex * 2;
        int rightChildIndex = curIndex * 2 + 1;
        int largestIndex = curIndex;

        // essentially finds max(curItem, leftChild, rightChild)
        if (leftChildIndex <= this.size
                && this.backingArray[leftChildIndex].compareTo(this.backingArray[largestIndex]) > 0)    {
            largestIndex = leftChildIndex;
        }
        if (rightChildIndex <= this.size
                && this.backingArray[rightChildIndex].compareTo(this.backingArray[largestIndex]) > 0)   {
            largestIndex = rightChildIndex;
        }

        // check if swap necessary
        if (largestIndex != curIndex)   {
            T temp = this.backingArray[largestIndex];
            this.backingArray[largestIndex] = this.backingArray[curIndex];
            this.backingArray[curIndex] = temp;
            this.downHeap(largestIndex); // call downHeap again on swapped position
        }
    }

    /**
     * Adds the data to the heap.
     *
     * If sufficient space is not available in the backing array (the backing
     * array is full except for index 0), resize it to double the current
     * length. You can assume that no duplicate data will be passed in.
     *
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void add(T data) {
        if (data == null)   {
            throw new java.lang.IllegalArgumentException("Cannot add null data to MaxHeap");
        }

        // check if resize needed
        if (this.backingArray.length <= this.size + 1)   { // add 1 because of 1-indexing
            T[] newBackingArray = (T[]) new Object[this.backingArray.length * 2];
            for (int i = 1; i <= this.size; i++)  {
                newBackingArray[i] = this.backingArray[i];
            }
            this.backingArray = newBackingArray;
        }

        // add element in position such that it preserves completeness of the heap
        // also update size
        this.backingArray[++this.size] = data;
        // call upHeap at index where new element exists
        upHeap(this.size);
    }

    /**
     * Recursive implementation of upHeap
     * @param curIndex Current index to upHeap
     */
    private void upHeap(int curIndex)   {
        int parentIndex = curIndex / 2; // simple floor division will give parent, bc rounds down if right node
        if (parentIndex >= 1 && this.backingArray[curIndex].compareTo(this.backingArray[parentIndex]) > 0)   {
            T temp = this.backingArray[curIndex];
            this.backingArray[curIndex] = this.backingArray[parentIndex];
            this.backingArray[parentIndex] = temp;
            upHeap(parentIndex);
        }
    }

    /**
     * Removes and returns the root of the heap.
     *
     * Do not shrink the backing array.
     *
     * Replace any unused spots in the array with null.
     *
     * @return the data that was removed
     * @throws java.util.NoSuchElementException if the heap is empty
     */
    public T remove() {
        T removed = getMax();
        this.backingArray[1] = this.backingArray[this.size];
        this.backingArray[this.size--] = null;

        this.downHeap(1);
        return removed;
    }

    /**
     * Returns the maximum element in the heap.
     *
     * @return the maximum element
     * @throws java.util.NoSuchElementException if the heap is empty
     */
    public T getMax() {
        if (this.size == 0) {
            throw new java.util.NoSuchElementException("Heap is empty");
        }
        return this.backingArray[1];
    }

    /**
     * Returns whether or not the heap is empty.
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Clears the heap.
     *
     * Resets the backing array to a new array of the initial capacity and
     * resets the size.
     */
    public void clear() {
        this.backingArray = (T[]) new Object[INITIAL_CAPACITY];
        this.size = 0;
    }

    /**
     * Returns the backing array of the heap.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the backing array of the list
     */
    public T[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }

    /**
     * Returns the size of the heap.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the list
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}
