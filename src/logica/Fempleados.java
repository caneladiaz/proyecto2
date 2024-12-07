package logica;

import datos.Vempleados;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import presentacion.FRM_pv;
import presentacion.frm_facturaVenta;

public class Fempleados {

    //se crea un objeto msql de tipo coneccion (Clase conexion)
    private conexion msql = new conexion();
    //se crea una variable cn en la cual se va a guardar los valores devuletos por el metodo conectar de la clase conexion.
    private Connection cn = msql.conectar();

    private String sql = "";
    public int totalregistro;

    public DefaultTableModel login(String user, String contraseña) {

        DefaultTableModel modelo;
        String[] titulos = {"idempleado", "user", "contraseña", "Nombre",
            "Apellido", "Nro Cedula ","Direccion", "telefono", "estado", "id distrito", "Distrito", "idRol", "Rol"};
        String[] registro = new String[13];
        totalregistro = 0;
        modelo = new DefaultTableModel(null, titulos);

        sql = "SELECT * FROM empleados where (user='\" + user + \"' and contraseña='\" + contraseña + \"')\n"
                + "order by idempleado;";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                 registro[0] = rs.getString("idempleado");
                registro[1] = rs.getString("user");
                registro[2] = rs.getString("contraseña");
                registro[3] = rs.getString("nombre");
                registro[4] = rs.getString("apellido");
                 registro[5] = rs.getString("nro_ci");
                registro[6] = rs.getString("direccion");
                registro[7] = rs.getString("telefono");
                registro[8] = rs.getString("estado");
                registro[9] = rs.getString("iddistrito");
                registro[10] = rs.getString("distrito");
                registro[11] = rs.getString("idRol");
                registro[12] = rs.getString("rol");

                totalregistro = totalregistro + 1;
                modelo.addRow(registro);
            }
            return modelo;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

    public DefaultTableModel mostrar(String buscar) {

        DefaultTableModel modelo;
        String[] titulos = {"idempleado", "user", "contraseña", "Nombre",
            "Apellido","Nro Cedula", "Direccion", "telefono", "estado", "id distrito", "Distrito", "idRol", "Rol"};
        String[] registro = new String[13];
        totalregistro = 0;
        modelo = new DefaultTableModel(null, titulos);

        sql = "select e.idempleado, e.user, e.contraseña, e.nombre, e.apellido, e.nro_ci,e.direccion, \n"
                + "e.telefono, e.estado, d.iddistrito, d.descripcion as Distrito, r.idrol, r.descripcion as rol\n"
                + "from empleados e \n"
                + "inner join distritos d on e.iddistrito = d.iddistrito\n"
                + "inner join rol r on e.idRol = r.idRol\n"
                + "where (e.nombre) like '%" + buscar + "%'\n"
                + "order by convert (e.idempleado, unsigned)";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                registro[0] = rs.getString("idempleado");
                registro[1] = rs.getString("user");
                registro[2] = rs.getString("contraseña");
                registro[3] = rs.getString("nombre");
                registro[4] = rs.getString("apellido");
                 registro[5] = rs.getString("nro_ci");
                registro[6] = rs.getString("direccion");
                registro[7] = rs.getString("telefono");
                registro[8] = rs.getString("estado");
                registro[9] = rs.getString("iddistrito");
                registro[10] = rs.getString("distrito");
                registro[11] = rs.getString("idRol");
                registro[12] = rs.getString("rol");

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
    public boolean insertar(Vempleados dts) {
        sql = "INSERT INTO empleados (idempleado,idRol,iddistrito,user,contraseña,"
                + " nombre, apellido,nro_ci,direccion,telefono,estado)"
                + " values (?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, dts.getIdempleado());
            pst.setString(2, dts.getIdRol());
            pst.setString(3, dts.getIddistrito());
            pst.setString(4, dts.getUser());
            pst.setString(5, dts.getContraseña());
            pst.setString(6, dts.getNombre());
            pst.setString(7, dts.getApellido());
            pst.setString(8, dts.getNro_ci());
            pst.setString(9, dts.getDireccion());
            pst.setString(10, dts.getTelefono());
            pst.setString(11, "activo");
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
    public boolean actualizar(Vempleados dts) {
        sql = "UPDATE empleados SET  user=?,contraseña=?,nombre=?, apellido=?,nro_ci=?,"
                + "direccion=?,telefono=?, idrol=?, iddistrito=? where idempleado=?";
        try {
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, dts.getUser());
            pst.setString(2, dts.getContraseña());
            pst.setString(3, dts.getNombre());
            pst.setString(4, dts.getApellido());
            pst.setString(5, dts.getNro_ci());
            pst.setString(6, dts.getDireccion());
            pst.setString(7, dts.getTelefono());
            pst.setString(8, dts.getIdRol());
            pst.setString(9, dts.getIddistrito());
            pst.setString(10, dts.getIdempleado());
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

    public boolean ocultar(Vempleados dts) {
        sql = "UPDATE empleados SET estado=? where idempleado=?";
        try {
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, "inactivo");
            pst.setString(2, dts.getIdempleado());
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

    public boolean activar(Vempleados dts) {
        sql = "UPDATE empleados SET estado=? where idempleado=?";
        try {
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, "activo");
            pst.setString(2, dts.getIdempleado());
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
    
    // Método para buscar datos por ID
    public void buscarDatosPorID(String id) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Consulta SQL para obtener los datos por ID
            String query = "SELECT nombre FROM empleados WHERE idempleado = ?";
            stmt = cn.prepareStatement(query);
            stmt.setString(1, id); // Pasa el ID como parámetro

            // Ejecuta la consulta
            rs = stmt.executeQuery();

            // Verifica si se encontraron datos
            if (rs.next()) {
                // Obtiene los valores de la consulta
                String nombre = rs.getString("nombre");
//                String precio_compra = rs.getString("precio_compra");
//                String telefono = rs.getString("telefono");

                // Muestra los datos en los campos de texto
//                frm_facturaVenta.txtEmpleado.setText(nombre);
                FRM_pv.txtEmpleado.setText(nombre);
//                FrmFacturaCompra.txtPrecioCompra.setText(precio_compra);
//                FrmFacturaCompra.txtTeléfonoProv.setText(telefono);
            } else {
                JOptionPane.showMessageDialog(null, "ID no encontrado");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al consultar la base de datos: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Cierra la conexión y recursos
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
