package org.pravus.qpojo.core.impl;

import org.pravus.qpojo.util.DocumentHandler;
import javax.swing.text.JTextComponent;
import org.netbeans.editor.BaseDocument;
import org.pravus.qpojo.core.DocumentPatcher;
import org.pravus.qpojo.core.Factory;
import org.pravus.qpojo.core.GetterAndSetterGenerator;
import org.pravus.qpojo.core.NetBeansGetterAndSetterGenerator;

public class NetBeansGetterAndSetterGeneratorImpl implements NetBeansGetterAndSetterGenerator {

    private final GetterAndSetterGenerator getterAndSetterGenerator;
    private final DocumentPatcher documentPatcher;

    public NetBeansGetterAndSetterGeneratorImpl() {
        this.documentPatcher = Factory.getDocumentPatcher();
        this.getterAndSetterGenerator = Factory.getGetterAndSetterGenerator();
    }

    @Override
    public void generate(JTextComponent textEditorComponent) {
        final BaseDocument document = (BaseDocument) textEditorComponent.getDocument();
        final String code = new DocumentHandler(document).getText();
        int positionBeforeTextChange = textEditorComponent.getCaretPosition();
        document.runAtomicAsUser(new Runnable() {
            @Override
            public void run() {
                documentPatcher.patch(document, getterAndSetterGenerator.generate(code));
            }

        });
        textEditorComponent.setCaretPosition(Integer.min(positionBeforeTextChange, document.getLength()));
    }
}
