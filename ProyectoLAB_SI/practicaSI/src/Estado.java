import java.util.ArrayList;
import java.util.List;

public class Estado {
	private ArrayList<Botella> botella;

	public Estado() {
		super();
		this.botella = new ArrayList<Botella>();
	}
	
	public Estado(Estado estado) {
		this.botella = new ArrayList<Botella>();
		ArrayList<Botella> estadosAux = estado.getBotella();
		for (int i = 0; i < estadosAux.size(); i++) {
			this.botella.add(new Botella(estadosAux.get(i)));
		}
	}

	public ArrayList<Botella> getBotella() {
		return botella;
	}

	public void setBotella(Botella bot) {
		botella.add(bot);
	}
	
	//Método para clonar el estado, utilizamos el constructor para clonarlo con una lista auxiliar
	
	public Estado clonarEstado() {
		return new Estado(this);
	}
	
	//Creamos en Estado el metodo EsAccionPosible de la tarea 1 para comprobar si se puede hacer una accion y cambiar de estado.
	
	public static boolean EsAccionPosible(Botella botella_origen, Botella botella_destino, int cantidad) {
		
		botella_origen.getPilaColores().peek().getId();
		if (cantidad <= cantDisponible(botella_destino) && 
				botella_origen.getCantidadOcupada() >= cantidad && 
				!botellaIgual(botella_origen, botella_destino)) {
			return true;
		} else {
			return false;
		}
			
	}
	
	private static boolean botellaIgual(Botella botella_origen, Botella botella_destino) {
		return botella_origen.getIdBotella()== botella_destino.getIdBotella();
	}

	private static int cantDisponible (Botella botella_destino) {
		
		int cantDisponible = botella_destino.getCantidadMax() - botella_destino.getCantidadOcupada();
		return cantDisponible;
		
	}

	public Estado Accion(Botella botella_origen, Botella botella_destino, int cantidad) {	
		Estado nuevoEstado = null;
		nuevoEstado = clonarEstado();
		
		botella_destino.incrementarColor(botella_origen.getPilaColores().peek().getId(), cantidad);
		botella_origen.decrementarColor(cantidad);
		return nuevoEstado;
	}
	
	public String toString() {
		String contenido = botella.toString();
		return contenido.replaceAll(" ", "");
	}

}

