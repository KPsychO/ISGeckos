package Biblioteca.Control;

public class SingletonBibliotecaDAO {
	
	private static BibliotecaDAO _bibliotecaDAO;;
	
	private static void createInstance() {
		
		_bibliotecaDAO = new BibliotecaDAOJSON();
		
	}
	
	public static BibliotecaDAO getInstance() {
		
		if(_bibliotecaDAO == null) createInstance();
		
		return _bibliotecaDAO;
		
	}

}
