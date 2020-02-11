package co.cask.hydrator.format.zip;

import org.apache.hadoop.conf.Configurable;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.compress.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ZipCodec2 implements Configurable, CompressionCodec {
    Logger logger = LoggerFactory.getLogger(ZipCodec2.class);
    private static String EXTENSION = ".zip";
    Configuration conf;

    /**
     * Set the configuration to be used by this object.
     *
     * @param conf
     */
    @Override
    public void setConf(Configuration conf) {
        this.conf = conf;
    }

    /**
     * Return the configuration used by this object.
     */
    @Override
    public Configuration getConf() {
        return conf;
    }

    /**
     * Create a {@link CompressionOutputStream} that will write to the given
     * {@link OutputStream}.
     *
     * @param out the location for the final output stream
     * @return a stream the user can write uncompressed data to have it compressed
     * @throws IOException
     */
    @Override
    public CompressionOutputStream createOutputStream(OutputStream out) throws IOException {
        return new ZipCompressionOutputStream(new ResetableZipOutputStream(out));
    }

    /**
     * Create a {@link CompressionOutputStream} that will write to the given
     * {@link OutputStream} with the given {@link Compressor}.
     *
     * @param out        the location for the final output stream
     * @param compressor compressor to use
     * @return a stream the user can write uncompressed data to have it compressed
     * @throws IOException
     */
    @Override
    public CompressionOutputStream createOutputStream(OutputStream out, Compressor compressor) throws IOException {
        logger.warn("Doing unsupported operation.");
        return createOutputStream(out);
    }

    /**
     * Get the type of {@link Compressor} needed by this {@link CompressionCodec}.
     *
     * @return the type of compressor needed by this codec.
     */
    @Override
    public Class<? extends Compressor> getCompressorType() {
        return null;
    }

    /**
     * Create a new {@link Compressor} for use by this {@link CompressionCodec}.
     *
     * @return a new compressor for use by this codec
     */
    @Override
    public Compressor createCompressor() {
        return null;
    }

    /**
     * Create a {@link CompressionInputStream} that will read from the given
     * input stream.
     *
     * @param in the stream to read compressed bytes from
     * @return a stream to read uncompressed bytes from
     * @throws IOException
     */
    @Override
    public CompressionInputStream createInputStream(InputStream in) throws IOException {
        return null;
    }

    /**
     * Create a {@link CompressionInputStream} that will read from the given
     * {@link InputStream} with the given {@link Decompressor}.
     *
     * @param in           the stream to read compressed bytes from
     * @param decompressor decompressor to use
     * @return a stream to read uncompressed bytes from
     * @throws IOException
     */
    @Override
    public CompressionInputStream createInputStream(InputStream in, Decompressor decompressor) throws IOException {
        return null;
    }

    /**
     * Get the type of {@link Decompressor} needed by this {@link CompressionCodec}.
     *
     * @return the type of decompressor needed by this codec.
     */
    @Override
    public Class<? extends Decompressor> getDecompressorType() {
        return null;
    }

    /**
     * Create a new {@link Decompressor} for use by this {@link CompressionCodec}.
     *
     * @return a new decompressor for use by this codec
     */
    @Override
    public Decompressor createDecompressor() {
        return null;
    }

    /**
     * Get the default filename extension for this kind of compression.
     *
     * @return the extension including the '.'
     */
    @Override
    public String getDefaultExtension() {
        return EXTENSION;
    }
}
