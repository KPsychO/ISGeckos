package Juego.Control;

import javax.swing.JPanel;

import Juego.View.MainViewJuego;
import common.Controller;

public class JuegoController extends Controller{

	@Override
	public JPanel getPanel(String panel, Object newValue) {
		if (panel.equals("JuegoTienda"))
			return new MainViewJuego((JuegoDTO) newValue);
		
		else if (panel.equals("VerEnTienda"))
			return new MainViewJuego((JuegoDTO) newValue);
		
		else
			return null;
	}

	
	
}
