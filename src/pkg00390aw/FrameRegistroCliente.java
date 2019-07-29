package pkg00390aw;

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

public class FrameRegistroCliente extends javax.swing.JFrame {

    public FrameRegistroCliente() {
        initComponents();
        reiniciarTabla1();
        generarTabla();
        setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
        this.jScrollPane2.setVisible(false);
        this.setLocationRelativeTo(null);
        this.jButton5.setVisible(false);

        ocultarBotones();
        this.BEmpAgregar.setVisible(true);
        bloquearPantallas();
        pintarGris();
    }

    public void obtenerDelFiltro(String nom) {
        this.pantNombre.setText(nom);
    }

    public void ocultarBotones() {
        this.jButton1.setVisible(false);
        this.jButton2.setVisible(false);
        this.jButton3.setVisible(false);
        this.BEditar.setVisible(false);
        this.BEmpAgregar.setVisible(false);
        this.BEmpEditar.setVisible(false);
        this.BCancelar.setVisible(false);
    }

    public void habilitarPantallas() {
        this.pantNombre.setEditable(true);
        this.pantCi.setEditable(true);
        this.pantEmpresa.setEditable(true);
        this.pantTelefono.setEditable(true);
        this.pantDireccion.setEditable(true);
        this.pantCorreo.setEditable(true);

    }

    public void bloquearPantallas() {
        this.pantNombre.setEditable(false);
        this.pantCi.setEditable(false);
        this.pantEmpresa.setEditable(false);
        this.pantTelefono.setEditable(false);
        this.pantDireccion.setEditable(false);
        this.pantCorreo.setEditable(false);
    }

    //this.pantNombre.setBackground(new java.awt.Color(153, 153, 153));
    public void pintarGris() {
        this.pantNombre.setBackground(new java.awt.Color(153, 153, 153));
        this.pantCi.setBackground(new java.awt.Color(153, 153, 153));
        this.pantEmpresa.setBackground(new java.awt.Color(153, 153, 153));
        this.pantTelefono.setBackground(new java.awt.Color(153, 153, 153));
        this.pantDireccion.setBackground(new java.awt.Color(153, 153, 153));
        this.pantCorreo.setBackground(new java.awt.Color(153, 153, 153));
    }

