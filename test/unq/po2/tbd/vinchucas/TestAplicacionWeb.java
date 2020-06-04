package unq.po2.tbd.vinchucas;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestAplicacionWeb {
	
	Usuario usuarioAMBA;
	Usuario usuarioCABA;
	Zona amba;
	Zona caba;
	Muestra muestraAMBA;
	Muestra muestraCABA;
	Opinion opinionAMBA;
	Buscador buscador;
	AplicacionWeb aplicacionWeb;

	@BeforeEach
	void setUp() throws Exception {
		this.usuarioAMBA = mock(Usuario.class);
		this.usuarioCABA = mock(Usuario.class);
		this.amba = mock(Zona.class);
		this.caba = mock(Zona.class);
		this.muestraAMBA = mock(Muestra.class);
		this.muestraCABA = mock(Muestra.class);
		this.opinionAMBA = mock(Opinion.class);
		this.buscador = mock(Buscador.class);
		this.aplicacionWeb = new AplicacionWeb(buscador);
	}

	@Test
	void testCreacion() {
		assertEquals(this.buscador, this.aplicacionWeb.getBuscador());
		assertTrue(this.aplicacionWeb.getUsuarios().isEmpty());
		assertTrue(this.aplicacionWeb.getZonas().isEmpty());
		assertTrue(this.aplicacionWeb.getMuestras().isEmpty());
	}
	
	@Test
	void testRegistrarUsuario() {
		this.aplicacionWeb.registrarUsuario(usuarioAMBA);
		assertEquals(1, this.aplicacionWeb.getUsuarios().size());
	}
	
	@Test
	void testRegistrarZona() {
		this.aplicacionWeb.registrarZona(amba);
		assertEquals(1, this.aplicacionWeb.getZonas().size());
	}
	
	@Test
	void testRegistrarMuestraSinZonas() {
		this.aplicacionWeb.registrarMuestra(muestraAMBA);
		assertEquals(1, this.aplicacionWeb.getMuestras().size());
	}
	
	@Test
	void testRegistrarMuestraConZonas() {
		this.aplicacionWeb.registrarZona(amba);
		this.aplicacionWeb.registrarZona(caba);
		
		when(amba.estaDentro(muestraAMBA)).thenReturn(true);
		when(caba.estaDentro(muestraAMBA)).thenReturn(false);
		
		this.aplicacionWeb.registrarMuestra(muestraAMBA);
		assertEquals(1, this.aplicacionWeb.getMuestras().size());
		
		verify(amba, times(1)).agregarMuestra(muestraAMBA);
		verify(caba, times(0)).agregarMuestra(muestraAMBA);
	}
	
	@Test
	void testRegistrarOpinionMuestraSinZonas() {
		this.aplicacionWeb.registrarOpinionSobreMuestra(muestraAMBA, opinionAMBA);
		verify(muestraAMBA, times(1)).agregarOpinion(opinionAMBA);
	}
	
	@Test
	void testRegistrarOpinionEnMuestraConZonas() {
		this.aplicacionWeb.registrarZona(amba);
		this.aplicacionWeb.registrarZona(caba);
		
		when(amba.estaDentro(muestraAMBA)).thenReturn(true);
		when(caba.estaDentro(muestraAMBA)).thenReturn(false);
		
		this.aplicacionWeb.registrarOpinionSobreMuestra(muestraAMBA, opinionAMBA);
		
		verify(amba, times(1)).nuevaOpinionRegistradaEn(muestraAMBA);
		verify(caba, times(0)).nuevaOpinionRegistradaEn(muestraAMBA);
	}

}