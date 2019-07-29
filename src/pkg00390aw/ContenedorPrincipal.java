
package pkg00390aw;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class ContenedorPrincipal implements Serializable{
    private ListaGastos lg=new ListaGastos();
    private ListaPersonal lp=new ListaPersonal();
    private ListaClientes lc=new ListaClientes();
    private Vconfig v=new Vconfig();
    private ListaRegistroCliente lrc=new ListaRegistroCliente();
    
    public ContenedorPrincipal(){
    }
    
    
    
    public void agregarFinalPersonal(NodoPersonal p){
        this.lp.agregarFinal(p);
    }
    
    public void agregarFinalCliente(NodoCliente p){
        this.lc.agregarFinal(p);
    }
    
    public void agregarFinalGastos(NodoGastos p){
        this.lg.agregarFinal(p);
    }
    
    public void guardar(ListaGastos m){
        try{
            ObjectOutput o1=new ObjectOutputStream(new FileOutputStream("DatosGastos.dat"));
            o1.writeObject(m);
            o1.close();
        }catch(IOException e){
            System.out.println("ERROR: "+e);
        }
    }
    
    public void guardar(ListaRegistroCliente m){
        try{
            ObjectOutput o1=new ObjectOutputStream(new FileOutputStream("LrcData.dat"));
            o1.writeObject(m);
            o1.close();
        }catch(IOException e){
            System.out.println("ERROR: "+e);
        }
    }
    
    public void guardar(ListaClientes m){
        try{
            ObjectOutput o1=new ObjectOutputStream(new FileOutputStream("DatosVentas.dat"));
            o1.writeObject(m);
            o1.close();
        }catch(IOException e){
            System.out.println("ERROR: "+e);
        }
    }
    
    public void guardar(Vconfig m){
        try{
            ObjectOutput o1=new ObjectOutputStream(new FileOutputStream("Vconfig.dat"));
            o1.writeObject(m);
            o1.close();
        }catch(IOException e){
            System.out.println("ERROR: "+e);
        }
    }
    
    public void guardar(){
        try{
            ObjectOutput o1=new ObjectOutputStream(new FileOutputStream("DatosPersonal.dat"));
            o1.writeObject(this.lp);
            o1.close();
        }catch(IOException e){
            System.out.println("ERROR: "+e);
        }
    }
    
    public Vconfig recuperarVconfig()throws IOException,ClassNotFoundException{
        ObjectInputStream o1=new ObjectInputStream(new FileInputStream("Vconfig.dat"));
        Vconfig l=(Vconfig)o1.readObject();
        o1.close();
        return l;
    }
    
    public ListaRegistroCliente recuperarRegistroCliente()throws IOException,ClassNotFoundException{
        ObjectInputStream o1=new ObjectInputStream(new FileInputStream("LrcData.dat"));
        ListaRegistroCliente l=(ListaRegistroCliente)o1.readObject();
        o1.close();
        return l;
    }
    
    public ListaGastos recuperar()throws IOException,ClassNotFoundException{
        ObjectInputStream o1=new ObjectInputStream(new FileInputStream("DatosGastos.dat"));
        ListaGastos l=(ListaGastos)o1.readObject();
        o1.close();
        return l;
    }
    
    public ListaClientes recuperarClientes()throws IOException,ClassNotFoundException{
        ObjectInputStream o1=new ObjectInputStream(new FileInputStream("DatosVentas.dat"));
        ListaClientes l=(ListaClientes)o1.readObject();
        o1.close();
        return l;
    }
    
    public ListaPersonal recuperarPersonal()throws IOException,ClassNotFoundException{
        ObjectInputStream o1=new ObjectInputStream(new FileInputStream("DatosPersonal.dat"));
        ListaPersonal l=(ListaPersonal)o1.readObject();
        o1.close();
        return l;
    }
    
    

    public ListaGastos getLg() {
        return lg;
    }

    public void setLg(ListaGastos lg) {
        this.lg = lg;
    }

    public ListaPersonal getLp() {
        return lp;
    }

    public void setLp(ListaPersonal lp) {
        this.lp = lp;
    }

    public ListaClientes getLc() {
        return lc;
    }

    public void setLc(ListaClientes lc) {
        this.lc = lc;
    }

    public Vconfig getV() {
        return v;
    }

    public void setV(Vconfig v) {
        this.v = v;
    }

    public ListaRegistroCliente getLrc() {
        return lrc;
    }

    public void setLrc(ListaRegistroCliente lrc) {
        this.lrc = lrc;
    }
    
}
