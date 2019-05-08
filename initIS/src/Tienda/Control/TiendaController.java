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
	UsuarioDTO _user;
	BibliotecaController _bilioCont;
	
	public TiendaController(UsuarioDTO user) {
		
		_user = user;
		// _bilioCont = new BibliotecaController(_user);  Hasta que no este biblioteca, no se añaden juegos
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
		
		if(_user.get_balance() >= juego.get_price()) {
		
			_TiendaDTO.comprarJuego(juego);
			_bilioCont.anadirJuego(juego);
			
			return true;
		
		}
		
		return false;
		
	}
	
	public void deleteTienda() {
		
		_TiendaDTO.deleteTienda();
		
	}

	@SuppressWarnings("exports")
	@Override
	public JPanel getPanel(String panel, Object o, UsuarioDTO user) {
		
		if (panel.equals("Tienda")) {
			getJuegosEnTienda();
			return new MainViewTienda(this, user);
		}
			
		
		else if (panel.equals("ComprarJuego"))
			return new ComprarJuego((JuegoDTO) o, user); 
		
		else
			return null;
		
	}

}
