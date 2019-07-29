
package pkg00390aw;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;

public class ListaReservas implements Serializable{
    private Queue<Reservas> reservas;
    
    public ListaReservas(){
        this.reservas=new LinkedList();
    }
    
    public void eliminarReserva(int hora,int minuto,String fecha){
        Queue<Reservas> aux=new LinkedList();
        while(!reservas.isEmpty()){
            Reservas r=reservas.remove();
            if((r.getHora()<=hora)&&(r.getMinuto()<=minuto)&&(r.getFechaReservacion().equals(fecha))){
                
            }else{
                aux.add(r);
            }
        }
        this.reservas.addAll(aux);
    }
    
    public void guardad(ListaReservas l1){
        try{
            ObjectOutputStream o1=new ObjectOutputStream(new FileOutputStream("ListaReservas.dat"));
            o1.writeObject(l1);
            o1.close();
        }catch(Exception e){
            System.out.println("Error: "+e);
        }
    }
    
    public ListaReservas recuperar() throws IOException, ClassNotFoundException{
        ObjectInputStream o1=new ObjectInputStream(new FileInputStream("ListaReservas.dat"));
        ListaReservas l1=(ListaReservas) o1.readObject();
        o1.close();
        return l1;
    }

    public Queue<Reservas> getReservas() {
        return reservas;
    }

    public void setReservas(Queue<Reservas> reservas) {
        this.reservas = reservas;
    }
    
    
}
