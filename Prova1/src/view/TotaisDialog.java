package view;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.Banco;

/**
 *
 * @author m98567
 */
public class TotaisDialog extends JDialog {
    private JPanel panelRoot, panelInfo;
    private JLabel labelTotalPoupanca, labelTotalCorrente;
    
    //model.Banco modelBanco = new Banco();
    
    public TotaisDialog(Banco modelBanco) {
        
        panelRoot = new JPanel();
        
        panelInfo  = new JPanel();
        BoxLayout layoutRoot = new BoxLayout(panelInfo, BoxLayout.Y_AXIS);
        panelInfo.setLayout (layoutRoot);
        
        String totalPoupanca = Float.toString(modelBanco.getSaldoContasPoupanca());
        String totalCorrente = Float.toString(modelBanco.getSaldoContasCorrente());
        
        labelTotalPoupanca = new JLabel("Total em contas Poupança: " + totalPoupanca);
        panelInfo.add(labelTotalPoupanca);
        
        labelTotalCorrente = new JLabel("Total em contas Corrente: " + totalCorrente);
        panelInfo.add(labelTotalCorrente);
        
        panelInfo.add(labelTotalPoupanca);
        panelInfo.add(labelTotalCorrente);
        
        panelRoot.add(panelInfo);
        this.setContentPane(panelRoot);
        this.pack();
        
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
    }
}
