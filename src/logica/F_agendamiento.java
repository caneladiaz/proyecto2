/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import datos.Vagendamiento;
import datos.VpedidosCompra;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author admin
 */
public class F_agendamiento {

    private conexion mysql = new conexion();
    private Connection cn = mysql.conectar();
    private String sSQL = "";
    private String sSQL2 = "";
    public Integer totalregistros;
    public Double totaldetalle;
    public String cadena;

    public DefaultTableModel mostrar(String buscar) {

        DefaultTableModel modelo;
        String[] titulos = {"Codigo", "fecha", "codCliente", "CodEmp", "Nombre_Empleado",
            "Nombre_Cliente", "idservicio", "Descripcion_Servicio", "estado"};
        String[] registro = new String[9];
        totalregistros = 0;
        modelo = new DefaultTableModel(null, titulos);
        // atender siempre las variables 
        sSQL = "SELECT a.idage, a.fecha, a.idclientenro, a.idempleado, e.nombre AS Nombre_Empleado, \n"
                + "p.nombre AS Nombre_Cliente, pd.idservicio, o.descripcion AS Descripcion_Servicio, a.estado\n"
                + "FROM agendamiento a\n"
                + "INNER JOIN empleados e ON a.idempleado = e.idempleado\n"
                + "INNER JOIN clientes p ON a.idclientenro = p.idclientenro\n"
                + "INNER JOIN detalle_agendamiento pd ON a.idage = pd.idage\n"
                + "INNER JOIN servicios o ON pd.idservicio = o.idservicio\n"
                + "WHERE (a.idage LIKE '%"+buscar+"%' OR a.estado LIKE '%"+buscar+"%') \n"
                + "ORDER BY CONVERT(a.idage, UNSIGNED);";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while (rs.next()) {
                //DEBE SER IGUAL AL DE LA BASE DE DATOS
                registro[0] = rs.getString("idage");
                registro[1] = rs.getString("fecha");
                registro[2] = rs.getString("idclientenro");
                registro[3] = rs.getString("idempleado");
                registro[4] = rs.getString("Nombre_Empleado");
                registro[5] = rs.getString("Nombre_Cliente");
                registro[6] = rs.getString("idservicio");
                registro[7] = rs.getString("Descripcion_Servicio");
                registro[8] = rs.getString("estado");

                totalregistros = totalregistros + 1;
                modelo.addRow(registro);
            }
            return modelo;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

    public DefaultTableModel mostrarPendiente(String buscar) {

        DefaultTableModel modelo;
        String[] titulos = {"Codigo", "fecha", "codCliente", "CodEmp", "Nombre_Empleado",
            "Nombre_Cliente", "idservicio", "Descripcion_Servicio", "estado"};
        String[] registro = new String[9];
        totalregistros = 0;
        modelo = new DefaultTableModel(null, titulos);
        // atender siempre las variables 
        sSQL = "SELECT a.idage, a.fecha, a.idclientenro, a.idempleado, e.nombre AS Nombre_Empleado, \n"
                + "p.nombre AS Nombre_Cliente, pd.idservicio, o.descripcion AS Descripcion_Servicio, a.estado\n"
                + "FROM agendamiento a\n"
                + "INNER JOIN empleados e ON a.idempleado = e.idempleado\n"
                + "INNER JOIN clientes p ON a.idclientenro = p.idclientenro\n"
                + "INNER JOIN detalle_agendamiento pd ON a.idage = pd.idage\n"
                + "INNER JOIN servicios o ON pd.idservicio = o.idservicio\n"
                + "WHERE (a.idage LIKE '%"+buscar+"%' OR a.estado LIKE '%"+buscar+"%' and a.estado='pendiente') \n"
                + "ORDER BY CONVERT(a.idage, UNSIGNED);";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while (rs.next()) {
                //DEBE SER IGUAL AL DE LA BASE DE DATOS
                registro[0] = rs.getString("idage");
                registro[1] = rs.getString("fecha");
                registro[2] = rs.getString("idclientenro");
                registro[3] = rs.getString("idempleado");
                registro[4] = rs.getString("Nombre_Empleado");
                registro[5] = rs.getString("Nombre_Cliente");
                registro[6] = rs.getString("idservicio");
                registro[7] = rs.getString("Descripcion_Servicio");
                registro[8] = rs.getString("estado");

                totalregistros = totalregistros + 1;
                modelo.addRow(registro);
            }
            return modelo;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    
    
    public boolean insertar(Vagendamiento dts) {
        sSQL = "INSERT INTO agendamiento(idage,idclientenro,\n"
                + "idempleado,fecha,estado)VALUES(?,?,?,?,?)";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setString(1, dts.getIdage());
            pst.setString(2, dts.getIdclientenro());
            pst.setString(3, dts.getIdempleado());
            pst.setString(4, dts.getFecha());
            pst.setString(5, "activo");

            int n = pst.executeUpdate();
            if (n != 0) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }

    }

    public boolean existeIdFactura(String idFactura) {
        String sql = "SELECT COUNT(*) FROM agendamiento WHERE idage = ?";
        try {
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, idFactura); // Establecer el parámetro idFactura en la consulta

            ResultSet rs = pst.executeQuery(); // Ejecutar la consulta

            if (rs.next()) { // Si hay resultado
                int count = rs.getInt(1); // Obtener el número de filas encontradas
                return count > 0; // Devuelve true si existe al menos una fila con el idFactura
            } else {
                return false; // No se encontró ninguna fila
            }

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }

    // Método para cambiar el estado de un pedido
    public void cambiarEstadoPedido(int idPedido_compra, String nuevoEstado) throws SQLException {
        String sql = "UPDATE agendamiento SET estado = ? WHERE idage = ?";

        try ( PreparedStatement pst = cn.prepareStatement(sql)) {
            pst.setString(1, nuevoEstado);
            pst.setInt(2, idPedido_compra);
            pst.executeUpdate();
        }
    }
}
