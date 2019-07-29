/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg00390aw;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author cuiza
 */
public class frameprincipal_1 extends javax.swing.JFrame {

    /**
     * Creates new form frameprincipal
     */
    public frameprincipal_1() throws IOException, ClassNotFoundException {
        initComponents();
        File f1 = new File("factura.dat");
        if (f1.exists()) {
            f = f.recuperar();
            if(f.getNroFactura()<100000){
            f.setNroFactura(f.getNroFactura() + 1);
            }else{
                f.setNroFactura(200);
            }
        }
        btnImprimir.setVisible(false);
        File file = new File("FacturaCliente.dat");
        if (file.exists()) {
            lfc = lfc.recuperar();
        }

        this.lpm = new ListaPedidosMes();
        File f4 = new File("ListaPedidosMes.dat");
        if (f4.exists()) {
            this.lpm = lpm.recuperar();
        }
    }

    private ListaFacturaCliente lfc = new ListaFacturaCliente();
    private ConfigFactura f = new ConfigFactura();
    private double precioUnitario[];
    private double[] precioParcial;
    private String[] producto;
    private double total;
    public String cod;
    private String administrador;
    private int nroPedido;
    private String garzon;

    public String getGarzon() {
        return garzon;
    }

    public void setGarzon(String garzon) {
        this.garzon = garzon;
    }

    public int getNroPedido() {
        return nroPedido;
    }

    public void setNroPedido(int nroPedido) {
        this.nroPedido = nroPedido;
    }

    public String getAdministrador() {
        return administrador;
    }

    public void setAdministrador(String administrador) {
        this.administrador = administrador;
    }

    public double[] getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double[] precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public double[] getPrecioParcial() {
        return precioParcial;
    }

    public void setPrecioParcial(double[] precioParcial) {
        this.precioParcial = precioParcial;
    }

    public String[] getProducto() {
        return producto;
    }

    public void setProducto(String[] producto) {
        this.producto = producto;
    }

