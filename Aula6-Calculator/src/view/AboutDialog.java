/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author alcsaw
 */
public class AboutDialog extends JDialog {
    private JPanel panelRoot, panelText, panelButtons;
    private JLabel labelDeveloper;
    private JButton buttonGithub, buttonClose;
    
    
    public AboutDialog(/* Calculator parentWindow */) {
        
        //panelRoot  = new JPanel(new FlowLayout());
        panelRoot  = new JPanel();
        BoxLayout layoutRoot = new BoxLayout(panelRoot, BoxLayout.Y_AXIS);
        panelRoot.setLayout (layoutRoot);
        
        panelText = new JPanel();
        BoxLayout layoutText = new BoxLayout(panelText, BoxLayout.X_AXIS);
        panelText.setLayout (layoutText);
        
        labelDeveloper = new JLabel("Follow me on Github!\n@Alcsaw");
        panelText.add(labelDeveloper);
        
        
        panelButtons = new JPanel(new FlowLayout());
        //BoxLayout layoutButtons = new BoxLayout(panelButtons, BoxLayout.X_AXIS);
        //panelText.setLayout (layoutButtons);
        
        buttonGithub = new JButton("Take me to Github!");
        panelButtons.add(buttonGithub);
        
        buttonGithub.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
                if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
                    try {
                        desktop.browse(new URI("https://github.com/Alcsaw/"));
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            }
        });
        
        buttonClose = new JButton("Close");
        panelButtons.add(buttonClose);
        
        buttonClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        
        panelRoot.add(panelText);
        panelRoot.add(panelButtons);
        
        this.setContentPane(panelRoot);
        this.pack();
        
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }
    
}
