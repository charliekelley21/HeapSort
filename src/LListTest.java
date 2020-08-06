import java.util.NoSuchElementException;
import student.TestCase;

/**
 * Tests the LList class
 * @author Barak Finnegan (bjfinn98)
 * @version 2020.07.15
 *
 */
public class LListTest extends TestCase {
    // set up variables
    private LList test1;
    private Record[] rec;

    /**
     * The setUp for the LList testing
     */
    public void setUp() {
        rec = new Record[5];
        rec[3] = new Record((short) 9, (short) 9);
        test1 = new LList();
    }


    /**
     * Tests the insert method of LList
     */
    public void testInsert() {
        test1.insert(new Buffer(3, rec));
        test1.insert(new Buffer(3, rec));
        test1.insert(new Buffer(3, rec));
        test1.insert(new Buffer(3, rec));
        test1.insert(new Buffer(3, rec));
        test1.insert(new Buffer(3, rec));
        assertEquals(6, test1.length());
    }


    /**
     * Tests the remove method of LList
     */
    public void testRemove() {
        try {
            test1.remove();
        }
        catch (Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }
        for (int i = 0; i < 10; i++) {
            test1.insert(new Buffer(i, rec));
        }
        Buffer test = test1.remove();
        assertEquals(9, test.getRecord(3).getKey());
        for (int i = 0; i < 9; i++) {
            test1.remove();
        }
        assertTrue(test1.isEmpty());
    }


    /**
     * Tests the append method of LList
     */
    public void testAppend() {
        test1.append(new Buffer(3, rec));
        test1.append(new Buffer(3, rec));
        test1.append(new Buffer(3, rec));
        test1.append(new Buffer(3, rec));
        test1.append(new Buffer(3, rec));
        test1.append(new Buffer(3, rec));
        assertEquals(6, test1.length());
    }


    /**
     * Tests the moveToStart method of LList
     */
    public void testMoveToStart() {
        for (int i = 0; i < 10; i++) {
            test1.append(new Buffer(i, rec));
        }
        test1.moveToStart();
        assertTrue(test1.getValue().inRange(0));
    }


    /**
     * Tests the moveToEnd method of LList
     */
    public void testMoveToEnd() {
        for (int i = 0; i < 10; i++) {
            test1.append(new Buffer(i, rec));
        }
        test1.moveToEnd();
        test1.prev();
        assertTrue(test1.getValue().inRange(9));
    }


    /**
     * Tests the next method of LList
     */
    public void testNext() {
        for (int i = 0; i < 10; i++) {
            test1.append(new Buffer(i, rec));
        }
        assertTrue(test1.getValue().inRange(0));
        test1.next();
        assertTrue(test1.getValue().inRange(1));
        test1.next();
        assertTrue(test1.getValue().inRange(2));
        for (int i = 0; i < 10; i++) {
            test1.next();
        }
        assertEquals(10, test1.currPos());
    }


    /**
     * Tests the length method of LList
     */
    public void testLength() {
        test1.insert(new Buffer(3, rec));
        test1.insert(new Buffer(3, rec));
        test1.insert(new Buffer(3, rec));
        test1.insert(new Buffer(3, rec));
        test1.insert(new Buffer(3, rec));
        test1.insert(new Buffer(3, rec));
        assertEquals(6, test1.length());
    }


    /**
     * Tests the prev method of LList
     */
    public void testPrev() {
        test1.prev();
        for (int i = 0; i < 10; i++) {
            test1.append(new Buffer(i, rec));
        }
        test1.prev();
        test1.moveToPos(8);
        assertTrue(test1.getValue().inRange(8));
    }


    /**
     * Tests the curPos method of LList
     */
    public void testCurrPos() {
        for (int i = 0; i < 10; i++) {
            test1.append(new Buffer(i, rec));
        }
        for (int i = 0; i < 10; i++) {
            test1.next();
        }
        assertEquals(10, test1.currPos());
    }


    /**
     * Tests the movToPos method of LList
     */
    public void testMovToPos() {
        for (int i = 0; i < 10; i++) {
            test1.append(new Buffer(i, rec));
        }
        assertTrue(test1.moveToPos(5));
        assertTrue(test1.getValue().inRange(5));
        assertFalse(test1.moveToPos(-5));
        assertFalse(test1.getValue().inRange(-5));
        assertFalse(test1.moveToPos(50));
        assertFalse(test1.getValue().inRange(50));
    }


    /**
     * Tests the isAtEnd method of LList
     */
    public void testIsAtEnd() {
        assertTrue(test1.isAtEnd());
        for (int i = 0; i < 10; i++) {
            test1.append(new Buffer(i, rec));
        }
        assertFalse(test1.isAtEnd());
        for (int i = 0; i < 10; i++) {
            test1.next();
        }
        assertTrue(test1.isAtEnd());
    }


    /**
     * Tests the isEmpty method of LList
     */
    public void testIsEmpty() {
        assertTrue(test1.isEmpty());
        test1.append(new Buffer(3, rec));
        assertFalse(test1.isEmpty());
    }


    /**
     * Tests the getValue() method of LList
     */
    public void testGetValue() {
        for (int i = 0; i < 10; i++) {
            test1.append(new Buffer(i, rec));
        }
        test1.prev();
        test1.moveToPos(8);
        assertTrue(test1.getValue().inRange(8));
        for (int i = 0; i < 10; i++) {
            test1.next();
        }
        try {
            test1.getValue();
        }
        catch (Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }
    }
}
