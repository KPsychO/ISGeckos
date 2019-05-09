package Formulario.Control;

public class SingletonFormularioDAO {
	
	private static FormularioDAO _formDAO;
	
	private static void createInstance() {
		
		_formDAO = new FormularioDAOJSON();
		
	}
	
	public static FormularioDAO getInstance() {
		
		if(_formDAO == null) createInstance();
		
		return _formDAO;
		
	}

}