/*
 * 
 */
package steganography;

public class ViewChunk {

    private BmpImage image;
    private int offset;
    private int width;
    private int height;
    private Hash hash;

    public ViewChunk(BmpImage im, int offset, int width, int height) {
        image = im;
        this.height = height;
        this.width = width;
        this.offset = offset;
        hash = new Hash();
    }

    public ViewChunkIterator getViewChunkIterator() {
        return new ViewChunkIterator(this);
    }

    public byte getByte(int x, int y) {
        return image.getByte(offset + y * image.getByteWidth() + x);
    }

    public void setByte(int x, int y, byte info) {
        image.setByte(offset + y * image.getByteWidth() + x, info);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getHashMean() {
        return hash.getHash(getByteMassive());
    }

    private byte[] getByteMassive() {
        byte[] byteMassive = new byte[height * width];
        int k = offset;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                byteMassive[i * width + j] = image.getByte(k + j);
            }
            k += image.getByteWidth();
        }
        return byteMassive;
    }
}
