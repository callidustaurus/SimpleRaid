package de.mabe;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class ConfigurationParser {
    private static boolean isSource = true;
    private static PathConfiguration pathConfig;

    public static PathConfiguration parse(String path) {

        FileInputStream is;
        try {
            is = new FileInputStream(new File(path));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));

        String line;
        pathConfig = new PathConfiguration();
        while ((line = readLine(reader)) != null) {
            handleLine(line.trim());
        }
        pathConfig.assertConfigIsComplete();
        return pathConfig;
    }

    private static void handleLine(String line) {
        if (line.startsWith("===")) {
            isSource = false;
            return;
        }
        if (isSource) {
            pathConfig.sourcePathes.add(line);
        } else {
            pathConfig.destinationPath = line;
        }
    }

    private static String readLine(BufferedReader reader) {
        try {
            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