    public String hora() {
        String fe = "";
        int hora, minutos;
        DecimalFormat df = new DecimalFormat("00");
        Calendar calendario = Calendar.getInstance();
        hora = calendario.get(Calendar.HOUR_OF_DAY);
        minutos = calendario.get(Calendar.MINUTE);
        fe = df.format(hora) + ":" + df.format(minutos);
        return fe;
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

    public String fecha2() {
        String f = "";
        int dia, mes, anio;
        Calendar calendario = Calendar.getInstance();
        dia = calendario.get(Calendar.DAY_OF_MONTH);
        mes = calendario.get(Calendar.MONTH) + 1;
        anio = calendario.get(Calendar.YEAR);
        f = dia + "" + mes + "" + anio;
        return f;
    }

    public void generarDatos() {
        this.txtnombreempresa.setText(f.getNombreEmpresa());
        txtAreaDireccion.setText(f.getDireccion());
        txtNit.setText(f.getNit());
        txtAturorizacion.setText(f.getNroAutorizacion());
        txtAreaActividad.setText(f.getDetalle());
        txtAreaNota.setText(f.getNota());
        txtclave.setText(f.getClave());
        txtfechaemision.setText(f.getFechaEmision());
        txtNroFactura.setText(f.getNroFactura() + "");
        txtFechaCompra.setText(fecha());
        txtHora.setText(hora());
        txtnropedido.setText(this.nroPedido + "");
        txtAdministrador.setText(this.administrador + "");
        txtgarzon.setText(this.garzon);
        //txtAreaDetalles.setText(f.getDetalle());
        txtprecioTotal.setText(this.total + "");
        String fac = "";
        for (int i = 0; i < producto.length; i++) {
            fac = fac + producto[i] + "\t" + precioUnitario[i] + "\t" + precioParcial[i] + "\n";
            //total = total + precioParcial[i];
        }

    }

    public void aceptarDatos() {
        ImageIcon fot = new ImageIcon("qr.png");

        lblQR.setIcon(fot);
    }

    public void generarQr() {

    }

    public String qrDatos() {

        String nit = f.getNit();
        String nFac = f.getNroFactura() + "";
        String nAuto = f.getNroAutorizacion();
        String fecha = fecha();        //dia/mes/año
        String pago = this.total + "";
        String cont = this.cod;
        String nitCl = this.txtNitCi.getText();

        String datos = nit + "|" + nFac + "|" + nAuto + "|" + fecha + "|" + pago + "|" + cont + "|" + nitCl + "|0|0|0|0";

        return datos;

    }

    public void generarCodigo() {

        DecimalFormat df = new DecimalFormat("00000");
        this.txtNroFactura.setText(df.format(f.getNroFactura()));
        try {
            String a1, b1, c1, d1, e1, e3;

            a1 = df.format(f.getNroFactura());
            b1 = f.getNroAutorizacion();
            c1 = fecha2();
            d1 = this.txtNitCi.getText();
            e1 = String.valueOf(total);
            e3 = f.getClave();

            codcontrol dale = new codcontrol();

            dale.generate(a1, b1, c1, d1, e1, e3);//

            String ultimo = dale.getUltimoDate();

            String a = ultimo.substring(0, 2);
            String b = ultimo.substring(2, 4);
            String c = ultimo.substring(4, 6);
            String d = ultimo.substring(6, 8);
            String e = ultimo.substring(8, 10);
            String fe = ultimo.substring(10, 12);
            String g = ultimo.substring(12, 14);

            cod = a + "-" + b + "-" + c + "-" + d + "-" + e + "-" + fe + "-" + g;
            this.txtCodcontrol.setText(cod);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public void nroFacturaGen() {
        DecimalFormat df = new DecimalFormat("00000");
        this.txtNroFactura.setText(df.format(f.getNroFactura()));
        try {
            String a1, b1, c1, d1, e1, e3;

            a1 = df.format(f.getNroFactura());
            b1 = f.getNroAutorizacion();
            c1 = fecha2();
            d1 = this.txtNitCi.getText();
            e1 = String.valueOf(total);
            e3 = f.getClave();

            codcontrol dale = new codcontrol();

            dale.generate(a1, b1, c1, d1, e1, e3);//

            String ultimo = dale.getUltimoDate();

            String a = ultimo.substring(0, 2);
            String b = ultimo.substring(2, 4);
            String c = ultimo.substring(4, 6);
            String d = ultimo.substring(6, 8);
            String e = ultimo.substring(8, 10);
            String fe = ultimo.substring(10, 12);
            String g = ultimo.substring(12, 14);

            cod = a + "-" + b + "-" + c + "-" + d + "-" + e + "-" + fe + "-" + g;
            this.txtCodcontrol.setText(cod);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        total = 0;
        for (int i = 0; i < producto.length; i++) {
            //fac = fac + producto[i] + "\t" + precioUnitario[i] + "\t" + precioParcial[i] + "\n";
            total = total + precioParcial[i];
        }
        this.txtprecioTotal.setText(total + "");
    }

    public void DatosDeEjemplo() {

        /////////////DATOS CONFIGURADOS PARA LA EMPRESA///////////////////
        txtnombreempresa.setText("EL DORADO");
        txtAreaDireccion.setText("C. Esperanza\n" + "\n" + "hola");
        txtNit.setText("12356895");
        txtAturorizacion.setText("7954854215");
        txtAreaActividad.setText("La mejor diversion esta aqui\n con la mejor musica.");
        txtAreaNota.setText("No se aceptan devoluciones");

        txtclave.setText("asdf4162a5s543a");
        txtfechaemision.setText("12/12/2018");

        txtNroFactura.setText("000256");  // A GENERAR
        txtCodcontrol.setText("4f-d5-s6-8e-9a");  //A GENERAR

        /////////////DATOS DE EJEMPLO DEL Y PARA EL CLIENTE////////////
        txtFechaCompra.setText("05/06/2018");
        txtHora.setText("2:35 am");

        txtNombre.setText("Adriano");
        txtNitCi.setText("26653156");

        txtnropedido.setText("125");
        txtAdministrador.setText("Mario");
        txtgarzon.setText("Laura");

        txtAreaDetalles.setText("Cerveza:  25     250 \n Ron Abuelo :  2    350\n margarita:  35     25.58");
        txtprecioTotal.setText("625.58");

        txtEfectivo.setText("800");

        ///imagen de qr de ejmplo////
        ImageIcon fot = new ImageIcon("qr.png");

        lblQR.setIcon(fot);

    }

    public void PrecioTotal() {
        total = 0;
        String fac = "";
        for (int i = 0; i < producto.length; i++) {
            fac = fac + producto[i] + "\t  " + precioUnitario[i] + "\t  " + precioParcial[i] + "\n";
            total = total + precioParcial[i];
        }
        txtAreaDetalles.setText(fac);
        this.txtprecioTotal.setText(total + "");
    }

    public PageFormat getPageFormat(PrinterJob pj) {

        PageFormat pf = pj.defaultPage();
        Paper paper = pf.getPaper();

        double middleHeight = 8;
        double headerHeight = 2.0;
        double footerHeight = 2.0;
        double width = convert_CM_To_PPI(20);
        double height = convert_CM_To_PPI(headerHeight + middleHeight + footerHeight);
        paper.setSize(width, height);
        paper.setImageableArea(
                0,
                10,
                width,
                height - convert_CM_To_PPI(1)
        );

        pf.setOrientation(PageFormat.PORTRAIT);
        pf.setPaper(paper);

        return pf;
    }

    protected static double convert_CM_To_PPI(double cm) {
        return toPPI(cm * 0.393600787);
    }

    protected static double toPPI(double inch) {
        return inch * 200d;
    }

    public class BillPrintable implements Printable {

        public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)
                throws PrinterException {

            /////////////// IMAGEN DE EJEMPLO DE QR ///////////
            Toolkit tool = Toolkit.getDefaultToolkit();
            Object img = tool.getImage("qr.png");

            ////////////////////////////////
            int result = NO_SUCH_PAGE;
            if (pageIndex == 0) {

                Graphics2D g2d = (Graphics2D) graphics;

                double width = pageFormat.getImageableWidth();

                g2d.translate((int) pageFormat.getImageableX(), (int) pageFormat.getImageableY());

                ////////// c//////////////
                FontMetrics metrics = g2d.getFontMetrics(new Font("Calibri", Font.BOLD, 9));

                int idLength = metrics.stringWidth("000");
                int amtLength = metrics.stringWidth("000000");
                int qtyLength = metrics.stringWidth("00000");
                int priceLength = metrics.stringWidth("000000");
                int prodLength = (int) width - idLength - amtLength - qtyLength - priceLength - 17;

                int productPosition = 0;
                int discountPosition = prodLength + 5;
                int pricePosition = discountPosition + idLength + 10;
                int qtyPosition = pricePosition + priceLength + 4;
                int amtPosition = qtyPosition + qtyLength;

                try {
                    /*Draw Header*/
                    int y = 20;
                    int yShift = 10;
                    int headerRectHeight = 15;
                    int headerRectHeighta = 40;

                    ////////Para la empresa/////////////
                    String nameEmpresa = txtnombreempresa.getText();
                    String Direccion = txtAreaDireccion.getText();
                    long nitempresa = Long.valueOf(txtNit.getText());
                    long numFactura = Long.valueOf(txtNroFactura.getText());  //A GENERAR
                    long numAutoriza = Long.valueOf(txtAturorizacion.getText());
                    String DetalleActividad = txtAreaActividad.getText();
                    String notas = txtAreaNota.getText();

                    String codiControl = txtCodcontrol.getText();  //A GENERAR
                    String fechEmis = txtfechaemision.getText(); //dato conocido junto a la clave

                    ////////////Datos del y para el cliente/////////
                    String fecha = txtFechaCompra.getText();
                    String hora = txtHora.getText();
                    String nameCliente = txtNombre.getText();
                    long NitCi = Long.valueOf(txtNitCi.getText());

                    String detallecompra = txtAreaDetalles.getText();//Reemplazaraqui

                    double pre = Double.parseDouble(txtprecioTotal.getText());
                    double precioTotal = Math.round(pre * 100d) / 100d;

                    double efect = Double.parseDouble((txtEfectivo.getText()));
                    double efectivo = Math.round(efect * 100d) / 100d;

                    double camb = efectivo - precioTotal;
                    double cambio = Math.round(camb * 100d) / 100d;
                    //////////////////////////////////////
                    String administra = txtAdministrador.getText();
                    String garzon = txtgarzon.getText();
                    int nropedido = Integer.parseInt(txtnropedido.getText());

                    ////////////////////////////////////PARA CONVERTIR NUMEROS A LETRAS////////////
                    numALetras dator = new numALetras();

                    long ipart = (long) precioTotal;
                    double fpart = precioTotal - ipart;

                    int dec = (int) Math.round((fpart * 100));

                    String nombre = dator.convertir((int) ipart);
                    String enLetras = nombre + " CON " + dec + "/100 Bs.";

                    ////////////////////////////////////////////
                    g2d.setFont(new Font("Calibri", Font.LAYOUT_RIGHT_TO_LEFT, 9));
                    g2d.drawString("          " + nameEmpresa + "            ", 50, y);
                    y += yShift;
                    String aux = "";
                    for (int i = 0; i < Direccion.length(); i++) {
                        if (Direccion.charAt(i) == '\n') {
                            g2d.drawString("           " + aux + "             ", 50, y);
                            y += yShift;
                            aux = "";
                        } else {
                            aux = aux + Direccion.charAt(i);
                        }
                    }

                    g2d.drawString("           " + aux + "             ", 50, y);
                    y += yShift;

                    g2d.drawString("              FACTURA                ", 50, y);
                    y += yShift;
                    g2d.drawString("────────────────────────────────────────────", 12, y);
                    y += yShift;
                    g2d.drawString("                 NIT: " + nitempresa + "  ", 12, y);
                    y += yShift;
                    g2d.drawString("       Nº Factura: " + numFactura + "  ", 12, y);
                    y += yShift;
                    g2d.drawString("Nº Autorizacion: " + numAutoriza + " ", 12, y);
                    y += yShift;
                    g2d.drawString("────────────────────────────────────────────", 12, y);
                    y += yShift;
                    g2d.drawString(DetalleActividad, 12, y);
                    y += headerRectHeight;
                    g2d.drawString("   Fecha:  " + fecha + "       hora:" + hora + "", 12, y);
                    y += yShift;
                    g2d.drawString("Nombre:  " + nameCliente + "    ", 12, y);
                    y += yShift;
                    g2d.drawString("   NIT/CI:  " + NitCi + "", 12, y);
                    y += yShift;
                    g2d.drawString("────────────────────────────────────────────", 12, y);
                    y += yShift;
                    g2d.drawString("         Detalle de Compra           ", 50, y);
                    y += headerRectHeight;
                    g2d.drawString("Administrador: " + administra + "  ", 12, y);
                    y += yShift;
                    g2d.drawString("         Garzon: " + garzon + "      ", 12, y);
                    y += yShift;
                    g2d.drawString(" Nº de pedido: " + nropedido + "       ", 12, y);
                    y += yShift;
                    //g2d.drawString("" + detallecompra + "", 12, y);
                    //y+=yShift;
                    String fac = "";
                    for (int i = 0; i < producto.length; i++) {
                        fac = producto[i] + "     " + precioUnitario[i] + "      " + precioParcial[i];
                        //total = total + precioParcial[i];
                        g2d.drawString(fac, 90, y);
                        y += yShift;
                    }
                    g2d.drawString("    " + "   Total a pagar: " + precioTotal + "", 90, y);
                    y += yShift;
                    g2d.drawString("         " + "      Efectivo: " + efectivo + "", 90, y);
                    y += yShift;
                    g2d.drawString("         " + "       Cambio: " + cambio + "", 90, y);
                    y += yShift;
                    g2d.drawString("Son: " + enLetras + "", 5, y);
                    y += yShift;
                    g2d.drawString("────────────────────────────────────────────", 12, y);
                    y += yShift;
                    g2d.drawString("Codigo de Control: " + codiControl + "", 12, y);
                    y += yShift;
                    g2d.drawString("Fecha límite de emisión:" + fechEmis + "", 12, y);
                    y += yShift;
                    g2d.drawImage((Image) img, 50, y, 72, 72, null);
                    y += 90;
                    g2d.setFont(new Font("Calibri", Font.BOLD, 9));
                    g2d.drawString("'ESTA FACTURA CONTRIBUYE AL DESARROLLO", 10, y);
                    y += yShift;
                    g2d.drawString("  DEL PAIS. EL USO ILÍCITO DE ESTA SERÁ", 15, y);
                    y += yShift;
                    g2d.drawString("      SANCIONADO DE ACUERDO A LEY'.     ", 20, y);
                    y += yShift;
                    g2d.drawString("────────────────────────────────────────────", 12, y);
                    y += yShift;
                    g2d.setFont(new Font("Calibri", Font.PLAIN, 9));
                    g2d.drawString(notas, 10, y);
                    y += yShift;
                    g2d.drawString("────────────────────────────────────────────", 12, y);
                    y += yShift;
                    g2d.setFont(new Font("Calibri", Font.PLAIN, 8));//plain Monospaced
                    g2d.drawString("Ley Nº 453 Estan prohibidas las practicas comerciales", 12, y);
                    y += yShift;
                    g2d.drawString("abusivas, tienes derecho a denunciarlas", 12, y);
                    y += yShift;

                } catch (Exception r) {
                    r.printStackTrace();
                }

                result = PAGE_EXISTS;
            }
            return result;

        }
    }

    public String obtenerDatos() {
        String fac = "";
        String Direccion = txtAreaDireccion.getText();
        fac = fac + "          " + f.getNombreEmpresa() + "            " + "\n";
        String aux = "";
        for (int i = 0; i < Direccion.length(); i++) {
            if (Direccion.charAt(i) == '\n') {
                fac += "           " + aux + "             \n";
                aux = "";
            } else {
                aux = aux + Direccion.charAt(i);
            }
        }
        fac += "           " + aux + "             \n";
        fac = fac + "              FACTURA                \n";
        fac = fac + "────────────────────────────────────────────\n";
        long nitempresa = Long.valueOf(txtNit.getText());
        fac = fac + "                 NIT: " + nitempresa + "  " + "\n";
        long numFactura = Long.valueOf(txtNroFactura.getText());
        //DecimalFormat df = new DecimalFormat("00000");
        fac = fac + "       Nº Factura: " + numFactura + "  " + "\n";
        long numAutoriza = Long.valueOf(txtAturorizacion.getText());
        fac = fac + "Nº Autorizacion: " + numAutoriza + " " + "\n";
        fac = fac + "────────────────────────────────────────────\n";
        fac = fac + txtAreaActividad.getText() + "\n\n";
        String fecha = txtFechaCompra.getText();
        String hora = txtHora.getText();
        fac = fac + "   Fecha:  " + fecha + "       hora:" + hora + "" + "\n";

        String nameCliente = txtNombre.getText();
        long NitCi = Long.valueOf(txtNitCi.getText());
        fac = fac + "Nombre:  " + nameCliente + "    " + "\n";
        fac = fac + "Nit/Ci:  " + NitCi + "\n";
        fac = fac + "────────────────────────────────────────────\n";
        fac = fac + "         Detalle de Compra           \n";
        String administra = txtAdministrador.getText();
        String garzon = txtgarzon.getText();
        int nropedido = Integer.parseInt(txtnropedido.getText());
        fac += "Administrador: " + administra + "  \n";
        fac += "Garzon: " + garzon + "      \n";
        fac += "Nº de pedido: " + nropedido + "       \n\n";
        fac = fac + txtAreaDetalles.getText() + "\n";

        fac += "    " + "   Total a pagar: " + txtprecioTotal.getText() + "\n";
        fac += "         " + "      Efectivo: " + txtEfectivo.getText() + "\n";
        double camb = Double.valueOf(txtEfectivo.getText()) - Double.valueOf(txtprecioTotal.getText());
        double cambio = Math.round(camb * 100d) / 100d;
        fac += "         " + "       Cambio: " + cambio + "\n";
        //***************************
        double pre = Double.parseDouble(txtprecioTotal.getText());
        double precioTotal = Math.round(pre * 100d) / 100d;

        double efect = Double.parseDouble((txtEfectivo.getText()));
        double efectivo = Math.round(efect * 100d) / 100d;

        camb = efectivo - precioTotal;
        cambio = Math.round(camb * 100d) / 100d;
        numALetras dator = new numALetras();

        long ipart = (long) precioTotal;
        double fpart = precioTotal - ipart;

        int dec = (int) Math.round((fpart * 100));

        String nombre = dator.convertir((int) ipart);
        String enLetras = nombre + " CON " + dec + "/100 Bs.";
        //********************************
        fac = fac + "Son: " + enLetras + "\n";
        fac = fac + "────────────────────────────────────────────\n";
        //try {
        fac = fac + "Codigo de Control: " + txtCodcontrol.getText() + "\n";
        /*} catch (Exception e) {
            System.out.println("Error: " + e);
        }*/
        fac = fac + "Fecha límite de emisión:" + txtfechaemision.getText();
        //codigoQR;
        return fac;
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtnombreempresa = new javax.swing.JTextField();
        txtAturorizacion = new javax.swing.JTextField();
        txtNit = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAreaDireccion = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtAreaActividad = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtAreaNota = new javax.swing.JTextArea();
        txtclave = new javax.swing.JTextField();
        txtfechaemision = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txtNroFactura = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtFechaCompra = new javax.swing.JTextField();
        txtHora = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtNitCi = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtAreaDetalles = new javax.swing.JTextArea();
        txtprecioTotal = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtCodcontrol = new javax.swing.JTextField();
        txtEfectivo = new javax.swing.JTextField();
        btnMostrar = new javax.swing.JButton();
        btnImprimir = new javax.swing.JButton();
        lblQR = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txtnropedido = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        txtAdministrador = new javax.swing.JTextField();
        txtgarzon = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setText("CONFIGURACION");

        jLabel2.setText("NOMBRE DE EMPRESA");

        jLabel3.setText("DIRECCION");

        jLabel4.setText("NIT");

        jLabel5.setText("Nro. de AUTORIZACION");

        jLabel6.setText("Detalle de actividad");

        jLabel7.setText("nota");

        jLabel8.setText("clave");

        jLabel9.setText("fecha limite de emision");

        txtnombreempresa.setEditable(false);
        txtnombreempresa.setBackground(new java.awt.Color(153, 153, 153));

        txtAturorizacion.setEditable(false);
        txtAturorizacion.setBackground(new java.awt.Color(153, 153, 153));

        txtNit.setEditable(false);
        txtNit.setBackground(new java.awt.Color(153, 153, 153));

        txtAreaDireccion.setEditable(false);
        txtAreaDireccion.setBackground(new java.awt.Color(153, 153, 153));
        txtAreaDireccion.setColumns(1);
        txtAreaDireccion.setRows(5);
        txtAreaDireccion.setTabSize(2);
        txtAreaDireccion.setMaximumSize(new java.awt.Dimension(300, 2147483647));
        jScrollPane1.setViewportView(txtAreaDireccion);

        txtAreaActividad.setEditable(false);
        txtAreaActividad.setBackground(new java.awt.Color(153, 153, 153));
        txtAreaActividad.setColumns(20);
        txtAreaActividad.setRows(5);
        jScrollPane2.setViewportView(txtAreaActividad);

        txtAreaNota.setEditable(false);
        txtAreaNota.setBackground(new java.awt.Color(153, 153, 153));
        txtAreaNota.setColumns(20);
        txtAreaNota.setRows(5);
        jScrollPane3.setViewportView(txtAreaNota);

        txtclave.setEditable(false);
        txtclave.setBackground(new java.awt.Color(153, 153, 153));

        txtfechaemision.setEditable(false);
        txtfechaemision.setBackground(new java.awt.Color(153, 153, 153));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(txtnombreempresa, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtAturorizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                            .addGap(4, 4, 4)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
                                                .addComponent(jScrollPane3)))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(txtclave)
                                                .addComponent(txtfechaemision))))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNit, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(111, 111, 111)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtnombreempresa, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAturorizacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(74, 74, 74)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtclave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtfechaemision, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(106, 106, 106))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setText("nro.Factura A generar");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 127, -1));

        txtNroFactura.setEditable(false);
        txtNroFactura.setBackground(new java.awt.Color(153, 153, 153));
        jPanel2.add(txtNroFactura, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 20, 144, -1));

        jLabel11.setText("fecha");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 109, -1));

        jLabel12.setText("hora");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 96, -1));

        jLabel13.setText("Nombre del cliente:");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 109, -1));

        jLabel14.setText("NIT/CI :");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 99, -1));

        txtFechaCompra.setEditable(false);
        txtFechaCompra.setBackground(new java.awt.Color(153, 153, 153));
        jPanel2.add(txtFechaCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, 144, -1));

        txtHora.setEditable(false);
        txtHora.setBackground(new java.awt.Color(153, 153, 153));
        jPanel2.add(txtHora, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 100, 144, -1));
        jPanel2.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 130, 144, -1));
        jPanel2.add(txtNitCi, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 160, 144, -1));

        jLabel15.setText("PRECIO TOTAL");
        jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 390, 99, -1));

        jLabel16.setText("DETALLES");
        jPanel2.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 320, 70, -1));

        txtAreaDetalles.setEditable(false);
        txtAreaDetalles.setBackground(new java.awt.Color(153, 153, 153));
        txtAreaDetalles.setColumns(20);
        txtAreaDetalles.setRows(5);
        jScrollPane4.setViewportView(txtAreaDetalles);

        jPanel2.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 290, 240, 90));

        txtprecioTotal.setEditable(false);
        txtprecioTotal.setBackground(new java.awt.Color(153, 153, 153));
        jPanel2.add(txtprecioTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 390, 148, -1));

        jLabel17.setText("Efectivo");
        jPanel2.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 420, 99, -1));
        jPanel2.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(624, 396, 85, -1));

        jLabel19.setText("CODIGO DE CONTROL");
        jPanel2.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 470, 141, -1));

        txtCodcontrol.setEditable(false);
        txtCodcontrol.setBackground(new java.awt.Color(153, 153, 153));
        jPanel2.add(txtCodcontrol, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 470, 148, -1));
        jPanel2.add(txtEfectivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 420, 148, -1));

        btnMostrar.setText("INGRESAR");
        btnMostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarActionPerformed(evt);
            }
        });
        jPanel2.add(btnMostrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 570, 150, 50));

        btnImprimir.setText("IMPRIMIR");
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });
        jPanel2.add(btnImprimir, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 570, 143, 54));

        lblQR.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblQR.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.add(lblQR, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 500, 130, 120));

        jLabel21.setText("Codigo QR.");
        jPanel2.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 560, 108, 29));

        jLabel22.setText("Nro de Pedido");
        jPanel2.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 99, -1));

        txtnropedido.setEditable(false);
        txtnropedido.setBackground(new java.awt.Color(153, 153, 153));
        jPanel2.add(txtnropedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 200, 144, -1));

        jLabel23.setText("Administrador");
        jPanel2.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, 99, -1));

        txtAdministrador.setEditable(false);
        txtAdministrador.setBackground(new java.awt.Color(153, 153, 153));
        jPanel2.add(txtAdministrador, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 230, 144, -1));

        txtgarzon.setEditable(false);
        txtgarzon.setBackground(new java.awt.Color(153, 153, 153));
        jPanel2.add(txtgarzon, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 260, 144, -1));

        jLabel24.setText("Garzon");
        jPanel2.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, 99, -1));

        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 20, 290, 540));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 682, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 641, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnMostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarActionPerformed
        generarCodigo();
        //DatosDeEjemplo();
        PrecioTotal();
        nroFacturaGen();
        daleQR dl = new daleQR();
        String cad = dl.generaQR(qrDatos());
        btnMostrar.setVisible(false);
        btnImprimir.setVisible(true);
        ImageIcon fot = new ImageIcon("qr.png");

        lblQR.setIcon(fot);

        imagenFactura();
        try {
            File f = new File("resultado.png");
            byte[] imgf = new byte[(int) f.length()];
            InputStream inte;
            inte = new FileInputStream(f);
            inte.read(imgf);
            InputStream in = new ByteArrayInputStream(imgf);
            BufferedImage imagen = ImageIO.read(in);
            ImageIcon icono = new ImageIcon(imagen);
            jButton1.setIcon(icono);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(frameprincipal_1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(frameprincipal_1.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnMostrarActionPerformed

    public void guardarRegistroFacturas() throws FileNotFoundException, IOException {
        FacturaCliente fc = new FacturaCliente();
        fc.setFecha(this.txtFechaCompra.getText());
        fc.setNroFactura(Integer.valueOf(this.txtNroFactura.getText()));
        fc.setNroAutorizacion(this.txtAturorizacion.getText());
        fc.setCi(this.txtNitCi.getText());
        fc.setNombre(this.txtNombre.getText());
        fc.setTotalImporte(Double.valueOf(this.txtprecioTotal.getText()));
        fc.setCodigoControl(this.txtCodcontrol.getText());
        fc.setEstado("v");
        File f = new File("resultado.png");
        byte[] imgf = new byte[(int) f.length()];
        InputStream inte = new FileInputStream(f);
        inte.read(imgf);
        fc.setImagenFactura(imgf);
        this.lfc.getP().add(fc);
        this.lfc.guardar(lfc);
    }

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed

        esFacturado(fecha(), Integer.valueOf(this.nroPedido));
        PrinterJob pj = PrinterJob.getPrinterJob();
        pj.setPrintable(new BillPrintable(), getPageFormat(pj));
        try {
            pj.print();
            btnMostrar.setVisible(true);

            btnImprimir.setVisible(false);
            guardarRegistroFacturas();
            f.guardar(f);
        } catch (PrinterException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            Logger.getLogger(frameprincipal_1.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnImprimirActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            File objeto = new File("resultado.png");
            Desktop.getDesktop().open(objeto);
        } catch (IOException e) {

        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private ListaPedidosMes lpm;

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
        FrameVentaCaja.tabla4(fecha, lpm);
    }

    public void crearTexto(String txt) throws IOException {

        File archivo = new File("fac.txt");
        BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));
        bw.write(txt);

        bw.close();
    }

    public BufferedImage ConvertirTxtPng(String txt) throws IOException {
        crearTexto(txt);
        FileReader fr1 = new FileReader(new File("fac.txt"));
        BufferedReader br1 = new BufferedReader(fr1);

        String linea1;
        int fil1 = 1;
        while ((linea1 = br1.readLine()) != null) {
            fil1 = fil1 + 11;
            //g2.drawString(linea, 0, fil);
        }
        BufferedImage bufferedImage = new BufferedImage(267, fil1 + 11, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = bufferedImage.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //g2.setColor(Color.yellow);
        //g2.fillOval(0, 0, this.getWidth(), this.getHeight());
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, 277, fil1 + 11);
        g2.setColor(Color.BLACK);

        FileReader fr = new FileReader(new File("fac.txt"));
        BufferedReader br = new BufferedReader(fr);

        String linea;
        int fil = 1;
        while ((linea = br.readLine()) != null) {
            fil = fil + 11;
            g2.drawString(linea, 0, fil);
        }

        fr.close();
        //g2.drawString("hola", 50, 50);
        //g2.drawString(txt, 0, this.getHeight() / 2);
        g2.dispose();
        return bufferedImage;
    }

    public void imagenFactura() {
        daleQR dl = new daleQR();
        String cad = dl.generaQR(qrDatos());
        try {
            File file = new File("prueba.png");
            ImageIO.write(ConvertirTxtPng(obtenerDatos()), "png", file);

            File file2 = new File("prueba2.png");
            ImageIO.write(ConvertirTxtPng2(crearImagen2()), "png", file2);

            BufferedImage biUno = ImageIO.read(new File("prueba.png"));
            BufferedImage biDos = ImageIO.read(new File("qr2.png"));
            BufferedImage biTres = ImageIO.read(new File("prueba2.png"));

            int altura = biUno.getHeight() + biDos.getHeight() + biTres.getHeight();
            BufferedImage biResultado = new BufferedImage(267, altura, BufferedImage.TYPE_INT_ARGB);
            Graphics g = biResultado.getGraphics();
            g.drawImage(biUno, 0, 0, null);
            g.drawImage(biDos, 0, biUno.getHeight() + 5, null);
            g.drawImage(biTres, 0, biUno.getHeight() + biDos.getHeight() + 5, null);
            this.facAltura = altura;
            ImageIO.write(biResultado, "png", new File("resultado.png"));

        } catch (IOException ex) {
            Logger.getLogger(frameprincipal_1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private int facAltura;

    public String crearImagen2() throws IOException {
        String fac = "";
        fac = fac + "'ESTA FACTURA CONTRIBUYE AL DESARROLLO\n";
        fac = fac + "  DEL PAIS. EL USO ILÍCITO DE ESTA SERÁ\n";
        fac = fac + "      SANCIONADO DE ACUERDO A LEY'.     \n";
        fac = fac + "────────────────────────────────────────────\n";
        fac = fac + txtAreaNota.getText() + "\n";
        fac = fac + "────────────────────────────────────────────\n";
        fac = fac + "Ley Nº 453 Estan prohibidas las practicas comerciales\n";
        fac = fac + "abusivas, tienes derecho a denunciarlas\n";
        //ConvertirTxtPng2(fac);
        return fac;
    }

    public void crearTexto2(String txt) throws IOException {

        File archivo = new File("fac2.txt");
        BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));
        bw.write(txt);

        bw.close();
    }

    public BufferedImage ConvertirTxtPng2(String txt) throws IOException {
        crearTexto2(txt);
        FileReader fr1 = new FileReader(new File("fac2.txt"));
        BufferedReader br1 = new BufferedReader(fr1);

        String linea1;
        int fil1 = 1;
        while ((linea1 = br1.readLine()) != null) {
            fil1 = fil1 + 11;
            //g2.drawString(linea, 0, fil);
        }
        BufferedImage bufferedImage = new BufferedImage(267, fil1 + 11, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = bufferedImage.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //g2.setColor(Color.yellow);
        //g2.fillOval(0, 0, this.getWidth(), this.getHeight());
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, 277, fil1 + 11);
        g2.setBackground(Color.WHITE);
        g2.setColor(Color.BLACK);

        FileReader fr = new FileReader(new File("fac2.txt"));
        BufferedReader br = new BufferedReader(fr);

        String linea;
        int fil = 1;
        while ((linea = br.readLine()) != null) {
            fil = fil + 11;
            g2.drawString(linea, 0, fil);
        }

        fr.close();
        //g2.drawString("hola", 50, 50);
        //g2.drawString(txt, 0, this.getHeight() / 2);
        g2.dispose();
        return bufferedImage;
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
            java.util.logging.Logger.getLogger(frameprincipal_1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frameprincipal_1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frameprincipal_1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frameprincipal_1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new frameprincipal_1().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(frameprincipal_1.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(frameprincipal_1.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnMostrar;
    private javax.swing.JButton jButton1;
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
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblQR;
    private javax.swing.JTextField txtAdministrador;
    private javax.swing.JTextArea txtAreaActividad;
    private javax.swing.JTextArea txtAreaDetalles;
    private javax.swing.JTextArea txtAreaDireccion;
    private javax.swing.JTextArea txtAreaNota;
    private javax.swing.JTextField txtAturorizacion;
    private javax.swing.JTextField txtCodcontrol;
    private javax.swing.JTextField txtEfectivo;
    private javax.swing.JTextField txtFechaCompra;
    private javax.swing.JTextField txtHora;
    private javax.swing.JTextField txtNit;
    private javax.swing.JTextField txtNitCi;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNroFactura;
    private javax.swing.JTextField txtclave;
    private javax.swing.JTextField txtfechaemision;
    private javax.swing.JTextField txtgarzon;
    private javax.swing.JTextField txtnombreempresa;
    private javax.swing.JTextField txtnropedido;
    private javax.swing.JTextField txtprecioTotal;
    // End of variables declaration//GEN-END:variables
}
