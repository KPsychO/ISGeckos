package Formulario.Control;

import org.json.JSONArray;

public interface FormularioDAO {
	
	public abstract JSONArray getFormularies();
	public abstract void insertFormulary(FormularioDTO newForm);
	
}
