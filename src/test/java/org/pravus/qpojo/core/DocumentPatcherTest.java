package org.pravus.qpojo.core;

import org.pravus.qpojo.util.DocumentTemplate;
import difflib.PatchFailedException;
import javax.swing.text.BadLocationException;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.netbeans.modules.editor.NbEditorDocument;
import static org.pravus.qpojo.core.TestResourceUtils.readInputFile;
import static org.pravus.qpojo.core.TestResourceUtils.readExpectedFile;

public class DocumentPatcherTest extends TestFromResourceFolders {

    private final DocumentPatcher documentPatcher;

    public DocumentPatcherTest(String folder) {
        super(folder);
        documentPatcher = Factory.getDocumentPatcher();
    }

    @Test
    public void patchToObtain() throws BadLocationException, PatchFailedException {
        DocumentTemplate document = new DocumentTemplate(new NbEditorDocument("text/x-java"))
                .append(readInputFile(getFolder()));
        String expectedText = new DocumentTemplate(new NbEditorDocument("text/x-java"))
                .append(readExpectedFile(getFolder()))
                .getText();

        documentPatcher.patch(document.getDocument(), expectedText);

        assertEquals(expectedText, document.getText());
    }

}
