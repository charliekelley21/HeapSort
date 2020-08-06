import student.TestCase;

/**
 * Testing class for the BufferStatistics class
 * 
 * @author Charlie Kelley (charlk21)
 * @version 2020.08.06
 *
 */
public class BufferStatisticsTest extends TestCase {

 // Setting up vars
    private BufferStatistics test;

    /**
     * This is the setUp method for the testing suite.
     */
    public void setUp() {
        test = new BufferStatistics();
    }
    
    /**
     * This will test the constructor for BufferStatistics
     */
    public void testConstructor() {
        assertEquals(test.getHits(), 0);
        assertEquals(test.getMisses(), 0);
        assertEquals(test.getDiskReads(), 0);
    }
    
    /**
     * Test the Hits getter and setter
     */
    public void testHits() {
        for (int i = 1; i <= 10; i++) {
            test.hit();
            assertEquals(test.getHits(), i);
        }
    }
    
    /**
     * Test the Misses getter and setter
     */
    public void testMisses() {
        for (int i = 1; i <= 10; i++) {
            test.miss();
            assertEquals(test.getMisses(), i);
        }
    }
    
    /**
     * Test the Misses getter and setter
     */
    public void testDiskReads() {
        for (int i = 1; i <= 10; i++) {
            test.diskRead();
            assertEquals(test.getDiskReads(), i);
        }
    }
}
