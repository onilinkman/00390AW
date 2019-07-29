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
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
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

/**
 *
 * @author WINDOWS 10
 */
public class FrameGestion extends javax.swing.JFrame {

    /**
     * Creates new form FrameGestion
     */
    public FrameGestion() throws IOException, ClassNotFoundException {
        initComponents();
        this.jProgressBar1.setMaximum(10000);
        this.jProgressBar2.setMaximum(10000);
        this.jProgressBar3.setMaximum(10000);
        this.jProgressBar4.setMaximum(50);
        this.lpm = new ListaPedidosMes();
        File f4 = new File("ListaPedidosMes.dat");
        if (f4.exists()) {
            this.lpm = lpm.recuperar();

        }
        this.fecha = fecha();
        llenarComboBox1();
        llenarTabla4();
        File f = new File("datosProductos.dat");
        if (f.exists()) {
            this.p = this.p.recuperar();

            //agregarProducto();
            //llenarTabla1();
        }
        detUnidades();
        try {
            llenarTabla5();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        llenarTabla1();
        File archivo = new File(".\\ProductosVendidos\\");
        archivo.mkdirs();
        llenarActivoAFechaActual(fecha());
    }

    public void llenarActivoAFechaActual(String fecha) {
        Queue<ListaPedidos> aux = new LinkedList();
        while (!this.lpm.getLp().isEmpty()) {
            ListaPedidos auxLp = this.lpm.getLp().remove();
            if (auxLp.getFecha().equals(fecha)) {
                auxLp.setActivo(activo());
            }
            aux.add(auxLp);
        }
        this.lpm.getLp().addAll(aux);
        this.lpm.guardar(lpm);
    }

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

    public void llenarTabla5() {
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
            //sa1=+c.getStockVendido().getStockVolTotal()*c.getUnitarioCc();//
            sa1 += vendido(this.jComboBox1.getSelectedItem().toString(), c.getNomProducto());
        }
        while (!lic.isEmpty()) {
            Licores l = lic.pop();
            //sa2+=l.getStockVendido().getStockVolTotal()*l.getUnitarioCc();
            sa2 += vendido(this.jComboBox1.getSelectedItem().toString(), l.getNomProducto());
        }
        while (!ref.isEmpty()) {
            Refrescos r = ref.pop();
            //sa3+=r.getStockVendido().getStockVolTotal()*r.getUnitarioCc();
            sa3 += vendido(this.jComboBox1.getSelectedItem().toString(), r.getNomProducto());
        }
        while (!cig.isEmpty()) {
            Cigarrillos c = cig.pop();
            //sa4+=c.getStockVendido().getStockTotalCigarro()*c.getBrutoUnidad();
            sa4 += vendido(this.jComboBox1.getSelectedItem().toString(), c.getNomProducto());
        }
        DecimalFormat df = new DecimalFormat("#.00");
        this.jTable5.setValueAt(df.format(sa1), 0, 2);
        this.jTable5.setValueAt(df.format(sa2), 1, 2);
        this.jTable5.setValueAt(df.format(sa3), 2, 2);
        this.jTable5.setValueAt(df.format(sa4), 3, 2);
        sumaActivos();
        balanceDiario();
    }

