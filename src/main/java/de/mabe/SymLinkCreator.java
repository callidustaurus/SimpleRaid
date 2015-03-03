package de.mabe;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class SymLinkCreator implements Executor {

    private List<File> oldFiles = new ArrayList<File>();
    private List<File> newFiles = new ArrayList<File>();

    @Override
    public void execute(PathConfiguration config) {
        File destPath = new File(config.destinationPath);
        delete(destPath);
        destPath.mkdir();

        for (String sourcePath : config.sourcePathes) {
            File file = new File(sourcePath);
            createSymbolicLinks(sourcePath, config.destinationPath, file.list());
        }
        printUpdatedFiles();
    }

    private void printUpdatedFiles() {
        List<File> deletedFiles = new ArrayList<File>();
        List<File> createdFiles = new ArrayList<File>();

        for (File file : oldFiles) {
            if (!newFiles.contains(file)) {
                deletedFiles.add(file);
            }
        }

        for (File file : newFiles) {
            if (!oldFiles.contains(file)) {
                createdFiles.add(file);
            }
        }

        System.out.println("Deleted files: " + deletedFiles);
        System.out.println("New files: " + createdFiles);
    }

    private void delete(File file) {
        if (file.isDirectory()) {
            for (File innerFile : file.listFiles()) {
                delete(innerFile);
            }
            file.delete();
        } else {
            oldFiles.add(file);
            file.delete();
        }
    }

    private void createSymbolicLinks(String sourcePath, String destPath, String[] filesAndDirectories) {
        for (String fileOrDirectory : filesAndDirectories) {
            File file = new File(sourcePath + "/" + fileOrDirectory);

            // if file is directory it must be created, except it exists already
            if (file.isDirectory()) {
                File newDirectoryInDestination = new File(destPath + "/" + fileOrDirectory);

                if (!newDirectoryInDestination.exists()) {
                    newDirectoryInDestination.mkdir();
                }
                // if file is directory files in this directory must also path tis symLink creation
                createSymbolicLinks(file.getPath(), destPath + "/" + fileOrDirectory, file.list());
            }
            // if file is file, create a symbolicLink to in in DestinationPath
            else {
                try {
                    File fileForSymbolicLink = new File(destPath + "/" + fileOrDirectory);
                    newFiles.add(fileForSymbolicLink);
                    Files.createSymbolicLink(fileForSymbolicLink.toPath(), file.toPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

    }
}
