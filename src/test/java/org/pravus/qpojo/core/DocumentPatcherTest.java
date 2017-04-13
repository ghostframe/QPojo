package org.pravus.qpojo.core;

import org.pravus.qpojo.util.DocumentTemplate;
import difflib.PatchFailedException;
import javax.swing.text.BadLocationException;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.netbeans.modules.editor.NbEditorDocument;
import static org.pravus.qpojo.core.TestResourceUtils.readInputFile;
import static org.pravus.qpojo.core.TestResourceUtils.readExpectedFile;

public class DocumentPatcherTest {

    DocumentPatcher documentPatcher;

    public DocumentPatcherTest() {
        documentPatcher = Factory.getDocumentPatcher();
    }

    @Test
    public void patchToObtain_GettersAndSettersPrivateFieldsAnnotations() throws BadLocationException, PatchFailedException {
        String testCase = "GettersAndSetters_PrivateFieldsAnnotations";
        
        DocumentTemplate document = new DocumentTemplate(new NbEditorDocument("text/x-java"))
                .append(readInputFile(testCase));
        String expectedText = new DocumentTemplate(new NbEditorDocument("text/x-java"))
                .append(readExpectedFile(testCase))
                .getText();

        documentPatcher.patch(document.getDocument(), expectedText);

        assertEquals(expectedText, document.getText());
    }

}
