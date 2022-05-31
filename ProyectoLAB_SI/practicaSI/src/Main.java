import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
public class Main {
    final static Scanner TECLADO = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        seleccionEstrategia();
    }

    public static void mostrarMenu() {
        System.out.println();
        System.out.println("Elija una opcion:");
        System.out.println("1- Anchura");
        System.out.println("2- Profundidad");
        System.out.println("3- Busqueda Uniforme");
        System.out.println("4- Busqueda Greedy");
        System.out.println("5- Busqueda A");
        System.out.println("6- Salir");
        System.out.println();
    }

    public static void seleccionEstrategia () {
        boolean opcionSalir = false;
        int opcionMenu;

        do {
            mostrarMenu();
            opcionMenu = TECLADO.nextInt();

            switch(opcionMenu) {
                case 1:
                break;

                case 2:
                break;

                case 3:
                break;

                case 4:
                break;

                case 5:
                break;

                case 6:
                    opcionSalir=true;
                    System.out.println("Hasta luego...");
                break;

                default:
                    System.out.println("Opción incorrecta...");
			}
        } while(!opcionSalir);
    }
    
}


