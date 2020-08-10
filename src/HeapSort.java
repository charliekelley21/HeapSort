import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;

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

        long initTime = System.currentTimeMillis();
        MaxHeap heap = new MaxHeap(pool);
        BufferStatistics output = heap.heapSort();
        long totalTime = System.currentTimeMillis() - initTime;

        toBeSorted.open();
        Record[] answers = toBeSorted.firstRecords();
        toBeSorted.close();
        int itemsPerLine = 8;

        for (int i = 0; i <= answers.length / itemsPerLine; i++) {
            for (int j = 0; j < itemsPerLine; j++) {
                // guarantee no array out of bounds
                int index = itemsPerLine * i + j;
                if (!(index >= answers.length)) {
                    System.out.print(answers[index].getKey() + " "
                        + answers[index].getKey() + " ");
                }
            }
            System.out.print("\n");
        }

        File outputFile = null;
        try {
            outputFile = new File(args[2]);
            // will not generate a new file on call.
            outputFile.createNewFile();
            FileWriter writer = new FileWriter(outputFile);
            writer.append("File name: " + args[2] + "\n");
            writer.append("Cache Hits: " + output.getHits() + "\n");
            writer.append("Cache Misses: " + output.getMisses() + "\n");
            writer.append("Disk Reads: " + output.getDiskReads() + "\n");
            writer.append("Disk Writes: " + output.getDiskWrites() + "\n");
            writer.append("Time To Sort: " + totalTime);
            writer.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
