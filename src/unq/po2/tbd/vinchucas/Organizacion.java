package unq.po2.tbd.vinchucas;

public class Organizacion implements Suscriptor {
	
	private Ubicacion ubicacion;
	private TipoOrganizacion tipo;
	private Integer numeroEmpleados;
	private FuncionalidadExterna funcionalidadNuevaMuestra;
	private FuncionalidadExterna funcionalidadNuevaOpinion;
	
	public Organizacion(Ubicacion ubicacion, TipoOrganizacion tipo, Integer numeroEmpleados, FuncionalidadExterna funcionalidadNuevaMuestra, FuncionalidadExterna funcionalidadNuevaOpinion) {
		this.setUbicacion(ubicacion);
		this.setTipo(tipo);
		this.setNumeroEmpleados(numeroEmpleados);
		this.setFuncionalidadNuevaMuestra(funcionalidadNuevaMuestra);
		this.setFuncionalidadNuevaOpinion(funcionalidadNuevaOpinion);
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
	
	public FuncionalidadExterna getFuncionalidadNuevaMuestra() {
		return funcionalidadNuevaMuestra;
	}

	public void setFuncionalidadNuevaMuestra(FuncionalidadExterna funcionalidadNuevaMuestra) {
		this.funcionalidadNuevaMuestra = funcionalidadNuevaMuestra;
	}

	public FuncionalidadExterna getFuncionalidadNuevaOpinion() {
		return funcionalidadNuevaOpinion;
	}

	public void setFuncionalidadNuevaOpinion(FuncionalidadExterna funcionalidadNuevaOpinion) {
		this.funcionalidadNuevaOpinion = funcionalidadNuevaOpinion;
	}

	@Override
	public void nuevaMuestra(Zona zona, Muestra muestra) {
		this.getFuncionalidadNuevaMuestra().nuevoEvento(this, zona, muestra);
	}
	
	@Override
	public void nuevaOpinion(Zona zona, Muestra muestra) {
		this.getFuncionalidadNuevaOpinion().nuevoEvento(this, zona, muestra);
	}

}
