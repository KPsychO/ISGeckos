package Juego.Control;

import org.json.JSONObject;

public class LogroDAOJSON implements LogroDAO {

	@Override
	public LogroDTO getLogro(@SuppressWarnings("exports") JSONObject logro) {
		
		String id = logro.getString("_id");
		String name = logro.getString("_name");
		String obtencion = logro.getString("_obtencion");
		
		LogroDTO dto = new LogroDTO(id, name, obtencion);
		
		return dto;
	}

}
