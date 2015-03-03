package de.mabe;

import java.io.File;

import org.junit.Test;

public class ConfigurationParserTest {

    @Test(expected = RuntimeException.class)
    public void ifFileDidNotExistExceptionIsThrown() {
        ConfigurationParser.parse("this/file/did/not/exist");
    }

    private void assertSourceContent(String sourceContent) throws Exception {
        File file = new File("tmp/testFile");
    }

}
