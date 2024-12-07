/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

public class Vrol {
    private String idRol;
    private String descripcion;

    public Vrol() {
    }

    
    public Vrol(String idRol, String descripcion) {
        this.idRol = idRol;
        this.descripcion = descripcion;
    }

    public String getIdRol() {
        return idRol;
    }

    public void setIdRol(String idRol) {
        this.idRol = idRol;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
    
    
}
