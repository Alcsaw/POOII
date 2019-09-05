/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula5;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author m98567
 */
public class FrameTesteEventos5 extends JFrame {
    private JLabel label1;
    private JButton btn1, btn2, btn3;
    private int count = 0;
    
    public FrameTesteEventos5() {
        super("Testa Eventos 5");
        
        label1 = new JLabel("Total de cliques: 0");
        label1.addMouseListener(new MouseAdapter() {
            //same as MouseListener but with empty implementation of its methods
            @Override
            public void mouseEntered(MouseEvent e) {
                label1.setForeground(Color.blue);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                label1.setForeground(Color.black);
            }
        });
        
        btn1 = new JButton("Clque aqui!");
        btn1.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                count++;
                label1.setText("Total de cliques: " + count);
            }
        });
        
        btn2 = new JButton("Abre teste 4");
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new FrameTesteEventos4();
                frame.setVisible(true);
            }
        });
        
        btn3 = new JButton("Abre modal padrão");
        btn3.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(rootPane, "Avisado, hein!", "Aviso!", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        this.setContentPane(panel);
        
        panel.add(label1);
        panel.add(btn1);
        panel.add(btn2);
        panel.add(btn3);
        
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
