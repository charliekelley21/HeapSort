
public class MinHeap {
    private int[] Heap; // Pointer to the heap array
    private int size;          // Maximum size of the heap
    private int n;             // Number of things now in heap
    
 // Constructor supporting preloading of heap contents
    MinHeap(int[] h, int num, int max)  {
        Heap = h;
        n = num;  
        size = max;
        }
}
