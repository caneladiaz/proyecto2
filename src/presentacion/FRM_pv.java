package presentacion;

import datos.VdetalleFacCompra;
import datos.VfacturaCompra;
import datos.Vinsumos;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import logica.FfacturaCompra;
import logica.Finsumos;
import java.util.Calendar;
import java.util.Date;
import logica.FdetalleFacCompra;
import logica.Fempleados;
import logica.Fpedido_compra;
import logica.Fproveedor;

/**
 *
 * @author admin
 */
public class FRM_pv extends javax.swing.JInternalFrame {

    public static String idusuario, idcompra;
    DefaultTableModel modelo = new DefaultTableModel();
    static ResultSet rs = null;

    public FRM_pv() {
        initComponents();
        setResizable(false);
        setTitle("Factura Compra");
        //setLocationRelativeTo(null);
        txtFecha.setDate(new Date());
        // Inicializar el modelo de la tabla
        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Descripción");
        modeloTabla.addColumn("Precio");
        modeloTabla.addColumn("Cantidad");
        modeloTabla.addColumn("Subtotal");
        jTableDetalle.setModel(modeloTabla);

        inhabilitar();
//        String Titulos[] = {"ID", "PRODUCTO", "CANTIDAD", "PRECIO", "TOTAL"};
//        modelo.setColumnIdentifiers(Titulos);
//        jTableDetalle.setModel(modelo);
//
//        java.util.Date date = new java.util.Date();
//        String format = new String("dd/MM/yyyy");
//        SimpleDateFormat formato = new SimpleDateFormat(format);
//        txtFecha.setDate(date);
        //txtidusuario.setText(frmPrincipal.lblID.getText());
        //txtusario.setText(frmPrincipal.lblNombre.getText()+" "+frmPrincipal.lblApellido.getText());
    }

    private String accion = "guardar";

    //Para después de cancelar alguna acción, deshabilita y vacia txt
    void inhabilitar() {
        txtIdEmpleado.setEnabled(false);
        txtPrecioCompra.setEnabled(false);
        txtIdProducto.setEnabled(false);
        txtProducto.setEnabled(false);
        txtCantidad.setEnabled(false);
        txtIdFacturaCompra.setEnabled(false);
        txtEmpleado.setEnabled(false);
        txtRucProveedor.setEnabled(false);
        txtFecha.setEnabled(false);
        txtSubtotal.setEnabled(false);
        txtIdProveedor.setEnabled(false);
        txtProveedor.setEnabled(false);
        txtTelefonoProv.setEnabled(false);
        txtTotalPago.setEnabled(false);
        txtTotalItems.setEnabled(false);
        txtTotalArticulos.setEnabled(false);
        txtIdPedido.setEnabled(false);

        jTableDetalle.setEnabled(true);

        btnBuscarcliente.setEnabled(false);
        btnMostrarEmpleado.setEnabled(false);
        btnBuscarProducto.setEnabled(false);
        btnagregar.setEnabled(false);
        btnNuevo.setEnabled(true);
        btnCancelar.setEnabled(false);
        btnSalir.setEnabled(true);
        btnGuardar.setEnabled(false);
    }

    void limpiar() {
        txtIdFacturaCompra.setText("");
        txtIdProveedor.setText("");
        txtProveedor.setText("");
        txtTelefonoProv.setText("");
        txtRucProveedor.setText("");
        txtIdEmpleado.setText("");
        txtEmpleado.setText("");
        txtIdProducto.setText("");
        txtProducto.setText("");
        txtPrecioCompra.setText("");
        txtCantidad.setText("");
        txtSubtotal.setText("");
        txtTotalPago.setText("");
        txtTotalItems.setText("");
        txtTotalArticulos.setText("");
        txtIdPedido.setText("");
        limpiarTabla();
        txtIdFacturaCompra.requestFocus();
    }

