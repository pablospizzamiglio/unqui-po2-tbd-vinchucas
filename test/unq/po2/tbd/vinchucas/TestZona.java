package unq.po2.tbd.vinchucas;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestZona {
	
	private Muestra muestra;
	private Ubicacion epicentro;
	private Ubicacion epicentroA;
	private Ubicacion epicentroB;
	private Suscriptor suscriptorA;
	private Suscriptor suscriptorB;
	private String nombre;
	private Double distancia;
	private Zona zona;

	@BeforeEach
	void setUp() throws Exception {
		this.muestra = mock(Muestra.class);
		this.epicentro = mock(Ubicacion.class);
		this.epicentroA = mock(Ubicacion.class);
		this.epicentroB = mock(Ubicacion.class);
		this.suscriptorA = mock(Suscriptor.class);
		this.suscriptorB = mock(Suscriptor.class);
		this.nombre = "AMBA";
		this.distancia = Double.valueOf(50);
		this.zona = new Zona(this.nombre, this.epicentro, this.distancia);
	}

	@Test
	void testGetterSetter() {			
		assertEquals(this.nombre, zona.getNombre());
		assertEquals(this.distancia, zona.getDistancia());
		assertEquals(this.epicentro, zona.getEpicentro());
		assertTrue(zona.getMuestras().isEmpty());
		assertTrue(zona.getSuscriptores().isEmpty());
	}
	
	@Test
	void testAgregarMuestra() {	
		zona.agregarMuestra(this.muestra);
		
		assertFalse(zona.getMuestras().isEmpty());
		assertEquals(1, zona.getMuestras().size());
	}
	
	@Test
	void testSuscribirDesuscribir() {		
		zona.suscribir(this.suscriptorA);
		zona.suscribir(this.suscriptorB);
		
		assertFalse(zona.getSuscriptores().isEmpty());
		assertEquals(2, zona.getSuscriptores().size());
		
		zona.desuscribir(this.suscriptorA);
		
		assertFalse(zona.getSuscriptores().isEmpty());
		assertEquals(1, zona.getSuscriptores().size());
		
		zona.desuscribir(this.suscriptorB);
		
		assertTrue(zona.getSuscriptores().isEmpty());
	}
	
	@Test
	void testNotificarSuscriptoresNuevaMuestra() {		
		zona.suscribir(this.suscriptorA);
		zona.suscribir(this.suscriptorB);
		
		zona.agregarMuestra(this.muestra);
		
		verify(this.suscriptorA, times(1)).nuevaMuestra(zona, this.muestra);
		verify(this.suscriptorB, times(1)).nuevaMuestra(zona, this.muestra);
	}
	
	@Test
	void testNotificarSuscriptoresNuevaOpinion() {		
		zona.suscribir(this.suscriptorA);
		zona.suscribir(this.suscriptorB);
		
		zona.nuevaOpinionRegistradaEn(this.muestra);
		
		verify(this.suscriptorA, times(1)).nuevaOpinion(zona, this.muestra);
		verify(this.suscriptorB, times(1)).nuevaOpinion(zona, this.muestra);
	}
	
	@Test
	void testEstaDentro() {
		when(this.muestra.getUbicacion()).thenReturn(this.epicentroA);
		when(this.epicentro.distancia(this.epicentroA)).thenReturn(Double.valueOf(20));
		
		assertTrue(zona.estaDentro(this.muestra));
		
		verify(this.muestra, times(1)).getUbicacion();
		verify(this.epicentro, times(1)).distancia(this.epicentroA);
	}
	
	@Test
	void testEstaFuera() {
		when(this.muestra.getUbicacion()).thenReturn(this.epicentroA);
		when(this.epicentro.distancia(this.epicentroA)).thenReturn(Double.valueOf(60));
		
		assertFalse(zona.estaDentro(this.muestra));
		
		verify(this.muestra, times(1)).getUbicacion();
		verify(this.epicentro, times(1)).distancia(this.epicentroA);
	}
	
	@Test
	void testZonasSolapadas() {
		Zona zonaA = new Zona("ZonaA", this.epicentroA, Double.valueOf(100));
		Zona zonaB = new Zona("ZonaB", this.epicentroB, Double.valueOf(75));
		List<Zona> zonas = new ArrayList<Zona>();
		zonas.add(zonaA);
		zonas.add(zonaB);
		
		when(this.epicentroA.distancia(this.epicentro)).thenReturn(Double.valueOf(60));
		when(this.epicentroB.distancia(this.epicentro)).thenReturn(Double.valueOf(500));
		
		List<Zona> solapadas = zona.zonasSolapadas(zonas);
		
		assertFalse(solapadas.isEmpty());
		assertEquals(1, solapadas.size());
	}

}
