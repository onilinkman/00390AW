
package pkg00390aw;

import java.io.Serializable;


public class ListaRegistroCliente implements Serializable{
    private NodoRegistroCliente p;
    public void ListaRegistroCliente(){
        this.p=null;
    }
    
    public void editar(String nom,RegistroCliente x){
        NodoRegistroCliente w=this.p;
        while(w!=null){
            if(w.getRegistro().getNombre().equals(nom)){
                w.setRegistro(x);
                break;
            }
            w=w.getSig();
        }
    }
    
    public void eliminar(String nom){
        NodoRegistroCliente w=this.p;
        if(w.getSig()!=null){
            while(w.getSig()!=null){
                if(w.getRegistro().getNombre().equals(nom)){
                    w.reemplazar(w.getSig());
                    break;
                }
                if(w.getSig().getSig()==null){
                    if(w.getSig().getRegistro().getNombre().equals(nom)){
                        w.setSig(null);
                        break;
                    }
                }
                w=w.getSig();
            }
            
        }else{
            if(this.p.getRegistro().getNombre().equals(nom)){
                this.p=null;
            }
        }
    }
    
    public void adiFinal(NodoRegistroCliente s){
        NodoRegistroCliente nc=this.p;
        if(this.p!=null){
            while(nc.getSig()!=null){
                nc=nc.getSig();
            }
            nc.setSig(s);
        }else{
            this.p=s;
        }
    }

    public NodoRegistroCliente getP() {
        return p;
    }

    public void setP(NodoRegistroCliente p) {
        this.p = p;
    }
    
}
