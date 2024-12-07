
package logica;

import datos.Vregion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author admin
 */
public class Fregion {
    //se crea un objeto msql de tipo coneccion (Clase conexion)
    private conexion msql=new conexion();
    //se crea una variable cn en la cual se va a guardar los valores devuletos por el metodo conectar de la clase conexion.
    private Connection cn= msql.conectar();
    
    private String sql="";
    public int totalregistro;
    
    public DefaultTableModel mostrar (String buscar){
        
        DefaultTableModel modelo;
        String []titulos={"Codigo", "Region" ,"estado"};
        String []registro=new String[3];
        totalregistro=0;
        modelo=new DefaultTableModel(null,titulos);
        
        sql="Select * from regiones where descripcion like '%"+buscar+"%'order by convert (idregion,unsigned);";
        try {
            Statement st= cn.createStatement();
            ResultSet rs= st.executeQuery(sql);
            while (rs.next()){
                registro[0] = rs.getString("idregion");
                registro[1] = rs.getString("descripcion");
                registro[2] = rs.getString("estado");
                
                totalregistro=totalregistro+1;
                modelo.addRow(registro);
            }
            return modelo;
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    
     //metodo insertar
    public boolean insertar(Vregion dts) {
        sql = "INSERT INTO regiones (idregion,descripcion,estado) values (?,?,?)";
        try {
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, dts.getIdregion());
            pst.setString(2, dts.getDescripcion());
            pst.setString(3, "Activo");
            int n = pst.executeUpdate();
             if (n != 0) {
                return false;
            } else {
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showInternalMessageDialog(null, e);
            return false;
        }
    }
    //metodo actualizar
    public boolean actualizar(Vregion dts) {
        sql = "UPDATE regiones SET descripcion=? where idregion=?";
        try {
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, dts.getDescripcion());
            pst.setString(2, dts.getIdregion());
            int n = pst.executeUpdate();
            if (n != 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showInternalMessageDialog(null, e);
            return false;
        }
    }
    
    public boolean ocultar(Vregion dts) {
        sql = "UPDATE regiones SET estado=? where idregion=?";
        try {
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, "inactivo");
            pst.setString(2, dts.getIdregion());
            int n = pst.executeUpdate();
            if (n != 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showInternalMessageDialog(null, e);
            return false;
        }
    }

    public boolean activar(Vregion dts) {
        sql = "UPDATE regiones SET estado=? where idregion=?";
        try {
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, "activo");
            pst.setString(2, dts.getIdregion());
            int n = pst.executeUpdate();
            if (n != 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showInternalMessageDialog(null, e);
            return false;
        }
    } 
}
