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
		assertEquals("Vinchuca Infestans", EspecieInsecto.VINCHUCA_INFESTANS.getEtiqueta());
		assertEquals("Vinchuca Guasayana", EspecieInsecto.VINCHUCA_GUASAYANA.getEtiqueta());
		assertEquals("Vinchuca Sordida", EspecieInsecto.VINCHUCA_SORDIDA.getEtiqueta());
		assertEquals("Chince Foliada", EspecieInsecto.CHINCHE_FOLIADA.getEtiqueta());
		assertEquals("Phtia-Chinche", EspecieInsecto.PHTIA_CHINCHE.getEtiqueta());
		assertEquals("Imagen poco clara", EspecieInsecto.IMAGEN_POCO_CLARA.getEtiqueta());
		assertEquals("Indefinida", EspecieInsecto.INDEFINIDA.getEtiqueta());
	}

}
