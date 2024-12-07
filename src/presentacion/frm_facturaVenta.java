package presentacion;

import datos.V_detalleVenta;
import datos.V_facturaVenta;
import datos.Vagendamiento;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import logica.F_FacturaVenta;
import logica.F_agendamiento;
import java.util.Calendar;
import java.util.Date;
import logica.F_detalleVenta;
import logica.F_servicios;
import logica.Fclientes;
import logica.Fempleados;
import static presentacion.frm_agendamiento.txtIdServicio;
import static presentacion.frm_agendamiento.txtServicio;

/**
 *
 * @author admin
 */
public class frm_facturaVenta extends javax.swing.JInternalFrame {

    public static String idusuario, idcompra;
    DefaultTableModel modelo = new DefaultTableModel();
    static ResultSet rs = null;

    public frm_facturaVenta() {
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

    }

    private String accion = "guardar";

    //Para después de cancelar alguna acción, deshabilita y vacia txt
    void inhabilitar() {
        txtIdServicio.setEnabled(false);
        txtServicio.setEnabled(false);
        txtIdEmpleado.setEnabled(false);
        txtPrecioCompra.setEnabled(false);
        txtIdAgendamiento.setEnabled(false);
        
//        txtIdAgendamiento.setEnabled(false);
//        txtAgendamiento.setEnabled(false);
        txtCantidad.setEnabled(false);
        txtIdFacturaCompra.setEnabled(false);
        txtEmpleado.setEnabled(false);
        txtFecha.setEnabled(false);
        txtSubtotal.setEnabled(false);
        txtTotalPago.setEnabled(false);
        txtTotalItems.setEnabled(false);
        txtTotalArticulos.setEnabled(false);
        txtcliente.setEnabled(false);
        txtidCliente.setEnabled(false);
        txtPrecioCompra.setEnabled(false);

        jTableDetalle.setEnabled(true);

        btnMostrarEmpleado.setEnabled(false);
//        btnBuscarAgendamiento.setEnabled(false);
        btnagregar.setEnabled(false);
        btnNuevo.setEnabled(true);
        btnCancelar.setEnabled(false);
        btnSalir.setEnabled(true);
        btnGuardar.setEnabled(false);
    }

    void limpiar() {
        txtIdServicio.setText("");
        txtServicio.setText("");
        txtIdFacturaCompra.setText("");
        txtIdEmpleado.setText("");
        txtEmpleado.setText("");
//        txtIdAgendamiento.setText("");
//        txtAgendamiento.setText("");
        txtPrecioCompra.setText("");
        txtCantidad.setText("");
        txtSubtotal.setText("");
        txtTotalPago.setText("");
        txtTotalItems.setText("");
        txtTotalArticulos.setText("");
        txtcliente.setText("");
        txtidCliente.setText("");
        txtPrecioCompra.setText("");
        limpiarTabla();
        txtIdFacturaCompra.requestFocus();
    }

    void habilitar() {
//        txtidventa.setVisible(false);
        jTableDetalle.setEnabled(true);
        txtCantidad.setEnabled(true);
        txtIdFacturaCompra.setEnabled(true);
        txtFecha.setEnabled(true);
        txtPrecioCompra.setEnabled(false);
        txtIdAgendamiento.setEnabled(false);
        txtIdAgendamiento.setEnabled(false);

        btnMostrarEmpleado.setEnabled(true);
//        btnBuscarAgendamiento.setEnabled(true);
        btnagregar.setEnabled(true);
        btnNuevo.setEnabled(true);
        btnCancelar.setEnabled(true);
        btnSalir.setEnabled(false);
        btnGuardar.setEnabled(true);

        txtPrecioCompra.setText("");
//        txtAgendamiento.setText("");
        txtCantidad.setText("");
        txtIdFacturaCompra.setText("");
        txtSubtotal.setText("");
        //tblDetalle.clearSelection(true);

    }

