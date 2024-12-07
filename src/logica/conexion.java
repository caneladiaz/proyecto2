package logica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class conexion {
     public String db="sistemaPeluqueria";
    public String url="jdbc:mysql://localhost:3306/"+db;
    public String user="root";
    public String pass="1234";
    Connection conecta = null;

    public conexion() {
    }
    
    public Connection conectar(){
       Connection link=null;
       try { //com.mysql.jdbc.Driver
           Class.forName("org.gjt.mm.mysql.Driver");
           link=DriverManager.getConnection(this.url,this.user,this.pass);     
                   
       } catch (ClassNotFoundException | SQLException e) {
           //el error que ocurra se guardara en la variable e
           JOptionPane.showMessageDialog(null,e);
       }
       return link;
   }
   public PreparedStatement prepareStatement(String sql){
       throw new UnsupportedOperationException("No soportado");
   } 
}
