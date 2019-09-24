package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import model.ScientificCalculator;
import model.StandardCalculator;

/**
 *
 * @author m98567
 * @github Alcsaw
 */
public class MainWindow extends JFrame {
    private JMenuBar menuBar;
    private JMenu menuEdit, menuView, menuHelp;
    private JMenuItem mItemAbout, mItemScientificMode, mItemLogs;
    private JPanel panelRoot, panelDisplay, panelButtons, panelScientific,
            panelStandard;
    private JTextField tf_display;
    private TitledBorder borderScientific, borderStandard;
    private JButton btnSqrt, btn1X, btnSin, btnPercent, btnExp, btnCos,
            btnX3, btnLn, btnTan, btnX2, btnFactorial, btnSec;
    private JButton btn7, btn8, btn9, btnDivide, btn4, btn5, btn6, btnMultiply,
            btn1, btn2, btn3, btnMinus, btn0, btnDot, btnEquals, btnSum;
    
    ArrayList<String> operations = new ArrayList<>();
    model.StandardCalculator stdCalc = new StandardCalculator();
    model.ScientificCalculator sciCalc = new ScientificCalculator();
    boolean equalsPressed = false;
    
    public MainWindow() {
        super("Calculator");
        
        
        /* Menu */
        menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);
        
        //menuEdit = new JMenu("Edit");
        //menuBar.add(menuEdit);
        menuView = new JMenu("View");
        menuBar.add(menuView);
        menuHelp = new JMenu("Help");
        menuBar.add(menuHelp);
        
        
        // Scientific Mode Chooser
        mItemScientificMode = new JCheckBoxMenuItem("Scientific Mode", true);
        menuView.add(mItemScientificMode);
        mItemScientificMode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelScientific.setVisible(mItemScientificMode.isSelected());
                
