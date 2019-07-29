package pkg00390aw;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class FrameLogin extends javax.swing.JFrame {

    public FrameLogin() {
        initComponents();
        setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
        this.dispose();
        this.setLocationRelativeTo(null);
        if (permiso()) {
            crearComboBox();
            //this.jLabel1.setText("SELECCIONE NUEVO USUARIO A DAR PERMISO");
            this.pantUsuario.setVisible(false);
        } else {
            this.jComboBox1.setVisible(false);
        }
    }

    public void crearComboBox() {
        NodoPersonal aux = FramePrincipal.cp.getLp().getP();
        while (aux != null) {
            this.jComboBox1.addItem(aux.getPersonal().getNombre());
            aux = aux.getSig();
        }

    }

    private int sw = 0;

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        pantUsuario = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        pantContrasenia = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("USUARIO");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 40, 130, 27));

        pantUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pantUsuarioActionPerformed(evt);
            }
        });
        getContentPane().add(pantUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 70, 150, 40));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("CONTRASEÑA");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, 150, 30));

        pantContrasenia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pantContraseniaActionPerformed(evt);
            }
        });
        getContentPane().add(pantContrasenia, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 140, 150, 40));

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/log_in.png"))); // NOI18N
        jButton1.setText("ACCEDER");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 200, 160, 40));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {  }));
        getContentPane().add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 200, 40));

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/back.png"))); // NOI18N
        jButton2.setText("ATRAS");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, -1, 40));

        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 70, 30));
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 280, 300));

        jLabel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 5, true));
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 285, 300));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (permiso()) {
            NodoPersonal w = FramePrincipal.cp.getLp().getP();
            while (w != null) {
                if (w.getPersonal().getNombre().equals(this.jComboBox1.getSelectedItem().toString())) {
                    try {
                        sinTexto(w.getPersonal().isAutorizacion() == true);
                    } catch (IOException ex) {
                        Logger.getLogger(FrameLogin.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(FrameLogin.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                w = w.getSig();
            }
        } else {
            try {
                conTexto();
            } catch (IOException ex) {
                Logger.getLogger(FrameLogin.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FrameLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void pantUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pantUsuarioActionPerformed
        this.pantContrasenia.requestFocus();
    }//GEN-LAST:event_pantUsuarioActionPerformed

    private void pantContraseniaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pantContraseniaActionPerformed
        if (permiso()) {
            NodoPersonal w = FramePrincipal.cp.getLp().getP();
            while (w != null) {
                if (w.getPersonal().getNombre().equals(this.jComboBox1.getSelectedItem().toString())) {
                    try {
                        sinTexto(w.getPersonal().isAutorizacion() == true);
                    } catch (IOException ex) {
                        Logger.getLogger(FrameLogin.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(FrameLogin.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                w = w.getSig();
            }
        } else {
            try {
                conTexto();
            } catch (IOException ex) {
                Logger.getLogger(FrameLogin.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FrameLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_pantContraseniaActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            FramePrincipal a = new FramePrincipal();
            a.show(true);

            this.dispose();
        } catch (IOException ex) {
            Logger.getLogger(FrameLogin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FrameLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private int cont = 0;
    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        if (cont == 5) {
            FrameRecuperaContrasenia a = new FrameRecuperaContrasenia();
            a.show(true);
        } else {
            cont++;
        }
        System.out.println(cont);
    }//GEN-LAST:event_jLabel5MouseClicked
    public void sinTexto(boolean a) throws IOException, ClassNotFoundException {
        if (a) {
            if (FramePrincipal.cp.getV().getContrasenia().equals(this.pantContrasenia.getText())) {
                if (sw == 1) {
                    FramePersonal as = new FramePersonal();
                    as.show(true);
                    this.dispose();
                } else if (sw == 2) {
                    FrameGestion g = new FrameGestion();
                    g.show(true);
                    this.dispose();
                } else if (sw == 3) {
                    FrameNuevoUsuario f = new FrameNuevoUsuario();
                    f.show(true);
                    this.dispose();
                } else if (sw == 4) {
                    FrameGestionPersonal fg = new FrameGestionPersonal();
                    fg.show(true);
                    this.dispose();
                } else if (sw == 5) {
                    FrameProductos fg;
                    try {
                        fg = new FrameProductos();
                        fg.show(true);

                        this.dispose();
                    } catch (IOException ex) {
                        Logger.getLogger(FramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(FramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "CONTRASEÑA INCORRECTA");
            }
        } else {
            JOptionPane.showMessageDialog(null, "USUARIO INCORRECTO");
        }
    }

    public void conTexto() throws IOException, ClassNotFoundException {
        if (FramePrincipal.cp.getV().getUsuario().equals(this.pantUsuario.getText())) {
            if (FramePrincipal.cp.getV().getContrasenia().equals(this.pantContrasenia.getText())) {
                if (sw == 1) {
                    FramePersonal as = new FramePersonal();
                    as.show(true);
                    this.dispose();
                } else if (sw == 2) {
                    FrameGestion g = new FrameGestion();
                    g.show(true);
                    this.dispose();
                } else if (sw == 3) {
                    FrameNuevoUsuario f = new FrameNuevoUsuario();
                    f.show(true);
                    this.dispose();
                } else if (sw == 4) {
                    FrameGestionPersonal fg = new FrameGestionPersonal();
                    fg.show(true);
                    this.dispose();
                } else if (sw == 5) {
                    FrameProductos fg;
                    try {
                        fg = new FrameProductos();
                        fg.show(true);

                        this.dispose();
                    } catch (IOException ex) {
                        Logger.getLogger(FramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(FramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "CONTRASEÑA INCORRECTA");
            }
        } else {
            JOptionPane.showMessageDialog(null, "USUARIO INCORRECTO");
        }
    }

    public boolean permiso() {
        NodoPersonal aux = FramePrincipal.cp.getLp().getP();
        while (aux != null) {
            if (aux.getPersonal().isAutorizacion() == true) {
                return true;
            }
            aux = aux.getSig();
        }
        return false;
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
            java.util.logging.Logger.getLogger(FrameLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameLogin().setVisible(true);
            }
        });
    }

    public int getSw() {
        return sw;
    }

    public void setSw(int sw) {
        this.sw = sw;
    }

    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("imagenes/engranaje3_1.png"));
        return retValue;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPasswordField pantContrasenia;
    private javax.swing.JTextField pantUsuario;
    // End of variables declaration//GEN-END:variables
}