    public double vendido(String fecha, String producto) {
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
                    if (producto.equals(ped.getProducto())) {
                        activoTotal += obtenerBruto(ped.getProducto()) * ped.getCantidad();
                    }
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

    public void balanceDiario() {
        int filas = this.jTable4.getRowCount();
        DecimalFormat df = new DecimalFormat("#.00");
        double total = 0;
        for (int i = 0; i < filas; i++) {
            total += Double.valueOf(this.jTable4.getValueAt(i, 1).toString());
        }
        jTextField3.setText(df.format(total) + "");
        pantTotal.setText(df.format(total) + "");
        double activo = 0;
        double vendido = 0;
        for (int i = 0; i < 4; i++) {
            activo += Double.valueOf(jTable5.getValueAt(i, 1).toString());
            vendido += Double.valueOf(jTable5.getValueAt(i, 2).toString());
        }
        pantActivo.setText(df.format(activo) + "");
        pantVendido.setText(df.format(vendido) + "");
        double utilidad = -vendido + total;
        this.pantUtilidad.setText(df.format(utilidad) + "");
    }

    public void sumaActivos() {
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
        this.jTable5.setValueAt(df.format(sa1), 0, 1);
        this.jTable5.setValueAt(df.format(sa2), 1, 1);
        this.jTable5.setValueAt(df.format(sa3), 2, 1);
        this.jTable5.setValueAt(df.format(sa4), 3, 1);
    }

    public void productoMasVendido(String fecha) {
        Queue<String> cola = new LinkedList();
        Stack<Cerveza> auxCer = new Stack<Cerveza>();
        auxCer.addAll(this.p.getcCerveza());
        while (!auxCer.isEmpty()) {
            cola.add(auxCer.pop().getNomProducto());
        }
        Stack<Licores> auxLic = new Stack<Licores>();
        auxLic.addAll(this.p.getcLicores());
        while (!auxLic.isEmpty()) {
            cola.add(auxLic.pop().getNomProducto());
        }
        Stack<Refrescos> auxRef = new Stack<Refrescos>();
        auxRef.addAll(this.p.getcRefrescos());
        while (!auxRef.isEmpty()) {
            cola.add(auxRef.pop().getNomProducto());
        }
        Stack<Cocteles> auxCoc = new Stack<Cocteles>();
        auxCoc.addAll(this.p.getcCocteles());
        while (!auxCoc.isEmpty()) {
            cola.add(auxCoc.pop().getNomProducto());
        }
        Stack<Cigarrillos> auxCig = new Stack<Cigarrillos>();
        auxCig.addAll(this.p.getcCigarrilos());
        while (!auxCig.isEmpty()) {
            cola.add(auxCig.pop().getNomProducto());
        }
        int tam = cola.size();
        String[][] cad = new String[2][tam];
        int i = 0;
        while (!cola.isEmpty()) {
            String nomProducto = cola.remove();
            //System.out.print(nomProducto+" ");
            Queue<ListaPedidos> auxPedidos = new LinkedList();
            auxPedidos.addAll(this.lpm.getLp());
            cad[0][i] = nomProducto;
            while (!auxPedidos.isEmpty()) {
                ListaPedidos listaPedidos = auxPedidos.remove();
                if (listaPedidos.getFecha().equals(fecha)) {
                    Queue<Pedidos> aux = new LinkedList();
                    aux.addAll(listaPedidos.getP());
                    int cantidad = 0;
                    while (!aux.isEmpty()) {
                        Pedidos p = aux.remove();
                        if (nomProducto.equals(p.getProducto())) {
                            cantidad = cantidad + p.getCantidad();

                        }
                    }
                    //System.out.println(cantidad);
                    cad[1][i] = cantidad + "";
                }
            }
            i++;
        }
        for (int j = 0; j < tam; j++) {

            System.out.println(cad[0][j]);
            System.out.println(cad[1][j]);
        }
        for (int j = 0; j < tam - 1; j++) {
            for (int k = j + 1; k < tam; k++) {
                if (Integer.valueOf(cad[1][j]) < Integer.valueOf(cad[1][k])) {
                    int aux = Integer.valueOf(cad[1][j]);
                    String a = cad[0][j];

                    cad[1][j] = cad[1][k];
                    cad[0][j] = cad[0][k];

                    cad[1][k] = aux + "";
                    cad[0][k] = a;
                }

            }
        }
        reiniciarTabla3();
        for (int j = 0; j < tam; j++) {
            agregarFilaTabla3();
            this.jTable3.setValueAt(cad[0][j], j, 0);
            this.jTable3.setValueAt(cad[1][j], j, 1);
        }
    }

    public void agregarFilaTabla3() {
        DefaultTableModel model = (DefaultTableModel) jTable3.getModel();
        model.addRow(new Object[]{});
    }

    public void reiniciarTabla3() {
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "PRODUCTO", "CANTIDAD"
                }
        ));
    }

    public void detUnidades() {
        Stack<Cerveza> c1 = new Stack<Cerveza>();
        int cont1 = 0;
        c1.addAll(this.p.getcCerveza());
        while (!c1.isEmpty()) {
            cont1 = (int) (cont1 + c1.pop().getStockAlmacen().getStockVolTotal());
        }
        this.jProgressBar1.setValue(cont1);

        Stack<Licores> c2 = new Stack<Licores>();
        int cont2 = 0;
        c2.addAll(this.p.getcLicores());
        while (!c2.isEmpty()) {
            cont2 = (int) (cont2 + c2.pop().getStockAlmacen().getStockVolTotal());
        }
        this.jProgressBar2.setValue(cont2);

        Stack<Refrescos> c3 = new Stack<Refrescos>();
        int cont3 = 0;
        c3.addAll(this.p.getcRefrescos());
        while (!c3.isEmpty()) {
            cont3 = (int) (cont3 + c3.pop().getStockAlmacen().getStockVolTotal());
        }
        this.jProgressBar3.setValue(cont3);

        Stack<Cigarrillos> c4 = new Stack<Cigarrillos>();
        int cont4 = 0;
        c4.addAll(this.p.getcCigarrilos());
        while (!c4.isEmpty()) {
            cont4 = cont4 + c4.pop().getStockAlmacen().getStockTotalCigarro();
        }
        this.jProgressBar4.setValue(cont4);
    }

    //*************************************
    public void agregarFilaTabla2() {
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        model.addRow(new Object[]{});
    }

    public void reiniciarTabla2ParaCLR() {
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Lista de productos", "Capital activo(bs)", "Capital vendido(bs)", "Vendido cajas", "Vendido +uds", "Vendido Vol.[cc]", "Stock Cajas", "Stock +uds.", "Stock Vol[cc]"
                }
        ));
    }

    public void reiniciarTabla2ParaCigarrillo() {
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Lista de productos", "Capital activo(bs)", "Capital vendido(bs)", "Vendido cajas", "Vendido +uds", "Stock Cajas", "Stock +uds."
                }
        ));
    }

    public void reiniciarTabla2ParaCocteles() {
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Lista de productos", "CANTIDAD", "PRECIO UNITARIO", "PRECIO TOTAL"
                }
        ));
    }

    public void llenarTabla2() {
        if (tipo.equals("Cerveza")) {
            reiniciarTabla2ParaCLR();
            Queue<Cerveza> c = new LinkedList();
            c.addAll(this.p.getcCerveza());
            int i = 0;
            while (!c.isEmpty()) {
                agregarFilaTabla2();
                Cerveza cerveza = c.remove();
                this.jTable2.setValueAt(cerveza.getNomProducto(), i, 0);
                this.jTable2.setValueAt(cerveza.getUnitarioCc() * cerveza.getStockAlmacen().getStockVolTotal(), i, 1);
                this.jTable2.setValueAt(cerveza.getStockVendido().getStockVolTotal() * cerveza.getUnitarioCc(), i, 2);
                this.jTable2.setValueAt(cerveza.getStockVendido().getStockCajas(), i, 3);
                this.jTable2.setValueAt(cerveza.getStockVendido().getStockUnidades(), i, 4);
                this.jTable2.setValueAt(cerveza.getStockVendido().getStockVolumen(), i, 5);
                this.jTable2.setValueAt(cerveza.getStockAlmacen().getStockCajas(), i, 6);
                this.jTable2.setValueAt(cerveza.getStockAlmacen().getStockUnidades(), i, 7);
                this.jTable2.setValueAt(cerveza.getStockAlmacen().getStockVolumen(), i, 8);
                i++;
            }
        } else if (tipo.equals("Licores")) {
            reiniciarTabla2ParaCLR();
            Queue<Licores> c = new LinkedList();
            c.addAll(this.p.getcLicores());
            int i = 0;
            while (!c.isEmpty()) {
                agregarFilaTabla2();
                Licores lic = c.remove();
                this.jTable2.setValueAt(lic.getNomProducto(), i, 0);
                this.jTable2.setValueAt(lic.getUnitarioCc() * lic.getStockAlmacen().getStockVolTotal(), i, 1);
                this.jTable2.setValueAt(lic.getStockVendido().getStockVolTotal() * lic.getUnitarioCc(), i, 2);
                this.jTable2.setValueAt(lic.getStockVendido().getStockCajas(), i, 3);
                this.jTable2.setValueAt(lic.getStockVendido().getStockUnidades(), i, 4);
                this.jTable2.setValueAt(lic.getStockVendido().getStockVolumen(), i, 5);
                this.jTable2.setValueAt(lic.getStockAlmacen().getStockCajas(), i, 6);
                this.jTable2.setValueAt(lic.getStockAlmacen().getStockUnidades(), i, 7);
                this.jTable2.setValueAt(lic.getStockAlmacen().getStockVolumen(), i, 8);
                i++;
            }
        } else if (tipo.equals("Refrescos")) {
            reiniciarTabla2ParaCLR();
            Queue<Refrescos> c = new LinkedList();
            c.addAll(this.p.getcRefrescos());
            int i = 0;
            while (!c.isEmpty()) {
                agregarFilaTabla2();
                Refrescos lic = c.remove();
                this.jTable2.setValueAt(lic.getNomProducto(), i, 0);
                this.jTable2.setValueAt(lic.getUnitarioCc() * lic.getStockAlmacen().getStockVolTotal(), i, 1);
                this.jTable2.setValueAt(lic.getStockVendido().getStockVolTotal() * lic.getUnitarioCc(), i, 2);
                this.jTable2.setValueAt(lic.getStockVendido().getStockCajas(), i, 3);
                this.jTable2.setValueAt(lic.getStockVendido().getStockUnidades(), i, 4);
                this.jTable2.setValueAt(lic.getStockVendido().getStockVolumen(), i, 5);
                this.jTable2.setValueAt(lic.getStockAlmacen().getStockCajas(), i, 6);
                this.jTable2.setValueAt(lic.getStockAlmacen().getStockUnidades(), i, 7);
                this.jTable2.setValueAt(lic.getStockAlmacen().getStockVolumen(), i, 8);
                i++;
            }
        } else if (tipo.equals("Cigarrillos")) {
            reiniciarTabla2ParaCigarrillo();
            Queue<Cigarrillos> c = new LinkedList();
            c.addAll(this.p.getcCigarrilos());
            int i = 0;
            while (!c.isEmpty()) {
                agregarFilaTabla2();
                Cigarrillos lic = c.remove();
                this.jTable2.setValueAt(lic.getNomProducto(), i, 0);
                this.jTable2.setValueAt(lic.getBrutoUnidad() * lic.getStockAlmacen().getStockTotalCigarro(), i, 1);
                this.jTable2.setValueAt(lic.getStockVendido().getStockTotalCigarro() * lic.getBrutoUnidad(), i, 2);
                this.jTable2.setValueAt(lic.getStockVendido().getStockCajasCigarro(), i, 3);
                this.jTable2.setValueAt(lic.getStockVendido().getStockUnidadesCigarros(), i, 4);
                this.jTable2.setValueAt(lic.getStockAlmacen().getStockCajasCigarro(), i, 5);
                this.jTable2.setValueAt(lic.getStockAlmacen().getStockUnidadesCigarros(), i, 6);
                i++;
            }
        } else if (tipo.equals("Cocteles")) {
            reiniciarTabla2ParaCocteles();
            Queue<Cocteles> c = new LinkedList();
            c.addAll(this.p.getcCocteles());
            int i = 0;
            while (!c.isEmpty()) {
                agregarFilaTabla2();
                Cocteles coc = c.remove();
                int cantidad = 0;
                double precioUnitario = 0;
                double precioTotal = 0;
                Queue<ListaPedidos> listPed = new LinkedList();
                listPed.addAll(this.lpm.getLp());
                while (!listPed.isEmpty()) {
                    ListaPedidos l1 = listPed.remove();
                    Queue<Pedidos> aux = new LinkedList();
                    aux.addAll(l1.getP());
                    while (!aux.isEmpty()) {
                        Pedidos ped = aux.remove();
                        if (coc.getNomProducto().equals(ped.getProducto())) {
                            cantidad += ped.getCantidad();
                            precioUnitario += ped.getPrecioUnitario();
                            precioTotal += ped.getPrecioParcial();
                        }
                    }
                }
                this.jTable2.setValueAt(coc.getNomProducto(), i, 0);
                this.jTable2.setValueAt(cantidad, i, 1);
                this.jTable2.setValueAt(precioUnitario, i, 2);
                this.jTable2.setValueAt(precioTotal, i, 3);
                i++;
            }
        }
    }

    public void sumarActivos() {
        double sum = 0;
        int filas = this.jTable2.getRowCount();
        for (int i = 0; i < filas; i++) {
            sum = sum + Double.valueOf(this.jTable2.getValueAt(i, 1).toString());

        }
        this.jTextField1.setText(sum + "");
    }

    public void sumarCapitalVendido() {
        double sum = 0;
        int filas = this.jTable2.getRowCount();
        for (int i = 0; i < filas; i++) {
            sum = sum + Double.valueOf(this.jTable2.getValueAt(i, 2).toString());

        }
        this.jTextField2.setText(sum + "");
    }

    private String tipo = "";
    public String fecha;
    private Productos p = new Productos();

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

    //*******LENAR COMBO BOX CON FECHA
    public void llenarComboBox1() {
        Queue<ListaPedidos> qlp = new LinkedList();
        qlp.addAll(this.lpm.getLp());
        agregarFilaATabla4();
        while (!qlp.isEmpty()) {

            ListaPedidos lp = qlp.remove();
            this.jComboBox1.addItem(lp.getFecha());

        }
    }

    //**************PRODUCTOS VENDIDOS******************
    public void llenarTabla3() {

        double suma = 0;
        reiniciarTabla3();
        int fila = 0;
        Queue<ListaPedidos> qlp = new LinkedList();
        qlp.addAll(this.lpm.getLp());
        agregarFilaATabla4();
        while (!qlp.isEmpty()) {

            Queue<Pedidos> aux2 = new LinkedList();

            ListaPedidos lp = qlp.remove();
            if (fecha.equals(lp.getFecha())) {
                aux2.addAll(lp.getP());
                while (!aux2.isEmpty()) {
                    Pedidos p = aux2.remove();
                    System.out.println(p.getPrecioParcial());
                    //if (p.getProducto().equalsIgnoreCase(nombreGarzon)) {
                    suma = suma + p.getPrecioParcial();
                    //}
                }
            }
        }
        //this.jTable4.setValueAt(nombreGarzon, fila, 0);
        //this.jTable4.setValueAt(sumaTotalVendido, fila, 1);
        suma = 0;
        fila++;
    }

    public String[] productos() {
        Map<String, Integer> mapa = new HashMap<String, Integer>();
        Queue<ListaPedidos> qlp = new LinkedList();
        qlp.addAll(this.lpm.getLp());
        //agregarFilaATabla4();
        //int i=0;
        int j = 0;
        while (!qlp.isEmpty()) {

            Queue<Pedidos> aux2 = new LinkedList();

            ListaPedidos lp = qlp.remove();
            if (fecha.equals(lp.getFecha())) {
                aux2.addAll(lp.getP());
                while (!aux2.isEmpty()) {
                    Pedidos p = aux2.remove();
                    mapa.put(p.getProducto(), j);
                    //          i++;
                    j++;
                }
            }
        }
        System.out.println(mapa + "tam: " + mapa.size());
        String[] s = new String[mapa.size()];
        for (int i = 0; i < mapa.size(); i++) {
            //s[i] = mapa.;
        }
        return s;
    }

    //************** VENTA POR GARZON ************************
    public void llenarTabla4() {
        reiniciarTabla4();
        NodoPersonal aux = FramePrincipal.cp.getLp().getP();
        double sumaTotalVendido = 0;
        while (aux != null) {
            if ("Garson".equalsIgnoreCase(aux.getPersonal().getNumMaquina())) {
                String nombreGarzon = aux.getPersonal().getNombre();
                int fila = 0;
                Queue<ListaPedidos> qlp = new LinkedList();
                qlp.addAll(this.lpm.getLp());
                agregarFilaATabla4();
                while (!qlp.isEmpty()) {

                    Queue<Pedidos> aux2 = new LinkedList();

                    ListaPedidos lp = qlp.remove();
                    if (fecha.equals(lp.getFecha())) {
                        aux2.addAll(lp.getP());
                        while (!aux2.isEmpty()) {
                            Pedidos p = aux2.remove();
                            System.out.println(p.getPrecioParcial());
                            if (p.getGarzon().equalsIgnoreCase(nombreGarzon)) {
                                sumaTotalVendido = sumaTotalVendido + p.getPrecioParcial();
                            }
                        }
                    }
                }
                this.jTable4.setValueAt(nombreGarzon, fila, 0);
                this.jTable4.setValueAt(sumaTotalVendido, fila, 1);
                sumaTotalVendido = 0;
                fila++;
            }
            aux = aux.getSig();
        }
    }

    public void reiniciarTabla4() {
        jTable4.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "GARZON", "SUMA TOTAL VENDIDO"
                }
        ));
    }

    public void agregarFilaATabla4() {
        DefaultTableModel model = (DefaultTableModel) jTable4.getModel();
        model.addRow(new Object[]{});
    }
    //********************************************************

    private ListaPedidosMes lpm;

    public void llenarTabla1() {
        reiniciarTabla1();
        int fila = 0;
        Queue<ListaPedidos> aux = new LinkedList();
        aux.addAll(this.lpm.getLp());
        while (!aux.isEmpty()) {
            Queue<Pedidos> aux2 = new LinkedList();
            ListaPedidos lp = aux.remove();
            if (this.jComboBox1.getSelectedItem().toString().equals(lp.getFecha())) {
                aux2.addAll(lp.getP());
                while (!aux2.isEmpty()) {
                    agregarFilaATabla1();
                    Pedidos p = aux2.remove();
                    this.jTable1.setValueAt(p.getNroPedidos(), fila, 0);
                    this.jTable1.setValueAt(p.getGarzon(), fila, 1);
                    this.jTable1.setValueAt(p.getAdministrador(), fila, 2);
                    this.jTable1.setValueAt(p.getProducto(), fila, 3);
                    this.jTable1.setValueAt(p.getCantidad(), fila, 4);
                    this.jTable1.setValueAt(p.getLugar(), fila, 5);
                    this.jTable1.setValueAt(p.getPrecioUnitario(), fila, 6);
                    this.jTable1.setValueAt(p.getPrecioParcial(), fila, 7);
                    this.jTable1.setValueAt(p.getFecha(), fila, 8);
                    this.jTable1.setValueAt(p.getHora(), fila, 9);
                    this.jTable1.setValueAt(p.getFactura(), fila, 10);
                    fila++;
                }
            }
        }
    }

    public void reiniciarTabla1() {
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "# Pedido", "Garzon", "administrador", "productos", "cantidad", "Lugar", "Precio unitario", "Precio total", "fecha", "hora", "Factura"
                }
        ));
    }

    public void agregarFilaATabla1() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.addRow(new Object[]{});
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        BCerveza = new javax.swing.JButton();
        jProgressBar1 = new javax.swing.JProgressBar();
        BLicores = new javax.swing.JButton();
        jProgressBar2 = new javax.swing.JProgressBar();
        BRefrescos = new javax.swing.JButton();
        jProgressBar3 = new javax.swing.JProgressBar();
        BCocteles = new javax.swing.JButton();
        BCigarrillos = new javax.swing.JButton();
        jProgressBar4 = new javax.swing.JProgressBar();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        pantProducto1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        pantFecha = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        pantActivo = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        pantVendido = new javax.swing.JTextField();
        pantTotal = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        pantUtilidad = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton16 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BCerveza.setText("CERVEZA");
        BCerveza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BCervezaActionPerformed(evt);
            }
        });

        BLicores.setText("LICORES");
        BLicores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BLicoresActionPerformed(evt);
            }
        });

        BRefrescos.setText("REFRESCOS");
        BRefrescos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BRefrescosActionPerformed(evt);
            }
        });

        BCocteles.setText("COCTELES");
        BCocteles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BCoctelesActionPerformed(evt);
            }
        });

        BCigarrillos.setText("CIGARRILLOS");
        BCigarrillos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BCigarrillosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(BCerveza, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(BLicores, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(BRefrescos, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(BCocteles, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(BCigarrillos, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(jProgressBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(jProgressBar3, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(148, 148, 148)
                        .addComponent(jProgressBar4, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(118, 118, 118))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BCerveza, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BLicores, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BRefrescos, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BCocteles, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BCigarrillos, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jProgressBar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jProgressBar3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jProgressBar4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 10, 710, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "# Pedido", "Garzon", "administrador", "productos","cantidad","Lugar","Precio unitario","Precio total","fecha","hora","Factura"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, 1340, 90));

        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 3, true));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "Lista de productos", "Capital activo(bs)", "Capital vendido(bs)", "Vendido cajas","Vendido +uds","Vendido vil.[cc]","Stock Cajas","Stock +uds.","Stock Vol[cc]"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(3, 14, 1310, 165));

        jLabel1.setText("Suma de capital activo");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(113, 183, 132, -1));

        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel2.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(113, 203, 130, -1));

        jLabel2.setText("Suma de capital vendido hoy");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(263, 183, 188, -1));

        jTextField2.setEditable(false);
        jTextField2.setBackground(new java.awt.Color(153, 153, 153));
        jPanel2.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(263, 203, 120, -1));

        pantProducto1.setEditable(false);
        pantProducto1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel2.add(pantProducto1, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 203, 90, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 1330, 240));

        jButton1.setText("ATRAS");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 20, 120, 60));

        jButton2.setText("Exportar a Excel");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 460, -1, -1));

        jLabel3.setText("PRODUCTO MAS VENDIDO");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 500, -1, -1));

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "PRODUCTO", "CANTIDAD"
            }
        ));
        jScrollPane3.setViewportView(jTable3);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 530, 240, 130));

        jLabel4.setText("VENTA POR GARZON");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 500, -1, -1));

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "GARZON", "SUMA TOTAL VENDIDO"
            }
        ));
        jScrollPane4.setViewportView(jTable4);

        getContentPane().add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 530, 290, 130));

        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 3, true));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"CERVEZAS", null, null},
                {"LICORES", null, null},
                {"REFRESCOS", null, null},
                {"CIGARRILLOS", null, null}
            },
            new String [] {
                "PRODUCTO", "SUMA DE ACTIVO", "SUMA CAPITAL VENDIDO"
            }
        ));
        jScrollPane5.setViewportView(jTable5);

        jPanel3.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, 121));

        jLabel6.setText("FECHA");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 71, -1));

        pantFecha.setEditable(false);
        pantFecha.setBackground(new java.awt.Color(153, 153, 153));
        jPanel3.add(pantFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 71, -1));

        jLabel7.setText("ACTIVO");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 180, 63, -1));

        pantActivo.setEditable(false);
        pantActivo.setBackground(new java.awt.Color(153, 153, 153));
        jPanel3.add(pantActivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 200, 63, -1));

        jLabel8.setText("VENDIDO");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 180, -1, -1));

        pantVendido.setEditable(false);
        pantVendido.setBackground(new java.awt.Color(153, 153, 153));
        jPanel3.add(pantVendido, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 200, 64, -1));

        pantTotal.setEditable(false);
        pantTotal.setBackground(new java.awt.Color(153, 153, 153));
        jPanel3.add(pantTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 200, 71, -1));

        jLabel9.setText("TOTAL VENTAS/DIA");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 180, 120, -1));

        pantUtilidad.setEditable(false);
        pantUtilidad.setBackground(new java.awt.Color(153, 153, 153));
        jPanel3.add(pantUtilidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 200, 73, -1));

        jLabel10.setText("UTILIDAD/DIA");
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 180, 119, -1));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("BALANCE DIARIO");
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 220, -1));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 460, 500, 240));

        jLabel5.setText("TOTAL VENTAS/DIA:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 680, 120, -1));
        getContentPane().add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 680, 160, -1));

        jButton5.setText("MENSUAL");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 470, 110, 110));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, 110, -1));

        jButton16.setText("Lista de ventas con factura");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton16, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 600, 200, 60));

        jButton3.setText("Abrir exportados");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 460, -1, -1));

        jButton4.setText("RESET DIA");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1240, 470, 110, 110));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BCervezaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BCervezaActionPerformed
        this.tipo = "Cerveza";
        this.pantProducto1.setText(tipo);
        llenarTabla2();
        sumarActivos();
        sumarCapitalVendido();
        //llenarTabla1Especifico();
        //this.PanelCocteles.setVisible(false);
    }//GEN-LAST:event_BCervezaActionPerformed

    private void BLicoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BLicoresActionPerformed
        this.tipo = "Licores";
        this.pantProducto1.setText(tipo);
        llenarTabla2();
        sumarActivos();
        sumarCapitalVendido();
        //llenarTabla1Especifico();
        //this.PanelCocteles.setVisible(false);
    }//GEN-LAST:event_BLicoresActionPerformed

    private void BRefrescosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BRefrescosActionPerformed
        this.tipo = "Refrescos";
        this.pantProducto1.setText(tipo);
        llenarTabla2();
        sumarActivos();
        sumarCapitalVendido();
        //llenarTabla1Especifico();
        //this.PanelCocteles.setVisible(false);
    }//GEN-LAST:event_BRefrescosActionPerformed

    private void BCoctelesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BCoctelesActionPerformed
        this.tipo = "Cocteles";
        this.pantProducto1.setText(tipo);
        llenarTabla2();
        //llenarTabla1Especifico();
        //this.PanelCocteles.setVisible(true);
    }//GEN-LAST:event_BCoctelesActionPerformed

    private void BCigarrillosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BCigarrillosActionPerformed
        this.tipo = "Cigarrillos";
        this.pantProducto1.setText(tipo);
        llenarTabla2();
        sumarActivos();
        sumarCapitalVendido();
        //llenarTabla1Especifico();
        //this.PanelCocteles.setVisible(false);
    }//GEN-LAST:event_BCigarrillosActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            FramePrincipal a = new FramePrincipal();
            a.show(true);

            this.dispose();
        } catch (IOException ex) {
            Logger.getLogger(FrameLogin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FrameLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        this.fecha = this.jComboBox1.getSelectedItem().toString();
        llenarTabla4();
        llenarTabla1();
        productos();
        productoMasVendido(fecha);
        llenarTabla5();
        pantFecha.setText(fecha);
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        try {
            FrameGestionMensual fg = new FrameGestionMensual();
            fg.show(true);
            this.dispose();
        } catch (IOException ex) {
            Logger.getLogger(FrameGestion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FrameGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        try {
            FrameFacturaCliente fc = new FrameFacturaCliente();
            fc.show(true);
        } catch (IOException ex) {
            Logger.getLogger(FrameVentaCaja.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FrameVentaCaja.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            importarExcelTabla1();
            JOptionPane.showMessageDialog(null, "SE IMPORTO EN EXCEL EL REGISTRO DE VENTAS CORRECTAMENTE");
        } catch (IOException ex) {
            Logger.getLogger(FrameGestion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (WriteException ex) {
            Logger.getLogger(FrameGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        FrameImportar fi = new FrameImportar(".\\ProductosVendidos\\");
        fi.ocultarAbrir();
        fi.show(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        int w = JOptionPane.showConfirmDialog(null, "Esto eliminara el Stock vendido hasta ahora.\n Aun se podra visualizar el stock vendido por dia en MENSUAL", "DESEA REINICIAR DIA?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (w == 0) {
            int max = this.p.getcCerveza().size();
            for (int i = 0; i < max; i++) {
                this.p.getcCerveza().get(i).setStockVendido(new Stock());
            }
            max = this.p.getcCigarrilos().size();
            for (int i = 0; i < max; i++) {
                this.p.getcCigarrilos().get(i).setStockVendido(new Stock());
            }
            max = this.p.getcCocteles().size();
            for (int i = 0; i < max; i++) {
                this.p.getcCocteles().get(i).setStockVendido(new Stock());
            }
            max = this.p.getcLicores().size();
            for (int i = 0; i < max; i++) {
                this.p.getcLicores().get(i).setStockVendido(new Stock());
            }
            max = this.p.getcRefrescos().size();
            for (int i = 0; i < max; i++) {
                this.p.getcRefrescos().get(i).setStockVendido(new Stock());
            }
            this.p.guardar(p);

            JOptionPane.showMessageDialog(null, "SE REINICIO EL STOCK VENDIDO HASTA AHORA\n\nSe mantendra el stock vendido del dia de hoy en MENSUAL");
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    public String modificarFecha(String fecha) {
        String fe = "";
        for (int i = 0; i < fecha.length(); i++) {
            if (fecha.charAt(i) != '/') {
                fe += fecha.charAt(i);
            } else {
                fe += '_';
            }
        }
        return fe;
    }

    public void importarExcelTabla1() throws IOException, WriteException {
        Queue<ListaPedidos> aux = new LinkedList();
        aux.addAll(this.lpm.getLp());
        String path = ".\\ProductosVendidos\\Venta_" + ObtenerFecha2() + ".xls";
        WritableWorkbook libro1 = Workbook.createWorkbook(new File(path));
        int con = 0;
        while (!aux.isEmpty()) {
            ListaPedidos auxlp = aux.remove();
            WritableSheet hoja = libro1.createSheet(modificarFecha(auxlp.getFecha()), con);

            Label etiqueta = new Label(0, 0, "# Pedido");
            hoja.addCell(etiqueta);
            etiqueta = new Label(1, 0, "Garzon");
            hoja.addCell(etiqueta);
            etiqueta = new Label(2, 0, "Administrador");
            hoja.addCell(etiqueta);
            etiqueta = new Label(3, 0, "Productos");
            hoja.addCell(etiqueta);
            etiqueta = new Label(4, 0, "Cantidad");
            hoja.addCell(etiqueta);
            etiqueta = new Label(5, 0, "Lugar");
            hoja.addCell(etiqueta);
            etiqueta = new Label(6, 0, "Precio Unitario");
            hoja.addCell(etiqueta);
            etiqueta = new Label(7, 0, "Precio Total");
            hoja.addCell(etiqueta);
            etiqueta = new Label(8, 0, "Fecha");
            hoja.addCell(etiqueta);
            etiqueta = new Label(9, 0, "Hora");
            hoja.addCell(etiqueta);
            etiqueta = new Label(10, 0, "Factura");
            hoja.addCell(etiqueta);

            int i = 1;
            Queue<Pedidos> auxPed = new LinkedList();
            auxPed.addAll(auxlp.getP());
            while (!auxPed.isEmpty()) {
                Pedidos ped = auxPed.remove();
                etiqueta = new Label(0, i, ped.getNroPedidos() + "");
                hoja.addCell(etiqueta);
                etiqueta = new Label(1, i, ped.getGarzon());
                hoja.addCell(etiqueta);
                etiqueta = new Label(2, i, ped.getAdministrador());
                hoja.addCell(etiqueta);
                etiqueta = new Label(3, i, ped.getProducto());
                hoja.addCell(etiqueta);
                etiqueta = new Label(4, i, ped.getCantidad() + "");
                hoja.addCell(etiqueta);
                etiqueta = new Label(5, i, ped.getLugar());
                hoja.addCell(etiqueta);
                etiqueta = new Label(6, i, ped.getPrecioUnitario() + "");
                hoja.addCell(etiqueta);
                etiqueta = new Label(7, i, ped.getPrecioParcial() + "");
                hoja.addCell(etiqueta);
                etiqueta = new Label(8, i, ped.getFecha());
                hoja.addCell(etiqueta);
                etiqueta = new Label(9, i, ped.getHora());
                hoja.addCell(etiqueta);
                etiqueta = new Label(10, i, ped.getFactura());
                hoja.addCell(etiqueta);
                i++;
            }
            con++;
        }
        libro1.write();
        libro1.close();
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
            java.util.logging.Logger.getLogger(FrameGestion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameGestion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameGestion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameGestion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new FrameGestion().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(FrameGestion.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(FrameGestion.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BCerveza;
    private javax.swing.JButton BCigarrillos;
    private javax.swing.JButton BCocteles;
    private javax.swing.JButton BLicores;
    private javax.swing.JButton BRefrescos;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JProgressBar jProgressBar2;
    private javax.swing.JProgressBar jProgressBar3;
    private javax.swing.JProgressBar jProgressBar4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField pantActivo;
    private javax.swing.JTextField pantFecha;
    private javax.swing.JTextField pantProducto1;
    private javax.swing.JTextField pantTotal;
    private javax.swing.JTextField pantUtilidad;
    private javax.swing.JTextField pantVendido;
    // End of variables declaration//GEN-END:variables
}
