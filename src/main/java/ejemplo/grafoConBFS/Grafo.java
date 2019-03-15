package ejemplo.grafoConBFS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Grafo {

	List<Nodo> nodos = new ArrayList<>();
	List<Arco> arcos = new ArrayList<>();
	
	public Grafo() { }
	
	public void addNodo(String nombre)
	{
		Nodo nodo = new Nodo();
		nodo.setNombre(nombre);
		nodos.add(nodo);
	}
	
	public void addArco(String origen, String destino) throws Exception {
		
		Nodo nodoOrigen = buscarNodo(origen);
		Nodo nodoDestino = buscarNodo(destino);
		
		// Origen o destino no encontrado
		if (nodoOrigen == null) {
			throw new RuntimeException("Error en la Búsqueda: NodoOrigen no encontrado");
		}
		if (nodoDestino == null) {
			throw new RuntimeException("Error en la Búsqueda: NodoOrigen no encontrado");
		}
		
		addArco(nodoOrigen, nodoDestino);
	}
	
	public void addArco(Nodo nodoOrigen, Nodo nodoDestino)
	{
		Arco arco = new Arco();
		arco.setOrigen(nodoOrigen);
		arco.setDestino(nodoDestino);
		arcos.add(arco);
		nodoOrigen.agregarArco(arco);
		
		arco = new Arco();
		arco.setOrigen(nodoDestino);
		arco.setDestino(nodoOrigen);
		arcos.add(arco);
		nodoDestino.agregarArco(arco);
	}
	
	public Nodo buscarNodo(String nombre) 
	{
		for (Nodo nodo: nodos) {
			if (nodo.getNombre().equals(nombre)) {
				return nodo;
			}
		}
		return null;
	}
	
	public boolean existeRuta(String origen, String destino) throws Exception {
		if (buscarRuta(origen, destino) != null) {
			return true;
		}
		return false;
	}
	
	public List<Nodo> buscarRuta(String origen, String destino) throws Exception {
		
		Nodo nodoOrigen = buscarNodo(origen);
		Nodo nodoDestino = buscarNodo(destino);
		List<Nodo> nodosRuta = new ArrayList<>();
		
		// Origen o destino no encontrado
		if (nodoOrigen == null) {
			throw new RuntimeException("Error en la Búsqueda: NodoOrigen no encontrado");
		}
		if (nodoDestino == null) {
			throw new RuntimeException("Error en la Búsqueda: NodoOrigen no encontrado");
		}
		
		if (buscarRutaBFS(nodosRuta, nodoOrigen, nodoDestino)) {
			return nodosRuta;
		} else {
			return null;
		}
	}
	
	private boolean buscarRutaBFS(List<Nodo> nodosRuta, Nodo nodoOrigen, Nodo nodoDestino) {
		
		// origen y destino son el mismo ?
		if(nodoOrigen.getNombre().equals(nodoDestino.getNombre())){
			System.err.println("Nodo destino encontrado en el mismo nodo origen");
		    nodosRuta.add(nodoOrigen);
            return true;
        }
		
		// si no son el mismo, revise las rutas usando una cola
        Queue<Nodo> queue = new LinkedList<>();
		ArrayList<Nodo> nodosVisitados = new ArrayList<>();
        
        queue.add(nodoOrigen);
        nodosVisitados.add(nodoOrigen);

        while(!queue.isEmpty()){
        	
            Nodo actual = queue.remove();
            if(actual.equals(nodoDestino)) {
            	nodosRuta.add(actual);
                return true;
            }
            else{
                if(actual.getNodosAdyacentes().isEmpty())
                    return false;
                else {
                	for (Nodo nodo: actual.getNodosAdyacentes()) {
                		if (!nodosVisitados.contains(nodo))
                			queue.add(nodo);
                	}
                }
                    
            }
            if (!nodosRuta.contains(actual))
            	nodosRuta.add(actual);
        }

        return false;        
	}	
	
}
