public class Color {
    private int id;
    private int cantidad;

    public Color(int id, int cantidad) {
        this.id = id;
		this.cantidad = cantidad;
    }

    public int getId() {
		return id;
	}

	public int getCantidad() {
		return cantidad;
	}

    public int incrementarCantidad(int cantidad) {
		this.cantidad += cantidad;
		return cantidad;
	}

    public int decrementarCantidad(int cantidad) {
        this.cantidad -= cantidad;
        return cantidad;
    }

    public String toString() {
		return "[" + id + "," + cantidad + "]";
	}
}
