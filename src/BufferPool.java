
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
        stats = new BufferStatistics();
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
        // convert index to Buffer num
        int bufferIndex = recordNumToBlockNum(index);
        // search pool for buffer with curr index
        for (int i = 0; i < pool.length(); i++) {
            Buffer tmp = pool.getValue();
            if (tmp.inRange(bufferIndex)) {
                // record found, cache hit
                stats.hit();

                // On hit we must move the buffer to first position
                pool.remove();
                pool.moveToStart();
                pool.insert(tmp);
                return tmp.getRecord(index);
            }
            pool.next();
        }
        // record not present, cache miss
        pool.moveToStart();
        stats.miss();

        // if not there create new buffer, push last out and update disk if
        // necessary
        Buffer newBuffer = newBuffer(bufferIndex);
        return newBuffer.getRecord(index);
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
                // On hit we must move the buffer to first position
                pool.remove();
                pool.moveToStart();
                tmp.setRecord(index, updated);
                pool.insert(tmp);
                // record written, found a hit in cache
                stats.hit();
                return;
            }
            pool.next();
        }
        // record not found, cache miss
        stats.miss();
        pool.moveToStart();
        // if not there create new buffer, push last out and update disk if
        // necessary
        Buffer newBuffer = newBuffer(bufferIndex);

        // update the new buffer
        newBuffer.setRecord(index, updated);

    }


    /**
     * Creates a new buffer and if the number of buffers exceed maximum will get
     * rid of last index.
     * 
     * Adheres to the Least Recently Used policy.
     * 
     * @param bufferIndex
     *            The bufferIndex of to request from RAFile
     * @return pointer to newly generated Buffer
     */
    private Buffer newBuffer(int bufferIndex) {
        // adding a new buffer requires a disk read
        stats.diskRead();
        file.open();
        if (pool.length() == maxBuffers) {
            pool.moveToEnd();
            pool.prev();
            Buffer stale = pool.remove();
            // Do we need to write?
            if (stale.dirty()) {
                file.write(stale.records(), stale.index());
                stats.diskWrite();
            }
        }
        Buffer newBuffer = new Buffer(bufferIndex, file.read(bufferIndex));
        // add newBuffer to start of list
        pool.moveToStart();
        pool.insert(newBuffer);
        file.close();

        return newBuffer;
    }


    /**
     * Flushes the remaining Buffers in the Buffer pool to write to disk.
     * 
     * @return int the number of buffers flushed
     */
    public int flush() {
        // adding a new buffer requires a disk read
        file.open();
        int ans = pool.length();
        pool.moveToStart();
        pool.moveToStart();
        while (!pool.isAtEnd()) {
            Buffer curr = pool.remove();
            file.write(curr.records(), curr.index());
        }
        pool.moveToStart();
        file.close();

        return ans;
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
