import java.io.FileNotFoundException;

/**
 * An external sorting package to hand sorting records of 4 bytes in length from
 * a .dat file
 * 
 * @author Charlie Kelley (charlk21)
 * @author Barak Finnegan (bjfinn98)
 * @version 2020.08.05
 */
public class HeapSort {

    /**
     * This is the entry point of the application
     * 
     * @param args
     *            Command line arguments
     * @throws FileNotFoundException
     *             Throws error when file non existent
     */
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length != 3) {
            throw new IllegalArgumentException(
                "Need three args: data-file-name num-buffers stat-file-name");
        }
        RAFile toBeSorted = new RAFile(args[0]);
        short bufferNum = Short.valueOf(args[1]);
        int recordNum = toBeSorted.recordNum();
        BufferPool pool = new BufferPool(toBeSorted, bufferNum, recordNum);
        MaxHeap heap = new MaxHeap(pool);

        long initTime = System.currentTimeMillis();
        BufferStatistics output = heap.heapSort();
        long totalTime = System.currentTimeMillis() - initTime;

        // call heap sort, this will return BufferStats obj and convert that to
        // stats file
    }
}
