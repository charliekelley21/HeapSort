
public class BufferPool {
    private int numRecords;
    private BufferStatistics stats;

    public int getNumRecords() {
        return numRecords;
    }
    
    // return null if out of bounds  i.e. if index == -1
    public Record read(int index) {
        return new Record();
    }
    
    public void write(int index, Record updated) {
        return;
    }
    
    public BufferStatistics getStats() { 
        return stats;
    }
}
