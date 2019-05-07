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
	
	public TiendaController() {
		
		_dao = new TiendaDAOJSON();
		_TiendaDTO = new TiendaDTO(createJuegosEnTienda());
		
	}


	private List<JuegoDTO> createJuegosEnTienda() {
		
		List<JuegoDTO> pubGames = new ArrayList<JuegoDTO>();
		//List<JuegoDTO> ownGames = new ArrayList<JuegoDTO>();
		
		//List<JuegoDTO> juegosEnTienda = new ArrayList<JuegoDTO>();
		
		pubGames = _dao.getPublishedGames();
		
		/*
		ownGames = _dao.getOwnedGames(user);
		
		for(JuegoDTO j : pubGames) {
			
			if(!ownGames.contains(j))
				juegosEnTienda.add(j);
			
		}
		
		return juegosEnTienda;
		*/
		return pubGames;
		
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

	@Override
	public JPanel getPanel(String panel, Object newValue) {
		
		if (panel.equals("Tienda"))
			return new MainViewTienda(this);
		
		else if (panel.equals("ComprarJuego"))
			return new ComprarJuego((JuegoDTO) newValue); 
		
		else
			return null;
		
	}

}
