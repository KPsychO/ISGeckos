package Tienda.Control;

import java.util.List;

import Juego.Control.JuegoDTO;

public class TiendaDTO {
	
	// Clase transfer para el tratamiento de datos necesarios para el objeto tienda
	// Contiene los metodos CRUD necesarios
	
	private List<JuegoDTO> _juegosEnTienda;

	public TiendaDTO(List<JuegoDTO> juegosEnTienda) {
		
		_juegosEnTienda = juegosEnTienda;
		
	}
	
	protected List<JuegoDTO> getJuegosEnTienda() {
		
		return _juegosEnTienda;
		
	}
	
	protected void deleteTienda() {
		
		_juegosEnTienda.clear();
		
	}

}
