package presentacion;

import datos.Vclientes;
import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import logica.Fclientes;
import logica.conexionReport;

/**
 *
 * @author admin
 */
public class FRM_clientes extends javax.swing.JInternalFrame {

    public FRM_clientes() {
        initComponents();
        mostrar("");
        mirar();
    }

    public String accion = "guardar";
    public String txt = "";

    void mirar() {
        txtCodigo.setEnabled(false);
        txtiddistrito.setEnabled(false);
        tblRegistro.setEnabled(true);
        txtdistrito.setEnabled(false);

        txtnombre.setEnabled(false);
        txtApellido.setEnabled(false);
        txtTelefono.setEnabled(false);
        txtDireccion.setEnabled(false);
        txtTelefono.setEnabled(false);
        txtcedula.setEnabled(false);

        btnNuevo.setEnabled(true);
        btnEditar.setEnabled(true);
        btnGuardar.setEnabled(true);
        btnocultar.setEnabled(true);
        btnActivar.setEnabled(true);
        btnSalir.setEnabled(true);

        txtCodigo.setText("");
        txtiddistrito.setText("");
        txtdistrito.setText("");
        txtnombre.setText("");
        txtApellido.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
        txtcedula.setText("");

        tblRegistro.clearSelection();
    }

    void modificar() {
        txtCodigo.setEnabled(true);
        tblRegistro.setEnabled(false);
        txtiddistrito.setEnabled(true);
        txtdistrito.setEnabled(true);
        txtiddistrito.setEnabled(false);
        txtdistrito.setEnabled(false);

        btnNuevo.setEnabled(false);
        btnEditar.setEnabled(false);
        btnGuardar.setEnabled(true);
        btncancelar.setEnabled(true);
        btnSalir.setEnabled(false);

        txtnombre.setEnabled(true);
        txtApellido.setEnabled(true);
        txtTelefono.setEnabled(true);
        txtDireccion.setEnabled(true);
        txtTelefono.setEnabled(true);
        txtcedula.setEnabled(true);

        btnMostrardistrito.setEnabled(true);
    }

