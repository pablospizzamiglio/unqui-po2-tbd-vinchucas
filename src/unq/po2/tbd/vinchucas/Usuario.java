package unq.po2.tbd.vinchucas;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Usuario {
	
	private String identificacion;
	private Nivel nivel;
	private List<Muestra> muestras;
	private List<Opinion> opiniones;

	public Usuario(String identificacion) {
		this.setIdentificacion(identificacion);
		this.setNivel(new NivelBasico());
		this.setMuestras(new ArrayList<Muestra>());
		this.setOpiniones(new ArrayList<Opinion>());
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public Nivel getNivel() {
		return nivel;
	}

	private void setNivel(Nivel nivel) {
		this.nivel = nivel;
	}

	public List<Muestra> getMuestras() {
		return muestras;
	}

	public void setMuestras(List<Muestra> muestras) {
		this.muestras = muestras;
	}

	public List<Opinion> getOpiniones() {
		return opiniones;
	}

	public void setOpiniones(List<Opinion> opiniones) {
		this.opiniones = opiniones;
	}
	
	public Boolean puedePromocionarNivel() {
		return true;
	}
	
	public Integer numeroMuestrasEnviadas() {
		return this.getMuestras().size();
	}
	
	public Integer numeroOpiniones() {
		return this.getOpiniones().size();
	}
	
	public Boolean esExperto() {
		return this.getNivel().esExperto();
	}
	
	public void opinarSobreMuestra(Muestra muestra, String calificacion, LocalDate fecha) {
		muestra.agregarOpinion(calificacion, fecha, this);
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
