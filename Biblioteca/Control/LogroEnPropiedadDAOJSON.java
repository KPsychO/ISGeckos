package Biblioteca.Control;

import org.json.JSONObject;

public class LogroEnPropiedadDAOJSON implements LogroEnPropiedadDAO{

	@Override
	public LogroEnPropiedadDTO getLogroEnPropiedad(JSONObject logro) {
		
		LogroEnPropiedadDTO dto;
		
		String _id = logro.getString("_achId");
		boolean _owned = logro.getBoolean("_owned");
		
		dto = new LogroEnPropiedadDTO(_id, _owned);
		
		return dto;
	}

}
