package co.cask.hydrator.format.zip;

import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.zip.ZipOutputStream;

/**
 * Extends {@link ZipOutputStream} to allow resetting compressor stream,
 * required by {@link org.apache.hadoop.io.compress.CompressionOutputStream} implementation.
 * @author neelesh.nirmal
 */
public class ResetableZipOutputStream extends ZipOutputStream {
    /**
     * Creates a new ZIP output stream.
     *
     * <p>The UTF-8 {@link Charset charset} is used
     * to encode the entry names and comments.
     *
     * @param out the actual output stream
     */
    public ResetableZipOutputStream(OutputStream out) {
        super(out);
    }

    void resetState() {
        def.reset();
    }
}
