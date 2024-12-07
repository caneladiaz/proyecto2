/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import datos.V_facturaVenta;
import datos.VfacturaCompra;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author admin
 */
public class F_FacturaVenta {

    private conexion mysql = new conexion();
    private Connection cn = mysql.conectar();
    private String sSQL = "";
    private String sSQL2 = "";
    public Integer totalregistros;
    public Double totaldetalle;
    public String cadena;

    public String mostrarultimo() {
        //seria como `para imprimir la factura 
        sSQL = "SELECT idfactura FROM `factura` order by idfactura DESC LIMIT 1";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {
                cadena = rs.getString("idfactura");

            }

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);

        }
        return cadena;
    }

    public boolean insertar(V_facturaVenta dts) {
        sSQL = "INSERT INTO factura(idfactura,idempleado,idage,\n"
                + "idclientenro,fecha, total_pago, total_item, total_serv)VALUES(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setString(1, dts.getIdfactura());
            pst.setString(2, dts.getIdempleado());
            pst.setString(3, dts.getIdage());
            pst.setString(4, dts.getIdclientenro()); 
            pst.setString(5, dts.getFecha());
            pst.setDouble(6, dts.getTotal_pago());
            pst.setString(7, dts.getTotal_item());
            pst.setString(8, dts.getTotal_ser());

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
        String sql = "SELECT COUNT(*) FROM factura WHERE idfactura = ?";
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
}