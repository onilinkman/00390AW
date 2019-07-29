
package pkg00390aw;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;

public class ListaFacturaProveedor implements Serializable{
    Queue<FacturaProveedor> p;
    public ListaFacturaProveedor(){
        p=new LinkedList();
    }
    public void guardar(ListaFacturaProveedor l1){
        try{
            ObjectOutputStream o1=new ObjectOutputStream(new FileOutputStream("facturaProveedores.dat"));
            o1.writeObject(l1);
            o1.close();
        }catch(Exception e){
            System.out.println("Error: "+e);
        }
    }
    
    public ListaFacturaProveedor recuperar() throws IOException, ClassNotFoundException{
        ObjectInputStream o1=new ObjectInputStream(new FileInputStream("facturaProveedores.dat"));
        ListaFacturaProveedor l1=(ListaFacturaProveedor) o1.readObject();
        o1.close();
        return l1;
    }
}
