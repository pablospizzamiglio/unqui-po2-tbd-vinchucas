package unq.po2.tbd.vinchucas;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestOrganizacion {
	
	private Ubicacion ubicacion;
	private FuncionalidadExterna funcionalidadNuevaMuestra;
	private FuncionalidadExterna funcionalidadNuevaOpinion;
	private Organizacion organizacion;
	private Muestra muestra;
	private Zona zona;

	@BeforeEach
	void setUp() throws Exception {
		this.ubicacion = mock(Ubicacion.class);
		this.funcionalidadNuevaMuestra = mock(FuncionalidadExterna.class);
		this.funcionalidadNuevaOpinion = mock(FuncionalidadExterna.class);
		this.organizacion = new Organizacion(
				this.ubicacion, 
				TipoOrganizacion.SALUD, 
				30, 
				this.funcionalidadNuevaMuestra, 
				this.funcionalidadNuevaOpinion
		);
		this.muestra = mock(Muestra.class);
		this.zona = mock(Zona.class);
	}

	@Test
	void testGettersSetters() {
		assertEquals(this.ubicacion, this.organizacion.getUbicacion());
		assertEquals(TipoOrganizacion.SALUD, this.organizacion.getTipo());
		assertEquals(30, this.organizacion.getNumeroEmpleados());
		assertEquals(this.funcionalidadNuevaMuestra, this.organizacion.getFuncionalidadNuevaMuestra());
		assertEquals(this.funcionalidadNuevaOpinion, this.organizacion.getFuncionalidadNuevaOpinion());
	}
	
	@Test
	void testNuevaMuestra() {
		this.organizacion.nuevaMuestra(this.zona, this.muestra);
		
		verify(this.funcionalidadNuevaMuestra, times(1)).nuevoEvento(this.organizacion, this.zona, this.muestra);
	}
	
	@Test
	void testNuevaOpinion() {
		this.organizacion.nuevaOpinion(this.zona, this.muestra);
		
		verify(this.funcionalidadNuevaOpinion, times(1)).nuevoEvento(this.organizacion, this.zona, this.muestra);
	}

}
