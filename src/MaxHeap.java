
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
     * The public constructor for MaxHeap
     * 
     * @param fileName
     *            int that will be the internal size of the heap
     */
    public MaxHeap(BufferPool newPool) {
        pool = newPool;
        numRecords = pool.getNumRecords();
        size = numRecords;
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
    private void heapify(int location) {
        // Should check left and right are in bounds before pool read
        Record left = pool.read(2 * location + 1);
        Record right = pool.read(2 * location + 2);
        int smallest_index;
        // must use the compareTo method in the Record
        if (left < size && heap[left] < heap[location]) {
            smallest_index = left;
            if (right < size && heap[right] < heap[left]) {
                smallest_index = right;
            }
        }
        else {
            smallest_index = location;
        }
        if (smallest_index != location) {
            int temp = heap[location];
            heap[location] = heap[smallest_index];
            heap[smallest_index] = temp;
            heapify(smallest_index);
        }
    }
}
