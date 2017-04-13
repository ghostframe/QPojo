package org.pravus.qpojo.core;

import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.pravus.qpojo.core.TestResourceUtils.readInputFile;
import static org.pravus.qpojo.core.TestResourceUtils.readExpectedFile;

public class GetterAndSetterGeneratorTest {

    GetterAndSetterGenerator getterAndSetterGenerator;

    public GetterAndSetterGeneratorTest() {
        getterAndSetterGenerator = Factory.getGetterAndSetterGenerator();
    }

    @Test
    public void generate_EmptySpaceAfterFields() {
        test("EmptySpaceAfterFields");
    }

    @Test
    public void generate_GettersAndSetters_NoFields() {
        test("GettersAndSetters_NoFields");
    }

    @Test
    public void generate_GettersAndSetters_PrivateFieldsAnnotations() {
        test("GettersAndSetters_PrivateFieldsAnnotations");
    }

    @Test
    public void generate_NoGettersAndSetters_NoFields() {
        test("NoGettersAndSetters_NoFields");
    }

    @Test
    public void generate_NoGettersAndSetters_PrivateAndPublicFields() {
        test("NoGettersAndSetters_PrivateAndPublicFields");
    }

    @Test
    public void generate_NoGettersAndSetters_PrivateFieldsAnnotations() {
        test("NoGettersAndSetters_PrivateFieldsAnnotations");
    }

    @Test
    public void generate_NoGettersAndSetters_PrivateFinalFields() {
        test("NoGettersAndSetters_PrivateFinalFields");
    }

    @Test
    public void generate_NoGettersAndSetters_PublicFields() {
        test("NoGettersAndSetters_PublicFields");
    }

    @Test
    public void generate_NoGettersAndSetters_StaticFields() {
        test("NoGettersAndSetters_StaticFields");
    }

    @Test
    public void generate_StaticGettersAndSetters() {
        test("StaticGettersAndSetters");
    }

    private void test(String testCaseName) {
        String input = readInputFile(testCaseName);
        String expected = readExpectedFile(testCaseName);
        String actual = getterAndSetterGenerator.generateFor(input);
        assertEquals(expected, actual);
    }
}
