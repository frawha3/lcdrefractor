LCD refactor

Objetivo: Crear un programa que imprime n�meros en estilo de una pantalla LCD

Entrada: La entrada contiene varias l�neas. Cada l�nea contiene 2 n�meros separados por coma. El primer n�mero representa el tama�o de la impresi�n (entre 1 y 10, esta variable se llama �size�). El segundo n�mero es el n�mero a mostrar en la pantalla. Para terminar, se debe digitar 0,0. Esto terminar� la aplicaci�n.

Salida: Imprimir los n�meros especificados usando el caracter �-� para los caracteres horizontales, y �|� para los verticales. Cada d�gito debe ocupar exactamente size+2 columnas y 2*size + 3 filas.

Entre cada impresi�n debe haber una l�nea blanca.

Ejemplo: Entrada: 2,12345 3,67890 0,0

Salida:

  
   _ _  _ _        _ _
|     |    | |  | |
|  _ _| _ _| |__| |_ _
| |        |    |     |
| |_ _  _ _|    |  _ _|

 _ _ _  _ _ _   _ _ _   _ _ _   _ _ _ 
|            | |     | |     | |     |
|            | |     | |     | |     |
|_ _ _       | |_ _ _| |_ _ _| |     |
|     |      | |     |       | |     |
|     |      | |     |       | |     |
|_ _ _|      | |_ _ _|  _ _ _| |_ _ _|