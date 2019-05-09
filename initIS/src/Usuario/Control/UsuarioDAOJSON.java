package Usuario.Control;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

//import Usuario.Control.UsuarioDao;
//import Usuario.Control.UsuarioDTO;

public class UsuarioDAOJSON implements UsuarioDAO {
	List<UsuarioDTO> listUser;

	@SuppressWarnings("exports")
	public JSONArray getListUsuariosJson() {
		
		JSONArray list = new JSONArray();
		listUser = new ArrayList<UsuarioDTO>();
		try {
			
			InputStream input = new FileInputStream("./src/resources/Users.txt");
			list  = new JSONArray(new JSONTokener(input));
			for (Object o : list) {
				
				JSONObject oJ = new JSONObject(new JSONTokener(o.toString()));
				listUser.add(new UsuarioDTO(oJ));
			}
		} catch (FileNotFoundException e) {
		}
		return list;
	}
	
	@Override
	public void insertarUsuario(UsuarioDTO us) {
		
		JSONObject obj = new JSONObject();
		
		obj.put ("_user_id", us.get_user_id()); 
		obj.put ("_username", us.get_username()); 
		obj.put ("_types", us.get_types()); 
		obj.put ("_password", us.get_password()); 
		obj.put ("_email", us.get_email()); 
		obj.put ("_country", us.get_country()); 
		obj.put ("_balance", us.get_balance());
		obj.put ("_desc", us.get_desc()); 

		JSONArray usu = getListUsuariosJson();
		usu.put(obj);
		
		try (FileWriter file = new FileWriter("./src/resources/Users.txt")) {
			file.write(usu.toString(4));
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

	@SuppressWarnings("exports")
	@Override
	public List<tipoCuenta> getTipos(JSONArray tiposArray) {
		ArrayList<tipoCuenta> lista = new ArrayList<tipoCuenta>();
		
		for (Object o : tiposArray) {
			
			lista.add(tipoCuenta.valueOf(o.toString()));
			
		}
		
		return lista;
	}
	
	public UsuarioDTO login (String username, String password) {
		
		JSONArray users = getListUsuariosJson();
		
		for (Object o : users) {
			
			JSONObject user = new JSONObject(new JSONTokener(o.toString()));
			
			if (user.getString("_username").equals(username)) {
				//System.out.println("username iguales");
				
				if (user.getString("_password").equals(password)) {
					//System.out.println("password iguales");
					return new UsuarioDTO(user);
				}
				else
					return null;
			}
		}
		
		return null;
		
	}

	@Override
	public UsuarioDTO getUser(String username) {
		
		JSONArray users = getListUsuariosJson();
		
		for (Object o : users) {
			
			JSONObject user = new JSONObject(new JSONTokener(o.toString()));
			
			if (user.getString("_username").equals(username)) {
				
				return new UsuarioDTO(user);
				
			}
		}
		
		return null;
		
	}
	
	public List<UsuarioDTO> lista(){
		getListUsuariosJson();
		return listUser;
	}

	public UsuarioDTO getUnregUser() {

		JSONArray users = getListUsuariosJson();
		
		JSONObject user = users.getJSONObject(0);
		
		return new UsuarioDTO(user);
		
	}

	@Override
	public UsuarioDTO getUserID(String id) {
		JSONArray users = getListUsuariosJson();
		
		for (Object o : users) {
			
			JSONObject user = new JSONObject(new JSONTokener(o.toString()));
			
			if (user.getString("_user_id").equals(id)) {
				
				return new UsuarioDTO(user);
				
			}
		}
		
		return null;
	}
	
	public void deleteUser(UsuarioDTO dto) {
		
		JSONArray arr = new JSONArray();
		arr = this.getListUsuariosJson();
		
		int i = 0;
		for (Object o : arr) {
			JSONObject user = new JSONObject(new JSONTokener(o.toString()));
			if (user.getString("_user_id").equals(dto.get_user_id()))
				break;
			i++;
		}
		
		if (i < arr.length())
			arr.remove(i);
		
		try (FileWriter file = new FileWriter("./src/resources/Users.txt")) {
			file.write(arr.toString(4));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}




