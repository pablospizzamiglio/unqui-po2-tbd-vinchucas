package unq.po2.tbd.vinchucas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestEspecieInsecto {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void test() {
		assertEquals("Vinchuca Infestans", Calificacion.VINCHUCA_INFESTANS.getEtiqueta());
		assertEquals("Vinchuca Guasayana", Calificacion.VINCHUCA_GUASAYANA.getEtiqueta());
		assertEquals("Vinchuca Sordida", Calificacion.VINCHUCA_SORDIDA.getEtiqueta());
		assertEquals("Chince Foliada", Calificacion.CHINCHE_FOLIADA.getEtiqueta());
		assertEquals("Phtia-Chinche", Calificacion.PHTIA_CHINCHE.getEtiqueta());
		assertEquals("Imagen poco clara", Calificacion.IMAGEN_POCO_CLARA.getEtiqueta());
		assertEquals("Indefinida", Calificacion.INDEFINIDA.getEtiqueta());
	}

}
