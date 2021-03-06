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

package be.yildizgames.common.hashing;

import be.yildizgames.common.hashing.infrastructure.HashingCrc32;
import be.yildizgames.common.hashing.infrastructure.HashingMd5;
import be.yildizgames.common.hashing.infrastructure.HashingSha1;

/**
 * @author Grégory Van den Borre
 */
public class HashingFactory {

    private static final Hashing MD5 = new HashingMd5();

    private static final Hashing SHA1 = new HashingSha1();

    private static final Hashing CRC32 = new HashingCrc32();

    private HashingFactory() {
        super();
    }

    public static Hashing get(Algorithm algorithm) {
        if(algorithm == Algorithm.MD5) {
            return MD5;
        } else if (algorithm == Algorithm.SHA1) {
            return SHA1;
        } else if (algorithm == Algorithm.CRC32) {
            return CRC32;
        }
        return MD5;
    }
}
