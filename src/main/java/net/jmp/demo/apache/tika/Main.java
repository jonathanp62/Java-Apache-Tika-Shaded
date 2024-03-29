package net.jmp.demo.apache.tika;

/*
 * (#)Main.java 0.9.0   02/02/2024
 * (#)Main.java 0.8.0   01/30/2024
 * (#)Main.java 0.3.0   01/23/2024
 * (#)Main.java 0.2.0   01/22/2024
 * (#)Main.java 0.1.0   01/22/2024
 *
 * @author    Jonathan Parker
 * @version   0.9.0
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

import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.LoggerFactory;

import org.slf4j.ext.XLogger;

public final class Main {
    private static final String FILE_NAMES = "config/file-names.json";

    private final XLogger logger = new XLogger(LoggerFactory.getLogger(this.getClass().getName()));

    private Main() {
        super();

        System.setProperty("jaxp.debug", "false");
    }

    private void run() {
        this.logger.entry();

        final var fileNames = this.getFileNames();

        if (fileNames.isPresent() && !fileNames.get().getList().isEmpty()) {
            final List<File> files = new ArrayList<>();

            for (final var fileName : fileNames.get().getList())
                files.add(new File(fileName));

            new Shaded().run(files);
        }

        this.logger.exit();
    }

    private Optional<FileNames> getFileNames() {
        this.logger.entry();

        FileNames fileNames = null;

        try {
            fileNames = new Gson().fromJson(Files.readString(Paths.get(FILE_NAMES)), FileNames.class);
        } catch (final IOException ioe) {
            this.logger.catching(ioe);
        }

        final Optional<FileNames> result = Optional.ofNullable(fileNames);

        this.logger.exit(result);

        return result;
    }

    public static void main(final String[] args) {
        new Main().run();
    }
}
