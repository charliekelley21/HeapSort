
public class BufferPool {
    private int numRecords;
    private RAFile file;
    private int maxBuffers;

    public BufferPool(RAFile f, int buffers, int numRecords) {
        file = f;
        maxBuffers = buffers;
        this.numRecords = numRecords;
    }


    public int getNumRecords() {
        return numRecords;
    }


    public Record read(int index) {
        short k = 0;
        short v = 0;
        return new Record(k, v);
    }


    public void write(int index, Record updated) {
        return;
    }
}
