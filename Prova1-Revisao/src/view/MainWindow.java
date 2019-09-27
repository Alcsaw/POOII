package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import model.ControleJogoVelha;

/**
 *
 * @author m98567
 */
public class MainWindow extends JFrame {
    private JPanel panelRoot, panelControls, panelButtons, panelFooter;
    private ButtonGroup radioBtnGroupPlayers;
    private JRadioButton radioPlayerX, radioPlayerO;
    private JButton btnNewGame, buttonsAray[];
    private JTextField tf_display;
    
    model.ControleJogoVelha controleJogo = new ControleJogoVelha();
    
    public MainWindow() {
        super("Tic Tac Toe");
        
        /* Layout */
        panelRoot = new JPanel(new BorderLayout(10, 10));
        
        panelControls = new JPanel(new FlowLayout());
        panelRoot.add(panelControls, BorderLayout.PAGE_START);
        
        panelButtons = new JPanel(new GridLayout(3, 3, 5, 5));
        panelRoot.add(panelButtons, BorderLayout.CENTER);
        
        panelFooter = new JPanel(new FlowLayout());
        panelRoot.add(panelFooter, BorderLayout.PAGE_END);
        
        this.setContentPane(panelRoot);
        
        this.setBounds(200, 100, 400, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        /* Controls */
        btnNewGame = new JButton("Novo Jogo");
        btnNewGame.addActionListener(new eventHandlerMovement());
        panelControls.add(btnNewGame);
        
        radioPlayerX = new JRadioButton("Jogador X", true);
        radioPlayerX.setEnabled(false);
        //radioPlayerX.setActionCommand("X");
        
        radioPlayerO = new JRadioButton("Jogador O");
        radioPlayerO.setEnabled(false);
        //radioPlayerO.setActionCommand("O");
        
        radioBtnGroupPlayers = new ButtonGroup();
        radioBtnGroupPlayers.add(radioPlayerX);
        radioBtnGroupPlayers.add(radioPlayerO);
        
        panelControls.add(radioPlayerX);
        panelControls.add(radioPlayerO);
        
        
        /* Buttons */
        buttonsAray = new JButton[9];
        for (int i = 0; i < buttonsAray.length; i++) {
            buttonsAray[i] = new JButton(Integer.toString(i + 1));
            buttonsAray[i].addActionListener(new eventHandlerMovement());
            panelButtons.add(buttonsAray[i]);
        }
        
        
        /* Display */
        tf_display = new JTextField(30);
        tf_display.setEditable(false);
        tf_display.setBackground(Color.white);
        panelFooter.add(tf_display);
        
        //this.pack();
        btnNewGame.doClick();
    }
    
    
    // Classe Interna = Controller
    public class eventHandlerMovement implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("source:" + e.getActionCommand());
            
            if (e.getActionCommand().equals("Novo Jogo")) {
                controleJogo.iniciaPartida();
                // retornar interface original
                resetBoard();
            } else {
                int index = Integer.parseInt(e.getActionCommand()) - 1;
                //System.out.println("SELEÇÃO: " + radioBtnGroupPlayers.getSelection().getActionCommand());
                
                buttonsAray[index].setEnabled(false);
                
                if (radioPlayerX.isSelected()) {
                    radioPlayerO.setSelected(true);
                    buttonsAray[index].setText("X");
                    controleJogo.adicionaMovimento('X', index);
                } else {
                    radioPlayerX.setSelected(true);
                    buttonsAray[index].setText("O");
                    controleJogo.adicionaMovimento('O', index);
                }
                
                char statusPartida = controleJogo.avaliaPartida();
                
                if (statusPartida == 'A') {
                    tf_display.setText("Partida em andamento (" +
                            controleJogo.getDuracaoPartida() + "s)");
                } else {
                    for (JButton button : buttonsAray) {
                        button.setEnabled(false);
                    }
                    
                    tf_display.setText("Jogador " + statusPartida + " venceu ("
                            + controleJogo.getDuracaoPartida() + "s)");
                }
            }
        }
        
        private void resetBoard() {
            for (int i = 0; i < buttonsAray.length; i++) {
                buttonsAray[i].setEnabled(true);
                buttonsAray[i].setText(Integer.toString(i + 1));
            }
            tf_display.setText("");
        }
    }
}
