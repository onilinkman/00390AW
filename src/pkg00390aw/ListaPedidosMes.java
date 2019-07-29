
package pkg00390aw;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.Queue;

public class ListaPedidosMes implements Serializable{
    private Queue<ListaPedidos> lp;
    public ListaPedidosMes(){
        lp=new LinkedList();
        
    }
    
    public Pedidos[] removerPedidos(int nroPedido,String fecha){
        Queue<ListaPedidos> aux=new LinkedList();
        Pedidos[] pep = null;
        while(!lp.isEmpty()){
            ListaPedidos l=lp.remove();
            if(l.getFecha().equals(fecha)){
                pep=l.removerPedido(nroPedido);
            }
            aux.add(l);
        }
        this.lp.addAll(aux);
        return pep;
    }
    
    public void addEnLaFecha(Pedidos p,String fecha){
        Queue<ListaPedidos> aux=new LinkedList();
        boolean sw=true;
        while(!lp.isEmpty()){
            ListaPedidos l=lp.remove();
            if(l.getFecha().equals(fecha)){
                sw=false;
                l.getP().add(p);
            }
            aux.add(l);
        }
        lp.addAll(aux);
        if(sw){
            ListaPedidos l=new ListaPedidos();
            l.getP().add(p);
            l.setFecha(fecha);
            this.lp.add(l);
        }
    }
    
    public void guardar(ListaPedidosMes l1){
        try{
            ObjectOutputStream o1=new ObjectOutputStream(new FileOutputStream("ListaPedidosMes.dat"));
            o1.writeObject(l1);
            o1.close();
        }catch(Exception e){
            System.out.println("Error: "+e);
        }
    }
    
    public ListaPedidosMes recuperar() throws IOException, ClassNotFoundException{
        ObjectInputStream o1=new ObjectInputStream(new FileInputStream("ListaPedidosMes.dat"));
        ListaPedidosMes l1=(ListaPedidosMes) o1.readObject();
        o1.close();
        return l1;
    }
    
    public String fecha() {
        String f = "";
        int dia, mes, anio;
        Calendar calendario = Calendar.getInstance();
        dia = calendario.get(Calendar.DAY_OF_MONTH);
        mes = calendario.get(Calendar.MONTH) + 1;
        anio = calendario.get(Calendar.YEAR);
        f = dia + "/" + mes + "/" + anio;
        return f;
    }

    public Queue<ListaPedidos> getLp() {
        return lp;
    }

    public void setLp(Queue<ListaPedidos> lp) {
        this.lp = lp;
    }
}
