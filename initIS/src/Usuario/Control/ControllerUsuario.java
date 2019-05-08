package Usuario.Control;

import Usuario.View.MainWindowAcuerdoSuscriptor;
import Usuario.View.MainWindowEliminarCuenta;
import Usuario.View.MainWindowModificarCuenta;
import Usuario.View.MainWindowCrearCuenta;
import Usuario.View.MainWindowIniciarSesion;
import Usuario.View.MainWindowPerfilUsuario;

import javax.swing.JOptionPane;

import Usuario.Control.EventoUsuario;
import Usuario.Control.UsuarioDTO;
import common.Controller;

public class ControllerUsuario {

	private Controller _controller;
	private UsuarioDTO _user;
	
	public ControllerUsuario(Controller controller, UsuarioDTO user) {
		_controller = controller;
		_user = user;
	}
	

	public void evento(EventoUsuario e, UsuarioDTO _user) {
		switch (e) {
		
		case PerfilUsuario:
			if (_user.isUnregistered()) 
				_controller.setPrincipalPanel(new MainWindowIniciarSesion());
			else
				_controller.setPrincipalPanel(new MainWindowPerfilUsuario(_user));
			break;
		case PerfilUsuarioCurrent:
			_controller.setPrincipalPanel(new MainWindowPerfilUsuario(_user));
			break;
		
		case BotonModificarCuenta:
			_controller.setPrincipalPanel(new MainWindowModificarCuenta(_user));
			break;
			
		case ModificarCuenta:
			_controller.setPrincipalPanel(new MainWindowPerfilUsuario(_user));
			break;
		
		case BotonEliminarCuenta: 
			_controller.setPrincipalPanel(new MainWindowEliminarCuenta(_user));
			break;
		
		case EliminarCuenta:
			_controller.setPrincipalPanel(new MainWindowIniciarSesion());
			break;
		
		case CerrarSesion: 
			_controller.setPrincipalPanel(new MainWindowIniciarSesion());
			break;
		
		case BotonCrearCuenta: 
			_controller.setPrincipalPanel(new MainWindowAcuerdoSuscriptor());
			break;
			
		case AcuerdoSuscriptor: 
			_controller.setPrincipalPanel(new MainWindowCrearCuenta());
			break;
			
		case CrearCuenta:
			_controller.setPrincipalPanel(new MainWindowIniciarSesion());
			break;
			
		default: break;
		
		}
	}
	
	public UsuarioDTO getUsuarioId(String ID) {
		return _user.getUsuarioID(ID);
	}
}