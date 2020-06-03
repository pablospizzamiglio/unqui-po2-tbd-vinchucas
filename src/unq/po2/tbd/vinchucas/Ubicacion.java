package unq.po2.tbd.vinchucas;

import java.util.List;
import java.util.stream.Collectors;

public class Ubicacion {
	
	private Double latitud;
	private Double longitud;

	public Ubicacion(Double latitud, Double longitud) {
		this.setLatitud(latitud);
		this.setLongitud(longitud);
	}

	public Double getLatitud() {
		return latitud;
	}

	private void setLatitud(Double latitud) {
		this.latitud = latitud;
	}

	public Double getLongitud() {
		return longitud;
	}

	private void setLongitud(Double longitud) {
		this.longitud = longitud;
	}
	
	/**
	 * Calcula la distancia en kilómetros entre dos ubicaciones
	 * aplicando la fórmula del semiverseno.
	 * 
	 * @param destino La Ubicacion de destino.
	 * @return La distancia entre dos ubicaciones en kilómetros.
	 */
	public Double distancia(Ubicacion destino) {
		Double radioTierraEnKm = Double.valueOf(6371);
		
        Double diferenciaLatitudinal = Math.toRadians(destino.getLatitud() - this.getLatitud());
        Double diferenciaLongitudinal = Math.toRadians(destino.getLongitud() - this.getLongitud());
        
        Double senoDiferenciaLatitudinal = Math.sin(diferenciaLatitudinal / 2);
        Double senoDiferenciaLongitudinal = Math.sin(diferenciaLongitudinal / 2);
        
        Double origenLatitudRadianes = Math.toRadians(this.getLatitud());
        Double destinoLatitudRadianes = Math.toRadians(destino.getLatitud());
        
        Double a = Math.pow(senoDiferenciaLatitudinal, 2) + Math.pow(senoDiferenciaLongitudinal, 2) * Math.cos(origenLatitudRadianes) * Math.cos(destinoLatitudRadianes);  
        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        
        return radioTierraEnKm * c;
	}
	
	public List<Ubicacion> ubicacionesAMenosDe(Double kilometros, List<Ubicacion> ubicaciones) {
		return ubicaciones.stream()
				.filter(u -> u.distancia(this) < kilometros)
				.collect(Collectors.toList());
	}

}