    //este méodo valida el id
    public void validarid() {
        F_FacturaVenta func = new F_FacturaVenta();
        V_facturaVenta dts = new V_facturaVenta();
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
            if (txtidCliente.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Ingrese el dato");
                return false;
            } else if (txtIdEmpleado.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Ingrese el dato");
                return false;
            } else if (filas == 0) {
                JOptionPane.showMessageDialog(null, "Agregue al menos un agendamiento");
//                btnBuscarAgendamiento.requestFocus();
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
        String id = txtIdServicio.getText();
        String descripcion = txtServicio.getText();
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
//            txtIdAgendamiento.setText("");
//            txtAgendamiento.setText("");
            txtPrecioCompra.setText("");
            txtCantidad.setText("");
            txtSubtotal.setText("");
            txtIdServicio.setText("");
            txtServicio.setText("");
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
        txtTotalPago.setText(String.valueOf(df.format(totalPago)));
    }

    void guardarDetalle() {
        F_detalleVenta func = new F_detalleVenta();
        V_detalleVenta dts = new V_detalleVenta();
        F_agendamiento funcMateriales = new F_agendamiento(); // Instancia para gestionar materiales
        int fila = 0;
        fila = jTableDetalle.getRowCount();
        for (int f = 0; f < fila; f++) {
            dts.setIdfactura(txtIdFacturaCompra.getText());
            dts.setIdservicio(jTableDetalle.getModel().getValueAt(f, 0).toString());
            dts.setPre_servicio(Double.parseDouble(jTableDetalle.getModel().getValueAt(f, 2).toString()));
            dts.setTotal_detalle(jTableDetalle.getModel().getValueAt(f, 4).toString());
            func.insertar(dts);

//            try {
//                // Actualizar el stock llamando al método de la clase fmateriales
//                int idProducto = Integer.parseInt(jTableDetalle.getModel().getValueAt(f, 0).toString());
//                double cantidadVendida = Double.parseDouble(jTableDetalle.getModel().getValueAt(f, 3).toString());
//
//                if (!funcMateriales.actualizarStock(idProducto, cantidadVendida)) {
//                    JOptionPane.showMessageDialog(this, "Error al actualizar el stock del producto", "Error", ERROR);
//                }
//            } catch (Exception e) {
//                JOptionPane.showMessageDialog(null, e.getMessage());
//            }
        }
        JOptionPane.showMessageDialog(null, "FACTURACION CORRECTA");

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
        btnMostrarEmpleado = new javax.swing.JButton();
        txtIdFacturaCompra = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtIdEmpleado = new javax.swing.JTextField();
        txtEmpleado = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtPrecioCompra = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        btnagregar = new javax.swing.JButton();
        txtFecha = new com.toedter.calendar.JDateChooser();
        jLabel11 = new javax.swing.JLabel();
        txtSubtotal = new javax.swing.JTextField();
        txtidCliente = new javax.swing.JTextField();
        txtcliente = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        btnBuscarCliente = new javax.swing.JButton();
        txtIdAgendamiento = new javax.swing.JTextField();
        btnAgendamiento = new javax.swing.JButton();
        txtIdServicio = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtServicio = new javax.swing.JTextField();
        btnBuscarServicio = new javax.swing.JButton();
        btnimprimir = new javax.swing.JButton();
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
        jLabel19 = new javax.swing.JLabel();
        txtTotalIva = new javax.swing.JTextField();
        ComboIva = new javax.swing.JComboBox<>();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconifiable(true);
        setTitle("Factura Venta");

        jPanel4.setBackground(new java.awt.Color(204, 255, 204));
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
                    .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnCancelar)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnGuardar)))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(btnNuevo)
                .addGap(43, 43, 43)
                .addComponent(btnGuardar)
                .addGap(38, 38, 38)
                .addComponent(btnCancelar)
                .addGap(35, 35, 35)
                .addComponent(btnSalir)
                .addContainerGap(358, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(0, 255, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Factura  de Venta", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

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

        jLabel6.setBackground(new java.awt.Color(0, 0, 0));
        jLabel6.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jLabel6.setText("Empleado");

        txtPrecioCompra.setEditable(false);
        txtPrecioCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrecioCompraActionPerformed(evt);
            }
        });

        jLabel8.setBackground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Precio compra:");

        txtCantidad.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCantidadFocusLost(evt);
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

        jLabel11.setBackground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Sub-Total:");

        txtSubtotal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSubtotalKeyTyped(evt);
            }
        });

        txtcliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtclienteActionPerformed(evt);
            }
        });

        jLabel9.setBackground(new java.awt.Color(0, 0, 0));
        jLabel9.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabel9.setText("Datos del cliente");

        btnBuscarCliente.setText("...");
        btnBuscarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarClienteActionPerformed(evt);
            }
        });

        txtIdAgendamiento.setEditable(false);
        txtIdAgendamiento.setForeground(new java.awt.Color(0, 255, 255));
        txtIdAgendamiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdAgendamientoActionPerformed(evt);
            }
        });

        btnAgendamiento.setText("agendamiento");
        btnAgendamiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgendamientoActionPerformed(evt);
            }
        });

        txtIdServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdServicioActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jLabel1.setText("Servicio");

        txtServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtServicioActionPerformed(evt);
            }
        });

        btnBuscarServicio.setText("...");
        btnBuscarServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarServicioActionPerformed(evt);
            }
        });

        btnimprimir.setText("imprimir");
        btnimprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnimprimirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(373, 373, 373)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtPrecioCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(75, 75, 75)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(173, 173, 173)
                        .addComponent(btnagregar, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 6, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(63, 63, 63)
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(txtIdEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnMostrarEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtIdAgendamiento, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnAgendamiento)
                                        .addGap(147, 147, 147)))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(txtIdFacturaCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(txtidCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel9)
                                            .addComponent(txtcliente, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jLabel8)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(txtIdServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(txtServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnBuscarServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(154, 154, 154)
                                                .addComponent(btnBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 386, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtFecha, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                                    .addComponent(jLabel3)
                                    .addComponent(btnimprimir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addContainerGap(135, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtIdFacturaCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel6)
                        .addGap(11, 11, 11)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtIdAgendamiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnAgendamiento))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtIdEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnMostrarEmpleado)))))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnimprimir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnagregar, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtIdServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBuscarServicio))
                        .addGap(25, 25, 25)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtcliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtidCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBuscarCliente))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel11))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel8)
                                .addGap(16, 16, 16)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPrecioCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        jPanel1.setBackground(new java.awt.Color(51, 255, 204));
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

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 910, 210));

        jPanel3.setBackground(new java.awt.Color(153, 255, 204));
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

        jLabel19.setText("total Iva");

        txtTotalIva.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtTotalIva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalIvaActionPerformed(evt);
            }
        });

        ComboIva.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "5", "10" }));
        ComboIva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboIvaActionPerformed(evt);
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
                .addGap(55, 55, 55)
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
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(ComboIva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTotalIva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(txtTotalPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 948, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                F_FacturaVenta func = new F_FacturaVenta();
                V_facturaVenta dts = new V_facturaVenta();

                F_servicios func1 = new F_servicios();
                dts.setIdfactura(txtIdFacturaCompra.getText());
                Long mfecha = txtFecha.getDate().getTime();
                java.sql.Date fecha = new java.sql.Date(mfecha);
                dts.setFecha(String.valueOf(fecha));

                dts.setIdempleado(txtIdEmpleado.getText());
                dts.setIdclientenro(txtidCliente.getText());
                dts.setIdage(txtIdAgendamiento.getText());

//                dts.setIdage(txtIdAgendamiento.getText());
                dts.setTotal_pago(Double.parseDouble(txtTotalPago.getText()));
                dts.setTotal_item(txtTotalItems.getText());
                dts.setTotal_ser(txtTotalArticulos.getText());

                try {
                    func1.cambiarEstadoPedido(Integer.parseInt(txtIdAgendamiento.getText()), "Pagado");
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
        frmMostrarempleado form = new frmMostrarempleado();
        form.setVisible(true);
        form.toFront();
//        String id = txtIdEmpleado.getText(); // Obtiene el ID del JTextField
//        Fempleados func = new Fempleados();
//        func.buscarDatosPorID(id);
    }//GEN-LAST:event_btnMostrarEmpleadoActionPerformed

    private void txtCantidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyReleased
//           CalcularSubTotal();
        int KeyCode = evt.getKeyCode();
        if (KeyCode == KeyEvent.VK_ENTER) {
            btnagregar.doClick();
        }
    }//GEN-LAST:event_txtCantidadKeyReleased

    private void btnagregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnagregarActionPerformed
        agregarProducto();
        actualizarTotales();
        calcularIVA();
    }//GEN-LAST:event_btnagregarActionPerformed

    private void btnagregarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnagregarKeyReleased
