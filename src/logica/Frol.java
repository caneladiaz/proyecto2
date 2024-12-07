/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author admin
 */
public class Frol {
    //se crea un objeto msql de tipo coneccion (Clase conexion)
    private conexion msql=new conexion();
    //se crea una variable cn en la cual se va a guardar los valores devuletos por el metodo conectar de la clase conexion.
    private Connection cn= msql.conectar();
    
    private String sql="";
    public int totalregistro;
    
    public DefaultTableModel mostrar (String buscar){
        
        DefaultTableModel modelo;
        String []titulos={"Codigo", "rol"};
        String []registro=new String[2];
        totalregistro=0;
        modelo=new DefaultTableModel(null,titulos);
        
        sql="Select * from Rol where descripcion like '%"+buscar+"%'order by convert (idrol,unsigned);";
        try {
            Statement st= cn.createStatement();
            ResultSet rs= st.executeQuery(sql);
            while (rs.next()){
                registro[0] = rs.getString("idRol");
                registro[1] = rs.getString("descripcion");
                
                totalregistro=totalregistro+1;
                modelo.addRow(registro);
            }
            return modelo;
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
}
