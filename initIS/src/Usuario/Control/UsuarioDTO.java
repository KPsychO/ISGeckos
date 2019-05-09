package Usuario.Control;

import java.util.List;

import org.json.JSONObject;

public class UsuarioDTO {
	
	private List<tipoCuenta> _types;
	private List<UsuarioDTO> _users;
	private int _balance;
	private String _avatarPath;
	
	private String _user_id;
	private String _username;
	private String _password;
	private String _desc;
	private String _email;
	private String _country;
	
	private UsuarioDAO _dao;
	
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
		
		_dao = new UsuarioDAOJSON();
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
	
	@SuppressWarnings("exports")
	public UsuarioDTO(JSONObject user) {
		
		_dao = new UsuarioDAOJSON();
		_balance = user.getInt("_balance");
		//_avatar = avatar;
		_desc = user.getString("_desc");
		_email = user.getString("_email");
		_country = user.getString("_country");
		_password = user.getString("_password");
		_user_id = user.getString("_user_id");
		_username = user.getString("_username");
		_types = _dao.getTipos(user.getJSONArray("_types"));
		
	}
	
	public UsuarioDTO(String id) {
		_dao = new UsuarioDAOJSON();
		UsuarioDTO newUser = _dao.getUserID(id);
		
		_types = newUser.get_types();
		_balance = newUser.get_balance();
		//_avatar = avatar;
		_desc = newUser.get_desc();
		_email = newUser.get_email();
		_country = newUser.get_country();
		_password = newUser.get_password();
		_user_id = newUser.get_user_id();
		_username = newUser.get_username();
	}

	public void eliminarUsuario(UsuarioDTO us) {
		if(_users.contains(us)) {
			_users.remove(us);
		}
		
	}
	
	public UsuarioDTO getUsuarioID(String ID) {
		_users = _dao.lista();
		for (UsuarioDTO dto : _users) {
			if (dto.get_user_id().equals(ID))
				return dto;
		}
		return null;
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

	public String getAvatarPath () {
		return _avatarPath;
	}
	
	public void setAvatarPath (String avatarPath) {
		this._avatarPath = avatarPath;
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
	
	public boolean isUnregistered() {
		return this._types.contains(tipoCuenta.unregistered);		
	}

	

}
