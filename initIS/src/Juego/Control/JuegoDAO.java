package Juego.Control;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public interface JuegoDAO {
	
	public abstract List<LogroDTO> getLogros(JSONArray jsonArray);

	public abstract List<String> getGenres(JSONArray jsonArray);
	
}
