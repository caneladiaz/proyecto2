
package logica;

import datos.Vdeptos;
import datos.Vdtto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Fdtto {
    //se crea un objeto msql de tipo coneccion (Clase conexion)
    private conexion msql=new conexion();
    //se crea una variable cn en la cual se va a guardar los valores devuletos por el metodo conectar de la clase conexion.
    private Connection cn= msql.conectar();
    
    private String sql="";
    public int totalregistro;
    
    public DefaultTableModel mostrar (String buscar){
        
        DefaultTableModel modelo;
        String []titulos={"Codigo", "Distrito","Id Depto", "Depto","estado"};
        String []registro=new String[5];
        totalregistro=0;
        modelo=new DefaultTableModel(null,titulos);
        
        sql="select r.iddistrito , r.descripcion as distrito, d.iddepartamento, d.descripcion as departamento, r.estado\n" +
            "from distritos r inner join departamentos d on r.iddepartamento= d.iddepartamento\n" +
            "where concat(r.descripcion, d.descripcion) like '%"+buscar+"%'\n" 
             + "order by convert (iddistrito, unsigned)";
        try {
            Statement st= cn.createStatement();
            ResultSet rs= st.executeQuery(sql);
            while (rs.next()){
                registro[0] = rs.getString("iddistrito");
                registro[1] = rs.getString("distrito");
                registro[2] = rs.getString("iddepartamento");
                registro[3] = rs.getString("departamento");
                registro[4] = rs.getString("estado");
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
    public boolean insertar(Vdtto dts){
        sql = "INSERT INTO Distritos (iddistrito, descripcion,iddepartamento,estado) "
                + "values (?,?,?,?)";
        try {
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, dts.getIddistrito());
            pst.setString(2, dts.getDescripcion());
            pst.setString(3, dts.getIddepartamento());
            pst.setString(4, "Activo");
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
    public boolean actualizar(Vdtto dts) {
        sql = "UPDATE distritos SET descripcion=?,iddepartamento=? where iddistrito=?";
        try {
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, dts.getDescripcion());
            pst.setString(2, dts.getIddepartamento());
            pst.setString(3, dts.getIddistrito());
            pst.setString(4, "Activo");
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
    public boolean ocultar(Vdtto dts) {
        sql = "UPDATE distritos SET estado=? where iddistrito=?";
        try {
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, "inactivo");
            pst.setString(2, dts.getIddistrito());
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

    public boolean activar(Vdtto dts) {
        sql = "UPDATE distritos SET estado=? where iddistrito=?";
        try {
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, "activo");
            pst.setString(2, dts.getIddistrito());
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
