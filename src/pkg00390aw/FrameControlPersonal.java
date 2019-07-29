package pkg00390aw;

import com.zkteco.biometric.FingerprintSensorErrorCode;
import com.zkteco.biometric.FingerprintSensorEx;
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
//import static pkg00360aw.FramePersonal.byteArrayToInt;

public class FrameControlPersonal extends javax.swing.JFrame {

    /**
     * Creates new form FrameControlPersonal
     */
    public FrameControlPersonal() {
        initComponents();
        setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
        this.dispose();
        this.setLocationRelativeTo(null);

        iniciarBiometrico();
        recorer();
        buscar();
    }

    public void buscar() {
        if (0 == mhDevice) {
            textArea.setText("Please Open device first!");
            return;
        }
        if (bRegister) {
            enroll_idx = 0;
            bRegister = false;
        }
        if (!bIdentify) {
            bIdentify = true;
        }
    }

    public String ObtenerFecha() {
        String fecha = "";
        Calendar calendario = Calendar.getInstance();
        try {
            Vconfig v = FramePrincipal.cp.recuperarVconfig();
            if (v.getHoraEntrada() > v.getHoraSalida()) {
                
                calendario.add(Calendar.HOUR_OF_DAY, -(v.getHoraSalida()+24-v.getHoraEntrada()));
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        int dia = calendario.get(Calendar.DAY_OF_MONTH);
        int mes = calendario.get(Calendar.MONTH) + 1;
        int anio = calendario.get(Calendar.YEAR);
        fecha = dia + "/" + mes + "/" + anio;

        return fecha;
    }

    public void identificar(String dir, String nom) {
        if (0 == mhDB) {
            textArea.setText("Please open device first!");
        }
        String path = dir;
        byte[] fpTemplate = new byte[2048];
        int[] sizeFPTemp = new int[1];
        sizeFPTemp[0] = 2048;
        int ret = FingerprintSensorEx.ExtractFromImage(mhDB, path, 500, fpTemplate, sizeFPTemp);
        if (0 == ret) {
            if (bIdentify) {
                int[] fid = new int[1];
                int[] score = new int[1];
                ret = FingerprintSensorEx.DBIdentify(mhDB, fpTemplate, fid, score);
                if (ret == 0) {
                    JOptionPane.showMessageDialog(null, nom);
                    textArea.setText("Identify succ, fid=" + fid[0] + ",score=" + score[0]);
                } else {
                    textArea.setText("Identify fail, errcode=" + ret);
                }

            } else if (cbRegTemp <= 0) {
                textArea.setText("Please register first!");
            } else {
                ret = FingerprintSensorEx.DBMatch(mhDB, lastRegTemp, fpTemplate);
                if (ret > 0) {
                    textArea.setText("Verify succ, score=" + ret);
                } else {
                    textArea.setText("Verify fail, ret=" + ret);
                }
            }
        } else {
            textArea.setText("ExtractFromImage fail, ret=" + ret);
        }
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

    public void recorer() {
        NodoPersonal aux = FramePrincipal.cp.getLp().getP();
        while (aux != null) {
            registrarImagen(aux.getPersonal().getDireccionHuella());
            aux = aux.getSig();
        }

    }

    public void registrarImagen(String dir) {
        if (0 == mhDB) {
            textArea.setText("Please open device first!");
        }
        String path = dir;
        byte[] fpTemplate = new byte[2048];
        int[] sizeFPTemp = new int[1];
        sizeFPTemp[0] = 2048;
        int ret = FingerprintSensorEx.ExtractFromImage(mhDB, path, 500, fpTemplate, sizeFPTemp);
        if (0 == ret) {
            ret = FingerprintSensorEx.DBAdd(mhDB, iFid, fpTemplate);
            if (0 == ret) {
                //String base64 = fingerprintSensor.BlobToBase64(fpTemplate, sizeFPTemp[0]);		
                iFid++;
                cbRegTemp = sizeFPTemp[0];
                System.arraycopy(fpTemplate, 0, lastRegTemp, 0, cbRegTemp);
                //Base64 Template
                //String strBase64 = Base64.encodeToString(regTemp, 0, ret, Base64.NO_WRAP);
                textArea.setText("enroll succ");
            } else {
                textArea.setText("DBAdd fail, ret=" + ret);
            }
        } else {
            textArea.setText("ExtractFromImage fail, ret=" + ret);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnImg = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextArea();
        BAdministracion = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(btnImg, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 90, 217, 293));

        textArea.setColumns(20);
        textArea.setRows(5);
        jScrollPane1.setViewportView(textArea);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 250, 100));

        BAdministracion.setFont(new java.awt.Font("Bookman Old Style", 3, 14)); // NOI18N
        BAdministracion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/personal.png"))); // NOI18N
        BAdministracion.setText("Administracion");
        BAdministracion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BAdministracionActionPerformed(evt);
            }
        });
        getContentPane().add(BAdministracion, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 220, 210, 70));

        jButton2.setFont(new java.awt.Font("Bookman Old Style", 3, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/back.png"))); // NOI18N
        jButton2.setText("ATRAS");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 20, 140, 60));

        jLabel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 5, true));
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 585, 400));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BAdministracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BAdministracionActionPerformed
        FrameLogin g = new FrameLogin();
        g.setSw(4);
        FreeSensor();
        g.show(true);
        this.dispose();

    }//GEN-LAST:event_BAdministracionActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        try {
            FramePrincipal a = new FramePrincipal();
            FreeSensor();

            a.show(true);
            this.dispose();

        } catch (IOException ex) {
            Logger.getLogger(FrameControlPersonal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FrameControlPersonal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(FrameControlPersonal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameControlPersonal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameControlPersonal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameControlPersonal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameControlPersonal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BAdministracion;
    private javax.swing.JButton btnImg;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea textArea;
    // End of variables declaration//GEN-END:variables
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
            writeBitmap(imgBuf, fpWidth, fpHeight, ".\\FotosPersonal.bmp");
            btnImg.setIcon(new ImageIcon(ImageIO.read(new File(".\\FotosPersonal.bmp"))));
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
                    try {
                        OnExtractOK(template, templateLen[0]);
                    } catch (IOException ex) {
                        Logger.getLogger(FrameControlPersonal.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(FrameControlPersonal.class.getName()).log(Level.SEVERE, null, ex);
                    }
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

    private void OnExtractOK(byte[] template, int len) throws IOException, ClassNotFoundException {
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
                    textArea.setText("enroll succ");
                } else {
                    textArea.setText("enroll fail, error code=" + ret);
                }
                bRegister = false;
            } else {
                textArea.setText("You need to press the " + (3 - enroll_idx) + " times fingerprint");
            }
        } else if (bIdentify) {
            int[] fid = new int[1];
            int[] score = new int[1];
            int ret = FingerprintSensorEx.DBIdentify(mhDB, template, fid, score);
            if (ret == 0) {
                textArea.setText("Identify succ, fid=" + fid[0] + ",score=" + score[0]);
                NodoPersonal aux = FramePrincipal.cp.getLp().getP();
                int i = 0;
                while (aux != null) {
                    File w = new File(aux.getPersonal().getDireccionHuella());
                    if (w.exists()) {
                        i++;

                        if (fid[0] == i) {

                            if (!aux.getPersonal().getControl().yaVino(ObtenerFecha())) {
                                JOptionPane.showMessageDialog(null, "BIENVENIDO " + aux.getPersonal().getNombre());
                            } else {
                                JOptionPane.showMessageDialog(null, "NOMBRE: " + aux.getPersonal().getNombre());
                            }
                            aux.getPersonal().getControl().modificar(ObtenerFecha());
                            aux.getPersonal().CalcularSueldoReal();
                            FramePrincipal.cp.guardar();
                            FramePrincipal a = new FramePrincipal();
                            FreeSensor();
                            a.show(true);
                            this.dispose();
                            break;
                        }
                    }
                    aux = aux.getSig();
                }
            } else {
                textArea.setText("Identify fail, errcode=" + ret);
            }

        } else if (cbRegTemp <= 0) {
            textArea.setText("Please register first!");
        } else {
            int ret = FingerprintSensorEx.DBMatch(mhDB, lastRegTemp, template);
            if (ret > 0) {
                textArea.setText("Verify succ, score=" + ret);
            } else {
                textArea.setText("Verify fail, ret=" + ret);
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
