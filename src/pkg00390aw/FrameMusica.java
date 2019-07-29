package pkg00390aw;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import pkg00390aw.ClaseBotones;
import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.MediaPlayer;
import uk.co.caprica.vlcj.player.MediaPlayerEventAdapter;
import uk.co.caprica.vlcj.player.embedded.FullScreenStrategy;
import uk.co.caprica.vlcj.player.embedded.x.XFullScreenStrategy;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

public class FrameMusica extends javax.swing.JFrame {

    private ListaDeMusicas lm;
    private JFrame frame = new JFrame();

    public FrameMusica() throws IOException, ClassNotFoundException {
        initComponents();
        this.setLocationRelativeTo(null);
        //jScrollPane4.setVisible(false);
        lm = new ListaDeMusicas();
        File f1 = new File(".\\musicasListas.dat");
        if (f1.exists()) {
            lm = lm.recuperar();
        }
        llenarTabla1();
        llenarTabla2();
        llenarTabla3();
        player = new EmbeddedMediaPlayerComponent();

//slider control de volumen
        sldVolumen.setMinimum(0);
        sldVolumen.setMaximum(100);
//slider control progreso
        sldProgress.setMinimum(0);
        sldProgress.setMaximum(100);
        sldProgress.setValue(0);
        sldProgress.setEnabled(false);
        //aniadir reproductor
        jPanel1.add(player);
        player.setSize(this.jPanel1.getSize());
        player.setVisible(true);

        sldVolumen.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                Object source = e.getSource();
                player.getMediaPlayer().setVolume(((JSlider) source).getValue());
            }
        });

        player.getMediaPlayer().addMediaPlayerEventListener(new MediaPlayerEventAdapter() {

            @Override
            public void positionChanged(MediaPlayer mp, float pos) {
                if (band) {
                    int value = Math.min(100, Math.round(pos * 100.0f));
                    sldProgress.setValue(value);
                    //System.out.println("Tiempo: "+player.getMediaPlayer().getPosition());
                    DecimalFormat df = new DecimalFormat("#.000");
                    if (Double.valueOf(df.format(player.getMediaPlayer().getPosition())) >= 1) {
                        siguiente.doClick();
                    }
                }
            }

            @Override
            public void finished(MediaPlayer mediaPlayer) {

            }

        });

        sldProgress.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                band = false;
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                band = true;
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }

        });
        //Control para cambiar a posicion de reproduccion
        sldProgress.addChangeListener(new ChangeListener() {

            @Override
            public synchronized void stateChanged(ChangeEvent e) {
                if (!band) {
                    Object source = e.getSource();
                    float np = ((JSlider) source).getValue() / 100f;
                    player.getMediaPlayer().setPosition(np);

                }

            }
        });
        btnPausa.setVisible(false);
    }

    public void llenarTabla3() {
        reiniciarTabla3();
        Queue<File> aux = new LinkedList();
        aux.addAll(lm.getTemasPorDefecto());
        int fila = 0;
        while (!aux.isEmpty()) {
            Generatabla3();
            File f = aux.remove();
            jTable3.setValueAt(f.getName(), fila, 0);
            fila++;
        }
    }

    public void llenarTabla2() {
        reiniciarTabla2();
        Queue<File> aux = new LinkedList();
        aux.addAll(this.lm.getListaPedidos());
        Queue<String> aux2 = new LinkedList();
        aux2.addAll(lm.getLugar());
        int fila = 0;
        while (!aux.isEmpty()) {
            Generatabla2();
            File f = aux.remove();
            String l = aux2.remove();
            this.jTable2.setValueAt(f.getName() + "  " + l, fila, 0);
            fila++;
        }
    }

    public void llenarTabla1() {
        reiniciarTabla1();
        Queue<File> aux = new LinkedList();
        aux.addAll(this.lm.getListaTemas());
        int fila = 0;
        while (!aux.isEmpty()) {
            Generatabla1();
            File f = aux.remove();
            this.jTable1.setValueAt(f.getName(), fila, 0);
            fila++;
        }
    }

    public void reiniciarTabla1() {
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "NOMBRE DE LA CANCION"
                }
        ));
    }

    public void reiniciarTabla2() {
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Lista de reproduccion pedido"
                }
        ));
    }

    public void reiniciarTabla3() {
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Por defecto"
                }
        ));
    }

    public void Generatabla2() {
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        model.addRow(new Object[]{""});
    }

    public void Generatabla3() {
        DefaultTableModel model = (DefaultTableModel) jTable3.getModel();
        model.addRow(new Object[]{""});
    }
    //*********************METODOS PARA VLC ************************

    private EmbeddedMediaPlayerComponent player;

    static {
        NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), "C:\\Program Files\\VideoLAN\\VLC\\");
        //NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), "C:\\Program Files (x86)\\VideoLAN\\VLC\\");
        Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
    }

    private boolean band = true;

    //******************************************************************
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        pantLugar = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        play = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        siguiente = new javax.swing.JButton();
        btnPausa = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        sldVolumen = new javax.swing.JSlider();
        sldProgress = new javax.swing.JSlider();
        jButton8 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Busqueda:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, -1));

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 90, -1));

        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 33, -1, 20));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "NOMBRE DE LA CANCION"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 180, 270));

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton2.setText("Reiniciar tabla");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 50, 120, 40));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "pedidos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(255, 0, 51))); // NOI18N
        jPanel3.setForeground(new java.awt.Color(0, 255, 153));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel3.add(pantLugar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 120, -1));

        jLabel8.setText("Mesa o Sala");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 120, 20));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 200, 160, 90));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "Lista de reproduccion pedido"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 30, 180, 250));

        play.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/play.png"))); // NOI18N
        play.setBorderPainted(false);
        play.setContentAreaFilled(false);
        play.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playActionPerformed(evt);
            }
        });
        getContentPane().add(play, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 390, 50, 30));

        jButton3.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/download (1).png"))); // NOI18N
        jButton3.setText("INGRESAR PEDIDO");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 110, 210, 40));

        siguiente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/siguiente.png"))); // NOI18N
        siguiente.setToolTipText("");
        siguiente.setBorderPainted(false);
        siguiente.setContentAreaFilled(false);
        siguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siguienteActionPerformed(evt);
            }
        });
        getContentPane().add(siguiente, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 390, 60, 30));

        btnPausa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/pausa.png"))); // NOI18N
        btnPausa.setBorderPainted(false);
        btnPausa.setContentAreaFilled(false);
        btnPausa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPausaActionPerformed(evt);
            }
        });
        getContentPane().add(btnPausa, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 390, 50, 30));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/sonido.png"))); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 470, -1, -1));

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 218, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 248, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 30, 220, 250));

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "Por defecto"
            }
        ));
        jScrollPane3.setViewportView(jTable3);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 310, 180, 190));

        jLabel2.setText("Tipo de reproduccion");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 360, -1, -1));

        jButton5.setText("PANTALLA COMPLETA");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 330, 220, -1));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Normal", "Aleatorio", "Inverso"}));
        getContentPane().add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 390, 110, -1));

        jButton6.setText("AGREGAR");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 510, 90, -1));

        jButton7.setText("AGREGAR");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, -1, -1));
        getContentPane().add(sldVolumen, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 466, 140, 40));
        getContentPane().add(sldProgress, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 290, 220, 30));

        jButton8.setText("ELIMINAR");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 510, -1, -1));

        jButton4.setText("ELIMINAR");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel4.setText("Lista de Pedidos");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 10, 100, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel5.setText("Musicas por defecto");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 290, 140, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void playActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playActionPerformed
        if (this.archivo == null) {
            SiguienteRep();
        } else if (sw) {
            play.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/play.png")));
            player.getMediaPlayer().setPause(player.getMediaPlayer().isPlaying() ? true : false);
            sw = false;
        } else {
            play.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/pausa.png")));
            player.getMediaPlayer().setPause(player.getMediaPlayer().isPlaying() ? true : false);
            sw = true;
        }
    }//GEN-LAST:event_playActionPerformed

    boolean sw = false;
    int posicion = 0;
    File archivo = null;

    public void SiguienteRep() {
        if (!this.lm.getListaPedidos().isEmpty()) {
            archivo = lm.getListaPedidos().remove();
            System.out.println(lm.getLugar().remove());
            player.getMediaPlayer().playMedia(archivo.getAbsolutePath());
            sldVolumen.setValue(player.getMediaPlayer().getVolume());
            sldProgress.setEnabled(true);
            lm.guardar(lm);
            llenarTabla2();
        } else if (this.jTable3.getRowCount() != 0) {
            int max = this.jTable3.getRowCount();
            if (this.jComboBox1.getSelectedIndex() == 0) {
                if (posicion >= max) {
                    posicion = 0;
                    archivo = getPosicionListaDefecto(posicion);
                    player.getMediaPlayer().playMedia(archivo.getAbsolutePath());
                    sldVolumen.setValue(player.getMediaPlayer().getVolume());
                    sldProgress.setEnabled(true);
                    this.jTable3.setRowSelectionInterval(posicion, posicion);
                } else {
                    player.getMediaPlayer().playMedia(getPosicionListaDefecto(posicion).getAbsolutePath());
                    sldVolumen.setValue(player.getMediaPlayer().getVolume());
                    sldProgress.setEnabled(true);
                    this.jTable3.setRowSelectionInterval(posicion, posicion);
                    posicion++;
                }
            } else if (this.jComboBox1.getSelectedIndex() == 1) {
                posicion = (int) (Math.random() * Math.pow(10, (int) Math.log10(this.jTable3.getRowCount()) + 1));
                while (posicion >= this.jTable3.getRowCount()) {
                    posicion = (int) (Math.random() * Math.pow(10, (int) Math.log10(this.jTable3.getRowCount()) + 1));
                }
                archivo = getPosicionListaDefecto(posicion);
                player.getMediaPlayer().playMedia(archivo.getAbsolutePath());
                sldVolumen.setValue(player.getMediaPlayer().getVolume());
                sldProgress.setEnabled(true);
                this.jTable3.setRowSelectionInterval(posicion, posicion);

            } else if (this.jComboBox1.getSelectedIndex() == 2) {
                if (posicion < 0) {
                    posicion = max - 1;
                    archivo = getPosicionListaDefecto(posicion);
                    player.getMediaPlayer().playMedia(archivo.getAbsolutePath());
                    sldVolumen.setValue(player.getMediaPlayer().getVolume());
                    sldProgress.setEnabled(true);
                    this.jTable3.setRowSelectionInterval(posicion, posicion);
                    posicion = posicion - 1;
                } else {
                    archivo = getPosicionListaDefecto(posicion);
                    player.getMediaPlayer().playMedia(archivo.getAbsolutePath());
                    sldVolumen.setValue(player.getMediaPlayer().getVolume());
                    sldProgress.setEnabled(true);
                    this.jTable3.setRowSelectionInterval(posicion, posicion);
                    posicion = posicion - 1;

                }

            }
        }
    }

    public File getPosicionListaDefecto(int i) {
        Queue<File> aux = new LinkedList();
        aux.addAll(lm.getTemasPorDefecto());
        int x = 0;
        while (!aux.isEmpty()) {
            File f = aux.remove();
            if (i == x) {
                return f;
            }
            x++;
        }
        return null;
    }
    private void siguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siguienteActionPerformed
        SiguienteRep();
    }//GEN-LAST:event_siguienteActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Videos", "mp4", "flv", "webm", "3gp", "dat");
        fileChooser.addChoosableFileFilter(filter);
        //fileChooser.setCurrentDirectory(new java.io.File("C:\\videos\\tmp\\"));
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
            this.lm.getTemasPorDefecto().add(file);
            //btnPlay.doClick();
        }
        lm.guardar(lm);
        llenarTabla3();
    }//GEN-LAST:event_jButton6ActionPerformed

    private String lugar;
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (!this.pantLugar.getText().equals("")) {
            if (this.Buscado.isEmpty()) {
                int[] x = this.jTable1.getSelectedRows();
                this.lm.pasarAPedidos(x, this.pantLugar.getText());
                llenarTabla2();
                lm.guardar(lm);
            } else {
                int[] x = this.jTable1.getSelectedRows();
                for (int i = 0; i < x.length; i++) {
                    Queue<File> aux = new LinkedList();
                    aux.addAll(Buscado);
                    int j = 0;
                    while (!aux.isEmpty()) {
                        File f = aux.remove();
                        if (j == x[i]) {
                            this.lm.getListaPedidos().add(f);
                            this.lm.getLugar().add(this.pantLugar.getText());
                        }
                        j++;
                    }
                }
                llenarTabla2();
                lm.guardar(lm);
            }
        } else {
            JOptionPane.showMessageDialog(null, "SELECCIONE LA MESA");
        }
    }//GEN-LAST:event_jButton3ActionPerformed


    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        llenarTabla1();
        this.jTextField1.setText("");
        this.Buscado = new LinkedList();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Videos", "mp4", "flv", "webm", "3gp", "dat");
        fileChooser.addChoosableFileFilter(filter);
        //fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        //fileChooser.setCurrentDirectory(new java.io.File("C:\\videos\\tmp\\"));
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
            this.lm.getListaTemas().add(file);
            //btnPlay.doClick();
        }
        lm.guardar(lm);
        llenarTabla1();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void btnPausaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPausaActionPerformed
        player.getMediaPlayer().setPause(player.getMediaPlayer().isPlaying() ? true : false);
    }//GEN-LAST:event_btnPausaActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        int[] x = this.jTable3.getSelectedRows();
        this.lm.eliminarPorDefecto(x);
        this.lm.guardar(lm);
        llenarTabla3();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if (!this.Buscado.isEmpty()) {
            int[] x = this.jTable1.getSelectedRows();
            this.lm.eliminarTemas(x);
            this.lm.guardar(lm);
            llenarTabla1();
        } else {
            JOptionPane.showMessageDialog(null, "Reinicie la tabla antes de eliminar un elemento");
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (this.jTextField1.getText().equals("")) {
            llenarTabla1Bucado(this.jTextField1.getText());
        } else {
            Buscado = new LinkedList();
            llenarTabla1();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        if (this.jTextField1.getText().equals("")) {
            llenarTabla1Bucado(this.jTextField1.getText());
        } else {
            Buscado = new LinkedList();
            llenarTabla1();
        }
    }//GEN-LAST:event_jTextField1KeyReleased


    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        frame = new JFrame("LibX11 Full Screen Strategy");
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                jPanel1.add(player);
                player.setSize(jPanel1.getSize());
                player.setVisible(true);
            }
        });
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocation(0, 0);
        frame.setSize(1200, 800);

        frame.add(player);

        frame.setVisible(true);
        player.setVisible(true);
        player.setSize(frame.getSize());
    }//GEN-LAST:event_jButton5ActionPerformed
    //*************** PANTALLA COMPLETA ******

//********************** BUSCADOR ********************************
    private Queue<File> Buscado = new LinkedList();

    public void llenarTabla1Bucado(String txt) {
        reiniciarTabla1();
        Queue<File> aux = this.lm.buscarTema(txt);
        Buscado = new LinkedList();
        int i = 0;
        while (!aux.isEmpty()) {
            Generatabla1();
            File f = aux.remove();
            Buscado.add(f);
            this.jTable1.setValueAt(f.getName(), i, 0);
            i++;
        }
    }

    //***********************************************
    public void Generatabla1() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.addRow(new Object[]{""});
    }
    File file;

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
            java.util.logging.Logger.getLogger(FrameMusica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameMusica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameMusica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameMusica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new FrameMusica().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(FrameMusica.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(FrameMusica.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPausa;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField pantLugar;
    private javax.swing.JButton play;
    private javax.swing.JButton siguiente;
    private javax.swing.JSlider sldProgress;
    private javax.swing.JSlider sldVolumen;
    // End of variables declaration//GEN-END:variables
}
