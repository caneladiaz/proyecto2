/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

public class V_facturaVenta {

    private String idfactura;
    private String idempleado;
    private String idage;
    private String idclientenro;
    private String fecha;
    private double total_pago;
    private String total_item;
    private String total_ser;

    public V_facturaVenta() {
    }

    public V_facturaVenta(String idfactura, String idempleado, String idage, String idclientenro, String fecha, double total_pago, String total_item, String total_ser) {
        this.idfactura = idfactura;
        this.idempleado = idempleado;
        this.idage = idage;
        this.idclientenro = idclientenro;
        this.fecha = fecha;
        this.total_pago = total_pago;
        this.total_item = total_item;
        this.total_ser = total_ser;
    }

    public String getIdfactura() {
        return idfactura;
    }

    public void setIdfactura(String idfactura) {
        this.idfactura = idfactura;
    }

    public String getIdempleado() {
        return idempleado;
    }

    public void setIdempleado(String idempleado) {
        this.idempleado = idempleado;
    }

    public String getIdage() {
        return idage;
    }

    public void setIdage(String idage) {
        this.idage = idage;
    }

    public String getIdclientenro() {
        return idclientenro;
    }

    public void setIdclientenro(String idclientenro) {
        this.idclientenro = idclientenro;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getTotal_pago() {
        return total_pago;
    }

    public void setTotal_pago(double total_pago) {
        this.total_pago = total_pago;
    }

    public String getTotal_item() {
        return total_item;
    }

    public void setTotal_item(String total_item) {
        this.total_item = total_item;
    }

    public String getTotal_ser() {
        return total_ser;
    }

    public void setTotal_ser(String total_ser) {
        this.total_ser = total_ser;
    }

    

}
