package pkg00390aw;

import java.util.Calendar;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Main_1 {

    public static void main(String[] args) {
        //System.out.println(JOptionPane.showConfirmDialog(null, "Desea continuar?", "mensaje", JOptionPane.YES_NO_OPTION)==0);
        //System.out.println((14*60+54)-(15*60+1));
        String as="sdfse_dsg.xls";
        String nom="ds";
        for(int i=0;i<=as.length()-nom.length();i++){
            System.out.println(as.substring(i, i+nom.length()));
        }
        
    }
    
    
    
    public static String generarCodigo(String[] s) {
        String codigo = "";
        boolean sw=true;
        
        while(sw){
        for (int i = 0; i < 1; i++) {
            int num = (int) (Math.random() * 1000);
            while (!(num >= 97 && num <= 122)) {
                num = (int) (Math.random() * 1000);
            }
            codigo = codigo + (char) num;
            for(int j=0;j<s.length;j++){
                if(s[j].equals(codigo)){
                    sw=true;
                }
            }
        }}

        return codigo;
    }

}
