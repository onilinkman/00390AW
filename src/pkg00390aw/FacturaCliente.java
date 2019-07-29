
package pkg00390aw;

import java.io.Serializable;

public class FacturaCliente implements Serializable{
    private String fecha;
    private int nroFactura;
    private String nroAutorizacion;
    private String estado;
    private String ci;
    private String nombre;
    private double totalImporte;
    private String codigoControl;
    private byte[] imagenFactura;
    public FacturaCliente(){
        this.fecha="";
        this.nroFactura=0;
        this.nroAutorizacion="";
        this.estado="";
        this.ci="";
        this.nombre="";
        this.totalImporte=0.0;
        this.codigoControl="";
        imagenFactura=null;
    }

    public byte[] getImagenFactura() {
        return imagenFactura;
    }

    public void setImagenFactura(byte[] imagenFactura) {
        this.imagenFactura = imagenFactura;
    }
    
    

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getNroFactura() {
        return nroFactura;
    }

    public void setNroFactura(int nroFactura) {
        this.nroFactura = nroFactura;
    }

    public String getNroAutorizacion() {
        return nroAutorizacion;
    }

    public void setNroAutorizacion(String nroAutorizacion) {
        this.nroAutorizacion = nroAutorizacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getTotalImporte() {
        return totalImporte;
    }

    public void setTotalImporte(double totalImporte) {
        this.totalImporte = totalImporte;
    }

    public String getCodigoControl() {
        return codigoControl;
    }

    public void setCodigoControl(String codigoControl) {
        this.codigoControl = codigoControl;
    }
    
    
}
