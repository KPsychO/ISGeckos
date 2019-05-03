package Usuario.Control;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

//import Usuario.Control.UsuarioDao;
//import Usuario.Control.UsuarioDTO;

public class UsuarioDAOJSON implements UsuarioDAO {

	public JSONArray getListUsuarios() {
		
		JSONArray list = new JSONArray();
		
		try {
			InputStream input = new FileInputStream("./src/resources/Usuarios.txt");
			list  = new JSONArray(new JSONTokener(input));
			
		} catch (FileNotFoundException e) {
		}
		return list;
	}
	
	@Override
	public void insertarUsuario(UsuarioDTO us) {
		
		JSONObject obj = new JSONObject();
		
		obj.put ("_tipoCuenta", us.getTipoCuenta()); 
		obj.put ("_balance", us.getBalance());
		obj.put ("_avatar", us.getAvatar()); 
		obj.put ("_descripcion", us.getDescripcion()); 
		obj.put ("_email", us.getEmail()); 
		obj.put ("_pais", us.getPais()); 
		obj.put ("_password", us.getPassword()); 
		obj.put ("_user_id", us.getUserId()); 
		obj.put ("_username", us.getUsername()); 
			
		JSONArray usu = getListUsuarios();
				
		try (FileWriter file = new FileWriter("./src/resources/Formularies.txt")) {
			usu.put(obj);
			file.write(usu.toString(4));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try (FileWriter file = new FileWriter("./src/resources/Usuarios.txt")) {
			file.write(usu.toString(4));
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
}
