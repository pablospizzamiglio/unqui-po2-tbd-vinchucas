package unq.po2.tbd.vinchucas;

import java.util.ArrayList;
import java.util.List;

public class AplicacionWeb implements Aplicacion {
	
	private Buscador buscador;
	private List<Usuario> usuarios;
	private List<Zona> zonas;
	private List<Muestra> muestras;
	
	public AplicacionWeb(Buscador buscador) {
		this.setBuscador(buscador);
		this.setUsuarios(new ArrayList<Usuario>());
		this.setZonas(new ArrayList<Zona>());
		this.setMuestras(new ArrayList<Muestra>());
	}
	
	public Buscador getBuscador() {
		return buscador;
	}

	private void setBuscador(Buscador buscador) {
		this.buscador = buscador;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	
	private void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	public List<Zona> getZonas() {
		return zonas;
	}
	
	private void setZonas(List<Zona> zonas) {
		this.zonas = zonas;
	}
	
	public List<Muestra> getMuestras() {
		return muestras;
	}
	
	private void setMuestras(List<Muestra> muestras) {
		this.muestras = muestras;
	}
	
	@Override
	public void registrarUsuario(Usuario usuario) {
		this.getUsuarios().add(usuario);
	}
	
	@Override
	public void registrarZona(Zona zona) {
		this.getZonas().add(zona);
		this.getMuestras().forEach(m -> zona.agregarMuestra(m));
	}
	
	@Override
	public void registrarMuestra(Muestra muestra) {
		this.getMuestras().add(muestra);

		for (Zona zona : this.getZonas()) {
			zona.agregarMuestra(muestra);
		}
	}

	@Override
	public void registrarOpinionSobreMuestra(Muestra muestra, Opinion opinion) {
		muestra.agregarOpinion(opinion);
		
		for (Zona zona : this.getZonas()) {
			if (zona.estaDentro(muestra)) {
				zona.nuevaOpinionRegistradaEn(muestra);
			}
		}
	}

	@Override
	public List<Muestra> buscar(List<CriterioDeBusqueda> criterios) {
		return this.getBuscador().buscar(this.getMuestras(), criterios);
	}


}
