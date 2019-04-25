package Tienda.Control;

import java.util.ArrayList;
import java.util.List;

import Juego.Control.JuegoDTO;

public class TiendaDTO {
	
	// Clase transfer para el tratamiento de datos necesarios para el objeto tienda
	// Contiene los metodos CRUD necesarios
	
	private String user_id;
	private List<JuegoDTO> juegosEnTienda = new ArrayList<JuegoDTO>();
	private TiendaDAO dao;
	
	public TiendaDTO(String user_id) {
		
		this.user_id = user_id;
		dao = new TiendaDAOJSON();
		createJuegosEnTienda();
		
	}
	
	private void createJuegosEnTienda() {
		
		List<JuegoDTO> pubGames = new ArrayList<JuegoDTO>();
		List<JuegoDTO> ownGames = new ArrayList<JuegoDTO>();
		
		pubGames = dao.getPublishedGames();
		ownGames = dao.getOwnedGames(user_id);
		
		for(JuegoDTO j : pubGames) {
			
			if(!ownGames.contains(j))
				juegosEnTienda.add(j);
			
		}
		
	}
	
	public void eliminarJuegoDeTienda(JuegoDTO j) {	// se usa al comprar un juego, se elimina de la tienda y se anade a la biblioteca
		
		if(juegosEnTienda.contains(j)) {
			juegosEnTienda.remove(j);
			// Juegos comprados.add(j);
		}
		
	}
	
	public List<JuegoDTO> getJuegosEnTienda() {
		
		return juegosEnTienda;
		
	}
	
	public void deleteTienda() {
		
		juegosEnTienda.clear();
		
	}

}
