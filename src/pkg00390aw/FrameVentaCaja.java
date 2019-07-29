/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg00390aw;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author WINDOWS 10
 */
public class FrameVentaCaja extends javax.swing.JFrame implements Runnable {

    /**
     * Creates new form FrameVentaCaja
     */
    public FrameVentaCaja() throws IOException, ClassNotFoundException, InterruptedException {
        initComponents();
        this.setLocationRelativeTo(null);

        this.jProgressBar1.setMaximum(10000);
        this.jProgressBar2.setMaximum(10000);
        this.jProgressBar3.setMaximum(10000);
        this.jProgressBar4.setMaximum(50);
        System.out.println(fecha());
        File f = new File("flat.dat");
        if (f.exists()) {
            cb = new ClaseBotones();
            cb = cb.recuperar();
            this.Mesas = cb.getMesas();
            this.Barras = cb.getBarras();
            this.Salas = cb.getSalas();
            this.cantBarras = cb.getBarras().length;
            this.cantMesas = cb.getMesas().length;
            this.cantSalas = cb.getSalas().length;
            colocarMesas2();
            colocarBarras2();
            colocarSalas2();
            this.jButton4.setVisible(false);
            this.jButton5.setVisible(false);
            this.jScrollPane4.setVisible(false);
        }

        File f2 = new File("datosProductos.dat");
        if (f2.exists()) {
            this.p = this.p.recuperar();
            //agregarProducto();
            //llenarTabla1();
            llenarTabla1();
            detUnidades();
        }

        lr = new ListaReservas();
        File f3 = new File("ListaReservas.dat");
        if (f3.exists()) {
            this.lr = lr.recuperar();
            mostrarEnTabla3();
        }

        this.lpm = new ListaPedidosMes();
        File f4 = new File("ListaPedidosMes.dat");
        if (f4.exists()) {
            this.lpm = lpm.recuperar();
            mostrarPedidoTabla4(fecha());
        }
        llenarGarzon();
        //this.pantCantidad.setText(fecha());
        this.cf = new ConfigFactura();
        File f5 = new File("factura.dat");
        if (f5.exists()) {
            this.cf = this.cf.recuperar();
            if (!this.cf.getFecha().equals(fecha())) {
                this.cf.setNroPedido(1);
                //this.cf.setFecha(fecha());
                this.cf.guardar(cf);

            }
        }
        this.cf.setFecha(fecha());
        jButton14.setVisible(false);
        hilo = new Thread(this);
        hilo.start();
        //this.run();
        ContenedorPrincipal cp = new ContenedorPrincipal();
        File f6 = new File(".\\Vconfig.dat");
        if (f6.exists()) {
            v = cp.recuperarVconfig();
        }else{
            JOptionPane.showMessageDialog(null, "Ve a CONTROL DE PERSONAL y luego a ADMINISTRACION para ingresar la hora de Entrada y salida antes de comenzar VENTA EN CAJA");
        }
    }
    Vconfig v;

    private ConfigFactura cf;
    //************************* RELOJ PARA ELIMINAR RESERVACIONES***************

    Thread hilo;
    private String fecha;
    ClaseBotones cb;

    /**
     *
     * @throws InterruptedException
     */
    /*public String fechaDiaSig() {
        String f = "";
        int dia, mes, anio;
        Calendar calendario = Calendar.getInstance();
        calendario.add(Calendar.DAY_OF_MONTH, 1);
        dia = calendario.get(Calendar.DAY_OF_MONTH);
        mes = calendario.get(Calendar.MONTH) + 1;
        anio = calendario.get(Calendar.YEAR);
        f = dia + "/" + mes + "/" + anio;
        return f;
    }*/
    @Override
    public void run() {
        Thread ct = Thread.currentThread();
        while (ct == hilo) {
            try {
                File f3 = new File("ListaReservas.dat");
                if (f3.exists()) {
                    this.lr = lr.recuperar();
                }
            } catch (IOException ex) {
                Logger.getLogger(FrameVentaCaja.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FrameVentaCaja.class.getName()).log(Level.SEVERE, null, ex);
            }
            Calendar calendario = Calendar.getInstance();
            int h = calendario.get(Calendar.HOUR_OF_DAY);
            int m = calendario.get(Calendar.MINUTE);
            int s = calendario.get(Calendar.SECOND);
            this.lr.eliminarReserva(h, m, fecha2());
            mostrarEnTabla3();
            DecimalFormat df = new DecimalFormat("00");
            this.lr.guardad(lr);
            this.jLabel21.setText(df.format(h) + ":" + df.format(m) + ":" + df.format(s));

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(FrameVentaCaja.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    //**************************
    public void llenarGarzon() {
        NodoPersonal aux = FramePrincipal.cp.getLp().getP();
        while (aux != null) {
            if ("Garson".equalsIgnoreCase(aux.getPersonal().getNumMaquina())) {
                this.jComboBox1.addItem(aux.getPersonal().getNombre());
            }
            aux = aux.getSig();
        }
    }
    //************************************
    private ListaReservas lr;
    private ListaPedidosMes lpm;

    public void mostrarEnTabla3() {
        reiniciarTabla3();
        Queue<Reservas> aux = new LinkedList();
        aux.addAll(lr.getReservas());
        int fila = 0;
        while (!aux.isEmpty()) {

            Reservas r = aux.remove();
            if (r.getFechaReservacion().equals(fecha3())|| r.getFechaReservacion().equals(fecha4())) {
                agregarFilaATabla3();
                this.jTable3.setValueAt(r.getLugar(), fila, 0);
                this.jTable3.setValueAt(r.getHora() + ":" + r.getMinuto(), fila, 1);
                this.jTable3.setValueAt(r.getFechaActual(), fila, 2);
                fila++;
            }
        }
    }

    public void reiniciarTabla3() {
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Lugar", "Hora","Fecha"
                }
        ));
    }

    public void agregarFilaATabla3() {
        DefaultTableModel model = (DefaultTableModel) jTable3.getModel();
        model.addRow(new Object[]{});
    }

    //***********************************
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

    //*******************************
    public void colocarMesas2() {
        for (int i = 0; i < this.cantMesas; i++) {
            Mesas[i].setText("M" + (i + 1));
            //colocar evento
            Mesas[i].addActionListener((java.awt.event.ActionEvent evt) -> {
                pintarMesas2(evt);
            });
            this.PanelMesas.add(Mesas[i]);
        }
    }
//

    public void pintarMesas2(java.awt.event.ActionEvent evt) {
        for (int i = 0; i < this.Mesas.length; i++) {
            if (evt.getSource().equals(Mesas[i])) {
                System.out.println(Mesas[i].getBackground());
                if (Mesas[i].getBackground().equals(new Color(214, 217, 223))) {
                    Mesas[i].setBackground(new Color(255, 0, 0));
                } else {
                    Mesas[i].setBackground(new Color(214, 217, 223));
                }
                this.pantLugar.setText(Mesas[i].getText());
            }
        }
        this.cb.setMesas(Mesas);
        this.cb.guardar();
    }

    public void colocarBarras2() {
        for (int i = 0; i < this.cantBarras; i++) {
            Barras[i].setText("B" + (i + 1));
            Barras[i].addActionListener((java.awt.event.ActionEvent evt) -> {
                pintarBarras2(evt);
            });
            this.PanelMesas.add(Barras[i]);
        }
    }

    public void pintarBarras2(java.awt.event.ActionEvent evt) {
        for (int i = 0; i < this.Barras.length; i++) {
            if (evt.getSource().equals(Barras[i])) {
                System.out.println(Barras[i].getBackground());
                if (Barras[i].getBackground().equals(new Color(214, 217, 223))) {
                    Barras[i].setBackground(new Color(255, 0, 0));
                } else {
                    Barras[i].setBackground(new Color(214, 217, 223));
                }
                this.pantLugar.setText(Barras[i].getText());
            }
        }
        this.cb.setBarras(Barras);
        this.cb.guardar();
    }

    public void colocarSalas2() {
        for (int i = 0; i < this.cantSalas; i++) {
            Salas[i].setText("S" + (i + 1));
            Salas[i].addActionListener((java.awt.event.ActionEvent evt) -> {
                pintarSalas2(evt);
            });
            this.PanelMesas.add(Salas[i]);
        }
    }

    public void pintarSalas2(java.awt.event.ActionEvent evt) {
        for (int i = 0; i < this.Salas.length; i++) {
            if (evt.getSource().equals(Salas[i])) {
                System.out.println(Salas[i].getBackground());
                if (Salas[i].getBackground().equals(new Color(214, 217, 223))) {
                    Salas[i].setBackground(new Color(255, 0, 0));
                } else {
                    Salas[i].setBackground(new Color(214, 217, 223));
                }
                this.pantLugar.setText(Salas[i].getText());
            }
        }
        this.cb.setSalas(Salas);
        this.cb.guardar();
    }

    private int cantMesas = 0;
    private int cantBarras = 0;
    private int cantSalas = 0;

    private JButton[] Mesas;
    private JButton[] Barras;
    private JButton[] Salas;

    //*****************************
    private Productos p = new Productos();
    private String tipo = "";
    private String dirfoto = "";

