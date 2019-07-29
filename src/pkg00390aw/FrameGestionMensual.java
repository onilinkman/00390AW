/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg00390aw;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author WINDOWS 10
 */
public class FrameGestionMensual extends javax.swing.JFrame {

    /**
     * Creates new form FrameGestionMensual
     */
    public FrameGestionMensual() throws IOException, ClassNotFoundException {
        initComponents();
        this.setLocationRelativeTo(null);
        this.lsd = new ListaStockDatos();

        File f1 = new File("ListaStockDatos.dat");
        if (f1.exists()) {
            this.lsd = lsd.recuperar();
            llenarTabla2();
        }
        totalCompra();

        this.lpm = new ListaPedidosMes();
        File f4 = new File("ListaPedidosMes.dat");
        if (f4.exists()) {
            this.lpm = lpm.recuperar();

        }
        File f = new File("datosProductos.dat");
        if (f.exists()) {
            this.p = this.p.recuperar();

            //agregarProducto();
            //llenarTabla1();
        }
        llenarTabla1();
        llenarTabla3();
        crearComboBox();
        File archivo = new File(".\\registrosMensuales\\");
        archivo.mkdirs();

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
        fecha = dia + "_" + mes + "_" + anio;

        return fecha;
    }

    public void guardarAExcelPersonal(NodoPersonal personal) throws IOException, WriteException {
        int nroCas = this.jTable1.getRowCount();

        String path = ".\\GestionesDelPersonal\\" + ObtenerFecha2() + "_" + personal.getPersonal().getNombre() + ".xls";
        WritableWorkbook libro1 = Workbook.createWorkbook(new File(path));
        WritableSheet hoja1 = libro1.createSheet("REGISTRO", 0);

        Label etiqueta = new Label(0, 0, "FECHA");
        hoja1.addCell(etiqueta);
        etiqueta = new Label(1, 0, "INGRESO");
        hoja1.addCell(etiqueta);
        etiqueta = new Label(2, 0, "SALIDA");
        hoja1.addCell(etiqueta);
        etiqueta = new Label(3, 0, "ADELANTO");
        hoja1.addCell(etiqueta);
        etiqueta = new Label(4, 0, "DESCUENTO");
        hoja1.addCell(etiqueta);
        etiqueta = new Label(5, 0, "FALTA");
        hoja1.addCell(etiqueta);
        etiqueta = new Label(6, 0, "PERMISO");
        hoja1.addCell(etiqueta);

        NodoControlPersonal aux = personal.getPersonal().getControl().getP();

        int i = 0;
        while (aux != null) {
            i++;
            etiqueta = new Label(0, i, aux.getCp().getFecha());
            hoja1.addCell(etiqueta);

            String minutoEntrada;
            if (aux.getCp().getMinutoEntrada() < 10) {
                minutoEntrada = "0" + aux.getCp().getMinutoEntrada();
            } else {
                minutoEntrada = String.valueOf(aux.getCp().getMinutoEntrada());
            }
            etiqueta = new Label(1, i, aux.getCp().getHoraEntrada() + ":" + minutoEntrada);
            hoja1.addCell(etiqueta);

            String minutoSalida;
            if (aux.getCp().getMinutoSalida() < 10) {
                minutoSalida = "0" + aux.getCp().getMinutoSalida();
            } else {
                minutoSalida = String.valueOf(aux.getCp().getMinutoSalida());
            }
            etiqueta = new Label(2, i, aux.getCp().getHoraSalida() + ":" + minutoSalida);
            hoja1.addCell(etiqueta);

            etiqueta = new Label(3, i, String.valueOf(aux.getCp().getAdelanto()));
            hoja1.addCell(etiqueta);

            etiqueta = new Label(4, i, String.valueOf(aux.getCp().getDescuento() * personal.getPersonal().getSueldo()));
            hoja1.addCell(etiqueta);

            if (aux.getCp().isFalta() == true) {
                if (aux.getCp().isPermiso() == true) {
                    etiqueta = new Label(5, i, "NO");
                    hoja1.addCell(etiqueta);
                } else {
                    etiqueta = new Label(5, i, "SI");
                    hoja1.addCell(etiqueta);
                }
            } else {
                etiqueta = new Label(5, i, "NO");
                hoja1.addCell(etiqueta);
            }

            if (aux.getCp().isPermiso() == true) {
                etiqueta = new Label(6, i, "SI");
                hoja1.addCell(etiqueta);
            } else {
                etiqueta = new Label(6, i, "NO");
                hoja1.addCell(etiqueta);
            }

            etiqueta = new Label(7, i, String.valueOf(obtenerHorasExtra(aux)));
            hoja1.addCell(etiqueta);

            aux = aux.getSig();
        }

        WritableSheet hoja2 = libro1.createSheet("CALCULOS", 1);

        etiqueta = new Label(0, 0, "TOTAL ADELANTOS");
        hoja2.addCell(etiqueta);
        etiqueta = new Label(1, 0, "TOTAL DESCUENTOS");
        hoja2.addCell(etiqueta);
        etiqueta = new Label(2, 0, "TOTAL FALTAS");
        hoja2.addCell(etiqueta);
        etiqueta = new Label(3, 0, "TOTAL PERMISOS");
        hoja2.addCell(etiqueta);
        etiqueta = new Label(4, 0, "TOTAL A PAGAR");
        hoja2.addCell(etiqueta);

        etiqueta = new Label(0, 1, String.valueOf(totalAdelantos(personal)));
        hoja2.addCell(etiqueta);
        etiqueta = new Label(1, 1, String.valueOf(totalDescuento(personal)));
        hoja2.addCell(etiqueta);
        etiqueta = new Label(2, 1, String.valueOf(totalFaltas(personal)));
        hoja2.addCell(etiqueta);
        etiqueta = new Label(3, 1, String.valueOf(totalPermisos(personal)));
        hoja2.addCell(etiqueta);
        etiqueta = new Label(4, 1, String.valueOf(personal.getPersonal().getSueldoReal()));
        hoja2.addCell(etiqueta);

        libro1.write();
        libro1.close();
        /*try {

            File objetofile = new File(path);
            Desktop.getDesktop().open(objetofile);

        } catch (IOException ex) {

            System.out.println(ex);
        }*/
    }

