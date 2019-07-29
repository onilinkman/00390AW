
package pkg00390aw;

import java.io.Serializable;


public class Reservas implements Serializable{
    private String nombre;
    private int nroPersonas;
    private String fechaActual;
    private String fechaReservacion;
    private int hora;
    private int minuto;
    private String lugar;
    
    public Reservas(){
        this.nombre="";
        this.nroPersonas=0;
        this.fechaActual="";
        fechaReservacion="";
        this.hora=0;
        this.minuto=0;
        this.lugar="";
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNroPersonas() {
        return nroPersonas;
    }

    public void setNroPersonas(int nroPersonas) {
        this.nroPersonas = nroPersonas;
    }

    public String getFechaActual() {
        return fechaActual;
    }

    public void setFechaActual(String fechaActual) {
        this.fechaActual = fechaActual;
    }

    public String getFechaReservacion() {
        return fechaReservacion;
    }

    public void setFechaReservacion(String fechaReservacion) {
        this.fechaReservacion = fechaReservacion;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public int getMinuto() {
        return minuto;
    }

    public void setMinuto(int minuto) {
        this.minuto = minuto;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }
    
    
}
