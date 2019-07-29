
package pkg00390aw;

import java.util.Calendar;

public class Reloj extends Thread{
    private int hora;
    private int minutos;
    private String fecha;
    
    @Override
    public void run(){
        Calendar calendario=Calendar.getInstance();
        this.hora=calendario.get(Calendar.HOUR);
        this.minutos=calendario.get(Calendar.MINUTE);
        
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public int getMinutos() {
        return minutos;
    }

    public void setMinutos(int minutos) {
        this.minutos = minutos;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    
}
