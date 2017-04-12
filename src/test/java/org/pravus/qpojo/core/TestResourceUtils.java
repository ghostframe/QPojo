package org.pravus.qpojo.core;

import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.IOUtils;

public class TestResourceUtils {

    private static final String TEST_RESOURCES_DIRECTORY = "src\\test\\resources\\";
    private static final String INPUT_TEXT_FILE_NAME = "input.txt";
    private static final String EXPECTED_TEXT_FILE_NAME = "expected.txt";

    public static String read(String pathWithinTestResourcesDirectory) {
        try {
            return IOUtils.toString(new FileReader(TEST_RESOURCES_DIRECTORY + pathWithinTestResourcesDirectory));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static String readInputFile(String pathWithinTestResourcesDirectory) {
        return read(pathWithinTestResourcesDirectory + "/" + INPUT_TEXT_FILE_NAME);
    }

    public static String readExpectedFile(String pathWithinTestResourcesDirectory) {
        return read(pathWithinTestResourcesDirectory + "/" + EXPECTED_TEXT_FILE_NAME);
    }

    public static List<String> allResourceFolderNames() {
        List<String> allResourceFolders = new ArrayList<>();
        File[] directories = new File(TEST_RESOURCES_DIRECTORY).listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.isDirectory();
            }
        });
        for (File directory : directories) {
            allResourceFolders.add(directory.getName());
        }
        return allResourceFolders;
    }

}
