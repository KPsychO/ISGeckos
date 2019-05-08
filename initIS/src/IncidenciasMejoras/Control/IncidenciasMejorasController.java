package IncidenciasMejoras.Control;

import javax.swing.JPanel;

import IncidenciasMejoras.View.MainViewDenunciasJugador;
import IncidenciasMejoras.View.MainViewIncidenciasJugador;
import IncidenciasMejoras.View.MainViewRevisionMensajes;
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
		else
			return null;
	}

}
