/*
 * Tests for Extraction.java
 */
package steganography;

import java.math.BigInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import steganographyException.SteganographyException;

public class ExtractionTest {

    public ExtractionTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        try {
            BigInteger[] args = new BigInteger[5];
            BigInteger[] values = new BigInteger[5];
            for (int i = 0; i < 5; i++) {
                args[i] = BigInteger.valueOf(i + 1000);
                values[i] = args[i].pow(5);
            }
            emb = new Embedding("1.bmp");
            emb.writeSharesToSubImages(args, values);
            emb.saveToFile("111.bmp");
        } catch (SteganographyException ex) {
            Logger.getLogger(ExtractionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getShares method, of class Extraction.
     */
    @Test
    public void testGetShares() {
        try {
            System.out.println("getShares");
            Extraction instance = null;
            instance = new Extraction("11.bmp", emb.hashMeans);
            instance.getSharesFromSubimages(5, 3);
        } catch (SteganographyException ex) {
            Logger.getLogger(ExtractionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*   @Test
    public void testReadPortationInformation() {
    System.out.println("readPortInfo");
    Extraction instance = null;
    try {
    instance = new Extraction("11.bmp", emb.hashMeans);
    } catch (SteganographyException ex) {
    Logger.getLogger(ExtractionTest.class.getName()).log(Level.SEVERE, null, ex);
    }
    instance.readPortationInformation();
    int result = instance.w;
    assertEquals(320, result);
    }*/
    public Embedding emb;
}
