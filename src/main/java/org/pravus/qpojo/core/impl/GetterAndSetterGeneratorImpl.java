package org.pravus.qpojo.core.impl;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import java.util.List;
import org.pravus.qpojo.core.GetterAndSetterGenerator;
import static org.pravus.qpojo.util.TextUtils.SYSTEM_END_OF_LINE;

public class GetterAndSetterGeneratorImpl implements GetterAndSetterGenerator {

    @Override
    public String generateFor(String inputCode) {
        CompilationUnit code = JavaParser.parse(inputCode);
        TypeDeclaration firstDeclaredClass = code.getTypes().get(0);
        deleteGettersAndSetters(firstDeclaredClass);
        createGettersAndSetters(firstDeclaredClass);
        return cleanupGetterAndSetterDoubleEOL(code);
    }

    private void createGettersAndSetters(TypeDeclaration typeDeclaration) {
        List<FieldDeclaration> fields = typeDeclaration.getFields();
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
    
    private String cleanupGetterAndSetterDoubleEOL(CompilationUnit compilationUnit) {
        // Get field declaration code and begin-end positions
        String code = compilationUnit.toString();
        List<FieldDeclaration> fields = compilationUnit.getType(0).getFields();
        if (fields.isEmpty()) {
            return code;
        } else {
            int firstFieldPosition = code.indexOf(getFirstFieldDeclaration(fields));
            int lastFieldPosition = code.indexOf(getLastFieldDeclaration(fields), 
                    firstFieldPosition);
            String beforeFieldCode = code.substring(0, firstFieldPosition);
            String fieldCode = code.substring(firstFieldPosition, lastFieldPosition)
                    .replace(SYSTEM_END_OF_LINE + SYSTEM_END_OF_LINE, SYSTEM_END_OF_LINE);
            String afterFieldCode = code.substring(lastFieldPosition);
            return beforeFieldCode + fieldCode + afterFieldCode;
        }
    }

    private static String getLastFieldDeclaration(List<FieldDeclaration> fields) {
        return fields.get(fields.size() - 1).getVariables().get(0).toString();
    }

    private static String getFirstFieldDeclaration(List<FieldDeclaration> fields) {
        return fields.get(0).getVariables().get(0).toString();
    }
    
}
