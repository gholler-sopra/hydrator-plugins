package co.cask.hydrator.format.zip;

import org.apache.hadoop.io.compress.CompressionInputStream;

import java.io.IOException;
import java.util.zip.ZipInputStream;

/**
 * Reads only first entry from {@link java.util.zip.ZipInputStream},
 * other entries if present will be ignored.
 * @author neelesh.nirmal 
 */
public class ZipCompressionInputStream extends CompressionInputStream {

    ZipCompressionInputStream(ZipInputStream in) throws IOException {
        super(in);
        // Positions stream at the beginning of the first entry data
        in.getNextEntry();
    }

    /**
     * Reads the next byte of data from the input stream. The value byte is
     * returned as an <code>int</code> in the range <code>0</code> to
     * <code>255</code>. If no byte is available because the end of the stream
     * has been reached, the value <code>-1</code> is returned. This method
     * blocks until input data is available, the end of the stream is detected,
     * or an exception is thrown.
     *
     * <p> A subclass must provide an implementation of this method.
     *
     * @return the next byte of data, or <code>-1</code> if the end of the
     * stream is reached.
     * @throws IOException if an I/O error occurs.
     */
    @Override
    public int read() throws IOException {
        return in.read();
    }

    /**
     * Read bytes from the stream.
     * Made abstract to prevent leakage to underlying stream.
     *
     * @param b
     * @param off
     * @param len
     */
    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        return in.read(b, off, len);
    }

    /**
     * Reset the decompressor to its initial state and discard any buffered data,
     * as the underlying stream may have been repositioned.
     */
    @Override
    public void resetState() throws IOException {
        in.reset();
    }

    /**
     * Closes this input stream and releases any system resources associated
     * with the stream.
     *
     * <p> The <code>close</code> method of <code>InputStream</code> does
     * nothing.
     *
     * @exception  IOException  if an I/O error occurs.
     */
    @Override
    public void close() throws IOException {
        try {
            ((ZipInputStream) in).closeEntry();
        } finally {
            super.close();
        }
    }
}
