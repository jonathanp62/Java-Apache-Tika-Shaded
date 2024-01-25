package net.jmp.demo.apache.tika;

/*
 * (#)TestFileExtensions.java   0.5.0   01/25/2024
 *
 * @author    Jonathan Parker
 * @version   0.5.0
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
    private Unshaded unshaded;
    private Shaded shaded;

    public TestFileExtensions() {
        super();
    }

    @Before
    public void before () {
        this.unshaded = new Unshaded();
        this.shaded = new Shaded();
    }

    @Test
    public void testUnshadedFileExtensions() throws Throwable {
        final var resultPdf = this.unshaded.getExtensionByMimeType("application/pdf");
        final var resultWord = this.unshaded.getExtensionByMimeType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        final var resultExcel = this.unshaded.getExtensionByMimeType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        final var resultPowerpoint = this.unshaded.getExtensionByMimeType("application/vnd.openxmlformats-officedocument.presentationml.presentation");
        final var resultJpeg = this.unshaded.getExtensionByMimeType("image/jpeg");
        final var resultPng = this.unshaded.getExtensionByMimeType("image/png");
        final var resultTxt = this.unshaded.getExtensionByMimeType("text/plain");

        assertEquals(".pdf", resultPdf);
        assertEquals(".docx", resultWord);
        assertEquals(".xlsx", resultExcel);
        assertEquals(".pptx", resultPowerpoint);
        assertEquals(".jpg", resultJpeg);
        assertEquals(".png", resultPng);
        assertEquals(".txt", resultTxt);
    }

    @Test
    public void testShadedFileExtensions() throws Throwable {
        final var resultPdf = this.shaded.getExtensionByMimeType("application/pdf");
        final var resultWord = this.shaded.getExtensionByMimeType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        final var resultExcel = this.shaded.getExtensionByMimeType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        final var resultPowerpoint = this.shaded.getExtensionByMimeType("application/vnd.openxmlformats-officedocument.presentationml.presentation");
        final var resultJpeg = this.shaded.getExtensionByMimeType("image/jpeg");
        final var resultPng = this.shaded.getExtensionByMimeType("image/png");
        final var resultTxt = this.shaded.getExtensionByMimeType("text/plain");

        assertEquals(".pdf", resultPdf);
        assertEquals(".docx", resultWord);
        assertEquals(".xlsx", resultExcel);
        assertEquals(".pptx", resultPowerpoint);
        assertEquals(".jpg", resultJpeg);
        assertEquals(".png", resultPng);
        assertEquals(".txt", resultTxt);
    }
}
