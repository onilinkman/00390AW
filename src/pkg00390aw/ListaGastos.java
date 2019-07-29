
package pkg00390aw;

import java.io.Serializable;

public class ListaGastos implements Serializable{
    private NodoGastos p;
    public ListaGastos(){
        this.p=null;
    }
    
    public void eliminar(int n) {
        int cont = 0;
        NodoGastos aux = this.p;
        if (n == 0) {
            if (this.p.getSig() != null) {
                this.p = this.p.getSig();
            } else {
                this.p = null;
            }
        } else {
            if(n==cantidad()-1){
                while(aux.getSig().getSig()!=null){
                    aux=aux.getSig();
                }
                aux.setSig(null);
            }else{
                for(int i=0;i<n;i++){
                    aux=aux.getSig();
                }
                if(aux.getSig()!=null){
                    aux.setGastos(aux.getSig().getGastos());
                    aux.setSig(aux.getSig().getSig());
                    //aux.reempla(aux.getSig());
                }else{
                    aux=null;
                }
            }
            /*while (aux != null) {
                
                aux = aux.getSig();
            }*/
        }
    }
    public int cantidad(){
        int i=0;
        NodoGastos aux=getP();
        while(aux!=null){
            i++;
            aux=aux.getSig();
        }
        return i;
    }
    
    public void aditar(Gastos w,int i){
        NodoGastos aux=this.p;
        int cont=0;
        while(aux!=null){
            if(cont==i){
                aux.setGastos(w);
            }
            cont++;
            aux=aux.getSig();
        }
    }
    
    public void agregarFinal(NodoGastos w){
        NodoGastos aux=this.p;
        if (aux != null) {
            while (aux.getSig()!= null) {
                aux = aux.getSig();
            }
            aux.setSig(w);
        }else{
        
            
            this.p=w;
        }
    }

    public NodoGastos getP() {
        return p;
    }

    public void setP(NodoGastos p) {
        this.p = p;
    }
    
    
}
