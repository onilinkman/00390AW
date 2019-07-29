
package pkg00390aw;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class FrameGastos extends javax.swing.JFrame {

    public FrameGastos() {
        initComponents();
        setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.dispose();
        this.pantFecha.setText(ObtenerFecha());
        //Generatabla1();
        reiniciarTabla();
        CrearLista();
        ocultarBotones();
        bloquearPantallas();
        pintarGris();
        this.BEmpIngresar.setVisible(true);
    }
    
    public void reiniciarTabla() {
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Fecha","CANTIDAD", "TOTAL", "CANTIDAD", "TOTAL","COLOR","METROS","TOTAL","METROS","TOTAL","NOMBRE","METROS","TOTAL","NOMBRE","TOTAL"
                }
        ));
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
    
    public void habilitarPantallas(){
        this.pantPellonMetros.setEditable(true);
        this.pantPellonTotal.setEditable(true);
        
        this.pantOtrosMetros.setEditable(true);
        this.pantOtrosNombre.setEditable(true);
        this.pantOtrosTotal.setEditable(true);
        
        this.pantServiciosTotal.setEditable(true);
        
        this.pantBordarCantidad.setEditable(true);
        this.pantBordarTotal.setEditable(true);
        
        this.pantSedaCantidad.setEditable(true);
        this.pantSedaTotal.setEditable(true);
        
        this.pantApliqueColor.setEditable(true);
        this.pantApliqueMetros.setEditable(true);
        this.pantApliqueTotal.setEditable(true);
    }
    
    public void bloquearPantallas(){
        this.pantPellonMetros.setEditable(false);
        this.pantPellonTotal.setEditable(false);
        
        this.pantOtrosMetros.setEditable(false);
        this.pantOtrosNombre.setEditable(false);
        this.pantOtrosTotal.setEditable(false);
        
        this.pantServiciosTotal.setEditable(false);
        
        this.pantBordarCantidad.setEditable(false);
        this.pantBordarTotal.setEditable(false);
        
        this.pantSedaCantidad.setEditable(false);
        this.pantSedaTotal.setEditable(false);
        
        this.pantApliqueColor.setEditable(false);
        this.pantApliqueMetros.setEditable(false);
        this.pantApliqueTotal.setEditable(false);
    }
    
    public void pintarGris(){
        this.pantPellonMetros.setBackground(new java.awt.Color(153,153,153));
        this.pantPellonTotal.setBackground(new java.awt.Color(153,153,153));
        
        this.pantOtrosMetros.setBackground(new java.awt.Color(153,153,153));
        this.pantOtrosNombre.setBackground(new java.awt.Color(153,153,153));
        this.pantOtrosTotal.setBackground(new java.awt.Color(153,153,153));
        
        this.pantServiciosTotal.setBackground(new java.awt.Color(153,153,153));
        
        this.pantBordarCantidad.setBackground(new java.awt.Color(153,153,153));
        this.pantBordarTotal.setBackground(new java.awt.Color(153,153,153));
        
        this.pantSedaCantidad.setBackground(new java.awt.Color(153,153,153));
        this.pantSedaTotal.setBackground(new java.awt.Color(153,153,153));
        
        this.pantApliqueColor.setBackground(new java.awt.Color(153,153,153));
        this.pantApliqueMetros.setBackground(new java.awt.Color(153,153,153));
        this.pantApliqueTotal.setBackground(new java.awt.Color(153,153,153));
    }
    
    public void pintarBlanco(){
        this.pantPellonMetros.setBackground(new java.awt.Color(255,255,255));
        this.pantPellonTotal.setBackground(new java.awt.Color(255,255,255));
        
        this.pantOtrosMetros.setBackground(new java.awt.Color(255,255,255));
        this.pantOtrosNombre.setBackground(new java.awt.Color(255,255,255));
        this.pantOtrosTotal.setBackground(new java.awt.Color(255,255,255));
        
        this.pantServiciosTotal.setBackground(new  java.awt.Color(255,255,255));
        
        this.pantBordarCantidad.setBackground(new java.awt.Color(255,255,255));
        this.pantBordarTotal.setBackground(new java.awt.Color(255,255,255));
        
        this.pantSedaCantidad.setBackground(new java.awt.Color(255,255,255));
        this.pantSedaTotal.setBackground(new java.awt.Color(255,255,255));
        
        this.pantApliqueColor.setBackground(new java.awt.Color(255,255,255));
        this.pantApliqueMetros.setBackground(new java.awt.Color(255,255,255));
        this.pantApliqueTotal.setBackground(new java.awt.Color(255,255,255));
    }
    
    public void ocultarBotones(){
        this.jButton1.setVisible(false);
        this.jButton2.setVisible(false);
        this.BEliminar.setVisible(false);
        this.BLimpiar.setVisible(false);
        this.BEmpEditar.setVisible(false);
        this.BEmpIngresar.setVisible(false);
        this.BCancelar.setVisible(false);
    }
    
    
    
    public void Generatabla1(){
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.addRow(new Object[]{""});
    }
    
    public void CrearLista(){
        NodoGastos np=FramePrincipal.cp.getLg().getP();
        int i=0;
        
        while(np!=null){
            Generatabla1();
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
        
        jTable1.setValueAt(fecha, i, 0);
        jTable1.setValueAt(hbCantidad, i, 1);
        jTable1.setValueAt(HBTotal, i, 2);
        jTable1.setValueAt(HSCantidad, i, 3);
        jTable1.setValueAt(HSTotal, i, 4);
        jTable1.setValueAt(AColor, i, 5);
        jTable1.setValueAt(AMetros, i, 6);
        jTable1.setValueAt(ATotal, i, 7);
        jTable1.setValueAt(PMetros, i, 8);
        jTable1.setValueAt(PTotal, i, 9);
        jTable1.setValueAt(ONombre, i, 10);
        jTable1.setValueAt(OMetros, i, 11);
        jTable1.setValueAt(OTotal, i, 12);
        jTable1.setValueAt(SNombre, i, 13);
        jTable1.setValueAt(STotal, i, 14);
    }

    
    public void ingresarDatos() {
        try {
            int bordadoCantidad = Integer.valueOf(this.pantBordarCantidad.getText());
            int sedaCantidad = Integer.valueOf(this.pantSedaCantidad.getText());
            try {
                double bordadoTotal = Double.valueOf(this.pantBordarTotal.getText());
                double sedaTotal = Double.valueOf(this.pantSedaTotal.getText());
                double apliqueMetros = Double.valueOf(this.pantApliqueMetros.getText());
                double apliqueTotal = Double.valueOf(this.pantApliqueTotal.getText());
                double pellonMetros = Double.valueOf(this.pantPellonMetros.getText());
                double pellonTotal = Double.valueOf(this.pantPellonTotal.getText());
                double otrosMetros = Double.valueOf(this.pantOtrosMetros.getText());
                double otrosTotal = Double.valueOf(this.pantOtrosTotal.getText());
                double serviciosTotal = Double.valueOf(this.pantServiciosTotal.getText());

                String apliqueColor = this.pantApliqueColor.getText();
                String otrosNombre = this.pantOtrosNombre.getText();
                String servicios = this.jComboBox1.getSelectedItem().toString();

                Gastos g = new Gastos();
                g.setApliqueMetros(apliqueMetros);
                g.setHBordadoCantidad(bordadoCantidad);
                g.setHSedaCantidad(sedaCantidad);
                g.setHBordadoTotal(bordadoTotal);
                g.setHSedaTotal(sedaTotal);
                g.setApliqueTotal(apliqueTotal);
                g.setPellonMetros(pellonMetros);
                g.setPellonTotal(pellonTotal);
                g.setOtrosMetros(otrosMetros);
                g.setOtrosTotal(otrosTotal);
                g.setServiciosTotal(serviciosTotal);
                g.setApliqueNombreColor(apliqueColor);
                g.setOtrosNombre(otrosNombre);
                g.setServiciosNombre(servicios);
                g.setFecha(this.pantFecha.getText());
                NodoGastos w = new NodoGastos();
                w.setGastos(g);
                FramePrincipal.cp.agregarFinalGastos(w);
                FramePrincipal.cp.guardar(FramePrincipal.cp.getLg());
                //Generatabla1();
                reiniciarTabla();
                CrearLista();
                limpiar();
                this.pantBordarCantidad.requestFocus();
                this.pantBordarCantidad.selectAll();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "\nEN EL \"TOTAL\" y \"METROS\" SOLO ADMITEN NUMEROS CON DECIMALES, SI DESEA QUE NO TENGA DATOS SOLO INSERTE 0.\n LOS DECIMALES SE ANOTAN CON UN \".\" EJEMPLO: 1.2");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "EN CANTIDAD INGRESE SOLO NUMEROS ENTEROS");
        }

    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        pantBordarCantidad = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        pantBordarTotal = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        pantSedaCantidad = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        pantSedaTotal = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        pantApliqueColor = new javax.swing.JTextField();
        pantApliqueMetros = new javax.swing.JTextField();
        pantApliqueTotal = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        pantPellonMetros = new javax.swing.JTextField();
        pantPellonTotal = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        pantOtrosNombre = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        pantOtrosMetros = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        pantOtrosTotal = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        pantServiciosTotal = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
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
        jButton1 = new javax.swing.JButton();
        pantFecha = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        BEliminar = new javax.swing.JButton();
        BLimpiar = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        BEmpIngresar = new javax.swing.JButton();
        BEmpEditar = new javax.swing.JButton();
        BCancelar = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 5, true));

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 16)); // NOI18N
        jLabel1.setText("HILO DE BORDAR");

        pantBordarCantidad.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        pantBordarCantidad.setText("0");
        pantBordarCantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pantBordarCantidadActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("CANTIDAD");

        pantBordarTotal.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        pantBordarTotal.setText("0.0");
        pantBordarTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pantBordarTotalActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("TOTAL (Bs)");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pantBordarCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pantBordarTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pantBordarTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(pantBordarCantidad, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 29, -1, -1));

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 5, true));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Tahoma", 3, 16)); // NOI18N
        jLabel4.setText("HILO DE SEDA");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, 150, 36));

        pantSedaCantidad.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        pantSedaCantidad.setText("0");
        pantSedaCantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pantSedaCantidadActionPerformed(evt);
            }
        });
        jPanel2.add(pantSedaCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 87, 91, 36));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("CANTIDAD");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 58, 91, 23));

        pantSedaTotal.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        pantSedaTotal.setText("0.0");
        pantSedaTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pantSedaTotalActionPerformed(evt);
            }
        });
        jPanel2.add(pantSedaTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(112, 87, 104, 36));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("TOTAL (Bs)");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(112, 58, 104, 23));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 30, 236, 149));

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 5, true));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Tahoma", 3, 16)); // NOI18N
        jLabel7.setText("APLIQUE");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(119, 11, 138, 31));

        pantApliqueColor.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        pantApliqueColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pantApliqueColorActionPerformed(evt);
            }
        });
        jPanel3.add(pantApliqueColor, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 130, 36));

        pantApliqueMetros.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        pantApliqueMetros.setText("0.0");
        pantApliqueMetros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pantApliqueMetrosActionPerformed(evt);
            }
        });
        jPanel3.add(pantApliqueMetros, new org.netbeans.lib.awtextra.AbsoluteConstraints(155, 80, 70, 36));

        pantApliqueTotal.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        pantApliqueTotal.setText("0.0");
        pantApliqueTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pantApliqueTotalActionPerformed(evt);
            }
        });
        jPanel3.add(pantApliqueTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 80, 94, 36));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("NOMBRE COLOR");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 42, 130, 30));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("METROS");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(153, 42, 71, 30));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("TOTAL (Bs)");
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(242, 42, 94, 30));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 30, 345, 149));

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));
        jPanel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 5, true));

        jLabel11.setFont(new java.awt.Font("Tahoma", 3, 16)); // NOI18N
        jLabel11.setText("PELLON");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("METROS");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("TOTAL(Bs)");

        pantPellonMetros.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        pantPellonMetros.setText("0.0");
        pantPellonMetros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pantPellonMetrosActionPerformed(evt);
            }
        });

        pantPellonTotal.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        pantPellonTotal.setText("0.0");
        pantPellonTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pantPellonTotalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                    .addComponent(pantPellonMetros))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pantPellonTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(99, 99, 99)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pantPellonTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(pantPellonMetros, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, -1, -1));

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));
        jPanel5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 5, true));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel14.setFont(new java.awt.Font("Tahoma", 3, 16)); // NOI18N
        jLabel14.setText("OTROS");
        jPanel5.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, 85, 28));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setText("NOMBRE");
        jPanel5.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 38, 75, 24));

        pantOtrosNombre.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        pantOtrosNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pantOtrosNombreActionPerformed(evt);
            }
        });
        jPanel5.add(pantOtrosNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 100, 36));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setText("METROS");
        jPanel5.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 40, 90, 20));

        pantOtrosMetros.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        pantOtrosMetros.setText("0.0");
        pantOtrosMetros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pantOtrosMetrosActionPerformed(evt);
            }
        });
        jPanel5.add(pantOtrosMetros, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 70, 90, 36));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setText("TOTAL (Bs)");
        jPanel5.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 35, 90, 30));

        pantOtrosTotal.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        pantOtrosTotal.setText("0.0");
        pantOtrosTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pantOtrosTotalActionPerformed(evt);
            }
        });
        jPanel5.add(pantOtrosTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 70, 100, 36));

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 200, 360, 130));

        jPanel6.setBackground(new java.awt.Color(204, 204, 204));
        jPanel6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 5, true));

        jLabel19.setFont(new java.awt.Font("Tahoma", 3, 16)); // NOI18N
        jLabel19.setText("SERVICIOS");

        jComboBox1.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-----","ALQUILER", "TELEFONO", "INTERNET", "LUZ","AGUA" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel18.setText("TOTAL(Bs)");

        pantServiciosTotal.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        pantServiciosTotal.setText("0.0");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(pantServiciosTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(54, 54, 54)
                                .addComponent(jLabel19)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pantServiciosTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 190, -1, -1));

        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "Fecha","CANTIDAD", "TOTAL", "CANTIDAD", "TOTAL","COLOR","METROS","TOTAL","METROS","TOTAL","NOMBRE","METROS","TOTAL","NOMBRE","TOTAL"

            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel11.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1350, 120));

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

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 440, 1140, 220));

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/aceptar.png"))); // NOI18N
        jButton1.setText("INGRESAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 390, 170, 40));

        pantFecha.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        getContentPane().add(pantFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 400, 126, 40));

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/edit.png"))); // NOI18N
        jButton2.setText("ACEPTAR EDICION");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 390, 220, 40));

        BEliminar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        BEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/deletered.png"))); // NOI18N
        BEliminar.setText("ELIMINAR");
        BEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BEliminarActionPerformed(evt);
            }
        });
        getContentPane().add(BEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 390, 150, 40));

        BLimpiar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        BLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/gnome_edit_clear.png"))); // NOI18N
        BLimpiar.setText("LIMPIAR");
        BLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BLimpiarActionPerformed(evt);
            }
        });
        getContentPane().add(BLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 390, 170, 40));

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/back.png"))); // NOI18N
        jButton3.setText("ATRAS");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 30, 150, 50));

        BEmpIngresar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        BEmpIngresar.setText("EMPEZAR A INGRESAR");
        BEmpIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BEmpIngresarActionPerformed(evt);
            }
        });
        getContentPane().add(BEmpIngresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 340, 200, 40));

        BEmpEditar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        BEmpEditar.setText("EMPEZAR A EDITAR");
        BEmpEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BEmpEditarActionPerformed(evt);
            }
        });
        getContentPane().add(BEmpEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 340, 200, 40));

        BCancelar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        BCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cancel2.png"))); // NOI18N
        BCancelar.setText("CANCELAR");
        BCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BCancelarActionPerformed(evt);
            }
        });
        getContentPane().add(BCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 390, 150, 40));

        jLabel20.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 5, true));
        getContentPane().add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1170, 710));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        ingresarDatos();
        ocultarBotones();
        bloquearPantallas();
        pintarGris();
        this.BEmpIngresar.setVisible(true);
        limpiar();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        System.out.println(this.jComboBox1.getSelectedItem().toString());
        if (this.jComboBox1.getSelectedItem().toString().equals("SUELDOS")) {
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
            this.pantServiciosTotal.setText(String.valueOf(cont));
            
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int fila=this.jTable1.getSelectedRow();
        editar(fila);
        
        ocultarBotones();
        bloquearPantallas();
        pintarGris();
        this.BEmpIngresar.setVisible(true);
        limpiar();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void pantBordarCantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pantBordarCantidadActionPerformed
        this.pantBordarTotal.requestFocus();
        this.pantBordarTotal.selectAll();
    }//GEN-LAST:event_pantBordarCantidadActionPerformed

    private void pantSedaCantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pantSedaCantidadActionPerformed
        this.pantSedaTotal.requestFocus();
        this.pantSedaTotal.selectAll();
    }//GEN-LAST:event_pantSedaCantidadActionPerformed

    private void pantBordarTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pantBordarTotalActionPerformed
        this.pantSedaCantidad.requestFocus();
        this.pantSedaCantidad.selectAll();
    }//GEN-LAST:event_pantBordarTotalActionPerformed

    private void pantSedaTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pantSedaTotalActionPerformed
        this.pantApliqueColor.requestFocus();
        this.pantApliqueColor.selectAll();
    }//GEN-LAST:event_pantSedaTotalActionPerformed

    private void pantApliqueColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pantApliqueColorActionPerformed
        this.pantApliqueMetros.requestFocus();
        this.pantApliqueMetros.selectAll();
    }//GEN-LAST:event_pantApliqueColorActionPerformed

    private void pantApliqueMetrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pantApliqueMetrosActionPerformed
        this.pantApliqueTotal.requestFocus();
        this.pantApliqueTotal.selectAll();
    }//GEN-LAST:event_pantApliqueMetrosActionPerformed

    private void pantApliqueTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pantApliqueTotalActionPerformed
        this.pantPellonMetros.requestFocus();
        this.pantPellonMetros.selectAll();
    }//GEN-LAST:event_pantApliqueTotalActionPerformed

    private void pantPellonTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pantPellonTotalActionPerformed
        this.pantOtrosNombre.requestFocus();
        this.pantOtrosNombre.selectAll();
    }//GEN-LAST:event_pantPellonTotalActionPerformed

    private void pantPellonMetrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pantPellonMetrosActionPerformed
        this.pantPellonTotal.requestFocus();
        this.pantPellonTotal.selectAll();
    }//GEN-LAST:event_pantPellonMetrosActionPerformed

    private void pantOtrosNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pantOtrosNombreActionPerformed
        this.pantOtrosMetros.requestFocus();
        this.pantOtrosMetros.selectAll();
    }//GEN-LAST:event_pantOtrosNombreActionPerformed

    private void pantOtrosMetrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pantOtrosMetrosActionPerformed
        this.pantOtrosTotal.requestFocus();
        this.pantOtrosTotal.selectAll();
    }//GEN-LAST:event_pantOtrosMetrosActionPerformed

    private void pantOtrosTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pantOtrosTotalActionPerformed
        this.pantServiciosTotal.requestFocus();
        this.pantServiciosTotal.selectAll();
    }//GEN-LAST:event_pantOtrosTotalActionPerformed

    private void BEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BEliminarActionPerformed
        int fila = this.jTable1.getSelectedRow();
        FramePrincipal.cp.getLg().eliminar(fila);
        reiniciarTabla();
        CrearLista();
        
        ocultarBotones();
        bloquearPantallas();
        pintarGris();
        this.BEmpIngresar.setVisible(true);
        limpiar();
    }//GEN-LAST:event_BEliminarActionPerformed

    private void BLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BLimpiarActionPerformed
        limpiar();
        
    }//GEN-LAST:event_BLimpiarActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        recuperarDatos();
        this.BEmpEditar.setVisible(true);
        this.BEliminar.setVisible(true);
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            FramePrincipal a=new FramePrincipal();
            a.show(true);
            this.dispose();
        } catch (IOException ex) {
            Logger.getLogger(FrameGastos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FrameGastos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void BEmpIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BEmpIngresarActionPerformed
        ocultarBotones();
        pintarBlanco();
        habilitarPantallas();
        this.jButton1.setVisible(true);
        this.BLimpiar.setVisible(true);
        this.BCancelar.setVisible(true);
        
    }//GEN-LAST:event_BEmpIngresarActionPerformed

    private void BCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BCancelarActionPerformed
        ocultarBotones();
        bloquearPantallas();
        pintarGris();
        this.BEmpIngresar.setVisible(true);
        limpiar();
    }//GEN-LAST:event_BCancelarActionPerformed

    private void BEmpEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BEmpEditarActionPerformed
        pintarBlanco();
        habilitarPantallas();
        this.jButton2.setVisible(true);
        this.BCancelar.setVisible(true);
    }//GEN-LAST:event_BEmpEditarActionPerformed

    public void recuperarDatos(){
        int fila=this.jTable1.getSelectedRow();
        int cont=0;
        NodoGastos ng=FramePrincipal.cp.getLg().getP();
        for(int i=0;i<fila;i++){
            ng=ng.getSig();
        }
        aLaPantalla(ng.getGastos());
        
    }
    
    public void aLaPantalla(Gastos g){
        this.pantBordarCantidad.setText(String.valueOf(g.getHBordadoCantidad()));
        this.pantBordarTotal.setText(String.valueOf(g.getHBordadoTotal()));
        
        this.pantSedaCantidad.setText(String.valueOf(g.getHSedaCantidad()));
        this.pantSedaTotal.setText(String.valueOf(g.getHSedaTotal()));
        
        this.pantApliqueColor.setText(String.valueOf(g.getApliqueNombreColor()));
        this.pantApliqueMetros.setText(String.valueOf(g.getApliqueMetros()));
        this.pantApliqueTotal.setText(String.valueOf(g.getApliqueTotal()));
        
        this.pantPellonMetros.setText(String.valueOf(g.getPellonMetros()));
        this.pantPellonTotal.setText(String.valueOf(g.getPellonTotal()));
        
        this.pantOtrosNombre.setText(String.valueOf(g.getOtrosNombre()));
        this.pantOtrosMetros.setText(String.valueOf(g.getOtrosMetros()));
        this.pantOtrosTotal.setText(String.valueOf(g.getOtrosTotal()));
        
        
    }
    
    public void limpiar(){
        this.pantBordarCantidad.setText("0");
        this.pantBordarTotal.setText("0.0");
        
        this.pantSedaCantidad.setText("0");
        this.pantSedaTotal.setText("0.0");
        
        this.pantApliqueColor.setText("");
        this.pantApliqueMetros.setText("0.0");
        this.pantApliqueTotal.setText("0.0");
        
        this.pantPellonMetros.setText("0.0");
        this.pantPellonTotal.setText("0.0");
        
        this.pantOtrosNombre.setText("");
        this.pantOtrosMetros.setText("0.0");
        this.pantOtrosTotal.setText("0.0");
        
        this.pantServiciosTotal.setText("0.0");
    }
    public void editar(int fila){
        try {
            int bordadoCantidad = Integer.valueOf(this.pantBordarCantidad.getText());
            int sedaCantidad = Integer.valueOf(this.pantSedaCantidad.getText());
            try {
                double bordadoTotal = Double.valueOf(this.pantBordarTotal.getText());
                double sedaTotal = Double.valueOf(this.pantSedaTotal.getText());
                double apliqueMetros = Double.valueOf(this.pantApliqueMetros.getText());
                double apliqueTotal = Double.valueOf(this.pantApliqueTotal.getText());
                double pellonMetros = Double.valueOf(this.pantPellonMetros.getText());
                double pellonTotal = Double.valueOf(this.pantPellonTotal.getText());
                double otrosMetros = Double.valueOf(this.pantOtrosMetros.getText());
                double otrosTotal = Double.valueOf(this.pantOtrosTotal.getText());
                double serviciosTotal = Double.valueOf(this.pantServiciosTotal.getText());

                String apliqueColor = this.pantApliqueColor.getText();
                String otrosNombre = this.pantOtrosNombre.getText();
                String servicios = this.jComboBox1.getSelectedItem().toString();

                Gastos g = new Gastos();
                g.setApliqueMetros(apliqueMetros);
                g.setHBordadoCantidad(bordadoCantidad);
                g.setHSedaCantidad(sedaCantidad);
                g.setHBordadoTotal(bordadoTotal);
                g.setHSedaTotal(sedaTotal);
                g.setApliqueTotal(apliqueTotal);
                g.setPellonMetros(pellonMetros);
                g.setPellonTotal(pellonTotal);
                g.setOtrosMetros(otrosMetros);
                g.setOtrosTotal(otrosTotal);
                g.setServiciosTotal(serviciosTotal);
                g.setApliqueNombreColor(apliqueColor);
                g.setOtrosNombre(otrosNombre);
                g.setServiciosNombre(servicios);
                g.setFecha(this.pantFecha.getText());
                
                FramePrincipal.cp.getLg().aditar(g, fila);
                FramePrincipal.cp.guardar(FramePrincipal.cp.getLg());
                Generatabla1();
                CrearLista();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "\nEN EL \"TOTAL\" y \"METROS\" SOLO ADMITEN NUMEROS CON DECIMALES, SI DESEA QUE NO TENGA DATOS SOLO INSERTE 0.\n LOS DECIMALES SE ANOTAN CON UN \".\" EJEMPLO: 1.2");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "EN CANTIDAD INGRESE SOLO NUMEROS ENTEROS");
        }
    }
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
            java.util.logging.Logger.getLogger(FrameGastos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameGastos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameGastos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameGastos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameGastos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BCancelar;
    private javax.swing.JButton BEliminar;
    private javax.swing.JButton BEmpEditar;
    private javax.swing.JButton BEmpIngresar;
    private javax.swing.JButton BLimpiar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField pantApliqueColor;
    private javax.swing.JTextField pantApliqueMetros;
    private javax.swing.JTextField pantApliqueTotal;
    private javax.swing.JTextField pantBordarCantidad;
    private javax.swing.JTextField pantBordarTotal;
    private javax.swing.JLabel pantFecha;
    private javax.swing.JLabel pantFecha1;
    private javax.swing.JLabel pantFecha2;
    private javax.swing.JLabel pantFecha3;
    private javax.swing.JLabel pantFecha4;
    private javax.swing.JLabel pantFecha5;
    private javax.swing.JLabel pantFecha6;
    private javax.swing.JTextField pantOtrosMetros;
    private javax.swing.JTextField pantOtrosNombre;
    private javax.swing.JTextField pantOtrosTotal;
    private javax.swing.JTextField pantPellonMetros;
    private javax.swing.JTextField pantPellonTotal;
    private javax.swing.JTextField pantSedaCantidad;
    private javax.swing.JTextField pantSedaTotal;
    private javax.swing.JTextField pantServiciosTotal;
    // End of variables declaration//GEN-END:variables
@Override
    public Image getIconImage(){
        Image retValue=Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("imagenes/engranaje3_1.png"));
        return retValue;
    }
}
