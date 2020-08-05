
public class BufferPool {
    private int numRecords;

    public int getNumRecords() {
        return numRecords;
    }
    
    public Record read(int index) {
        return new Record();
    }
    
    public void write(int index, Record updated) {
        return;
    }
}
