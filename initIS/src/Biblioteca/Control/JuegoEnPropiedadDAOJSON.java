package Biblioteca.Control;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class JuegoEnPropiedadDAOJSON implements JuegoEnPropiedadDAO{
	/*
	public JuegoEnPropiedadDTO getJuego(JSONObject juego) {
		String id = juego.getString("_gameId");
		
	}
	*/
	public JSONObject JuegoEnPropiedadToJSON(JuegoEnPropiedadDTO game) {
		JSONObject gameJSON = new JSONObject();
		gameJSON.put("_gameId", game.get_id());
		gameJSON.put("_hoursPlayed", game.get_hoursPlayed());
		gameJSON.put("_lastEx", game.get_lastEx());
		gameJSON.put("_installed", game.is_installed());
		gameJSON.put("_actVers", game.get_actVersion());
		
		return gameJSON;
	}

	public JuegoEnPropiedadDTO getLogros(JSONObject game) {
		
		return null;
	}

	@Override
	public List<JuegoEnPropiedadDTO> getJuegosID(String id) {
		JSONArray jsonInput = new JSONArray();
		List<JuegoEnPropiedadDTO> list = new ArrayList<JuegoEnPropiedadDTO>();
		try {
			InputStream input = new FileInputStream("./src/resources/Biblioteca.txt");
			jsonInput = new JSONArray(new JSONTokener(input));
			
			
			for (Object o : jsonInput) {
				JSONObject bib = new JSONObject(new JSONTokener(o.toString()));
				if (bib.getString("_userId").equals(id)) {
					JSONArray juegos = bib.getJSONArray("_gamesList");
					
					for (Object juego : juegos) {
						JSONObject prop = new JSONObject(new JSONTokener(juego.toString()));
						
						list.add(new JuegoEnPropiedadDTO(prop));
						
					}
					
					return list;
					
				}
			}

			
		} catch (FileNotFoundException e) {
			
		}
		
		return null;
	}

}
