package Tienda.Control;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import Juego.Control.JuegoDTO;
import Usuario.Control.UsuarioDTO;

public class TiendaDAOJSON implements TiendaDAO {
	
	private JSONArray getGames() {
		JSONArray jsonInput = null;
		try {
			InputStream input = new FileInputStream("./resources/Games.txt");
			jsonInput = new JSONArray(new JSONTokener(input));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return jsonInput;
	}

	@Override
	public List<JuegoDTO> getPublishedGames() {
		List<JuegoDTO> list = new ArrayList<JuegoDTO>();
		
		JSONArray jsonInput = this.getGames();
		for (Object o : jsonInput) {
			JSONObject oJ = new JSONObject(new JSONTokener(o.toString()));
			list.add(new JuegoDTO(oJ));
			
		}

		return list;
	}
	
	@Override
	public List<JuegoDTO> getPublishedGames(String genre) {

		List<JuegoDTO> list = new ArrayList<JuegoDTO>();
		
		JSONArray jsonInput = this.getGames();
		for (Object o : jsonInput) {
			JSONObject oJ = new JSONObject(new JSONTokener(o.toString()));
			for (Object jObject : oJ.getJSONArray("_genres")) {
				String k = jObject.toString();
				if (k.equals(genre)) {
					list.add(new JuegoDTO(oJ));
					break;
				}
			}
		}
			
		return list;
	}

	@Override
	public int getAccountBalance(String user_id) {
		
		int ret = 0;
		
		List<UsuarioDTO> list = new ArrayList<UsuarioDTO>();
		try {
			InputStream input = new FileInputStream("./resources//Users.txt");
			JSONArray jsonInput = new JSONArray(new JSONTokener(input));
			for (Object o : jsonInput) {
				
				JSONObject oJ = new JSONObject(new JSONTokener(o.toString()));
				list.add(new UsuarioDTO(oJ));
				
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		for (UsuarioDTO u : list) {
			
			if (u.get_user_id().equals(user_id))
				ret = u.get_balance();
			
		}
		
		return ret;
		
	}

}
