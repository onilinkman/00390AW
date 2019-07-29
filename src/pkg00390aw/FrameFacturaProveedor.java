/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg00390aw;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

/**
 *
 * @author WINDOWS 10
 */
public class FrameFacturaProveedor extends javax.swing.JFrame {

    /**
     * Creates new form FrameFacturaProveedor
     */
    public FrameFacturaProveedor() throws IOException, ClassNotFoundException {
        initComponents();
        this.setLocationRelativeTo(null);
        File f = new File("facturaProveedores.dat");
        lf = new ListaFacturaProveedor();
        bonificacion = 0.0;
        if (f.exists()) {
            lf = lf.recuperar();
        }
        llenarTabla();

        this.lsd = new ListaStockDatos();

        File f1 = new File("ListaStockDatos.dat");
        if (f1.exists()) {
            this.lsd = lsd.recuperar();
        }
        jButton7.setVisible(false);
        jButton5.setVisible(false);
        jButton6.setVisible(false);
        File archi=new File(".\\FacturaProveedor\\");
        archi.mkdirs();
    }

    private ListaStockDatos lsd;
    private ListaFacturaProveedor lf;
    private double bonificacion;
    private int posicion;

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        pantProveedor = new javax.swing.JTextField();
        pantNit = new javax.swing.JTextField();
        pantNroFactura = new javax.swing.JTextField();
        pantNroAutorizacion = new javax.swing.JTextField();
        pantCodigoControl = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        pantNoSujeto = new javax.swing.JTextField();
        pantTotalCompra = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        pantFecha = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        jLabel1.setText("Nombre de proveedor");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 11, 170, -1));

        jLabel2.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        jLabel2.setText("NIT");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, 31, -1));

        jLabel3.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        jLabel3.setText("Nro Factura");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 10, 110, -1));

        jLabel4.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        jLabel4.setText("Nro de Autorizacion");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 10, 170, -1));

        jLabel5.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        jLabel5.setText("Codigo de control");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 10, 150, 10));

        jLabel6.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        jLabel6.setText("Total de compra(A)");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 10, -1, -1));
        getContentPane().add(pantProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 31, 121, -1));
        getContentPane().add(pantNit, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 30, 95, -1));
        getContentPane().add(pantNroFactura, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 30, 94, -1));
        getContentPane().add(pantNroAutorizacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 30, 114, -1));
        getContentPane().add(pantCodigoControl, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 30, 111, 20));

        jLabel7.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        jLabel7.setText("NO SUJETO (B)");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 60, 111, 10));
        getContentPane().add(pantNoSujeto, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 80, 111, 20));
        getContentPane().add(pantTotalCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 30, 112, -1));

        jLabel8.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        jLabel8.setText("Fecha de compra");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 60, 150, -1));
        getContentPane().add(pantFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 80, 110, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {
                "esp", "Contador", "fecha", "NIT","Nombre de proveedor","Num de factura","Con (0)","Nro. autorizacion","Total de compra","No Sujeto","subtotal","bonificacion","base","fiscal","Codigo de control","tp"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 157, 1050, 130));

        jButton1.setText("VER GUARDADOS");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 350, -1, -1));

        jButton2.setText("INGRESAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 350, -1, -1));

        jButton3.setText("EDITAR");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 350, -1, -1));

        jButton4.setText("EXPORTAR A EXCEL");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 300, -1, 40));

        jButton5.setText("ELIMINAR");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 350, 130, -1));

        jButton6.setText("ACEPTAR EDICION");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 350, 130, -1));

        jButton7.setText("CANCELAR");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 350, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (!this.pantNroFactura.getText().equals("")) {
            if (!this.pantNroAutorizacion.getText().equals("")) {
                agregar();
                reiniciarTabla();
                llenarTabla();

                this.lf.guardar(lf);
            } else {
                JOptionPane.showMessageDialog(null, "ES NECESARIO LLENAR EL NRO DE AUTORIZACION");
            }
        } else {
            JOptionPane.showMessageDialog(null, "ES NECESARIO LLENAR EL NUMERO DE FACTURA");
        }
    }//GEN-LAST:event_jButton2ActionPerformed


    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        editar();
        jButton7.setVisible(true);
        jButton5.setVisible(true);
        jButton6.setVisible(true);
        jButton3.setVisible(false);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        elimina();
        reiniciarTabla();
        llenarTabla();
        this.lf.guardar(lf);
        jButton7.setVisible(!true);
        jButton5.setVisible(!true);
        jButton6.setVisible(!true);
        jButton3.setVisible(!false);
        JOptionPane.showMessageDialog(null, "ELEMENTO ELIMINADO CORRECTAMENTE");
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        aceptarEdicion();
        reiniciarTabla();
        llenarTabla();
        jButton7.setVisible(!true);
        jButton5.setVisible(!true);
        jButton6.setVisible(!true);
        jButton3.setVisible(!false);
        JOptionPane.showMessageDialog(null, "SE GUARDARON LOS CAMBIOS!");
        this.lf.guardar(lf);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        jButton7.setVisible(!true);
        jButton5.setVisible(!true);
        jButton6.setVisible(!true);
        jButton3.setVisible(!false);
        this.pantProveedor.setText("");
        this.pantNit.setText("");
        this.pantNroFactura.setText("");
        this.pantNroAutorizacion.setText("");
        this.pantCodigoControl.setText("");
        this.pantNoSujeto.setText("");
        this.pantTotalCompra.setText("");
        this.pantFecha.setText("");
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try {
            guardarExcel();
            JOptionPane.showMessageDialog(null, "SE GUARDO CORRECTAMENTE");
        } catch (IOException ex) {
            Logger.getLogger(FrameFacturaProveedor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (WriteException ex) {
            Logger.getLogger(FrameFacturaProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        File archi=new File(".\\FacturaProveedor\\");
        try {
            Desktop.getDesktop().open(archi);
        } catch (IOException ex) {
            Logger.getLogger(FrameFacturaProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed
//***********************GUARDAR EN EXCEL**********************
    public void guardarExcel() throws IOException, WriteException{
        WritableWorkbook libro1=Workbook.createWorkbook(new File(".\\FacturaProveedor\\facturasProveedores__"+ObtenerFecha()+".xls"));
        WritableSheet hoja1=libro1.createSheet("FacturasProveedor", 0);
        
        Label etiqueta=new Label(0,0,"esp");
        hoja1.addCell(etiqueta);
        etiqueta=new Label(1,0,"CONTADOR");
        hoja1.addCell(etiqueta);
        etiqueta=new Label(2,0,"FECHA");
        hoja1.addCell(etiqueta);
        etiqueta=new Label(3,0,"NIT");
        hoja1.addCell(etiqueta);
        etiqueta=new Label(4,0,"NOMBRE DEL PROVEEDOR");
        hoja1.addCell(etiqueta);
        etiqueta=new Label(5,0,"NUMERO DE FACTURA");
        hoja1.addCell(etiqueta);
        etiqueta=new Label(6,0,"CON (0)");
        hoja1.addCell(etiqueta);
        etiqueta=new Label(7,0,"Nro. AUTORIZACION");
        hoja1.addCell(etiqueta);
        etiqueta=new Label(8,0,"TOTAL DE COMPRA");
        hoja1.addCell(etiqueta);
        etiqueta=new Label(9,0,"No SUJETO");
        hoja1.addCell(etiqueta);
        etiqueta=new Label(10,0,"SUBTOTAL");
        hoja1.addCell(etiqueta);
        etiqueta=new Label(11,0,"BONIFICACION");
        hoja1.addCell(etiqueta);
        etiqueta=new Label(12,0,"BASE");
        hoja1.addCell(etiqueta);
        etiqueta=new Label(13,0,"FISCAL");
        hoja1.addCell(etiqueta);
        etiqueta=new Label(14,0,"CODIGO DE CONTROL");
        hoja1.addCell(etiqueta);
        etiqueta=new Label(15,0,"tp");
        hoja1.addCell(etiqueta);
        
        int filas=this.jTable1.getRowCount();
        int columnas=this.jTable1.getColumnCount();
        for(int i=1;i<=filas;i++){
            for(int j=0;j<columnas;j++){
                etiqueta=new Label(j,i,this.jTable1.getValueAt(i-1, j).toString());
                hoja1.addCell(etiqueta);
            }
        }
        libro1.write();
        libro1.close();
    }
    
    public String ObtenerFecha() {
        String fecha = "";
        Calendar calendario = Calendar.getInstance();
        int dia = calendario.get(Calendar.DAY_OF_MONTH);
        int mes = calendario.get(Calendar.MONTH) + 1;
        int anio = calendario.get(Calendar.YEAR);
        int hora = calendario.get(Calendar.HOUR_OF_DAY);
        int minuto = calendario.get(Calendar.MINUTE);
        int segundo = calendario.get(Calendar.SECOND);
        fecha = dia + "_" + mes + "_" + anio + "_" + hora + "" + minuto + "" + segundo;

        return fecha;
    }

//***********************EDITAR ELEMENTO*********************

    public void editar() {
        int fila = this.jTable1.getSelectedRow();
        this.pantProveedor.setText(this.jTable1.getValueAt(fila, 4).toString());
        this.pantNit.setText(this.jTable1.getValueAt(fila, 3).toString());
        this.pantNroFactura.setText(this.jTable1.getValueAt(fila, 5).toString());
        this.pantNroAutorizacion.setText(this.jTable1.getValueAt(fila, 7).toString());
        this.pantCodigoControl.setText(this.jTable1.getValueAt(fila, 14).toString());
        this.pantNoSujeto.setText(this.jTable1.getValueAt(fila, 9).toString());
        this.pantTotalCompra.setText(this.jTable1.getValueAt(fila, 8).toString());
        this.pantFecha.setText(this.jTable1.getValueAt(fila, 2).toString());
    }

    public void aceptarEdicion() {
        int fila = this.jTable1.getSelectedRow();
        FacturaProveedor p = new FacturaProveedor();
        p.setBonificacion(bonificacion);
        p.setFecha(this.pantFecha.getText());
        p.setNit(this.pantNit.getText());
        p.setNombreProveedor(this.pantProveedor.getText());
        p.setNroFactura(this.pantNroFactura.getText());
        p.setNroAutorizacion(this.pantNroAutorizacion.getText());
        p.setTotalCompra(Double.valueOf(this.pantTotalCompra.getText()));
        try {
            p.setNoSujeto(Double.valueOf(this.pantNoSujeto.getText()));
        } catch (Exception e) {
            System.out.println(e);
            p.setNoSujeto(0);
        }
        if (!this.pantCodigoControl.getText().equals("")) {
            p.setCodigoControl(this.pantCodigoControl.getText());
        } else {
            p.setCodigoControl("0");
        }
        p.calcular();
        Queue<FacturaProveedor> aux = new LinkedList();
        int i = 0;
        while (!this.lf.p.isEmpty()) {
            FacturaProveedor ff = this.lf.p.remove();
            if (fila != i) {
                aux.add(ff);
            } else {
                aux.add(p);
            }
            i++;
        }
        this.lf.p.addAll(aux);
    }

//***********************ELIMINAR ELEMENTO*******************
    public void elimina() {
        int fila = this.jTable1.getSelectedRow();
        Queue<FacturaProveedor> aux = new LinkedList();
        int i = 0;
        while (!this.lf.p.isEmpty()) {
            FacturaProveedor ff = this.lf.p.remove();
            if (fila != i) {
                System.out.println(fila);
                aux.add(ff);
            }
            i++;
        }
        this.lf.p.addAll(aux);
    }

//***********************LLENAR TABLA************************
    public void llenarTabla() {
        Queue<FacturaProveedor> aux = new LinkedList();
        aux.addAll(lf.p);
        int fila = 0;
        while (!aux.isEmpty()) {
            Generatabla1();
            FacturaProveedor p = aux.remove();
            this.jTable1.setValueAt("1", fila, 0);
            this.jTable1.setValueAt((fila + 1), fila, 1);
            this.jTable1.setValueAt(p.getFecha(), fila, 2);
            this.jTable1.setValueAt(p.getNit(), fila, 3);
            this.jTable1.setValueAt(p.getNombreProveedor(), fila, 4);
            this.jTable1.setValueAt(p.getNroFactura(), fila, 5);
            this.jTable1.setValueAt("0", fila, 6);
            this.jTable1.setValueAt(p.getNroAutorizacion(), fila, 7);
            this.jTable1.setValueAt(p.getTotalCompra(), fila, 8);
            this.jTable1.setValueAt(p.getNoSujeto(), fila, 9);
            this.jTable1.setValueAt(p.getSubTotal(), fila, 10);
            this.jTable1.setValueAt(p.getBonificacion(), fila, 11);
            this.jTable1.setValueAt(p.getBase(), fila, 12);
            this.jTable1.setValueAt(p.getFiscal(), fila, 13);
            this.jTable1.setValueAt(p.getCodigoControl(), fila, 14);
            this.jTable1.setValueAt("1", fila, 15);
            fila++;
        }
    }

    //***********************agregar fila************************
    public void Generatabla1() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.addRow(new Object[]{""});
    }

    //***********************REINICIAR TABLA*********************
    public void reiniciarTabla() {
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "esp", "Contador", "fecha", "NIT", "Nombre de proveedor", "Num de factura", "Con (0)", "Nro. autorizacion", "Total de compra", "No Sujeto", "subtotal", "bonificacion", "base", "fiscal", "Codigo de control", "tp"
                }
        ));
    }

    //***********************AGREGAR FACTURA*********************
    public void agregar() {
        FacturaProveedor p = new FacturaProveedor();
        p.setBonificacion(bonificacion);
        p.setFecha(this.pantFecha.getText());
        p.setNit(this.pantNit.getText());
        p.setNombreProveedor(this.pantProveedor.getText());
        p.setNroFactura(this.pantNroFactura.getText());
        p.setNroAutorizacion(this.pantNroAutorizacion.getText());
        p.setTotalCompra(Double.valueOf(this.pantTotalCompra.getText()));
        try {
            p.setNoSujeto(Double.valueOf(this.pantNoSujeto.getText()));
        } catch (Exception e) {
            System.out.println(e);
            p.setNoSujeto(0);
        }
        if (!this.pantCodigoControl.getText().equals("")) {
            p.setCodigoControl(this.pantCodigoControl.getText());
        } else {
            p.setCodigoControl("0");
        }
        p.calcular();
        this.lf.p.add(p);
    }

    //***********************************************
    public void enviarDatos(String proveedor, double totalCompra, String fecha) {
        this.pantProveedor.setText(proveedor);
        this.pantNit.setText(hallarNit(proveedor));
        this.pantTotalCompra.setText(totalCompra + "");
        this.pantFecha.setText(fecha);
        this.pantNoSujeto.setText("0");
    }

    //*****************************************
    public String hallarNit(String proveedor) {
        String ci = "";
        NodoRegistroCliente w = FramePrincipal.cp.getLrc().getP();
        while (w != null) {
            if (proveedor.equalsIgnoreCase(w.getRegistro().getNombre())) {
                ci = w.getRegistro().getCi();
            }
            w = w.getSig();
        }
        return ci;
    }
    //***********************************************************

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
            java.util.logging.Logger.getLogger(FrameFacturaProveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameFacturaProveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameFacturaProveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameFacturaProveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new FrameFacturaProveedor().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(FrameFacturaProveedor.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(FrameFacturaProveedor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField pantCodigoControl;
    private javax.swing.JTextField pantFecha;
    private javax.swing.JTextField pantNit;
    private javax.swing.JTextField pantNoSujeto;
    private javax.swing.JTextField pantNroAutorizacion;
    private javax.swing.JTextField pantNroFactura;
    private javax.swing.JTextField pantProveedor;
    private javax.swing.JTextField pantTotalCompra;
    // End of variables declaration//GEN-END:variables
}
