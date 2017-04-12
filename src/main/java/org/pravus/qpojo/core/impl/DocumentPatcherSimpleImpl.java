package org.pravus.qpojo.core.impl;

import org.pravus.qpojo.util.DocumentHandler;
import javax.swing.text.Document;
import org.pravus.qpojo.core.DocumentPatcher;

public class DocumentPatcherSimpleImpl implements DocumentPatcher {

    @Override
    public void patch(Document document, String newText) {
        new DocumentHandler(document).setText(newText);
    }

}
