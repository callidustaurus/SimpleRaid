package de.mabe;

import java.util.ArrayList;
import java.util.List;

public class PathConfiguration {
    List<String> sourcePathes;
    String destinationPath;

    public PathConfiguration() {
        sourcePathes = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Sources " + sourcePathes + " destination: " + destinationPath;
    }

    public void assertConfigIsComplete() {
        String errorMessage = "";
        if (sourcePathes.isEmpty()) {
            errorMessage += "There are no sources defined. ";
        }
        if (destinationPath == null || destinationPath.isEmpty()) {
            errorMessage += "There is no destinations definded.";
        }

        if (!errorMessage.isEmpty()) {
            throw new RuntimeException(errorMessage);
        }
    }
}
