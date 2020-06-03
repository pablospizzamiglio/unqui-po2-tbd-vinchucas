package unq.po2.tbd.vinchucas;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Zona {

	private String nombre;
	private Ubicacion epicentro;
	private Double distancia;
	private List<Muestra> muestras;
	private List<Suscriptor> suscriptores;

	public Zona(String nombre, Ubicacion epicentro, Double distancia, List<Muestra> muestras,
			List<Suscriptor> suscriptores) {
		this.setNombre(nombre);
		this.setEpicentro(epicentro);
		this.setDistancia(distancia);
		this.setMuestras(new ArrayList<Muestra>());
		this.setSuscriptores(new ArrayList<Suscriptor>());
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Ubicacion getEpicentro() {
		return epicentro;
	}

	public void setEpicentro(Ubicacion epicentro) {
		this.epicentro = epicentro;
	}

	public Double getDistancia() {
		return distancia;
	}

	public void setDistancia(Double radio) {
		this.distancia = radio;
	}

	public List<Muestra> getMuestras() {
		return muestras;
	}

	private void setMuestras(List<Muestra> muestras) {
		this.muestras = muestras;
	}

	public List<Suscriptor> getSuscriptores() {
		return suscriptores;
	}

	private void setSuscriptores(List<Suscriptor> suscriptores) {
		this.suscriptores = suscriptores;
	}

	public void agregarMuestra(Muestra muestra) {
		this.getMuestras().add(muestra);
		this.notificar(this, muestra);
	}

	public void suscribir(Suscriptor subscriptor) {
		this.getSuscriptores().add(subscriptor);
	}

	public void desuscribir(Suscriptor suscriptor) {
		this.getSuscriptores().remove(suscriptor);
	}

	public void notificar(Zona zona, Muestra muestra) {
		this.getSuscriptores().forEach(s -> s.notificar(zona, muestra));
	}

	public Boolean estaDentro(Muestra muestra) {
		return this.getEpicentro().distancia(muestra.getUbicacion()) <= this.getDistancia();
	}
	
	/**
	 * Calcula si dos zonas están solapadas.
	 * Dos zonas están solapadas si la distancia entre sus 
	 * epicentros es menor que la suma de sus radios.
	 * 
	 * @param destino La zona de interés.
	 * @return Un valor booleano que indica si ambas zonas se solapan.
	 */	
	public Boolean estaSolapada(Zona zona) {
		return this.getEpicentro().distancia(zona.getEpicentro()) < this.getDistancia() + zona.getDistancia();
	}

	public List<Zona> solapadas(List<Zona> zonas) {
		return zonas.stream()
				.filter(z -> z.estaSolapada(this))
				.collect(Collectors.toList());
	}

}
