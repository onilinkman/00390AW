
package pkg00390aw;

import java.io.Serializable;

public class Personal implements Serializable{
    private String nombre,direccion,carnet,celular,ocupacion;
    private double sueldo;
    private String numMaquina;
    private String direccionFoto;
    private String direccionHuella;
    private String fechaIngreso;
    private ListaControlPersonal control;
    private boolean autorizacion;
    private double SueldoReal;
    public Personal(){
        this.nombre=this.direccion=this.carnet=this.celular=this.ocupacion="";
        this.sueldo=0.0;
        this.numMaquina="";
        this.direccionFoto="";
        control=new ListaControlPersonal();
        this.autorizacion=false;
    }
    
    public void CalcularSueldoReal(){
        NodoControlPersonal w=this.control.getP();
        double cont=0;
        double ade=0;
        double extra=0;
        double extraTotal=0;
        while(w!=null){
            int horaEx = (w.getCp().getHoraSalida() * 60 + w.getCp().getMinutoSalida())-(FramePrincipal.cp.getV().getHoraSalida() * 60 + FramePrincipal.cp.getV().getMinutoSalida()) ;
            System.out.println("DIFERENCIA DE HORA"+horaEx);
            horaEx = horaEx / 60;
            System.out.println("HORAS EXTRA"+horaEx);
            if (horaEx > 0) {
                extra = horaEx * FramePrincipal.cp.getV().getPagoExtra();
            } else {
                extra = 0;
            }
            if (FramePrincipal.cp.getV().isActivo() == true) {
                if (w.getCp().isPermiso() == false) {

                    cont = cont + w.getCp().getDescuento();

                }
                extraTotal = extraTotal + extra;
            }

            ade = ade + w.getCp().getAdelanto();
            w = w.getSig();
        }
        SueldoReal=sueldo-sueldo*(cont)-ade+extraTotal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    public String getNumMaquina() {
        return numMaquina;
    }

    public void setNumMaquina(String numMaquina) {
        this.numMaquina = numMaquina;
    }

    public String getDireccionFoto() {
        return direccionFoto;
    }

    public void setDireccionFoto(String direccionFoto) {
        this.direccionFoto = direccionFoto;
    }

    public String getDireccionHuella() {
        return direccionHuella;
    }

    public void setDireccionHuella(String direccionHuella) {
        this.direccionHuella = direccionHuella;
    }

    public boolean isAutorizacion() {
        return autorizacion;
    }

    public void setAutorizacion(boolean autorizacion) {
        this.autorizacion = autorizacion;
    }

    public ListaControlPersonal getControl() {
        return control;
    }

    public void setControl(ListaControlPersonal control) {
        this.control = control;
    }

    public double getSueldoReal() {
        return SueldoReal;
    }

    public void setSueldoReal(double SueldoReal) {
        this.SueldoReal = SueldoReal;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }
    
    
}
