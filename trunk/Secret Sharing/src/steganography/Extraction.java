/*
 * Extraction secter shares from image
 */
package steganography;

import java.io.*;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.ListIterator;
import steganographyException.SteganographyException;

public class Extraction {

    public BigInteger args[];
    public BigInteger values[];

    public Extraction(String filename, String hashFilename) throws SteganographyException {
        args = null;
        values = null;
        image = new BmpImage(filename);
        mask = (byte) 'v';
        readHashfile(hashFilename);
    }

    void readHashfile(String hashFilename) throws SteganographyException {
        FileReader hashFile;
        BufferedReader bufFile;
        LinkedList<String> list = new LinkedList<String>();
        try {
            hashFile = new FileReader(hashFilename);
            bufFile = new BufferedReader(hashFile);
        } catch (FileNotFoundException ex) {
            throw new SteganographyException("Couldn't open " + hashFilename + " file");
        }
        String read;
        try {
            while ((read = bufFile.readLine()) != null) {
                list.addLast(read);
            }
        } catch (IOException ex) {
            throw new SteganographyException(ex.getMessage());
        }
        hashMeans = new String[list.size()];
        hashMeans = list.toArray(new String[0]);
    }

    public void getSharesFromSubimages(int threshold) throws SteganographyException {
        args = new BigInteger[threshold];
        values = new BigInteger[threshold];
        int begin = 0;
        int count = 0;
        while (begin < image.getSize() - minShareLength) {
            LinkedList<ViewChunk> imageChunk = image.getViewChunks(begin, image.getByteWidth(), image.getHeight());
            ViewChunk firstChunk = imageChunk.getFirst();
            ViewChunkIterator chunkIter = firstChunk.getViewChunkIterator();
            if (mask == readMask(chunkIter)) {
                int w = readIntFromSubimage(chunkIter);
                int h = readIntFromSubimage(chunkIter);
                if ((h <= 0) || (w <= 0) || (h > image.getHeight()) || (w > image.getByteWidth()) || (h * w * threshold > image.getSize() - 1)) {
                    begin++;
                    continue;
                }
                LinkedList<ViewChunk> list = image.getViewChunks(begin, w, h);
                ListIterator<ViewChunk> iter = list.listIterator();
                while (iter.hasNext()) {
                    ViewChunk chunk = iter.next();
                    if (isEqualHash(chunk.getHashMean())) {
                        readShareFromSubimage(chunk.getViewChunkIterator(), count);
                        count++;
                        if ((count == threshold)) {
                            return;
                        }
                    }
                }
                begin++;
            }
        }
        throw new SteganographyException("Restore " + count + " shares!");
    }

    private void readShareFromSubimage(ViewChunkIterator chunk, int count) {
        chunk.skip(8 + 32 * 2);
        int length = readIntFromSubimage(chunk);
        args[count] = readBigIntegerFromSubimage(chunk, length);
        length = readIntFromSubimage(chunk);
        values[count] = readBigIntegerFromSubimage(chunk, length);
    }

    private byte readMask(ViewChunkIterator chunk) {
        byte m = 0;
        int current;
        for (int j = 0; j < 8; j++) {
            current = (chunk.next() & 0xFF);
            current = (current & 1); // обнуляем всё кроме 1 бита
            current = current << j;
            m = (byte) (m | current);
        }
        return m;
    }

    private int readIntFromSubimage(ViewChunkIterator chunk) {
        int info = 0;
        int current = 0;
        for (int j = 0; j < 32; j++) {
            current = (chunk.next() & 0xFF);
            current = (current & 1); // обнуляем всё кроме 1 бита
            current = current << j;
            info = info | current;
        }
        return info;
    }

    private BigInteger readBigIntegerFromSubimage(ViewChunkIterator chunk, int length) {
        BigInteger bigInfo = BigInteger.valueOf(0);
        for (int j = 0; j < length; j++) {
            BigInteger bigCurrent = BigInteger.valueOf(chunk.next() & 0xFF);
            bigCurrent = bigCurrent.and(BigInteger.ONE); // обнуляем всё кроме 1 бита
            bigCurrent = bigCurrent.shiftLeft(j);
            bigInfo = bigInfo.or(bigCurrent);
        }
        return bigInfo;
    }

    private boolean isEqualHash(String hash) {
        for (int i = 0; i < hashMeans.length; i++) {
            if (hash.equals(hashMeans[i])) {
                return true;
            }
        }
        return false;
    }
    private BmpImage image;
    private int minShareLength = 136;
    private String[] hashMeans;
    private byte mask;
}
