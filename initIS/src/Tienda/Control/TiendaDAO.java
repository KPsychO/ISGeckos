package Tienda.Control;

import java.util.List;
import Juego.Control.JuegoDTO;

public interface TiendaDAO {
	
	public abstract List<JuegoDTO>getPublishedGames();
	public abstract int getAccountBalance(String user_id);
	public abstract List<JuegoDTO> getPublishedGames(String genre);

}
