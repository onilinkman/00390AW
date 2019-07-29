package pkg00390aw;

import com.zkteco.biometric.FingerprintSensorErrorCode;
import com.zkteco.biometric.FingerprintSensorEx;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class FramePersonal extends javax.swing.JFrame {

    public FramePersonal() {
        initComponents();
        this.setLocationRelativeTo(null);

        int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
        System.out.println(ancho);
        this.setBounds(0, 0, ancho, 700);

        JTableHeader th = jTable1.getTableHeader();
        Font fuente = new Font("NOMBRE DE EMPLEADOS", Font.CENTER_BASELINE, 14);
        th.setFont(fuente);
        CrearLista();
        setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
        this.dispose();

        bloquearPantallas();
        ocultarBotones();
        pintarPantallasGris();
        this.jLabel8.setText("<html>Fecha de ingreso</html>");
    }

    public void limpiarTodo() {
        this.pantNombre.setText("");
        this.pantCelular.setText("");
        this.pantDireccion.setText("");
        //this.pantNroMaquina.setText("");
        this.pantOcupacion.setText("");
        this.pantSueldo.setText("");
        this.pantNumCarnet.setText("");
        this.BSacarFoto.setIcon(null);
        this.btnImg.setIcon(null);
        this.jDateChooser1.setDate(null);
    }

    public Calendar recuperarCalendario(String fecha) {
        Calendar calendario = Calendar.getInstance();
        int dia, mes, anio;
        dia = mes = 0;
        int cont = 0;
        char x;
        String aux = "";
        for (int i = 0; i < fecha.length(); i++) {
            if (fecha.charAt(i) != '/') {
                aux = aux + fecha.charAt(i);
            } else if (cont == 0) {
                cont++;
                dia = Integer.valueOf(aux);
                aux = "";
            } else if (cont == 1) {
                cont++;
                mes = Integer.valueOf(aux) - 1;
                aux = "";
            }

        }
        anio = Integer.valueOf(aux);
        calendario.set(anio, mes, dia);

        return calendario;
    }

    public void Generatabla1() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.addRow(new Object[]{""});
    }

    public void CrearLista() {
        NodoPersonal np = FramePrincipal.cp.getLp().getP();
        int i = 0;

        while (np != null) {
            Generatabla1();
            AgregarPersona(np.getPersonal(), i);
            i++;
            np = np.getSig();
        }
    }

    public void AgregarPersona(Personal p, int x) {
        //y x
        String nombre = p.getNombre();
        String direccion = p.getDireccion();
        String carnet = p.getCarnet();
        String celular = p.getCelular();
        String ocupacion = p.getOcupacion();
        String sueldo = String.valueOf(p.getSueldo());
        String maquina = p.getNumMaquina();

        jTable1.setValueAt(nombre, x, 0);
        jTable1.setValueAt(carnet, x, 1);
        jTable1.setValueAt(direccion, x, 2);
        jTable1.setValueAt(ocupacion, x, 4);
        jTable1.setValueAt(celular, x, 3);

        jTable1.setValueAt(sueldo, x, 5);
        jTable1.setValueAt(maquina, x, 6);

        this.jTable1.setValueAt(p.getFechaIngreso(), x, 7);
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
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        pantNombre = new javax.swing.JTextField();
        pantDireccion = new javax.swing.JTextField();
        pantNumCarnet = new javax.swing.JTextField();
        pantCelular = new javax.swing.JTextField();
        pantOcupacion = new javax.swing.JTextField();
        pantSueldo = new javax.swing.JTextField();
        BSacarFoto = new javax.swing.JButton();
        btnImg = new javax.swing.JButton();
        BIngresar = new javax.swing.JButton();
        BEditar = new javax.swing.JButton();
        BLimpiar = new javax.swing.JButton();
        BEliminar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextArea();
        BCancelar = new javax.swing.JButton();
        BEscaneo = new javax.swing.JButton();
        BAceptarEdicion = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());

        jLabel1.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel1.setText("NOMBRE Y APELLIDO");

        jLabel2.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel2.setText("DIRECCION");

        jLabel3.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel3.setText("NUMERO CARNET");

        jLabel4.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel4.setText("CELULAR");

        jLabel5.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel5.setText("OCUPACION");

        jLabel6.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel6.setText("SUELDO");

        jLabel7.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel7.setText("PERSONAL");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "NOMBRES","C.I","DIRECCION","CELULAR","OCUPACION","SUELDO","Nro MAQUINA","FECHA INGRESO"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        pantNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pantNombreActionPerformed(evt);
            }
        });

        pantDireccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pantDireccionActionPerformed(evt);
            }
        });

        pantNumCarnet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pantNumCarnetActionPerformed(evt);
            }
        });

        pantCelular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pantCelularActionPerformed(evt);
            }
        });

        pantOcupacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pantOcupacionActionPerformed(evt);
            }
        });

        pantSueldo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pantSueldoActionPerformed(evt);
            }
        });

        BSacarFoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BSacarFotoActionPerformed(evt);
            }
        });

        BIngresar.setFont(new java.awt.Font("Bookman Old Style", 3, 14)); // NOI18N
        BIngresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/aceptar.png"))); // NOI18N
        BIngresar.setText("INGRESAR");
        BIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BIngresarActionPerformed(evt);
            }
        });

        BEditar.setFont(new java.awt.Font("Bookman Old Style", 3, 14)); // NOI18N
        BEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/edit.png"))); // NOI18N
        BEditar.setText("EDITAR");
        BEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BEditarActionPerformed(evt);
            }
        });

        BLimpiar.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        BLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/gnome_edit_clear.png"))); // NOI18N
        BLimpiar.setText("LIMPIAR");
        BLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BLimpiarActionPerformed(evt);
            }
        });

        BEliminar.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        BEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/deletered.png"))); // NOI18N
        BEliminar.setText("ELIMINAR");
        BEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BEliminarActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/group_add_128.png"))); // NOI18N
        jButton1.setText("Empezar a Registrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        textArea.setColumns(20);
        textArea.setFont(new java.awt.Font("Consolas", 1, 13)); // NOI18N
        textArea.setRows(5);
        jScrollPane2.setViewportView(textArea);

        BCancelar.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        BCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cancel2.png"))); // NOI18N
        BCancelar.setText("CANCELAR");
        BCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BCancelarActionPerformed(evt);
            }
        });

        BEscaneo.setFont(new java.awt.Font("Tahoma", 3, 13)); // NOI18N
        BEscaneo.setText("REINICIAR ESCANEO");
        BEscaneo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BEscaneoActionPerformed(evt);
            }
        });

        BAceptarEdicion.setBackground(new java.awt.Color(51, 153, 0));
        BAceptarEdicion.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        BAceptarEdicion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/aceptar.png"))); // NOI18N
        BAceptarEdicion.setText("ACEPTAR EDICION");
        BAceptarEdicion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BAceptarEdicionActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/back.png"))); // NOI18N
        jButton2.setText("ATRAS");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel8.setText("Fecha de ingreso");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Empleador", "Administrador", "Garson", "Seguridad","Otros" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(90, 90, 90)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pantNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(pantDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(252, 252, 252)
                        .addComponent(jLabel4))
                    .addComponent(pantOcupacion, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pantSueldo, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(100, 100, 100)
                                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(49, 49, 49)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(BCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(BEscaneo, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel6)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pantNumCarnet, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BSacarFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(340, 340, 340)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(pantCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(10, 10, 10)
                .addComponent(btnImg, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(BIngresar, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(BAceptarEdicion, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(BEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(180, 180, 180)
                        .addComponent(BLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20)
                .addComponent(BEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1240, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(240, 240, 240)
                        .addComponent(jLabel6))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(320, 320, 320)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(BSacarFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(btnImg, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(pantNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(pantDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel4))
                                .addGap(2, 2, 2)
                                .addComponent(pantOcupacion, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jLabel3))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(160, 160, 160)
                                .addComponent(jLabel7))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(110, 110, 110)
                                .addComponent(pantCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(pantNumCarnet, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                            .addComponent(jComboBox1))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(pantSueldo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(BCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(BEscaneo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BIngresar, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BAceptarEdicion, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(36, 36, 36)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BIngresarActionPerformed
        if (verificaNombre()) {
            ingresaPersonal();
            reiniciarTabla();
            CrearLista();

            FreeSensor();
            textArea.setText("Registro Finalizado!");
        } else {
            JOptionPane.showMessageDialog(null, "ESTE NOMBRE YA ESTA REGISTRADO EN EL PERSONAL");
        }
    }//GEN-LAST:event_BIngresarActionPerformed

    public boolean verificaNombre() {
        NodoPersonal w = FramePrincipal.cp.getLp().getP();
        while (w != null) {
            if (w.getPersonal().getNombre().equalsIgnoreCase(this.pantNombre.getText())) {
                return false;
            }
            w = w.getSig();
        }
        return true;
    }

    private void BSacarFotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BSacarFotoActionPerformed
        /*this.BSacarFoto=new javax.swing.JButton();
        this.BSacarFoto.setIcon(null);*/
        if (!this.pantNombre.getText().equals("")) {
            WebcamViewerExample1 w = new WebcamViewerExample1();
            w.setSwith(3);
            w.run(this.pantNombre.getText());
            this.BSacarFoto.setText("");
        } else {
            JOptionPane.showMessageDialog(null, "INGRESE PRIMERO EL NOMBRE");
        }
    }//GEN-LAST:event_BSacarFotoActionPerformed

    private void BEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BEliminarActionPerformed
        int fila = jTable1.getSelectedRow();
        FramePrincipal.cp.getLp().eliminar(this.jTable1.getValueAt(fila, 0).toString());
        reiniciarTabla();
        CrearLista();
        FramePrincipal.cp.guardar();
        limpiarTodo();
    }//GEN-LAST:event_BEliminarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        ocultarBotones();
        limpiarTodo();
        this.jButton1.setVisible(false);
        this.BEscaneo.setVisible(true);
        this.BIngresar.setVisible(true);
        this.BCancelar.setVisible(true);

        pintaPantallasBlanco();
        habilitarPantallas();

        this.BSacarFoto.setText("<html>PARA SACAR FOTO PRESIONE AQUI</html>");
        iniciarBiometrico();
        if (0 == mhDevice) {
            textArea.setText("encienda su dispositivo primero!");
            return;
        }
        if (!bRegister) {
            enroll_idx = 0;
            bRegister = true;
            textArea.setText("Ponga su dedo 3 veces!");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void BEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BEditarActionPerformed
        habilitarPantallas();
        pintaPantallasBlanco();
        this.pantNombre.setEditable(false);
        this.pantNombre.setBackground(new java.awt.Color(153, 153, 153));
        this.BEditar.setVisible(false);
        this.BAceptarEdicion.setVisible(true);
        this.BCancelar.setVisible(true);
        this.BEscaneo.setVisible(true);
    }//GEN-LAST:event_BEditarActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        limpiarTodo();
        pintarPantallasGris();
        bloquearPantallas();
        ocultarBotones();
        this.jButton1.setVisible(true);
        this.BEditar.setVisible(true);
        //this.BCancelar.setVisible(true);
        this.BEliminar.setVisible(true);
        try {
            OpcionesTabla();
        } catch (IOException ex) {
            Logger.getLogger(FramePersonal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jTable1MouseClicked

    public void OpcionesTabla() throws IOException {
        int fila = this.jTable1.getSelectedRow();
        NodoPersonal aux = FramePrincipal.cp.getLp().getP();
        System.out.println(this.jTable1.getValueAt(fila, 0).toString());
        while (aux != null) {
            System.out.println(aux.getPersonal().getNombre());
            if (aux.getPersonal().getNombre().equals(this.jTable1.getValueAt(fila, 0).toString())) {
                this.pantNombre.setText(aux.getPersonal().getNombre());
                this.pantDireccion.setText(aux.getPersonal().getDireccion());
                this.pantCelular.setText(aux.getPersonal().getCelular());
                //this.pantNroMaquina.setText(aux.getPersonal().getNumMaquina());
                this.jComboBox1.setSelectedItem(aux.getPersonal().getNumMaquina());//BORRAR SI NO FUNCIOAN COMO SE ESPERA
                this.pantSueldo.setText(String.valueOf(aux.getPersonal().getSueldo()));
                this.pantNumCarnet.setText(aux.getPersonal().getCarnet());
                this.pantOcupacion.setText(aux.getPersonal().getOcupacion());
                try {
                    this.BSacarFoto.setIcon(new ImageIcon(ImageIO.read(new File(aux.getPersonal().getDireccionFoto()))));
                } catch (Exception e) {

                }
                System.out.println(aux.getPersonal().getDireccionFoto());
                try {
                    this.btnImg.setIcon(new ImageIcon(ImageIO.read(new File(aux.getPersonal().getDireccionHuella()))));
                } catch (Exception e) {

                }
                try {
                    this.jDateChooser1.setCalendar(recuperarCalendario(this.jTable1.getValueAt(fila, 7).toString()));
                } catch (Exception e) {

                }
                //this.btnImg.setIcon(new javax.swing.ImageIcon(aux.getPersonal().getDireccionHuella()));
                break;
            }
            aux = aux.getSig();
        }
    }
    private void BCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BCancelarActionPerformed
        FreeSensor();
        textArea.setText("Registro Cerrado!");

        bloquearPantallas();
        ocultarBotones();
        pintarPantallasGris();
        limpiarTodo();
        this.jButton1.setVisible(true);
    }//GEN-LAST:event_BCancelarActionPerformed

    private void BAceptarEdicionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BAceptarEdicionActionPerformed
        editarPersonal();
        reiniciarTabla();
        CrearLista();
    }//GEN-LAST:event_BAceptarEdicionActionPerformed

    private void BEscaneoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BEscaneoActionPerformed
        FreeSensor();
        iniciarBiometrico();
        if (0 == mhDevice) {
            textArea.setText("encienda su dispositivo primero!");
            return;
        }
        if (!bRegister) {
            enroll_idx = 0;
            bRegister = true;
            textArea.setText("Ponga su dedo 3 veces!");
        }
    }//GEN-LAST:event_BEscaneoActionPerformed

    private void pantNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pantNombreActionPerformed
        this.pantDireccion.requestFocus();
    }//GEN-LAST:event_pantNombreActionPerformed

    private void pantDireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pantDireccionActionPerformed
        this.pantOcupacion.requestFocus();
    }//GEN-LAST:event_pantDireccionActionPerformed

    private void pantOcupacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pantOcupacionActionPerformed
        this.pantCelular.requestFocus();
    }//GEN-LAST:event_pantOcupacionActionPerformed

    private void pantCelularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pantCelularActionPerformed
        this.pantNumCarnet.requestFocus();
    }//GEN-LAST:event_pantCelularActionPerformed

    private void pantNumCarnetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pantNumCarnetActionPerformed
        
    }//GEN-LAST:event_pantNumCarnetActionPerformed

    private void pantSueldoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pantSueldoActionPerformed

    }//GEN-LAST:event_pantSueldoActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {

            FramePrincipal a = new FramePrincipal();
            FreeSensor();
            a.show(true);
            this.dispose();
        } catch (IOException ex) {
            Logger.getLogger(FramePersonal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FramePersonal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void BLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BLimpiarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BLimpiarActionPerformed

    public void bloquearPantallas() {
        this.pantNombre.setEditable(false);
        this.pantDireccion.setEditable(false);
        this.pantCelular.setEditable(false);
        
        this.pantSueldo.setEditable(false);
        this.pantNumCarnet.setEditable(false);
        this.pantOcupacion.setEditable(false);
    }

    public void pintarPantallasGris() {
        this.pantNombre.setBackground(new java.awt.Color(153, 153, 153));
        this.pantDireccion.setBackground(new java.awt.Color(153, 153, 153));
        this.pantCelular.setBackground(new java.awt.Color(153, 153, 153));
        //this.pantNroMaquina.setBackground(new java.awt.Color(153, 153, 153));
        this.pantSueldo.setBackground(new java.awt.Color(153, 153, 153));
        this.pantNumCarnet.setBackground(new java.awt.Color(153, 153, 153));
        this.pantOcupacion.setBackground(new java.awt.Color(153, 153, 153));
    }

    public void pintaPantallasBlanco() {
        this.pantNombre.setBackground(new java.awt.Color(255, 255, 255));
        this.pantDireccion.setBackground(new java.awt.Color(255, 255, 255));
        this.pantCelular.setBackground(new java.awt.Color(255, 255, 255));
        //this.pantNroMaquina.setBackground(new java.awt.Color(255, 255, 255));
        this.pantSueldo.setBackground(new java.awt.Color(255, 255, 255));
        this.pantNumCarnet.setBackground(new java.awt.Color(255, 255, 255));
        this.pantOcupacion.setBackground(new java.awt.Color(255, 255, 255));
    }

    public void habilitarPantallas() {
        this.pantNombre.setEditable(true);
        this.pantDireccion.setEditable(true);
        this.pantCelular.setEditable(true);
        //this.pantNroMaquina.setEditable(true);
        this.pantSueldo.setEditable(true);
        this.pantNumCarnet.setEditable(true);
        this.pantOcupacion.setEditable(true);
    }

    public void ocultarBotones() {
        this.BIngresar.setVisible(false);
        this.BEliminar.setVisible(false);
        this.BEditar.setVisible(false);
        this.BLimpiar.setVisible(false);
        this.BCancelar.setVisible(false);
        this.BEscaneo.setVisible(false);
        this.BAceptarEdicion.setVisible(false);
    }

    public void iniciarBiometrico() {
        if (0 != mhDevice) {
            //already inited
            textArea.setText("Please close device first!");
            return;
        }
        int ret = FingerprintSensorErrorCode.ZKFP_ERR_OK;
        //Initialize
        cbRegTemp = 0;
        bRegister = false;
        bIdentify = false;
        iFid = 1;
        enroll_idx = 0;
        if (FingerprintSensorErrorCode.ZKFP_ERR_OK != FingerprintSensorEx.Init()) {
            textArea.setText("Init failed!");
            return;
        }
        ret = FingerprintSensorEx.GetDeviceCount();
        if (ret < 0) {
            textArea.setText("NO ESTA CONECTADO!");
            FreeSensor();
            return;
        }
        if (0 == (mhDevice = FingerprintSensorEx.OpenDevice(0))) {
            textArea.setText("Fallo en abrir dispositivo, ret = " + ret + "!");
            FreeSensor();
            return;
        }
        if (0 == (mhDB = FingerprintSensorEx.DBInit())) {
            textArea.setText("Init DB fallo, ret = " + ret + "!");
            FreeSensor();
            return;
        }

        int nFmt = 0;	//Ansi

        FingerprintSensorEx.DBSetParameter(mhDB, 5010, nFmt);
        byte[] paramValue = new byte[4];
        int[] size = new int[1];

        size[0] = 4;
        FingerprintSensorEx.GetParameters(mhDevice, 1, paramValue, size);
        fpWidth = byteArrayToInt(paramValue);
        size[0] = 4;
        FingerprintSensorEx.GetParameters(mhDevice, 2, paramValue, size);
        fpHeight = byteArrayToInt(paramValue);
        imgbuf = new byte[fpWidth * fpHeight];
        btnImg.resize(fpWidth, fpHeight);
        mbStop = false;
        workThread = new WorkThread();
        workThread.start();
        textArea.setText("Biometrico iniciado!");
    }

    public void reiniciarTabla() {
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "NOMBRES", "C.I", "DIRECCION", "CELULAR", "OCUPACION", "SUELDO", "Nro MAQUINA", "FECHA INGRESO"
                }
        ));
    }

    public void editarPersonal() {
        String nombre = this.pantNombre.getText();
        String direccion = this.pantDireccion.getText();
        String carnet = this.pantNumCarnet.getText();
        String celular = this.pantCelular.getText();
        String ocupacion = this.pantOcupacion.getText();
        String fecha = calendario(this.jDateChooser1.getCalendar());
        //if(!this.pantSueldo.getText().equals("")){
        double sueldo = 0.0;
        try {
            sueldo = Double.valueOf(this.pantSueldo.getText());
            String maquina = this.jComboBox1.getSelectedItem().toString();
            if (!estanLlenas(nombre, direccion, carnet, celular, ocupacion, this.pantSueldo.getText(), maquina)) {

                Personal p = new Personal();
                p.setNombre(nombre);
                p.setCarnet(carnet);
                p.setCelular(celular);
                p.setDireccion(direccion);
                p.setOcupacion(ocupacion);
                p.setFechaIngreso(fecha);
                p.setSueldo(sueldo);
                p.setDireccionFoto(".\\FotosPersonal\\" + this.pantNombre.getText() + ".png");
                p.setDireccionHuella(".\\FotosPersonal\\" + this.pantNombre.getText() + ".bmp");
                p.setNumMaquina(maquina);

                FramePrincipal.cp.getLp().editar(nombre, p);

                FramePrincipal.cp.guardar();
                FreeSensor();
                textArea.setText("Registro Cerrado!");

                bloquearPantallas();
                ocultarBotones();
                pintarPantallasGris();
                limpiarTodo();
                this.jButton1.setVisible(true);
            } else {

                JOptionPane.showMessageDialog(null, "DEBE LLENAR TODAS LAS CASILLAS");

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "El valor que ingreso no es valido, o no ingreso ninguno.\nLos numeros deben tener un punto y no una coma\n Ejemplo: 9.87");
        }
    }

    public void obtenerFechaIngreso() {
        Calendar calendario;
        //calendario.set
    }

    public String calendario(Calendar s) {
        try {
            return s.get(Calendar.DAY_OF_MONTH) + "/" + (s.get(Calendar.MONTH) + 1) + "/" + s.get(Calendar.YEAR);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ingrese una fecha de ingreso");
        }
        return "";
    }

    public void ingresaPersonal() {
        String nombre = this.pantNombre.getText();
        String direccion = this.pantDireccion.getText();
        String carnet = this.pantNumCarnet.getText();
        String celular = this.pantCelular.getText();
        String ocupacion = this.pantOcupacion.getText();
        //if(!this.pantSueldo.getText().equals("")){
        String fecha = calendario(this.jDateChooser1.getCalendar());
        double sueldo = 0.0;
        try {
            sueldo = Double.valueOf(this.pantSueldo.getText());
            String maquina = this.jComboBox1.getSelectedItem().toString();

            if (!estanLlenas(nombre, direccion, carnet, celular, ocupacion, this.pantSueldo.getText(), maquina)) {

                Personal p = new Personal();
                p.setNombre(nombre);
                p.setCarnet(carnet);
                p.setCelular(celular);
                p.setDireccion(direccion);
                p.setOcupacion(ocupacion);
                p.setSueldo(sueldo);
                p.setDireccionFoto(".\\FotosPersonal\\" + this.pantNombre.getText() + ".png");
                p.setDireccionHuella(".\\FotosPersonal\\" + this.pantNombre.getText() + ".bmp");
                p.setNumMaquina(maquina);
                p.setFechaIngreso(fecha);
                NodoPersonal np = new NodoPersonal();
                np.setPersonal(p);
                FramePrincipal.cp.agregarFinalPersonal(np);

                FramePrincipal.cp.guardar();
                FreeSensor();
                textArea.setText("Registro Cerrado!");

                bloquearPantallas();
                ocultarBotones();
                pintarPantallasGris();
                limpiarTodo();
                this.jButton1.setVisible(true);
            } else {

                JOptionPane.showMessageDialog(null, "DEBE LLENAR TODAS LAS CASILLAS");

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "El valor que ingreso no es valido, o no ingreso ninguno.\nLos numeros deben tener un punto y no una coma\n Ejemplo: 9.87");
        }
        //}

    }

    public boolean estanLlenas(String nombre, String direccion, String carnet, String celular, String ocupacion, String sueldo, String maquina) {
        boolean sw = nombre.equals("") || direccion.equals("") || carnet.equals("") || celular.equals("") || ocupacion.equals("") || sueldo.equals("") || maquina.equals("");
        return sw;

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
            java.util.logging.Logger.getLogger(FramePersonal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FramePersonal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FramePersonal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FramePersonal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FramePersonal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BAceptarEdicion;
    private javax.swing.JButton BCancelar;
    private javax.swing.JButton BEditar;
    private javax.swing.JButton BEliminar;
    private javax.swing.JButton BEscaneo;
    private javax.swing.JButton BIngresar;
    private javax.swing.JButton BLimpiar;
    public static javax.swing.JButton BSacarFoto;
    private javax.swing.JButton btnImg;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField pantCelular;
    private javax.swing.JTextField pantDireccion;
    private javax.swing.JTextField pantNombre;
    private javax.swing.JTextField pantNumCarnet;
    private javax.swing.JTextField pantOcupacion;
    private javax.swing.JTextField pantSueldo;
    private javax.swing.JTextArea textArea;
    // End of variables declaration//GEN-END:variables

    /*
        *METODOS DEL BIOMETRICO
     */
    int fpWidth = 0;
    //the height of fingerprint image
    int fpHeight = 0;
    //for verify test
    private byte[] lastRegTemp = new byte[2048];
    //the length of lastRegTemp
    private int cbRegTemp = 0;
    //pre-register template
    private byte[][] regtemparray = new byte[3][2048];
    //Register
    private boolean bRegister = false;
    //Identify
    private boolean bIdentify = true;
    //finger id
    private int iFid = 1;

    private int nFakeFunOn = 1;
    //must be 3
    static final int enroll_cnt = 3;
    //the index of pre-register function
    private int enroll_idx = 0;

    private byte[] imgbuf = null;
    private byte[] template = new byte[2048];
    private int[] templateLen = new int[1];

    private boolean mbStop = true;
    private long mhDevice = 0;
    private long mhDB = 0;
    private WorkThread workThread = null;

    private void OnCatpureOK(byte[] imgBuf) {
        try {
            writeBitmap(imgBuf, fpWidth, fpHeight, ".\\FotosPersonal\\" + this.pantNombre.getText() + ".bmp");
            btnImg.setIcon(new ImageIcon(ImageIO.read(new File(".\\FotosPersonal\\" + this.pantNombre.getText() + ".bmp"))));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private class WorkThread extends Thread {

        @Override
        public void run() {
            super.run();
            int ret = 0;
            while (!mbStop) {
                templateLen[0] = 2048;
                if (0 == (ret = FingerprintSensorEx.AcquireFingerprint(mhDevice, imgbuf, template, templateLen))) {
                    if (nFakeFunOn == 1) {
                        byte[] paramValue = new byte[4];
                        int[] size = new int[1];
                        size[0] = 4;
                        int nFakeStatus = 0;
                        //GetFakeStatus
                        ret = FingerprintSensorEx.GetParameters(mhDevice, 2004, paramValue, size);
                        nFakeStatus = byteArrayToInt(paramValue);
                        System.out.println("ret = " + ret + ",nFakeStatus=" + nFakeStatus);
                        if (0 == ret && (byte) (nFakeStatus & 31) != 31) {
                            textArea.setText("Is a fake-finer?");
                            return;
                        }
                    }
                    OnCatpureOK(imgbuf);
                    OnExtractOK(template, templateLen[0]);
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }

        private void runOnUiThread(Runnable runnable) {
            // TODO Auto-generated method stub

        }
    }

    public static byte[] changeByte(int data) {
        return intToByteArray(data);
    }

    public static byte[] intToByteArray(final int number) {
        byte[] abyte = new byte[4];
        abyte[0] = (byte) (0xff & number);
        abyte[1] = (byte) ((0xff00 & number) >> 8);
        abyte[2] = (byte) ((0xff0000 & number) >> 16);
        abyte[3] = (byte) ((0xff000000 & number) >> 24);
        return abyte;
    }

    public static void writeBitmap(byte[] imageBuf, int nWidth, int nHeight,
            String path) throws IOException {
        java.io.FileOutputStream fos = new java.io.FileOutputStream(path);
        java.io.DataOutputStream dos = new java.io.DataOutputStream(fos);

        int w = (((nWidth + 3) / 4) * 4);
        int bfType = 0x424d;
        int bfSize = 54 + 1024 + w * nHeight;
        int bfReserved1 = 0;
        int bfReserved2 = 0;
        int bfOffBits = 54 + 1024;

        dos.writeShort(bfType);
        dos.write(changeByte(bfSize), 0, 4);
        dos.write(changeByte(bfReserved1), 0, 2);
        dos.write(changeByte(bfReserved2), 0, 2);
        dos.write(changeByte(bfOffBits), 0, 4);

        int biSize = 40;
        int biWidth = nWidth;
        int biHeight = nHeight;
        int biPlanes = 1;
        int biBitcount = 8;
        int biCompression = 0;
        int biSizeImage = w * nHeight;
        int biXPelsPerMeter = 0;
        int biYPelsPerMeter = 0;
        int biClrUsed = 0;
        int biClrImportant = 0;

        dos.write(changeByte(biSize), 0, 4);
        dos.write(changeByte(biWidth), 0, 4);
        dos.write(changeByte(biHeight), 0, 4);
        dos.write(changeByte(biPlanes), 0, 2);
        dos.write(changeByte(biBitcount), 0, 2);
        dos.write(changeByte(biCompression), 0, 4);
        dos.write(changeByte(biSizeImage), 0, 4);
        dos.write(changeByte(biXPelsPerMeter), 0, 4);
        dos.write(changeByte(biYPelsPerMeter), 0, 4);
        dos.write(changeByte(biClrUsed), 0, 4);
        dos.write(changeByte(biClrImportant), 0, 4);

        for (int i = 0; i < 256; i++) {
            dos.writeByte(i);
            dos.writeByte(i);
            dos.writeByte(i);
            dos.writeByte(0);
        }

        byte[] filter = null;
        if (w > nWidth) {
            filter = new byte[w - nWidth];
        }

        for (int i = 0; i < nHeight; i++) {
            dos.write(imageBuf, (nHeight - 1 - i) * nWidth, nWidth);
            if (w > nWidth) {
                dos.write(filter, 0, w - nWidth);
            }
        }
        dos.flush();
        dos.close();
        fos.close();
    }

    public static int byteArrayToInt(byte[] bytes) {
        int number = bytes[0] & 0xFF;
        number |= ((bytes[1] << 8) & 0xFF00);
        number |= ((bytes[2] << 16) & 0xFF0000);
        number |= ((bytes[3] << 24) & 0xFF000000);
        return number;
    }

    private void OnExtractOK(byte[] template, int len) {
        if (bRegister) {
            int[] fid = new int[1];
            int[] score = new int[1];
            int ret = FingerprintSensorEx.DBIdentify(mhDB, template, fid, score);
            if (ret == 0) {
                textArea.setText("the finger already enroll by " + fid[0] + ",cancel enroll");
                bRegister = false;
                enroll_idx = 0;
                return;
            }
            if (enroll_idx > 0 && FingerprintSensorEx.DBMatch(mhDB, regtemparray[enroll_idx - 1], template) <= 0) {
                textArea.setText("please press the same finger 3 times for the enrollment");
                return;
            }
            System.arraycopy(template, 0, regtemparray[enroll_idx], 0, 2048);
            enroll_idx++;
            if (enroll_idx == 3) {
                int[] _retLen = new int[1];
                _retLen[0] = 2048;
                byte[] regTemp = new byte[_retLen[0]];

                if (0 == (ret = FingerprintSensorEx.DBMerge(mhDB, regtemparray[0], regtemparray[1], regtemparray[2], regTemp, _retLen))
                        && 0 == (ret = FingerprintSensorEx.DBAdd(mhDB, iFid, regTemp))) {
                    iFid++;
                    cbRegTemp = _retLen[0];
                    System.arraycopy(regTemp, 0, lastRegTemp, 0, cbRegTemp);
                    //Base64 Template

                    OnCatpureOK(imgbuf);
                    //writeBitmap(imgBuf, fpWidth, fpHeight, "fingerprint.bmp");
                    textArea.setText("Presione otra vez para finalizar");
                } else {
                    textArea.setText("enroll fail, error code=" + ret);
                }
                bRegister = false;
            } else {
                textArea.setText("Te falta presionar " + (3 - enroll_idx) + " Veces mas");
            }
        } else if (bIdentify) {
            int[] fid = new int[1];
            int[] score = new int[1];
            int ret = FingerprintSensorEx.DBIdentify(mhDB, template, fid, score);
            if (ret == 0) {
                textArea.setText("Identificacion realizada, fid=" + fid[0] + ",score=" + score[0]);
            } else {
                textArea.setText("Identificacion fallida, errcode=" + ret);
            }

        } else if (cbRegTemp <= 0) {
            textArea.setText("Porfavor registre primero!");
        } else {
            int ret = FingerprintSensorEx.DBMatch(mhDB, lastRegTemp, template);
            if (ret > 0) {
                textArea.setText("Verificacion realizada, score=" + ret);
            } else {
                textArea.setText("Verificacion fallida, ret=" + ret);
            }
        }
    }

    private void FreeSensor() {
        mbStop = true;
        try {		//wait for thread stopping
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (0 != mhDB) {
            FingerprintSensorEx.DBFree(mhDB);
            mhDB = 0;
        }
        if (0 != mhDevice) {
            FingerprintSensorEx.CloseDevice(mhDevice);
            mhDevice = 0;
        }
        FingerprintSensorEx.Terminate();
    }

    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("imagenes/engranaje3_1.png"));
        return retValue;
    }
}
