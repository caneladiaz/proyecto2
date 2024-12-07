package datos;

public class V_detalleVenta {

    private String iddetalle_venta;
    private String idfactura;
    private String idservicio;
    private String total_detalle;
    private double pre_servicio;

    public V_detalleVenta() {
    }

    public V_detalleVenta(String iddetalle_venta, String idfactura, String idservicio, String total_detalle, double pre_servicio) {
        this.iddetalle_venta = iddetalle_venta;
        this.idfactura = idfactura;
        this.idservicio = idservicio;
        this.total_detalle = total_detalle;
        this.pre_servicio = pre_servicio;
    }

    public String getIddetalle_venta() {
        return iddetalle_venta;
    }

    public void setIddetalle_venta(String iddetalle_venta) {
        this.iddetalle_venta = iddetalle_venta;
    }

    public String getIdfactura() {
        return idfactura;
    }

    public void setIdfactura(String idfactura) {
        this.idfactura = idfactura;
    }

    public String getIdservicio() {
        return idservicio;
    }

    public void setIdservicio(String idservicio) {
        this.idservicio = idservicio;
    }

    public String getTotal_detalle() {
        return total_detalle;
    }

    public void setTotal_detalle(String total_detalle) {
        this.total_detalle = total_detalle;
    }

    public double getPre_servicio() {
        return pre_servicio;
    }

    public void setPre_servicio(double pre_servicio) {
        this.pre_servicio = pre_servicio;
    }

  
  
}
