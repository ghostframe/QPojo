package org.pravus.qpojo.core;

import com.github.javaparser.ParseProblemException;

public interface GetterAndSetterGenerator {

    String generateFor(String inputCode) throws ParseProblemException;
}
