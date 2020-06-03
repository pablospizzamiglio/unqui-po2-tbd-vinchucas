package unq.po2.tbd.vinchucas;

public interface Nivel {
	
	public Boolean esExperto();
	
	public void opinarSobreMuestra(Muestra muestra, Usuario usuario, String calificacion);
	
	public void enviarMuestra(Muestra muestra, Usuario usuario, String calificacion);

}
