package unq.po2.tbd.vinchucas;

public class Basico extends Nivel{

	@Override
	public Boolean esExperto(Usuario usuario) {
		
		return usuario.puedeCambiarNivel();
	}

	@Override
	public void cambiarNivel(Usuario usuario) {
		
		if(usuario.esExperto()) {
			
				usuario.setNivel(new Experto());
		}
	}



}
