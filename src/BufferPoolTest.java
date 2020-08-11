import java.io.FileNotFoundException;
import student.TestCase;

/**
 * The testing class for the BufferPool
 * 
 * @author Charlie Kelley (charlk21)
 * @author Barak Finnegan (bjfinn98)
 * @version 2020.08.05
 */
public class BufferPoolTest extends TestCase {

    // Setting up vars
    private BufferPool test;

    /**
     * This is the setUp method for the testing suite.
     * 
     * @throws FileNotFoundException
     *             Will never be thrown
     */
    public void setUp() throws FileNotFoundException {
        RAFile file = new RAFile("src/test/p3_input_sample.txt");
        test = new BufferPool(file, 2, file.recordNum());
    }


    /**
     * Tests the getNumRecords method
     */
    public void testGetNumRecords() {
        assertEquals(4096, test.getNumRecords());
    }


    /**
     * Tests the getNumRecords method
     */
    public void testGetStats() {
        assertEquals(0, test.getStats().getDiskReads());
        assertEquals(0, test.getStats().getDiskWrites());
        assertEquals(0, test.getStats().getHits());
        assertEquals(0, test.getStats().getMisses());
    }


    /**
     * Tests the write method
     */
    public void testWrite() {
        Record newRec1 = test.read(0);
        Record newRec2 = test.read(1024);
        Record newRec3 = test.read(2048);
        test.read(3072);
        test.write(-1, newRec1);
        test.write(0, newRec1);
        test.write(1024, newRec2);
        test.write(2048, newRec3);
        test.write(2048, newRec3);
        assertEquals(newRec3.getKey(), test.read(2048).getKey());
    }


    /**
     * Tests the flush method
     */
    public void testFlush() {
        Record newRec1 = test.read(0);
        Record newRec2 = test.read(1024);
        Record newRec3 = test.read(2048);
        test.read(3072);
        test.write(-1, newRec1);
        test.write(0, newRec1);
        test.write(1024, newRec2);
        test.write(2048, newRec3);
        test.write(2048, newRec3);
        test.flush();
        assertEquals(newRec3.getKey(), test.read(2048).getKey());
    }


    /**
     * Tests the read method
     */
    public void testRead() {
        Record newRec1 = test.read(0);
        Record newRec2 = test.read(1024);
        Record newRec3 = test.read(2048);
        test.read(3072);
        assertNull(test.read(-1));
        assertNotNull(newRec1.getKey());
        assertNotNull(newRec2.getKey());
        assertNotNull(newRec3.getKey());
    }

}
