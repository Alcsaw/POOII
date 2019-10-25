/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.sql.Date;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author m98567
 */
public class CadastroDeLivros extends JDialog implements ActionListener, FocusListener {
    
    private JPanel panelRoot, panelLeft, panelRight;
    private JLabel labelID, labelTitulo, labelAutor, labelCategoria, labelDtLancamento;
    private JTextField tfID, tfTitulo, tfAutor, tfCategoria, tfDtLancamento;
    private JButton btnOK, btnCancel;
    private model.Livro livro;

    public CadastroDeLivros(model.Livro l) throws HeadlessException {
        livro = l;
        /* Definições sobre o Frame*/
        this.setBounds(300, 300, 500, 400);
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setModal(true);
        this.setTitle("Cadastro de Livro");
        
        /*Criando Painel principal*/
        panelRoot = new JPanel();
        GridLayout layoutGrid = new GridLayout(1, 2, 10, 15);
        panelRoot.setLayout(layoutGrid);
        
        /*Criando Painel esquerdo*/
        panelLeft = new JPanel();
        /*BoxLayout layoutLeft = new BoxLayout(panelLeft, BoxLayout.Y_AXIS);        
        panelLeft.setLayout(layoutLeft);*/
        GridLayout layoutColumn = new GridLayout(6, 1, 50, 10);
        panelLeft.setLayout(layoutColumn);
        
        /*Criando Componentes do Painel esquerdo*/
        labelID = new JLabel("ID");
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
        tfID.addFocusListener(this);
        
        tfTitulo = new JTextField();
        tfAutor = new JTextField();
        tfCategoria = new JTextField();
        tfDtLancamento = new JTextField();
        
        btnCancel = new JButton("Cancelar");
        btnCancel.addActionListener(this);//adicionando minha própria classe como ouvinte
        
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
        //ação que vai executar quando clicar no botão
        
        if(e.getSource() == btnCancel){
            this.dispose();
        }
        else{
            //só pode ser o botão ok
            
            livro.setTitulo(tfTitulo.getText());
            livro.setAutor(tfAutor.getText());
            livro.setId(Integer.parseInt(tfID.getText()));
            livro.setCategoria(model.EnumCat.valueOf(tfCategoria.getText()));
            livro.setDataPublicacao(Date.valueOf(tfDtLancamento.getText()));

            this.dispose();
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        if(e.getSource() == tfID)
            tfID.setText("0");
    }

    @Override
    public void focusLost(FocusEvent e) {
        
    }
}
