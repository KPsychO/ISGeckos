package Formulario.Control;

import org.json.JSONArray;

public interface FormularioDAO {
	
	public abstract JSONArray getFormularies();
	
	//Por que esto era un boolean?
	public abstract void insertFormulary(FormularioDTO newForm, String type);
	public abstract void deleteFormulary(int n);
	public abstract void insertGame(int n);
	
}
