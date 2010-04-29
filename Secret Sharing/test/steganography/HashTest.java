/*
 * Tests for Hash.java
 */

package steganography;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class HashTest {

    public HashTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Test
    public void testGetHash() {
        Hash instance = new Hash();
        String s = "vitaa";
        instance.getHash(s.getBytes());
    }

}