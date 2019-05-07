package Usuario.Control;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

//import Usuario.Control.UsuarioDao;
//import Usuario.Control.UsuarioDTO;

public class UsuarioDAOJSON implements UsuarioDAO {
	List<UsuarioDTO> listUser;

	@SuppressWarnings("exports")
	public JSONArray getListUsuarios() {
		
		JSONArray list = new JSONArray();
		listUser= new ArrayList<UsuarioDTO>();
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
		
		obj.put ("_tipoCuenta", us.get_types()); 
		obj.put ("_balance", us.get_balance());
		//obj.put ("_avatar", us.get_avatar()); 
		obj.put ("_descripcion", us.get_desc()); 
		obj.put ("_email", us.get_email()); 
		obj.put ("_pais", us.get_country()); 
		obj.put ("_password", us.get_password()); 
		obj.put ("_user_id", us.get_user_id()); 
		obj.put ("_username", us.get_username()); 
			
		JSONArray usu = getListUsuarios();
		usu.put(obj);
		
		try (FileWriter file = new FileWriter("./src/resources/Usuarios.txt")) {
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
	
	public UsuarioDTO login(String username, String password) {
		
		JSONArray users = getListUsuarios();
		
		for (Object o : users) {
			
			JSONObject user = new JSONObject(new JSONTokener(o.toString()));
			
			if (user.getString("_username").equals(username)) {
				//System.out.println("username iguales");
				
				if (user.getString("_password").equals(password)) {
					//System.out.println("password iguales");
					return new UsuarioDTO(user);
				}
				else {
					String error = "Contrasena incorrecta";
					JOptionPane.showMessageDialog(null, error, "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		
		return null;
		
	}

	@Override
	public UsuarioDTO getUser(String username) {
		
		JSONArray users = getListUsuarios();
		
		for (Object o : users) {
			
			JSONObject user = new JSONObject(new JSONTokener(o.toString()));
			
			if (user.getString("_username").equals(username)) {
				
				return new UsuarioDTO(user);
				
			}
		}
		
		return null;
		
	}
	
	public List<UsuarioDTO> lista(){
		getListUsuarios();
		return listUser;
	}

	public UsuarioDTO getUnregUser() {

		JSONArray users = getListUsuarios();
		
		JSONObject user = users.getJSONObject(0);
		
		return new UsuarioDTO(user);
		
	}
}




