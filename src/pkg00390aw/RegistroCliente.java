
package pkg00390aw;

import java.io.Serializable;


public class RegistroCliente implements Serializable{
    private String nombre;
    private String ci;
    private String empresa;
    private String telefono;
    private String direccion;
    private String correo;
    public void RegistroCliente(){
        this.nombre="";
        this.ci="";
        this.empresa="";
        this.telefono="";
        this.direccion="";
        this.correo="";
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
}