    void habilitar() {
//        txtidventa.setVisible(false);
        jTableDetalle.setEnabled(true);
        txtCantidad.setEnabled(true);
        txtIdFacturaCompra.setEnabled(true);
        txtFecha.setEnabled(true);
//        txtPrecioCompra.setEnabled(true);

        btnBuscarcliente.setEnabled(true);
        btnMostrarEmpleado.setEnabled(true);
        btnBuscarProducto.setEnabled(true);
        btnagregar.setEnabled(true);
        btnNuevo.setEnabled(true);
        btnCancelar.setEnabled(true);
        btnSalir.setEnabled(false);
        btnGuardar.setEnabled(true);

        txtPrecioCompra.setText("");
        txtProducto.setText("");
        txtCantidad.setText("");
        txtIdFacturaCompra.setText("");
        txtSubtotal.setText("");
        txtRucProveedor.setText("");
        //tblDetalle.clearSelection(true);

    }

    //este méodo valida el id
    public void validarid() {
        FfacturaCompra func = new FfacturaCompra();
        VfacturaCompra dts = new VfacturaCompra();
        String idFactura = txtIdFacturaCompra.getText(); // Obtener el ID de factura del JTextField
        if (!func.existeIdFactura(idFactura)) {
            // Lógica para insertar la factura ya que no existe            
        } else {
            JOptionPane.showMessageDialog(null, "El ID de la factura ya existe en la base de datos.");
            txtIdFacturaCompra.setText("");
            txtIdFacturaCompra.requestFocus();
        }
    }