    void mostrar(String buscar) {
        try {
            DefaultTableModel modelo;
            Fclientes func = new Fclientes();
            modelo = func.mostrar(buscar);//tiene los valores de select
            tblRegistro.setModel(modelo);//se pasan los datos de la tabla
            lblTotalRegistro.setText("Total de Registro " + func.totalregistro);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

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
                txtnombre.setEnabled(false);
                btnGuardar.setEnabled(false);
            }
        }
    }

    public boolean validar() {
        if (txtiddistrito.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese el distrito");
            txtdistrito.requestFocus();
            txtdistrito.setBackground(Color.YELLOW);

            return false;

        } else if (txtnombre.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese el nombre");
            txtnombre.requestFocus();
            txtnombre.setBackground(Color.YELLOW);

            return false;
        } else if (txtApellido.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese el apellido");
            txtApellido.requestFocus();
            txtApellido.setBackground(Color.YELLOW);
            return false;
        } else if (txtcedula.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese el numero de documento");
            txtcedula.requestFocus();
            txtcedula.setBackground(Color.YELLOW);
            return false;

        } else if (txtTelefono.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese el telefono");
            txtTelefono.requestFocus();
            txtTelefono.setBackground(Color.YELLOW);
            return false;

        } else if (txtDireccion.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese el direccion");
            txtDireccion.requestFocus();
            txtDireccion.setBackground(Color.YELLOW);
            return false;

        } else {
            return true;
        }

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
        txtApellido = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        txtnombre = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtiddistrito = new javax.swing.JTextField();
        txtdistrito = new javax.swing.JTextField();
        btnMostrardistrito = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtcedula = new javax.swing.JTextField();
        btnimprimir = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        btnNuevo = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnocultar = new javax.swing.JButton();
        btncancelar = new javax.swing.JButton();
        btnActivar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();

        setBackground(new java.awt.Color(102, 255, 255));
        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconifiable(true);

        pBuscar.setBackground(new java.awt.Color(204, 255, 255));

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
                        .addComponent(jLabel1)
                        .addGap(40, 40, 40)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 590, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 791, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(pBuscarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTotalRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pBuscarLayout.setVerticalGroup(
            pBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pBuscarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(40, 40, 40)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addComponent(lblTotalRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tabpane.addTab("Buscar", pBuscar);

        pNuevo.setBackground(new java.awt.Color(153, 255, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel2.setText("Codigo:");

        jLabel3.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel3.setText("apellido");

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

        txtApellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtApellidoActionPerformed(evt);
            }
        });
        txtApellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApellidoKeyTyped(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel4.setText("nombre");

        jLabel6.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel6.setText("direccion");

        jLabel9.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel9.setText("telefono");

        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyTyped(evt);
            }
        });

        txtnombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnombreActionPerformed(evt);
            }
        });
        txtnombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtnombreKeyTyped(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel8.setText("Distrito");

        txtiddistrito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtiddistritoActionPerformed(evt);
            }
        });

        txtdistrito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdistritoActionPerformed(evt);
            }
        });

        btnMostrardistrito.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        btnMostrardistrito.setText("...");
        btnMostrardistrito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrardistritoActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel7.setText("Nro Cedula:");

        txtcedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcedulaKeyTyped(evt);
            }
        });

        btnimprimir.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnimprimir.setText("imprimir");
        btnimprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnimprimirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pNuevoLayout = new javax.swing.GroupLayout(pNuevo);
        pNuevo.setLayout(pNuevoLayout);
        pNuevoLayout.setHorizontalGroup(
            pNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pNuevoLayout.createSequentialGroup()
                .addGroup(pNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pNuevoLayout.createSequentialGroup()
                            .addGap(11, 11, 11)
                            .addComponent(jLabel2)
                            .addGap(62, 62, 62)
                            .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pNuevoLayout.createSequentialGroup()
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(txtiddistrito, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(txtdistrito, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pNuevoLayout.createSequentialGroup()
                        .addGroup(pNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(pNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtnombre, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                                .addComponent(txtcedula, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtApellido)))))
                .addGroup(pNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pNuevoLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(btnMostrardistrito)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pNuevoLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnimprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(101, 101, 101))))
            .addGroup(pNuevoLayout.createSequentialGroup()
                .addGroup(pNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pNuevoLayout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(499, Short.MAX_VALUE))
        );
        pNuevoLayout.setVerticalGroup(
            pNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pNuevoLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(pNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pNuevoLayout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(pNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtdistrito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtiddistrito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnMostrardistrito)
                            .addComponent(jLabel8)))
                    .addGroup(pNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(56, 56, 56)
                .addGroup(pNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtcedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(28, 28, 28)
                .addGroup(pNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnimprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(pNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(58, 58, 58)
                .addGroup(pNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(pNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(157, 157, 157))
        );

        tabpane.addTab("Nuevo/Modificar", pNuevo);

        jPanel1.setBackground(new java.awt.Color(0, 255, 255));
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

        btncancelar.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        btncancelar.setText("Cancelar");
        btncancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelarActionPerformed(evt);
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
                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnGuardar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnocultar, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btncancelar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnActivar, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNuevo)
                    .addComponent(btnEditar))
                .addGap(46, 46, 46)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnocultar)
                    .addComponent(btnGuardar))
                .addGap(66, 66, 66)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btncancelar)
                    .addComponent(btnActivar))
                .addGap(73, 73, 73)
                .addComponent(btnSalir)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabpane)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tabpane, javax.swing.GroupLayout.PREFERRED_SIZE, 616, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblRegistroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblRegistroMouseClicked
        int fila = tblRegistro.rowAtPoint(evt.getPoint()); //recoge la fila que es selccionada coon el mouse
        txtCodigo.setText(tblRegistro.getValueAt(fila, 0).toString());

        txtnombre.setText(tblRegistro.getValueAt(fila, 1).toString());//4
        txtApellido.setText(tblRegistro.getValueAt(fila, 2).toString());
        txtcedula.setText(tblRegistro.getValueAt(fila, 3).toString());
        txtDireccion.setText(tblRegistro.getValueAt(fila, 4).toString());
        txtTelefono.setText(tblRegistro.getValueAt(fila, 5).toString());
        txtiddistrito.setText(tblRegistro.getValueAt(fila, 7).toString());
        txtdistrito.setText(tblRegistro.getValueAt(fila, 8).toString());

        txt = tblRegistro.getValueAt(fila, 1).toString();

    }//GEN-LAST:event_tblRegistroMouseClicked

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        mostrar(txtBuscar.getText());//para buscar, al ir escribiendo tiene que traer
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void txtCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoActionPerformed

    private void txtnombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnombreActionPerformed

    private void txtdistritoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdistritoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdistritoActionPerformed

    private void btnMostrardistritoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrardistritoActionPerformed
        frmMostrardistrito form = new frmMostrardistrito();
        form.setVisible(true);
        form.toFront();
    }//GEN-LAST:event_btnMostrardistritoActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        accion = "guardar";

        modificar();

        tabpane.setSelectedIndex(tabpane.indexOfComponent(pNuevo));
        txtCodigo.requestFocusInWindow();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        if (tblRegistro.getSelectedRows().length > 0) { //verifica que si ha seleccionado un registro, en caso de ser sleccionado modifica
            accion = "modificar";
            modificar();
            txtCodigo.setEnabled(false);
            tabpane.setSelectedIndex(tabpane.indexOfComponent(pNuevo));
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un registro");
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (validar() == true) {
            if (accion.equals("guardar")) {
                Fclientes func = new Fclientes();
                Vclientes dts = new Vclientes();

                dts.setIdclientenro(txtCodigo.getText());
                dts.setIddistrito(txtiddistrito.getText());
                dts.setNombre(txtnombre.getText());
                dts.setApellido(txtApellido.getText());
                dts.setNro_ci(txtcedula.getText());
                dts.setDireccion(txtDireccion.getText());
                dts.setTelefono(txtTelefono.getText());
                func.insertar(dts);
                mostrar("");
            }
            if (accion.equals("modificar")) {
                Fclientes func = new Fclientes();
                Vclientes dts = new Vclientes();

//               
                dts.setIddistrito(txtiddistrito.getText());
                dts.setNombre(txtnombre.getText());
                dts.setApellido(txtApellido.getText());
                dts.setNro_ci(txtcedula.getText());
                dts.setDireccion(txtDireccion.getText());
                dts.setTelefono(txtTelefono.getText());
                dts.setIdclientenro(txtCodigo.getText());
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
                Fclientes func = new Fclientes();
                Vclientes dts = new Vclientes();
                dts.setIdclientenro(txtCodigo.getText());
                func.ocultar(dts);
                mostrar("");
                mirar();
            }
        }
    }//GEN-LAST:event_btnocultarActionPerformed

    private void btncancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelarActionPerformed
        mirar();

        txtCodigo.setBackground(Color.white);
        txtiddistrito.setBackground(Color.white);
        txtnombre.setBackground(Color.white);
        txtApellido.setBackground(Color.white);
        txtDireccion.setBackground(Color.white);
        txtTelefono.setBackground(Color.white);
        tabpane.setSelectedIndex(tabpane.indexOfComponent(pBuscar));
        /*cuando se haga click en el boton de cancelar para que vuelva en
        la pestana de buscar*/
    }//GEN-LAST:event_btncancelarActionPerformed

    private void btnActivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActivarActionPerformed
        if (!txtCodigo.getText().equals("")) {
            int confirmacion = JOptionPane.showConfirmDialog(rootPane, "Esta seguro de que quiere activar el registro?");
            if (confirmacion == 0) {
                Fclientes func = new Fclientes();
                Vclientes dts = new Vclientes();
                dts.setIdclientenro(txtCodigo.getText());
                func.activar(dts);
                mostrar("");
                mirar();
            }
        }
    }//GEN-LAST:event_btnActivarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void txtApellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtApellidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtApellidoActionPerformed

    private void txtiddistritoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtiddistritoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtiddistritoActionPerformed

    private void txtCodigoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCodigoFocusLost
        if (txtCodigo.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese el codigo");
            txtCodigo.requestFocus();
            txtCodigo.setBackground(Color.GREEN);
        } else {
            validarid();
        }
    }//GEN-LAST:event_txtCodigoFocusLost

    private void txtCodigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyTyped
        char key = evt.getKeyChar(); // Obtiene el carácter de la tecla presionada

        if (!Character.isDigit(key)) { // Verifica si el carácter no es un dígito
            evt.consume(); // Ignora el carácter si no es un número
        }
    }//GEN-LAST:event_txtCodigoKeyTyped

    private void txtcedulaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcedulaKeyTyped
        // TODO add your handling code here:
         char key = evt.getKeyChar(); // Obtiene el carácter de la tecla presionada

        if (!Character.isDigit(key)) { // Verifica si el carácter no es un dígito
            evt.consume(); // Ignora el carácter si no es un número
        }
    }//GEN-LAST:event_txtcedulaKeyTyped

    private void txtnombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnombreKeyTyped
        // TODO add your handling code here:
        int key = evt.getKeyChar();// es para que solo ingresen letras y espacio
        boolean letras = (key >= 65 && key <= 90) || (key >= 97 && key <= 122)
                || (key == 32);
        if (!letras) {
            evt.consume();
        }
    }//GEN-LAST:event_txtnombreKeyTyped

    private void txtApellidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidoKeyTyped
        // TODO add your handling code here:
        
        int key = evt.getKeyChar();// es para que solo ingresen letras y espacio
        boolean letras = (key >= 65 && key <= 90) || (key >= 97 && key <= 122)
                || (key == 32);
        if (!letras) {
            evt.consume();
        }
    }//GEN-LAST:event_txtApellidoKeyTyped

    private void txtTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyTyped
        // TODO add your handling code here
         char key = evt.getKeyChar(); // Obtiene el carácter de la tecla presionada

        if (!Character.isDigit(key)) { // Verifica si el carácter no es un dígito
            evt.consume(); // Ignora el carácter si no es un número
        }
    }//GEN-LAST:event_txtTelefonoKeyTyped

    private void btnimprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnimprimirActionPerformed
         Map<String, Object> parameters = new HashMap<>();

        conexionReport.iniciarReport("C:\\Users\\admin\\JaspersoftWorkspace"
                + "\\MyReports\\reporte_cliente.jasper", parameters);
    }//GEN-LAST:event_btnimprimirActionPerformed

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
            java.util.logging.Logger.getLogger(FRM_clientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FRM_clientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FRM_clientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FRM_clientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FRM_clientes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActivar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnMostrardistrito;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btncancelar;
    private javax.swing.JButton btnimprimir;
    private javax.swing.JButton btnocultar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTotalRegistro;
    private javax.swing.JPanel pBuscar;
    private javax.swing.JPanel pNuevo;
    private javax.swing.JTabbedPane tabpane;
    private javax.swing.JTable tblRegistro;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtcedula;
    public static javax.swing.JTextField txtdistrito;
    public static javax.swing.JTextField txtiddistrito;
    private javax.swing.JTextField txtnombre;
    // End of variables declaration//GEN-END:variables
}
