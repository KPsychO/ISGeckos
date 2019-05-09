package Formulario.Control;

import Formulario.Control.EventoFormulario;
import Formulario.View.MainViewFormulario;
import Formulario.View.MainViewPublicacion;
import Usuario.Control.UsuarioDTO;
import common.Controller;

public class ControllerFormulario {

	
private Controller _controller;
	
	public ControllerFormulario(Controller controller) {
		
		_controller = controller;
	}
	
	public void evento(EventoFormulario e, UsuarioDTO _user) {
		switch (e) {
		case ViewFormulario:
			_controller.setPrincipalPanel(new MainViewFormulario(_user));
			break;
		case ViewPublicacion:
			_controller.setPrincipalPanel(new MainViewPublicacion(_user));
			break;
		
		default:
			break;
		}
	}
}
