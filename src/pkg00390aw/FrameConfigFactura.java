/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg00390aw;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author WINDOWS 10
 */
public class FrameConfigFactura extends javax.swing.JFrame {

    /**
     * Creates new form FrameConfigFactura
     */
    public FrameConfigFactura() throws IOException, ClassNotFoundException {
        initComponents();
        File f1 = new File("factura.dat");
        if (f1.exists()) {
            f = f.recuperar();
            mostrar();
        }
        this.setLocationRelativeTo(null);
    }

    public void mostrar() {
        this.pantEmpresa.setText(f.getNombreEmpresa());
        this.pantDireccion.setText(f.getDireccion());
        this.pantNit.setText(f.getNit());
        this.pantAutorizacion.setText(f.getNroAutorizacion());
        this.pantActividad.setText(f.getDetalle());
        this.pantNota.setText(f.getNota());
        this.pantClave.setText(f.getClave());
        this.pantFecha.setText(f.getFechaEmision());

    }

    private ConfigFactura f = new ConfigFactura();
    private String admin;

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        pantEmpresa = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        pantNit = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        pantActividad = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        pantAutorizacion = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        pantNota = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        pantClave = new javax.swing.JTextField();
        pantFecha = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        pantDireccion = new javax.swing.JTextArea();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("CONFIGURACION FACTURA");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 10, -1, -1));

        jLabel2.setText("Nombre empresa:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));
        getContentPane().add(pantEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 170, -1));

        jLabel3.setText("Nit:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 70, -1));
        getContentPane().add(pantNit, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 170, -1));

        jLabel4.setText("Detalle de actividad:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, -1));
        getContentPane().add(pantActividad, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 170, -1));

        jLabel5.setText("Direccion:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 50, -1, -1));

        jLabel6.setText("Nro. de autorizacion:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 210, -1, -1));
        getContentPane().add(pantAutorizacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 230, 180, -1));

        jLabel7.setText("Nota:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 120, -1));
        getContentPane().add(pantNota, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 170, -1));

        jLabel8.setText("Clave:");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 280, 50, -1));

        jLabel9.setText("Fecha limite de emision");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 270, -1, -1));
        getContentPane().add(pantClave, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 300, 100, -1));
        getContentPane().add(pantFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 300, 120, -1));

        jButton1.setText("GUARDAR Y REGRESAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 330, -1, -1));

        pantDireccion.setColumns(20);
        pantDireccion.setRows(5);
        jScrollPane1.setViewportView(pantDireccion);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 70, 190, 120));

        jButton2.setText("REGRESAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 340, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        f.setNombreEmpresa(this.pantEmpresa.getText());
        f.setDireccion(this.pantDireccion.getText());
        f.setNit(this.pantNit.getText());
        f.setNroAutorizacion(this.pantAutorizacion.getText());
        f.setDetalle(this.pantActividad.getText());
        f.setNota(this.pantNota.getText());
        f.setClave(this.pantClave.getText());
        f.setFechaEmision(this.pantFecha.getText());
        f.guardar(f);
        JOptionPane.showMessageDialog(null, "SE GUARDO CORRECTAMENTE");
        try {
            FrameVentaCaja vc = new FrameVentaCaja();
            vc.setAdministrador(this.admin);
            vc.show(true);
            this.dispose();
        } catch (IOException ex) {
            Logger.getLogger(FrameConfigFactura.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FrameConfigFactura.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(FrameConfigFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            FrameVentaCaja vc = new FrameVentaCaja();
            vc.setAdministrador(this.admin);
            vc.show(true);
            this.dispose();
        } catch (IOException ex) {
            Logger.getLogger(FrameConfigFactura.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FrameConfigFactura.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(FrameConfigFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(FrameConfigFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameConfigFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameConfigFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameConfigFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new FrameConfigFactura().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(FrameConfigFactura.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(FrameConfigFactura.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField pantActividad;
    private javax.swing.JTextField pantAutorizacion;
    private javax.swing.JTextField pantClave;
    private javax.swing.JTextArea pantDireccion;
    private javax.swing.JTextField pantEmpresa;
    private javax.swing.JTextField pantFecha;
    private javax.swing.JTextField pantNit;
    private javax.swing.JTextField pantNota;
    // End of variables declaration//GEN-END:variables
}
