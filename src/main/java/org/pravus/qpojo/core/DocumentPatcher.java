package org.pravus.qpojo.core;

import javax.swing.text.Document;

public interface DocumentPatcher {

    void patch(Document document, String newText);

}
