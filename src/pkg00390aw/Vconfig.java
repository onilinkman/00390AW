
package pkg00390aw;

import java.io.Serializable;

public class Vconfig implements Serializable{
    private String usuario;
    private String contrasenia;
    private double[] atraso;
    private double[] descuento;
    private int horaEntrada;
    private int horaSalida;
    private int minutoEntrada;
    private int minutoSalida;
    private boolean activo;
    private double PagoExtra;
    public Vconfig(){
        this.usuario="1234";
        this.contrasenia="1234";
        atraso=new double[4];
        descuento=new double[4];
        atraso[0]=15;
        atraso[1]=30;
        atraso[2]=60;
        descuento[0]=0;
        descuento[1]=0;
        descuento[2]=0;
        descuento[3]=0;
        this.horaEntrada=0;
        this.horaSalida=0;
        this.minutoEntrada=0;
        this.minutoSalida=0;
        this.activo=true;
        this.PagoExtra=0;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public double[] getAtraso() {
        return atraso;
    }

    public double getPagoExtra() {
        return PagoExtra;
    }

    public void setPagoExtra(double PagoExtra) {
        this.PagoExtra = PagoExtra;
    }

    public void setAtraso(double[] atraso) {
        this.atraso = atraso;
    }

    public double[] getDescuento() {
        return descuento;
    }

    public void setDescuento(double[] descuento) {
        this.descuento = descuento;
    }

    public int getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(int horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public int getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(int horaSalida) {
        this.horaSalida = horaSalida;
    }

    public int getMinutoEntrada() {
        return minutoEntrada;
    }

    public void setMinutoEntrada(int minutoEntrada) {
        this.minutoEntrada = minutoEntrada;
    }

    public int getMinutoSalida() {
        return minutoSalida;
    }

    public void setMinutoSalida(int minutoSalida) {
        this.minutoSalida = minutoSalida;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
    
}
