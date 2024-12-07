package logica;

import datos.Vdeptos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Fdeptos {
    //se crea un objeto msql de tipo coneccion (Clase conexion)

    private conexion msql = new conexion();
    //se crea una variable cn en la cual se va a guardar los valores devuletos por el metodo conectar de la clase conexion.
    private Connection cn = msql.conectar();

    private String sql = "";
    public int totalregistro;

    public DefaultTableModel mostrar(String buscar) {

        DefaultTableModel modelo;
        String[] titulos = {"iddepartamento", "departamentos", "idregion", "region", "estado"};
        String[] registro = new String[5];
        totalregistro = 0;
        modelo = new DefaultTableModel(null, titulos);
        //, p on d.idregion = p.idregion\n
        sql = "select r.iddepartamento, r.descripcion as departamento, d.idregion, d.descripcion as region, r.estado\n"
                + "from departamentos r inner join regiones d on r.idregion= d.idregion\n"
                + "where (r.descripcion) like '%"+buscar+"%' \n"
                + "order by convert (iddepartamento,unsigned)";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {

                registro[0] = rs.getString("iddepartamento");
                registro[1] = rs.getString("departamento");
                registro[2] = rs.getString("idregion");
                registro[3] = rs.getString("Region");
                registro[4] = rs.getString("estado");
                totalregistro = totalregistro + 1;
                modelo.addRow(registro);
            }
            return modelo;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

    //metodo insertar
    public boolean insertar(Vdeptos dts) {
        sql = "INSERT INTO departamentos (iddepartamento,descripcion,idregion, estado) values (?,?,?,?)";
        try {
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, dts.getIddepartamento());
            pst.setString(2, dts.getDescripcion());
            pst.setString(3, dts.getIdregion());
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
    public boolean actualizar(Vdeptos dts) {
        sql = "UPDATE departamentos SET descripcion=?, idregion=? where iddepartamento=?";
        try {
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, dts.getDescripcion());
            pst.setString(2, dts.getIdregion());
            pst.setString(3, dts.getIddepartamento());
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

    public boolean ocultar(Vdeptos dts) {
        sql = "UPDATE departamentos SET estado=? where iddepartamento=?";
        try {
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, "inactivo");
            pst.setString(2, dts.getIddepartamento());
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

    public boolean activar(Vdeptos dts) {
        sql = "UPDATE departamentos SET estado=? where iddepartamento=?";
        try {
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, "activo");
            pst.setString(2, dts.getIddepartamento());
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
