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
import static org.junit.Assert.*;
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
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getShares method, of class Extraction.
     */
    @Test
    public void testGetShares()  {
        System.out.println("getShares");
        Extraction instance = null;
        try {
            instance = new Extraction("11.bmp");
        } catch (SteganographyException ex) {
            Logger.getLogger(ExtractionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        instance.getShares();
        BigInteger result = instance.values[2];
        assertEquals(new BigInteger("1010040080080032"), result);
    }

}