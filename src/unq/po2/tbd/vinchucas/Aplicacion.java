package unq.po2.tbd.vinchucas;

import java.util.List;

public interface Aplicacion {
	
	public void registrarZona(Zona zona);
	
	public void registrarUsuario(Usuario usuario);

	public void registrarMuestra(Muestra muestra);

	public void registrarOpinionSobreMuestra(Muestra muestra, Opinion opinion);

	public List<Muestra> buscar(List<CriterioDeBusqueda> criterios);

}
