package co.cask.hydrator.format.zip;

import org.apache.hadoop.conf.Configurable;
import org.apache.hadoop.io.compress.CompressionInputStream;
import org.apache.hadoop.io.compress.CompressionOutputStream;
import org.apache.hadoop.io.compress.DefaultCodec;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipInputStream;

public class ZipCodec extends DefaultCodec implements Configurable {
    private static final String EXTENSION = ".zip";

    @Override
    public CompressionOutputStream createOutputStream(OutputStream out) throws IOException {
        return new ZipCompressionOutputStream(new ResetableZipOutputStream(out));
    }

    @Override
    public CompressionInputStream createInputStream(InputStream in) throws IOException {
        return new ZipCompressionInputStream(new ZipInputStream(in));
    }

    @Override
    public String getDefaultExtension() {
        return EXTENSION;
    }
}
