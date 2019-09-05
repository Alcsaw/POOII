/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula1;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author m98567
 */
public class CadastroDeLivros extends JFrame implements ActionListener {
    
    private JPanel panelRoot, panelLeft, panelRight;
    private JLabel labelID, labelTitulo, labelAutor, labelCategoria, labelDtLancamento;
    private JTextField tfID, tfTitulo, tfAutor, tfCategoria, tfDtLancamento;
    private JButton btnOK, btnCancel;

    public CadastroDeLivros() throws HeadlessException {
        /* Definições sobre o Frame*/
        this.setBounds(300, 300, 500, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        /*Criando Painel principal*/
        panelRoot = new JPanel();
        GridLayout layoutGrid = new GridLayout(1, 2, 0, 15);
        panelRoot.setLayout(layoutGrid);
        
        /*Criando Painel esquerdo*/
        panelLeft = new JPanel();
        /*BoxLayout layoutLeft = new BoxLayout(panelLeft, BoxLayout.Y_AXIS);        
        panelLeft.setLayout(layoutLeft);*/
        GridLayout layoutColumn = new GridLayout(6, 1, 50, 10);
        panelLeft.setLayout(layoutColumn);
        
        /*Criando Componentes do Painel esquerdo*/
        labelID = new JLabel("ID", JLabel.RIGHT);
        labelTitulo = new JLabel("Título");
        labelAutor = new JLabel("Autor");
        labelCategoria = new JLabel("Categoria");
        labelDtLancamento = new JLabel("Data de lançamento");
        
        btnOK = new JButton("OK");
        btnOK.addActionListener(this);
        
        /*Populando Painel esquerdo*/
        panelLeft.add(labelID);
        panelLeft.add(labelTitulo);
        panelLeft.add(labelAutor);
        panelLeft.add(labelCategoria);
        panelLeft.add(labelDtLancamento);
        panelLeft.add(btnOK);
        
        /*------------------------------------*/
        
        /*Criando Painel direito*/
        panelRight = new JPanel();
        /*BoxLayout layoutRight = new BoxLayout(panelRight, BoxLayout.Y_AXIS);        
        panelRight.setLayout(layoutRight);*/
        panelRight.setLayout(layoutColumn);
        
        /*Criando Componentes do Painel esquerdo*/
        tfID = new JTextField();
        tfTitulo = new JTextField();
        tfAutor = new JTextField();
        tfCategoria = new JTextField();
        tfDtLancamento = new JTextField();
        
        btnCancel = new JButton("Cancelar");
        btnCancel.addActionListener(this);
        
        /*Populando painel direito (textFields)*/
        panelRight.add(tfID);
        panelRight.add(tfTitulo);
        panelRight.add(tfAutor);
        panelRight.add(tfCategoria);
        panelRight.add(tfDtLancamento);
        panelRight.add(btnCancel);
        
        
        this.setContentPane(panelRoot);
        panelRoot.add(panelLeft);
        panelRoot.add(panelRight);
        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Ação executada quando clicar em algum ActionListener.
        // Todos eventos são tratados aqui.
        if (e.getSource() == btnCancel) {
            this.dispose();
        } else {
            //Livro livro = new Livro(2, "A Tríade do Tempo", "Cristian Barbosa",
              //                      EnumCategoria.AUTO_AJUDA, new Date("2000-01-01"));
            Livro livro = new Livro(
                    Integer.parseInt(tfID.getText()),
                    tfTitulo.getText(),
                    tfAutor.getText(),
                    EnumCategoria.valueOf(tfCategoria.getText()),
                    new Date(tfDtLancamento.getText())
            );
            
            ArrayList<Livro> listaLivros = new ArrayList<>();
            listaLivros.add(livro);
            
            JOptionPane.showMessageDialog(this, "Livro " + livro.getTitulo()
                    + "cadastrado com sucesso.");
        }
    }
    
    
}