    public void pintarBlanco() {
        this.pantNombre.setBackground(new java.awt.Color(255, 255, 255));
        this.pantCi.setBackground(new java.awt.Color(255, 255, 255));
        this.pantEmpresa.setBackground(new java.awt.Color(255, 255, 255));
        this.pantTelefono.setBackground(new java.awt.Color(255, 255, 255));
        this.pantDireccion.setBackground(new java.awt.Color(255, 255, 255));
        this.pantCorreo.setBackground(new java.awt.Color(255, 255, 255));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        pantNombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        pantEmpresa = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        pantDireccion = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        pantCi = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        pantTelefono = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        pantCorreo = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        BGuardarExcel = new javax.swing.JButton();
        BImportar = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        BEditar = new javax.swing.JButton();
        BEmpAgregar = new javax.swing.JButton();
        BEmpEditar = new javax.swing.JButton();
        BCancelar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel1.setText("REGISTRO DE CLIENTE");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(222, 11, 231, 46));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("NOMBRE");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 70, 130, 30));

        pantNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pantNombreActionPerformed(evt);
            }
        });
        getContentPane().add(pantNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, 220, 40));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("EMPRESA");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 140, 120, 30));

        pantEmpresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pantEmpresaActionPerformed(evt);
            }
        });
        getContentPane().add(pantEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, 220, 40));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("DIRECCION");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 210, 120, 30));

        pantDireccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pantDireccionActionPerformed(evt);
            }
        });
        getContentPane().add(pantDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 240, 220, 40));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("NIT O CI");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 70, 140, 30));

        pantCi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pantCiActionPerformed(evt);
            }
        });
        getContentPane().add(pantCi, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 100, 220, 40));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("TELEFONO");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 140, 180, 30));

        pantTelefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pantTelefonoActionPerformed(evt);
            }
        });
        getContentPane().add(pantTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 170, 220, 40));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("CORREO");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 210, 170, 30));
        getContentPane().add(pantCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 240, 220, 40));

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/aceptar.png"))); // NOI18N
        jButton1.setText("AGREGAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 280, 150, 30));

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/gnome_edit_clear.png"))); // NOI18N
        jButton2.setText("LIMPIAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 320, 170, 40));

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/deletered.png"))); // NOI18N
        jButton3.setText("ELIMINAR");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 320, 170, 40));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NOMBRE", "NIT O CI", "EMPRESA", "TELEFONO","CORREO","DIRECCION"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 370, 580, 100));

        BGuardarExcel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        BGuardarExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/excel.png"))); // NOI18N
        BGuardarExcel.setText("GUARDAR EN EXCEL");
        BGuardarExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BGuardarExcelActionPerformed(evt);
            }
        });
        getContentPane().add(BGuardarExcel, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 480, 240, 60));

        BImportar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        BImportar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/import.png"))); // NOI18N
        BImportar.setText("IMPORTAR");
        BImportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BImportarActionPerformed(evt);
            }
        });
        getContentPane().add(BImportar, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 480, 190, 60));

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/back.png"))); // NOI18N
        jButton4.setText("ATRAS");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 10, 160, 50));

        jButton5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cancel2.png"))); // NOI18N
        jButton5.setText("CERRAR");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 10, 140, 50));

        BEditar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        BEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/aceptar.png"))); // NOI18N
        BEditar.setText("ACEPTAR EDICION");
        BEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BEditarActionPerformed(evt);
            }
        });
        getContentPane().add(BEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 290, 220, 30));

        BEmpAgregar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        BEmpAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/group_add_128.png"))); // NOI18N
        BEmpAgregar.setText("EMPEZAR A AGREGAR");
        BEmpAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BEmpAgregarActionPerformed(evt);
            }
        });
        getContentPane().add(BEmpAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 290, 270, 70));

        BEmpEditar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        BEmpEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/edit.png"))); // NOI18N
        BEmpEditar.setText("EMPEZAR A EDITAR");
        BEmpEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BEmpEditarActionPerformed(evt);
            }
        });
        getContentPane().add(BEmpEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 290, 210, 70));

        BCancelar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        BCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cancel2.png"))); // NOI18N
        BCancelar.setText("CANCELAR");
        BCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BCancelarActionPerformed(evt);
            }
        });
        getContentPane().add(BCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(235, 320, 150, 40));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NOMBRE", "NIT O CI", "EMPRESA", "TELEFONO","CORREO","DIRECCION"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 580, 190));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pantNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pantNombreActionPerformed
        this.pantCi.requestFocus();
        this.pantCi.selectAll();

    }//GEN-LAST:event_pantNombreActionPerformed

    private void pantCiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pantCiActionPerformed
        this.pantEmpresa.requestFocus();
        this.pantEmpresa.selectAll();
    }//GEN-LAST:event_pantCiActionPerformed

    private void pantEmpresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pantEmpresaActionPerformed
        this.pantTelefono.requestFocus();
        this.pantTelefono.selectAll();
    }//GEN-LAST:event_pantEmpresaActionPerformed

    private void pantTelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pantTelefonoActionPerformed
        this.pantDireccion.requestFocus();
        this.pantDireccion.selectAll();
    }//GEN-LAST:event_pantTelefonoActionPerformed

    private void pantDireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pantDireccionActionPerformed
        this.pantCorreo.requestFocus();
        this.pantCorreo.selectAll();
    }//GEN-LAST:event_pantDireccionActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (verificaNombre()) {
            agregar();
            FramePrincipal.cp.guardar(FramePrincipal.cp.getLrc());
            reiniciarTabla1();
            generarTabla();

            limpiarVentanas();
            ocultarBotones();
            this.BEmpAgregar.setVisible(true);
            bloquearPantallas();
            pintarGris();
        } else {
            JOptionPane.showMessageDialog(null, "ESTE PROVEEDOR YA ESTA REGISTRADO");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    public boolean verificaNombre() {
        NodoRegistroCliente w = FramePrincipal.cp.getLrc().getP();
        while (w != null) {
            if (w.getRegistro().getNombre().equalsIgnoreCase(this.pantNombre.getText())) {
                return false;
            }
            w = w.getSig();
        }
        return true;
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        limpiarVentanas();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int fila = this.jTable1.getSelectedRow();
        String nombre = this.jTable1.getValueAt(fila, 0).toString();

        FramePrincipal.cp.getLrc().eliminar(nombre);
        reiniciarTabla1();
        generarTabla();
        FramePrincipal.cp.guardar(FramePrincipal.cp.getLrc());

        limpiarVentanas();
        ocultarBotones();
        this.BEmpAgregar.setVisible(true);
        bloquearPantallas();
        pintarGris();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void BGuardarExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BGuardarExcelActionPerformed
        try {
            guardarExcel();
            JOptionPane.showMessageDialog(null, "SE GUARDO CORRECTAMENTE");
        } catch (IOException ex) {
            Logger.getLogger(FrameRegistroCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (WriteException ex) {
            Logger.getLogger(FrameRegistroCliente.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_BGuardarExcelActionPerformed

    private void BImportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BImportarActionPerformed
        FrameImportar a = new FrameImportar(".\\ClientesExportados\\");
        a.setSw(4);
        a.show(true);
    }//GEN-LAST:event_BImportarActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
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
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void BEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BEditarActionPerformed
        editar();
        reiniciarTabla1();
        generarTabla();

        limpiarVentanas();
        ocultarBotones();
        this.BEmpAgregar.setVisible(true);
        bloquearPantallas();
        pintarGris();
    }//GEN-LAST:event_BEditarActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        recuperarTabla();
        bloquearPantallas();
        pintarGris();
    }//GEN-LAST:event_jTable1MouseClicked

    private void BEmpAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BEmpAgregarActionPerformed
        ocultarBotones();
        this.jButton1.setVisible(true);
        this.jButton2.setVisible(true);
        this.BCancelar.setVisible(true);
        pintarBlanco();
        habilitarPantallas();

    }//GEN-LAST:event_BEmpAgregarActionPerformed

    private void BCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BCancelarActionPerformed
        limpiarVentanas();
        ocultarBotones();
        this.BEmpAgregar.setVisible(true);
        bloquearPantallas();
        pintarGris();
    }//GEN-LAST:event_BCancelarActionPerformed

    private void BEmpEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BEmpEditarActionPerformed
        habilitarPantallas();
        pintarBlanco();
        this.pantNombre.setEditable(false);
        this.pantNombre.setBackground(new java.awt.Color(153, 153, 153));
        ocultarBotones();
        this.jButton3.setVisible(true);
        this.BEditar.setVisible(true);
        this.BCancelar.setVisible(true);
    }//GEN-LAST:event_BEmpEditarActionPerformed

    public void recuperarTabla() {
        int fila = this.jTable1.getSelectedRow();
        this.pantNombre.setText(this.jTable1.getValueAt(fila, 0).toString());
        this.pantCi.setText(this.jTable1.getValueAt(fila, 1).toString());
        this.pantEmpresa.setText(this.jTable1.getValueAt(fila, 2).toString());
        this.pantTelefono.setText(this.jTable1.getValueAt(fila, 3).toString());
        this.pantCorreo.setText(this.jTable1.getValueAt(fila, 4).toString());
        this.pantDireccion.setText(this.jTable1.getValueAt(fila, 5).toString());
        ocultarBotones();
        this.BEmpAgregar.setVisible(true);
        this.BEmpEditar.setVisible(true);
    }

    public void importarExcel(String dir) throws IOException, BiffException {//
        reiniciarTabla2();
        Workbook workbook = Workbook.getWorkbook(new File(dir));
        Sheet hoja1 = workbook.getSheet(0);
        for (int fila = 1; fila < hoja1.getRows(); fila++) {
            generarFila2();
            for (int columna = 0; columna < hoja1.getColumns(); columna++) {
                String dato = hoja1.getCell(columna, fila).getContents();
                this.jTable2.setValueAt(dato, fila - 1, columna);
            }
        }
        ocultarBotones();
        this.BGuardarExcel.setVisible(false);
        this.BImportar.setVisible(false);
        this.jButton1.setVisible(false);
        this.jButton2.setVisible(false);
        this.jButton3.setVisible(false);
        this.jButton4.setVisible(false);
        this.jButton5.setVisible(true);
        this.jScrollPane2.setVisible(true);
        this.jScrollPane1.setVisible(false);
        this.jLabel1.setVisible(false);
        this.jLabel2.setVisible(false);
        this.jLabel3.setVisible(false);
        this.jLabel4.setVisible(false);
        this.jLabel5.setVisible(false);
        this.jLabel6.setVisible(false);
        this.jLabel7.setVisible(false);
        this.pantNombre.setVisible(false);
        this.pantCi.setVisible(false);
        this.pantEmpresa.setVisible(false);
        this.pantTelefono.setVisible(false);
        this.pantDireccion.setVisible(false);
        this.pantCorreo.setVisible(false);
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
        fecha = dia + "_" + mes + "_" + anio + "_" + hora + "_" + minuto + "_" + segundo;

        return fecha;
    }

    public void guardarExcel() throws IOException, WriteException {
        WritableWorkbook libro1 = Workbook.createWorkbook(new File(".\\ClientesExportados\\" + ObtenerFecha() + "_Clientes.xls"));
        WritableSheet hoja1 = libro1.createSheet("CLIENTES", 0);

        Label etiqueta = new Label(0, 0, "NOMBRE");
        hoja1.addCell(etiqueta);
        etiqueta = new Label(1, 0, "NIT O CI");
        hoja1.addCell(etiqueta);
        etiqueta = new Label(2, 0, "EMPRESA");
        hoja1.addCell(etiqueta);
        etiqueta = new Label(3, 0, "TELEFONO");
        hoja1.addCell(etiqueta);
        etiqueta = new Label(4, 0, "CORREO");
        hoja1.addCell(etiqueta);
        etiqueta = new Label(5, 0, "DIRECCION");
        hoja1.addCell(etiqueta);

        NodoRegistroCliente aux = FramePrincipal.cp.getLrc().getP();
        int i = 0;
        while (aux != null) {
            i++;

            etiqueta = new Label(0, i, aux.getRegistro().getNombre());
            hoja1.addCell(etiqueta);
            etiqueta = new Label(1, i, aux.getRegistro().getCi());
            hoja1.addCell(etiqueta);
            etiqueta = new Label(2, i, aux.getRegistro().getEmpresa());
            hoja1.addCell(etiqueta);
            etiqueta = new Label(3, i, aux.getRegistro().getTelefono());
            hoja1.addCell(etiqueta);
            etiqueta = new Label(4, i, aux.getRegistro().getCorreo());
            hoja1.addCell(etiqueta);;
            etiqueta = new Label(5, i, aux.getRegistro().getDireccion());
            hoja1.addCell(etiqueta);

            aux = aux.getSig();
        }
        libro1.write();
        libro1.close();
    }

    public void limpiarVentanas() {
        this.pantNombre.setText("");
        this.pantCi.setText("");
        this.pantEmpresa.setText("");
        this.pantTelefono.setText("");
        this.pantDireccion.setText("");
        this.pantCorreo.setText("");
    }

    public void reiniciarTabla1() {
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "NOMBRE", "NIT O CI", "EMPRESA", "TELEFONO", "CORREO", "DIRECCION"
                }
        ));
    }

    public void reiniciarTabla2() {
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "NOMBRE", "NIT O CI", "EMPRESA", "TELEFONO", "CORREO", "DIRECCION"
                }
        ));
    }

    public void generarTabla() {
        NodoRegistroCliente w = FramePrincipal.cp.getLrc().getP();
        int i = 0;
        while (w != null) {
            generarFila();
            llenarTabla(w.getRegistro(), i);
            i++;
            w = w.getSig();
        }
    }

    public void generarFila() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.addRow(new Object[]{""});
    }

    public void generarFila2() {
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        model.addRow(new Object[]{""});
    }

    public void llenarTabla(RegistroCliente r, int i) {
        this.jTable1.setValueAt(r.getNombre(), i, 0);
        this.jTable1.setValueAt(r.getCi(), i, 1);
        this.jTable1.setValueAt(r.getEmpresa(), i, 2);
        this.jTable1.setValueAt(r.getTelefono(), i, 3);
        this.jTable1.setValueAt(r.getCorreo(), i, 4);
        this.jTable1.setValueAt(r.getDireccion(), i, 5);
    }

    public void agregar() {
        RegistroCliente w = new RegistroCliente();
        w.setNombre(this.pantNombre.getText());
        w.setCi(this.pantCi.getText());
        w.setEmpresa(this.pantEmpresa.getText());
        w.setTelefono(this.pantTelefono.getText());
        w.setDireccion(this.pantDireccion.getText());
        w.setCorreo(this.pantCorreo.getText());
        NodoRegistroCliente a = new NodoRegistroCliente();
        a.setRegistro(w);
        FramePrincipal.cp.getLrc().adiFinal(a);
        limpiarVentanas();
        //FramePrincipal.cp.guardar(FramePrincipal.cp.getLrc());
    }

    public void editar() {
        RegistroCliente w = new RegistroCliente();
        w.setNombre(this.pantNombre.getText());
        w.setCi(this.pantCi.getText());
        w.setEmpresa(this.pantEmpresa.getText());
        w.setTelefono(this.pantTelefono.getText());
        w.setDireccion(this.pantDireccion.getText());
        w.setCorreo(this.pantCorreo.getText());
        FramePrincipal.cp.getLrc().editar(this.pantNombre.getText(), w);
        limpiarVentanas();
        FramePrincipal.cp.guardar(FramePrincipal.cp.getLrc());
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
            java.util.logging.Logger.getLogger(FrameRegistroCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameRegistroCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameRegistroCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameRegistroCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameRegistroCliente().setVisible(true);
            }
        });
    }

    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("imagenes/engranaje3_1.png"));
        return retValue;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BCancelar;
    private javax.swing.JButton BEditar;
    private javax.swing.JButton BEmpAgregar;
    private javax.swing.JButton BEmpEditar;
    private javax.swing.JButton BGuardarExcel;
    private javax.swing.JButton BImportar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField pantCi;
    private javax.swing.JTextField pantCorreo;
    private javax.swing.JTextField pantDireccion;
    private javax.swing.JTextField pantEmpresa;
    private javax.swing.JTextField pantNombre;
    private javax.swing.JTextField pantTelefono;
    // End of variables declaration//GEN-END:variables
}
