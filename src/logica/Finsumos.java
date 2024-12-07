package logica;

import datos.Vinsumos;
import datos.Vproveedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import presentacion.FRM_pv;

public class Finsumos {
    //se crea un objeto msql de tipo coneccion (Clase conexion)

    private conexion msql = new conexion();
    //se crea una variable cn en la cual se va a guardar los valores devuletos por el metodo conectar de la clase conexion.
    private Connection cn = msql.conectar();

    private String sql = "";
    public int totalregistro;
    private String sSQL = "";

    public DefaultTableModel mostrar(String buscar) {

        DefaultTableModel modelo;
        String[] titulos = {"Codigo", "Producto/Art", "Cantidad", "Precio_compra",
            "estado", "Cod_proveedor", "proveedor", "cod_Categoria", "categoria", "cod_uni", "descripcion","Cant_U"};
        String[] registro = new String[12];
        totalregistro = 0;
        modelo = new DefaultTableModel(null, titulos);
        // atender simepre las variables
        sql = "select e.idinsumos, e.descripcion as insumo, e.cantidad, e.precio_compra\n"
                + ",e.estado,d.idproveedor,d.nombre as proveedor, r.idcategoria, r.descripcion as categoria, \n"
                + "u.idunidades, u.descripcion, u.cantidad AS unidad\n"
                + "from insumos e \n"
                + "inner join proveedor d on e.idproveedor = d.idproveedor\n"
                + "inner join categoria r on e.idcategoria = r.idcategoria\n"
                + "INNER JOIN unidades u ON e.idunidades = u.idunidades\n"
                + "where (e.descripcion) like '%" + buscar + "%' \n"
                + "order by convert (e.idinsumos, unsigned)";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                //DEBE SER IGUAL AL DE LA BASE DE DATOS
                registro[0] = rs.getString("idinsumos");
                registro[1] = rs.getString("insumo");
                registro[2] = rs.getString("cantidad");
                registro[3] = rs.getString("precio_compra");
                registro[4] = rs.getString("estado");
                registro[5] = rs.getString("idproveedor");
                registro[6] = rs.getString("proveedor");
                registro[7] = rs.getString("idcategoria");
                registro[8] = rs.getString("categoria");
                registro[9] = rs.getString("idunidades");
                registro[10] = rs.getString("descripcion");
                registro[11] = rs.getString("unidad");

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
    public boolean insertar(Vinsumos dts) {
        sql = "INSERT INTO insumos (idinsumos,idproveedor,idcategoria,idunidades,descripcion,cantidad,precio_compra, "
                + " estado) values (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, dts.getIdinsumos());
            pst.setString(2, dts.getIdproveedor());
            pst.setString(3, dts.getIdcategoria());
            pst.setString(4, dts.getIdunidades());
            pst.setString(5, dts.getDescripcion());
            pst.setDouble(6, dts.getCantidad());
            pst.setDouble(7, dts.getPrecio_compra());
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
    public boolean actualizar(Vinsumos dts) {
        sql = "UPDATE insumos SET descripcion=?, cantidad=?, "
                + " precio_compra=?, idproveedor=?,"
                + " idcategoria=?, idunidades=? where idinsumos=?";
        try {
            PreparedStatement pst = cn.prepareStatement(sql);

            pst.setString(1, dts.getDescripcion());
            pst.setDouble(2, dts.getCantidad());
            pst.setDouble(3, dts.getPrecio_compra());
            pst.setString(4, dts.getIdproveedor());
            pst.setString(5, dts.getIdcategoria());
            pst.setString(6, dts.getIdunidades());
            pst.setString(7, dts.getIdinsumos());

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

    public boolean ocultar(Vinsumos dts) {
        sql = "UPDATE insumos SET estado=? where idinsumos=?";
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

    public boolean activar(Vinsumos dts) {
        sql = "UPDATE insumos SET estado=? where idinsumos=?";
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

    public boolean actualizarStock(int idinsumo, double cantidadVendida) {
        sql = "UPDATE insumos SET cantidad = cantidad + ? WHERE idinsumos = ?";

        try ( PreparedStatement pst = cn.prepareStatement(sql)) {
            pst.setDouble(1, cantidadVendida); // Resta la cantidad vendida al stock actual
            pst.setInt(2, idinsumo); // ID del producto a actualizar
            int n = pst.executeUpdate();
            return n != 0; // Devuelve true si la actualización fue exitosa
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return false; // Devuelve false si hubo un error
        }
    }
    
    
    public void buscarDatosPorID(String id) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Consulta SQL para obtener los datos por ID
            String query = "SELECT descripcion,precio_compra FROM insumos WHERE idinsumos = ?";
            stmt = cn.prepareStatement(query);
            stmt.setString(1, id); // Pasa el ID como parámetro

            // Ejecuta la consulta
            rs = stmt.executeQuery();

            // Verifica si se encontraron datos
            if (rs.next()) {
                // Obtiene los valores de la consulta
                String nombre = rs.getString("descripcion");
                String precio_compra = rs.getString("precio_compra");
               
//                String precio_compra = rs.getString("precio_compra");
//                String telefono = rs.getString("telefono");

                // Muestra los datos en los campos de texto
                FRM_pv.txtProducto.setText(nombre);
                FRM_pv.txtPrecioCompra.setText(precio_compra);
              
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
