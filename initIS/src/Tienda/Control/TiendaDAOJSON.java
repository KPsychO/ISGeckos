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

	@Override
	public List<JuegoDTO> getPublishedGames() {
		List<JuegoDTO> list = new ArrayList<JuegoDTO>();
		try {
			InputStream input = new FileInputStream("./src/resources/Games.txt");
			JSONArray jsonInput = new JSONArray(new JSONTokener(input));
			for (Object o : jsonInput) {
				JSONObject oJ = new JSONObject(new JSONTokener(o.toString()));
				list.add(new JuegoDTO(oJ));
				
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public List<JuegoDTO> getOwnedGames(String user_id) {
		
		List<JuegoDTO> list = new ArrayList<JuegoDTO>();
		try {
			InputStream input = new FileInputStream("./src/resources/OwnedGames.txt");
			JSONArray jsonInput = new JSONArray(new JSONTokener(input));
			for (Object o : jsonInput) {
				JSONObject oJ = new JSONObject(new JSONTokener(o.toString()));
				Object aux = oJ.get(user_id);
				JSONArray items = new JSONArray(new JSONTokener(aux.toString()));
				
				for(Object i : items) {
					
					list.add(new JuegoDTO(i.toString()));
					
				}
				
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return list;
		
	}

	@Override
	public int getAccountBalance(String user_id) {
		
		int ret = 0;
		
		List<UsuarioDTO> list = new ArrayList<UsuarioDTO>();
		try {
			InputStream input = new FileInputStream("./src/resources/Users.txt");
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
