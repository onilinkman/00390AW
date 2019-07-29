
package pkg00390aw;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class ConfigFactura implements Serializable{
    private String nombreEmpresa;
    private String direccion;
    private String nit;
    private String nroAutorizacion;
    private String Detalle;
    private String nota;
    private String fechaEmision;
    private String clave;
    private int nroFactura;
    private int nroPedido;
    private String fecha;
    public ConfigFactura(){
        this.nombreEmpresa="";
        this.direccion="";
        this.nit="";
        this.nroAutorizacion="";
        this.Detalle="";
        this.nota="";
        this.fechaEmision="";
        this.clave="";
        this.nroFactura=199;
        this.nroPedido=1;
        this.fecha="";
    }
    
    public void guardar(ConfigFactura l1){
        try{
            ObjectOutputStream o1=new ObjectOutputStream(new FileOutputStream("factura.dat"));
            o1.writeObject(l1);
            o1.close();
        }catch(Exception e){
            System.out.println("Error: "+e);
        }
    }
    
    public ConfigFactura recuperar() throws IOException, ClassNotFoundException{
        ObjectInputStream o1=new ObjectInputStream(new FileInputStream("factura.dat"));
        ConfigFactura l1=(ConfigFactura) o1.readObject();
        o1.close();
        return l1;
    }

    public int getNroFactura() {
        return nroFactura;
    }

    public void setNroFactura(int nroFactura) {
        this.nroFactura = nroFactura;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getNroAutorizacion() {
        return nroAutorizacion;
    }

    public void setNroAutorizacion(String nroAutorizacion) {
        this.nroAutorizacion = nroAutorizacion;
    }

    public String getDetalle() {
        return Detalle;
    }

    public void setDetalle(String Detalle) {
        this.Detalle = Detalle;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public String getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(String fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public int getNroPedido() {
        return nroPedido;
    }

    public void setNroPedido(int nroPedido) {
        this.nroPedido = nroPedido;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
}
