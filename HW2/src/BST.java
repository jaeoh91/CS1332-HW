import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Queue;

/**
 * Your implementation of a BST.
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
public class BST<T extends Comparable<? super T>> {

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private BSTNode<T> root;
    private int size;

    /**
     * Constructs a new BST.
     *
     * This constructor should initialize an empty BST.
     *
     * Since instance variables are initialized to their default values, there
     * is no need to do anything for this constructor.
     */
    public BST() {
        // DO NOT IMPLEMENT THIS CONSTRUCTOR!
    }

    /**
     * Constructs a new BST.
     *
     * This constructor should initialize the BST with the data in the
     * Collection. The data should be added in the same order it is in the
     * Collection.
     *
     * Hint: Not all Collections are indexable like Lists, so a regular for loop
     * will not work here. However, all Collections are Iterable, so what type
     * of loop would work?
     *
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data or any element in data
     *                                            is null
     */
    public BST(Collection<T> data) {
        if (data == null)   {
            throw new IllegalArgumentException("Cannot initialize BST with null data");
        }

        for (T item : data) {
            if (item == null)   {
                throw new IllegalArgumentException("Cannot initialize BST with null data");
            }
            this.add(item);
        }
    }

    /**
     * Adds the data to the tree.
     *
     * This must be done recursively.
     *
     * The data becomes a leaf in the tree.
     *
     * Traverse the tree to find the appropriate location. If the data is
     * already in the tree, then nothing should be done (the duplicate
     * shouldn't get added, and size should not be incremented).
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void add(T data) {
        if (data == null)   {
            throw new IllegalArgumentException("Cannot add null data to a BST");
        }

        this.root = addH(this.root, data);
    }

    /**
     * Helper method for pointer reinforcement based implementation of add()
     * @param cur Node currently being reinforced
     * @param data Data to add
     * @return reinforced Node
     */
    private BSTNode<T> addH(BSTNode<T> cur, T data)   {
        if (cur == null)    {
            this.size++;
            return new BSTNode<T>(data);
        }
        int compareResult = data.compareTo(cur.getData());

        if (compareResult > 0)  {
            cur.setRight(addH(cur.getRight(), data));
        } else if (compareResult < 0)  {
            cur.setLeft(addH(cur.getLeft(), data));
        }
        // case compareResult == 0 omitted because we want to ignore duplicates
        return cur;
    }

    /**
     * Removes and returns the data from the tree matching the given parameter.
     *
     * This must be done recursively.
     *
     * There are 3 cases to consider:
     * 1: The node containing the data is a leaf (no children). In this case,
     * simply remove it.
     * 2: The node containing the data has one child. In this case, simply
     * replace it with its child.
     * 3: The node containing the data has 2 children. Use the predecessor to
     * replace the data. You MUST use recursion to find and remove the
     * predecessor (you will likely need an additional helper method to
     * handle this case efficiently).
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to remove
     * @return the data that was removed
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    public T remove(T data) {
        if (data == null)   {
            throw new java.lang.IllegalArgumentException("Cannot remove null data from BST");
        }
        BSTNode<T> dummy = new BSTNode<T>(null);

        this.root = removeH(this.root, data, dummy);

        this.size--;
        return dummy.getData();
    }


    /**
     * Helper method for recursive implementation of remove()
     * @param curNode Current Node being pointer reinforced
     * @param data Data to check for
     * @param dummy Reference to dummy variable eventually storing removed data
     * @return reference to curNode
     */
    private BSTNode<T> removeH(BSTNode<T> curNode, T data, BSTNode<T> dummy)   {
        if (curNode == null)    {
            throw new java.util.NoSuchElementException("Data to remove does not exist");
        }

        int compareResult = data.compareTo(curNode.getData());
        if (compareResult > 0)  {
            curNode.setRight(removeH(curNode.getRight(), data, dummy));
        } else if (compareResult < 0) {
            curNode.setLeft(removeH(curNode.getLeft(), data, dummy));
        } else {
            dummy.setData(curNode.getData());

            int children = countChildren(curNode);
            if (children == 0)  {
                return null;
            } else if (children == 1)   {
                return (curNode.getLeft() == null) ? curNode.getRight() : curNode.getLeft();
            } else {
                BSTNode<T> predecessorDummy = new BSTNode<T>(null);
                curNode.setLeft(removePredecessor(curNode.getLeft(), predecessorDummy));

                curNode.setData(predecessorDummy.getData());
                return curNode;
            }
        }
        return curNode;
    }

    /**
     * Helper method for pointer-reinforcement based implementation of remove()
     * @param curNode Node currently being recursed on
     * @param dummy Dummy node to store value of predecessor
     * @return Reinforced Node
     */
    private BSTNode<T> removePredecessor(BSTNode<T> curNode, BSTNode<T> dummy)  {
        if (curNode.getRight() == null)  {
            dummy.setData(curNode.getData());
            return curNode.getLeft();
        } else {
            curNode.setRight(removePredecessor(curNode.getRight(), dummy));
        }
        return curNode;
    }

    /**
     * Returns the number of children a Node has
     * @param node Node to check
     * @return Number of children
     */
    private int countChildren(BSTNode<T> node)  {
        return (node.getLeft() == null ? 0 : 1) + (node.getRight() == null ? 0 : 1);
    }

    /**
     * Returns the data from the tree matching the given parameter.
     *
     * This must be done recursively.
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to search for
     * @return the data in the tree equal to the parameter
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    public T get(T data) {
        if (data == null)   {
            throw new java.lang.IllegalArgumentException("Cannot call get() with null data");
        }
        return getH(this.root, data);
    }

    /**
     * Helper method for recursive implementation of get()
     * @param curNode node currently recursing on
     * @param data the data to search for
     * @return Data found in the matching node
     */
    private T getH(BSTNode<T> curNode, T data)  {
        if (curNode == null)    {
            throw new java.util.NoSuchElementException("Data could not be found");
        }

        int compareResult = data.compareTo(curNode.getData());

        if (compareResult > 0) {
            return getH(curNode.getRight(), data);
        } else if (compareResult < 0)   {
            return getH(curNode.getLeft(), data);
        } else {
            return curNode.getData();
        }
    }

    /**
     * Returns whether or not data matching the given parameter is contained
     * within the tree.
     *
     * This must be done recursively.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to search for
     * @return true if the parameter is contained within the tree, false
     * otherwise
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public boolean contains(T data) {
        if (data == null)   {
            throw new java.lang.IllegalArgumentException("Cannot call contains() onn null data");
        }
        return containsH(this.root, data);
    }

    /**
     * Helper method for recursive implementation of contains()
     * @param curNode The node currently being recursed on
     * @param data The data to search for
     * @return True if data found, False if not
     */
    private boolean containsH(BSTNode<T> curNode, T data)  {
        if (curNode == null)    {
            return false;
        }

        int compareResult = data.compareTo(curNode.getData());
        if (compareResult > 0)  {
            return containsH(curNode.getRight(), data);
        } else if (compareResult < 0)   {
            return containsH(curNode.getLeft(), data);
        } else {
            return true;
        }
    }

    /**
     * Generate a pre-order traversal of the tree.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @return the preorder traversal of the tree
     */
    public List<T> preorder() {
        ArrayList<T> traversalList = new ArrayList<T>(this.size);
        preorderH(this.root, traversalList);
        return traversalList;
    }

    /**
     * Helper method for recursive implementation of preorder()
     * @param curNode Node currently recursing on
     * @param traversalList List to add data to
     */
    private void preorderH(BSTNode<T> curNode, ArrayList<T> traversalList) {
        if (curNode != null)    {
            traversalList.add(curNode.getData());
            preorderH(curNode.getLeft(), traversalList);
            preorderH(curNode.getRight(), traversalList);
        }
    }

    /**
     * Generate an in-order traversal of the tree.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @return the inorder traversal of the tree
     */
    public List<T> inorder() {
        ArrayList<T> traversal = new ArrayList<T>(this.size);
        inorderH(this.root, traversal);
        return traversal;
    }

    /**
     * Helper method for recursive implementation of inorder()
     * @param curNode Node currently recursing on
     * @param traversal List to add data to
     */
    private void inorderH(BSTNode<T> curNode, ArrayList<T> traversal) {
        if (curNode != null)    {
            inorderH(curNode.getLeft(), traversal);
            traversal.add(curNode.getData());
            inorderH(curNode.getRight(), traversal);
        }
    }

    /**
     * Generate a post-order traversal of the tree.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @return the postorder traversal of the tree
     */
    public List<T> postorder() {
        ArrayList<T> traversal = new ArrayList<T>(this.size);
        postorderH(this.root, traversal);
        return traversal;
    }

    /**
     * Helper method for recursive implementation of postorder()
     * @param curNode Node currently being recursed on
     * @param traversal List storing order of traversal
     */
    private void postorderH(BSTNode<T> curNode, ArrayList<T> traversal) {
        if (curNode != null)    {
            postorderH(curNode.getLeft(), traversal);
            postorderH(curNode.getRight(), traversal);
            traversal.add(curNode.getData());
        }
    }

    /**
     * Generate a level-order traversal of the tree.
     *
     * This does not need to be done recursively.
     *
     * Hint: You will need to use a queue of nodes. Think about what initial
     * node you should add to the queue and what loop / loop conditions you
     * should use.
     *
     * Must be O(n).
     *
     * @return the level order traversal of the tree
     */
    public List<T> levelorder() {
        ArrayList<T> traversal = new ArrayList<T>(this.size);
        Queue<BSTNode<T>> levelQueue = new LinkedList<BSTNode<T>>();
        if (this.root != null)  {
            levelQueue.add(this.root);
        }

        while (levelQueue.size() > 0)   {
            BSTNode<T> cur = levelQueue.remove();
            traversal.add(cur.getData());

            if (cur.getLeft() != null)   {
                levelQueue.add(cur.getLeft());
            }
            if (cur.getRight() != null) {
                levelQueue.add(cur.getRight());
            }
        }

        return traversal;
    }

    /**
     * Returns the height of the root of the tree.
     *
     * This must be done recursively.
     *
     * A node's height is defined as max(left.height, right.height) + 1. A
     * leaf node has a height of 0 and a null child has a height of -1.
     *
     * Must be O(n).
     *
     * @return the height of the root of the tree, -1 if the tree is empty
     */
    public int height() {
        return heightH(this.root);
    }

    /**
     * Helper method for recursive implementation of height()
     * @param curNode Node currently being recursed on
     * @return Current height - 1
     */
    private int heightH(BSTNode<T> curNode)   {
        if (curNode == null)    {
            return -1;
        } else {
            return Math.max(heightH(curNode.getLeft()), heightH(curNode.getRight())) + 1;
        }
    }

    /**
     * Clears the tree.
     *
     * Clears all data and resets the size.
     *
     * Must be O(1).
     */
    public void clear() {
        this.root = null;
        this.size = 0;
    }

    /**
     * Returns the root of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the root of the tree
     */
    public BSTNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }

    /**
     * Returns the size of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the tree
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}
