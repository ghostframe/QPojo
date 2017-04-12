package org.pravus.qpojo.core.impl;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import java.util.List;
import org.pravus.qpojo.core.CodeFormatter;
import org.pravus.qpojo.core.Factory;
import org.pravus.qpojo.core.GetterAndSetterGenerator;

public class GetterAndSetterGeneratorImpl implements GetterAndSetterGenerator {

    private final CodeFormatter codeFormatter;

    public GetterAndSetterGeneratorImpl() {
        codeFormatter = Factory.getCodeFormatter();
    }

    @Override
    public String generate(String inputCode) {
        CompilationUnit code = JavaParser.parse(inputCode);
        TypeDeclaration<?> type = code.getTypes().get(0);
        deleteGettersAndSetters(type);
        createGettersAndSetters(type.getFields());
        return codeFormatter.format(code);
    }

    private void createGettersAndSetters(List<FieldDeclaration> fields) {
        for (FieldDeclaration field : fields) {
            if (field.isPrivate() && !field.isStatic()) {
                MethodDeclaration method = field.createGetter();
                if (isBoolean(field)) {
                    //Replace "get" with "is"
                    method.setName("is" + method.getNameAsString().substring(3));
                }
                if (!field.isFinal()) {
                    field.createSetter();
                }
            }
        }
    }
    
    private boolean isBoolean(FieldDeclaration field) {
        return field.getVariable(0).getType().toString().equals("boolean");
    }

    private void deleteGettersAndSetters(TypeDeclaration<?> type) {
        List<MethodDeclaration> methods = type.getMethods();
        for (MethodDeclaration method : methods) {
            if (startsWithGetSetIs(method.getNameAsString()) 
                    && !method.isStatic()) {
                method.remove();
            }
        }
    }
    
    private boolean startsWithGetSetIs(String methodName) {
        return methodName.startsWith("get") || methodName.startsWith("set") || methodName.startsWith("is");
    }

}
