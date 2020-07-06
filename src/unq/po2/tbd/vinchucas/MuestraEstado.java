package unq.po2.tbd.vinchucas;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class MuestraEstado {
	
	public abstract Boolean puedeOpinar(Muestra muestra, Usuario usuario);
	
	public abstract NivelVerificacion getNivelVerificacion();
		
	// Template method
	public void recalcularEstado(Muestra muestra) {
		if (this.puedeCambiarEstado(muestra)) {
			this.cambiarAlSiguienteEstado(muestra);
		}
	}
		
	// Primitive Operation
	public abstract Boolean puedeCambiarEstado(Muestra muestra);
	
	/**
	 * Dispara el cambio de estado sobre una muestra. 
	 * 
	 * Por defecto no realiza cambios para no forzar la redefinición en un estado final.
	 * 
	 * Hook method
	 * 
	 * @param muestra
	 */
	public void cambiarAlSiguienteEstado(Muestra muestra) {
		// Hook method que por defecto no tiene comportamiento
	}

	// Template method
	public Calificacion resultadoActual(Muestra muestra) {
		Calificacion resultadoActual = Calificacion.INDEFINIDA;
		
		if (muestra.getOpiniones().size() > 1) {
			Map<Calificacion, Long> votosPorOpinion = this.calcularVotosPorOpinion(muestra);
			Long votoMaximal = this.calcularVotoMaximal(votosPorOpinion);
			List<Calificacion> opinionesEmpatadas = this.recolectarOpinionesEmpatadas(votosPorOpinion, votoMaximal);
			
			// Si hay un solo elemento en la lista no hay empate
			if (opinionesEmpatadas.size() == 1) {
				resultadoActual = opinionesEmpatadas.get(0);
			}
		}
		
		return resultadoActual;
	}
	
	/**
	 * Calcula la cantidad de votos por cada opinión perteneciente a la muestra mediante la asociación
	 * de una calificación con su respectiva cantidad de votos
	 * 
	 * Hook method
	 *
	 * @param muestra la instancia de {@code Muestra} sobre la cual calcular los votos
	 * @return una instancia de {@code Map} que lleva como clave la {@code Clasificacion} de la {@code Opinion} 
	 * y la cantidad de veces que fue votada como valor
	 */
	public Map<Calificacion, Long> calcularVotosPorOpinion(Muestra muestra) {
		return muestra.getOpiniones().stream()
				.collect(Collectors.groupingBy(Opinion::getCalificacion, Collectors.counting()));
	}
	
	/**
	 * Calcula el número de votos máximo de un {@code Map} provisto.
	 * 
	 * Hook method
	 * 
	 * @param votosPorOpinion una asociación asociación de instancias de {@code Calificacion} 
	 * con su respectiva cantidad de votos  
	 * @return el voto maximal como instancia de {@code Long}
	 */
	public Long calcularVotoMaximal(Map<Calificacion, Long> votosPorOpinion) {
		return Collections.max(votosPorOpinion.values());
	}
	
	/**
	 * Recolecta las opiniones que tienen la cantidad de votos provista
	 * 
	 * Hook method
	 * 
	 * @param votosPorOpinion una asociación asociación de instancias de {@code Calificacion} 
	 * con su respectiva cantidad de votos
	 * @param votoMaximal la mayor cantidad de votos
	 * @return una lista de instancias de {@code Calificacion} que tienen la misma cantidad de votos
	 */
	public List<Calificacion> recolectarOpinionesEmpatadas(Map<Calificacion, Long> votosPorOpinion, Long votoMaximal) {
		return votosPorOpinion.entrySet().stream()
			    .filter(e -> e.getValue() == votoMaximal)
			    .map(e -> e.getKey())
			    .collect(Collectors.toList());
	}
	
	
}
