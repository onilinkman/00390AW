/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg00390aw;

import AppPackage.AnimationClass;
import java.awt.Color;
import java.awt.Panel;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author WINDOWS 10
 */
public class FrameProductos extends javax.swing.JFrame {

    /**
     * Creates new form FrameProductos
     */
    public FrameProductos() throws IOException, ClassNotFoundException {
        initComponents();
        //dimensionarTabla();
        this.jProgressBar1.setMaximum(10000);
        this.jProgressBar2.setMaximum(10000);
        this.jProgressBar3.setMaximum(10000);
        this.jProgressBar4.setMaximum(50);
        this.jButton9.setText("<html>AGREGAR O ELIMINAR PRODUCTO</html>");
        this.jButton10.setText("<html>AGREGAR PROVEEDOR</html>");
        this.setLocationRelativeTo(null);
        File f = new File("datosProductos.dat");
        if (f.exists()) {
            this.p = this.p.recuperar();
            //agregarProducto();
            //llenarTabla1();
            llenarTabla1();
            reiniciarCombosBoxCoctel();
            llenarCombosBoxCoctel();
            //detUnidades();
        }

        crearComboBox1();
        this.pantFecha.setText(fecha());

        this.lsd = new ListaStockDatos();

        File f1 = new File("ListaStockDatos.dat");
        if (f1.exists()) {
            this.lsd = lsd.recuperar();
            llenarTabla2();
        }
        File fi = new File("FotoProducto.png");
        boolean sw = fi.delete();
        System.out.println(sw);
        ocultarBotones();
        this.jButton17.setVisible(true);
        this.jButton19.setVisible(false);
        this.jButton18.setVisible(false);
        jButton29.setVisible(false);
        jButton23.setVisible(false);
        jButton28.setVisible(false);
        jButton25.setVisible(false);

    }

    //*************preparar para ingresar OTROS*********************************
    public void habilitarOtros() {
        this.pantNomProducto.setEditable(true);
        this.pantVol.setEditable(false);
        this.pantUnidades.setEditable(false);
        this.pantPrecioUnitario.setEditable(false);

        this.pantNomProducto.setBackground(new Color(255, 255, 255));
        this.pantVol.setBackground(new Color(153, 153, 153));
        this.pantUnidades.setBackground(new Color(153, 153, 153));
        this.pantPrecioUnitario.setBackground(new Color(153, 153, 153));

        this.jLabel29.setText("Nombre de producto");
        this.jLabel30.setText("Vol.[cc]/ud");
        this.jLabel31.setText("uds./caja");
        this.jLabel32.setText("Precio de venta unitario");

        this.pantnomCocteles.setEditable(false);
        this.pantnomCocteles.setBackground(new Color(153, 153, 153));
        this.pantPrecioUnCoctel.setEditable(false);
        this.pantPrecioUnCoctel.setBackground(new Color(153, 153, 153));
        this.pantVolCoc1.setEditable(false);
        this.pantVolCoc1.setBackground(new Color(153, 153, 153));
        this.pantVolCoc2.setEditable(false);
        this.pantVolCoc2.setBackground(new Color(153, 153, 153));
        this.pantVolCoc3.setEditable(false);
        this.pantVolCoc3.setBackground(new Color(153, 153, 153));
        this.pantVolCoc4.setEditable(false);
        this.pantVolCoc4.setBackground(new Color(153, 153, 153));
    }

    //*************prepara para el ingreso de Cocteles*****************************************
    public void habilitarCocteles() {
        this.pantNomProducto.setEditable(false);
        this.pantVol.setEditable(false);
        this.pantUnidades.setEditable(false);
        this.pantPrecioUnitario.setEditable(false);

        this.pantNomProducto.setBackground(new Color(153, 153, 153));
        this.pantVol.setBackground(new Color(153, 153, 153));
        this.pantUnidades.setBackground(new Color(153, 153, 153));
        this.pantPrecioUnitario.setBackground(new Color(153, 153, 153));

        this.jLabel29.setText("Nombre de producto");
        this.jLabel30.setText("Vol.[cc]/ud");
        this.jLabel31.setText("uds./caja");
        this.jLabel32.setText("Precio de venta unitario");

        this.pantnomCocteles.setEditable(true);
        this.pantnomCocteles.setBackground(new Color(255, 255, 255));
        this.pantPrecioUnCoctel.setEditable(true);
        this.pantPrecioUnCoctel.setBackground(new Color(255, 255, 255));
        this.pantVolCoc1.setEditable(true);
        this.pantVolCoc1.setBackground(new Color(255, 255, 255));
        this.pantVolCoc2.setEditable(true);
        this.pantVolCoc2.setBackground(new Color(255, 255, 255));
        this.pantVolCoc3.setEditable(true);
        this.pantVolCoc3.setBackground(new Color(255, 255, 255));
        this.pantVolCoc4.setEditable(true);
        this.pantVolCoc4.setBackground(new Color(255, 255, 255));

        this.jLabel9.setVisible(true);
        this.pantStock3.setVisible(true);

        this.jLabel10.setText("Volumen total");
    }

    //*************Prepara para el ingreso DE CIGARRILLOS**************************************
    public void habilitarCigarrillo() {
        this.pantNomProducto.setEditable(true);
        this.pantVol.setEditable(false);
        this.pantUnidades.setEditable(true);
        this.pantPrecioUnitario.setEditable(true);

        this.pantNomProducto.setBackground(new Color(255, 255, 255));
        this.pantVol.setBackground(new Color(153, 153, 153));
        this.pantUnidades.setBackground(new Color(255, 255, 255));
        this.pantPrecioUnitario.setBackground(new Color(255, 255, 255));

        this.jLabel29.setText("Nombre de producto");
        this.jLabel30.setText("Vol.[cc]/ud");
        this.jLabel31.setText("uds./caja");
        this.jLabel32.setText("Precio de venta unitario");

        this.pantnomCocteles.setEditable(false);
        this.pantnomCocteles.setBackground(new Color(153, 153, 153));
        this.pantPrecioUnCoctel.setEditable(false);
        this.pantPrecioUnCoctel.setBackground(new Color(153, 153, 153));
        this.pantVolCoc1.setEditable(false);
        this.pantVolCoc1.setBackground(new Color(153, 153, 153));
        this.pantVolCoc2.setEditable(false);
        this.pantVolCoc2.setBackground(new Color(153, 153, 153));
        this.pantVolCoc3.setEditable(false);
        this.pantVolCoc3.setBackground(new Color(153, 153, 153));
        this.pantVolCoc4.setEditable(false);
        this.pantVolCoc4.setBackground(new Color(153, 153, 153));

        this.jLabel9.setVisible(false);
        this.pantStock3.setVisible(false);

        this.jLabel10.setText("Total Cigarrillos");
    }

    //*************Prepara para el ingreso de datos PARA CERVEZA LICORES REFRESCOS************153
    public void habilitarCLR() {
        this.pantNomProducto.setEditable(true);
        this.pantVol.setEditable(true);
        this.pantUnidades.setEditable(true);
        this.pantPrecioUnitario.setEditable(true);

        this.pantNomProducto.setBackground(new Color(255, 255, 255));
        this.pantVol.setBackground(new Color(255, 255, 255));
        this.pantUnidades.setBackground(new Color(255, 255, 255));
        this.pantPrecioUnitario.setBackground(new Color(255, 255, 255));

        this.jLabel29.setText("Nombre de producto");
        this.jLabel30.setText("Vol.[cc]/ud");
        this.jLabel31.setText("uds./caja");
        this.jLabel32.setText("Precio de venta unitario");

        this.pantnomCocteles.setEditable(false);
        this.pantnomCocteles.setBackground(new Color(153, 153, 153));
        this.pantPrecioUnCoctel.setEditable(false);
        this.pantPrecioUnCoctel.setBackground(new Color(153, 153, 153));
        this.pantVolCoc1.setEditable(false);
        this.pantVolCoc1.setBackground(new Color(153, 153, 153));
        this.pantVolCoc2.setEditable(false);
        this.pantVolCoc2.setBackground(new Color(153, 153, 153));
        this.pantVolCoc3.setEditable(false);
        this.pantVolCoc3.setBackground(new Color(153, 153, 153));
        this.pantVolCoc4.setEditable(false);
        this.pantVolCoc4.setBackground(new Color(153, 153, 153));

        this.jLabel9.setVisible(true);
        this.pantStock3.setVisible(true);

        this.jLabel10.setText("Volumen total");

    }

    public void llenarDeCero() {
        this.pantVol.setText("");
        this.pantUnidades.setText("");
        this.pantPrecioUnitario.setText("");

        this.pantnomCocteles.setText("");
        this.pantPrecioUnCoctel.setText("");
        this.pantVolCoc1.setText("");
        this.pantVolCoc2.setText("");
        this.pantVolCoc3.setText("");
        this.pantVolCoc4.setText("");

        this.pantNomProducto.setText("");
        this.pantnomCocteles.setText("");
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
        this.pantCantCajas.setText("0");
        this.pantCantUnidades.setText("0");
        this.pantCantTotalUd.setText("0");
        this.pantPrecioBruto.setText("0");
        this.pantPrecioCaja.setText("0");
        this.pantPrecioTotal.setText("0");
        this.BVerFoto.setIcon(null);
        this.imagen = null;
        this.bonificacion = false;
    }

    private ListaStockDatos lsd;

    //********************
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

    //********************Crear Combo Box de los proveedores************************************
    public void crearComboBox1() {
        NodoRegistroCliente w = FramePrincipal.cp.getLrc().getP();
        while (w != null) {
            this.jComboBox1.addItem(w.getRegistro().getNombre());
            w = w.getSig();
        }
    }
//***********************************************************************************
    private Productos p = new Productos();
    private String tipo = "";
    private String dirfoto = "";

    public void dimensionarTabla() {
        JTableHeader jth = new JTableHeader();
        this.jTable2.setTableHeader(jth);
    }

    //******************DETERMINAR EL TAMANIO DE LASUNIDADES********************
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

    //******************Llenado de datos a las ventanas mediante la tabla********
    private int posicion;

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
            this.pantNomProducto.setText(cerv.getNomProducto());
            this.pantVol.setText(cerv.getVolumen() + "");
            this.pantUnidades.setText(cerv.getCantidad() + "");
            this.pantPrecioUnitario.setText(cerv.getPrecio() + "");
            this.pantStock1.setText(cerv.getStockAlmacen().getStockCajas() + "");
            this.pantStock2.setText(cerv.getStockAlmacen().getStockUnidades() + "");
            this.pantStock3.setText(cerv.getStockAlmacen().getStockVolumen() + "");
            this.pantStock4.setText(cerv.getStockAlmacen().getStockVolTotal() + "");
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
            this.pantNomProducto.setText(lic.getNomProducto());
            this.pantVol.setText(lic.getVolumen() + "");
            this.pantUnidades.setText(lic.getCantidad() + "");
            this.pantPrecioUnitario.setText(lic.getPrecio() + "");

            this.pantStock1.setText(lic.getStockAlmacen().getStockCajas() + "");
            this.pantStock2.setText(lic.getStockAlmacen().getStockUnidades() + "");
            this.pantStock3.setText(lic.getStockAlmacen().getStockVolumen() + "");
            this.pantStock4.setText(lic.getStockAlmacen().getStockVolTotal() + "");
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
            this.pantNomProducto.setText(lic.getNomProducto());
            this.pantVol.setText(lic.getVolumen() + "");
            this.pantUnidades.setText(lic.getCantidad() + "");
            this.pantPrecioUnitario.setText(lic.getPrecio() + "");

