package de.mabe;

import java.io.File;
import java.io.IOException;

public class SimpleRaid {
    public static void main(String[] args) throws IOException {
        String pathToConfig = new File("src/main/resources/config/path.conf").getAbsolutePath(); // maybe passed by args. now set for easy testing
        PathConfiguration config = ConfigurationParser.parse(pathToConfig);
        System.out.println(config);
    }
}
