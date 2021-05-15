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
import be.yildizgames.common.hashing.Formatter;
import be.yildizgames.common.hashing.HashingFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

/**
 * @author Grégory Van den Borre
 */
class FormatterTest {

    @Nested
    class ConvertToHexa {

        @Test
        void md5() {
            var md5Bytes = HashingFactory.MD5.compute(Path.of("src/test/resources/circle.png"));
            Assertions.assertEquals("9bd0016b3e59497aa8f3f839d196dd78", Formatter.convertToHexa(md5Bytes));
        }
    }

    @Nested
    class ConvertToBinary {

        @Test
        void md5() {
            var md5Bytes = HashingFactory.MD5.compute(Path.of("src/test/resources/circle.png"));
            Assertions.assertEquals(md5Bytes, Formatter.convertToBinary("9bd0016b3e59497aa8f3f839d196dd78", Algorithm.MD5));
        }

        @Test
        void crc32() {
            var crcBytes = HashingFactory.CRC32.compute(Path.of("src/test/resources/circle.png"));
            Assertions.assertEquals(crcBytes, Formatter.convertToBinary("df31953e", Algorithm.CRC32));
        }
    }
}
