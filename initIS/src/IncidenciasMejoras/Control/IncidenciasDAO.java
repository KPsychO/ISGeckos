package IncidenciasMejoras.Control;

import java.util.List;

public interface IncidenciasDAO {

	public abstract List<IncidenciasMejorasDTO>getListIncidencias();
	
	public void insertarIncidencia(IncidenciasMejorasDTO inciMej);
	
}
