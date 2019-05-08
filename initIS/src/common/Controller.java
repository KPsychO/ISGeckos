package common;

import javax.swing.JPanel;

import Usuario.Control.UsuarioDTO;

public abstract class Controller {
	
	//Do nothing
	@SuppressWarnings("exports")
	public abstract JPanel getPanel(String panel, Object o, UsuarioDTO user);
}
