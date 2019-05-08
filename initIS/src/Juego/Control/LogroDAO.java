package Juego.Control;

import java.util.List;

import org.json.JSONObject;

public interface LogroDAO{

	public abstract LogroDTO getLogro(@SuppressWarnings("exports") JSONObject logro);
	public abstract LogroDTO getLogroID(List<LogroDTO> lista, String id);
}
