package Formulario.Control;

import common.Controller;

import javax.swing.JPanel;

import Formulario.View.MainViewFormulario;
import Formulario.View.MainViewPublicacion;
import Usuario.Control.UsuarioDTO;


public class FormularioController extends Controller{

	@Override
	public JPanel getPanel(String panel, Object o, UsuarioDTO user) {
		if (panel.equals("Formulario"))
			return new MainViewFormulario(user);
		
		else if (panel.equals("Publicacion"))
			return new MainViewPublicacion(user);
		
		else
			return null;
	}
	
}
