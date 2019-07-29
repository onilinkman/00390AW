
package pkg00390aw;

import java.io.Serializable;
import java.util.Calendar;

public class ControlPersonal implements Serializable{
    private String fecha;
    private double adelanto;
    private boolean permiso;
    private int horaEntrada;
    private int minutoEntrada;
    private int horaSalida;
    private int minutoSalida;
    private double sueldo;
    private boolean falta;
    private double descuento;
    private double pagoExtra;
    
    public ControlPersonal(){
        this.fecha="";
        this.adelanto=0;
        this.permiso=false;
        this.horaEntrada=0;
        this.horaSalida=0;
        this.minutoEntrada=0;
        this.minutoSalida=0;
        this.sueldo=0;
        this.falta=false;
        this.descuento=0;
        this.pagoExtra=0;
    }
    
    public void descuentoPorAtraso(){
        int HEntrada=FramePrincipal.cp.getV().getHoraEntrada();
        int MEntrada=FramePrincipal.cp.getV().getMinutoEntrada();
        int minutosTotalEntrada=HEntrada*60+MEntrada;
        int minutosEntrada=this.horaEntrada*60+this.minutoEntrada;
        int diferencia=minutosEntrada-minutosTotalEntrada;
        System.out.println(diferencia);
        double[] descu=FramePrincipal.cp.getV().getDescuento();
        if (!permiso) {
            if (diferencia >= 0 && diferencia < 15) {
                descuento = descu[0];
                falta=false;
            } else if (diferencia >= 15 && diferencia < 30) {
                descuento = descu[1];
                falta=false;
            } else if (diferencia >= 30 && diferencia < 60) {
                descuento = descu[2];
                falta=false;
            } else if (diferencia >= 60) {
                descuento = descu[3];
                falta=true;
            } else {
                descuento=0;
                falta=false;
            }
        }
        /*
        if(HEntrada==0 && MEntrada==0){
                descuento=descu[3];
                falta=true;
            }
        */
    }

    public boolean isFalta() {
        return falta;
    }

    public void setFalta(boolean falta) {
        this.falta = falta;
    }
    
    

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getAdelanto() {
        return adelanto;
    }

    public void setAdelanto(double adelanto) {
        this.adelanto = adelanto;
    }

    public boolean isPermiso() {
        return permiso;
    }

    public void setPermiso(boolean permiso) {
        this.permiso = permiso;
    }

    public int getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(int horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public int getMinutoEntrada() {
        return minutoEntrada;
    }

    public void setMinutoEntrada(int minutoEntrada) {
        this.minutoEntrada = minutoEntrada;
    }

    public int getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(int horaSalida) {
        this.horaSalida = horaSalida;
    }

    public int getMinutoSalida() {
        return minutoSalida;
    }

    public void setMinutoSalida(int minutoSalida) {
        this.minutoSalida = minutoSalida;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public double getPagoExtra() {
        return pagoExtra;
    }

    public void setPagoExtra(double pagoExtra) {
        this.pagoExtra = pagoExtra;
    }
    
}
