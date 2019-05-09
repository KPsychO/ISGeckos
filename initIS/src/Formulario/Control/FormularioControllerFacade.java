package Formulario.Control;

import javax.swing.JPanel;

import Formulario.Control.EventoFormulario;
import Formulario.View.MainViewFormulario;
import Formulario.View.MainViewPublicacion;
import Usuario.Control.UsuarioDTO;
import common.Controller;

public class FormularioControllerFacade {
	
private Controller _controller;
	
	public FormularioControllerFacade(Controller controller) {
		
		_controller = controller;
	}
	
	public void evento(EventoFormulario e, UsuarioDTO _user) {
		switch (e) {
		case ViewFormulario:
			_controller.setPrincipalPanel(new MainViewFormulario(_user, this));
			break;
		case ViewPublicacion:
			_controller.setPrincipalPanel(new MainViewPublicacion(_user, this));
			break;
		
		default:
			break;
		}
	}
	
	public JPanel getFormularioPanel(UsuarioDTO dev) {
		return new MainViewFormulario(dev, this);
	}

	public JPanel getPublicacion(UsuarioDTO dev) {
		return new MainViewPublicacion(dev, this);
	}
}
