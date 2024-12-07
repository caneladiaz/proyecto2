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
public class Vdeptos {
    private String iddepartamento;
    private String idregion;
    private String descripcion;
    private String estado;

    public Vdeptos() {
    }
    
    public Vdeptos (String iddepartamento, String idregion, String descripcion, String estado){
        this.iddepartamento=iddepartamento;
        this.idregion=idregion;
        this.descripcion=descripcion;
        this.estado=estado;
    }

    public String getIddepartamento() {
        return iddepartamento;
    }

    public void setIddepartamento(String iddepartamento) {
        this.iddepartamento = iddepartamento;
    }

    public String getIdregion() {
        return idregion;
    }

    public void setIdregion(String idregion) {
        this.idregion = idregion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}
