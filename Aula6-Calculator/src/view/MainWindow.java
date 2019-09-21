/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.sun.xml.internal.messaging.saaj.util.TeeInputStream;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import model.Calculator;

/**
 *
 * @author m98567
 */
public class MainWindow extends JFrame {
    private JMenuBar menuBar;
    private JMenu menuEdit, menuView, menuHelp;
    private JMenuItem mItemSobre;
    private JPanel panelRoot, panelDisplay, panelButtons, panelScientific,
            panelStandard;
    private JTextField tf_display;
    private TitledBorder borderScientific, borderStandard;
    private JButton btnSqrt, btn1X, btnSin, btnPercent, btnExp, btnCos,
            btnX3, btnLn, btnTan, btnX2, btnFactorial, btnSec;
    private JButton btn7, btn8, btn9, btnDivide, btn4, btn5, btn6, btnMultiply,
            btn1, btn2, btn3, btnMinus, btn0, btnDot, btnEquals, btnSum;
    
    public MainWindow() {
        super("Calculator");
        
        
        /* Menu */
        menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);
        
        menuEdit = new JMenu("Edit");
        menuBar.add(menuEdit);
        menuView = new JMenu("View");
        menuBar.add(menuView);
        menuHelp = new JMenu("Help");
        menuBar.add(menuHelp);
                
        
        /* Layout */
        //rootPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        panelRoot = new JPanel(new BorderLayout(10, 10));
        
        panelDisplay = new JPanel(new FlowLayout());
        //rootPanel.add(displayPanel);
        panelRoot.add(panelDisplay, BorderLayout.PAGE_START);
        
        panelButtons = new JPanel(new GridLayout(1, 2, 5, 5));
        //rootPanel.add(buttonsPanel);
        panelRoot.add(panelButtons, BorderLayout.CENTER);
        
        panelScientific = new JPanel(new GridLayout(4, 3, 5, 5));
        borderScientific = BorderFactory.createTitledBorder("Scientific");
        panelScientific.setBorder(borderScientific);
        panelButtons.add(panelScientific);
        
        panelStandard = new JPanel(new GridLayout(4, 4, 5, 5));
        borderStandard = BorderFactory.createTitledBorder("Standard");
        panelStandard.setBorder(borderStandard);
        panelButtons.add(panelStandard);
        
        this.setContentPane(panelRoot);
        
        this.setBounds(200, 100, 450, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        /* Display */
        tf_display = new JTextField(40);
        tf_display.setEditable(false);
        tf_display.setBackground(Color.white);
        tf_display.setAlignmentX(RIGHT_ALIGNMENT);
        panelDisplay.add(tf_display);
        
        
        /* Scientific */
        //labelScientific = new JLabel("Scientific");
        
        btnSqrt = new JButton("sqrt");
        panelScientific.add(btnSqrt);
        btnSqrt.addActionListener(new eventHandler());
        
        btn1X = new JButton("1/x");
        panelScientific.add(btn1X);
        
        btnSin = new JButton("sin");
        panelScientific.add(btnSin);
        
        btnPercent = new JButton("%");
        panelScientific.add(btnPercent);
        
        btnExp = new JButton("Exp");
        panelScientific.add(btnExp);
        
        btnCos = new JButton("cos");
        panelScientific.add(btnCos);
        
        btnX3 = new JButton("x^3");
        panelScientific.add(btnX3);
        
        btnLn = new JButton("ln");
        panelScientific.add(btnLn);
        
        btnTan = new JButton("tan");
        panelScientific.add(btnTan);
        
        btnX2 = new JButton("x^2");
        panelScientific.add(btnX2);
        
        btnFactorial = new JButton("n!");
        panelScientific.add(btnFactorial);
        
        btnSec = new JButton("%");
        panelScientific.add(btnSec);
        
        
        /* Standard */
        //labelStandard = new JLabel("Standard");
        btn7 = new JButton("7");
        panelStandard.add(btn7);
        btn7.addActionListener(new eventHandler());
        
        btn8 = new JButton("8");
        panelStandard.add(btn8);
        btn8.addActionListener(new eventHandler());
        
        btn9 = new JButton("9");
        panelStandard.add(btn9);
        btn9.addActionListener(new eventHandler());
        
        btnDivide = new JButton("/");
        panelStandard.add(btnDivide);
        btnDivide.addActionListener(new eventHandler());
        
        btn4 = new JButton("4");
        panelStandard.add(btn4);
        btn4.addActionListener(new eventHandler());
        
        btn5 = new JButton("5");
        panelStandard.add(btn5);
        btn5.addActionListener(new eventHandler());
        
        btn6 = new JButton("6");
        panelStandard.add(btn6);
        btn6.addActionListener(new eventHandler());
        
        btnMultiply = new JButton("*");
        panelStandard.add(btnMultiply);
        btnMultiply.addActionListener(new eventHandler());
        
        btn1 = new JButton("1");
        panelStandard.add(btn1);
        btn1.addActionListener(new eventHandler());
        
        btn2 = new JButton("2");
        panelStandard.add(btn2);
        btn2.addActionListener(new eventHandler());
        
        btn3 = new JButton("3");
        panelStandard.add(btn3);
        btn3.addActionListener(new eventHandler());
        
        btnMinus = new JButton("-");
        panelStandard.add(btnMinus);
        btnMinus.addActionListener(new eventHandler());
        
        btn0 = new JButton("0");
        panelStandard.add(btn0);
        btn0.addActionListener(new eventHandler());
        
        btnDot = new JButton(".");
        panelStandard.add(btnDot);
        btnDot.addActionListener(new eventHandler());
        
        btnEquals = new JButton("=");
        panelStandard.add(btnEquals);
        btnEquals.addActionListener(new eventHandler());
        
        btnSum = new JButton("+");
        panelStandard.add(btnSum);
        btnSum.addActionListener(new eventHandler());
        
    }
    
    // Classe Interna = Controller
    public class eventHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("source:" + e.getActionCommand());
            model.Calculator calc = new Calculator();
            
            ArrayList<String> operations = new ArrayList<>();
            
            if (e.getActionCommand().equals("=")) {
                try {
                    calc.doCalculation(operations);
                } catch (ArithmeticException | Calculator.OperationFormatException ex) {
                    Logger.getLogger(MainWindow.class.getName()).log(Level.WARNING, null, ex);
                }
            } else {
                tf_display.setText(tf_display.getText() + e.getActionCommand());
                operations.add(e.getActionCommand());
            }
            
            try {
                Integer.parseInt(e.getActionCommand());
                
            } catch (NumberFormatException notAnInteger) {
                switch (e.getActionCommand()) {
                    case "/":
                        System.out.println("Divide");
                        break;
                    case "*":
                        System.out.println("Multiply");
                        break;
                    case "-":
                        System.out.println("Subtract");
                        break;
                    case "+":
                        System.out.println("Sum");
                        break;
                    case ".":
                        System.out.println("Dot");
                        break;
                    case "=":
                        System.out.println("Equals");
                        break;
                    default:
                        System.out.println("UNKNOWN: " + e.getActionCommand());
                        break;
                }
                
            }
//            double temperatureCelsius = Double.parseDouble(tf_display.getText());
            
            
            //double fahrenheit = conversaoTemperatura.converterParaFahrenheit();
            
            //labelFahrenheit.setText(String.format("%.2fº Fahrenheit", fahrenheit));
        }
    }
}
