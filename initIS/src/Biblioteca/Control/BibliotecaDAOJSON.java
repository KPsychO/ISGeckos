package Biblioteca.Control;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import Juego.Control.JuegoDTO;
import Usuario.Control.UsuarioDTO;

public class BibliotecaDAOJSON implements BibliotecaDAO{

	public JSONArray getOwnedGamesJSON() {
		JSONArray arr = new JSONArray();
		try {
			InputStream input = new FileInputStream("./src/resources/Biblioteca.txt");
			arr = new JSONArray(new JSONTokener(input));
			
		} catch (FileNotFoundException e) {}
		return arr;
	}

	@Override
	public List<JuegoEnPropiedadDTO> getOwnedGames(String user_id) {
		//Todavia sin usuario
		List<JuegoEnPropiedadDTO> list = new ArrayList<JuegoEnPropiedadDTO>();
		
		JSONArray jsonInput = getOwnedGamesJSON();
		
		for (Object o : jsonInput) {
			JSONObject bib = new JSONObject(new JSONTokener(o.toString()));
			if (bib.getString("_userId").equals(user_id)) {
				JSONArray juegos = bib.getJSONArray("_gamesList");
				
				for (Object juego : juegos) {
					JSONObject prop = new JSONObject(new JSONTokener(juego.toString()));
					
					list.add(new JuegoEnPropiedadDTO(prop));
					
					
					
				}
				
				return list;
				
			}
		}

		
		return null;
	}

	
	public List<BibliotecaDTO> getLibraries(){
		List<BibliotecaDTO> libraries = new ArrayList<BibliotecaDTO>();
		
		try {
			InputStream input = new FileInputStream("./src/resources/Biblioteca.txt");
			JSONArray jsonInput = new JSONArray(new JSONTokener(input));
			
			for (Object o : jsonInput) {
				
				JSONObject library = new JSONObject(new JSONTokener(o.toString()));
				libraries.add(new BibliotecaDTO(library));
				
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return libraries;
	}
	
	public JSONArray getLibrariesJSON() {
		JSONArray arr = new JSONArray();
		try {
			InputStream input = new FileInputStream("./src/resources/Biblioteca.txt");
			arr = new JSONArray(new JSONTokener(input));
			
		} catch (FileNotFoundException e) {}
		return arr;
	}
	

	public void writeBiblJSON(BibliotecaDTO library, String userId) {
		
		JSONArray libraries = getLibrariesJSON();
		
		JSONObject obj = new JSONObject();
		
		for(Object o : libraries) {
			JSONObject lib = new JSONObject(new JSONTokener(o.toString()));
			if (lib.getString("_id").equals(library.get_userId())) {
				obj = lib;
			}
		}
		
		obj.put("_userId", library.get_userId());
		obj.put("_gamesList", library.juegosEnBibliotecaJSON());
		
		try (FileWriter file = new FileWriter("./src/resources/Biblioteca.txt")) {
			libraries.put(obj);
			file.write(libraries.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	@Override
	public List<JuegoEnPropiedadDTO> getOwnedGames(JSONArray arrayGames) {
		List<JuegoEnPropiedadDTO> games = new ArrayList<JuegoEnPropiedadDTO>();
		
		for(Object o : arrayGames) {
			JSONObject game = new JSONObject(new JSONTokener(o.toString()));
			games.add(new JuegoEnPropiedadDAOJSON().getLogros(game));
		}
		
		return games;
	}

	@Override
	public void writeBiblJSON(List<JuegoEnPropiedadDTO> gameList, String userId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actualizarBiblioteca(JuegoEnPropiedadDTO newGame, UsuarioDTO user) {
		JSONArray biblioteca = getOwnedGamesJSON();
		
		JSONObject bib_user = new JSONObject();
		
		int i = 0, j = 0;
		boolean fin = false;
		for(Object o : biblioteca) {
			bib_user = new JSONObject(new JSONTokener(o.toString()));
			
			if (bib_user.getString("_userId").equals(user.get_user_id())) {
				JSONArray juegos_biblioteca = bib_user.getJSONArray("_gamesList");
				
				for(Object juego : juegos_biblioteca) {
					
					JSONObject game = new JSONObject(new JSONTokener(juego.toString()));
					if (game.getString("_gameId").equals(newGame.get_id())) {
						fin = true;
						break;
						
					}
					j++;
				}
			}
			if (fin)
				break;
			i++;
			
		}
		
		bib_user = biblioteca.getJSONObject(i);
		JSONArray game_list = bib_user.getJSONArray("_gamesList");
		game_list.remove(j);
		
		JSONObject obj = new JSONObject();
		
		obj.put("_gameId", newGame.get_id());
		obj.put("_lastEx", newGame.get_lastEx());
		obj.put("_actVers", newGame.get_actVersion());
		obj.put("_hoursPlayed", newGame.get_hoursPlayed());
		obj.put("_installed", newGame.is_installed());
		
		game_list.put(obj);
		
		bib_user.put("_gamesList", game_list);
		
		biblioteca.remove(i);
		
		try (FileWriter file = new FileWriter("./src/resources/Biblioteca.txt")) {
			biblioteca.put(bib_user);
			file.write(biblioteca.toString(4));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insertarJuego(JuegoDTO newGame, UsuarioDTO user) {
		JSONArray biblioteca = getOwnedGamesJSON();
		
		JSONObject bib_user = new JSONObject();
		
		int i = 0;
		boolean fin = false;
		for(Object o : biblioteca) {
			bib_user = new JSONObject(new JSONTokener(o.toString()));
			
			if (bib_user.getString("_userId").equals(user.get_user_id())) {

				break;
			}

			i++;
			
		}
		JSONObject obj = new JSONObject();
		JSONArray game_list = new JSONArray();
		if (i < biblioteca.length()) {
			bib_user = biblioteca.getJSONObject(i);
			game_list = bib_user.getJSONArray("_gamesList");
		}else {
			bib_user.put("_userId", user.get_user_id());
		}
		
		
		obj.put("_gameId", newGame.get_id());
		obj.put("_lastEx", "Never");
		obj.put("_actVers", newGame.get_version());
		obj.put("_hoursPlayed", 0);
		obj.put("_installed", false);
		
		game_list.put(obj);
		
		bib_user.put("_gamesList", game_list);
		
		biblioteca.remove(i);
		
		try (FileWriter file = new FileWriter("./src/resources/Biblioteca.txt")) {
			biblioteca.put(bib_user);
			file.write(biblioteca.toString(4));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	


}
