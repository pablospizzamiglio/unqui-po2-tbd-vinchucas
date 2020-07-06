package unq.po2.tbd.vinchucas;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


class OpinionTest {
	
	@Mock Usuario usuarioBasico;
	@Mock Usuario usuarioExperto;
	@Mock Opinion opinion;
	LocalDate hoy;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.hoy = LocalDate.now();
		when(this.usuarioBasico.esExperto()).thenReturn(false);
		when(this.usuarioExperto.esExperto()).thenReturn(true);
		this.opinion = new Opinion(Calificacion.IMAGEN_POCO_CLARA, this.hoy, this.usuarioBasico);
	}
	
	@Test
	void testConstructor() {
		// verificación
		assertNotNull(this.opinion);
	}
	
	@Test
	void testGetCalificacion() {
		// SUT
		Calificacion resultado = this.opinion.getCalificacion();
		// verificación
		assertEquals(Calificacion.IMAGEN_POCO_CLARA, resultado);
	}
	
	@Test
	void testGetFecha() {
		// SUT
		LocalDate resultado = this.opinion.getFecha();
		// verificación
		assertEquals(this.hoy, resultado);
	}
	
	@Test
	void testGetUsuario() {
		// SUT
		Usuario resultado = this.opinion.getUsuario();
		// verificación
		assertEquals(this.usuarioBasico, resultado);
	}
	
	@Test
	void testEsDeUsuarioExperto() {
		// SUT
		Boolean resultado = this.opinion.esDeUsuarioExperto();
		// verificación
		assertFalse(resultado);
	}

}
