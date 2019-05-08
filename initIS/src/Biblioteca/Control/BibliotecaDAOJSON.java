package Biblioteca.Control;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class BibliotecaDAOJSON implements BibliotecaDAO{

	
	// Lista de juegos de la biblioteca gen�rica
	@Override
	public List<JuegoEnPropiedadDTO> getOwnedGames() {
		List<JuegoEnPropiedadDTO> list = new ArrayList<JuegoEnPropiedadDTO>();
		
		try {
			InputStream input = new FileInputStream("./src/resources/PruebaBibl.txt");
			JSONArray jsonInput = new JSONArray(new JSONTokener(input));
			
			for (Object o : jsonInput) {
				JSONObject oJ = new JSONObject(new JSONTokener(o.toString()));
				list.add(new JuegoEnPropiedadDTO(oJ));				
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return list;
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
	
	@SuppressWarnings("resource")
	@Override
	public void writeBiblJSON(List<JuegoEnPropiedadDTO> gameList, String userId) {
		try {
			OutputStream output = new FileOutputStream("./src/resources/BiblOutPrueba.txt");
			JSONArray jsonOutput = new JSONArray();
			for(JuegoEnPropiedadDTO i: gameList) {
				jsonOutput.put(i.JuegoEnPropiedadToJSON());
			}
			JSONObject out = new JSONObject();
			out.put("", jsonOutput);
			PrintStream p = new PrintStream(output);
			p.println(jsonOutput.toString());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Error de fichero salida");
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
	public List<JuegoEnPropiedadDTO> getOwnedGames(String user_id) {
		//Todavia sin usuario
		List<JuegoEnPropiedadDTO> list = new ArrayList<JuegoEnPropiedadDTO>();
		
		try {
			InputStream input = new FileInputStream("./src/resources/PruebaBibl.txt");
			JSONArray jsonInput = new JSONArray(new JSONTokener(input));
			
			for (Object o : jsonInput) {
				JSONObject oJ = new JSONObject(new JSONTokener(o.toString()));
				list.add(new JuegoEnPropiedadDTO(oJ));				
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return list;
	}


}
