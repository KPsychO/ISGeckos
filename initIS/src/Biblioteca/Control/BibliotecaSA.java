package Biblioteca.Control;

import java.util.List;

import org.json.JSONArray;

import Juego.Control.JuegoDTO;
import Usuario.Control.UsuarioDTO;

public class BibliotecaSA {

	private BibliotecaDTO _dto;
	
	public BibliotecaSA(UsuarioDTO user) {
		_dto = new BibliotecaDTO(getOwnedGames(user), user);
	}

	
	private List<JuegoEnPropiedadDTO> getOwnedGames(UsuarioDTO user) {
		return SingletonBibliotecaDAO.getInstance().getOwnedGames(user.get_user_id());
	}


	public List<JuegoEnPropiedadDTO> getOwnedGames(String userId) {
		
		return SingletonBibliotecaDAO.getInstance().getOwnedGames(userId);
		
	}
	
	public List<JuegoEnPropiedadDTO> getOwnedGames(JSONArray jsonArray) {
		
		return SingletonBibliotecaDAO.getInstance().getOwnedGames(jsonArray);
	}


	public void anadirJuego(JuegoEnPropiedadDTO newGame, UsuarioDTO user) {
		_dto.anadirJuego(newGame);
		SingletonBibliotecaDAO.getInstance().actualizarBiblioteca(newGame, user);
	}


	public void newBibliotecaPanel(UsuarioDTO user) {
		_dto = new BibliotecaDTO(getOwnedGames(user), user);
		
	}


	public List<JuegoEnPropiedadDTO> get_juegosEnBiblioteca() {
		return _dto.get_juegosEnBiblioteca();
	}


	public List<BibliotecaDTO> getLibraries() {
		return SingletonBibliotecaDAO.getInstance().getLibraries();
	}


	public void actualizarJuego(JuegoEnPropiedadDTO juego, UsuarioDTO user) {
		juego.set_actVersion(juego.get_version());
		SingletonBibliotecaDAO.getInstance().actualizarBiblioteca(_dto.getJuego(juego), user);
	}


	public void instalarJuego(JuegoEnPropiedadDTO juego, UsuarioDTO user) {
		juego.set_installed(true);
		SingletonBibliotecaDAO.getInstance().actualizarBiblioteca(juego, user);
	}


	public void ejecutarJuego(JuegoEnPropiedadDTO juego, UsuarioDTO user) {
		_dto.ejecutarJuego(juego);
		SingletonBibliotecaDAO.getInstance().actualizarBiblioteca(_dto.getJuego(juego), user);
	}


	public void comprarJuego(JuegoDTO j, UsuarioDTO user) {
		SingletonBibliotecaDAO.getInstance().insertarJuego(j, user);
	}


	public void eliminarJuego(String id) {
		SingletonBibliotecaDAO.getInstance().eliminarJuegos(id);
	}


	public void eliminarUsuario(UsuarioDTO user) {
		SingletonBibliotecaDAO.getInstance().eliminarBiblioteca(user.get_user_id());
	}
		
}