    //*****************************
    public void llenarTabla1Especifico() {
        int cont = 0;
        reiniciarTabla1();
        if (this.tipo.equals("Cerveza")) {
            Stack<Cerveza> a = new Stack<Cerveza>();
            a.addAll(this.p.getcCerveza());
            Stack<Cerveza> aux = new Stack<Cerveza>();
            while (!a.isEmpty()) {
                Generatabla1();
                Cerveza c = (Cerveza) a.pop();
                this.jTable1.setValueAt(c.getNomProducto(), cont, 0);
                cont++;
                aux.add(c);
            }
            a.addAll(aux);
        } else if (this.tipo.equals("Licores")) {
            Stack<Licores> l = new Stack<Licores>();
            l.addAll(this.p.getcLicores());
            Stack<Licores> auxl = new Stack<Licores>();
            while (!l.isEmpty()) {
                Generatabla1();
                Licores licor = (Licores) l.pop();
                this.jTable1.setValueAt(licor.getNomProducto(), cont, 0);
                cont++;
                auxl.add(licor);
            }
            l.addAll(auxl);
        } else if (this.tipo.equals("Refrescos")) {
            Stack<Refrescos> r = new Stack<Refrescos>();
            r.addAll(this.p.getcRefrescos());
            Stack<Refrescos> auxr = new Stack<Refrescos>();
            while (!r.isEmpty()) {
                Generatabla1();
                Refrescos ref = (Refrescos) r.pop();
                this.jTable1.setValueAt(ref.getNomProducto(), cont, 0);
                cont++;
                auxr.add(ref);
            }
            r.addAll(auxr);
        } else if (this.tipo.equals("Cocteles")) {
            Stack<Cocteles> co = new Stack<Cocteles>();
            co.addAll(this.p.getcCocteles());
            Stack<Cocteles> auxc = new Stack<Cocteles>();
            while (!co.isEmpty()) {
                Generatabla1();
                Cocteles coc = (Cocteles) co.pop();
                this.jTable1.setValueAt(coc.getNomProducto(), cont, 0);
                cont++;
                auxc.add(coc);
            }
            co.addAll(auxc);
        } else if (this.tipo.equals("Cigarrillos")) {
            Stack<Cigarrillos> ci = new Stack<Cigarrillos>();
            ci.addAll(this.p.getcCigarrilos());
            Stack<Cigarrillos> auxci = new Stack<Cigarrillos>();
            while (!ci.isEmpty()) {
                Generatabla1();
                Cigarrillos cig = (Cigarrillos) ci.pop();
                this.jTable1.setValueAt(cig.getNomProducto(), cont, 0);
                cont++;
                auxci.add(cig);
            }
            ci.addAll(auxci);
        } else if (this.tipo.equals("Otros")) {
            Stack<Otros> o = new Stack<Otros>();
            o.addAll(this.p.getcOtros());
            Stack<Otros> auxO = new Stack<Otros>();
            while (!o.isEmpty()) {
                Generatabla1();
                Otros otros = (Otros) o.pop();
                this.jTable1.setValueAt(otros.getNomProducto(), cont, 0);
                cont++;
                auxO.add(otros);
            }
            o.addAll(auxO);
        } else {
            JOptionPane.showMessageDialog(null, "Presione un boton del producto que desea registrar");
        }
    }

    public void llenarTabla1() {
        reiniciarTabla1();
        //para cerveza
        //Stack a=this.p.getcCerveza();
        //a=new Stack();
        Stack<Cerveza> a = new Stack<Cerveza>();
        a.addAll(this.p.getcCerveza());
        Stack<Cerveza> aux = new Stack<Cerveza>();
        int cont = 0;
        while (!a.isEmpty()) {
            Generatabla1();
            Cerveza c = (Cerveza) a.pop();
            this.jTable1.setValueAt(c.getNomProducto(), cont, 0);
            cont++;
            aux.add(c);
        }
        a.addAll(aux);
        //****************************para licores
        //Stack l=this.p.getcLicores();
        Stack<Licores> l = new Stack<Licores>();
        l.addAll(this.p.getcLicores());
        Stack<Licores> auxl = new Stack<Licores>();
        while (!l.isEmpty()) {
            Generatabla1();
            Licores licor = (Licores) l.pop();
            this.jTable1.setValueAt(licor.getNomProducto(), cont, 0);
            cont++;
            auxl.add(licor);
        }
        l.addAll(auxl);
        //para Refrescos
        //Stack r=this.p.getcRefrescos();
        Stack<Refrescos> r = new Stack<Refrescos>();
        r.addAll(this.p.getcRefrescos());
        Stack<Refrescos> auxr = new Stack<Refrescos>();
        while (!r.isEmpty()) {
            Generatabla1();
            Refrescos ref = (Refrescos) r.pop();
            this.jTable1.setValueAt(ref.getNomProducto(), cont, 0);
            cont++;
            auxr.add(ref);
        }
        r.addAll(auxr);
        //para cocteles
        //Stack co=this.p.getcCocteles();
        Stack<Cocteles> co = new Stack<Cocteles>();
        co.addAll(this.p.getcCocteles());
        Stack<Cocteles> auxc = new Stack<Cocteles>();
        while (!co.isEmpty()) {
            Generatabla1();
            Cocteles coc = (Cocteles) co.pop();
            this.jTable1.setValueAt(coc.getNomProducto(), cont, 0);
            cont++;
            auxc.add(coc);
        }
        co.addAll(auxc);
        //para Cigarrillos
        //Stack ci=this.p.getcCigarrilos();
        Stack<Cigarrillos> ci = new Stack<Cigarrillos>();
        ci.addAll(this.p.getcCigarrilos());
        Stack<Cigarrillos> auxci = new Stack<Cigarrillos>();
        while (!ci.isEmpty()) {
            Generatabla1();
            Cigarrillos cig = (Cigarrillos) ci.pop();
            this.jTable1.setValueAt(cig.getNomProducto(), cont, 0);
            cont++;
            auxci.add(cig);
        }
        ci.addAll(auxci);
        //Para Otros
        //Stack o=this.p.getcOtros();
        Stack<Otros> o = new Stack<Otros>();
        o.addAll(this.p.getcOtros());
        Stack<Otros> auxO = new Stack<Otros>();
        while (!o.isEmpty()) {
            Generatabla1();
            Otros otros = (Otros) o.pop();
            this.jTable1.setValueAt(otros.getNomProducto(), cont, 0);
            cont++;
            auxO.add(otros);
        }
        o.addAll(auxO);

    }

