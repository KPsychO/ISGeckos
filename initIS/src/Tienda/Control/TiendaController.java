package Tienda.Control;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import Juego.Control.ControllerJuego;
import Juego.Control.JuegoDTO;
import Juego.View.MainViewJuego;
import Tienda.View.ComprarJuego;
import Tienda.View.MainViewTienda;
import Usuario.Control.UsuarioDTO;
import common.Controller;

public class TiendaController{
	
	private TiendaDAO _dao;
	private TiendaDTO _TiendaDTO;
	private Controller _controller;
	
	public TiendaController(Controller cont, UsuarioDTO user) {
		
		_controller = cont;
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
	
	public boolean comprarJuego(JuegoDTO juego) {
		
		if(_controller.getCurrentUser().get_balance() >= juego.get_price()) {
		
			_TiendaDTO.comprarJuego(juego);
			_controller.anadirJuegoComprado(juego);
			
			return true;
		
		}
		
		return false;
		
	}
	
	public void evento(EventoTienda e, JuegoDTO _juego, UsuarioDTO _user) {
		switch (e) {
		case accesoJuego:
			_controller.setPrincipalPanel(new MainViewJuego(_juego, getControllerJuego()));
			break;
		case comprarJuego:
			_controller.setPrincipalPanel(new MainViewTienda(this, _user));
			break;
		default:
			break;
		}
	}
	
	public void deleteTienda() {
		
		_TiendaDTO.deleteTienda();
		
	}
	
	@SuppressWarnings("exports")
	public JPanel getTiendaPanel(UsuarioDTO user) {
		
		return new MainViewTienda(this, user);
		
	}
	
	@SuppressWarnings("exports")
	public JPanel getComprarJuego(JuegoDTO juego) {
		
		return new ComprarJuego(juego, this);
		
	}
	
	public UsuarioDTO getCurrentUser() {
		
		return _controller.getCurrentUser();
		
	}
	
	public ControllerJuego getControllerJuego() {
		
		return _controller.getControllerJuego();	
		
	}

}
