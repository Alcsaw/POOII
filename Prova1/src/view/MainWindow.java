package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import model.Banco;
import model.Conta;
import model.EnumConta;

/**
 *
 * @author m98567
 */
public class MainWindow extends JFrame {
    private JPanel panelRoot, panelInputs, panelButtons;
    private JTextField tf_nomeCliente, tf_saldo;
    private JLabel labelNomeCliente, labelSaldo, labelTipoConta;
    private ButtonGroup radioBtnGroupTipoConta;
    private JRadioButton radioPoupanca, radioCorrente;
    private JButton btnAdicionar, btnTotais, btnPesquisar, btnFechar;
    
    model.Conta modelConta = new Conta();
    model.Banco modelBanco = new Banco();
    
    public MainWindow getThis() {
        return this;
    }
    
    public MainWindow() {
        super("Banco dos Programadores");
        
        /* Layout */
        panelRoot = new JPanel(new BorderLayout(20, 5));
        
        //tentar aplicar gridLayout para ter gap nas laterais
        //panelInputs = new JPanel(new GridLayout());
        panelInputs = new JPanel();
        BoxLayout boxLayout = new BoxLayout(panelInputs, BoxLayout.Y_AXIS);
        panelInputs.setLayout(boxLayout);
        panelRoot.add(panelInputs, BorderLayout.CENTER);
        
        panelButtons = new JPanel(new GridLayout(2, 4));
        panelRoot.add(panelButtons, BorderLayout.PAGE_END);
        
        this.setContentPane(panelRoot);
        
        this.setBounds(200, 100, 350, 240);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        /* Inputs */
        labelNomeCliente = new JLabel("Nome do cliente:");
        panelInputs.add(labelNomeCliente);
        
        tf_nomeCliente = new JTextField(30);
        panelInputs.add(tf_nomeCliente);
        
        labelSaldo = new JLabel("Saldo:");
        panelInputs.add(labelSaldo);
        
        tf_saldo = new JTextField();
        panelInputs.add(tf_saldo);
        
        labelTipoConta = new JLabel("Tipo de conta:");
        panelInputs.add(labelTipoConta);
        
        radioPoupanca = new JRadioButton("Poupança");
        radioCorrente = new JRadioButton("Corrente", true);
        
        radioBtnGroupTipoConta = new ButtonGroup();
        radioBtnGroupTipoConta.add(radioPoupanca);
        radioBtnGroupTipoConta.add(radioCorrente);
        
        panelInputs.add(radioPoupanca);
        panelInputs.add(radioCorrente);
        
        
        /* Buttons */
        btnAdicionar = new JButton("Adicionar");
        btnAdicionar.addActionListener(new EventHandlerButtons());
        panelButtons.add(btnAdicionar);
        
        btnTotais = new JButton("Totais");
        btnTotais.addActionListener(new EventHandlerButtons());
        panelButtons.add(btnTotais);
        
        btnPesquisar = new JButton("Pesquisar");
        btnPesquisar.addActionListener(new EventHandlerButtons());
        panelButtons.add(btnPesquisar);
        
        btnFechar = new JButton("Fechar");
        btnFechar.addActionListener(new EventHandlerButtons());
        panelButtons.add(btnFechar);

        //this.pack();
    }
    
    public JTextField getTextFieldNome() {
        return tf_nomeCliente;
    }
    
    public JTextField getTextFieldSaldo() {
        return tf_saldo;
    }
    
    public JRadioButton getRadioButtonPoupanca() {
        return radioPoupanca;
    }
    
    public JRadioButton getRadioButtonCorente() {
        return radioCorrente;
    }
    
            
    // Classe Interna = Controller
    public class EventHandlerButtons implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("source:" + e.getActionCommand());
            
            switch (e.getActionCommand()) {
                case "Adicionar":
                    String nomeCliente = tf_nomeCliente.getText();
                    String saldo = tf_saldo.getText();
                    if (modelConta.validarNomeCliente(nomeCliente) && modelConta.validarSaldo(saldo)) {
                        
                        EnumConta tipo = EnumConta.CORRENTE;
                        
                        if (radioPoupanca.isSelected()) {
                            tipo = EnumConta.POUPANÇA;
                        }
                        
                        modelBanco.addConta(nomeCliente, tipo, Float.parseFloat(saldo));
                        clearScreen();
                    } else {
                        WarningDialog warningDialog = new WarningDialog("add");
                        warningDialog.setVisible(true);
                    }
                    break;
                case "Totais":
                    TotaisDialog totaisDialog = new TotaisDialog(modelBanco);
                    totaisDialog.setVisible(true);
                    break;
                case "Pesquisar":
                    PesquisarDialog pesquisarDialog = new PesquisarDialog(getThis(), modelBanco);
                    pesquisarDialog.setVisible(true);
                    break;
                case "Fechar":
                    dispose();
                    break;
                default:
                    System.out.println("ERRO(?)");
                    break;
            }
            
            /* else {
            int index = Integer.parseInt(e.getActionCommand()) - 1;
            //System.out.println("SELEÇÃO: " + radioBtnGroupPlayers.getSelection().getActionCommand());
            buttonsAray[index].setEnabled(false);
            if (radioPoupanca.isSelected()) {
            radioCorrente.setSelected(true);
            buttonsAray[index].setText("X");
            controleJogo.adicionaMovimento('X', index);
            } else {
            radioPoupanca.setSelected(true);
            buttonsAray[index].setText("O");
            controleJogo.adicionaMovimento('O', index);
            }
            char statusPartida = controleJogo.avaliaPartida();
            if (statusPartida == 'A') {
            tf_nomeCliente.setText("Partida em andamento (" +
            controleJogo.getDuracaoPartida() + "s)");
            } else {
            for (JButton button : buttonsAray) {
            button.setEnabled(false);
            }
            tf_nomeCliente.setText("Jogador " + statusPartida + " venceu ("
            + controleJogo.getDuracaoPartida() + "s)");
            }
            }*/
        }
        
        private void clearScreen() {
            tf_nomeCliente.setText("");
            tf_saldo.setText("");
            
            radioCorrente.setSelected(true);
        }
    }
}