    public int totalPermisos(NodoPersonal w) {
        int total = 0;
        NodoControlPersonal aux = w.getPersonal().getControl().getP();
        while (aux != null) {
            if (aux.getCp().isPermiso() == true) {
                total++;
            }
            aux = aux.getSig();
        }
        return total;
    }

    public int totalFaltas(NodoPersonal w) {
        int total = 0;
        NodoControlPersonal aux = w.getPersonal().getControl().getP();
        while (aux != null) {
            if (aux.getCp().isPermiso() == false) {
                if (aux.getCp().isFalta() == true) {
                    total++;
                }
            }
            aux = aux.getSig();
        }
        return total;
    }

    public double totalDescuento(NodoPersonal w) {
        double total = 0;
        NodoControlPersonal aux = w.getPersonal().getControl().getP();
        while (aux != null) {
            total = total + aux.getCp().getDescuento();
            aux = aux.getSig();
        }
        total = total * w.getPersonal().getSueldo();
        return total;
    }

    public double totalAdelantos(NodoPersonal w) {
        double total = 0;
        NodoControlPersonal aux = w.getPersonal().getControl().getP();
        while (aux != null) {
            total = total + aux.getCp().getAdelanto();
            aux = aux.getSig();
        }
        return total;

    }

    public int obtenerHorasExtra(NodoControlPersonal w) {
        int horaSalidaCv = FramePrincipal.cp.getV().getHoraSalida();
        int minutosSalidaCv = FramePrincipal.cp.getV().getMinutoSalida();
        int horaSalida = w.getCp().getHoraSalida();
        int minutoSalida = w.getCp().getMinutoSalida();
        int horaExtra = (horaSalida * 60 + minutoSalida) - (horaSalidaCv * 60 + minutosSalidaCv);
        if (horaExtra > 0) {
            horaExtra = horaExtra / 60;
        } else {
            horaExtra = 0;
        }
        return horaExtra;
    }

