package datos;

public class Vagendamiento {

    private String idage;
    private String idservicio;
    private String idclientenro;
    private String idempleado;
    private String fecha;
    private String estado;

    public Vagendamiento() {
    }

    public Vagendamiento(String idage, String idservicio, String idclientenro, String idempleado, String fecha, String estado) {
        this.idage = idage;
        this.idservicio = idservicio;
        this.idclientenro = idclientenro;
        this.idempleado = idempleado;
        this.fecha = fecha;
        this.estado = estado;
    }

    public String getIdage() {
        return idage;
    }

    public void setIdage(String idage) {
        this.idage = idage;
    }

    public String getIdservicio() {
        return idservicio;
    }

    public void setIdservicio(String idservicio) {
        this.idservicio = idservicio;
    }

    public String getIdclientenro() {
        return idclientenro;
    }

    public void setIdclientenro(String idclientenro) {
        this.idclientenro = idclientenro;
    }

    public String getIdempleado() {
        return idempleado;
    }

    public void setIdempleado(String idempleado) {
        this.idempleado = idempleado;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    

}
