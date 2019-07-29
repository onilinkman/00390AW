
package pkg00390aw;

import java.awt.Desktop;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
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


public class FrameGestionPersonal extends javax.swing.JFrame {

    public FrameGestionPersonal() {
        initComponents();
        setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
        this.dispose();
        this.setLocationRelativeTo(null);
        mostrarHorario();
        
        crearComboBox();
        Generartabla2();
        
        llenarTabla2();
        this.BCerrar.setVisible(false);
        if(FramePrincipal.cp.getV().isActivo()==true){
            this.pantActivo.setText("ACTIVO");
        }else{
            this.pantActivo.setText("DESCATIVADO");
        }
        this.pantHoraExtra.setText(String.valueOf(FramePrincipal.cp.getV().getPagoExtra()));
    }
    
    public void llenarTabla2(){
        NodoPersonal aux=FramePrincipal.cp.getLp().getP();
        while(aux!=null){
            if(this.jComboBox1.getSelectedItem().toString().equals(aux.getPersonal().getNombre())){
                this.pantSueldo.setText(String.valueOf(aux.getPersonal().getSueldo()));
                this.pantSueldoTotal.setText(String.valueOf(aux.getPersonal().getSueldoReal()));
                totalDescuento(aux.getPersonal().getControl().getP(),aux.getPersonal().getSueldoReal(),aux.getPersonal().getSueldo());
                
            }
            aux=aux.getSig();
        }
    }
    
    public void totalDescuento(NodoControlPersonal p,double sueldo,double des){
        double totalAdelantos=0;
        double totalDescuentos=0;
        int totalFaltas=0;
        double totalPermisos=0;
        while(p!=null){
            totalAdelantos=totalAdelantos+p.getCp().getAdelanto();
            totalDescuentos=totalDescuentos+p.getCp().getDescuento();
            if(p.getCp().isFalta()==true){
                if(p.getCp().isPermiso()==false){
                totalFaltas++;
                }
            }
            if(p.getCp().isPermiso()==true){
                totalPermisos++;
            }
            p=p.getSig();
        }
        double totalAPagar=sueldo;
        this.jTable2.setValueAt(String.valueOf(totalAdelantos), 0, 0);
        this.jTable2.setValueAt(des*totalDescuentos, 0, 1);
        this.jTable2.setValueAt(String.valueOf(totalFaltas), 0, 2);
        this.jTable2.setValueAt(totalPermisos, 0, 3);
        this.jTable2.setValueAt(totalAPagar, 0, 4);
    }
    
    public void mostrarHorario(){
        
        int horaE=FramePrincipal.cp.getV().getHoraEntrada();
        int minE=FramePrincipal.cp.getV().getMinutoEntrada();
        int horaS=FramePrincipal.cp.getV().getHoraSalida();
        int minS=FramePrincipal.cp.getV().getMinutoSalida();
        this.pantHoraEntrada.setValue(horaE);
        this.pantMinutoEntrada.setValue(minE);
        this.pantHoraSalida.setValue(horaS);
        this.pantMinutoSalida.setValue(minS);
    }

    public void crearComboBox(){
        NodoPersonal aux=FramePrincipal.cp.getLp().getP();
        while(aux!=null){
            this.jComboBox1.addItem(aux.getPersonal().getNombre());
            aux=aux.getSig();
        }
        /*this.jComboBox1.addItem("hola");
        this.jComboBox1.addItem("2");*/
    }
    
