
package pkg00390aw;
import java.awt.Desktop;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import jxl.read.biff.BiffException;

public class FrameImportar extends javax.swing.JFrame {

    private String path;
    
    public FrameImportar() {
        initComponents();
        setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
        this.dispose();
        
    }
    public void ocultarAbrir(){
        jButton2.setVisible(false);
    }
    public void ocultarExcel(){
        jButton1.setVisible(false);
    }
    
    public FrameImportar(String path) {
        initComponents();
        setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
        this.dispose();
        this.path=path;
        llenarTabla();
    }
    public void reiniciarTabla() {
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "LISTA DE REGISTROS"
                }
        ));
    }
    public void Generatabla1(){
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.addRow(new Object[]{""});
    }
    
    public void llenarTabla(){
        System.out.println(this.path);
        File a=new File(this.path);
        File b[]=a.listFiles();
        for(int i=0;i<b.length;i++){
            Generatabla1();
            this.jTable1.setValueAt(b[i].getName(), i, 0);
        }
        //System.out.println(w.getCp().getFecha());
        
    }

    /*
        String as="sdfse_dsg.xls";
        String nom="dsg.xls";
        System.out.println(as.substring(as.length()-nom.length()).equals(nom));
        System.out.println(as.substring(0, as.length()-nom.length())+nom);
    */
    public void llenarTabla(String nombre) {
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "LISTA DE REGISTROS"
                }
        ));
        String nom = nombre + ".xls";
        System.out.println(nom);
        File a = new File(this.path);
        File b[]=a.listFiles();
        int j=0;
        for(int i=0;i<b.length;i++){
            String as=b[i].getPath();
            if(as.substring(as.length()-nom.length()).equals(nom)){
                File c=new File(as.substring(0, as.length()-nom.length())+nom);
                if(c.exists()){
                    Generatabla1();
                    this.jTable1.setValueAt(c.getName(), j, 0);
                    j++;
                }
            }
        }
    }
    
    private int sw=0;

    public int getSw() {
        return sw;
    }

    public void setSw(int sw) {
        this.sw = sw;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        BEliminar = new javax.swing.JButton();
        BAtras = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "LISTA DE REGISTROS"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(49, 61, 273, 163));

        jLabel1.setFont(new java.awt.Font("Bookman Old Style", 2, 18)); // NOI18N
        jLabel1.setText("REGISTROS ANTERIORES");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(49, 11, 273, 39));

        jButton1.setFont(new java.awt.Font("Bookman Old Style", 3, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/excel.png"))); // NOI18N
        jButton1.setText("ABRIR CON EXCEL");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, 220, 40));

        BEliminar.setFont(new java.awt.Font("Bookman Old Style", 3, 14)); // NOI18N
        BEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/action_undo.png"))); // NOI18N
        BEliminar.setText("ELIMINAR");
        BEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BEliminarActionPerformed(evt);
            }
        });
        getContentPane().add(BEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 160, 50));

        BAtras.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        BAtras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cancel2.png"))); // NOI18N
        BAtras.setText("CERRAR");
        BAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BAtrasActionPerformed(evt);
            }
        });
        getContentPane().add(BAtras, new org.netbeans.lib.awtextra.AbsoluteConstraints(235, 328, 130, 43));

        jButton2.setFont(new java.awt.Font("Bookman Old Style", 3, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/download (1).png"))); // NOI18N
        jButton2.setText("ABRIR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 240, 160, 50));

        jLabel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 5, true));
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 380, 400));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //this.dispose();
        abrir();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void BEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BEliminarActionPerformed
        
        eliminar();
        reiniciarTabla();
        llenarTabla();
    }//GEN-LAST:event_BEliminarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String dir=path;
        int fila=this.jTable1.getSelectedRow();
        dir=path+this.jTable1.getValueAt(fila, 0);
        if(sw==1){//gestion
            FrameGesstion f=new FrameGesstion();
            try {
                f.importarExcell(dir);
                f.show(true);
                this.dispose();
            } catch (IOException ex) {
                Logger.getLogger(FrameImportar.class.getName()).log(Level.SEVERE, null, ex);
            } catch (BiffException ex) {
                Logger.getLogger(FrameImportar.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        if(sw==2){
            FrameGestionPersonal a=new FrameGestionPersonal();
            try {
                a.importarExcel(dir);
                a.vistaImportado(dir);
                a.show(true);
                this.dispose();
            } catch (IOException ex) {
                Logger.getLogger(FrameImportar.class.getName()).log(Level.SEVERE, null, ex);
            } catch (BiffException ex) {
                Logger.getLogger(FrameImportar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(sw==3){
            FrameTablaMostrar a=new FrameTablaMostrar();
            try {
                a.importarExcel(dir);
                a.sumar();
                a.show(true);
                this.dispose();
            } catch (IOException ex) {
                Logger.getLogger(FrameImportar.class.getName()).log(Level.SEVERE, null, ex);
            } catch (BiffException ex) {
                Logger.getLogger(FrameImportar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(sw==4){
            FrameRegistroCliente a=new FrameRegistroCliente();
            try {
                a.importarExcel(dir);
                a.show(true);
                this.dispose();
            } catch (IOException ex) {
                Logger.getLogger(FrameImportar.class.getName()).log(Level.SEVERE, null, ex);
            } catch (BiffException ex) {
                Logger.getLogger(FrameImportar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(sw==5){
            try {
                FrameFacturaCliente ff=new FrameFacturaCliente();
                ff.importarExcel(dir);
                ff.show(true);
            } catch (IOException ex) {
                Logger.getLogger(FrameImportar.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FrameImportar.class.getName()).log(Level.SEVERE, null, ex);
            } catch (BiffException ex) {
                Logger.getLogger(FrameImportar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(sw==6){
            try {
                FrameGestionMensual gm=new FrameGestionMensual();
                gm.ocultarBotones();
                gm.mostrarContenido(dir);
                gm.show(true);
            } catch (IOException ex) {
                Logger.getLogger(FrameImportar.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FrameImportar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void BAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BAtrasActionPerformed
        this.dispose();
        /*if(sw==1){
            FrameGesstion a=new FrameGesstion();
            a.show(true);
            this.dispose();
        }else if(sw==2){
            FrameGestionPersonal a=new FrameGestionPersonal();
            a.show(true);
            this.dispose();
        }*/
    }//GEN-LAST:event_BAtrasActionPerformed

    public void abrir(){
        String dir=path;
        int fila=this.jTable1.getSelectedRow();
        dir=path+this.jTable1.getValueAt(fila, 0);
        try {

            File objetofile = new File(dir);
            Desktop.getDesktop().open(objetofile);

        } catch (IOException ex) {

            System.out.println(ex);
        }
    }
    
    public void eliminar(){
        String dir=path;
        int fila=this.jTable1.getSelectedRow();
        dir=path+this.jTable1.getValueAt(fila, 0);
        File file=new File(dir);
        file.delete();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
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
            java.util.logging.Logger.getLogger(FrameImportar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameImportar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameImportar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameImportar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameImportar().setVisible(true);
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
    private javax.swing.JButton BAtras;
    private javax.swing.JButton BEliminar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
