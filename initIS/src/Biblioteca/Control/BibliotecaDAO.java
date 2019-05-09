package Biblioteca.Control;

import java.util.List;

import org.json.JSONArray;

import Juego.Control.JuegoDTO;
import Usuario.Control.UsuarioDTO;

public interface BibliotecaDAO {

	public abstract List<JuegoEnPropiedadDTO> getOwnedGames(String user_id);
	public abstract List<JuegoEnPropiedadDTO> getOwnedGames(JSONArray games);
	
	public abstract List<BibliotecaDTO> getLibraries();
	
	public abstract void writeBiblJSON(List<JuegoEnPropiedadDTO> gameList, String userId);

	void writeBiblJSON(BibliotecaDTO library, String userId);

	public abstract void actualizarBiblioteca(JuegoEnPropiedadDTO newGame, UsuarioDTO user);
	
	public abstract void insertarJuego(JuegoDTO j, UsuarioDTO currentUser);

}
