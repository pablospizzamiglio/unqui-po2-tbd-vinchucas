package unq.po2.tbd.vinchucas;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CriterioFechaCreacion implements CriterioDeBusqueda{
	private LocalDate fechaCreacion;

	@Override
	public List<Muestra> buscarPorCriterio(List<Muestra> muestras) {
		List <Muestra> resultado = new ArrayList <Muestra>() ;
		 for(Muestra m:muestras) {
			 if(m.getFecha().equals(this.fechaCreacion)) {
				 resultado.add(m);
			 }
		 }
		return resultado;
	}
	
	public CriterioFechaCreacion(LocalDate fecha) {
		this.fechaCreacion = fecha;
	}

}