    public void crearComboBox() {
        NodoPersonal aux = FramePrincipal.cp.getLp().getP();
        while (aux != null) {
            this.jComboBox1.addItem(aux.getPersonal().getNombre());
            aux = aux.getSig();
        }
        /*this.jComboBox1.addItem("hola");
        this.jComboBox1.addItem("2");*/
    }
    private Productos p = new Productos();
    private ListaPedidosMes lpm;
    private ListaStockDatos lsd;

    public void totalCompra() {
        int filas = this.jTable2.getRowCount();
        double total = 0;
        for (int i = 0; i < filas; i++) {
            total += Double.valueOf(this.jTable2.getValueAt(i, 8).toString());
        }
        jTextField1.setText(total + "");
    }

    public void llenarTabla2() {
        reiniciarTabla2();
        Queue<StockDatos> aux = new LinkedList();
        aux.addAll(this.lsd.getP());
        int cont = 0;
        while (!aux.isEmpty()) {
            generarTabla2();
            StockDatos p = aux.remove();
            this.jTable2.setValueAt(p.getNombreProducto(), cont, 0);
            this.jTable2.setValueAt(p.getProveedor(), cont, 1);
            this.jTable2.setValueAt(p.getCantidadCajas(), cont, 2);
            this.jTable2.setValueAt(p.getCantidadUnidades(), cont, 3);
            this.jTable2.setValueAt(p.getCantidadTotal(), cont, 4);
            this.jTable2.setValueAt(p.getPreciosCaja(), cont, 5);
            this.jTable2.setValueAt(p.getPrecioBruto(), cont, 6);
            this.jTable2.setValueAt(p.getPrecioUnitario(), cont, 7);
            this.jTable2.setValueAt(p.getPrecioTotal(), cont, 8);
            this.jTable2.setValueAt(p.getFecha(), cont, 9);
            this.jTable2.setValueAt(p.getStockCajas(), cont, 10);
            this.jTable2.setValueAt(p.getStockUnidades(), cont, 11);
            this.jTable2.setValueAt(p.getStockVolumen(), cont, 12);
            this.jTable2.setValueAt(p.getStockVolTotal(), cont, 13);
            this.jTable2.setValueAt(p.getStockTotalCigarro(), cont, 14);
            this.jTable2.setValueAt(p.getStockCajasCigarro(), cont, 15);
            this.jTable2.setValueAt(p.getStockUnidadesCigarros(), cont, 16);
            this.jTable2.setValueAt(p.getFactura(), cont, 17);
            cont++;
        }
    }

    public void generarTabla2() {
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        model.addRow(new Object[]{""});
    }

