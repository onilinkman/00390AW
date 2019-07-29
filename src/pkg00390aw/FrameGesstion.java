/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg00390aw;

import com.sun.corba.se.spi.orbutil.threadpool.Work;
import java.awt.Desktop;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

/**
 *
 * @author WINDOWS 10
 */
public class FrameGesstion extends javax.swing.JFrame {

    
    public FrameGesstion() {
        initComponents();
        this.setLocationRelativeTo(null);
        crearLista();
        CrearLista3();
        setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
        this.dispose();
        llenarBalance();
        crearComboBox();
        this.jButton1.setVisible(false);
        this.jButton5.setVisible(false);
        this.jPanel2.setVisible(false);
    }
    
    public void crearComboBox() {
        NodoPersonal aux = FramePrincipal.cp.getLp().getP();
        while (aux != null) {
            this.jComboBox1.addItem(aux.getPersonal().getNombre());
            aux = aux.getSig();
        }
        /*this.jComboBox1.addItem("hola");
        this.jComboBox1.addItem("2");*/
    }
    
    public void resetearTabla1() {
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "", "FECHA INGRESO", "FECHA ENTREGA", "CLIENTE", "DISEÑO", "PRENDA", "CANTIDAD", "COSTO UNITARIO", "ADELANTO", "SALDO", "TOTAL", "ENCARGADO", "Nro MAQUINA", "TAMAÑO"
                }
        ));
    }
    public void resetearTabla2() {
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                    {""}
                },
                new String[]{
                    "FECHA", "INGRESO TOTAL", "GASTOS", "SUELDOS", "SERVICIOS", "UTILIDAD"
                }
        ));
    }
    public void resetearTabla3() {
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Fecha", "CANTIDAD", "TOTAL", "CANTIDAD", "TOTAL", "COLOR", "METROS", "TOTAL", "METROS", "TOTAL", "NOMBRE", "METROS", "TOTAL", "NOMBRE", "TOTAL"

                }
        ));
    }
    
    public void llenarBalance(){
        DecimalFormat df=new DecimalFormat("#.00");
        this.jTable2.setValueAt(ObtenerFecha(), 0, 0);
        this.jTable2.setValueAt(String.valueOf(obtenerTotalIngresos()), 0, 1);
        this.jTable2.setValueAt(String.valueOf(obtenerGastos()), 0, 2);
        this.jTable2.setValueAt(String.valueOf(obtenerSueldos()), 0, 3);
        this.jTable2.setValueAt(String.valueOf(obtenerServicios()), 0, 4);
        this.jTable2.setValueAt(corrigeComa(df.format(obtenerUtilidad())), 0, 5);
    }
    
    public String corrigeComa(String num){
        String rec="";
        for(int i=0;i<num.length();i++){
            if(num.charAt(i)==','){
                rec=rec+".";
            }else{
                rec=rec+num.charAt(i);
            }
        }
        return rec;
    }

    public void guardarAExcelPersonal(NodoPersonal personal) throws IOException, WriteException{
        int nroCas=this.jTable1.getRowCount();
        
        String path=".\\GestionesDelPersonal\\"+ObtenerFecha2()+"_"+personal.getPersonal().getNombre()+".xls";
        WritableWorkbook libro1 = Workbook.createWorkbook(new File(path));
        WritableSheet hoja1 = libro1.createSheet("REGISTRO", 0);
        
        Label etiqueta = new Label(0, 0, "FECHA");
        hoja1.addCell(etiqueta);
        etiqueta = new Label(1, 0, "INGRESO");
        hoja1.addCell(etiqueta);
        etiqueta = new Label(2, 0, "SALIDA");
        hoja1.addCell(etiqueta);
        etiqueta = new Label(3, 0, "ADELANTO");
        hoja1.addCell(etiqueta);
        etiqueta = new Label(4, 0, "DESCUENTO");
        hoja1.addCell(etiqueta);
        etiqueta = new Label(5, 0, "FALTA");
        hoja1.addCell(etiqueta);
        etiqueta = new Label(6, 0, "PERMISO");
        hoja1.addCell(etiqueta);
        
        NodoControlPersonal aux=personal.getPersonal().getControl().getP();
        
        int i=0;
        while(aux!=null){
            i++;
            etiqueta=new Label(0,i,aux.getCp().getFecha());
            hoja1.addCell(etiqueta);
            
            String minutoEntrada;
            if(aux.getCp().getMinutoEntrada()<10){
                minutoEntrada="0"+aux.getCp().getMinutoEntrada();
            }else{
                minutoEntrada=String.valueOf(aux.getCp().getMinutoEntrada());
            }
            etiqueta=new Label(1,i,aux.getCp().getHoraEntrada()+":"+minutoEntrada);
            hoja1.addCell(etiqueta);
            
            String minutoSalida;
            if(aux.getCp().getMinutoSalida()<10){
                minutoSalida="0"+aux.getCp().getMinutoSalida();
            }else{
                minutoSalida=String.valueOf(aux.getCp().getMinutoSalida());
            }
            etiqueta=new Label(2,i,aux.getCp().getHoraSalida()+":"+minutoSalida);
            hoja1.addCell(etiqueta);
            
            etiqueta=new Label(3,i,String.valueOf(aux.getCp().getAdelanto()));
            hoja1.addCell(etiqueta);
            
            etiqueta=new Label(4,i,String.valueOf(aux.getCp().getDescuento()*personal.getPersonal().getSueldo()));
            hoja1.addCell(etiqueta);
            
            if(aux.getCp().isFalta()==true){
                if(aux.getCp().isPermiso()==true){
                    etiqueta=new Label(5,i,"NO");
                    hoja1.addCell(etiqueta);
                }else{
                    etiqueta=new Label(5,i,"SI");
                    hoja1.addCell(etiqueta);
                }
            }else{
                etiqueta=new Label(5,i,"NO");
                hoja1.addCell(etiqueta);
            }
            
            if(aux.getCp().isPermiso()==true){
                etiqueta=new Label(6,i,"SI");
                hoja1.addCell(etiqueta);
            }else{
                etiqueta=new Label(6,i,"NO");
                hoja1.addCell(etiqueta);
            }
            
            etiqueta=new Label(7,i,String.valueOf(obtenerHorasExtra(aux)));
            hoja1.addCell(etiqueta);
            
            aux=aux.getSig();
        }

        
        
        WritableSheet hoja2 = libro1.createSheet("CALCULOS", 1);

        etiqueta = new Label(0, 0, "TOTAL ADELANTOS");
        hoja2.addCell(etiqueta);
        etiqueta = new Label(1, 0, "TOTAL DESCUENTOS");
        hoja2.addCell(etiqueta);
        etiqueta = new Label(2, 0, "TOTAL FALTAS");
        hoja2.addCell(etiqueta);
        etiqueta = new Label(3, 0, "TOTAL PERMISOS");
        hoja2.addCell(etiqueta);
        etiqueta = new Label(4, 0, "TOTAL A PAGAR");
        hoja2.addCell(etiqueta);

        
        etiqueta = new Label(0, 1,String.valueOf(totalAdelantos(personal)) );
        hoja2.addCell(etiqueta);
        etiqueta = new Label(1, 1, String.valueOf(totalDescuento(personal)));
        hoja2.addCell(etiqueta);
        etiqueta = new Label(2, 1, String.valueOf(totalFaltas(personal)));
        hoja2.addCell(etiqueta);
        etiqueta = new Label(3, 1, String.valueOf(totalPermisos(personal)));
        hoja2.addCell(etiqueta);
        etiqueta = new Label(4, 1, String.valueOf(personal.getPersonal().getSueldoReal()));
        hoja2.addCell(etiqueta);

        libro1.write();
        libro1.close();
        /*try {

            File objetofile = new File(path);
            Desktop.getDesktop().open(objetofile);

        } catch (IOException ex) {

            System.out.println(ex);
        }*/
    }
    
    
    
    public int totalPermisos(NodoPersonal w){
        int total=0;
        NodoControlPersonal aux=w.getPersonal().getControl().getP();
        while(aux!=null){
            if(aux.getCp().isPermiso()==true){
                total++;
            }
            aux=aux.getSig();
        }
        return total;
    }
    
    public int totalFaltas(NodoPersonal w){
        int total=0;
        NodoControlPersonal aux=w.getPersonal().getControl().getP();
        while(aux!=null){
            if(aux.getCp().isPermiso()==false){
                if(aux.getCp().isFalta()==true){
                    total++;
                }
            }
            aux=aux.getSig();
        }
        return total;
    }
    
    public double totalDescuento(NodoPersonal w){
        double total=0;
        NodoControlPersonal aux=w.getPersonal().getControl().getP();
        while(aux!=null){
            total=total+aux.getCp().getDescuento();
            aux=aux.getSig();
        }
        total=total*w.getPersonal().getSueldo();
        return total;
    }
    
    public double totalAdelantos(NodoPersonal w){
        double total=0;
        NodoControlPersonal aux=w.getPersonal().getControl().getP();
        while(aux!=null){
            total=total+aux.getCp().getAdelanto();
            aux=aux.getSig();
        }
        return total;
        
    }
    public int obtenerHorasExtra(NodoControlPersonal w){
        int horaSalidaCv=FramePrincipal.cp.getV().getHoraSalida();
        int minutosSalidaCv=FramePrincipal.cp.getV().getMinutoSalida();
        int horaSalida=w.getCp().getHoraSalida();
        int minutoSalida=w.getCp().getMinutoSalida();
        int horaExtra=(horaSalida*60+minutoSalida)-(horaSalidaCv*60+minutosSalidaCv);
        if(horaExtra>0){
            horaExtra=horaExtra/60;
        }else{
            horaExtra=0;
        }
        return horaExtra;
    }
    
    public double obtenerUtilidad(){
        return (obtenerTotalIngresos()-obtenerGastos()-obtenerSueldos()-obtenerServicios());
    }
    
    public double obtenerServicios(){
        double cont =0;
        NodoGastos np=FramePrincipal.cp.getLg().getP();
        int i=0;
        
        while(np!=null){
            cont=cont+np.getGastos().getServiciosTotal();
            i++;
            np=np.getSig();
        }
        return cont;
    }
    
    public double obtenerSueldos() {
        double cont = 0;
        NodoPersonal aux = FramePrincipal.cp.getLp().getP();
        while (aux != null) {
            if (FramePrincipal.cp.getV().isActivo() == false) {
                cont = cont + aux.getPersonal().getSueldoReal();
            } else {
                cont = cont + aux.getPersonal().getSueldoReal();
            }
            aux = aux.getSig();
        }
        return cont;
    }
    
    public double obtenerGastos(){
        double cont =0;
        NodoGastos np=FramePrincipal.cp.getLg().getP();
        int i=0;
        
        while(np!=null){
            cont=cont+np.getGastos().getHBordadoTotal();
            cont=cont+np.getGastos().getHSedaTotal();
            cont=cont+np.getGastos().getApliqueTotal();
            cont=cont+np.getGastos().getPellonTotal();
            cont=cont+np.getGastos().getOtrosTotal();
            
            i++;
            np=np.getSig();
        }
        return cont;
    }
    
    public double obtenerTotalIngresos(){
        double cont=0;
        NodoCliente nc=FramePrincipal.cp.getLc().getP();
        int i=0;
        while(nc!=null){
            if (nc.getCliente().isEntregado()) {
                cont=cont+nc.getCliente().getTotal();
                i++;
            }
            nc = nc.getSig();
        }
        return cont;
    }
    
    public String ObtenerFecha(){
        String fecha="";
        Calendar calendario=Calendar.getInstance();
        int dia=calendario.get(Calendar.DAY_OF_MONTH);
        int mes=calendario.get(Calendar.MONTH)+1;
        int anio=calendario.get(Calendar.YEAR);
        fecha=dia+"/"+mes+"/"+anio;
        
        return fecha;
    }
    
    
    public void Generatabla1(){
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.addRow(new Object[]{""});
            
    }
    
    public void crearLista(){
        NodoCliente nc=FramePrincipal.cp.getLc().getP();
        int i=0;
        while(nc!=null){
            if (nc.getCliente().isEntregado()) {
                Generatabla1();
                agregarATabla(nc.getCliente(), i);
                i++;
            }
            nc = nc.getSig();
        }
    }
    
    public void agregarATabla(Cliente c,int i){
        String fechaIngreso=c.getFechaIngreso();
        String fechaEntrega=c.getFechaEntrega();
        String cliente=c.getNombre();
        String nobreDisenio=c.getDisenio();
        String nombrePrenda=c.getPrenda();
        int cantidad=c.getCantidad();
        double CostoUnitarion=c.getCostoUnitario();
        double adelanto=c.getAdelanto();
        double saldo=c.getSaldo();
        double total=c.getTotal();
        String encargado=c.getEncargado();
        String nroMaquina=MaquinaEmpleado(c.getEncargado());
        
        jTable1.setValueAt(c.getCodigoCliente(), i, 0);
        jTable1.setValueAt(fechaIngreso, i, 1);
        jTable1.setValueAt(fechaEntrega, i, 2);
        jTable1.setValueAt(cliente, i, 3);
        
        
        
        jTable1.setValueAt(nobreDisenio, i, 4);
        jTable1.setValueAt(nombrePrenda, i, 5);
        jTable1.setValueAt(cantidad, i, 6);
        jTable1.setValueAt(CostoUnitarion, i, 7);
        jTable1.setValueAt(adelanto, i, 8);
        jTable1.setValueAt(saldo, i, 9);
        jTable1.setValueAt(total, i, 10);
        jTable1.setValueAt(encargado, i, 11);
        jTable1.setValueAt(nroMaquina, i, 12);
        jTable1.setValueAt(c.getTamanio(), i, 13);
    }
    
    
    
    
    
    
    
    
    
    public void Generatabla3(){
            DefaultTableModel model = (DefaultTableModel) jTable3.getModel();
            model.addRow(new Object[]{""});
    }
    
    public void CrearLista3(){
        NodoGastos np=FramePrincipal.cp.getLg().getP();
        int i=0;
        
        while(np!=null){
            Generatabla3();
            AgregarGasto(np.getGastos(),i);
            i++;
            np=np.getSig();
        }
    }
    
    
    public void AgregarGasto(Gastos g,int i){
        String fecha=g.getFecha();
        
        int hbCantidad=g.getHBordadoCantidad();
        double HBTotal=g.getHBordadoTotal();
        
        int HSCantidad=g.getHSedaCantidad();
        double HSTotal=g.getHSedaTotal();
        
        String AColor=g.getApliqueNombreColor();
        double AMetros=g.getApliqueMetros();
        double ATotal=g.getApliqueTotal();
        
        double PMetros=g.getPellonMetros();
        double PTotal=g.getPellonTotal();
        
        String ONombre=g.getOtrosNombre();
        double OMetros=g.getOtrosMetros();
        double OTotal=g.getOtrosTotal();
        
        String SNombre=g.getServiciosNombre();
        double STotal=g.getServiciosTotal();
        
        jTable3.setValueAt(fecha, i, 0);
        jTable3.setValueAt(hbCantidad, i, 1);
        jTable3.setValueAt(HBTotal, i, 2);
        jTable3.setValueAt(HSCantidad, i, 3);
        jTable3.setValueAt(HSTotal, i, 4);
        jTable3.setValueAt(AColor, i, 5);
        jTable3.setValueAt(AMetros, i, 6);
        jTable3.setValueAt(ATotal, i, 7);
        jTable3.setValueAt(PMetros, i, 8);
        jTable3.setValueAt(PTotal, i, 9);
        jTable3.setValueAt(ONombre, i, 10);
        jTable3.setValueAt(OMetros, i, 11);
        jTable3.setValueAt(OTotal, i, 12);
        jTable3.setValueAt(SNombre, i, 13);
        jTable3.setValueAt(STotal, i, 14);
    }
    
    
    
    public String MaquinaEmpleado(String empleado){
        String maquina="";
        NodoPersonal w=FramePrincipal.cp.getLp().getP();
        while(w!=null){
            if(w.getPersonal().getNombre().equals(empleado)){
                maquina=w.getPersonal().getNumMaquina();
            }
            w=w.getSig();
        }
        return maquina;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        pantFecha2 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        pantFecha1 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        pantFecha3 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        pantFecha4 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        pantFecha5 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        pantFecha6 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton6 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "Fecha","CANTIDAD", "TOTAL", "CANTIDAD", "TOTAL","COLOR","METROS","TOTAL","METROS","TOTAL","NOMBRE","METROS","TOTAL","NOMBRE","TOTAL"

            }
        ));
        jScrollPane3.setViewportView(jTable3);

        jPanel11.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1350, 120));

        jPanel7.setBackground(new java.awt.Color(204, 204, 204));
        jPanel7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 3, true));

        pantFecha2.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        pantFecha2.setText("HILO DE BORDAR");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addComponent(pantFecha2, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(pantFecha2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 64, Short.MAX_VALUE))
        );

        jPanel11.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(93, 0, -1, -1));

        jPanel12.setBackground(new java.awt.Color(204, 204, 204));
        jPanel12.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 3, true));

        pantFecha1.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        pantFecha1.setText("HILO DE SEDA");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(36, Short.MAX_VALUE)
                .addComponent(pantFecha1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(pantFecha1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 64, Short.MAX_VALUE))
        );

        jPanel11.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 0, -1, -1));

        jPanel13.setBackground(new java.awt.Color(204, 204, 204));
        jPanel13.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 3, true));

        pantFecha3.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        pantFecha3.setText("APLIQUE");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(103, Short.MAX_VALUE)
                .addComponent(pantFecha3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(pantFecha3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 70, Short.MAX_VALUE))
        );

        jPanel11.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(448, 0, 270, -1));

        jPanel14.setBackground(new java.awt.Color(204, 204, 204));
        jPanel14.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 3, true));

        pantFecha4.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        pantFecha4.setText("PELLON");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap(71, Short.MAX_VALUE)
                .addComponent(pantFecha4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(pantFecha4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 70, Short.MAX_VALUE))
        );

        jPanel11.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(715, 0, -1, -1));

        jPanel15.setBackground(new java.awt.Color(204, 204, 204));
        jPanel15.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 3, true));

        pantFecha5.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        pantFecha5.setText("OTROS");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap(100, Short.MAX_VALUE)
                .addComponent(pantFecha5, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(84, 84, 84))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addComponent(pantFecha5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 70, Short.MAX_VALUE))
        );

        jPanel11.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 0, 270, -1));

        jPanel16.setBackground(new java.awt.Color(204, 204, 204));
        jPanel16.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 3, true));

        pantFecha6.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        pantFecha6.setText("SERVICIOS");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap(65, Short.MAX_VALUE)
                .addComponent(pantFecha6, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addComponent(pantFecha6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 64, Short.MAX_VALUE))
        );

        jPanel11.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 0, -1, -1));

        jScrollPane2.setViewportView(jPanel11);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, 1140, 190));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {""}
            },
            new String [] {
                "FECHA","INGRESO TOTAL","GASTOS","SUELDOS","SERVICIOS","UTILIDAD"
            }
        ));
        jScrollPane4.setViewportView(jTable2);

        getContentPane().add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 490, 740, 100));

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/undo.png"))); // NOI18N
        jButton1.setText("RESET");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 600, 160, 60));

        jButton5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cancel2.png"))); // NOI18N
        jButton5.setText("CERRAR");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 30, 190, 50));

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/document_export (1).png"))); // NOI18N
        jButton2.setText("EXPORTAR GUARDAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 600, 240, 60));

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/folder_open (1).png"))); // NOI18N
        jButton3.setText("REGISTROS ANTERIORES");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 600, 290, 60));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("LISTA DE GASTOS");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 230, 40));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("BALANCE MENSUAL");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 460, 170, 40));

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/back.png"))); // NOI18N
        jButton4.setText("ATRAS");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 30, 140, 50));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("LISTA DE INGRESOS");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 220, 40));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "","FECHA INGRESO", "FECHA ENTREGA", "CLIENTE","DISEÑO","PRENDA","CANTIDAD","COSTO UNITARIO","ADELANTO","SALDO","TOTAL","ENCARGADO","Nro MAQUINA","TAMAÑO"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-90, 30, 1170, 140));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 1170, 180));

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jComboBox1.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {  }));
        jPanel2.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 210, 40));

        jButton6.setFont(new java.awt.Font("Bookman Old Style", 1, 16)); // NOI18N
        jButton6.setText("Registros Anteriores");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 210, 60));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("REGISTROS DE EMPLEADOS");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 180, 30));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 480, 210, 160));

        jLabel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 5, true));
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1230, 690));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            guardarAExcel();
            JOptionPane.showMessageDialog(null, "SE GUARDO CORRECTAMENTE");
            this.jButton1.setVisible(true);
            this.jPanel2.setVisible(true);
            
            
        } catch (IOException ex) {
            Logger.getLogger(FrameGesstion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (WriteException ex) {
            Logger.getLogger(FrameGesstion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        NodoPersonal w=FramePrincipal.cp.getLp().getP();
        while(w!=null){
            try {
                guardarAExcelPersonal(w);
            } catch (IOException ex) {
                Logger.getLogger(FrameGesstion.class.getName()).log(Level.SEVERE, null, ex);
            } catch (WriteException ex) {
                Logger.getLogger(FrameGesstion.class.getName()).log(Level.SEVERE, null, ex);
            }
            w=w.getSig();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        FrameImportar a=new FrameImportar(".\\GestionesExportados\\");
        a.setSw(1);
        a.show(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (JOptionPane.showConfirmDialog(null, "SE BORRARAN TODAS LAS VENTAS YA ENTREGADAS Y EL RESUMEN MENSUAL DEL PERSONAL,DESEA CONTINUAR?", "ADVERTENCIA", JOptionPane.YES_NO_OPTION) == 0) {
            //eliminarPedidosEntregados();
            eliminarBienClientes();
            eliminarControlPersonal();
            eliminarGastos();
            this.jButton1.setVisible(false);
            resetearTabla1();
            resetearTabla2();
            resetearTabla3();
            crearLista();
            CrearLista3();
            llenarBalance();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try {
            FramePrincipal a=new FramePrincipal();
            a.show(true);
            this.dispose();
        } catch (IOException ex) {
            Logger.getLogger(FrameGesstion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FrameGesstion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        FrameImportar a=new FrameImportar(".\\GestionesDelPersonal\\");
        a.setSw(2);
        a.llenarTabla(this.jComboBox1.getSelectedItem().toString());
        a.show(true);
    }//GEN-LAST:event_jButton6ActionPerformed

    public void reiniciarTabla1() {
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "","FECHA INGRESO", "FECHA ENTREGA", "CLIENTE", "DISEÑO", "PRENDA", "CANTIDAD", "COSTO UNITARIO", "ADELANTO", "SALDO", "TOTAL", "ENCARGADO", "Nro MAQUINA", "TAMAÑO"
                }
        ));
    }

    public void importarExcell(String dir) throws IOException, BiffException{
        reiniciarTabla1();
        resetearTabla2();
        resetearTabla3();
        Workbook workbook= Workbook.getWorkbook(new File(dir));
        Sheet hoja1=workbook.getSheet(0);
        for(int fila=1;fila<hoja1.getRows();fila++){
            Generatabla1();
            for(int columna=0;columna<hoja1.getColumns();columna++){
                String dato=hoja1.getCell(columna, fila).getContents();
                this.jTable1.setValueAt(dato, fila-1, columna+1);
                
            }
        }
        Sheet hoja2=workbook.getSheet(1);
        for(int fila=1;fila<hoja2.getRows();fila++){
            Generatabla3();
            for(int columna=0;columna<hoja2.getColumns();columna++){
                String dato=hoja2.getCell(columna, fila).getContents();
                this.jTable3.setValueAt(dato, fila-1, columna);
            }
        }
        Sheet hoja3=workbook.getSheet(2);
        for(int columna=0;columna<hoja3.getColumns();columna++){
            String dato=hoja3.getCell(columna,1).getContents();
            this.jTable2.setValueAt(dato, 0, columna);
        }
        this.jButton1.setVisible(false);
        this.jButton2.setVisible(false);
        this.jButton3.setVisible(false);
        //this.jTable3.setVisible(false);
        this.jButton4.setVisible(false);
        this.jButton5.setVisible(true);
    }
    
    public void eliminarGastos(){
        FramePrincipal.cp.getLg().setP(null);
        FramePrincipal.cp.guardar(FramePrincipal.cp.getLg());
    }
    public void eliminarControlPersonal(){
        NodoPersonal aux=FramePrincipal.cp.getLp().getP();
        while(aux!=null){
            aux.getPersonal().getControl().setP(null);
            aux=aux.getSig();
        }
        FramePrincipal.cp.guardar();
    }
    
    public void eliminarPedidosEntregados(){
        
        NodoCliente nc=FramePrincipal.cp.getLc().getP();
        //int i=0;
        while(nc!=null){
            //System.out.println(nc.getCliente().getCodigoCliente());
            if (nc.getCliente().isEntregado()==true) {
                try {
                    File arch = new File(nc.getCliente().getDireccionDisenio());
                    arch.delete();
                    File arch2 = new File(nc.getCliente().getDireccionImagen());
                    arch2.delete();
                } catch (Exception e) {
                    System.out.println("ERROR: "+e);
                }
                FramePrincipal.cp.getLc().eliminar(nc.getCliente().getCodigoCliente());
                
                //i++;
            }
            nc = nc.getSig();
        }
        FramePrincipal.cp.guardar(FramePrincipal.cp.getLc());
        
    }
    public void eliminarBienClientes(){
        int filas=this.jTable1.getRowCount();
        for(int i=0;i<filas;i++){
            FramePrincipal.cp.getLc().eliminar(this.jTable1.getValueAt(i, 0).toString());
            
        }
        FramePrincipal.cp.guardar(FramePrincipal.cp.getLc());
    }
    
    
    public void guardarAExcel() throws IOException, WriteException{
        int nroCas=this.jTable1.getRowCount();
        File f=new File(".\\GestionesExportados\\");
        f.mkdirs();
        WritableWorkbook libro1 = Workbook.createWorkbook(new File(".\\GestionesExportados\\"+ObtenerFecha2()+"RESUMEN"+".xls"));
        WritableSheet hoja1 = libro1.createSheet("ListaIngresos", 0);

        Label etiqueta = new Label(0, 0, "FECHA INGRESO");
        hoja1.addCell(etiqueta);
        etiqueta = new Label(1, 0, "FECHA ENTREGA");
        hoja1.addCell(etiqueta);
        etiqueta = new Label(2, 0, "CLIENTE");
        hoja1.addCell(etiqueta);
        etiqueta = new Label(3, 0, "DISEÑO");
        hoja1.addCell(etiqueta);
        etiqueta = new Label(4, 0, "PRENDA");
        hoja1.addCell(etiqueta);
        etiqueta = new Label(5, 0, "CANTIDAD");
        hoja1.addCell(etiqueta);
        etiqueta = new Label(6, 0, "COSTO UNITARIO");
        hoja1.addCell(etiqueta);
        etiqueta = new Label(7, 0, "ADELANTO");
        hoja1.addCell(etiqueta);
        etiqueta = new Label(8, 0, "SALDO");
        hoja1.addCell(etiqueta);
        etiqueta = new Label(9, 0, "TOTAL");
        hoja1.addCell(etiqueta);
        etiqueta = new Label(10, 0, "ENCARGADO");
        hoja1.addCell(etiqueta);
        etiqueta = new Label(11, 0, "Nro MAQUINA");
        hoja1.addCell(etiqueta);
        etiqueta=new Label(12,0,"TAMAÑO");
        hoja1.addCell(etiqueta);

        for (int i = 1; i <= nroCas; i++) {
            etiqueta = new Label(0, i, this.jTable1.getValueAt(i-1, 1).toString());
            hoja1.addCell(etiqueta);
            etiqueta = new Label(1, i, this.jTable1.getValueAt(i-1, 2).toString());
            hoja1.addCell(etiqueta);
            etiqueta = new Label(2, i, this.jTable1.getValueAt(i-1, 3).toString());
            hoja1.addCell(etiqueta);
            etiqueta = new Label(3, i, this.jTable1.getValueAt(i-1, 4).toString());
            hoja1.addCell(etiqueta);
            etiqueta = new Label(4, i, this.jTable1.getValueAt(i-1, 5).toString());
            hoja1.addCell(etiqueta);
            etiqueta = new Label(5, i, this.jTable1.getValueAt(i - 1, 6).toString());
            hoja1.addCell(etiqueta);
            etiqueta = new Label(6, i, this.jTable1.getValueAt(i - 1, 7).toString());
            hoja1.addCell(etiqueta);
            etiqueta = new Label(7, i, this.jTable1.getValueAt(i - 1, 8).toString());
            hoja1.addCell(etiqueta);
            etiqueta = new Label(8, i, this.jTable1.getValueAt(i - 1, 9).toString());
            hoja1.addCell(etiqueta);
            etiqueta = new Label(9, i, this.jTable1.getValueAt(i - 1, 10).toString());
            hoja1.addCell(etiqueta);
            etiqueta = new Label(10, i, this.jTable1.getValueAt(i - 1, 11).toString());
            hoja1.addCell(etiqueta);
            etiqueta = new Label(11, i, this.jTable1.getValueAt(i - 1, 12).toString());
            hoja1.addCell(etiqueta);
            etiqueta = new Label(12, i, this.jTable1.getValueAt(i - 1, 13).toString());
            hoja1.addCell(etiqueta);
        }
        
        nroCas=this.jTable3.getRowCount();
        WritableSheet hoja2 = libro1.createSheet("ListaGastos", 1);

        etiqueta = new Label(0, 0, "FECHA");
        hoja2.addCell(etiqueta);
        etiqueta = new Label(1, 0, "CANTIDAD DE HILO DE BORDAR");
        hoja2.addCell(etiqueta);
        etiqueta = new Label(2, 0, "TOTAL DE HILO DE BORDAR");
        hoja2.addCell(etiqueta);
        etiqueta = new Label(3, 0, "CANTIDAD DE HILO DE SEDA");
        hoja2.addCell(etiqueta);
        etiqueta = new Label(4, 0, "TOTAL DE HILO DE SEDA");
        hoja2.addCell(etiqueta);
        etiqueta = new Label(5, 0, "APLIQUE (COLOR)");
        hoja2.addCell(etiqueta);
        etiqueta = new Label(6, 0, "APLIQUE(METROS)");
        hoja2.addCell(etiqueta);
        etiqueta = new Label(7, 0, "APLIQUE(TOTAL)");
        hoja2.addCell(etiqueta);
        etiqueta = new Label(8, 0, "PELLON (METROS)");
        hoja2.addCell(etiqueta);
        etiqueta = new Label(9, 0, "PELLON (TOTAL)");
        hoja2.addCell(etiqueta);
        etiqueta = new Label(10, 0, "OTROS(NOMBRE)");
        hoja2.addCell(etiqueta);
        etiqueta = new Label(11, 0, "OTROS(METROS)");
        hoja2.addCell(etiqueta);
        etiqueta = new Label(12, 0, "OTROS(TOTAL)");
        hoja2.addCell(etiqueta);
        etiqueta = new Label(13, 0, "SERVICIOS(NOMBRE)");
        hoja2.addCell(etiqueta);
        etiqueta = new Label(14, 0, "SERVICIOS(TOTAL)");
        hoja2.addCell(etiqueta);

        for (int i = 1; i <= nroCas; i++) {
            etiqueta = new Label(0, i, this.jTable3.getValueAt(i-1, 0).toString());
            hoja2.addCell(etiqueta);
            etiqueta = new Label(1, i, this.jTable3.getValueAt(i-1, 1).toString());
            hoja2.addCell(etiqueta);
            etiqueta = new Label(2, i, this.jTable3.getValueAt(i-1, 2).toString());
            hoja2.addCell(etiqueta);
            etiqueta = new Label(3, i, this.jTable3.getValueAt(i-1, 3).toString());
            hoja2.addCell(etiqueta);
            etiqueta = new Label(4, i, this.jTable3.getValueAt(i-1, 4).toString());
            hoja2.addCell(etiqueta);
            etiqueta = new Label(5, i, this.jTable3.getValueAt(i - 1, 5).toString());
            hoja2.addCell(etiqueta);
            etiqueta = new Label(6, i, this.jTable3.getValueAt(i - 1, 6).toString());
            hoja2.addCell(etiqueta);
            etiqueta = new Label(7, i, this.jTable3.getValueAt(i - 1, 7).toString());
            hoja2.addCell(etiqueta);
            etiqueta = new Label(8, i, this.jTable3.getValueAt(i - 1, 8).toString());
            hoja2.addCell(etiqueta);
            etiqueta = new Label(9, i, this.jTable3.getValueAt(i - 1, 9).toString());
            hoja2.addCell(etiqueta);
            etiqueta = new Label(10, i, this.jTable3.getValueAt(i - 1, 10).toString());
            hoja2.addCell(etiqueta);
            etiqueta = new Label(11, i, this.jTable3.getValueAt(i - 1, 11).toString());
            hoja2.addCell(etiqueta);
            etiqueta = new Label(12, i, this.jTable3.getValueAt(i - 1, 12).toString());
            hoja2.addCell(etiqueta);
            etiqueta = new Label(13, i, this.jTable3.getValueAt(i - 1, 13).toString());
            hoja2.addCell(etiqueta);
            etiqueta = new Label(14, i, this.jTable3.getValueAt(i - 1, 14).toString());
            hoja2.addCell(etiqueta);
        }
        
        WritableSheet hoja3 = libro1.createSheet("BALANCE MENSUAL", 2);

        etiqueta = new Label(0, 0, "FECHA");
        hoja3.addCell(etiqueta);
        etiqueta = new Label(1, 0, "INGRESOS TOTALES");
        hoja3.addCell(etiqueta);
        etiqueta = new Label(2, 0, "GASTOS");
        hoja3.addCell(etiqueta);
        etiqueta = new Label(3, 0, "SUELDOS");
        hoja3.addCell(etiqueta);
        etiqueta = new Label(4, 0, "SERVICIOS");
        hoja3.addCell(etiqueta);
        etiqueta = new Label(5, 0, "UTILIDAD");
        hoja3.addCell(etiqueta);

        etiqueta = new Label(0, 1, this.jTable2.getValueAt(0, 0).toString());
        hoja3.addCell(etiqueta);
        etiqueta = new Label(1, 1, this.jTable2.getValueAt(0, 1).toString());
        hoja3.addCell(etiqueta);
        etiqueta = new Label(2, 1, this.jTable2.getValueAt(0, 2).toString());
        hoja3.addCell(etiqueta);
        etiqueta = new Label(3, 1, this.jTable2.getValueAt(0, 3).toString());
        hoja3.addCell(etiqueta);
        etiqueta = new Label(4, 1, this.jTable2.getValueAt(0, 4).toString());
        hoja3.addCell(etiqueta);
        etiqueta = new Label(5, 1, this.jTable2.getValueAt(0, 5).toString());
        hoja3.addCell(etiqueta);

        libro1.write();
        libro1.close();
    }
    
    public String ObtenerFecha2(){
        String fecha;
        Calendar calendario=Calendar.getInstance();
        int dia=calendario.get(Calendar.DAY_OF_MONTH);
        int mes=calendario.get(Calendar.MONTH)+1;
        int anio=calendario.get(Calendar.YEAR);
        int hora=calendario.get(Calendar.HOUR_OF_DAY);
        int minuto=calendario.get(Calendar.MINUTE);
        int segundo=calendario.get(Calendar.SECOND);
        fecha=dia+"_"+mes+"_"+anio;
        
        return fecha;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrameGesstion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameGesstion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameGesstion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameGesstion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameGesstion().setVisible(true);
            }
        });
    }
    @Override
    public Image getIconImage(){
        Image retValue=Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("imagenes/engranaje3_1.png"));
        return retValue;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JLabel pantFecha1;
    private javax.swing.JLabel pantFecha2;
    private javax.swing.JLabel pantFecha3;
    private javax.swing.JLabel pantFecha4;
    private javax.swing.JLabel pantFecha5;
    private javax.swing.JLabel pantFecha6;
    // End of variables declaration//GEN-END:variables
}
