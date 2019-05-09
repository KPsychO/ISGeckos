package IncidenciasMejoras.Control;

import IncidenciasMejoras.Control.IncidenciasDAO;
import IncidenciasMejoras.Control.IncidenciasDAOJSON;

public class SingletonIncidenciasDAO {
	
	private static IncidenciasDAO _incidenciasDAO;
	
	private static void createInstance() {
		
		_incidenciasDAO = new IncidenciasDAOJSON();
		
	}
	
	public static IncidenciasDAO getInstance() {
		
		if(_incidenciasDAO == null) createInstance();
		
		return _incidenciasDAO;
		
	}

}
