package Juego.Control;

import java.util.List;

public interface JuegoDAO {
	
	public abstract List<LogroDTO> getLogros(String _id);
	
}
