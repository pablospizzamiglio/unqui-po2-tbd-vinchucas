package unq.po2.tbd.vinchucas;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
	Ubicacion buenosAires;
	Ubicacion santaFe;
	Ubicacion cordoba;
	Ubicacion tucuman;
	Ubicacion salta;
	LocalDate hoy;
	Muestra muestra;
	Opinion opinion1;
	Opinion opinion2;
	Opinion opinion3;
	Opinion opinion4;
	Opinion opinion5;
	Imagen imagen1;
	Imagen imagen2;
	Imagen imagen3;
	Imagen imagen4;
	Imagen imagen5;

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
		
		buenosAires = mock(Ubicacion.class);
		santaFe = mock(Ubicacion.class);
		cordoba = mock(Ubicacion.class);
		tucuman = mock(Ubicacion.class);
		salta = mock(Ubicacion.class);
		
		hoy = LocalDate.now();
		
		opinion1 = mock(Opinion.class);
		opinion2 = mock(Opinion.class);
		opinion3 = mock(Opinion.class);
		opinion4 = mock(Opinion.class);
		opinion5 = mock(Opinion.class);
		
		imagen1 = mock(Imagen.class);
		imagen2 = mock(Imagen.class);
		imagen3 = mock(Imagen.class);
		imagen4 = mock(Imagen.class);
		imagen5 = mock(Imagen.class);
	}

	@Test
	void testUsuarioBasicoCreaMuestra() {
		when(usuarioBasico1.getIdentificacion()).thenReturn("usuario_basico");
		when(usuarioBasico1.esExperto()).thenReturn(false);
		when(opinion1.getCalificacion()).thenReturn(Calificacion.VINCHUCA_INFESTANS);
		when(opinion1.getUsuario()).thenReturn(usuarioBasico1);
		when(opinion1.esDeUsuarioExperto()).thenReturn(false);
		when(opinion1.getFecha()).thenReturn(hoy);
		
		muestra = new Muestra(imagen1, buenosAires, opinion1);
		
		assertEquals(1, muestra.getOpiniones().size());
		assertEquals("usuario_basico", muestra.getIdentificacionUsuario());
		assertEquals(imagen1, muestra.getFoto());
		assertEquals(buenosAires, muestra.getUbicacion());
		assertEquals(hoy, muestra.getFecha());
		assertEquals(Calificacion.INDEFINIDA, muestra.resultadoActual());
		assertEquals(hoy, muestra.getFechaUltimaVotacion());
		assertEquals(NivelVerificacion.VOTADA, muestra.getNivelVerificacion());
	}
	
	@Test
	void testUsuarioExpertoCreaMuestra() {	
		when(usuarioExperto1.getIdentificacion()).thenReturn("usuario_experto");
		when(usuarioExperto1.esExperto()).thenReturn(true);
		when(opinion1.getCalificacion()).thenReturn(Calificacion.VINCHUCA_INFESTANS);
		when(opinion1.getUsuario()).thenReturn(usuarioExperto1);
		when(opinion1.esDeUsuarioExperto()).thenReturn(false);
		when(opinion1.getFecha()).thenReturn(hoy);
		
		muestra = new Muestra(imagen1, buenosAires, opinion1);
		
		assertEquals(1, muestra.getOpiniones().size());
		assertEquals("usuario_experto", muestra.getIdentificacionUsuario());
		assertEquals(imagen1, muestra.getFoto());
		assertEquals(buenosAires, muestra.getUbicacion());
		assertEquals(hoy, muestra.getFecha());
		assertEquals(Calificacion.INDEFINIDA, muestra.resultadoActual());
		assertEquals(hoy, muestra.getFechaUltimaVotacion());
		assertEquals(NivelVerificacion.VOTADA, muestra.getNivelVerificacion());
	}
	
	@Test
	void testUsuarioBasicoOpinaLuegoDeUsuarioBasicoYNoCoinciden() {
		when(usuarioBasico1.getIdentificacion()).thenReturn("usuario_basico_1");
		when(usuarioBasico1.esExperto()).thenReturn(false);
		when(opinion1.getCalificacion()).thenReturn(Calificacion.VINCHUCA_INFESTANS);
		when(opinion1.getUsuario()).thenReturn(usuarioBasico1);
		when(opinion1.esDeUsuarioExperto()).thenReturn(false);
		when(opinion1.getFecha()).thenReturn(hoy);
		
		when(usuarioBasico2.getIdentificacion()).thenReturn("usuario_basico_2");
		when(usuarioBasico2.esExperto()).thenReturn(false);
		when(opinion2.getCalificacion()).thenReturn(Calificacion.CHINCHE_FOLIADA);
		when(opinion2.getUsuario()).thenReturn(usuarioBasico2);
		when(opinion2.esDeUsuarioExperto()).thenReturn(false);
		when(opinion2.getFecha()).thenReturn(hoy);
		
		muestra = new Muestra(imagen1, buenosAires, opinion1);				
		muestra.agregarOpinion(opinion2);
		
		assertEquals(2, muestra.getOpiniones().size());
		assertEquals(Calificacion.INDEFINIDA, muestra.resultadoActual());
		assertEquals(hoy, muestra.getFechaUltimaVotacion());
		assertEquals(NivelVerificacion.VOTADA, muestra.getNivelVerificacion());
	}
	
	@Test
	void testUsuarioExpertoOpinaLuegoDeUsuarioExpertoYNoCoinciden() {
		when(usuarioExperto1.getIdentificacion()).thenReturn("usuario_experto_1");
		when(usuarioExperto1.esExperto()).thenReturn(true);
		when(opinion1.getCalificacion()).thenReturn(Calificacion.VINCHUCA_INFESTANS);
		when(opinion1.getUsuario()).thenReturn(usuarioExperto1);
		when(opinion1.esDeUsuarioExperto()).thenReturn(true);
		when(opinion1.getFecha()).thenReturn(hoy);
		
		when(usuarioExperto2.getIdentificacion()).thenReturn("usuario_experto_2");
		when(usuarioExperto2.esExperto()).thenReturn(true);
		when(opinion2.getCalificacion()).thenReturn(Calificacion.CHINCHE_FOLIADA);
		when(opinion2.getUsuario()).thenReturn(usuarioExperto2);
		when(opinion2.esDeUsuarioExperto()).thenReturn(true);
		when(opinion2.getFecha()).thenReturn(hoy);
		
		muestra = new Muestra(imagen1, buenosAires, opinion1);			
		muestra.agregarOpinion(opinion2);
		
		assertEquals(2, muestra.getOpiniones().size());
		assertEquals(Calificacion.INDEFINIDA, muestra.resultadoActual());
		assertEquals(hoy, muestra.getFechaUltimaVotacion());
		assertEquals(NivelVerificacion.VOTADA, muestra.getNivelVerificacion());
	}
	
	@Test
	void testUsuarioExpertoOpinaLuegoDeUsuarioExpertoYCoincidenVerificandoAsiLaMuestra() {
		when(usuarioExperto1.getIdentificacion()).thenReturn("usuario_experto_1");
		when(usuarioExperto1.esExperto()).thenReturn(true);
		when(opinion1.getCalificacion()).thenReturn(Calificacion.VINCHUCA_INFESTANS);
		when(opinion1.getUsuario()).thenReturn(usuarioExperto1);
		when(opinion1.esDeUsuarioExperto()).thenReturn(true);
		when(opinion1.getFecha()).thenReturn(hoy.minusDays(1));
		
		when(usuarioExperto2.getIdentificacion()).thenReturn("usuario_experto_2");
		when(usuarioExperto2.esExperto()).thenReturn(true);
		when(opinion2.getCalificacion()).thenReturn(Calificacion.VINCHUCA_INFESTANS);
		when(opinion2.getUsuario()).thenReturn(usuarioExperto2);
		when(opinion2.esDeUsuarioExperto()).thenReturn(true);
		when(opinion2.getFecha()).thenReturn(hoy);
		
		muestra = new Muestra(imagen1, buenosAires, opinion1);	
		muestra.agregarOpinion(opinion2);
		
		assertEquals(2, muestra.getOpiniones().size());
		assertTrue(muestra.estaVerificada());
		assertEquals(Calificacion.VINCHUCA_INFESTANS, muestra.resultadoActual());
		assertEquals(hoy, muestra.getFechaUltimaVotacion());
		assertEquals(NivelVerificacion.VERIFICADA, muestra.getNivelVerificacion());
	}
	
	@Test
	void testTresUsuariosBasicosCoincidenLuegoDeDos() {
		when(usuarioBasico1.getIdentificacion()).thenReturn("usuario_basico_1");
		when(usuarioBasico1.esExperto()).thenReturn(false);
		when(opinion1.getCalificacion()).thenReturn(Calificacion.VINCHUCA_INFESTANS);
		when(opinion1.getUsuario()).thenReturn(usuarioBasico1);
		when(opinion1.esDeUsuarioExperto()).thenReturn(false);
		when(opinion1.getFecha()).thenReturn(hoy);
		
		when(usuarioBasico2.getIdentificacion()).thenReturn("usuario_basico_2");
		when(usuarioBasico2.esExperto()).thenReturn(false);
		when(opinion2.getCalificacion()).thenReturn(Calificacion.VINCHUCA_INFESTANS);
		when(opinion2.getUsuario()).thenReturn(usuarioBasico2);
		when(opinion2.esDeUsuarioExperto()).thenReturn(false);
		when(opinion2.getFecha()).thenReturn(hoy);
		
		when(usuarioBasico3.getIdentificacion()).thenReturn("usuario_basico_3");
		when(usuarioBasico3.esExperto()).thenReturn(false);
		when(opinion3.getCalificacion()).thenReturn(Calificacion.IMAGEN_POCO_CLARA);
		when(opinion3.getUsuario()).thenReturn(usuarioBasico3);
		when(opinion3.esDeUsuarioExperto()).thenReturn(false);
		when(opinion3.getFecha()).thenReturn(hoy);
		
		when(usuarioBasico4.getIdentificacion()).thenReturn("usuario_basico_4");
		when(usuarioBasico4.esExperto()).thenReturn(false);
		when(opinion4.getCalificacion()).thenReturn(Calificacion.IMAGEN_POCO_CLARA);
		when(opinion4.getUsuario()).thenReturn(usuarioBasico4);
		when(opinion4.esDeUsuarioExperto()).thenReturn(false);
		when(opinion4.getFecha()).thenReturn(hoy);
		
		when(usuarioBasico5.getIdentificacion()).thenReturn("usuario_basico_5");
		when(usuarioBasico5.esExperto()).thenReturn(false);
		when(opinion5.getCalificacion()).thenReturn(Calificacion.IMAGEN_POCO_CLARA);
		when(opinion5.getUsuario()).thenReturn(usuarioBasico5);
		when(opinion5.esDeUsuarioExperto()).thenReturn(false);
		when(opinion5.getFecha()).thenReturn(hoy);
		
		muestra = new Muestra(imagen1, buenosAires, opinion1);			
		muestra.agregarOpinion(opinion2);
		muestra.agregarOpinion(opinion3);
		muestra.agregarOpinion(opinion4);
		muestra.agregarOpinion(opinion5);
		
		assertEquals(5, muestra.getOpiniones().size());
		assertEquals(Calificacion.IMAGEN_POCO_CLARA, muestra.resultadoActual());
		assertEquals(hoy, muestra.getFechaUltimaVotacion());
		assertEquals(NivelVerificacion.VOTADA, muestra.getNivelVerificacion());
	}
	
	@Test
	void testUsuarioBasicoTrataDeOpinarSobreMuestraVerificada() {	
		when(usuarioExperto1.getIdentificacion()).thenReturn("usuario_experto_1");
		when(usuarioExperto1.esExperto()).thenReturn(true);
		when(opinion1.getCalificacion()).thenReturn(Calificacion.VINCHUCA_INFESTANS);
		when(opinion1.getUsuario()).thenReturn(usuarioExperto1);
		when(opinion1.esDeUsuarioExperto()).thenReturn(true);
		when(opinion1.getFecha()).thenReturn(hoy);
		
		when(usuarioExperto2.getIdentificacion()).thenReturn("usuario_experto_2");
		when(usuarioExperto2.esExperto()).thenReturn(true);
		when(opinion2.getCalificacion()).thenReturn(Calificacion.VINCHUCA_INFESTANS);
		when(opinion2.getUsuario()).thenReturn(usuarioExperto2);
		when(opinion2.esDeUsuarioExperto()).thenReturn(true);
		when(opinion2.getFecha()).thenReturn(hoy); 
		
		when(usuarioBasico3.getIdentificacion()).thenReturn("usuario_basico_1");
		when(usuarioBasico3.esExperto()).thenReturn(false);
		when(opinion3.getCalificacion()).thenReturn(Calificacion.VINCHUCA_INFESTANS);
		when(opinion3.getUsuario()).thenReturn(usuarioBasico3);
		when(opinion3.esDeUsuarioExperto()).thenReturn(false);
		when(opinion3.getFecha()).thenReturn(hoy);
		
		muestra = new Muestra(imagen1, buenosAires, opinion1);			
		muestra.agregarOpinion(opinion2);
		muestra.agregarOpinion(opinion3);
		
		assertEquals(2, muestra.getOpiniones().size());
		assertEquals(Calificacion.VINCHUCA_INFESTANS, muestra.resultadoActual());
		assertTrue(muestra.estaVerificada());
	}
	
	@Test
	void testUsuarioExpertoTrataDeOpinarSobreMuestraVerificada() {
		when(usuarioExperto1.getIdentificacion()).thenReturn("usuario_experto_1");
		when(usuarioExperto1.esExperto()).thenReturn(true);
		when(opinion1.getCalificacion()).thenReturn(Calificacion.VINCHUCA_INFESTANS);
		when(opinion1.getUsuario()).thenReturn(usuarioExperto1);
		when(opinion1.esDeUsuarioExperto()).thenReturn(true);
		when(opinion1.getFecha()).thenReturn(hoy);
		
		when(usuarioExperto2.getIdentificacion()).thenReturn("usuario_experto_2");
		when(usuarioExperto2.esExperto()).thenReturn(true);
		when(opinion2.getCalificacion()).thenReturn(Calificacion.VINCHUCA_INFESTANS);
		when(opinion2.getUsuario()).thenReturn(usuarioExperto2);
		when(opinion2.esDeUsuarioExperto()).thenReturn(true);
		when(opinion2.getFecha()).thenReturn(hoy); 
		
		when(usuarioExperto3.getIdentificacion()).thenReturn("usuario_experto_2");
		when(usuarioExperto3.esExperto()).thenReturn(true);
		when(opinion3.getCalificacion()).thenReturn(Calificacion.IMAGEN_POCO_CLARA);
		when(opinion3.getUsuario()).thenReturn(usuarioExperto3);
		when(opinion3.esDeUsuarioExperto()).thenReturn(true);
		when(opinion3.getFecha()).thenReturn(hoy);
		
		when(usuarioExperto3.getIdentificacion()).thenReturn("usuario_experto_3");
		when(usuarioExperto3.esExperto()).thenReturn(false);
		
		muestra = new Muestra(imagen1, buenosAires, opinion1);			
		muestra.agregarOpinion(opinion2);
		muestra.agregarOpinion(opinion3);
		
		assertEquals(2, muestra.getOpiniones().size());
		assertEquals(Calificacion.VINCHUCA_INFESTANS, muestra.resultadoActual());
		assertTrue(muestra.estaVerificada());
	}
	
	@Test
	void testUsuarioBasicoIntentaOpinarDosVeces() {
		when(usuarioBasico1.getIdentificacion()).thenReturn("usuario_basico_1");
		when(usuarioBasico1.esExperto()).thenReturn(false);
		when(opinion1.getCalificacion()).thenReturn(Calificacion.VINCHUCA_INFESTANS);
		when(opinion1.getUsuario()).thenReturn(usuarioBasico1);
		when(opinion1.esDeUsuarioExperto()).thenReturn(false);
		when(opinion1.getFecha()).thenReturn(hoy);
		
		when(opinion2.getCalificacion()).thenReturn(Calificacion.CHINCHE_FOLIADA);
		when(opinion2.getUsuario()).thenReturn(usuarioBasico1);
		when(opinion2.esDeUsuarioExperto()).thenReturn(false);
		when(opinion2.getFecha()).thenReturn(hoy);
		
		muestra = new Muestra(imagen1, buenosAires, opinion1);			
		muestra.agregarOpinion(opinion2);
		
		assertEquals(1, muestra.getOpiniones().size());
		assertEquals(Calificacion.INDEFINIDA, muestra.resultadoActual());
		assertFalse(muestra.estaVerificada());
	}
	
	@Test
	void testUsuarioExpertoIntentaOpinarDosVeces() {
		when(usuarioExperto1.getIdentificacion()).thenReturn("usuario_experto_1");
		when(usuarioExperto1.esExperto()).thenReturn(false);
		when(opinion1.getCalificacion()).thenReturn(Calificacion.VINCHUCA_INFESTANS);
		when(opinion1.getUsuario()).thenReturn(usuarioExperto1);
		when(opinion1.esDeUsuarioExperto()).thenReturn(false);
		when(opinion1.getFecha()).thenReturn(hoy);
		
		when(opinion2.getCalificacion()).thenReturn(Calificacion.CHINCHE_FOLIADA);
		when(opinion2.getUsuario()).thenReturn(usuarioExperto1);
		when(opinion2.esDeUsuarioExperto()).thenReturn(false);
		when(opinion2.getFecha()).thenReturn(hoy);
		
		muestra = new Muestra(imagen1, buenosAires, opinion1);		
		muestra.agregarOpinion(opinion2);
		
		assertEquals(1, muestra.getOpiniones().size());
		assertEquals(Calificacion.INDEFINIDA, muestra.resultadoActual());
		assertFalse(muestra.estaVerificada());
	}
	
	@Test
	void testMuestrasAMenosDeDistancia() {
		when(usuarioBasico1.getIdentificacion()).thenReturn("usuario_basico_1");
		when(usuarioBasico1.esExperto()).thenReturn(false);
		when(opinion1.getCalificacion()).thenReturn(Calificacion.VINCHUCA_INFESTANS);
		when(opinion1.getUsuario()).thenReturn(usuarioBasico1);
		when(opinion1.esDeUsuarioExperto()).thenReturn(false);
		when(opinion1.getFecha()).thenReturn(hoy);
		
		Muestra muestraBuenosAires = new Muestra(imagen1, buenosAires, opinion1);
		
		when(usuarioBasico2.getIdentificacion()).thenReturn("usuario_basico_2");
		when(usuarioBasico2.esExperto()).thenReturn(false);
		when(opinion2.getCalificacion()).thenReturn(Calificacion.VINCHUCA_INFESTANS);
		when(opinion2.getUsuario()).thenReturn(usuarioBasico2);
		when(opinion2.esDeUsuarioExperto()).thenReturn(false);
		when(opinion2.getFecha()).thenReturn(hoy);
		when(santaFe.distancia(buenosAires)).thenReturn(Double.valueOf(395.56));
		
		Muestra muestraSantaFe = new Muestra(imagen2, santaFe, opinion2);
		
		when(usuarioBasico3.getIdentificacion()).thenReturn("usuario_basico_3");
		when(usuarioBasico3.esExperto()).thenReturn(false);
		when(opinion3.getCalificacion()).thenReturn(Calificacion.VINCHUCA_INFESTANS);
		when(opinion3.getUsuario()).thenReturn(usuarioBasico3);
		when(opinion3.esDeUsuarioExperto()).thenReturn(false);
		when(opinion3.getFecha()).thenReturn(hoy);
		when(cordoba.distancia(buenosAires)).thenReturn(Double.valueOf(647.69));
		
		Muestra muestraCordoba = new Muestra(imagen3, cordoba, opinion3);
		
		when(usuarioBasico4.getIdentificacion()).thenReturn("usuario_basico_4");
		when(usuarioBasico4.esExperto()).thenReturn(false);
		when(opinion4.getCalificacion()).thenReturn(Calificacion.VINCHUCA_INFESTANS);
		when(opinion4.getUsuario()).thenReturn(usuarioBasico4);
		when(opinion4.esDeUsuarioExperto()).thenReturn(false);
		when(opinion4.getFecha()).thenReturn(hoy);
		when(tucuman.distancia(buenosAires)).thenReturn(Double.valueOf(1084.98));
		
		Muestra muestraTucuman = new Muestra(imagen4, tucuman, opinion4);
		
		when(usuarioBasico5.getIdentificacion()).thenReturn("usuario_basico_5");
		when(usuarioBasico5.esExperto()).thenReturn(false);
		when(opinion5.getCalificacion()).thenReturn(Calificacion.VINCHUCA_INFESTANS);
		when(opinion5.getUsuario()).thenReturn(usuarioBasico5);
		when(opinion5.esDeUsuarioExperto()).thenReturn(false);
		when(opinion5.getFecha()).thenReturn(hoy);
		when(salta.distancia(buenosAires)).thenReturn(Double.valueOf(1285.99));
		
		Muestra muestraSalta = new Muestra(imagen5, salta, opinion5);
		
		List<Muestra> muestras = new ArrayList<Muestra>();
		muestras.add(muestraSantaFe);
		muestras.add(muestraCordoba);
		muestras.add(muestraTucuman);
		muestras.add(muestraSalta);
		
		assertEquals(2, muestraBuenosAires.muestrasAMenosDe(Double.valueOf(700), muestras).size());
	}

}
