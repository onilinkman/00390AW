
package pkg00390aw;

import java.io.Serializable;

public class NodoGastos implements Serializable{
    private Gastos gastos;
    private NodoGastos sig;
    public NodoGastos(){
        this.gastos=null;
        this.sig=null;
    }

    public Gastos getGastos() {
        return gastos;
    }

    public void setGastos(Gastos gastos) {
        this.gastos = gastos;
    }

    public NodoGastos getSig() {
        return sig;
    }

    public void setSig(NodoGastos sig) {
        this.sig = sig;
    }
    
}
