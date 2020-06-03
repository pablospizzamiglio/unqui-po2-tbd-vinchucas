package unq.po2.tbd.vinchucas;

import java.util.ArrayList;
import java.util.List;

public class Organizacion implements Suscriptor {
	
	private Ubicacion ubicacion;
	private TipoOrganizacion tipo;
	private Integer numeroEmpleados;
	private List<FuncionalidadExterna> funcionalidades;
	
	public Organizacion(Ubicacion ubicacion, TipoOrganizacion tipo, Integer numeroEmpleados) {
		this.setUbicacion(ubicacion);
		this.setTipo(tipo);
		this.setNumeroEmpleados(numeroEmpleados);
		this.setFuncionalidades(new ArrayList<FuncionalidadExterna>());
	}

	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}

	public TipoOrganizacion getTipo() {
		return tipo;
	}

	public void setTipo(TipoOrganizacion tipo) {
		this.tipo = tipo;
	}

	public Integer getNumeroEmpleados() {
		return numeroEmpleados;
	}

	public void setNumeroEmpleados(Integer numeroEmpleados) {
		this.numeroEmpleados = numeroEmpleados;
	}
	
	public List<FuncionalidadExterna> getFuncionalidades() {
		return funcionalidades;
	}
	
	private void setFuncionalidades(List<FuncionalidadExterna> funcionalidades) {
		this.funcionalidades = funcionalidades;
	}
	
	public void agregarFuncionalidad(FuncionalidadExterna funcionalidad) {
		this.getFuncionalidades().add(funcionalidad);
	}
	
	public void quitarFuncionalidad(FuncionalidadExterna funcionalidad) {
		this.getFuncionalidades().remove(funcionalidad);
	}

	@Override
	public void notificar(Zona zona, Muestra muestra) {
		this.getFuncionalidades().forEach(f -> f.nuevoEvento(this, zona, muestra));
	}

}
