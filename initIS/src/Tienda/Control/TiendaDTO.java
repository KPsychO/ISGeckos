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
	
	protected void comprarJuego(JuegoDTO j) {	// se usa al comprar un juego, se elimina de la tienda y se anade a la biblioteca
		
		for(JuegoDTO jj : _juegosEnTienda) {
			
			if(jj.get_id().equals(j.get_id())) {
				_juegosEnTienda.remove(jj);
				break;
			}
			
		}
		
	}
	
	protected List<JuegoDTO> getJuegosEnTienda() {
		
		return _juegosEnTienda;
		
	}
	
	protected void deleteTienda() {
		
		_juegosEnTienda.clear();
		
	}

}
