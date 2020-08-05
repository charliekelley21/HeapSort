import student.TestCase;

/**
 * Testing class for the MinHeap
 * 
 * @author Charlie Kelley (charlk21)
 * @author Barak Finnegan (bjfinn98)
 * @version 2020.08.04
 */
public class MinHeapTest extends TestCase {

    // Setting up vars
    private MinHeap test;

    /**
     * This is the setUp method for the testing suite.
     */
    public void setUp() {
        test = new MinHeap();
    }


    /**
     * This will test the constructors for MinHeap
     */
    public void testConstructors() {
        assertEquals(100, test.getArray().length);
        test = new MinHeap(5);
        assertEquals(5, test.getArray().length);
        test = new MinHeap(new int[] { 5, 4, 3, 2, 1 });
        int[] testing = test.getArray();
        assertEquals(5, testing.length);
        int[] ans = new int[] {1, 2, 3, 5, 4};
        for(int i = 0; i < ans.length; i++) {
            assertEquals(ans[i], testing[i]);
        }
    }

}
