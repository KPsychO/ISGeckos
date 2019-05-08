package Biblioteca.Control;

import java.util.List;

import org.json.JSONArray;

public interface BibliotecaDAO {

	public abstract List<JuegoEnPropiedadDTO> getOwnedGames(String user_id);
	public abstract List<JuegoEnPropiedadDTO> getOwnedGames(JSONArray games);
	
	public abstract List<BibliotecaDTO> getLibraries();
	
	public abstract void writeBiblJSON(List<JuegoEnPropiedadDTO> gameList, String userId);
	public abstract List<JuegoEnPropiedadDTO> getOwnedGames();

}
