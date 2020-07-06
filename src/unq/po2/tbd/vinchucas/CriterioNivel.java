package unq.po2.tbd.vinchucas;

import java.util.ArrayList;
import java.util.List;

public class CriterioNivel implements CriterioDeBusqueda {
	private NivelVerificacion nivel;
	
	public CriterioNivel(NivelVerificacion nivel) {
		this.nivel = nivel;
	}

	@Override
	public List<Muestra> buscarPorCriterio(List<Muestra> muestras) {
		List <Muestra> resultado = new ArrayList <Muestra>() ;
		 for(Muestra m:muestras) {
			 if(m.getNivelVerificacion().equals(this.nivel)) {
				 resultado.add(m);
			 }
		 }
		return resultado;
	 }
	
	}
