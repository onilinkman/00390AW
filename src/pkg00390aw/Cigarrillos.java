
package pkg00390aw;

import java.io.Serializable;

public class Cigarrillos extends Mercancia implements Serializable {

    private Stock stockAlmacen;
    private Stock stockVendido;
    //*****ESTO SERVIRA PARA LOS DETALLES********
    private double brutoUnidad;
    private double unitarioCc;
    //**********************************************

    public Cigarrillos() {
        super();
        this.stockAlmacen = new Stock();
        this.stockVendido = new Stock();
        this.brutoUnidad = 0;
        this.unitarioCc = 0;
    }
    //NO ES VOLUMEN TOTAL ES LA CANTIDAD DE CIGARRILLOS
    public void aumentarStockVendido(int volumenTotal){
        this.stockVendido.setStockCajasCigarro(stockCajasCigarrillos(volumenTotal));
        this.stockVendido.setStockUnidadesCigarros(this.stockUnidadesCigarrillos(volumenTotal));
        
        this.stockVendido.setStockTotalCigarro(volumenTotal);
    }

    public void aumentarStockAlmacen(int volumenTotal) {
        this.stockAlmacen.setStockCajasCigarro(stockCajasCigarrillos(volumenTotal));
        this.stockAlmacen.setStockUnidadesCigarros(this.stockUnidadesCigarrillos(volumenTotal));
        
        this.stockAlmacen.setStockTotalCigarro(volumenTotal);
    }

    public int stockCajasCigarrillos(int totalCigarros) {
        int cajas = 0;
            cajas = (int) (totalCigarros / this.getCantidad());
        return cajas;
    }

    public int stockUnidadesCigarrillos(int totalCigarros) {
        int cajasInt = 0;
        int cajas = 0;
        int unidades = 0;
        cajasInt = (int) (totalCigarros / this.getCantidad());
        cajas = totalCigarros - this.getCantidad()*cajasInt;
        double res = cajas - cajasInt;
        unidades = (int) (res * this.getCantidad());
        return cajas;
    }

    public double getBrutoUnidad() {
        return brutoUnidad;
    }

    public void setBrutoUnidad(double brutoUnidad) {
        this.brutoUnidad = brutoUnidad;
    }

    public double getUnitarioCc() {
        return unitarioCc;
    }

    public void setUnitarioCc(double unitarioCc) {
        this.unitarioCc = unitarioCc;
    }

    public Stock getStockAlmacen() {
        return stockAlmacen;
    }

    public void setStockAlmacen(Stock stockAlmacen) {
        this.stockAlmacen = stockAlmacen;
    }

    public Stock getStockVendido() {
        return stockVendido;
    }

    public void setStockVendido(Stock stockVendido) {
        this.stockVendido = stockVendido;
    }
}
