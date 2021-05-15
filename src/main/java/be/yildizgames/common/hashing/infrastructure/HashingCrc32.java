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

import be.yildizgames.common.hashing.Algorithm;
import be.yildizgames.common.hashing.FileHash;
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
    public FileHash compute(Path path) {
        try {
            return compute(Files.newInputStream(path));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public FileHash compute(InputStream stream, int size) {
        var l =  computeLong(stream, size);
        return new FileHash(new byte[] {
                    (byte) l,
                    (byte) (l >> 8),
                    (byte) (l >> 16),
                    (byte) (l >> 24),
                    (byte) (l >> 32),
                    (byte) (l >> 40),
                    (byte) (l >> 48),
                    (byte) (l >> 56)}, Algorithm.CRC32);
    }

    @Override
    public FileHash compute(InputStream stream) {
        try {
            return compute(stream, stream.available());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private long computeLong(InputStream stream) {
        try {
            return computeLong(stream, stream.available());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private long computeLong(InputStream stream, int size) {
        try {
            final CRC32 crc = new CRC32();
            int read = 0;
            byte[] bytes = new byte[size];
            byte[] buffer = new byte[1024];
            while(read != size) {
                int currentRead = stream.read(buffer);
                System.arraycopy(buffer, 0, bytes, read, currentRead);
                read += currentRead;
            }
            crc.update(bytes);
            return crc.getValue();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
