package logica;

import datos.Vclientes;
import datos.Vproveedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import presentacion.FRM_pv;

public class Fproveedor {
    //se crea un objeto msql de tipo coneccion (Clase conexion)

    private conexion msql = new conexion();
    //se crea una variable cn en la cual se va a guardar los valores devuletos por el metodo conectar de la clase conexion.
    private Connection cn = msql.conectar();

    private String sql = "";
    public int totalregistro;

    public DefaultTableModel mostrar(String buscar) {

        DefaultTableModel modelo;
        String[] titulos = {"Codigo", "Nombre", "Apellido", "Nro_Cedula", "Ruc", "Teléfono",
            "Dirección", "Email", "estado", "cod_Distrito", "distrito"};
        String[] registro = new String[11];
        totalregistro = 0;
        modelo = new DefaultTableModel(null, titulos);
        // atender simepre las variables 
        sql = "select e.idproveedor, e.nombre, e.apellido,e.nro_ci,e.ruc, e.telefono "
                + ",e.direccion,e.email, e.estado,d.iddistrito,\n"
                + " d.descripcion as Distrito\n"
                + "from proveedor e \n"
                + "inner join distritos d on e.iddistrito = d.iddistrito\n"
                + "where (e.nombre) like \"%%\"\n"
                + "order by convert (e.nombre, unsigned)";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                //DEBE SER IGUAL AL DE LA BASE DE DATOS
                registro[0] = rs.getString("idproveedor");
                registro[1] = rs.getString("nombre");
                registro[2] = rs.getString("apellido");
                registro[3] = rs.getString("nro_ci");
                registro[4] = rs.getString("ruc");
                registro[5] = rs.getString("telefono");
                registro[6] = rs.getString("direccion");
                registro[7] = rs.getString("email");
                registro[8] = rs.getString("estado");
                registro[9] = rs.getString("iddistrito");
                registro[10] = rs.getString("distrito");
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
    public boolean insertar(Vproveedor dts) {
        sql = "INSERT INTO proveedor (idproveedor,iddistrito,nombre,apellido,nro_ci,ruc, "
                + "telefono, direccion,email, estado) values (?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, dts.getIdproveedor());
            pst.setString(2, dts.getIddistrito());
            pst.setString(3, dts.getNombre());
            pst.setString(4, dts.getApellido());
            pst.setString(5, dts.getNro_ci());
            pst.setString(6, dts.getRuc());
            pst.setString(7, dts.getTelefono());
            pst.setString(8, dts.getDireccion());
            pst.setString(9, dts.getEmail());
            pst.setString(10, "Activo");
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
    public boolean actualizar(Vproveedor dts) {
        sql = "UPDATE proveedor SET nombre=?, apellido=?,nro_ci=?, "
                + " telefono=?, email=?, direccion=?,iddistrito=? where idproveedor=?";
        try {
            PreparedStatement pst = cn.prepareStatement(sql);

            pst.setString(1, dts.getNombre());
            pst.setString(2, dts.getApellido());
            pst.setString(3, dts.getNro_ci());
            pst.setString(4, dts.getTelefono());
            pst.setString(5, dts.getDireccion());
            pst.setString(6, dts.getEmail());
            pst.setString(7, dts.getIddistrito());
            pst.setString(8, dts.getIdproveedor());

            //  pst.setString(8, "Activo");
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

    public boolean ocultar(Vproveedor dts) {
        sql = "UPDATE proveedor SET estado=? where idproveedor=?";
        try {
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, "inactivo");
            pst.setString(2, dts.getIdproveedor());
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

    public boolean activar(Vproveedor dts) {
        sql = "UPDATE proveedor SET estado=? where idproveedor=?";
        try {
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, "activo");
            pst.setString(2, dts.getIdproveedor());
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
            String query = "SELECT nombre,ruc,telefono FROM proveedor WHERE idproveedor = ?";
            stmt = cn.prepareStatement(query);
            stmt.setString(1, id); // Pasa el ID como parámetro

            // Ejecuta la consulta
            rs = stmt.executeQuery();

            // Verifica si se encontraron datos
            if (rs.next()) {
                // Obtiene los valores de la consulta
                String nombre = rs.getString("nombre");
                String ruc = rs.getString("ruc");
                String telefono = rs.getString("telefono");
//                String precio_compra = rs.getString("precio_compra");
//                String telefono = rs.getString("telefono");

                // Muestra los datos en los campos de texto
                FRM_pv.txtProveedor.setText(nombre);
                FRM_pv.txtRucProveedor.setText(ruc);
                FRM_pv.txtTelefonoProv.setText(telefono);
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
