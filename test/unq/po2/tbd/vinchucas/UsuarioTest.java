package unq.po2.tbd.vinchucas;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UsuarioTest {

	private Usuario usuario;
	private AplicacionWeb aplicacionWeb;
	private Buscador buscador;
	private List<Opinion> opiniones;
	private List<Muestra> muestras;
	
	@BeforeEach
	void setUp() throws Exception {
		this.buscador = mock(Buscador.class);
		this.aplicacionWeb = new AplicacionWeb(this.buscador);
		this.usuario = new Usuario(this.aplicacionWeb, "usuario01");
		this.opiniones = this.generarOpinionesParaUnMismoUsuario(25, this.usuario);
		this.muestras = this.generarMuestrasConLaMismaOpinion(15, this.usuario);
	}
	
	List<Opinion> generarOpinionesParaUnMismoUsuario(Integer n, Usuario usuario) {
		List<Opinion> opiniones = new ArrayList<Opinion>();
		for (int i = 0; i <= n; i++) {
			opiniones.add(new Opinion(Calificacion.VINCHUCA_SORDIDA, LocalDate.of(20, 12, 25), usuario));
		}
		
		return opiniones;
	}
	
	List<Muestra> generarMuestrasConLaMismaOpinion(Integer n, Usuario usuario) {
		Imagen foto = new Foto("VinchucaFea");
		Ubicacion ubicacion = new Ubicacion(10.00, 50.00);
		Opinion opinion = new Opinion(Calificacion.VINCHUCA_SORDIDA, LocalDate.of(20, 12, 5), usuario);
		
		List<Muestra> muestras = new ArrayList<Muestra>();
		for (int i = 0; i <= n; i++) {
			muestras.add(new Muestra(foto, ubicacion, opinion));
		}
		
		return muestras;
	}

	@Test
	void testConstructorUsuario() {
		// verify
		assertNotNull(this.usuario);	
	}
	
	@Test
	void testGetAplicacion() {
		// verify
		assertEquals(this.usuario.getAplicacion(), this.aplicacionWeb);
	}
	
	@Test
	void testGetIdentificacion() {
		// verify
		assertEquals(this.usuario.getIdentificacion(), "usuario01");
	}
	
	@Test
	void testGetMuestras() {
		// verify
		assertTrue(this.usuario.getMuestras().isEmpty());
	}
	
	@Test
	void testGetOpiniones() {
		// verify
		assertTrue(this.usuario.getOpiniones().isEmpty());
	}
	
	@Test
	void testGetEsExperto() {
		// verify
		assertFalse(this.usuario.esExperto());
	}

	@Test
	void testNumeroDeMuestraUltimosTreintaDiasDelUsuarioEsIgualACero() {
		// verify
		assertEquals(this.usuario.numeroMuestrasUltimos30Dias(), 0);
	}
	
	@Test
	void testNumeroDeMuestraUltimosTreintaDiasDelUsuarioEsIgualAUno() {
		// setUp
		Usuario usuario = new Usuario(this.aplicacionWeb, "usuario02");
		Foto foto = new Foto("vinchucaFea");
		Ubicacion ubicacion = new Ubicacion(10.00, 30.00);
		Opinion opinion = new Opinion(Calificacion.VINCHUCA_SORDIDA, LocalDate.of(20, 12, 5), usuario);
		Muestra muestra = new Muestra(foto, ubicacion, opinion);
		// exercise
		usuario.getMuestras().add(muestra);
		// verify
		assertEquals(usuario.numeroMuestrasUltimos30Dias(), 1);
	}
	
	
	@Test
	void testNumeroDeOpinionesDelUsuarioLosUltimosTreintaDiasEsIgualACero() {
		// verify
		assertEquals(this.usuario.numeroOpinionesUltimos30Dias(), 0);
	}
	
	@Test
	void testNumeroDeOpinionesDelUsuarioLosUltimosTreintaDiasEsIgualAUno() {
		// setUp
		Usuario usuario = new Usuario(this.aplicacionWeb, "usuario02");
		Opinion opinion = new Opinion(Calificacion.VINCHUCA_SORDIDA, LocalDate.of(20, 12, 5), usuario);
		// exercise
		usuario.getOpiniones().add(opinion);
		// verify
		assertEquals(usuario.numeroOpinionesUltimos30Dias(), 1);
	}
	
	@Test
	void testUsuarioNoComienzaConNivelExperto() {
		// verify
		assertFalse(this.usuario.esExperto());
	}
	
	@Test
	void TestUsuarioNoPuedeCambiarDeNivel() {
		// verify
		assertFalse(this.usuario.puedeCambiarNivel());
	}
	
	@Test
	void TestElUsuarioConNivelInicialNoCambiaDeNivel() {
		// exercise
		this.usuario.recalcularNivel();
		// verify
		assertFalse(this.usuario.esExperto());
	}
	
	@Test
	void testElUsuarioConNivelInicialCambiaDeNivel() {
		// setUp
		this.usuario.getMuestras().addAll(this.muestras);
		this.usuario.getOpiniones().addAll(this.opiniones);
		// exercise
		this.usuario.recalcularNivel();
		// verify
		assertTrue(this.usuario.esExperto());
	}
	
	@Test
	void testUsuarioConNivelExpertoNoCambiaANivelBasico() {
		// setUp
		this.usuario.getMuestras().addAll(this.muestras);
		this.usuario.getOpiniones().addAll(this.opiniones);
		this.usuario.recalcularNivel();
		// EN ESTE PUNTO USUARIO ES EXPERTO Y NO DEBE CAMBIAR SI CUMPLE LO MINIMO PARA SER EXPERTO.
		// exercise
		this.usuario.recalcularNivel();
		// verify
		assertTrue(this.usuario.esExperto());
	}
	
	@Test
	void testUsuarioConNivelExpertoCambiaANivelBasico(){
		// setUp
		this.usuario.getMuestras().addAll(this.muestras);
		this.usuario.getOpiniones().addAll(this.opiniones);
		this.usuario.recalcularNivel();
		// EN ESTE PUNTO USUARIO ES EXPERTO.
		this.usuario.getOpiniones().removeAll(this.opiniones);
		// exercise
		this.usuario.recalcularNivel();
		// verify
		assertFalse(this.usuario.esExperto());
	}

	@Test
	void testUsuarioEsMarcadoComoEspecialista() {
		// setUp
		this.usuario.getMuestras().addAll(this.muestras);
		this.usuario.getOpiniones().addAll(this.opiniones);
		this.usuario.recalcularNivel();
		// EN ESTE PUNTO USUARIO ES EXPERTO.
		this.usuario.getOpiniones().removeAll(this.opiniones);
		this.usuario.recalcularNivel();
		// exercise
		this.usuario.esEspecialista();
		// verify
		assertTrue(this.usuario.esExperto());
	}
	
	@Test
	void testUsuarioEsMarcadoComoEspecialistaEntoncesNoPuedeCambiarDeNivel() {
		// setUp
		this.usuario.getMuestras().addAll(muestras);
		this.usuario.getOpiniones().addAll(opiniones);
		this.usuario.puedeCambiarNivel();
		this.usuario.recalcularNivel();
		// EN ESTE PUNTO USUARIO ES EXPERTO
		this.usuario.getOpiniones().removeAll(opiniones);
		this.usuario.recalcularNivel();
		this.usuario.esEspecialista();
		// EN ESTE PUNTO USUARIO SIENDO ESPECIALISTA ES EXPERTO.
		// exercise
		this.usuario.recalcularNivel();
		// verify
		assertTrue(this.usuario.esExperto());
	}
	
	@Test
	void testUsuarioEnviaMuestra() {
		// setUp
		Usuario usuario = new Usuario(aplicacionWeb, "usuario02");
		Foto foto = new Foto("vinchucaFea");
		Ubicacion ubicacion = new Ubicacion(10.00, 30.00);
		Zona zona = new Zona("BuenosAires", ubicacion, 15.00);
		Opinion opinion = new Opinion(Calificacion.VINCHUCA_SORDIDA, LocalDate.of(20, 12, 5), usuario);
		Muestra muestra = new Muestra(foto, ubicacion, opinion);
		// exercise
		usuario.enviarMuestra(muestra);
		// verify
		assertEquals(aplicacionWeb.getMuestras().size(),1);
		assertTrue(zona.getMuestras().isEmpty());
		assertFalse(usuario.esExperto());
	}
	
	
	@Test
	void testUsuarioOpinaSobreMuestras() {
		// setUp
		Usuario usuario = new Usuario(aplicacionWeb, "usuario02");
		Foto foto = new Foto("vinchucaFea");
		Ubicacion ubicacion = new Ubicacion(10.00, 30.00);
		Opinion opinion = new Opinion(Calificacion.VINCHUCA_SORDIDA, LocalDate.of(20, 12, 5), usuario);
		Muestra muestra = new Muestra(foto, ubicacion, opinion);
		// exercise
		usuario.opinarSobreMuestra(muestra, opinion);
		// verify
		assertEquals(usuario.getOpiniones().size(), 1);
		assertEquals(muestra.getOpiniones().size(), 1);
		assertFalse(usuario.esExperto());
	}
}









