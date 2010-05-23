/*
 * Embedding secret shares to image
 */
package steganography;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.ListIterator;
import steganographyException.SteganographyException;

public class Embedding {

    public BmpImage image;

    public Embedding(String filename) throws SteganographyException {
        image = new BmpImage(filename);
        mask = (byte) 'v';
    }

    public void writeSharesToSubImages(BigInteger[] args, BigInteger[] values) throws SteganographyException {

        /*  if (!isPortationCorrect(args, values)) {
        throw new SteganographyException("Couldn't write code to this image");
        }*/
        list = image.getViewChunks(args.length);
        hashMeans = new String[args.length];
        ListIterator<ViewChunk> iter = list.listIterator();
        int i = 0;
        ViewChunk curChunk;
        while (iter.hasNext()) {
            curChunk = iter.next();
            writeShareToChunk(curChunk.getViewChunkIterator(), args[i], values[i], curChunk.getWidth(), curChunk.getHeight());
            hashMeans[i] = curChunk.getHashMean();
            if (i == args.length - 1 ) {
                i = 0;
            } else {
                i++;
            }
        }
    }  

    private void writeShareToChunk(ViewChunkIterator chunk, BigInteger arg, BigInteger value, int chunkWidth, int chunkHeight) {
        writeMask(chunk);
        writeIntToSubimage(chunk, chunkWidth);
        writeIntToSubimage(chunk, chunkHeight);
        writeIntToSubimage(chunk, arg.bitLength());
        writeBigIntegerToSubimage(chunk, arg);
        writeIntToSubimage(chunk, value.bitLength());
        writeBigIntegerToSubimage(chunk, value);
    }

    private void writeMask(ViewChunkIterator chunk) {
        byte m = mask;
        int current;
        for (int j = 0; j < 8; j++) {
            current = (chunk.next() & 0xFF);
            current = (current & 254); // зануляем 1 младший бит
            current = (current | (m & 1)); // записываем в текущий цвет новую информацию
            m = (byte) (m >> 1);
            chunk.writeToCurrent((byte) current);
        }
    }

    private void writeIntToSubimage(ViewChunkIterator chunk, int info) {
        int current;
        for (int j = 0; j < 32; j++) {
            current = (chunk.next() & 0xFF);
            current = (current & 254); // зануляем 1 младший бит
            current = (current | (info & 1)); // записываем в текущий цвет новую информацию
            info = info >> 1;
            chunk.writeToCurrent((byte) current);
        }
    }

    private void writeBigIntegerToSubimage(ViewChunkIterator chunk, BigInteger arg) {
        int length = arg.bitLength();
        int current;
        BigInteger bigInfo = arg;
        for (int j = 0; j < length; j++) {
            current = (chunk.next() & 0xFF);
            current = (current & 254); // зануляем 1 младший бит
            current = (bigInfo.and(BigInteger.ONE)).or(BigInteger.valueOf(current)).intValue(); // записываем в текущий цвет новую информацию
            bigInfo = bigInfo.shiftRight(1);
            chunk.writeToCurrent((byte) current);
        }
    }

    public void writeFileWithHash(String filename) throws SteganographyException {

        try {
            FileWriter hashFile = null;
            hashFile = new FileWriter(filename);
            BufferedWriter buf = new BufferedWriter(hashFile);
            for (int i = 0; i < hashMeans.length; i++) {
                buf.write(hashMeans[i]);
                buf.newLine();
            }
            buf.close();
            hashFile.close();
        } catch (IOException ex) {
            throw new SteganographyException(ex.getMessage());
        }

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

    private LinkedList<ViewChunk> list;
    private String[] hashMeans;
    private byte mask;
    private int w;
    private int h;
}
