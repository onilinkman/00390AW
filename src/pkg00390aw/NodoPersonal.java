
package pkg00390aw;

import java.io.Serializable;

public class NodoPersonal implements Serializable{
    private Personal personal;
    private NodoPersonal sig;
    public NodoPersonal(){
        this.sig=null;
        
    }
    
    public void reemplazar(NodoPersonal s){
        
        this.personal=s.getPersonal();
        this.sig=s.getSig();
        
    }

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }

    public NodoPersonal getSig() {
        return sig;
    }

    public void setSig(NodoPersonal sig) {
        this.sig = sig;
    }
    
}
