
/**
 * The file that manages the reads and writes directly to and from disk
 * 
 * @author Barak Finnegan (bjfinn98)
 * @version 2020.08.05
 */
public class RAFile {

    // Setting up vars
    private String fileName;

    /**
     * Constructor for the RAFile class
     * 
     * @param fName
     *            String of the name of the file
     */
    public RAFile(String fName) {
        fileName = fName;
    }


    /**
     * The number of records in need of sorting
     * 
     * @return int of number of Records
     */
    public int recordNum() {
        return 0;
    }

}
