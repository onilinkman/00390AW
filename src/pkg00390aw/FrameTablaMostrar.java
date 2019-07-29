
package pkg00390aw;

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

public class FrameTablaMostrar extends javax.swing.JFrame {

    public FrameTablaMostrar() {
        initComponents();
        this.setLocationRelativeTo(null);
        setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
        this.dispose();
        //s=sn;
        //llenarTabla(sn);
        //Sumar(sn);
    }
    public FrameTablaMostrar(NodoCliente sn) {
        initComponents();
        setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
        this.dispose();
        this.sn=sn;
        llenarTabla(sn);
        Sumar(sn);
    }
    public NodoCliente sn;
    
    public void Sumar(NodoCliente s){
        double Saldo=0;
        double Total=0;
        NodoCliente aux=s;
        DecimalFormat df=new DecimalFormat("#.00");
        while(aux!=null){
            Saldo=Saldo+aux.getCliente().getSaldo();
            Total=Total+aux.getCliente().getTotal();
            aux=aux.getSig();
        }
        this.pantSaldo.setText(df.format(Saldo));
        this.pantSuma.setText(df.format(Total));
    }
    
    public void sumar(){
        int filas=this.jTable1.getRowCount();
        double sumaSaldo=0;
        double sumaTotal=0;
        DecimalFormat df=new DecimalFormat("#.00");
        
        for(int i=0;i<filas;i++){
            sumaSaldo=sumaSaldo+Double.valueOf(this.jTable1.getValueAt(i, 8).toString());
            sumaTotal=sumaTotal+Double.valueOf(this.jTable1.getValueAt(i, 9).toString());
        }
        this.pantSaldo.setText(df.format(sumaSaldo));
        this.pantSuma.setText(df.format(sumaTotal));
    }
    
