/*
 * MIT License
 *
 * Copyright (c) 2017 Inova IT
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package si.inova.neatle;

import java.io.IOException;

/**
 * Created by tomazs on 9/27/2016.
 */
public class StringSource implements InputSource {

    private final String str;

    private byte[] buffer = null;
    private int offset = 0;

    public StringSource() {
        this.str = null;
    }

    public StringSource(String str) {
        this.str = str;
    }

    protected String resolveString() {
        return this.str;
    }

    @Override
    public final void close() throws IOException {
    }

    @Override
    public final void open() throws IOException {
        String string = resolveString();
        if (string == null) {
            buffer = null;
            return;
        }
        buffer = string.getBytes("UTF8");
        offset = 0;
    }

    @Override
    public final byte[] nextChunk() throws IOException {
        if (buffer == null || offset >= buffer.length) {
            return null;
        }
        int remaining = buffer.length - offset;
        int chunkSize = Math.min(20, remaining);

        byte[] ret = new byte[chunkSize];
        System.arraycopy(buffer, offset, ret, 0, chunkSize);

        offset += chunkSize;

        return ret;
    }
}
