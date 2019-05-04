package Biblioteca.Control;

import java.util.List;

public interface BibliotecaDAO {

	public abstract List<JuegoEnPropiedadDTO> getOwnedGames(String user_id);
	
	public abstract void writeBiblJSON(List<JuegoEnPropiedadDTO> gameList, String userId);

}
