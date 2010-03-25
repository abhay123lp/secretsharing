/*
 * Test for Joining.java
 */
package secretsharing;

import java.math.BigInteger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import secretsharingException.JoiningException;

public class JoiningTest {

    public JoiningTest() {
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
     * Test of getMessage method, of class Joining.
     */
    @Test
    public void testGetMessage() throws Exception {
        System.out.println("getMessage");
        int k = 3;
        BigInteger[] args = new BigInteger[k];
        BigInteger[] values = new BigInteger[k];
        args[0] = BigInteger.valueOf(2);
        args[1] = BigInteger.valueOf(3);
        args[2] = BigInteger.valueOf(5);
        values[0] = BigInteger.valueOf(3);
        values[1] = BigInteger.valueOf(7);
        values[2] = BigInteger.valueOf(5);
        Joining j = new Joining(BigInteger.valueOf(13));
        BigInteger result = j.getMessage(args, values, k);
        assertEquals(BigInteger.valueOf(11), result);
    }

    @Test
    public void testFillMatrix() {
        System.out.println("fillMatrix");
        int k = 3;
        BigInteger[] args = new BigInteger[k];
        args[0] = BigInteger.valueOf(2);
        args[1] = BigInteger.valueOf(3);
        args[2] = BigInteger.valueOf(5);
        Joining j = new Joining(BigInteger.valueOf(13));
        BigInteger[][] matrix = new BigInteger[k][k];
        j.fillMatrix(matrix, args);
        assertEquals(BigInteger.valueOf(12), matrix[2][0]);
    }

    @Test
    public void testSystemSolve() {
        System.out.println("SystemSolve");
        BigInteger[] values;
        BigInteger[][] matrix = null;
        int k = 3;
        matrix = new BigInteger[k][k];
        matrix[0][0] = BigInteger.valueOf(3);
        matrix[0][1] = BigInteger.valueOf(4);
        matrix[0][2] = BigInteger.valueOf(1);
        matrix[1][0] = BigInteger.valueOf(1);
        matrix[1][1] = BigInteger.valueOf(2);
        matrix[1][2] = BigInteger.valueOf(0);
        matrix[2][0] = BigInteger.valueOf(4);
        matrix[2][1] = BigInteger.valueOf(1);
        matrix[2][2] = BigInteger.valueOf(2);
        values = new BigInteger[k];
        for (int i = 0; i < k; i++) {
            values[i] = BigInteger.valueOf(1);
        }
        BigInteger result = null;
        Joining j = new Joining(BigInteger.valueOf(5));
        try {
            result = j.systemSolve(matrix, values);
        } catch (JoiningException ex) {
        }
        assertEquals(BigInteger.valueOf(4), result);
    }
}