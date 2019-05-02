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
		/*
		List<FormularioDTO> list = new ArrayList<FormularioDTO>();
		try {
			InputStream input = new FileInputStream("./src/resources/Formularies.txt");
			JSONArray jsonInput = new JSONArray(new JSONTokener(input));
			for (Object o : jsonInput) {
				
				JSONObject oJ = new JSONObject(new JSONTokener(o.toString()));
				
				String title = oJ.get("_title").toString();
				int price = Integer.valueOf(oJ.get("_price").toString());
				int pegi = Integer.valueOf(oJ.get("_pegi").toString());
				String desc = oJ.get("_desc").toString();
				
				list.add(new FormularioDTO(title, desc, pegi, price));
				
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return list;
		*/
		
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
		
		//Vamos a mejorar esto
		
		JSONObject obj = new JSONObject();
		
		obj.put("_id", newForm.get_id());
		obj.put("_title", newForm.get_title());
		obj.put("_descShort", newForm.get_desc());
		obj.put("_price", newForm.get_price());
		obj.put("_pegi", newForm.get_pegi());
		obj.put("_date", newForm.get_date());
		obj.put("_descLong", newForm.get_descLong());
		obj.put("_genres", newForm.get_genres());
		
		
		JSONArray formularios = getFormularies();
		formularios.put(obj);
		
		try (FileWriter file = new FileWriter("./src/resources/Formularies.txt")) {
			file.write(formularios.toString(4));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*
		boolean executed = false;
        try{
        	String aux = "";
        	OutputStream  output = new FileOutputStream("./src/resources/Formularies.txt");
        	output.write("[\r\n".getBytes());
    		
    		for(int i = 0; i < list.size(); i++){
    			aux = list.get(i).toString();// to string, estaria bastante bien la verdad
    			output.write(aux.getBytes());
    			output.write(" ,".getBytes());
    			output.write("\r\n".getBytes());
    		}
    		output.write(newForm.toString().getBytes());
    		output.write("]\r\n".getBytes());
		} catch (IOException e) {//No estoy seguro -> Revisar
			e.printStackTrace();
		}
		*/

	}

}
