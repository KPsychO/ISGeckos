package Comunidad.View;

import javax.swing.JPanel;

import IncidenciasMejoras.Control.EventoIncidenciasMejoras;
import IncidenciasMejoras.View.MainViewDenunciasJuego;
import IncidenciasMejoras.View.MainViewDenunciasJugador;
import IncidenciasMejoras.View.MainViewIncidenciasJuego;
import IncidenciasMejoras.View.MainViewIncidenciasJugador;
import Juego.Control.JuegoDTO;
import Tienda.View.MainViewTienda;
import Usuario.Control.UsuarioDTO;
import common.Controller;

public class ControllerComunidad {
private Controller _controller;
	
	public ControllerComunidad(Controller controller) {
		
		_controller = controller;
		
	}
	
	public void evento(EventoComunidad e, JuegoDTO _juego, UsuarioDTO _user) {
		switch (e) {
		case PerfilUsuarioDenunciado:
			_controller.setPrincipalPanel(new MainViewPerfilUsuarioDenunciado(_user, this));
			break;
		/*case Comunidad:
			_controller.setPrincipalPanel(_controller.get);
			break;*/
		case DenunciasJugador:
			_controller.setPrincipalPanel(_controller.getDenunciasJugador(_user));
			break;
		default:
			break;
		}
	}

	public JPanel getComunidadPanel(UsuarioDTO dto) {
		return new MainViewComunidad(dto, this);
	}
}