    public boolean validar() {
        int filas = jTableDetalle.getRowCount();
        if (!txtIdFacturaCompra.getText().equals("")) {
            if (txtIdProveedor.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Ingrese el dato");
                return false;
            } else if (txtIdEmpleado.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Ingrese el dato");
                return false;
            } else if (filas == 0) {
                JOptionPane.showMessageDialog(null, "Agregue al menos un ARTICULO");
                btnBuscarProducto.requestFocus();
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    private DefaultTableModel modeloTabla;
    private int totalItems = 0;
    private int totalArticulos = 0;
    private double totalPago = 0;
    DecimalFormat df = new DecimalFormat("###");

    private void agregarProducto() {
        // Obtener los datos de los campos de texto
        String id = txtIdProducto.getText();
        String descripcion = txtProducto.getText();
        String precioStr = txtPrecioCompra.getText();
        String cantidadStr = txtCantidad.getText();

        // Validar que los campos no estén vacíos
        if (id.isEmpty() || descripcion.isEmpty() || precioStr.isEmpty() || cantidadStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, rellena todos los campos.");
            return;
        }
        try {
            // Convertir el precio y la cantidad a los tipos de datos correctos
            double precio = Double.parseDouble(precioStr);
            int cantidad = Integer.parseInt(cantidadStr);
            double subtotal = precio * cantidad; // Calcular subtotal

            // Verificar si el producto ya existe en la tabla por su ID
            boolean productoExistente = false;
            for (int i = 0; i < modeloTabla.getRowCount(); i++) {
                String idExistente = (String) modeloTabla.getValueAt(i, 0); // ID está en la columna 0
                if (idExistente.equals(id)) { // Producto ya existe
                    // Obtener la cantidad actual y sumarle la nueva cantidad
                    int cantidadActual = (int) modeloTabla.getValueAt(i, 3); // Cantidad está en la columna 3
                    int nuevaCantidad = cantidadActual + cantidad;

                    // Calcular el nuevo subtotal
                    Double nuevoSubtotal = (nuevaCantidad * precio);

                    // Actualizar la cantidad y el subtotal en la tabla
                    modeloTabla.setValueAt(nuevaCantidad, i, 3); // Actualizar la cantidad
                    modeloTabla.setValueAt(df.format(nuevoSubtotal), i, 4); // Actualizar el subtotal

                    // Actualizar los contadores
                    totalArticulos += cantidad;
                    totalPago += subtotal;

                    productoExistente = true; // Marcar que el producto ya existe
                    break; // No es necesario seguir buscando
                }
            }
            // Si el producto no existe en la tabla, agregarlo como nueva fila
            if (!productoExistente) {
                modeloTabla.addRow(new Object[]{id, descripcion, df.format(precio), cantidad, df.format(subtotal)});
                // Actualizar los contadores
                totalItems++;
                totalArticulos += cantidad;
                totalPago += subtotal;
            }
            // Limpiar los campos de texto después de agregar
            txtIdProducto.setText("");
            txtProducto.setText("");
            txtPrecioCompra.setText("");
            txtCantidad.setText("");
            txtSubtotal.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El precio debe ser un número decimal y la cantidad un número entero.");
        }
    }

    private void calcularSubtotal() {
        // Verificar que los campos de precio y cantidad no estén vacíos
        String precioStr = txtPrecioCompra.getText();
        String cantidadStr = txtCantidad.getText();
        if (precioStr.isEmpty() || cantidadStr.isEmpty()) {
            return;
        }
        try {
            double precio = Double.parseDouble(precioStr);
            int cantidad = Integer.parseInt(cantidadStr);
            double subtotal = precio * cantidad;
            //para mostrar en el txtsubtotal
            txtSubtotal.setText(String.valueOf(df.format(subtotal)));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El precio debe ser un número decimal y la cantidad un número entero.");
        }
    }

    private void calcularIVA() {
        try {
            // Obtener el porcentaje de IVA seleccionado (5% o 10%)
            String ivaSeleccionado = ComboIva.getSelectedItem().toString(); // Ejemplo: JComboBox con "5" y "10"
            double porcentajeIVA = Double.parseDouble(ivaSeleccionado);

            // Calcular el IVA sobre el totalPago
            double iva = totalPago * (porcentajeIVA / 100);

            // Mostrar el IVA calculado en el campo txtIVA
            txtTotalIva.setText(df.format(iva));

            // Actualizar el total con IVA
            double totalConIVA = totalPago + iva;
            txtTotalPago.setText(df.format(totalConIVA));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Error al calcular el IVA.");
        }
    }

    private void actualizarTotales() {
        txtTotalItems.setText(String.valueOf(totalItems));
        txtTotalArticulos.setText(String.valueOf(totalArticulos));
    }

    void guardarDetalle() {
        FdetalleFacCompra func = new FdetalleFacCompra();
        VdetalleFacCompra dts = new VdetalleFacCompra();
        Finsumos funcMateriales = new Finsumos(); // Instancia para gestionar materiales
        int fila = 0;
        fila = jTableDetalle.getRowCount();
        for (int f = 0; f < fila; f++) {
            dts.setIdfactura_compra(txtIdFacturaCompra.getText());
            dts.setIdinsumos(jTableDetalle.getModel().getValueAt(f, 0).toString());
            dts.setCantidad(Double.parseDouble(jTableDetalle.getModel().getValueAt(f, 3).toString()));
            dts.setPrecio_compra(Double.parseDouble(jTableDetalle.getModel().getValueAt(f, 2).toString()));
            dts.setTotal_detalle(jTableDetalle.getModel().getValueAt(f, 4).toString());
            func.insertar(dts);

            try {
                // Actualizar el stock llamando al método de la clase fmateriales
                int idProducto = Integer.parseInt(jTableDetalle.getModel().getValueAt(f, 0).toString());
                double cantidadVendida = Double.parseDouble(jTableDetalle.getModel().getValueAt(f, 3).toString());

                if (!funcMateriales.actualizarStock(idProducto, cantidadVendida)) {
                    JOptionPane.showMessageDialog(this, "Error al actualizar el stock del producto", "Error", ERROR);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
        JOptionPane.showMessageDialog(null, "LA VENTA HA SIDO EXITOSA");

    }

    void limpiarTabla() {
        try {
            modeloTabla.setRowCount(0); // Limpia todas las filas del modelo de tabla
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al limpiar la tabla: " + e.getMessage());
        }
        txtTotalPago.setText("");
        txtTotalItems.setText("");
        txtTotalArticulos.setText("");
        totalItems = 0;
        totalArticulos = 0;
        totalPago = 0;

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        btnNuevo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        txtRucProveedor = new javax.swing.JTextField();
        txtProveedor = new javax.swing.JTextField();
        txtIdProveedor = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnMostrarEmpleado = new javax.swing.JButton();
        txtIdFacturaCompra = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtIdEmpleado = new javax.swing.JTextField();
        txtEmpleado = new javax.swing.JTextField();
        btnBuscarcliente = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtIdProducto = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtProducto = new javax.swing.JTextField();
        txtPrecioCompra = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        btnagregar = new javax.swing.JButton();
        txtFecha = new com.toedter.calendar.JDateChooser();
        btnBuscarProducto = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        txtSubtotal = new javax.swing.JTextField();
        txtTelefonoProv = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtIdPedido = new javax.swing.JTextField();
        btnPedido = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableDetalle = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtTotalArticulos = new javax.swing.JTextField();
        txtTotalItems = new javax.swing.JTextField();
        txtTotalPago = new javax.swing.JTextField();
        ComboIva = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        txtTotalIva = new javax.swing.JTextField();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconifiable(true);
        setTitle("Factura Compra");

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("ACCIONES:"));

        btnNuevo.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCancelar)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardar)
                    .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(btnNuevo)
                .addGap(32, 32, 32)
                .addComponent(btnGuardar)
                .addGap(32, 32, 32)
                .addComponent(btnCancelar)
                .addGap(18, 18, 18)
                .addComponent(btnSalir)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(204, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Factura compra", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        txtProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProveedorActionPerformed(evt);
            }
        });

        txtIdProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdProveedorActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("proveedor");

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("RUC:");

        btnMostrarEmpleado.setText("...");
        btnMostrarEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarEmpleadoActionPerformed(evt);
            }
        });

        txtIdFacturaCompra.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtIdFacturaCompraFocusLost(evt);
            }
        });
        txtIdFacturaCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdFacturaCompraActionPerformed(evt);
            }
        });
        txtIdFacturaCompra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIdFacturaCompraKeyTyped(evt);
            }
        });

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Fecha emisión:");

        jLabel4.setBackground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Nro. Factura:");

        btnBuscarcliente.setText("...");
        btnBuscarcliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarclienteActionPerformed(evt);
            }
        });

        jLabel6.setBackground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Empleado");

        jLabel7.setBackground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Producto:");

        txtProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProductoActionPerformed(evt);
            }
        });

        txtPrecioCompra.setEditable(false);

        jLabel8.setBackground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Precio compra:");

        txtCantidad.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCantidadFocusLost(evt);
            }
        });
        txtCantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantidadActionPerformed(evt);
            }
        });
        txtCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCantidadKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidadKeyTyped(evt);
            }
        });

        jLabel10.setBackground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Cantidad:");

        btnagregar.setText("Agregar");
        btnagregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnagregarActionPerformed(evt);
            }
        });
        btnagregar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                btnagregarKeyReleased(evt);
            }
        });

        btnBuscarProducto.setText("...");
        btnBuscarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarProductoActionPerformed(evt);
            }
        });

        jLabel11.setBackground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Sub-Total:");

        txtTelefonoProv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelefonoProvActionPerformed(evt);
            }
        });

        jLabel9.setBackground(new java.awt.Color(0, 0, 0));
        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Telefono");

        txtIdPedido.setForeground(new java.awt.Color(0, 255, 255));
        txtIdPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdPedidoActionPerformed(evt);
            }
        });

        btnPedido.setText("pedido");
        btnPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPedidoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(267, 267, 267)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(txtRucProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnBuscarcliente, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(txtTelefonoProv, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 202, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(txtIdFacturaCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtIdEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIdProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(jLabel7))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(txtEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnMostrarEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel6))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 494, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))))
                .addContainerGap(73, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtIdProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBuscarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(txtPrecioCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(210, 210, 210)
                        .addComponent(jLabel8)))
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(189, 189, 189)
                                .addComponent(btnagregar, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addComponent(jLabel11))
                                    .addComponent(txtSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(34, 34, 34)
                                .addComponent(txtIdPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8))))
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnPedido)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtRucProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIdProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBuscarcliente)
                            .addComponent(txtProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTelefonoProv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(69, 69, 69)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel8))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtPrecioCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txtEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtIdEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnMostrarEmpleado))
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txtProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtIdProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnBuscarProducto)))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel11)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(11, 11, 11))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtIdFacturaCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtIdPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnPedido))
                        .addGap(41, 41, 41)))
                .addComponent(btnagregar, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Detalle ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTableDetalle.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTableDetalle);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 910, 288));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel5.setText("total de item:");

        jLabel14.setText("total Articulos");

        jLabel18.setText("total a pagar");

        txtTotalArticulos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtTotalArticulos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalArticulosActionPerformed(evt);
            }
        });

        txtTotalItems.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtTotalItems.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalItemsActionPerformed(evt);
            }
        });

        txtTotalPago.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtTotalPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalPagoActionPerformed(evt);
            }
        });

        ComboIva.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "5", "10" }));
        ComboIva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboIvaActionPerformed(evt);
            }
        });

        jLabel19.setText("total Iva");

        txtTotalIva.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtTotalIva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalIvaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel14)
                .addGap(18, 18, 18)
                .addComponent(txtTotalArticulos, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTotalItems, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTotalPago, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtTotalIva, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(ComboIva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel14)
                        .addComponent(txtTotalArticulos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(txtTotalItems, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel18)
                        .addComponent(txtTotalPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel19)
                        .addComponent(ComboIva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtTotalIva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 991, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.getAccessibleContext().setAccessibleName("Pedido de productos");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        accion = "guardar";
        habilitar();
        txtIdFacturaCompra.requestFocusInWindow();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (validar() == true) { //se valida que todos los campos estén ingresados
            if (accion.equals("guardar")) {
                FfacturaCompra func = new FfacturaCompra();
                VfacturaCompra dts = new VfacturaCompra();

                Fpedido_compra func1 = new Fpedido_compra();
                dts.setIdfactura_compra(txtIdFacturaCompra.getText());
                Long mfecha = txtFecha.getDate().getTime();
                java.sql.Date fecha = new java.sql.Date(mfecha);
                dts.setFecha(String.valueOf(fecha));

                dts.setIdempleado(txtIdEmpleado.getText());
                dts.setIdproveedor(txtIdProveedor.getText());
                dts.setIdpedidos_compra(txtIdPedido.getText());
                dts.setTotal_Pago(Double.parseDouble(txtTotalPago.getText()));
                dts.setTotal_item(txtTotalItems.getText());
                dts.setTotal_articulo(txtTotalArticulos.getText());
                try {
                    func1.cambiarEstadoPedido(Integer.parseInt(txtIdPedido.getText()), "Pagado");
                } catch (SQLException e) {
                    e.printStackTrace(); // Manejo de excepción
                    // Otras acciones de recuperación o notificación al usuario
                }

                func.insertar(dts);
                guardarDetalle();
                limpiar();
            }
        }

    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        limpiar();
        inhabilitar();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnMostrarEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarEmpleadoActionPerformed
//        frmMostrarempleado form = new frmMostrarempleado();
//        form.setVisible(true);
//        form.toFront();
        String id = txtIdEmpleado.getText(); // Obtiene el ID del JTextField
        Fempleados func = new Fempleados();
        func.buscarDatosPorID(id);
    }//GEN-LAST:event_btnMostrarEmpleadoActionPerformed

    private void btnBuscarclienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarclienteActionPerformed
//        frmMostrarPoveedor form = new frmMostrarPoveedor();
//        form.setVisible(true);
//        form.toFront();
        String id = txtIdProveedor.getText(); // Obtiene el ID del JTextField
        Fproveedor func = new Fproveedor();
        func.buscarDatosPorID(id);


    }//GEN-LAST:event_btnBuscarclienteActionPerformed

    private void txtProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProductoActionPerformed

    private void txtCantidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyReleased
        //CalcularSubTotal();
        int KeyCode = evt.getKeyCode();
        if (KeyCode == KeyEvent.VK_ENTER) {
            btnagregar.doClick();
        }
    }//GEN-LAST:event_txtCantidadKeyReleased

    private void btnagregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnagregarActionPerformed
        agregarProducto();
        calcularIVA();
        actualizarTotales();
    }//GEN-LAST:event_btnagregarActionPerformed

    private void btnagregarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnagregarKeyReleased
        //        CalcularSubTotal();
        //        int KeyCode= evt.getKeyCode();
        //        if(KeyCode==KeyEvent.VK_ENTER)btnagregar.requestFocus();{
        //
        //        }
        //
    }//GEN-LAST:event_btnagregarKeyReleased

    private void btnBuscarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarProductoActionPerformed
