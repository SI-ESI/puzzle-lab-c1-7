public class Nodo {
	private int id;
	private double costo;
	private Estado estado;
	private Nodo id_padre;
	private Accion accion;
	private int profundidad;
	private double heuristica;
	private double valor;
	
	public Nodo(int id, double costo, Estado estado, Nodo id_padre, Accion accion, int profundidad, double heuristica,
			double valor) {
		super();
		this.id = id;
		this.costo = costo;
		this.estado = estado;
		this.id_padre = id_padre;
		this.accion = accion;
		this.profundidad = profundidad;
		this.heuristica = heuristica;
		this.valor = valor;
	}
	
	public Nodo() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getCosto() {
		return costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Nodo getId_padre() {
		return id_padre;
	}

	public void setId_padre(Nodo id_padre) {
		this.id_padre = id_padre;
	}

	public Accion getAccion() {
		return accion;
	}

	public void setAccion(Accion accion) {
		this.accion = accion;
	}

	public int getProfundidad() {
		return profundidad;
	}

	public void setProfundidad(int profundidad) {
		this.profundidad = profundidad;
	}

	public double getHeuristica() {
		return heuristica;
	}

	public void setHeuristica(double heuristica) {
		this.heuristica = heuristica;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {	
		return id + "-" + estado.toString();	
	}
	
}
