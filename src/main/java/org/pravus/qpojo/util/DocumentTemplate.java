/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pravus.qpojo.util;

import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

public class DocumentTemplate {

    private final Document document;

    public DocumentTemplate(Document document) {
        this.document = document;
    }

    public String getText() {
        try {
            return document.getText(0, document.getLength());
        } catch (BadLocationException ex) {
            throw new IllegalStateException(ex);
        }
    }

    public DocumentTemplate append(String text) {
        insert(text, document.getLength());
        return this;
    }

    public void insert(String text, int offset) {
        try {
            document.insertString(offset, text, null);
        } catch (BadLocationException ex) {
            throw new IllegalArgumentException(ex);
        }
    }

    public void remove(int startPosition, int length) {
        try {
            document.remove(startPosition, length);
        } catch (BadLocationException ex) {
            throw new IllegalArgumentException(ex);
        }
    }

    public Document getDocument() {
        return document;
    }

}
