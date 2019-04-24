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
			InputStream input = new FileInputStream("./src/resources/PublishedGames.txt");
			JSONArray jsonInput = new JSONArray(new JSONTokener(input));
			for (Object o : jsonInput) {
				
				JSONObject oJ = new JSONObject(new JSONTokener(o.toString()));
				String id = oJ.get("_id").toString();
				String title = oJ.get("_title").toString();
				int price = Integer.valueOf(oJ.get("_price").toString());
				int pegi = Integer.valueOf(oJ.get("_pegi").toString());
				String desc = oJ.get("_desc").toString();
				
				list.add(new JuegoDTO(id, title, price, pegi, desc));
				
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public List<JuegoDTO> getOwnedGames(String user_id) {	// A ver, deberias tener 21498279840 archivos de juegos y usuarios, pero pereaz
		
		List<JuegoDTO> list = new ArrayList<JuegoDTO>();
		try {
			InputStream input = new FileInputStream("./src/resources/OwnedGames.txt");
			JSONArray jsonInput = new JSONArray(new JSONTokener(input));
			for (Object o : jsonInput) {
				
				JSONObject oJ = new JSONObject(new JSONTokener(o.toString()));
				String id = oJ.get("_id").toString();
				String title = oJ.get("_title").toString();
				int price = Integer.valueOf(oJ.get("_price").toString());
				int pegi = Integer.valueOf(oJ.get("_pegi").toString());
				String desc = oJ.get("_desc").toString();
				
				list.add(new JuegoDTO(id, title, price, pegi, desc));
				
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
				String id = oJ.get("_id").toString();
				String username = oJ.get("_username").toString();
				int balance = Integer.valueOf(oJ.get("_balance").toString());
				
				list.add(new UsuarioDTO(id, username, balance));
				
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		for(UsuarioDTO u : list) {
			
			if (u.getId().equals(user_id))
				ret = u.getBalance();
			
		}
		
		return ret;
		
		
	}

}
