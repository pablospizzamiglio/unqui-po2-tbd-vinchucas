package unq.po2.tbd.vinchucas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



class BuscadorTest {
	
	   private Muestra muestra1;
	   private Muestra muestra2;
	   private Muestra muestra3;
	   private Muestra muestra4;
	   private AplicacionWeb web;
	   private Buscador buscador;
	   private CriterioNivel criterio1;
	   private CriterioTipoInsecto criterio2;
	   private CriterioFechaUltimaVotacion criterio3;
	   private CriterioFechaCreacion criterio4;
	   private CriterioCompuesto criterio5;
	   private LocalDate d1;
	   private LocalDate d2;
	   private Foto foto1;
	   private Foto foto2;
	   private Foto foto3;
	   private Foto foto4;
	   private Opinion o1;
	   private Opinion o2;
	   private Opinion o3;
	   private Opinion o4;
	   private Ubicacion lanus;
	   private Ubicacion avellaneda ;
	   private Ubicacion quilmes ;
	   private Ubicacion varela;
	   private Usuario alex ;
	   private Usuario ima ;
	   private Usuario maria;
	   

	   List <CriterioDeBusqueda> criterios ;
	   List <Muestra> muestras ;

	   @BeforeEach
	   public void setUp() {
		   buscador = new Buscador();
		   criterio1 = new CriterioNivel(NivelVerificacion.VOTADA);
		   criterio2 = new CriterioTipoInsecto(Calificacion.VINCHUCA_INFESTANS);
		   d1 = LocalDate.of(2020,1,2);
		   d2 = LocalDate.of(2020,9,9);
		   criterio3 = new CriterioFechaUltimaVotacion(d1) ;
		   criterio4 = new CriterioFechaCreacion(d2);
		   criterio5 = new CriterioCompuesto();
		   ima = new Usuario(web, "ima");
		   alex = new Usuario(web,"alex");
		   maria = new Usuario(web,"maria");
		   avellaneda = new Ubicacion(1.3,22.1);
		   lanus = new Ubicacion(11.2,32.7);
		   quilmes = new Ubicacion(12.3,3.2);
		   varela = new Ubicacion(28.7,77.9);
		   foto1 = new Foto("fotito");
		   foto2 = new Foto("vinchuquita");
		   foto3 = new Foto("bichito");
		   foto4 = new Foto("lucecita");
		   o1 = new Opinion(Calificacion.VINCHUCA_INFESTANS,d1,alex);
		   o2 = new Opinion(Calificacion.VINCHUCA_INFESTANS,d2,ima);
		   o3 = new Opinion(Calificacion.VINCHUCA_SORDIDA,d1,alex);
		   o4 = new Opinion(Calificacion.INDEFINIDA,d2,maria);
		   muestra1 = new Muestra(foto1, avellaneda, o1);
		   muestra2 = new Muestra(foto2,quilmes,o2);
		   muestra3 = new Muestra(foto3,lanus,o3);
		   muestra4 = new Muestra(foto4,varela,o4);
           web = new AplicacionWeb(buscador);
           criterios = new ArrayList <CriterioDeBusqueda>();
		   muestras = new ArrayList <Muestra>();
		   web.registrarMuestra(muestra1);
		   web.registrarMuestra(muestra2);
		   web.registrarMuestra(muestra3);
		   web.registrarMuestra(muestra4);
	   }

	@Test
	public void constructorTest() {
		this.buscador = new Buscador();
		assertNotNull(this.buscador);
	}
	
	  @Test
	   public void buscarPorEspecie() {
		criterios.add(criterio2);
		muestras.add(muestra1);
		assertEquals(muestras,buscador.buscar(muestras, criterios));
	 }
	  
	@Test 
	public void buscarPorFechaDeCreacion() {
		criterios.add(criterio4);
		muestras.add(muestra2);
		assertEquals(muestras,buscador.buscar(muestras, criterios));
	}
		
	@Test 
	public void buscarPorFechaUltimaVotacion() {
		criterios.add(criterio3);
		muestras.add(muestra3);
		assertEquals(muestras,buscador.buscar(muestras, criterios));
	}
	   
	@Test
	public void buscarPorNivel() {
		criterios.add(criterio1);
		muestras.add(muestra4);
		assertEquals(muestras, buscador.buscar(muestras, criterios));
	}
	
	@Test
	public void busquedaSinResultado() {
		criterios.add(criterio2);
		muestras.add(muestra3);
		assertTrue(buscador.buscar(muestras, criterios).isEmpty());
	}
	  
	@Test
	public void busquedaCompuesta() {
		criterio5.agregarCriterio(criterio1);
		criterio5.agregarCriterio(criterio3);
		criterio5.agregarCriterio(criterio4);
		criterios.add(criterio5);
		muestras.add(muestra2);
		muestras.add(muestra3);
		muestras.add(muestra4);
		assertEquals(muestras,buscador.buscar(muestras, criterios));
	}	
}
