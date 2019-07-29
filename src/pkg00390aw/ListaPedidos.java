
package pkg00390aw;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;

public class ListaPedidos implements Serializable{
    private String fecha;
    private double vendido;
    private double activo;
    private Queue<Pedidos> p;
    public ListaPedidos(){
        p=new LinkedList();
        this.fecha="";
        this.vendido=0;
        this.activo=0;
    }
    
    public Pedidos[] removerPedido(int nroPedido){
        Queue<Pedidos> aux=new LinkedList();
        Queue<Pedidos> aux2=new LinkedList();
        int cont=0;
        while(!this.p.isEmpty()){
            Pedidos pe=this.p.remove();
            if(nroPedido==pe.getNroPedidos()){
                aux2.add(pe);
            }else{
                aux.add(pe);
            }
        }
        this.p.addAll(aux);
        Pedidos[] pedidos=new Pedidos[aux2.size()];
        while(!aux2.isEmpty()){
            pedidos[cont]=aux2.remove();
            cont++;
        }
        return pedidos;
    }
    
    public void guardar(ListaPedidos l1){
        try{
            ObjectOutputStream o1=new ObjectOutputStream(new FileOutputStream("ListaPedidos.dat"));
            o1.writeObject(l1);
            o1.close();
        }catch(Exception e){
            System.out.println("Error: "+e);
        }
    }
    
    public ListaPedidos recuperar() throws IOException, ClassNotFoundException{
        ObjectInputStream o1=new ObjectInputStream(new FileInputStream("ListaPedidos.dat"));
        ListaPedidos l1=(ListaPedidos) o1.readObject();
        o1.close();
        return l1;
    }

    public double getVendido() {
        return vendido;
    }

    public void setVendido(double vendido) {
        this.vendido = vendido;
    }

    public double getActivo() {
        return activo;
    }

    public void setActivo(double activo) {
        this.activo = activo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    

    public Queue<Pedidos> getP() {
        return p;
    }

    public void setP(Queue<Pedidos> p) {
        this.p = p;
    }
    
    
}
