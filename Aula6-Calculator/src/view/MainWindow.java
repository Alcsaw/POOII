/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.sun.xml.internal.messaging.saaj.util.TeeInputStream;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import model.Calculate;

/**
 *
 * @author m98567
 */
public class MainWindow extends JFrame {
    private JMenuBar menuBar;
    private JMenu menuEdit, menuView, menuHelp;
    private JMenuItem mItemSobre;
    private JPanel rootPanel, scientificPanel, standardPanel;
    private JTextField tf_display;
    private JLabel labelScientific, labelStandard;
    private JButton btn_sqrt, btn_1_x, btn_sin, btn_percent, btn_exp, btn_cos,
            btn_x_3, btn_ln, btn_tan, btn_x_2, btn_fatorial, btn_sec;
    private JButton btn_7, btn_8, btn_9, btn_divide, btn_4, btn_5, btn_6,
            btn_multiply, btn_1, btn_2, btn_3, btn_minus, btn_0, btn_dot,
            btn_equals, btn_sum;
    
    public MainWindow() {
        super("Conversor de Temperatura");
        
        
        /* Menu */
        menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);
        menuEdit = new JMenu("Edit");
        menuView = new JMenu("View");
        menuHelp = new JMenu("Help");
        
        
        /* Layout */
        rootPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        
        scientificPanel = new JPanel(new GridLayout(3, 4, 10, 10));
        
        standardPanel = new JPanel(new GridLayout(3, 4, 10, 10));
        
        
        /* Display */
        tf_display = new JTextField("", 24);
        
        
        /* Scientific */
        labelScientific = new JLabel("Scientific");
        
        btn_sqrt = new JButton("sqrt");
        btn_sqrt.addActionListener(new eventHandler());
        
        
        /* Standard */
        labelStandard = new JLabel("Standard");
        
        Container c = this.getContentPane();
        c.setLayout(new FlowLayout());
        c.add(tf_display);
        c.add(labelScientific);
        c.add(btn_sqrt);
        
        this.setBounds(100, 100, 250, 100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    // Classe Interna = Controller
    public class eventHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            double temperatureCelsius = Double.parseDouble(tf_display.getText());
            
            model.Calculate conversaoTemperatura = new Calculate();
            //double fahrenheit = conversaoTemperatura.converterParaFahrenheit();
            
            //labelFahrenheit.setText(String.format("%.2fº Fahrenheit", fahrenheit));
        }
    }
}
