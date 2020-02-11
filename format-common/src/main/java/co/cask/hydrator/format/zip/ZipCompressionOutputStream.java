package co.cask.hydrator.format.zip;

import org.apache.hadoop.io.compress.CompressionOutputStream;

import java.io.IOException;
import java.util.zip.ZipEntry;

/**
 * Writes given data into ZIP archive by placing all data in one entry with default naming.
 * @author neelesh.nirmal
 */
public class ZipCompressionOutputStream extends CompressionOutputStream {
    private static final String DEFAULT_ENTRY_NAME = "entry.out";

    ZipCompressionOutputStream(ResetableZipOutputStream out) throws IOException {
        super(out);
        ZipEntry zipEntry = new ZipEntry(DEFAULT_ENTRY_NAME);
        out.putNextEntry(zipEntry);
    }
    /**
     * Writes the specified byte to this output stream. The general
     * contract for <code>write</code> is that one byte is written
     * to the output stream. The byte to be written is the eight
     * low-order bits of the argument <code>b</code>. The 24
     * high-order bits of <code>b</code> are ignored.
     * <p>
     * Subclasses of <code>OutputStream</code> must provide an
     * implementation for this method.
     *
     * @param b the <code>byte</code>.
     * @throws IOException if an I/O error occurs. In particular,
     *                     an <code>IOException</code> may be thrown if the
     *                     output stream has been closed.
     */
    @Override
    public void write(int b) throws IOException {
        out.write(b);
    }

    /**
     * Write compressed bytes to the stream.
     * Made abstract to prevent leakage to underlying stream.
     *
     * @param b
     * @param off
     * @param len
     */
    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        out.write(b, off, len);
    }

    /**
     * Finishes writing compressed data to the output stream
     * without closing the underlying stream.
     */
    @Override
    public void finish() throws IOException {
        ((ResetableZipOutputStream) out).closeEntry();
    }

    /**
     * Reset the compression to the initial state.
     * Does not reset the underlying stream.
     */
    @Override
    public void resetState() throws IOException {
        ((ResetableZipOutputStream) out).resetState();
    }
}
