/*
 */
package steganography;

public class ViewChunkIterator {

    private ViewChunk chunk;
    private int counter;
    private int width;

    public ViewChunkIterator(ViewChunk chunk) {
        this.chunk = chunk;
        counter = -1;
        width = chunk.getWidth();
    }

    public byte next() {
        counter++;
        return chunk.getByte(counter % width, counter / width);
    }

    public void writeToCurrent(byte info) {
        chunk.setByte(counter % width, counter / width, info);
    }

    public void skip(int count) {
        counter += count;
    }
}
