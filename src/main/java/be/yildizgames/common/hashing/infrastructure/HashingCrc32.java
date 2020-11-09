/*
 * MIT License
 *
 * Copyright (c) 2019 Grégory Van den Borre
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package be.yildizgames.common.hashing.infrastructure;

import be.yildizgames.common.hashing.Hashing;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.CRC32;

/**
 * @author Grégory Van den Borre
 */
public class HashingCrc32 implements Hashing {

    public HashingCrc32() {
        super();
    }

    @Override
    public String compute(Path path) {
        try {
            return compute(Files.newInputStream(path));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public String compute(InputStream stream) {
        try {
            final CRC32 crc = new CRC32();
            byte[] targetArray = new byte[stream.available()];
            stream.read(targetArray);
            crc.update(targetArray);
            return String.format("%08x", crc.getValue());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}