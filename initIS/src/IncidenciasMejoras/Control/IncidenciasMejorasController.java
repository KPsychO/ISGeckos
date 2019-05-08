package IncidenciasMejoras.Control;

import javax.swing.JPanel;

import IncidenciasMejoras.View.MainViewDenunciasJuego;
import IncidenciasMejoras.View.MainViewDenunciasJugador;
import IncidenciasMejoras.View.MainViewIncidenciasJuego;
import IncidenciasMejoras.View.MainViewIncidenciasJugador;
import IncidenciasMejoras.View.MainViewRevisionMensajes;
import Juego.Control.JuegoDTO;
import Usuario.Control.UsuarioDTO;
import common.Controller;

public class IncidenciasMejorasController extends Controller {

	@Override
	public JPanel getPanel(String panel, Object o, UsuarioDTO user) {
		if (panel.equals("Soporte"))
			return new MainViewIncidenciasJugador(user);
		else if (panel.equals("DenunciarJugador"))
			return new MainViewDenunciasJugador(user, (UsuarioDTO) o);
		else if (panel.equals("revIncMej"))
			return new MainViewRevisionMensajes(user);
		else if (panel.equals("IncidenciaJuego"))
			return new MainViewIncidenciasJuego(user, (JuegoDTO) o);
		else if (panel.equals("DenunciarJuego"))
			return new MainViewDenunciasJuego(user, (JuegoDTO) o);
		else
			return null;
	}

}