//                CalcularSubTotal();
        int KeyCode = evt.getKeyCode();
        if (KeyCode == KeyEvent.VK_ENTER) {
            btnagregar.requestFocus();

        }

    }//GEN-LAST:event_btnagregarKeyReleased

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

    private void txtPrecioCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecioCompraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioCompraActionPerformed

    private void txtclienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtclienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtclienteActionPerformed

    private void btnBuscarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarClienteActionPerformed
//        frmMostrarcliente form = new frmMostrarcliente();
//        form.setVisible(true);
//        form.toFront();
        String id = txtidCliente.getText();
        Fclientes func = new Fclientes();
        func.buscarDatosPorID(id);
    }//GEN-LAST:event_btnBuscarClienteActionPerformed

    private void txtTotalIvaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalIvaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalIvaActionPerformed

    private void ComboIvaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboIvaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboIvaActionPerformed

    private void txtIdAgendamientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdAgendamientoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdAgendamientoActionPerformed

    private void btnAgendamientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgendamientoActionPerformed
        // TODO add your handling code here:
        frmMostrarage form = new frmMostrarage();
        form.setVisible(true);
        form.toFront();
    }//GEN-LAST:event_btnAgendamientoActionPerformed

    private void txtIdServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdServicioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdServicioActionPerformed

    private void txtServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtServicioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtServicioActionPerformed

    private void btnBuscarServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarServicioActionPerformed
