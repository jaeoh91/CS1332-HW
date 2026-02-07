import java.util.NoSuchElementException;

/**
 * Your implementation of a SinglyLinkedList without a tail pointer.
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
public class SinglyLinkedList<T> {

    // Do not add new instance variables or modify existing ones.
    private SinglyLinkedListNode<T> head;
    private int size;

    // Do not add a constructor. There is no backing structure to instantiate.

    /**
     * Adds the element to the specified index.
     * Must be O(1) for index 0 and O(n) for all other cases.
     *
     * @param index the index to add the new element
     * @param data  the data to add
     * @throws IndexOutOfBoundsException if index < 0 or index > size
     * @throws IllegalArgumentException  if data is null
     */
    public void addAtIndex(int index, T data) {
        // null checks
        if (data == null)   {
            throw new IllegalArgumentException("Data to addAtIndex cannot be null");
        }
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException("Invalid index to addAtIndex");
        }

        SinglyLinkedListNode<T> curNode = this.head;
        for (int i = 0; i < index - 1; i++) {
            curNode = curNode.getNext();
        }

        // edge case handling
        if (index == 0)  {
            this.head = new SinglyLinkedListNode<T>(data, this.head);
        } else {
            SinglyLinkedListNode<T> newNode = new SinglyLinkedListNode<T>(data, curNode.getNext());
            curNode.setNext(newNode);
        }
        this.size++;
    }

    /**
     * Adds the element to the front of the list.
     * Must be O(1).
     *
     * @param data the data to add to the front of the list
     * @throws IllegalArgumentException if data is null
     */
    public void addToFront(T data) {
        this.addAtIndex(0, data);
    }

    /**
     * Adds the element to the back of the list.
     * Must be O(n).
     *
     * @param data the data to add to the back of the list
     * @throws IllegalArgumentException if data is null
     */
    public void addToBack(T data) {
        this.addAtIndex(this.size, data);
    }

    /**
     * Removes and returns the element at the specified index.
     * Must be O(1) for index 0 and O(n) for all other cases.
     *
     * @param index the index of the element to remove
     * @return the data that was removed
     * @throws IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T removeAtIndex(int index) {
        // error checking
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index given to removeAtIndex");
        }

        SinglyLinkedListNode<T> curNode = this.head;
        T data;
        if (index == 0) {
            data = curNode.getData();
            this.head = curNode.getNext();
        } else {
            for (int i = 0; i < index - 1; i++) { // iterate to node before node to remove
                curNode = curNode.getNext();
            }

            data = curNode.getNext().getData();
            curNode.setNext(curNode.getNext().getNext());
        }

        this.size--;
        return data;
    }

    /**
     * Removes and returns the first data of the list.
     * Must be O(1).
     *
     * @return the data formerly located at the front of the list
     * @throws NoSuchElementException if the list is empty
     */
    public T removeFromFront() {
        if (this.size == 0) {
            throw new NoSuchElementException("SLL is empty");
        }
        return this.removeAtIndex(0);
    }

    /**
     * Removes and returns the last data of the list.
     * Must be O(n).
     *
     * @return the data formerly located at the back of the list
     * @throws NoSuchElementException if the list is empty
     */
    public T removeFromBack() {
        if (this.size == 0) {
            throw new NoSuchElementException("SLL is empty");
        }
        return this.removeAtIndex(this.size - 1);
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
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        SinglyLinkedListNode<T> curr = head;
        for (int i = 0; i < index; i++) {
            curr = curr.getNext();
        }
        return curr.getData();
    }

    /**
     * Returns an array representation of the linked list.
     * We provide this method to you.
     *
     * @return the array of length size holding all the data
     * (not the nodes) in the list in the same order
     */
    public T[] toArray() {
        // DO NOT MODIFY THIS METHOD!
        T[] array = (T[]) new Object[size];
        SinglyLinkedListNode<T> curr = head;
        for (int i = 0; i < size; i++) {
            array[i] = curr.getData();
            curr = curr.getNext();
        }
        return array;
    }

    /**
     * Returns the head node of the list.
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the node at the head of the list
     */
    public SinglyLinkedListNode<T> getHead() {
        // DO NOT MODIFY THIS METHOD!
        return head;
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