            this.pantStock1.setText(lic.getStockAlmacen().getStockCajas() + "");
            this.pantStock2.setText(lic.getStockAlmacen().getStockUnidades() + "");
            this.pantStock3.setText(lic.getStockAlmacen().getStockVolumen() + "");
            this.pantStock4.setText(lic.getStockAlmacen().getStockVolTotal() + "");
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

            pantnomCocteles.setText(cc.getNomProducto());
            pantPrecioUnCoctel.setText(cc.getPrecio() + "");
            pantVolCoc1.setText(cc.getVolumen1() + "");
            pantVolCoc2.setText(cc.getVolumen2() + "");
            pantVolCoc3.setText(cc.getVolumen3() + "");
            pantVolCoc4.setText(cc.getVolumen4() + "");
            comboCerveza.setSelectedItem(cc.getBebida1());
            System.out.println(cc.getBebida1());
            comboLicor1.setSelectedItem(cc.getBebida2());
            comboLicor2.setSelectedItem(cc.getBebida3());
            comboRefrescos.setSelectedItem(cc.getBebida4());

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
            this.pantNomProducto.setText(ci.getNomProducto());
            this.pantVol.setText("");
            this.pantUnidades.setText(ci.getCantidad() + "");
            this.pantPrecioUnitario.setText(ci.getPrecio() + "");

