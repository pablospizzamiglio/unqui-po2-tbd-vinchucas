package unq.po2.tbd.vinchucas;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Usuario {
	
	private Aplicacion aplicacion;
	private String identificacion;
	private Nivel nivel;
	private List<Muestra> muestras;
	private List<Opinion> opiniones;
	
	public Usuario(Aplicacion aplicacion, String identificacion) {
		this.setAplicacion(aplicacion);
		this.setIdentificacion(identificacion);
		this.setMuestras(new ArrayList<Muestra>());
		this.setOpiniones(new ArrayList<Opinion>());
		this.setNivel(new NivelBasico());
	}
	
	public Aplicacion getAplicacion() {
		return aplicacion;
	}
	
	private void setAplicacion(Aplicacion aplicacion) {
		this.aplicacion = aplicacion;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	private void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public Nivel getNivel() {
		return nivel;
	}
	
	public void setNivel(Nivel nivel) {
		this.nivel = nivel;
	}

	public List<Muestra> getMuestras() {
		return muestras;
	}

	private void setMuestras(List<Muestra> muestras) {
		this.muestras = muestras;
	}

	public List<Opinion> getOpiniones() {
		return opiniones;
	}

	private void setOpiniones(List<Opinion> opiniones) {
		this.opiniones = opiniones;
	}

	public Long numeroMuestrasUltimos30Dias() {
		return this.getOpiniones().stream()
				.filter(m -> Math.abs(ChronoUnit.DAYS.between(m.getFecha(), LocalDate.now())) >= 30)
				.count();
	}
	
	public Long numeroOpinionesUltimos30Dias() {
		return this.getOpiniones().stream()
				.filter(o -> Math.abs(ChronoUnit.DAYS.between(o.getFecha(), LocalDate.now())) >= 30)
				.count();
	}
	
	public Boolean puedeCambiarNivel() {
		return this.numeroMuestrasUltimos30Dias() == 10 
				&& this.numeroOpinionesUltimos30Dias() == 20;
	}
	
	public void recalcularNivel() {
		if (this.puedeCambiarNivel()) {
			this.getNivel().cambiarNivel(this);
		}
	}
	
	public Boolean esExperto() {
		return this.getNivel().esExperto();
	}
	
	public void enviarMuestra(Muestra muestra) {
		this.getMuestras().add(muestra);
		this.getAplicacion().registrarMuestra(muestra);
		this.recalcularNivel();
	}
	
	public void opinarSobreMuestra(Muestra muestra, Opinion opinion) {
		this.getOpiniones().add(opinion);
		this.getAplicacion().registrarOpinionSobreMuestra(muestra, opinion);
		this.recalcularNivel();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((identificacion == null) ? 0 : identificacion.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (identificacion == null) {
			if (other.identificacion != null)
				return false;
		} else if (!identificacion.equals(other.identificacion))
			return false;
		return true;
	}

}
