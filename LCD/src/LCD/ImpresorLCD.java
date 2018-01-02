package LCD;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.HashMap; 


/**
 * Clase que imprime números en estilo de una pantalla LCD
 */
public class ImpresorLCD {

    //Puntos fijos
	//Coordenada de la esquina superior izq del digito
    private final int[] pf1;
    
    //Coordenada del lado izq del centro del digito
    private final int[] pf2;

	//Coordenada de la esquina inferior izq del digito    
    private final int[] pf3;
    
	//Coordenada de la derecha del centro del digito    
    private final int[] pf4;

	//Coordenada de la esquina superior derecha del digito    
    private final int[] pf5;
    
    //Matriz para almacenar los numero a imprimir
    private String[][] matrizImpr;

    //Constatntes con los caracteres con los cual se construye el numero 
    static final String CARACTER_VERTICAL = "|";
    static final String CARACTER_HORIZONTAL = "-";
    
    //Constantes que indican la orientacion con la cual se escribe un segmento
    static final String POSICION_X = "X";
    static final String POSICION_Y = "Y";
    
    //Tamaño Segmento Digitos
    private int size;

    //Filas del digito
    private int filasDig;
    
    //Columnas del digito
    private int columDig;
    
    //Total filas del numero
    private int totalFilas;
    
    //Total columnas del numero
    private int totalColum;

    //Mapa con el punto fijo que corresponden a cada segemento de digito
    HashMap<Integer, int[]> pfPorSeg = new HashMap<Integer, int[]>();
    
    //Mapa con la posicion fija que corresponde a cada segmento de digito
    HashMap<Integer, String> posFijaPorSeg = new HashMap<Integer, String>();
   
    
    /**
     * Construye ImpresorLCD, se incializan los puntos fijos. <br>
     */
    public ImpresorLCD() {
        // Inicializa los puntos fijos
        this.pf1 = new int[2];
        this.pf2 = new int[2];
        this.pf3 = new int[2];
        this.pf4 = new int[2];
        this.pf5 = new int[2];
        
        // Inicializa el mapa con el punto fijo de cada segmento
        this.pfPorSeg.put(1,this.pf1);
        this.pfPorSeg.put(2,this.pf2);
        this.pfPorSeg.put(3,this.pf5);
        this.pfPorSeg.put(4,this.pf4);
        this.pfPorSeg.put(5,this.pf1);
        this.pfPorSeg.put(6,this.pf2);
        this.pfPorSeg.put(7,this.pf3);
        
        // Inicializa el mapa con la posicion fija de cada segmento
        this.posFijaPorSeg.put(1,POSICION_Y);
        this.posFijaPorSeg.put(2,POSICION_Y);
        this.posFijaPorSeg.put(3,POSICION_Y);
        this.posFijaPorSeg.put(4,POSICION_Y);
        this.posFijaPorSeg.put(5,POSICION_X);
        this.posFijaPorSeg.put(6,POSICION_X);
        this.posFijaPorSeg.put(7,POSICION_X);
    }

    /**
     *
     * Metodo encargado de un segmento a la matriz de impresion
     *
     * @param punto Punto Pivote
     * @param posFija Posicion Fija
     */    
    private void adicionarSegmento(int[] punto, String posFija) 
    {
    	int valor;
        if (posFija.equalsIgnoreCase(POSICION_X)) 
        {
            for (int y = 1; y <= this.size; y++) 
            {
                valor = punto[1] + y;
                this.matrizImpr[punto[0]][valor] = CARACTER_HORIZONTAL;
            }
        } 
        else 
        {
            for (int i = 1; i <= this.size; i++) 
            {
                valor = punto[0] + i;
                this.matrizImpr[valor][punto[1]] = CARACTER_VERTICAL;
            }
        }
    }

