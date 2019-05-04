package Juego.Control;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class JuegoDAOJSON implements JuegoDAO{

	@SuppressWarnings("exports")
	@Override
	public List<LogroDTO> getLogros(JSONArray arrayLogros) {
		ArrayList<LogroDTO> logros = new ArrayList<LogroDTO>();
		
		for (Object o : arrayLogros) {

			JSONObject logro = new JSONObject(new JSONTokener(o.toString()));
			logros.add(new LogroDAOJSON().getLogro(logro));
			
		}
		
		return logros;
	}

	@Override
	public List<String> getGenres(@SuppressWarnings("exports") JSONArray arrayGenres) {
		ArrayList<String> genres = new ArrayList<String>();
		
		for (Object o : arrayGenres) {

			genres.add(o.toString());
			
		}
		
		return genres;
	}
	

}
