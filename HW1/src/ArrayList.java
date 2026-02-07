import java.util.NoSuchElementException;

/**
 * Your implementation of an ArrayList.
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
public class ArrayList<T> {

    /**
     * The initial capacity of the ArrayList.
     *
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final int INITIAL_CAPACITY = 9;

    // Do not add new instance variables or modify existing ones.
    private T[] backingArray;
    private int size;

    /**
     * Constructs a new ArrayList.
     *
     * Java does not allow for regular generic array creation, so you will have
     * to cast an Object[] to a T[] to get the generic typing.
     */
    public ArrayList() {
        this.backingArray = (T[]) new Object[INITIAL_CAPACITY];
        this.size = 0;
    }

    /**
     * Adds the element to the specified index.
     *
     * Remember that this add may require elements to be shifted.
     *
     * Must be amortized O(1) for index size and O(n) for all other cases.
     *
     * @param index the index at which to add the new element
     * @param data  the data to add at the specified index
     * @throws IndexOutOfBoundsException if index < 0 or index > size
     * @throws IllegalArgumentException  if data is null
     */
    public void addAtIndex(int index, T data) {
        // null checks
        if (data == null)   {
            throw new IllegalArgumentException("Data is null");
        }   else if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException("Invalid index given to addAtIndex");
        }

        // resize check
        if (this.size == this.backingArray.length)    {
            T[] newBackingArray = (T[]) new Object[this.backingArray.length * 2];
            for (int i = 0; i < index; i++) {
                newBackingArray[i] = this.backingArray[i];
            }

            newBackingArray[index] = data;

            for (int i = index; i < this.size; i++) {
                newBackingArray[i + 1] = this.backingArray[i];
            }

            this.backingArray = newBackingArray;

        } else {
            for (int i = size; i > index; i--) {
                this.backingArray[i] = this.backingArray[i - 1];
            }
            this.backingArray[index] = data;
        }
        this.size++;
    }

    /**
     * Removes and returns the element at the specified index.
     * Remember that this remove may require elements to be shifted.
     * Must be O(1) for index size - 1 and O(n) for all other cases.
     *
     * @param index the index of the element to remove
     * @return the data formerly located at the specified index
     * @throws IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T removeAtIndex(int index) {
        if (index < 0 || index >= size)  {
            throw new IndexOutOfBoundsException("Invalid index given to removeAtIndex");
        }

        T removedData = this.backingArray[index];

        for (int i = index; i < size - 1; i++)  {
            this.backingArray[i] = this.backingArray[i + 1];
        }

        this.backingArray[size - 1] = null;
        this.size--;
        return removedData;
    }

    /**
     * Adds the element to the front of the list.
     * We provide this method to you.
     *
     * @param data the data to add to the front of the list
     * @throws IllegalArgumentException if data is null
     */
    public void addToFront(T data) {
        // DO NOT MODIFY THIS METHOD!
        addAtIndex(0, data);
    }

    /**
     * Adds the element to the back of the list.
     * We provide this method to you.
     *
     * @param data the data to add to the back of the list
     * @throws IllegalArgumentException if data is null
     */
    public void addToBack(T data) {
        // DO NOT MODIFY THIS METHOD!
        addAtIndex(size, data);
    }

    /**
     * Removes and returns the first element of the list.
     * We provide this method to you.
     *
     * Must be O(n).
     *
     * @return the data formerly located at the front of the list
     * @throws NoSuchElementException if the list is empty
     */
    public T removeFromFront() {
        // DO NOT MODIFY THIS METHOD!
        return removeAtIndex(0);
    }

    /**
     * Removes and returns the last element of the list.
     * We provide this method to you.
     *
     * @return the data formerly located at the back of the list
     * @throws NoSuchElementException if the list is empty
     */
    public T removeFromBack() {
        // DO NOT MODIFY THIS METHOD!
        return removeAtIndex(size - 1);
    }

    /**
     * Returns the element at the specified index.
     * We provide this method to you.
     *
     * @param index the index of the element to get
     * @return the data stored at the index in the list
     * @throws IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T get(int index) {
        // DO NOT MODIFY THIS METHOD!
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("The index is out of bounds");
        }
        return backingArray[index];
    }

    /**
     * Returns the backing array of the list.
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
     * Returns the size of the list.
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