    /**
     *
     * Metodo encargado de definir los segmentos que componen un digito y
     * a partir de los segmentos adicionar la representacion del digito a la matriz de impresion
     *
     * @param numero Digito
     */
    private void adicionarDigito(int numero) 
    {
        // Establece los segmentos de cada numero
        List<Integer> segList = new ArrayList<>();

        switch (numero) 
        {
            case 1:
                segList=new ArrayList<>(Arrays.asList(3,4));
                break;
            case 2:
            	segList=new ArrayList<>(Arrays.asList(5,3,6,2,7));
                break;
            case 3:
            	segList=new ArrayList<>(Arrays.asList(5,3,6,4,7));
                break;
            case 4:
            	segList=new ArrayList<>(Arrays.asList(1,6,3,4));
                break;
            case 5:
            	segList=new ArrayList<>(Arrays.asList(5,1,6,4,7));
                break;
            case 6:
            	segList=new ArrayList<>(Arrays.asList(5,1,6,2,7,4));
                break;
            case 7:
            	segList=new ArrayList<>(Arrays.asList(5,3,4));
                break;
            case 8:
            	segList=new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7));
                break;
            case 9:
            	segList=new ArrayList<>(Arrays.asList(1,3,4,5,6,7));
                break;
            case 0:
            	segList=new ArrayList<>(Arrays.asList(1,2,3,4,5,7));
                break;
            default:
                break;
        }

        Iterator<Integer> iterator = segList.iterator();

        while (iterator.hasNext()) 
        {
        	int segmento=iterator.next();
            adicionarSegmento(pfPorSeg.get(segmento),posFijaPorSeg.get(segmento));
        }
    }

    /**
     *
     * Metodo encargado de imprimir un numero
     *
     * @param numeroImp Numero a Imprimir
     * @param espacio Espacio Entre digitos
     * @throws IllegalArgumentException si algu caracter no es un digito
     */    
    private void imprimirNumero( String numeroImp, int espacio) 
    {
        int pivotX = 0;
        char[] digitos;

        // Calcula el numero de filas cada digito
        this.filasDig = (2 * this.size) + 3;

        // Calcula el numero de columna de cada digito
        this.columDig = this.size + 2;

        // Calcula el total de filas de la matriz en la que se almacenaran los digitos
        this.totalFilas = this.filasDig;

        // Calcula el total de columnas de la matriz en la que se almacenaran los digitos
        this.totalColum = (this.columDig * numeroImp.length())
                + (espacio * numeroImp.length());

        // Crea matriz para almacenar los numero a imprimir
        this.matrizImpr = new String[this.totalFilas][this.totalColum];

        // Crea el arreglo de digitos
        digitos = numeroImp.toCharArray();

        // Inicializa matriz
        inicializarMatriz();
        
        //Calcula primera coordenada de los puntos fijos
        this.pf1[0] = 0;
        this.pf2[0] = (this.filasDig / 2);
        this.pf3[0] = (this.filasDig - 1);
        this.pf4[0] = (this.filasDig / 2);  
        this.pf5[0] = 0;
        
        for (char digito : digitos) 
        {    
            //Valida que el caracter sea un digito
            if( ! Character.isDigit(digito))
            {
                throw new IllegalArgumentException("Caracter " + digito
                    + " no es un digito");
            }

            int numero = Integer.parseInt(String.valueOf(digito));

            //Actualiza segunda coordenada de los puntos fijos
            this.pf1[1] = 0 + pivotX;
            this.pf2[1] = 0 + pivotX;
            this.pf3[1] = 0 + pivotX;
            this.pf4[1] = (this.columDig - 1) + pivotX;
            this.pf5[1] = (this.columDig - 1) + pivotX;

            pivotX = pivotX + this.columDig + espacio;

            adicionarDigito(numero);
        }

        // Imprime matriz
        imprimirMatriz();
    }

     /**
     *
     * Metodo encargado de procesar la entrada que contiene el size del segmento
     * de los digitos y los digitos a imprimir
     *
     * @param comando Entrada que contiene el size del segmento de los digito
     * y el numero a imprimir
     * @param espacioDig Espacio Entre digitos
     * @throws IllegalArgumentException si el comando no contiene coma
     * o si contiene parametros de mas
     * o si contiene parametros de menos
     * o si el size no es numerico
     * o si el size no esta entre 1 y 10 inclusives
     */  
    public void procesar(String comando, int espacioDig) 
    {    
        String[] parametros;
        
        int size;

        //Valida que el comando contenga comma
        if (!comando.contains(",")) 
        {
            throw new IllegalArgumentException("Cadena " + comando
                    + " no contiene caracter ,");
        }
        
        //Se hace el split de la cadena
        parametros = comando.split(",");
        
        //Valida que no hayan parametros de mas
        if(parametros.length>2)
        {
           throw new IllegalArgumentException("Cadena " + comando
                    + " contiene mas caracter ,"); 
        }
        
        //Valida que hayan suficientes parametros
        if(parametros.length<2)
        {
           throw new IllegalArgumentException("Cadena " + comando
                    + " no contiene los parametros requeridos"); 
        }
        
        //Valida que el parametro size sea un numerico
        if(!isNumeric(parametros[0]))
        {
        	throw new IllegalArgumentException("Parametro Size [" + parametros[0]
                    + "] no es un numero");
        }
        
        this.size = Integer.parseInt(parametros[0]);
        
        //Valida que el size este entre 1 y 10
        if(this.size <1 || this.size >10)
        {
            throw new IllegalArgumentException("El parametro size ["+this.size
                    + "] debe estar entre 1 y 10");
        }
        
        // Realiza la impresion del numero
        imprimirNumero( parametros[1],espacioDig);

    }

    /**
     *
     * Metodo encargado de validar si una cadena es numerica
     *
     * @param cadena Cadena
     */  
    static boolean isNumeric(String cadena) 
    {
        try 
        {
            Integer.parseInt(cadena);
            return true;
        } 
        catch (NumberFormatException ex) 
        {
            return false;
        }
    }
    
    /**
    *
    * Metodo encargado de inicializar la matriz de Impresion con el caracter: " "
    */   
    private void inicializarMatriz()
    {
    	 for (int i = 0; i < this.totalFilas; i++) 
    	 {
    		   Arrays.fill(this.matrizImpr[i], " ");
         }
    }
    
    /**
    *
    * Metodo encargado de imprimir la matriz de Impresion
    */   
    private void imprimirMatriz()
    {
    	for (int i = 0; i < this.totalFilas; i++) 
    	{
            for (int j = 0; j < this.totalColum; j++) 
            {
                System.out.print(this.matrizImpr[i][j]);
            }
            System.out.println();
        }
    }
}