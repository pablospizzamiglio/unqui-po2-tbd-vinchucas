package unq.po2.tbd.vinchucas;

public class Especialista extends Nivel{

	@Override
	public Boolean esExperto(Usuario usuario) {
		
		return true;
	}

	@Override
	public void cambiarNivel(Usuario usuario) {
		//USUARIO ESPECIALISTA NO PUEDE CAMBIAR DE NIVEL, SU NIVEL SIEMPRE SERA EXPERTO.
	}



}
