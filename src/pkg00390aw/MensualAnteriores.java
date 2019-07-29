package pkg00390aw;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Calendar;

public class MensualAnteriores implements Serializable {

    private String[][] gastos;
    private String[][] balDia;
    private String[] balMes;

    public MensualAnteriores() {
        gastos = null;
        balDia = null;
        balMes = null;
    }

    /*public String fecha() {
        String f = "";
        int dia, mes, anio;
        Calendar calendario = Calendar.getInstance();
        dia = calendario.get(Calendar.DAY_OF_MONTH);
        mes = calendario.get(Calendar.MONTH) + 1;
        anio = calendario.get(Calendar.YEAR);
        f = dia + "_" + mes + "_" + anio;
        return f;
    }*/
    public void guardar(MensualAnteriores l1, String path) {
        try {
            ObjectOutputStream o1 = new ObjectOutputStream(new FileOutputStream(path));
            o1.writeObject(l1);
            o1.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public MensualAnteriores recuperar(String path) throws IOException, ClassNotFoundException {
        System.out.println("Dir: " + path);
        ObjectInputStream o1 = new ObjectInputStream(new FileInputStream(path));
        MensualAnteriores l1 = (MensualAnteriores) o1.readObject();
        o1.close();
        return l1;
    }

    public String[][] getGastos() {
        return gastos;
    }

    public void setGastos(String[][] gastos) {
        this.gastos = gastos;
    }

    public String[][] getBalDia() {
        return balDia;
    }

    public void setBalDia(String[][] balDia) {
        this.balDia = balDia;
    }

    public String[] getBalMes() {
        return balMes;
    }

    public void setBalMes(String[] balMes) {
        this.balMes = balMes;
    }

}
