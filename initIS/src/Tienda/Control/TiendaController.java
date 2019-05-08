package Tienda.Control;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import Biblioteca.Control.BibliotecaController;
import Juego.Control.JuegoDTO;
import Tienda.View.ComprarJuego;
import Tienda.View.MainViewTienda;
import Usuario.Control.UsuarioDTO;
import common.Controller;

public class TiendaController extends Controller{
	
	private TiendaDAO _dao;
	TiendaDTO _TiendaDTO;
	Controller _controller;
	
	public TiendaController(Controller cont) {
		
		_controller = cont;
		_dao = new TiendaDAOJSON();
		_TiendaDTO = new TiendaDTO(createJuegosEnTienda(_controller.getCurrentUser()));
		
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
	
	public boolean comprarJuego(JuegoDTO juego) {
		
		if(_controller.getCurrentUser().get_balance() >= juego.get_price()) {
		
			_TiendaDTO.comprarJuego(juego);
			_controller.anadirJuegoComprado(juego);
			
			return true;
		
		}
		
		return false;
		
	}
	
	public void deleteTienda() {
		
		_TiendaDTO.deleteTienda();
		
	}
	
	public JPanel getTienda() {
		
		return new MainViewTienda(this, _controller.getCurrentUser());
		
	}
	
	public JPanel getComprarJuego(JuegoDTO juego) {
		
		return new ComprarJuego(juego, _controller.getCurrentUser(), this);
		
	}

}
