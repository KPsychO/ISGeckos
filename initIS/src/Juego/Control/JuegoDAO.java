package Juego.Control;

import java.util.List;

import org.json.JSONArray;

public interface JuegoDAO {
	
	@SuppressWarnings("exports")
	public abstract List<LogroDTO> getLogros(JSONArray jsonArray);

	@SuppressWarnings("exports")
	public abstract List<String> getGenres(JSONArray jsonArray);
	
}
