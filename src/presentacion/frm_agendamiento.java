package presentacion;

import datos.Vagendamiento;
import datos.VdetalleAgendamiento;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import logica.F_agendamiento;
import logica.F_detalleAgendamiento;
import logica.F_servicios;
import logica.conexionReport;

/**
 *
 * @author admin
 */
public class frm_agendamiento extends javax.swing.JInternalFrame {

    /**
     * Creates new form frm_agendamiento
     */
    public frm_agendamiento() {
        initComponents();
        setResizable(false);
        setTitle("AGENDAMIENTO DE CITAS");
        //setLocationRelativeTo(null);
        txtFecha.setDate(new Date());
        // Inicializar el modelo de la tabla
        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Descripción");
        modeloTabla.addColumn("Cantidad");
        jTableDetalle.setModel(modeloTabla);
        txtIdAgendamieno.requestFocusInWindow();
        inhabilitar();
    }

    private String accion = "guardar";

    //Para después de cancelar alguna acción, deshabilita y vacia txt
    void inhabilitar() {
        txtIdServicio.setEnabled(false);
        txtServicio.setEnabled(false);
        txtidCliente.setEnabled(false);
        txtapellido_cliente.setEnabled(false);
        txtcliente.setEnabled(false);
        txtidempleado.setEnabled(false);
        txtEmpleado.setEnabled(false);
        txtCantidad.setEnabled(false);
        txtFecha.setEnabled(false);
        txtIdAgendamieno.setEnabled(false);
        jTableDetalle.setEnabled(true);

        btnBuscarServicio.setEnabled(false);
        btnBuscarCliente.setEnabled(false);
        btnMostrarEmpleado.setEnabled(false);
        btnCancelar.setEnabled(false);
        btnGuardar.setEnabled(false);
        btnNuevo.setEnabled(true);
        btnSalir.setEnabled(true);
        btnagregar.setEnabled(false);
    }

    void habilitar() {
        txtIdAgendamieno.setEnabled(true);
        txtFecha.setEnabled(true);
        txtCantidad.setEnabled(true);

        jTableDetalle.setEnabled(true);

        btnBuscarServicio.setEnabled(true);
        btnBuscarCliente.setEnabled(true);
        btnMostrarEmpleado.setEnabled(true);
        btnCancelar.setEnabled(true);
        btnGuardar.setEnabled(true);
        btnNuevo.setEnabled(true);
        btnSalir.setEnabled(false);
        btnagregar.setEnabled(true);

        txtServicio.setText("");
        txtEmpleado.setText("");

        txtIdServicio.setText("");
        txtEmpleado.setText("");

    }

    void limpiar() {

        txtIdServicio.setText("");
        txtServicio.setText("");
        txtidCliente.setText("");
        txtapellido_cliente.setText("");
        txtcliente.setText("");
        txtidempleado.setText("");
        txtEmpleado.setText("");

        txtIdAgendamieno.setText("");

//        txtSubtotal.setText("");
////        txtTotalPago.setText("");
        limpiarTabla();
        txtIdAgendamieno.requestFocus();
    }

    //este méodo valida el id
    public void validarid() {
        F_agendamiento func = new F_agendamiento();
        Vagendamiento dts = new Vagendamiento();
        String idage = txtIdAgendamieno.getText(); // Obtener el ID de factura del JTextField
        if (!func.existeIdFactura(idage)) {
            // Lógica para insertar la factura ya que no existe            
        } else {
            JOptionPane.showMessageDialog(null, "El ID de la factura ya existe en la base de datos.");
            txtIdAgendamieno.setText("");
            txtIdAgendamieno.requestFocus();
        }
    }

