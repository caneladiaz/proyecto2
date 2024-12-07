/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.io.File;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;

public class conexionReport {
    // Configuración de la base de datos
    private static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/sistemapeluqueria?serverTimezone=UTC";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASS = "1234";
    //private static final String REPORT_PATH = "C:\Users\Hp\JaspersoftWorkspace\MyReports\alumnosano.jasper"; // Ruta a tu archivo .jasper

    public static void iniciarReport(String reportPath, Map<String, Object> parameters) {


        Connection connection = null;

        try {
            // Cargar el driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establecer la conexión
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
            //System.out.println("Se conecto wachooooooo");

            // Cargar el reporte .jasper
            JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(reportPath);


            // Rellenar el reporte como te la voy a rellenar a vos 
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);

            // Mostrar el reporte en una ventana
            if (jasperPrint.getPages().isEmpty()) {
                System.out.println("El reporte no tiene páginas.");
            } else {
                JasperViewer.viewReport(jasperPrint, false);
            }

//              PILIN 


        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Driver no encontrado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error de conexión: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (JRException e) {
            JOptionPane.showMessageDialog(null, "Error al generar el reporte: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } finally {
            // Cerrar la conexión si está abierta como tus piernas 
            //              PILINnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
