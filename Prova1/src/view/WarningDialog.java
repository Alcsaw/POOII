package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.Banco;

/**
 *
 * @author m98567
 */
public class WarningDialog extends JDialog {
    private JPanel panelRoot, panelInfo;
    private JLabel labelAviso;
    private JButton buttonOK;
    
    model.Banco modelBanco = new Banco();
    
    public WarningDialog(String tipo) {
        
        String warningString = "";
        switch(tipo) {
            case "add":
                warningString = "Não foi possível adicionar esta conta."
                        + "Verifique os dados inseridos e tente novamente.";
                break;
            case "busca":
                warningString = "Nenhuma conta encontrada com esse nome.";
                break;
            default:
                break;
        }
        
        panelRoot = new JPanel();
        
        panelInfo  = new JPanel();
        BoxLayout layoutRoot = new BoxLayout(panelInfo, BoxLayout.Y_AXIS);
        panelInfo.setLayout (layoutRoot);
        
        labelAviso = new JLabel(warningString);
        panelInfo.add(labelAviso);
        
        panelInfo.add(labelAviso);
        
        buttonOK = new JButton("OK");
        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        panelInfo.add(buttonOK);
        
        panelRoot.add(panelInfo);
        this.setContentPane(panelRoot);
        this.pack();
        
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
    }
}
