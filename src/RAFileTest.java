import java.io.FileNotFoundException;
import java.io.IOException;
import student.TestCase;

/**
 * This class tests the RAFile class
 * 
 * @author Barak Finnegan (bjfinn98)
 * @version 2020.08.05
 */
public class RAFileTest extends TestCase {

    // Setting up vars
    private RAFile test;

    /**
     * This is the set up method for the test suite
     */
    public void setUp() {
        try {
            test = new RAFile("src/test/p3_input_sample.dat");
        }
        catch (FileNotFoundException e) {
        }
    }


    /**
     * This tests the recordNum method
     */
    public void testRecordNum() {
        assertEquals(4096, test.recordNum());
    }


    /**
     * This tests the close method
     */
    public void testClose() {
        test.close();
        assertNotNull(test);
    }


    /**
     * This tests the close method
     */
    public void testLengthExceptions() {
        test.close();
        try {
            test.recordNum();
        }
        catch (Exception e) {
            assertTrue(e instanceof IOException);
        }
    }


    /**
     * This tests the open method
     */
    public void testOpen() {
        test.close();
        test.open();
        assertNotNull(test);
    }


    /**
     * This tests the read method
     */
    public void testRead() {
        Record[] records = test.read(0);
        assertEquals(3391, records[0].getKey());
        assertEquals(119, records[2].getKey());
    }


    /**
     * This tests the read method
     */
    public void testReadExceptions() {
        try {
            test.read(100);
        }
        catch (Exception e) {
            assertNull(test.read(100));
        }
    }


    /**
     * This tests the write method
     */
    public void testWrite() {
        Record[] records = test.read(0);
        assertEquals(3391, records[0].getKey());
        assertEquals(119, records[2].getKey());
        Record[] newRecords = new Record[1024];
        for (int i = 0; i < newRecords.length; i++) {
            newRecords[i] = new Record(records[i].getKey(), records[i]
                .getValue());
        }
        short k = 5;
        short v = 5;
        newRecords[0] = new Record(k, v);
        newRecords[2] = new Record(k, v);
        assertEquals(5, newRecords[0].getKey());
        assertEquals(5, newRecords[2].getKey());
        test.close();
        test.open();
        assertTrue(test.write(newRecords, 0));
        Record[] testing = test.read(0);
        assertEquals(newRecords[0].getKey(), testing[0].getKey());
        assertEquals(newRecords[2].getKey(), testing[2].getKey());
        test.write(records, 0);
    }


    /**
     * This tests the write method
     */
    public void testWriteExceptions() {
        try {
            assertFalse(test.write(new Record[10], 100));
            assertFalse(test.write(new Record[10], -1));
        }
        catch (Exception e) {
            assertTrue(e instanceof IOException);
        }
    }


    /**
     * This tests the firstRecords method
     */
    public void testFirstRecords() {
        Record[] testing = test.firstRecords();
        assertEquals(3391, testing[0].getKey());
        assertEquals(7998, testing[1].getKey());
    }
}
