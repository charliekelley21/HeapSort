
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
     * @param pool
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
     * method to swap two indexes
     */
    private void swap(int first, int second) {
        Record one = pool.read(first);
        pool.write(first, pool.read(second));
        pool.write(second, one);
    }


    /**
     * Will construct a max heap from the items in heap
     */
    public void buildHeap() {
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
        int largest = pos;
        int l = 2 * pos + 1;
        int r = 2 * pos + 2;
        // if left child is larger than root
        if ((l < size) && (pool.read(l).getKey() > pool.read(pos).getKey())) {
            largest = l;
        }
        // if right child is larger than the largest so far
        if ((r < size) && (pool.read(r).getKey() > pool.read(largest)
            .getKey())) {
            largest = r;
        }
        // if largest isnt the root
        if (largest != pos) {
            swap(pos, largest);
            heapify(largest);
        }

    }


    /**
     * This removes the maximum value from the heap and decreases the size of
     * the heap.
     *
     * @return the Record with the maximum key value
     */
    public Record removeMax() {
        // error were last index in buffer array is null.
        // not being read in as null, so must be written as null.
        if (size == 0) {
            return null;
        }
        this.size--;
        swap(0, size);
        heapify(0);
        return pool.read(size);
    }


    /**
     * Heap sort algorithm performed on a set of Records
     *
     * @return stats for the stat file.
     */
    public BufferStatistics heapSort() {
        for (int i = size - 1; i > 0; i--) {
            removeMax();
        }
        return pool.getStats();
    }

}
