package Juego.Control;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import Usuario.Control.UsuarioDTO;

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

	@Override
	public List<JuegoDTO> getJuegos() {
		
		List<JuegoDTO> list = new ArrayList<JuegoDTO>();
		try {
			InputStream input = new FileInputStream("./src/resources/Games.txt");
			JSONArray jsonInput = new JSONArray(new JSONTokener(input));
			
			for (Object o : jsonInput) {
				
				JSONObject juego = new JSONObject(new JSONTokener(o.toString()));
				list.add(new JuegoDTO(juego));
				
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return list;
		
	}
	
	public List<JuegoDTO> getJuegosDeveloper(UsuarioDTO dev){
		
		List<JuegoDTO> lista = new ArrayList<JuegoDTO>();

		try {
			InputStream input = new FileInputStream("./src/resources/DeveloperGames.txt");
			JSONArray jsonInput = new JSONArray(new JSONTokener(input));
			
			for (Object o : jsonInput) {
				
				JSONObject juego = new JSONObject(new JSONTokener(o.toString()));
				lista.add(new JuegoDTO(juego));
				
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return lista;
		
	}

}
