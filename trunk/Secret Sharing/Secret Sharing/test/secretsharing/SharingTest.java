/*
 * Tests for Sharing.java
 */

package secretsharing;

import java.math.BigInteger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class SharingTest {

    public SharingTest() {
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
     * Test of generatePolynom method, of class Sharing.
     */
    @Test
    public void testGeneratePolynom() {
        System.out.println("generatePolynom");
        int power = 6;
        Sharing instance = new Sharing(BigInteger.valueOf(12), BigInteger.valueOf(147));
        instance.generatePolynom(power);
      //  for (int i=0; i<power; i++)
      //      System.out.println(instance.coefficients[i].toString());
    }

    /**
     * Test of getPolynomValue method, of class Sharing.
     */
    @Test
    public void testGetPolynomValue() {
        System.out.println("getPolynomValue");
        int power = 2;
        Sharing instance = new Sharing(BigInteger.valueOf(3), BigInteger.valueOf(7));
        instance.generatePolynom(power);
        BigInteger arg = BigInteger.valueOf(2);
         for (int i=0; i<power; i++)
            System.out.println(instance.coefficients[i].toString());
        System.out.println();
        BigInteger result = instance.getPolynomValue(arg);
        System.out.println(result.toString());
    }

    /**
     * Test of getShares method, of class Sharing.
     */
    @Test
    public void testGetShares() throws Exception {
        System.out.println("getShares");
        int k = 2;
        int n = 4;
        BigInteger[] args = new BigInteger[n];
        for (int i=0;i<n;i++)
            args[i] = BigInteger.valueOf(i);
        Sharing instance = new Sharing(BigInteger.valueOf(3), BigInteger.valueOf(113));
        BigInteger[] result = instance.getShares(args, k, n);
        for (int i=0; i<result.length; i++)
              System.out.println(result[i].toString());
    }

}