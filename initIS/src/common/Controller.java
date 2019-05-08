package common;

import javax.swing.JPanel;

import Juego.Control.JuegoDTO;
import Usuario.Control.UsuarioDTO;

public abstract class Controller {
	
	//Do nothing
	public abstract JPanel getPanel(String panel, Object o, UsuarioDTO user);
}
