
package datos;

import javax.print.DocFlavor;


public class VdetalleFacCompra {
   private String idfactura_compra; 
   private String idDetallefactura_compra; 
   private String idinsumos;
   private String total_detalle;
   private double cantidad;
   private double precio_compra;

    public VdetalleFacCompra() {
    }

    public VdetalleFacCompra(String idfactura_compra, String idDetallefactura_compra, String idinsumos, String total_detalle, double cantidad, double precio_compra) {
        this.idfactura_compra = idfactura_compra;
        this.idDetallefactura_compra = idDetallefactura_compra;
        this.idinsumos = idinsumos;
        this.total_detalle = total_detalle;
        this.cantidad = cantidad;
        this.precio_compra = precio_compra;
    }

    public String getIdfactura_compra() {
        return idfactura_compra;
    }

    public void setIdfactura_compra(String idfactura_compra) {
        this.idfactura_compra = idfactura_compra;
    }

    public String getIdDetallefactura_compra() {
        return idDetallefactura_compra;
    }

    public void setIdDetallefactura_compra(String idDetallefactura_compra) {
        this.idDetallefactura_compra = idDetallefactura_compra;
    }

    public String getIdinsumos() {
        return idinsumos;
    }

    public void setIdinsumos(String idinsumos) {
        this.idinsumos = idinsumos;
    }

    public String getTotal_detalle() {
        return total_detalle;
    }

    public void setTotal_detalle(String total_detalle) {
        this.total_detalle = total_detalle;
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

    


   

   
   
   
}