            this.pantStock1.setText(ci.getStockAlmacen().getStockCajasCigarro() + "");
            this.pantStock2.setText(ci.getStockAlmacen().getStockUnidadesCigarros() + "");
            this.pantStock4.setText(ci.getStockAlmacen().getStockTotalCigarro() + "");
            this.jProgressBar4.setValue(ci.getStockAlmacen().getStockTotalCigarro());
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
            this.pantNomProducto.setText(o.getNomProducto());
        } else {
            JOptionPane.showMessageDialog(null, "Presione un boton del producto que desea registrar");

        }
    }

    //******************LLENAR COMBO BOX DE LOS COCTELES*****************//
    public void reiniciarCombosBoxCoctel() {
        this.comboCerveza.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Vacio"}));
        this.comboLicor1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Vacio"}));
        this.comboLicor2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Vacio"}));
        this.comboRefrescos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Vacio"}));
    }

    public void llenarCombosBoxCoctel() {
        Stack<Cerveza> a = new Stack<Cerveza>();
        a.addAll(this.p.getcCerveza());
        Stack<Cerveza> aux = new Stack<Cerveza>();
        int cont = 0;
        while (!a.isEmpty()) {
            //Generatabla1();
            Cerveza c = (Cerveza) a.pop();
            //this.jTable1.setValueAt(c.getNomProducto(), cont, 0);
            this.comboCerveza.addItem(c.getNomProducto());
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
            //Generatabla1();
            Licores licor = (Licores) l.pop();
            this.comboLicor1.addItem(licor.getNomProducto());
            this.comboLicor2.addItem(licor.getNomProducto());
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
            //Generatabla1();
            Refrescos ref = (Refrescos) r.pop();
            this.comboRefrescos.addItem(ref.getNomProducto());
            cont++;
            auxr.add(ref);
        }
        r.addAll(auxr);
    }

    //******************AGREGAR PRODUCTO A LA LISTA**********************
    public void agregarProducto() {
        if (this.tipo.equals("Cerveza")) {
            Cerveza cerveza = new Cerveza();
            cerveza.setTipo(tipo);
            cerveza.setFoto(imagen);
            cerveza.setNomProducto(this.pantNomProducto.getText());
            cerveza.setVolumen(Double.valueOf(this.pantVol.getText()));
            cerveza.setCantidad(Integer.valueOf(this.pantUnidades.getText()));
            cerveza.setPrecio(Double.valueOf(this.pantPrecioUnitario.getText()));
            this.p.getcCerveza().push(cerveza);
        } else if (this.tipo.equals("Licores")) {
            Licores licores = new Licores();
            licores.setTipo(tipo);
            licores.setFoto(imagen);
            licores.setNomProducto(this.pantNomProducto.getText());
            licores.setVolumen(Double.valueOf(this.pantVol.getText()));
            licores.setCantidad(Integer.valueOf(this.pantUnidades.getText()));
            licores.setPrecio(Double.valueOf(this.pantPrecioUnitario.getText()));
            this.p.getcLicores().push(licores);
        } else if (this.tipo.equals("Refrescos")) {
            Refrescos refrescos = new Refrescos();
            refrescos.setTipo(tipo);
            refrescos.setFoto(imagen);
            refrescos.setNomProducto(this.pantNomProducto.getText());
            refrescos.setVolumen(Double.valueOf(this.pantVol.getText()));
            refrescos.setCantidad(Integer.valueOf(this.pantUnidades.getText()));
            refrescos.setPrecio(Double.valueOf(this.pantPrecioUnitario.getText()));
            this.p.getcRefrescos().push(refrescos);
        } else if (this.tipo.equals("Cocteles")) {
            //inabilitar pantallas y botones y activar los de cocteles
            /*Cocteles cocteles=new Cocteles();
            cocteles.setTipo(tipo);
            cocteles.setNomProducto(this.pantnomCocteles.getText());
            cocteles.setPrecio(Double.valueOf(this.pantPrecioUnCoctel.getText()));
            cocteles.setBebida1(this.comboCerveza.getSelectedItem().toString());
            cocteles.setBebida2(this.comboLicor1.getSelectedItem().toString());
            cocteles.setBebida3(this.comboLicor2.getSelectedItem().toString());
            cocteles.setBebida4(this.comboRefrescos.getSelectedItem().toString());
            cocteles.setVolumen1(Double.valueOf(this.pantVolCoc1.getText()));
            cocteles.setVolumen2(Double.valueOf(this.pantVolCoc2.getText()));
            cocteles.setVolumen3(Double.valueOf(this.pantVolCoc3.getText()));
            cocteles.setVolumen4(Double.valueOf(this.pantVolCoc4.getText()));
            this.p.getcCocteles().push(cocteles);*/

        } else if (this.tipo.equals("Cigarrillos")) {
            Cigarrillos cigarrillos = new Cigarrillos();
            cigarrillos.setTipo(tipo);
            cigarrillos.setFoto(imagen);
            cigarrillos.setNomProducto(this.pantNomProducto.getText());
            cigarrillos.setCantidad(Integer.valueOf(this.pantUnidades.getText()));
            cigarrillos.setPrecio(Double.valueOf(this.pantPrecioUnitario.getText()));
            this.p.getcCigarrilos().push(cigarrillos);
        } else if (this.tipo.equals("Otros")) {
            Otros otrs = new Otros();
            otrs.setNomProducto(this.pantNomProducto.getText());
            otrs.setFoto(imagen);
            this.p.getcOtros().push(otrs);
        } else {
            JOptionPane.showMessageDialog(null, "Presione un boton del producto que desea registrar");
        }
    }

    //******************LLENAR LISTA DE PRODUCTOS*************************
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

    //******************LIMPIAR VENTANAS DE AGREGAR Y ELIMINAR***********
    public void limpiarVentanasAdd() {
        this.pantNomProducto.setText("");
        this.pantPrecioUnitario.setText("");
        this.pantUnidades.setText("");
    }

    public void limpiarVentanasAddCocte() {
        this.pantnomCocteles.setText("");
        this.pantPrecioUnCoctel.setText("");
        this.pantVolCoc1.setText("");
        this.pantVolCoc2.setText("");
        this.pantVolCoc3.setText("");
        this.pantVolCoc4.setText("");

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jMenuItem1 = new javax.swing.JMenuItem();
        scrollAddProducto = new javax.swing.JScrollPane();
        jPanel6 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        pantNomProducto = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        pantVol = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        pantUnidades = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        pantPrecioUnitario = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jLabel28 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        pantnomCocteles = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        pantPrecioUnCoctel = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        comboCerveza = new javax.swing.JComboBox<>();
        jLabel36 = new javax.swing.JLabel();
        comboLicor1 = new javax.swing.JComboBox<>();
        jLabel37 = new javax.swing.JLabel();
        comboLicor2 = new javax.swing.JComboBox<>();
        jLabel38 = new javax.swing.JLabel();
        comboRefrescos = new javax.swing.JComboBox<>();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jLabel39 = new javax.swing.JLabel();
        pantVolCoc1 = new javax.swing.JTextField();
        pantVolCoc2 = new javax.swing.JTextField();
        pantVolCoc3 = new javax.swing.JTextField();
        pantVolCoc4 = new javax.swing.JTextField();
        jButton23 = new javax.swing.JButton();
        jButton26 = new javax.swing.JButton();
        jButton27 = new javax.swing.JButton();
        jButton28 = new javax.swing.JButton();
        jButton29 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
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
        jLabel47 = new javax.swing.JLabel();
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
        BOtros = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
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
        jPanel4 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        pantCantCajas = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        pantCantUnidades = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        pantCantTotalUd = new javax.swing.JTextField();
        pantFecha = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jLabel17 = new javax.swing.JLabel();
        pantPrecioCaja = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        pantPrecioBruto = new javax.swing.JTextField();
        pantPrecioTotal = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        jButton24 = new javax.swing.JButton();
        jButton25 = new javax.swing.JButton();
        BVerFoto = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(930, 690));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setText("CERRAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 390, -1, -1));

        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel29.setText("Nombre de producto");
        jPanel7.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, -1, -1));
        jPanel7.add(pantNomProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, 130, -1));

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel30.setText("Vol.[cc]/ud");
        jPanel7.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));
        jPanel7.add(pantVol, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 100, -1));

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel31.setText("uds./caja");
        jPanel7.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 60, -1, -1));
        jPanel7.add(pantUnidades, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 80, 100, -1));

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel32.setText("Precio de venta unitario");
        jPanel7.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, 150, -1));
        jPanel7.add(pantPrecioUnitario, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 130, 80, -1));

        jButton2.setText("ELIMINAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel7.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 200, -1, -1));

        jButton3.setText("ACEPTAR EDICION");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel7.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, -1, -1));

        jButton4.setText("AGREGAR");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel7.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 100, 30));

        jButton19.setText("CANCELAR");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });
        jPanel7.add(jButton19, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 160, 110, 30));

        jButton17.setText("NUEVO");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });
        jPanel7.add(jButton17, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 160, 140, 30));

        jButton18.setText("EDITAR");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });
        jPanel7.add(jButton18, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 200, -1, 30));

        jPanel6.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 280, 240));

        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel28.setText("AGREGAR PRODUCTO");
        jPanel6.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 20, -1, -1));

        jButton8.setText("TOMAR FOTO DEL PRODUCTO");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 330, -1, 70));

        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel33.setText("Nombre del coctel");
        jPanel8.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, -1));
        jPanel8.add(pantnomCocteles, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 120, -1));

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel34.setText("Precio de venta unitario");
        jPanel8.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, 140, -1));
        jPanel8.add(pantPrecioUnCoctel, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 30, 100, -1));

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel35.setText("Eleccion de creveza");
        jPanel8.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        comboCerveza.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel8.add(comboCerveza, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 110, -1));

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel36.setText("Eleccion de Licor");
        jPanel8.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 110, 20));

        comboLicor1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel8.add(comboLicor1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 110, -1));

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel37.setText("Eleccion de Licor");
        jPanel8.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 110, 20));

        comboLicor2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel8.add(comboLicor2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 110, -1));

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel38.setText("Eleccion de Refresco");
        jPanel8.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 130, 20));

        comboRefrescos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel8.add(comboRefrescos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 110, -1));

        jButton11.setText("ACEPTAR EDICION");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jPanel8.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 150, -1));

        jButton12.setText("AGREGAR PRODUCTO");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        jPanel8.add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, -1, -1));

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel39.setText("Volumen en [cc]");
        jPanel8.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 60, 100, -1));
        jPanel8.add(pantVolCoc1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 80, 110, -1));
        jPanel8.add(pantVolCoc2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 120, 110, -1));
        jPanel8.add(pantVolCoc3, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 160, 110, -1));
        jPanel8.add(pantVolCoc4, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 200, 110, -1));

        jButton23.setText("ELIMINAR");
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });
        jPanel8.add(jButton23, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 240, -1, -1));

        jButton26.setText("NUEVO");
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton26ActionPerformed(evt);
            }
        });
        jPanel8.add(jButton26, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 230, 140, 30));

        jButton27.setText("EDITAR");
        jButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton27ActionPerformed(evt);
            }
        });
        jPanel8.add(jButton27, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 270, -1, -1));

        jButton28.setText("CANCELAR");
        jButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton28ActionPerformed(evt);
            }
        });
        jPanel8.add(jButton28, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 230, -1, -1));

        jButton29.setText("CANCELAR");
        jButton29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton29ActionPerformed(evt);
            }
        });
        jPanel8.add(jButton29, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 270, -1, -1));

        jPanel6.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 80, 300, 300));

        jButton13.setText("RESETARIO");
        jPanel6.add(jButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 390, -1, -1));

        scrollAddProducto.setViewportView(jPanel6);

        getContentPane().add(scrollAddProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 70, 610, 440));

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

        jLabel47.setText("Precio de venta unitario");

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
                            .addComponent(jLabel47)
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
                .addContainerGap(33, Short.MAX_VALUE))
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
                    .addComponent(jLabel47))
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

        BOtros.setText("OTROS");
        BOtros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BOtrosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(BCerveza, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(BLicores, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(BRefrescos, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(BCocteles, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(BCigarrillos, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(BOtros, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jProgressBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jProgressBar3, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(148, 148, 148)
                .addComponent(jProgressBar4, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    .addComponent(BCigarrillos, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BOtros, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jProgressBar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jProgressBar3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jProgressBar4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 810, -1));

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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                        .addGap(117, 117, 117)
                        .addComponent(jLabel10))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(117, 117, 117)
                        .addComponent(pantStock4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(137, 137, 137)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(17, 17, 17))
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
                .addComponent(jLabel10)
                .addGap(6, 6, 6)
                .addComponent(pantStock4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 100, 320, 200));

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102), 3));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setText("CANTIDADES");
        jPanel4.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, -1, 20));

        jLabel12.setText("Cajas");
        jPanel4.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 39, -1, -1));

        pantCantCajas.setText("0");
        pantCantCajas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                pantCantCajasKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                pantCantCajasKeyReleased(evt);
            }
        });
        jPanel4.add(pantCantCajas, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 90, 30));

        jLabel13.setText("+Unidades");
        jPanel4.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 40, 80, -1));

        pantCantUnidades.setText("0");
        pantCantUnidades.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                pantCantUnidadesKeyReleased(evt);
            }
        });
        jPanel4.add(pantCantUnidades, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, 80, 30));

        jLabel14.setText("Total Unidades");
        jPanel4.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 40, -1, -1));

        pantCantTotalUd.setEditable(false);
        pantCantTotalUd.setBackground(new java.awt.Color(153, 153, 153));
        pantCantTotalUd.setText("0");
        jPanel4.add(pantCantTotalUd, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 60, 80, 30));
        jPanel4.add(pantFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 90, -1));

        jLabel15.setText("Fecha de compra");
        jPanel4.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, -1));

        jLabel16.setText("Lista de proveedores");
        jPanel4.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 100, -1, -1));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {  }));
        jPanel4.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 120, 100, -1));

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setText("CON FACTURA");
        jRadioButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButton1MouseClicked(evt);
            }
        });
        jPanel4.add(jRadioButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, -1, -1));

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("SIN FACTURA");
        jPanel4.add(jRadioButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 150, -1, -1));

        jLabel17.setText("PRECIOS");
        jPanel4.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 10, -1, 20));

        pantPrecioCaja.setText("0");
        jPanel4.add(pantPrecioCaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 60, 60, 30));

        jLabel18.setText("Por caja");
        jPanel4.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 40, -1, -1));

        jLabel19.setText("En bruto/unidad");
        jPanel4.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 40, -1, -1));

        pantPrecioBruto.setEditable(false);
        pantPrecioBruto.setBackground(new java.awt.Color(153, 153, 153));
        jPanel4.add(pantPrecioBruto, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 60, 90, 30));

        pantPrecioTotal.setText("0");
        pantPrecioTotal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                pantPrecioTotalKeyReleased(evt);
            }
        });
        jPanel4.add(pantPrecioTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 60, 80, 30));

        jLabel20.setText("Total de compra");
        jPanel4.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 40, -1, -1));

        jButton5.setText("Bonificacion o regalo");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 100, -1, -1));

        jButton6.setText("REGISTRAR");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 150, -1, -1));

        jButton7.setText("CANCELAR");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 110, -1, -1));

        jButton16.setText("INGRESAR BONO O REGALO");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton16, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 110, -1, -1));

        jButton22.setText("EMPEZAR A REGISTRAR");
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton22, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 100, -1, 40));

        jButton24.setText("CANCELAR");
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton24, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 150, -1, -1));

        jButton25.setText("PERDIDA");
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton25, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 150, -1, -1));

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 310, 530, 190));

        BVerFoto.setText("Ver Foto");
        BVerFoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BVerFotoActionPerformed(evt);
            }
        });
        getContentPane().add(BVerFoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 410, 180, 90));

        jButton9.setText("jButton9");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 310, 90, 80));

        jButton10.setText("jButton10");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(819, 310, 90, 80));

        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "            ", "       ", "Cajas", "+unidades","Total uds.","Por Caja","En bruto/ud","De venta unitario","Total compra","   ","Cajas","+unidades","Volumen","Volumen total","Total Cigarros","Cajas","+unidades","    "
            }
        ));
        jTable2.setRowHeight(32);
        jTable2.setRowMargin(2);
        jScrollPane3.setViewportView(jTable2);
        jTable2.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        jPanel5.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 890, 100));

        jLabel21.setText("Nombre Producto");
        jLabel21.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jPanel5.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 90, 35));

        jLabel22.setText("Proveedor");
        jLabel22.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jPanel5.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 0, 97, 35));

        jLabel23.setText("Cantidad");
        jLabel23.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jPanel5.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(216, 0, 97, 35));

        jLabel24.setText("Precios");
        jLabel24.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jPanel5.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(319, 0, 97, 35));

        jLabel25.setText("Fecha de compra");
        jLabel25.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jPanel5.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(422, 0, 97, 35));

        jLabel26.setText("Stock");
        jLabel26.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jPanel5.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(525, 0, 97, 35));

        jLabel27.setText("Factura");
        jLabel27.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jPanel5.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(628, 0, 97, 35));

        jScrollPane2.setViewportView(jPanel5);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 500, 900, 150));

        jButton14.setText("Mostrar Lista completa");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 450, -1, -1));

        jButton15.setText("ATRAS");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton15, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 20, 80, 40));

        jButton20.setText("ELIMINAR");
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton20, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 650, 160, 40));

        jButton21.setText("EDITAR");
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton21, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 650, 170, 40));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        AnimationClass sc = new AnimationClass();
        System.out.println(this.scrollAddProducto.getX());
        sc.jTextAreaXLeft(this.scrollAddProducto.getX(), 300, 10, 10, this.scrollAddProducto);

        reiniciarCombosBoxCoctel();
        llenarCombosBoxCoctel();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        AnimationClass sc = new AnimationClass();
        sc.jTextAreaXRight(this.scrollAddProducto.getX(), this.scrollAddProducto.getX() + this.scrollAddProducto.getWidth(), 10, 10, this.scrollAddProducto);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void BCervezaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BCervezaActionPerformed
        this.tipo = "Cerveza";
        llenarTabla1Especifico();
        this.PanelCocteles.setVisible(false);
        habilitarCLR();
        llenarDeCero();
        inabilitarBono();
        this.PanelCocteles.setVisible(false);
        this.jPanel2.setVisible(true);
        jButton6.setVisible(false);
        this.jButton24.setVisible(false);
        jButton7.setVisible(false);
        jButton16.setVisible(false);
        jButton5.setVisible(false);
        jButton22.setVisible(true);
    }//GEN-LAST:event_BCervezaActionPerformed

    private void BLicoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BLicoresActionPerformed
        this.tipo = "Licores";
        llenarTabla1Especifico();
        habilitarCLR();
        llenarDeCero();
        inabilitarBono();
        this.PanelCocteles.setVisible(false);
        this.jPanel2.setVisible(true);
        jButton6.setVisible(false);
        this.jButton24.setVisible(false);
        jButton7.setVisible(false);
        jButton16.setVisible(false);
        jButton5.setVisible(false);
        jButton22.setVisible(true);
    }//GEN-LAST:event_BLicoresActionPerformed

    private void BRefrescosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BRefrescosActionPerformed
        this.tipo = "Refrescos";
        llenarTabla1Especifico();
        this.PanelCocteles.setVisible(false);
        habilitarCLR();
        llenarDeCero();
        inabilitarBono();
        this.PanelCocteles.setVisible(false);
        this.jPanel2.setVisible(true);
        jButton6.setVisible(false);
        this.jButton24.setVisible(false);
        jButton7.setVisible(false);
        jButton16.setVisible(false);
        jButton5.setVisible(false);
        jButton22.setVisible(true);
    }//GEN-LAST:event_BRefrescosActionPerformed

    private void BCoctelesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BCoctelesActionPerformed
        this.tipo = "Cocteles";
        llenarTabla1Especifico();
        this.PanelCocteles.setVisible(true);
        habilitarCocteles();
        llenarDeCero();
        inabilitarBono();
        this.PanelCocteles.setVisible(true);
        this.jPanel2.setVisible(false);
        jButton6.setVisible(false);
        this.jButton24.setVisible(false);
        jButton7.setVisible(false);
        jButton16.setVisible(false);
        jButton5.setVisible(false);
        jButton22.setVisible(false);
        jButton26.setVisible(true);
    }//GEN-LAST:event_BCoctelesActionPerformed

    private void BCigarrillosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BCigarrillosActionPerformed
        this.tipo = "Cigarrillos";
        llenarTabla1Especifico();
        this.PanelCocteles.setVisible(false);
        habilitarCigarrillo();
        llenarDeCero();
        inabilitarBono();
        this.PanelCocteles.setVisible(false);
        this.jPanel2.setVisible(true);
        jButton6.setVisible(false);
        this.jButton24.setVisible(false);
        jButton7.setVisible(false);
        jButton16.setVisible(false);
        jButton5.setVisible(false);
        jButton22.setVisible(true);
    }//GEN-LAST:event_BCigarrillosActionPerformed

    private void BOtrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BOtrosActionPerformed
        this.tipo = "Otros";
        llenarTabla1Especifico();
        this.PanelCocteles.setVisible(false);
        llenarDeCero();
        habilitarOtros();
        inabilitarBono();
        this.PanelCocteles.setVisible(false);
        this.jPanel2.setVisible(true);
        this.jButton24.setVisible(false);
        jButton7.setVisible(false);
        jButton16.setVisible(false);
        jButton5.setVisible(false);
        jButton22.setVisible(false);
        jButton6.setVisible(true);
        jButton6.setVisible(true);
    }//GEN-LAST:event_BOtrosActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if (!this.p.existeProducto(this.pantNomProducto.getText())) {

            try {
                this.imagen = obtenerFoto();

            } catch (IOException ex) {
                Logger.getLogger(FrameProductos.class.getName()).log(Level.SEVERE, null, ex);
            }
            agregarProducto();
            //llenarTabla1();
            llenarTabla1Especifico();
            reiniciarCombosBoxCoctel();
            llenarCombosBoxCoctel();
            detUnidades();
            this.p.guardar(p);

            this.pantNomProducto.setText("");
            this.pantVol.setText("");
            this.pantUnidades.setText("");
            this.pantPrecioUnitario.setText("");
            this.jButton8.setVisible(false);
            this.jButton19.setVisible(false);
            this.jButton17.setVisible(true);
            this.jButton4.setVisible(false);
        } else {
            JOptionPane.showMessageDialog(null, "Este producto ya existe");
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    public boolean noExisteProducto() {
        if (this.tipo.equals("Cerveza")) {
            Stack<Cerveza> aux = new Stack<Cerveza>();
            aux.addAll(this.p.getcCerveza());
            while (!aux.isEmpty()) {
                Cerveza c = aux.pop();
                if (c.getNomProducto().equals(this.pantNomProducto.getText())) {
                    return false;
                }
            }
        } else if (this.tipo.equals("Licores")) {
            Stack<Licores> aux = new Stack<Licores>();
            aux.addAll(this.p.getcLicores());
            while (!aux.isEmpty()) {
                Licores c = aux.pop();
                if (c.getNomProducto().equals(this.pantNomProducto.getText())) {
                    return false;
                }
            }
        } else if (this.tipo.equals("Refrescos")) {
            Stack<Refrescos> aux = new Stack<Refrescos>();
            aux.addAll(this.p.getcRefrescos());
            while (!aux.isEmpty()) {
                Refrescos c = aux.pop();
                if (c.getNomProducto().equals(this.pantNomProducto.getText())) {
                    return false;
                }
            }
        } else if (this.tipo.equals("Cocteles")) {
            //inabilitar pantallas y botones y activar los de cocteles
            Stack<Cocteles> aux = new Stack<Cocteles>();
            aux.addAll(this.p.getcCocteles());
            while (!aux.isEmpty()) {
                Cocteles c = aux.pop();
                if (c.getNomProducto().equals(this.pantnomCocteles.getText())) {
                    return false;
                }
            }
        } else if (this.tipo.equals("Cigarrillos")) {
            Stack<Cigarrillos> aux = new Stack<Cigarrillos>();
            aux.addAll(this.p.getcCigarrilos());
            while (!aux.isEmpty()) {
                Cigarrillos c = aux.pop();
                if (c.getNomProducto().equals(this.pantNomProducto.getText())) {
                    return false;
                }
            }
        } else if (this.tipo.equals("Otros")) {
            Stack<Otros> aux = new Stack<Otros>();
            aux.addAll(this.p.getcOtros());
            while (!aux.isEmpty()) {
                Otros c = aux.pop();
                if (c.getNomProducto().equals(this.pantNomProducto.getText())) {
                    return false;
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Presione un boton del producto que desea registrar");
        }
        return true;
    }

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed

        llenarTabla1();
        this.tipo = "";
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        mostrarDatos();
        inabilitarBono();
        jButton18.setVisible(true);
        jButton25.setVisible(true);
    }//GEN-LAST:event_jTable1MouseClicked

    private void pantCantCajasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pantCantCajasKeyPressed

    }//GEN-LAST:event_pantCantCajasKeyPressed

    public void ingresarBonificacion() {
        Double total = Double.valueOf(this.pantCantTotalUd.getText());
        Double volumen = Double.valueOf(this.pantDetalles1.getText());
        Double res = total * volumen;
        DecimalFormat df = new DecimalFormat("#.00");
        if (this.tipo.equals("Cerveza")) {
            Cerveza cer = this.p.getcCerveza().get(this.posicion);
            int unidadesCaj = cer.getCantidad();
            double volTotal = res;
            this.pantStock4.setText((Double.valueOf(this.pantStock4.getText()) + res) + "");
            double volUnidad = cer.getVolumen();

            double cajas = Double.valueOf(convertirComa(df.format(volTotal / (volUnidad * unidadesCaj))));
            int cajasInt = (int) (volTotal / (volUnidad * unidadesCaj));
            this.pantStock1.setText((Integer.valueOf(this.pantStock1.getText()) + cajasInt) + "");

            double restoCaja = cajas - cajasInt;

            double unidad = Double.valueOf(convertirComa(df.format(restoCaja * unidadesCaj)));
            int unidadInt = (int) unidad;
            this.pantStock2.setText((Integer.valueOf(this.pantStock2.getText()) + unidadInt) + "");

            double restoUnidad = unidad - unidadInt;
            double volumen1 = restoUnidad * volUnidad;
            double BonVol = Double.valueOf(this.pantStock3.getText()) + Double.valueOf(convertirComa(df.format(volumen1)));
            this.pantStock3.setText(BonVol + "");

            this.pantDetalles3.setText(cer.getBrutoUnidad() + "");
            this.pantDetalles4.setText(cer.getUnitarioCc() + "");

        } else if (this.tipo.equals("Licores")) {
            Licores cer = this.p.getcLicores().get(this.posicion);
            int unidadesCaj = cer.getCantidad();
            double volTotal = res;
            this.pantStock4.setText((Double.valueOf(this.pantStock4.getText()) + res) + "");
            double volUnidad = cer.getVolumen();

            double cajas = Double.valueOf(convertirComa(df.format(volTotal / (volUnidad * unidadesCaj))));
            int cajasInt = (int) (volTotal / (volUnidad * unidadesCaj));
            this.pantStock1.setText((Integer.valueOf(this.pantStock1.getText()) + cajasInt) + "");

            double restoCaja = cajas - cajasInt;

            double unidad = Double.valueOf(convertirComa(df.format(restoCaja * unidadesCaj)));
            int unidadInt = (int) unidad;
            this.pantStock2.setText((Integer.valueOf(this.pantStock2.getText()) + unidadInt) + "");

            double restoUnidad = unidad - unidadInt;
            double volumen1 = restoUnidad * volUnidad;
            double BonVol = Double.valueOf(this.pantStock3.getText()) + Double.valueOf(convertirComa(df.format(volumen1)));
            this.pantStock3.setText(BonVol + "");

            this.pantDetalles3.setText(cer.getBrutoUnidad() + "");
            this.pantDetalles4.setText(cer.getUnitarioCc() + "");

        } else if (this.tipo.equals("Refrescos")) {
            Refrescos cer = this.p.getcRefrescos().get(this.posicion);
            int unidadesCaj = cer.getCantidad();
            double volTotal = res;
            double volUnidad = cer.getVolumen();

            double cajas = Double.valueOf(convertirComa(df.format(volTotal / (volUnidad * unidadesCaj))));
            int cajasInt = (int) (volTotal / (volUnidad * unidadesCaj));
            this.pantStock1.setText((Integer.valueOf(this.pantStock1.getText()) + cajasInt) + "");

            double restoCaja = cajas - cajasInt;

            double unidad = Double.valueOf(convertirComa(df.format(restoCaja * unidadesCaj)));
            int unidadInt = (int) unidad;
            this.pantStock2.setText((Integer.valueOf(this.pantStock2.getText()) + unidadInt) + "");

            double restoUnidad = unidad - unidadInt;
            double volumen1 = restoUnidad * volUnidad;
            double BonVol = Double.valueOf(this.pantStock3.getText()) + Double.valueOf(convertirComa(df.format(volumen1)));
            this.pantStock3.setText(BonVol + "");

            this.pantDetalles3.setText(cer.getBrutoUnidad() + "");
            this.pantDetalles4.setText(cer.getUnitarioCc() + "");

        } else if (this.tipo.equals("Cocteles")) {
            //inabilitar pantallas y botones y activar los de cocteles
        } else if (this.tipo.equals("Cigarrillos")) {
            int res1 = Integer.valueOf(this.pantCantTotalUd.getText());
            Cigarrillos cer = this.p.getcCigarrilos().get(this.posicion);
            int unidadesCaj = cer.getCantidad();
            int volTotal = res1;
            this.pantStock4.setText((Integer.valueOf(this.pantStock4.getText()) + res1) + "");

            double cajas = Double.valueOf(convertirComa(df.format(volTotal / (unidadesCaj))));
            int cajasInt = (int) (volTotal / (unidadesCaj));
            this.pantStock1.setText((Integer.valueOf(this.pantStock1.getText()) + cajasInt) + "");

            double restoCaja = cajas - cajasInt;

            double unidad = Double.valueOf(convertirComa(df.format(restoCaja * unidadesCaj)));
            int unidadInt = (int) unidad;
            this.pantStock2.setText((Integer.valueOf(this.pantStock2.getText()) + unidadInt) + "");

            this.pantDetalles3.setText(cer.getBrutoUnidad() + "");
            this.pantDetalles4.setText(cer.getUnitarioCc() + "");

        } else if (this.tipo.equals("Otros")) {

        } else {
            JOptionPane.showMessageDialog(null, "Presione un boton del producto que desea registrar");
        }

    }

    private void pantCantCajasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pantCantCajasKeyReleased
        if (this.bonificacion) {
            String caj = this.pantCantCajas.getText();
            if (!caj.equals("")) {
                try {
                    int cajas = Integer.valueOf(caj);
                    int unid = Integer.valueOf(this.pantCantUnidades.getText());

                    if (this.tipo.equals("Cerveza")) {
                        int udcaj = this.p.getcCerveza().get(this.posicion).getCantidad() * cajas + unid;
                        this.pantCantTotalUd.setText(udcaj + "");
                    } else if (this.tipo.equals("Licores")) {
                        int udcaj = this.p.getcLicores().get(this.posicion).getCantidad() * cajas + unid;
                        this.pantCantTotalUd.setText(udcaj + "");
                    } else if (this.tipo.equals("Refrescos")) {
                        int udcaj = this.p.getcRefrescos().get(this.posicion).getCantidad() * cajas + unid;
                        this.pantCantTotalUd.setText(udcaj + "");
                    } else if (this.tipo.equals("Cocteles")) {
                        //inabilitar pantallas y botones y activar los de cocteles
                    } else if (this.tipo.equals("Cigarrillos")) {
                        int udcaj = this.p.getcCigarrilos().get(this.posicion).getCantidad() * cajas + unid;
                        this.pantCantTotalUd.setText(udcaj + "");
                    } else if (this.tipo.equals("Otros")) {

                    } else {
                        JOptionPane.showMessageDialog(null, "Presione un boton del producto que desea registrar");
                    }
                    //obtenerVolumenTotalYStock();
                } catch (Exception e) {
                    this.pantCantTotalUd.setText("Error");
                }
            } else {
                this.pantCantCajas.setText("0");
            }
        } else {
            String caj = this.pantCantCajas.getText();
            if (!caj.equals("")) {
                try {
                    int cajas = Integer.valueOf(caj);
                    int unid = Integer.valueOf(this.pantCantUnidades.getText());

                    if (this.tipo.equals("Cerveza")) {
                        int udcaj = this.p.getcCerveza().get(this.posicion).getCantidad() * cajas + unid;
                        this.pantCantTotalUd.setText(udcaj + "");
                    } else if (this.tipo.equals("Licores")) {
                        int udcaj = this.p.getcLicores().get(this.posicion).getCantidad() * cajas + unid;
                        this.pantCantTotalUd.setText(udcaj + "");
                    } else if (this.tipo.equals("Refrescos")) {
                        int udcaj = this.p.getcRefrescos().get(this.posicion).getCantidad() * cajas + unid;
                        this.pantCantTotalUd.setText(udcaj + "");
                    } else if (this.tipo.equals("Cocteles")) {
                        //inabilitar pantallas y botones y activar los de cocteles
                    } else if (this.tipo.equals("Cigarrillos")) {
                        int udcaj = this.p.getcCigarrilos().get(this.posicion).getCantidad() * cajas + unid;
                        this.pantCantTotalUd.setText(udcaj + "");
                    } else if (this.tipo.equals("Otros")) {

                    } else {
                        JOptionPane.showMessageDialog(null, "Presione un boton del producto que desea registrar");
                    }
                    generarBruto();
                    obtenerVolumenTotalYStock();
                } catch (Exception e) {
                    this.pantCantTotalUd.setText("Error");
                }
            } else {
                this.pantCantCajas.setText("0");
            }
        }
    }//GEN-LAST:event_pantCantCajasKeyReleased

    private void pantCantUnidadesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pantCantUnidadesKeyReleased
        if (this.bonificacion) {
            String caj = this.pantCantCajas.getText();
            if (!caj.equals("")) {
                try {
                    int cajas = Integer.valueOf(caj);
                    int unid = Integer.valueOf(this.pantCantUnidades.getText());

                    if (this.tipo.equals("Cerveza")) {
                        int udcaj = this.p.getcCerveza().get(this.posicion).getCantidad() * cajas + unid;
                        this.pantCantTotalUd.setText(udcaj + "");
                    } else if (this.tipo.equals("Licores")) {
                        int udcaj = this.p.getcLicores().get(this.posicion).getCantidad() * cajas + unid;
                        this.pantCantTotalUd.setText(udcaj + "");
                    } else if (this.tipo.equals("Refrescos")) {
                        int udcaj = this.p.getcRefrescos().get(this.posicion).getCantidad() * cajas + unid;
                        this.pantCantTotalUd.setText(udcaj + "");
                    } else if (this.tipo.equals("Cocteles")) {
                        //inabilitar pantallas y botones y activar los de cocteles
                    } else if (this.tipo.equals("Cigarrillos")) {
                        int udcaj = this.p.getcCigarrilos().get(this.posicion).getCantidad() * cajas + unid;
                        this.pantCantTotalUd.setText(udcaj + "");
                    } else if (this.tipo.equals("Otros")) {

                    } else {
                        JOptionPane.showMessageDialog(null, "Presione un boton del producto que desea registrar");
                    }
                    //obtenerVolumenTotalYStock();
                } catch (Exception e) {
                    this.pantCantTotalUd.setText("Error");
                }
            }
        } else {
            String caj = this.pantCantUnidades.getText();
            if (!caj.equals("")) {
                try {
                    int cajas = Integer.valueOf(this.pantCantCajas.getText());
                    int unid = Integer.valueOf(caj);

                    if (this.tipo.equals("Cerveza")) {
                        int udcaj = this.p.getcCerveza().get(this.posicion).getCantidad() * cajas + unid;
                        this.pantCantTotalUd.setText(udcaj + "");
                    } else if (this.tipo.equals("Licores")) {
                        int udcaj = this.p.getcLicores().get(this.posicion).getCantidad() * cajas + unid;
                        this.pantCantTotalUd.setText(udcaj + "");
                    } else if (this.tipo.equals("Refrescos")) {
                        int udcaj = this.p.getcRefrescos().get(this.posicion).getCantidad() * cajas + unid;
                        this.pantCantTotalUd.setText(udcaj + "");
                    } else if (this.tipo.equals("Cocteles")) {
                        //inabilitar pantallas y botones y activar los de cocteles
                    } else if (this.tipo.equals("Cigarrillos")) {
                        int udcaj = this.p.getcCigarrilos().get(this.posicion).getCantidad() * cajas + unid;
                        this.pantCantTotalUd.setText(udcaj + "");
                    } else if (this.tipo.equals("Otros")) {

                    } else {
                        JOptionPane.showMessageDialog(null, "Presione un boton del producto que desea registrar");
                    }
                    generarBruto();
                    obtenerVolumenTotalYStock();
                } catch (Exception e) {
                    this.pantCantTotalUd.setText("Error");
                }
            } else {
                this.pantCantUnidades.setText("0");
            }
        }
    }//GEN-LAST:event_pantCantUnidadesKeyReleased

    private void pantPrecioTotalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pantPrecioTotalKeyReleased
        if (!this.tipo.equals("Otros")) {
            generarBruto();
            generarPrecioUnitario();
        } else {

        }
    }//GEN-LAST:event_pantPrecioTotalKeyReleased

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        if (!this.p.existeProducto(this.pantNomProducto.getText())) {
            if (this.tipo.equals("Cocteles")) {
                //inabilitar pantallas y botones y activar los de cocteles
                try {
                    this.imagen = obtenerFoto();

                } catch (IOException ex) {
                    Logger.getLogger(FrameProductos.class.getName()).log(Level.SEVERE, null, ex);
                }
                Cocteles cocteles = new Cocteles();
                cocteles.setTipo(tipo);
                cocteles.setFoto(imagen);
                cocteles.setNomProducto(this.pantnomCocteles.getText());
                try{
                cocteles.setPrecio(Double.valueOf(this.pantPrecioUnCoctel.getText()));
                }catch(Exception e){
                    System.out.println("Error: "+e);
                    JOptionPane.showMessageDialog(null, "INGRESE NUMEROS CORRECTOS");
                }
                cocteles.setBebida1(this.comboCerveza.getSelectedItem().toString());
                cocteles.setBebida2(this.comboLicor1.getSelectedItem().toString());
                cocteles.setBebida3(this.comboLicor2.getSelectedItem().toString());
                cocteles.setBebida4(this.comboRefrescos.getSelectedItem().toString());
                try {
                    cocteles.setVolumen1(Double.valueOf(this.pantVolCoc1.getText()));
                } catch (Exception e) {
                    System.out.println("Error: " + e);
                    cocteles.setVolumen1(0);
                    this.pantVolCoc1.setText("0");
                }
                try {
                    cocteles.setVolumen2(Double.valueOf(this.pantVolCoc2.getText()));
                } catch (Exception e) {
                    System.out.println("Error: " + e);
                    cocteles.setVolumen2(0);
                    this.pantVolCoc2.setText("0");
                }
                try {
                    cocteles.setVolumen3(Double.valueOf(this.pantVolCoc3.getText()));
                } catch (Exception e) {
                    System.out.println("Error: " + e);
                    cocteles.setVolumen3(0);
                    this.pantVolCoc3.setText("0");
                }
                try {
                    cocteles.setVolumen4(Double.valueOf(this.pantVolCoc4.getText()));
                } catch (Exception e) {
                    System.out.println("Error: " + e);
                    cocteles.setVolumen4(0);
                    this.pantVolCoc4.setText("0");
                }
                this.p.getcCocteles().push(cocteles);
                this.p.guardar(p);
                llenarTabla1Especifico();
                detUnidades();
                this.p.guardar(p);

                jButton26.setVisible(true);
                jButton23.setVisible(false);
                jButton12.setVisible(false);
                jButton27.setVisible(true);
                jButton11.setVisible(false);
                jButton23.setVisible(false);
                jButton8.setVisible(false);
                jButton28.setVisible(false);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Este producto ya existe");
        }
    }//GEN-LAST:event_jButton12ActionPerformed

    private void detalleCoctel2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_detalleCoctel2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_detalleCoctel2ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        FrameRegistroCliente frc = new FrameRegistroCliente();
        frc.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        eliminarProducto();

        llenarTabla1Especifico();
        reiniciarCombosBoxCoctel();
        llenarCombosBoxCoctel();
        detUnidades();
        this.p.guardar(p);
        this.pantNomProducto.setText("");
        this.pantVol.setText("");
        this.pantUnidades.setText("");
        this.pantPrecioUnitario.setText("");
        this.jButton8.setVisible(false);
        this.jButton19.setVisible(false);
        this.jButton17.setVisible(true);
        this.jButton4.setVisible(false);
        this.jButton3.setVisible(false);
        this.jButton2.setVisible(false);
        JOptionPane.showMessageDialog(null, "ELPRODUCTO SE ELIMINO CORRECTAMENTE");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        try {
            this.imagen = obtenerFoto();

        } catch (IOException ex) {
            Logger.getLogger(FrameProductos.class.getName()).log(Level.SEVERE, null, ex);
        }
        editarProducto();

        llenarTabla1Especifico();
        reiniciarCombosBoxCoctel();
        llenarCombosBoxCoctel();
        detUnidades();
        this.p.guardar(p);

        this.pantNomProducto.setText("");
        this.pantVol.setText("");
        this.pantUnidades.setText("");
        this.pantPrecioUnitario.setText("");
        this.jButton8.setVisible(false);
        this.jButton19.setVisible(false);
        this.jButton17.setVisible(true);
        this.jButton4.setVisible(false);
        this.jButton3.setVisible(false);
        this.jButton2.setVisible(false);
        this.jButton18.setVisible(true);
        this.imagen = null;
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        if (this.tipo.equals("Cocteles")) {
            //inabilitar pantallas y botones y activar los de cocteles
            Cocteles cocteles = this.p.getcCocteles().get(posicion);
            cocteles.setTipo(tipo);
            if (this.imagen != null) {
                cocteles.setFoto(imagen);
            }
            cocteles.setNomProducto(this.pantnomCocteles.getText());
            cocteles.setPrecio(Double.valueOf(this.pantPrecioUnCoctel.getText()));
            cocteles.setBebida1(this.comboCerveza.getSelectedItem().toString());
            cocteles.setBebida2(this.comboLicor1.getSelectedItem().toString());
            cocteles.setBebida3(this.comboLicor2.getSelectedItem().toString());
            cocteles.setBebida4(this.comboRefrescos.getSelectedItem().toString());
            cocteles.setVolumen1(Double.valueOf(this.pantVolCoc1.getText()));
            cocteles.setVolumen2(Double.valueOf(this.pantVolCoc2.getText()));
            cocteles.setVolumen3(Double.valueOf(this.pantVolCoc3.getText()));
            cocteles.setVolumen4(Double.valueOf(this.pantVolCoc4.getText()));
            this.p.getcCocteles().set(this.posicion, cocteles);
            this.p.guardar(p);

            jButton26.setVisible(true);
            jButton23.setVisible(false);
            jButton12.setVisible(false);
            jButton27.setVisible(true);
            jButton11.setVisible(false);
            jButton23.setVisible(false);
            jButton8.setVisible(false);
            jButton29.setVisible(false);
        }

    }//GEN-LAST:event_jButton11ActionPerformed

    private void jRadioButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButton1MouseClicked
        try {
            FrameFacturaProveedor fp = new FrameFacturaProveedor();
            fp.setPosicion(this.jTable2.getSelectedRow());
            fp.enviarDatos(this.jComboBox1.getSelectedItem().toString(), Double.valueOf(this.pantPrecioTotal.getText()), this.pantFecha.getText());
            fp.show(true);
        } catch (IOException ex) {
            Logger.getLogger(FrameProductos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FrameProductos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jRadioButton1MouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        if (this.jRadioButton1.isSelected() || this.jRadioButton2.isSelected()) {
            if (this.tipo.equals("Cigarrillos")) {
                registrarCigarrillo();
            } else {
                registrar();
            }
            llenarTabla2();
            this.lsd.guardar(lsd);
            this.p.guardar(p);
            inabilitarBono();
            detUnidades();
            mostrarDatos();
            vaciarPantallasRegistro();
            jButton6.setVisible(false);
            this.jButton24.setVisible(false);
            jButton7.setVisible(false);
            jButton16.setVisible(false);
            jButton5.setVisible(false);
            jButton22.setVisible(true);

            if (!this.tipo.equals("Otros")) {

                int i = JOptionPane.showConfirmDialog(null, "DESEA AUMENTAR BONIFICACION?", "DESEA AUMENTAR BONIFICACION?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (i == 0) {
                    String producto = this.jTable1.getValueAt(this.jTable1.getSelectedRow(), 0).toString();
                    try {
                        int cantidad = Integer.valueOf(JOptionPane.showInputDialog("INGRESE LAS UNIDADES QUE DESEA AUMENTAR\n\nPonga en negativo si desea rebajar"));
                        this.p.aumentarProducto(cantidad, producto);
                        mostrarDatos();
                        this.p.guardar(p);
                    } catch (Exception e) {
                        System.out.println("Error: " + e);
                        JOptionPane.showMessageDialog(null, "SOLO SE ADMITEN NUMEROS ENTEROS");
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "SELECCIONE SI SERA CON FACTURA O NO");
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    public void vaciarPantallasRegistro() {
        this.pantCantCajas.setText("0");
        this.pantCantUnidades.setText("0");
        this.pantCantTotalUd.setText("0");
        this.pantPrecioBruto.setText("0");
        this.pantPrecioCaja.setText("0");
        this.pantPrecioTotal.setText("0");
    }

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

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        WebcamViewerExample1 w = new WebcamViewerExample1();
        w.setSwith(4);
        w.run("");


    }//GEN-LAST:event_jButton8ActionPerformed

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

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        this.cajas = Integer.valueOf(this.pantCantCajas.getText());
        this.unidades = Integer.valueOf(this.pantCantUnidades.getText());
        this.total = Integer.valueOf(this.pantCantTotalUd.getText());
        habilitarBono();

        this.pantCantCajas.setText("0");
        this.pantCantUnidades.setText("0");
        jButton7.setVisible(true);
        jButton6.setVisible(false);
        jButton16.setVisible(true);
        jButton24.setVisible(false);
        this.jButton5.setVisible(false);
    }//GEN-LAST:event_jButton5ActionPerformed

    public void habilitarBono() {
        this.pantPrecioCaja.setEditable(false);
        this.pantPrecioCaja.setBackground(new Color(153, 153, 153));
        //this.pantPrecioBruto.setVisible(false);
        this.pantPrecioTotal.setBackground(new Color(153, 153, 153));

        this.pantPrecioTotal.setEditable(false);

        //this.bonificacion = true;
    }

    public void inabilitarBono() {
        this.pantPrecioCaja.setVisible(true);
        this.pantPrecioBruto.setVisible(true);
        this.pantPrecioTotal.setVisible(true);
        this.bonificacion = false;
    }
    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        /*ingresarBonificacion();
        inabilitarBono();*/
        jButton16.setVisible(false);
        this.jButton7.setVisible(false);
        jButton5.setVisible(true);
        jButton6.setVisible(true);
        jButton24.setVisible(true);
        this.pantCantCajas.setText(this.cajas + "");
        this.pantCantUnidades.setText(this.unidades + "");
        this.pantCantTotalUd.setText(this.total + "");
        this.pantPrecioCaja.setEditable(true);
        this.pantPrecioCaja.setBackground(new Color(255, 255, 255));
        this.pantPrecioTotal.setEditable(true);
        this.pantPrecioTotal.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        try {
            int fil = this.jTable1.getSelectedRow();
            try {
                this.p.aumentarProducto(Integer.valueOf(this.pantCantTotalUd.getText()), this.jTable1.getValueAt(fil, 0).toString());
                mostrarDatos();
                JOptionPane.showMessageDialog(null, "SE AUMENTO AL STOCK " + this.pantCantTotalUd.getText() + " DE UNIDADES CORRECTAMENTE");
                jButton16.setVisible(false);
                this.jButton7.setVisible(false);
                jButton5.setVisible(true);
                jButton6.setVisible(true);
                jButton24.setVisible(true);
                this.pantCantCajas.setText(this.cajas + "");
                this.pantCantUnidades.setText(this.unidades + "");
                this.pantCantTotalUd.setText(this.total + "");
                this.pantPrecioCaja.setEditable(true);
                this.pantPrecioCaja.setBackground(new Color(255, 255, 255));
                this.pantPrecioTotal.setEditable(true);
                this.pantPrecioTotal.setBackground(new Color(255, 255, 255));

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "INGRESE CORRECTAMENTE LOS DATOS EN CANTIDAD");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "SELECCIONE UN PRODUCTO DE LA LISTA");
        }

    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        ocultarBotones();
        this.jButton19.setVisible(true);
        this.jButton4.setVisible(true);
        this.jButton8.setVisible(true);
        this.pantNomProducto.setText("");
        this.pantVol.setText("");
        this.pantUnidades.setText("");
        this.pantPrecioUnitario.setText("");
        imagen = null;
        File f = new File("FotoProducto.png");
        boolean sw = f.delete();
        System.out.println(sw);

        /*this.pantNomProducto.setEditable(true);
        this.pantVol.setEditable(true);
        this.pantUnidades.setEditable(true);
        this.pantPrecioUnitario.setEditable(true);

        this.pantNomProducto.setBackground(new Color(255, 255, 255));
        this.pantVol.setBackground(new Color(255, 255, 255));
        this.pantUnidades.setBackground(new Color(153, 153, 153));
        this.pantPrecioUnitario.setBackground(new Color(153, 153, 153));*/
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        this.jButton17.setVisible(false);
        this.jButton3.setVisible(true);
        this.jButton8.setVisible(true);
        this.jButton2.setVisible(false);
        this.jButton18.setVisible(false);
        this.jButton19.setVisible(true);
        //this.jButton3.setVisible(false);
        this.jButton2.setVisible(true);
        imagen = null;
        File f = new File("FotoProducto.png");
        boolean sw = f.delete();
        System.out.println(sw);
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        this.pantNomProducto.setText("");
        this.pantVol.setText("");
        this.pantUnidades.setText("");
        this.pantPrecioUnitario.setText("");
        this.jButton8.setVisible(false);
        this.jButton19.setVisible(false);
        this.jButton17.setVisible(true);
        this.jButton4.setVisible(false);
        this.jButton3.setVisible(false);
        this.jButton2.setVisible(false);
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        Queue<StockDatos> aux = new LinkedList();
        //aux.addAll(this.lsd.getP());
        int i = 0;
        int fil = this.jTable2.getSelectedRow();
        String producto = this.jTable2.getValueAt(fil, 0).toString();
        int cant = Integer.valueOf(this.jTable2.getValueAt(fil, 4).toString());
        this.p.aumentarProducto(-cant, producto);
        while (!lsd.getP().isEmpty()) {
            StockDatos p = lsd.getP().remove();
            if (i != this.jTable2.getSelectedRow()) {
                aux.add(p);
            }
            i++;
        }
        lsd.getP().addAll(aux);
        llenarTabla2();
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        eliminarProducto();
    }//GEN-LAST:event_jButton23ActionPerformed

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        // TODO add your handling code here:
        if (!this.tipo.equals("")) {
            int s = this.jTable1.getSelectedRow();
            if (s != -1) {

                //jButton5.setVisible(true);
                jButton6.setVisible(true);
                this.jButton22.setVisible(false);
                jButton24.setVisible(true);
                this.pantPrecioCaja.setEditable(true);
                this.pantPrecioCaja.setBackground(new Color(255, 255, 255));
                this.pantPrecioTotal.setEditable(true);
                this.pantPrecioTotal.setBackground(new Color(255, 255, 255));
            } else {
                JOptionPane.showMessageDialog(null, "SELECCIONE UN PRODUCTO DE LA TABLA DE PRODUCTOS");
            }
        } else {
            JOptionPane.showMessageDialog(null, "SELECCIONE UN TIPO DE RPODUCTO CON LOS BOTONES DE ARRIBA");
        }
    }//GEN-LAST:event_jButton22ActionPerformed

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
        // TODO add your handling code here:
        jButton6.setVisible(false);
        this.jButton24.setVisible(false);
        jButton7.setVisible(false);
        jButton16.setVisible(false);
        jButton5.setVisible(false);
        jButton22.setVisible(true);
    }//GEN-LAST:event_jButton24ActionPerformed

    //SERVIRA PARA GUARDAR LO Q SE INGRESA
    private int cajas = 0;
    private int unidades = 0;
    private int total = 0;
    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed
        jButton26.setVisible(false);
        jButton12.setVisible(true);
        jButton8.setVisible(true);
        jButton29.setVisible(false);
        jButton27.setVisible(false);
        jButton28.setVisible(true);
        imagen = null;
        File f = new File("FotoProducto.png");
        boolean sw = f.delete();
        System.out.println(sw);
    }//GEN-LAST:event_jButton26ActionPerformed

    private void jButton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton28ActionPerformed
        jButton26.setVisible(true);
        jButton23.setVisible(false);
        jButton12.setVisible(false);
        jButton27.setVisible(true);
        jButton11.setVisible(false);
        jButton23.setVisible(false);
        jButton8.setVisible(false);
        jButton29.setVisible(false);
        jButton28.setVisible(false);
    }//GEN-LAST:event_jButton28ActionPerformed

    private void jButton29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton29ActionPerformed
        jButton26.setVisible(true);
        jButton23.setVisible(false);
        jButton12.setVisible(false);
        jButton27.setVisible(true);
        jButton11.setVisible(false);
        jButton23.setVisible(false);
        jButton8.setVisible(false);
        jButton29.setVisible(false);
    }//GEN-LAST:event_jButton29ActionPerformed

    private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed
        jButton8.setVisible(true);
        jButton29.setVisible(true);
        jButton11.setVisible(true);
        jButton27.setVisible(false);
        jButton23.setVisible(true);
        jButton26.setVisible(false);
        imagen = null;
        File f = new File("FotoProducto.png");
        boolean sw = f.delete();
        System.out.println(sw);
    }//GEN-LAST:event_jButton27ActionPerformed

    public int posicionElementoTabla1(String producto) {
        int cant = this.jTable1.getRowCount();
        for (int i = 0; i < cant; i++) {
            if (producto.equals(this.jTable1.getValueAt(i, 0).toString())) {
                return i;
            }
        }
        return -1;
    }

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        this.PanelCocteles.setVisible(false);
        this.jPanel2.setVisible(true);
        int fil = this.jTable2.getSelectedRow();
        String producto = this.jTable2.getValueAt(fil, 0).toString();
        int cant = Integer.valueOf(this.jTable2.getValueAt(fil, 4).toString());
        this.p.aumentarProducto(-cant, producto);
        this.jComboBox1.setSelectedItem(this.jTable2.getValueAt(fil, 1).toString());
        String tipo = this.p.obtenerTipo(producto);
        this.tipo = tipo;
        llenarTabla1Especifico();
        int posicion = posicionElementoTabla1(producto);
        this.jTable1.setRowSelectionInterval(posicion, posicion);
        mostrarDatos();
        if (tipo.equals("Cigarrillos")) {
            habilitarCigarrillo();
            this.pantCantCajas.setText(this.jTable2.getValueAt(fil, 2).toString());
            this.pantCantUnidades.setText(this.jTable2.getValueAt(fil, 3).toString());
            this.pantCantTotalUd.setText(this.jTable2.getValueAt(fil, 4).toString());
            this.pantPrecioCaja.setText(this.jTable2.getValueAt(fil, 5).toString());
            this.pantPrecioBruto.setText(this.jTable2.getValueAt(fil, 6).toString());
            this.pantDetalles2.setText(this.jTable2.getValueAt(fil, 7).toString());
            this.pantPrecioTotal.setText(this.jTable2.getValueAt(fil, 8).toString());
            this.pantFecha.setText(this.jTable2.getValueAt(fil, 9).toString());
            this.pantStock1.setText(this.jTable2.getValueAt(fil, 14).toString());
            this.pantStock2.setText(jTable2.getValueAt(fil, 15).toString());
            //this.pantStock3.setText(this.jTable2.getValueAt(fil, 12).toString());
            this.pantStock4.setText(this.jTable2.getValueAt(fil, 16).toString());
            String sw = this.jTable2.getValueAt(fil, 17).toString();
            if (sw.equals("si")) {
                this.jRadioButton1.setSelected(true);
            } else {
                this.jRadioButton2.setSelected(true);
            }
        } else {
            habilitarCLR();
            this.pantCantCajas.setText(this.jTable2.getValueAt(fil, 2).toString());
            this.pantCantUnidades.setText(this.jTable2.getValueAt(fil, 3).toString());
            this.pantCantTotalUd.setText(this.jTable2.getValueAt(fil, 4).toString());
            this.pantPrecioCaja.setText(this.jTable2.getValueAt(fil, 5).toString());
            this.pantPrecioBruto.setText(this.jTable2.getValueAt(fil, 6).toString());
            this.pantDetalles2.setText(this.jTable2.getValueAt(fil, 7).toString());
            this.pantPrecioTotal.setText(this.jTable2.getValueAt(fil, 8).toString());
            this.pantFecha.setText(this.jTable2.getValueAt(fil, 9).toString());
            this.pantStock1.setText(this.jTable2.getValueAt(fil, 10).toString());
            this.pantStock2.setText(jTable2.getValueAt(fil, 11).toString());
            this.pantStock3.setText(this.jTable2.getValueAt(fil, 12).toString());
            this.pantStock4.setText(this.jTable2.getValueAt(fil, 13).toString());
            String sw = this.jTable2.getValueAt(fil, 17).toString();
            if (sw.equals("si")) {
                this.jRadioButton1.setSelected(true);
            } else {
                this.jRadioButton2.setSelected(true);
            }
        }

        Queue<StockDatos> aux = new LinkedList();
        //aux.addAll(this.lsd.getP());
        int i = 0;
        while (!lsd.getP().isEmpty()) {
            StockDatos p = lsd.getP().remove();
            if (i != this.jTable2.getSelectedRow()) {
                aux.add(p);
            }
            i++;
        }
        lsd.getP().addAll(aux);
        llenarTabla2();
    }//GEN-LAST:event_jButton21ActionPerformed

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
        String producto = this.jTable1.getValueAt(this.jTable1.getSelectedRow(), 0).toString();
        try {
            int cantidad = Integer.valueOf(JOptionPane.showInputDialog("INGRESE LAS UNIDADES QUE DESEA AUMENTAR\n\nPonga en negativo si desea rebajar"));
            this.p.aumentarProducto(cantidad, producto);
            mostrarDatos();
            this.p.guardar(p);
        } catch (Exception e) {
            System.out.println("Error: " + e);
            JOptionPane.showMessageDialog(null, "SOLO SE ADMITEN NUMEROS ENTEROS");
        }
    }//GEN-LAST:event_jButton25ActionPerformed

    //**********************************
    public void inhabilitarPantallas() {
        this.pantCantCajas.setEditable(false);
        this.pantCantCajas.setBackground(new Color(153, 153, 153));

        this.pantnomCocteles.setEditable(false);
        this.pantnomCocteles.setBackground(new Color(153, 153, 153));
        this.pantPrecioUnCoctel.setEditable(false);
        this.pantPrecioUnCoctel.setBackground(new Color(153, 153, 153));
        this.pantVolCoc1.setEditable(false);
        this.pantVolCoc1.setBackground(new Color(153, 153, 153));
        this.pantVolCoc2.setEditable(false);
        this.pantVolCoc2.setBackground(new Color(153, 153, 153));
        this.pantVolCoc3.setEditable(false);
        this.pantVolCoc3.setBackground(new Color(153, 153, 153));
        this.pantVolCoc4.setEditable(false);
        this.pantVolCoc4.setBackground(new Color(153, 153, 153));
        this.pantNomProducto.setEditable(false);
        this.pantVol.setEditable(false);
        this.pantUnidades.setEditable(false);
        this.pantPrecioUnitario.setEditable(false);

        this.pantNomProducto.setBackground(new Color(153, 153, 153));
        this.pantVol.setBackground(new Color(153, 153, 153));
        this.pantUnidades.setBackground(new Color(153, 153, 153));
        this.pantPrecioUnitario.setBackground(new Color(153, 153, 153));
    }

    public void ocultarBotones() {
        this.jButton17.setVisible(false);
        this.jButton2.setVisible(false);
        this.jButton3.setVisible(false);
        this.jButton4.setVisible(false);
        this.jButton8.setVisible(false);
        this.jButton12.setVisible(false);
        this.jButton11.setVisible(false);
        this.jButton13.setVisible(false);
        this.jButton5.setVisible(false);
        this.jButton7.setVisible(false);
        this.jButton6.setVisible(false);
        this.jButton16.setVisible(false);
    }

