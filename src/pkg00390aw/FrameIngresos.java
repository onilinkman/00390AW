package pkg00390aw;

import java.awt.Desktop;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
public class FrameIngresos extends javax.swing.JFrame {

    public FrameIngresos(int ancho, int largo) {
        initComponents();
        this.setLocationRelativeTo(null);

        //int ancho=java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
        System.out.println(ancho);
        //this.setBounds(0, 0, ancho-40, largo-30);
        //this.jPanel1.setBounds(0, 0, ancho-40, largo-40);
        //this.jScrollPane1.setBounds(0, 0, ancho-40, largo-40);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentMoved(ComponentEvent ce) {
                System.out.println("x= " + getX() + ",y= " + getY());

            }
        });
        crearComboBox();
        crearComboBox2();

        crearLista();

        jTable1.getColumnModel().getColumn(1).setCellEditor(new Celda_CheckBox());
        jTable1.getColumnModel().getColumn(1).setCellRenderer(new Render_CheckBox());

        jTable1.getColumnModel().getColumn(1).setPreferredWidth(5);
        jTable1.getColumnModel().getColumn(1).setResizable(false);

        jTable1.getColumnModel().getColumn(0).setPreferredWidth(1);
        jTable1.getColumnModel().getColumn(0).setResizable(false);
        //jTable1.getColumnModel().getColumn(13).setResizable(false);
        this.pantCodigoCliente.setText(generarCodigo());
        setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
        //this.dispose();
        ocultarBotones();
        this.BEmpezarIngresar.setVisible(true);
        bloquear();
    }

    public void bloquear() {
        bloquearPantallas();
        pintarGris();

    }

    public void habilitar() {
        habilitarPantallas();
        pintarBlanco();
    }

    public void bloquearPantallas() {
        this.pantPrenda.setEditable(false);
        this.pantDisenio.setEditable(false);
        this.pantTamanio.setEditable(false);
        this.pantCantidad.setEditable(false);
        this.pantCostoUnitario.setEditable(false);
        this.pantAdelanto.setEditable(false);
    }

    //this.pantNombre.setBackground(new java.awt.Color(153, 153, 153));
    public void pintarGris() {
        this.pantPrenda.setBackground(new java.awt.Color(153, 153, 153));
        this.pantDisenio.setBackground(new java.awt.Color(153, 153, 153));
        this.pantTamanio.setBackground(new java.awt.Color(153, 153, 153));
        this.pantCantidad.setBackground(new java.awt.Color(153, 153, 153));
        this.pantCostoUnitario.setBackground(new java.awt.Color(153, 153, 153));
        this.pantAdelanto.setBackground(new java.awt.Color(153, 153, 153));
    }

    public void habilitarPantallas() {
        this.pantPrenda.setEditable(true);
        this.pantDisenio.setEditable(true);
        this.pantTamanio.setEditable(true);
        this.pantCantidad.setEditable(true);
        this.pantCostoUnitario.setEditable(true);
        this.pantAdelanto.setEditable(true);
    }

    public void pintarBlanco() {
        this.pantPrenda.setBackground(new java.awt.Color(255, 255, 255));
        this.pantDisenio.setBackground(new java.awt.Color(255, 255, 255));
        this.pantTamanio.setBackground(new java.awt.Color(255, 255, 255));
        this.pantCantidad.setBackground(new java.awt.Color(255, 255, 255));
        this.pantCostoUnitario.setBackground(new java.awt.Color(255, 255, 255));
        this.pantAdelanto.setBackground(new java.awt.Color(255, 255, 255));
    }

    public void ocultarBotones() {
        this.BMostrar.setVisible(false);
        this.pantIngresar.setVisible(false);
        this.pantEditar.setVisible(false);
        this.pantEntregado.setVisible(false);
        this.jButton4.setVisible(false);
        this.jButton5.setVisible(false);
        this.jButton6.setVisible(false);
        this.jButton7.setVisible(false);
        this.jButton8.setVisible(false);
        this.BCancelar.setVisible(false);
        this.jButton2.setVisible(false);
        this.BEmpezarIngresar.setVisible(false);
        this.BEmpEditar.setVisible(false);
    }

    public FrameIngresos() {
        initComponents();

        int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
        System.out.println(ancho);
        this.setBounds(0, 0, ancho, 700);

        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentMoved(ComponentEvent ce) {
                System.out.println("x= " + getX() + ",y= " + getY());

            }
        });
        crearComboBox();
        crearComboBox2();

        this.jScrollPane1.setBounds(0, 0, this.getWidth() - 20, this.getHeight() - 30);
        this.jPanel1.setBounds(0, 0, this.getWidth() - 20, this.getHeight() - 30);
        crearLista();

        jTable1.getColumnModel().getColumn(0).setCellEditor(new Celda_CheckBox());
        jTable1.getColumnModel().getColumn(0).setCellRenderer(new Render_CheckBox());

        jTable1.getColumnModel().getColumn(0).setPreferredWidth(5);
        jTable1.getColumnModel().getColumn(0).setResizable(false);
        jTable1.getColumnModel().getColumn(14).setPreferredWidth(0);
        jTable1.getColumnModel().getColumn(14).setResizable(false);
        jTable1.getColumnModel().getColumn(13).setResizable(false);
        this.pantCodigoCliente.setText(generarCodigo());
        setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
        this.dispose();
    }

    public void limpiarVentanas() {
        this.BImagenPrenda.setIcon(null);
        this.BImagenDisenio.setIcon(null);
        this.pantCodigoCliente.setText(generarCodigo());
        this.pantNombre.setText("");
        this.pantCantidad.setText("0");
        this.pantPrenda.setText("");
        this.pantDisenio.setText("");
        this.pantCostoUnitario.setText("0.0");
        this.pantTamanio.setText("");
        this.pantTotal.setText("");
        this.pantAdelanto.setText("0.0");
        this.pantSaldo.setText("");
        this.pantFecha.setText("");

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

    public void crearComboBox() {
        NodoPersonal aux = FramePrincipal.cp.getLp().getP();
        while (aux != null) {
            this.jComboBox1.addItem(aux.getPersonal().getNombre());
            aux = aux.getSig();
        }
        /*this.jComboBox1.addItem("hola");
        this.jComboBox1.addItem("2");*/
    }

    public void crearComboBox2() {
        NodoRegistroCliente w = FramePrincipal.cp.getLrc().getP();
        while (w != null) {
            this.jComboBox2.addItem(w.getRegistro().getNombre());
            w = w.getSig();
        }
    }

    public void reiniciarTabla() {
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new Object[]{
                    "", "", "FECHA INGRESO", "FECHA ENTREGA", "CLIENTE", "DISEÑO", "PRENDA", "CANTIDAD", "COSTO UNITARIO", "ADELANTO", "SALDO", "TOTAL", "ENCARGADO", "Nro MAQUINA", "TAMAÑO"
                }
        ));
        jTable1.getColumnModel().getColumn(1).setCellEditor(new Celda_CheckBox());
        jTable1.getColumnModel().getColumn(1).setCellRenderer(new Render_CheckBox());

        jTable1.getColumnModel().getColumn(1).setPreferredWidth(5);
        jTable1.getColumnModel().getColumn(1).setResizable(false);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(0);
        jTable1.getColumnModel().getColumn(0).setResizable(false);
        //jTable1.getColumnModel().getColumn(13).setResizable(false);
    }

    public void Generatabla1() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.addRow(new Object[]{""});

    }

    public void crearLista() {
        NodoCliente nc = FramePrincipal.cp.getLc().getP();
        int i = 0;
        while (nc != null) {
            if (!nc.getCliente().isEntregado()) {
                Generatabla1();
                agregarATabla(nc.getCliente(), i);
                i++;
            }
            nc = nc.getSig();
        }
    }

    public void agregarATabla(Cliente c, int i) {
        String fechaIngreso = c.getFechaIngreso();
        String fechaEntrega = c.getFechaEntrega();
        String cliente = c.getNombre();
        String nobreDisenio = c.getDisenio();
        String nombrePrenda = c.getPrenda();
        int cantidad = c.getCantidad();
        double CostoUnitarion = c.getCostoUnitario();
        double adelanto = c.getAdelanto();
        double saldo = c.getSaldo();
        double total = c.getTotal();
        String encargado = c.getEncargado();
        String nroMaquina = MaquinaEmpleado(c.getEncargado());

        jTable1.setValueAt(c.getCodigoCliente(), i, 0);
        jTable1.setValueAt(false, i, 1);

        jTable1.setValueAt(fechaIngreso, i, 2);
        jTable1.setValueAt(fechaEntrega, i, 3);

        jTable1.setValueAt(cliente, i, 4);//cambiar si o funciona con el i,0

        jTable1.setValueAt(nobreDisenio, i, 5);
        jTable1.setValueAt(nombrePrenda, i, 6);
        jTable1.setValueAt(cantidad, i, 7);
        jTable1.setValueAt(CostoUnitarion, i, 8);
        jTable1.setValueAt(adelanto, i, 9);
        jTable1.setValueAt(saldo, i, 10);
        jTable1.setValueAt(total, i, 11);
        jTable1.setValueAt(encargado, i, 12);
        jTable1.setValueAt(nroMaquina, i, 13);
        jTable1.setValueAt(c.getTamanio(), i, 14);

    }

    public String MaquinaEmpleado(String empleado) {
        String maquina = "";
        NodoPersonal w = FramePrincipal.cp.getLp().getP();
        while (w != null) {
            if (w.getPersonal().getNombre().equals(empleado)) {
                maquina = w.getPersonal().getNumMaquina();
            }
            w = w.getSig();
        }
        return maquina;
    }

    public void ingresarDatos() {
        String codigo = this.pantCodigoCliente.getText();

        this.pantCodigoCliente.setText(codigo);

        String nombre = this.jComboBox2.getSelectedItem().toString();//
        String prenda = this.pantPrenda.getText();//
        String disenio = this.pantDisenio.getText();//
        String tamanio = this.pantTamanio.getText();//
        String cantidad = this.pantCantidad.getText();
        System.out.println(cantidad);
        String costoUnitario = this.pantCostoUnitario.getText();
        try {
            int cantida = Integer.valueOf(this.pantCantidad.getText());
            double costo = Double.valueOf(this.pantCostoUnitario.getText());
            double res = cantida * costo;
            //this.pantTotal.setText(String.valueOf(res));
        } catch (Exception e) {
            System.out.println("Error: " + e);
            JOptionPane.showMessageDialog(null, "INGRESE VALORES EN COSTO Y CANTIDAD, SOLO NUMEROS VALIDOS\nLOS DECIMALES SE ANOTAN CON \".\" EJEMPLO: 0.2");
        }
        String total = this.pantTotal.getText();
        String adelanto = this.pantAdelanto.getText();
        String saldo = this.pantSaldo.getText();
        String fechaEntrega = calendario(this.jDateChooser1.getCalendar());//
        try {
            String encargado = this.jComboBox1.getSelectedItem().toString();//

            if (!estanLlenas(nombre, prenda, disenio, tamanio, cantidad, costoUnitario, total, adelanto, saldo, fechaEntrega, encargado)) {
                Cliente c = new Cliente();
                c.setNombre(nombre);
                c.setPrenda(prenda);
                c.setDisenio(disenio);
                c.setFechaEntrega(fechaEntrega);
                c.setEncargado(encargado);
                c.setTamanio(tamanio);

                try {
                    c.setCostoUnitario(Double.valueOf(costoUnitario));
                    try {
                        c.setSaldo(Double.valueOf(saldo));
                        try {
                            c.setAdelanto(Double.valueOf(adelanto));
                            try {
                                c.setTotal(Double.valueOf(total));
                                try {
                                    c.setCantidad(Integer.valueOf(this.pantCantidad.getText()));

                                    c.setFechaIngreso(ObtenerFecha());
                                    c.setCodigoCliente(codigo);
                                    c.setDireccionDisenio(".\\ImagenesClientes\\" + codigo + "D" + ".png");
                                    c.setDireccionImagen(".\\ImagenesClientes\\" + codigo + "P" + ".png");
                                    //this.pantCodigoCliente.setText(generarCodigo());
                                    System.out.println(codigo);
                                    NodoCliente p = new NodoCliente();
                                    p.setCliente(c);
                                    FramePrincipal.cp.agregarFinalCliente(p);

                                    FramePrincipal.cp.guardar(FramePrincipal.cp.getLc());

                                    ocultarBotones();
                                    this.BEmpezarIngresar.setVisible(true);

                                    bloquear();
                                    limpiarVentanas();

                                } catch (Exception e) {
                                    JOptionPane.showMessageDialog(null, "INGRESE SOLO NUMEROS VALIDOS EN SALDO.");
                                }
                            } catch (Exception e) {
                                //JOptionPane.showMessageDialog(null, "INGRESE SOLO NUMEROS VALIDOS EN TOTAL.\n LOS DECIMALES SE ANOTAN CON \".\" Y NO CON \",\"");
                            }
                        } catch (Exception d) {
                            JOptionPane.showMessageDialog(null, "INGRESE SOLO NUMEROS VALIDOS EN ADELANTO.\n LOS DECIMALES SE ANOTAN CON \".\" Y NO CON \",\"");
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "INGRESE SOLO NUMEROS VALIDOS EN SALDO.\n LOS DECIMALES SE ANOTAN CON \".\" Y NO CON \",\"");
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "INGRESE SOLO NUMEROS VALIDOS EN COSTO UNITARIO.\n LOS DECIMALES SE ANOTAN CON \".\" Y NO CON \",\"");
                }

            }
        } catch (Exception s) {
            JOptionPane.showMessageDialog(null, "NO REGISTRO NINGUN EMPLEADO");
        }
    }

    public String ObtenerFecha() {
        String fecha = "";
        Calendar calendario = Calendar.getInstance();
        int dia = calendario.get(Calendar.DAY_OF_MONTH);
        int mes = calendario.get(Calendar.MONTH) + 1;
        int anio = calendario.get(Calendar.YEAR);
        fecha = dia + "/" + mes + "/" + anio;

        return fecha;
    }

    public boolean estanLlenas(String nombre, String prenda, String disenio, String tamanio, String cantidad, String costoUnitario, String total, String adelanto, String saldo,
            String fechaEntrega, String encargado) {

        return (nombre.equals("") || prenda.equals("") || disenio.equals("") || tamanio.equals("") || cantidad.equals("") || costoUnitario.equals("") || total.equals("") || adelanto.equals("") || saldo.equals("") || fechaEntrega.equals("") || encargado.equals(""));
    }

    //alma.setTipo(this.PantTipo.getModel().getSelectedItem().toString());
    public String calendario(Calendar s) {
        try {
            return s.get(Calendar.DAY_OF_MONTH) + "/" + (s.get(Calendar.MONTH) + 1) + "/" + s.get(Calendar.YEAR);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ingrese una fecha de entrega");
        }
        return "";
    }

    /*
    String codigo = "";
        for (int i = 0; i < 12; i++) {
            int num = (int) (Math.random() * 1000);
            while (!((num >= 48 && num <= 57) || (num >= 65 && num <= 90) || (num >= 97 && num <= 122))) {
                num = (int) (Math.random() * 1000);
            }
            codigo = codigo + (char) num;
        }

        return codigo;
     */
    public String generarCodigo() {
        String codigo = "";
        boolean sw = true;
        while (sw) {
            for (int i = 0; i < 12; i++) {
                int num = (int) (Math.random() * 1000);
                while (!((num >= 48 && num <= 57) || (num >= 65 && num <= 90) || (num >= 97 && num <= 122))) {
                    num = (int) (Math.random() * 1000);
                }
                codigo = codigo + (char) num;
            }
            sw = false;
            NodoCliente nc = FramePrincipal.cp.getLc().getP();
            while (nc != null) {
                if (nc.getCliente().getCodigoCliente().equals(codigo)) {
                    sw = true;
                    //break;
                }
                nc = nc.getSig();
            }
        }

        return codigo;
    }

    /*
    public String generarCodigo(){
        String codigo = "";
        boolean sw=true;
        while (sw) {
            for (int i = 0; i < 12; i++) {
                int num = (int) (Math.random() * 1000);
                while (!((num >= 48 && num <= 57) || (num >= 65 && num <= 90) || (num >= 97 && num <= 122))) {
                    num = (int) (Math.random() * 1000);
                }
                codigo = codigo + (char) num;
            }sw=false;
            NodoG aux=getP();
            while(aux!=null){
                if(!aux.getCodigodelGasto().equals(codigo)){
                    sw=true;
                    break;
                }
                
                aux = aux.getSig();
            }
            if (aux != null) {
                if (!aux.getCodigodelGasto().equals(codigo)) {
                    sw = true;
                    break;
                }
            }
        }
        return codigo;
    }
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        pantTamanio = new javax.swing.JTextField();
        pantDisenio = new javax.swing.JTextField();
        pantPrenda = new javax.swing.JTextField();
        pantCostoUnitario = new javax.swing.JTextField();
        pantTotal = new javax.swing.JTextField();
        pantSaldo = new javax.swing.JTextField();
        pantAdelanto = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        pantIngresar = new javax.swing.JButton();
        BImagenPrenda = new javax.swing.JButton();
        BImagenDisenio = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        pantEditar = new javax.swing.JButton();
        BLimpiar = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        pantEntregado = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        BMostrar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        pantCodigoCliente = new javax.swing.JLabel();
        pantFecha = new javax.swing.JLabel();
        pantEncargado = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        pantCantidad = new javax.swing.JTextField();
        pantSuma = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jButton9 = new javax.swing.JButton();
        BEmpezarIngresar = new javax.swing.JButton();
        BCancelar = new javax.swing.JButton();
        pantNombre = new javax.swing.JTextField();
        BEmpEditar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        pantFiltro = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setViewportBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 5, true));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pantTamanio.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        pantTamanio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pantTamanioActionPerformed(evt);
            }
        });
        jPanel1.add(pantTamanio, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 250, 100, 40));

        pantDisenio.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        pantDisenio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pantDisenioActionPerformed(evt);
            }
        });
        jPanel1.add(pantDisenio, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 180, 260, 40));

        pantPrenda.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        pantPrenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pantPrendaActionPerformed(evt);
            }
        });
        jPanel1.add(pantPrenda, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 260, 40));

        pantCostoUnitario.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        pantCostoUnitario.setText("0.0");
        pantCostoUnitario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pantCostoUnitarioActionPerformed(evt);
            }
        });
        pantCostoUnitario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                pantCostoUnitarioKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                pantCostoUnitarioKeyReleased(evt);
            }
        });
        jPanel1.add(pantCostoUnitario, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 310, 260, 40));

        pantTotal.setEditable(false);
        pantTotal.setBackground(new java.awt.Color(204, 204, 204));
        pantTotal.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        jPanel1.add(pantTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, 140, 40));

        pantSaldo.setEditable(false);
        pantSaldo.setBackground(new java.awt.Color(204, 204, 204));
        pantSaldo.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        jPanel1.add(pantSaldo, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 380, 130, 40));

        pantAdelanto.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        pantAdelanto.setText("0.0");
        pantAdelanto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pantAdelantoActionPerformed(evt);
            }
        });
        pantAdelanto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                pantAdelantoKeyReleased(evt);
            }
        });
        jPanel1.add(pantAdelanto, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 380, 120, 40));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel2.setText("NOMBRE DE PRENDA");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 240, 20));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel3.setText("NOMBRE DE DISEÑO");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 160, 260, 20));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel4.setText("TAMAÑO: ");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 140, 40));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel5.setText("CANTIDAD");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 110, 40));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel6.setText("COSTO UNITARIO");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 280, 220, 30));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel7.setText("TOTAL");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, 210, 30));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel8.setText("ADELANTO");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 350, 160, 30));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel9.setText("SALDO");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 350, 140, 30));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel11.setText("FECHA DE ENTREGA");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 230, 190, 40));

        jDateChooser1.setFocusable(false);
        jDateChooser1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jPanel1.add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 230, 150, 40));

        pantIngresar.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        pantIngresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/aceptar.png"))); // NOI18N
        pantIngresar.setText("INGRESAR");
        pantIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pantIngresarActionPerformed(evt);
            }
        });
        jPanel1.add(pantIngresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(591, 380, 160, 50));
        jPanel1.add(BImagenPrenda, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 50, 280, 260));
        jPanel1.add(BImagenDisenio, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 50, 280, 260));

        jButton4.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jButton4.setText("CAMARA");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 320, 120, 40));

        jButton5.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jButton5.setText("FILE");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 320, 120, 40));

        jButton6.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jButton6.setText("CAMARA");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 320, 120, 40));

        jButton7.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jButton7.setText("EDITAR IMAGEN");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 320, 170, 40));

        jButton8.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jButton8.setText("EDITAR IMAGEN");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 370, 190, 40));

        pantEditar.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        pantEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/edit.png"))); // NOI18N
        pantEditar.setText("EDITAR");
        pantEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pantEditarActionPerformed(evt);
            }
        });
        jPanel1.add(pantEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 380, -1, 50));

        BLimpiar.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        BLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/gnome_edit_clear.png"))); // NOI18N
        BLimpiar.setText("LIMPIAR");
        BLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BLimpiarActionPerformed(evt);
            }
        });
        jPanel1.add(BLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 460, -1, 50));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {  }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jPanel1.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 440, 190, 40));

        pantEntregado.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        pantEntregado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ok.png"))); // NOI18N
        pantEntregado.setText("ENTREGADO");
        pantEntregado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pantEntregadoActionPerformed(evt);
            }
        });
        jPanel1.add(pantEntregado, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 460, 180, 50));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel10.setText("ENCARGADO:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 440, 140, 40));

        BMostrar.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        BMostrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/bill_of_document.png"))); // NOI18N
        BMostrar.setText("MOSTRAR");
        BMostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BMostrarActionPerformed(evt);
            }
        });
        jPanel1.add(BMostrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 490, 170, 60));

        jButton1.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/import.png"))); // NOI18N
        jButton1.setText("IMPORTAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 490, 190, 60));
        jPanel1.add(pantCodigoCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 190, 190, 30));

        pantFecha.setFont(new java.awt.Font("Tahoma", 0, 3)); // NOI18N
        jPanel1.add(pantFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 240, 150, 40));

        pantEncargado.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        pantEncargado.setText("jLabel12");
        jPanel1.add(pantEncargado, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 440, 170, 40));

        jButton2.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/deletered.png"))); // NOI18N
        jButton2.setText("ELIMINAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 460, 160, 50));

        pantCantidad.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        pantCantidad.setText("0");
        pantCantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pantCantidadActionPerformed(evt);
            }
        });
        pantCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                pantCantidadKeyReleased(evt);
            }
        });
        jPanel1.add(pantCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 300, 90, 40));

        pantSuma.setEditable(false);
        pantSuma.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.add(pantSuma, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 515, 140, 40));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel12.setText("SUMA");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 510, 60, 40));

        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 550, 50, 140));

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new Object [] {
                "","", "FECHA INGRESO", "FECHA ENTREGA","CLIENTE","DISEÑO","PRENDA","CANTIDAD","COSTO UNITARIO","ADELANTO","SALDO","TOTAL","ENCARGADO","Nro MAQUINA","TAMAÑO"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(-30, 0, 1290, 130));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 560, 1290, 140));

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/group_add_128.png"))); // NOI18N
        jButton3.setText("REGISTRAR CLIENTE");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 30, 270, 70));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText("CLIENTE");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 70, 30));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {  }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });
        jPanel1.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 90, 170, 40));

        jButton9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/back.png"))); // NOI18N
        jButton9.setText("ATRAS");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(1260, 20, 150, 60));

        BEmpezarIngresar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        BEmpezarIngresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/aceptar.png"))); // NOI18N
        BEmpezarIngresar.setText("EMPEZAR A INGRESAR");
        BEmpezarIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BEmpezarIngresarActionPerformed(evt);
            }
        });
        jPanel1.add(BEmpezarIngresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 460, -1, 50));

        BCancelar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        BCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cancel2.png"))); // NOI18N
        BCancelar.setText("CANCELAR");
        BCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BCancelarActionPerformed(evt);
            }
        });
        jPanel1.add(BCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 380, 150, 50));

        pantNombre.setEditable(false);
        pantNombre.setFont(new java.awt.Font("Consolas", 0, 1)); // NOI18N
        pantNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pantNombreActionPerformed(evt);
            }
        });
        jPanel1.add(pantNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 60, 130, 20));

        BEmpEditar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        BEmpEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/edit.png"))); // NOI18N
        BEmpEditar.setText("EMPEZAR A EDITAR");
        BEmpEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BEmpEditarActionPerformed(evt);
            }
        });
        jPanel1.add(BEmpEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 380, 220, 50));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("FILTRAR");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, -1, -1));

        pantFiltro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                pantFiltroKeyReleased(evt);
            }
        });
        jPanel1.add(pantFiltro, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 30, 210, 40));

        jScrollPane1.setViewportView(jPanel1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1350, 740));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pantIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pantIngresarActionPerformed

        ingresarDatos();
        reiniciarTabla();
        crearLista();

        /*this.pantEntregado.setSelected(true);
        System.out.println(this.pantCantidad.getValue().toString());*/
    }//GEN-LAST:event_pantIngresarActionPerformed

    private void pantEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pantEditarActionPerformed
        editar();
        reiniciarTabla();
        crearLista();
        bloquear();
        ocultarBotones();
        this.BEmpezarIngresar.setVisible(true);
        limpiarVentanas();
    }//GEN-LAST:event_pantEditarActionPerformed

    public void editar() {
        String nombre = this.jComboBox2.getSelectedItem().toString();//
        String prenda = this.pantPrenda.getText();//
        String disenio = this.pantDisenio.getText();//
        String tamanio = this.pantTamanio.getText();//
        String cantidad = this.pantCantidad.getText();
        System.out.println(cantidad);
        String costoUnitario = this.pantCostoUnitario.getText();
        try {
            int cantida = Integer.valueOf(this.pantCantidad.getText());
            double costo = Double.valueOf(this.pantCostoUnitario.getText());
            double res = cantida * costo;
            //this.pantTotal.setText(String.valueOf(res));
        } catch (Exception e) {
            System.out.println("Error: " + e);
            JOptionPane.showMessageDialog(null, "INGRESE VALORES EN COSTO Y CANTIDAD, SOLO NUMEROS VALIDOS\nLOS DECIMALES SE ANOTAN CON \".\" EJEMPLO: 0.2");
        }
        String total = this.pantTotal.getText();
        String adelanto = this.pantAdelanto.getText();
        String saldo = this.pantSaldo.getText();
        String fechaEntrega = calendario(this.jDateChooser1.getCalendar());//
        String encargado = this.jComboBox1.getSelectedItem().toString();//

        if (!estanLlenas(nombre, prenda, disenio, tamanio, cantidad, costoUnitario, total, adelanto, saldo, fechaEntrega, encargado)) {
            Cliente c = new Cliente();
            c.setNombre(nombre);
            c.setPrenda(prenda);
            c.setDisenio(disenio);
            c.setFechaEntrega(fechaEntrega);
            c.setEncargado(encargado);
            c.setTamanio(tamanio);

            try {
                c.setCostoUnitario(Double.valueOf(costoUnitario));
                try {
                    c.setSaldo(Double.valueOf(saldo));
                    try {
                        c.setAdelanto(Double.valueOf(adelanto));
                        try {
                            c.setTotal(Double.valueOf(total));
                            try {
                                c.setCantidad(Integer.valueOf(this.pantCantidad.getText()));
                                //if(this.jDateChooser1.getDate() == null){
                                c.setFechaIngreso(ObtenerFecha());
                                //}
                                c.setCodigoCliente(this.pantCodigoCliente.getText());
                                c.setDireccionDisenio(".\\ImagenesClientes\\" + this.pantCodigoCliente.getText() + "D" + ".png");
                                c.setDireccionImagen(".\\ImagenesClientes\\" + this.pantCodigoCliente.getText() + "P" + ".png");

                                FramePrincipal.cp.getLc().editar(this.pantCodigoCliente.getText(), c);

                                FramePrincipal.cp.guardar(FramePrincipal.cp.getLc());
                                this.pantCodigoCliente.setText(generarCodigo());

                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(null, "INGRESE SOLO NUMEROS VALIDOS EN SALDO.");
                            }
                        } catch (Exception e) {
                            //JOptionPane.showMessageDialog(null, "INGRESE SOLO NUMEROS VALIDOS EN TOTAL.\n LOS DECIMALES SE ANOTAN CON \".\" Y NO CON \",\"");
                        }
                    } catch (Exception d) {
                        JOptionPane.showMessageDialog(null, "INGRESE SOLO NUMEROS VALIDOS EN ADELANTO.\n LOS DECIMALES SE ANOTAN CON \".\" Y NO CON \",\"");
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "INGRESE SOLO NUMEROS VALIDOS EN SALDO.\n LOS DECIMALES SE ANOTAN CON \".\" Y NO CON \",\"");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "INGRESE SOLO NUMEROS VALIDOS EN COSTO UNITARIO.\n LOS DECIMALES SE ANOTAN CON \".\" Y NO CON \",\"");
            }

        }
    }
    private void BMostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BMostrarActionPerformed
        int i = 0;
        NodoCliente sw = FramePrincipal.cp.getLc().getP();
        ListaClientes lc = new ListaClientes();
        while (sw != null) {
            if (!sw.getCliente().isEntregado()) {
                NodoCliente c = new NodoCliente();
                c.setCliente(verificaCheck(i));
                if (c.getCliente() != null) {
                    lc.agregarFinal(c);
                }
                i++;
            }
            sw = sw.getSig();
        }

        FrameTablaMostrar f = new FrameTablaMostrar(lc.getP());
        //f.setSn(lc.getP());

        f.show(true);
    }//GEN-LAST:event_BMostrarActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        limpiarVentanas();
        cambiarCheck();
        int fila = this.jTable1.getSelectedRow();
        try {
            recuperarDatos(fila);
        } catch (IOException ex) {
            Logger.getLogger(FrameIngresos.class.getName()).log(Level.SEVERE, null, ex);
        }
        sumarCheckSaldo();
        ocultarBotones();
        this.BEmpEditar.setVisible(true);
        this.BEmpezarIngresar.setVisible(true);
        this.BMostrar.setVisible(true);
        this.pantEntregado.setVisible(true);
        /*this.BEmpezarIngresar.setVisible(false);
        this.pantEditar.setVisible(true);
        this.BMostrar.setVisible(true);
        this.pantEntregado.setVisible(true);
        this.jButton2.setVisible(true);
        this.jButton4.setVisible(true);
        this.jButton5.setVisible(true);
        this.jButton6.setVisible(true);
        this.jButton7.setVisible(true);
        this.jButton8.setVisible(true);*/
    }//GEN-LAST:event_jTable1MouseClicked

    /*public void verDatos(){
        NodoCliente w=FramePrincipal.cp.getLc().getP();
        while(w!=null){
            if(w.getCliente().getCodigoCliente())
            w=w.getSig();
        }
    }*/
    public void sumarCheckSaldo() {
        int filas = this.jTable1.getRowCount();
        double cont = 0;
        for (int i = 0; i < filas; i++) {
            cont = cont + sumarCheck(i);
        }
        this.pantSuma.setText(String.valueOf(cont));

    }

    public void recuperarDatos(int x) throws IOException {
        String codigo = this.jTable1.getValueAt(x, 0).toString();
        System.out.println("RECUPERADO" + codigo);
        NodoCliente aux = FramePrincipal.cp.getLc().getP();
        while (aux != null) {
            if (codigo.equals(aux.getCliente().getCodigoCliente())) {
                this.pantNombre.setText(aux.getCliente().getNombre());
                this.jComboBox2.setSelectedItem(aux.getCliente().getNombre());

                this.pantPrenda.setText(aux.getCliente().getPrenda());
                this.pantDisenio.setText(aux.getCliente().getDisenio());
                this.pantTamanio.setText(aux.getCliente().getTamanio());
                //this.jDateChooser1.setVisible(false);
                //this.pantFecha.setText(aux.getCliente().getFechaEntrega());//

                this.jDateChooser1.setCalendar(recuperarCalendario(this.jTable1.getValueAt(x, 3).toString()));

                this.pantCostoUnitario.setText(String.valueOf(aux.getCliente().getCostoUnitario()));
                this.pantTotal.setText(String.valueOf(aux.getCliente().getTotal()));
                this.pantAdelanto.setText(String.valueOf(aux.getCliente().getAdelanto()));
                this.pantSaldo.setText(String.valueOf(aux.getCliente().getSaldo()));
                this.pantEncargado.setText(aux.getCliente().getEncargado());//
                this.pantCantidad.setText(String.valueOf(aux.getCliente().getCantidad()));
                this.pantCodigoCliente.setText(aux.getCliente().getCodigoCliente());
                try {
                    this.BImagenPrenda.setIcon(new ImageIcon(ImageIO.read(new File(aux.getCliente().getDireccionImagen()))));
                    System.out.println(aux.getCliente().getDireccionImagen());
                } catch (Exception e) {
                    //JOptionPane.showMessageDialog(null, "NO SE ENCONTRO LA IMAGEN DE LA PRENDA");
                }//this.BImagenDisenio.setIcon(new ImageIcon(ImageIO.read(new File(".\\ImagenesClientes\\" + this.pantCodigoCliente.getText()+"D.png"))));
                try {
                    this.BImagenDisenio.setIcon(new ImageIcon(ImageIO.read(new File(aux.getCliente().getDireccionDisenio()))));
                } catch (Exception e) {
                    //JOptionPane.showMessageDialog(null, "NO SE ENCONTRO LA IMAGEN DEL DISEÑO");
                }

            }
            aux = aux.getSig();
        }
    }


    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        FrameImportar a = new FrameImportar(".\\IngresosExportados\\");
        a.setSw(3);
        a.show(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        WebcamViewerExample1 we = new WebcamViewerExample1();
        we.setSwith(1);
        we.run(this.pantCodigoCliente.getText() + "P");
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        WebcamViewerExample1 we = new WebcamViewerExample1();
        we.setSwith(2);
        we.run(this.pantCodigoCliente.getText() + "D");
    }//GEN-LAST:event_jButton4ActionPerformed

    private void pantEntregadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pantEntregadoActionPerformed
        //System.out.println(this.jTable1.getRowCount());//Devuelve la cantidad de las filas
        FramePrincipal.cp.getLc().entregado(this.pantCodigoCliente.getText());
        reiniciarTabla();
        crearLista();
        FramePrincipal.cp.guardar(FramePrincipal.cp.getLc());
        JOptionPane.showMessageDialog(null, "LA VENTA CONCLUYO CORRECTAMENTE");
        bloquear();
        ocultarBotones();
        this.BEmpezarIngresar.setVisible(true);
    }//GEN-LAST:event_pantEntregadoActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        JFileChooser fc = new JFileChooser();
        int seleccion = fc.showOpenDialog(null);
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.PNG", "png");
        fc.setFileFilter(filtro);
        /*System.out.println(seleccion);
        System.out.println(fc.getSelectedFile().getName());*/
        if (seleccion == JFileChooser.APPROVE_OPTION) {

            String nombreX = this.pantCodigoCliente.getText() + "D";
            CopiarGifACarpeta(fc.getSelectedFile(), nombreX);
            try {
                this.BImagenDisenio.setIcon(new ImageIcon(ImageIO.read(new File(".\\ImagenesClientes\\" + this.pantCodigoCliente.getText() + "D.png"))));
                //FrameIngresos.BImagenPrenda.setIcon(new ImageIcon(ImageIO.read(new File(".\\ImagenesClientes\\" + this.nombreFoto + ".png"))));
            } catch (IOException ex) {
                Logger.getLogger(FrameIngresos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int fila = jTable1.getSelectedRow();
        FramePrincipal.cp.getLc().eliminar(this.jTable1.getValueAt(fila, 0).toString());
        reiniciarTabla();
        crearLista();
        FramePrincipal.cp.guardar(FramePrincipal.cp.getLc());
        bloquear();
        ocultarBotones();
        this.BEmpezarIngresar.setVisible(true);
        JOptionPane.showMessageDialog(null, "SE ELIMINO CORRECTAMENTE");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        try {
            File d = new File(".");
            File objetofile = new File(d.getAbsolutePath() + "\\ImagenesClientes\\" + this.pantCodigoCliente.getText() + "P.png");
            Desktop.getDesktop().open(objetofile);

        } catch (IOException ex) {

            System.out.println(ex);
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        try {
            File d = new File(".");
            File objetofile = new File(d.getAbsolutePath() + "\\ImagenesClientes\\" + this.pantCodigoCliente.getText() + "D" + ".png");
            Desktop.getDesktop().open(objetofile);

        } catch (IOException ex) {

            System.out.println(ex);
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void BLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BLimpiarActionPerformed
        limpiarVentanas();

    }//GEN-LAST:event_BLimpiarActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void pantCostoUnitarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pantCostoUnitarioActionPerformed
        this.pantAdelanto.requestFocus();
        this.pantAdelanto.selectAll();
        /*try {
            double res = Double.valueOf(this.pantCostoUnitario.getText()) * Integer.valueOf(this.pantCantidad.getValue().toString());
            this.pantTotal.setText(String.valueOf(res));

        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "INGRESE SOLO NUMEROS REALES");
        }*/

    }//GEN-LAST:event_pantCostoUnitarioActionPerformed

    private void pantCostoUnitarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pantCostoUnitarioKeyPressed
        /*try {
            double res = Double.valueOf(this.pantCostoUnitario.getText()) * Integer.valueOf(this.pantCantidad.getValue().toString());
            this.pantTotal.setText(String.valueOf(res));

        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "INGRESE SOLO NUMEROS REALES");
        }*/
    }//GEN-LAST:event_pantCostoUnitarioKeyPressed

    private void pantCostoUnitarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pantCostoUnitarioKeyReleased
        try {
            double res = Double.valueOf(this.pantCostoUnitario.getText()) * Integer.valueOf(this.pantCantidad.getText().toString());
            this.pantTotal.setText(String.valueOf(res));

        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "INGRESE SOLO NUMEROS REALES");
        }

        try {
            double res = Double.valueOf(this.pantTotal.getText()) - Double.valueOf(this.pantAdelanto.getText());
            DecimalFormat df = new DecimalFormat("#.00");
            
            this.pantSaldo.setText(corrigeComa(df.format(res)));

        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "INGRESE SOLO NUMEROS REALES");
        }
    }//GEN-LAST:event_pantCostoUnitarioKeyReleased

    public String corrigeComa(String num){
        String rec="";
        for(int i=0;i<num.length();i++){
            if(num.charAt(i)==','){
                rec=rec+".";
            }else{
                rec=rec+num.charAt(i);
            }
        }
        return rec;
    }
    
    private void pantAdelantoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pantAdelantoKeyReleased
        try {
            double res = Double.valueOf(this.pantTotal.getText()) - Double.valueOf(this.pantAdelanto.getText());
            DecimalFormat df = new DecimalFormat("#.00");
            this.pantSaldo.setText(corrigeComa(df.format(res)));

        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "INGRESE SOLO NUMEROS REALES");
        }
    }//GEN-LAST:event_pantAdelantoKeyReleased

    private void pantNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pantNombreActionPerformed
        this.pantPrenda.requestFocus();
    }//GEN-LAST:event_pantNombreActionPerformed

    private void pantPrendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pantPrendaActionPerformed
        this.pantDisenio.requestFocus();
    }//GEN-LAST:event_pantPrendaActionPerformed

    private void pantDisenioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pantDisenioActionPerformed
        this.pantTamanio.requestFocus();
    }//GEN-LAST:event_pantDisenioActionPerformed

    private void pantTamanioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pantTamanioActionPerformed
        //this.pantCostoUnitario.requestFocus();
        this.pantCantidad.requestFocus();
        this.pantCantidad.selectAll();
    }//GEN-LAST:event_pantTamanioActionPerformed

    private void pantCantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pantCantidadActionPerformed
        this.pantCostoUnitario.requestFocus();
        this.pantCostoUnitario.selectAll();
    }//GEN-LAST:event_pantCantidadActionPerformed

    private void pantCantidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pantCantidadKeyReleased
        try {
            double res = Double.valueOf(this.pantCostoUnitario.getText()) * Integer.valueOf(this.pantCantidad.getText());
            this.pantTotal.setText(String.valueOf(res));

        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "INGRESE SOLO NUMEROS REALES");
        }
    }//GEN-LAST:event_pantCantidadKeyReleased

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel13MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        FrameRegistroCliente a = new FrameRegistroCliente();
        a.obtenerDelFiltro(this.pantFiltro.getText());
        a.show(true);
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        this.pantNombre.setText(this.jComboBox2.getSelectedItem().toString());
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        try {
            FramePrincipal a = new FramePrincipal();
            a.show(true);
            this.dispose();
        } catch (IOException ex) {
            Logger.getLogger(FrameIngresos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FrameIngresos.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_jButton9ActionPerformed

    private void BEmpezarIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BEmpezarIngresarActionPerformed
        habilitar();

        this.pantCodigoCliente.setText(generarCodigo());
        this.pantIngresar.setVisible(true);
        ocultarBotones();
        this.BCancelar.setVisible(true);

        this.pantIngresar.setVisible(true);

        this.jButton4.setVisible(true);
        this.jButton5.setVisible(true);
        this.jButton6.setVisible(true);
        this.jButton7.setVisible(true);
        this.jButton8.setVisible(true);
        this.BImagenPrenda.setIcon(null);
        this.BImagenDisenio.setIcon(null);
        //limpiarVentanas();
    }//GEN-LAST:event_BEmpezarIngresarActionPerformed

    private void BCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BCancelarActionPerformed
        bloquear();
        ocultarBotones();
        this.BEmpezarIngresar.setVisible(true);
    }//GEN-LAST:event_BCancelarActionPerformed

    private void BEmpEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BEmpEditarActionPerformed
        ocultarBotones();
        this.BEmpezarIngresar.setVisible(false);
        this.pantEditar.setVisible(true);
        this.BMostrar.setVisible(true);
        this.pantEntregado.setVisible(true);
        this.jButton2.setVisible(true);
        this.jButton4.setVisible(true);
        this.jButton5.setVisible(true);
        this.jButton6.setVisible(true);
        this.jButton7.setVisible(true);
        this.jButton8.setVisible(true);
        this.BCancelar.setVisible(true);

        habilitar();
    }//GEN-LAST:event_BEmpEditarActionPerformed

    private void pantAdelantoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pantAdelantoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pantAdelantoActionPerformed

    private void pantFiltroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pantFiltroKeyReleased
        if (!this.pantFiltro.getText().equals("")) {
            NodoRegistroCliente w = FramePrincipal.cp.getLrc().getP();
            reiniciarComboBox2();
            while (w != null) {
                if (tieneLaLetra(w.getRegistro(), this.pantFiltro.getText())) {
                    this.jComboBox2.addItem(w.getRegistro().getNombre());
                }
                w = w.getSig();
            }
        } else {
            reiniciarComboBox2();
            crearComboBox2();
        }
    }//GEN-LAST:event_pantFiltroKeyReleased

    public boolean tieneLaLetra(RegistroCliente rc, String letra) {
        boolean sw = false;
        for (int i = 0; i <= rc.getNombre().length() - letra.length(); i++) {
            if (rc.getNombre().substring(i, i + letra.length()).equalsIgnoreCase(letra)) {
                return true;
            }
        }
        return sw;
    }

    public void reiniciarComboBox2() {
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{}));

        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });
    }

    public void CopiarGifACarpeta(File directorio, String nombreX) {
        String dire = "";
        System.out.println(dire);
        try {
            //File se=new File(direccion);
            //para que funcione tiene que crearse un documento con la misma extencion a la que quieres clonar
            File nue = new File(".\\ImagenesClientes\\" + nombreX + ".png");
            //nue.mkdirs();
            FileInputStream in = new FileInputStream(directorio.getAbsoluteFile());
            FileOutputStream out = new FileOutputStream(nue);
            int c;
            while ((c = in.read()) != -1) {
                out.write(c);
            }
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cambiarCheck() {
        int fila = jTable1.getSelectedRow();
        int columna = jTable1.getSelectedColumn();
        boolean sw = Boolean.valueOf(jTable1.getValueAt(fila, 1).toString());
        //System.out.println(sw);
        //if(columna==3){
        jTable1.setValueAt(!sw, fila, 1);
        System.out.println(sw);
        //}
    }

    public double sumarCheck(int i) {
        if (jTable1.getValueAt(i, 1).toString().equals("true")) {
            return Double.valueOf(jTable1.getValueAt(i, 10).toString());
        }
        return 0;
    }

    public Cliente verificaCheck(int i) {
        Cliente c = new Cliente();
        if (jTable1.getValueAt(i, 1).toString().equals("true")) {
            c.setFechaIngreso(jTable1.getValueAt(i, 2).toString());
            c.setFechaEntrega(jTable1.getValueAt(i, 3).toString());
            c.setNombre(jTable1.getValueAt(i, 4).toString());
            c.setDisenio(jTable1.getValueAt(i, 5).toString());
            c.setPrenda(jTable1.getValueAt(i, 6).toString());
            c.setCantidad(Integer.valueOf(jTable1.getValueAt(i, 7).toString()));
            c.setCostoUnitario(Double.valueOf(jTable1.getValueAt(i, 8).toString()));
            c.setAdelanto(Double.valueOf(jTable1.getValueAt(i, 9).toString()));
            c.setSaldo(Double.valueOf(jTable1.getValueAt(i, 10).toString()));
            c.setTotal(Double.valueOf(jTable1.getValueAt(i, 11).toString()));
            c.setEncargado(jTable1.getValueAt(i, 12).toString());
            c.setTamanio(this.jTable1.getValueAt(i, 14).toString());
            //c.set(jTable1.getValueAt(i, 12).toString());
            return c;
        } else {
            return null;
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
            java.util.logging.Logger.getLogger(FrameIngresos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameIngresos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameIngresos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameIngresos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameIngresos().setVisible(true);

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
    private javax.swing.JButton BEmpEditar;
    private javax.swing.JButton BEmpezarIngresar;
    public static javax.swing.JButton BImagenDisenio;
    public static javax.swing.JButton BImagenPrenda;
    private javax.swing.JButton BLimpiar;
    private javax.swing.JButton BMostrar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField pantAdelanto;
    private javax.swing.JTextField pantCantidad;
    private javax.swing.JLabel pantCodigoCliente;
    private javax.swing.JTextField pantCostoUnitario;
    private javax.swing.JTextField pantDisenio;
    private javax.swing.JButton pantEditar;
    private javax.swing.JLabel pantEncargado;
    private javax.swing.JButton pantEntregado;
    private javax.swing.JLabel pantFecha;
    private javax.swing.JTextField pantFiltro;
    private javax.swing.JButton pantIngresar;
    private javax.swing.JTextField pantNombre;
    private javax.swing.JTextField pantPrenda;
    private javax.swing.JTextField pantSaldo;
    private javax.swing.JTextField pantSuma;
    private javax.swing.JTextField pantTamanio;
    private javax.swing.JTextField pantTotal;
    // End of variables declaration//GEN-END:variables
}
