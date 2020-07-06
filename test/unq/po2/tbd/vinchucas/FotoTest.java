package unq.po2.tbd.vinchucas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class FotoTest {
	
	private Imagen foto;
	private String nombreArchivo;

	@BeforeEach
	void setUp() throws Exception {
		this.nombreArchivo = "vinchuca.jpg";
		this.foto = new Foto(this.nombreArchivo);
	}
	
	@Test
	void testConstructor() {
		assertNotNull(this.foto);
	}

	@Test
	void testGetNombreArchivo() {
		assertEquals(this.nombreArchivo, this.foto.getNombreArchivo());
	}

}
