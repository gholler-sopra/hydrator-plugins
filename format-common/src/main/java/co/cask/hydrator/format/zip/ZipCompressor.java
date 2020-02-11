package co.cask.hydrator.format.zip;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.compress.Compressor;

import java.io.IOException;

public class ZipCompressor implements Compressor {
    /**
     * Sets input data for compression.
     * This should be called whenever #needsInput() returns
     * <code>true</code> indicating that more input data is required.
     *
     * @param b   Input data
     * @param off Start offset
     * @param len Length
     */
    @Override
    public void setInput(byte[] b, int off, int len) {

    }

    /**
     * Returns true if the input data buffer is empty and
     * #setInput() should be called to provide more input.
     *
     * @return <code>true</code> if the input data buffer is empty and
     * #setInput() should be called in order to provide more input.
     */
    @Override
    public boolean needsInput() {
        return false;
    }

    /**
     * Sets preset dictionary for compression. A preset dictionary
     * is used when the history buffer can be predetermined.
     *
     * @param b   Dictionary data bytes
     * @param off Start offset
     * @param len Length
     */
    @Override
    public void setDictionary(byte[] b, int off, int len) {
        throw new UnsupportedOperationException("Operation not supported.");
    }

    /**
     * Return number of uncompressed bytes input so far.
     */
    @Override
    public long getBytesRead() {
        return 0;
    }

    /**
     * Return number of compressed bytes output so far.
     */
    @Override
    public long getBytesWritten() {
        return 0;
    }

    /**
     * When called, indicates that compression should end
     * with the current contents of the input buffer.
     */
    @Override
    public void finish() {
        throw new UnsupportedOperationException("Operation not supported.");
    }

    /**
     * Returns true if the end of the compressed
     * data output stream has been reached.
     *
     * @return <code>true</code> if the end of the compressed
     * data output stream has been reached.
     */
    @Override
    public boolean finished() {
        return false;
    }

    /**
     * Fills specified buffer with compressed data. Returns actual number
     * of bytes of compressed data. A return value of 0 indicates that
     * needsInput() should be called in order to determine if more input
     * data is required.
     *
     * @param b   Buffer for the compressed data
     * @param off Start offset of the data
     * @param len Size of the buffer
     * @return The actual number of bytes of compressed data.
     */
    @Override
    public int compress(byte[] b, int off, int len) throws IOException {
        return 0;
    }

    /**
     * Resets compressor so that a new set of input data can be processed.
     */
    @Override
    public void reset() {
        throw new UnsupportedOperationException("Operation not supported.");
    }

    /**
     * Closes the compressor and discards any unprocessed input.
     */
    @Override
    public void end() {
        throw new UnsupportedOperationException("Operation not supported.");
    }

    /**
     * Prepare the compressor to be used in a new stream with settings defined in
     * the given Configuration
     *
     * @param conf Configuration from which new setting are fetched
     */
    @Override
    public void reinit(Configuration conf) {
        throw new UnsupportedOperationException("Operation not supported.");
    }
}
