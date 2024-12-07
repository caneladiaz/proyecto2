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
public class Vdtto {
    private String iddistrito;
    private String iddepartamento;
    private String descripcion;

    public Vdtto() {
    }
    
    public Vdtto (String iddistrito, String iddepartamento, String descripcion){
        this.iddistrito=iddistrito;
        this.iddepartamento=iddepartamento;
        this.descripcion=descripcion;
    }

    public String getIddistrito() {
        return iddistrito;
    }

    public void setIddistrito(String iddistrito) {
        this.iddistrito = iddistrito;
    }

    public String getIddepartamento() {
        return iddepartamento;
    }

    public void setIddepartamento(String iddepartamento) {
        this.iddepartamento = iddepartamento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
}
