
package datos;

public class Vunidades {
    private String idunidades;
   private String descripcion;
   private String cantidad;
   private String estado;

    public Vunidades() {
    }

    public Vunidades(String idunidades, String descripcion, String cantidad, String estado) {
        this.idunidades = idunidades;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.estado = estado;
    }

    public String getIdunidades() {
        return idunidades;
    }

    public void setIdunidades(String idunidades) {
        this.idunidades = idunidades;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

   
}
