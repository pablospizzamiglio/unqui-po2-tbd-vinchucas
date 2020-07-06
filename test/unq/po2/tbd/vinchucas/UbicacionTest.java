package unq.po2.tbd.vinchucas;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class UbicacionTest {
	
	
	Ubicacion londres;
	Ubicacion neuquen;
	Ubicacion laPampa;
	Ubicacion buenosAires;
	Ubicacion santaFe;
	Ubicacion cordoba;
	Ubicacion tucuman;
	Ubicacion salta;
	List<Ubicacion> ubicaciones;

	@BeforeEach
	void setUp() throws Exception {
		this.londres = new Ubicacion(Double.valueOf(51.500153), Double.valueOf(-0.126236));
		this.neuquen = new Ubicacion(Double.valueOf(-38.951610), Double.valueOf(-68.059100));
		this.laPampa = new Ubicacion(Double.valueOf(-37.000000), Double.valueOf(-66.000000));
		this.buenosAires = new Ubicacion(Double.valueOf(-34.608418), Double.valueOf(-58.373161));
		this.santaFe = new Ubicacion(Double.valueOf(-31.633330), Double.valueOf(-60.700000));
		this.cordoba = new Ubicacion(Double.valueOf(-31.413500), Double.valueOf(-64.181050));
		this.tucuman = new Ubicacion(Double.valueOf(-26.824140), Double.valueOf(-65.222600));
		this.salta = new Ubicacion(Double.valueOf(-24.785900), Double.valueOf(-65.411660));
		
		this.ubicaciones = List.of(
			laPampa,
			neuquen,
			santaFe,
			cordoba,
			tucuman,
			salta
		);
	}

	@Test
	void testGetLatitud() {	
		// verificación
		assertEquals(Double.valueOf(-34.608418), this.buenosAires.getLatitud());
	}
	
	@Test
	void testGetLongitud() {
		// verificación
		assertEquals(Double.valueOf(-58.373161), this.buenosAires.getLongitud());
	}
	
	@Test
	void testDistancia() {
		// verificación
		assertEquals(Double.valueOf(11127.332978507113), this.buenosAires.distancia(this.londres));
	}
	
	@Test
	void testUbicacionesAMenosDe20kmInexistentes() {
		// SUT
		List<Ubicacion> resultado = this.buenosAires.ubicacionesAMenosDe(Double.valueOf(20), this.ubicaciones);
		// verificación
		assertTrue(resultado.isEmpty());
	}
	
	@Test
	void testUbicacionesAMenosDe700km() {
		// SUT
		List<Ubicacion> resultado = this.buenosAires.ubicacionesAMenosDe(Double.valueOf(700), this.ubicaciones);
		// verificación
		assertEquals(2, resultado.size());
	}

}
