package unq.po2.tbd.vinchucas;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Muestra {

	private MuestraEstado estado;
	private Imagen foto;
	private Ubicacion ubicacion;
	private Usuario usuario;
	private LocalDate fecha;
	private Calificacion especie;
	private List<Opinion> opiniones;
	
	public Muestra(Imagen foto, Ubicacion ubicacion, Opinion opinion) {
		this.setEstado(new MuestraVotacionAbierta());
		this.setFoto(foto);
		this.setUbicacion(ubicacion);
		this.setUsuario(opinion.getUsuario());
		this.setFecha(opinion.getFecha());
		this.setEspecie(opinion.getCalificacion());
		this.setOpiniones(new ArrayList<Opinion>());
		this.agregarOpinion(opinion);
	}

	public MuestraEstado getEstado() {
		return estado;
	}
	
	public void setEstado(MuestraEstado estado) {
		this.estado = estado;
	}
	
	private Usuario getUsuario() {
		return usuario;
	}

	private void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Imagen getFoto() {
		return foto;
	}

	private void setFoto(Imagen foto) {
		this.foto = foto;
	}

	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	private void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	private void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public Calificacion getEspecie() {
		return especie;
	}

	private void setEspecie(Calificacion especie) {
		this.especie = especie;
	}
	
	public List<Opinion> getOpiniones() {
		return opiniones;
	}

	private void setOpiniones(List<Opinion> opiniones) {
		this.opiniones = opiniones;
	}

	public void agregarOpinion(Opinion opinion) {
		if (this.getEstado().puedeOpinar(this, opinion.getUsuario())) {
			this.getOpiniones().add(opinion);
			this.getEstado().recalcularEstado(this);
		}
	}
	
	public String getIdentificacionUsuario() {
		return this.getUsuario().getIdentificacion();
	}
	
	public Calificacion resultadoActual() {
		return this.getEstado().resultadoActual(this);
	}
	
	public Boolean opinoPreviamente(Usuario usuario) {
		return this.getOpiniones().stream()
				.anyMatch(o -> o.getUsuario().equals(usuario));
	}
	
	private Double distancia(Muestra muestra) {
		return this.getUbicacion().distancia(muestra.getUbicacion());
	}
	
	public List<Muestra> muestrasAMenosDe(Double kilometros, List<Muestra> muestras) {
		return muestras.stream()
				.filter(m -> m.distancia(this) < kilometros)
				.collect(Collectors.toList());
	}
	
	public NivelVerificacion getNivelVerificacion() {
		return this.getEstado().getNivelVerificacion();
	}
	
	public LocalDate getFechaUltimaVotacion() {
		return this.getOpiniones().get(this.getOpiniones().size() -1).getFecha();
	}

}
