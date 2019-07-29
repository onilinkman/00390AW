
package pkg00390aw;

import java.io.Serializable;


public class NodoRegistroCliente implements Serializable{
    private RegistroCliente registro;
    private NodoRegistroCliente sig;
    public NodoRegistroCliente(){
        this.sig=null;
    }
    
    public void reemplazar(NodoRegistroCliente s){
        this.registro=s.getRegistro();
        this.sig=s.getSig();
    }

    public RegistroCliente getRegistro() {
        return registro;
    }

    public void setRegistro(RegistroCliente registro) {
        this.registro = registro;
    }

    public NodoRegistroCliente getSig() {
        return sig;
    }

    public void setSig(NodoRegistroCliente sig) {
        this.sig = sig;
    }
    
}
