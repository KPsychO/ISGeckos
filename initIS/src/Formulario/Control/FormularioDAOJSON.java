package Formulario.Control;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class FormularioDAOJSON implements FormularioDAO{

	public JSONArray getFormularies() {
		
		JSONArray arr = new JSONArray();
		
		try {
			//InputStream input = new FileInputStream("./resources//Formularies.txt");
			InputStream input = new FileInputStream("./resources/Formularies.txt");
			arr = new JSONArray(new JSONTokener(input));
			
		} catch (FileNotFoundException e) {
			;
		}
		return arr;
	}

	@Override
	public void insertFormulary(FormularioDTO newForm, String type){
	
		JSONArray formularios = getFormularies();
		
		JSONObject obj = new JSONObject();
		
		for (Object o : formularios) {
			
			JSONObject form = new JSONObject(new JSONTokener(o.toString()));
			
			if (form.getString("_id").equals(newForm.get_id())) {
				obj = form;
			}
			
		}
		
		obj.put("_id", newForm.get_id());
		obj.put("_title", newForm.get_title());
		obj.put("_descShort", newForm.get_desc());
		obj.put("_price", newForm.get_price());
		obj.put("_pegi", newForm.get_pegi());
		obj.put("_date", newForm.get_date());
		obj.put("_descLong", newForm.get_descLong());
		obj.put("_genres", newForm.get_genres());
		obj.put("_achievements", newForm.get_achievements());
		obj.put("_developer", newForm.get_developer());
		obj.put("_type", type);
		
		formularios.put(obj);
		
		this.saveFormularios(formularios);

	}
	
	public void saveFormularios(JSONArray arr) {
		try (FileWriter file = new FileWriter("./resources/Formularies.txt")){
			//FileWriter file = new FileWriter("./resources//Formularies.txt");
			file.write(arr.toString(4));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteFormulary(int n) {
		JSONArray arr = new JSONArray();
		arr = getFormularies();
		arr.remove(n);
		
		this.saveFormularios(arr);
	}

	@Override
	public void insertGame(int n) {
		
		JSONArray newGames = new JSONArray();
		JSONArray formularios = new JSONArray();
		
		formularios = getFormularies();
		JSONObject formulario_actual = formularios.getJSONObject(n);
		int remove = -1;
		
		try {
			//InputStream input = new FileInputStream("./resources//Games.txt");
			InputStream input = new FileInputStream("./resources/Games.txt");
			newGames = new JSONArray(new JSONTokener(input));
			int i = 0;
			for (Object o : newGames) {
				
				JSONObject game = new JSONObject(new JSONTokener(o.toString()));
				
				if (game.getString("_id").equals(formulario_actual.getString("_id"))) {
					remove = i;
					break;
				}
				i++;
			}
			
			if (remove != -1)
				newGames.remove(remove);
			
			newGames.put(formulario_actual);
			
			try (FileWriter file = new FileWriter("./resources/Games.txt")){
				//FileWriter file = new FileWriter("./resources//Formularies.txt");
				file.write(newGames.toString(4));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e) {
			;
		}
	}

	@Override
	public void deleteFormularies(String id) {
		JSONArray arr = getFormularies();
		int i = 0;
		while (i < arr.length()) {
			
			JSONObject game = new JSONObject(new JSONTokener(arr.get(i).toString()));
			if (game.getString("_id").equals(id)) {
				arr.remove(i);
			}
			else
				i++;
		}
		
		this.saveFormularios(arr);

	}
}
