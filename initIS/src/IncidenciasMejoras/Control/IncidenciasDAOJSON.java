package IncidenciasMejoras.Control;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class IncidenciasDAOJSON implements IncidenciasDAO {
	private List<IncidenciasMejorasDTO> list = new ArrayList<IncidenciasMejorasDTO>();
	private String idUser;
	private String idUserDenun;
	private String desc;
	private String coment;
	private String idGame;
	@Override
	public List<IncidenciasMejorasDTO> getListIncidencias() {
		try {
			InputStream input = new FileInputStream("./src/resources/IncidenciasMejoras.txt");
			JSONArray jsonInput = new JSONArray(new JSONTokener(input));
			for (Object o : jsonInput) {
				
				JSONObject oJ = new JSONObject(new JSONTokener(o.toString()));
				String type = oJ.get("_type").toString();
				switch (type) {
				case "DenJug": 
					idUser = oJ.get("_id_user").toString();
					idUserDenun = oJ.get("_id_user_denun").toString();
					desc = oJ.get("_desc").toString();
					coment = oJ.get("_coment").toString();
					list.add(new IncidenciasMejorasDTO(type, idUser, idUserDenun, null, desc, coment));
					break;
				case "DenJue": 
					idGame = oJ.get("_id").toString();
					idUser = oJ.get("_id_user").toString();
					desc = oJ.get("_desc").toString();
					coment = oJ.get("_coment").toString();
					list.add(new IncidenciasMejorasDTO(type, idUser, null, idGame, desc, coment));
					break;
				case "IncJug":
					idUser = oJ.get("_id_user").toString();
					desc = oJ.get("_desc").toString();
					coment = oJ.get("_coment").toString();
					list.add(new IncidenciasMejorasDTO(type, idUser, null, null, desc, coment));
					break;
				case "IncJue": 
					idGame = oJ.get("_id").toString();
					idUser = oJ.get("_id_user").toString();
					desc = oJ.get("_desc").toString();
					coment = oJ.get("_coment").toString();
					list.add(new IncidenciasMejorasDTO(type, idUser, null, idGame, desc, coment));
					break;
				default: break;
				}
				
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public void insertarIncidencia(IncidenciasMejorasDTO inciMej) {
		try{
			OutputStream file = new FileOutputStream("./src/resources/IncidenciasMejoras.txt");
			PrintStream p = new PrintStream(file);
			p.println("[");
			for (int i = 0; i < list.size(); i++) {
				switch (list.get(i).get_type()) {
				case "DenJug":
					p.print("{ \n \"_type\": \"" + list.get(i).get_type() + "\",");
					p.print("\n \"_id_user\": " + "\"" + list.get(i).get_id_user() + "\"" + ",");
					p.print(" \n \"_id_user_denun\": " + "\"" + list.get(i).get_id_user_Denun() + "\"" + ",");
					p.print(" \n \"_desc\": " + "\": " + list.get(i).get_desc() + "\"" + ",");
					p.print(" \n \"_coment\": " + "\"" + list.get(i).get_coment() +"\","  + "\n }, \n");
					break;
				case "DenJue": 
					p.print("{ \n \"_type\": \"" + list.get(i).get_type() + "\",");
					p.print("\n \"_id_user\": " + "\"" + list.get(i).get_id_user() + "\"" + ",");
					p.print(" \n \"_id\": " + "\"" + list.get(i).get_id_game() + "\"" + ",");
					p.print(" \n \"_desc\": " + "\": " + list.get(i).get_desc() + "\"" + ",");
					p.print(" \n \"_coment\": " + "\"" + list.get(i).get_coment() +"\","  + "\n }, \n");
					break;
				case "IncJug":
					p.print("{ \n \"_type\": \"" + list.get(i).get_type() + "\",");
					p.print("\n \"_id_user\": " + "\"" + list.get(i).get_id_user() + "\"" + ",");
					p.print(" \n \"_desc\": " + "\": " + list.get(i).get_desc() + "\"" + ",");
					p.print(" \n \"_coment\": " + "\"" + list.get(i).get_coment() +"\","  + "\n }, \n");
					break;
				case "IncJue": 
					p.print("{ \n \"_type\": \"" + list.get(i).get_type() + "\",");
					p.print("\n \"_id_user\": " + "\"" + list.get(i).get_id_user() + "\"" + ",");
					p.print("\n \"_id\": " + "\"" + list.get(i).get_id_game() + "\"" + ",");
					p.print(" \n \"_desc\": " + "\": " + list.get(i).get_desc() + "\"" + ",");
					p.print(" \n \"_coment\": " + "\"" + list.get(i).get_coment() +"\","  + "\n }, \n");
					break;
				default: break;
				}
			}
			
			switch (inciMej.get_type()) {
			case "DenJug":
				p.print("{ \n \"_type\": \"" + inciMej.get_type() + "\",");
				p.print("\n \"_id_user\": " + "\"" + inciMej.get_id_user() + "\"" + ",");
				p.print("\n \"_id_user_denun\": " + "\"" + inciMej.get_id_user_Denun() + "\"" + ",");
				p.print("\n \"_desc\": " + "\"" + inciMej.get_desc() + "\"" + ",");
				p.print("\n \"_coment\": " + inciMej.get_coment() +"\"," + "\n } \n");
				break;
			case "DenJue": 
				p.print("{ \n \"_type\": \"" + inciMej.get_type() + "\",");
				p.print("\n \"_id_user\": " + "\"" + inciMej.get_id_user() + "\"" + ",");
				p.print("\n \"_id\": " + "\"" + inciMej.get_id_game() + "\"" + ",");
				p.print("\n \"_desc\": " + "\"" + inciMej.get_desc() + "\"" + ",");
				p.print("\n \"_coment\": " + inciMej.get_coment() +"\"," + "\n } \n");
				break;
			case "IncJug":
				p.print("{ \n \"_type\": \"" + inciMej.get_type() + "\",");
				p.print("\n \"_id_user\": " + "\"" + inciMej.get_id_user() + "\"" + ",");
				p.print(" \n \"_desc\": " + "\": " + inciMej.get_desc() + "\"" + ",");
				p.print(" \n \"_coment\": " + "\"" + inciMej.get_coment() +"\","  + "\n }, \n");
				break;
			case "IncJue": 
				p.print("{ \n \"_type\": \"" + inciMej.get_type() + "\",");
				p.print("\n \"_id_user\": " + "\"" + inciMej.get_id_user() + "\"" + ",");
				p.print("\n \"_id\": " + "\"" + inciMej.get_id_game() + "\"" + ",");
				p.print(" \n \"_desc\": " + "\": " + inciMej.get_desc() + "\"" + ",");
				p.print(" \n \"_coment\": " + "\"" + inciMej.get_coment() +"\","  + "\n }, \n");
				break;
			default: break;
			}
			p.println("]");
			
		}

		catch (IOException e){
			e.printStackTrace();
		}
	}

}
