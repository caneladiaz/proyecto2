package presentacion;

import datos.Vdtto;
import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import logica.Fdtto;
import logica.Fdeptos;
import static presentacion.FRM_departamento.txtidregion;
import static presentacion.FRM_departamento.txtregion;

public class FRM_distrito extends javax.swing.JInternalFrame {

    /**
     * Creates new form FRM_distrito
     */
    public FRM_distrito() {
        initComponents();
        mostrar("");
        mirar();
    }
    public String accion = "guardar";
    public String txt = "";

    void mirar() {
        txtCodigo.setEnabled(false);
        txtDescripcion.setEnabled(false);
        tblRegistro.setEnabled(true);
        txtiddepto.setEnabled(false);
        txtDepto.setEnabled(false);

        btnSalir.setEnabled(true);
        btnNuevo.setEnabled(true);
        btnEditar.setEnabled(true);
        btnGuardar.setEnabled(false);
        btnCancelar.setEnabled(true);
        //btnEliminar.setEnabled(true);
        btnMostrarDepto.setEnabled(false);

        txtCodigo.setText("");
        txtDescripcion.setText("");
        txtiddepto.setText("");
        txtDepto.setText("");
    }

    void mostrar(String buscar) {
        try {
            DefaultTableModel modelo;
            Fdtto func = new Fdtto();
            modelo = func.mostrar(buscar);//tiene los valores de select
            tblRegistro.setModel(modelo);//se pasan los datos de la tabla
            lblTotalRegistro.setText("Total de Registro " + func.totalregistro);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    /*metodo  para que cuando este en la pestaña de nuevo/modificar 
    solo muestre los botones correspondientes*/
    void modificar() {
        txtCodigo.setEnabled(true);
        txtDescripcion.setEnabled(true);

        tblRegistro.setEnabled(false);
        txtiddepto.setEnabled(false);

        btnNuevo.setEnabled(false);
        btnEditar.setEnabled(false);
        btnGuardar.setEnabled(true);
        btnCancelar.setEnabled(true);
        //btnEliminar.setEnabled(false);
        btnSalir.setEnabled(false);

        btnMostrarDepto.setEnabled(true);
    }

    //este méodo valida el id
    public void validarid() {
        String result = txtCodigo.getText();
        int fila = tblRegistro.getRowCount();
        // Iterar sobre todas las filas de la tabla
        for (int f = 0; f < fila; f++) {
            // Obtener el valor de la columna de interés en la fila actual
            String valorEnTabla = tblRegistro.getValueAt(f, 0).toString();
            // Comparar el valor ingresado con el valor en la tabla
            if (result.equals(valorEnTabla)) {
                JOptionPane.showMessageDialog(this, "Ese id ya existe");
                txtDescripcion.setEnabled(false);
                btnGuardar.setEnabled(false);
            }
        }
    }

    public boolean validardesc() {
        if (!txtCodigo.getText().equals("")) {
            if (txtDescripcion.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Debe ingresar el dato");
                txtDescripcion.requestFocus();
                txtDescripcion.setBackground(Color.pink);
                return false;
            }
            return true;
        }
        return false;
    }

    public boolean validardescdup(String usuario) {
        int fila = tblRegistro.getRowCount();
        for (int f = 0; f < fila; f++) {
            String valorEnTabla = tblRegistro.getValueAt(f, 1).toString();
            if (usuario.equalsIgnoreCase(valorEnTabla)) {
                return true; // Usuario duplicado encontrado
            }
        }
        return false; // No se encontró duplicado
    }
//convertir texto

    public String converTexto(String valor) {
        //convertir todo a minuscula
        valor = valor.toLowerCase();
        // Extraer la primera letra
        String primletra = valor.substring(0, 1);
        // Convertir la primera letra a mayúscula
        String newprimletra = primletra.toUpperCase();
        // Reemplazar la primera letra en la cadena original
        String result = newprimletra + valor.substring(1);
        return result;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnNuevo = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnocultar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnActivar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        tabpane = new javax.swing.JTabbedPane();
        pBuscar = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblRegistro = new javax.swing.JTable();
        txtBuscar = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        lblTotalRegistro = new javax.swing.JLabel();
        pNuevo = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        txtDescripcion = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtDepto = new javax.swing.JTextField();
        btnMostrarDepto = new javax.swing.JButton();
        txtiddepto = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconifiable(true);

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("ACCIONES:"));

        btnNuevo.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnEditar.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnGuardar.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnocultar.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        btnocultar.setText("ocultar");
        btnocultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnocultarActionPerformed(evt);
            }
        });

        btnCancelar.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnActivar.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        btnActivar.setText("activar");
        btnActivar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActivarActionPerformed(evt);
            }
        });

        btnSalir.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCancelar)
                    .addComponent(btnGuardar)
                    .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnocultar, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnActivar, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(btnNuevo)
                .addGap(18, 18, 18)
                .addComponent(btnGuardar)
                .addGap(18, 18, 18)
                .addComponent(btnCancelar)
                .addGap(22, 22, 22)
                .addComponent(btnEditar)
                .addGap(18, 18, 18)
                .addComponent(btnocultar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnActivar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 113, Short.MAX_VALUE)
                .addComponent(btnSalir)
                .addContainerGap())
        );

        tabpane.setBackground(new java.awt.Color(102, 255, 102));

        pBuscar.setBackground(new java.awt.Color(102, 255, 102));

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
        });
        jScrollPane1.setViewportView(tblRegistro);

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });

        jLabel1.setText("Buscar:");

        lblTotalRegistro.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 255, 255)));

        javax.swing.GroupLayout pBuscarLayout = new javax.swing.GroupLayout(pBuscar);
        pBuscar.setLayout(pBuscarLayout);
        pBuscarLayout.setHorizontalGroup(
            pBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pBuscarLayout.createSequentialGroup()
                .addGroup(pBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pBuscarLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pBuscarLayout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(lblTotalRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        pBuscarLayout.setVerticalGroup(
            pBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pBuscarLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(pBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(200, 200, 200)
                .addComponent(lblTotalRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabpane.addTab("Buscar", pBuscar);

        pNuevo.setBackground(new java.awt.Color(204, 255, 204));

        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel2.setText("Codigo:");

        jLabel3.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel3.setText("Depto:");

        txtCodigo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCodigoFocusLost(evt);
            }
        });
        txtCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoActionPerformed(evt);
            }
        });
        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoKeyTyped(evt);
            }
        });

        txtDescripcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDescripcionActionPerformed(evt);
            }
        });
        txtDescripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDescripcionKeyTyped(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel4.setText("distrito");

        btnMostrarDepto.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        btnMostrarDepto.setText("...");
        btnMostrarDepto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarDeptoActionPerformed(evt);
            }
        });

        txtiddepto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtiddeptoActionPerformed(evt);
            }
        });

        jLabel5.setText("*");

        jLabel6.setText("Los campos ci");

        javax.swing.GroupLayout pNuevoLayout = new javax.swing.GroupLayout(pNuevo);
        pNuevo.setLayout(pNuevoLayout);
        pNuevoLayout.setHorizontalGroup(
            pNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pNuevoLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(pNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pNuevoLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(25, 25, 25)
                        .addComponent(txtCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE))
                    .addGroup(pNuevoLayout.createSequentialGroup()
                        .addGroup(pNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pNuevoLayout.createSequentialGroup()
                                .addComponent(txtiddepto, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(txtDepto, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(btnMostrarDepto, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(35, 35, 35)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
            .addGroup(pNuevoLayout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addComponent(jLabel6)
                .addContainerGap())
        );
        pNuevoLayout.setVerticalGroup(
            pNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pNuevoLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(pNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtDepto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnMostrarDepto)
                        .addComponent(txtiddepto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(pNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(44, 44, 44)
                .addComponent(jLabel6)
                .addContainerGap(178, Short.MAX_VALUE))
        );

        tabpane.addTab("Nuevo/Modificar", pNuevo);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabpane)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabpane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblRegistroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblRegistroMouseClicked
        int fila = tblRegistro.rowAtPoint(evt.getPoint()); //recoge la fila que es seleccionada con el mouse
        txtCodigo.setText(tblRegistro.getValueAt(fila, 0).toString());
        
        txtDescripcion.setText(tblRegistro.getValueAt(fila, 3).toString());
        txtiddepto.setText(tblRegistro.getValueAt(fila, 1).toString());
        txtDepto.setText(tblRegistro.getValueAt(fila, 2).toString());

        txt = tblRegistro.getValueAt(fila, 1).toString();
    }//GEN-LAST:event_tblRegistroMouseClicked

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        mostrar(txtBuscar.getText());//para buscar, al ir escribiendo tiene que traer
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void txtCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoActionPerformed

    private void btnMostrarDeptoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarDeptoActionPerformed
        frmMostrarDepto form = new frmMostrarDepto();
        form.setVisible(true);
        form.toFront();
    }//GEN-LAST:event_btnMostrarDeptoActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        accion = "guardar";
        tabpane.setSelectedIndex(tabpane.indexOfComponent(pNuevo));
        modificar();        
        txtCodigo.requestFocusInWindow();
        
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        if (tblRegistro.getSelectedRows().length > 0) { //verifica que si ha seleccionado un registro, en caso de ser sleccionado modifica
            accion = "modificar";
            modificar();
            tabpane.setSelectedIndex(tabpane.indexOfComponent(pNuevo));
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un registro");
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (validardesc() == true) {
            if (accion.equals("guardar")) {
                Fdtto func = new Fdtto();
                Vdtto dts = new Vdtto();
                if (validardescdup(txtDescripcion.getText())) {
                    JOptionPane.showMessageDialog(this, "Ese dato ya existe");
                    txtDescripcion.requestFocus();
                    return;
                }
                dts.setIddistrito(txtCodigo.getText());
                 dts.setDescripcion(txtDescripcion.getText());
                  dts.setIddepartamento(txtiddepto.getText());
                func.insertar(dts);
                mostrar("");
            }
            if (accion.equals("modificar")) {
                Fdtto func = new Fdtto();
                Vdtto dts = new Vdtto();
                if (validardescdup(txtDescripcion.getText())) {
                    JOptionPane.showMessageDialog(this, "Ese dato ya existe");
                    txtDescripcion.requestFocus();
                    return;
                }
                
                dts.setIddistrito(txtCodigo.getText());
                dts.setDescripcion(txtDescripcion.getText());
                 dts.setIddepartamento(txtiddepto.getText());
                func.actualizar(dts);
                modificar();
                mostrar("");

            }
            mirar();
            tabpane.setSelectedIndex(tabpane.indexOfComponent(pBuscar));
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnocultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnocultarActionPerformed
        if (!txtCodigo.getText().equals("")) {
            int confirmacion = JOptionPane.showConfirmDialog(rootPane, "Esta seguro de que quiere ocultar el registro?");
            if (confirmacion == 0) {
                Fdtto func = new Fdtto();
                Vdtto dts = new Vdtto();
                dts.setIddistrito(txtCodigo.getText());
                func.ocultar(dts);
                mostrar("");
                mirar();
            }
        }
    }//GEN-LAST:event_btnocultarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        mirar();
        txtDescripcion.setBackground(Color.white);
        tabpane.setSelectedIndex(tabpane.indexOfComponent(pBuscar));
        /*cuando se haga click en el boton de cancelar para que vuelva en
        la pestana de buscar*/
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnActivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActivarActionPerformed
        if (!txtCodigo.getText().equals("")) {
            int confirmacion = JOptionPane.showConfirmDialog(rootPane, "Esta seguro de que quiere activar el registro?");
            if (confirmacion == 0) {
                Fdtto func = new Fdtto();
                Vdtto dts = new Vdtto();
                dts.setIddistrito(txtCodigo.getText());
                func.activar(dts);
                mostrar("");
                mirar();
            }
        }
    }//GEN-LAST:event_btnActivarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void txtDescripcionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionKeyTyped
        int key = evt.getKeyChar();// es para que solo ingresen letras y espacio
        boolean letras = (key >= 65 && key <= 90) || (key >= 97 && key <= 122)
                || (key == 32);
        if (!letras) {
            evt.consume();
        }
    }//GEN-LAST:event_txtDescripcionKeyTyped

    private void txtiddeptoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtiddeptoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtiddeptoActionPerformed

    private void txtCodigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoKeyTyped

    private void txtCodigoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCodigoFocusLost
        if (txtCodigo.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese el codigo");
            txtCodigo.requestFocus();
            txtCodigo.setBackground(Color.GREEN);
        } else {
            validarid();
        }
    }//GEN-LAST:event_txtCodigoFocusLost

    private void txtDescripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescripcionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescripcionActionPerformed

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
            java.util.logging.Logger.getLogger(FRM_distrito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FRM_distrito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FRM_distrito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FRM_distrito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FRM_distrito().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActivar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnMostrarDepto;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnocultar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTotalRegistro;
    private javax.swing.JPanel pBuscar;
    private javax.swing.JPanel pNuevo;
    private javax.swing.JTabbedPane tabpane;
    private javax.swing.JTable tblRegistro;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCodigo;
    public static javax.swing.JTextField txtDepto;
    private javax.swing.JTextField txtDescripcion;
    public static javax.swing.JTextField txtiddepto;
    // End of variables declaration//GEN-END:variables
}
