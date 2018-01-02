package LCDTest;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import LCD.ImpresorLCD;
import junit.framework.TestCase;

public class PruebasImpresorLCD extends TestCase
{
	//clase donde se haran las pruebas
	ImpresorLCD impresorLCD; 
	
	//Contenedor con las cadenas de caracteres corrrepondientes a la salida esperada del programa para cada digito
	private String [] digitos=new String[10];
	
	//Varaiable para guardar la cadena de caracteres correspondiente al numero 1234567890 con size=2 y espacio de 2
	private String numConSize2;

	//Varaiable para guardar la cadena de caracteres correspondiente al numero 1234567890 con size=3 y espacio de 2
	private String numConSize3;
	
	//Varaiable para guardar la cadena de caracteres correspondiente al numero 1234567890 con size=2 y espacio de 3
	private String numConEspacio3;
	
	//Variable en la cual se guarda la salida del programa
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	
	/**
	 * Escenario de prueba, incialliza el ImpresorLCD 
	 */
	private void setup1()
	{
		try
		{
			impresorLCD = new ImpresorLCD();
		}
		catch( Exception e )
        {
            fail( "No debería lanzar excepción."+e.getMessage());
        }
	
		//Se configura el sistema para que escriba a outContent
		System.setOut(new PrintStream(outContent));
	}
	
	/**
	 * Metodo encargado de inicializar las cadenas de caracteres corrrepondientes a la salida de cada digito
	 */
	private void setupDigitos()
	{
		digitos[0]= " --   \n|  |  \n|  |  \n      \n|  |  \n|  |  \n --   \n";
		digitos[1]= "      \n   |  \n   |  \n      \n   |  \n   |  \n      \n";
		digitos[2]= " --   \n   |  \n   |  \n --   \n|     \n|     \n --   \n";
		digitos[3]= " --   \n   |  \n   |  \n --   \n   |  \n   |  \n --   \n";
		digitos[4]= "      \n|  |  \n|  |  \n --   \n   |  \n   |  \n      \n";
		digitos[5]= " --   \n|     \n|     \n --   \n   |  \n   |  \n --   \n";
		digitos[6]= " --   \n|     \n|     \n --   \n|  |  \n|  |  \n --   \n";
		digitos[7]= " --   \n   |  \n   |  \n      \n   |  \n   |  \n      \n";
		digitos[8]= " --   \n|  |  \n|  |  \n --   \n|  |  \n|  |  \n --   \n";
		digitos[9]= " --   \n|  |  \n|  |  \n --   \n   |  \n   |  \n --   \n";
	}
	
