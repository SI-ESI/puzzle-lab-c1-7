import java.util.List;

public class Frontera {
	private List<Nodo> frontera;

	public Frontera(List<Nodo> frontera) {
		super();
		this.frontera = frontera;
	}

	public List<Nodo> getFrontera() {
		return frontera;
	}

	public void setFrontera(List<Nodo> frontera) {
		this.frontera = frontera;
	}
	
	public void pushN (Nodo nodo) {
		frontera.add(nodo);
		ordenar(frontera);
	}
	
	private void ordenar(List<Nodo> frontera) {
		for (int i = 0; i < frontera.size(); i++) {
			for (int j = 0; j < frontera.size(); j++) {
				if (i != j) {
					if (frontera.get(i).getValor() < frontera.get(j).getValor() && i > j) {
						Nodo aux = frontera.get(i);
						frontera.remove(i);
						frontera.add(j, aux);
					}
					if (frontera.get(i).getValor() > frontera.get(j).getValor() && i < j) {
						Nodo aux = frontera.get(j);
						frontera.remove(j);
						frontera.add(i, aux);
					}
					if (frontera.get(i).getValor() == frontera.get(j).getValor()) {
						if (frontera.get(i).getId() < frontera.get(j).getId() && i > j) {
							Nodo aux = frontera.get(i);
							frontera.remove(i);
							frontera.add(j, aux);
						}
						if (frontera.get(i).getId() > frontera.get(j).getId() && i < j) {
							Nodo aux = frontera.get(j);
							frontera.remove(j);
							frontera.add(i, aux);
						}
					}
				}
			}
		}
	}
	

	public Nodo popN () {
		Nodo nodo = frontera.get(0);
		frontera.remove(0);
		return nodo;
	}
	
	
}
