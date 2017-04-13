package org.pravus.qpojo.core.impl;

import difflib.Delta;
import difflib.DiffUtils;
import difflib.Patch;
import java.util.Collections;
import java.util.List;
import javax.swing.text.Document;
import org.pravus.qpojo.core.DocumentPatcher;
import org.pravus.qpojo.util.DocumentTemplate;
import static org.pravus.qpojo.util.ListAndStringUtils.characterListToString;
import static org.pravus.qpojo.util.ListAndStringUtils.stringToCharacterList;
import static org.pravus.qpojo.util.TextUtils.NETBEANS_END_OF_LINE;
import static org.pravus.qpojo.util.TextUtils.SYSTEM_END_OF_LINE;

public class DiffDocumentPatcher implements DocumentPatcher {

    @Override
    public void patch(Document document, String newText) {
        DocumentTemplate documentHandler = new DocumentTemplate(document);
        Patch<Character> patch = DiffUtils.diff(stringToCharacterList(documentHandler.getText()),
                stringToCharacterList(newText.replace(SYSTEM_END_OF_LINE, NETBEANS_END_OF_LINE)));
        List<Delta<Character>> lista = patch.getDeltas();
        Collections.reverse(lista);
        for (Delta<Character> delta : lista) {
            applyDelta(documentHandler, delta);
        }
    }

    private void applyDelta(DocumentTemplate documentHandler, Delta<Character> delta) {
        switch (delta.getType()) {
            case CHANGE:
                applyDeleteDelta(documentHandler, delta);
                applyInsertDelta(documentHandler, delta);
                break;
            case DELETE:
                applyDeleteDelta(documentHandler, delta);
                break;
            case INSERT:
                applyInsertDelta(documentHandler, delta);
                break;
            default:
                break;
        }
    }

    private void applyInsertDelta(DocumentTemplate documentHandler, Delta<Character> delta) {
        String deltaString = characterListToString(delta.getRevised().getLines());
        if (!deltaString.equals("\r")) {
            documentHandler.insert(deltaString, delta.getOriginal().getPosition());
        }
    }

    private void applyDeleteDelta(DocumentTemplate documentHandler, Delta<Character> delta) {
        documentHandler.remove(delta.getOriginal().getPosition(),
                delta.getOriginal().size());
    }
}
