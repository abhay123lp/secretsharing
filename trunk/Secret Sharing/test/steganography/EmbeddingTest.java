/*
 * Tests for Embedding.java
 */
package steganography;

import java.math.BigInteger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class EmbeddingTest {

    public EmbeddingTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of writeShares method, of class Embedding.
     */
    @Test
    public void testWriteShares() throws Exception {
        System.out.println("writeShares");
        BigInteger[] args = new BigInteger[3];
        BigInteger[] values = new BigInteger[3];
        for (int i = 0; i < 3; i++) {
            args[i] = BigInteger.valueOf((i+100)*(i+100));
            values[i] = args[i].pow(i+100);
        }
        Embedding instance = new Embedding("1.bmp");
        instance.writeShares(args, values);
        // TODO review the generated test code and remove the default call to fail.
        //  fail("The test case is a prototype.");
    }

    /**
     * Test of saveToFile method, of class Embedding.
     */
    @Test
    public void testSaveToFile() {
        System.out.println("saveToFile");
        String filename = "";
        Embedding instance = null;
        //instance.saveToFile(filename);
        // TODO review the generated test code and remove the default call to fail.
        //  fail("The test case is a prototype.");
    }
}
