package presentacion;

import datos.Vempleados;
import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import logica.Fempleados;
import logica.conexionReport;

public class FRM_empleados extends javax.swing.JInternalFrame {

    /**
     * Creates new form FRM_empleados
     */
    public FRM_empleados() {
        initComponents();
        mostrar("");
        mirar();
    }
    public String accion = "guardar";
    public String txt = "";

    void mirar() {
        txtCodigo.setEnabled(false);
        txtiddistrito.setEnabled(false);
        txtdistrito.setEnabled(false);
        tblRegistro.setEnabled(true);

        txtNombre.setEnabled(false);
        txtApellido.setEnabled(false);
        txtci.setEnabled(false);

        txtTelefono.setEnabled(false);
        txtdireccion.setEnabled(false);
        txtuser.setEnabled(false);
        txtcontraseña.setEnabled(false);
        txtidrol.setEnabled(false);
        txtrol.setEnabled(false);

        btnNuevo.setEnabled(true);
        btnEditar.setEnabled(true);
        btnGuardar.setEnabled(true);
        btnocultar.setEnabled(true);
        btnActivar.setEnabled(true);
        btnSalir.setEnabled(true);

        txtCodigo.setText("");
        txtiddistrito.setText("");
        txtdistrito.setText("");
        txtuser.setText("");
        txtcontraseña.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtdireccion.setText("");
        txtTelefono.setText("");
        txtrol.setText("");
        txtidrol.setText("");
        tblRegistro.clearSelection();
    }

    void modificar() {
        txtCodigo.setEnabled(true);
        tblRegistro.setEnabled(false);

        btnNuevo.setEnabled(false);
        btnEditar.setEnabled(false);
        btnGuardar.setEnabled(true);
        btnocultar.setEnabled(true);
        btnActivar.setEnabled(false);
        btnSalir.setEnabled(false);

        txtNombre.setEnabled(true);
        txtApellido.setEnabled(true);
        txtci.setEnabled(true);

        txtTelefono.setEnabled(true);
        txtdireccion.setEnabled(true);
        txtuser.setEnabled(true);
        txtcontraseña.setEnabled(true);

        btnMostrardistrito.setEnabled(true);
        btnMostrarRol.setEnabled(true);
    }

