package unq.po2.tbd.vinchucas;

import java.time.LocalDate;

public class Opinion {
	
	private String calificacion;
	private LocalDate fecha;
	private Usuario usuario;
	
	public Opinion(String calificacion, LocalDate fecha, Usuario usuario) {
		this.setCalificacion(calificacion);
		this.setFecha(fecha);
		this.setUsuario(usuario);
	}

	public String getCalificacion() {
		return calificacion;
	}

	private void setCalificacion(String calificacion) {
		this.calificacion = calificacion;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	private void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	private void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Boolean esDeUsuarioExperto() {
		return this.getUsuario().esExperto();
	}

}
