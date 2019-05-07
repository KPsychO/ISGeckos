package Tienda.Control;

import java.util.ArrayList;
import java.util.List;

import Juego.Control.JuegoDTO;
import Usuario.Control.UsuarioDTO;

public class TiendaController {
	
	private TiendaDAO _dao;
	TiendaDTO _TiendaDTO;
	
	public TiendaController(UsuarioDTO user) {
		
		_dao = new TiendaDAOJSON();
		_TiendaDTO = new TiendaDTO(user, createJuegosEnTienda(user.get_user_id()));
		
	}
	
	private List<JuegoDTO> createJuegosEnTienda(String user) {
		
		List<JuegoDTO> pubGames = new ArrayList<JuegoDTO>();
		List<JuegoDTO> ownGames = new ArrayList<JuegoDTO>();
		
		List<JuegoDTO> juegosEnTienda = new ArrayList<JuegoDTO>();
		
		pubGames = _dao.getPublishedGames();
		ownGames = _dao.getOwnedGames(user);
		
		for(JuegoDTO j : pubGames) {
			
			if(!ownGames.contains(j))
				juegosEnTienda.add(j);
			
		}
		
		return juegosEnTienda;
		
	}
	
	public void comprarJuego(JuegoDTO juego) {
		
		_TiendaDTO.comprarJuego(juego);
		
		/// bibliotecaController.anadirJuego(juego)
		
	}
	
	public void deleteTienda() {
		
		_TiendaDTO.deleteTienda();
		
	}

	public List<JuegoDTO> getJuegosEnTienda() {
		
		return _TiendaDTO.getJuegosEnTienda();
		
	}
	
}
