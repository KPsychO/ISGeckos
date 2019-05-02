package Formulario.Control;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import Juego.Control.JuegoDTO;

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

}
