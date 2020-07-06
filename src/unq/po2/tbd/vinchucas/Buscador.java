package unq.po2.tbd.vinchucas;


import java.util.ArrayList;
import java.util.List;

public class Buscador {
	
	public Buscador() {
		
	}
	
	public List<Muestra> buscar(List<Muestra> muestras, List<CriterioDeBusqueda> criterios) {
		List <Muestra> resultado = new ArrayList <Muestra>() ;
		criterios.stream().forEach ((c)-> {			
			c.buscarPorCriterio(muestras).forEach(m -> { 
				if (!resultado.contains(m)) {
					resultado.add(m);
				}
			});
		});
		return resultado;
	} 
	
}
