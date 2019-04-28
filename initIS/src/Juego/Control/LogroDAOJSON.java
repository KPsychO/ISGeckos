package Juego.Control;

import org.json.JSONObject;

public class LogroDAOJSON implements LogroDAO {

	@Override
	public LogroDTO getLogro(@SuppressWarnings("exports") JSONObject logro) {
		
		String name = logro.getString("_name");
		String obtencion = logro.getString("_getMode");
		
		LogroDTO dto = new LogroDTO(name, obtencion);
		
		return dto;
	}

}
