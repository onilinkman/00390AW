/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.  616 362
 */
package pkg00390aw;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author WINDOWS 10
 */
public class FrameMesas extends javax.swing.JFrame {

    public FrameMesas() throws IOException, ClassNotFoundException {
        initComponents();
        posicionX=java.awt.Toolkit.getDefaultToolkit().getScreenSize().width/2-this.getWidth()/2;
        System.out.println(posicionX);
        posicionY=java.awt.Toolkit.getDefaultToolkit().getScreenSize().height/2-this.getHeight()/2;
        System.out.println(posicionY);
        this.setLocation(posicionX, posicionY);
        
        File f=new File("flat.dat");
        if(f.exists()){
            ClaseBotones cb=new ClaseBotones();
            cb=cb.recuperar();
            this.Mesas=cb.getMesas();
            this.Barras=cb.getBarras();
            this.Salas=cb.getSalas();
            this.cantBarras=cb.getBarras().length;
            this.cantMesas=cb.getMesas().length;
            this.cantSalas=cb.getSalas().length;
            colocarMesas2();
            colocarBarras2();
            colocarSalas2();
        }
    }
    
    public void colocarMesas2(){
        for(int i=0;i<this.cantMesas;i++){
            Mesas[i].setText("M"+(i+1));
            
            Mesas[i].addMouseMotionListener(new MouseMotionListener(){
                @Override
                public void mouseDragged(MouseEvent me) {
                     move(me);
                }

                @Override
                public void mouseMoved(MouseEvent me) {
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
                
            });
            this.add(Mesas[i]);
        }
    }
    
    public void colocarBarras2(){
        for(int i=0;i<this.cantBarras;i++){
            Barras[i].setText("B"+(i+1));
            Barras[i].addMouseMotionListener(new MouseMotionListener(){
                @Override
                public void mouseDragged(MouseEvent me) {
                    moveBarra(me); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void mouseMoved(MouseEvent me) {
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
                
            });
            this.add(Barras[i]);
        }
    }
    
    public void colocarSalas2(){
        for(int i=0;i<this.cantSalas;i++){
            Salas[i].setText("S"+(i+1));
            Salas[i].addMouseMotionListener(new MouseMotionListener() {
                @Override
                public void mouseDragged(MouseEvent me) {
                    moveSalas(me);
                }

                @Override
                public void mouseMoved(MouseEvent me) {
                //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
            this.add(Salas[i]);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jButton1.setText("Salir y Guardar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Salir");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setText("CONFIGURACION MESA");

        jButton3.setText("Nuevo");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addGap(158, 158, 158)
                .addComponent(jButton1)
                .addGap(18, 18, 18))
            .addGroup(layout.createSequentialGroup()
                .addGap(212, 212, 212)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(208, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 295, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        ClaseBotones cb=new ClaseBotones();
        cb.setBarras(this.Barras);
        cb.setMesas(Mesas);
        cb.setSalas(Salas);
        Rectangle[] r1=new Rectangle[this.cantBarras];
        for(int i=0;i<this.Barras.length;i++){
            r1[i]=this.Barras[i].getBounds();
            this.Barras[i].setBackground(new Color(214, 217, 223));
        }
        cb.setHuesosBarras(r1);
        
        Rectangle[] r2=new Rectangle[this.cantMesas];
        for(int i=0;i<this.cantMesas;i++){
            r2[i]=this.Mesas[i].getBounds();
            this.Mesas[i].setBackground(new Color(214, 217, 223));
        }
        cb.setHuesosMesas(r2);
        
        Rectangle[] r3=new Rectangle[this.cantSalas];
        for(int i=0;i<this.cantSalas;i++){
            r3[i]=this.Salas[i].getBounds();
            this.Salas[i].setBackground(new Color(214, 217, 223));
        }
        cb.setHuesosSalas(r3);
        cb.guardar();
        try {
            FrameVentaCaja vc=new FrameVentaCaja();
            vc.setVisible(true);
            this.dispose();
        } catch (IOException ex) {
            Logger.getLogger(FrameMesas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FrameMesas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(FrameMesas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if(this.Barras!=null){
            for(int i=0;i<this.cantBarras;i++){
                this.Barras[i].setVisible(false);
            }
        }
        if(this.Mesas!=null){
            for(int i=0;i<this.cantMesas;i++){
                this.Mesas[i].setVisible(false);
            }
        }
        if(this.Salas!=null){
            for(int i=0;i<this.cantSalas;i++){
                this.Salas[i].setVisible(false);
            }
        }
        cantMesas=Integer.valueOf(JOptionPane.showInputDialog(null, "Ingrese la cantidad de mesas"));
        cantBarras=Integer.valueOf(JOptionPane.showInputDialog(null, "Ingrese la cantidad de barras"));
        cantSalas=Integer.valueOf(JOptionPane.showInputDialog(null, "Ingrese la cantidad de Salas"));
        colocarMesas();
        colocarBarras();
        colocarSalas();
        this.paintAll(this.getGraphics()); 
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            FrameVentaCaja vc=new FrameVentaCaja();
            vc.setVisible(true);
            this.dispose();
        } catch (IOException ex) {
            Logger.getLogger(FrameMesas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FrameMesas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(FrameMesas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private int cantMesas=0;
    private int cantBarras=0;
    private int cantSalas=0;
    
    private JButton[] Mesas;
    private JButton[] Barras;
    private JButton[] Salas;
    
    private int posicionX=0;
    private int posicionY=0;
    
    public void colocarSalas(){
        this.Salas=new JButton[this.cantSalas];
        for(int i=0;i<this.cantSalas;i++){
            Salas[i]=new JButton();
            Salas[i].setBounds(50, 150, 70, 50);
            Salas[i].setText("S"+(i+1));
            Salas[i].addMouseMotionListener(new MouseMotionListener() {
                @Override
                public void mouseDragged(MouseEvent me) {
                    moveSalas(me);
                }

                @Override
                public void mouseMoved(MouseEvent me) {
                //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
            this.add(Salas[i]);
        }
    }
    
    public void moveSalas(MouseEvent me){
        for(int i=0;i<this.cantSalas;i++){
            if(Salas[i].equals(me.getSource())){
                Salas[i].setBounds(me.getXOnScreen()-this.posicionX-35, me.getYOnScreen()-this.posicionY-50, 70, 50);
            }
        }
    }
    
    public void colocarBarras(){
        this.Barras=new JButton[this.cantBarras];
        for(int i=0;i<this.cantBarras;i++){
            Barras[i]=new JButton();
            Barras[i].setBounds(50, 100, 70, 50);
            Barras[i].setText("B"+(i+1));
            Barras[i].addMouseMotionListener(new MouseMotionListener(){
                @Override
                public void mouseDragged(MouseEvent me) {
                    moveBarra(me); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void mouseMoved(MouseEvent me) {
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
                
            });
            this.add(Barras[i]);
        }
    }
    public void moveBarra(MouseEvent me){
        for(int i=0;i<this.cantBarras;i++){
            if(Barras[i].equals(me.getSource())){
                Barras[i].setBounds(me.getXOnScreen()-this.posicionX-35, me.getYOnScreen()-this.posicionY-50, 70, 50);
            }
        }
    }
    
    public void colocarMesas(){
        this.Mesas=new JButton[this.cantMesas];
        for(int i=0;i<this.cantMesas;i++){
            Mesas[i]=new JButton();
            Mesas[i].setBounds(50, 50, 70, 50);
            Mesas[i].setText("M"+(i+1));
            
            Mesas[i].addMouseMotionListener(new MouseMotionListener(){
                @Override
                public void mouseDragged(MouseEvent me) {
                     move(me);
                }

                @Override
                public void mouseMoved(MouseEvent me) {
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
                
            });
            this.add(Mesas[i]);
        }
        
    }
    
    public void move(MouseEvent me){
        for(int i=0;i<this.cantMesas;i++){
            if(Mesas[i].equals(me.getSource())){
                Mesas[i].setBounds(me.getXOnScreen()-this.posicionX-35, me.getYOnScreen()-this.posicionY-50, 70, 50);
            }
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
            java.util.logging.Logger.getLogger(FrameMesas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameMesas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameMesas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameMesas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new FrameMesas().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(FrameMesas.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(FrameMesas.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
