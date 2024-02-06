package net.jmp.demo.apache.tika;

/*
 * (#)TestFileExtensions.java   0.10.0  02/06/2024
 * (#)TestFileExtensions.java   0.8.0   01/30/2024
 * (#)TestFileExtensions.java   0.5.0   01/25/2024
 *
 * @author    Jonathan Parker
 * @version   0.10.0
 * @since     0.5.0
 *
 * MIT License
 *
 * Copyright (c) 2024 Jonathan M. Parker
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

import org.junit.*;

import static org.junit.Assert.assertEquals;

public class TestFileExtensions {
    private Shaded shaded;

    public TestFileExtensions() {
        super();
    }

    @Before
    public void before () {
        this.shaded = new Shaded();
    }

    @Test
    public void testShadedFileExtensions() throws Throwable {
        final var method = Shaded.class.getDeclaredMethod("getExtensionByMimeType", String.class);

        method.setAccessible(true);

        final var resultPdf = method.invoke(this.shaded, "application/pdf");
        final var resultWord = method.invoke(this.shaded, "application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        final var resultExcel = method.invoke(this.shaded, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        final var resultPowerpoint = method.invoke(this.shaded, "application/vnd.openxmlformats-officedocument.presentationml.presentation");
        final var resultJpeg = method.invoke(this.shaded, "image/jpeg");
        final var resultPng = method.invoke(this.shaded, "image/png");
        final var resultTxt = method.invoke(this.shaded, "text/plain");
        final var resultZip = method.invoke(this.shaded, "application/zip");
        final var resultRar1 = method.invoke(this.shaded, "application/x-rar-compressed; version=4");
        final var resultRar2 = method.invoke(this.shaded, "application/x-rar-compressed; version=5");

        assertEquals(".pdf", resultPdf);
        assertEquals(".docx", resultWord);
        assertEquals(".xlsx", resultExcel);
        assertEquals(".pptx", resultPowerpoint);
        assertEquals(".jpg", resultJpeg);
        assertEquals(".png", resultPng);
        assertEquals(".txt", resultTxt);
        assertEquals(".zip", resultZip);
        assertEquals(".rar", resultRar1);
        assertEquals(".rar", resultRar2);
    }
}
