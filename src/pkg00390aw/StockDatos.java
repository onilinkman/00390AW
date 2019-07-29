
package pkg00390aw;

import java.io.Serializable;

public class StockDatos implements Serializable{
    private String nombreProducto;
    private String proveedor;
    private int cantidadCajas;
    private int cantidadUnidades;
    private int cantidadTotal;
    private double preciosCaja;
    private double precioBruto;
    private double precioTotal;
    private double precioUnitario;
    private String fecha;
    private int stockCajas;
    private int stockUnidades;
    private double stockVolumen;
    private double stockVolTotal;
    private double stockTotalCigarro;
    private int stockCajasCigarro;
    private int stockUnidadesCigarros;
    private String factura;
    public StockDatos(){
        this.nombreProducto="";
        this.proveedor="";
        this.cantidadCajas=0;
        this.cantidadUnidades=0;
        this.cantidadTotal=0;
        this.precioTotal=0;
        this.preciosCaja=0;
        this.precioBruto=0;
        this.precioUnitario=0;
        this.fecha="";
        this.stockCajas=0;
        this.stockUnidades=0;
        this.stockVolumen=0;
        this.stockVolTotal=0;
        this.stockTotalCigarro=0;
        this.stockCajasCigarro=0;
        this.stockUnidadesCigarros=0;
        this.factura="";
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public int getCantidadCajas() {
        return cantidadCajas;
    }

    public void setCantidadCajas(int cantidadCajas) {
        this.cantidadCajas = cantidadCajas;
    }

    public int getCantidadUnidades() {
        return cantidadUnidades;
    }

    public void setCantidadUnidades(int cantidadUnidades) {
        this.cantidadUnidades = cantidadUnidades;
    }

    public int getCantidadTotal() {
        return cantidadTotal;
    }

    public void setCantidadTotal(int cantidadTotal) {
        this.cantidadTotal = cantidadTotal;
    }

    public double getPreciosCaja() {
        return preciosCaja;
    }

    public void setPreciosCaja(double preciosCaja) {
        this.preciosCaja = preciosCaja;
    }

    public double getPrecioBruto() {
        return precioBruto;
    }

    public void setPrecioBruto(double precioBruto) {
        this.precioBruto = precioBruto;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getStockCajas() {
        return stockCajas;
    }

    public void setStockCajas(int stockCajas) {
        this.stockCajas = stockCajas;
    }

    public int getStockUnidades() {
        return stockUnidades;
    }

    public void setStockUnidades(int stockUnidades) {
        this.stockUnidades = stockUnidades;
    }

    public double getStockVolumen() {
        return stockVolumen;
    }

    public void setStockVolumen(double stockVolumen) {
        this.stockVolumen = stockVolumen;
    }

    public double getStockVolTotal() {
        return stockVolTotal;
    }

    public void setStockVolTotal(double stockVolTotal) {
        this.stockVolTotal = stockVolTotal;
    }

    public double getStockTotalCigarro() {
        return stockTotalCigarro;
    }

    public void setStockTotalCigarro(double stockTotalCigarro) {
        this.stockTotalCigarro = stockTotalCigarro;
    }

    public int getStockCajasCigarro() {
        return stockCajasCigarro;
    }

    public void setStockCajasCigarro(int stockCajasCigarro) {
        this.stockCajasCigarro = stockCajasCigarro;
    }

    public int getStockUnidadesCigarros() {
        return stockUnidadesCigarros;
    }

    public void setStockUnidadesCigarros(int stockUnidadesCigarros) {
        this.stockUnidadesCigarros = stockUnidadesCigarros;
    }

    public String getFactura() {
        return factura;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public void setFactura(String factura) {
        this.factura = factura;
    }
    
}
