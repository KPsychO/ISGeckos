package Biblioteca.Control;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import Usuario.Control.UsuarioDTO;

public class BibliotecaDTO {

	private static BibliotecaController _bc;
	
	private String _userId;
	private List<JuegoEnPropiedadDTO> _juegosEnBiblioteca = new ArrayList<JuegoEnPropiedadDTO>();
	@SuppressWarnings("unused")
	private List<BibliotecaDTO> _bibliotecas;
	
	//private BibliotecaDAO _dao;
	
	// Biblioteca de prueba
	public BibliotecaDTO(List<JuegoEnPropiedadDTO> games) {
		this._userId = "Generic";
		this._juegosEnBiblioteca = games;
	}
	
	// Biblioteca de un usuario en concreto
	public BibliotecaDTO(List<JuegoEnPropiedadDTO> games, UsuarioDTO user) {
		this._userId = user.get_user_id();
		this._juegosEnBiblioteca = games;
	}
	
	public BibliotecaDTO(BibliotecaDTO dto) {
		this._userId = dto.get_userId();
		this._juegosEnBiblioteca = dto.get_juegosEnBiblioteca();
	}
	
	public BibliotecaDTO(UsuarioDTO dto) {
		_userId = dto.get_user_id();
		createJuegosEnBiblioteca();
	}

	public BibliotecaDTO(JSONObject biblioteca) {

		_userId = biblioteca.getString("_userId");
		_juegosEnBiblioteca = _bc.getOwnedGames(biblioteca.getJSONArray("_gamesList"));
	}

	public String get_userId() {
		return _userId;
	}

	@SuppressWarnings("static-access")
	private void createJuegosEnBiblioteca() {
		List<JuegoEnPropiedadDTO> _listOwnedGames = new ArrayList <JuegoEnPropiedadDTO>();
		
		_listOwnedGames = this._bc.getOwnedGames(this._userId);
		
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
	
	public void anadirJuego(JuegoEnPropiedadDTO juego) {
		this._juegosEnBiblioteca.add(juego);
	}
}
