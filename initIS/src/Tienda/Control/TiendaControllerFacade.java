package Tienda.Control;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import Biblioteca.Control.JuegoEnPropiedadDTO;
import Juego.Control.JuegoControllerFacade;
import Juego.Control.JuegoDTO;
import Juego.View.MainViewJuego;
import Tienda.View.ComprarJuego;
import Tienda.View.MainViewTienda;
import Usuario.Control.UsuarioDTO;
import common.Controller;

public class TiendaControllerFacade{
	
	private TiendaDTO _TiendaDTO;
	private Controller _controller;
	
	public TiendaControllerFacade(Controller cont) {
		
		_controller = cont;
		_TiendaDTO = new TiendaDTO(createJuegosEnTienda(_controller.getCurrentUser()));
		
	}
	
	public TiendaControllerFacade() {

		_TiendaDTO = new TiendaDTO(createJuegosEnTienda(_controller.getCurrentUser()));
	}

	public void changeUser(UsuarioDTO newUser) {
		
		_TiendaDTO = new TiendaDTO(createJuegosEnTienda(newUser));
		
	}

	private List<JuegoDTO> createJuegosEnTienda(UsuarioDTO user) {
		
		return  SingletonTiendaDAO.getInstance().getPublishedGames();
		
	}
	
	public List<JuegoDTO> getJuegosEnTienda() {
		return SingletonTiendaDAO.getInstance().getPublishedGames();
	}
	
	public boolean comprarJuego(JuegoDTO juego) {
		
		if(_controller.getCurrentUser().get_balance() >= juego.get_price()) {
		
			_controller.quitaBalance(juego.get_price());
			_controller.anadirJuegoComprado(juego);
			
			return true;
		
		}
		
		return false;
		
	}
	
	public void evento(EventoTienda e, JuegoDTO _juego, UsuarioDTO _user) {
		switch (e) {
		case accesoJuego:
			boolean comprado = false;
			List<JuegoEnPropiedadDTO> list = _controller.getOwnedGames();
			for(JuegoDTO j : list) {
				if (j.get_id().equals(_juego.get_id()))
					comprado = true;
			}
			_controller.setPrincipalPanel(_controller.createVistaJuegoTienda(_juego, comprado));
			break;
		case comprarJuego:
			_controller.setPrincipalPanel(new MainViewTienda(this));
			break;
		case juegoComprado:
			_controller.anadirJuegoComprado(_juego);
		default:
			break;
		}
	}
	
	public void deleteTienda() {
		
		_TiendaDTO.deleteTienda();
		
	}
	
	@SuppressWarnings("exports")
	public JPanel getTiendaPanel() {
		
		return new MainViewTienda(this);
		
	}
	
	@SuppressWarnings("exports")
	public JPanel getComprarJuego(JuegoDTO juego) {
		
		return new ComprarJuego(juego, this);
		
	}
	
	public UsuarioDTO getCurrentUser() {
		
		return _controller.getCurrentUser();
		
	}
	
	public JuegoControllerFacade getControllerJuego() {
		
		return _controller.getControllerJuego();	
		
	}

}
