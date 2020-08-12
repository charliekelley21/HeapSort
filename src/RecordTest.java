

import student.TestCase;

/**
 * Test class for the Record Data object
 * 
 * @author Barak Finnegan (bjfinn98)
 * @version 2020.08.05
 */
public class RecordTest extends TestCase {

    private Record test;
    private Record test2;

    /**
     * The set up for the test suite
     */
    public void setUp() {
        short k = 15;
        short v = 60;
        test = new Record(k, v);
        k = 45;
        v = 45;
        test2 = new Record(k, v);
    }


    /**
     * Test the compareTo method
     */
    public void testCompareTo() {
        // test should be to the left of test2
        int testRes = test.compareTo(test2);
        assertTrue(testRes < 0);
    }


    /**
     * Tests the getKey method
     */
    public void testGetKey() {
        assertEquals(45, test2.getKey());
    }


    /**
     * Tests the getValue method
     */
    public void testGetValue() {
        assertEquals(60, test.getValue());
    }
}
