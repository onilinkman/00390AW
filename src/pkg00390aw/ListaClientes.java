
package pkg00390aw;

import java.io.Serializable;

public class ListaClientes implements Serializable{
    private NodoCliente p;
    public ListaClientes(){
        p=null;
    }
    
    public void editar(String cod, Cliente nc){
        NodoCliente w=this.p;
        while(w!=null){
            if(w.getCliente().getCodigoCliente().equals(cod)){
                w.setCliente(nc);
            }
            w=w.getSig();
        }
    }
    
    public void agregarFinal(NodoCliente w){
        NodoCliente aux=this.p;
        if (aux != null) {
            while (aux.getSig()!= null) {
                aux = aux.getSig();
            }
            aux.setSig(w);
        }else{
            this.p=w;
        }
    }
    
    public void eliminar(String nom){
        NodoCliente w=this.p;
        if(w.getSig()!=null){
            while(w.getSig()!=null){
                if(w.getCliente().getCodigoCliente().equals(nom)){
                    w.reemplazar(w.getSig());
                    break;
                }
                if(w.getSig().getSig()==null){
                    if(w.getSig().getCliente().getCodigoCliente().equals(nom)){
                        w.setSig(null);
                        break;
                    }
                }
                w=w.getSig();
            }
            
        }else{
            if(this.p.getCliente().getCodigoCliente().equals(nom)){
                this.p=null;
            }
        }
    }

    public void entregado(String codigo ){
        NodoCliente aux=this.p;
        while(aux!=null){
            if(codigo.equals(aux.getCliente().getCodigoCliente())){
                aux.getCliente().setEntregado(true);
            }
            aux=aux.getSig();
        }
    }
    
    public NodoCliente getP() {
        return p;
    }

    public void setP(NodoCliente p) {
        this.p = p;
    }
    
}