//        frmMostrarInsumos form = new frmMostrarInsumos();
//        form.setVisible(true);
//        form.toFront();

        String id = txtIdProducto.getText(); // Obtiene el ID del JTextField
        Finsumos func = new Finsumos();
        func.buscarDatosPorID(id);
    }//GEN-LAST:event_btnBuscarProductoActionPerformed

    private void txtIdProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdProveedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdProveedorActionPerformed

    private void txtProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProveedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProveedorActionPerformed

    private void txtTelefonoProvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefonoProvActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoProvActionPerformed

    private void txtTotalArticulosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalArticulosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalArticulosActionPerformed

    private void txtTotalItemsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalItemsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalItemsActionPerformed

    private void txtTotalPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalPagoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalPagoActionPerformed

    private void txtIdFacturaCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdFacturaCompraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdFacturaCompraActionPerformed

    private void txtIdFacturaCompraFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtIdFacturaCompraFocusLost
        if (txtIdFacturaCompra.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese el codigo");
            txtIdFacturaCompra.requestFocus();
            txtIdFacturaCompra.setBackground(Color.GREEN);
        } else {
            validarid();
        }
    }//GEN-LAST:event_txtIdFacturaCompraFocusLost

    private void txtCantidadFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCantidadFocusLost
        calcularSubtotal();
    }//GEN-LAST:event_txtCantidadFocusLost

    private void txtTotalIvaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalIvaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalIvaActionPerformed

    private void ComboIvaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboIvaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboIvaActionPerformed

    private void btnPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPedidoActionPerformed
        // TODO add your handling code here:
        frmMostrarpedido form = new frmMostrarpedido();
        form.setVisible(true);
        form.toFront();
    }//GEN-LAST:event_btnPedidoActionPerformed

    private void txtIdPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdPedidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdPedidoActionPerformed

    private void txtCantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyTyped
