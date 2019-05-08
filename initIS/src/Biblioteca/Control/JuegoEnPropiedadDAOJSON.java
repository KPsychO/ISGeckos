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
		return null;
	}

	public JuegoEnPropiedadDTO getLogros(JSONObject game) {
		
		return null;
	}
	
	public List<JuegoEnPropiedadDTO> getJuegos() {
		List<JuegoEnPropiedadDTO> games = new ArrayList<JuegoEnPropiedadDTO>();
		
		try {
			InputStream input = new FileInputStream("./src/resources/PruebaBibl.txt");
			JSONArray jsonInput = new JSONArray(new JSONTokener(input));
			
			for (Object o : jsonInput) {
				
				JSONObject juego = new JSONObject(new JSONTokener(o.toString()));
				games.add(new JuegoEnPropiedadDTO(juego));
				
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return games;
	}

}
