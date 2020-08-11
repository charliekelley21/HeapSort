
/**
 * The Max-Heap that will sort the external file.
 *
 * @author Charlie Kelley (charlk21)
 * @author Barak Finnegan (bjfinn98)
 * @version 2020.08.05
 */
public class MaxHeap {

    // Setting up vars
    private final int numRecords; // max number of elements in heap
    private int size; // current size of the heap
    private BufferPool pool;

    /**
     * The public constructor for MaxHeap
     */
    public MaxHeap() {
        numRecords = 0;
        size = 0;
        pool = null;
    }


    /**
     * The public constructor for MaxHeap
     *
     * @param newPool
     *            BufferPool used by MaxHeap
     */
    public MaxHeap(BufferPool pool) {
        this.pool = pool;
        numRecords = pool.getNumRecords();
        size = numRecords;
        buildHeap();
    }


    /**
     * Getter for the MaxHeap pool object, helper for testing
     * 
     * @return internal pool
     */
    public BufferPool getPool() {
        return pool;
    }


    /**
     * Getter for the size of the maxHeap
     *
     * @return size
     */
    public int heapSize() {
        return size;
    }


    /**
     * Will construct a max heap from the items in heap
     */
    private void buildHeap() {
        for (int i = (size / 2) - 1; i >= 0; i--) {
            heapify(i);
        }
    }


    /**
     * This is the internal heapify method
     *
     * @param pos
     *            The location to perform heapify
     */
    private void heapify(int pos) {
        if ((pos < 0) || (pos >= size / 2)) {
            return;
        }
        Record curr = pool.read(pos);
        int childIndex = 2 * pos + 1;
        if (childIndex < (size - 1)) {
            Record swap = pool.read(childIndex);
            Record right = pool.read(childIndex + 1);
            // right child is the greater of the two
            if (Short.compare(swap.getKey(), right.getKey()) < 0) {
                childIndex++;
                swap = right;
            }
            if (Short.compare(curr.getKey(), swap.getKey()) >= 0) {
                return;
            }
            pool.write(pos, swap);
            pool.write(childIndex, curr);
        }
        heapify(childIndex);
    }


    /**
     * This removes the maximum value from the heap and decreases the size of
     * the heap.
     *
     * @return the Record with the maximum key value
     */
    private Record removeMax() {
        // error were last index in buffer array is null.
        // not being read in as null, so must be written as null.
        if (size <= 0) {
            return null;
        }
        Record max = pool.read(0);
        size--;
        Record rm = pool.read(size);
        // first loop we are writing null values, interestingly lots of values
        // in first buffer are null.
        pool.write(0, rm);
        pool.write(size, max);
        // size--;
        heapify(0);
        return max;
    }


    /**
     * Heap sort algorithm performed on a set of Records
     *
     * @return
     */
    public BufferStatistics heapSort() {
        // error were last index in buffer array is null.
        // not being read in as null, so must be written as null.
        for (int i = 0; i < numRecords; i++) {
            Record max = removeMax();
            //System.out.println(max.getKey());
        }
        return pool.getStats();
    }

}
