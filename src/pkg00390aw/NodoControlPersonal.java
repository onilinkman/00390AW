
package pkg00390aw;

import java.io.Serializable;

public class NodoControlPersonal implements Serializable{
    private ControlPersonal cp;
    private NodoControlPersonal sig;
    public NodoControlPersonal(){
        this.sig=null;
    }

    public ControlPersonal getCp() {
        return cp;
    }

    public void setCp(ControlPersonal cp) {
        this.cp = cp;
    }

    public NodoControlPersonal getSig() {
        return sig;
    }

    public void setSig(NodoControlPersonal sig) {
        this.sig = sig;
    }
    
}
