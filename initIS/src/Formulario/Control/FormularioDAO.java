package Formulario.Control;

import java.util.List;

import org.json.JSONArray;

import Formulario.Control.FormularioDTO;

public interface FormularioDAO {
	
	public abstract JSONArray getFormularies();
	
	//Por que esto era un boolean?
	public abstract void insertFormulary(FormularioDTO newForm);
	
}
