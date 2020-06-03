package unq.po2.tbd.vinchucas;

import java.util.ArrayList;
import java.util.List;

public class AplicacionWeb {
	
	private List<Usuario> usuarios;
	private List<Zona> zonas;
	private List<Muestra> muestras;
	
	public AplicacionWeb(List<Usuario> usuarios, List<Zona> zonas, List<Muestra> muestras) {
		this.setUsuarios(new ArrayList<Usuario>());
		this.setZonas(new ArrayList<Zona>());
		this.setMuestras(new ArrayList<Muestra>());
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
	
	public void registrarUsuario(Usuario usuario) {
		this.getUsuarios().add(usuario);
	}
	
	public void registrarZona(Zona zona) {
		this.getZonas().add(zona);
	}
	
	public void registrarMuestra(Muestra muestra) {
		this.getMuestras().add(muestra);
	}

}
