package view;

import DataAccessLayer.DAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import model.Product;

/**
 *
 * @author m98567
 */
public class ProductsManagement extends javax.swing.JFrame {

    /**
     * Creates new form CategoryManagement
     */
    public ProductsManagement() {
        try {
            initComponents();
            loadTable();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductsManagement.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ProductsManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        tableProducts = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        buttonEditProduct = new javax.swing.JButton();
        buttonDeleteProduct = new javax.swing.JButton();
        buttonAddProduct = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gerenciamento de Produtos");

        tableProducts.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Descrição", "Preço", "Categoria"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableProducts);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Gerenciamento de Produtos");

        buttonEditProduct.setText("Editar Produto");
        buttonEditProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEditProductActionPerformed(evt);
            }
        });

        buttonDeleteProduct.setText("Excluir Produto");
        buttonDeleteProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDeleteProductActionPerformed(evt);
            }
        });

        buttonAddProduct.setText("Adicionar Produto");
        buttonAddProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddProductActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 461, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(51, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(122, 122, 122))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(buttonAddProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonEditProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonDeleteProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonDeleteProduct)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(buttonEditProduct)
                        .addComponent(buttonAddProduct)))
                .addGap(14, 14, 14))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonEditProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEditProductActionPerformed
        try {
            // TODO add your handling code here:
            DAO<Product> pDAO = new DAO<Product>();
            
            int row = tableProducts.getSelectedRow();
            TableModel tableModel = tableProducts.getModel();

            int selectedID = (int) tableModel.getValueAt(row, 0);
            Product p = new Product();
            p = pDAO.getById(Product.class, selectedID);

            FormAddProd form = new FormAddProd(p);
            form.setVisible(true);
            
            loadTable();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductsManagement.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ProductsManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_buttonEditProductActionPerformed

    private void buttonAddProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddProductActionPerformed
        // TODO add your handling code here:
        FormAddProd form = new FormAddProd();
        form.setVisible(true);
        
        try {
            loadTable();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductsManagement.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ProductsManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonAddProductActionPerformed

    private void buttonDeleteProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDeleteProductActionPerformed
        try {
            // TODO add your handling code here:
            DAO<Product> pDAO = new DAO<Product>();
            
            int row = tableProducts.getSelectedRow();
            TableModel tableModel = tableProducts.getModel();

            int selectedID = (int) tableModel.getValueAt(row, 0);
            Product p = pDAO.getById(Product.class, selectedID);
            pDAO.delete(p);
            loadTable();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductsManagement.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ProductsManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_buttonDeleteProductActionPerformed

    private void loadTable() throws ClassNotFoundException, SQLException {
        DefaultTableModel model = (DefaultTableModel) tableProducts.getModel();
        model.setRowCount(0);

        ArrayList<Product> lst = new DAO<Product>().get(Product.class);

        for (int i = 0; i < lst.size(); i++) {
            //adicionar cada atendimento no JTable
            Object[] obj = new Object[4];//vetor para as 2 colunas
            obj[0] = lst.get(i).getId();
            obj[1] = lst.get(i).getDescription();
            obj[2] = lst.get(i).getPrice();
            obj[3] = lst.get(i).getCategory().getDescription();
            model.addRow(obj);
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
            java.util.logging.Logger.getLogger(ProductsManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProductsManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProductsManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProductsManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ProductsManagement().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAddProduct;
    private javax.swing.JButton buttonDeleteProduct;
    private javax.swing.JButton buttonEditProduct;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableProducts;
    // End of variables declaration//GEN-END:variables
}
