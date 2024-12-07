
package datos;


public class Vinsumos {
    private String idinsumos;
    private String idproveedor;
    private String idcategoria;
    private String idunidades;
    private String descripcion;
    private double cantidad;
    private double precio_compra;
    private String estado;

    public Vinsumos() {
    }

    public Vinsumos(String idinsumos, String idproveedor, String idcategoria, String idunidades, String descripcion, double cantidad, double precio_compra, String estado) {
        this.idinsumos = idinsumos;
        this.idproveedor = idproveedor;
        this.idcategoria = idcategoria;
        this.idunidades = idunidades;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precio_compra = precio_compra;
        this.estado = estado;
    }

    public String getIdinsumos() {
        return idinsumos;
    }

    public void setIdinsumos(String idinsumos) {
        this.idinsumos = idinsumos;
    }

    public String getIdproveedor() {
        return idproveedor;
    }

    public void setIdproveedor(String idproveedor) {
        this.idproveedor = idproveedor;
    }

    public String getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(String idcategoria) {
        this.idcategoria = idcategoria;
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

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio_compra() {
        return precio_compra;
    }

    public void setPrecio_compra(double precio_compra) {
        this.precio_compra = precio_compra;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

   
    
}
