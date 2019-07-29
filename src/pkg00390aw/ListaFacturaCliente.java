
package pkg00390aw;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;


public class ListaFacturaCliente implements Serializable{
    private Queue<FacturaCliente> p;
    public ListaFacturaCliente(){
        this.p=new LinkedList();
    }
    
    public void guardar(ListaFacturaCliente l1){
        try{
            ObjectOutputStream o1=new ObjectOutputStream(new FileOutputStream("FacturaCliente.dat"));
            o1.writeObject(l1);
            o1.close();
        }catch(Exception e){
            System.out.println("Error: "+e);
        }
    }
    
    public ListaFacturaCliente recuperar() throws IOException, ClassNotFoundException{
        ObjectInputStream o1=new ObjectInputStream(new FileInputStream("FacturaCliente.dat"));
        ListaFacturaCliente l1=(ListaFacturaCliente) o1.readObject();
        o1.close();
        return l1;
    }

    public Queue<FacturaCliente> getP() {
        return p;
    }

    public void setP(Queue<FacturaCliente> p) {
        this.p = p;
    }
    
}
