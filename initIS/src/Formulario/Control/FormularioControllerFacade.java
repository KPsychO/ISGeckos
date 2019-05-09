package Formulario.Control;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.json.JSONArray;

import Formulario.Control.EventoFormulario;
import Formulario.View.MainViewFormulario;
import Formulario.View.MainViewPublicacion;
import Usuario.Control.UsuarioDTO;
import common.Controller;
import common.EventoCommon;

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
			_controller.evento(EventoCommon.Usuario, null, _user);
		case Perfil:
			_controller.evento(EventoCommon.Usuario, null, _user);
		
		default:
			break;
		}
	}
	
	public JPanel getFormularioPanel(UsuarioDTO dev) {
		return new MainViewFormulario(dev, this);
	}

	public JPanel getPublicacion(UsuarioDTO dev) {
		Object JPanel = null;
		if(this.getNumForm() != 0) {
			return new MainViewPublicacion(dev, this);
		}
		else {
			JOptionPane.showMessageDialog(null, "No hay mas formulariuos", "Error", JOptionPane.ERROR_MESSAGE);
			return null;
		} 
	}
	
	public int getNumForm() {
		JSONArray form = new JSONArray();
		form = new FormularioDAOJSON().getFormularies();
		return form.length();
	}
}
