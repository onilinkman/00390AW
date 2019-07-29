package pkg00390aw;
import java.awt.BorderLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.lang.Thread.UncaughtExceptionHandler;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamDiscoveryEvent;
import com.github.sarxos.webcam.WebcamDiscoveryListener;
import com.github.sarxos.webcam.WebcamEvent;
import com.github.sarxos.webcam.WebcamListener;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamPicker;
import com.github.sarxos.webcam.WebcamResolution;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;


/**
 * Proof of concept of how to handle webcam video stream from Java
 * 
 * @author Bartosz Firyn (SarXos)
 */
//se crea en implements ActionListener

public class WebcamViewerExample1 extends JFrame implements ActionListener, Runnable, WebcamListener, WindowListener, UncaughtExceptionHandler, ItemListener, WebcamDiscoveryListener {

	private static final long serialVersionUID = 1L;

	private Webcam webcam = null;
	private WebcamPanel panel = null;
	private WebcamPicker picker = null;
        
        //Se crea una nueva variable tipo jButton
        private javax.swing.JButton boton=new javax.swing.JButton("TOMAR FOTO");
        //private jButton boton=new jButton("TOMAR FOTO");

        private String nombreFoto="";
        
        /*public Image run3(String nom){
            run(nom);
            Image w=webcam.getImage();
            return w;
        }
        public String run2(String nom){
            run(nom);
            String w=".\\FotosEmpleados\\"+this.nombreFoto+".png";
            return w;
        }*/
        
