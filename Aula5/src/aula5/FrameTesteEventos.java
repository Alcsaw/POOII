/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula5;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author m98567
 */
public class FrameTesteEventos extends JFrame implements ActionListener {
    private JLabel label1;
    private JButton btn1;
    private int count = 0;
    
    public FrameTesteEventos() {
        super("Testa Eventos 1");
        
        label1 = new JLabel("Total de cliques: 0");
        btn1 = new JButton("Clque aqui!");
        btn1.addActionListener(this);
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        this.setContentPane(panel);
        
        panel.add(label1);
        panel.add(btn1);
        
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        count++;
        this.label1.setText("Total de cliques: " + count);
    }
}
