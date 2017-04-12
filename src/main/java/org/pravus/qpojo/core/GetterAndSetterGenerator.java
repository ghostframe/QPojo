package org.pravus.qpojo.core;

import com.github.javaparser.ParseProblemException;

public interface GetterAndSetterGenerator {

    String generate(String inputCode) throws ParseProblemException;
}
