/*
 * Image
 */
package steganography;


import java.io.*;
import java.util.LinkedList;
import steganographyException.SteganographyException;

public class BmpImage {

    public BmpImage(String filename) throws SteganographyException {
        FileInputStream image = null;
        try {
            image = new FileInputStream(filename);
        } catch (FileNotFoundException ex) {
            throw new SteganographyException("Couldn't open " + filename);
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
            throw new SteganographyException("Image is broken " + ex);
        }

        width = (((int) infohead[7] & 0xff) << 24) | (((int) infohead[6] & 0xff) << 16) | (((int) infohead[5] & 0xff) << 8) | (int) infohead[4] & 0xff;
        height = (((int) infohead[11] & 0xff) << 24) | (((int) infohead[10] & 0xff) << 16) | (((int) infohead[9] & 0xff) << 8) | (int) infohead[8] & 0xff;
        bitcount = (((int) infohead[15] & 0xff) << 8) | (int) infohead[14] & 0xff;
        int sizeimage = (((int) infohead[23] & 0xff) << 24) | (((int) infohead[22] & 0xff) << 16) | (((int) infohead[21] & 0xff) << 8) | (int) infohead[20] & 0xff;
        pad = (sizeimage / height) - width * 3;
    }

    public LinkedList<ViewChunk> getViewChunks(int begin, int chunkWidth, int chunkHeight) {
        LinkedList<ViewChunk> list = new LinkedList<ViewChunk>();
        int offset = begin;
        int newLine = 1;
        if (getByteWidth() - offset < chunkWidth) {
            return null;
        }
        while (true) {
            list.add(new ViewChunk(this, offset, chunkWidth, chunkHeight));
            offset += chunkWidth;
            if (begin + getByteWidth() + getByteWidth() * chunkHeight * (newLine - 1) - offset < chunkWidth) {
                offset = (begin + getByteWidth() * chunkHeight * newLine);
                newLine++;
                if (offset + (getByteWidth() * chunkHeight) - 1 > getSize()) {
                    return list;
                }
            }
        }
    }

    public LinkedList<ViewChunk> getViewChunks(int sharesCount) {
        int wn = ((int) java.lang.Math.sqrt(sharesCount));
        int hn;
        if (wn * wn == sharesCount) {
            hn = wn;
        } else {
            hn = (int) (sharesCount / wn);
            if (sharesCount % wn != 0) {
                hn++;
            }
        }
        int chunkWidth = width / wn * 3;
        int chunkHeight = height / hn;
        LinkedList<ViewChunk> list = new LinkedList<ViewChunk>();
        int offset = 0;
        for (int i = 0; i < hn; i++) {
            for (int j = 0; j < wn; j++) {
                list.add(new ViewChunk(this, offset, chunkWidth, chunkHeight));
                offset += chunkWidth;
            }
            offset += (getByteWidth() - chunkWidth * wn);
            offset += getByteWidth() * (chunkHeight - 1);
        }
        return list;
    }

    public byte getByte(int offset) {
        return rgb[offset];
    }

    public void setByte(int offset, byte info) {
        rgb[offset] = info;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getByteWidth() {
        return (width + pad) * 3;
    }

    public int getSize() {
        return rgb.length;
    }

    public byte[] getHead() {
        return head;
    }

    public byte[] getInfohead() {
        return infohead;
    }

    public byte[] getRGB() {
        return rgb;
    }

    public int getPad() {
        return pad;
    }
    private byte head[];
    private byte infohead[];
    private byte rgb[];
    private int width;
    private int height;
    private int bitcount;
    private int pad;
}
