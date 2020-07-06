package unq.po2.tbd.vinchucas;

import java.util.ArrayList;
import java.util.List;

public class CriterioCompuesto implements CriterioDeBusqueda {
	private List<CriterioDeBusqueda> criterios;
	
	public CriterioCompuesto() {
	  this.criterios = new ArrayList <CriterioDeBusqueda> ();
	}
	
	public void agregarCriterio(CriterioDeBusqueda criterio) {
		this.getCriterios().add(criterio);
	}
	
	public List<CriterioDeBusqueda> getCriterios() {
		return criterios;
	}

	@Override
	public List<Muestra> buscarPorCriterio(List<Muestra> muestras) {
		List <Muestra> resultado = new ArrayList <Muestra>() ;
		 this.getCriterios().stream().forEach ((c)-> {
			resultado.addAll(c.buscarPorCriterio(muestras));
		});
		return resultado;
	}

	
}

