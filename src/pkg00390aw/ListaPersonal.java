package pkg00390aw;

import java.io.Serializable;

public class ListaPersonal implements Serializable {

    private NodoPersonal p;

    public ListaPersonal() {
        this.p = null;
    }

    public void editar(String nom, Personal per) {
        NodoPersonal w = this.p;
        while (w != null) {
            if (nom.equals(w.getPersonal().getNombre())) {
                w.setPersonal(per);
            }
            w = w.getSig();
        }
    }

    public void AgregarFecha(String fecha) {
        NodoPersonal w = this.p;
        while (w != null) {
            w.getPersonal().getControl().iniciarDia(fecha);
            w = w.getSig();
        }
    }

    public void eliminar(String nom) {
        NodoPersonal w = this.p;
        if (w.getSig() != null) {
            while (w.getSig() != null) {
                if (w.getPersonal().getNombre().equals(nom)) {
                    w.reemplazar(w.getSig());
                    break;
                }
                if (w.getSig().getSig() == null) {
                    if (w.getSig().getPersonal().getNombre().equals(nom)) {
                        w.setSig(null);
                        break;
                    }
                }
                w = w.getSig();
            }
        } else if (this.p.getPersonal().getNombre().equals(nom)) {
            this.p = null;
        }
    }

    public void agregarFinal(NodoPersonal w) {
        NodoPersonal aux = this.p;
        if (aux != null) {
            while (aux.getSig() != null) {
                aux = aux.getSig();
            }
            aux.setSig(w);
        } else {

            this.p = w;
        }
    }

    public NodoPersonal getP() {
        return p;
    }

    public void setP(NodoPersonal p) {
        this.p = p;
    }

}
