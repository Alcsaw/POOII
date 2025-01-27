package view;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Atendente;
import model.Atendimento;
import model.Auxiliar;
import model.Cliente;
import model.Pessoa;

/**
 *
 * @author alcsaw
 */
public class RegistroAtendimentos extends javax.swing.JFrame {
    
    private Auxiliar aux = null;

    /**
     * Creates new form RegistroAtendimentos
     */
    public RegistroAtendimentos() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tableAtendimentos = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        buttonGravar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        textAreaDescricaoAtendimento = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        comboBoxCliente = new javax.swing.JComboBox<>();
        comboBoxAtendente = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        tableAtendimentos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Atendente", "Descriçao do atendimento"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableAtendimentos);

        jLabel1.setText("Atendimentos anteriores deste cliente:");

        buttonGravar.setMnemonic('g');
        buttonGravar.setText("Gravar");
        buttonGravar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGravarActionPerformed(evt);
            }
        });

        textAreaDescricaoAtendimento.setColumns(20);
        textAreaDescricaoAtendimento.setRows(5);
        jScrollPane2.setViewportView(textAreaDescricaoAtendimento);

        jLabel2.setText("Descricao do atendimento:");

        jLabel3.setText("Atendente:");

        jLabel4.setText("Cliente:");

        comboBoxCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxClienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(buttonGravar))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(0, 1, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(comboBoxAtendente, 0, 302, Short.MAX_VALUE)
                            .addComponent(comboBoxCliente, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(comboBoxCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(comboBoxAtendente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonGravar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonGravarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGravarActionPerformed
        Atendimento atendimento = new Atendimento();
        
        Atendente atendente = (Atendente)comboBoxAtendente.getSelectedItem();
        Cliente cliente = (Cliente)comboBoxCliente.getSelectedItem();
        
        if (atendente != null && cliente != null) {
            try {
                String descricao = textAreaDescricaoAtendimento.getText();
                
                if (descricao.length() == 0) {
                    JOptionPane.showMessageDialog(this, "Eh preciso preencher a descricao do atendimento",
                        "ERROR", JOptionPane.ERROR_MESSAGE);
                } else {
                    aux = new Auxiliar();
                    aux.inserirAtendimento(atendente, cliente, descricao);
                    textAreaDescricaoAtendimento.setText("");
                    carregaTabela();
                }
                
            } catch (NullPointerException npe) {
                JOptionPane.showMessageDialog(this, "Eh preciso preencher a descricao do atendimento",
                        "ERROR", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException | ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um Cliente e um Atendente.",
                        "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_buttonGravarActionPerformed

    private void comboBoxClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxClienteActionPerformed
        try {
            carregaTabela();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(RegistroAtendimentos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_comboBoxClienteActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        try {
            //evento que ocorre ao abrir o formulário
            
            //instanciaro objeto que vou usar para acessar o BD
            aux = new Auxiliar();

            //carregar as combos e deixar nenhum selecionado                                    
            carregaCombo(comboBoxAtendente, aux.retornaAtendentes());
            carregaCombo(comboBoxCliente, aux.retornaClientes());
            comboBoxAtendente.setSelectedIndex(-1);
            comboBoxCliente.setSelectedIndex(-1);

        } catch (ClassNotFoundException | SQLException ex) {
            //exibir uma mensagem de erro e também registrar o log
            JOptionPane.showMessageDialog(this, ex.toString(), "ERRO", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(RegistroAtendimentos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_formWindowOpened

    /**
     * método para carregar a combo passada por parâmetro com a lista
     * assim posso utilizar para carregar ambas as combos
    */
    private void carregaCombo(JComboBox cmb, ArrayList list) {
        cmb.removeAllItems();
        for (int i = 0; i < list.size(); i++) {
            cmb.addItem(list.get(i));
        }
    }
    
    //método para carregar a tabela com o cliente da combo
    private void carregaTabela() throws ClassNotFoundException, SQLException {
        DefaultTableModel model = (DefaultTableModel) tableAtendimentos.getModel();
        model.setRowCount(0);//limpar as linhas antigas da jTable

        Pessoa modelPessoa = (Pessoa) comboBoxCliente.getSelectedItem();
        if (modelPessoa != null) {//se tenho um cliente escolhido na combo então...
            //Peço para a camada de acesso aos dados a lista de atendimentos
            //deste cliente
            ArrayList<Atendimento> list = aux.retornaAtendimentosCliente(modelPessoa.getId());

            for (int i = 0; i < list.size(); i++) {
                //adicionar cada atendimento no JTable
                Object[] obj = new Object[2];//vetor para as 2 colunas
                obj[0] = list.get(i).getNomeAtendente();
                obj[1] = list.get(i).getDescricao();

                model.addRow(obj);
            }
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RegistroAtendimentos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegistroAtendimentos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegistroAtendimentos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegistroAtendimentos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegistroAtendimentos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonGravar;
    private javax.swing.JComboBox<String> comboBoxAtendente;
    private javax.swing.JComboBox<String> comboBoxCliente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tableAtendimentos;
    private javax.swing.JTextArea textAreaDescricaoAtendimento;
    // End of variables declaration//GEN-END:variables
}
