package net.jmp.demo.apache.tika;

/*
 * (#)TestShaded.java   0.5.0   01/25/2024
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

import java.io.File;

import org.junit.*;

import static org.junit.Assert.assertEquals;

public class TestShaded {
    private Shaded shaded;

    public TestShaded() {
        super();
    }

    @Before
    public void before() {
        this.shaded = new Shaded();
    }

    private void testMimeTypeOfFile(final String fileName, final String expectedMimeType) throws Throwable {
        final var file = new File(fileName);
        final var result = this.shaded.simpleDetect(file);

        assertEquals(expectedMimeType, result);
    }

    @Test
    public void testSimplePdf() throws Throwable {
        this.testMimeTypeOfFile(
                "/Users/Maestro/Documents/Improving_Code_Quality_.pdf",
                "application/pdf"
        );
    }

    @Test
    public void testSimplePdfAsTmp() throws Throwable {
        this.testMimeTypeOfFile(
                "/Users/Maestro/Documents/PDF-Document.tmp",
                "application/pdf"
        );
    }

    @Test
    public void testSimpleWord() throws Throwable {
        this.testMimeTypeOfFile(
                "/Users/Maestro/Documents/Watkins Academy of Music Piano Competition.docx",
                "application/vnd.openxmlformats-officedocument.wordprocessingml.document"
        );
    }

    @Test
    public void testSimpleWordAsTmp() throws Throwable {
        this.testMimeTypeOfFile(
                "/Users/Maestro/Documents/Word-Document.tmp",
                "application/vnd.openxmlformats-officedocument.wordprocessingml.document"
        );
    }

    @Test
    public void testSimpleExcel() throws Throwable {
        this.testMimeTypeOfFile(
                "/Users/Maestro/Documents/Fidelity-CDs.xlsx",
                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
        );
    }

    @Test
    public void testSimpleExcelAsTmp() throws Throwable {
        this.testMimeTypeOfFile(
                "/Users/Maestro/Documents/Excel-Document.tmp",
                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
        );
    }

    @Test
    public void testSimplePowerpoint() throws Throwable {
        this.testMimeTypeOfFile(
                "/Users/Maestro/Documents/Epic-Presentation.pptx",
                "application/vnd.openxmlformats-officedocument.presentationml.presentation"
        );
    }

    @Test
    public void testSimplePowerpointAsTmp() throws Throwable {
        this.testMimeTypeOfFile(
                "/Users/Maestro/Documents/Powerpoint-Document.tmp",
                "application/vnd.openxmlformats-officedocument.presentationml.presentation"
        );
    }

    @Test
    public void testSimpleJpeg() throws Throwable {
        this.testMimeTypeOfFile(
                "/Users/Maestro/Pictures/Ace-Sketch.jpg",
                "image/jpeg"
        );
    }

    @Test
    public void testSimpleJpegAsTmp() throws Throwable {
        this.testMimeTypeOfFile(
                "/Users/Maestro/Pictures/JPEG-Image.tmp",
                "image/jpeg"
        );
    }

    @Test
    public void testSimplePng() throws Throwable {
        this.testMimeTypeOfFile(
                "/Users/Maestro/Documents/pillow-A-Vertical-Flipped.png",
                "image/png"
        );
    }

    @Test
    public void testSimplePngAsTmp() throws Throwable {
        this.testMimeTypeOfFile(
                "/Users/Maestro/Documents/PNG-Image.tmp",
                "image/png"
        );
    }

    @Test
    public void testSimpleTxt() throws Throwable {
        this.testMimeTypeOfFile(
                "/Users/Maestro/Documents/LinkedIn-Greeting.txt",
                "text/plain"
        );
    }

    @Test
    public void testSimpleTxtAsTmp() throws Throwable {
        this.testMimeTypeOfFile(
                "/Users/Maestro/Documents/Text-Document.tmp",
                "text/plain"
        );
    }
}
