/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula4;

import java.lang.reflect.Array;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import sun.security.jca.JCAUtil;

/**
 *
 * @author m98567
 */
public class Localizar_optionPane extends JDialog {
    //private Array buscasAnteriores;
    private String[] buscasAnteriores = new String[10];
    private JPanel panelRoot;
    private JOptionPane optionPaneFind;
    private JButton buttonFind, buttonCancel;
    private CadastroDeFrases formPai;
    
    public Localizar_optionPane(CadastroDeFrases pai) {
        formPai = pai;  // get's the father's reference
        
        /*Criando Painel principal*/
        panelRoot = new JPanel();
        BoxLayout layoutBox = new BoxLayout(panelRoot, BoxLayout.X_AXIS);
        panelRoot.setLayout(layoutBox);
        
        Object[] possibilities = {"ham", "spam", "yam"};
        String s = (String) JOptionPane.showInputDialog(
                panelRoot,
                "",
                "Find",
                JOptionPane.PLAIN_MESSAGE,
                null,
                possibilities,
                "ham");
        

        //If a string was returned, say so.
        if ((s != null) && (s.length() > 0)) {
            System.out.println(s);
            return;
        }

        //If you're here, the return value was null/empty.
        System.out.println("Come on, finish the sentence!");
        
        this.setContentPane(panelRoot);
        this.pack();
    }
}