//        farm_mostrarServicios form = new farm_mostrarServicios();
//        form.setVisible(true);
//        form.toFront();
        String id = txtIdServicio.getText();
        F_servicios func = new F_servicios();
        func.buscarDatosPorID(id);


    }//GEN-LAST:event_btnBuscarServicioActionPerformed

    private void txtIdFacturaCompraKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdFacturaCompraKeyTyped
        // TODO add your handling code here:
        char key = evt.getKeyChar(); // Obtiene el carácter de la tecla presionada

        if (!Character.isDigit(key)) { // Verifica si el carácter no es un dígito
            evt.consume(); // Ignora el carácter si no es un número
        }
    }//GEN-LAST:event_txtIdFacturaCompraKeyTyped

    private void txtSubtotalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSubtotalKeyTyped
        // TODO add your handling code here:
        char key = evt.getKeyChar(); // Obtiene el carácter de la tecla presionada

        if (!Character.isDigit(key)) { // Verifica si el carácter no es un dígito
            evt.consume(); // Ignora el carácter si no es un número
        }
    }//GEN-LAST:event_txtSubtotalKeyTyped

    private void txtCantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyTyped
        // TODO add your handling code here:
        char key = evt.getKeyChar(); // Obtiene el carácter de la tecla presionada

        if (!Character.isDigit(key)|| key == '0') { // Verifica si el carácter no es un dígito
            evt.consume(); // Ignora el carácter si no es un número
        }
    }//GEN-LAST:event_txtCantidadKeyTyped

    private void btnimprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnimprimirActionPerformed
        //        // TODO add your handling code here:
        //        Map<String, Object> parameters = new HashMap<>();
        //        parameters.put("ID", "10");// parametros que necesita el SQL en reports
        //        //ruta del archivo.jasper + los paramtros del mismo
        //        //            conexionReport.iniciarReport("C:\Users\Elias Carranza\JaspersoftWorkspace\MyReports\Leaf_Violet.jasper", parameters);
        //        conexionReport.iniciarReport("C:\\Users\\admin\\JaspersoftWorkspace\\MyReports\\prueba.jasper", parameters);
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
            java.util.logging.Logger.getLogger(frm_facturaVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_facturaVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_facturaVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_facturaVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frm_facturaVenta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboIva;
    private javax.swing.JButton btnAgendamiento;
    private javax.swing.JButton btnBuscarCliente;
    private javax.swing.JButton btnBuscarServicio;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnMostrarEmpleado;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnagregar;
    private javax.swing.JButton btnimprimir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
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
    public static javax.swing.JTextField txtIdAgendamiento;
    public static javax.swing.JTextField txtIdEmpleado;
    private javax.swing.JTextField txtIdFacturaCompra;
    public static javax.swing.JTextField txtIdServicio;
    public static javax.swing.JTextField txtPrecioCompra;
    public static javax.swing.JTextField txtServicio;
    public static javax.swing.JTextField txtSubtotal;
    private javax.swing.JTextField txtTotalArticulos;
    private javax.swing.JTextField txtTotalItems;
    private javax.swing.JTextField txtTotalIva;
    private javax.swing.JTextField txtTotalPago;
    public static javax.swing.JTextField txtcliente;
    public static javax.swing.JTextField txtidCliente;
    // End of variables declaration//GEN-END:variables
}
