/*
 * Return hash for byte array with using SHA-1
 */
package steganography;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import steganographyException.SteganographyException;

public class Hash {

    public String getHash(byte[] msg) {

        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA1");
        } catch (NoSuchAlgorithmException ex) {
           new SteganographyException(ex.toString());
        }
        md.reset();
        md.update(msg);
        byte[] digest = md.digest();
        String hexStr = "";
        for (int i = 0; i < digest.length; i++) {
            hexStr += Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1);
        }
        return hexStr;
    }
}
