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
import java.security.DigestInputStream;
import java.security.MessageDigest;

/**
 * @author Grégory Van den Borre
 */
public class HashingSha1 implements Hashing {

    public HashingSha1() {
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
        return compute(stream);
    }

    @Override
    public FileHash compute(InputStream stream) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            DigestInputStream dis = new DigestInputStream(stream, md);
            byte[] buffer = new byte[1024];
            int read = dis.read(buffer);
            while (read > -1) {
                read = dis.read(buffer);
            }
            return new FileHash(dis.getMessageDigest().digest(), Algorithm.SHA1);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}
