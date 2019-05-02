package Juego.Control;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class JuegoDAOJSON implements JuegoDAO{

	@Override
	//Necesitamos un JSON con todos los logros, con el id del juego al que pertenecen
	public List<LogroDTO> getLogros(String _id) {
		ArrayList<LogroDTO> logros = new ArrayList<LogroDTO>();
		InputStream input;
		try {
			input = new FileInputStream("./src/resources/PublishedGames.txt");
			JSONArray jsonInput = new JSONArray(new JSONTokener(input));
			
			for (Object o : jsonInput) {
				
				JSONObject logro = new JSONObject(new JSONTokener(o.toString()));
				
				//Comprobamos si el logro es del juego actual
				if (logro.getString("_id").equals(_id)) {
					LogroDAOJSON ldj = new LogroDAOJSON();
					logros.add(ldj.getLogro(logro));
				}
				
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return logros;
	}
	

}
