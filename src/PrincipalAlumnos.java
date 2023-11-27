import java.util.Scanner;

public class PrincipalAlumnos {

    //Declaramos constantes:
    public static final int TAM = 4; // "TAM" con valor 4, para definir el tamaño de las matrices "matriz" y "matrizVisible".
    public static final int INTENTOS = 5; //"Intentos" con valor 5, para definir el número de intentos posibles en el juego.
    public static final char OCULTO = '#'; //"OCULTO" con valor '#', para representar un carácter oculto.

    public static char[][] matriz = new char[TAM][TAM];
    //Declara una matriz llamada "matriz" de tipo "char", con un tamaño de TAM por TAM, que en este caso es 4x4.
    public static boolean[][] matrizVisible = new boolean[TAM][TAM];
    //Declara una matriz llamada "matrizVisible" de tipo "boolean", con un tamaño de TAM por TAM, para los elementos que son visibles de la matriz "matriz".

    public static void main(String[] args) {
        ocultarTodaLaMatriz(matrizVisible); //Llamamos al método "ocultarTodaLaMatriz" para ocultar la "matrizVisible".
        fillMatrix(matriz);//Llamamos al método "fillMatrix" para llenar "matriz" de carácteres aleatorios en cada elemento.
        randomizeMatrix(matriz);//Llamamos al método "randomizeMatrix" para cambiar el orden aleatoriamente de "matriz".

        int intentos = INTENTOS;//Declara la variable intentos y la inicializa con el valor de la constante INTENTOS, para contar el número de intentos que el jugador tiene para ganar el juego.

        while (intentos > 0 && !checkFinished(matrizVisible)) {//Bucle mientras en jugador tenga intentos y la matriz no este completa (checkFinished).
            printVisibleMatrix(matriz, matrizVisible);//Llama al método que imprime la matriz con los elementos que estan visibles.
            System.out.println("Este es el tablero actual. Te quedan " + intentos + " intentos");
            int fila1 = leerEnteroEnRango(0, TAM - 1);//Declara una variable que el jugador ha elegido como fila.
            int columna1 = leerEnteroEnRango(0, TAM - 1);//Declara una variable que el jugador ha elegido como columna.

            if (!matrizVisible[fila1][columna1]) {//Condición que se evalúa a "true" si el elemento seleccionado es "false".
                mostrarCasilla(fila1, columna1);//Muestra la elección.
                int fila2 = leerEnteroEnRango(0, TAM - 1);//Declara segunda elección de fila.
                int columna2 = leerEnteroEnRango(0, TAM - 1);//Declara segunda elección de columna.

                if (!matrizVisible[fila2][columna2]) {//Condición que evalua a "true" si la segunda eleccion es "false".
                    mostrarCasilla(fila2, columna2);//Muestra la segunda elección.

                    if (matriz[fila1][columna1] == matriz[fila2][columna2]) {//Condicón que comprueba si los dos elementos de las dos elecciones son iguales.
                        System.out.println("¡Bien! Encontraste una pareja");
                    } else {//Sino cumple la condicion anterior no se evalua a "true".
                        System.out.println("Vaya, no coinciden. Vuelve a intentarlo");
                        ocultarCasilla(fila1, columna1);//Llama al método ocultarCasilla para volver a ocultar las primera eleccion.
                        ocultarCasilla(fila2, columna2);//Llama al método ocultarCasilla para volver a ocultar las segunda eleccion
                        intentos--;//Decrementa la variable intentos en 1.
                    }
                } else {
                    System.out.println("Ya has seleccionado esa casilla");
                }
            } else {
                System.out.println("Ya has seleccionado esa casilla");
            }
        }

        if (checkFinished(matrizVisible)) {//inicia una condición  if  que se evalúa a  true  si la matriz  matrizVisible  está completa.
            System.out.println("¡Enhorabuena! Has ganado");
        } else {
            System.out.println("¡Vaya! Has perdido. Vuelve a jugar otra vez.");
        }
    }

    private static void mostrarCasilla(int fila, int columna) {//Método que muestra la matriz estableciendo el valor de la "matrizVisible" en "true" para esa casilla.
        matrizVisible[fila][columna] = true;
        printVisibleMatrix(matriz, matrizVisible);
    }

    private static void ocultarCasilla(int fila, int columna) {//Método que oculta la matriz estableciendo el valor de la "matrizVisible" en "false" para esa casilla.
        matrizVisible[fila][columna] = false;
        printVisibleMatrix(matriz, matrizVisible);
    }

    private static void printMatrix(char[][] matriz) {//Método para imprimir la "matrix" en la consola.
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void printVisibleMatrix(char[][] matriz, boolean[][] matrizVisible) {//Método para imprimir la "matriz" con las casillas ocultas con el carácter oculto.
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if (matrizVisible[i][j]) {
                    System.out.print(matriz[i][j] + " ");
                } else {
                    System.out.print(OCULTO + " ");
                }
            }
            System.out.println();
        }
    }

    public static void fillMatrix(char[][] matriz) {//Método para llenar "matriz" de caracteres aleatorios.
        char letra = 'A';
        int tam = matriz.length * matriz[0].length / 2;

        for (int i = 0; i < tam; i++) {
            for (int j = 0; j < 2; j++) {
                int fila, columna;
                do {
                    fila = (int) (Math.random() * matriz.length);
                    columna = (int) (Math.random() * matriz[0].length);
                } while (matriz[fila][columna] != '\u0000');

                matriz[fila][columna] = letra;
            }
            letra++;
        }
    }

    private static void randomizeMatrix(char[][] matriz) {//Método para aleatorizar "matriz" e intercambiar el valor de dos casillas aleatorias.
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                int fila1 = (int) (Math.random() * matriz.length);
                int columna1 = (int) (Math.random() * matriz[i].length);

                char temp = matriz[i][j];
                matriz[i][j] = matriz[fila1][columna1];
                matriz[fila1][columna1] = temp;
            }
        }
    }

    private static void ocultarTodaLaMatriz(boolean[][] matrizVisible) {//Establece el valor de cada casilla en "false".
        for (int i = 0; i < matrizVisible.length; i++) {
            for (int j = 0; j < matrizVisible[i].length; j++) {
                matrizVisible[i][j] = false;
            }
        }
    }

    private static boolean checkFinished(boolean[][] matrizVisible) {//Si no hay ninguna casilla oculta, el metodo devuelve "true".
        for (int i = 0; i < matrizVisible.length; i++) {
            for (int j = 0; j < matrizVisible[i].length; j++) {
                if (!matrizVisible[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private static Scanner scanner = new Scanner(System.in);

    public static char leerChar(String mensaje) {
        System.out.print(mensaje + ": ");
        return scanner.next().charAt(0);
    }

    public static int leerEnteroEnRango(int inicio, int fin) {//Metodo que comprueba si el numero introducido por el jugador esta dentro del rango posible.
        int num;
        do {
            System.out.print("Introduce un número para tu eleccion de fila/columna entendiendo que  " + inicio + "  es la primera y  " + fin + "  es la última : " );
            num = scanner.nextInt();
        } while (num < inicio || num > fin);
        return num;
    }
}

