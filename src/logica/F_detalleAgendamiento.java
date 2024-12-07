/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import datos.VdetalleAgendamiento;
import datos.VdetallePedidocompra;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

public class F_detalleAgendamiento {
     
    private conexion mysql = new conexion();
    private Connection cn = mysql.conectar();
    private String sSQL ="";
    public Integer totalregistros;
    public Double totaldetalle;


    //le cambie factura_ventas por detalle_factura

    public boolean insertar(VdetalleAgendamiento dts) {
        sSQL = "insert into detalle_agendamiento(idage,"
                + "idservicio, cantidad_servicios)\n"
                + "values(?,?,?)";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);

            pst.setString(1, dts.getIdage());
            pst.setString(2, dts.getIdservicio());
            pst.setDouble(3, dts.getCantidad_servicios());
            
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
