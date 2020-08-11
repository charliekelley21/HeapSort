import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;

/**
 * The file that manages the reads and writes directly to and from disk
 * 
 * @author Barak Finnegan (bjfinn98)
 * @version 2020.08.05
 */
public class RAFile {

    // Setting up vars
    private RandomAccessFile file;
    private final int bufferRecordSize = 4096;
    private final String fName;

    /**
     * Constructor for the RAFile class
     * 
     * @param fName
     *            String of the name of the file
     * @throws FileNotFoundException
     *             Throws exception when file not found
     */
    public RAFile(String fName) throws FileNotFoundException {
        this.fName = fName;
        file = new RandomAccessFile(fName, "r");
    }


    /**
     * The number of records in need of sorting.
     * Since the file is guarenteed to exist, cannot visit catch in this method
     * 
     * @return int of number of Records
     */
    public int recordNum() {
        int recNum = -1;
        try {
            recNum = (int)(file.length() / 4);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return recNum;
    }


    /**
     * Closes the RAFile
     */
    public void close() {
        try {
            file.close();
        }
        catch (IOException e) {
        }
    }


    /**
     * Opens the RAFile
     */
    public void open() {
        try {
            file = new RandomAccessFile(fName, "rw");
        }
        catch (FileNotFoundException e) {
        }
    }


    /**
     * Gets the block of memory in the requests region
     * 
     * @param loc
     *            The buffer location in records (0 ~ 0-1023, 1 ~ 1024-2047)
     * @return an array of Record objects
     */
    public Record[] read(int loc) {
        // set location to
        loc = loc * bufferRecordSize;
        Record[] records = new Record[bufferRecordSize / 4];
        try {
            file.seek(loc);
            byte[] readBytes = new byte[bufferRecordSize];
            file.read(readBytes);
            short[] shorts = new short[readBytes.length / 2];
            ByteBuffer.wrap(readBytes).asShortBuffer().get(shorts);
            for (int i = 0; i < records.length; i++) {
                records[i] = new Record(shorts[2 * i], shorts[2 * i + 1]);
            }
        }
        catch (IOException e) {
            return null;
        }
        return records;
    }


    /**
     * Writes a new block of memory to the output file.
     * 
     * @param newRecords
     *            The new Records to be written
     * @param loc
     *            The buffer location in records (0 ~ 0-1023, 1 ~ 1024-2047)
     * @return boolean on success
     */
    public boolean write(Record[] newRecords, int loc) {
        // set location to
        loc = loc * bufferRecordSize;
        try {
            if (loc > file.length()) {
                return false;
            }
            file.seek(loc);
            byte[] writeBytes = new byte[bufferRecordSize];
            short[] shorts = new short[writeBytes.length / 2];
            for (int i = 0; i < newRecords.length; i++) {
                shorts[2 * i] = newRecords[i].getKey();
                shorts[2 * i + 1] = newRecords[i].getValue();
            }
            ByteBuffer.wrap(writeBytes).asShortBuffer().put(shorts);
            file.write(writeBytes);
        }
        catch (IOException e) {
            return false;
        }
        return true;
    }


    /**
     * Gets the first record from each 4096 byte block.
     * 
     * @return an array of the bytes in the block
     */
    public Record[] firstRecords() {
        int numBlocks = recordNum() / 1024;
        Record[] answer = new Record[numBlocks];
        for (int i = 0; i < answer.length; i++) {
            try {
                file.seek(i * 4096);
                short k = file.readShort();
                short v = file.readShort();
                Record newInsert = new Record(k, v);
                answer[i] = newInsert;
            }
            catch (IOException e) {
            }
        }
        return answer;
    }
}
