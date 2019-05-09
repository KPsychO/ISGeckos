package Tienda.Control;

public class SingletonTiendaDAO {
	
	private static TiendaDAO _tiendaDAO;
	
	private static void createInstance() {
		
		_tiendaDAO = new TiendaDAOJSON();
		
	}
	
	public static TiendaDAO getInstance() {
		
		if(_tiendaDAO == null) createInstance();
		
		return _tiendaDAO;
		
	}

}