    public void reiniciarTabla1() {
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "PRODUCTOS"
                }
        ));
    }

    public void Generatabla1() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.addRow(new Object[]{""});
    }
    //****************************
    private int posicion;
    private byte[] imagen = null;

    public void mostrarDatos() {
        int fila = this.jTable1.getSelectedRow();
        System.out.println(fila);
        if (this.tipo.equals("Cerveza")) {
            posicion = (this.p.getcCerveza().size() - 1) - fila;
            Cerveza cerv = this.p.getcCerveza().get(posicion);
            try {
                imagen = cerv.getFoto();
                if (imagen != null) {
                    InputStream in = new ByteArrayInputStream(imagen);
                    BufferedImage imagen;
                    try {
                        imagen = ImageIO.read(in);
                        this.BVerFoto.setIcon(new ImageIcon(imagen));
                    } catch (IOException ex) {
                        Logger.getLogger(FrameProductos.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    this.BVerFoto.setIcon(null);
                }
            } catch (Exception e) {
                System.out.println("Sin imagen\nError: " + e);
                this.BVerFoto.setIcon(null);
            }
            this.pantDetalles1.setText(String.valueOf(cerv.getVolumen()));
            this.pantDetalles2.setText(String.valueOf(cerv.getPrecio()));
            this.pantDetalles3.setText(cerv.getBrutoUnidad() + "");
            this.pantDetalles4.setText(cerv.getUnitarioCc() + "");
            //enviando datos del producto
            /*          this.pantNomProducto.setText(cerv.getNomProducto());
            this.pantVol.setText(cerv.getVolumen() + "");
            this.pantUnidades.setText(cerv.getCantidad() + "");
            this.pantPrecioUnitario.setText(cerv.getPrecio() + "");
             */ this.pantStock1.setText(cerv.getStockAlmacen().getStockCajas() + "");
            this.pantStock2.setText(cerv.getStockAlmacen().getStockUnidades() + "");
            this.pantStock3.setText(cerv.getStockAlmacen().getStockVolumen() + "");
            this.pantStock4.setText(cerv.getStockAlmacen().getStockVolTotal() + "");
            this.pantStock5.setText(cerv.getStockVendido().getStockVolTotal() + "");
            this.jProgressBar1.setValue((int) cerv.getStockAlmacen().getStockVolTotal());
        } else if (this.tipo.equals("Licores")) {
            posicion = (this.p.getcLicores().size() - 1) - fila;
            Licores lic = this.p.getcLicores().get(posicion);
            this.pantDetalles1.setText(String.valueOf(lic.getVolumen()));
            this.pantDetalles2.setText(String.valueOf(lic.getPrecio()));
            this.pantDetalles3.setText(lic.getBrutoUnidad() + "");
            this.pantDetalles4.setText(lic.getUnitarioCc() + "");
            //
            try {
                imagen = lic.getFoto();
                InputStream in = new ByteArrayInputStream(imagen);
                BufferedImage imagen;
                try {
                    imagen = ImageIO.read(in);
                    this.BVerFoto.setIcon(new ImageIcon(imagen));
                } catch (IOException ex) {
                    Logger.getLogger(FrameProductos.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (Exception e) {
                System.out.println("Sin imagen\nError: " + e);
                this.BVerFoto.setIcon(null);
            }
            /*          this.pantNomProducto.setText(lic.getNomProducto());
            this.pantVol.setText(lic.getVolumen() + "");
            this.pantUnidades.setText(lic.getCantidad() + "");
            this.pantPrecioUnitario.setText(lic.getPrecio() + "");
             */
            this.pantStock1.setText(lic.getStockAlmacen().getStockCajas() + "");
            this.pantStock2.setText(lic.getStockAlmacen().getStockUnidades() + "");
            this.pantStock3.setText(lic.getStockAlmacen().getStockVolumen() + "");
            this.pantStock4.setText(lic.getStockAlmacen().getStockVolTotal() + "");
            this.pantStock5.setText(lic.getStockVendido().getStockVolTotal() + "");
            this.jProgressBar2.setValue((int) lic.getStockAlmacen().getStockVolTotal());
        } else if (this.tipo.equals("Refrescos")) {
            posicion = (this.p.getcRefrescos().size() - 1) - fila;
            Refrescos lic = this.p.getcRefrescos().get(posicion);
            this.pantDetalles1.setText(String.valueOf(lic.getVolumen()));
            this.pantDetalles2.setText(String.valueOf(lic.getPrecio()));
            this.pantDetalles3.setText(lic.getBrutoUnidad() + "");
            this.pantDetalles4.setText(lic.getUnitarioCc() + "");
            //
            try {
                imagen = lic.getFoto();
                InputStream in = new ByteArrayInputStream(imagen);
                BufferedImage imagen;
                try {
                    imagen = ImageIO.read(in);
                    this.BVerFoto.setIcon(new ImageIcon(imagen));
                } catch (IOException ex) {
                    this.BVerFoto.setIcon(null);
                    Logger.getLogger(FrameProductos.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (Exception e) {
                System.out.println("Sin imagen\nError: " + e);
                this.BVerFoto.setIcon(null);
            }
            /*this.pantNomProducto.setText(lic.getNomProducto());
            this.pantVol.setText(lic.getVolumen() + "");
            this.pantUnidades.setText(lic.getCantidad() + "");
            this.pantPrecioUnitario.setText(lic.getPrecio() + "");
             */
            this.pantStock1.setText(lic.getStockAlmacen().getStockCajas() + "");
            this.pantStock2.setText(lic.getStockAlmacen().getStockUnidades() + "");
            this.pantStock3.setText(lic.getStockAlmacen().getStockVolumen() + "");
            this.pantStock4.setText(lic.getStockAlmacen().getStockVolTotal() + "");
            this.pantStock5.setText(lic.getStockVendido().getStockVolTotal() + "");
            this.jProgressBar3.setValue((int) lic.getStockAlmacen().getStockVolTotal());
        } else if (this.tipo.equals("Cocteles")) {
            //inabilitar pantallas y botones y activar los de cocteles
            posicion = (this.p.getcCocteles().size() - 1) - fila;
            Cocteles cc = this.p.getcCocteles().get(posicion);
            try {
                imagen = cc.getFoto();
                InputStream in = new ByteArrayInputStream(imagen);
                BufferedImage imagen;
                try {
                    imagen = ImageIO.read(in);
                    this.BVerFoto.setIcon(new ImageIcon(imagen));
                } catch (IOException ex) {
                    this.BVerFoto.setIcon(null);
                    Logger.getLogger(FrameProductos.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (Exception e) {
                System.out.println("Sin imagen\nError: " + e);
                this.BVerFoto.setIcon(null);
            }
            this.detallesPrecio.setText(cc.getPrecio() + "");
            this.detalleCoctel1.setText(cc.getBebida1());
            this.detalleCoctel2.setText(cc.getBebida2());
            this.detalleCoctel3.setText(cc.getBebida3());
            this.detalleVol1.setText(cc.getVolumen1() + "");
            this.detalleVol2.setText(cc.getVolumen2() + "");
            this.detalleVol3.setText(cc.getVolumen3() + "");

            //pantnomCocteles.setText(cc.getNomProducto());
            //pantPrecioUnCoctel.setText(cc.getPrecio() + "");
            //pantVolCoc1.setText(cc.getVolumen1() + "");
            //pantVolCoc2.setText(cc.getVolumen2() + "");
            //pantVolCoc3.setText(cc.getVolumen3() + "");
            //pantVolCoc4.setText(cc.getVolumen4() + "");
            //comboCerveza.setSelectedItem(cc.getBebida1());
            System.out.println(cc.getBebida1());
            //comboLicor1.setSelectedItem(cc.getBebida2());
            //comboLicor2.setSelectedItem(cc.getBebida3());
            //comboRefrescos.setSelectedItem(cc.getBebida4());

            this.pantStock1.setText(cc.getStockAlmacen().getStockCajas() + "");
            this.pantStock2.setText(cc.getStockAlmacen().getStockUnidades() + "");
            this.pantStock3.setText(cc.getStockAlmacen().getStockVolumen() + "");
            this.pantStock4.setText(cc.getStockAlmacen().getStockVolTotal() + "");
        } else if (this.tipo.equals("Cigarrillos")) {
            posicion = (this.p.getcCigarrilos().size() - 1) - fila;
            Cigarrillos ci = this.p.getcCigarrilos().get(posicion);
            this.pantDetalles2.setText(String.valueOf(ci.getPrecio()));
            this.pantDetalles3.setText(ci.getBrutoUnidad() + "");
            this.pantDetalles4.setText(ci.getUnitarioCc() + "");
            try {
                imagen = ci.getFoto();
                InputStream in = new ByteArrayInputStream(imagen);
                BufferedImage imagen;
                try {
                    imagen = ImageIO.read(in);
                    this.BVerFoto.setIcon(new ImageIcon(imagen));
                } catch (IOException ex) {
                    this.BVerFoto.setIcon(null);
                    Logger.getLogger(FrameProductos.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (Exception e) {
                System.out.println("Sin imagen\nError: " + e);
                this.BVerFoto.setIcon(null);
            }
            //this.pantNomProducto.setText(ci.getNomProducto());
            //this.pantVol.setText("");
            //this.pantUnidades.setText(ci.getCantidad() + "");
            //this.pantPrecioUnitario.setText(ci.getPrecio() + "");

            this.pantStock1.setText(ci.getStockAlmacen().getStockCajasCigarro() + "");
            this.pantStock2.setText(ci.getStockAlmacen().getStockUnidadesCigarros() + "");
            this.pantStock4.setText(ci.getStockAlmacen().getStockTotalCigarro() + "");
            this.jProgressBar4.setValue(ci.getStockAlmacen().getStockTotalCigarro());
            this.pantStock5.setText(ci.getStockVendido().getStockTotalCigarro() + "");
        } else if (this.tipo.equals("Otros")) {
            posicion = (this.p.getcOtros().size() - 1) - fila;
            Otros o = this.p.getcOtros().get(posicion);
            try {
                imagen = o.getFoto();
                InputStream in = new ByteArrayInputStream(imagen);
                BufferedImage imagen;
                try {
                    imagen = ImageIO.read(in);
                    this.BVerFoto.setIcon(new ImageIcon(imagen));
                } catch (IOException ex) {
                    Logger.getLogger(FrameProductos.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (Exception e) {
                System.out.println("Sin imagen\nError: " + e);
                this.BVerFoto.setIcon(null);
            }
            //this.pantNomProducto.setText(o.getNomProducto());
        } else {
            JOptionPane.showMessageDialog(null, "Presione un boton del producto que desea registrar");

        }
    }

    /*public void mostrarDatos() {
        int fila = this.jTable1.getSelectedRow();
        System.out.println(fila);
        if (this.tipo.equals("Cerveza")) {
            posicion = (this.p.getcCerveza().size() - 1) - fila;
            Cerveza cerv = this.p.getcCerveza().get(posicion);
            try {
                imagen = cerv.getFoto();
                if (imagen != null) {
                    InputStream in = new ByteArrayInputStream(imagen);
                    BufferedImage imagen;
                    try {
                        imagen = ImageIO.read(in);
                        this.BVerFoto.setIcon(new ImageIcon(imagen));
                    } catch (IOException ex) {
                        Logger.getLogger(FrameProductos.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } catch (Exception e) {
                System.out.println("Sin imagen\nError: " + e);
                this.BVerFoto.setIcon(null);
            }
            this.pantDetalles1.setText(String.valueOf(cerv.getVolumen()));
            this.pantDetalles2.setText(String.valueOf(cerv.getPrecio()));
            //enviando datos del producto
            this.pantStock1.setText(cerv.getStockAlmacen().getStockCajas() + "");
            this.pantStock2.setText(cerv.getStockAlmacen().getStockUnidades() + "");
            this.pantStock3.setText(cerv.getStockAlmacen().getStockVolumen() + "");
            this.pantStock4.setText(cerv.getStockAlmacen().getStockVolTotal() + "");
            this.pantStock5.setText(cerv.getStockVendido().getStockVolTotal() + "");
            this.jProgressBar1.setValue((int) cerv.getStockAlmacen().getStockVolTotal());

        } else if (this.tipo.equals("Licores")) {
            posicion = (this.p.getcLicores().size() - 1) - fila;
            Licores lic = this.p.getcLicores().get(posicion);
            this.pantDetalles1.setText(String.valueOf(lic.getVolumen()));
            this.pantDetalles2.setText(String.valueOf(lic.getPrecio()));
            //
            try {
                imagen = lic.getFoto();
                InputStream in = new ByteArrayInputStream(imagen);
                BufferedImage imagen;
                try {
                    imagen = ImageIO.read(in);
                    this.BVerFoto.setIcon(new ImageIcon(imagen));
                } catch (IOException ex) {
                    Logger.getLogger(FrameProductos.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (Exception e) {
                System.out.println("Sin imagen\nError: " + e);
                this.BVerFoto.setIcon(null);
            }

            this.pantStock1.setText(lic.getStockAlmacen().getStockCajas() + "");
            this.pantStock2.setText(lic.getStockAlmacen().getStockUnidades() + "");
            this.pantStock3.setText(lic.getStockAlmacen().getStockVolumen() + "");
            this.pantStock4.setText(lic.getStockAlmacen().getStockVolTotal() + "");
            this.pantStock5.setText(lic.getStockVendido().getStockVolTotal() + "");
            this.jProgressBar2.setValue((int) lic.getStockAlmacen().getStockVolTotal());
        } else if (this.tipo.equals("Refrescos")) {
            posicion = (this.p.getcRefrescos().size() - 1) - fila;
            Refrescos lic = this.p.getcRefrescos().get(posicion);
            this.pantDetalles1.setText(String.valueOf(lic.getVolumen()));
            this.pantDetalles2.setText(String.valueOf(lic.getPrecio()));
            //
            try {
                imagen = lic.getFoto();
                InputStream in = new ByteArrayInputStream(imagen);
                BufferedImage imagen;
                try {
                    imagen = ImageIO.read(in);
                    this.BVerFoto.setIcon(new ImageIcon(imagen));
                } catch (IOException ex) {
                    Logger.getLogger(FrameProductos.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (Exception e) {
                System.out.println("Sin imagen\nError: " + e);
                this.BVerFoto.setIcon(null);
            }

            this.pantStock1.setText(lic.getStockAlmacen().getStockCajas() + "");
            this.pantStock2.setText(lic.getStockAlmacen().getStockUnidades() + "");
            this.pantStock3.setText(lic.getStockAlmacen().getStockVolumen() + "");
            this.pantStock4.setText(lic.getStockAlmacen().getStockVolTotal() + "");
            this.pantStock5.setText(lic.getStockVendido().getStockVolTotal() + "");
            this.jProgressBar3.setValue((int) lic.getStockAlmacen().getStockVolTotal());
        } else if (this.tipo.equals("Cocteles")) {
            //inabilitar pantallas y botones y activar los de cocteles
            posicion = (this.p.getcCocteles().size() - 1) - fila;
            Cocteles cc = this.p.getcCocteles().get(posicion);
            try {
                imagen = cc.getFoto();
                InputStream in = new ByteArrayInputStream(imagen);
                BufferedImage imagen;
                try {
                    imagen = ImageIO.read(in);
                    this.BVerFoto.setIcon(new ImageIcon(imagen));
                } catch (IOException ex) {
                    Logger.getLogger(FrameProductos.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (Exception e) {
                System.out.println("Sin imagen\nError: " + e);
                this.BVerFoto.setIcon(null);
            }

            this.detallesPrecio.setText(cc.getPrecio() + "");
            this.detalleCoctel1.setText(cc.getBebida1());
            this.detalleCoctel2.setText(cc.getBebida2());
            this.detalleCoctel3.setText(cc.getBebida3());
            this.detalleVol1.setText(cc.getVolumen1() + "");
            this.detalleVol2.setText(cc.getVolumen2() + "");
            this.detalleVol3.setText(cc.getVolumen3() + "");

            this.pantStock1.setText(cc.getStockAlmacen().getStockCajas() + "");
            this.pantStock2.setText(cc.getStockAlmacen().getStockUnidades() + "");
            this.pantStock3.setText(cc.getStockAlmacen().getStockVolumen() + "");
            this.pantStock4.setText(cc.getStockAlmacen().getStockVolTotal() + "");
        } else if (this.tipo.equals("Cigarrillos")) {
            posicion = (this.p.getcCigarrilos().size() - 1) - fila;
            Cigarrillos ci = this.p.getcCigarrilos().get(posicion);
            this.pantDetalles2.setText(String.valueOf(ci.getPrecio()));
            try {
                imagen = ci.getFoto();
                InputStream in = new ByteArrayInputStream(imagen);
                BufferedImage imagen;
                try {
                    imagen = ImageIO.read(in);
                    this.BVerFoto.setIcon(new ImageIcon(imagen));
                } catch (IOException ex) {
                    Logger.getLogger(FrameProductos.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (Exception e) {
                System.out.println("Sin imagen\nError: " + e);
                this.BVerFoto.setIcon(null);
            }

            this.pantStock1.setText(ci.getStockAlmacen().getStockCajasCigarro() + "");
            this.pantStock2.setText(ci.getStockAlmacen().getStockUnidadesCigarros() + "");
            this.pantStock4.setText(ci.getStockAlmacen().getStockTotalCigarro() + "");
            this.pantStock5.setText(ci.getStockVendido().getStockTotalCigarro() + "");
            this.jProgressBar4.setValue((int) ci.getStockAlmacen().getStockTotalCigarro());

        } else if (this.tipo.equals("Otros")) {
            posicion = (this.p.getcOtros().size() - 1) - fila;
            Otros o = this.p.getcOtros().get(posicion);
        } else {
            JOptionPane.showMessageDialog(null, "Presione un boton del producto que desea registrar");
        }
    }*/
    //***************************
    public void habilitarCLR() {

        this.jLabel9.setVisible(true);
        this.pantStock3.setVisible(true);

        this.jLabel10.setText("Volumen total");

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane4 = new javax.swing.JScrollPane();
        PanelMesas = new javax.swing.JPanel();
        jButton11 = new javax.swing.JButton();
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
        PanelCocteles = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        detalleCoctel1 = new javax.swing.JTextField();
        detalleVol1 = new javax.swing.JTextField();
        detalleCoctel2 = new javax.swing.JTextField();
        detalleVol2 = new javax.swing.JTextField();
        detallesPrecio = new javax.swing.JTextField();
        detalleCoctel3 = new javax.swing.JTextField();
        detalleVol3 = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        pantDetalles1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        pantDetalles3 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        pantDetalles2 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        pantDetalles4 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        pantStock1 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        pantStock2 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        pantStock3 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        pantStock4 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        pantStock5 = new javax.swing.JTextField();
        BVerFoto = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        pantAdministrador = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        pantNroPedidos = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        pantCantidad = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        pantTotal = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jLabel19 = new javax.swing.JLabel();
        pantLugar = new javax.swing.JTextField();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jToggleButton1 = new javax.swing.JToggleButton();
        jButton16 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1142, 692));
        setResizable(false);
        setSize(new java.awt.Dimension(1142, 692));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PanelMesas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102), 3));

        jButton11.setText("OCULTAR");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelMesasLayout = new javax.swing.GroupLayout(PanelMesas);
        PanelMesas.setLayout(PanelMesasLayout);
        PanelMesasLayout.setHorizontalGroup(
            PanelMesasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelMesasLayout.createSequentialGroup()
                .addComponent(jButton11)
                .addGap(0, 543, Short.MAX_VALUE))
        );
        PanelMesasLayout.setVerticalGroup(
            PanelMesasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelMesasLayout.createSequentialGroup()
                .addComponent(jButton11)
                .addGap(0, 339, Short.MAX_VALUE))
        );

        jScrollPane4.setViewportView(PanelMesas);

        getContentPane().add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 310, 630, 370));

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

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 710, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "PRODUCTOS"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 170, 320));

        PanelCocteles.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 3, true));

        jLabel40.setText("Detalles Coctel");

        detalleCoctel1.setEditable(false);
        detalleCoctel1.setBackground(new java.awt.Color(153, 153, 153));

        detalleVol1.setEditable(false);
        detalleVol1.setBackground(new java.awt.Color(153, 153, 153));

        detalleCoctel2.setEditable(false);
        detalleCoctel2.setBackground(new java.awt.Color(153, 153, 153));
        detalleCoctel2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                detalleCoctel2ActionPerformed(evt);
            }
        });

        detalleVol2.setEditable(false);
        detalleVol2.setBackground(new java.awt.Color(153, 153, 153));

        detallesPrecio.setEditable(false);
        detallesPrecio.setBackground(new java.awt.Color(153, 153, 153));

        detalleCoctel3.setEditable(false);
        detalleCoctel3.setBackground(new java.awt.Color(153, 153, 153));

        detalleVol3.setEditable(false);
        detalleVol3.setBackground(new java.awt.Color(153, 153, 153));

        jLabel41.setText("Elegido");

        jLabel42.setText("Elegido");

        jLabel43.setText("Volumen[cc]");

        jLabel44.setText("Volumen[cc]");

        jLabel45.setText("Elegido");

        jLabel46.setText("Volumen[cc]");

        jLabel20.setText("Precio de ventas unitario");

        javax.swing.GroupLayout PanelCoctelesLayout = new javax.swing.GroupLayout(PanelCocteles);
        PanelCocteles.setLayout(PanelCoctelesLayout);
        PanelCoctelesLayout.setHorizontalGroup(
            PanelCoctelesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelCoctelesLayout.createSequentialGroup()
                .addGroup(PanelCoctelesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelCoctelesLayout.createSequentialGroup()
                        .addGap(144, 144, 144)
                        .addComponent(jLabel40))
                    .addGroup(PanelCoctelesLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(PanelCoctelesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelCoctelesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel41, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                                .addComponent(detalleCoctel1, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(detalleCoctel2, javax.swing.GroupLayout.Alignment.LEADING))
                            .addComponent(jLabel42))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PanelCoctelesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelCoctelesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(detalleVol1)
                                .addComponent(detalleVol2, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE))
                            .addComponent(jLabel44)
                            .addComponent(jLabel43))
                        .addGap(18, 18, 18)
                        .addGroup(PanelCoctelesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(PanelCoctelesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(detallesPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(PanelCoctelesLayout.createSequentialGroup()
                                    .addGroup(PanelCoctelesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(detalleCoctel3, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel45))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(PanelCoctelesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel46)
                                        .addComponent(detalleVol3)))))))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        PanelCoctelesLayout.setVerticalGroup(
            PanelCoctelesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelCoctelesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel40)
                .addGap(9, 9, 9)
                .addGroup(PanelCoctelesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(jLabel43)
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelCoctelesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(detalleCoctel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(detalleVol1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(detallesPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PanelCoctelesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel42)
                    .addComponent(jLabel44)
                    .addComponent(jLabel45)
                    .addComponent(jLabel46))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelCoctelesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(detalleCoctel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(detalleVol2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(detalleCoctel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(detalleVol3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(62, Short.MAX_VALUE))
        );

        getContentPane().add(PanelCocteles, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 100, 370, 200));

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102), 3));

        jLabel1.setText("DETALLES");

        pantDetalles1.setEditable(false);
        pantDetalles1.setBackground(new java.awt.Color(153, 153, 153));
        pantDetalles1.setText("0");

        jLabel2.setText("VOLUMEN [cc]/ud.");

        jLabel3.setText("PRECIO bruto/ud");

        pantDetalles3.setEditable(false);
        pantDetalles3.setBackground(new java.awt.Color(153, 153, 153));
        pantDetalles3.setText("0");

        jLabel4.setText("Precio de ventas unitario");

        pantDetalles2.setEditable(false);
        pantDetalles2.setBackground(new java.awt.Color(153, 153, 153));
        pantDetalles2.setText("0");

        jLabel5.setText("Precio unitario/cc");

        pantDetalles4.setEditable(false);
        pantDetalles4.setBackground(new java.awt.Color(153, 153, 153));
        pantDetalles4.setText("0");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(pantDetalles1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(pantDetalles2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(pantDetalles3, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(pantDetalles4, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(138, 138, 138)
                        .addComponent(jLabel1)))
                .addGap(17, 17, 17))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pantDetalles1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pantDetalles2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pantDetalles3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pantDetalles4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 100, 370, 200));

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102), 3));

        jLabel6.setText("STOCK");

        pantStock1.setEditable(false);
        pantStock1.setBackground(new java.awt.Color(153, 153, 153));
        pantStock1.setText("0");

        jLabel7.setText("CAJAS");

        jLabel8.setText("+Unidades");

        pantStock2.setEditable(false);
        pantStock2.setBackground(new java.awt.Color(153, 153, 153));
        pantStock2.setText("0");

        jLabel9.setText("Volumen [cc]");

        pantStock3.setEditable(false);
        pantStock3.setBackground(new java.awt.Color(153, 153, 153));
        pantStock3.setText("0");

        jLabel10.setText("Volumen total");

        pantStock4.setEditable(false);
        pantStock4.setBackground(new java.awt.Color(153, 153, 153));
        pantStock4.setText("0");

        jLabel18.setText("Vol. Vendido");

        pantStock5.setEditable(false);
        pantStock5.setBackground(new java.awt.Color(153, 153, 153));
        pantStock5.setText("0");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(137, 137, 137)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel7)
                .addGap(68, 68, 68)
                .addComponent(jLabel8)
                .addGap(48, 48, 48)
                .addComponent(jLabel9))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(pantStock1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(pantStock2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(pantStock3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(120, 120, 120)
                .addComponent(jLabel10))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(pantStock5, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(120, 120, 120)
                .addComponent(pantStock4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addGap(6, 6, 6)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pantStock1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pantStock2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pantStock3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addComponent(jLabel10))
                .addGap(6, 6, 6)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pantStock5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pantStock4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 100, 320, 200));

        BVerFoto.setText("Ver Foto");
        BVerFoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BVerFotoActionPerformed(evt);
            }
        });
        getContentPane().add(BVerFoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 430, 170, 90));

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102), 3));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pantAdministrador.setEditable(false);
        pantAdministrador.setBackground(new java.awt.Color(102, 102, 102));
        jPanel4.add(pantAdministrador, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 130, -1));

        jLabel11.setText("ADMINISTRADOR");
        jPanel4.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, -1, -1));

        jLabel12.setText("# DE PEDIDOS");
        jPanel4.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, 90, -1));

        pantNroPedidos.setEditable(false);
        pantNroPedidos.setBackground(new java.awt.Color(102, 102, 102));
        jPanel4.add(pantNroPedidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 130, -1));

        jLabel15.setText("GARZON");
        jPanel4.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, 60, -1));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {  }));
        jPanel4.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 130, 90, -1));

        jLabel16.setText("CANTIDAD");
        jPanel4.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, -1, -1));

        pantCantidad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pantCantidadMouseClicked(evt);
            }
        });
        pantCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                pantCantidadKeyReleased(evt);
            }
        });
        jPanel4.add(pantCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 170, 80, -1));

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 310, 190, 210));

        jButton1.setText("Lista reservaciones");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 530, -1, 60));

        jButton2.setText("Configuracion Mesa");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 20, 140, 60));

        jButton3.setText("PEDIDO NUEVO");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 323, 130, 30));

        jButton4.setText("INGRESAR PEDIDO");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 360, 130, 30));

        jButton5.setText("ENTREGAR");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 400, 130, 50));

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102), 3));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {
                "Producto", "Cantidad", "Precio unitario", "Precio parcial","Lugar"
            }
        ));
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jLabel13.setText("PEDIDOS");

        jButton6.setText("EDITAR");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("DEVOLUCION");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jLabel14.setText("PRECIO TOTAL PEDIDOS:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(182, 182, 182))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jButton6)
                        .addGap(18, 18, 18)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pantTotal))
                        .addContainerGap())))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addGap(8, 8, 8)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton7)
                    .addComponent(jButton6)
                    .addComponent(pantTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 320, 500, 240));

        jLabel17.setText("RESERVADOS");
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 530, -1, -1));

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "Lugar", "Hora"
            }
        ));
        jScrollPane3.setViewportView(jTable3);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 550, 200, 130));

        jLabel19.setText("Lugar:");
        getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 500, -1, -1));

        pantLugar.setEditable(false);
        pantLugar.setBackground(new java.awt.Color(153, 153, 153));
        getContentPane().add(pantLugar, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 520, 90, -1));

        jButton8.setText("Configuracion Factura");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 20, 150, 60));

        jButton9.setText("Facturar ya");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 460, 100, 30));

        jButton10.setText("Mostrar Mesas");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 620, 190, 40));

        jButton15.setText("ATRAS");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton15, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 30, 80, 50));

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "# de pedido", "Factura"
            }
        ));
        jTable4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable4MouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(jTable4);

        getContentPane().add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 100, 200, 160));

        jButton12.setText("Facturar seleccionado");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 270, -1, -1));

        jButton13.setText("EDITAR");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 270, -1, -1));

        jButton14.setText("GUARDAR EDICION");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton14, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 400, -1, 50));

        jLabel21.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        jLabel21.setText("jLabel21");
        getContentPane().add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 590, 110, 50));

        jToggleButton1.setText("TECLADO");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jToggleButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 320, 90, 50));

        jButton16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton16.setText("Reproductor");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton16, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 390, 120, 50));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BCervezaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BCervezaActionPerformed
        this.tipo = "Cerveza";
        llenarTabla1Especifico();
        this.PanelCocteles.setVisible(false);
        habilitarCLR();
        llenarDeCero();
    }//GEN-LAST:event_BCervezaActionPerformed

    private void BLicoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BLicoresActionPerformed
        this.tipo = "Licores";
        llenarTabla1Especifico();
        this.PanelCocteles.setVisible(false);
        habilitarCLR();
        llenarDeCero();
    }//GEN-LAST:event_BLicoresActionPerformed

    private void BRefrescosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BRefrescosActionPerformed
        this.tipo = "Refrescos";
        llenarTabla1Especifico();
        this.PanelCocteles.setVisible(false);
        habilitarCLR();
        llenarDeCero();
    }//GEN-LAST:event_BRefrescosActionPerformed

    public void llenarDeCero() {
        this.pantStock1.setText("0");
        this.pantStock2.setText("0");
        this.pantStock3.setText("0");
        this.pantStock4.setText("0");
        this.pantDetalles1.setText("0");
        this.pantDetalles2.setText("0");
        this.pantDetalles3.setText("0");
        this.pantDetalles4.setText("0");
        this.detalleVol1.setText("0");
        this.detalleVol2.setText("0");
        this.detalleVol3.setText("0");
        this.detalleCoctel1.setText("");
        this.detalleCoctel2.setText("");
        this.detalleCoctel3.setText("");
        this.detallesPrecio.setText("0");
        this.BVerFoto.setIcon(null);
        this.imagen = null;
    }

    private void BCoctelesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BCoctelesActionPerformed
        this.tipo = "Cocteles";
        llenarTabla1Especifico();
        this.PanelCocteles.setVisible(true);
        habilitarCocteles();
        llenarDeCero();
        this.PanelCocteles.setVisible(true);
        this.jPanel2.setVisible(false);
    }//GEN-LAST:event_BCoctelesActionPerformed

    private void BCigarrillosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BCigarrillosActionPerformed
        this.tipo = "Cigarrillos";
        llenarTabla1Especifico();
        this.PanelCocteles.setVisible(false);
        habilitarCigarrillo();
        llenarDeCero();
        this.PanelCocteles.setVisible(false);
        this.jPanel2.setVisible(true);
    }//GEN-LAST:event_BCigarrillosActionPerformed

    public void habilitarCigarrillo() {

        this.jLabel9.setVisible(false);
        this.pantStock3.setVisible(false);

        this.jLabel10.setText("Total Cigarrillos");
    }

    public void habilitarCocteles() {

        this.jLabel9.setVisible(true);
        this.pantStock3.setVisible(true);

        this.jLabel10.setText("Volumen total");
    }
    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        mostrarDatos();
    }//GEN-LAST:event_jTable1MouseClicked

    private void detalleCoctel2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_detalleCoctel2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_detalleCoctel2ActionPerformed

    private double suma = 0;
    private Queue<Double> volumenes = new LinkedList();
    private Queue<Double> volumenUnidad = new LinkedList();

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if (!this.pantLugar.getText().equals("")) {
            if (!this.pantCantidad.getText().equals("")) {
                int fila = this.jTable1.getSelectedRow();
                try {
                    ingresarPedido();
                    obtenerTotal();
                    //***
                    this.p.venderProducto(Integer.valueOf(this.pantCantidad.getText()), this.jTable1.getValueAt(fila, 0).toString());
                    mostrarDatos();
                    //***
                    volumenes.add(Double.valueOf(this.pantStock5.getText()));
                    volumenUnidad.add(Double.valueOf(this.pantDetalles1.getText()));

                    this.suma += Double.valueOf(this.pantStock5.getText());
                    this.pantStock5.setText(suma + "");
                } catch (Exception e) {
                    System.out.println("Error: " + e);
                    JOptionPane.showMessageDialog(null, "SELECCIONE UN PRODUCTO DE LA LISTA, O LLENE LAS CASILLAS CORRESPONDIENTES");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese una cantidad");
            }
            detUnidades();
        } else {
            JOptionPane.showMessageDialog(null, "SELECCIONE LA MESA A LA QUE SE ENTREGARA EL PEDIDO");
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private int fila = 0;

    //*******CALCULANDO DATOS DEL STOCK PARA CIGARRILLOS*************************
    public int stockCajasCigarrillos(int totalCigarros) {
        int cajas = 0;
        if (this.tipo.equals("Cigarrillos")) {
            Cigarrillos c = this.p.getcCigarrilos().get(posicion);
            cajas = (int) (totalCigarros / c.getCantidad());

        }
        return cajas;
    }

    public int stockUnidadesCigarrillos(int totalCigarros) {
        int cajasInt = 0;
        double cajas = 0;
        int unidades = 0;
        if (this.tipo.equals("Cigarrillos")) {
            Cigarrillos c = this.p.getcCigarrilos().get(posicion);
            cajasInt = (int) (totalCigarros / c.getCantidad());
            cajas = totalCigarros / c.getCantidad();
            double res = cajas - cajasInt;
            unidades = (int) (res * c.getCantidad());
        }
        return unidades;
    }

    //*******CALCULANDO DATOS DEL STOCK VENDIDO PARA CERVEZA LICORES REFRESCOS***************
    public int stockCajas(double volumenTotal) {
        int cajas = 0;
        if (this.tipo.equals("Cerveza")) {
            Cerveza cerv = this.p.getcCerveza().get(posicion);
            Double volUni = cerv.getVolumen();
            int undCaja = cerv.getCantidad();
            cajas = (int) (volumenTotal / (volUni * undCaja));

        } else if (this.tipo.equals("Licores")) {
            Licores lic = this.p.getcLicores().get(posicion);
            Double volUni = lic.getVolumen();
            int undCaja = lic.getCantidad();
            cajas = (int) (volumenTotal / (volUni * undCaja));
        } else if (this.tipo.equals("Refrescos")) {
            Refrescos lic = this.p.getcRefrescos().get(posicion);
            Double volUni = lic.getVolumen();
            int undCaja = lic.getCantidad();
            cajas = (int) (volumenTotal / (volUni * undCaja));
        }
        return cajas;
    }

    public int stockUnidades(double volumenTotal) {
        int unidades = 0;
        double cajas = 0;
        int cajasInt = 0;
        double restoCajas = 0;
        if (this.tipo.equals("Cerveza")) {
            posicion = (this.p.getcCerveza().size() - 1) - fila;
            Cerveza cerv = this.p.getcCerveza().get(posicion);
            Double volUni = cerv.getVolumen();
            int undCaja = cerv.getCantidad();
            cajas = volumenTotal / (volUni * undCaja);
            cajasInt = (int) (volumenTotal / (volUni * undCaja));
            restoCajas = cajas - cajasInt;
            unidades = (int) (restoCajas * undCaja);
        } else if (this.tipo.equals("Licores")) {
            posicion = (this.p.getcLicores().size() - 1) - fila;
            Licores lic = this.p.getcLicores().get(posicion);
            Double volUni = lic.getVolumen();
            int undCaja = lic.getCantidad();
            cajas = volumenTotal / (volUni * undCaja);
            cajasInt = (int) (volumenTotal / (volUni * undCaja));
            restoCajas = cajas - cajasInt;
            unidades = (int) (restoCajas * undCaja);
        } else if (this.tipo.equals("Refrescos")) {
            posicion = (this.p.getcRefrescos().size() - 1) - fila;
            Refrescos lic = this.p.getcRefrescos().get(posicion);
            Double volUni = lic.getVolumen();
            int undCaja = lic.getCantidad();
            cajas = volumenTotal / (volUni * undCaja);
            cajasInt = (int) (volumenTotal / (volUni * undCaja));
            restoCajas = cajas - cajasInt;
            unidades = (int) (restoCajas * undCaja);
        }
        return unidades;
    }

    public double stockVolumen(double volumenTotal) {
        int unidadesInt = 0;
        double unidades = 0;
        double volumen = 0;
        double cajas = 0;
        int cajasInt = 0;
        double restoCajas = 0;
        if (this.tipo.equals("Cerveza")) {
            posicion = (this.p.getcCerveza().size() - 1) - fila;
            Cerveza cerv = this.p.getcCerveza().get(posicion);
            Double volUni = cerv.getVolumen();
            int undCaja = cerv.getCantidad();
            cajas = volumenTotal / (volUni * undCaja);
            cajasInt = (int) (volumenTotal / (volUni * undCaja));
            restoCajas = cajas - cajasInt;
            unidadesInt = (int) (restoCajas * undCaja);
            unidades = restoCajas * undCaja;
            double restoUnidades = unidades - unidadesInt;
            volumen = restoUnidades * volUni;
        } else if (this.tipo.equals("Licores")) {
            posicion = (this.p.getcLicores().size() - 1) - fila;
            Licores lic = this.p.getcLicores().get(posicion);
            Double volUni = lic.getVolumen();
            int undCaja = lic.getCantidad();
            cajas = volumenTotal / (volUni * undCaja);
            cajasInt = (int) (volumenTotal / (volUni * undCaja));
            restoCajas = cajas - cajasInt;
            unidadesInt = (int) (restoCajas * undCaja);
            unidades = restoCajas * undCaja;
            double restoUnidades = unidades - unidadesInt;
            volumen = restoUnidades * volUni;
        } else if (this.tipo.equals("Refrescos")) {
            posicion = (this.p.getcRefrescos().size() - 1) - fila;
            Refrescos lic = this.p.getcRefrescos().get(posicion);
            Double volUni = lic.getVolumen();
            int undCaja = lic.getCantidad();
            cajas = volumenTotal / (volUni * undCaja);
            cajasInt = (int) (volumenTotal / (volUni * undCaja));
            restoCajas = cajas - cajasInt;
            unidadesInt = (int) (restoCajas * undCaja);
            unidades = restoCajas * undCaja;
            double restoUnidades = unidades - unidadesInt;
            volumen = restoUnidades * volUni;
        }
        return volumen;
    }

    //***************REDUCIR DEL STOCK Y AUMENTAR STOCK VENDIDO*************************************
    public void reducirStock() {
        if (this.tipo.equals("Cerveza")) {
            this.p.getcCerveza().get(posicion).aumentarStockAlmacen(-Double.valueOf(this.pantStock5.getText()));//AQUI ESTAMOS REDUCIENDO EL STOCK
            this.p.getcCerveza().get(posicion).aumentarStockVendido(Double.valueOf(this.pantStock5.getText()));//AQUI AUMENTO EL STOCK VENDIDO
        } else if (this.tipo.equals("Licores")) {
            this.p.getcLicores().get(posicion).aumentarStockAlmacen(-Double.valueOf(this.pantStock5.getText()));//AQUI ESTAMOS REDUCIENDO EL STOCK
            this.p.getcLicores().get(posicion).aumentarStockVendido(Double.valueOf(this.pantStock5.getText()));//AQUI AUMENTO EL STOCK VENDIDO
        } else if (this.tipo.equals("Refrescos")) {
            this.p.getcRefrescos().get(posicion).aumentarStockAlmacen(-Double.valueOf(this.pantStock5.getText()));//AQUI ESTAMOS REDUCIENDO EL STOCK
            this.p.getcRefrescos().get(posicion).aumentarStockVendido(Double.valueOf(this.pantStock5.getText()));//AQUI AUMENTO EL STOCK VENDIDO
        }
    }

    //****************INGRESAR PEDIDOS*************************************
    public void ingresarPedido() {
        try {
            //int fila = this.jTable1.getSelectedRow();
            agregarFilaTabla2();
            this.jTable2.setValueAt(this.jTable1.getValueAt(this.jTable1.getSelectedRow(), 0), fila, 0);
            this.jTable2.setValueAt(this.pantCantidad.getText(), fila, 1);
            this.jTable2.setValueAt(this.pantDetalles2.getText(), fila, 2);
            if (tipo.equals("Cocteles")) {
                this.jTable2.setValueAt(this.detallesPrecio.getText(), fila, 2);
                this.jTable2.setValueAt(((Double.valueOf(this.pantCantidad.getText())) * (Double.valueOf(this.detallesPrecio.getText()))) + "", fila, 3);
            } else {
                this.jTable2.setValueAt(this.pantDetalles2.getText(), fila, 2);
                this.jTable2.setValueAt(((Double.valueOf(this.pantCantidad.getText())) * (Double.valueOf(this.pantDetalles2.getText()))) + "", fila, 3);
            }
            this.jTable2.setValueAt(this.pantLugar.getText(), fila, 4);
            fila++;
        } catch (Exception e) {
            System.out.println(("Error: " + e));
            JOptionPane.showMessageDialog(null, "Seleccione un producto de la tabla de productos");
        }
    }

    public void reiniciarTabla2() {
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Producto", "Cantidad", "Precio unitario", "Precio parcial", "Lugar"
                }
        ));
    }

    public void agregarFilaTabla2() {
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        model.addRow(new Object[]{""});
    }

    public String fecha2() {
        String f = "";
        int dia, mes, anio;
        Calendar calendario = Calendar.getInstance();
        dia = calendario.get(Calendar.DAY_OF_MONTH);
        mes = calendario.get(Calendar.MONTH) + 1;
        anio = calendario.get(Calendar.YEAR);
        f = dia + "/" + mes + "/" + anio;
        return f;
    }
    public String fecha3() {
        String f = "";
        int dia, mes, anio;
        Calendar calendario = Calendar.getInstance();
        
        //if(v.getHoraEntrada()>v.getHoraSalida()){
        //calendario.add(Calendar.DAY_OF_MONTH, +1);
        //}
        System.out.println(calendario.get(Calendar.HOUR_OF_DAY) + ":" + calendario.get(Calendar.MINUTE));
        dia = calendario.get(Calendar.DAY_OF_MONTH);
        mes = calendario.get(Calendar.MONTH) + 1;
        anio = calendario.get(Calendar.YEAR);
        f = dia + "/" + mes + "/" + anio;
        return f;
    }
    
    public String fecha4() {
        String f = "";
        int dia, mes, anio;
        Calendar calendario = Calendar.getInstance();
        
        //if(v.getHoraEntrada()>v.getHoraSalida()){
        calendario.add(Calendar.DAY_OF_MONTH, +1);
        //}
        System.out.println(calendario.get(Calendar.HOUR_OF_DAY) + ":" + calendario.get(Calendar.MINUTE));
        dia = calendario.get(Calendar.DAY_OF_MONTH);
        mes = calendario.get(Calendar.MONTH) + 1;
        anio = calendario.get(Calendar.YEAR);
        f = dia + "/" + mes + "/" + anio;
        return f;
    }

    public String fecha() {
        String f = "";
        int dia, mes, anio;
        Calendar calendario = Calendar.getInstance();

        //if(v.getHoraEntrada()>v.getHoraSalida()){
        calendario.add(Calendar.HOUR_OF_DAY, -5);
        //}
        System.out.println(calendario.get(Calendar.HOUR_OF_DAY) + ":" + calendario.get(Calendar.MINUTE));
        dia = calendario.get(Calendar.DAY_OF_MONTH);
        mes = calendario.get(Calendar.MONTH) + 1;
        anio = calendario.get(Calendar.YEAR);
        f = dia + "/" + mes + "/" + anio;
        return f;
    }

    //********************OBTENER EL TOTAL DE VENTA*************************
    public void obtenerTotal() {

        int totalFilas = this.jTable2.getRowCount();
        double cont = 0;
        for (int i = 0; i < totalFilas; i++) {
            cont = cont + Double.valueOf(this.jTable2.getValueAt(i, 3).toString());
        }
        this.pantTotal.setText(cont + "");

    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            FrameReservas fr = new FrameReservas();
            fr.setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(FrameVentaCaja.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FrameVentaCaja.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        try {
            FrameConfigFactura fcf = new FrameConfigFactura();
            fcf.show(true);
            this.setAdministrador(this.pantAdministrador.getText());
            this.dispose();
        } catch (IOException ex) {
            Logger.getLogger(FrameVentaCaja.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FrameVentaCaja.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void pantCantidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pantCantidadKeyReleased
        /*try {
            if (!this.tipo.equalsIgnoreCase("Cigarrillos")) {
                double t = Double.valueOf(this.pantCantidad.getText()) * Double.valueOf(this.pantDetalles1.getText());
                this.pantStock5.setText(String.valueOf(t));
            } else {
                double t = Double.valueOf(this.pantCantidad.getText());
                this.pantStock5.setText(String.valueOf(t));
            }
            //obtenerVolumenTotalYStock();
        } catch (Exception e) {
            this.pantStock5.setText("Error");
        }*/
    }//GEN-LAST:event_pantCantidadKeyReleased
    //**********************ENVIA EL NOMBRE DEL ADMINISTRADOR DESDE EL LOGIN*************
    public void setAdministrador(String d) {
        this.pantAdministrador.setText(d);
    }

    //************************************************************************
    public void obtenerVolumenTotalYStock() {
        Double total = Double.valueOf(this.pantCantidad.getText());
        Double volumen = Double.valueOf(this.pantDetalles1.getText());
        Double res = total * volumen;
        DecimalFormat df = new DecimalFormat("#.00");
        this.pantStock4.setText(convertirComa(df.format(res)));

        //****STOCK***
        if (this.tipo.equals("Cerveza")) {
            Cerveza cer = this.p.getcCerveza().get(this.posicion);
            int unidadesCaj = cer.getCantidad();
            double volTotal = Double.valueOf(this.pantStock4.getText());
            double volUnidad = cer.getVolumen();

            double cajas = Double.valueOf(convertirComa(df.format(volTotal / (volUnidad * unidadesCaj))));
            int cajasInt = (int) (volTotal / (volUnidad * unidadesCaj));
            this.pantStock1.setText(cajasInt + "");

            double restoCaja = cajas - cajasInt;

            double unidad = Double.valueOf(convertirComa(df.format(restoCaja * unidadesCaj)));
            int unidadInt = (int) unidad;
            this.pantStock2.setText(unidadInt + "");

            double restoUnidad = unidad - unidadInt;
            double volumen1 = restoUnidad * volUnidad;
            this.pantStock3.setText(convertirComa(df.format(volumen1)));

        } else if (this.tipo.equals("Licores")) {
            Licores cer = this.p.getcLicores().get(this.posicion);
            int unidadesCaj = cer.getCantidad();
            double volTotal = Double.valueOf(this.pantStock4.getText());
            double volUnidad = cer.getVolumen();

            double cajas = Double.valueOf(convertirComa(df.format(volTotal / (volUnidad * unidadesCaj))));
            int cajasInt = (int) (volTotal / (volUnidad * unidadesCaj));
            this.pantStock1.setText(cajasInt + "");

            double restoCaja = cajas - cajasInt;

            double unidad = Double.valueOf(convertirComa(df.format(restoCaja * unidadesCaj)));
            int unidadInt = (int) unidad;
            this.pantStock2.setText(unidadInt + "");

            double restoUnidad = unidad - unidadInt;
            double volumen1 = restoUnidad * volUnidad;
            this.pantStock3.setText(convertirComa(df.format(volumen1)));
        } else if (this.tipo.equals("Refrescos")) {
            Refrescos cer = this.p.getcRefrescos().get(this.posicion);
            int unidadesCaj = cer.getCantidad();
            double volTotal = Double.valueOf(this.pantStock4.getText());
            double volUnidad = cer.getVolumen();

            double cajas = Double.valueOf(convertirComa(df.format(volTotal / (volUnidad * unidadesCaj))));
            int cajasInt = (int) (volTotal / (volUnidad * unidadesCaj));
            this.pantStock1.setText(cajasInt + "");

            double restoCaja = cajas - cajasInt;

            double unidad = Double.valueOf(convertirComa(df.format(restoCaja * unidadesCaj)));
            int unidadInt = (int) unidad;
            this.pantStock2.setText(unidadInt + "");

            double restoUnidad = unidad - unidadInt;
            double volumen1 = restoUnidad * volUnidad;
            this.pantStock3.setText(convertirComa(df.format(volumen1)));
        } else if (this.tipo.equals("Cigarrillos")) {
            this.pantStock4.setText(this.pantCantidad.getText());
            Cigarrillos cer = this.p.getcCigarrilos().get(this.posicion);
            int unidadesCaj = cer.getCantidad();
            double volTotal = Double.valueOf(this.pantStock4.getText());

            double cajas = Double.valueOf(convertirComa(df.format(volTotal / (unidadesCaj))));
            int cajasInt = (int) (volTotal / (unidadesCaj));
            this.pantStock1.setText(cajasInt + "");

            double restoCaja = cajas - cajasInt;

            double unidad = Double.valueOf(convertirComa(df.format(restoCaja * unidadesCaj)));
            int unidadInt = (int) unidad;
            this.pantStock2.setText(unidadInt + "");

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

    /*public void generarBruto() {
        String cad = this.pantPrecioTotal.getText();
        if (!cad.equals("")) {
            double compra = Double.valueOf(cad);
            double unidad = Double.valueOf(this.pantCantTotalUd.getText());
            double brut = compra / unidad;
            DecimalFormat df = new DecimalFormat("#.00");
            String res = convertirComa(df.format(brut));
            this.pantPrecioBruto.setText(res);
            this.pantDetalles3.setText(res);
        } else {
            this.pantPrecioTotal.setText("0");
        }
    }*/

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        try {
            frameprincipal_1 ff = new frameprincipal_1();
            //FrameFactura ff = new FrameFactura();
            int max = this.jTable2.getRowCount();
            double[] pu = new double[max];
            double[] pp = new double[max];
            String[] pr = new String[max];
            for (int i = 0; i < max; i++) {
                pu[i] = Double.valueOf(this.jTable2.getValueAt(i, 2).toString());
                pp[i] = Double.valueOf(this.jTable2.getValueAt(i, 3).toString());
                pr[i] = this.jTable2.getValueAt(i, 0).toString();
            }
            ff.setPrecioParcial(pp);
            ff.setPrecioUnitario(pu);
            ff.setProducto(pr);

            ff.setGarzon(this.jComboBox1.getSelectedItem().toString());
            ff.nroFacturaGen();
            ff.setAdministrador(this.pantAdministrador.getText());
            ff.setNroPedido(Integer.valueOf(this.pantNroPedidos.getText()));
            //esFacturado(fecha(), Integer.valueOf(this.pantNroPedidos.getText()));
            ff.generarDatos();

            ff.show(true);
            this.factura = "si";

        } catch (IOException ex) {
            Logger.getLogger(FrameVentaCaja.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FrameVentaCaja.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    //************METODO PARA FACTURAR UNO DE LA TABLA4***************
    public void facturarTabla4(String fecha) throws IOException, ClassNotFoundException {
        //reiniciarTabla4();
        Queue<ListaPedidos> aux = new LinkedList();
        aux.addAll(this.lpm.getLp());

        while (!aux.isEmpty()) {
            ListaPedidos lp = aux.remove();
            if (fecha.equals(lp.getFecha())) {
                Queue<Pedidos> aux2 = new LinkedList();
                aux2.addAll(lp.getP());
                int nro = Integer.valueOf(this.jTable4.getValueAt(this.jTable4.getSelectedRow(), 0).toString());
                Queue<Pedidos> aux3 = new LinkedList();
                aux3.addAll(lp.getP());
                int cont = 0;
                while (!aux3.isEmpty()) {
                    Pedidos p = aux3.remove();
                    if (nro == p.getNroPedidos()) {

                        cont++;
                    }
                }

                frameprincipal_1 ff = new frameprincipal_1();
                int max = cont;

                double[] pu = new double[max];
                double[] pp = new double[max];
                String[] pr = new String[max];
                int i = 0;
                while (!aux2.isEmpty()) {
                    Pedidos p = aux2.remove();
                    if (nro == p.getNroPedidos()) {

                        pu[i] = p.getPrecioUnitario();
                        pp[i] = p.getPrecioParcial();
                        pr[i] = p.getProducto();

                        ff.setAdministrador(p.getAdministrador());

                        //CAMBIAR LAS FACTURAS A SI CREAR ESE METODO
                        i++;
                    }
                }
                ff.setPrecioParcial(pp);
                ff.setPrecioUnitario(pu);
                ff.setProducto(pr);
                ff.setGarzon(this.jComboBox1.getSelectedItem().toString());
                ff.nroFacturaGen();
                ff.setNroPedido(Integer.valueOf(nro + ""));
                //esFacturado(fecha(), nro);
                ff.generarDatos();
                ff.show(true);
                //p.setFactura("si");

            }
        }
    }

    public void esFacturado(String fecha, int nro) {
        ListaPedidosMes a = new ListaPedidosMes();
        while (!this.lpm.getLp().isEmpty()) {
            ListaPedidos lp = lpm.getLp().remove();
            while (!lp.getP().isEmpty()) {
                Pedidos p = lp.getP().remove();
                if (p.getNroPedidos() == nro && fecha.equals(p.getFecha())) {
                    p.setFactura("si");
                }
                a.addEnLaFecha(p, p.getFecha());
            }
        }
        this.lpm = a;
        this.lpm.guardar(lpm);
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            FrameMesas fm = new FrameMesas();
            fm.show(true);
        } catch (IOException ex) {
            Logger.getLogger(FrameVentaCaja.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FrameVentaCaja.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        entregarPedido();
        factura = "no";
        this.jButton3.setVisible(true);
        this.jButton4.setVisible(false);
        this.jButton5.setVisible(false);
        this.fila = 0;
        reiniciarTabla2();
        JOptionPane.showMessageDialog(null, "Se entrego correctamente");
        mostrarPedidoTabla4(fecha());
        this.cf.setNroPedido(this.cf.getNroPedido() + 1);
        this.cf.guardar(cf);
        this.suma = 0;
        this.p.guardar(p);

    }//GEN-LAST:event_jButton5ActionPerformed


    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        //AnimationClass sc = new AnimationClass();
        //System.out.println(this.PanelMesas.getX());
        //sc.jTextAreaYUp(this.jScrollPane4.getY(), 310, 10, 10, this.jScrollPane4);//.jTextAreaXLeft(this.scrollAddProducto.getX(), 300, 10, 10, this.scrollAddProducto);
        //this.jScrollPane4.setBounds(390, 310, 630, 370);
        this.jScrollPane4.setVisible(true);
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        //AnimationClass sc = new AnimationClass();
        //System.out.println(this.PanelMesas.getX());
        //sc.jTextAreaYDown(this.jScrollPane4.getY(), 680, 10, 10, this.jScrollPane4);
        //this.jScrollPane4.setBounds(390, 680, 630, 370);
        this.jScrollPane4.setVisible(false);

    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        try {
            FramePrincipal a = new FramePrincipal();
            a.show(true);

            this.dispose();
        } catch (IOException ex) {
            Logger.getLogger(FrameLogin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FrameLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.jButton4.setVisible(true);
        this.jButton5.setVisible(true);
        this.jButton3.setVisible(false);
        /*this.cf.setNroPedido(this.cf.getNroPedido() + 1);
        this.cf.guardar(cf);*/
        this.pantNroPedidos.setText(this.cf.getNroPedido() + "");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        //this.jTable2.
        try {
            int fila = this.jTable2.getSelectedRow();
            this.p.devolverProducto(Integer.valueOf(this.jTable2.getValueAt(fila, 1).toString()), this.jTable2.getValueAt(fila, 0).toString());
            mostrarDatos();
            Queue<Double> aux = new LinkedList();
            Queue<Double> aux2 = new LinkedList();
            int max = this.volumenes.size();
            for (int i = 0; i < max; i++) {
                double s = this.volumenes.remove();

                if (i == jTable2.getSelectedRow()) {
                    this.suma = this.suma - s;
                    this.pantStock5.setText(suma + "");
                } else {
                    aux.add(s);
                    aux2.add(this.volumenUnidad.remove());
                }
            }

            this.volumenes.addAll(aux);
            DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
            //model.addRow(new Object[]{""});
            model.removeRow(jTable2.getSelectedRow());
            //this.suma=suma-volumenes;

            this.fila = this.fila - 1;
            obtenerTotal();
            detUnidades();
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        try {
            facturarTabla4(fecha());
            mostrarPedidoTabla4(fecha());
        } catch (IOException ex) {
            Logger.getLogger(FrameVentaCaja.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FrameVentaCaja.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton12ActionPerformed

    private void BVerFotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BVerFotoActionPerformed
        if (imagen != null) {
            FrameVerFoto vf = new FrameVerFoto();
            try {
                vf.cargarImagen(imagen);
                vf.show(true);
            } catch (IOException ex) {
                Logger.getLogger(FrameProductos.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "ESTE PRODUCTO NO TIENE FOTO");
        }
    }//GEN-LAST:event_BVerFotoActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked

        int fila = this.jTable2.getSelectedRow();
        this.pantCantidad.setText(this.jTable2.getValueAt(fila, 1).toString());
        obtenerUnidadVolumen();
    }//GEN-LAST:event_jTable2MouseClicked

    public void obtenerUnidadVolumen() {
        int fila = this.jTable2.getSelectedRow();
        int i = 0;
        Queue<Double> aux = new LinkedList();
        aux.addAll(this.volumenUnidad);
        while (!aux.isEmpty()) {
            double s = aux.remove();
            if (fila == i) {
                this.pantDetalles1.setText(s + "");
            }
            i++;
        }
    }

    public double volumenAUnitario() {

        int fila = this.jTable2.getSelectedRow();
        Queue<Double> aux = new LinkedList();
        aux.addAll(this.volumenUnidad);
        int i = 0;
        while (!aux.isEmpty()) {
            double s = aux.remove();
            //this.suma = suma + s;
            if (i == fila) {
                return s;

            }
            i++;
        }
        return 0;
    }


    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        suma = 0;
        int fila = this.jTable2.getSelectedRow();
        Queue<Double> aux = new LinkedList();
        Queue<Double> aux2 = new LinkedList();
        aux2.addAll(this.volumenUnidad);
        //aux.addAll(this.volumenes);
        int i = 0;

        while (!this.volumenes.isEmpty()) {
            //double s=this.volumenes.remove();
            double vu = aux2.remove();
            if (i == fila) {
                double s = this.volumenes.remove();
                this.suma = suma - s;
                double par = Integer.valueOf(this.pantCantidad.getText()) * vu;
                aux.add(par);
                suma = suma + par;
            } else {
                aux.add(this.volumenes.remove());
            }
            i++;
        }
        this.volumenes.addAll(aux);
        this.pantStock5.setText(suma + "");
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jTable4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable4MouseClicked

        //this.lpm.guardar(lpm);
    }//GEN-LAST:event_jTable4MouseClicked

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        obtenerDeTabla4Datos(fecha());
        fila = this.jTable2.getRowCount();
        this.jButton4.setVisible(true);
        mostrarPedidoTabla4(fecha());
        jButton14.setVisible(true);
        this.jButton13.setVisible(false);
        jButton3.setVisible(false);
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        entregarPedido();
        factura = "no";
        this.jButton3.setVisible(true);
        this.jButton4.setVisible(false);
        this.jButton5.setVisible(false);
        this.fila = 0;
        reiniciarTabla2();
        JOptionPane.showMessageDialog(null, "Se entrego correctamente");

        //this.cf.setNroPedido(this.cf.getNroPedido() + 1);
        this.cf.guardar(cf);
        this.suma = 0;
        mostrarPedidoTabla4(fecha());
        this.p.guardar(p);
        jButton14.setVisible(false);
        jButton13.setVisible(true);
        jButton3.setVisible(true);
    }//GEN-LAST:event_jButton14ActionPerformed

    private boolean tecla = false;
    TecladoVirtual tv;
    JPopupMenu pop;
    private void pantCantidadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pantCantidadMouseClicked

        if (tecla) {
            tv = new TecladoVirtual(this.pantCantidad);
            pop = new JPopupMenu();
            pop.add(tv);
            pop.setVisible(tecla);
            pop.setLocation(pantCantidad.getLocationOnScreen().x + 112, pantCantidad.getLocationOnScreen().y - 1);
        }

    }//GEN-LAST:event_pantCantidadMouseClicked

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        tecla = this.jToggleButton1.isSelected();
        try {
            pop.setVisible(false);
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        
        FrameMusica vp;
        try {
            vp = new FrameMusica();
            vp.show(true);
        } catch (IOException ex) {
            Logger.getLogger(FrameVentaCaja.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FrameVentaCaja.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jButton16ActionPerformed

    //*********************MOSTRAR PEDIDOS ENTREGADOS PARA SU EDICION********************
    public void obtenerDeTabla4Datos(String fech) {
        int fila = this.jTable4.getSelectedRow();
        int nroPedido = Integer.valueOf(this.jTable4.getValueAt(fila, 0).toString());
        this.pantNroPedidos.setText(nroPedido + "");
        Queue<ListaPedidos> aux = new LinkedList();
        //aux.addAll(this.lpm.getLp());
        Pedidos[] pep = this.lpm.removerPedidos(nroPedido, fech);
        reiniciarTabla2();
        for (int i = 0; i < pep.length; i++) {
            System.out.println(pep[i].getProducto());

            try {
                //int fila = this.jTable1.getSelectedRow();
                agregarFilaTabla2();
                this.jTable2.setValueAt(pep[i].getProducto(), i, 0);
                this.jTable2.setValueAt(pep[i].getCantidad(), i, 1);
                this.jTable2.setValueAt(pep[i].getPrecioUnitario(), i, 2);
                this.jTable2.setValueAt(pep[i].getPrecioParcial(), i, 3);
                this.jTable2.setValueAt(pep[i].getLugar(), i, 4);

            } catch (Exception e) {
                System.out.println(("Error: " + e));
                JOptionPane.showMessageDialog(null, "Seleccione un producto de la tabla de productos");
            }
        }
    }

    //*********************ENTREGAR PEDIDO*******************************
    private String factura = "no";

    public void entregarPedido() {
        int cant = this.jTable2.getRowCount();
        String garzo = this.jComboBox1.getSelectedItem().toString();
        String admin = this.pantAdministrador.getText();

        for (int i = 0; i < cant; i++) {
            Pedidos p = new Pedidos();
            p.setNroPedidos(Integer.valueOf(this.pantNroPedidos.getText()));
            p.setGarzon(garzo);
            p.setAdministrador(admin);
            p.setLugar(this.jTable2.getValueAt(i, 4).toString());
            p.setPrecioParcial(Double.valueOf(this.jTable2.getValueAt(i, 3).toString()));
            p.setPrecioUnitario(Double.valueOf(this.jTable2.getValueAt(i, 2).toString()));
            p.setCantidad(Integer.valueOf(this.jTable2.getValueAt(i, 1).toString()));
            p.setProducto(this.jTable2.getValueAt(i, 0).toString());
            p.setFecha(fecha2());
            p.setHora(hora());
            p.setFactura(factura);
            this.lpm.addEnLaFecha(p, fecha());
        }
        this.lpm.guardar(lpm);
    }

    //mostrar pedido EN TABLA 4*************************
    public static void tabla4(String fecha, ListaPedidosMes lpm) {
        Queue<ListaPedidos> aux = new LinkedList();
        aux.addAll(lpm.getLp());
        while (!aux.isEmpty()) {
            ListaPedidos lp = aux.remove();
            if (fecha.equals(lp.getFecha())) {
                Queue<Pedidos> aux2 = new LinkedList();
                aux2.addAll(lp.getP());
                int nro = -1;
                int cont = 0;
                while (!aux2.isEmpty()) {
                    Pedidos p = aux2.remove();
                    if (nro != p.getNroPedidos()) {
                        FrameVentaCaja.jTable4.setValueAt(p.getNroPedidos() + "", cont, 0);
                        FrameVentaCaja.jTable4.setValueAt(p.getFactura(), cont, 1);
                        nro = p.getNroPedidos();
                        cont++;
                    }
                }

            }
        }
    }

    public void mostrarPedidoTabla4(String fecha) {
        reiniciarTabla4();
        Queue<ListaPedidos> aux = new LinkedList();
        aux.addAll(this.lpm.getLp());
        while (!aux.isEmpty()) {
            ListaPedidos lp = aux.remove();
            if (fecha.equals(lp.getFecha())) {
                Queue<Pedidos> aux2 = new LinkedList();
                aux2.addAll(lp.getP());
                int nro = -1;
                int cont = 0;
                while (!aux2.isEmpty()) {
                    Pedidos p = aux2.remove();
                    if (nro != p.getNroPedidos()) {
                        Generatabla4();
                        this.jTable4.setValueAt(p.getNroPedidos() + "", cont, 0);
                        this.jTable4.setValueAt(p.getFactura(), cont, 1);
                        nro = p.getNroPedidos();
                        cont++;
                    }
                }

            }
        }
    }

    public void reiniciarTabla4() {
        jTable4.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "# de pedido", "Factura"
                }
        ));
    }

    public void Generatabla4() {
        DefaultTableModel model = (DefaultTableModel) jTable4.getModel();
        model.addRow(new Object[]{""});
    }
    //*********************************************************

    public String hora() {
        String fe = "";
        int hora, minutos;
        Calendar calendario = Calendar.getInstance();
        hora = calendario.get(Calendar.HOUR);
        minutos = calendario.get(Calendar.MINUTE);
        fe = hora + ":" + minutos;
        return fe;
    }

    //*******************************************************************
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
            java.util.logging.Logger.getLogger(FrameVentaCaja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameVentaCaja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameVentaCaja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameVentaCaja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new FrameVentaCaja().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(FrameVentaCaja.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(FrameVentaCaja.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(FrameVentaCaja.class.getName()).log(Level.SEVERE, null, ex);
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
    private javax.swing.JButton BVerFoto;
    private javax.swing.JPanel PanelCocteles;
    private javax.swing.JPanel PanelMesas;
    private javax.swing.JTextField detalleCoctel1;
    private javax.swing.JTextField detalleCoctel2;
    private javax.swing.JTextField detalleCoctel3;
    private javax.swing.JTextField detalleVol1;
    private javax.swing.JTextField detalleVol2;
    private javax.swing.JTextField detalleVol3;
    private javax.swing.JTextField detallesPrecio;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
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
    private static javax.swing.JTable jTable4;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JTextField pantAdministrador;
    private javax.swing.JTextField pantCantidad;
    private javax.swing.JTextField pantDetalles1;
    private javax.swing.JTextField pantDetalles2;
    private javax.swing.JTextField pantDetalles3;
    private javax.swing.JTextField pantDetalles4;
    private javax.swing.JTextField pantLugar;
    private javax.swing.JTextField pantNroPedidos;
    private javax.swing.JTextField pantStock1;
    private javax.swing.JTextField pantStock2;
    private javax.swing.JTextField pantStock3;
    private javax.swing.JTextField pantStock4;
    private javax.swing.JTextField pantStock5;
    private javax.swing.JTextField pantTotal;
    // End of variables declaration//GEN-END:variables
}
