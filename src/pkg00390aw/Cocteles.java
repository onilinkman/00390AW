package pkg00390aw;

import java.io.Serializable;

/**
 *
 * @author WINDOWS 10
 */
public class Cocteles extends Mercancia implements Serializable {

    private double Volumen;

    private String bebida1;
    private String bebida2;
    private String bebida3;
    private String bebida4;
    private double volumen1;
    private double volumen2;
    private double volumen3;
    private double volumen4;
    private Stock stockAlmacen;
    private Stock stockVendido;

    public Cocteles() {
        super();
        this.Volumen = 0.0;
        this.bebida1 = "";
        this.bebida2 = "";
        this.bebida3 = "";
        this.bebida4 = "";
        this.volumen1 = 0.0;
        this.volumen2 = 0.0;
        this.volumen3 = 0.0;
        this.volumen4 = 0.0;
        this.stockAlmacen = new Stock();
        this.stockVendido = new Stock();
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

    public String getBebida1() {
        return bebida1;
    }

    public void setBebida1(String bebida1) {
        this.bebida1 = bebida1;
    }

    public String getBebida2() {
        return bebida2;
    }

    public void setBebida2(String bebida2) {
        this.bebida2 = bebida2;
    }

    public String getBebida3() {
        return bebida3;
    }

    public void setBebida3(String bebida3) {
        this.bebida3 = bebida3;
    }

    public String getBebida4() {
        return bebida4;
    }

    public void setBebida4(String bebida4) {
        this.bebida4 = bebida4;
    }

    public double getVolumen1() {
        return volumen1;
    }

    public void setVolumen1(double volumen1) {
        this.volumen1 = volumen1;
    }

    public double getVolumen2() {
        return volumen2;
    }

    public void setVolumen2(double volumen2) {
        this.volumen2 = volumen2;
    }

    public double getVolumen3() {
        return volumen3;
    }

    public void setVolumen3(double volumen3) {
        this.volumen3 = volumen3;
    }

    public double getVolumen4() {
        return volumen4;
    }

    public void setVolumen4(double volumen4) {
        this.volumen4 = volumen4;
    }

}
