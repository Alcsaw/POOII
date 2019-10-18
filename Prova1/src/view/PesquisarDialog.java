package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import model.Banco;
import model.Conta;
import model.EnumConta;

/**
 *
 * @author m98567
 */
public class PesquisarDialog extends JDialog {
    private JPanel panelRoot, panelInput, panelButtons;
    private JTextField textFieldNome;
    private JLabel labelNome;
    private JButton buttonOK, buttonCancel;
    private MainWindow parentWindow;
    
    //model.Banco modelBanco = new Banco();
    
    public PesquisarDialog( MainWindow parent, Banco modelBanco ) {
        parentWindow = parent;  // gets the parent's reference
        
        /*Criando Painel principal*/
        panelRoot = new JPanel(new GridLayout(3, 1, 60, 2));    //gap sendo ignorado com sucesso!
        
        panelInput = new JPanel();
        BorderLayout layoutBox = new BorderLayout(10, 1);
        panelInput.setLayout(layoutBox);
        
        panelRoot.add(panelInput);
        
        panelButtons = new JPanel();
        panelRoot.add(panelButtons);
        
        labelNome = new JLabel("Informe o nome");
        panelInput.add(labelNome, BorderLayout.PAGE_START);
        
        textFieldNome = new JTextField();
        panelInput.add(textFieldNome, BorderLayout.PAGE_END);
        
        buttonOK = new JButton("OK");
        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                String nome = textFieldNome.getText();
                
                try {
                    Conta conta = modelBanco.buscarConta(nome);
                    
                    String nomeCliente = conta.getNomeCliente();
                    String saldo = Float.toString(conta.getSaldo());

                    parentWindow.getTextFieldNome().setText(nome);
                    parentWindow.getTextFieldSaldo().setText(saldo);

                    if (conta.getTipoConta() == EnumConta.POUPANÇA)
                        parentWindow.getRadioButtonPoupanca().setSelected(true);
                    else
                        parentWindow.getRadioButtonCorente().setSelected(true);
                    
                } catch (IndexOutOfBoundsException contaInexistente) {
                    System.out.println("WARNING: Conta inexistente");
                    WarningDialog contaInexistenteDialog = new WarningDialog("busca");
                    contaInexistenteDialog.setVisible(true);
                }
                
                
            }
        });
        panelButtons.add(buttonOK);
        
        buttonCancel = new JButton("Cancelar");
        buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        panelButtons.add(buttonCancel);
        
                
        this.setContentPane(panelRoot);
        this.pack();
    }
}
