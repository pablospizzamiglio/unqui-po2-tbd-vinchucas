package unq.po2.tbd.vinchucas;

public class UsuarioEspecialista extends Usuario {

	public UsuarioEspecialista(Aplicacion aplicacion, String identificacion) {
		super(aplicacion, identificacion);
		this.setNivel(new NivelExperto());
	}
	
	@Override
	public Boolean puedeCambiarNivel() {
		return false;
	}
	
	@Override
	public Boolean esExperto() {
		return true;
	}

}
