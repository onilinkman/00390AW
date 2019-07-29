
package pkg00390aw;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;

public class ListaStockDatos implements Serializable{
    public Queue<StockDatos> p;
    public ListaStockDatos(){
        p=new LinkedList();
    }
    
    public void guardar(ListaStockDatos l1){
        try{
            ObjectOutputStream o1=new ObjectOutputStream(new FileOutputStream("ListaStockDatos.dat"));
            o1.writeObject(l1);
            o1.close();
        }catch(Exception e){
            System.out.println("Error: "+e);
        }
    }
    
    public ListaStockDatos recuperar() throws IOException, ClassNotFoundException{
        ObjectInputStream o1=new ObjectInputStream(new FileInputStream("ListaStockDatos.dat"));
        ListaStockDatos l1=(ListaStockDatos) o1.readObject();
        o1.close();
        return l1;
    }

    public Queue<StockDatos> getP() {
        return p;
    }

    public void setP(Queue<StockDatos> p) {
        this.p = p;
    }
    
}
