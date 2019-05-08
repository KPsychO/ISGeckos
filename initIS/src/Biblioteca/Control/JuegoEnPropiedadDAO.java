package Biblioteca.Control;

import java.util.List;

import org.json.JSONObject;

public interface JuegoEnPropiedadDAO {

	public JSONObject JuegoEnPropiedadToJSON(JuegoEnPropiedadDTO game);

	public List<JuegoEnPropiedadDTO> getJuegos();
	
}
