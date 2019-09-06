/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula6;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author m98567
 */
public class MoreButtonTypes extends JFrame {
    private JPanel panelRoot, panelButtons;
    private JTextField textFieldDisplay;
    private JCheckBox checkBox1, checkBox2;
    private JRadioButton radioButton1, radioButton2;
    private ButtonGroup radios;
    private JList diasDaSemana;
    private JMenuBar menuBar;
    
    public MoreButtonTypes() {
        /* Definições sobre o Frame*/
        super("Teste com Check Boxes e Radion Buttons");
        this.setBounds(300, 300, 400, 320);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        panelRoot = new JPanel();
        FlowLayout layoutBox = new FlowLayout(FlowLayout.CENTER, 10, 10);
        //GridLayout gridLayoutRoot = new GridLayout(2, 1);
        //panelRoot.setLayout(gridLayoutRoot);
        
        // Criando Barra de Ferramentas
        menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);
        JMenu menuArquivo = new JMenu("Arquivo");
        JMenu menuEditar = new JMenu("Editar");
        JMenu menuSobre = new JMenu("Sobre");
        
        menuBar.add(menuArquivo);
        menuBar.add(menuEditar);
        menuBar.add(menuSobre);
        
        // Adicionando ítens ao menu Arquivo
        JMenuItem menuItemSalvar = new JMenuItem("Salvar");
        JMenuItem menuItemSalvarComo = new JMenuItem("Salvar como");
        
        menuArquivo.add(menuItemSalvar);
        menuArquivo.add(menuItemSalvarComo);
        
        // Adicionando eventos ao menu
        menuItemSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.showSaveDialog(panelRoot);
            }
        });
        
        panelButtons = new JPanel();
        
        checkBox1 = new JCheckBox("Check 1", true);
        checkBox1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (checkBox1.isSelected())
                    textFieldDisplay.setText("Check 1");
                else
                    textFieldDisplay.setText("");
            }
        });
        
        checkBox2 = new JCheckBox("Check 2");
        checkBox2.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (checkBox2.isSelected())
                    textFieldDisplay.setText("Check 2");
                else
                    textFieldDisplay.setText("");
            }
        });
        
        panelButtons.add(checkBox1);
        panelButtons.add(checkBox2);
        
        
        radioButton1 = new JRadioButton("Radio1", true);
        radioButton1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (radioButton1.isSelected())
                    textFieldDisplay.setText("Radio 1");
                else
                    textFieldDisplay.setText("Radio 2");
            }
        });
        
        radioButton2 = new JRadioButton("Radio2");
        radioButton2.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (radioButton2.isSelected())
                    textFieldDisplay.setText("Radio 2");
                else
                    textFieldDisplay.setText("Radio 1");
            }
        });
        
        panelButtons.add(radioButton1);
        panelButtons.add(radioButton2);
        
        radios = new ButtonGroup();
        radios.add(radioButton1);
        radios.add(radioButton2);
        
        panelRoot.add(panelButtons);
        
        textFieldDisplay = new JTextField(30);
        panelRoot.add(textFieldDisplay);
        
        diasDaSemana = new JList(
                new String[] {"Domingo", "Segunda", "Terça", "Quarta",
                "Quinta", "Sexta", "Sábado"}
                );
        diasDaSemana.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                List selecao = diasDaSemana.getSelectedValuesList();
                StringBuilder selecaoString = new StringBuilder();
                
                for (Object object : selecao) {
                    selecaoString.append(object.toString());
                }
                
                textFieldDisplay.setText(selecaoString.toString());
            }
        });
        panelRoot.add(diasDaSemana);
        
        this.getContentPane().add(panelRoot);
    }
    
}
