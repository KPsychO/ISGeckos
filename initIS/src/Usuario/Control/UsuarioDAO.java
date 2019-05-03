package Usuario.Control;

import org.json.JSONArray;

public interface UsuarioDAO {

	public abstract JSONArray getListUsuarios();
	public void insertarUsuario(UsuarioDTO us);
	
}
