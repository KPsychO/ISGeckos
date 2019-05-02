package IncidenciasMejoras.Control;

import java.util.List;

import org.json.JSONArray;

public interface IncidenciasDAO {

	public abstract JSONArray getListIncidencias();
	
	public void insertarIncidencia(IncidenciasMejorasDTO inciMej);
	
}
