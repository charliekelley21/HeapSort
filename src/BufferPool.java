
/**
 * Controls when we need to read or write to disk
 * Attempts to minimize disk accesses for external sorting
 * 
 * @author Charlie Kelley (charlk21)
 * @author Barak Finnegan (bjfinn98)
 * @version 2020.08.05
 */
public class BufferPool {

    // Setting up vars
    private int numRecords;
    private LList pool;
    private RAFile file;
    private BufferStatistics stats;
    private final int maxBuffers;

    /**
     * Constructor for the BufferPool class
     * 
     * @param f
     *            The file the buffer pool reads from
     * @param buffers
     *            the maximum amount of buffers
     * @param numRecords
     *            the total number of records in file
     */
    public BufferPool(RAFile f, int buffers, int numRecords) {
        file = f;
        maxBuffers = buffers;
        this.numRecords = numRecords;
    }


    /**
     * Returns the number of records on disk
     * 
     * @return int of number of records
     */
    public int getNumRecords() {
        return numRecords;
    }


    /**
     * Determines whether we must read in new info from disk or whether the info
     * is stored on buffer.
     * 
     * @param index
     *            index to be retrieved
     * @return null if out of bounds i.e. if index == -1
     */
    public Record read(int index) {
        return new Record((short)0, (short)0);
    }


    /**
     * This method will determine whether we need to create a new buffer.
     * Also when a buffer gets exiled from our pool we will write it to memory.
     * 
     * @param index
     *            Memory index of the buffer.
     * @param updated
     *            The new Record to be store into the buffer.
     */
    public void write(int index, Record updated) {
        return;
    }


    /**
     * Will return the information that we need to output
     * 
     * @return BufferStatistics on how interactive our cache worked
     */
    public BufferStatistics getStats() {
        return stats;
    }
}
