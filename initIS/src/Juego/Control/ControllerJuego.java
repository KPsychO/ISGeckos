package Juego.Control;

import javax.swing.JPanel;

import Juego.View.MainViewActualizarJuego;
import Juego.View.MainViewDeveloper;
import Juego.View.MainViewJuego;
import Usuario.Control.UsuarioDTO;
import common.Controller;

public class ControllerJuego {
	
	private Controller _controller;
	
	public ControllerJuego(Controller controller) {
		
		_controller = controller;
		
	}
	
	public void evento(EventoJuego e, JuegoDTO _juego, UsuarioDTO _user) {
		switch (e) {
		case JuegoTienda:
			_controller.setPrincipalPanel(new MainViewJuego(_juego, this));
			break;
		case Desarrolladora:
			_controller.setPrincipalPanel(new MainViewDeveloper(_user, this));
			break;
		case ActualizarJuego:
			_controller.setPrincipalPanel(new MainViewActualizarJuego(_user, _juego, this));
			break;
		case EliminarJuego:
			break;
		case ComprarJuego:
			_controller.setPrincipalPanel(_controller.getComprarJuego(_juego));
			break;
		default:
			break;
		}
	}
	
	public boolean isRegistered() {
		return _controller.isRegistered();
	}

	public JPanel getDesarrolladoraPanel(UsuarioDTO dto) {
		return new MainViewDeveloper(dto, this);
	}

}
