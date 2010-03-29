/*
 * Embedding secret shares to image
 */
package steganography;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import steganographyException.EmbeddingException;

public class Embedding {

    public Embedding(String filename) throws EmbeddingException {

        FileInputStream image = null;
        try {
            image = new FileInputStream(filename);
        } catch (FileNotFoundException ex) {
            throw new EmbeddingException("Couldn't open " + filename + " file");
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
            throw new EmbeddingException("Image is broken");
        }

        width = (((int) infohead[7] & 0xff) << 24) | (((int) infohead[6] & 0xff) << 16) | (((int) infohead[5] & 0xff) << 8) | (int) infohead[4] & 0xff;
        height = (((int) infohead[11] & 0xff) << 24) | (((int) infohead[10] & 0xff) << 16) | (((int) infohead[9] & 0xff) << 8) | (int) infohead[8] & 0xff;
        bitcount = (((int) infohead[15] & 0xff) << 8) | (int) infohead[14] & 0xff;
        int sizeimage = (((int) infohead[23] & 0xff) << 24) | (((int) infohead[22] & 0xff) << 16) | (((int) infohead[21] & 0xff) << 8) | (int) infohead[20] & 0xff;
        pad = (sizeimage / height) - width * 3;
    }

    public void writeShares(BigInteger[] args, BigInteger[] values) throws EmbeddingException {
        if (bitcount != 24) {
            throw new EmbeddingException("Not 24-bit format");
        }

        int count = 0;
        for (int i = 0; i < args.length; i++) {
            int current;
            // записывем int - количество байл в args[i]
            int info = args[i].bitCount();
            for (int j = 0; j < 32; j++) {
                current = (rgb[count] & 0xFF);
                current = (current & 254); // зануляем 1 младший бит
                current = (current | (info & 1)); // записываем в текущий цвет новую информацию
                info = info >> 1;
                rgb[count] = (byte) current;
                count++;
            }
            // записываем args[i]
            for (int j = 0; j < args[i].bitCount(); j++) {
                BigInteger arg = args[i];
                current = (rgb[count] & 0xFF);
                current = (current & 254); // зануляем 1 младший бит
                current = (arg.and(BigInteger.ONE)).or(BigInteger.valueOf(current)).intValue(); // записываем в текущий цвет новую информацию
                arg = arg.shiftRight(1);
                rgb[count] = (byte) current;
                count++;
            }
            // записывем int - количество байл в values[i]
            info = values[i].bitCount();
            for (int j = 0; j < 32; j++) {
                current = (rgb[count] & 0xFF);
                current = (current & 254); // зануляем 1 младший бит
                current = (current | (info & 1)); // записываем в текущий цвет новую информацию
                info = info >> 1;
                rgb[count] = (byte) current;
                count++;
            }
            // записываем values[i]
            for (int j = 0; j < values[i].bitCount(); j++) {
                BigInteger arg = values[i];
                current = (rgb[count] & 0xFF);
                current = (current & 254); // зануляем 1 младший бит
                current = (arg.and(BigInteger.ONE)).or(BigInteger.valueOf(current)).intValue(); // записываем в текущий цвет новую информацию
                arg = arg.shiftRight(1);
                rgb[count] = (byte) current;
                count++;
            }
        }
    }

    public void saveToFile(String filename) throws EmbeddingException {
        if (!filename.contains(".bmp")) {
            filename += ".bmp";
        }
        FileOutputStream codeimg = null;
        try {
            codeimg = new FileOutputStream(filename);
        } catch (FileNotFoundException ex) {
            throw new EmbeddingException("Couldn't save to file");
        }
        try {
            codeimg.write(head);
            codeimg.write(infohead);
            codeimg.write(rgb);
            codeimg.close();
        } catch (IOException ex) {
            throw new EmbeddingException("Couldn't save to file");
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
