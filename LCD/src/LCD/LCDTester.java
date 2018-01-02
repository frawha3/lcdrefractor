package LCD;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * Clase que permite ejecutar el ImpresorLCD para imprimir números en estilo de una pantalla LCD
 */
public class LCDTester 
{
	//Constante con cadena de caracter con la cual el suuario indica el fin de la entrada
    static final String CADENA_FINAL = "0,0";
    
    /**
     * Este metodo ejecuta el programa
     * @param args no son necesarios.
     */
    public static void main(String[] args) 
    {

        // Guarda las lineas de entrada
        List<String> listaComando = new ArrayList<>();
        
        //Linea de entrada
        String comando;
        
        //Espacio entre digitos de un numero 
        int espacioDig;
        

        try (Scanner lector = new Scanner(System.in)) 
        {
            System.out.print("Espacio entre Digitos (0 a 5): ");
            comando = lector.next();

            // Valida si la linea de commando es un numero
            if (!ImpresorLCD.isNumeric(comando))
            {
            	 throw new IllegalArgumentException("Cadena " + comando
                         + " no es un entero");
            }
            
            espacioDig = Integer.parseInt(comando);
            
            // se valida que el espaciado este entre 0 y 5
            if(espacioDig <0 || espacioDig >5)
            {
                throw new IllegalArgumentException("El espacio entre "
                        + "digitos debe estar entre 0 y 5");
            }
            
            System.out.print("Entrada: ");
            
            //Lee las lineas de entrada y las guarda en listaComando
            while (!(comando = lector.next()).equalsIgnoreCase(CADENA_FINAL))
            {
            	System.out.print("Entrada: ");
            	listaComando.add(comando);
            }
            
            ImpresorLCD impresorLCD = new ImpresorLCD();
            Iterator<String> iterator = listaComando.iterator();
            
            //Procesa cada entrada de listaComando llamado a una instancia de la clase impresorLCD
            while (iterator.hasNext()) 
            {
                  impresorLCD.procesar(iterator.next(), espacioDig);
             
            }
        }  
        catch (Exception ex) 
        {
            System.out.println("Error: "+ex.getMessage());
        }

    }

}