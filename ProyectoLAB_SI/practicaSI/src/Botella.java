import java.util.Stack;

public class Botella {
    private int idBotella;
	private int cantidadMax;
	private int cantidadOcupada;
	private Stack<Color> pilaColores;

    public Botella(int idBotella, int cantidadMax) {
        this.idBotella = idBotella;
		this.cantidadMax = cantidadMax;
        pilaColores = new Stack<Color>();
        cantidadOcupada = 0;
    }
    
    public Botella(Botella b) {
		this.idBotella = b.getIdBotella();
		this.cantidadMax = b.getCantidadMax();
		cantidadOcupada = b.getCantidadOcupada();
		pilaColores = copiarPila(b.getPilaColores());
	}

    private Stack<Color> copiarPila(Stack<Color> pilaOrigen) {
        Stack<Color> aux = new Stack<Color>();
		Stack<Color> copia = new Stack<Color>();

        while (!pilaOrigen.empty()) {
            aux.add(pilaOrigen.pop()); 
        }

        while (!aux.empty()) {
            copia.add(new Color(aux.peek().getId(), aux.peek().getCantidad()));
            pilaOrigen.add(aux.pop());
        }
        return copia;
    }

    public void incrementarColor(int idColor, int cantidad) {
        //Comprobamos que la pila no esta vacia.
        //Hemos cambiado el if por si salta una excepción al manipular la pila y está vacía.
        if (!pilaColores.empty() && idColor == pilaColores.peek().getId()) {
                pilaColores.peek().incrementarCantidad(cantidad);
        } else {
            pilaColores.add(new Color(idColor, cantidad));
        }
        cantidadOcupada += cantidad;
    }
    
    public void decrementarColor(int cantidad) {
        if (pilaColores.peek().getCantidad() == cantidad) {
            pilaColores.pop();
        } else {
            pilaColores.peek().decrementarCantidad(cantidad);
        }
        cantidadOcupada -= cantidad;
    }    

    public int getIdBotella() {
		return idBotella;
	}

	public int getCantidadMax() {
		return cantidadMax;
	}

	public int getCantidadOcupada() {
		return cantidadOcupada;
	}

    public void setCantidadOcupada(int cantidad) {
        cantidadOcupada = cantidad;
    }

	public Stack<Color> getPilaColores() {
		return pilaColores;
	}

	public void setPilaColores(Stack<Color> pilaColores) {
		this.pilaColores = pilaColores;
	}
    
}
