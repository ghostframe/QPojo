package org.pravus.qpojo.core;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import static org.pravus.qpojo.core.TestResourceUtils.readInputFile;
import static org.pravus.qpojo.core.TestResourceUtils.readExpectedFile;

public class GetterAndSetterGeneratorTest extends TestFromResourceFolders {

    private final GetterAndSetterGenerator getterAndSetterGenerator;

    public GetterAndSetterGeneratorTest(String folder) {
        super(folder);
        getterAndSetterGenerator = Factory.getGetterAndSetterGenerator();
    }

    @Test
    public void test() {
        String input = readInputFile(getFolder());
        String expected = readExpectedFile(getFolder());

        System.out.println("-------------------" + getFolder() + "--------------------");
        String output = getterAndSetterGenerator.generate(input);
        System.out.println("---------------------------------------");

        assertEquals(expected, output);
    }

}
