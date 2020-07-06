package unq.po2.tbd.vinchucas;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


class AplicacionWebTest {
	
	private @Mock Usuario usuarioAMBA;
	private @Mock Usuario usuarioCABA;
	private @Mock Zona amba;
	private @Mock Zona caba;
	private @Mock Muestra muestraAMBA;
	private @Mock Muestra muestraCABA;
	private @Mock Opinion opinionAMBA;
	private @Mock Buscador buscador;
	private AplicacionWeb aplicacionWeb;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.aplicacionWeb = new AplicacionWeb(buscador);
	}

	@Test
	void testConstructor() {
		// verificaci�n
		assertNotNull(this.aplicacionWeb);
	}
	
	@Test
	void testGetBuscador() {
		// verificaci�n
		assertEquals(this.buscador, this.aplicacionWeb.getBuscador());
	}
	
	@Test
	void testGetUsuarios() {
		// verificaci�n
		assertTrue(this.aplicacionWeb.getUsuarios().isEmpty());
	}
	
	@Test
	void testGetZonas() {
		// verificaci�n
		assertTrue(this.aplicacionWeb.getZonas().isEmpty());
	}
	
	@Test
	void testGetMuestras() {
		// verificaci�n
		assertTrue(this.aplicacionWeb.getMuestras().isEmpty());
	}
	
	@Test
	void testRegistrarUsuario() {
		// SUT
		this.aplicacionWeb.registrarUsuario(usuarioAMBA);
		// verificaci�n
		assertEquals(1, this.aplicacionWeb.getUsuarios().size());
	}
	
	@Test
	void testRegistrarZona() {
		// SUT
		this.aplicacionWeb.registrarZona(amba);
		// verificaci�n
		assertEquals(1, this.aplicacionWeb.getZonas().size());
	}
	
	@Test
	void testRegistrarMuestraSinZonas() {
		// SUT
		this.aplicacionWeb.registrarMuestra(muestraAMBA);
		// verificaci�n
		assertEquals(1, this.aplicacionWeb.getMuestras().size());
	}
	
	@Test
	void testRegistrarMuestraConZonas() {
		// set up
		this.aplicacionWeb.registrarZona(amba);
		this.aplicacionWeb.registrarZona(caba);		
		when(amba.estaDentro(muestraAMBA)).thenReturn(true);
		when(caba.estaDentro(muestraAMBA)).thenReturn(false);
		// SUT
		this.aplicacionWeb.registrarMuestra(muestraAMBA);
		// verificaci�n
		assertEquals(1, this.aplicacionWeb.getMuestras().size());
		verify(amba, times(1)).agregarMuestra(muestraAMBA);
		verify(caba, times(1)).agregarMuestra(muestraAMBA);
	}
	
	@Test
	void testRegistrarOpinionMuestraSinZonas() {
		// SUT
		this.aplicacionWeb.registrarOpinionSobreMuestra(muestraAMBA, opinionAMBA);
		// verificaci�n
		verify(muestraAMBA, times(1)).agregarOpinion(opinionAMBA);
	}
	
	@Test
	void testRegistrarOpinionEnMuestraConZonas() {
		// set up
		this.aplicacionWeb.registrarZona(amba);
		this.aplicacionWeb.registrarZona(caba);
		when(amba.estaDentro(muestraAMBA)).thenReturn(true);
		when(caba.estaDentro(muestraAMBA)).thenReturn(false);
		// SUT
		this.aplicacionWeb.registrarOpinionSobreMuestra(muestraAMBA, opinionAMBA);
		// verificaci�n
		verify(amba, times(1)).nuevaOpinionRegistradaEn(muestraAMBA);
		verify(caba, times(0)).nuevaOpinionRegistradaEn(muestraAMBA);
	}

}