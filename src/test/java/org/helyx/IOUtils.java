package org.helyx;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

public class IOUtils {

    public static InputStream toInputStream(String content) throws UnsupportedEncodingException {
        return new ByteArrayInputStream(content.getBytes("UTF-8"));
    }

}
