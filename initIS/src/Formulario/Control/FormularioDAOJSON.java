package Formulario.Control;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import Juego.Control.JuegoDTO;

public class FormularioDAOJSON implements FormularioDAO{

	public List<FormularioDTO> getFormularies() {
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
	}

	@Override
	public boolean insertFormulary(List<FormularioDTO> list, FormularioDTO newForm){
		
		
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

		
		return true;
	}

}
