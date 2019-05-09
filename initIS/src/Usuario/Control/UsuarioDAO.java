package Usuario.Control;

import java.util.List;

import org.json.JSONArray;

public interface UsuarioDAO {

	@SuppressWarnings("exports")
	public abstract JSONArray getListUsuariosJson();
	
	public void insertarUsuario(UsuarioDTO us);
	
	@SuppressWarnings("exports")
	public List<tipoCuenta> getTipos(JSONArray tiposArray);
	
	public UsuarioDTO login (String username, String password);
	
	public UsuarioDTO getUser(String username);
	
	public List<UsuarioDTO> lista();
	
	public UsuarioDTO getUnregUser();

	public abstract UsuarioDTO getUserID(String id);
	
	public abstract void deleteUser(UsuarioDTO dto);
}
