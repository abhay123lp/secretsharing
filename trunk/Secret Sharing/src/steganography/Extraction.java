/*
 * Extraction secter shares from image
 */
package steganography;

import java.io.*;
import java.math.BigInteger;
import steganographyException.SteganographyException;

public class Extraction {

    public BigInteger args[];
    public BigInteger values[];

    public Extraction(String filename) throws SteganographyException {
        args = null;
        values = null;
        FileInputStream image = null;
        try {
            image = new FileInputStream(filename);
        } catch (FileNotFoundException ex) {
            throw new SteganographyException("Couldn't open " + filename + " file");
        }
        head = new byte[14];
        infohead = new byte[40];
        try {
            image.read(head, 0, 14);
            image.read(infohead, 0, 40);
            int size = image.available();
            if (size > 0) {
                rgb = new byte[size];
                image.read(rgb, 0, size);
            }
        } catch (IOException ex) {
            throw new SteganographyException("Image is broken");
        }

        width = (((int) infohead[7] & 0xff) << 24) | (((int) infohead[6] & 0xff) << 16) | (((int) infohead[5] & 0xff) << 8) | (int) infohead[4] & 0xff;
        height = (((int) infohead[11] & 0xff) << 24) | (((int) infohead[10] & 0xff) << 16) | (((int) infohead[9] & 0xff) << 8) | (int) infohead[8] & 0xff;
        bitcount = (((int) infohead[15] & 0xff) << 8) | (int) infohead[14] & 0xff;
        int sizeimage = (((int) infohead[23] & 0xff) << 24) | (((int) infohead[22] & 0xff) << 16) | (((int) infohead[21] & 0xff) << 8) | (int) infohead[20] & 0xff;
        pad = (sizeimage / height) - width * 3;
        count = 0;
    }

    public void getShares() {
        int length = readInt();
        args = new BigInteger[length];
        values = new BigInteger[length];
        for (int i = 0; i < length; i++) {
            args[i] = readBigInteger(readInt());
            values[i] = readBigInteger(readInt());
        }
        for (int i = 0; i < length; i++) {
            System.out.println(args[i].toString());
            System.out.println(values[i].toString());
        }
    }

    private BigInteger readBigInteger(int length) {
        BigInteger info = BigInteger.valueOf(0);
        for (int j = 0; j < length; j++) {
            BigInteger current = BigInteger.valueOf(rgb[count] & 0xFF);
            current = current.and(BigInteger.ONE); // обнуляем всё кроме 1 бита
            current = current.shiftLeft(j);
            info = info.or(current);
            count++;
        }
        return info;
    }

    private int readInt() {
        int info = 0;
        for (int i = 0; i < 32; i++) {
            int current = (rgb[count] & 0xFF);
            current = (current & 1); // обнуляем всё кроме 1 бита
            current = current << i;
            info = info | current;
            count++;
        }
        return info;
    }
    private int count;
    private byte head[];
    private byte infohead[];
    private byte rgb[];
    private int width;
    private int height;
    private int bitcount;
    private int pad;
}
