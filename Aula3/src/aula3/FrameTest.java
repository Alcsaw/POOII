/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula3;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author m98567
 */
public class FrameTest extends JFrame {
    
    private JButton b1, b2, b3, b4;
    private JTextField tf1, tf2;
    private JLabel label1;
    
    public FrameTest() {
        super("Teste");
        
        JPanel container = new JPanel();
        
        //FlowLayout layout = new FlowLayout(FlowLayout.LEFT);
        //BoxLayout layout = new BoxLayout(container, BoxLayout.X_AXIS);
        BorderLayout layout = new BorderLayout();
        container.setLayout(layout);
        
        label1 = new JLabel("Labelllllllll");
        container.add(label1, BorderLayout.PAGE_START);
        
        b1 = new JButton("Clique aqui!");
        //container.add(b1);
        container.add(b1, BorderLayout.LINE_END);
        
        b2 = new JButton("Ou aqui!");
        //container.add(b2);
        container.add(b2, BorderLayout.PAGE_END);
        
        tf1 = new JTextField("text field");
        //container.add(tf1);
        container.add(tf1, BorderLayout.PAGE_START);
        
        
        
        this.setContentPane(container);
        
        this.setBounds(300, 300, 200, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
