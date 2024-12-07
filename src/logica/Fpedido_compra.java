/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import datos.VfacturaCompra;
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
public class Fpedido_compra {

    private conexion mysql = new conexion();
    private Connection cn = mysql.conectar();
    private String sSQL = "";
    private String sSQL2 = "";
    public Integer totalregistros;
    public Double totaldetalle;
    public String cadena;

    public DefaultTableModel mostrar(String buscar) {

        DefaultTableModel modelo;
        String[] titulos = {"Codigo", "fecha", "CodEmp", "empleado",
            "idproveedor", "nombre", "idinsumos", "descripcion", "cantidad",
            "total_items"," total_articulos", "estado"};
        String[] registro = new String[12];
        totalregistros = 0;
        modelo = new DefaultTableModel(null, titulos);
        // atender siempre las variables 
        sSQL = "select i.idpedidos_compra , i.fecha_emision, i.idempleados, e.nombre as empleados,i.idproveedor,\n"
                + "p.nombre , pd.idinsumos, o.descripcion, pd.cantidad, i.total_items, i.total_articulos, i.estado \n"
                + "from pedidos_compra i\n"
                + "inner join empleados e on i.idempleados = e.idempleado\n"
                + "inner join proveedor p on i.idproveedor = p.idproveedor\n"
                + "inner join detalle_pedido_compra pd on i.idpedidos_compra = pd.idpedidos_compra\n"
                + "inner join insumos o on pd.idinsumos = o.idinsumos\n"
                + "where (i.idpedidos_compra like '%"+buscar+"%' or i.estado like '%"+buscar+"%') and i.estado='pendiente' \n"
                + "order by convert (i.idpedidos_compra, unsigned);";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while (rs.next()) {
                //DEBE SER IGUAL AL DE LA BASE DE DATOS
                registro[0] = rs.getString("idpedidos_compra");
                registro[1] = rs.getString("fecha_emision");
                registro[2] = rs.getString("idempleados");
                registro[3] = rs.getString("empleados");
                registro[4] = rs.getString("idproveedor");
                registro[5] = rs.getString("nombre");
                registro[6] = rs.getString("idinsumos");
                registro[7] = rs.getString("descripcion");
                registro[8] = rs.getString("cantidad");
                registro[9] = rs.getString("total_items");
                registro[10] = rs.getString("total_articulos");
                registro[11] = rs.getString("estado");

                totalregistros = totalregistros + 1;
                modelo.addRow(registro);
            }
            return modelo;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

    public boolean insertar(VpedidosCompra dts) {
        sSQL = "INSERT INTO pedidos_compra(idpedidos_compra,idproveedor,idempleados,\n"
                + "total_items, total_articulos,fecha_emision,estado)VALUES(?,?,?,?,?,?,?)";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setString(1, dts.getIdpedidocompra());
            pst.setString(2, dts.getIdproveedor());
            pst.setString(3, dts.getIdempleado());
            pst.setString(4, dts.getTotal_item());
            pst.setString(5, dts.getTotal_articulo());
            pst.setString(6, dts.getFecha());
            pst.setString(7, "Pendiente");

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
        String sql = "SELECT COUNT(*) FROM pedidos_compra WHERE idpedidos_compra = ?";
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
        String sql = "UPDATE pedidos_compra SET estado = ? WHERE idpedidos_compra = ?";

        try ( PreparedStatement pst = cn.prepareStatement(sql)) {
            pst.setString(1, nuevoEstado);
            pst.setInt(2, idPedido_compra);
            pst.executeUpdate();
        }
    }

}
