package IncidenciasMejoras.Control;

import javax.swing.JPanel;

import IncidenciasMejoras.View.MainViewDenunciasJuego;
import IncidenciasMejoras.View.MainViewDenunciasJugador;
import IncidenciasMejoras.View.MainViewIncidenciasJuego;
import IncidenciasMejoras.View.MainViewIncidenciasJugador;
import Juego.Control.EventoJuego;
import Juego.Control.JuegoDTO;
import Juego.View.MainViewActualizarJuego;
import Juego.View.MainViewDeveloper;
import Juego.View.MainViewJuego;
import Tienda.View.MainViewTienda;
import Usuario.Control.UsuarioDTO;
import common.Controller;

public class ControllerIncidenciasMejoras {

	private Controller _controller;
	
	public ControllerIncidenciasMejoras(Controller controller) {
		
		_controller = controller;
		
	}
	
	public void evento(EventoIncidenciasMejoras e, JuegoDTO _juego, UsuarioDTO _user) {
		switch (e) {
		case DenunciasJuego:
			_controller.setPrincipalPanel(new MainViewDenunciasJugador(_user, null, this));
			break;
		case IncidenciasJugador:
			_controller.setPrincipalPanel(new MainViewIncidenciasJugador(_user, this));
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

}
