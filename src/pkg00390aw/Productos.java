package pkg00390aw;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Stack;
import javax.swing.JOptionPane;

public class Productos implements Serializable {

    private Stack<Cerveza> cCerveza;
    private Stack<Licores> cLicores;
    private Stack<Refrescos> cRefrescos;
    private Stack<Cocteles> cCocteles;
    private Stack<Cigarrillos> cCigarrilos;
    private Stack<Otros> cOtros;

    public Productos() {
        this.cCerveza = new Stack<Cerveza>();
        this.cLicores = new Stack<Licores>();
        this.cRefrescos = new Stack<Refrescos>();
        this.cCocteles = new Stack<Cocteles>();
        this.cCigarrilos = new Stack<Cigarrillos>();
        this.cOtros = new Stack<Otros>();
    }

    public double obtenerVolumen(int cantidad, String nomProducto) {//Esto ayudara a obtener volumen total que se vendio, Solo servira para cerveza, licores, refrescos
        String tipo = obtenerTipo(nomProducto);
        double volumen = 0;
        if (tipo.equals("Cerveza")) {
            int posCer = obtenerPosicionCerveza(nomProducto);
            if (posCer != -1) {
                volumen = this.cCerveza.get(posCer).getVolumen() * cantidad;
            }
        } else if (tipo.equals("Licores")) {
            int posCer = obtenerPosicionLicor(nomProducto);
            if (posCer != -1) {
                volumen = this.cLicores.get(posCer).getVolumen() * cantidad;
            }
        } else if (tipo.equals("Refrescos")) {
            int posCer = obtenerPosicionRefrescos(nomProducto);
            if (posCer != -1) {
                volumen = this.cRefrescos.get(posCer).getVolumen() * cantidad;
            }
        } else if (tipo.equals("Cigarrillos")) {

        } else if (tipo.equals("Cocteles")) {
            int posCer = obtenerPosicionCocteles(nomProducto);
            if (posCer != -1) {
                Cocteles c = this.cCocteles.get(posCer);
                double vol1 = c.getVolumen1();
                double vol2 = c.getVolumen2();
                double vol3 = c.getVolumen3();
                volumen = (vol1 + vol2 + vol3) * cantidad;
            }
        }
        return volumen;
    }

    public void devolverProducto(int cantidad, String nomProducto) {
        String tipo = obtenerTipo(nomProducto);
        if (tipo.equals("Cerveza")) {
            devolverCerveza(cantidad, nomProducto);
        } else if (tipo.equals("Licores")) {
            devolverLicor(cantidad, nomProducto);
        } else if (tipo.equals("Refrescos")) {
            devolverRefresco(cantidad, nomProducto);
        } else if (tipo.equals("Cigarrillos")) {
            devolverCigarrilos(cantidad, nomProducto);
        } else if (tipo.equals("Cocteles")) {
            devolverCocteles(cantidad, nomProducto);
        }
    }

    public void aumentarProducto(int cantidad, String nomProducto) {
        String tipo = obtenerTipo(nomProducto);
        if (tipo.equals("Cerveza")) {
            int posCer = obtenerPosicionCerveza(nomProducto);
            if (posCer != -1) {
                double volumenCer = this.cCerveza.get(posCer).getVolumen() * cantidad;
                this.cCerveza.get(posCer).aumentarStockAlmacen(this.cCerveza.get(posCer).getStockAlmacen().getStockVolTotal() + volumenCer);
                //this.cCerveza.get(posCer).aumentarStockVendido(this.cCerveza.get(posCer).getStockVendido().getStockVolTotal()+volumenCer);
            }
        } else if (tipo.equals("Licores")) {
            int posCer = obtenerPosicionLicor(nomProducto);
            if (posCer != -1) {
                double volumenCer = this.cLicores.get(posCer).getVolumen() * cantidad;
                this.cLicores.get(posCer).aumentarStockAlmacen(this.cLicores.get(posCer).getStockAlmacen().getStockVolTotal() + volumenCer);
                //this.cLicores.get(posCer).aumentarStockVendido(this.cLicores.get(posCer).getStockVendido().getStockVolTotal() + volumenCer);
            }
        } else if (tipo.equals("Refrescos")) {
            int posCer = obtenerPosicionRefrescos(nomProducto);
            if (posCer != -1) {
                double volumenCer = this.cRefrescos.get(posCer).getVolumen() * cantidad;
                this.cRefrescos.get(posCer).aumentarStockAlmacen(this.cRefrescos.get(posCer).getStockAlmacen().getStockVolTotal() + volumenCer);
                //this.cRefrescos.get(posCer).aumentarStockVendido(this.cRefrescos.get(posCer).getStockVendido().getStockVolTotal() + volumenCer);
            }
        } else if (tipo.equals("Cigarrillos")) {
            int posCer = obtenerPosicionCigarrillos(nomProducto);
            if (posCer != -1) {
                this.cCigarrilos.get(posCer).aumentarStockAlmacen(this.cCigarrilos.get(posCer).getStockAlmacen().getStockTotalCigarro() + cantidad);
                //this.cCigarrilos.get(posCer).aumentarStockVendido(this.cCigarrilos.get(posCer).getStockVendido().getStockTotalCigarro() + cantidad);
            }
        }
    }

