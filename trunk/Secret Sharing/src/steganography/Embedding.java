/*
 * Embedding secret shares to image
 */
package steganography;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import steganographyException.SteganographyException;

public class Embedding {

    public Embedding(String filename) throws SteganographyException {

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

    public void writeShares(BigInteger[] args, BigInteger[] values) throws SteganographyException {
        if (bitcount != 24) {
            throw new SteganographyException("Not 24-bit format");
        }
        writeInt(args.length);
        for (int i = 0; i < args.length; i++) {
            writeInt(args[i].bitLength());
            writeBigInteger(args[i]);
            writeInt(values[i].bitLength());
            writeBigInteger(values[i]);
        }
    }

    private void writeBigInteger(BigInteger info) {
        int current;
        int length = info.bitLength();
        for (int j = 0; j < length; j++) {
            current = (rgb[count] & 0xFF);
            current = (current & 254); // зануляем 1 младший бит
            current = (info.and(BigInteger.ONE)).or(BigInteger.valueOf(current)).intValue(); // записываем в текущий цвет новую информацию
            info = info.shiftRight(1);
            rgb[count] = (byte) current;
            count++;
        }
    }

    private void writeInt(int info) {
        int current;
        for (int j = 0; j < 32; j++) {
            current = (rgb[count] & 0xFF);
            current = (current & 254); // зануляем 1 младший бит
            current = (current | (info & 1)); // записываем в текущий цвет новую информацию
            info = info >> 1;
            rgb[count] = (byte) current;
            count++;
        }
    }

    public void saveToFile(String filename) throws SteganographyException {
        if (!filename.contains(".bmp")) {
            filename += ".bmp";
        }
        FileOutputStream codeimg = null;
        try {
            codeimg = new FileOutputStream(filename);
        } catch (FileNotFoundException ex) {
            throw new SteganographyException("Couldn't save to file");
        }
        try {
            codeimg.write(head);
            codeimg.write(infohead);
            codeimg.write(rgb);
            codeimg.close();
        } catch (IOException ex) {
            throw new SteganographyException("Couldn't save to file");
        }
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
