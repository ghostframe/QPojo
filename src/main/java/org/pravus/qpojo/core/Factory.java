package org.pravus.qpojo.core;

import org.pravus.qpojo.core.impl.CodeFormatterPositionImpl;
import org.pravus.qpojo.core.impl.GetterAndSetterGeneratorImpl;
import org.pravus.qpojo.core.impl.DocumentPatcherDiffImpl;
import org.pravus.qpojo.core.impl.NetBeansGetterAndSetterGeneratorImpl;

public final class Factory {

    private static final DocumentPatcher DOCUMENT_PATCHER = new DocumentPatcherDiffImpl();
    private static final CodeFormatter CODE_FORMATTER_IMPL = new CodeFormatterPositionImpl();
    private static final GetterAndSetterGenerator GETTER_AND_SETTER_GENERATOR = new GetterAndSetterGeneratorImpl();
    private static final NetBeansGetterAndSetterGenerator NETBEANS_GETTER_AND_SETTER_GENERATOR = new NetBeansGetterAndSetterGeneratorImpl();

    public static DocumentPatcher getDocumentPatcher() {
        return DOCUMENT_PATCHER;
    }

    public static GetterAndSetterGenerator getGetterAndSetterGenerator() {
        return GETTER_AND_SETTER_GENERATOR;
    }

    public static NetBeansGetterAndSetterGenerator getNetBeansGetterAndSetterGenerator() {
        return NETBEANS_GETTER_AND_SETTER_GENERATOR;
    }

    public static CodeFormatter getCodeFormatter() {
        return CODE_FORMATTER_IMPL;
    }
}
