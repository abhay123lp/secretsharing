/*
 * 
 */

package steganography;

//import org.junit.AfterClass;

import org.junit.Test;

//import org.junit.BeforeClass;
//import org.junit.Test;
//import static org.junit.Assert.*;
//import java.lang.;
//import junit.framework.Test;
public class ViewChunkTest {

    public ViewChunkTest() {
    }

 

    @Test
    public void testGetByte() {
        System.out.println("getByte");
        int x = 0;
        int y = 0;
        ViewChunk instance = null;
        byte expResult = 0;
        byte result = instance.getByte(x, y);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testSetByte() {
        System.out.println("setByte");
        int x = 0;
        int y = 0;
        byte info = 0;
        ViewChunk instance = null;
        instance.setByte(x, y, info);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetHashMean() {
        System.out.println("getHashMean");
        ViewChunk instance = null;
        String expResult = "";
        String result = instance.getHashMean();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

}