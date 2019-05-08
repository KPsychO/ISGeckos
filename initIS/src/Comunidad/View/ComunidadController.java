package Comunidad.View;

import javax.swing.JPanel;

import Usuario.Control.UsuarioDTO;
import common.Controller;

public class ComunidadController extends Controller {

	@Override
	public JPanel getPanel(String panel, Object o, UsuarioDTO user) {
		if (panel.equals("PerfilUsuarioDenunciado"))
			return new MainViewPerfilUsuarioDenunciado((UsuarioDTO) o);
		else if (panel.equals("Comunidad")) {
			return new MainViewComunidad(user);
		}
		else
			return null;
	}

}
