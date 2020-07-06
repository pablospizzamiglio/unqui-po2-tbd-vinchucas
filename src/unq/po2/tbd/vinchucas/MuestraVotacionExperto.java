package unq.po2.tbd.vinchucas;

import java.util.Map;
import java.util.stream.Collectors;

public class MuestraVotacionExperto extends MuestraEstado {

	public MuestraVotacionExperto() {
		// TODO Auto-generated constructor stub
	}

	public Boolean puedeOpinar(Muestra muestra, Usuario usuario) {
		return !muestra.opinoPreviamente(usuario) && usuario.esExperto();
	}

	@Override
	public NivelVerificacion getNivelVerificacion() {
		return NivelVerificacion.VOTADA;
	}

	@Override
	public Boolean puedeCambiarEstado(Muestra muestra) {
		Calificacion resultadoActual = muestra.resultadoActual();
		
		Long numeroOpinionesExpertasIgualesAlResultadoActual = muestra.getOpiniones().stream()
				.filter(o -> o.esDeUsuarioExperto() && o.getCalificacion() == resultadoActual)
				.count();
		
		return numeroOpinionesExpertasIgualesAlResultadoActual == 2;
	}
	
	@Override
	public void cambiarAlSiguienteEstado(Muestra muestra) {
		muestra.setEstado(new MuestraVerificada());
	}
	
	/**
	* Calcula la cantidad de votos expertos por cada opinión perteneciente a la muestra mediante la asociación
	* de una calificación con su respectiva cantidad de votos
	*
	* @param muestra la instancia de {@code Muestra} sobre la cual calcular los votos
	* @return una instancia de {@code Map} que lleva como clave la {@code Clasificacion} de la {@code Opinion} 
	* y la cantidad de veces que fue votada como valor
	*/
	@Override
	public Map<Calificacion, Long> calcularVotosPorOpinion(Muestra muestra) {
		return muestra.getOpiniones().stream()
				.filter(o -> o.esDeUsuarioExperto())
				.collect(Collectors.groupingBy(Opinion::getCalificacion, Collectors.counting()));
	}

}
