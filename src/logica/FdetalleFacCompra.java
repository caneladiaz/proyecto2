
package logica;

import datos.VdetalleFacCompra;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

/**
 *
 * @author admin
 */
public class FdetalleFacCompra {
    
    private conexion mysql = new conexion();
    private Connection cn = mysql.conectar();
    private String sSQL ="";
    public Integer totalregistros;
    public Double totaldetalle;


    //le cambie factura_ventas por detalle_factura

    public boolean insertar(VdetalleFacCompra dts) {
        sSQL = "insert into detalle_factura_compra(idfactura_compra,"
                + "idinsumos, total_Detalle, cantidad, preciocompra)\n"
                + "values(?,?,?,?,?)";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);

            pst.setString(1, dts.getIdfactura_compra());
            pst.setString(2, dts.getIdinsumos());
            pst.setString(3, dts.getTotal_detalle());
            pst.setDouble(4, dts.getCantidad());
            pst.setDouble(5, dts.getPrecio_compra());
            
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
