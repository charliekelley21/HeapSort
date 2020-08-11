import java.io.FileNotFoundException;
import student.TestCase;

import org.junit.Test;

/**
 * This class tests the HeapSort class
 * 
 * @author Barak Finnegan (bjfinn98)
 * @version 2020.08.05
 */

public class HeapSortTest extends TestCase {
    
    
    /**
     * An integration test for the whole project
     * 
     * @throws FileNotFoundException
     *             will never happen
     */
    @SuppressWarnings("static-access")
    @Test(timeout=600000)
    public void testMain() throws FileNotFoundException {
        HeapSort test = new HeapSort();
        test.main(new String[] { "src/test/p3_input_sample.dat", "4",
            "output.txt" });
        assertEquals("5 8404 8131 244 16634 2746 24619 6627", systemOut()
            .getHistory());
    }


    /**
     * Test the illegal argument exception
     */
    public void testIllegalArg() {
        try {
            HeapSort.main(new String[] {});
        }
        catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }
    }


    /**
     * Test the file not found exception
     */
    public void testFileNotFound() {
        try {
            HeapSort.main(new String[] { "yarg", "3", "out.txt" });
        }
        catch (Exception e) {
            assertTrue(e instanceof FileNotFoundException);
        }
    }

}
