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

	@Override
	public List<JuegoEnPropiedadDTO> getOwnedGames(String user_id) {
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


}
