/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg00390aw;

import java.io.Serializable;

/**
 *
 * @author WINDOWS 10
 */
public class Otros implements Serializable {

    private String nomProducto;
    private byte[] foto;
    private Stock stockAlmacen;
    private Stock stockVendido;

    public Otros() {
        this.nomProducto = "";
        this.stockAlmacen=new Stock();
        this.stockVendido=new Stock();
        this.foto=null;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
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
    

    public String getNomProducto() {
        return nomProducto;
    }

    public void setNomProducto(String nomProducto) {
        this.nomProducto = nomProducto;
    }

}