    public boolean validar() {
        int filas = jTableDetalle.getRowCount();
        if (!txtIdAgendamieno.getText().equals("")) {
            if (txtidempleado.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Ingrese el dato");
                return false;

            } else if (filas == 0) {
                JOptionPane.showMessageDialog(null, "Agregue al menos un servicio");
                btnBuscarServicio.requestFocus();
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
//    DecimalFormat df = new DecimalFormat("###");

    private void agregarProducto() {
        // Obtener los datos de los campos de texto
        String id = txtIdServicio.getText();
        String descripcion = txtServicio.getText();
        String cantidadStr = txtCantidad.getText();
//        String cantidadP=txtcantidad_unidad.getText();
//        String presentacion=txtunidadM.getText();

        // Validar que los campos no estén vacíos
        if (id.isEmpty() || descripcion.isEmpty() || cantidadStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, rellena todos los campos.");
            return;
        }
        try {
            // Convertir el precio y la cantidad a los tipos de datos correctos
//            double precio = Double.parseDouble(precioStr);
            int cantidad = Integer.parseInt(cantidadStr);
//            int cantidadp = Integer.parseInt(cantidadP);
//            double subtotal = precio * cantidad; // Calcular subtotal

            // Verificar si el producto ya existe en la tabla por su ID
            boolean productoExistente = false;
            for (int i = 0; i < modeloTabla.getRowCount(); i++) {
                String idExistente = (String) modeloTabla.getValueAt(i, 0); // ID está en la columna 0
                if (idExistente.equals(id)) { // Producto ya existe
                    // Obtener la cantidad actual y sumarle la nueva cantidad
                    int cantidadActual = (int) modeloTabla.getValueAt(i, 2); // Cantidad está en la columna 3
                    int nuevaCantidad = cantidadActual + cantidad;

                    // Calcular el nuevo subtotal
//                    Double nuevoSubtotal = (nuevaCantidad * precio);
                    // Actualizar la cantidad y el subtotal en la tabla
                    modeloTabla.setValueAt(nuevaCantidad, i, 2); // Actualizar la cantidad
//                    modeloTabla.setValueAt(df.format(nuevoSubtotal), i, 4); // Actualizar el subtotal

                    // Actualizar la cantidad
//                    totalArticulos += cantidad;
                    productoExistente = true; // Marcar que el producto ya existe
                    break; // No es necesario seguir buscando
                }
            }
            // Si el producto no existe en la tabla, agregarlo como nueva fila
            if (!productoExistente) {
                modeloTabla.addRow(new Object[]{id, descripcion, cantidad});
                // Actualizar los contadores
//                totalItems++;
//                totalArticulos += cantidad;
//                totalPago += subtotal;
            }
            // Limpiar los campos de texto después de agregar
            txtIdServicio.setText("");
            txtServicio.setText("");
            txtCantidad.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El precio debe ser un número decimal y la cantidad un número entero.");
        }
    }

    void guardarDetalle() {
        F_detalleAgendamiento func = new F_detalleAgendamiento();
        VdetalleAgendamiento dts = new VdetalleAgendamiento();

        F_servicios funcMateriales = new F_servicios(); // Instancia para gestionar materiales
        int fila = 0;
        fila = jTableDetalle.getRowCount();
        for (int f = 0; f < fila; f++) {
            dts.setIdage(txtIdAgendamieno.getText());
            dts.setIdservicio(jTableDetalle.getModel().getValueAt(f, 0).toString());
            dts.setCantidad_servicios(Double.parseDouble(jTableDetalle.getModel().getValueAt(f, 2).toString()));
//            dts.setPrecio_compra(Double.parseDouble(jTableDetalle.getModel().getValueAt(f, 2).toString()));
//            dts.setTotal_detalle(jTableDetalle.getModel().getValueAt(f, 4).toString());
            func.insertar(dts);
        }
        JOptionPane.showMessageDialog(null, "SE HA GUARDADO CON EXITO");

    }

    void limpiarTabla() {
        try {
            modeloTabla.setRowCount(0); // Limpia todas las filas del modelo de tabla
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al limpiar la tabla: " + e.getMessage());
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

        jPanel4 = new javax.swing.JPanel();
        btnNuevo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        txtServicio = new javax.swing.JTextField();
        txtIdServicio = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btnMostrarEmpleado = new javax.swing.JButton();
        txtIdAgendamieno = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtidempleado = new javax.swing.JTextField();
        txtEmpleado = new javax.swing.JTextField();
        btnBuscarServicio = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtidCliente = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtcliente = new javax.swing.JTextField();
        txtapellido_cliente = new javax.swing.JTextField();
        btnagregar = new javax.swing.JButton();
        txtFecha = new com.toedter.calendar.JDateChooser();
        btnBuscarCliente = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        btnimprimir1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableDetalle = new javax.swing.JTable();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconifiable(true);
        setTitle("Agendamiento de Citas");

        jPanel4.setBackground(new java.awt.Color(255, 204, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("ACCIONES:"));
        jPanel4.setForeground(new java.awt.Color(204, 102, 255));

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
                    .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnGuardar)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(btnNuevo)
                .addGap(31, 31, 31)
                .addComponent(btnGuardar)
                .addGap(33, 33, 33)
                .addComponent(btnCancelar)
                .addGap(18, 18, 18)
                .addComponent(btnSalir)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 204, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Agendamiento de Citas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 18))); // NOI18N

        txtServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtServicioActionPerformed(evt);
            }
        });

        txtIdServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdServicioActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("servicio");

        btnMostrarEmpleado.setText("...");
        btnMostrarEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarEmpleadoActionPerformed(evt);
            }
        });

        txtIdAgendamieno.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtIdAgendamienoFocusLost(evt);
            }
        });
        txtIdAgendamieno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdAgendamienoActionPerformed(evt);
            }
        });
        txtIdAgendamieno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIdAgendamienoKeyTyped(evt);
            }
        });

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Fecha emisión:");

        jLabel4.setBackground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Nro. Agendamiento");

        btnBuscarServicio.setText("...");
        btnBuscarServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarServicioActionPerformed(evt);
            }
        });

        jLabel6.setBackground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Empleado");

        jLabel7.setBackground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Datos del cliente");

        txtcliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtclienteActionPerformed(evt);
            }
        });

        txtapellido_cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtapellido_clienteActionPerformed(evt);
            }
        });

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

        btnBuscarCliente.setText("...");
        btnBuscarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarClienteActionPerformed(evt);
            }
        });

        jLabel10.setBackground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Cantidad:");

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

        btnimprimir1.setText("imprimir");
        btnimprimir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnimprimir1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(116, 116, 116)
                                .addComponent(jLabel7))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtidCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtcliente, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtapellido_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(60, 60, 60)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(310, 310, 310)
                        .addComponent(btnagregar, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnimprimir1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(txtidempleado, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel6)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(txtEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnMostrarEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3)
                                            .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(56, 56, 56))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(txtIdServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel1))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnBuscarServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(392, 392, 392)))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4)
                                    .addComponent(txtIdAgendamieno, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(152, 152, 152)))
                        .addGap(393, 393, 393))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtIdAgendamieno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(144, 144, 144)
                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtidempleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnMostrarEmpleado))
                        .addGap(47, 47, 47)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtIdServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(btnBuscarServicio))
                                .addGap(29, 29, 29)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnimprimir1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtcliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtidCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtapellido_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnagregar, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBuscarCliente))))
                .addGap(18, 18, 18))
        );

        jPanel1.setBackground(new java.awt.Color(255, 204, 255));
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

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 910, 220));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 948, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 948, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed

        accion = "guardar";
        habilitar();
        txtIdAgendamieno.requestFocusInWindow();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (validar() == true) { //se valida que todos los campos estén ingresados
            if (accion.equals("guardar")) {
                F_agendamiento func = new F_agendamiento();
                Vagendamiento dts = new Vagendamiento();

                dts.setIdage(txtIdAgendamieno.getText());
                Long mfecha = txtFecha.getDate().getTime();
                java.sql.Date fecha = new java.sql.Date(mfecha);
                dts.setFecha(String.valueOf(fecha));

                dts.setIdempleado(txtidempleado.getText());
                dts.setIdservicio(txtIdServicio.getText());
                dts.setIdclientenro(txtidCliente.getText());

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

    private void txtServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtServicioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtServicioActionPerformed

    private void txtIdServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdServicioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdServicioActionPerformed

    private void btnMostrarEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarEmpleadoActionPerformed
        frmMostrarEMPLEADOElias form = new frmMostrarEMPLEADOElias();
        form.setVisible(true);
        form.toFront();
    }//GEN-LAST:event_btnMostrarEmpleadoActionPerformed

    private void txtIdAgendamienoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtIdAgendamienoFocusLost
        if (txtIdAgendamieno.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese el codigo");
            txtIdAgendamieno.requestFocus();
            txtIdAgendamieno.setBackground(Color.GREEN);
        } else {
            validarid();
        }
    }//GEN-LAST:event_txtIdAgendamienoFocusLost

    private void txtIdAgendamienoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdAgendamienoActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtIdAgendamienoActionPerformed

    private void btnBuscarServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarServicioActionPerformed
        farm_mostrarServicios form = new farm_mostrarServicios();
        form.setVisible(true);
        form.toFront();
    }//GEN-LAST:event_btnBuscarServicioActionPerformed

    private void txtclienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtclienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtclienteActionPerformed

    private void btnagregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnagregarActionPerformed
        agregarProducto();
    }//GEN-LAST:event_btnagregarActionPerformed

    private void btnagregarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnagregarKeyReleased
        int KeyCode = evt.getKeyCode();
        if (KeyCode == KeyEvent.VK_ENTER) {
            btnagregar.requestFocus();
        }

    }//GEN-LAST:event_btnagregarKeyReleased

    private void btnBuscarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarClienteActionPerformed
        frmMostrarcliente form = new frmMostrarcliente();
        form.setVisible(true);
        form.toFront();
    }//GEN-LAST:event_btnBuscarClienteActionPerformed

    private void txtapellido_clienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtapellido_clienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtapellido_clienteActionPerformed

    private void txtCantidadFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCantidadFocusLost
        //calcularSubtotal();
    }//GEN-LAST:event_txtCantidadFocusLost

    private void txtCantidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyReleased
        //CalcularSubTotal();
        int KeyCode = evt.getKeyCode();
        if (KeyCode == KeyEvent.VK_ENTER) {
            btnagregar.doClick();
        }
    }//GEN-LAST:event_txtCantidadKeyReleased

    private void txtCantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyTyped
        // TODO add your handling code here
        char key = evt.getKeyChar(); // Obtiene el carácter de la tecla presionada

        if (!Character.isDigit(key)|| key == '0') { // Verifica si el carácter no es un dígito
            evt.consume(); // Ignora el carácter si no es un número
        }
    }//GEN-LAST:event_txtCantidadKeyTyped

    private void txtIdAgendamienoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdAgendamienoKeyTyped
        // TODO add your handling code here:
        char key = evt.getKeyChar(); // Obtiene el carácter de la tecla presionada

        if (!Character.isDigit(key)) { // Verifica si el carácter no es un dígito
            evt.consume(); // Ignora el carácter si no es un número
        }
    }//GEN-LAST:event_txtIdAgendamienoKeyTyped

    private void btnimprimir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnimprimir1ActionPerformed
         Map<String, Object> parameters = new HashMap<>();

        conexionReport.iniciarReport("C:\\Users\\admin\\JaspersoftWorkspace"
                + "\\MyReports\\reporte_agenda.jasper", parameters);
    }//GEN-LAST:event_btnimprimir1ActionPerformed

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
            java.util.logging.Logger.getLogger(frm_agendamiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_agendamiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_agendamiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_agendamiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frm_agendamiento().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarCliente;
    private javax.swing.JButton btnBuscarServicio;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnMostrarEmpleado;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnagregar;
    private javax.swing.JButton btnimprimir1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableDetalle;
    public static javax.swing.JTextField txtCantidad;
    public static javax.swing.JTextField txtEmpleado;
    private com.toedter.calendar.JDateChooser txtFecha;
    private javax.swing.JTextField txtIdAgendamieno;
    public static javax.swing.JTextField txtIdServicio;
    public static javax.swing.JTextField txtServicio;
    public static javax.swing.JTextField txtapellido_cliente;
    public static javax.swing.JTextField txtcliente;
    public static javax.swing.JTextField txtidCliente;
    public static javax.swing.JTextField txtidempleado;
    // End of variables declaration//GEN-END:variables
}
