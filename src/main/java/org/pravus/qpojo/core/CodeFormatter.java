package org.pravus.qpojo.core;

import com.github.javaparser.ast.CompilationUnit;

public interface CodeFormatter {

    String format(CompilationUnit compilationUnit);

}
