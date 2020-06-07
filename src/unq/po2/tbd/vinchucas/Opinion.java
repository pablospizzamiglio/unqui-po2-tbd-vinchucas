package unq.po2.tbd.vinchucas;

import java.time.LocalDate;

public class Opinion {
	
	private Calificacion calificacion;
	private LocalDate fecha;
	private Usuario usuario;
	private Boolean esDeUsuarioExperto;
	
	public Opinion(Calificacion calificacion, LocalDate fecha, Usuario usuario) {
		this.setCalificacion(calificacion);
		this.setFecha(fecha);
		this.setUsuario(usuario);
		this.setEsDeUsuarioExperto(usuario.esExperto());
	}

	public Calificacion getCalificacion() {
		return calificacion;
	}

	private void setCalificacion(Calificacion calificacion) {
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
		return esDeUsuarioExperto;
	}

	private void setEsDeUsuarioExperto(Boolean esDeUsuarioExperto) {
		this.esDeUsuarioExperto = esDeUsuarioExperto;
	}
}
