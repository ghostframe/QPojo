/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pravus.qpojo.util;

import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

public class DocumentHandler {

    private final Document document;

    public DocumentHandler(Document document) {
        this.document = document;
    }

    public String getText() {
        try {
            return document.getText(0, document.getLength());
        } catch (BadLocationException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void setText(String newText) {
        clear();
        append(newText);
    }

    public void insert(String text, int offset) {
        try {
            document.insertString(offset, text, null);
        } catch (BadLocationException ex) {
            throw new RuntimeException(ex);
        }
    }

    public DocumentHandler append(String text) {
        try {
            document.insertString(document.getLength(), text, null);
            return this;
        } catch (BadLocationException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void remove(int startPosition, int length) {
        try {
            document.remove(startPosition, length);
        } catch (BadLocationException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void clear() {
        try {
            document.remove(0, document.getLength());
        } catch (BadLocationException ex) {
            throw new RuntimeException(ex);
        }
    }

    public Document getDocument() {
        return document;
    }

}
