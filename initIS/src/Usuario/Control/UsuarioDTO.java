package Usuario.Control;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class UsuarioDTO {
	
	private enum __tipoCuenta {user, developer, admin};
	private __tipoCuenta _tipoCuenta;
	private int _balance;
	private ImageIcon _avatar;
	private String _descripcion;
	private String _email;
	private String _pais;
	private String _password;
	private String _user_id;
	private String _username;

	private List<UsuarioDTO> users = new ArrayList<UsuarioDTO>();
	
	public UsuarioDTO(
			__tipoCuenta tipoCuenta,
			int balance,
			ImageIcon avatar,
			String descripcion,
			String email,
			String pais,
			String password,
			String user_id,
			String username) {
		
		_tipoCuenta = tipoCuenta;
		_balance = balance;
		_avatar = avatar;
		_descripcion = descripcion;
		_email = email;
		_pais = pais;
		_password = password;
		_user_id = user_id;
		_username = username;
		
	}
	
	public void eliminarUsuario(UsuarioDTO us) {
		if(users.contains(us)) {
			users.remove(us);
		}
	}
	
	public __tipoCuenta getTipoCuenta() {
		return _tipoCuenta;
	}
	public void setTipoCuenta(__tipoCuenta tipoCuenta) {
		_tipoCuenta = tipoCuenta;
	}
	
	
	public int getBalance() {	
		return _balance;
	}
	public void setBalance (int balance) {
		_balance = balance;
	}
	
	public ImageIcon getAvatar() {
		return _avatar;
	}
	public void setAvatar(ImageIcon avatar) {
		_avatar = avatar;
	}
	
	public String getDescripcion() {
		return _descripcion;
	}
	public void setDescripcion(String descripcion) {
		_descripcion = descripcion;
	}
	
	public String getEmail() {
		return _email;
	}
	public void setEmail (String email) {
		_email = email;
	}
	
	public String getPais() {
		return _pais;
	}
	public void setPais (String pais) {
		_pais = pais;
	}
	
	public String getPassword() {
		return _password;
	}
	public void setPassword(String password) {
		_password = password;
	}
	
	public String getUserId() {
		return _user_id;
	}
	public void setUserId(String user_id) {
		_user_id = user_id;
	}
	
	public String getUsername() {
		return _username;
	}
	public void setUserName (String username) {
		_username = username;
	}


}
