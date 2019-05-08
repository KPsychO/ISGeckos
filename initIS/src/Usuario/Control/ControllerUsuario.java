package Usuario.Control;

import Usuario.View.MainWindowAcuerdoSuscriptor;
import Usuario.View.MainWindowEliminarCuenta;
import Usuario.View.MainWindowModificarCuenta;
import Usuario.View.MainWindowCrearCuenta;
import Usuario.View.MainWindowIniciarSesion;
import Usuario.View.MainWindowPerfilUsuario;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

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
			if (_user != null) {
				_controller.setCurrentUser(_user);
				_controller.setPrincipalPanel(new MainWindowPerfilUsuario(_user, this));
			}
			
			break;
			
		case ModificarCuenta:
			_controller.setPrincipalPanel(new MainWindowPerfilUsuario(_user, this));
			break;
		
		case EliminarCuenta:
			_controller.setPrincipalPanel(new MainWindowIniciarSesion(this));
			break;
		
		case CerrarSesion: 
			_controller.setCurrentUser(getUsuarioUnreg());
			_controller.setPrincipalPanel(new MainWindowIniciarSesion(this));
			break;
		
		case BotonCrearCuenta: 
			_controller.setPrincipalPanel(new MainWindowAcuerdoSuscriptor(this));
			break;
			
		case AcuerdoSuscriptor: 
			_controller.setPrincipalPanel(new MainWindowCrearCuenta(this));
			break;
			
		case CrearCuenta:
			_controller.setPrincipalPanel(new MainWindowIniciarSesion(this));
			break;
			
		default: break;
		
		}
	}
	
	public UsuarioDTO getUsuarioId(String ID) {
		return _user.getUsuarioID(ID);
	}
	
	public UsuarioDTO getUsuarioUnreg() {
		return _user.getUsuarioID("0000000000");
	}


	public JPanel getIconoPanel(UsuarioDTO dto) {
		if (dto.isUnregistered())
			return new MainWindowIniciarSesion(this);
		else
			return new MainWindowPerfilUsuario(dto, this);
	}
}