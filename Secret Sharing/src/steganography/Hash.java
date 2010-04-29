/*
 * Return hash for byte array with using SHA-1
 */
package steganography;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {

    public String getHash(byte[] msg) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException ex) {
           new steganographyException.SteganographyException("Error with hash");
        }
        md.update(msg);
        byte[] aMessageDigest = md.digest();

        // Printout
      //  System.out.println("Original: " + new String(msg));
     //   System.out.println("Message Digest: " + new String(aMessageDigest));
        return new String(aMessageDigest);
    }
}
