import student.TestCase;

/**
 * This class tests the Link class
 * 
 * @author Barak Finnegan (bjfinn98)
 * @version 2020.07.11
 */

public class LinkTest extends TestCase {

    // set up variables
    private Link test1;
    private Link test2;
    private Link test3;

    /**
     * The setUp for the LList testing
     */
    public void setUp() {
        Record[] lib = new Record[1024];
        lib[100] = new Record((short)6, (short)7);
        test1 = new Link(new Buffer(1, lib), null);
        test2 = new Link(test1);
        test3 = new Link(new Buffer(2, lib), test2);
    }


    /**
     * This tests the element function in Link
     */
    public void testElement() {
        assertTrue(test3.element().inRange(2));
        assertNull(test2.element());
    }


    /**
     * This tests the setElement function in Link
     */
    public void testSetElement() {
        Record[] lib = new Record[1024];
        lib[100] = new Record((short)7, (short)7);
        assertTrue(test3.setElement(new Buffer(2, lib)).inRange(2));
        assertTrue(test3.element().inRange(2));
    }


    /**
     * This tests the next function in Link
     */
    public void testNext() {
        assertEquals(test2, test3.next());
        assertEquals(test1, test2.next());
        assertNull(test1.next());
    }


    /**
     * This tests the setNext function in Link
     */
    public void testSetNext() {
        assertEquals(test2, test3.next());
        assertEquals(test1, test3.setNext(test1));
        assertEquals(test1, test3.next());
    }
}
