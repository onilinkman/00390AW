
package pkg00390aw;

import java.io.Serializable;
import java.text.DecimalFormat;

public class Cerveza extends Mercancia implements Serializable {

    private double Volumen;//este dato es del producto
    private Stock stockAlmacen;
    private Stock stockVendido;

    //*****ESTO SERVIRA PARA LOS DETALLES********
    private double brutoUnidad;
    private double unitarioCc;
    //**********************************************

    public Cerveza() {
        super();
        this.Volumen = 0.0;
        stockAlmacen = new Stock();
        stockVendido = new Stock();
        brutoUnidad = 0;
        unitarioCc = 0;
    }
    
    public double convertirUnidadesAVolumen(int cantidad){
        double volumenTotal=this.Volumen*cantidad;
        
        return volumenTotal;
    }
    
    public void aumentarStockVendido(double volumenTotal){
        this.stockVendido.setStockCajas(stockCajas(volumenTotal));
        this.stockVendido.setStockUnidades(this.stockUnidades(volumenTotal));
        this.stockVendido.setStockVolumen(this.stockVolumen(volumenTotal));
        this.stockVendido.setStockVolTotal(volumenTotal);
    }

    public void aumentarStockAlmacen(double volumenTotal) {
        this.stockAlmacen.setStockCajas(stockCajas(volumenTotal));
        this.stockAlmacen.setStockUnidades(this.stockUnidades(volumenTotal));
        this.stockAlmacen.setStockVolumen(this.stockVolumen(volumenTotal));
        this.stockAlmacen.setStockVolTotal(volumenTotal);
    }

    public int stockCajas(double volumenTotal) {
        int cajas = 0;
        Double volUni = this.getVolumen();
        int undCaja = this.getCantidad();
        cajas = (int) (volumenTotal / (volUni * undCaja));

        return cajas;
    }

    public int stockUnidades(double volumenTotal) {
        int unidades = 0;
        double cajas = 0;
        int cajasInt = 0;
        double restoCajas = 0;
        Double volUni = this.getVolumen();
        int undCaja = this.getCantidad();
        cajas = volumenTotal / (volUni * undCaja);
        cajasInt = (int) (volumenTotal / (volUni * undCaja));
        restoCajas = cajas - cajasInt;
        unidades = (int) (restoCajas * undCaja);
        return unidades;
    }

    public double stockVolumen(double volumenTotal) {
        int unidadesInt = 0;
        double unidades = 0;
        double volumen = 0;
        double cajas = 0;
        int cajasInt = 0;
        double restoCajas = 0;
        Double volUni = this.getVolumen();
        int undCaja = this.getCantidad();
        cajas = volumenTotal / (volUni * undCaja);
        cajasInt = (int) (volumenTotal / (volUni * undCaja));
        restoCajas = cajas - cajasInt;
        unidadesInt = (int) (restoCajas * undCaja);
        unidades = restoCajas * undCaja;
        double restoUnidades = unidades - unidadesInt;
        volumen = restoUnidades * volUni;
        DecimalFormat df=new DecimalFormat("#.00");
        return Double.valueOf(df.format(volumen));
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

    public double getVolumen() {
        return Volumen;
    }

    public void setVolumen(double Volumen) {
        this.Volumen = Volumen;
    }
}
