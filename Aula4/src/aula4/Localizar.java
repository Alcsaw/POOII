/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula4;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class Localizar extends JDialog {
    //private Array buscasAnteriores;
    private String[] buscasAnteriores = new String[10];
    private JPanel panelRoot;
    //private JTextField textFieldFind;
    private JComboBox comboBoxFind;
    //private JOptionPane optionPaneFind;
    private JButton buttonFind, buttonCancel;
    private CadastroDeFrases formPai;
    
    public Localizar( CadastroDeFrases pai ) {
        formPai = pai;  // get's the father's reference
        
        /*Criando Painel principal*/
        panelRoot = new JPanel();
        BoxLayout layoutBox = new BoxLayout(panelRoot, BoxLayout.X_AXIS);
        panelRoot.setLayout(layoutBox);
        
        //textFieldFind = new JTextField();
        //panelRoot.add(textFieldFind);
        comboBoxFind = new JComboBox(buscasAnteriores);
        comboBoxFind.setEditable(true);
        panelRoot.add(comboBoxFind);
        
        buttonFind = new JButton("Localizar");
        buttonFind.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Saves the searched text in memory
                String palavraAtual = comboBoxFind.getSelectedItem().toString();
                comboBoxFind.addItem(palavraAtual);
                
                // Finds the matching text (highlight) on the other frame
                int startPosition = formPai.getTextAreaConcat().getText().indexOf(palavraAtual);
                formPai.getTextAreaConcat().setSelectionStart(startPosition);
                System.out.println("POSIÇÃO: " + startPosition);
                formPai.getTextAreaConcat().setSelectionEnd(startPosition + palavraAtual.length());
                formPai.getTextAreaConcat().setSelectionColor(Color.orange);
            }
        });
        panelRoot.add(buttonFind);
        
        buttonCancel = new JButton("Cancelar");
        panelRoot.add(buttonCancel);
        
                
        this.setContentPane(panelRoot);
        this.pack();
    }
}
