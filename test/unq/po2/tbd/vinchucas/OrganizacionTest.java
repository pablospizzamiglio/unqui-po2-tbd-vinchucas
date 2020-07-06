package unq.po2.tbd.vinchucas;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


class OrganizacionTest {

	private @Mock Ubicacion ubicacion;
	private @Mock Organizacion organizacion;
	private @Mock Muestra muestra;
	private @Mock FuncionalidadExterna funcionalidadNuevaMuestra;
	private @Mock FuncionalidadExterna funcionalidadNuevaOpinion;
	private Zona zona;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.organizacion = new Organizacion(
				this.ubicacion, 
				TipoOrganizacion.SALUD, 
				30, 
				this.funcionalidadNuevaMuestra, 
				this.funcionalidadNuevaOpinion
		);
	}
	
	@Test
	void testGetUbicacion() {
		// verificación
		assertEquals(this.ubicacion, this.organizacion.getUbicacion());
	}
	
	@Test
	void testGetTipo() {
		// verificación
		assertEquals(TipoOrganizacion.SALUD, this.organizacion.getTipo());
	}
	
	@Test
	void testGetNumeroEmpleados() {
		// verificación
		assertEquals(30, this.organizacion.getNumeroEmpleados());
	}
	
	@Test
	void testGetFuncionalidadNuevaMuestra() {
		// verificación
		assertEquals(this.funcionalidadNuevaMuestra, this.organizacion.getFuncionalidadNuevaMuestra());
	}
	
	@Test
	void testGetFuncionalidadNuevaOpinion() {
		// verificación
		assertEquals(this.funcionalidadNuevaOpinion, this.organizacion.getFuncionalidadNuevaOpinion());
	}
	
	@Test
	void testNuevaMuestra() {
		// SUT
		this.organizacion.nuevaMuestra(this.zona, this.muestra);
		// verificación
		verify(this.funcionalidadNuevaMuestra, times(1)).nuevoEvento(this.organizacion, this.zona, this.muestra);
	}
	
	@Test
	void testNuevaOpinion() {
		// SUT
		this.organizacion.nuevaOpinion(this.zona, this.muestra);
		// verificación
		verify(this.funcionalidadNuevaOpinion, times(1)).nuevoEvento(this.organizacion, this.zona, this.muestra);
	}

}
