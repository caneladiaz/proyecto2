package presentacion;

import datos.Vdeptos;
import datos.Vregion;
import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import logica.Fdeptos;
import logica.Fregion;
import logica.conexionReport;

public class FRM_departamento extends javax.swing.JInternalFrame {

    public FRM_departamento() {
        initComponents();
        mostrar("");
        mirar();
    }

    public String accion = "guardar";
    public String txt = "";

    void mirar() {
        txtCodigodep.setEnabled(false);
        txtDescripcion.setEnabled(false);
        tblRegistro.setEnabled(true);
        txtidregion.setEnabled(false);
        txtregion.setEnabled(false);

        btnSalir.setEnabled(true);
        btnNuevo.setEnabled(true);
        btnEditar.setEnabled(true);
        btnGuardar.setEnabled(false);
        btnCancelar.setEnabled(true);
        //btnEliminar.setEnabled(true);
        btnMostrarPais.setEnabled(false);

        txtCodigodep.setText("");
        txtDescripcion.setText("");
        txtidregion.setText("");
        txtregion.setText("");
    }

    /*metodo  para que cuando este en la pestaña de nuevo/modificar 
    solo muestre los botones correspondientes*/
    void modificar() {
        txtCodigodep.setEnabled(true);
        txtDescripcion.setEnabled(true);

        tblRegistro.setEnabled(false);
        txtidregion.setEnabled(false);
        txtregion.setEnabled(false);

        btnNuevo.setEnabled(false);
        btnEditar.setEnabled(false);
        btnGuardar.setEnabled(true);
        btnCancelar.setEnabled(true);
        //btnEliminar.setEnabled(false);
        btnSalir.setEnabled(false);

        btnMostrarPais.setEnabled(true);
    }

