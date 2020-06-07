package unq.po2.tbd.vinchucas;

public enum Calificacion {
	
	VINCHUCA_INFESTANS("Vinchuca Infestans"),
	VINCHUCA_GUASAYANA("Vinchuca Guasayana"),
	VINCHUCA_SORDIDA("Vinchuca Sordida"),
	CHINCHE_FOLIADA("Chince Foliada"),
	PHTIA_CHINCHE("Phtia-Chinche"),
	IMAGEN_POCO_CLARA("Imagen poco clara"),
	INDEFINIDA("Indefinida");
	
	private final String etiqueta;
	
	private Calificacion(String etiqueta) {
		this.etiqueta = etiqueta;
	}
	
	public String getEtiqueta() {
		return this.etiqueta;
	}

}