	/**
	 * Metodo encargado de incializar las cadenas de caracteres correspondientes 
	 * al numero 1234567890 con size=2 y espacio de 2, con size=3 y espacio de 2
	 * y con size=2 y espacio de 3 
	 */
	private void setupNumeros()
	{
		numConSize2="       --    --          --    --    --    --    --    --   \n   |     |     |  |  |  |     |        |  |  |  |  |  |  |  \n   |     |     |  |  |  |     |        |  |  |  |  |  |  |  \n       --    --    --    --    --          --    --         \n   |  |        |     |     |  |  |     |  |  |     |  |  |  \n   |  |        |     |     |  |  |     |  |  |     |  |  |  \n       --    --          --    --          --    --    --   \n";
		numConSize3="        ---    ---           ---    ---    ---    ---    ---    ---   \n    |      |      |  |   |  |      |          |  |   |  |   |  |   |  \n    |      |      |  |   |  |      |          |  |   |  |   |  |   |  \n    |      |      |  |   |  |      |          |  |   |  |   |  |   |  \n        ---    ---    ---    ---    ---           ---    ---          \n    |  |          |      |      |  |   |      |  |   |      |  |   |  \n    |  |          |      |      |  |   |      |  |   |      |  |   |  \n    |  |          |      |      |  |   |      |  |   |      |  |   |  \n        ---    ---           ---    ---           ---    ---    ---   \n";
		numConEspacio3="        --     --            --     --     --     --     --     --    \n   |      |      |   |  |   |      |         |   |  |   |  |   |  |   \n   |      |      |   |  |   |      |         |   |  |   |  |   |  |   \n        --     --     --     --     --            --     --           \n   |   |         |      |      |   |  |      |   |  |      |   |  |   \n   |   |         |      |      |   |  |      |   |  |      |   |  |   \n        --     --            --     --            --     --     --    \n";
	}
	
	
	/**
     * Prueba el que la clase ImpresorLCD imprima correctamente cada caracter 
     */
	public void testDigitos() 
	{
		setup1();
		setupDigitos();
		for(int i =0;i<10;i++)
		{
			impresorLCD.procesar("2,"+i, 2);
			assertEquals("Error imprimiendo el digito " +i+".",digitos[i], outContent.toString());
			outContent.reset();
		}
		
	}
	
	
	/**
	 * Prueba que la clase ImpresorLCD pueda imprimir numeros competos con diferentes tamaños de numero 
	 * y espacios entre digitos.
	 * Se prueba la calse para el numero 1234567890 con size=2 y espacio de 2, con size=3 y espacio de 2
	 * y con size=2 y espacio de 3 
	 */
	public void testNumeros() 
	{
		setup1();
		setupNumeros();
		
		impresorLCD.procesar("2,1234567890", 2);
		assertEquals("Error imprimiento el numero 1234567890 con tamaño igual a 2.",numConSize2, outContent.toString());
		outContent.reset();
		
		impresorLCD.procesar("3,1234567890", 2);
		assertEquals("Error imprimiento el numero 1234567890 con tamaño igual a 3.",numConSize3, outContent.toString());
		outContent.reset();
		
		impresorLCD.procesar("2,1234567890", 3);
		assertEquals("Error imprimiento el numero 1234567890 con tamaño igual a 2 y 3 espacios entre digitos.",numConEspacio3, outContent.toString());
		outContent.reset();
		
	}
	
	
	/**
	 * Prueba que la clase ImpresorLCD lance IllegalArgumentException cuando corresponde:
	 * Si el size no es numerico
	 * Si el comando contiene entradas de menos
	 * Si comando no contiene el caracter ,
	 * Si el size no esta en el rango
	 * Si el comando tiene entradas de mas
	 * Si el numero contiene digitos no numericos
	 */
	public void testExcepciones()
	{
		setup1();
		
		try 
		{
			impresorLCD.procesar("a,1234567890", 2);
	        fail("Se esperaba un IllegalArgumentException ya que el size indicado no es numerico");
	    } 
		catch (IllegalArgumentException IllegalArgumentException) 
		{
	        assertEquals(IllegalArgumentException.getMessage(), "Parametro Size [a] no es un numero");
	    }
		
		try 
		{
			impresorLCD.procesar("1,", 2);
	        fail("Se esperaba un IllegalArgumentException ya que el comando contiene entradas de menos");
	    } 
		catch (IllegalArgumentException IllegalArgumentException) 
		{
	        assertEquals("Cadena 1, no contiene los parametros requeridos",IllegalArgumentException.getMessage());
	    }
		
		try 
		{
			impresorLCD.procesar("1", 2);
	        fail("Se esperaba un IllegalArgumentException ya que el comando no contiene el caracter ,");
	    } 
		catch (IllegalArgumentException IllegalArgumentException) 
		{
	        assertEquals("Cadena 1 no contiene caracter ,",IllegalArgumentException.getMessage());
	    }
		
		try 
		{
			impresorLCD.procesar("11,1", 2);
	        fail("Se esperaba un IllegalArgumentException ya que el size no esta en el rango");
	    } 
		catch (IllegalArgumentException IllegalArgumentException) 
		{
	        assertEquals("El parametro size [11] debe estar entre 1 y 10",IllegalArgumentException.getMessage());
	    }
		
		try 
		{
			impresorLCD.procesar("1,1,1", 2);
	        fail("Se esperaba un IllegalArgumentException ya que el comando tiene entradas de mas");
	    } 
		catch (IllegalArgumentException IllegalArgumentException) 
		{
	        assertEquals("Cadena 1,1,1 contiene mas caracter ,",IllegalArgumentException.getMessage());
	    }
		
		try 
		{
			impresorLCD.procesar("1,a", 2);
	        fail("Se esperaba un IllegalArgumentException ya que el numero tiene un digito no numerico");
	    } 
		catch (IllegalArgumentException IllegalArgumentException) 
		{
	        assertEquals("Caracter a no es un digito",IllegalArgumentException.getMessage());
	    }
		
	}
	

}
