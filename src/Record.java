
/**
 * Testing class for the Record
 * 
 * @author Barak Finnegan (bjfinn98)
 * @version 2020.08.05
 */
public class Record {

    // Setting up vars
    private short key;
    private short value;

    /**
     * The constructor for the Record class
     * 
     * @param k
     *            short on the key
     * @param v
     *            short on the value
     */
    public Record(short k, short v) {
        key = k;
        value = v;
    }


    /**
     * Basic getter for Key
     * 
     * @return short on the Key
     */
    public short getKey() {
        return key;
    }


    /**
     * Basic getter for Value
     * 
     * @return short on the Value
     */
    public short getValue() {
        return value;
    }


    /**
     * If this Record is before comparison will return negative, equal will
     * return 0, and after return positive.
     * 
     * @param newRecord
     *            Record to compare
     * @return int based on comparison
     */
    public int compareTo(Record newRecord) {
        return key - newRecord.getKey();
    }
}
