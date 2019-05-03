package IncidenciasMejoras.Control;

import org.json.JSONArray;

public interface IncidenciasDAO {

	public abstract JSONArray getListIncidencias();
	
	public void insertarIncidencia(IncidenciasMejorasDTO inciMej);
	
}
