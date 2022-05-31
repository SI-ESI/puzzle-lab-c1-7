import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;
public class Main {
    final static Scanner TECLADO = new Scanner(System.in);
    
    final static int profundidadMaxima = 1000;
	final static int costo = 1;
	
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
                    System.out.println("OpciÃ³n incorrecta...");
			}
        } while(!opcionSalir);
    }

    
    private static ArrayList<Problema> leerFichero() {
    	String fichero = "Estados.txt";
    	Problema problemaInicial;
    	ArrayList<Problema> listaProblemas = null;
    	
    	try {
    		Scanner leer = new Scanner(new File(fichero)); 
    		while (leer.hasNext()) {
    			String lineaMal = leer.nextLine(); 
    			String linea = lineaMal.replaceAll(" ", ""); //Eliminamos los espacios en blanco
    			problemaInicial = generarProblema(linea);
    			if (problemaInicial != null) {
    				listaProblemas.add(problemaInicial);
    			}
    		}
    		leer.close();
    	} catch (FileNotFoundException e) {// Excepción si no encuentra el fichero
    		System.out.println(e.getMessage() + "Fichero no encontrado");
    		System.exit(1);
    	}
    	return listaProblemas;
    }

	private static Problema generarProblema(String linea) {
		try {
			Problema problema;
			JSONObject Jproblema = new JSONObject(linea);
			
			String id = Jproblema.getString("id");
			int tamBotella = Jproblema.getInt("bottleSize");
			String estadoInicial = Jproblema.getJSONArray("initState").toString();
			
			Estado estado = generarEstado(estadoInicial, tamBotella);
			problema = new Problema(id, tamBotella, estado);
			return problema;
		} catch (Exception e) {
			return null;
		}
	}

    private static Estado generarEstado(String jsonLinea, int tamMAX) {
		Estado eAux = new Estado();
		JSONArray arrayEstado = new JSONArray(jsonLinea);

		for (int i = 0; i < arrayEstado.length(); i++) {// por cada elemento en el arrayEstado
			Botella bAux = new Botella(i, tamMAX);

			if (arrayEstado.getJSONArray(i).length() != 0) {
				// crear botella
				Stack<Color> pila = new Stack<Color>();
				Stack<Color> pilaAux = new Stack<Color>();
				JSONArray arrayBotella = arrayEstado.getJSONArray(i);
				for (int j = 0; j < arrayBotella.length(); j++) {// por cada elemento en el arrayBotella
					// crea un color
					JSONArray arrayColor = arrayBotella.getJSONArray(j);
					Color cAux = new Color(arrayColor.getInt(0), arrayColor.getInt(1));
					pilaAux.push(cAux);
				}
				pila = invertirPila(pilaAux);
				bAux.setPilaColores(pila);
			}
			eAux.setBotella(bAux);
		}
		return eAux;
	}
    
	private static Stack<Color> invertirPila(Stack<Color> pilaAux) {
		
		Stack<Color> pila = null ;
		while (!pilaAux.empty()){
			pila.add(pilaAux.pop());
		}
		
		return pila;
	}
	
	private static PriorityQueue<Sucesor> Sucesores(Estado estadoInicial) {
		PriorityQueue<Sucesor> sucesores = null;
		
		for (Botella botellaOrigen : estadoInicial.getBotella()) {
			for (Botella botellaDestino : estadoInicial.getBotella()) {
				if (botellaOrigen.getCantidadOcupada()> 0) {
					int cantidad = botellaOrigen.getPilaColores().peek().getCantidad();
					Accion accion = new Accion(botellaOrigen.getIdBotella(), botellaDestino.getIdBotella(), cantidad);
					if (estadoInicial.EsAccionPosible(botellaOrigen, botellaDestino, cantidad)) {
						Estado nuevoEstado = estadoInicial.Accion(botellaOrigen, botellaDestino, cantidad);
						sucesores.add(new Sucesor(accion, nuevoEstado, costo));
					}
				}
			}
		}
		return sucesores;
	}
}


