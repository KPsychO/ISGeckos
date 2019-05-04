package Usuario.Control;

import java.util.List;

import org.json.JSONArray;

public interface UsuarioDAO {

	public abstract JSONArray getListUsuarios();
	
	public void insertarUsuario(UsuarioDTO us);
	
	public List<tipoCuenta> getTipos(JSONArray tiposArray);
	
	public UsuarioDTO login (String username, String password);
	
	public List<UsuarioDTO> lista();
	
}
