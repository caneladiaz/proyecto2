
package presentacion;

import java.util.HashMap;
import java.util.Map;
import logica.conexionReport;


public class FORMULARIOSinformes extends javax.swing.JInternalFrame {

 
    public FORMULARIOSinformes() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        btnproductos = new javax.swing.JButton();
        btnAgenda = new javax.swing.JButton();
        btnpedidos = new javax.swing.JButton();
        btnCliente = new javax.swing.JButton();
        btnDEPARTAMENTO = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        btnREGION = new javax.swing.JButton();
        btnDistrito = new javax.swing.JButton();
        btnproveedor = new javax.swing.JButton();

        setBackground(new java.awt.Color(0, 255, 204));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        setClosable(true);
        setIconifiable(true);
        setTitle("Formulario de Informes");

        jDesktopPane1.setBackground(new java.awt.Color(51, 255, 204));

        btnproductos.setFont(new java.awt.Font("Gill Sans Ultra Bold", 0, 11)); // NOI18N
        btnproductos.setText("productos");
        btnproductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnproductosActionPerformed(evt);
            }
        });

        btnAgenda.setFont(new java.awt.Font("Gill Sans Ultra Bold", 0, 11)); // NOI18N
        btnAgenda.setText("agenda");
        btnAgenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgendaActionPerformed(evt);
            }
        });

        btnpedidos.setFont(new java.awt.Font("Gill Sans Ultra Bold", 0, 11)); // NOI18N
        btnpedidos.setText("pedidos");
        btnpedidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnpedidosActionPerformed(evt);
            }
        });

        btnCliente.setFont(new java.awt.Font("Gill Sans Ultra Bold", 0, 11)); // NOI18N
        btnCliente.setText("Clientes");
        btnCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClienteActionPerformed(evt);
            }
        });

        btnDEPARTAMENTO.setFont(new java.awt.Font("Gill Sans Ultra Bold", 0, 11)); // NOI18N
        btnDEPARTAMENTO.setText("departamento");
        btnDEPARTAMENTO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDEPARTAMENTOActionPerformed(evt);
            }
        });

        jTextField1.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        jTextField1.setText("                INFORMES");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        btnREGION.setFont(new java.awt.Font("Gill Sans Ultra Bold", 0, 11)); // NOI18N
        btnREGION.setText("Region");
        btnREGION.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnREGIONActionPerformed(evt);
            }
        });

        btnDistrito.setFont(new java.awt.Font("Gill Sans Ultra Bold", 0, 11)); // NOI18N
        btnDistrito.setText("Distrito");
        btnDistrito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDistritoActionPerformed(evt);
            }
        });

        btnproveedor.setFont(new java.awt.Font("Gill Sans Ultra Bold", 0, 11)); // NOI18N
        btnproveedor.setText("proveedores");
        btnproveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnproveedorActionPerformed(evt);
            }
        });

        jDesktopPane1.setLayer(btnproductos, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(btnAgenda, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(btnpedidos, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(btnCliente, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(btnDEPARTAMENTO, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jTextField1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(btnREGION, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(btnDistrito, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(btnproveedor, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDEPARTAMENTO)
                    .addComponent(btnCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgenda, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(201, 201, 201)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnREGION, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnproveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnpedidos, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 166, Short.MAX_VALUE)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDistrito, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnproductos, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(77, 77, 77))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(288, 288, 288))
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDEPARTAMENTO, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnREGION, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDistrito, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnpedidos, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnproductos, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgenda, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnproveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(205, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jDesktopPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jDesktopPane1))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setBounds(0, 0, 900, 560);
    }// </editor-fold>//GEN-END:initComponents

    private void btnproductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnproductosActionPerformed

        Map<String, Object> parameters = new HashMap<>();

        conexionReport.iniciarReport("C:\\Users\\admin\\JaspersoftWorkspace"
                + "\\MyReports\\reporte_productos.jasper", parameters);
    }//GEN-LAST:event_btnproductosActionPerformed

    private void btnAgendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgendaActionPerformed
        // TODO add your handling code here:
        Map<String, Object> parameters = new HashMap<>();

        conexionReport.iniciarReport("C:\\Users\\admin\\JaspersoftWorkspace"
                + "\\MyReports\\reporte_agenda.jasper", parameters);
    }//GEN-LAST:event_btnAgendaActionPerformed

    private void btnpedidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnpedidosActionPerformed

        Map<String, Object> parameters = new HashMap<>();

        conexionReport.iniciarReport("C:\\Users\\admin\\JaspersoftWorkspace"
                + "\\MyReports\\reporte_pedido.jasper", parameters);
    }//GEN-LAST:event_btnpedidosActionPerformed

    private void btnClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClienteActionPerformed
        Map<String, Object> parameters = new HashMap<>();
        conexionReport.iniciarReport("C:\\Users\\admin\\JaspersoftWorkspace"
                + "\\MyReports\\reporte_cliente.jasper", parameters);
    }//GEN-LAST:event_btnClienteActionPerformed

    private void btnDEPARTAMENTOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDEPARTAMENTOActionPerformed
        // TODO add your handling code here:
        Map<String, Object> parameters = new HashMap<>();

        conexionReport.iniciarReport("C:\\Users\\admin\\JaspersoftWorkspace"
                + "\\MyReports\\reporte_depa.jasper", parameters);
    }//GEN-LAST:event_btnDEPARTAMENTOActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void btnREGIONActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnREGIONActionPerformed
        // TODO add your handling code here:
         Map<String, Object> parameters = new HashMap<>();

        conexionReport.iniciarReport("C:\\Users\\admin\\JaspersoftWorkspace"
                + "\\MyReports\\reporte_region.jasper", parameters);
    }//GEN-LAST:event_btnREGIONActionPerformed

    private void btnDistritoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDistritoActionPerformed
         Map<String, Object> parameters = new HashMap<>();

        conexionReport.iniciarReport("C:\\Users\\admin\\JaspersoftWorkspace"
                + "\\MyReports\\reporte_distrito.jasper", parameters);
    }//GEN-LAST:event_btnDistritoActionPerformed

    private void btnproveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnproveedorActionPerformed
        // TODO add your handling code here:
        Map<String, Object> parameters = new HashMap<>();

        conexionReport.iniciarReport("C:\\Users\\admin\\JaspersoftWorkspace"
                + "\\MyReports\\reporte_proveedor.jasper", parameters);
    }//GEN-LAST:event_btnproveedorActionPerformed

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
            java.util.logging.Logger.getLogger(FORMULARIOSinformes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FORMULARIOSinformes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FORMULARIOSinformes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FORMULARIOSinformes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FORMULARIOSinformes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgenda;
    private javax.swing.JButton btnCliente;
    private javax.swing.JButton btnDEPARTAMENTO;
    private javax.swing.JButton btnDistrito;
    private javax.swing.JButton btnREGION;
    private javax.swing.JButton btnpedidos;
    private javax.swing.JButton btnproductos;
    private javax.swing.JButton btnproveedor;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
