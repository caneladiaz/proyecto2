/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import datos.Vinsumos;
import datos.VpedidosCompra;
import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import logica.Finsumos;
import logica.Fpedido_compra;
import logica.conexionReport;

/**
 *
 * @author admin
 */
public class FRM_INSUMO extends javax.swing.JInternalFrame {

    /**
     * Creates new form FRM_INSUMO
     */
    public FRM_INSUMO() {
        initComponents();
        mostrar("");
        mirar();
    }

    public String accion = "guardar";
    public String txt = "";

    void mirar() {
        txtidproducto.setEnabled(false);
        txtProducto.setEnabled(false);
        tblRegistro.setEnabled(true);
        txtidproeedor.setEnabled(false);
        txtproveedor.setEnabled(false);
        txtcantidad.setEnabled(false);
        txtidcategoria.setEnabled(false);
        txtidunidades.setEnabled(false);
        txtunidades.setEnabled(false);
        txtcompra.setEnabled(false);

        btnNuevo.setEnabled(true);
        btneditar.setEnabled(true);
        btnGuardar.setEnabled(true);
        btnocultar.setEnabled(true);
        btnActivar.setEnabled(true);
        btneditar.setEnabled(true);

        txtidproducto.setText("");
        txtProducto.setText("");
        txtidproeedor.setText("");
        txtproveedor.setText("");
        txtcantidad.setText("");
        txtidcategoria.setText("");
        txtcategoria.setText("");
        txtidunidades.setText("");
        txtunidades.setText("");
        txtcompra.setText("");

        tblRegistro.clearSelection();

    }

    void modificar() {
        txtidproducto.setEnabled(true);
        txtProducto.setEnabled(true);

        tblRegistro.setEnabled(true);
        txtidproeedor.setEnabled(false);
        txtproveedor.setEnabled(false);
        txtidcategoria.setEnabled(false);
        txtcategoria.setEnabled(false);
        txtidunidades.setEnabled(false);
        txtunidades.setEnabled(false);

        txtcantidad.setEnabled(true);
        txtcompra.setEnabled(true);

//        btnNuevo.setEnabled(false);
//        btneditar.setEnabled(false);
        btnGuardar.setEnabled(true);
        btncancelar.setEnabled(true);

        btnMostrarproveedor.setEnabled(true);
        btnMostrarcategoria.setEnabled(true);
        btnMostrarUnidades.setEnabled(true);

    }

