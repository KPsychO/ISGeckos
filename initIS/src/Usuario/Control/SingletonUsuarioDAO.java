package Usuario.Control;

public class SingletonUsuarioDAO {

	private static UsuarioDAO _userDAO;
	
	private static void createInstance() {
		
		_userDAO= new UsuarioDAOJSON();
		
	}
	
	public static UsuarioDAO getInstance() {
		
		if(_userDAO == null) createInstance();
		
		return _userDAO;
		
	}

}