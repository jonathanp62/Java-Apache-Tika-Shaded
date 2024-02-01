package net.jmp.demo.apache.tika;

/*
 * (#)Shaded.java   0.8.0   01/31/2024
 * (#)Shaded.java   0.5.0   01/25/2024
 * (#)Shaded.java   0.4.0   01/23/2024
 * (#)Shaded.java   0.3.0   01/23/2024
 *
 * @author    Jonathan Parker
 * @version   0.8.0
 * @since     0.3.0
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
import java.io.IOException;

import java.nio.file.Paths;

import java.util.List;

import org.shaded.apache.tika.Tika;

import org.shaded.apache.tika.config.TikaConfig;

import org.shaded.apache.tika.io.TikaInputStream;

import org.shaded.apache.tika.metadata.Metadata;

import org.shaded.apache.tika.mime.MimeTypeException;
import org.shaded.apache.tika.mime.MimeTypes;

import org.shaded.apache.tika.parser.AutoDetectParser;
import org.shaded.apache.tika.parser.ParseContext;
import org.shaded.apache.tika.parser.Parser;

import org.shaded.apache.tika.sax.BodyContentHandler;

import org.slf4j.LoggerFactory;

import org.slf4j.ext.XLogger;

final class Shaded {
    private final XLogger logger = new XLogger(LoggerFactory.getLogger(this.getClass().getName()));
    private final boolean runWhatDoesNotWork = false;

    Shaded() {
        super();
    }

    void run(final List<File> files) {
        this.logger.entry(files);

        this.defaultDetecting(files);

        if (this.runWhatDoesNotWork) {
            this.facade(files);
            this.parsing(files);
        }

        this.simple(files);
        this.parseToString(files);

        this.logger.exit();
    }

    private void defaultDetecting(final List<File> files) {
        this.logger.entry(files);

        try {
            for (final var file : files) {
                final var mimeType = this.defaultDetect(file);

                if (mimeType != null && !mimeType.isBlank() && this.logger.isInfoEnabled()) {
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

    private String defaultDetect(final File file) throws Exception {
        this.logger.entry(file);

        String mimeType = null;

        final var tika = new TikaConfig();
        final var metadata = new Metadata();

        final var mediaType = tika
                .getDetector()
                .detect(TikaInputStream.get(Paths.get(file.toURI())), metadata);

        if (mediaType != null)
            mimeType = mediaType.toString();

        this.logger.exit(mimeType);

        return mimeType;
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

        try {
            for (final var file : files) {
                final var mimeType = this.simpleDetect(file);

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

    private String simpleDetect(final File file) throws IOException {
        this.logger.entry(file);

        final var tika = new Tika();
        final var mimeType = tika.detect(file);

        this.logger.exit(mimeType);

        return mimeType;
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

        this.logger.exit(extension);

        return extension;
    }

    private void parseToString(final List<File> files) {
        this.logger.entry(files);

        // In Acadia only text files and PDF files are parsed to string

        try {
            for (final var file : files) {
                if ("LinkedIn-Greeting.txt".equals(file.getName())
                        || "Text-Document.tmp".equals(file.getName())
                        || "Improving_Code_Quality_.pdf".equals(file.getName())) {
                    final var tika = new Tika();
                    final var string = tika.parseToString(file);

                    if (this.logger.isInfoEnabled()) {
                        this.logger.info("File {}", file.getName());
                        this.logger.info("String: {}", string);
                    }
                }
            }
        } catch (final Exception e) {
            this.logger.catching(e);
        }

        this.logger.exit();
    }
}
