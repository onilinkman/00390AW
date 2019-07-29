
package pkg00390aw;

import javax.swing.JOptionPane;

/**
 *
 * @author cuiza
 */
public class cod {
    
    public static void main (String []args){
        
        dividir();
       // ejecNUmALetra();
    }
    
    
    
    
    public static void dividir(){
        
        double number =45.768;
        
        long ipart=(long) number;
        double fpart=number-ipart;
        
        int dec=(int) Math.round((fpart*100));
        System.out.println(ipart+"\n"+fpart+"\n"+dec);
    }
    
    
    public static void ejecNUmALetra(){
         numALetras dator= new numALetras();
         double number =98.695;//////
        
         long ipart=(long) number;
         double fpart=number-ipart;
        
        int dec=(int) Math.round((fpart*100));
       
        
        String nombre=dator.convertir((int) ipart);
        
        System.out.println(nombre+ " CON "+dec+ "/100 Bs.");
        
    }
    
}
