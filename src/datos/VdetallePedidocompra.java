
package datos;

public class VdetallePedidocompra {
    private String iddetalle_pedido_compra; 
   private String idpedido_compra; 
   private String idinsumos;
   private double cantidad;

    public VdetallePedidocompra() {
    }

    public VdetallePedidocompra(String iddetalle_pedido_compra, String idpedido_compra, String idinsumos, double cantidad) {
        this.iddetalle_pedido_compra = iddetalle_pedido_compra;
        this.idpedido_compra = idpedido_compra;
        this.idinsumos = idinsumos;
        this.cantidad = cantidad;
    }

    public String getIddetalle_pedido_compra() {
        return iddetalle_pedido_compra;
    }

    public void setIddetalle_pedido_compra(String iddetalle_pedido_compra) {
        this.iddetalle_pedido_compra = iddetalle_pedido_compra;
    }

    public String getIdpedido_compra() {
        return idpedido_compra;
    }

    public void setIdpedido_compra(String idpedido_compra) {
        this.idpedido_compra = idpedido_compra;
    }

    public String getIdinsumos() {
        return idinsumos;
    }

    public void setIdinsumos(String idinsumos) {
        this.idinsumos = idinsumos;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    
   
   
}
