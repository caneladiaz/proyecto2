/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import datos.V_detalleVenta;
import datos.VdetalleFacCompra;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

/**
 *
 * @author admin
 */
public class F_detalleVenta {

    private conexion mysql = new conexion();
    private Connection cn = mysql.conectar();
    private String sSQL = "";
    public Integer totalregistros;
    public Double totaldetalle;

    //le cambie factura_ventas por detalle_factura
    public boolean insertar(V_detalleVenta dts) {
        sSQL = "insert into detalle_venta(idfactura,idservicio,"
                + "total_Detalle,pre_servicio)\n"
                + "values(?,?,?,?)";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);

            pst.setString(1, dts.getIdfactura());
            pst.setString(2, dts.getIdservicio());
            pst.setString(3, dts.getTotal_detalle());
            pst.setDouble(4, dts.getPre_servicio());

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
}
