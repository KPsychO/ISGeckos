package Usuario.Control;

public class UsuarioDTO {
	
	String _user_id;
	String _username;
	int _balance;
	// toda tu mierda
	
	public UsuarioDTO(String id, String username, int balance) {
		
		_user_id = id;
		_username = username;
		_balance = balance;
		
	}
	
	public String getId() {
		
		return _user_id;
		
	}
	
	public int getBalance() {
		
		return _balance;
		
	}

}
