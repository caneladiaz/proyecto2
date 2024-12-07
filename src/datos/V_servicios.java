
package datos;

public class V_servicios {
   private String idservico; 
   private String descripcion; 
   private String total_servicio;

    public V_servicios() {
    }

    public V_servicios(String idservico, String descripcion, String total_servicio) {
        this.idservico = idservico;
        this.descripcion = descripcion;
        this.total_servicio = total_servicio;
    }

    public String getIdservico() {
        return idservico;
    }

    public void setIdservico(String idservico) {
        this.idservico = idservico;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTotal_servicio() {
        return total_servicio;
    }

    public void setTotal_servicio(String total_servicio) {
        this.total_servicio = total_servicio;
    }
 
}
