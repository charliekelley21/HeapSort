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
     * This will test the constructors for MaxHeap
     * 
     * @throws FileNotFoundException
     *             if file not found
     */
    public void testConstructors() throws FileNotFoundException {
        assertNull(test.getPool());
        assertEquals(test.heapSize(), 0);

        RAFile toBeSorted = new RAFile("src/test/p3_input_sample.txt");
        short bufferNum = Short.valueOf("4");
        int recordNum = toBeSorted.recordNum();
        BufferPool pool = new BufferPool(toBeSorted, bufferNum, recordNum);
        MaxHeap heap = new MaxHeap(pool);

        assertEquals(heap.heapSize(), 4096);
        assertEquals(pool, heap.getPool());

        // BufferStatistics output = heap.heapSort();
    }


    /**
     * This will test the buildHeap method for MaxHeap
     * 
     * @throws FileNotFoundException
     *             if file not found
     */
    public void testBuildHeap() throws FileNotFoundException {
        RAFile toBeSorted = new RAFile("src/test/p3_input_sample.txt");
        short bufferNum = Short.valueOf("4");
        int recordNum = toBeSorted.recordNum();
        BufferPool pool = new BufferPool(toBeSorted, bufferNum, recordNum);
        MaxHeap heap = new MaxHeap(pool);

        heap.buildHeap();
        assertTrue(pool.read(0).getKey() > pool.read(1).getKey());
        assertTrue(pool.read(25).getKey() > pool.read(51).getKey());
        assertTrue(pool.read(100).getKey() > pool.read(202).getKey());
    }


    /**
     * This will test the removeMax method for MaxHeap
     * 
     * @throws FileNotFoundException
     *             if file not found
     */
    public void testRemoveMax() throws FileNotFoundException {
        MaxHeap heap = new MaxHeap();
        assertNull(heap.removeMax());

        RAFile toBeSorted = new RAFile("src/test/p3_input_sample.txt");
        short bufferNum = Short.valueOf("4");
        int recordNum = toBeSorted.recordNum();
        BufferPool pool = new BufferPool(toBeSorted, bufferNum, recordNum);
        heap = new MaxHeap(pool);
        heap.buildHeap();

        Record max = pool.read(0);
        Record removed = heap.removeMax();
        Record moved = pool.read(4095);

        assertEquals(max.getKey(), removed.getKey());
        assertEquals(moved.getKey(), removed.getKey());

        max = pool.read(0);
        removed = heap.removeMax();
        moved = pool.read(4094);

        assertEquals(max.getKey(), removed.getKey());
        assertEquals(moved.getKey(), removed.getKey());
    }


    /**
     * This will test the heapSort method for MaxHeap
     * 
     * @throws FileNotFoundException
     *             if file not found
     */
    public void testHeapSortWithRemove() throws FileNotFoundException {
        RAFile toBeSorted = new RAFile("src/test/p3_input_sample.txt");
        short bufferNum = Short.valueOf("4");
        int recordNum = toBeSorted.recordNum();
        BufferPool pool = new BufferPool(toBeSorted, bufferNum, recordNum);
        MaxHeap heap = new MaxHeap(pool);

        int last = pool.read(0).getKey();

        // heap.heapSort();
        for (int i = 4095; i > 0; i--) {
            assertTrue(pool.read(0).getKey() <= last);
            last = pool.read(0).getKey();
            Record max = pool.read(0);
            Record removed = heap.removeMax();
            Record moved = pool.read(i);
            assertEquals(max.getKey(), removed.getKey());
            assertEquals(moved.getKey(), removed.getKey());
        }
        pool.flush();
        try {
            CheckFile sorted = new CheckFile();
            assertTrue(sorted.checkFile("src/test/p3_input_sample.txt"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Tests the heapsort method
     * 
     * @throws FileNotFoundException
     *             if file not found
     */
    public void testHeapSort() throws FileNotFoundException {
        RAFile toBeSorted = new RAFile("src/test/p3_input_sample.txt");
        short bufferNum = Short.valueOf("4");
        int recordNum = toBeSorted.recordNum();
        BufferPool pool = new BufferPool(toBeSorted, bufferNum, recordNum);
        MaxHeap heap = new MaxHeap(pool);

        heap.heapSort();
        try {
            CheckFile sorted = new CheckFile();
            assertTrue(sorted.checkFile("src/test/p3_input_sample.txt"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
