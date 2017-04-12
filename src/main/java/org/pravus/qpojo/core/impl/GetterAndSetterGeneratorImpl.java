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
    public String generateFor(String inputCode) {
        CompilationUnit code = JavaParser.parse(inputCode);
        TypeDeclaration<?> firstDeclaredClass = code.getTypes().get(0);
        deleteGettersAndSettersFromClass(firstDeclaredClass);
        createGettersAndSettersForFields(firstDeclaredClass.getFields());
        return codeFormatter.format(code);
    }

    private void createGettersAndSettersForFields(List<FieldDeclaration> fields) {
        for (FieldDeclaration field : fields) {
            if (field.isPrivate() && !field.isStatic()) {
                MethodDeclaration method = field.createGetter();
                if (isBoolean(field)) {
                    String originalName = method.getName().toString();
                    method.setName(originalName.replaceFirst("get", "is"));
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

    private void deleteGettersAndSettersFromClass(TypeDeclaration<?> type) {
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
