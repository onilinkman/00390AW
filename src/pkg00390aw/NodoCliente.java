
package pkg00390aw;

import java.io.Serializable;

public class NodoCliente implements Serializable{
    private Cliente cliente;
    private NodoCliente sig;
    public NodoCliente(){
        this.cliente=null;
        this.sig=null;
    }
    
    public void reemplazar(NodoCliente s){
        
        this.cliente=s.getCliente();
        this.sig=s.getSig();
        
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }


    public NodoCliente getSig() {
        return sig;
    }

    public void setSig(NodoCliente sig) {
        this.sig = sig;
    }
    
    
}
