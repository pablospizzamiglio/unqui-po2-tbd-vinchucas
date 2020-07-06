package unq.po2.tbd.vinchucas;

import java.util.ArrayList;
import java.util.List;

public class CriterioTipoInsecto implements CriterioDeBusqueda {
	private Calificacion calificacion ;
	
	public CriterioTipoInsecto(Calificacion calificacion) {
		this.calificacion = calificacion;
	}

	@Override
	public List<Muestra> buscarPorCriterio(List<Muestra> muestras) {
		List <Muestra> resultado = new ArrayList <Muestra>() ;
		 for(Muestra m:muestras) {
			 if(m.getEspecie()==(this.calificacion)) {
				 resultado.add(m);
			 }
		 }
		return resultado;
	 }
	}



