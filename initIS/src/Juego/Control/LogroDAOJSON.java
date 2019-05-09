package Juego.Control;

import java.util.List;

import org.json.JSONObject;

public class LogroDAOJSON implements LogroDAO {

	@Override
	public LogroDTO getLogro(@SuppressWarnings("exports") JSONObject logro) {
		
		String id = logro.getString("_id");
		String name = logro.getString("_name");
		String obtencion = logro.getString("_getMode");
		
		LogroDTO dto = new LogroDTO(name, obtencion, id);
		
		return dto;
	}

	@Override
	public LogroDTO getLogroID(List<LogroDTO> lista, String id) {
		for (LogroDTO l : lista) {
			if (l.get_id().equals(id)) {
				return l;
			}
		}
		return null;
	}
	

}
