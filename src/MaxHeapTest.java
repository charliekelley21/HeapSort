import java.io.FileNotFoundException;
import student.TestCase;

/**
 * Testing class for the MinHeap
 * 
 * @author Charlie Kelley (charlk21)
 * @version 2020.08.04
 */
public class MaxHeapTest extends TestCase {

    // Setting up vars
    private MaxHeap test;

    /**
     * This is the setUp method for the testing suite.
     */
    public void setUp() {
        test = new MaxHeap();
    }


    /**
     * This will test the constructors for MinHeap
     */
    public void testConstructors() {
        assertNull(test.getPool());
        assertEquals(test.heapSize(), 0);
    }

}
