package pkg00390aw;

import java.io.Serializable;
import java.util.Calendar;

public class ListaControlPersonal implements Serializable {

    private NodoControlPersonal p;

    public ListaControlPersonal() {
        this.p = null;
    }

    public void ingresarPermisoYAdelanto(String fecha, double adelanto, boolean permiso) {
        NodoControlPersonal aux = this.p;
        while (aux != null) {
            if (aux.getCp().getFecha().equals(fecha)) {
                aux.getCp().setAdelanto(adelanto);
                aux.getCp().setPermiso(permiso);
                //aux.getCp().setFalta(!permiso);
            }
            aux = aux.getSig();
        }
    }
    
    public void iniciarDia(String fecha){
        NodoControlPersonal w=this.p;
        boolean sw=true;
        while(w!=null){
            if(w.getCp().getFecha().equals(fecha)){
                sw=false;
            }
            w=w.getSig();
        }
        if(sw){
            NodoControlPersonal a = new NodoControlPersonal();
            ControlPersonal c = new ControlPersonal();
            c.setFecha(fecha);
            c.descuentoPorAtraso();//posible borrar
            c.setFalta(true);
            double[] descu=FramePrincipal.cp.getV().getDescuento();
            c.setDescuento(descu[3]);
            a.setCp(c);
            agregarFinal(a);
        }
    }

    public void modificar(String fecha) {
        NodoControlPersonal w = this.p;
        boolean sw = true;
        while (w != null) {
            if (w.getCp().getFecha().equals(fecha)) {
                ControlPersonal c = w.getCp();
                if (w.getCp().getHoraEntrada() == 0 && w.getCp().getMinutoEntrada() == 0) {
                    //c.setFecha(fecha);
                    c.setHoraEntrada(HoraEntrada());
                    c.setMinutoEntrada(MinutoEntrada());
                    c.descuentoPorAtraso();
                } else {
                    c.setHoraSalida(HoraSalida());
                    c.setMinutoSalida(MinutoSalida());

                }
                sw = false;
                w.setCp(c);
                break;
            }
            w = w.getSig();
        }
        if (sw) {
            NodoControlPersonal a = new NodoControlPersonal();
            ControlPersonal c = new ControlPersonal();
            c.setFecha(fecha);
            c.setHoraEntrada(HoraEntrada());
            c.setMinutoEntrada(MinutoEntrada());
            c.descuentoPorAtraso();
            a.setCp(c);
            agregarFinal(a);
        }
    }

    public int HoraEntrada() {
        Calendar calendario = Calendar.getInstance();
        return calendario.get(Calendar.HOUR_OF_DAY);
    }

    public int HoraSalida() {
        Calendar calendario = Calendar.getInstance();
        return calendario.get(Calendar.HOUR_OF_DAY);
    }

    public int MinutoEntrada() {
        Calendar calendario = Calendar.getInstance();
        return calendario.get(Calendar.MINUTE);
    }

    public int MinutoSalida() {
        Calendar calendario = Calendar.getInstance();
        return calendario.get(Calendar.MINUTE);
    }

    public boolean yaVino(String fecha) {
        NodoControlPersonal w = this.p;
        while (w != null) {
            if (w.getCp().getFecha().equals(fecha)) {
                return true;
            }
            w = w.getSig();
        }
        return false;
    }

    public void agregarFinal(NodoControlPersonal s) {
        NodoControlPersonal nc = this.p;
        if (this.p != null) {
            while (nc.getSig() != null) {
                nc = nc.getSig();
            }
            nc.setSig(s);
        } else {
            this.p = s;
        }
    }

    public NodoControlPersonal getP() {
        return p;
    }

    public void setP(NodoControlPersonal p) {
        this.p = p;
    }

}