    public void venderProducto(int cantidad, String nomProducto) {
        String tipo = obtenerTipo(nomProducto);
        if (tipo.equals("Cerveza")) {

            venderCerveza(cantidad, nomProducto);

        } else if (tipo.equals("Licores")) {
            venderLicor(cantidad, nomProducto);
        } else if (tipo.equals("Refrescos")) {
            venderRefresco(cantidad, nomProducto);
        } else if (tipo.equals("Cigarrillos")) {
            venderCigarrillos(cantidad, nomProducto);
        } else if (tipo.equals("Cocteles")) {
            venderCocteles(cantidad, nomProducto);
        }
    }

    public void devolverCigarrilos(int cantidad, String nomProducto) {
        int posCer = obtenerPosicionCigarrillos(nomProducto);
        if (posCer != -1) {
            this.cCigarrilos.get(posCer).aumentarStockAlmacen(this.cCigarrilos.get(posCer).getStockAlmacen().getStockTotalCigarro() + cantidad);//
            this.cCigarrilos.get(posCer).aumentarStockVendido(this.cCigarrilos.get(posCer).getStockVendido().getStockTotalCigarro() - cantidad);
        }
    }

    public void venderCigarrillos(int cantidad, String nomProducto) {
        int posCer = obtenerPosicionCigarrillos(nomProducto);
        if (posCer != -1) {
            this.cCigarrilos.get(posCer).aumentarStockAlmacen(this.cCigarrilos.get(posCer).getStockAlmacen().getStockTotalCigarro() - cantidad);
            this.cCigarrilos.get(posCer).aumentarStockVendido(this.cCigarrilos.get(posCer).getStockVendido().getStockTotalCigarro() + cantidad);
        }
    }

    public void venderRefresco(int cantidad, String nomProducto) {
        int posCer = obtenerPosicionRefrescos(nomProducto);
        if (posCer != -1) {
            double volumenCer = this.cRefrescos.get(posCer).getVolumen() * cantidad;
            this.cRefrescos.get(posCer).aumentarStockAlmacen(this.cRefrescos.get(posCer).getStockAlmacen().getStockVolTotal() - volumenCer);
            this.cRefrescos.get(posCer).aumentarStockVendido(this.cRefrescos.get(posCer).getStockVendido().getStockVolTotal() + volumenCer);
        }
    }

    public void devolverRefresco(int cantidad, String nomProducto) {
        int posCer = obtenerPosicionRefrescos(nomProducto);
        if (posCer != -1) {
            double volumenCer = this.cRefrescos.get(posCer).getVolumen() * cantidad;
            this.cRefrescos.get(posCer).aumentarStockAlmacen(this.cRefrescos.get(posCer).getStockAlmacen().getStockVolTotal() + volumenCer);
            this.cRefrescos.get(posCer).aumentarStockVendido(this.cRefrescos.get(posCer).getStockVendido().getStockVolTotal() - volumenCer);
        }
    }

