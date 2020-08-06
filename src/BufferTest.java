import student.TestCase;

/**
 * Test class for the Buffer Data object
 * 
 * @author Barak Finnegan (bjfinn98)
 * @version 2020.08.06
 */
public class BufferTest extends TestCase {

    // Setting up vars
    private Buffer test;

    /**
     * The set up method for the test suite
     */
    public void setUp() {
        Record[] lib = new Record[1024];
        lib[100] = new Record((short)6, (short)7);
        test = new Buffer(1, lib);
    }


    /**
     * Tests the inRange method for the Buffer
     */
    public void testInRange() {
        assertTrue(test.inRange(1));
        assertFalse(test.inRange(0));
    }


    /**
     * Tests the inRange method for the Buffer
     */
    public void testGetRecord() {
        // can get the record using relative path
        Record tes1 = test.getRecord(100);
        // can get the record using absolute path
        Record tes2 = test.getRecord(1124);
        assertNotNull(tes1);
        assertNotNull(tes2);
    }

}
