package unq.po2.tbd.vinchucas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CalificacionTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testGetEtiqueta() {
		assertEquals("Vinchuca Infestans", Calificacion.VINCHUCA_INFESTANS.getEtiqueta());
	}

}
