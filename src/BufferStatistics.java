
/**
 * Controls the run-time statistics for the heapsort algorithm
 * 
 * @author Charlie Kelley (charlk21)
 * @version 2020.08.06
 */
public class BufferStatistics {
    // set up vars
    private int cacheHits;
    private int cacheMisses;
    private int diskReads;
    private int diskWrites;

    /**
     * Default constructor BufferStatistics
     */
    public BufferStatistics() {
        cacheHits = 0;
        cacheMisses = 0;
        diskReads = 0;
        diskWrites = 0;
    }


    /**
     * Increase the cache hit count
     */
    public void hit() {
        cacheHits++;
    }


    /**
     * Getter for the number of cache hits
     * 
     * @return cache hits
     */
    public int getHits() {
        return cacheHits;
    }


    /**
     * Increase the cache miss count
     */
    public void miss() {
        cacheMisses++;
    }


    /**
     * getter for the number of cache misses
     * 
     * @return cache misses
     */
    public int getMisses() {
        return cacheMisses;
    }


    /**
     * Increase the disk read count
     */
    public void diskRead() {
        diskReads++;
    }


    /**
     * getter for the number of disk reads
     * 
     * @return disk reads
     */
    public int getDiskReads() {
        return diskReads;
    }


    /**
     * Increase the disk write count
     */
    public void diskWrite() {
        diskWrites++;
    }


    /**
     * getter for the number of disk writes
     * 
     * @return disk writes
     */
    public int getDiskWrites() {
        return diskWrites;
    }
}
