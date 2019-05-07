package Tienda.Control;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import Juego.Control.JuegoDTO;
import Tienda.View.ComprarJuego;
import Tienda.View.MainViewTienda;
import Usuario.Control.UsuarioDTO;
import common.Controller;

public class TiendaController extends Controller{
	
	private TiendaDAO _dao;
	TiendaDTO _TiendaDTO;
	
	public TiendaController(UsuarioDTO user) {
		
		_dao = new TiendaDAOJSON();
		_TiendaDTO = new TiendaDTO(createJuegosEnTienda(user));
		
	}
	
	public TiendaController() {
		_dao = new TiendaDAOJSON();
		_TiendaDTO = new TiendaDTO(getJuegosEnTienda());
	}

	

	public void changeUser(UsuarioDTO newUser) {
		
		_TiendaDTO = new TiendaDTO(createJuegosEnTienda(newUser));
		
	}


	private List<JuegoDTO> createJuegosEnTienda(UsuarioDTO user) {
		
		List<JuegoDTO> pubGames = new ArrayList<JuegoDTO>();
		List<JuegoDTO> ownGames = new ArrayList<JuegoDTO>();
		
		List<JuegoDTO> juegosEnTienda = new ArrayList<JuegoDTO>();
		
		pubGames = _dao.getPublishedGames();
		
		ownGames = _dao.getOwnedGames(user.get_user_id());
		
		for(JuegoDTO j : pubGames) {
			
			if(!ownGames.contains(j))
				juegosEnTienda.add(j);
			
		}
		
		return juegosEnTienda;
		
	}
	
	public List<JuegoDTO> getJuegosEnTienda() {
		List<JuegoDTO> juegosEnTienda = _dao.getPublishedGames();
		
		return juegosEnTienda;
	}
	
	public void comprarJuego(JuegoDTO juego) {
		
		_TiendaDTO.comprarJuego(juego);
		
		/// bibliotecaController.anadirJuego(juego)
		
	}
	
	public void deleteTienda() {
		
		_TiendaDTO.deleteTienda();
		
	}

	@Override
	public JPanel getPanel(String panel, Object o, UsuarioDTO user) {
		
		if (panel.equals("Tienda")) {
			getJuegosEnTienda();
			return new MainViewTienda(this, user);
		}
			
		
		else if (panel.equals("ComprarJuego"))
			return new ComprarJuego((JuegoDTO) o); 
		
		else
			return null;
		
	}

}
