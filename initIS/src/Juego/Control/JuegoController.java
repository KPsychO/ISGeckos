package Juego.Control;

import javax.swing.JPanel;

import Juego.View.MainViewJuego;
import Usuario.Control.UsuarioDTO;
import common.Controller;

public class JuegoController extends Controller{

	public JPanel getPanel(String panel, UsuarioDTO user, JuegoDTO juego) {
		if (panel.equals("JuegoTienda"))
			return new MainViewJuego(juego);
		
		else if (panel.equals("VerEnTienda"))
			return new MainViewJuego(juego);
		
		else
			return null;
	}

	@Override
	public JPanel getPanel(String panel, Object o, UsuarioDTO user) {
		if (panel.equals("JuegoTienda"))
			return new MainViewJuego((JuegoDTO) o);
		
		else if (panel.equals("VerEnTienda"))
			return new MainViewJuego((JuegoDTO) o);
		
		else
			return null;
	}

	
	
}
