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

import java.nio.charset.StandardCharsets;

/**
 * @author Grégory Van den Borre
 */
public class Formatter {

    private static final byte[] HEX_ARRAY = "0123456789abcdef".getBytes(StandardCharsets.US_ASCII);

    private Formatter() {
        super();
    }

    public static String convertToHexa(FileHash hash) {
        var b = hash.getBytes();
        if(hash.getAlgorithm() == Algorithm.CRC32) {
            long l = ((long) b[7] << 56)
                    | ((long) b[6] & 0xff) << 48
                    | ((long) b[5] & 0xff) << 40
                    | ((long) b[4] & 0xff) << 32
                    | ((long) b[3] & 0xff) << 24
                    | ((long) b[2] & 0xff) << 16
                    | ((long) b[1] & 0xff) << 8
                    | ((long) b[0] & 0xff);
            return String.format("%08x", l);
        } else {
            byte[] hexChars = new byte[b.length * 2];
            for (int j = 0; j < b.length; j++) {
                int v = b[j] & 0xFF;
                hexChars[j * 2] = HEX_ARRAY[v >>> 4];
                hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
            }
            return new String(hexChars, StandardCharsets.UTF_8);
        }
    }

    public static FileHash convertToBinary(String hexa, Algorithm algorithm) {
        if(algorithm == Algorithm.CRC32) {
            long l = Long.parseLong(hexa,16);
            return new FileHash(new byte[] {
                    (byte) l,
                    (byte) (l >> 8),
                    (byte) (l >> 16),
                    (byte) (l >> 24),
                    (byte) (l >> 32),
                    (byte) (l >> 40),
                    (byte) (l >> 48),
                    (byte) (l >> 56)}, Algorithm.CRC32);
        } else {
            int len = hexa.length();
            byte[] data = new byte[len / 2];
            for (int i = 0; i < len; i += 2) {
                data[i / 2] = (byte) ((Character.digit(hexa.charAt(i), 16) << 4)
                        + Character.digit(hexa.charAt(i + 1), 16));
            }
            return new FileHash(data, algorithm);
        }
    }
}