                if (mItemScientificMode.isSelected()) {
                    panelButtons.setLayout(new GridLayout(1, 2, 5, 5));
                } else {
                    panelButtons.setLayout(new BoxLayout(panelButtons, BoxLayout.X_AXIS));
                    //tf_display = new JTextField(20);  //Y doesn't it work?
                }
                
            }
        });

        
        // Menu Item About
        mItemAbout = new JMenuItem("About");
        menuHelp.add(mItemAbout);
        mItemAbout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AboutDialog about = new AboutDialog();
                
            }
        });
        
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
        btnSqrt.addActionListener(new eventHandlerScientific());
        
        btn1X = new JButton("1/x");
        panelScientific.add(btn1X);
        btn1X.addActionListener(new eventHandlerScientific());
        
        btnSin = new JButton("sin");
        panelScientific.add(btnSin);
        btnSin.addActionListener(new eventHandlerScientific());
        
        btnPercent = new JButton("%");
        panelScientific.add(btnPercent);
        btnPercent.addActionListener(new eventHandlerScientific());
        
        btnExp = new JButton("Exp");
        panelScientific.add(btnExp);
        btnExp.addActionListener(new eventHandlerScientific());
        
        btnCos = new JButton("cos");
        panelScientific.add(btnCos);
        btnCos.addActionListener(new eventHandlerScientific());
        
        btnX3 = new JButton("x^3");
        panelScientific.add(btnX3);
        btnX3.addActionListener(new eventHandlerScientific());
        
        btnLn = new JButton("ln");
        panelScientific.add(btnLn);
        btnLn.addActionListener(new eventHandlerScientific());
        
        btnTan = new JButton("tan");
        panelScientific.add(btnTan);
        btnTan.addActionListener(new eventHandlerScientific());
        
        btnX2 = new JButton("x^2");
        panelScientific.add(btnX2);
        btnX2.addActionListener(new eventHandlerScientific());
        
        btnFactorial = new JButton("n!");
        panelScientific.add(btnFactorial);
        btnFactorial.addActionListener(new eventHandlerScientific());
        
        btnSec = new JButton("%");
        panelScientific.add(btnSec);
        btnSec.addActionListener(new eventHandlerScientific());
        
        
        /* Standard */
        //labelStandard = new JLabel("Standard");
        btn7 = new JButton("7");
        panelStandard.add(btn7);
        btn7.addActionListener(new eventHandlerStandard());
        
        btn8 = new JButton("8");
        panelStandard.add(btn8);
        btn8.addActionListener(new eventHandlerStandard());
        
        btn9 = new JButton("9");
        panelStandard.add(btn9);
        btn9.addActionListener(new eventHandlerStandard());
        
        btnDivide = new JButton("/");
        panelStandard.add(btnDivide);
        btnDivide.addActionListener(new eventHandlerStandard());
        
        btn4 = new JButton("4");
        panelStandard.add(btn4);
        btn4.addActionListener(new eventHandlerStandard());
        
        btn5 = new JButton("5");
        panelStandard.add(btn5);
        btn5.addActionListener(new eventHandlerStandard());
        
        btn6 = new JButton("6");
        panelStandard.add(btn6);
        btn6.addActionListener(new eventHandlerStandard());
        
        btnMultiply = new JButton("*");
        panelStandard.add(btnMultiply);
        btnMultiply.addActionListener(new eventHandlerStandard());
        
        btn1 = new JButton("1");
        panelStandard.add(btn1);
        btn1.addActionListener(new eventHandlerStandard());
        
        btn2 = new JButton("2");
        panelStandard.add(btn2);
        btn2.addActionListener(new eventHandlerStandard());
        
        btn3 = new JButton("3");
        panelStandard.add(btn3);
        btn3.addActionListener(new eventHandlerStandard());
        
        btnMinus = new JButton("-");
        panelStandard.add(btnMinus);
        btnMinus.addActionListener(new eventHandlerStandard());
        
        btn0 = new JButton("0");
        panelStandard.add(btn0);
        btn0.addActionListener(new eventHandlerStandard());
        
        btnDot = new JButton(".");
        panelStandard.add(btnDot);
        btnDot.addActionListener(new eventHandlerStandard());
        
        btnEquals = new JButton("=");
        panelStandard.add(btnEquals);
        btnEquals.addActionListener(new eventHandlerStandard());
        
        btnSum = new JButton("+");
        panelStandard.add(btnSum);
        btnSum.addActionListener(new eventHandlerStandard());
        
    }
    
    // Classe Interna = Controller
    public class eventHandlerStandard implements ActionListener {
        // TODO: Add priority checks
        
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("source:" + e.getActionCommand());
            boolean isOperator = false;
            
            if (e.getActionCommand().equals("=")) {
                System.out.println("Equals");
                
                double result = -666;
                equalsPressed = true;
                
                try {
                    result = stdCalc.doCalculation(operations);
                } catch (ArithmeticException | StandardCalculator.OperationFormatException ex) {
                    Logger.getLogger(MainWindow.class.getName()).log(Level.WARNING, null, ex);
                }
                
                tf_display.setText(Double.toString(result));
                operations.clear();
                operations.add(Double.toString(result));
            } else {
                
                try {
                    Integer.parseInt(e.getActionCommand());
                } catch (NumberFormatException notAnInteger) {
                    switch (e.getActionCommand()) {
                        case "/":
                            System.out.println("Divide");
                            isOperator = true;
                            break;
                        case "*":
                            System.out.println("Multiply");
                            isOperator = true;
                            break;
                        case "-":
                            System.out.println("Subtract");
                            isOperator = true;
                            break;
                        case "+":
                            System.out.println("Sum");
                            isOperator = true;
                            break;
                        case ".":
                            System.out.println("Dot");
                            break;
                        /*case "=":
                            System.out.println("Equals");
                            break;*/
                        default:
                            System.out.println("UNKNOWN: " + e.getActionCommand());
                            break;
                    }
                }
                
                if (equalsPressed && isOperator) {
                    // If we want to perform an operation over the previous result, keep it.
                    tf_display.setText(tf_display.getText() + e.getActionCommand());
                    equalsPressed = false;
                } else if (equalsPressed) {
                    // But if we want to perform a new calculation, clears the previous result.
                    tf_display.setText(e.getActionCommand());
                    equalsPressed = false;
                    operations.clear();
                } else {
                    tf_display.setText(tf_display.getText() + e.getActionCommand());
                }

                operations.add(e.getActionCommand());
            }
//            double temperatureCelsius = Double.parseDouble(tf_display.getText());
            
            
            //double fahrenheit = conversaoTemperatura.converterParaFahrenheit();
            
            //labelFahrenheit.setText(String.format("%.2fº Fahrenheit", fahrenheit));
        }
    }
    
    
    public class eventHandlerScientific implements ActionListener {
        // TODO: Add priority checks

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("source:" + e.getActionCommand());
            
            double result = -666;
            
            switch (e.getActionCommand()) {
                case "sqrt":
                    System.out.println("sqrt");
            
                    try {
                        result = sciCalc.doCalculation("sqrt", operations);
                    } catch (UnsupportedOperationException | ScientificCalculator.OperationFormatException ex) {
                        Logger.getLogger(MainWindow.class.getName()).log(Level.WARNING, null, ex);
                        System.out.println("ERROR! " + ex.getMessage());
                    }
                    equalsPressed = true;
                    break;
                case "1/x":
                    System.out.println("1/x");
            
                    try {
                        result = sciCalc.doCalculation("1/x", operations);
                    } catch (UnsupportedOperationException | ScientificCalculator.OperationFormatException ex) {
                        Logger.getLogger(MainWindow.class.getName()).log(Level.WARNING, null, ex);
                        System.out.println("ERROR! " + ex.getMessage());
                    }
                    equalsPressed = true;
                    break;
                case "sin":
                    System.out.println("sin");
            
                    try {
                        result = sciCalc.doCalculation("sin", operations);
                    } catch (UnsupportedOperationException | ScientificCalculator.OperationFormatException ex) {
                        Logger.getLogger(MainWindow.class.getName()).log(Level.WARNING, null, ex);
                        System.out.println("ERROR! " + ex.getMessage());
                    }
                    equalsPressed = true;
                    break;
                case "%":
                    System.out.println("%");
            
                    try {
                        result = sciCalc.doCalculation("%", operations);
                    } catch (UnsupportedOperationException | ScientificCalculator.OperationFormatException ex) {
                        Logger.getLogger(MainWindow.class.getName()).log(Level.WARNING, null, ex);
                        System.out.println("ERROR! " + ex.getMessage());
                    }
                    equalsPressed = true;
                    break;
                case "Exp":
                    System.out.println("Exp");
            
                    try {
                        result = sciCalc.doCalculation("Exp", operations);
                    } catch (UnsupportedOperationException | ScientificCalculator.OperationFormatException ex) {
                        Logger.getLogger(MainWindow.class.getName()).log(Level.WARNING, null, ex);
                        System.out.println("ERROR! " + ex.getMessage());
                    }
                    equalsPressed = true;
                    break;
                case "cos":
                    System.out.println("cos");
            
                    try {
                        result = sciCalc.doCalculation("cos", operations);
                    } catch (UnsupportedOperationException | ScientificCalculator.OperationFormatException ex) {
                        Logger.getLogger(MainWindow.class.getName()).log(Level.WARNING, null, ex);
                        System.out.println("ERROR! " + ex.getMessage());
                    }
                    equalsPressed = true;
                    break;
                case "x^3":
                    System.out.println("x^3");
            
                    try {
                        result = sciCalc.doCalculation("x^3", operations);
                    } catch (UnsupportedOperationException | ScientificCalculator.OperationFormatException ex) {
                        Logger.getLogger(MainWindow.class.getName()).log(Level.WARNING, null, ex);
                        System.out.println("ERROR! " + ex.getMessage());
                    }
                    equalsPressed = true;
                    break;
                case "ln":
                    System.out.println("ln");
            
                    try {
                        result = sciCalc.doCalculation("ln", operations);
                    } catch (UnsupportedOperationException | ScientificCalculator.OperationFormatException ex) {
                        Logger.getLogger(MainWindow.class.getName()).log(Level.WARNING, null, ex);
                        System.out.println("ERROR! " + ex.getMessage());
                    }
                    equalsPressed = true;
                    break;
                case "tan":
                    System.out.println("tan");
            
                    try {
                        result = sciCalc.doCalculation("tan", operations);
                    } catch (UnsupportedOperationException | ScientificCalculator.OperationFormatException ex) {
                        Logger.getLogger(MainWindow.class.getName()).log(Level.WARNING, null, ex);
                        System.out.println("ERROR! " + ex.getMessage());
                    }
                    equalsPressed = true;
                    break;
                case "x^2":
                    System.out.println("x^2");
            
                    try {
                        result = sciCalc.doCalculation("x^2", operations);
                    } catch (UnsupportedOperationException | ScientificCalculator.OperationFormatException ex) {
                        Logger.getLogger(MainWindow.class.getName()).log(Level.WARNING, null, ex);
                        System.out.println("ERROR! " + ex.getMessage());
                    }
                    equalsPressed = true;
                    break;
                case "n!":
                    System.out.println("n!");
            
                    try {
                        result = sciCalc.doCalculation("n!", operations);
                    } catch (UnsupportedOperationException | ScientificCalculator.OperationFormatException ex) {
                        Logger.getLogger(MainWindow.class.getName()).log(Level.WARNING, null, ex);
                        System.out.println("ERROR! " + ex.getMessage());
                    }
                    equalsPressed = true;
                    break;
                case "sec":
                    System.out.println("sec");
            
                    try {
                        result = sciCalc.doCalculation("sec", operations);
                    } catch (UnsupportedOperationException | ScientificCalculator.OperationFormatException ex) {
                        Logger.getLogger(MainWindow.class.getName()).log(Level.WARNING, null, ex);
                        System.out.println("ERROR! " + ex.getMessage());
                    }
                    equalsPressed = true;
                    break;
                default:
                    System.out.println("UNKNOWN: " + e.getActionCommand());
                    break;
            }
            
            tf_display.setText(Double.toString(result));
            operations.clear();
            operations.add(Double.toString(result));
            System.out.println("Scientific calculation performed.");
        }
    }
}
