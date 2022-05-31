public class Problema {
	private String idProblema;
	private int capacidadBot;
	private Estado estadoInicial;
	public Problema(String idProblema, int capacidadBot, Estado estadoInicial) {
		super();
		this.idProblema = idProblema;
		this.capacidadBot = capacidadBot;
		this.estadoInicial = estadoInicial;
	}
	public String getIdProblema() {
		return idProblema;
	}
	public void setIdProblema(String idProblema) {
		this.idProblema = idProblema;
	}
	public int getCapacidadBot() {
		return capacidadBot;
	}
	public void setCapacidadBot(int capacidadBot) {
		this.capacidadBot = capacidadBot;
	}
	public Estado getEstadoInicial() {
		return estadoInicial;
	}
	public void setEstadoInicial(Estado estadoInicial) {
		this.estadoInicial = estadoInicial;
	}
	@Override
	public String toString() {
		return "Problema [idProblema=" + idProblema + ", Tamaño botellas=" + capacidadBot + ", estadoInicial="
				+ estadoInicial + "]";
	}
	
	
}
