/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.sun.xml.internal.messaging.saaj.util.TeeInputStream;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import model.ConversaoTemperatura;

/**
 *
 * @author m98567
 */
public class JanelaPrincipal extends JFrame {
    private JTextField textField;
    private JLabel labelCelsius, labelFahrenheit;
    private JButton button;
    
    public JanelaPrincipal() {
        super("Conversor de Temperatura");
        
        textField = new JTextField("0", 10);
        labelCelsius = new JLabel("Celsius");
        
        button = new JButton("Converter");
        button.addActionListener(new eventHandler());
        
        labelFahrenheit = new JLabel("Fahrenheit");
        
        Container c = this.getContentPane();
        c.setLayout(new FlowLayout());
        c.add(textField);
        c.add(labelCelsius);
        c.add(button);
        c.add(labelFahrenheit);
        
        this.setBounds(100, 100, 250, 100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    // Classe Interna = Controller
    public class eventHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            double temperatureCelsius = Double.parseDouble(textField.getText());
            
            model.ConversaoTemperatura conversaoTemperatura = new ConversaoTemperatura(temperatureCelsius);
            double fahrenheit = conversaoTemperatura.converterParaFahrenheit();
            
            labelFahrenheit.setText(String.format("%.2fº Fahrenheit", fahrenheit));
        }
    }
}
