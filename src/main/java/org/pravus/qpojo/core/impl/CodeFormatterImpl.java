package org.pravus.qpojo.core.impl;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.FieldDeclaration;
import java.util.List;
import org.pravus.qpojo.core.CodeFormatter;
import static org.pravus.qpojo.util.TextUtils.SYSTEM_END_OF_LINE;

public class CodeFormatterImpl implements CodeFormatter {

    @Override
    public String format(CompilationUnit compilationUnit) {
        // Get field declaration code and begin-end positions
        String code = compilationUnit.toString();
        List<FieldDeclaration> fields = compilationUnit.getType(0).getFields();
        if (fields.isEmpty()) {
            return code;
        } else {
            int firstFieldPosition = code.indexOf(fields.get(0).getVariables().get(0).toString());
            int lastFieldPosition = code.indexOf(fields.get(fields.size() - 1).getVariables().get(0).toString());
            String beforeFieldCode = code.substring(0, firstFieldPosition);
            String fieldCode = code.substring(firstFieldPosition, lastFieldPosition)
                    .replace(SYSTEM_END_OF_LINE + SYSTEM_END_OF_LINE, SYSTEM_END_OF_LINE);
            String afterFieldCode = code.substring(lastFieldPosition);
            return beforeFieldCode + fieldCode + afterFieldCode;
        }
    }
}
