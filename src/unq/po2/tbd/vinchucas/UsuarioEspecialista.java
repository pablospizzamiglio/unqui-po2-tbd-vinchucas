package unq.po2.tbd.vinchucas;

public class UsuarioEspecialista extends Usuario {

	public UsuarioEspecialista(Aplicacion aplicacion, String identificacion) {
		super(aplicacion, identificacion);
		this.setNivel(new NivelExperto());
	}
	
	@Override
	public void setNivel(Nivel nivel) {
		if (this.getNivel() == null) {
			super.setNivel(nivel);
		}
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
