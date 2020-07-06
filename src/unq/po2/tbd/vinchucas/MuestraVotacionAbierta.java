package unq.po2.tbd.vinchucas;

public class MuestraVotacionAbierta extends MuestraEstado {

	public MuestraVotacionAbierta() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Boolean puedeOpinar(Muestra muestra, Usuario usuario) {
		return !muestra.opinoPreviamente(usuario);
	}

	@Override
	public NivelVerificacion getNivelVerificacion() {
		return NivelVerificacion.VOTADA;
	}

	@Override
	public Boolean puedeCambiarEstado(Muestra muestra) {
		Long opinionesExpertas = muestra.getOpiniones().stream()
				.filter(o -> o.esDeUsuarioExperto())
				.count();
		return opinionesExpertas == 1;
	}

	@Override
	public void cambiarAlSiguienteEstado(Muestra muestra) {
		muestra.setEstado(new MuestraVotacionExperto());
	}
	
}
