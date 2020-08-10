
/**
 * The Max-Heap that will sort the external file.
 *
 * @author Charlie Kelley (charlk21)
 * @author Barak Finnegan (bjfinn98)
 * @version 2020.08.05
 */
public class MaxHeap {

    // Setting up vars
    private final int numRecords;
    private int size;
    private BufferPool pool;

    /**
     * The public constructor for MaxHeap
     */
    public MaxHeap() {
        numRecords = 0;
        size = 0;
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
        buildMaxHeap();
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
     * Determines if the current position is a leaf node
     * 
     * @param pos
     *            current position
     * @return true if node is a leaf, else false
     */
    boolean isLeaf(int pos) {
        return (pos >= size / 2) && (pos < size);
    }


    /**
     * Return position for left child of pos
     * 
     * @param pos
     *            current position
     * @return the index of the left child node, -1 if not present
     */
    private int leftchild(int pos) {
        if (pos >= size / 2)
            return -1;
        return 2 * pos + 1;
    }


    /**
     * Return position for right child of pos
     * 
     * @param pos
     *            current position
     * @return the index of the right child node, -1 if not present
     */
    private int rightchild(int pos) {
        if (pos >= (size - 1) / 2)
            return -1;
        return 2 * pos + 2;
    }


    /**
     * Return position for parent
     * 
     * @param pos
     *            current position
     * @return index for the parent of the current node
     */
    private int parent(int pos) {
        if (pos <= 0)
            return -1;
        return (pos - 1) / 2;
    }


    /**
     * Will construct a max heap from the items in heap
     */
    private void buildMaxHeap() {
        for (int i = size / 2 + 1; i >= 0; i--) {
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
        if ((pos < 0) || (pos >= size)) {
            return;
        }
        int childIndex;
        Record curr;
        Record child = null;
        while (!isLeaf(pos)) {
            curr = pool.read(pos);
            childIndex = leftchild(pos);
            if (childIndex != -1) {
                child = pool.read(childIndex);
            }
            if (child != null && child.getKey() < curr.getKey()) {
                childIndex = rightchild(pos);
                if (childIndex != -1) {
                    child = pool.read(childIndex);
                }
            }
            if (child != null && curr.getKey() >= child.getKey()) {
                return;
            }
            pool.write(pos, child);
            pool.write(childIndex, curr);
            pos = childIndex;
        }
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
        if (size == 0) {
            return null;
        }
        Record max = pool.read(0);
        size--;
        Record rm = pool.read(size);
        // first loop we are writing null values, interestingly lots of values
        // in first buffer are null.
        pool.write(0, rm);
        pool.write(size, max);
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
            removeMax();
        }
        return pool.getStats();
    }

}
