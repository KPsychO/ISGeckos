package Formulario.Control;

import java.util.ArrayList;
import java.util.List;

import common.Controller;

import javax.swing.JPanel;

import Formulario.View.ViewFormulario;
import Formulario.View.MainViewPublicacion;
import Usuario.Control.UsuarioDTO;


public class PublicacionController extends Controller{

	@Override
	public JPanel getPanel(String panel, Object o, UsuarioDTO user) {
		if (panel.equals("Formulario"))
			return new ViewFormulario();
		
		else if (panel.equals("Publicacion"))
			return new MainViewPublicacion();
		
		else
			return null;
	}
	
}
