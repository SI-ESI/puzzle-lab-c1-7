import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;

public class Main {
    final static Scanner TECLADO = new Scanner(System.in);
    
    final static int profundidadMaxima = 1000;
	final static int costo = 1;
	
    public static void main(String[] args) throws Exception {
    	
    	ArrayList<Problema> listaProblemas;
    	listaProblemas = leerFichero();
    	for (Problema p : listaProblemas) {
    		System.out.println(p.toString());
    		Estado estado =  p.getEstadoInicial();
    		seleccionEstrategia(estado);
    	}
    	
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

    public static void seleccionEstrategia (Estado estado) {
        boolean opcionSalir = false;
        int opcionMenu;
        
        List<Nodo> solucion;
        Nodo nodoS;
        List<String> solucion_final;

        do {
            mostrarMenu();
            opcionMenu = TECLADO.nextInt();

            switch(opcionMenu) {
                case 1:
                	nodoS = busquedaAnchura(estado);
        			solucion = generarSolucion(nodoS);
        			solucion_final = stringSolucion(solucion);
        			exportar(solucion_final);
        			System.out.println("Solucion generada");
        			
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
    

    
    private static void exportar(List<String> solucion_final) {
    	int i;
		FileWriter datofl = null;
		try {
			datofl = new FileWriter("resultados.txt");
			BufferedWriter bfwdato = new BufferedWriter(datofl);// crea un buffer
			for (i = 0; i < solucion_final.size(); i++) {
				bfwdato.write(solucion_final.get(i).toString() + "\n");
			}
			bfwdato.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (datofl != null) {
				try {
					datofl.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

	private static List<String> stringSolucion(List<Nodo> solucion) {
		List<String> sol = null;
		String stringSolucion;
		
		for(Nodo nAux :  solucion) {
			if (nAux.getId_padre() == null) {
				stringSolucion = "[" + nAux.getId() + "][" + String.format("%.1f", nAux.getCosto()) + "," + md5(nAux.getEstado().toString())
                + ",None,None," + nAux.getProfundidad() + "," + String.format("%.2f", nAux.getHeuristica()) + ","
                + String.format("%.2f", nAux.getValor()) + "]";;
			} else {
				stringSolucion = "[" + nAux.getId() + "][" + String.format("%.1f", nAux.getCosto()) + "," + md5(nAux.getEstado().toString()) + ","
						+ nAux.getId_padre().getId() + "," + nAux.getAccion().toString() + "," + nAux.getProfundidad() + ","
						+ String.format("%.2f", nAux.getHeuristica()) + "," + String.format("%.2f", nAux.getValor()) + "]";;
			}
			sol.add(stringSolucion);
		}
		
		return sol;
	}

	private static String md5(String input) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(input.getBytes());
			BigInteger no = new BigInteger(1, messageDigest);
			String hashtext = no.toString(16);
			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}
			return hashtext;
			
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	private static List<Nodo> generarSolucion(Nodo nodoS) {
    	List<Nodo> solucion = null;
    	
    	do {
    		nodoS = nodoS.getId_padre();
    		solucion.add(nodoS);
    	}while(nodoS.getId() != 0);
    	
		return solucion;
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
	
	private static ArrayList<Sucesor> Sucesores(Estado estadoInicial) {
		ArrayList<Sucesor> sucesores = null;
		
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
	  
	public static int expandir(Nodo nodo, Frontera frontera, int ultimoNodo, String estrategia) {
		ArrayList<Sucesor> nuevas_acciones = Sucesores(nodo.getEstado());
		for (int i = 0; i < nuevas_acciones.size(); i++) {
			ultimoNodo++;
			Nodo n = new Nodo();
			n.setId(ultimoNodo);
			n.setId_padre(n);
			n.setEstado(nuevas_acciones.get(i).getNuevoEstado());
			n.setAccion(nuevas_acciones.get(i).getAccion());
			n.setProfundidad(n.getProfundidad() + 1);
			n.setHeuristica(Heuristica(n));
			n.setCosto(n.getCosto() + 1.0);
			if (estrategia.equals("Profundidad")) {
				n.setValor(n.getProfundidad());
			}
			if (estrategia.equals("Anchura")) {
				n.setValor(1 / (n.getProfundidad() + 1));
			}
			if (estrategia.equals("Uniforme")) {
				n.setValor(n.getCosto());
			}
			if (estrategia.equals("Voraz")) {

				n.setValor(n.getHeuristica());
			}
			if (estrategia.equals("A")) {

				n.setValor(n.getHeuristica() + n.getCosto());
			}
			
			frontera.pushN(n);
		}
		return ultimoNodo;
	}

	private static double Heuristica(Nodo n) {
		ArrayList<Botella> botellas = n.getEstado().getBotella();
		ArrayList<Integer> visitados = null;
		
		int heuristica = 0;
		
		for (Botella b : botellas) {
			if (b.getCantidadOcupada() == 0) {
				heuristica ++;
			} else {
				heuristica +=b.getPilaColores().size();
				if(visitados.contains(b.getPilaColores().peek().getId())) {
					heuristica ++;
				}else {
					visitados.add(b.getPilaColores().peek().getId());
				}
			}
		}
		return heuristica - botellas.size();
	}
	

	public static Nodo busquedaAnchura(Estado e) {
		Nodo solucion = null;
		Nodo nodoAux;
		Estado estadoAux;
		ArrayList<Nodo> nodos = new ArrayList<Nodo>();
		Frontera frontera = new Frontera(nodos);
		List<Botella> botellas;
		List<List<Botella>> listaVisitados = new ArrayList<List<Botella>>();

		int idNodo = 0;
		frontera.pushN(new Nodo(0, 0.0, e, null, null, 0, 0, 1));
		while (!frontera.getFrontera().isEmpty()) {
			nodoAux = frontera.getFrontera().get(0);
			nodoAux.setHeuristica(Heuristica(nodoAux));

			frontera.getFrontera().remove(0);
			estadoAux = nodoAux.getEstado();
			botellas = estadoAux.getBotella();
			List<Botella> estadoClonado = new ArrayList<>();
			estadoClonado = e.clonarEstado().getBotella();
			if (objetivo(estadoAux)) {
				solucion = nodoAux;

				break;
			} else {
				if (listaVisitados.isEmpty()) {
					listaVisitados.add(estadoClonado);
					idNodo = expandir(nodoAux, frontera, idNodo, "Profundidad");
				} else {
					if (visitado(listaVisitados, estadoClonado, 0) == false) {
						listaVisitados.add(estadoClonado);
						idNodo = expandir(nodoAux, frontera, idNodo, "Profundidad");
					}
				}
			}
		}

		return solucion;
	}
	
	private static boolean objetivo(Estado estadoAux) {
		List<Botella> listaBot = estadoAux.getBotella();
		List<Integer> listaIdColores = null;
		
		for( Botella botella : listaBot) {
			if (botella.getPilaColores().size() > 1) {
				return false;
			}
			if(botella.getCantidadOcupada() > 0) {
				if(listaIdColores.contains(botella.getPilaColores().peek().getId())) {
					return false;
				} else {
					listaIdColores.add(botella.getPilaColores().peek().getId());
				}
			}
		}
		
		return true;
		
	}

	private static boolean visitado(List<List<Botella>> listaVisitados, List<Botella> estadoClonado, int i) {
		boolean visitado = false;
		boolean estadoV = true;
		int cantidadActual = 0;
		int cantidad = 0;
		
		for (Botella b : estadoClonado) {
			cantidadActual += b.getCantidadOcupada();
		}
		
		return visitado;
		
	}

}


