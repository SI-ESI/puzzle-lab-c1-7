public class Sucesor {
	private Accion accion;
	private Estado nuevoEstado;
	private int costo;
	
	public Sucesor(Accion accion, Estado nuevoEstado, int costo) {
		super();
		this.accion = accion;
		this.nuevoEstado = nuevoEstado;
		this.costo = costo;
	}

	public Accion getAccion() {
		return accion;
	}

	public void setAccion(Accion accion) {
		this.accion = accion;
	}

	public Estado getNuevoEstado() {
		return nuevoEstado;
	}

	public void setNuevoEstado(Estado nuevoEstado) {
		this.nuevoEstado = nuevoEstado;
	}

	public int getCosto() {
		return costo;
	}

	public void setCosto(int costo) {
		this.costo = costo;
	}
	
}