package de.mabe;

import java.util.ArrayList;
import java.util.List;

public class PathConfiguration {
    List sourcePathes;
    String destinationPath;

    public PathConfiguration() {
        sourcePathes = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Sources " + sourcePathes + " destination: " + destinationPath;
    }
}
