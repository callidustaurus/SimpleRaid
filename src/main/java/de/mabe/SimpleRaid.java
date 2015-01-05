package de.mabe;

import java.io.File;

public class SimpleRaid {
    public static void main(String[] args) {
        String pathToConfig = new File("src/main/resources/config").getAbsolutePath(); // maybe passed by args. now set for easy testing
        PathConfiguration config = ConfigurationParser.parse(pathToConfig);
    }
}
