
package pkg00390aw;

import java.io.Serializable;

public class Gastos implements Serializable{
    private int HBordadoCantidad;
    private double HBordadoTotal;
    
    private int HSedaCantidad;
    private double HSedaTotal;
    
    private String ApliqueNombreColor;
    private double ApliqueMetros;
    private double ApliqueTotal;
    
    private double PellonMetros;
    private double PellonTotal;
    
    private String OtrosNombre;
    private double OtrosMetros;
    private double OtrosTotal;
    
    private String ServiciosNombre;
    private double ServiciosTotal;
    
    private String Fecha;
    
    public Gastos(){
        this.HBordadoCantidad=this.HSedaCantidad=0;
        this.HBordadoTotal=this.HSedaTotal=this.ApliqueMetros=this.ApliqueTotal=this.PellonMetros=this.PellonTotal=this.OtrosMetros=0.0;
        this.OtrosTotal=this.ServiciosTotal=0.0;
        this.ApliqueNombreColor=this.OtrosNombre=this.ServiciosNombre="";
        this.Fecha="";
    }

    public int getHBordadoCantidad() {
        return HBordadoCantidad;
    }

    public void setHBordadoCantidad(int HBordadoCantidad) {
        this.HBordadoCantidad = HBordadoCantidad;
    }

    public double getHBordadoTotal() {
        return HBordadoTotal;
    }

    public void setHBordadoTotal(double HBordadoTotal) {
        this.HBordadoTotal = HBordadoTotal;
    }

    public int getHSedaCantidad() {
        return HSedaCantidad;
    }

    public void setHSedaCantidad(int HSedaCantidad) {
        this.HSedaCantidad = HSedaCantidad;
    }

    public double getHSedaTotal() {
        return HSedaTotal;
    }

    public void setHSedaTotal(double HSedaTotal) {
        this.HSedaTotal = HSedaTotal;
    }

    public String getApliqueNombreColor() {
        return ApliqueNombreColor;
    }

    public void setApliqueNombreColor(String ApliqueNombreColor) {
        this.ApliqueNombreColor = ApliqueNombreColor;
    }

    public double getApliqueMetros() {
        return ApliqueMetros;
    }

    public void setApliqueMetros(double ApliqueMetros) {
        this.ApliqueMetros = ApliqueMetros;
    }

    public double getApliqueTotal() {
        return ApliqueTotal;
    }

    public void setApliqueTotal(double ApliqueTotal) {
        this.ApliqueTotal = ApliqueTotal;
    }

    public double getPellonMetros() {
        return PellonMetros;
    }

    public void setPellonMetros(double PellonMetros) {
        this.PellonMetros = PellonMetros;
    }

    public double getPellonTotal() {
        return PellonTotal;
    }

    public void setPellonTotal(double PellonTotal) {
        this.PellonTotal = PellonTotal;
    }

    public String getOtrosNombre() {
        return OtrosNombre;
    }

    public void setOtrosNombre(String OtrosNombre) {
        this.OtrosNombre = OtrosNombre;
    }

    public double getOtrosMetros() {
        return OtrosMetros;
    }

    public void setOtrosMetros(double OtrosMetros) {
        this.OtrosMetros = OtrosMetros;
    }

    public double getOtrosTotal() {
        return OtrosTotal;
    }

    public void setOtrosTotal(double OtrosTotal) {
        this.OtrosTotal = OtrosTotal;
    }

    public String getServiciosNombre() {
        return ServiciosNombre;
    }

    public void setServiciosNombre(String ServiciosNombre) {
        this.ServiciosNombre = ServiciosNombre;
    }

    public double getServiciosTotal() {
        return ServiciosTotal;
    }

    public void setServiciosTotal(double ServiciosTotal) {
        this.ServiciosTotal = ServiciosTotal;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }
    
    
    
    
    
}