        public void run(String nom) {

                this.nombreFoto=nom;
		Webcam.addDiscoveryListener(this);

		setTitle("Java Webcam Capture POC");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		addWindowListener(this);

		picker = new WebcamPicker();
		picker.addItemListener(this);

		webcam = picker.getSelectedWebcam();

		if (webcam == null) {
			System.out.println("No webcams found...");
			System.exit(1);
		}

		//webcam.setViewSize(WebcamResolution.VGA.getSize()); //IMPORTANTE PARA GUARDAR LAS FOTOS CON DIMENSION POR DEFECTO
                webcam.setViewSize(new Dimension(320,240));
		webcam.addWebcamListener(WebcamViewerExample1.this);

		panel = new WebcamPanel(webcam, false);
		panel.setFPSDisplayed(true);

		add(picker, BorderLayout.WEST);
		add(panel, BorderLayout.CENTER);

                //Agregamos el boton
                add(boton,BorderLayout.EAST);
                //add(boton,BorderLayout.SOUTH);
                //agregar un listener
                boton.addActionListener(this);
                
                
                
		pack();
		setVisible(true);

		Thread t = new Thread() {

			@Override
			public void run() {
				panel.start();
			}
		};
		t.setName("example-starter");
		t.setDaemon(true);
		t.setUncaughtExceptionHandler(this);
		t.start();
                //Personal.BTomarFoto.setIcon(new javax.swing.ImageIcon(".\\FotosEmpleados\\"+this.nombreFoto+".png"));
                this.setLocationRelativeTo(null);
                this.setResizable(false);
                
	}
        public BufferedImage obtenerImagen(){
            return webcam.getImage();
        }
	@Override
	public void run() {

                
		Webcam.addDiscoveryListener(this);

		setTitle("Java Webcam Capture POC");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		addWindowListener(this);

		picker = new WebcamPicker();
		picker.addItemListener(this);

		webcam = picker.getSelectedWebcam();

		if (webcam == null) {
			System.out.println("No webcams found...");
			System.exit(1);
		}

		webcam.setViewSize(WebcamResolution.VGA.getSize());
		webcam.addWebcamListener(WebcamViewerExample1.this);

		panel = new WebcamPanel(webcam, false);
		panel.setFPSDisplayed(true);

		add(picker, BorderLayout.WEST);
		add(panel, BorderLayout.CENTER);

                //Agregamos el boton
                boton.setBackground(SystemColor.control);
                boton.setBounds(new Rectangle(120, 120, 200, 200));
                add(boton,BorderLayout.EAST);
                //agregar un listener
                boton.addActionListener(this);
                
                
		pack();
		setVisible(true);

		Thread t = new Thread() {

			@Override
			public void run() {
                            
				panel.start();
			}
		};
		t.setName("example-starter");
		t.setDaemon(true);
		t.setUncaughtExceptionHandler(this);
		t.start();
	}

        
        
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new WebcamViewerExample1());
	}

	@Override
	public void webcamOpen(WebcamEvent we) {
		System.out.println("webcam open");
	}

	@Override
	public void webcamClosed(WebcamEvent we) {
		System.out.println("webcam closed");
	}

	@Override
	public void webcamDisposed(WebcamEvent we) {
		System.out.println("webcam disposed");
	}

	@Override
	public void webcamImageObtained(WebcamEvent we) {
		// do nothing
	}

	@Override
	public void windowActivated(WindowEvent e) {
	}

	@Override
	public void windowClosed(WindowEvent e) {
		webcam.close();
	}

	@Override
	public void windowClosing(WindowEvent e) {
	}

	@Override
	public void windowOpened(WindowEvent e) {
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		System.out.println("webcam viewer resumed");
		panel.resume();
	}

	@Override
	public void windowIconified(WindowEvent e) {
		System.out.println("webcam viewer paused");
		panel.pause();
	}

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		System.err.println(String.format("Exception in thread %s", t.getName()));
		e.printStackTrace();
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getItem() != webcam) {
			if (webcam != null) {

				panel.stop();

				remove(panel);

				webcam.removeWebcamListener(this);
				webcam.close();

				webcam = (Webcam) e.getItem();
				webcam.setViewSize(WebcamResolution.VGA.getSize());
				webcam.addWebcamListener(this);

				System.out.println("selected " + webcam.getName());

				panel = new WebcamPanel(webcam, false);
				panel.setFPSDisplayed(true);

				add(panel, BorderLayout.CENTER);
				pack();

				Thread t = new Thread() {

					@Override
					public void run() {
						panel.start();
					}
				};
				t.setName("example-stoper");
				t.setDaemon(true);
				t.setUncaughtExceptionHandler(this);
				t.start();
			}
		}
	}

	@Override
	public void webcamFound(WebcamDiscoveryEvent event) {
		if (picker != null) {
			picker.addItem(event.getWebcam());
		}
	}

	@Override
	public void webcamGone(WebcamDiscoveryEvent event) {
		if (picker != null) {
			picker.removeItem(event.getWebcam());
		}
	}

    @Override
    public void actionPerformed(ActionEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
            //FrameIngresos.BImagenPrenda.setIcon(new javax.swing.ImageIcon(".\\ImagenesClientes\\" + this.nombreFoto + ".png"));
        if (swith == 1) {
            if (e.getSource() == boton) {
                BufferedImage image = webcam.getImage();

                try {
                    // save image to PNG file
                    ImageIO.write(image, "PNG", new File(".\\ImagenesClientes\\" + this.nombreFoto + ".png"));
                    //Personal.BTomarFoto.setIcon(new javax.swing.ImageIcon(".\\FotosEmpleados\\"+this.nombreFoto+".png"));
                } catch (IOException ex) {
                    Logger.getLogger(WebcamViewerExample1.class.getName()).log(Level.SEVERE, null, ex);
                }
                //setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//PERMITE CERRAR LA VENTANA YA NO SE CIERRA LA APLICACION
                this.dispose();//PERMITE CERRAR LA VENTANA

            }
            try {
                FrameIngresos.BImagenPrenda.setIcon(new ImageIcon(ImageIO.read(new File(".\\ImagenesClientes\\" + this.nombreFoto + ".png"))));
            } catch (IOException ex) {
                Logger.getLogger(WebcamViewerExample1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(swith==2){
            if (e.getSource() == boton) {
                BufferedImage image = webcam.getImage();

                try {
                    // save image to PNG file
                    ImageIO.write(image, "PNG", new File(".\\ImagenesClientes\\" + this.nombreFoto + ".png"));
                    //Personal.BTomarFoto.setIcon(new javax.swing.ImageIcon(".\\FotosEmpleados\\"+this.nombreFoto+".png"));
                } catch (IOException ex) {
                    Logger.getLogger(WebcamViewerExample1.class.getName()).log(Level.SEVERE, null, ex);
                }
                //setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//PERMITE CERRAR LA VENTANA YA NO SE CIERRA LA APLICACION
                this.dispose();//PERMITE CERRAR LA VENTANA

            }
            try {
                FrameIngresos.BImagenDisenio.setIcon(new ImageIcon(ImageIO.read(new File(".\\ImagenesClientes\\" + this.nombreFoto + ".png"))));
                //FramePersonal.BSacarFoto.setIcon(new ImageIcon(ImageIO.read(new File(".\\FotosPersonal\\" + this.nombreFoto + ".png"))));
            } catch (IOException ex) {
                Logger.getLogger(WebcamViewerExample1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(swith==3){
            if (e.getSource() == boton) {
                BufferedImage image = webcam.getImage();

                try {
                    // save image to PNG file
                    ImageIO.write(image, "PNG", new File(".\\FotosPersonal\\" + this.nombreFoto + ".png"));
                    //Personal.BTomarFoto.setIcon(new javax.swing.ImageIcon(".\\FotosEmpleados\\"+this.nombreFoto+".png"));
                } catch (IOException ex) {
                    Logger.getLogger(WebcamViewerExample1.class.getName()).log(Level.SEVERE, null, ex);
                }
                //setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//PERMITE CERRAR LA VENTANA YA NO SE CIERRA LA APLICACION
                this.dispose();//PERMITE CERRAR LA VENTANA

            }
            try {
                //new ImageIcon(ImageIO.read(new File(".\\FotosPersonal\\"+this.pantNombre.getText()+".bmp")))
                FramePersonal.BSacarFoto.setIcon(new ImageIcon(ImageIO.read(new File(".\\FotosPersonal\\" + this.nombreFoto + ".png"))));
            } catch (IOException ex) {
                Logger.getLogger(WebcamViewerExample1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(swith==4){
            if(e.getSource()==boton){
                BufferedImage image=webcam.getImage();
                try{
                    ImageIO.write(image, "PNG", new File("FotoProducto.png"));
                }catch(IOException ex){
                    Logger.getLogger(WebcamViewerExample1.class.getName()).log(Level.SEVERE, null, ex);
                }
                this.dispose();
            }
        }
    }
    private int swith=1;
    
    
    public void eliminarFoto(String nom){
        File a=new File(".\\FotosEmpleados\\");
        File[] sw=a.listFiles();
        for(int i=0;i<sw.length;i++){
            System.out.println(sw[i].getName());
            if(sw[i].getName().equals(nom+".png")){
                System.out.println("esto DeberiaEliminar: "+nom+".png");
                sw[i].delete();
            }
        }
    }

    public int isSwith() {
        return swith;
    }

    public void setSwith(int swith) {
        this.swith = swith;
    }
    
    public String previsualizar(){
        BufferedImage image=webcam.getImage();
        return this.nombreFoto;
    }
}
