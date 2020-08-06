
/**
 * This is a single instance of a buffer in the buffer pool
 * 
 * @author Barak Finnegan (bjfinn98)
 * @version 2020.08.05
 */
public class Buffer {

    // Setting up vars
    private Record[] library;
    private int index;

    /**
     * The constructor for the Buffer class
     */
    public Buffer(int i, Record[] lib) {
        library = lib;
        index = i;
    }


    /**
     * Determines if the converted index is this index
     * 
     * @param loc
     *            The buffer index
     * @return boolean on whether the record can be found here
     */
    public boolean inRange(int loc) {
        return loc == index;
    }


    /**
     * Will return the record in the library at index
     * 
     * @param loc
     *            The location of the Record either absolute or relative
     * @return Record at the location in Buffer
     */
    public Record getRecord(int loc) {
        return library[loc % 1024];
    }


    /**
     * Will update a Record in the buffer
     * 
     * @param loc
     *            location to update
     * @param r
     *            the new Record
     */
    public void setRecord(int loc, Record r) {
        library[loc % 1024] = r;
    }

}
