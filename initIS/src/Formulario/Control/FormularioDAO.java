package Formulario.Control;

import java.util.List;
import Formulario.Control.FormularioDTO;

public interface FormularioDAO {
	public abstract List<FormularioDTO>getFormularies();
	public abstract boolean insertFormulary(List<FormularioDTO> list, FormularioDTO newForm);
	
}