    void mostrar(String buscar) {
        try {
            DefaultTableModel modelo;
            Fempleados func = new Fempleados();
            modelo = func.mostrar(buscar);//tiene los valores de select
            tblRegistro.setModel(modelo);//se pasan los datos de la tabla
            lblTotalRegistro.setText("Total de Registro " + func.totalregistro);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    void login(String buscar, String a) {
        try {
            DefaultTableModel modelo;
            Fempleados func = new Fempleados();
            modelo = func.login(buscar, a);//tiene los valores de select
            tblRegistro.setModel(modelo);//se pasan los datos de la tabla
            lblTotalRegistro.setText("Total de Registro " + func.totalregistro);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
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

//                txtCodigo.setEnabled(false);
                btnGuardar.setEnabled(false);
            }
        }
    }

    public boolean validar() {
        if (txtuser.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese el usuario");
            txtuser.requestFocus();
            txtuser.setBackground(Color.YELLOW);
            return false;
        } else if (txtcontraseña.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese el password");
            txtcontraseña.requestFocus();
            txtcontraseña.setBackground(Color.YELLOW);
            return false;
        } else if (txtNombre.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese el nombre");
            txtNombre.requestFocus();
            txtNombre.setBackground(Color.YELLOW);
            return false;
        } else if (txtApellido.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese el apellido");
            txtApellido.requestFocus();
            txtApellido.setBackground(Color.YELLOW);
            return false;
        } else if (txtci.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese numero de cedula");
            txtci.requestFocus();
            txtci.setBackground(Color.YELLOW);
            return false;
        } else if (txtdireccion.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese la direccion");
            txtdireccion.requestFocus();
            txtdireccion.setBackground(Color.YELLOW);
            return false;
        } else if (txtTelefono.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese el teléfono");
            txtTelefono.requestFocus();
            txtTelefono.setBackground(Color.YELLOW);
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
        txtBuscar = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblRegistro = new javax.swing.JTable();
        pNuevo = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtApellido = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtdireccion = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtiddistrito = new javax.swing.JTextField();
        txtdistrito = new javax.swing.JTextField();
        btnMostrardistrito = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtidrol = new javax.swing.JTextField();
        txtrol = new javax.swing.JTextField();
        btnMostrarRol = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        txtuser = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtcontraseña = new javax.swing.JTextField();
        txtCodigo = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtci = new javax.swing.JTextField();
        btnimprimir = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        btnNuevo = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        btncancelar = new javax.swing.JButton();
        btnocultar = new javax.swing.JButton();
        btnActivar = new javax.swing.JButton();
        lblTotalRegistro = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 153, 255));
        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconifiable(true);

        pBuscar.setBackground(new java.awt.Color(255, 102, 255));

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });

        jLabel1.setText("Buscar:");

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

        javax.swing.GroupLayout pBuscarLayout = new javax.swing.GroupLayout(pBuscar);
        pBuscar.setLayout(pBuscarLayout);
        pBuscarLayout.setHorizontalGroup(
            pBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pBuscarLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1)
                .addGap(40, 40, 40)
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(778, Short.MAX_VALUE))
            .addGroup(pBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pBuscarLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1011, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        pBuscarLayout.setVerticalGroup(
            pBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pBuscarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap(496, Short.MAX_VALUE))
            .addGroup(pBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pBuscarLayout.createSequentialGroup()
                    .addGap(80, 80, 80)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(71, Short.MAX_VALUE)))
        );

        tabpane.addTab("Buscar", pBuscar);

        pNuevo.setBackground(new java.awt.Color(255, 153, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel2.setText("Codigo:");

        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel6.setText("Nombre");

        txtApellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApellidoKeyTyped(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel7.setText("Apellido:");

        txtdireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtdireccionKeyTyped(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel8.setText("Direccion");

        jLabel3.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel3.setText("Distrito:");

        btnMostrardistrito.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        btnMostrardistrito.setText("...");
        btnMostrardistrito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrardistritoActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel9.setText("Telefono");

        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyTyped(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel4.setText("rol:");

        btnMostrarRol.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        btnMostrarRol.setText("...");
        btnMostrarRol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarRolActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel10.setText("usuario");

        txtuser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtuserActionPerformed(evt);
            }
        });
        txtuser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtuserKeyTyped(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel11.setText("contraseña");

        txtcontraseña.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcontraseñaActionPerformed(evt);
            }
        });
        txtcontraseña.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcontraseñaKeyTyped(evt);
            }
        });

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

        jLabel12.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel12.setText("cedula");

        txtci.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtciActionPerformed(evt);
            }
        });

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
                .addContainerGap()
                .addGroup(pNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pNuevoLayout.createSequentialGroup()
                        .addGroup(pNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pNuevoLayout.createSequentialGroup()
                                .addGroup(pNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(pNuevoLayout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addComponent(jLabel2)))
                                .addGap(35, 35, 35)
                                .addGroup(pNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtuser, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                                    .addComponent(txtCodigo)))
                            .addGroup(pNuevoLayout.createSequentialGroup()
                                .addGroup(pNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(pNuevoLayout.createSequentialGroup()
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtiddistrito))
                                    .addGroup(pNuevoLayout.createSequentialGroup()
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtidrol, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pNuevoLayout.createSequentialGroup()
                                        .addComponent(txtrol, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnMostrarRol))
                                    .addGroup(pNuevoLayout.createSequentialGroup()
                                        .addComponent(txtdistrito, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnMostrardistrito)))))
                        .addContainerGap(600, Short.MAX_VALUE))
                    .addGroup(pNuevoLayout.createSequentialGroup()
                        .addGroup(pNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(pNuevoLayout.createSequentialGroup()
                                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtcontraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pNuevoLayout.createSequentialGroup()
                                    .addGroup(pNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(35, 35, 35)
                                    .addGroup(pNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                                        .addComponent(txtApellido))))
                            .addGroup(pNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pNuevoLayout.createSequentialGroup()
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtci, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pNuevoLayout.createSequentialGroup()
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(35, 35, 35)
                                    .addComponent(txtTelefono))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pNuevoLayout.createSequentialGroup()
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(35, 35, 35)
                                    .addComponent(txtdireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnimprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22))))
        );
        pNuevoLayout.setVerticalGroup(
            pNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pNuevoLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(pNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtuser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtcontraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 93, Short.MAX_VALUE)
                .addGroup(pNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtrol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMostrarRol)
                    .addComponent(txtidrol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtdistrito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMostrardistrito)
                    .addComponent(txtiddistrito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(pNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pNuevoLayout.createSequentialGroup()
                        .addGroup(pNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(27, 27, 27)
                        .addGroup(pNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txtci, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pNuevoLayout.createSequentialGroup()
                        .addComponent(btnimprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)))
                .addGroup(pNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtdireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(pNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(50, 50, 50))
        );

        tabpane.addTab("Nuevo/Modificar", pNuevo);

        jPanel1.setBackground(new java.awt.Color(255, 0, 255));
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

        btnSalir.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        btncancelar.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        btncancelar.setText("Cancelar");
        btncancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelarActionPerformed(evt);
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
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btnSalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEditar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnNuevo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE))
                    .addComponent(btncancelar)
                    .addComponent(btnActivar, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnocultar, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 34, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(btnNuevo)
                .addGap(12, 12, 12)
                .addComponent(btnGuardar)
                .addGap(18, 18, 18)
                .addComponent(btnEditar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btncancelar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnocultar)
                .addGap(9, 9, 9)
                .addComponent(btnActivar)
                .addGap(18, 18, 18)
                .addComponent(btnSalir)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblTotalRegistro.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 255, 255)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabpane)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(lblTotalRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tabpane)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTotalRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        mostrar(txtBuscar.getText());//para buscar, al ir escribiendo tiene que traer

    }//GEN-LAST:event_txtBuscarKeyReleased

    private void btnMostrardistritoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrardistritoActionPerformed
        frmMostrardistrito form = new frmMostrardistrito();
        form.setVisible(true);
        form.toFront();

    }//GEN-LAST:event_btnMostrardistritoActionPerformed

    private void btnMostrarRolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarRolActionPerformed
        frmMostrarRol form = new frmMostrarRol();
        form.setVisible(true);
        form.toFront();// TODO add your handling code here:
    }//GEN-LAST:event_btnMostrarRolActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (validar() == true) {
            if (accion.equals("guardar")) {
                Fempleados func = new Fempleados();
                Vempleados dts = new Vempleados();
//                if (validardescdup(txtuser.getText())) {
//                    JOptionPane.showMessageDialog(this, "Ese dato ya existe");
//                    txtuser.requestFocus();
//                    return;
//                }
                dts.setIdempleado(txtCodigo.getText());
                dts.setIdRol(txtidrol.getText());
                dts.setIddistrito(txtiddistrito.getText());
                dts.setUser(txtuser.getText());
                dts.setContraseña(txtcontraseña.getText());
                dts.setNombre(txtNombre.getText());
                dts.setApellido(txtApellido.getText());
                dts.setNro_ci(txtci.getText());
                dts.setDireccion(txtdireccion.getText());
                dts.setTelefono(txtTelefono.getText());
                func.insertar(dts);
                //login("", "");
                mostrar("");
            }
            if (accion.equals("modificar")) {
                Fempleados func = new Fempleados();
                Vempleados dts = new Vempleados();
//                if (!txt.equalsIgnoreCase(txtuser.getText())) {
//                    if (validardescdup(txtuser.getText())) {
//                        JOptionPane.showMessageDialog(this, "Ese dato ya existe");
//                        txtuser.requestFocus();
//                        return;
//                    }
//                }
                dts.setUser(txtuser.getText());
                dts.setContraseña(txtcontraseña.getText());
                dts.setNombre(txtNombre.getText());
                dts.setApellido(txtApellido.getText());
                dts.setNro_ci(txtci.getText());
                dts.setDireccion(txtdireccion.getText());
                dts.setTelefono(txtTelefono.getText());
                dts.setIdRol(txtidrol.getText());
                dts.setIddistrito(txtiddistrito.getText());
                dts.setIdempleado(txtCodigo.getText());
                //login("", "");
                func.actualizar(dts);
                modificar();
                mostrar("");
            }
            mirar();
            tabpane.setSelectedIndex(tabpane.indexOfComponent(pBuscar));
        }

    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        accion = "guardar";
        modificar();
        tabpane.setSelectedIndex(tabpane.indexOfComponent(pNuevo));
        txtCodigo.requestFocusInWindow();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        if (tblRegistro.getSelectedRows().length > 0) { //verifica que si ha seleccionadtxtCodigo, en caso de ser sleccionado modifica
            accion = "modificar";
            modificar();
            txtCodigo.setEnabled(false);
            tabpane.setSelectedIndex(tabpane.indexOfComponent(pNuevo));
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un registro");
    }//GEN-LAST:event_btnEditarActionPerformed
    }
    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void txtuserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtuserActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtuserActionPerformed

    private void txtCodigoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCodigoFocusLost
        if (txtCodigo.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese el codigo");
            txtCodigo.requestFocus();
            txtCodigo.setBackground(Color.GREEN);
        } else {
            validarid();
        }

    }//GEN-LAST:event_txtCodigoFocusLost

    private void btnocultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnocultarActionPerformed
        if (!txtCodigo.getText().equals("")) {
            int confirmacion = JOptionPane.showConfirmDialog(rootPane, "Esta seguro de que quiere ocultar el registro?");
            if (confirmacion == 0) {
                Fempleados func = new Fempleados();
                Vempleados dts = new Vempleados();
                dts.setIdempleado(txtCodigo.getText());
                func.ocultar(dts);
                mostrar("");
                mirar();
            }
        }
    }//GEN-LAST:event_btnocultarActionPerformed

    private void btnActivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActivarActionPerformed
        if (!txtCodigo.getText().equals("")) {
            int confirmacion = JOptionPane.showConfirmDialog(rootPane, "Esta seguro de que quiere activar el registro?");
            if (confirmacion == 0) {
                Fempleados func = new Fempleados();
                Vempleados dts = new Vempleados();
                dts.setIdempleado(txtCodigo.getText());
                func.activar(dts);
                mostrar("");
                mirar();
            }
        }
    }//GEN-LAST:event_btnActivarActionPerformed

    private void txtCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoActionPerformed

    private void tblRegistroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblRegistroMouseClicked
        int fila = tblRegistro.rowAtPoint(evt.getPoint());
        txtCodigo.setText(tblRegistro.getValueAt(fila, 0).toString());
        txtuser.setText(tblRegistro.getValueAt(fila, 1).toString());
        txtcontraseña.setText(tblRegistro.getValueAt(fila, 2).toString());
        txtNombre.setText(tblRegistro.getValueAt(fila, 3).toString());

        txtApellido.setText(tblRegistro.getValueAt(fila, 4).toString());
        txtci.setText(tblRegistro.getValueAt(fila, 5).toString());
        txtdireccion.setText(tblRegistro.getValueAt(fila, 6).toString());
        txtTelefono.setText(tblRegistro.getValueAt(fila, 7).toString());

        txtiddistrito.setText(tblRegistro.getValueAt(fila, 8).toString());
        txtdistrito.setText(tblRegistro.getValueAt(fila, 9).toString());
        txtidrol.setText(tblRegistro.getValueAt(fila, 10).toString());
        txtrol.setText(tblRegistro.getValueAt(fila, 11).toString());
        txt = tblRegistro.getValueAt(fila, 1).toString();
    }//GEN-LAST:event_tblRegistroMouseClicked

    private void btncancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelarActionPerformed
        mirar();
