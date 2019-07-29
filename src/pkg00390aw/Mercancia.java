
package pkg00390aw;

import java.awt.image.BufferedImage;
import java.io.Serializable;

public class Mercancia implements Serializable{
    private int Cantidad;
    private double Precio;
    private String NomProducto;
    private byte[] foto;
    private String Fecha;
    private String Tipo;
    
    public Mercancia(){
        this.Cantidad=0;
        this.Precio=0.0;
        this.NomProducto="";
        this.foto=null;
        this.Fecha="";
        this.Tipo="";
    }


    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int Cantidad) {
        this.Cantidad = Cantidad;
    }

    public double getPrecio() {
        return Precio;
    }

    public void setPrecio(double Precio) {
        this.Precio = Precio;
    }

    public String getNomProducto() {
        return NomProducto;
    }

    public void setNomProducto(String NomProducto) {
        this.NomProducto = NomProducto;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

   


    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }
    
    
}
