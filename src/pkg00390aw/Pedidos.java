
package pkg00390aw;

import java.io.Serializable;

public class Pedidos implements Serializable{
    private String producto;
    private int cantidad;
    private double precioUnitario;
    private double precioParcial;
    private String lugar;
    private String garzon;
    private String administrador;
    private String productos;
    private String fecha;
    private String hora;
    private String factura;
    private int nroPedidos;
    
    public Pedidos(){
        this.producto="";
        this.cantidad=0;
        this.precioUnitario=0.0;
        this.precioParcial=0.0;
        this.lugar="";
        this.garzon="";
        this.administrador="";
        this.productos="";
        this.fecha="";
        this.hora="";
        this.factura="";
        this.nroPedidos=0;
    }

    public int getNroPedidos() {
        return nroPedidos;
    }

    public void setNroPedidos(int nroPedidos) {
        this.nroPedidos = nroPedidos;
    }

    public String getAdministrador() {
        return administrador;
    }

    public void setAdministrador(String administrador) {
        this.administrador = administrador;
    }

    public String getProductos() {
        return productos;
    }

    public void setProductos(String productos) {
        this.productos = productos;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getFactura() {
        return factura;
    }

    public void setFactura(String factura) {
        this.factura = factura;
    }
    
    

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public double getPrecioParcial() {
        return precioParcial;
    }

    public void setPrecioParcial(double precioParcial) {
        this.precioParcial = precioParcial;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getGarzon() {
        return garzon;
    }

    public void setGarzon(String garzon) {
        this.garzon = garzon;
    }
    
}
