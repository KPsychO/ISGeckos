package IncidenciasMejoras.Control;

import javax.swing.JPanel;

import org.json.JSONArray;

import Comunidad.View.MainViewPerfilUsuarioDenunciado;
import IncidenciasMejoras.View.MainViewIncidenciasJugador;
import IncidenciasMejoras.View.MainViewRevisionMensajes;
import Juego.Control.JuegoDTO;
import Usuario.Control.UsuarioDTO;
import common.Controller;
import common.EventoCommon;

public class IncidenciasMejorasControllerFacade {

	private Controller _controller;
	
	public IncidenciasMejorasControllerFacade(Controller controller) {
		
		_controller = controller;
		
	}
	
	public void evento(EventoIncidenciasMejoras e, JuegoDTO _juego, UsuarioDTO _user) {
		switch (e) {
		case IncMejATienda:
			_controller.setPrincipalPanel(_controller.getTienda());
			break;
		case IncMejABiblioteca:
			_controller.setPrincipalPanel(_controller.getBiblioteca());
			break;
		case IncMejAComunidad:
			_controller.setPrincipalPanel(_controller.getComunidad());
			break;
		case PerfilUsuario:
			_controller.evento(EventoCommon.Usuario, _juego, _user);
			break;
		default:
			break;
		}
	}

	public JPanel getIncidenciasMejorasPanel(UsuarioDTO dto) {
		return new MainViewIncidenciasJugador(dto, this);
	}

	public JPanel getRevMejPanel() {
		return new MainViewRevisionMensajes(_controller.getCurrentUser(), this);
	}
	
	public int getIncidenciasMejoras() {
		JSONArray im = new JSONArray();
		im = new IncidenciasDAOJSON().getListIncidencias();
		return im.length();
	}

}
