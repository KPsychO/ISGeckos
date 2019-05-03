package Formulario.Control;

import org.json.JSONArray;

public interface FormularioDAO {
	
	public abstract JSONArray getFormularies();
	
	//Por que esto era un boolean?
	public abstract void insertFormulary(FormularioDTO newForm);
	
}
