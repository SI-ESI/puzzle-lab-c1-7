public class Accion {
	private int botella_origen;
	private int botella_destino;
	private int cantidad;
	
	public Accion(int botella_origen, int botella_destino, int cantidad) {
		super();
		this.botella_origen = botella_origen;
		this.botella_destino = botella_destino;
		this.cantidad = cantidad;
	}

	public int getBotella_origen() {
		return botella_origen;
	}

	public void setBotella_origen(int botella_origen) {
		this.botella_origen = botella_origen;
	}

	public int getBotella_destino() {
		return botella_destino;
	}

	public void setBotella_destino(int botella_destino) {
		this.botella_destino = botella_destino;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public String toString() {
		return "(" + botella_origen + ", " + botella_destino + ", "
				+ cantidad + ")";
	}
	
	
	
}