    void mostrar(String buscar) {
        try {
            DefaultTableModel modelo;
            Fdeptos func = new Fdeptos();
            modelo = func.mostrar(buscar);//tiene los valores de select
            tblRegistro.setModel(modelo);//se pasan los datos de la tabla
            lblTotalRegistro.setText("Total de Registro " + func.totalregistro);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    //este méodo valida el id
    public void validarid() {
        String result = txtCodigodep.getText();
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
        if (!txtCodigodep.getText().equals("")) {
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
        btnCancelar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        btnocultar = new javax.swing.JButton();
        btnActivar = new javax.swing.JButton();
        tabpane = new javax.swing.JTabbedPane();
        pBuscar = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblRegistro = new javax.swing.JTable();
        txtBuscar = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        lblTotalRegistro = new javax.swing.JLabel();
        btnimprimir = new javax.swing.JButton();
        pNuevo = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtCodigodep = new javax.swing.JTextField();
        txtDescripcion = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtregion = new javax.swing.JTextField();
        btnMostrarPais = new javax.swing.JButton();
        txtidregion = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconifiable(true);

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));
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

        btnCancelar.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnSalir.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        btnocultar.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        btnocultar.setText("ocultar");
        btnocultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnocultarActionPerformed(evt);
            }
        });

        btnActivar.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        btnActivar.setText("activar");
        btnActivar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActivarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnActivar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnocultar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btnEditar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnNuevo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(btnGuardar)
                .addGap(18, 18, 18)
                .addComponent(btnNuevo)
                .addGap(18, 18, 18)
                .addComponent(btnEditar)
                .addGap(18, 18, 18)
                .addComponent(btnSalir)
                .addGap(18, 18, 18)
                .addComponent(btnCancelar)
                .addGap(18, 18, 18)
                .addComponent(btnocultar)
                .addGap(18, 18, 18)
                .addComponent(btnActivar)
                .addContainerGap(48, Short.MAX_VALUE))
        );

        pBuscar.setBackground(new java.awt.Color(0, 102, 102));

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

        btnimprimir.setText("imprimir");
        btnimprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnimprimirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pBuscarLayout = new javax.swing.GroupLayout(pBuscar);
        pBuscar.setLayout(pBuscarLayout);
        pBuscarLayout.setHorizontalGroup(
            pBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pBuscarLayout.createSequentialGroup()
                .addGroup(pBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pBuscarLayout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(lblTotalRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pBuscarLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pBuscarLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnimprimir)
                .addGap(72, 72, 72))
        );
        pBuscarLayout.setVerticalGroup(
            pBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pBuscarLayout.createSequentialGroup()
                .addGroup(pBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pBuscarLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(pBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)))
                    .addGroup(pBuscarLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnimprimir)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(169, 169, 169)
                .addComponent(lblTotalRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabpane.addTab("Buscar", pBuscar);

        pNuevo.setBackground(new java.awt.Color(0, 102, 102));

        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel2.setText("Codigo:");

        jLabel3.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel3.setText("Region");

        txtCodigodep.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCodigodepFocusLost(evt);
            }
        });
        txtCodigodep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigodepActionPerformed(evt);
            }
        });

        txtDescripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDescripcionKeyTyped(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel4.setText("Departamento:");

        txtregion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtregionActionPerformed(evt);
            }
        });

        btnMostrarPais.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        btnMostrarPais.setText("...");
        btnMostrarPais.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarPaisActionPerformed(evt);
            }
        });

        jLabel5.setText("*");

        jLabel6.setText("Los campos ci");

        javax.swing.GroupLayout pNuevoLayout = new javax.swing.GroupLayout(pNuevo);
        pNuevo.setLayout(pNuevoLayout);
        pNuevoLayout.setHorizontalGroup(
            pNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pNuevoLayout.createSequentialGroup()
                .addGroup(pNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pNuevoLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(pNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pNuevoLayout.createSequentialGroup()
                                .addGroup(pNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addGroup(pNuevoLayout.createSequentialGroup()
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtidregion, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(pNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pNuevoLayout.createSequentialGroup()
                                        .addComponent(txtregion, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnMostrarPais))
                                    .addComponent(txtCodigodep, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(pNuevoLayout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pNuevoLayout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addComponent(jLabel6)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pNuevoLayout.setVerticalGroup(
            pNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pNuevoLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(pNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtCodigodep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtregion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMostrarPais)
                    .addComponent(txtidregion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(44, 44, 44)
                .addComponent(jLabel6)
                .addContainerGap(195, Short.MAX_VALUE))
        );

        tabpane.addTab("Nuevo/Modificar", pNuevo);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabpane, javax.swing.GroupLayout.PREFERRED_SIZE, 539, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabpane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        accion = "guardar";
        modificar();
        txtCodigodep.requestFocusInWindow();
        tabpane.setSelectedIndex(tabpane.indexOfComponent(pNuevo));
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        if (tblRegistro.getSelectedRows().length > 0) { //verifica que si ha seleccionado un registro, en caso de ser sleccionado modifica
            accion = "modificar";
            tabpane.setSelectedIndex(tabpane.indexOfComponent(pNuevo));
            modificar();
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un registro");
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (validardesc() == true) {
            if (accion.equals("guardar")) {
                Fdeptos func = new Fdeptos();
                Vdeptos dts = new Vdeptos();
                if (validardescdup(txtDescripcion.getText())) {
                    JOptionPane.showMessageDialog(this, "Ese dato ya existe");
                    txtDescripcion.requestFocus();
                    return;
                }
                dts.setIddepartamento(txtCodigodep.getText());
                dts.setDescripcion(txtDescripcion.getText());
                dts.setIdregion(txtidregion.getText());
                func.insertar(dts);
                mostrar("");
            }
            if (accion.equals("modificar")) {
                Fdeptos func = new Fdeptos();
                Vdeptos dts = new Vdeptos();
                if (!txt.equalsIgnoreCase(txtDescripcion.getText())) {
                    if (validardescdup(txtDescripcion.getText())) {
                        JOptionPane.showMessageDialog(this, "Ese dato ya existe");
                        txtDescripcion.requestFocus();
                        return;
                    }
                }
                dts.setDescripcion(txtDescripcion.getText());
                dts.setIddepartamento(txtCodigodep.getText());
                dts.setIdregion(txtidregion.getText());
                func.actualizar(dts);
                modificar();
                mostrar("");
            }
            mirar();
            tabpane.setSelectedIndex(tabpane.indexOfComponent(pBuscar));
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        mirar();
        txtDescripcion.setBackground(Color.white);
        tabpane.setSelectedIndex(tabpane.indexOfComponent(pBuscar));
        /*cuando se haga click en el boton de cancelar para que vuelva en
        la pestana de buscar*/
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void tblRegistroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblRegistroMouseClicked
        int fila = tblRegistro.rowAtPoint(evt.getPoint()); //recoge la fila que es seleccionada con el mouse
        txtCodigodep.setText(tblRegistro.getValueAt(fila, 0).toString());
        txtidregion.setText(tblRegistro.getValueAt(fila, 2).toString());
        txtregion.setText(tblRegistro.getValueAt(fila, 3).toString());
        txtDescripcion.setText(tblRegistro.getValueAt(fila, 1).toString());
        txt = tblRegistro.getValueAt(fila, 1).toString();
    }//GEN-LAST:event_tblRegistroMouseClicked

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        mostrar(txtBuscar.getText());//para buscar, al ir escribiendo tiene que traer
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void txtCodigodepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigodepActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigodepActionPerformed

    private void btnMostrarPaisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarPaisActionPerformed
        frmMostrarRegion form = new frmMostrarRegion();
        form.setVisible(true);
        form.toFront();

    }//GEN-LAST:event_btnMostrarPaisActionPerformed

    private void btnocultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnocultarActionPerformed
        if (!txtCodigodep.getText().equals("")) {
            int confirmacion = JOptionPane.showConfirmDialog(rootPane, "Esta seguro de que quiere ocultar el registro?");
            if (confirmacion == 0) {
                Fdeptos func = new Fdeptos();
                Vdeptos dts = new Vdeptos();
                dts.setIddepartamento(txtCodigodep.getText());
                func.ocultar(dts);
                mostrar("");
                mirar();
            }
        }
    }//GEN-LAST:event_btnocultarActionPerformed

    private void btnActivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActivarActionPerformed
        if (!txtCodigodep.getText().equals("")) {
            int confirmacion = JOptionPane.showConfirmDialog(rootPane, "Esta seguro de que quiere activar el registro?");
            if (confirmacion == 0) {
                Fdeptos func = new Fdeptos();
                Vdeptos dts = new Vdeptos();
                dts.setIddepartamento(txtCodigodep.getText());
                func.activar(dts);
                mostrar("");
                mirar();
            }
        }
    }//GEN-LAST:event_btnActivarActionPerformed

    private void txtDescripcionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionKeyTyped
        int key = evt.getKeyChar();// es para que solo ingresen letras y espacio
        boolean letras = (key >= 65 && key <= 90) || (key >= 97 && key <= 122)
                || (key == 32);
        if (!letras) {
            evt.consume();
        }
    }//GEN-LAST:event_txtDescripcionKeyTyped

    private void txtCodigodepFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCodigodepFocusLost
        if (txtCodigodep.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese el codigo");
            txtCodigodep.requestFocus();
            txtCodigodep.setBackground(Color.GREEN);
        } else {
            validarid();
        }
    }//GEN-LAST:event_txtCodigodepFocusLost

    private void btnimprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnimprimirActionPerformed
        // TODO add your handling code here:
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("ID", "10");// parametros que necesita el SQL en reports
        //ruta del archivo.jasper + los paramtros del mismo 
//            conexionReport.iniciarReport("C:\Users\Elias Carranza\JaspersoftWorkspace\MyReports\Leaf_Violet.jasper", parameters);
        conexionReport.iniciarReport("C:\\Users\\admin\\JaspersoftWorkspace\\MyReports\\prueba.jasper", parameters);
    }//GEN-LAST:event_btnimprimirActionPerformed

    private void txtregionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtregionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtregionActionPerformed

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
            java.util.logging.Logger.getLogger(FRM_departamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FRM_departamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FRM_departamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FRM_departamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FRM_departamento().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActivar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnMostrarPais;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnimprimir;
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
    private javax.swing.JTextField txtCodigodep;
    private javax.swing.JTextField txtDescripcion;
    public static javax.swing.JTextField txtidregion;
    public static javax.swing.JTextField txtregion;
    // End of variables declaration//GEN-END:variables
}
