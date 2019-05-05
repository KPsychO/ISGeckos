package Usuario.Control;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import org.json.JSONArray;
import org.json.JSONObject;

public class UsuarioDTO {
	
	private List<tipoCuenta> _types;
	private List<UsuarioDTO> _users;
	private int _balance;
	//private ImageIcon _avatar;
	
	private String _user_id;
	private String _username;
	private String _password;
	private String _desc;
	private String _email;
	private String _country;
	
	private UsuarioDAO dao;
	
	public UsuarioDTO(
			List<tipoCuenta> types,
			int balance,
			//ImageIcon avatar,
			String desc,
			String email,
			String country,
			String password,
			String user_id,
			String username) {
		
		dao = new UsuarioDAOJSON();
		_types = types;
		_balance = balance;
		//_avatar = avatar;
		_desc = desc;
		_email = email;
		_country = country;
		_password = password;
		_user_id = user_id;
		_username = username;
		
	}
	
	public UsuarioDTO(JSONObject user) {
		
		dao = new UsuarioDAOJSON();
		_balance = user.getInt("_balance");
		//_avatar = avatar;
		_desc = user.getString("_desc");
		_email = user.getString("_email");
		_country = user.getString("_country");
		_password = user.getString("_password");
		_user_id = user.getString("_user_id");
		_username = user.getString("_username");
		_types = dao.getTipos(user.getJSONArray("_types"));
		
	}
	
	public UsuarioDTO(String usuario) {
		dao = new UsuarioDAOJSON();
		_users = dao.lista();
	}

	public void eliminarUsuario(UsuarioDTO us) {
		if(_users.contains(us)) {
			_users.remove(us);
		}
	}
	
	public boolean isDev() {
		return _types.contains(tipoCuenta.developer);
	}
	
	public boolean isAdmin() {
		return _types.contains(tipoCuenta.admin);
	}

	public List<tipoCuenta> get_types() {
		return _types;
	}

	public void set_types(List<tipoCuenta> _types) {
		this._types = _types;
	}

	public int get_balance() {
		return _balance;
	}

	public void set_balance(int _balance) {
		this._balance = _balance;
	}

	public String get_user_id() {
		return _user_id;
	}

	public void set_user_id(String _user_id) {
		this._user_id = _user_id;
	}

	public String get_username() {
		return _username;
	}

	public void set_username(String _username) {
		this._username = _username;
	}

	public String get_password() {
		return _password;
	}

	public void set_password(String _password) {
		this._password = _password;
	}

	public String get_desc() {
		return _desc;
	}

	public void set_desc(String _desc) {
		this._desc = _desc;
	}

	public String get_email() {
		return _email;
	}

	public void set_email(String _email) {
		this._email = _email;
	}

	public String get_country() {
		return _country;
	}

	public void set_country(String _country) {
		this._country = _country;
	}

	public List<UsuarioDTO> getUsers() {
		return _users;
	}

}
