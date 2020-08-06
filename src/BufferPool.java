
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
        pool = new LList();
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
     * This is the internal method for understanding where to instantiate a new
     * buffer or write to an existing one
     * 
     * @param recordLoc
     *            The location of the record
     * @return The block of memory index
     */
    private int recordNumToBlockNum(int recordLoc) {
        return recordLoc / 1024;
    }


    /**
     * Determines whether we must read in new info from disk or whether the info
     * is stored on buffer.
     * 
     * @param index
     *            index to be retrieved
     * @return desired Record or null if out of bounds
     */
    public Record read(int index) {
        // The index inputed into the read is a absolute record index. RAFile
        // functions on bufferIndexes, call recordNumToBlockNum to convert.
        // Also delete when you see this.
        return new Record((short)0, (short)0);
    }


    /**
     * This method will determine whether we need to create a new buffer.
     * Also when a buffer gets exiled from our pool we will write it to memory.
     * 
     * @param index
     *            Memory index of the Record.
     * @param updated
     *            The new Record to be store into the buffer.
     */
    public void write(int index, Record updated) {
        // search buffer pool, if there update stats and buffer end
        int bufferIndex = recordNumToBlockNum(index);
        for (int i = 0; i < pool.length(); i++) {
            Buffer tmp = pool.getValue();
            if (tmp.inRange(bufferIndex)) {
                tmp.setRecord(index, updated);

                // MUST UPDATE STATS

                return;
            }
            pool.next();
        }
        pool.moveToStart();
        // if not there create new buffer, push last out and update disk if
        // necessary
        Buffer newBuffer = newBuffer(bufferIndex);

        // update the new buffer update stats
        newBuffer.setRecord(index, updated);

        // MUST UPDATE STATS

    }


    /**
     * Creates a new buffer and if the number of buffers exceed maximum will get
     * rid of last index.
     * 
     * Adheres to the Least Frequently Used policy.
     * 
     * @param bufferIndex
     *            The bufferIndex of to request from RAFile
     * @return pointer to newly generated Buffer
     */
    private Buffer newBuffer(int bufferIndex) {
        if (pool.length() == maxBuffers) {
            pool.moveToEnd();
            pool.prev();
            Buffer stale = pool.remove();
            // Do we need to write?
            if(stale.dirty()) {
                file.write(stale.records(), stale.index());
            }
        }
        Buffer newBuffer = new Buffer(bufferIndex, file.read(bufferIndex));
        // add newBuffer to start of list
        pool.moveToStart();
        pool.insert(newBuffer);

        return newBuffer;
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
