
package pkg00390aw;

import java.io.Serializable;

public class FacturaProveedor implements Serializable{

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getBase() {
        return base;
    }

    public void setBase(double base) {
        this.base = base;
    }

    public double getFiscal() {
        return fiscal;
    }

    public void setFiscal(double fiscal) {
        this.fiscal = fiscal;
    }
    private String nombreProveedor;
    private String nit;
    private String nroFactura;
    private String nroAutorizacion;
    private String codigoControl;
    private double totalCompra;
    private double noSujeto;
    private String fecha;
    private double subTotal;
    private double bonificacion;
    private double base;
    private double fiscal;
    public void FacturaProveedor(){
        this.nombreProveedor="";
        this.nit="";
        this.nroAutorizacion="";
        this.nroFactura="";
        this.codigoControl="";
        this.totalCompra=0.0;
        this.noSujeto=0.0;
        this.fecha="";
        this.subTotal=0.0;
        this.bonificacion=0.0;
        this.base=0.0;
        this.fiscal=0.0;
    }
    public void calcular(){
        this.subTotal=this.totalCompra-this.noSujeto;
        this.base=this.subTotal-this.bonificacion;
        this.fiscal=this.base*0.13;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getNroFactura() {
        return nroFactura;
    }

    public void setNroFactura(String nroFactura) {
        this.nroFactura = nroFactura;
    }

    public String getNroAutorizacion() {
        return nroAutorizacion;
    }

    public void setNroAutorizacion(String nroAutorizacion) {
        this.nroAutorizacion = nroAutorizacion;
    }

    public String getCodigoControl() {
        return codigoControl;
    }

    public void setCodigoControl(String codigoControl) {
        this.codigoControl = codigoControl;
    }

    public double getTotalCompra() {
        return totalCompra;
    }

    public void setTotalCompra(double totalCompra) {
        this.totalCompra = totalCompra;
    }

    public double getNoSujeto() {
        return noSujeto;
    }

    public void setNoSujeto(double noSujeto) {
        this.noSujeto = noSujeto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getBonificacion() {
        return bonificacion;
    }

    public void setBonificacion(double bonificacion) {
        this.bonificacion = bonificacion;
    }
    
}