    public void venderLicor(int cantidad, String nomProducto) {
        int posCer = obtenerPosicionLicor(nomProducto);
        if (posCer != -1) {
            double volumenCer = this.cLicores.get(posCer).getVolumen() * cantidad;
            this.cLicores.get(posCer).aumentarStockAlmacen(this.cLicores.get(posCer).getStockAlmacen().getStockVolTotal() - volumenCer);
            this.cLicores.get(posCer).aumentarStockVendido(this.cLicores.get(posCer).getStockVendido().getStockVolTotal() + volumenCer);
        }
    }

    public void devolverLicor(int cantidad, String nomProducto) {
        int posCer = obtenerPosicionLicor(nomProducto);
        if (posCer != -1) {
            double volumenCer = this.cLicores.get(posCer).getVolumen() * cantidad;
            this.cLicores.get(posCer).aumentarStockAlmacen(this.cLicores.get(posCer).getStockAlmacen().getStockVolTotal() + volumenCer);
            this.cLicores.get(posCer).aumentarStockVendido(this.cLicores.get(posCer).getStockVendido().getStockVolTotal() - volumenCer);
        }
    }

    public void venderCerveza(int cantidad, String nomProducto) {
        int posCer = obtenerPosicionCerveza(nomProducto);
        if (posCer != -1) {
            double volumenCer = this.cCerveza.get(posCer).getVolumen() * cantidad;
            this.cCerveza.get(posCer).aumentarStockAlmacen(this.cCerveza.get(posCer).getStockAlmacen().getStockVolTotal() - volumenCer);
            this.cCerveza.get(posCer).aumentarStockVendido(this.cCerveza.get(posCer).getStockVendido().getStockVolTotal() + volumenCer);
        }
    }

    public void devolverCerveza(int cantidad, String nomProducto) {
        int posCer = obtenerPosicionCerveza(nomProducto);
        if (posCer != -1) {
            double volumenCer = this.cCerveza.get(posCer).getVolumen() * cantidad;
            this.cCerveza.get(posCer).aumentarStockAlmacen(this.cCerveza.get(posCer).getStockAlmacen().getStockVolTotal() + volumenCer);
            this.cCerveza.get(posCer).aumentarStockVendido(this.cCerveza.get(posCer).getStockVendido().getStockVolTotal() - volumenCer);
        }
    }

    public void devolverCocteles(int cantidad, String nomProducto) {
        int i = obtenerPosicionCocteles(nomProducto);
        if (i != -1) {
            Cocteles c = this.cCocteles.get(i);//obtenemos el coctel para obtener las bebidas y los volumenes a reducir de las bebidas
            int posCer = obtenerPosicionCerveza(c.getBebida1());
            if (posCer != -1) {
                double volumenCer = c.getVolumen1() * cantidad;
                this.cCerveza.get(posCer).aumentarStockAlmacen(this.cCerveza.get(posCer).getStockAlmacen().getStockVolTotal() + volumenCer);
                this.cCerveza.get(posCer).aumentarStockVendido(this.cCerveza.get(posCer).getStockVendido().getStockVolTotal() - volumenCer);
            }
            int posLic1 = obtenerPosicionLicor(c.getBebida2());
            if (posLic1 != -1) {
                double volumenLic1 = c.getVolumen2() * cantidad;
                this.cLicores.get(posLic1).aumentarStockAlmacen(this.cLicores.get(posLic1).getStockAlmacen().getStockVolTotal() + volumenLic1);
                this.cLicores.get(posLic1).aumentarStockVendido(this.cLicores.get(posLic1).getStockVendido().getStockVolTotal() - volumenLic1);
            }
            int posLic2 = obtenerPosicionLicor(c.getBebida3());
            if (posLic2 != -1) {
                double volumenlic2 = c.getVolumen3() * cantidad;
                this.cLicores.get(posLic2).aumentarStockAlmacen(this.cLicores.get(posLic2).getStockAlmacen().getStockVolTotal() + volumenlic2);
                this.cLicores.get(posLic2).aumentarStockVendido(this.cLicores.get(posLic2).getStockVendido().getStockVolTotal() - volumenlic2);
            }
        } else {
            JOptionPane.showMessageDialog(null, "NO SE ENCONTRO EL PRODUCTO REGISTRADO");
        }
    }

