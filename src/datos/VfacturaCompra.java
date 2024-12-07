/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

/**
 *
 * @author admin
 */
public class VfacturaCompra {

    private String idfactura_compra;
    private String idproveedor;
    private String idempleado;
    private String idpedidos_compra;
    private String fecha;
    private double total_Pago;
    private String total_item;
    private String total_articulo;

    public VfacturaCompra() {
    }

    public VfacturaCompra(String idfactura_compra, String idproveedor, String idempleado, String idpedidos_compra, String fecha, double total_Pago, String total_item, String total_articulo) {
        this.idfactura_compra = idfactura_compra;
        this.idproveedor = idproveedor;
        this.idempleado = idempleado;
        this.idpedidos_compra = idpedidos_compra;
        this.fecha = fecha;
        this.total_Pago = total_Pago;
        this.total_item = total_item;
        this.total_articulo = total_articulo;
    }

    public String getIdfactura_compra() {
        return idfactura_compra;
    }

    public void setIdfactura_compra(String idfactura_compra) {
        this.idfactura_compra = idfactura_compra;
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

    public String getIdpedidos_compra() {
        return idpedidos_compra;
    }

    public void setIdpedidos_compra(String idpedidos_compra) {
        this.idpedidos_compra = idpedidos_compra;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getTotal_Pago() {
        return total_Pago;
    }

    public void setTotal_Pago(double total_Pago) {
        this.total_Pago = total_Pago;
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

    
}
