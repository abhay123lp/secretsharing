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

    public String[] hashMeans;

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
        w = 0;
        h = 0;
        mask = (byte) 'v';
        hash = new Hash();
    }

    public void writeSharesToSubImages(BigInteger[] args, BigInteger[] values) throws SteganographyException {
        int wn = ((int) java.lang.Math.sqrt(args.length));
        int hn;
        if (args.length % wn == 0) {
            hn = wn;
        } else {
            hn = ((int) (args.length / wn) + 1);
        }
        w = width / wn;
        h = height / hn;
        if (!isPortationCorrect(args, values)) {
            throw new SteganographyException("Couldn't write code to this image");
        }
        hashMeans = new String[args.length];
     //   writePortationInformation(w, h);
        int begin = rgb.length - 1;
        bitCount = 1;
        for (int j = 0; j < args.length; j++) {
            hashMeans[j] = hash.getHash(getSubImages(h, w, begin));
            writeShareToSubimage(args[j], values[j], begin);
           if ((begin - w * 3 + 1) % width == 0) {
                begin -= width * (h - 1) * 3 + w * 3;
            } else {
                begin -= w * 3;
            }
        }
        //  for (int i=0; i<hashMeans.length; i++)
        //      System.out.println(hashMeans[i]);
    }

    private void writeShareToSubimage(BigInteger arg, BigInteger value, int begin) {
        count = begin;
        writeMask();
        writeIntToSubimage(w);
        writeIntToSubimage(h);
        writeIntToSubimage(arg.bitLength());
        writeBigIntegerToSubimage(arg);
        writeIntToSubimage(value.bitLength());
        writeBigIntegerToSubimage(value);
    }

    private void writePortationInformation(int w, int h) {
        byte[] hByte = intToByteArray(h);
        byte[] wByte = intToByteArray(w);
        for (int i = 0; i < 4; i++) {
            infohead[24 + i] = hByte[i];
            infohead[28 + i] = wByte[i];
        }
    }

    private byte[] intToByteArray(int value) {
        return new byte[]{
                    (byte) (value >>> 24), (byte) (value >> 16 & 0xff), (byte) (value >> 8 & 0xff), (byte) (value & 0xff)};
    }

    private boolean isPortationCorrect(BigInteger[] args, BigInteger[] values) {
        int i = findMax(args);
        int j = findMax(values);
        int maxLengthArgs = args[i].bitLength() + values[i].bitLength() + 128 + 8;
        int maxLengthValues = args[j].bitLength() + values[j].bitLength() + 128 + 8;
        if ((maxLengthArgs > h * w * 3) || (maxLengthValues > h * w * 3)) {
            return false;
        } else {
            return true;
        }
    }

    private int findMax(BigInteger[] mas) {
        int max = 0;
        for (int i = 1; i < mas.length; i++) {
            if (mas[max].bitLength() < mas[i].bitLength()) {
                max = i;
            }
        }
        return max;
    }

    private void writeMask() {
        byte m = mask;
        int current;
        bitCount = 1;
        for (int j = 0; j < 8; j++) {
            current = (rgb[count] & 0xFF);
            current = (current & 254); // зануляем 1 младший бит
            current = (current | (m & 1)); // записываем в текущий цвет новую информацию
            m = (byte) (m >> 1);
            rgb[count] = (byte) current;
            if (bitCount == w * 3) {
                count = count - width * 3 + w * 3 - 1;
                bitCount = 1;
            } else {
                count--;
                bitCount++;
            }
        }
    }

    private void writeIntToSubimage(int info) {
        int current;
        for (int j = 0; j < 32; j++) {
            current = (rgb[count] & 0xFF);
            current = (current & 254); // зануляем 1 младший бит
            current = (current | (info & 1)); // записываем в текущий цвет новую информацию
            info = info >> 1;
            rgb[count] = (byte) current;
            if (bitCount == w * 3) {
                count = count - width * 3 + w * 3 - 1;
                bitCount = 1;
            } else {
                count--;
                bitCount++;
            }
        }
    }

    private void writeBigIntegerToSubimage(BigInteger arg) {
        int length = arg.bitLength();
        int current;
        BigInteger bigInfo = arg;
        for (int j = 0; j < length; j++) {
            current = (rgb[count] & 0xFF);
            current = (current & 254); // зануляем 1 младший бит
            current = (bigInfo.and(BigInteger.ONE)).or(BigInteger.valueOf(current)).intValue(); // записываем в текущий цвет новую информацию
            bigInfo = bigInfo.shiftRight(1);
            rgb[count] = (byte) current;
            if (bitCount == w * 3) {
                count = count - width * 3 + w * 3 - 1;
                bitCount = 1;
            } else {
                count--;
                bitCount++;
            }
        }
    }

    public void writeShares(BigInteger[] args, BigInteger[] values) throws SteganographyException {
        if (bitcount != 24) {
            throw new SteganographyException("Not 24-bit format");
        }
        writeInt(args.length, count, rgb);
        count += 32;
        for (int i = 0; i < args.length; i++) {
            writeInt(args[i].bitLength(), count, rgb);
            count += 32;
            writeBigInteger(args[i], count, rgb);
            count += args[i].bitLength();
            writeInt(values[i].bitLength(), count, rgb);
            count += 32;
            writeBigInteger(values[i], count, rgb);
            count += values[i].bitLength();
        }
    }

    private void writeBigInteger(BigInteger info, int index, byte[] share) {
        int current;
        int length = info.bitLength();
        int k = index;
        for (int i = 0; i < length; i++) {
            current = (share[k] & 0xFF);
            current = (current & 254); // зануляем 1 младший бит
            current = (info.and(BigInteger.ONE)).or(BigInteger.valueOf(current)).intValue(); // записываем в текущий цвет новую информацию
            info = info.shiftRight(1);
            share[k] = (byte) current;
            k++;
        }
    }

    private void writeInt(int info, int index, byte[] share) {
        int current;
        int k = index;
        for (int j = 0; j
                < 32; j++) {
            current = (share[k] & 0xFF);
            current = (current & 254); // зануляем 1 младший бит
            current = (current | (info & 1)); // записываем в текущий цвет новую информацию
            info = info >> 1;
            share[k] = (byte) current;
            k++;
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

    private byte[] getSubImages(int h, int w, int index) {
        byte[] subimage = new byte[h * w * 3];
        int k = index;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w * 3; j++) {
                subimage[i] = rgb[k - j];
            }
            k -= width * 3;
        }
        return subimage;
    }
    private Hash hash;
    private int bitCount;
    private byte mask;
    private int w;
    private int h;
    private int count;
    private byte head[];
    private byte infohead[];
    private byte rgb[];
    private int width;
    private int height;
    private int bitcount;
    private int pad;
}