//        txtDescripcion.setBackground(Color.white);
        tabpane.setSelectedIndex(tabpane.indexOfComponent(pBuscar));
        /*cuando se haga click en el boton de cancelar para que vuelva en
        la pestana de buscar*/
    }//GEN-LAST:event_btncancelarActionPerformed

    private void txtCodigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyTyped
        // TODO add your handling code here:
        char key = evt.getKeyChar(); // Obtiene el carácter de la tecla presionada

        if (!Character.isDigit(key)) { // Verifica si el carácter no es un dígito
            evt.consume(); // Ignora el carácter si no es un número
        }
    }//GEN-LAST:event_txtCodigoKeyTyped

    private void txtciActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtciActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtciActionPerformed

    private void txtuserKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtuserKeyTyped
        // TODO add your handling code here:
        int key = evt.getKeyChar();// es para que solo ingresen letras y espacio
        boolean letras = (key >= 65 && key <= 90) || (key >= 97 && key <= 122)
                || (key == 32);
        if (!letras) {
            evt.consume();
        }
    }//GEN-LAST:event_txtuserKeyTyped

    private void txtcontraseñaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcontraseñaKeyTyped
        // TODO add your handling code here:
        char key = evt.getKeyChar(); // Obtiene el carácter de la tecla presionada

        if (!Character.isDigit(key)) { // Verifica si el carácter no es un dígito
            evt.consume(); // Ignora el carácter si no es un número
        }
    }//GEN-LAST:event_txtcontraseñaKeyTyped

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        // TODO add your handling code here
        int key = evt.getKeyChar();// es para que solo ingresen letras y espacio
        boolean letras = (key >= 65 && key <= 90) || (key >= 97 && key <= 122)
                || (key == 32);
        if (!letras) {
            evt.consume();
        }
    }//GEN-LAST:event_txtNombreKeyTyped

    private void txtApellidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidoKeyTyped
        // TODO add your handling code here
        int key = evt.getKeyChar();// es para que solo ingresen letras y espacio
        boolean letras = (key >= 65 && key <= 90) || (key >= 97 && key <= 122)
                || (key == 32);
        if (!letras) {
            evt.consume();
        }
    }//GEN-LAST:event_txtApellidoKeyTyped

    private void txtcontraseñaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcontraseñaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcontraseñaActionPerformed

    private void txtTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyTyped
        // TODO add your handling code here:
        char key = evt.getKeyChar(); // Obtiene el carácter de la tecla presionada

        if (!Character.isDigit(key)) { // Verifica si el carácter no es un dígito
            evt.consume(); // Ignora el carácter si no es un número
        }
    }//GEN-LAST:event_txtTelefonoKeyTyped

    private void txtdireccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdireccionKeyTyped
        int key = evt.getKeyChar();// es para que solo ingresen letras y espacio
        boolean letras = (key >= 65 && key <= 90) || (key >= 97 && key <= 122)
                || (key == 32);
        if (!letras) {
            evt.consume();
        }
    }//GEN-LAST:event_txtdireccionKeyTyped

    private void btnimprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnimprimirActionPerformed
        // TODO add your handling code here:
        Map<String, Object> parameters = new HashMap<>();
        conexionReport.iniciarReport("C:\\Users\\"
                + "admin\\JaspersoftWorkspace\\MyReports\\reporte_empleados.jasper", parameters);
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
            java.util.logging.Logger.getLogger(FRM_empleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FRM_empleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FRM_empleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FRM_empleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FRM_empleados().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActivar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnMostrarRol;
    private javax.swing.JButton btnMostrardistrito;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btncancelar;
    private javax.swing.JButton btnimprimir;
    private javax.swing.JButton btnocultar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtci;
    private javax.swing.JTextField txtcontraseña;
    private javax.swing.JTextField txtdireccion;
    public static javax.swing.JTextField txtdistrito;
    public static javax.swing.JTextField txtiddistrito;
    public static javax.swing.JTextField txtidrol;
    public static javax.swing.JTextField txtrol;
    private javax.swing.JTextField txtuser;
    // End of variables declaration//GEN-END:variables
}
