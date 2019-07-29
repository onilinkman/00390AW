package pkg00390aw;

import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class FrameFacturaCliente extends javax.swing.JFrame {

    public FrameFacturaCliente() throws IOException, ClassNotFoundException {
        initComponents();
        this.setLocationRelativeTo(null);
        File file = new File("FacturaCliente.dat");
        if (file.exists()) {
            lfc = lfc.recuperar();
        }
        llenarTabla1();
    }

    private ListaFacturaCliente lfc = new ListaFacturaCliente();

    public void llenarTabla1() {
        Queue<FacturaCliente> aux = new LinkedList();
        aux.addAll(this.lfc.getP());
        int i = 0;
        while (!aux.isEmpty()) {
            agregarFilaTabla1();
            FacturaCliente fc = aux.remove();
            this.jTable1.setValueAt((i + 1), i, 0);
            this.jTable1.setValueAt(fc.getFecha(), i, 1);
            this.jTable1.setValueAt(fc.getNroFactura(), i, 2);
            this.jTable1.setValueAt(fc.getNroAutorizacion(), i, 3);
            this.jTable1.setValueAt(fc.getEstado(), i, 4);
            this.jTable1.setValueAt(fc.getCi(), i, 5);
            this.jTable1.setValueAt(fc.getNombre(), i, 6);
            this.jTable1.setValueAt(fc.getTotalImporte(), i, 7);
            this.jTable1.setValueAt(0.00, i, 8);
            this.jTable1.setValueAt(0.00, i, 9);
            this.jTable1.setValueAt(0.00, i, 10);
            this.jTable1.setValueAt(fc.getTotalImporte(), i, 11);
            this.jTable1.setValueAt(0.00, i, 12);
            this.jTable1.setValueAt(fc.getTotalImporte(), i, 13);
            DecimalFormat df = new DecimalFormat("#.00");
            this.jTable1.setValueAt(convertirComa(df.format(fc.getTotalImporte() * 0.13)), i, 14);
            this.jTable1.setValueAt(fc.getCodigoControl(), i, 15);
            i++;
        }
    }

    public String convertirComa(String s) {
        String cad = "";
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ',') {
                cad = cad + ".";
            } else {
                cad = cad + s.charAt(i);
            }
        }
        return cad;
    }

    public void agregarFilaTabla1() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.addRow(new Object[]{});
    }

    public void reiniciarTabla1() {
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "CONT", "fecha", "Nro Factura", "Nro Autorizacion", "Estado", "NIT/CI Cliente", "Nombre Razon Social", "Total Importe", "Tas", "ex", "Tasa cero", "subtotal", "reb", "Debito", "Fiscal", "Codigo Control"
                }
        ));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "CONT","fecha", "NRO FACTURA", "NRO AUTORIZACION", "ESTADO","NIT/CI CLIENTE","NOMBRE RAZON SOCIAL","Total Importe","Tas","ex","Tasa cero","subtotal","reb","Debito","Fiscal","Codigo Control"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 11, 1290, 350));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/download (1).png"))); // NOI18N
        jButton1.setText("VER GUARDADOS");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 380, 190, -1));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/excel.png"))); // NOI18N
        jButton2.setText("EXPORTAR A EXCEL");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 380, -1, -1));

        jButton3.setText("VER FACTURA");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 380, 200, 40));

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton4.setText("guardar copia de imagen de la factura en una carpeta");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 380, 390, 80));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int fila = this.jTable1.getSelectedRow();
        Queue<FacturaCliente> aux = new LinkedList();
        aux.addAll(this.lfc.getP());
        int i = 0;
        while (!aux.isEmpty()) {
            FacturaCliente fc = aux.remove();
            if (fila == i) {
                if (fc.getImagenFactura() != null) {
                    InputStream in = new ByteArrayInputStream(fc.getImagenFactura());
                    try {
                        BufferedImage imagen = ImageIO.read(in);
                        ImageIO.write(imagen, "PNG", new File("preVisualizarFactura.png"));
                        File objeto = new File("preVisualizarFactura.png");
                        Desktop.getDesktop().open(objeto);
                    } catch (IOException ex) {
                        Logger.getLogger(FrameFacturaCliente.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No Tiene imagen");
                }
            }
            i++;
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            guardarExcel();
            JOptionPane.showMessageDialog(null, "Se guardo correctamente");
        } catch (IOException ex) {
            Logger.getLogger(FrameFacturaCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (WriteException ex) {
            Logger.getLogger(FrameFacturaCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        FrameImportar fi=new FrameImportar();
        fi.setPath(".\\ListaVentasConFactura\\");
        fi.llenarTabla();
        fi.setSw(5);
        fi.show(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        File f=new File(".\\"+fecha()+"\\");
        f.mkdirs();
        
        Queue<FacturaCliente> aux = new LinkedList();
        aux.addAll(this.lfc.getP());
        while (!aux.isEmpty()) {
            FacturaCliente fc = aux.remove();
                if (fc.getImagenFactura() != null) {
                    InputStream in = new ByteArrayInputStream(fc.getImagenFactura());
                    try {
                        BufferedImage imagen = ImageIO.read(in);
                        ImageIO.write(imagen, "PNG", new File(f.getAbsolutePath()+"\\"+fc.getNroFactura()+".png"));
                        //File objeto = new File("preVisualizarFactura.png");
                        Desktop.getDesktop().open(f);
                    } catch (IOException ex) {
                        Logger.getLogger(FrameFacturaCliente.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No Tiene imagen");
                }
            
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    public String fecha() {
        String f = "";
        int dia, mes, anio;
        Calendar calendario = Calendar.getInstance();
        dia = calendario.get(Calendar.DAY_OF_MONTH);
        mes = calendario.get(Calendar.MONTH) + 1;
        anio = calendario.get(Calendar.YEAR);
        f = dia + "_" + mes + "_" + anio;
        return f;
    }
    
    public void importarExcel(String dir) throws IOException, BiffException{
        reiniciarTabla1();
        Workbook workbook = Workbook.getWorkbook(new File(dir));
        Sheet hoja1 = workbook.getSheet(0);
        for(int fila=1;fila<hoja1.getRows();fila++){
            agregarFilaTabla1();
            for(int columna=0;columna<hoja1.getColumns();columna++){
                String dato = hoja1.getCell(columna, fila).getContents();
                System.out.println(dato);
                this.jTable1.setValueAt(dato, fila-1, columna);
            }
        }
    }
    
    public void guardarExcel() throws IOException, WriteException {
        WritableWorkbook libro1 = Workbook.createWorkbook(new File(".\\ListaVentasConFactura\\Facturas_" + ObtenerFecha2() + ".xls"));
        WritableSheet hoja1 = libro1.createSheet("REGISTRO DE FACTURAS", 0);

        Label etiqueta = new Label(0, 0, "CONTADOR");
        hoja1.addCell(etiqueta);
        etiqueta = new Label(1, 0, "FECHA");
        hoja1.addCell(etiqueta);
        etiqueta = new Label(2, 0, "Nro DEFACTURA");
        hoja1.addCell(etiqueta);
        etiqueta = new Label(3, 0, "Nro DE AUTORIZACION");
        hoja1.addCell(etiqueta);
        etiqueta = new Label(4, 0, "ESTADO");
        hoja1.addCell(etiqueta);
        etiqueta = new Label(5, 0, "NIT/CI cliente");
        hoja1.addCell(etiqueta);
        etiqueta = new Label(6, 0, "Nombre razon social");
        hoja1.addCell(etiqueta);
        etiqueta = new Label(7, 0, "TOTAL IMPORTE");
        hoja1.addCell(etiqueta);
        etiqueta = new Label(8, 0, "Tas");
        hoja1.addCell(etiqueta);
        etiqueta = new Label(9, 0, "EX");
        hoja1.addCell(etiqueta);
        etiqueta = new Label(10, 0, "TASA CERO");
        hoja1.addCell(etiqueta);
        etiqueta = new Label(11, 0, "SUBTOTAL");
        hoja1.addCell(etiqueta);
        etiqueta = new Label(12, 0, "REB");
        hoja1.addCell(etiqueta);
        etiqueta = new Label(13, 0, "DEBITO");
        hoja1.addCell(etiqueta);
        etiqueta = new Label(14, 0, "FISCAL");
        hoja1.addCell(etiqueta);
        etiqueta = new Label(15, 0, "CODIGO DE CONTROL");
        hoja1.addCell(etiqueta);

        int filas = this.jTable1.getRowCount();
        int columnas = this.jTable1.getColumnCount();
        for (int i = 1; i <= filas; i++) {
            for (int j = 0; j < columnas; j++) {
                etiqueta = new Label(j, i, this.jTable1.getValueAt(i - 1, j).toString());
                hoja1.addCell(etiqueta);
            }
        }
        libro1.write();
        libro1.close();
    }

    public String ObtenerFecha2() {
        String fecha;
        Calendar calendario = Calendar.getInstance();
        int dia = calendario.get(Calendar.DAY_OF_MONTH);
        int mes = calendario.get(Calendar.MONTH) + 1;
        int anio = calendario.get(Calendar.YEAR);
        int hora = calendario.get(Calendar.HOUR_OF_DAY);
        int minuto = calendario.get(Calendar.MINUTE);
        int segundo = calendario.get(Calendar.SECOND);
        fecha = dia + "_" + mes + "_" + anio + "__" + hora + "" + minuto + "" + segundo;

        return fecha;
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
            java.util.logging.Logger.getLogger(FrameFacturaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameFacturaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameFacturaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameFacturaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new FrameFacturaCliente().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(FrameFacturaCliente.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(FrameFacturaCliente.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