    public void venderCocteles(int cantidad, String nomProducto) {
        int i = obtenerPosicionCocteles(nomProducto);
        if (i != -1) {
            Cocteles c = this.cCocteles.get(i);//obtenemos el coctel para obtener las bebidas y los volumenes a reducir de las bebidas
            int posCer = obtenerPosicionCerveza(c.getBebida1());
            if (posCer != -1) {
                double volumenCer = c.getVolumen1() * cantidad;
                this.cCerveza.get(posCer).aumentarStockAlmacen(this.cCerveza.get(posCer).getStockAlmacen().getStockVolTotal()-volumenCer);
                this.cCerveza.get(posCer).aumentarStockVendido(this.cCerveza.get(posCer).getStockVendido().getStockVolTotal()+volumenCer);
            }
            int posLic1 = obtenerPosicionLicor(c.getBebida2());
            if (posLic1 != -1) {
                double volumenLic1 = c.getVolumen2() * cantidad;
                this.cLicores.get(posLic1).aumentarStockAlmacen(this.cLicores.get(posLic1).getStockAlmacen().getStockVolTotal()-volumenLic1);
                this.cLicores.get(posLic1).aumentarStockVendido(this.cLicores.get(posLic1).getStockVendido().getStockVolTotal()+volumenLic1);
            }
            int posLic2 = obtenerPosicionLicor(c.getBebida3());
            if (posLic2 != -1) {
                double volumenlic2 = c.getVolumen3() * cantidad;
                this.cLicores.get(posLic2).aumentarStockAlmacen(this.cLicores.get(posLic2).getStockAlmacen().getStockVolTotal()-volumenlic2);
                this.cLicores.get(posLic2).aumentarStockVendido(this.cLicores.get(posLic2).getStockVendido().getStockVolTotal()+volumenlic2);
            }
        } else {
            JOptionPane.showMessageDialog(null, "NO SE ENCONTRO EL PRODUCTO REGISTRADO");
        }
    }

    public String obtenerTipo(String producto) {//Obtiene el tipo dependiendo del nombre del producto
        String tipo = "";
        Stack<Cerveza> cer = new Stack<Cerveza>();
        cer.addAll(this.cCerveza);
        while (!cer.isEmpty()) {
            Cerveza c = cer.pop();
            if (c.getNomProducto().equals(producto)) {
                return "Cerveza";
            }
        }

        Stack<Licores> lic = new Stack<Licores>();
        lic.addAll(this.cLicores);
        while (!lic.isEmpty()) {
            Licores l = lic.pop();
            if (l.getNomProducto().equals(producto)) {
                return "Licores";
            }
        }

        Stack<Refrescos> ref = new Stack<Refrescos>();
        ref.addAll(this.cRefrescos);
        while (!ref.isEmpty()) {
            Refrescos r = ref.pop();
            if (r.getNomProducto().equals(producto)) {
                return "Refrescos";
            }
        }

        Stack<Cigarrillos> cir = new Stack<Cigarrillos>();
        cir.addAll(this.cCigarrilos);
        while (!cir.isEmpty()) {
            Cigarrillos c = cir.pop();
            if (c.getNomProducto().equals(producto)) {
                return "Cigarrillos";
            }
        }

        Stack<Cocteles> coc = new Stack<Cocteles>();
        coc.addAll(this.cCocteles);
        while (!coc.isEmpty()) {
            Cocteles c = coc.pop();
            if (c.getNomProducto().equals(producto)) {
                return "Cocteles";
            }
        }
        return tipo;
    }

    public boolean existeProducto(String producto) {//Obtiene el tipo dependiendo del nombre del producto
        Stack<Cerveza> cer = new Stack<Cerveza>();
        cer.addAll(this.cCerveza);
        while (!cer.isEmpty()) {
            Cerveza c = cer.pop();
            if (c.getNomProducto().equals(producto)) {
                return true;
            }
        }

        Stack<Licores> lic = new Stack<Licores>();
        lic.addAll(this.cLicores);
        while (!lic.isEmpty()) {
            Licores l = lic.pop();
            if (l.getNomProducto().equals(producto)) {
                return true;
            }
        }

        Stack<Refrescos> ref = new Stack<Refrescos>();
        ref.addAll(this.cRefrescos);
        while (!ref.isEmpty()) {
            Refrescos r = ref.pop();
            if (r.getNomProducto().equals(producto)) {
                return true;
            }
        }

        Stack<Cigarrillos> cir = new Stack<Cigarrillos>();
        cir.addAll(this.cCigarrilos);
        while (!cir.isEmpty()) {
            Cigarrillos c = cir.pop();
            if (c.getNomProducto().equals(producto)) {
                return true;
            }
        }

        Stack<Cocteles> coc = new Stack<Cocteles>();
        coc.addAll(this.cCocteles);
        while (!coc.isEmpty()) {
            Cocteles c = coc.pop();
            if (c.getNomProducto().equals(producto)) {
                return true;
            }
        }
        return false;
    }