    void mostrar(String buscar) {
        try {
            DefaultTableModel modelo;
            Finsumos func = new Finsumos();
            modelo = func.mostrar(buscar);//tiene los valores de select
            tblRegistro.setModel(modelo);//se pasan los datos de la tabla
            lblTotalRegistro.setText("Total de Registro " + func.totalregistro);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void validarid() {
        Fpedido_compra func = new Fpedido_compra();
        VpedidosCompra dts = new VpedidosCompra();
        String idFactura = txtidproducto.getText(); // Obtener el ID de factura del JTextField
        if (!func.existeIdFactura(idFactura)) {
            // Lógica para insertar la factura ya que no existe            
        } else {
            JOptionPane.showMessageDialog(null, "El ID de la factura ya existe en la base de datos.");
            txtidproducto.setText("");
            txtidproducto.requestFocus();
        }
    }

    public boolean validar() {
        if (txtProducto.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese el producto");
            txtProducto.requestFocus();
            txtProducto.setBackground(Color.YELLOW);

            return false;
        } else if (txtcompra.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese la compra");
            txtcompra.requestFocus();
            txtcompra.setBackground(Color.YELLOW);
            return false;
        } else if (txtcantidad.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese la cantidad");
            txtcantidad.requestFocus();
            txtcantidad.setBackground(Color.YELLOW);
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
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
        pNuevo1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtidproducto = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtcompra = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtProducto = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtcantidad = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtidproeedor = new javax.swing.JTextField();
        txtproveedor = new javax.swing.JTextField();
        btnMostrarproveedor = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txtidcategoria = new javax.swing.JTextField();
        txtcategoria = new javax.swing.JTextField();
        btnMostrarcategoria = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        txtidunidades = new javax.swing.JTextField();
        txtunidades = new javax.swing.JTextField();
        btnMostrarUnidades = new javax.swing.JButton();
        btnimprimir = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        btnNuevo = new javax.swing.JButton();
        btneditar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btncancelar = new javax.swing.JButton();
        btnocultar = new javax.swing.JButton();
        btnActivar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconifiable(true);

        pBuscar.setBackground(new java.awt.Color(255, 255, 102));
        pBuscar.setForeground(new java.awt.Color(51, 51, 51));

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
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 658, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
            .addGroup(pBuscarLayout.createSequentialGroup()
                .addGap(59, 59, 59)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTotalRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        tabpane.addTab("Buscar", pBuscar);

        pNuevo1.setBackground(new java.awt.Color(204, 204, 0));

        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel2.setText("Id Producto:");

        txtidproducto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtidproductoFocusLost(evt);
            }
        });
        txtidproducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtidproductoActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel5.setText("P. compra:");

        txtcompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcompraActionPerformed(evt);
            }
        });
        txtcompra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcompraKeyTyped(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel3.setText("producto:");

        txtProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtProductoKeyTyped(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel10.setText("cantidad");

        txtcantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcantidadKeyTyped(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel8.setText("proveedor");

        txtidproeedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtidproeedorActionPerformed(evt);
            }
        });

        txtproveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtproveedorActionPerformed(evt);
            }
        });

        btnMostrarproveedor.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        btnMostrarproveedor.setText("...");
        btnMostrarproveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarproveedorActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel9.setText("categoria");

        txtidcategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtidcategoriaActionPerformed(evt);
            }
        });

        txtcategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcategoriaActionPerformed(evt);
            }
        });

        btnMostrarcategoria.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        btnMostrarcategoria.setText("...");
        btnMostrarcategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarcategoriaActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel11.setText("unidades");

        txtidunidades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtidunidadesActionPerformed(evt);
            }
        });

        txtunidades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtunidadesActionPerformed(evt);
            }
        });

        btnMostrarUnidades.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        btnMostrarUnidades.setText("...");
        btnMostrarUnidades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarUnidadesActionPerformed(evt);
            }
        });

        btnimprimir.setText("imprimir");
        btnimprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnimprimirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pNuevo1Layout = new javax.swing.GroupLayout(pNuevo1);
        pNuevo1.setLayout(pNuevo1Layout);
        pNuevo1Layout.setHorizontalGroup(
            pNuevo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pNuevo1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(pNuevo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pNuevo1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(41, 41, 41))
                    .addGroup(pNuevo1Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(pNuevo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtidproducto, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pNuevo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pNuevo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtcantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pNuevo1Layout.createSequentialGroup()
                        .addComponent(txtcompra, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)
                        .addComponent(btnimprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(90, 90, 90))
            .addGroup(pNuevo1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(pNuevo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pNuevo1Layout.createSequentialGroup()
                        .addGroup(pNuevo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pNuevo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pNuevo1Layout.createSequentialGroup()
                                .addComponent(txtidproeedor, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(245, 245, 245)
                                .addComponent(btnMostrarproveedor))
                            .addComponent(txtidcategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pNuevo1Layout.createSequentialGroup()
                                .addGap(79, 79, 79)
                                .addGroup(pNuevo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtproveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtcategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(btnMostrarcategoria))))
                    .addGroup(pNuevo1Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtidunidades, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(txtunidades, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnMostrarUnidades)
                        .addGap(2, 2, 2)))
                .addContainerGap(382, Short.MAX_VALUE))
        );
        pNuevo1Layout.setVerticalGroup(
            pNuevo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pNuevo1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pNuevo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtidproducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtcompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnimprimir))
                .addGap(28, 28, 28)
                .addGroup(pNuevo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtcantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(77, 77, 77)
                .addGroup(pNuevo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtidproeedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtproveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMostrarproveedor))
                .addGap(26, 26, 26)
                .addGroup(pNuevo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtidcategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(txtcategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMostrarcategoria))
                .addGap(38, 38, 38)
                .addGroup(pNuevo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtidunidades, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtunidades, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMostrarUnidades)
                    .addComponent(jLabel11))
                .addContainerGap(278, Short.MAX_VALUE))
        );

        tabpane.addTab("Nuevo/Modificar", pNuevo1);

        jPanel1.setBackground(new java.awt.Color(255, 255, 153));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("ACCIONES:"));

        btnNuevo.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btneditar.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        btneditar.setText("Editar");
        btneditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditarActionPerformed(evt);
            }
        });

        btnGuardar.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
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
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btneditar, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btncancelar)
                        .addGap(18, 18, 18)
                        .addComponent(btnActivar, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 10, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnGuardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnocultar, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNuevo)
                    .addComponent(btneditar))
                .addGap(109, 109, 109)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnocultar))
                .addGap(119, 119, 119)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btncancelar)
                    .addComponent(btnActivar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSalir)
                .addGap(128, 128, 128))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabpane)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabpane, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblRegistroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblRegistroMouseClicked
        int fila = tblRegistro.rowAtPoint(evt.getPoint()); //recoge la fila que es selccionada coon el mouse
        txtidproducto.setText(tblRegistro.getValueAt(fila, 0).toString());
        txtProducto.setText(tblRegistro.getValueAt(fila, 1).toString());
        txtcantidad.setText(tblRegistro.getValueAt(fila, 2).toString());

        txtcompra.setText(tblRegistro.getValueAt(fila, 3).toString());

        txtidproeedor.setText(tblRegistro.getValueAt(fila, 5).toString());
        txtproveedor.setText(tblRegistro.getValueAt(fila, 6).toString());

        txtidcategoria.setText(tblRegistro.getValueAt(fila, 7).toString());
        txtcategoria.setText(tblRegistro.getValueAt(fila, 8).toString());

        txtidunidades.setText(tblRegistro.getValueAt(fila, 9).toString());
        txtunidades.setText(tblRegistro.getValueAt(fila, 10).toString());

        txt = tblRegistro.getValueAt(fila, 1).toString();
    }//GEN-LAST:event_tblRegistroMouseClicked

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        mostrar(txtBuscar.getText());//para buscar, al ir escribiendo tiene que traer
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        accion = "guardar";
        modificar();
        txtidproducto.requestFocusInWindow();
        tabpane.setSelectedIndex(tabpane.indexOfComponent(pNuevo1));
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btneditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditarActionPerformed
        if (tblRegistro.getSelectedRows().length > 0) { //verifica que si ha seleccionado un registro, en caso de ser sleccionado modifica
            accion = "modificar";
            tabpane.setSelectedIndex(tabpane.indexOfComponent(pNuevo1));
            modificar();
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un registro");
        }
    }//GEN-LAST:event_btneditarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (validar() == true) {
            if (accion.equals("guardar")) {
                Finsumos func = new Finsumos();
                Vinsumos dts = new Vinsumos();

                dts.setIdinsumos(txtidproducto.getText());
                dts.setDescripcion(txtProducto.getText());
                dts.setPrecio_compra(Double.parseDouble(txtcompra.getText()));

                dts.setCantidad(Double.valueOf(txtcantidad.getText()));

                dts.setIdproveedor(txtidproeedor.getText());
                dts.setIdcategoria(txtidcategoria.getText());
                dts.setIdunidades(txtidunidades.getText());

                func.insertar(dts);
                mostrar("");
            }
            if (accion.equals("modificar")) {
                Finsumos func = new Finsumos();
                Vinsumos dts = new Vinsumos();

                dts.setDescripcion(txtProducto.getText());
                dts.setCantidad(Double.valueOf(txtcantidad.getText()));
                dts.setPrecio_compra(Double.parseDouble(txtcompra.getText()));

                dts.setIdproveedor(txtidproeedor.getText());
                dts.setIdcategoria(txtidcategoria.getText());
                dts.setIdunidades(txtidunidades.getText());
                dts.setIdinsumos(txtidproducto.getText());

                func.actualizar(dts);
                modificar();
                mostrar("");
            }
            mirar();
            tabpane.setSelectedIndex(tabpane.indexOfComponent(pBuscar));
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btncancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelarActionPerformed
        mirar();
        txtidproducto.setBackground(Color.white);
        tabpane.setSelectedIndex(tabpane.indexOfComponent(pBuscar));
        /*cuando se haga click en el boton de cancelar para que vuelva en
        la pestana de buscar*/
    }//GEN-LAST:event_btncancelarActionPerformed

    private void btnocultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnocultarActionPerformed
        if (!txtidproducto.getText().equals("")) {
            int confirmacion = JOptionPane.showConfirmDialog(rootPane, "Esta seguro de que quiere ocultar el registro?");
            if (confirmacion == 0) {
                Finsumos func = new Finsumos();
                Vinsumos dts = new Vinsumos();
                dts.setIdinsumos(txtidproducto.getText());
                func.ocultar(dts);
                mostrar("");
                mirar();
            }
        }
    }//GEN-LAST:event_btnocultarActionPerformed

    private void btnActivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActivarActionPerformed
        if (!txtidproducto.getText().equals("")) {
            int confirmacion = JOptionPane.showConfirmDialog(rootPane, "Esta seguro de que quiere activar el registro?");
            if (confirmacion == 0) {
                Finsumos func = new Finsumos();
                Vinsumos dts = new Vinsumos();
                dts.setIdinsumos(txtidproducto.getText());
                func.activar(dts);
                mostrar("");
                mirar();
            }
        }
    }//GEN-LAST:event_btnActivarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void txtidproductoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtidproductoFocusLost
        if (txtidproducto.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese el codigo");
            txtidproducto.requestFocus();
            txtidproducto.setBackground(Color.GREEN);
        } else {
            validarid();
        }
    }//GEN-LAST:event_txtidproductoFocusLost

    private void txtidproductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtidproductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtidproductoActionPerformed

    private void txtcompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcompraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcompraActionPerformed

    private void txtcompraKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcompraKeyTyped
        // TODO add your handling code here:
        // TODO add your handling code here:
        char key = evt.getKeyChar(); // Obtiene el carácter de la tecla presionada

        if (!Character.isDigit(key)) { // Verifica si el carácter no es un dígito
            evt.consume(); // Ignora el carácter si no es un número
        }
    }//GEN-LAST:event_txtcompraKeyTyped

    private void txtProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProductoKeyTyped
        // TODO add your handling code here:
        int key = evt.getKeyChar();// es para que solo ingresen letras y espacio
        boolean letras = (key >= 65 && key <= 90) || (key >= 97 && key <= 122)
                || (key == 32);
        if (!letras) {
            evt.consume();
        }
    }//GEN-LAST:event_txtProductoKeyTyped

    private void txtcantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcantidadKeyTyped
        // TODO add your handling code here:
        char key = evt.getKeyChar(); // Obtiene el carácter de la tecla presionada

        if (!Character.isDigit(key)) { // Verifica si el carácter no es un dígito
            evt.consume(); // Ignora el carácter si no es un número
        }
    }//GEN-LAST:event_txtcantidadKeyTyped

    private void txtidproeedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtidproeedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtidproeedorActionPerformed

    private void txtproveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtproveedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtproveedorActionPerformed

    private void btnMostrarproveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarproveedorActionPerformed
        frmMostrarPoveedor form = new frmMostrarPoveedor();
        form.setVisible(true);
        form.toFront();
    }//GEN-LAST:event_btnMostrarproveedorActionPerformed

    private void txtidcategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtidcategoriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtidcategoriaActionPerformed

    private void txtcategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcategoriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcategoriaActionPerformed

    private void btnMostrarcategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarcategoriaActionPerformed
        // TODO add your handling code here
        frmMostrarCategoria form = new frmMostrarCategoria();
        form.setVisible(true);
        form.toFront();
    }//GEN-LAST:event_btnMostrarcategoriaActionPerformed

    private void txtidunidadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtidunidadesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtidunidadesActionPerformed

    private void txtunidadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtunidadesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtunidadesActionPerformed

    private void btnMostrarUnidadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarUnidadesActionPerformed
        // TODO add your handling code here:
        frmMostrarUnidades form = new frmMostrarUnidades();
        form.setVisible(true);
        form.toFront();
    }//GEN-LAST:event_btnMostrarUnidadesActionPerformed

    private void btnimprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnimprimirActionPerformed
       Map<String, Object> parameters = new HashMap<>();

        conexionReport.iniciarReport("C:\\Users\\admin\\JaspersoftWorkspace"
                + "\\MyReports\\reporte_productos.jasper", parameters);
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
            java.util.logging.Logger.getLogger(FRM_INSUMO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FRM_INSUMO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FRM_INSUMO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FRM_INSUMO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FRM_INSUMO().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActivar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnMostrarUnidades;
    private javax.swing.JButton btnMostrarcategoria;
    private javax.swing.JButton btnMostrarproveedor;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btncancelar;
    private javax.swing.JButton btneditar;
    private javax.swing.JButton btnimprimir;
    private javax.swing.JButton btnocultar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTotalRegistro;
    private javax.swing.JPanel pBuscar;
    private javax.swing.JPanel pNuevo1;
    private javax.swing.JTabbedPane tabpane;
    private javax.swing.JTable tblRegistro;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtProducto;
    private javax.swing.JTextField txtcantidad;
    public static javax.swing.JTextField txtcategoria;
    private javax.swing.JTextField txtcompra;
    public static javax.swing.JTextField txtidcategoria;
    private javax.swing.JTextField txtidproducto;
    public static javax.swing.JTextField txtidproeedor;
    public static javax.swing.JTextField txtidunidades;
    public static javax.swing.JTextField txtproveedor;
    public static javax.swing.JTextField txtunidades;
    // End of variables declaration//GEN-END:variables
}