    public void reiniciarTabla2() {
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "            ", "       ", "Cajas", "+unidades", "Total uds.", "Por Caja", "En bruto/ud", "De venta unitario", "Total compra", "   ", "Cajas", "+unidades", "Volumen", "Volumen total", "Total Cigarros", "Cajas", "+unidades", "    "
                }
        ));
    }

    public void llenarTabla1() {
        reiniciarTabla1();
        Queue<ListaPedidos> pm = new LinkedList();
        DecimalFormat df = new DecimalFormat("#.00");
        pm.addAll(lpm.getLp());
        int i = 0;
        while (!pm.isEmpty()) {
            ListaPedidos lp = pm.remove();
            generarTabla1();
            jTable1.setValueAt(lp.getFecha(), i, 0);
            jTable1.setValueAt(lp.getActivo(), i, 1);
            jTable1.setValueAt(vendido(lp.getFecha()), i, 2);
            jTable1.setValueAt(totalVentasDia(lp.getFecha()), i, 3);
            jTable1.setValueAt(df.format(-vendido(lp.getFecha()) + totalVentasDia(lp.getFecha())), i, 4);
            i++;
        }
    }

    public double totalVentasDia(String fecha) {
        double total = 0;
        Queue<ListaPedidos> pm = new LinkedList();
        pm.addAll(lpm.getLp());
        while (!pm.isEmpty()) {
            ListaPedidos lp = pm.remove();
            if (lp.getFecha().equals(fecha)) {
                Queue<Pedidos> aux = new LinkedList();
                aux.addAll(lp.getP());
                while (!aux.isEmpty()) {
                    Pedidos p = aux.remove();
                    total += p.getPrecioParcial();
                }
            }
        }
        DecimalFormat df = new DecimalFormat("#.00");
        return Double.valueOf(df.format(total));
    }

    /*public double vendido() {
        Stack<Cerveza> cer = new Stack<Cerveza>();
        Stack<Licores> lic = new Stack<Licores>();
        Stack<Refrescos> ref = new Stack<Refrescos>();
        Stack<Cigarrillos> cig = new Stack<Cigarrillos>();
        cer.addAll(this.p.getcCerveza());
        lic.addAll(this.p.getcLicores());
        ref.addAll(this.p.getcRefrescos());
        cig.addAll(this.p.getcCigarrilos());
        double sa1 = 0;
        double sa2 = 0;
        double sa3 = 0;
        double sa4 = 0;
        while (!cer.isEmpty()) {
            Cerveza c = cer.pop();
            sa1 = +c.getStockVendido().getStockVolTotal() * c.getUnitarioCc();
        }
        while (!lic.isEmpty()) {
            Licores l = lic.pop();
            sa2 += l.getStockVendido().getStockVolTotal() * l.getUnitarioCc();
        }
        while (!ref.isEmpty()) {
            Refrescos r = ref.pop();
            sa3 += r.getStockVendido().getStockVolTotal() * r.getUnitarioCc();
        }
        while (!cig.isEmpty()) {
            Cigarrillos c = cig.pop();
            sa4 += c.getStockVendido().getStockTotalCigarro() * c.getBrutoUnidad();
        }
        DecimalFormat df = new DecimalFormat("#.00");
        double total = sa1 + sa2 + sa3 + sa4;
        return Double.valueOf(df.format(total));
    }*/
    public double activo() {
        Stack<Cerveza> cer = new Stack<Cerveza>();
        Stack<Licores> lic = new Stack<Licores>();
        Stack<Refrescos> ref = new Stack<Refrescos>();
        Stack<Cigarrillos> cig = new Stack<Cigarrillos>();
        cer.addAll(this.p.getcCerveza());
        lic.addAll(this.p.getcLicores());
        ref.addAll(this.p.getcRefrescos());
        cig.addAll(this.p.getcCigarrilos());
        double sa1 = 0;
        double sa2 = 0;
        double sa3 = 0;
        double sa4 = 0;
        while (!cer.isEmpty()) {
            Cerveza c = cer.pop();
            sa1 = +c.getStockAlmacen().getStockVolTotal() * c.getUnitarioCc();
        }
        while (!lic.isEmpty()) {
            Licores l = lic.pop();
            sa2 += l.getStockAlmacen().getStockVolTotal() * l.getUnitarioCc();
        }
        while (!ref.isEmpty()) {
            Refrescos r = ref.pop();
            sa3 += r.getStockAlmacen().getStockVolTotal() * r.getUnitarioCc();
        }
        while (!cig.isEmpty()) {
            Cigarrillos c = cig.pop();
            sa4 += c.getStockAlmacen().getStockTotalCigarro() * c.getBrutoUnidad();
        }
        DecimalFormat df = new DecimalFormat("#.00");
        double total = sa1 + sa2 + sa3 + sa4;
        return Double.valueOf(df.format(total));
    }

    public void ocultarBotones() {
        jButton3.setVisible(false);
        jButton4.setVisible(false);
        jPanel2.setVisible(false);
        jButton5.setVisible(false);
        jButton1.setVisible(false);
    }

    public double vendido(String fecha) {
        Queue<ListaPedidos> aux = new LinkedList();
        aux.addAll(this.lpm.getLp());
        double activoTotal = 0;
        while (!aux.isEmpty()) {
            ListaPedidos lp = aux.remove();
            if (lp.getFecha().equals(fecha)) {
                Queue<Pedidos> p = new LinkedList();
                p.addAll(lp.getP());
                while (!p.isEmpty()) {
                    Pedidos ped = p.remove();
                    activoTotal += obtenerBruto(ped.getProducto()) * ped.getCantidad();
                }
            }
        }
        DecimalFormat df = new DecimalFormat("#.00");
        return Double.valueOf(df.format(activoTotal));
    }

    public double obtenerBruto(String producto) {
        Stack<Cerveza> cer = new Stack<Cerveza>();
        Stack<Licores> lic = new Stack<Licores>();
        Stack<Refrescos> ref = new Stack<Refrescos>();
        Stack<Cigarrillos> cig = new Stack<Cigarrillos>();
        cer.addAll(this.p.getcCerveza());
        lic.addAll(this.p.getcLicores());
        ref.addAll(this.p.getcRefrescos());
        cig.addAll(this.p.getcCigarrilos());
        while (!cer.isEmpty()) {
            Cerveza c = cer.pop();
            if (producto.equals(c.getNomProducto())) {
                return c.getBrutoUnidad();
            }
        }
        while (!lic.isEmpty()) {
            Licores l = lic.pop();
            if (producto.equals(l.getNomProducto())) {
                return l.getBrutoUnidad();
            }
        }
        while (!ref.isEmpty()) {
            Refrescos r = ref.pop();
            if (producto.equals(r.getNomProducto())) {
                return r.getBrutoUnidad();
            }
        }
        while (!cig.isEmpty()) {
            Cigarrillos c = cig.pop();
            if (producto.equals(c.getNomProducto())) {
                return c.getBrutoUnidad();
            }
        }
        return 0;
    }

    public void generarTabla1() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.addRow(new Object[]{""});
    }

    public void reiniciarTabla1() {
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "FECHA", "Activos", "Vendido", "Total Ventas/Dia", "Utilidad/Dia"
                }
        ));
    }

    public double sueldos() {
        double sueldo = 0;
        NodoPersonal np = FramePrincipal.cp.getLp().getP();
        while (np != null) {
            sueldo += np.getPersonal().getSueldoReal();
            np = np.getSig();
        }
        return sueldo;
    }

    public String fecha2() {
        String f = "";
        int dia, mes, anio;
        Calendar calendario = Calendar.getInstance();
        dia = calendario.get(Calendar.DAY_OF_MONTH);
        mes = calendario.get(Calendar.MONTH) + 1;
        anio = calendario.get(Calendar.YEAR);
        f = dia + "_" + mes + "_" + anio;
        return f;
    }

    public String fecha() {
        String f = "";
        int dia, mes, anio;
        Calendar calendario = Calendar.getInstance();
        dia = calendario.get(Calendar.DAY_OF_MONTH);
        mes = calendario.get(Calendar.MONTH) + 1;
        anio = calendario.get(Calendar.YEAR);
        f = dia + "/" + mes + "/" + anio;
        return f;
    }

    public void llenarTabla3() {
        int filas = this.jTable1.getRowCount();
        double ventas = 0;
        double utilidad = 0;
        for (int i = 0; i < filas; i++) {
            ventas += Double.valueOf(this.jTable1.getValueAt(i, 3).toString());
            utilidad += Double.valueOf(this.jTable1.getValueAt(i, 4).toString());
        }

        this.jTable3.setValueAt(fecha(), 0, 0);
        this.jTable3.setValueAt(ventas, 0, 1);
        this.jTable3.setValueAt(utilidad, 0, 2);
        this.jTable3.setValueAt(this.jTextField1.getText(), 0, 3);
        this.jTable3.setValueAt(sueldos(), 0, 4);
        this.jTable3.setValueAt(activo(), 0, 5);
        DecimalFormat df = new DecimalFormat("#.00");
        this.jTable3.setValueAt(df.format(ventas - utilidad - sueldos()), 0, 6);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton6 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("GASTOS");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(69, 11, 168, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Suma de total gastos");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 250, 185, -1));
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 270, 115, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("BALANCE POR DIA");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, -1, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "FECHA", "Activos", "Vendido", "Total Ventas/Dia","Utilidad/Dia"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, 610, 280));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("BALANCE POR MES");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 340, 160, -1));

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null,null,null,null}
            },
            new String [] {
                "FECHA", "Total Ventas", "Suma Utilidad", "Total gastos","sueldo","Capital activo","Utilidad Neta"
            }
        ));
        jScrollPane3.setViewportView(jTable3);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 360, 670, 80));

        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable2.setAutoCreateRowSorter(true);
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "            ", "       ", "Cajas", "+unidades","Total uds.","Por Caja","En bruto/ud","De venta unitario","Total compra","   ","Cajas","+unidades","Volumen","Volumen total","Total Cigarros","Cajas","+unidades","    "
            }
        ));
        jTable2.setRowHeight(32);
        jTable2.setRowMargin(2);
        jScrollPane5.setViewportView(jTable2);

        jPanel5.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 1280, 130));

        jLabel21.setText("Producto");
        jLabel21.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jPanel5.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 70, 35));

        jLabel22.setText("Proveedor");
        jLabel22.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jPanel5.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, 70, 35));

        jLabel23.setText("Cantidad");
        jLabel23.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jPanel5.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 0, 210, 35));

        jLabel24.setText("Precios");
        jLabel24.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jPanel5.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 0, 280, 35));

        jLabel25.setText("Fecha de compra");
        jLabel25.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jPanel5.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 0, 70, 35));

        jLabel26.setText("Stock");
        jLabel26.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jPanel5.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 0, 480, 35));

        jLabel27.setText("Factura");
        jLabel27.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jPanel5.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 0, 97, 35));

        jScrollPane4.setViewportView(jPanel5);

        getContentPane().add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 1300, 180));

        jButton1.setText("ATRAS");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 10, -1, -1));

        jButton2.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jButton2.setText("GRAFICAS DE PROYECCION");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 450, 270, 80));

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jComboBox1.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {  }));
        jPanel2.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 210, 40));

        jButton6.setFont(new java.awt.Font("Bookman Old Style", 1, 16)); // NOI18N
        jButton6.setText("Registros Anteriores");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 210, 60));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("REGISTROS DE EMPLEADOS");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 180, 30));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 460, 210, 160));

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jButton3.setText("guardar a excel Personal");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 540, 240, 50));

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton4.setText("RESET MES");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 460, -1, 100));

        jButton5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton5.setText("REGISTROS ANTERIORES");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 600, 240, 40));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {

            FrameGestion a = new FrameGestion();
            a.show(true);
            this.dispose();
        } catch (IOException ex) {
            Logger.getLogger(FramePersonal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FramePersonal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int filas = jTable1.getRowCount();
        String fechas[] = new String[filas];
        double ventas[] = new double[filas];
        for (int i = 0; i < filas; i++) {
            fechas[i] = jTable1.getValueAt(i, 0).toString();
            ventas[i] = Double.valueOf(jTable1.getValueAt(i, 3).toString());

        }
        //******
        DefaultPieDataset data = new DefaultPieDataset();
        for (int i = 0; i < filas; i++) {
            data.setValue(fechas[i], ventas[i]);
        }

        // Creando el Grafico
        JFreeChart chart = ChartFactory.createPieChart(
                "TOTAL VENTAS POR DIA",
                data,
                true,
                true,
                false);

        // Mostrar Grafico
        ChartFrame frame = new ChartFrame("Grafica mensual", chart);
        frame.pack();
        frame.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        FrameImportar a = new FrameImportar(".\\GestionesDelPersonal\\");
        a.setSw(2);
        a.llenarTabla(this.jComboBox1.getSelectedItem().toString());
        a.show(true);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        NodoPersonal w = FramePrincipal.cp.getLp().getP();
        while (w != null) {
            try {
                guardarAExcelPersonal(w);
            } catch (IOException ex) {
                Logger.getLogger(FrameGesstion.class.getName()).log(Level.SEVERE, null, ex);
            } catch (WriteException ex) {
                Logger.getLogger(FrameGesstion.class.getName()).log(Level.SEVERE, null, ex);
            }
            w = w.getSig();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        int i = JOptionPane.showConfirmDialog(null, "Esto eliminara todos los datos, incluyendo las facturas, asegurese de haber guardado en excel las facturas \nSe guardara una copia de seguridad de esta pantalla, se podra ver en REGISTROS ANTERIORES con el nombre de la fecha actual", "DESEA REINICIAR MES?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (i == 0) {
            guardarMesResgitro();
            reiniciarMes();
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    public void reiniciarMes() {
        this.lsd = new ListaStockDatos();
        lsd.guardar(lsd);
        this.lpm = new ListaPedidosMes();
        lpm.guardar(lpm);
        ListaFacturaCliente lfc = new ListaFacturaCliente();
        lfc.guardar(lfc);
        eliminarControlPersonal();
    }

    public void eliminarControlPersonal() {
        NodoPersonal aux = FramePrincipal.cp.getLp().getP();
        while (aux != null) {
            aux.getPersonal().getControl().setP(null);
            aux = aux.getSig();
        }
        FramePrincipal.cp.guardar();
    }

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        FrameImportar fi = new FrameImportar(".\\registrosMensuales\\");
        fi.setSw(6);
        fi.show(true);

    }//GEN-LAST:event_jButton5ActionPerformed

    public void guardarMesResgitro() {
        int filas = this.jTable2.getRowCount();
        int columnas = this.jTable2.getColumnCount();
        String[][] gastos = new String[filas][columnas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                gastos[i][j] = this.jTable2.getValueAt(i, j).toString();
            }
        }
        filas = this.jTable1.getRowCount();
        columnas = this.jTable1.getColumnCount();
        String[][] balDia = new String[filas][columnas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                balDia[i][j] = this.jTable1.getValueAt(i, j).toString();
            }
        }
        columnas = this.jTable3.getColumnCount();
        String[] balMes = new String[columnas];
        for (int i = 0; i < columnas; i++) {
            balMes[i] = this.jTable3.getValueAt(0, i).toString();
        }
        ma.setBalDia(balDia);
        ma.setBalMes(balMes);
        ma.setGastos(gastos);
        ma.guardar(ma, ".\\registrosMensuales\\" + fecha2() + ".dat");
    }

    public void mostrarContenido(String path) throws IOException, ClassNotFoundException {
        //reiniciarMes();
        reiniciarTabla1();
        reiniciarTabla2();
        ma = ma.recuperar(path);
        int filas = ma.getGastos().length;
        System.out.println(filas);
        for (int i = 0; i < filas; i++) {
            generarTabla2();
            for (int j = 0; j < ma.getGastos()[i].length; j++) {
                System.out.println(ma.getGastos()[i][j]);
                this.jTable2.setValueAt(ma.getGastos()[i][j], i, j);
            }
        }
        filas = ma.getBalDia().length;
        for (int i = 0; i < filas; i++) {
            generarTabla1();
            for (int j = 0; j < ma.getBalDia()[i].length; j++) {
                this.jTable1.setValueAt(ma.getBalDia()[i][j], i, j);
            }
        }
        int columnas = ma.getBalMes().length;
        for (int i = 0; i < columnas; i++) {
            this.jTable3.setValueAt(ma.getBalMes()[i], 0, i);
        }
        totalCompra();
    }

    private MensualAnteriores ma = new MensualAnteriores();

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
            java.util.logging.Logger.getLogger(FrameGestionMensual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameGestionMensual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameGestionMensual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameGestionMensual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new FrameGestionMensual().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(FrameGestionMensual.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(FrameGestionMensual.class.getName()).log(Level.SEVERE, null, ex);
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
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
