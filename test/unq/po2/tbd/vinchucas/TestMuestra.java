package unq.po2.tbd.vinchucas;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestMuestra {
	
	Usuario usuarioBasico1;
	Usuario usuarioBasico2;
	Usuario usuarioBasico3;
	Usuario usuarioBasico4;
	Usuario usuarioBasico5;
	Usuario usuarioExperto1;
	Usuario usuarioExperto2;
	Usuario usuarioExperto3;
	Ubicacion ubicacionMuestra;
	LocalDate hoy;
	Muestra muestra;

	@BeforeEach
	void setUp() throws Exception {
		usuarioBasico1 = mock(Usuario.class);
		usuarioBasico2 = mock(Usuario.class);
		usuarioBasico3 = mock(Usuario.class);
		usuarioBasico4 = mock(Usuario.class);
		usuarioBasico5 = mock(Usuario.class);
		usuarioExperto1 = mock(Usuario.class);
		usuarioExperto2 = mock(Usuario.class);
		usuarioExperto3 = mock(Usuario.class);
		ubicacionMuestra = mock(Ubicacion.class);
		hoy = LocalDate.now();
	}

	@Test
	void testUsuarioBasicoCreaMuestra() {
		when(usuarioBasico1.getIdentificacion()).thenReturn("usuario_basico");
		
		muestra = new Muestra(usuarioBasico1, "foto.jpg", hoy, ubicacionMuestra, "vinchuca infestans");
		
		assertEquals(1, muestra.getOpiniones().size());
		assertEquals("usuario_basico", muestra.getIdentificacionUsuario());
		assertEquals("foto.jpg", muestra.getFoto());
		assertEquals(ubicacionMuestra, muestra.getUbicacion());
		assertEquals("vinchuca infestans", muestra.getEspecie());
		assertEquals(hoy, muestra.getFecha());

	}
	
	@Test
	void testUsuarioExpertoCreaMuestra() {
		when(usuarioExperto1.getIdentificacion()).thenReturn("usuario_experto");
		when(usuarioExperto1.esExperto()).thenReturn(true);
		
		muestra = new Muestra(usuarioExperto1, "foto.jpg", hoy, ubicacionMuestra, "vinchuca infestans");
		
		assertEquals(1, muestra.getOpiniones().size());
		assertEquals("usuario_experto", muestra.getIdentificacionUsuario());
		assertEquals("foto.jpg", muestra.getFoto());
		assertEquals(ubicacionMuestra, muestra.getUbicacion());
		assertEquals("vinchuca infestans", muestra.getEspecie());
		assertEquals(hoy, muestra.getFecha());

	}
	
	@Test
	void testUsuarioBasicoOpinaLuegoDeUsuarioBasico() {
		when(usuarioBasico1.getIdentificacion()).thenReturn("usuario_basico_1");
		when(usuarioBasico1.esExperto()).thenReturn(false);
		
		when(usuarioBasico2.getIdentificacion()).thenReturn("usuario_basico_2");
		when(usuarioBasico2.esExperto()).thenReturn(false);
		
		muestra = new Muestra(usuarioBasico1, "foto.jpg", hoy, ubicacionMuestra, "vinchuca infestans");				
		muestra.agregarOpinion("chinche foliada", hoy, usuarioBasico2);
		
		assertEquals(2, muestra.getOpiniones().size());
		assertEquals("no definido", muestra.resultadoActual());
	}
	
	@Test
	void testUsuarioExpertoOpinaLuegoDeUsuarioExperto() {
		when(usuarioExperto1.getIdentificacion()).thenReturn("usuario_experto_1");
		when(usuarioExperto1.esExperto()).thenReturn(true);
		
		when(usuarioExperto2.getIdentificacion()).thenReturn("usuario_experto_2");
		when(usuarioExperto2.esExperto()).thenReturn(true);
		
		muestra = new Muestra(usuarioExperto1, "foto.jpg", hoy, ubicacionMuestra, "vinchuca infestans");			
		muestra.agregarOpinion("chinche foliada", hoy, usuarioExperto2);
		
		assertEquals(2, muestra.getOpiniones().size());
		assertEquals("no definido", muestra.resultadoActual());
	}
	
	@Test
	void testUsuarioExpertoOpinaLuegoDeUsuarioExpertoYCoincidenVerificandoAsiLaMuestra() {
		when(usuarioExperto1.getIdentificacion()).thenReturn("usuario_experto_1");
		when(usuarioExperto1.esExperto()).thenReturn(true);
		
		when(usuarioExperto2.getIdentificacion()).thenReturn("usuario_experto_2");
		when(usuarioExperto2.esExperto()).thenReturn(true);
		
		muestra = new Muestra(usuarioExperto1, "foto.jpg", hoy, ubicacionMuestra, "vinchuca infestans");	
		muestra.agregarOpinion("vinchuca infestans", hoy, usuarioExperto2);
		
		assertEquals(2, muestra.getOpiniones().size());
		assertTrue(muestra.estaVerificada());
		assertEquals("vinchuca infestans", muestra.resultadoActual());
	}
	
	@Test
	void testTresUsuariosBasicosCoincidenLuegoDeDos() {
		when(usuarioBasico1.getIdentificacion()).thenReturn("usuario_basico_1");
		when(usuarioBasico1.esExperto()).thenReturn(false);
		
		when(usuarioBasico2.getIdentificacion()).thenReturn("usuario_basico_2");
		when(usuarioBasico2.esExperto()).thenReturn(false);
		
		when(usuarioBasico3.getIdentificacion()).thenReturn("usuario_basico_3");
		when(usuarioBasico3.esExperto()).thenReturn(false);
		
		when(usuarioBasico4.getIdentificacion()).thenReturn("usuario_basico_4");
		when(usuarioBasico4.esExperto()).thenReturn(false);
		
		when(usuarioBasico5.getIdentificacion()).thenReturn("usuario_basico_5");
		when(usuarioBasico5.esExperto()).thenReturn(false);
		
		muestra = new Muestra(usuarioBasico1, "foto.jpg", hoy, ubicacionMuestra, "vinchuca infestans");			
		muestra.agregarOpinion("vinchuca infestans", hoy, usuarioBasico2);
		muestra.agregarOpinion("imagen poco clara", hoy, usuarioBasico3);
		muestra.agregarOpinion("imagen poco clara", hoy, usuarioBasico4);
		muestra.agregarOpinion("imagen poco clara", hoy, usuarioBasico5);
		
		assertEquals(5, muestra.getOpiniones().size());
		assertEquals("imagen poco clara", muestra.resultadoActual());
	}
	
	@Test
	void testUsuarioBasicoTrataDeOpinarSobreMuestraVerificada() {
		when(usuarioExperto1.getIdentificacion()).thenReturn("usuario_experto_1");
		when(usuarioExperto1.esExperto()).thenReturn(true);
		
		when(usuarioExperto2.getIdentificacion()).thenReturn("usuario_experto_2");
		when(usuarioExperto2.esExperto()).thenReturn(true);
		
		when(usuarioBasico1.getIdentificacion()).thenReturn("usuario_basico_1");
		when(usuarioBasico1.esExperto()).thenReturn(false);
		
		muestra = new Muestra(usuarioExperto1, "foto.jpg", hoy, ubicacionMuestra, "vinchuca infestans");			
		muestra.agregarOpinion("vinchuca infestans", hoy, usuarioExperto2);
		muestra.agregarOpinion("imagen poco clara", hoy, usuarioBasico1);
		
		assertEquals(2, muestra.getOpiniones().size());
		assertEquals("vinchuca infestans", muestra.resultadoActual());
		assertTrue(muestra.estaVerificada());
	}
	
	@Test
	void testUsuarioExpertoTrataDeOpinarSobreMuestraVerificada() {
		when(usuarioExperto1.getIdentificacion()).thenReturn("usuario_experto_1");
		when(usuarioExperto1.esExperto()).thenReturn(true);
		
		when(usuarioExperto2.getIdentificacion()).thenReturn("usuario_experto_2");
		when(usuarioExperto2.esExperto()).thenReturn(true);
		
		when(usuarioExperto3.getIdentificacion()).thenReturn("usuario_experto_3");
		when(usuarioExperto3.esExperto()).thenReturn(false);
		
		muestra = new Muestra(usuarioExperto1, "foto.jpg", hoy, ubicacionMuestra, "vinchuca infestans");			
		muestra.agregarOpinion("vinchuca infestans", hoy, usuarioExperto2);
		muestra.agregarOpinion("imagen poco clara", hoy, usuarioExperto3);
		
		assertEquals(2, muestra.getOpiniones().size());
		assertEquals("vinchuca infestans", muestra.resultadoActual());
		assertTrue(muestra.estaVerificada());
	}
	
	@Test
	void testUsuarioBasicoIntentaOpinarDosVeces() {
		when(usuarioBasico1.getIdentificacion()).thenReturn("usuario_basico_1");
		when(usuarioBasico1.esExperto()).thenReturn(true);
		
		muestra = new Muestra(usuarioBasico1, "foto.jpg", hoy, ubicacionMuestra, "vinchuca infestans");			
		muestra.agregarOpinion("vinchuca infestans", hoy, usuarioBasico1);
		
		assertEquals(1, muestra.getOpiniones().size());
		assertEquals("no definido", muestra.resultadoActual());
		assertFalse(muestra.estaVerificada());
	}
	
	@Test
	void testUsuarioExpertoIntentaOpinarDosVeces() {
		when(usuarioExperto1.getIdentificacion()).thenReturn("usuario_experto_1");
		when(usuarioExperto1.esExperto()).thenReturn(true);
		
		muestra = new Muestra(usuarioExperto1, "foto.jpg", hoy, ubicacionMuestra, "vinchuca infestans");			
		muestra.agregarOpinion("vinchuca infestans", hoy, usuarioExperto1);
		
		assertEquals(1, muestra.getOpiniones().size());
		assertEquals("no definido", muestra.resultadoActual());
		assertFalse(muestra.estaVerificada());
	}

}
