package LCDTest;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import LCD.LCDTester;
import junit.framework.TestCase;

public class PruebasLCDTester extends TestCase
{
	//Variable en la cual se guarda la salida del programa
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	
	//Variable donde se guarda la salida esperada del programa 
	private String salida;
	
	/**
	 * Prueba que el programa imprima correctamente el numero 12345 con size=2 
	 * y el numero 67890 con size=3 ambos con espacio de 0 entre digitos
	 */
	public void testNumeros()
	{
		//Se configura el sistema para que escriba a outContent
		System.setOut(new PrintStream(outContent));
		
		//Salida esperada del programa
		salida="Espacio entre Digitos (0 a 5): Entrada: Entrada: Entrada:      --  --      --  -- \n   |   |   ||  ||   |   \n   |   |   ||  ||   |   \n     --  --  --  --  -- \n   ||      |   |   ||  |\n   ||      |   |   ||  |\n     --  --      --  -- \n ---  ---  ---  ---  --- \n|        ||   ||   ||   |\n|        ||   ||   ||   |\n|        ||   ||   ||   |\n ---       ---  ---      \n|   |    ||   |    ||   |\n|   |    ||   |    ||   |\n|   |    ||   |    ||   |\n ---       ---  ---  --- \n";
		
		//Se configura la entrada del programa 
		System.setIn(new ByteArrayInputStream("0\n2,123456\n3,67890\n0,0".getBytes()));
		
		//Se ejecuta el programa
		LCDTester.main(null);
		
		//Se valida que la respuesta obtenida sea igual a la esperada 
		assertEquals("Error", salida, outContent.toString());
	}
	
	/**
	 * Prueba que el programa notifique correctamente cuando el espacio indicado no es un entero
	 */
	public void testValidarEspacioEntero()
	{
		//Se configura el sistema para que escriba a outContent
		System.setOut(new PrintStream(outContent));
		
		//Salida esperada del programa
		salida="Espacio entre Digitos (0 a 5): Error: Cadena a no es un entero\n";
		
		//Se configura la entrada del programa
		System.setIn(new ByteArrayInputStream("a\n2,1234\n0,0".getBytes()));
		
		//Se ejecuta el programa
		LCDTester.main(null);
		
		//Se valida que la respuesta obtenida sea igual a la esperada 
		assertEquals("Error validando que la el espacio entre digitos sea numerico", salida, outContent.toString());
		
	}
	
	/**
	 * Prueba que el programa notifique correctamente cuando el espacio indicado no esta entre 0 y 5
	 */
	public void testValidarValorEspacio()
	{
		//Se configura el sistema para que escriba a outContent
		System.setOut(new PrintStream(outContent));
		
		//Salida esperada del programa
		salida="Espacio entre Digitos (0 a 5): Error: El espacio entre digitos debe estar entre 0 y 5\n";
		
		//Se configura la entrada del programa
		System.setIn(new ByteArrayInputStream("6\n2,1234\n0,0".getBytes()));
		
		//Se ejecuta el programa
		LCDTester.main(null);
		
		//Se valida que la respuesta obtenida sea igual a la esperada 
		assertEquals("Error validando que el valor del espacio este entre 0 y 5", salida, outContent.toString());
	}
	
	
}