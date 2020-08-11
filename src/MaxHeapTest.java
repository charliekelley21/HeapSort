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
    public void testConstructors()  throws FileNotFoundException {
        assertNull(test.getPool());
        assertEquals(test.heapSize(), 0);
        
        RAFile toBeSorted = new RAFile("src/test/p3_input_sample.dat");
        short bufferNum = Short.valueOf("4");
        int recordNum = toBeSorted.recordNum();
        BufferPool pool = new BufferPool(toBeSorted, bufferNum, recordNum);
        MaxHeap heap = new MaxHeap(pool);
        
        assertEquals(heap.heapSize(), 4096);
        assertEquals(pool, heap.getPool());
        
        //BufferStatistics output = heap.heapSort();
    }
    
    /**
     * This will test the constructors for MinHeap
     */
    public void testBuildHeap()  throws FileNotFoundException {        
        RAFile toBeSorted = new RAFile("src/test/p3_input_sample.dat");
        short bufferNum = Short.valueOf("4");
        int recordNum = toBeSorted.recordNum();
        BufferPool pool = new BufferPool(toBeSorted, bufferNum, recordNum);
        MaxHeap heap = new MaxHeap(pool);
        
        heap.buildHeap();
        assertTrue(pool.read(0).getKey() > pool.read(1).getKey());
        assertTrue(pool.read(25).getKey() > pool.read(51).getKey());
        assertTrue(pool.read(100).getKey() > pool.read(102).getKey());
        assertTrue(pool.read(0).getKey() > pool.read(500).getKey());
    }

}
