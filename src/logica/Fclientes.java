/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import datos.Vclientes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import presentacion.frm_facturaVenta;

/**
 *
 * @author admin
 */
public class Fclientes {

    //se crea un objeto msql de tipo coneccion (Clase conexion)
    private conexion msql = new conexion();
    //se crea una variable cn en la cual se va a guardar los valores devuletos por el metodo conectar de la clase conexion.
    private Connection cn = msql.conectar();

    private String sql = "";
    public int totalregistro;

    public DefaultTableModel mostrar(String buscar) {

        DefaultTableModel modelo;
        String[] titulos = {"Codi        DefaultTableModel modelo;\n" +
"go", "Nombre", "Apellido","Nroº cedula",
            "Dirección", "Teléfono", "Estado","Cod_distrito","distrito"};
        String[] registro = new String[9];
        totalregistro = 0;
        modelo = new DefaultTableModel(null, titulos);
        // atender siempre las variables 
        sql = "select e.idclientenro, e.nombre, e.apellido,e.nro_ci, e.direccion, e.telefono, e.estado,d.iddistrito,\n"
                + " d.descripcion as Distrito\n"
                + "from clientes e \n"
                + "inner join distritos d on e.iddistrito = d.iddistrito\n"
                + "where (e.nombre) like \"%%\"\n"
                + "order by convert (e.nombre, unsigned)";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                //DEBE SER IGUAL AL DE LA BASE DE DATOS
                registro[0] = rs.getString("idclientenro");
                registro[1] = rs.getString("nombre");
                registro[2] = rs.getString("apellido");
                registro[3] = rs.getString("nro_ci");
                registro[4] = rs.getString("direccion");
                registro[5] = rs.getString("telefono");
                registro[6] = rs.getString("estado");
                registro[7] = rs.getString("iddistrito");
                registro[8] = rs.getString("distrito");
                
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
    public boolean insertar(Vclientes dts) {
        sql = "INSERT INTO clientes ( idclientenro,iddistrito,nombre,apellido,nro_ci,direccion,"
                + " telefono,estado) values (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, dts.getIdclientenro());
            pst.setString(2, dts.getIddistrito());
            pst.setString(3, dts.getNombre());
            pst.setString(4, dts.getApellido());
            pst.setString(5, dts.getNro_ci());
            pst.setString(6, dts.getDireccion());
            pst.setString(7, dts.getTelefono());
            pst.setString(8, "Activo");
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
    public boolean actualizar(Vclientes dts) {
        sql = "UPDATE clientes SET nombre=?,apellido=?,nro_ci=?,direccion=?, "
                + "telefono=?,iddistrito=? where idclientenro=?";
        try {
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, dts.getNombre());
            pst.setString(2, dts.getApellido());
            pst.setString(3, dts.getNro_ci());
            pst.setString(4, dts.getDireccion());
            pst.setString(5, dts.getTelefono());
            pst.setString(6, dts.getIddistrito());
            pst.setString(7, dts.getIdclientenro());

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

    public boolean ocultar(Vclientes dts) {
        sql = "UPDATE clientes SET estado=? where idclientenro=?";
        try {
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, "inactivo");
            pst.setString(2, dts.getIdclientenro());
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

    public boolean activar(Vclientes dts) {
        sql = "UPDATE cliente SET estado=? where idclientenro=?";
        try {
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, "activo");
            pst.setString(2, dts.getIdclientenro());
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
            String query = "SELECT nombre FROM clientes WHERE idclientenro = ?";
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
                frm_facturaVenta.txtcliente.setText(nombre);
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
