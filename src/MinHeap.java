
/**
 * The Min-Heap that will sort the external file.
 * 
 * @author Charlie Kelley (charlk21)
 * @author Barak Finnegan (bjfinn98)
 * @version 2020.08.04
 */
public class MinHeap {

    // Setting up vars
    private final int arrayLength;
    private int size;
    private int[] heap;

    /**
     * The public constructor for MinHeap
     */
    public MinHeap() {
        arrayLength = 100;
        size = 0;
        heap = new int[arrayLength];
    }


    /**
     * The public constructor for MinHeap
     * 
     * @param l
     *            int that will be the internal size of the heap
     */
    public MinHeap(int l) {
        arrayLength = l;
        size = 0;
        heap = new int[arrayLength];
    }


    /**
     * The public constructor for MinHeap
     * 
     * @param init
     *            the initial array to be built
     */
    public MinHeap(int[] init) {
        arrayLength = init.length;
        size = init.length;
        heap = init;
        buildMinHeap();
    }


    /**
     * Will construct a min heap from the items in heap
     */
    private void buildMinHeap() {
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
        int left = 2 * location + 1;
        int right = 2 * location + 2;
        int smallest_index;
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


    /**
     * Returns the heap
     * 
     * @return int[] of the current state of the heap
     */
    public int[] getArray() {
        return heap;
    }
}