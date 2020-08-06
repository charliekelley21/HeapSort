
public class BufferPool {
    private int numRecords;
    private RAFile file;
    private BufferStatistics stats;
    private int maxBuffers;

    public BufferPool(RAFile f, int buffers, int numRecords) {
        file = f;
        maxBuffers = buffers;
        this.numRecords = numRecords;
    }


    public int getNumRecords() {
        return numRecords;
    }


    // return null if out of bounds  i.e. if index == -1
    public Record read(int index) {
        return new Record((short)0, (short)0);
    }


    public void write(int index, Record updated) {
        return;
    }
    
    public BufferStatistics getStats() { 
        return stats;
    }
}
