
package datos;

public class Vempleados {
    private String idempleado;
    private String idRol;
    private String iddistrito;
    private String user;
    private String contraseña;
    private String nombre;
    private String apellido;
    private String nro_ci;
    private String direccion;
    private String telefono;
    private String estado;

    public Vempleados() {
    }

    public Vempleados(String idempleado, String idRol, String iddistrito, String user, String contraseña, String nombre, String apellido, String nro_ci, String direccion, String telefono, String estado) {
        this.idempleado = idempleado;
        this.idRol = idRol;
        this.iddistrito = iddistrito;
        this.user = user;
        this.contraseña = contraseña;
        this.nombre = nombre;
        this.apellido = apellido;
        this.nro_ci = nro_ci;
        this.direccion = direccion;
        this.telefono = telefono;
        this.estado = estado;
    }

    public String getIdempleado() {
        return idempleado;
    }

    public void setIdempleado(String idempleado) {
        this.idempleado = idempleado;
    }

    public String getIdRol() {
        return idRol;
    }

    public void setIdRol(String idRol) {
        this.idRol = idRol;
    }

    public String getIddistrito() {
        return iddistrito;
    }

    public void setIddistrito(String iddistrito) {
        this.iddistrito = iddistrito;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNro_ci() {
        return nro_ci;
    }

    public void setNro_ci(String nro_ci) {
        this.nro_ci = nro_ci;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

   
    
}
