import java.util.Scanner;

public class OrdenamientoBusqueda {

    private static final int TAMANO = 20;
    private static int[] listaOriginal = new int[TAMANO];
    private static boolean arregloGenerado = false;
    private static boolean arregloOrdenado = false;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;

        do {
            mostrarMenu();
            System.out.print("Seleccione una opción: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Por favor, ingrese un número válido.");
                scanner.next();
                System.out.print("Seleccione una opción: ");
            }
            opcion = scanner.nextInt();
            System.out.println();

            switch (opcion) {
                case 1:
                    generarNumeros(listaOriginal);
                    arregloGenerado = true;
                    arregloOrdenado = false;
                    break;

                case 2:
                    if (!arregloGenerado) {
                        System.out.println("Primero debe generar los números aleatorios (Opción 1).");
                    } else {
                        mostrarArreglo("Arreglo actual", listaOriginal);
                    }
                    break;

                case 3:
                    if (!arregloGenerado) {
                        System.out.println("Primero debe generar los números aleatorios (Opción 1).");
                    } else {
                        System.out.println("--- Proceso de Ordenamiento QuickSort ---");
                        ordenarQuicksort(listaOriginal, 0, listaOriginal.length - 1);
                        arregloOrdenado = true;
                        System.out.println("\n¡Arreglo ordenado exitosamente!");
                        mostrarArreglo("Arreglo ordenado", listaOriginal);
                    }
                    break;

                case 4:
                    if (!arregloGenerado) {
                        System.out.println("Primero debe generar los números aleatorios (Opción 1).");
                    } else {
                        if (!arregloOrdenado) {
                            System.out.println("Nota: Para aplicar Búsqueda Binaria el arreglo debe estar ordenado.");
                            System.out.println("Ordenando arreglo automáticamente con QuickSort...");
                            ordenarQuicksort(listaOriginal, 0, listaOriginal.length - 1);
                            arregloOrdenado = true;
                            mostrarArreglo("Arreglo ordenado", listaOriginal);
                        }

                        System.out.print("Ingrese el número entero que desea buscar: ");
                        while (!scanner.hasNextInt()) {
                            System.out.println("Entrada inválida. Ingrese un número entero.");
                            scanner.next();
                            System.out.print("Ingrese el número que desea buscar: ");
                        }
                        int valorBuscado = scanner.nextInt();

                        int posicion = buscar(listaOriginal, valorBuscado, 0, listaOriginal.length - 1);

                        mostrarResultadoBusqueda(valorBuscado, posicion, listaOriginal.length);
                    }
                    break;

                case 5:
                    System.out.println("Saliendo del programa. ¡Hasta pronto!");
                    break;

                default:
                    System.out.println("Opción no válida. Intente con un valor entre 1 y 5.");
                    break;
            }
            System.out.println();
        } while (opcion != 5);

        scanner.close();
    }

    public static void mostrarMenu() {
        System.out.println("====================================================");
        System.out.println("   PRÁCTICA GUIADA #1: ORDENAMIENTO Y BÚSQUEDA      ");
        System.out.println("====================================================");
        System.out.println("1. Generar 20 números aleatorios (1 - 50)");
        System.out.println("2. Mostrar arreglo");
        System.out.println("3. Ordenar arreglo con QuickSort");
        System.out.println("4. Buscar número con Búsqueda Binaria");
        System.out.println("5. Salir");
        System.out.println("====================================================");
    }

    public static void generarNumeros(int listaOriginal[]) {
        int number;
        System.out.println("Lista original generada aleatoriamente:");
        for (int i = 0; i < listaOriginal.length; i++) {
            number = (int) (Math.random() * 50 + 1);
            listaOriginal[i] = number;
        }
        mostrarArreglo("Lista original", listaOriginal);
    }

    public static void mostrarArreglo(String mensaje, int listaOriginal[]) {
        System.out.println("\n[" + mensaje + "]");
        System.out.print("Índices:   ");
        for (int i = 0; i < listaOriginal.length; i++) {
            System.out.printf("[%2d] ", i);
        }
        System.out.println();
        System.out.print("Valores:   ");
        for (int i = 0; i < listaOriginal.length; i++) {
            System.out.printf(" %2d  ", listaOriginal[i]);
        }
        System.out.println();
    }

    public static void ordenarQuicksort(int listaOriginal[], int inferior, int superior) {
        int i, j, central;
        double pivote;
        central = (inferior + superior) / 2;
        pivote = listaOriginal[central];
        i = inferior;
        j = superior;

        System.out.println("Límites que se analizan. Inferior: " + i + " Superior: " + j + " Pivote: " + (int) pivote);

        do {
            while (listaOriginal[i] < pivote) i++;
            while (listaOriginal[j] > pivote) j--;
            if (i <= j) {
                intercambiar(listaOriginal, i, j);
                i++;
                j--;
            }
        } while (i <= j);

        if (inferior < j) {
            ordenarQuicksort(listaOriginal, inferior, j);
        }
        if (i < superior) {
            ordenarQuicksort(listaOriginal, i, superior);
        }
    }

    public static void intercambiar(int listaOriginal[], int i, int j) {
        int aux = listaOriginal[i];
        listaOriginal[i] = listaOriginal[j];
        listaOriginal[j] = aux;
    }

    public static int buscar(int listaOriginal[], int valorBuscado, int inferior, int superior) {
        int central;
        if (inferior > superior) {
            return -1;
        } else {
            central = (inferior + superior) / 2;
            if (listaOriginal[central] == valorBuscado) {
                return central;
            } else if (listaOriginal[central] < valorBuscado) {
                return buscar(listaOriginal, valorBuscado, central + 1, superior);
            } else {
                return buscar(listaOriginal, valorBuscado, inferior, central - 1);
            }
        }
    }

    public static void mostrarResultadoBusqueda(int valorBuscado, int posicion, int totalElementos) {
        System.out.println("\n----------------------------------------------------");
        System.out.println("               RESULTADO DE LA BÚSQUEDA             ");
        System.out.println("----------------------------------------------------");

        if (posicion == -1) {
            System.out.println("El número " + valorBuscado + " NO se encuentra en el arreglo.");
        } else {
            int puntoMedio = totalElementos / 2;
            String lado;

            if (posicion < puntoMedio) {
                lado = "LADO IZQUIERDO (índices 0 a " + (puntoMedio - 1) + ")";
            } else {
                lado = "LADO DERECHO (índices " + puntoMedio + " a " + (totalElementos - 1) + ")";
            }

            System.out.println("¡Número " + valorBuscado + " ENCONTRADO!");
            System.out.println("-> Posición (Índice): [" + posicion + "]");
            System.out.println("-> Valor en el arreglo: " + listaOriginal[posicion]);
            System.out.println("-> Ubicación en el arreglo: " + lado);
            System.out.println("----------------------------------------------------");
        }
    }
}