import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * This is a basic set of unit tests for ArrayList and SinglyLinkedList.
 *
 * Passing these tests doesn't guarantee any grade on these assignments. These
 * student JUnits that we provide should be thought of as a sanity check to
 * help you get started on the homework and writing JUnits in general.
 *
 * We highly encourage you to write your own set of JUnits for each homework
 * to cover edge cases you can think of for each data structure. Your code must
 * work correctly and efficiently in all cases, which is why it's important
 * to write comprehensive tests to cover as many cases as possible.
 *
 * @author CS 1332 TAs
 * @version 2.0
 */
public class StudentTest {

    private static final int TIMEOUT = 200;
    private ArrayList<String> arrayList;
    private SinglyLinkedList<String> linkedList;

    @Before
    public void setUp() {
        arrayList = new ArrayList<>();
        linkedList = new SinglyLinkedList<>();
    }

    // ==================== ArrayList Tests ====================

    @Test(timeout = TIMEOUT)
    public void testArrayListInitialization() {
        assertEquals(0, arrayList.size());
        assertArrayEquals(new Object[ArrayList.INITIAL_CAPACITY],
                arrayList.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testArrayListAddAtIndex() {
        arrayList.addAtIndex(0, "2a");   // 2a
        arrayList.addAtIndex(0, "1a");   // 1a, 2a
        arrayList.addAtIndex(2, "4a");   // 1a, 2a, 4a
        arrayList.addAtIndex(2, "3a");   // 1a, 2a, 3a, 4a
        arrayList.addAtIndex(0, "0a");   // 0a, 1a, 2a, 3a, 4a

        assertEquals(5, arrayList.size());

        Object[] expected = new Object[ArrayList.INITIAL_CAPACITY];
        expected[0] = "0a";
        expected[1] = "1a";
        expected[2] = "2a";
        expected[3] = "3a";
        expected[4] = "4a";
        assertArrayEquals(expected, arrayList.getBackingArray());

        // check add to end of ArrayList
        arrayList.addAtIndex(arrayList.size(), "hi");
        assertEquals(6, arrayList.size());
        expected[5] = "hi";
        assertArrayEquals(expected, arrayList.getBackingArray());

        //check resize
        arrayList.addAtIndex(0, "2a");   // 2a
        arrayList.addAtIndex(0, "1a");   // 1a, 2a
        arrayList.addAtIndex(0, "0a");   // 0a, 1a, 2a, 3a, 4a
    }

    @Test(timeout = TIMEOUT)
    public void testArrayListRemoveAtIndex() {
        String temp = "2a"; // For equality checking.

        arrayList.addAtIndex(0, "0a");   // 0a
        arrayList.addAtIndex(1, "1a");   // 0a, 1a
        arrayList.addAtIndex(2, temp);   // 0a, 1a, 2a
        arrayList.addAtIndex(3, "3a");   // 0a, 1a, 2a, 3a
        arrayList.addAtIndex(4, "4a");   // 0a, 1a, 2a, 3a, 4a
        arrayList.addAtIndex(5, "5a");   // 0a, 1a, 2a, 3a, 4a, 5a
        assertEquals(6, arrayList.size());

        // assertSame checks for reference equality whereas assertEquals checks
        // value equality.
        assertSame(temp, arrayList.removeAtIndex(2));    // 0a, 1a, 3a, 4a, 5a

        assertEquals(5, arrayList.size());

        Object[] expected = new Object[ArrayList.INITIAL_CAPACITY];
        expected[0] = "0a";
        expected[1] = "1a";
        expected[2] = "3a";
        expected[3] = "4a";
        expected[4] = "5a";
        assertArrayEquals(expected, arrayList.getBackingArray());
    }

    // ==================== SinglyLinkedList Tests ====================

    @Test(timeout = TIMEOUT)
    public void testLinkedListInitialization() {
        assertEquals(0, linkedList.size());
        assertNull(linkedList.getHead());
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedListAddAtIndex() {
        linkedList.addAtIndex(0, "2a");   // 2a
        linkedList.addAtIndex(0, "1a");   // 1a, 2a
        linkedList.addAtIndex(2, "4a");   // 1a, 2a, 4a
        linkedList.addAtIndex(2, "3a");   // 1a, 2a, 3a, 4a
        linkedList.addAtIndex(0, "0a");   // 0a, 1a, 2a, 3a, 4a

        assertEquals(5, linkedList.size());

        SinglyLinkedListNode<String> cur = linkedList.getHead();
        assertNotNull(cur);
        assertEquals("0a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("1a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("2a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("3a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("4a", cur.getData());

        cur = cur.getNext();
        assertNull(cur);
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedListAddToFront() {
        linkedList.addToFront("4a");  // 4a
        linkedList.addToFront("3a");  // 3a, 4a
        linkedList.addToFront("2a");  // 2a, 3a, 4a
        linkedList.addToFront("1a");  // 1a, 2a, 3a, 4a
        linkedList.addToFront("0a");  // 0a, 1a, 2a, 3a, 4a

        assertEquals(5, linkedList.size());

        SinglyLinkedListNode<String> cur = linkedList.getHead();
        assertNotNull(cur);
        assertEquals("0a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("1a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("2a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("3a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("4a", cur.getData());

        cur = cur.getNext();
        assertNull(cur);
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedListAddToBack() {
        linkedList.addToBack("0a");   // 0a
        linkedList.addToBack("1a");   // 0a, 1a
        linkedList.addToBack("2a");   // 0a, 1a, 2a
        linkedList.addToBack("3a");   // 0a, 1a, 2a, 3a
        linkedList.addToBack("4a");   // 0a, 1a, 2a, 3a, 4a

        assertEquals(5, linkedList.size());

        SinglyLinkedListNode<String> cur = linkedList.getHead();
        assertNotNull(cur);
        assertEquals("0a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("1a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("2a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("3a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("4a", cur.getData());

        cur = cur.getNext();
        assertNull(cur);
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedListRemoveAtIndex() {
        String temp = "2a";

        linkedList.addAtIndex(0, "0a");   // 0a
        linkedList.addAtIndex(1, "1a");   // 0a, 1a
        linkedList.addAtIndex(2, temp);   // 0a, 1a, 2a
        linkedList.addAtIndex(3, "3a");   // 0a, 1a, 2a, 3a
        linkedList.addAtIndex(4, "4a");   // 0a, 1a, 2a, 3a, 4a
        linkedList.addAtIndex(5, "5a");   // 0a, 1a, 2a, 3a, 4a, 5a
        assertEquals(6, linkedList.size());

        assertSame(temp, linkedList.removeAtIndex(2));    // 0a, 1a, 3a, 4a, 5a

        assertEquals(5, linkedList.size());

        SinglyLinkedListNode<String> cur = linkedList.getHead();
        assertNotNull(cur);
        assertEquals("0a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("1a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("3a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("4a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("5a", cur.getData());

        cur = cur.getNext();
        assertNull(cur);
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedListRemoveFromFront() {
        String temp = "0a";

        linkedList.addAtIndex(0, temp);   // 0a
        linkedList.addAtIndex(1, "1a");   // 0a, 1a
        linkedList.addAtIndex(2, "2a");   // 0a, 1a, 2a
        linkedList.addAtIndex(3, "3a");   // 0a, 1a, 2a, 3a
        linkedList.addAtIndex(4, "4a");   // 0a, 1a, 2a, 3a, 4a
        linkedList.addAtIndex(5, "5a");   // 0a, 1a, 2a, 3a, 4a, 5a
        assertEquals(6, linkedList.size());

        assertSame(temp, linkedList.removeFromFront());   // 1a, 2a, 3a, 4a, 5a

        assertEquals(5, linkedList.size());

        SinglyLinkedListNode<String> cur = linkedList.getHead();
        assertNotNull(cur);
        assertEquals("1a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("2a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("3a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("4a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("5a", cur.getData());

        cur = cur.getNext();
        assertNull(cur);
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedListRemoveFromBack() {
        String temp = "5a";

        linkedList.addAtIndex(0, "0a");   // 0a
        linkedList.addAtIndex(1, "1a");   // 0a, 1a
        linkedList.addAtIndex(2, "2a");   // 0a, 1a, 2a
        linkedList.addAtIndex(3, "3a");   // 0a, 1a, 2a, 3a
        linkedList.addAtIndex(4, "4a");   // 0a, 1a, 2a, 3a, 4a
        linkedList.addAtIndex(5, temp);   // 0a, 1a, 2a, 3a, 4a, 5a
        assertEquals(6, linkedList.size());

        assertSame(temp, linkedList.removeFromBack());    // 0a, 1a, 2a, 3a, 4a

        assertEquals(5, linkedList.size());

        SinglyLinkedListNode<String> cur = linkedList.getHead();
        assertNotNull(cur);
        assertEquals("0a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("1a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("2a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("3a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("4a", cur.getData());

        cur = cur.getNext();
        assertNull(cur);
    }
}
