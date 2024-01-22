package net.jmp.demo.apache.tika;

/*
 * (#)Main.java 0.1.0   01/22/2024
 *
 * @author    Jonathan Parker
 * @version   0.1.0
 * @since     0.1.0
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

import java.io.FileInputStream;

import java.nio.file.Paths;

import java.util.List;

import org.apache.tika.Tika;

import org.apache.tika.config.TikaConfig;

import org.apache.tika.io.TikaInputStream;

import org.apache.tika.metadata.Metadata;

import org.apache.tika.mime.MimeTypes;
import org.apache.tika.mime.MimeTypeException;

import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;

import org.apache.tika.sax.BodyContentHandler;

import org.slf4j.LoggerFactory;

import org.slf4j.ext.XLogger;

public final class Main {
    private final XLogger logger = new XLogger(LoggerFactory.getLogger(this.getClass().getName()));

    private Main() {
        super();
    }

    private void run() {
        this.logger.entry();

        final var listOfFiles = List.of(
                new File("/Users/Maestro/Documents/Improving_Code_Quality_.pdf"),
                new File("/Users/Maestro/Documents/Watkins Academy of Music Piano Competition.docx"),
                new File("/Users/Maestro/Documents/pillow-A-Vertical-Flipped.png"),
                new File("/Users/Maestro/Pictures/Ace-Sketch.jpg")
        );

        this.defaultDetecting(listOfFiles);
        this.facade(listOfFiles);
        this.parsing(listOfFiles);
        this.simple(listOfFiles);

        this.logger.exit();
    }

    private void defaultDetecting(final List<File> files) {
        this.logger.entry(files);

        // For the Word document the content type is application/vnd.openxmlformats-officedocument.wordprocessingml.document

        try {
            final var tika = new TikaConfig();

            for (final var file : files) {
                final var metadata = new Metadata();
                final var mediaType = tika
                        .getDetector()
                        .detect(TikaInputStream.get(Paths.get(file.toURI()), metadata), metadata);

                if (mediaType != null) {
                    final var mimeType = mediaType.toString();

                    if (mimeType != null && !mimeType.isBlank() && this.logger.isInfoEnabled()) {
                        this.logger.info("{}: {}: {}",
                                file.getName(),
                                mimeType,
                                this.getExtensionByMimeType(mimeType));
                    }
                }
            }
        } catch (final Exception e) {
            this.logger.catching(e);
        }

        this.logger.exit();
    }

    private void facade(final List<File> files) {
        this.logger.entry(files);

        try {
            for (final var file : files) {
                final var tika = new Tika(new TikaConfig());

                try (final var stream = new FileInputStream(file)) {
                    final var mimeType = tika.parseToString(stream);

                    if (mimeType != null && !mimeType.isBlank() && this.logger.isInfoEnabled())
                        this.logger.info("{}: {}: {}",
                                file.getName(),
                                mimeType,
                                this.getExtensionByMimeType(mimeType)); // Nothing logged
                }
            }
        } catch (final Exception e) {
            this.logger.catching(e);
        }

        this.logger.exit();
    }

    private void parsing(final List<File> files) {
        this.logger.entry(files);

        // For the Word document the content type is application/x-tika-ooxml

        try {
            for (final var file : files) {
                try (final var stream = new FileInputStream(file)) {
                    final Parser parser = new AutoDetectParser();

                    final var handler = new BodyContentHandler();
                    final var metadata = new Metadata();
                    final var context = new ParseContext();

                    parser.parse(stream, handler, metadata, context);

                    final var mimeType = metadata.get("Content-Type");

                    if (mimeType != null && !mimeType.isBlank() && this.logger.isInfoEnabled())
                        this.logger.info("{}: {}: {}",
                                file.getName(),
                                mimeType,
                                this.getExtensionByMimeType(mimeType));
                }
            }
        } catch (final Exception e) {
            this.logger.catching(e);
        }

        this.logger.exit();
    }

    private void simple(final List<File> files) {
        this.logger.entry(files);

        // For the Word document the content type is application/vnd.openxmlformats-officedocument.wordprocessingml.document

        try {
            for (final var file : files) {
                final var tika = new Tika();
                final var mimeType = tika.detect(file);

                if (mimeType != null && !mimeType.isBlank() && this.logger.isInfoEnabled())
                    this.logger.info("{}: {}: {}",
                            file.getName(),
                            mimeType,
                            this.getExtensionByMimeType(mimeType));
            }
        } catch (final Exception e) {
            this.logger.catching(e);
        }

        this.logger.exit();
    }

    private String getExtensionByMimeType(final String mimetypeString) {
        this.logger.entry(mimetypeString);

        String extension;

        final var allTypes = MimeTypes.getDefaultMimeTypes();

        try {
            final var mimeType = allTypes.forName(mimetypeString);

            if (mimeType != null)
                extension = mimeType.getExtension();
            else
                extension = "";
        } catch (final MimeTypeException mte) {
            this.logger.catching(mte);

            extension = "";
        }

        return extension;
    }

    public static void main(final String[] args) {
        new Main().run();
    }
}
