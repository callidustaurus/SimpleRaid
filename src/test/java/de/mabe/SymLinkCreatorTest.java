package de.mabe;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SymLinkCreatorTest {
    private final String testFolder = "/tmp/testFolder";

    @Before
    @After
    public void cleanUp() {
        delete(new File(testFolder));
    }

    @Test
    public void existingFolderAndFilesInDestPathWillBeDeletedAndDestRootWillBeCreatedNew() {
        createFile(testFolder + "test/testFile");

        PathConfiguration config = new PathConfiguration();
        config.destinationPath = testFolder;

        Executor executer = new SymLinkCreator();
        executer.execute(config);

        assertFileOrFolderDidNOTExists(testFolder + "test/testFile");
        assertFileOrFolderExists(testFolder);
    }

    private void delete(File file) {
        if (file.isDirectory()) {
            for (File innerFile : file.listFiles()) {
                delete(innerFile);
            }
            file.delete();
        } else {
            file.delete();
        }
        assertFalse(file.exists());
    }

    private void assertFileOrFolderExists(String pathToFolderOrFile) {
        File fileOrFolder = new File(pathToFolderOrFile);
        assertTrue("The file or folder " + fileOrFolder.getPath() + " did not exists, but it should", fileOrFolder.exists());
    }

    private void assertFileOrFolderDidNOTExists(String pathToFolderOrFile) {
        File fileOrFolder = new File(pathToFolderOrFile);
        assertFalse("The file or folder " + fileOrFolder.getPath() + " exists, but it should NOT", fileOrFolder.exists());
    }

    private File createFile(String fileNameInclPath) {
        File folder = new File(fileNameInclPath.substring(0, fileNameInclPath.lastIndexOf('/')));
        folder.mkdirs();
        File file = new File(fileNameInclPath.substring(fileNameInclPath.lastIndexOf('/') + 1));
        try {
            file.createNewFile();
        } catch (IOException e) {
            Assert.fail();
        }
        return file;
    }
}
