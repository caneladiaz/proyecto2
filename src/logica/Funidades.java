package logica;

import java.sql.PreparedStatement;
import datos.Vunidades;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Funidades {

    //se crea un objeto msql de tipo coneccion (Clase conexion)
    private conexion msql = new conexion();
    //se crea una variable cn en la cual se va a guardar los valores devuletos por el metodo conectar de la clase conexion.
    private Connection cn = msql.conectar();

    private String sql = "";
    private String sSQL = "";
    private String sSQL2 = "";
    public int totalregistro;

    public DefaultTableModel mostrar(String buscar) {

        DefaultTableModel modelo;
        String[] titulos = {"Codigo", "descripcion", "cantidad", "estado"};
        String[] registro = new String[4];
        totalregistro = 0;
        modelo = new DefaultTableModel(null, titulos);

        sql = "Select * from unidades where descripcion like '%" + buscar + "%'order by convert (idunidades,unsigned);";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                registro[0] = rs.getString("idunidades");
                registro[1] = rs.getString("descripcion");
                registro[2] = rs.getString("cantidad");
                registro[3] = rs.getString("estado");
                totalregistro = totalregistro + 1;
                modelo.addRow(registro);
            }
            return modelo;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

}
