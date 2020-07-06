package unq.po2.tbd.vinchucas;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class MuestraTest {
	
	private @Mock Usuario usuario1;
	private @Mock Usuario usuario2;
	private @Mock Usuario usuario3;
	private @Mock Usuario usuario4;
	private @Mock Ubicacion buenosAires;
	private @Mock Ubicacion santaFe;
	private @Mock Ubicacion cordoba;
	private @Mock Ubicacion tucuman;
	private @Mock Opinion opinion1;
	private @Mock Opinion opinion2;
	private @Mock Opinion opinion3;
	private @Mock Opinion opinion4;
	private @Mock Imagen imagen1;
	private @Mock Imagen imagen2;
	private @Mock Imagen imagen3;
	private @Mock Imagen imagen4;
	private LocalDate hoy;
	private Muestra muestra;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.hoy = LocalDate.now();
	}
	
	private void configurarOpinionBasica(Usuario usuario, Opinion opinion, Calificacion calificacion) {
		this.configurarOpinion(usuario, opinion, calificacion, false);
	}
	
	private void configurarOpinionExperta(Usuario usuario, Opinion opinion, Calificacion calificacion) {
		this.configurarOpinion(usuario, opinion, calificacion, true);
	}
	
	private void configurarOpinion(Usuario usuario, Opinion opinion, Calificacion calificacion, Boolean esExperto) {
		when(usuario.esExperto()).thenReturn(esExperto);
		when(opinion.getUsuario()).thenReturn(usuario);
		when(opinion.esDeUsuarioExperto()).thenReturn(esExperto);
		when(opinion.getCalificacion()).thenReturn(calificacion);
	}
	
	@Test
	void testConstructor() {
		// exercise
		this.muestra = new Muestra(this.imagen1, this.buenosAires, this.opinion1);
		// verify
		assertNotNull(this.muestra);
	}
	
	@Test
	void testGetIdentificacionUsuario() {
		// set up
		when(this.usuario1.getIdentificacion()).thenReturn("usuario");
		when(this.opinion1.getUsuario()).thenReturn(this.usuario1);
		this.muestra = new Muestra(this.imagen1, this.buenosAires, this.opinion1);
		// exercise
		String resultado = this.muestra.getIdentificacionUsuario();
		// verify
		verify(this.opinion1, times(2)).getUsuario();
		verify(this.usuario1, times(1)).getIdentificacion();
		assertEquals("usuario", resultado);
	}
	
	@Test
	void testGetFecha() {
		// set up
		when(this.opinion1.getFecha()).thenReturn(this.hoy);
		this.muestra = new Muestra(this.imagen1, this.buenosAires, this.opinion1);
		// exercise
		LocalDate resultado = this.muestra.getFecha();
		// verify
		verify(this.opinion1, times(1)).getFecha();
		assertEquals(this.hoy, resultado);
	}
	
	@Test
	void testGetFoto() {
		// set up
		this.muestra = new Muestra(this.imagen1, this.buenosAires, this.opinion1);
		// exercise
		Imagen resultado = this.muestra.getFoto();
		// verify
		assertEquals(this.imagen1, resultado);
	}
	
	@Test
	void testGetEspecie() {
		// set up
		when(this.opinion1.getCalificacion()).thenReturn(Calificacion.VINCHUCA_INFESTANS);
		this.muestra = new Muestra(this.imagen1, this.buenosAires, this.opinion1);
		// exercise
		Calificacion resultado = this.muestra.getEspecie();
		// verify
		verify(this.opinion1, times(1)).getCalificacion();
		assertEquals(Calificacion.VINCHUCA_INFESTANS, resultado);
	}
	
	@Test
	void testGetUbicacion() {
		// set up
		this.muestra = new Muestra(this.imagen1, this.buenosAires, this.opinion1);
		// exercise
		Ubicacion resultado = this.muestra.getUbicacion();
		// verify
		assertEquals(this.buenosAires, resultado);
	}
	
	@Test
	void testGetOpiniones() {
		// set up
		this.muestra = new Muestra(this.imagen1, this.buenosAires, this.opinion1);
		// exercise
		List<Opinion> resultado = this.muestra.getOpiniones();
		// verify
		assertEquals(List.of(this.opinion1), resultado);
	}
	
	@Test
	void testAgregarOpinionNueva() {
		// set up
		when(this.opinion1.getUsuario()).thenReturn(this.usuario1);
		when(this.opinion2.getUsuario()).thenReturn(this.usuario2);
		this.muestra = new Muestra(this.imagen1, this.buenosAires, this.opinion1);
		// exercise
		this.muestra.agregarOpinion(this.opinion2);
		// verify
		assertEquals(List.of(this.opinion1, this.opinion2), this.muestra.getOpiniones());
	}
	
	@Test
	void testAgregarOpinionRepetida() {
		// set up
		when(this.opinion1.getUsuario()).thenReturn(this.usuario1);
		this.muestra = new Muestra(this.imagen1, this.buenosAires, this.opinion1);
		// exercise
		this.muestra.agregarOpinion(this.opinion1);
		// verify
		assertEquals(List.of(this.opinion1), this.muestra.getOpiniones());
	}
	
	@Test
	void testAgregarOpinionDeUsuarioBasicoMuestraVotacionExperto() {
		// set up
		this.configurarOpinionBasica(this.usuario1, this.opinion1, Calificacion.VINCHUCA_INFESTANS);
		this.configurarOpinionExperta(this.usuario2, this.opinion2, Calificacion.CHINCHE_FOLIADA);
		this.configurarOpinionBasica(this.usuario3, this.opinion3, Calificacion.CHINCHE_FOLIADA);		
		this.muestra = new Muestra(this.imagen1, this.buenosAires, this.opinion1);
		this.muestra.agregarOpinion(this.opinion2);
		// exercise
		this.muestra.agregarOpinion(this.opinion3);
		// verify
		assertEquals(List.of(this.opinion1, this.opinion2), this.muestra.getOpiniones());
	}
	
	@Test
	void testAgregarOpinionMuestraVerificada() {
		// set up
		this.configurarOpinionBasica(this.usuario1, this.opinion1, Calificacion.VINCHUCA_INFESTANS);
		this.configurarOpinionExperta(this.usuario2, this.opinion2, Calificacion.CHINCHE_FOLIADA);
		this.configurarOpinionExperta(this.usuario3, this.opinion3, Calificacion.CHINCHE_FOLIADA);
		this.configurarOpinionBasica(this.usuario4, this.opinion4, Calificacion.VINCHUCA_SORDIDA);	
		this.muestra = new Muestra(this.imagen1, this.buenosAires, this.opinion1);
		this.muestra.agregarOpinion(this.opinion2);
		this.muestra.agregarOpinion(this.opinion3);
		// exercise
		this.muestra.agregarOpinion(this.opinion4);
		// verify
		assertEquals(List.of(this.opinion1, this.opinion2, this.opinion3), this.muestra.getOpiniones());
	}
	
	// Agregar más casos
	@Test
	void testGetNivelVerificacionVotacionAbierta() {
		// set up
		this.muestra = new Muestra(this.imagen1, this.buenosAires, this.opinion1);
		// exercise
		NivelVerificacion resultado = this.muestra.getNivelVerificacion();
		// verify
		assertEquals(NivelVerificacion.VOTADA, resultado);
	}
	
	@Test
	void testGetNivelVerificacionVotacionExperto() {
		// set up
		this.configurarOpinionBasica(this.usuario1, this.opinion1, Calificacion.VINCHUCA_INFESTANS);
		this.configurarOpinionExperta(this.usuario2, this.opinion2, Calificacion.CHINCHE_FOLIADA);
		this.configurarOpinionBasica(this.usuario3, this.opinion3, Calificacion.CHINCHE_FOLIADA);		
		this.muestra = new Muestra(this.imagen1, this.buenosAires, this.opinion1);
		this.muestra.agregarOpinion(this.opinion2);
		this.muestra.agregarOpinion(this.opinion3);
		NivelVerificacion resultado = this.muestra.getNivelVerificacion();
		// verify
		assertEquals(NivelVerificacion.VOTADA, resultado);
	}
	
	@Test
	void testGetNivelVerificacionVerificada() {
		// set up
		this.configurarOpinionBasica(this.usuario1, this.opinion1, Calificacion.VINCHUCA_INFESTANS);
		this.configurarOpinionExperta(this.usuario2, this.opinion2, Calificacion.CHINCHE_FOLIADA);
		this.configurarOpinionExperta(this.usuario3, this.opinion3, Calificacion.CHINCHE_FOLIADA);
		this.muestra = new Muestra(this.imagen1, this.buenosAires, this.opinion1);
		this.muestra.agregarOpinion(this.opinion2);
		this.muestra.agregarOpinion(this.opinion3);
		// exercise
		NivelVerificacion resultado = this.muestra.getNivelVerificacion();
		// verify
		assertEquals(NivelVerificacion.VERIFICADA, resultado);
	}
	
	// Agregar más casos
	@Test
	void testMuestraVotacionAbiertaResultadoActualIndefinida() {
		// set up
		this.muestra = new Muestra(this.imagen1, this.buenosAires, this.opinion1);
		// exercise
		Calificacion resultado = this.muestra.resultadoActual();
		// verify
		assertEquals(Calificacion.INDEFINIDA, resultado);
	}
	
	@Test
	void testMuestraVotacionAbiertaResultadoActual() {
		// set up
		this.configurarOpinionBasica(this.usuario1, this.opinion1, Calificacion.VINCHUCA_INFESTANS);
		this.configurarOpinionBasica(this.usuario2, this.opinion2, Calificacion.VINCHUCA_INFESTANS);		
		this.muestra = new Muestra(this.imagen1, this.buenosAires, this.opinion1);
		this.muestra.agregarOpinion(this.opinion2);
		// exercise
		Calificacion resultado = this.muestra.resultadoActual();
		// verify
		assertEquals(Calificacion.VINCHUCA_INFESTANS, resultado);
	}
	
	@Test
	void testMuestraVotacionExpertoResultadoActualIndefinida() {
		// set up
		this.configurarOpinionBasica(this.usuario1, this.opinion1, Calificacion.VINCHUCA_INFESTANS);
		this.configurarOpinionExperta(this.usuario2, this.opinion2, Calificacion.CHINCHE_FOLIADA);
		this.configurarOpinionExperta(this.usuario3, this.opinion3, Calificacion.VINCHUCA_INFESTANS);
		this.muestra = new Muestra(this.imagen1, this.buenosAires, this.opinion1);
		this.muestra.agregarOpinion(this.opinion2);
		this.muestra.agregarOpinion(this.opinion3);
		// exercise
		Calificacion resultado = this.muestra.resultadoActual();
		// verify
		assertEquals(Calificacion.INDEFINIDA, resultado);
	}
	
	@Test
	void testMuestraVerificadaResultadoActual() {
		// set up
		this.configurarOpinionBasica(this.usuario1, this.opinion1, Calificacion.VINCHUCA_INFESTANS);
		this.configurarOpinionExperta(this.usuario2, this.opinion2, Calificacion.CHINCHE_FOLIADA);
		this.configurarOpinionExperta(this.usuario3, this.opinion3, Calificacion.CHINCHE_FOLIADA);
		this.muestra = new Muestra(this.imagen1, this.buenosAires, this.opinion1);
		this.muestra.agregarOpinion(this.opinion2);
		this.muestra.agregarOpinion(this.opinion3);
		// exercise
		Calificacion resultado = this.muestra.resultadoActual();
		// verify
		assertEquals(Calificacion.CHINCHE_FOLIADA, resultado);
	}
	
	@Test
	void testOpinoPreviamenteVerdadero() {
		// set up
		when(this.opinion1.getUsuario()).thenReturn(this.usuario1);
		this.muestra = new Muestra(this.imagen1, this.buenosAires, this.opinion1);
		// exercise
		Boolean resultado = this.muestra.opinoPreviamente(this.usuario1);
		// verify
		assertTrue(resultado);
	}
	
	@Test
	void testOpinoPreviamenteFalso() {
		// set up
		when(this.opinion1.getUsuario()).thenReturn(this.usuario1);
		this.muestra = new Muestra(this.imagen1, this.buenosAires, this.opinion1);
		// exercise
		Boolean resultado = this.muestra.opinoPreviamente(this.usuario2);
		// verify
		assertFalse(resultado);
	}
	
	@Test
	void testGetEstadoVotacionAbierta() {
		// set up
		this.muestra = new Muestra(this.imagen1, this.buenosAires, this.opinion1);
		// exercise
		MuestraEstado resultado = this.muestra.getEstado();
		// verify
		assertEquals(MuestraVotacionAbierta.class, resultado.getClass());
	}
	
	@Test
	void testGetEstadoVotacionExperto() {
		// set up		
		this.configurarOpinionBasica(this.usuario1, this.opinion1, Calificacion.VINCHUCA_INFESTANS);
		this.configurarOpinionExperta(this.usuario2, this.opinion2, Calificacion.CHINCHE_FOLIADA);
		this.muestra = new Muestra(this.imagen1, this.buenosAires, this.opinion1);
		this.muestra.agregarOpinion(this.opinion2);
		// exercise
		MuestraEstado resultado = this.muestra.getEstado();
		// verify
		assertEquals(MuestraVotacionExperto.class, resultado.getClass());
	}
	
	@Test
	void testGetEstadoVerificada() {
		// set up
		this.configurarOpinionBasica(this.usuario1, this.opinion1, Calificacion.VINCHUCA_INFESTANS);
		this.configurarOpinionExperta(this.usuario2, this.opinion2, Calificacion.CHINCHE_FOLIADA);
		this.configurarOpinionExperta(this.usuario3, this.opinion3, Calificacion.CHINCHE_FOLIADA);
		this.muestra = new Muestra(this.imagen1, this.buenosAires, this.opinion1);
		this.muestra.agregarOpinion(this.opinion2);
		this.muestra.agregarOpinion(this.opinion3);
		// exercise
		MuestraEstado resultado = this.muestra.getEstado();
		// verify
		assertEquals(MuestraVerificada.class, resultado.getClass());
		assertEquals(Calificacion.CHINCHE_FOLIADA, this.muestra.resultadoActual());
	}
	
	@Test
	void testMuestrasAMenosDeDistancia() {		
		// set up
		when(santaFe.distancia(buenosAires)).thenReturn(Double.valueOf(395.56));
		Muestra muestraSantaFe = new Muestra(imagen2, santaFe, opinion2);
		
		when(cordoba.distancia(buenosAires)).thenReturn(Double.valueOf(647.69));
		Muestra muestraCordoba = new Muestra(imagen3, cordoba, opinion3);
		
		when(tucuman.distancia(buenosAires)).thenReturn(Double.valueOf(1084.98));
		Muestra muestraTucuman = new Muestra(imagen4, tucuman, opinion4);
		
		List<Muestra> muestras = List.of(muestraSantaFe, muestraCordoba, muestraTucuman);
		this.muestra = new Muestra(imagen1, buenosAires, opinion1);
		
		// exercise
		List<Muestra> muestrasAMenosDe700Km = this.muestra.muestrasAMenosDe(Double.valueOf(700), muestras);
		
		// verify
		assertEquals(List.of(muestraSantaFe, muestraCordoba), muestrasAMenosDe700Km);
	}

}
