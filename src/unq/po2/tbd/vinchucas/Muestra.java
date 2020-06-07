package unq.po2.tbd.vinchucas;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Muestra {

	private Usuario usuario;
	private Imagen foto;
	private Ubicacion ubicacion;
	private LocalDate fecha;
	private Calificacion especie;
	private List<Opinion> opiniones;
	
	public Muestra(Imagen foto, Ubicacion ubicacion, Opinion opinion) {
		this.setUsuario(opinion.getUsuario());
		this.setFoto(foto);
		this.setFecha(opinion.getFecha());
		this.setUbicacion(ubicacion);
		this.setEspecie(opinion.getCalificacion());
		this.setOpiniones(new ArrayList<Opinion>());
		this.agregarOpinion(opinion);
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

	public void setOpiniones(List<Opinion> opiniones) {
		this.opiniones = opiniones;
	}

	public void agregarOpinion(Opinion opinion) {
		if (puedeOpinar(opinion.getUsuario())) {
			this.getOpiniones().add(opinion);
		}
	}
	
	public String getIdentificacionUsuario() {
		return this.getUsuario().getIdentificacion();
	}
	
	public Calificacion resultadoActual() {
		Calificacion resultadoActual = Calificacion.INDEFINIDA;
		
		Optional<Entry<Calificacion, Long>> opinionMasVotada = this.getOpiniones().stream()
				.collect(Collectors.groupingBy(Opinion::getCalificacion, Collectors.counting()))
				.entrySet()
				.stream()
				.max(Comparator.comparing(Entry::getValue));
		
		if (opinionMasVotada.isPresent() && opinionMasVotada.get().getValue() > 1) {
			Long maximaCantidadVotos = opinionMasVotada.get().getValue();
			
			List<Entry<Calificacion, Long>> opinionesEmpatadas = this.getOpiniones().stream()
					.collect(Collectors.groupingBy(Opinion::getCalificacion, Collectors.counting()))
					.entrySet()
					.stream()
					.filter(e -> e.getValue() == maximaCantidadVotos)
					.collect(Collectors.toList());
			
			if (opinionesEmpatadas.size() == 1) {
				resultadoActual = opinionMasVotada.get().getKey();
			}
		}
		
		return resultadoActual;
	}
	
	private Long numeroOpinionesExperto() {
		return this.getOpiniones().stream()
				.filter(o -> o.esDeUsuarioExperto())
				.count();
	}
	
	public Boolean estaVerificada() {
		Optional<Long> opinionMasVotada = this.getOpiniones().stream()
				.filter(o -> o.esDeUsuarioExperto())
				.collect(Collectors.groupingBy(Opinion::getCalificacion, Collectors.counting()))
				.values()
				.stream()
				.max(Comparator.comparing(Function.identity()));
		
		Long mayorNumeroVotos = 0L;
		if (opinionMasVotada.isPresent()) {
			mayorNumeroVotos = opinionMasVotada.get();
		}
		
		return mayorNumeroVotos == 2;
	}
	
	private Boolean yaOpino(Usuario usuario) {
		return this.getOpiniones().stream()
				.anyMatch(o -> o.getUsuario().equals(usuario));
	}
	
	private Boolean puedeOpinar(Usuario usuario) {
		Long numeroOpinionesExperto = this.numeroOpinionesExperto();
		return !this.yaOpino(usuario) 
				&& (
						numeroOpinionesExperto == 0L 
						|| (numeroOpinionesExperto >= 1L && !this.estaVerificada() && usuario.esExperto())
				);
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
		NivelVerificacion nivelVerificacion = NivelVerificacion.VOTADA;
		if (this.estaVerificada()) {
			nivelVerificacion = NivelVerificacion.VERIFICADA;
		}
		return nivelVerificacion;
	}
	
	public LocalDate getFechaUltimaVotacion() {
		return this.getOpiniones().get(this.getOpiniones().size() -1).getFecha();
	}

}
