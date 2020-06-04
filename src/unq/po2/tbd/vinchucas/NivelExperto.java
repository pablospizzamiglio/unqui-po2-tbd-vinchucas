package unq.po2.tbd.vinchucas;

public class NivelExperto implements Nivel {

	@Override
	public Boolean esExperto() {
		return false;
	}

	@Override
	public void cambiarNivel(Usuario usuario) {
		usuario.setNivel(new NivelExperto());
	}

}
