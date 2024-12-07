/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import datos.VdetalleFacCompra;
import datos.VdetallePedidocompra;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

/**
 *
 * @author admin
 */
public class Fdetalle_pedido_compra {
     
    private conexion mysql = new conexion();
    private Connection cn = mysql.conectar();
    private String sSQL ="";
    public Integer totalregistros;
    public Double totaldetalle;


    //le cambie factura_ventas por detalle_factura

    public boolean insertar(VdetallePedidocompra dts) {
        sSQL = "insert into detalle_pedido_compra(idpedidos_compra,"
                + "idinsumos, cantidad)\n"
                + "values(?,?,?)";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);

            pst.setString(1, dts.getIdpedido_compra());
            pst.setString(2, dts.getIdinsumos());
            pst.setDouble(3, dts.getCantidad());
            
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
