package Tienda.Control;

import java.util.List;

import Juego.Control.JuegoDTO;
import Usuario.Control.UsuarioDTO;

public class TiendaDTO {
	
	// Clase transfer para el tratamiento de datos necesarios para el objeto tienda
	// Contiene los metodos CRUD necesarios
	
	private List<JuegoDTO> _juegosEnTienda;

	
	public TiendaDTO(UsuarioDTO user, List<JuegoDTO> juegosEnTienda) {
		
		_juegosEnTienda = juegosEnTienda;
		
	}
	
	protected void comprarJuego(JuegoDTO j) {	// se usa al comprar un juego, se elimina de la tienda y se anade a la biblioteca
		
		if(_juegosEnTienda.contains(j)) {
			_juegosEnTienda.remove(j);
		}
		
	}
	
	protected List<JuegoDTO> getJuegosEnTienda() {
		
		return _juegosEnTienda;
		
	}
	
	protected void deleteTienda() {
		
		_juegosEnTienda.clear();
		
	}

}
