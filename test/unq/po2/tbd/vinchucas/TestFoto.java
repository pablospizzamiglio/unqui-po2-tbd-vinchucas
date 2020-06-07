package unq.po2.tbd.vinchucas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestFoto {
	
	private Imagen foto;
	private String nombreArchivo;

	@BeforeEach
	void setUp() throws Exception {
		this.nombreArchivo = "/home/usuario/pictures/vinchuca.jpg";
		this.foto = new Foto(this.nombreArchivo);
	}

	@Test
	void testGetNombreArchivo() {
		assertEquals(this.nombreArchivo, this.foto.getNombreArchivo());
	}

}
