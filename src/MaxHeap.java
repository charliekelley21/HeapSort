
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
     * @param fileName
     *            int that will be the internal size of the heap
     */
    public MaxHeap(BufferPool newPool) {
        pool = newPool;
        numRecords = pool.getNumRecords();
        size = numRecords;
        buildMaxHeap();
    }

    public int heapSize() {
        return size;
    }


    boolean isLeaf(int pos) {
        return (pos >= size / 2) && (pos < size);
    }


    // Return position for left child of pos
    int leftchild(int pos) {
        if (pos >= size / 2)
            return -1;
        return 2 * pos + 1;
    }


    // Return position for right child of pos
    int rightchild(int pos) {
        if (pos >= (size - 1) / 2)
            return -1;
        return 2 * pos + 2;
    }


    // Return position for parent
    int parent(int pos) {
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
     * @param location
     *            The location to perform heapify
     */
    private void heapify(int pos) {
        int childIndex = leftchild(pos);
        Record curr = pool.read(pos);
        if (curr == null || isLeaf(pos)) {
            return;
        }
        Record child = pool.read(childIndex);
        if (child != null && child.getKey() < curr.getKey()) {
            childIndex = rightchild(pos);
            child = pool.read(childIndex);
        }
        if (child != null && curr.getKey() >= child.getKey()) {
            return;
        }
        pool.write(pos, child);
        pool.write(childIndex, curr);
        heapify(childIndex);
    }
    
    private Record removeMax() {
        if (size == 0) {
            return null;
        }
        Record max = pool.read(0);
        Record rm = pool.read(size--);
        pool.write(0, rm);
        pool.write(size, max);
        return rm;
    }
    
    public BufferStatistics heapSort(int length) {
        if (length <= 0) {
            return pool.getStats();
        }
        removeMax();
        buildMaxHeap();
        return heapSort(length - 1);
    }
    
    
}
