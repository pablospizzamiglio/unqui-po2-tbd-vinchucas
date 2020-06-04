package unq.po2.tbd.vinchucas;

import java.time.LocalDate;

public class Opinion {
	
	private EspecieInsecto calificacion;
	private LocalDate fecha;
	private Usuario usuario;
	private Boolean esDeUsuarioExperto;
	
	public Opinion(EspecieInsecto calificacion, LocalDate fecha, Usuario usuario) {
		this.setCalificacion(calificacion);
		this.setFecha(fecha);
		this.setUsuario(usuario);
		this.setEsDeUsuarioExperto(usuario.esExperto());
	}

	public EspecieInsecto getCalificacion() {
		return calificacion;
	}

	private void setCalificacion(EspecieInsecto calificacion) {
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
