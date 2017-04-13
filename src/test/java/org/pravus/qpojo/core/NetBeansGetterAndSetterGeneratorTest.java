package org.pravus.qpojo.core;

import javax.swing.JTextArea;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.netbeans.modules.editor.NbEditorDocument;
import static org.pravus.qpojo.core.MimeUtils.JAVA_MIME_TYPE;
import org.pravus.qpojo.util.DocumentTemplate;

public class NetBeansGetterAndSetterGeneratorTest {

    private final NetBeansGetterAndSetterGenerator netBeansGetterAndSetterGenerator;

    public NetBeansGetterAndSetterGeneratorTest() {
        netBeansGetterAndSetterGenerator = Factory.getNetBeansGetterAndSetterGenerator();
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

    public void test(String testCase) {
        NbEditorDocument inputDocument = new NbEditorDocument(JAVA_MIME_TYPE);
        DocumentTemplate inputDocumentTemplate
                = new DocumentTemplate(inputDocument)
                        .append(TestResourceUtils.readInputFile(testCase));
        NbEditorDocument expectedDocument = new NbEditorDocument(JAVA_MIME_TYPE);
        String expectedText = new DocumentTemplate(expectedDocument)
                .append(TestResourceUtils.readExpectedFile(testCase))
                .getText();

        netBeansGetterAndSetterGenerator.generate(new JTextArea(inputDocument));
        assertEquals(inputDocumentTemplate.getText(), expectedText);
    }

}
