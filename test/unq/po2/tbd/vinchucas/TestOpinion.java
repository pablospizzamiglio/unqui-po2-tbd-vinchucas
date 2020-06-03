package unq.po2.tbd.vinchucas;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestOpinion {
	
	Usuario usuarioBasico;
	Usuario usuarioExperto;
	Opinion opinion;
	LocalDate hoy;

	@BeforeEach
	void setUp() throws Exception {
		usuarioBasico = mock(Usuario.class);
		usuarioExperto = mock(Usuario.class);
		hoy = LocalDate.now();
	}

	@Test
	void testCreationOpinionUsuarioBasico() {
		when(usuarioBasico.getIdentificacion()).thenReturn("usuario_basico");
		when(usuarioBasico.esExperto()).thenReturn(false);
		
		opinion = new Opinion("imagen poco clara", hoy, usuarioBasico);
		
		assertEquals("imagen poco clara", opinion.getCalificacion());
		assertEquals(hoy, opinion.getFecha());
		assertEquals(usuarioBasico, opinion.getUsuario());
		assertFalse(opinion.esDeUsuarioExperto());
	}
	
	@Test
	void testCreationOpinionUsuarioExperto() {
		when(usuarioExperto.getIdentificacion()).thenReturn("usuario_experto");
		when(usuarioExperto.esExperto()).thenReturn(true);
		
		opinion = new Opinion("vinchuca infestans", hoy, usuarioExperto);
		
		assertEquals("vinchuca infestans", opinion.getCalificacion());
		assertEquals(hoy, opinion.getFecha());
		assertEquals(usuarioExperto, opinion.getUsuario());
		assertTrue(opinion.esDeUsuarioExperto());
	}

}