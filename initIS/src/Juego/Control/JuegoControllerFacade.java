package Juego.Control;

import javax.swing.JPanel;

import Juego.View.MainViewActualizarJuego;
import Juego.View.MainViewDeveloper;
import Juego.View.MainViewEliminarJuego;
import Juego.View.MainViewJuego;
import Usuario.Control.UsuarioDTO;
import common.Controller;

public class JuegoControllerFacade {
	
	private Controller _controller;
	
	public JuegoControllerFacade(Controller controller) {
		
		_controller = controller;
		
	}
	
	public void evento(EventoJuego e, JuegoDTO _juego) {
		switch (e) {
		case JuegoTienda:
			_controller.setPrincipalPanel(new MainViewJuego(_juego, this));
			break;
		case Desarrolladora:
			_controller.setPrincipalPanel(new MainViewDeveloper(_controller.getCurrentUser(), this));
			break;
		case ActualizarJuego:
			_controller.setPrincipalPanel(new MainViewActualizarJuego(_controller.getCurrentUser(), _juego, this));
			break;
		case EliminarJuego:
			_controller.setPrincipalPanel(new MainViewEliminarJuego(_juego, this));
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

	public void formularioEliminar(JuegoDTO dto, String type, String just) {
		
		_controller.insertarFormulario(_controller.crearFormulario(dto, type, just), "Eliminar");
	}

	public void eliminarJuego(String id) {
		SingletonJuegoDAO.getInstance().eliminarJuego(id);
	}

}
