/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula4;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author m98567
 */
public class CadastroDeFrases extends JFrame {
    
    private JPanel panelRoot, panelBottom;
    private JTextField textFieldPhrase;
    private JTextArea textAreaConcat;
    private JButton buttonSendPhrase;
    
    public CadastroDeFrases getThis() {
        return this;
    }
    
    public CadastroDeFrases() {
        /* Definições sobre o Frame*/
        //this.setBounds(300, 300, 500, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        /*Criando Painel principal*/
        panelRoot = new JPanel();
        //GridLayout layoutGrid = new GridLayout(2, 1);
        BoxLayout layoutBox = new BoxLayout(panelRoot, BoxLayout.Y_AXIS);
        panelRoot.setLayout(layoutBox);
        
        textAreaConcat = new JTextArea(10, 40);
        textAreaConcat.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                //if ((e.getKeyCode() == KeyEvent.VK_F) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
                if ((e.getKeyCode() == KeyEvent.VK_F) && e.isControlDown()) {
                    // isControlDown resume a comparação de modificadores comentada acima
                    System.out.println("FIND key combination pressed.");
                    Localizar l = new Localizar(getThis());
                    //Localizar_optionPane l = new Localizar_optionPane(getThis());
                    l.setVisible(true);        
                }
            }
        });
        panelRoot.add(textAreaConcat);
        
        panelBottom = new JPanel();
        FlowLayout layoutFlow = new FlowLayout(FlowLayout.CENTER, 5, 10);
        panelBottom.setLayout(layoutFlow);
        
        textFieldPhrase = new JTextField(20);
        textFieldPhrase.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String phrase = textFieldPhrase.getText() + "\n";
                    textAreaConcat.append(phrase);
                    textFieldPhrase.requestFocus();
                } else if ((e.getKeyCode() == KeyEvent.VK_F) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
                    Localizar l = new Localizar(getThis());
                    //Localizar_optionPane l = new Localizar_optionPane(getThis());
                    l.setVisible(true);        
                    System.out.println("FIND key combination pressed.");
                }
            }
        });
        panelBottom.add(textFieldPhrase);
        
        buttonSendPhrase = new JButton("Inserir linha");
        buttonSendPhrase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String phrase = textFieldPhrase.getText() + "\n";
                textAreaConcat.append(phrase);
            }
        });
        buttonSendPhrase.setMnemonic('i');
        panelBottom.add(buttonSendPhrase);
        
        panelRoot.add(panelBottom);
        this.setContentPane(panelRoot);
    }
/*
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            String phrase = textFieldPhrase.getText() + "\n";
            textAreaConcat.append(phrase);
            textFieldPhrase.requestFocus();
        } else if (e.getKeyCode() == KeyEvent.VK_CONTROL
                && e.getKeyCode() == KeyEvent.VK_F) {
            Localizar l = new Localizar();
            l.setVisible(true);
        } else if ((e.getKeyCode() == KeyEvent.VK_F) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
            Localizar l = new Localizar();
            l.setVisible(true);        
            System.out.println("FIND key combination pressed.");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }*/

    public JTextArea getTextAreaConcat() {
        return textAreaConcat;
    }
}