//******************VARIABLES EXCLUSIVAS PARA LA BONIFICACION***************
    private boolean bonificacion = false;

    public void aumentarAlStock() {

    }
    //*************************
    private byte[] imagen = null;

    public byte[] obtenerFoto() throws FileNotFoundException, IOException {
        File f = new File("FotoProducto.png");
        if (f.exists()) {
            byte[] imgf = new byte[(int) f.length()];
            InputStream inte = new FileInputStream(f);
            inte.read(imgf);
            InputStream in = new ByteArrayInputStream(imgf);
            return imgf;
        }
        return null;
    }

    //*************REGISTRO Y GENERAR LA TABLA********************
    public void registrarCigarrillo() {

        //this.lsd.getP().add(sd);
        //****AUMENTANDO STOCK DEL CIGARRILLO***********
        int fila = this.jTable1.getSelectedRow();
        this.p.aumentarProducto(Integer.valueOf(this.pantCantTotalUd.getText()), this.jTable1.getValueAt(fila, 0).toString());

        JOptionPane.showMessageDialog(null, "SE REGISTRO Y AUMENTO EL STOCK CORRECTAMENTE");
        if (this.tipo.equals("Cigarrillos")) {
            this.p.getcCigarrilos().get(posicion).setBrutoUnidad(Double.valueOf(this.pantDetalles3.getText()));
            this.p.getcCigarrilos().get(posicion).setUnitarioCc(Double.valueOf(this.pantDetalles4.getText()));
        } else {
            JOptionPane.showMessageDialog(null, "Presione un boton del producto que desea registrar");
        }

        //**************
        StockDatos sd = new StockDatos();
        sd.setNombreProducto(this.jTable1.getValueAt(this.jTable1.getSelectedRow(), 0).toString());
        sd.setProveedor(this.jComboBox1.getSelectedItem().toString());
        sd.setCantidadCajas(Integer.valueOf(this.pantCantCajas.getText()));
        sd.setCantidadUnidades(Integer.valueOf(this.pantCantUnidades.getText()));
        sd.setPreciosCaja(Double.valueOf(this.pantCantCajas.getText()));
        sd.setCantidadTotal(Integer.valueOf(this.pantCantTotalUd.getText()));
        sd.setPrecioBruto(Double.valueOf(this.pantPrecioBruto.getText()));
        sd.setPrecioUnitario(Double.valueOf(this.pantDetalles2.getText()));
        sd.setPrecioTotal(Double.valueOf(this.pantPrecioTotal.getText()));
        sd.setFecha(this.pantFecha.getText());
        mostrarDatos();
        sd.setStockCajasCigarro(Integer.valueOf(this.pantStock1.getText()));
        sd.setStockUnidadesCigarros(Integer.valueOf(this.pantStock2.getText()));
        //.setStockVolumen(Double.valueOf(this.pantStock3.getText()));
        sd.setStockTotalCigarro(Integer.valueOf(this.pantStock4.getText()));
        if (this.jRadioButton1.isSelected()) {
            sd.setFactura("si");
        } else {
            sd.setFactura("no");
        }
        this.lsd.getP().add(sd);
        JOptionPane.showMessageDialog(null, "SE REGISTRO Y AUMENTO EL STOCK CORRECTAMENTE");
    }

    public void registrar() {

        int fila = this.jTable1.getSelectedRow();
        this.p.aumentarProducto(Integer.valueOf(this.pantCantTotalUd.getText()), this.jTable1.getValueAt(fila, 0).toString());
        //**AUMENTANDO AL STOCK DEL PRODUCTO****
        if (this.tipo.equals("Cerveza")) {

            this.p.getcCerveza().get(posicion).setBrutoUnidad(Double.valueOf(this.pantDetalles3.getText()));
            this.p.getcCerveza().get(posicion).setUnitarioCc(Double.valueOf(this.pantDetalles4.getText()));

            //imagen = null;
        } else if (this.tipo.equals("Licores")) {
            this.p.getcLicores().get(posicion).setBrutoUnidad(Double.valueOf(this.pantDetalles3.getText()));
            this.p.getcLicores().get(posicion).setUnitarioCc(Double.valueOf(this.pantDetalles4.getText()));

            //imagen = null;
        } else if (this.tipo.equals("Refrescos")) {
            this.p.getcRefrescos().get(posicion).setBrutoUnidad(Double.valueOf(this.pantDetalles3.getText()));
            this.p.getcRefrescos().get(posicion).setUnitarioCc(Double.valueOf(this.pantDetalles4.getText()));
            //imagen = null;
        } else if (this.tipo.equals("Cocteles")) {

        } else if (this.tipo.equals("Cigarrillos")) {
            ///****ESTO SE LE LLENA EN OTRA CLASE
        } else {
            JOptionPane.showMessageDialog(null, "Presione un boton del producto que desea registrar");
        }

        StockDatos sd = new StockDatos();
        sd.setNombreProducto(this.jTable1.getValueAt(this.jTable1.getSelectedRow(), 0).toString());
        sd.setProveedor(this.jComboBox1.getSelectedItem().toString());
        sd.setCantidadCajas(Integer.valueOf(this.pantCantCajas.getText()));
        sd.setCantidadUnidades(Integer.valueOf(this.pantCantUnidades.getText()));
        sd.setCantidadTotal(Integer.valueOf(this.pantCantTotalUd.getText()));
        sd.setPreciosCaja(Double.valueOf(this.pantCantCajas.getText()));
        sd.setPrecioBruto(Double.valueOf(this.pantPrecioBruto.getText()));
        sd.setPrecioUnitario(Double.valueOf(this.pantDetalles2.getText()));
        sd.setPrecioTotal(Double.valueOf(this.pantPrecioTotal.getText()));
        sd.setFecha(this.pantFecha.getText());
        mostrarDatos();
        sd.setStockCajas(Integer.valueOf(this.pantStock1.getText()));
        sd.setStockUnidades(Integer.valueOf(this.pantStock2.getText()));
        sd.setStockVolumen(Double.valueOf(this.pantStock3.getText()));
        sd.setStockVolTotal(Double.valueOf(this.pantStock4.getText()));
        if (this.jRadioButton1.isSelected()) {
            sd.setFactura("si");
        } else {
            sd.setFactura("no");
        }
        this.lsd.getP().add(sd);
        JOptionPane.showMessageDialog(null, "SE REGISTRO Y AUMENTO EL STOCK CORRECTAMENTE");

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

//***************METODO PARA EDITAR PRODUCTOS*****************
    public void editarProducto() {

        if (this.tipo.equals("Cerveza")) {
            Cerveza cerveza = this.p.getcCerveza().get(this.posicion);
            cerveza.setTipo(tipo);
            if (this.imagen != null) {
                cerveza.setFoto(imagen);
            }
            cerveza.setNomProducto(this.pantNomProducto.getText());
            cerveza.setVolumen(Double.valueOf(this.pantVol.getText()));
            cerveza.setCantidad(Integer.valueOf(this.pantUnidades.getText()));
            cerveza.setPrecio(Double.valueOf(this.pantPrecioUnitario.getText()));
            this.p.getcCerveza().set(this.posicion, cerveza);
            imagen = null;
        } else if (this.tipo.equals("Licores")) {
            Licores licores = this.p.getcLicores().get(this.posicion);
            licores.setTipo(tipo);
            if (this.imagen != null) {
                licores.setFoto(imagen);
            }
            licores.setNomProducto(this.pantNomProducto.getText());
            licores.setVolumen(Double.valueOf(this.pantVol.getText()));
            licores.setCantidad(Integer.valueOf(this.pantUnidades.getText()));
            licores.setPrecio(Double.valueOf(this.pantPrecioUnitario.getText()));
            this.p.getcLicores().set(this.posicion, licores);
            imagen = null;
        } else if (this.tipo.equals("Refrescos")) {
            Refrescos refrescos = this.p.getcRefrescos().get(this.posicion);
            refrescos.setTipo(tipo);
            if (this.imagen != null) {
                refrescos.setFoto(imagen);
            }
            refrescos.setNomProducto(this.pantNomProducto.getText());
            refrescos.setVolumen(Double.valueOf(this.pantVol.getText()));
            refrescos.setCantidad(Integer.valueOf(this.pantUnidades.getText()));
            refrescos.setPrecio(Double.valueOf(this.pantPrecioUnitario.getText()));
            this.p.getcRefrescos().set(this.posicion, refrescos);
            imagen = null;
        } else if (this.tipo.equals("Cocteles")) {
            //inabilitar pantallas y botones y activar los de cocteles
            /*Cocteles cocteles=new Cocteles();
            cocteles.setTipo(tipo);
            cocteles.setNomProducto(this.pantnomCocteles.getText());
            cocteles.setPrecio(Double.valueOf(this.pantPrecioUnCoctel.getText()));
            cocteles.setBebida1(this.comboCerveza.getSelectedItem().toString());
            cocteles.setBebida2(this.comboLicor1.getSelectedItem().toString());
            cocteles.setBebida3(this.comboLicor2.getSelectedItem().toString());
            cocteles.setBebida4(this.comboRefrescos.getSelectedItem().toString());
            cocteles.setVolumen1(Double.valueOf(this.pantVolCoc1.getText()));
            cocteles.setVolumen2(Double.valueOf(this.pantVolCoc2.getText()));
            cocteles.setVolumen3(Double.valueOf(this.pantVolCoc3.getText()));
            cocteles.setVolumen4(Double.valueOf(this.pantVolCoc4.getText()));
            this.p.getcCocteles().push(cocteles);*/

        } else if (this.tipo.equals("Cigarrillos")) {
            Cigarrillos cigarrillos = this.p.getcCigarrilos().get(this.posicion);
            cigarrillos.setTipo(tipo);
            if (this.imagen != null) {
                cigarrillos.setFoto(imagen);
            }
            cigarrillos.setNomProducto(this.pantNomProducto.getText());
            cigarrillos.setCantidad(Integer.valueOf(this.pantUnidades.getText()));
            cigarrillos.setPrecio(Double.valueOf(this.pantPrecioUnitario.getText()));
            this.p.getcCigarrilos().set(this.posicion, cigarrillos);
            imagen = null;
        } else if (this.tipo.equals("Otros")) {
            Otros otrs = this.p.getcOtros().get(posicion);
            otrs.setNomProducto(this.pantNomProducto.getText());
            if (this.imagen != null) {
                otrs.setFoto(imagen);
            }
            this.p.getcOtros().set(this.posicion, otrs);
            imagen = null;
        } else {
            JOptionPane.showMessageDialog(null, "Presione un boton del producto que desea registrar");
        }

    }

//*******************METODO PARA ELIMINAR UN PRODUCTO*************
    public void eliminarProducto() {
        try {

            if (this.tipo.equals("Cerveza")) {
                this.p.getcCerveza().removeElementAt(this.posicion);
            } else if (this.tipo.equals("Licores")) {
                this.p.getcLicores().removeElementAt(this.posicion);
            } else if (this.tipo.equals("Refrescos")) {
                this.p.getcRefrescos().removeElementAt(this.posicion);
            } else if (this.tipo.equals("Cocteles")) {
                this.p.getcCocteles().removeElementAt(this.posicion);
            } else if (this.tipo.equals("Cigarrillos")) {
                this.p.getcCigarrilos().removeElementAt(this.posicion);
            } else if (this.tipo.equals("Otros")) {
                this.p.getcOtros().removeElementAt(this.posicion);
            } else {
                JOptionPane.showMessageDialog(null, "Presione un boton del producto que desea eliminar");
            }

        } catch (Exception e) {
            this.pantCantTotalUd.setText("Error");
        }
    }

    //*************************************************
    public void obtenerVolumenTotalYStock() {
        Double total = Double.valueOf(this.pantCantTotalUd.getText());
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
            this.pantStock4.setText(this.pantCantTotalUd.getText());
            Cigarrillos cer = this.p.getcCigarrilos().get(this.posicion);
            int unidadesCaj = cer.getCantidad();
            double volTotal = Double.valueOf(this.pantStock4.getText());

            double cajas = Double.valueOf(convertirComa(df.format(volTotal / (unidadesCaj))));//
            int cajasInt = (int) (volTotal / (unidadesCaj));
            this.pantStock1.setText(cajasInt + "");

            double restoCaja = cajas - cajasInt;

            double unidad = Double.valueOf(convertirComa(df.format(restoCaja * unidadesCaj)));
            int unidadInt = (int) unidad;
            this.pantStock2.setText(unidadInt + "");

        }
    }

    public void generarBruto() {
        String cad = this.pantPrecioTotal.getText();
        if (!cad.equals("")) {
            try {
                double compra = Double.valueOf(cad);
                double unidad = Double.valueOf(this.pantCantTotalUd.getText());
                double brut = compra / unidad;
                DecimalFormat df = new DecimalFormat("#.00");
                String res = convertirComa(df.format(brut));
                this.pantPrecioBruto.setText(res);
                this.pantDetalles3.setText(res);
            } catch (Exception e) {
                this.pantPrecioBruto.setText("Error");
            }
        } else {
            this.pantPrecioTotal.setText("0");
        }
    }

    public void generarPrecioUnitario() {
        try {
            double bruto = Double.valueOf(this.pantDetalles3.getText());
            double volumen = Double.valueOf(this.pantDetalles1.getText());
            if (bruto != 0 && volumen != 0) {
                double precioUni = bruto / volumen;
                DecimalFormat df = new DecimalFormat("#.000000");
                String res = convertirComa(df.format(precioUni));
                this.pantDetalles4.setText(res);
            }
        } catch (Exception e) {
            this.pantDetalles4.setText("Error");
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
            java.util.logging.Logger.getLogger(FrameProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new FrameProductos().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(FrameProductos.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(FrameProductos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BCerveza;
    private javax.swing.JButton BCigarrillos;
    private javax.swing.JButton BCocteles;
    private javax.swing.JButton BLicores;
    private javax.swing.JButton BOtros;
    private javax.swing.JButton BRefrescos;
    private javax.swing.JButton BVerFoto;
    private javax.swing.JPanel PanelCocteles;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> comboCerveza;
    private javax.swing.JComboBox<String> comboLicor1;
    private javax.swing.JComboBox<String> comboLicor2;
    private javax.swing.JComboBox<String> comboRefrescos;
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
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
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
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JProgressBar jProgressBar2;
    private javax.swing.JProgressBar jProgressBar3;
    private javax.swing.JProgressBar jProgressBar4;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField pantCantCajas;
    private javax.swing.JTextField pantCantTotalUd;
    private javax.swing.JTextField pantCantUnidades;
    private javax.swing.JTextField pantDetalles1;
    private javax.swing.JTextField pantDetalles2;
    private javax.swing.JTextField pantDetalles3;
    private javax.swing.JTextField pantDetalles4;
    private javax.swing.JTextField pantFecha;
    private javax.swing.JTextField pantNomProducto;
    private javax.swing.JTextField pantPrecioBruto;
    private javax.swing.JTextField pantPrecioCaja;
    private javax.swing.JTextField pantPrecioTotal;
    private javax.swing.JTextField pantPrecioUnCoctel;
    private javax.swing.JTextField pantPrecioUnitario;
    private javax.swing.JTextField pantStock1;
    private javax.swing.JTextField pantStock2;
    private javax.swing.JTextField pantStock3;
    private javax.swing.JTextField pantStock4;
    private javax.swing.JTextField pantUnidades;
    private javax.swing.JTextField pantVol;
    private javax.swing.JTextField pantVolCoc1;
    private javax.swing.JTextField pantVolCoc2;
    private javax.swing.JTextField pantVolCoc3;
    private javax.swing.JTextField pantVolCoc4;
    private javax.swing.JTextField pantnomCocteles;
    private javax.swing.JScrollPane scrollAddProducto;
    // End of variables declaration//GEN-END:variables
}
