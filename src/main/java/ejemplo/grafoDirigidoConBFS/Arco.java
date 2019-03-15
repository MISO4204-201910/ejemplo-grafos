package ejemplo.grafoDirigidoConBFS;

public class Arco {

	private Nodo origen;
	
	private Nodo destino;
	
	public Arco() {	}
	
	public Nodo getOrigen() {
		return origen;
	}
	public void setOrigen(Nodo origen) {
		this.origen = origen;
	}
	
	public Nodo getDestino() {
		return destino;
	}
	public void setDestino(Nodo destino) {
		this.destino = destino;
	}
	
}
