package pkg00390aw;







import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

public class codcontrol {
    
    //datos para generar codigo de control
    private String authorizationNumber; //Número de Autorización 
    private String invoiceNumber; //Número de Factura
    private String NITCI; //nit o carnet de identidad
    private String dateOfTransaction; //Fecha de la Transacción  EJEMPLO 2007/07/02 convertir 20070702
    private String transactionAmount; //Monto de la Transacción
    private String dosageKey;//Llave 
    //otras variables 
    private String fiveDigitsVero;
    private String stringDKey;
    private int sumProduct;
    

     String ultimoDate;
    
    /**
     * Constructor de clase
     */
    public codcontrol(){}
    
    /**
     * Genera el codigo de control
     * @param aNumber Numero de autorizacion
     * @param iNumber Numero de factura
     * @param nitci NIT o CI 
     * @param dTransaction fecha de transaccion de la forma:
     *                       2007/07/02 a 20070702
     * @param tAmount Monto de la transacción 
     * @param dKey Llave 
     * @return String Codigo de control
     */
    public String generate(String aNumber, String iNumber, String nitci, 
                           String dTransaction, String tAmount, String dKey){
        this.authorizationNumber = aNumber;
        this.invoiceNumber = iNumber;
        this.NITCI =nitci;
        this.dateOfTransaction = dTransaction;
        this.transactionAmount = roundUp(tAmount);
        this.dosageKey = dKey;
        invoiceNumber = addVeroDigit(invoiceNumber,2);
        NITCI = addVeroDigit(NITCI,2);
        dateOfTransaction = addVeroDigit(dateOfTransaction,2);
        this.transactionAmount = addVeroDigit(transactionAmount,2);
        Long sumOfVariables = Long.valueOf(invoiceNumber)
                              + Long.valueOf(NITCI)
                              + Long.valueOf(dateOfTransaction)
                              + Long.valueOf(transactionAmount);
        ultimoDate =sumOfVariables+"AE";
        
    
        
        
        return null;
       
    }//end:generateControlCode

    public String getUltimoDate() {
        return ultimoDate;
    }

    public void setUltimoDate(String ultimoDate) {
        this.ultimoDate = ultimoDate;
    }
        
  
    private String addVeroDigit(String value,int max){
        for(int i=1;i<=max;i++)
            value += Vero.generateVero(value);            
        return value;
    }
    
    /**
     * Redondea hacia arriba
     * @param value cadena con valor numerico de la forma 123 123.4 123,4
     */
    private String roundUp(String value){        
        //reemplaza (,) por (.)
        value = value.replace(",", ".");
        //redondea a 0 decimales
        BigDecimal valueBD = new BigDecimal(Double.parseDouble(value));
        valueBD = valueBD.setScale(0, BigDecimal.ROUND_HALF_UP);        
        return String.valueOf(valueBD);
    }
    
    /* metodos usados solo para realizar el testeo */
    public String getFiveDigitsVero() {
        return fiveDigitsVero;
    }

    public String getStringDKey() {
        return stringDKey;
    }

    public int getSumProduct() {
        return sumProduct;
    }

   
    
}//end:class