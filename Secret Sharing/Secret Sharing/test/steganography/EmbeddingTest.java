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
        BigInteger[] args = new BigInteger[10];
        BigInteger[] values = new BigInteger[10];
        for (int i = 0; i < args.length; i++) {
            args[i] = BigInteger.valueOf(i+1000);
            values[i] = args[i].pow(5);
        }
        Embedding instance = new Embedding("1.bmp");
        instance.writeSharesToSubImages(args, values);
        instance.saveToFile("11.bmp");
        instance.writeFileWithHash("properties");
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
