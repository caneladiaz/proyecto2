
package datos;


public class VpedidosCompra {
    private String idpedidocompra; 
    private String fecha;
    private String total_item;
    private String total_articulo;
    private String idproveedor;
    private String idempleado;
    private String estado;

    public VpedidosCompra() {
    }

    public VpedidosCompra(String idpedidocompra, String fecha, String total_item, String total_articulo, String idproveedor, String idempleado, String estado) {
        this.idpedidocompra = idpedidocompra;
        this.fecha = fecha;
        this.total_item = total_item;
        this.total_articulo = total_articulo;
        this.idproveedor = idproveedor;
        this.idempleado = idempleado;
        this.estado = estado;
    }

    public String getIdpedidocompra() {
        return idpedidocompra;
    }

    public void setIdpedidocompra(String idpedidocompra) {
        this.idpedidocompra = idpedidocompra;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTotal_item() {
        return total_item;
    }

    public void setTotal_item(String total_item) {
        this.total_item = total_item;
    }

    public String getTotal_articulo() {
        return total_articulo;
    }

    public void setTotal_articulo(String total_articulo) {
        this.total_articulo = total_articulo;
    }

    public String getIdproveedor() {
        return idproveedor;
    }

    public void setIdproveedor(String idproveedor) {
        this.idproveedor = idproveedor;
    }

    public String getIdempleado() {
        return idempleado;
    }

    public void setIdempleado(String idempleado) {
        this.idempleado = idempleado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    

    
    
}
