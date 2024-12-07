/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import logica.Fproveedor;

/**
 *
 * @author admin
 */
public class frmMostrarPoveedor extends javax.swing.JFrame {

    /**
     * Creates new form frmMostrarPoveedor
     */
    public frmMostrarPoveedor() {
        initComponents();
        mostrar("");
    }

    void mostrar(String buscar) {
        try {
            DefaultTableModel modelo;
            Fproveedor func = new Fproveedor();
            modelo = func.mostrar(buscar);//tiene los valores de select
            tblRegistro.setModel(modelo);//se pasan los datos de la tabla
            lblTotalRegistro.setText("Total de Registro " + func.totalregistro);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblRegistro = new javax.swing.JTable();
        lblTotalRegistro = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblRegistro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblRegistro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblRegistroMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblRegistroMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblRegistro);

        lblTotalRegistro.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 255, 255)));

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });

        jLabel1.setText("Buscar:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(117, 117, 117)
                        .addComponent(lblTotalRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(lblTotalRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblRegistroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblRegistroMouseClicked
        //int fila = tblRegistro.rowAtPoint(evt.getPoint()); //reecoge la fila que es selccionada coon el mouse
        //txtCodigo.setText(tblRegistro.getValueAt(fila, 0).toString());
        //txtDescripcion.setText(tblRegistro.getValueAt(fila, 1).toString());
    }//GEN-LAST:event_tblRegistroMouseClicked

    private void tblRegistroMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblRegistroMousePressed
        //para llevar los paises e índice a la tabla frmDepto
        if (evt.getClickCount() == 2) {//si hace doble click
            int fila = tblRegistro.getSelectedRow();//guarda el índice de la fila
            String cod, valor;
            cod = tblRegistro.getValueAt(fila, 0).toString();//lo del tbl es un objeto
            valor = tblRegistro.getValueAt(fila, 2).toString();

            if (FRM_INSUMO.txtidproeedor != null && FRM_INSUMO.txtidproeedor != null) {
                FRM_INSUMO.txtidproeedor.setText(cod);
                FRM_INSUMO.txtproveedor.setText(valor);
            }

            if (FRM_pv.txtIdProveedor != null && FRM_pv.txtIdProveedor != null) {
                String ruc, valor1, telfono;
                ruc = tblRegistro.getValueAt(fila, 1).toString();
                valor1 = tblRegistro.getValueAt(fila, 2).toString();
                telfono = tblRegistro.getValueAt(fila, 4).toString();

                FRM_pv.txtIdProveedor.setText(cod);
                FRM_pv.txtRucProveedor.setText(ruc);
                FRM_pv.txtProveedor.setText(valor1);
                FRM_pv.txtTelefonoProv.setText(telfono);
            }

            if (FRM_pedidos_compras.txtIdProveedor != null && FRM_pv.txtIdProveedor != null) {
                String ruc, valor1, telfono;
                ruc = tblRegistro.getValueAt(fila, 1).toString();
                valor1 = tblRegistro.getValueAt(fila, 2).toString();
                telfono = tblRegistro.getValueAt(fila, 4).toString();

                FRM_pedidos_compras.txtIdProveedor.setText(cod);
                FRM_pedidos_compras.txtRucProveedor.setText(ruc);
                FRM_pedidos_compras.txtProveedor.setText(valor1);
                FRM_pedidos_compras.txtTelefonoProv.setText(telfono);
            }
            dispose();

        }
    }//GEN-LAST:event_tblRegistroMousePressed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        mostrar(txtBuscar.getText());//para buscar, al ir escribiendo tiene que traer
    }//GEN-LAST:event_txtBuscarKeyReleased

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
            java.util.logging.Logger.getLogger(frmMostrarPoveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmMostrarPoveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmMostrarPoveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmMostrarPoveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmMostrarPoveedor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTotalRegistro;
    private javax.swing.JTable tblRegistro;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
