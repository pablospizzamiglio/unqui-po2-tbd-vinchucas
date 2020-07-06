package unq.po2.tbd.vinchucas;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


class ZonaTest {
	
	private @Mock Muestra muestra;
	private @Mock Ubicacion epicentro;
	private @Mock Ubicacion epicentroA;
	private @Mock Ubicacion epicentroB;
	private @Mock Suscriptor suscriptorA;
	private @Mock Suscriptor suscriptorB;
	private String nombre;
	private Double distancia;
	private Zona zona;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.nombre = "AMBA";
		this.distancia = Double.valueOf(50);
		this.zona = new Zona(this.nombre, this.epicentro, this.distancia);
	}
	
	@Test
	void testConstructor() {
		// verificación
		assertNotNull(this.zona);
	}
	
	@Test
	void testGetNombre() {
		// verficación
		assertEquals(this.nombre, zona.getNombre());
	}
	
	@Test
	void testGetDistancia() {
		// verificación
		assertEquals(this.distancia, zona.getDistancia());
	}
	
	@Test
	void testGetEpicentro() {
		// verificación
		assertEquals(this.epicentro, zona.getEpicentro());
	}
	
	@Test
	void testGetMuestras() {
		// verificación
		assertTrue(zona.getMuestras().isEmpty());
	}

	@Test
	void testGetSuscriptores() {
		// verificación
		assertTrue(zona.getSuscriptores().isEmpty());
	}
	
	@Test
	void testAgregarMuestra() {	
		// SUT
		zona.agregarMuestra(this.muestra);
		// verificación
		assertEquals(1, zona.getMuestras().size());
	}
	
	@Test
	void testSuscribir() {
		// SUT
		zona.suscribir(this.suscriptorA);
		// verificación
		assertEquals(1, zona.getSuscriptores().size());
	}
	
	@Test
	void testDesuscribir() {
		// set up
		zona.suscribir(this.suscriptorA);
		// SUT
		zona.desuscribir(this.suscriptorA);
		// verificación
		assertEquals(0, zona.getSuscriptores().size());
	}
	
	@Test
	void testNotificarSuscriptoresNuevaMuestra() {	
		// set up
		zona.suscribir(this.suscriptorA);
		zona.suscribir(this.suscriptorB);
		// SUT
		zona.agregarMuestra(this.muestra);
		// verificación
		verify(this.suscriptorA, times(1)).nuevaMuestra(zona, this.muestra);
		verify(this.suscriptorB, times(1)).nuevaMuestra(zona, this.muestra);
	}
	
	@Test
	void testNotificarSuscriptoresNuevaOpinion() {
		// set up
		zona.suscribir(this.suscriptorA);
		zona.suscribir(this.suscriptorB);
		// SUT
		zona.nuevaOpinionRegistradaEn(this.muestra);
		// verificación
		verify(this.suscriptorA, times(1)).nuevaOpinion(zona, this.muestra);
		verify(this.suscriptorB, times(1)).nuevaOpinion(zona, this.muestra);
	}
	
	@Test
	void testEstaDentro() {
		// set up
		when(this.muestra.getUbicacion()).thenReturn(this.epicentroA);
		when(this.epicentro.distancia(this.epicentroA)).thenReturn(Double.valueOf(20));
		// SUT
		Boolean resultado = zona.estaDentro(this.muestra);
		// verificación
		assertTrue(resultado);
	}
	
	@Test
	void testEstaFuera() {
		// set up
		when(this.muestra.getUbicacion()).thenReturn(this.epicentroA);
		when(this.epicentro.distancia(this.epicentroA)).thenReturn(Double.valueOf(60));
		// SUT
		Boolean resultado = zona.estaDentro(this.muestra);
		// verificación
		assertFalse(resultado);
	}
	
	@Test
	void testZonasSolapadas() {
		// set up
		Zona zonaA = new Zona("ZonaA", this.epicentroA, Double.valueOf(100));
		Zona zonaB = new Zona("ZonaB", this.epicentroB, Double.valueOf(75));
		List<Zona> zonas = new ArrayList<Zona>();
		zonas.add(zonaA);
		zonas.add(zonaB);
		when(this.epicentroA.distancia(this.epicentro)).thenReturn(Double.valueOf(60));
		when(this.epicentroB.distancia(this.epicentro)).thenReturn(Double.valueOf(500));
		// SUT
		List<Zona> resultado = zona.zonasSolapadas(zonas);
		// verificación
		assertEquals(1, resultado.size());
	}

}
