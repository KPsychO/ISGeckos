package Juego.Control;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import Usuario.Control.UsuarioDTO;

public interface JuegoDAO {
	
	@SuppressWarnings("exports")
	public abstract List<LogroDTO> getLogros(JSONArray jsonArray);

	@SuppressWarnings("exports")
	public abstract List<String> getGenres(JSONArray jsonArray);
	
	public abstract List<JuegoDTO> getJuegos();
	
	public abstract List<JuegoDTO> getJuegosDeveloper(UsuarioDTO dev);
	
	public abstract JuegoDTO getJuegoID(String id);
	
}
