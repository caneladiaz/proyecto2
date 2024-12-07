/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

public class VdetalleAgendamiento {
    private String idage; 
   private String idDetalleAge; 
   private String idservicio;
   private double cantidad_servicios;

    public VdetalleAgendamiento() {
    }

    public VdetalleAgendamiento(String idage, String idDetalleAge, String idservicio, double cantidad_servicios) {
        this.idage = idage;
        this.idDetalleAge = idDetalleAge;
        this.idservicio = idservicio;
        this.cantidad_servicios = cantidad_servicios;
    }

    public String getIdage() {
        return idage;
    }

    public void setIdage(String idage) {
        this.idage = idage;
    }

    public String getIdDetalleAge() {
        return idDetalleAge;
    }

    public void setIdDetalleAge(String idDetalleAge) {
        this.idDetalleAge = idDetalleAge;
    }

    public String getIdservicio() {
        return idservicio;
    }

    public void setIdservicio(String idservicio) {
        this.idservicio = idservicio;
    }

    public double getCantidad_servicios() {
        return cantidad_servicios;
    }

    public void setCantidad_servicios(double cantidad_servicios) {
        this.cantidad_servicios = cantidad_servicios;
    }
   
   
}
