package org.pravus.qpojo.netbeans;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.text.JTextComponent;
import org.netbeans.api.editor.EditorRegistry;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;
//import org.openide.util.NbBundle.Messages;
import org.pravus.qpojo.core.Factory;
import org.pravus.qpojo.core.NetBeansGetterAndSetterGenerator;

@ActionID(id = "org.pravus.qpojo.netbeans.RebuildGettersAndSettersAction",
        category = "Source")
@ActionRegistration(displayName = "#CTL_RebuildGettersAndSettersAction")
@Messages("CTL_RebuildGettersAndSettersAction=Rebuild getters and Setters")
@ActionReferences({
    @ActionReference(path = "Menu/Source", position = 350)
    ,
    @ActionReference(path = "Editors/text/x-java/Actions", position = 1590)
    ,
    @ActionReference(path = "Shortcuts", name = "OS-G")})
public final class RebuildGettersAndSettersAction implements ActionListener {

    private final NetBeansGetterAndSetterGenerator swingGetterAndSetterGenerator;

    public RebuildGettersAndSettersAction() {
        swingGetterAndSetterGenerator = Factory.getNetBeansGetterAndSetterGenerator();
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        try {
            JTextComponent editorTextComponent = EditorRegistry.lastFocusedComponent();
            if (editorTextComponent != null) {
                swingGetterAndSetterGenerator.generate(editorTextComponent);
            }
        } catch (Exception ex) {
            
        }
    }
}
