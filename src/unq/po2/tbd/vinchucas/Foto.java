package unq.po2.tbd.vinchucas;

public class Foto implements Imagen {
	
	private String nombreArchivo;

	public Foto(String nombreArchivo) {
		this.setNombreArchivo(nombreArchivo);
	}

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}
	
}
