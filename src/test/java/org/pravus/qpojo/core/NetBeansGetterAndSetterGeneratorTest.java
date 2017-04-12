package org.pravus.qpojo.core;

import javax.swing.JTextArea;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.netbeans.modules.editor.NbEditorDocument;
import org.pravus.qpojo.util.DocumentTemplate;

public class NetBeansGetterAndSetterGeneratorTest extends TestFromResourceFolders {

    private final NetBeansGetterAndSetterGenerator netBeansGetterAndSetterGenerator;

    public NetBeansGetterAndSetterGeneratorTest(String folder) {
        super(folder);
        netBeansGetterAndSetterGenerator = Factory.getNetBeansGetterAndSetterGenerator();
    }

    @Test
    public void test() {
        NbEditorDocument inputDocument = new NbEditorDocument("application/x-java");
        DocumentTemplate inputDocumentHandler = new DocumentTemplate(inputDocument)
                .append(TestResourceUtils.readInputFile(getFolder()));
        NbEditorDocument expectedDocument = new NbEditorDocument("application/x-java");
        String expectedText = new DocumentTemplate(expectedDocument)
                .append(TestResourceUtils.readExpectedFile(getFolder()))
                .getText();

        netBeansGetterAndSetterGenerator.generate(new JTextArea(inputDocument));
        System.out.println(inputDocumentHandler.getText());
        assertEquals(inputDocumentHandler.getText(), expectedText);
    }

}
