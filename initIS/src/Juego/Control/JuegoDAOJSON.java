package Juego.Control;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
			InputStream input = new FileInputStream("./src/resources/Games.txt");
			JSONArray jsonInput = new JSONArray(new JSONTokener(input));
			
			for (Object o : jsonInput) {
				
				JSONObject juego = new JSONObject(new JSONTokener(o.toString()));
				
				if (juego.getString("_developer").equals(dev.get_user_id())) {
					lista.add(new JuegoDTO(juego));
				}
				
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return lista;
		
	}

	@Override
	public JuegoDTO getJuegoID(String id) {
		List<JuegoDTO> lista = getJuegos();
		
		for (JuegoDTO j : lista) {
			if (j.get_id().equals(id))
				return j;
		}
		
		return null;
		
	}

	public void eliminarJuego(String id) {
		
		InputStream input = null;
		try {
			input = new FileInputStream("./src/resources/Games.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JSONArray jsonInput = new JSONArray(new JSONTokener(input));
		int i = 0;
		for (Object o : jsonInput) {
				
			JSONObject juego = new JSONObject(new JSONTokener(o.toString()));
			
			if (juego.getString("_id").equals(id)) {
				break;
			}
			
			i++;
		}

		jsonInput.remove(i);
		
		try (FileWriter file = new FileWriter("./src/resources/Games.txt")) {
			file.write(jsonInput.toString(4));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	

}
