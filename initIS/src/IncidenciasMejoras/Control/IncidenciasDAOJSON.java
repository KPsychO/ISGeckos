package IncidenciasMejoras.Control;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;


import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class IncidenciasDAOJSON implements IncidenciasDAO {

	private JSONArray list;
	@Override
	public JSONArray getListIncidencias() {
		
		list = new JSONArray();
		
		try {
			//InputStream input = new FileInputStream("./resources//IncidenciasMejoras.txt");
			InputStream input = new FileInputStream("./resources/IncidenciasMejoras.txt");
			list  = new JSONArray(new JSONTokener(input));
			
		} catch (FileNotFoundException e) {

		}
		return list;
	}
	
	public void insertarIncidencia(IncidenciasMejorasDTO inciMej) {
		JSONObject obj = new JSONObject();
		
		switch (inciMej.get_type()) {
		case "DenJug":
			obj.put("_type", inciMej.get_type());
			obj.put("_username", inciMej.get_username());
			obj.put("_id_user", inciMej.get_id_user());
			obj.put("_id_user_denun", inciMej.get_id_user_Denun());
			obj.put("_desc", inciMej.get_desc());
			obj.put("_coment", inciMej.get_coment());
			break;
		case "DenJue": 
			obj.put("_type", inciMej.get_type());
			obj.put("_username", inciMej.get_username());
			obj.put("_id_user", inciMej.get_id_user());
			obj.put("_game", inciMej.get_game());
			obj.put("_id", inciMej.get_id_game());
			obj.put("_desc", inciMej.get_desc());
			obj.put("_coment", inciMej.get_coment());
			break;
		case "IncJug":
			obj.put("_type", inciMej.get_type());
			obj.put("_username", inciMej.get_username());
			obj.put("_id_user", inciMej.get_id_user());
			obj.put("_desc", inciMej.get_desc());
			obj.put("_coment", inciMej.get_coment());
			break;
		case "IncJue": 
			obj.put("_type", inciMej.get_type());
			obj.put("_username", inciMej.get_username());
			obj.put("_id_user", inciMej.get_id_user());
			obj.put("_game", inciMej.get_game());
			obj.put("_id", inciMej.get_id_game());
			obj.put("_desc", inciMej.get_desc());
			obj.put("_coment", inciMej.get_coment());
			break;
		default: break;
		}
		
		JSONArray incMej = getListIncidencias();
		incMej.put(obj);
		
		try (FileWriter file = new FileWriter("./resources/IncidenciasMejoras.txt")){
			//FileWriter file = new FileWriter("./resources//IncidenciasMejoras.txt");
			file.write(incMej.toString(4));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void borrarIncidencia(int n) {
		JSONArray arr = new JSONArray();
		arr = getListIncidencias();
		arr.remove(n);
		
		try (FileWriter file = new FileWriter("./resources/IncidenciasMejoras.txt")){
			//FileWriter file = new FileWriter("./resources//IncidenciasMejoras.txt");
			file.write(arr.toString(4));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}