package unq.po2.tbd.vinchucas;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CriterioFechaUltimaVotacion implements CriterioDeBusqueda {
	private LocalDate fechaVotacion;
	
	public CriterioFechaUltimaVotacion(LocalDate fecha) {
		this.fechaVotacion = fecha;
	}

	@Override
	public List<Muestra> buscarPorCriterio(List<Muestra> muestras) {
		List <Muestra> resultado = new ArrayList <Muestra>() ;
		 for(Muestra m:muestras) {
			 if(m.getFechaUltimaVotacion().equals(this.fechaVotacion)) {
				 resultado.add(m);
			 }
		 }
		return resultado;
	}

}
