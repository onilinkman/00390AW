
package pkg00390aw;

import java.awt.Rectangle;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import javax.swing.JButton;

public class ClaseBotones implements Serializable{
    private JButton Mesas[];
    private JButton Salas[];
    private JButton Barras[];
    private Rectangle[] huesosMesas;
    private Rectangle[] huesosSalas;
    private Rectangle[] huesosBarras;

    public Rectangle[] getHuesosMesas() {
        return huesosMesas;
    }

    public void setHuesosMesas(Rectangle[] huesosMesas) {
        this.huesosMesas = huesosMesas;
    }

    public Rectangle[] getHuesosSalas() {
        return huesosSalas;
    }

    public void setHuesosSalas(Rectangle[] huesosSalas) {
        this.huesosSalas = huesosSalas;
    }

    public Rectangle[] getHuesosBarras() {
        return huesosBarras;
    }

    public void setHuesosBarras(Rectangle[] huesosBarras) {
        this.huesosBarras = huesosBarras;
    }
    public ClaseBotones(){
    }
    public void guardar(){
        try{
            ObjectOutputStream o1=new ObjectOutputStream(new FileOutputStream("flat.dat"));
            o1.writeObject(this);
            o1.close();
        }catch(Exception e){
            System.out.println("Error: "+e);
        }
    }
    
    public ClaseBotones recuperar() throws IOException, ClassNotFoundException{
        ObjectInputStream o1=new ObjectInputStream(new FileInputStream("flat.dat"));
        ClaseBotones l1=(ClaseBotones)o1.readObject();
        o1.close();
        return l1;
    }

    public JButton[] getMesas() {
        return Mesas;
    }

    public void setMesas(JButton[] Mesas) {
        this.Mesas = Mesas;
    }

    public JButton[] getSalas() {
        return Salas;
    }

    public void setSalas(JButton[] Salas) {
        this.Salas = Salas;
    }

    public JButton[] getBarras() {
        return Barras;
    }

    public void setBarras(JButton[] Barras) {
        this.Barras = Barras;
    }
    
}