    public void llenarTabla(NodoCliente s){
        NodoCliente aux=s;
        int i=0;
        while(aux!=null){
            Generatabla1();
            agregarATabla(aux.getCliente(),i);
            i++;
            aux=aux.getSig();
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
        
        jTable1.setValueAt(fechaIngreso, i, 0);
        jTable1.setValueAt(fechaEntrega, i, 1);
        jTable1.setValueAt(cliente, i, 2);
        
        
        
        jTable1.setValueAt(nobreDisenio, i, 3);
        jTable1.setValueAt(nombrePrenda, i, 4);
        jTable1.setValueAt(cantidad, i, 5);
        jTable1.setValueAt(CostoUnitarion, i, 6);
        jTable1.setValueAt(adelanto, i, 7);
        jTable1.setValueAt(saldo, i, 8);
        jTable1.setValueAt(total, i, 9);
        jTable1.setValueAt(encargado, i, 10);
        jTable1.setValueAt(nroMaquina, i, 11);
        jTable1.setValueAt(c.getTamanio(), i, 12);
    }
    
    public void importarExcel(String dir) throws IOException, BiffException{
        reiniciarTabla();
        Workbook workbook=Workbook.getWorkbook(new File(dir));
        Sheet hoja1=workbook.getSheet(0);
        for(int fila=1;fila<hoja1.getRows();fila++){
            Generatabla1();
            for(int columna=0;columna<hoja1.getColumns();columna++){
                String dato=hoja1.getCell(columna, fila).getContents();
                this.jTable1.setValueAt(dato, fila-1, columna);
            }
        }
        this.jButton1.setVisible(false);
        this.jButton2.setVisible(false);
    }
    
    public void reiniciarTabla() {
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "FECHA INGRESO", "FECHA ENTREGA", "CLIENTE", "DISEÑO", "PRENDA", "CANTIDAD", "COSTO UNITARIO", "ADELANTO", "SALDO", "TOTAL", "ENCARGADO", "Nro MAQUINA", "TAMAÑO"
                }
        ));
    }

    public NodoCliente getSn() {
        return sn;
    }

    public void setSn(NodoCliente sn) {
        this.sn = sn;
    }

    
    public void Generatabla1(){
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.addRow(new Object[]{""});
            
    }

    public String MaquinaEmpleado(String empleado) {
        String maquina = "";
        NodoPersonal w = FramePrincipal.cp.getLp().getP();
        while (w != null) {
            if (w.getPersonal().getNombre().equals(empleado)) {
                maquina = w.getPersonal().getNumMaquina();
            }
            w = w.getSig();
        }
        return maquina;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        pantSaldo = new javax.swing.JTextField();
        pantSuma = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "FECHA INGRESO", "FECHA ENTREGA", "CLIENTE","DISEÑO","PRENDA","CANTIDAD","COSTO UNITARIO","ADELANTO","SALDO","TOTAL","ENCARGADO","Nro MAQUINA","TAMAÑO"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 1000, 140));

        pantSaldo.setEditable(false);
        getContentPane().add(pantSaldo, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 190, 110, 40));

        pantSuma.setEditable(false);
        getContentPane().add(pantSuma, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 190, 120, 40));

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/excel.png"))); // NOI18N
        jButton1.setText("EXPORTAR A EXCEL");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 280, 220, 50));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel2.setText("SUMA DE SALDOS:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 190, 180, 40));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel3.setText("SUMA DE TOTAL:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 190, 160, 40));
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1030, 270));

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cancel2.png"))); // NOI18N
        jButton2.setText("CERRAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 280, 190, 60));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            guardarAExcel();
            JOptionPane.showMessageDialog(null, "SE GUARDO CORRECTAMENTE");
        } catch (IOException ex) {
            Logger.getLogger(FrameTablaMostrar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (WriteException ex) {
            Logger.getLogger(FrameTablaMostrar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    public String ObtenerFecha(){
        String fecha="";
        Calendar calendario=Calendar.getInstance();
        int dia=calendario.get(Calendar.DAY_OF_MONTH);
        int mes=calendario.get(Calendar.MONTH)+1;
        int anio=calendario.get(Calendar.YEAR);
        int hora=calendario.get(Calendar.HOUR_OF_DAY);
        int minuto=calendario.get(Calendar.MINUTE);
        int segundo=calendario.get(Calendar.SECOND);
        fecha=dia+"_"+mes+"_"+anio+"_"+hora+"_"+minuto+"_"+segundo;
        
        return fecha;
    }
    
    public void guardarAExcel() throws IOException, WriteException{
        int nroCas=this.jTable1.getRowCount();
        WritableWorkbook libro1 = Workbook.createWorkbook(new File(".\\IngresosExportados\\Resumen"+ObtenerFecha()+".xls"));
        WritableSheet hoja1 = libro1.createSheet("DATOS", 0);

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
        etiqueta = new Label(12, 0, "TAMAÑO");
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
            etiqueta = new Label(7, i, this.jTable1.getValueAt(i - 1, 7).toString());
            hoja1.addCell(etiqueta);
            etiqueta = new Label(8, i, this.jTable1.getValueAt(i - 1, 8).toString());
            hoja1.addCell(etiqueta);
            etiqueta = new Label(9, i, this.jTable1.getValueAt(i - 1, 9).toString());
            hoja1.addCell(etiqueta);
            etiqueta = new Label(10, i, this.jTable1.getValueAt(i - 1, 10).toString());
            hoja1.addCell(etiqueta);
            etiqueta = new Label(11, i, this.jTable1.getValueAt(i - 1, 11).toString());
            hoja1.addCell(etiqueta);
            etiqueta = new Label(12, i, this.jTable1.getValueAt(i - 1, 12).toString());
            hoja1.addCell(etiqueta);
        }
        libro1.write();
        libro1.close();
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
            java.util.logging.Logger.getLogger(FrameTablaMostrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameTablaMostrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameTablaMostrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameTablaMostrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameTablaMostrar().setVisible(true);
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField pantSaldo;
    private javax.swing.JTextField pantSuma;
    // End of variables declaration//GEN-END:variables
}
