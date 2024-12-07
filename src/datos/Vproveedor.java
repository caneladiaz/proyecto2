package datos;

public class Vproveedor {

    private String idproveedor;
    private String iddistrito;
    private String nombre;
    private String apellido;
    private String nro_ci;
    private String ruc;
    private String telefono;
    private String direccion;
    private String email;
    private String estado;

    public Vproveedor() {
    }

    public Vproveedor(String idproveedor, String iddistrito, String nombre, String apellido, String nro_ci, String ruc, String telefono, String direccion, String email, String estado) {
        this.idproveedor = idproveedor;
        this.iddistrito = iddistrito;
        this.nombre = nombre;
        this.apellido = apellido;
        this.nro_ci = nro_ci;
        this.ruc = ruc;
        this.telefono = telefono;
        this.direccion = direccion;
        this.email = email;
        this.estado = estado;
    }

    public String getIdproveedor() {
        return idproveedor;
    }

    public void setIdproveedor(String idproveedor) {
        this.idproveedor = idproveedor;
    }

    public String getIddistrito() {
        return iddistrito;
    }

    public void setIddistrito(String iddistrito) {
        this.iddistrito = iddistrito;
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

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    

}
