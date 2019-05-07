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
			InputStream input = new FileInputStream("./src/resources/Formularies.txt");
			arr = new JSONArray(new JSONTokener(input));
			
		} catch (FileNotFoundException e) {
			;
		}
		return arr;
	}

	@Override
	public void insertFormulary(FormularioDTO newForm){
		
		JSONObject obj = new JSONObject();
		
		obj.put("_id", newForm.get_id());
		obj.put("_title", newForm.get_title());
		obj.put("_descShort", newForm.get_desc());
		obj.put("_price", newForm.get_price());
		obj.put("_pegi", newForm.get_pegi());
		obj.put("_date", newForm.get_date());
		obj.put("_descLong", newForm.get_descLong());
		obj.put("_genres", newForm.get_genres());
		obj.put("_achievements", newForm.get_achievements());
		
		JSONArray formularios = getFormularies();
		
		try (FileWriter file = new FileWriter("./src/resources/Formularies.txt")) {
			formularios.put(obj);
			file.write(formularios.toString(4));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void deleteFormulary(int n) {
		JSONArray arr = new JSONArray();
		arr = getFormularies();
		arr.remove(n);
		
		try (FileWriter file = new FileWriter("./src/resources/Formularies.txt")) {
			file.write(arr.toString(4));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insertGame(int n) {
		
		JSONArray arrJ = new JSONArray();
		JSONArray arrF = new JSONArray();
		
		try {
			InputStream input = new FileInputStream("./src/resources/NewGames.txt");
			arrJ = new JSONArray(new JSONTokener(input));
			
			arrF = getFormularies();
			arrJ.put(arrF.get(n));
			//
			try (FileWriter file = new FileWriter("./src/resources/NewGames.txt")) {
				file.write(arrJ.toString(4));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e) {
			;
		}
	}
}
