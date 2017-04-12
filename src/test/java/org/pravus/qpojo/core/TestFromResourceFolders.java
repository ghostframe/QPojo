package org.pravus.qpojo.core;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public abstract class TestFromResourceFolders {

    @Parameterized.Parameters(name = "{0}")
    public static Iterable<? extends Object> data() {
        return TestResourceUtils.allResourceFolderNames();
    }

    private final String folder;

    public TestFromResourceFolders(String folder) {
        this.folder = folder;
    }

    public String getFolder() {
        return folder;
    }
}
