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
    }

    public void getShares() {
        int length = 0;
        for (int i = 0; i < 32; i++) {
            int current = (rgb[i] & 0xFF);
            current = (current & 1); // обнуляем всё кроме 1 бита
            current = current << i;
            length = length | current;
        }
        int count = 32;
        BigInteger arg;
        args = new BigInteger[length];
        values = new BigInteger[length];
        int info;

        for (int i = 0; i < length; i++) {
            info = 0;
            for (int j = 0; j < 32; j++) {
                int current = (rgb[count] & 0xFF);
                current = (current & 1); // обнуляем всё кроме 1 бита
                current = current << j;
                info = info | current;
                count++;
            }
            arg = BigInteger.valueOf(0);
            for (int j = 0; j < info; j++) {
                BigInteger current = BigInteger.valueOf(rgb[count] & 0xFF);
                current = current.and(BigInteger.ONE); // обнуляем всё кроме 1 бита
                current = current.shiftLeft(j);
                arg = arg.or(current);
                count++;
            }
            args[i] = arg;
            info = 0;
            for (int j = 0; j < 32; j++) {
                int current = (rgb[count] & 0xFF);
                current = (current & 1); // обнуляем всё кроме 1 бита
                current = current << j;
                info = info | current;
                count++;
            }

            arg = BigInteger.valueOf(0);
            for (int j = 0; j < info; j++) {
                BigInteger current = BigInteger.valueOf(rgb[count] & 0xFF);
                current = current.and(BigInteger.ONE); // обнуляем всё кроме 1 бита
                current = current.shiftLeft(j);
                arg = arg.or(current);
                count++;
            }
            values[i] = arg;
        }
        for (int i=0; i<length; i++)
        {
            System.out.println(args[i].toString());
            System.out.println(values[i].toString());
        }
    }
    private byte head[];
    private byte infohead[];
    private byte rgb[];
    private int width;
    private int height;
    private int bitcount;
    private int pad;
}