    public void guardar(Productos p) {
        try {
            ObjectOutputStream o1 = new ObjectOutputStream(new FileOutputStream("datosProductos.dat"));
            o1.writeObject(p);
            o1.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public Productos recuperar() throws IOException, ClassNotFoundException {
        ObjectInputStream o1 = new ObjectInputStream(new FileInputStream("datosProductos.dat"));
        Productos l1 = (Productos) o1.readObject();
        o1.close();
        return l1;
    }

    public int obtenerPosicionCocteles(String nomProducto) {
        int i = 0;
        Stack<Cocteles> l = new Stack<Cocteles>();
        l.addAll(this.cCocteles);
        int max = l.size() - 1;
        while (!l.isEmpty()) {
            if (l.pop().getNomProducto().equals(nomProducto)) {
                return max - i;
            }
            i++;
        }
        return -1;
    }

    public int obtenerPosicionCigarrillos(String nomProducto) {
        int i = 0;
        Stack<Cigarrillos> l = new Stack<Cigarrillos>();
        l.addAll(this.cCigarrilos);
        int max = l.size() - 1;
        while (!l.isEmpty()) {
            if (l.pop().getNomProducto().equals(nomProducto)) {
                return max - i;
            }
            i++;
        }
        return -1;
    }

    public int obtenerPosicionRefrescos(String nomProducto) {
        int i = 0;
        Stack<Refrescos> l = new Stack<Refrescos>();
        l.addAll(this.cRefrescos);
        int max = l.size() - 1;
        while (!l.isEmpty()) {
            if (l.pop().getNomProducto().equals(nomProducto)) {
                return max - i;
            }
            i++;
        }
        return -1;
    }

    public int obtenerPosicionLicor(String nomProducto) {
        int i = 0;
        Stack<Licores> l = new Stack<Licores>();
        l.addAll(this.cLicores);
        int max = l.size() - 1;
        while (!l.isEmpty()) {
            if (l.pop().getNomProducto().equals(nomProducto)) {
                return max - i;
            }
            i++;
        }
        return -1;
    }

    public int obtenerPosicionCerveza(String nomProducto) {
        int i = 0;
        Stack<Cerveza> c = new Stack<Cerveza>();
        c.addAll(this.cCerveza);
        int max = c.size() - 1;
        while (!c.isEmpty()) {

            if (c.pop().getNomProducto().equals(nomProducto)) {
                return max - i;
            }
            i++;
        }
        return -1;
    }

    public Stack<Cerveza> getcCerveza() {
        return cCerveza;
    }

    public void setcCerveza(Stack<Cerveza> cCerveza) {
        this.cCerveza = cCerveza;
    }

    public Stack<Licores> getcLicores() {
        return cLicores;
    }

    public void setcLicores(Stack<Licores> cLicores) {
        this.cLicores = cLicores;
    }

    public Stack<Refrescos> getcRefrescos() {
        return cRefrescos;
    }

    public void setcRefrescos(Stack<Refrescos> cRefrescos) {
        this.cRefrescos = cRefrescos;
    }

    public Stack<Cocteles> getcCocteles() {
        return cCocteles;
    }

    public void setcCocteles(Stack<Cocteles> cCocteles) {
        this.cCocteles = cCocteles;
    }

    public Stack<Cigarrillos> getcCigarrilos() {
        return cCigarrilos;
    }

    public void setcCigarrilos(Stack<Cigarrillos> cCigarrilos) {
        this.cCigarrilos = cCigarrilos;
    }

    public Stack<Otros> getcOtros() {
        return cOtros;
    }

    public void setcOtros(Stack<Otros> cOtros) {
        this.cOtros = cOtros;
    }

}
