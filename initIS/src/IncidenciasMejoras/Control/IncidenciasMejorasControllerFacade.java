package IncidenciasMejoras.Control;

import javax.swing.JPanel;

import IncidenciasMejoras.View.MainViewDenunciasJugador;
import IncidenciasMejoras.View.MainViewIncidenciasJuego;
import IncidenciasMejoras.View.MainViewIncidenciasJugador;
import IncidenciasMejoras.View.MainViewRevisionMensajes;
import Juego.Control.JuegoDTO;
import Usuario.Control.UsuarioDTO;
import common.Controller;

public class IncidenciasMejorasControllerFacade {

	private Controller _controller;
	
	public IncidenciasMejorasControllerFacade(Controller controller) {
		
		_controller = controller;
		
	}
	
	public void evento(EventoIncidenciasMejoras e, JuegoDTO _juego, UsuarioDTO _user) {
		switch (e) {
		case DenunciasJuego:
			_controller.setPrincipalPanel(new MainViewDenunciasJugador(_user, null, this));
			break;
		case IncidenciasJuego:
			_controller.setPrincipalPanel(new MainViewIncidenciasJuego(_user, _juego, this));
			break;
		case IncMejATienda:
			_controller.setPrincipalPanel(_controller.getTienda());
			break;
		default:
			break;
		}
	}

	public JPanel getIncidenciasMejorasPanel(UsuarioDTO dto) {
		return new MainViewIncidenciasJugador(dto, this);
	}

	public JPanel getRevMejPanel() {
		return new MainViewRevisionMensajes(_controller.getCurrentUser());
	}

}
