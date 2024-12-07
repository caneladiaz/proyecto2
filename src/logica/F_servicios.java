/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import presentacion.frm_facturaVenta;

public class F_servicios {
    
     //se crea un objeto msql de tipo coneccion (Clase conexion)
    private conexion msql = new conexion();
    //se crea una variable cn en la cual se va a guardar los valores devuletos por el metodo conectar de la clase conexion.
    private Connection cn = msql.conectar();

    private String sql = "";
    private String sSQL = "";
    private String sSQL2 = "";
    public int totalregistro;

    public DefaultTableModel mostrar(String buscar) {

        DefaultTableModel modelo;
        String[] titulos = {"Codigo", "descripcion", "total_servicio", "estado"};
        String[] registro = new String[4];
        totalregistro = 0;
        modelo = new DefaultTableModel(null, titulos);

        sql = "Select * from servicios where descripcion like '%" + buscar + "%'order by convert (idservicio,unsigned);";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                registro[0] = rs.getString("idservicio");
                registro[1] = rs.getString("descripcion");
                registro[2] = rs.getString("precio_servicio");
                registro[3] = rs.getString("estado");
                totalregistro = totalregistro + 1;
                modelo.addRow(registro);
            }
            return modelo;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

     // Método para cambiar el estado de un pedido
    public void cambiarEstadoPedido(int idservicio, String nuevoEstado) throws SQLException {
        String sql = "UPDATE agendamiento SET estado = ? WHERE idage = ?";

        try ( PreparedStatement pst = cn.prepareStatement(sql)) {
            pst.setString(1, nuevoEstado);
            pst.setInt(2, idservicio);
            pst.executeUpdate();
        }
    }
    // Método para buscar datos por ID
    public void buscarDatosPorID(String id) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Consulta SQL para obtener los datos por ID
            String query = "SELECT descripcion, precio_servicio FROM servicios WHERE idservicio = ?";
            stmt = cn.prepareStatement(query);
            stmt.setString(1, id); // Pasa el ID como parámetro

            // Ejecuta la consulta
            rs = stmt.executeQuery();

            // Verifica si se encontraron datos
            if (rs.next()) {
                // Obtiene los valores de la consulta
                String nombre = rs.getString("descripcion");
                String precio_compra = rs.getString("precio_servicio");
//                String telefono = rs.getString("telefono");

                // Muestra los datos en los campos de texto
                frm_facturaVenta.txtServicio.setText(nombre);
                frm_facturaVenta.txtPrecioCompra.setText(precio_compra);
//                FrmFacturaCompra.txtTeléfonoProv.setText(telefono);
            } else {
                JOptionPane.showMessageDialog(null, "ID no encontrado");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al consultar la base de datos: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Cierra la conexión y recursos
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