//         TODO add your handling code here:
        char key = evt.getKeyChar(); // Obtiene el carácter de la tecla presionada

        if (!Character.isDigit(key) || key == '0') { // Verifica si el carácter no es un dígito
            evt.consume(); // Ignora el carácter si no es un número
        }
//            evt.consume();
//            evt.consume();
    }//GEN-LAST:event_txtCantidadKeyTyped

    private void txtIdFacturaCompraKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdFacturaCompraKeyTyped
        // TODO add your handling code here:
        char key = evt.getKeyChar(); // Obtiene el carácter de la tecla presionada

        if (!Character.isDigit(key)) { // Verifica si el carácter no es un dígito
            evt.consume(); // Ignora el carácter si no es un número
        }
    }//GEN-LAST:event_txtIdFacturaCompraKeyTyped

    private void txtCantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidadActionPerformed

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
            java.util.logging.Logger.getLogger(FRM_pv.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FRM_pv.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FRM_pv.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FRM_pv.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FRM_pv().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboIva;
    private javax.swing.JButton btnBuscarProducto;
    private javax.swing.JButton btnBuscarcliente;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnMostrarEmpleado;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnPedido;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnagregar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableDetalle;
    public static javax.swing.JTextField txtCantidad;
    public static javax.swing.JTextField txtEmpleado;
    private com.toedter.calendar.JDateChooser txtFecha;
    public static javax.swing.JTextField txtIdEmpleado;
    private javax.swing.JTextField txtIdFacturaCompra;
    public static javax.swing.JTextField txtIdPedido;
    public static javax.swing.JTextField txtIdProducto;
    public static javax.swing.JTextField txtIdProveedor;
    public static javax.swing.JTextField txtPrecioCompra;
    public static javax.swing.JTextField txtProducto;
    public static javax.swing.JTextField txtProveedor;
    public static javax.swing.JTextField txtRucProveedor;
    public static javax.swing.JTextField txtSubtotal;
    public static javax.swing.JTextField txtTelefonoProv;
    private javax.swing.JTextField txtTotalArticulos;
    private javax.swing.JTextField txtTotalItems;
    private javax.swing.JTextField txtTotalIva;
    private javax.swing.JTextField txtTotalPago;
    // End of variables declaration//GEN-END:variables
}
