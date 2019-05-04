package Biblioteca.Control;

import java.util.ArrayList;
import java.util.List;

import Juego.Control.JuegoDTO;

public class BibliotecaDTO {

	private String _userId;
	private List<JuegoEnPropiedadDTO> _juegosEnBiblioteca = new ArrayList<JuegoEnPropiedadDTO>();
	
	private BibliotecaDAO _dao;
	
	public BibliotecaDTO(String userId) {
		this._userId = userId;
		this._dao = new BibliotecaDAOJSON();
		createJuegosEnBiblioteca();
	}
	
	public BibliotecaDTO(BibliotecaDTO dto) {
		this._userId = dto.get_userId();
		this._dao = dto.get_dao();
		this._juegosEnBiblioteca = dto.get_juegosEnBiblioteca();
	}
	
	public BibliotecaDAO get_dao() {
		return _dao;
	}

	public String get_userId() {
		return _userId;
	}

	private void createJuegosEnBiblioteca() {
		List<JuegoEnPropiedadDTO> _listOwnedGames = new ArrayList <JuegoEnPropiedadDTO>();
		
		_listOwnedGames = this._dao.getOwnedGames(this._userId);
		
		for(JuegoEnPropiedadDTO i : _listOwnedGames)
			this._juegosEnBiblioteca.add(i);		
	}
	/*
	private void addJuego(JuegoDTO game) {
		JuegoEnPropiedadDTO newGame = new JuegoEnPropiedadDTO(game);
		_juegosEnBiblioteca.add(newGame);
	}
	 */
	public List<JuegoEnPropiedadDTO> get_juegosEnBiblioteca() {
		return _juegosEnBiblioteca;
	}
	
	public void BibltoJSON(List<JuegoEnPropiedadDTO> gameList, String userId) {
		_dao = new BibliotecaDAOJSON();
		_dao.writeBiblJSON(gameList, userId);
	}
}
