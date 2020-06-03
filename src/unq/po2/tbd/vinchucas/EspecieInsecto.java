package unq.po2.tbd.vinchucas;

public enum EspecieInsecto {
	
	VINCHUCA_INFESTANS("Vinchuca Infestans"),
	VINCHUCA_GUASAYANA("Vinchuca Guasayana"),
	VINCHUCA_SORDIDA("Vinchuca Sordida"),
	CHINCHE_FOLIADA("Chince Foliada"),
	PHTIA_CHINCHE("Phtia-Chinche");
	
	private final String etiqueta;
	
	private EspecieInsecto(String etiqueta) {
		this.etiqueta = etiqueta;
	}
	
	public String getEtiqueta() {
		return this.etiqueta;
	}

}