    public void Generatabla1(){
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.addRow(new Object[]{""});
    }
    public void Generartabla2(){
        DefaultTableModel model=(DefaultTableModel)jTable2.getModel();
        model.addRow(new Object[]{""});
    }
    public void buscaPersona(String nombre){
        NodoPersonal w=FramePrincipal.cp.getLp().getP();
        while(w!=null){
            if(w.getPersonal().getNombre().equals(nombre)){
                llenarTabla(w.getPersonal().getControl().getP(),w.getPersonal());
                System.out.println("SUELDO REAL:"+w.getPersonal().getSueldoReal());
            }
            w=w.getSig();
        }
    }
    public void llenarTabla(NodoControlPersonal nn,Personal p){
        
        NodoControlPersonal w=nn;
        int i=0;
        //System.out.println(w.getCp().getFecha());
        while(w!=null){
            
            Generatabla1();
            this.jTable1.setValueAt(w.getCp().getFecha(), i, 0);
            System.out.println(w.getCp().getFecha());
            this.jTable1.setValueAt(w.getCp().getHoraEntrada()+":"+w.getCp().getMinutoEntrada(), i, 1);
            this.jTable1.setValueAt(w.getCp().getHoraSalida()+":"+w.getCp().getMinutoSalida(), i, 2);
            this.jTable1.setValueAt(w.getCp().getAdelanto(), i, 3);
            this.jTable1.setValueAt(w.getCp().getDescuento()*p.getSueldo()+"Bs", i, 4);
            if(w.getCp().isFalta()==true){
                if(w.getCp().isPermiso()==false){
                    this.jTable1.setValueAt("SI", i, 5);
                }else{
                    this.jTable1.setValueAt("NO", i, 5);
                }
            }else{
                this.jTable1.setValueAt("NO", i, 5);
            }
            if(w.getCp().isPermiso()==true){
                this.jTable1.setValueAt("SI", i, 6);
                
            }else{
                this.jTable1.setValueAt("NO", i, 6);
            }
            
            this.jTable1.setValueAt(String.valueOf(obtenerHorasExtra(w)), i, 7);
            
            System.out.println("ADELANTO:"+w.getCp().getAdelanto());
            
            i++;
            w=w.getSig();
        }
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
    
    public void reiniciarTabla() {
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "FECHA","INGRESO","SALIDA","ADELANTO","DESCUENTO","FALTA","PERMISO","HORAS EXTRA"
                }
        ));
    }
    
    public void reiniciarTabla2(){
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "TOTAL ADELANTOS","TOTAL DESCUENTOS","TOTAL FALTAS","TOTAL PERMISOS","TOTAL PAGAR"
                }
        ));
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pantActivo = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        pantHoraEntrada = new javax.swing.JSpinner();
        jLabel2 = new javax.swing.JLabel();
        pantMinutoEntrada = new javax.swing.JSpinner();
        jLabel3 = new javax.swing.JLabel();
        pantHoraSalida = new javax.swing.JSpinner();
        pantMinutoSalida = new javax.swing.JSpinner();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        pantSueldo = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        pantPermiso = new javax.swing.JRadioButton();
        pantAdelanto = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jComboBox2 = new javax.swing.JComboBox<>();
        pantAtraso = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        pantSueldoTotal = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        BCerrar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        pantHoraExtra = new javax.swing.JTextField();
        BHoraExtra = new javax.swing.JButton();
        BIniciarDia = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pantActivo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        pantActivo.setText("ACTIVO");
        pantActivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pantActivoActionPerformed(evt);
            }
        });
        getContentPane().add(pantActivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 140, 57));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {}));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 40, 90, 30));

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("HORARIO");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 100, 30));

        pantHoraEntrada.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        jPanel1.add(pantHoraEntrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 70, 50, 30));

        jLabel2.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        jLabel2.setText("HORA DE INGRESO");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(55, 50, -1, -1));

        pantMinutoEntrada.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        jPanel1.add(pantMinutoEntrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, 50, 30));

        jLabel3.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        jLabel3.setText("HORARIO DE SALIDA");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, 130, -1));

        pantHoraSalida.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        jPanel1.add(pantHoraSalida, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, 50, 30));

        pantMinutoSalida.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        jPanel1.add(pantMinutoSalida, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 130, 50, 30));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel12.setText(":");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 133, 10, 20));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel13.setText(":");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 73, 10, 20));

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton3.setText("GUARDAR HORARIO");
        jButton3.setToolTipText("");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 20, 210, 210));

        pantSueldo.setEditable(false);
        pantSueldo.setBackground(new java.awt.Color(204, 204, 204));
        pantSueldo.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        getContentPane().add(pantSueldo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 90, 40));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("SUELDO");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, 90, 30));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("PERSONAL");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, 90, 30));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("REGISTRO");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, 120, 30));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "FECHA","INGRESO","SALIDA","ADELANTO","DESCUENTO","FALTA","PERMISO","HORAS EXTRA"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 670, 100));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("CALCULOS");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 430, 110, 30));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "TOTAL ADELANTOS","TOTAL DESCUENTOS","TOTAL FALTAS","TOTAL PERMISOS","TOTAL PAGAR"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 460, 670, 120));

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ok.png"))); // NOI18N
        jButton2.setText("INGRESAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 150, 40));

        pantPermiso.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        pantPermiso.setText("PERMISO");
        pantPermiso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pantPermisoActionPerformed(evt);
            }
        });
        jPanel2.add(pantPermiso, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 30, -1, 30));

        pantAdelanto.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        pantAdelanto.setText("0.0");
        jPanel2.add(pantAdelanto, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 90, 30));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Bs");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, 20, 30));

        jLabel7.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel7.setText("ADELANTOS");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 110, 30));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 220, 120));

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "15min", "30min", "60min", "Mayor a 60min" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });
        jPanel3.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 100, 30));

        pantAtraso.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        jPanel3.add(pantAtraso, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, 60, 30));

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton4.setText("GUARDAR");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 150, 40));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText("DESCUENTO POR ATRASO");
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 200, -1));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 80, 220, 140));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("SUELDO TOTAL");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 210, 110, 30));

        pantSueldoTotal.setEditable(false);
        pantSueldoTotal.setBackground(new java.awt.Color(204, 204, 204));
        pantSueldoTotal.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        getContentPane().add(pantSueldoTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 240, 100, 40));

        jButton1.setFont(new java.awt.Font("Bookman Old Style", 3, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/folder_open (1).png"))); // NOI18N
        jButton1.setText("REGISTROS ANTERIORES");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 600, 280, 60));

        jButton5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton5.setText("MENU PRINCIPAL");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 20, 180, 40));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, 500, 90));

        BCerrar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        BCerrar.setText("CERRAR");
        BCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BCerrarActionPerformed(evt);
            }
        });
        getContentPane().add(BCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 20, 110, 40));

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel16.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        jLabel16.setText("HORAS EXTRA (1 hora/Bs)");
        jPanel4.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));
        jPanel4.add(pantHoraExtra, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 60, 30));

        BHoraExtra.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        BHoraExtra.setText("GUARDAR");
        BHoraExtra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BHoraExtraActionPerformed(evt);
            }
        });
        jPanel4.add(BHoraExtra, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, -1, -1));

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 230, 190, 70));

        BIniciarDia.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        BIniciarDia.setText("INICIAR DIA");
        BIniciarDia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BIniciarDiaActionPerformed(evt);
            }
        });
        getContentPane().add(BIniciarDia, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 250, 150, 30));

        jLabel9.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 5, true));
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 735, 680));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        reiniciarTabla();
        
        buscaPersona(this.jComboBox1.getSelectedItem().toString());
        System.out.println(this.jComboBox1.getSelectedItem().toString());
        //llenarTabla2();
        reiniciarTabla2();
        Generartabla2();
        llenarTabla2();
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int horaE=(int) this.pantHoraEntrada.getValue();
        int minE=(int) this.pantMinutoEntrada.getValue();
        int horaS=(int) this.pantHoraSalida.getValue();
        int minS=(int) this.pantMinutoSalida.getValue();
        FramePrincipal.cp.getV().setHoraEntrada(horaE);
        FramePrincipal.cp.getV().setMinutoEntrada(minE);
        FramePrincipal.cp.getV().setHoraSalida(horaS);
        FramePrincipal.cp.getV().setMinutoSalida(minS);
        FramePrincipal.cp.guardar(FramePrincipal.cp.getV());
        JOptionPane.showMessageDialog(null, "SE GUARDO EL HORARIO DE TRABAJO");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        double[] d = FramePrincipal.cp.getV().getDescuento();
        if (this.jComboBox2.getSelectedItem().toString().equals("15min")) {
            this.pantAtraso.setText(String.valueOf(d[0] * 100));
        } else if (this.jComboBox2.getSelectedItem().toString().equals("30min")) {
            this.pantAtraso.setText(String.valueOf(d[1] * 100));
        } else if (this.jComboBox2.getSelectedItem().toString().equals("60min")) {
            this.pantAtraso.setText(String.valueOf(d[2] * 100));
        } else if (this.jComboBox2.getSelectedItem().toString().equals("Mayor a 60min")) {
            this.pantAtraso.setText(String.valueOf(d[3] * 100));
        }
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String nom=this.jComboBox1.getSelectedItem().toString();
        boolean per=this.pantPermiso.isSelected();
        try {
            double adelanto = Double.valueOf(this.pantAdelanto.getText());
            NodoPersonal aux = FramePrincipal.cp.getLp().getP();
            while (aux != null) {
                if (aux.getPersonal().getNombre().equals(nom)) {
                    aux.getPersonal().getControl().ingresarPermisoYAdelanto(ObtenerFecha(), adelanto, per);
                    aux.getPersonal().CalcularSueldoReal();
                    if(per==true){
                        
                    }
                    FramePrincipal.cp.guardar();
                    JOptionPane.showMessageDialog(null, "SE INGRESO CORRECTAMENTE");
                   
                    reiniciarTabla();

                    buscaPersona(this.jComboBox1.getSelectedItem().toString());
                    System.out.println(this.jComboBox1.getSelectedItem().toString());
                    //llenarTabla2();
                    reiniciarTabla2();
                    Generartabla2();
                    llenarTabla2();
                }
                aux = aux.getSig();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "SOLO PUEDE INGRESAR NUMEROS VALIDOS");
            System.out.println("ERROR: " + e);
        }
        this.pantAtraso.setText("");
    }//GEN-LAST:event_jButton2ActionPerformed

    
     public String ObtenerFecha(){
        String fecha="";
        Calendar calendario=Calendar.getInstance();
        int dia=calendario.get(Calendar.DAY_OF_MONTH);
        int mes=calendario.get(Calendar.MONTH)+1;
        int anio=calendario.get(Calendar.YEAR);
        fecha=dia+"/"+mes+"/"+anio;
        
        return fecha;
    }
     
     public String ObtenerFecha2(){
        String fecha="";
        Calendar calendario=Calendar.getInstance();
        int dia=calendario.get(Calendar.DAY_OF_MONTH);
        int mes=calendario.get(Calendar.MONTH)+1;
        int anio=calendario.get(Calendar.YEAR);
        fecha=dia+"_"+mes+"_"+anio;
        
        return fecha;
    }
    private void pantPermisoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pantPermisoActionPerformed
        //System.out.println(this.pantPermiso.isSelected());
    }//GEN-LAST:event_pantPermisoActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        //"15min", "30min", "60min", "Mayor a 60min"
        double[] d=FramePrincipal.cp.getV().getDescuento();
        try {
            if (this.jComboBox2.getSelectedItem().toString().equals("15min")) {
                d[0] = Double.valueOf(this.pantAtraso.getText()) / 100;
            } else if (this.jComboBox2.getSelectedItem().toString().equals("30min")) {
                d[1] = Double.valueOf(this.pantAtraso.getText()) / 100;
            } else if (this.jComboBox2.getSelectedItem().toString().equals("60min")) {
                d[2] = Double.valueOf(this.pantAtraso.getText()) / 100;
            } else if (this.jComboBox2.getSelectedItem().toString().equals("Mayor a 60min")) {
                d[3] = Double.valueOf(this.pantAtraso.getText()) / 100;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "INGRESE SOLO NUMEROS VALIDOS");
        }
        FramePrincipal.cp.getV().setDescuento(d);
        
        FramePrincipal.cp.guardar(FramePrincipal.cp.getV());
    }//GEN-LAST:event_jButton4ActionPerformed

    private void pantActivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pantActivoActionPerformed
            if(FramePrincipal.cp.getV().isActivo()==true){
                if(JOptionPane.showConfirmDialog(null, "Las faltas y atrasos del personal ya no se le tendra en cuenta en gastos del personal\nSi ya guardo esos datos nos se alterara lo guardado\nDesea continuar?", "AVISO", JOptionPane.YES_NO_OPTION)==0){
                    FramePrincipal.cp.getV().setActivo(false);
                    this.pantActivo.setText("DESACTIVADO");
                    ///

                    NodoPersonal aux = FramePrincipal.cp.getLp().getP();

                    while (aux != null) {

                        
                        aux.getPersonal().CalcularSueldoReal();
                        FramePrincipal.cp.guardar();

                        aux = aux.getSig();
                    }

                    //FramePrincipal.cp.getLp().getP().getPersonal().CalcularSueldoReal();
                    FramePrincipal.cp.guardar(FramePrincipal.cp.getV());
                    
                    reiniciarTabla();
                    buscaPersona(this.jComboBox1.getSelectedItem().toString());
                    System.out.println(this.jComboBox1.getSelectedItem().toString());
                    //llenarTabla2();
                    reiniciarTabla2();
                    Generartabla2();
                    llenarTabla2();
               }
        } else {
            FramePrincipal.cp.getV().setActivo(true);
            this.pantActivo.setText("ACTIVO");
            FramePrincipal.cp.guardar(FramePrincipal.cp.getV());

            NodoPersonal aux = FramePrincipal.cp.getLp().getP();

            while (aux != null) {

                aux.getPersonal().CalcularSueldoReal();
                FramePrincipal.cp.guardar();

                aux = aux.getSig();
            }

            reiniciarTabla();
            buscaPersona(this.jComboBox1.getSelectedItem().toString());
            System.out.println(this.jComboBox1.getSelectedItem().toString());
            //llenarTabla2();
            reiniciarTabla2();
            Generartabla2();
            llenarTabla2();
        }
    }//GEN-LAST:event_pantActivoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        FrameImportar a=new FrameImportar(".\\GestionesDelPersonal\\");
        a.setSw(2);
        
        //a.setPath();
        a.show(true);
        
    }//GEN-LAST:event_jButton1ActionPerformed

    public void vistaImportado(String dir){
        this.jPanel2.setVisible(false);
        this.pantActivo.setVisible(false);
        this.jLabel6.setVisible(false);
        this.jPanel1.setVisible(false);
        this.jPanel3.setVisible(false);
        this.jLabel5.setVisible(false);
        this.pantSueldo.setVisible(false);
        this.jLabel4.setVisible(false);
        this.pantSueldoTotal.setVisible(false);
        this.jComboBox1.setVisible(false);
        this.jLabel15.setText("<html>"+dir+"</html>");
        this.BCerrar.setVisible(true);
        this.jButton5.setVisible(false);
        this.jButton1.setVisible(false);
        //this.BExportar.setVisible(false);
    }
    
    public void importarExcel(String dir) throws IOException, BiffException{
        Workbook workbook=Workbook.getWorkbook(new File(dir));
        Sheet hoja1=workbook.getSheet(0);
        for(int fila=1;fila<hoja1.getRows();fila++){
            Generatabla1();
            for(int columnas=0;columnas<hoja1.getColumns();columnas++){
                String dato=hoja1.getCell(columnas, fila).getContents();
                this.jTable1.setValueAt(dato, fila-1, columnas);
            }
        }
        Sheet hoja2=workbook.getSheet(1);
        for(int columna=0;columna<hoja2.getColumns();columna++){
            String dato=hoja2.getCell(columna, 1).getContents();
            this.jTable2.setValueAt(dato, 0, columna);
        }
    }
    
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        try {
            FramePrincipal a=new FramePrincipal();
            a.show(true);
            this.dispose();
        } catch (IOException ex) {
            Logger.getLogger(FrameGestionPersonal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FrameGestionPersonal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jButton5ActionPerformed

    private void BCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BCerrarActionPerformed
        this.dispose();
    }//GEN-LAST:event_BCerrarActionPerformed

    private void BHoraExtraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BHoraExtraActionPerformed
        try{
            FramePrincipal.cp.getV().setPagoExtra(Double.valueOf(this.pantHoraExtra.getText()));
            FramePrincipal.cp.guardar(FramePrincipal.cp.getV());
            JOptionPane.showMessageDialog(null, "SE GUARDO CORRECTAMENTE");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "INGRESE SOLO NUMEROS VALIDOS");
        }
    }//GEN-LAST:event_BHoraExtraActionPerformed

    private void BIniciarDiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BIniciarDiaActionPerformed
        FramePrincipal.cp.getLp().AgregarFecha(ObtenerFecha());
        
        FramePrincipal.cp.guardar();
        
        reiniciarTabla();
        buscaPersona(this.jComboBox1.getSelectedItem().toString());
        System.out.println(this.jComboBox1.getSelectedItem().toString());
        //llenarTabla2();
        reiniciarTabla2();
        Generartabla2();
        llenarTabla2();
        JOptionPane.showMessageDialog(null, "SE INICIO EL REGISTRO DEL DIA "+ObtenerFecha()+"PARA EL PERSONAL");
    }//GEN-LAST:event_BIniciarDiaActionPerformed

    public void guardarAExcel() throws IOException, WriteException{
        int nroCas=this.jTable1.getRowCount();
        
        String path=".\\GestionesDelPersonal\\"+ObtenerFecha2()+"_"+this.jComboBox1.getSelectedItem().toString()+".xls";
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
        

        for (int i = 1; i <= nroCas; i++) {
            etiqueta = new Label(0, i, this.jTable1.getValueAt(i-1, 0).toString());
            hoja1.addCell(etiqueta);
            etiqueta = new Label(1, i, this.jTable1.getValueAt(i-1, 1).toString());
            hoja1.addCell(etiqueta);
            etiqueta = new Label(2, i, this.jTable1.getValueAt(i-1, 2).toString());
            hoja1.addCell(etiqueta);
            etiqueta = new Label(3, i, this.jTable1.getValueAt(i-1, 3).toString());
            hoja1.addCell(etiqueta);
            etiqueta = new Label(4, i, this.jTable1.getValueAt(i-1, 4).toString());
            hoja1.addCell(etiqueta);
            etiqueta = new Label(5, i, this.jTable1.getValueAt(i - 1, 5).toString());
            hoja1.addCell(etiqueta);
            etiqueta = new Label(6, i, this.jTable1.getValueAt(i - 1, 6).toString());
            hoja1.addCell(etiqueta);
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

        etiqueta = new Label(0, 1, this.jTable2.getValueAt(0, 0).toString());
        hoja2.addCell(etiqueta);
        etiqueta = new Label(1, 1, this.jTable2.getValueAt(0, 1).toString());
        hoja2.addCell(etiqueta);
        etiqueta = new Label(2, 1, this.jTable2.getValueAt(0, 2).toString());
        hoja2.addCell(etiqueta);
        etiqueta = new Label(3, 1, this.jTable2.getValueAt(0, 3).toString());
        hoja2.addCell(etiqueta);
        etiqueta = new Label(4, 1, this.jTable2.getValueAt(0, 4).toString());
        hoja2.addCell(etiqueta);

        libro1.write();
        libro1.close();
        
        
        try {

            File objetofile = new File(path);
            Desktop.getDesktop().open(objetofile);

        } catch (IOException ex) {

            System.out.println(ex);
        }
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
            java.util.logging.Logger.getLogger(FrameGestionPersonal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameGestionPersonal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameGestionPersonal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameGestionPersonal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameGestionPersonal().setVisible(true);
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
    private javax.swing.JButton BCerrar;
    private javax.swing.JButton BHoraExtra;
    private javax.swing.JButton BIniciarDia;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JButton pantActivo;
    private javax.swing.JTextField pantAdelanto;
    private javax.swing.JTextField pantAtraso;
    private javax.swing.JSpinner pantHoraEntrada;
    private javax.swing.JTextField pantHoraExtra;
    private javax.swing.JSpinner pantHoraSalida;
    private javax.swing.JSpinner pantMinutoEntrada;
    private javax.swing.JSpinner pantMinutoSalida;
    private javax.swing.JRadioButton pantPermiso;
    private javax.swing.JTextField pantSueldo;
    private javax.swing.JTextField pantSueldoTotal;
    // End of variables declaration//GEN-END:variables
}
