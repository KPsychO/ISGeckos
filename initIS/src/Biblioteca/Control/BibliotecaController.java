package Biblioteca.Control;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import org.json.JSONArray;

import Biblioteca.View.MainViewBiblioteca;
import Juego.Control.JuegoDTO;
import Usuario.Control.UsuarioDTO;
import common.Controller;

public class BibliotecaController{

	private BibliotecaDAO _dao;
	private BibliotecaDTO _dto;
	
	private Controller _controller;
	
	// Constructora sin par�metros -- Biblioteca gen�rica
	public BibliotecaController(Controller cont) {
		
		_controller = cont;
		_dao = new BibliotecaDAOJSON();
		_dto = new BibliotecaDTO(getGames());
	}
	
	// Constructora con par�metro user -- Biblioteca personal
	
	public BibliotecaController(UsuarioDTO user) {
		_dao = new BibliotecaDAOJSON();
		_dto = new BibliotecaDTO(getGames(user), user);
		
	}
	
	public List<JuegoEnPropiedadDTO> getGames(){
		List<JuegoEnPropiedadDTO> games = new ArrayList<JuegoEnPropiedadDTO>();
		
		games = _dao.getOwnedGames();
		
		return games;
	}
	
	public List<JuegoEnPropiedadDTO> getGames(UsuarioDTO user){
		
		List<JuegoEnPropiedadDTO> games = new ArrayList<JuegoEnPropiedadDTO>();
		
		games = _dao.getOwnedGames(user.get_user_id());
		
		return games;
	}

	public void anadirJuego(JuegoDTO juego) {
		JuegoEnPropiedadDTO newGame = new JuegoEnPropiedadDTO(juego);
		_dto.anadirJuego(newGame);
	}
	
	public JPanel getBibliotecaPanel() {
		
		return new MainViewBiblioteca(this, _controller.getCurrentUser());
		
	}

	public List<BibliotecaDTO> getLibraries() {
		
		return this._dao.getLibraries();
	}

	public List<JuegoEnPropiedadDTO> getOwnedGames(JSONArray jsonArray) {
		
		return this._dao.getOwnedGames(jsonArray);
	}

	public List<JuegoEnPropiedadDTO> getOwnedGames(String _userId) {
		
		return this._dao.getOwnedGames(_userId);
	}
	
}
