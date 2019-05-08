package common;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import IncidenciasMejoras.Control.ControllerIncidenciasMejoras;
import IncidenciasMejoras.View.MainViewDenunciasJugador;
import Juego.Control.ControllerJuego;
import Juego.Control.JuegoDTO;
import Usuario.Control.UsuarioDTO;
import valoraciones.ControllerValoraciones;

public class Controller {
	//Poner vuestros controller aqui
	private ControllerValoraciones _controllerValoraciones;
	private ControllerJuego _controllerJuego;
	private ControllerIncidenciasMejoras _controllerIncidenciasMejoras;
	
	private MainWindow _mw;
	
	private UsuarioDTO _current_user;
	
	public Controller() {
		
		_current_user = new UsuarioDTO("0000000000");
		_mw = new MainWindow();
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				//Pistacho, hasta que no hagas tu controller esto no va a funcar
				_mw.reinicia(null, _current_user);
			}
		});
		
	}
	public void setPrincipalPanel(JPanel panel) {
		_mw.reinicia(panel, _current_user);
	}
	
	public void setCurrentUser(UsuarioDTO user) {
		_current_user = user;
	}
	
	//Me encanta hacer esto
	public boolean isRegistered() {
		return !_current_user.isUnregistered();
	}
	
	// Diego
	public JPanel getComprarJuego(JuegoDTO _juego) {
		// TODO Auto-generated method stub
		return null;
	}
	
	// Diego
	public JPanel getTienda() {
		// TODO Auto-generated method stub
		return null;
	}
	
	//Jin
	public JPanel getDenunciasJugador(UsuarioDTO userDen) {
		return new MainViewDenunciasJugador(_current_user, userDen, _controllerIncidenciasMejoras);
	}
	
	/* Salbio
	public UsuarioDTO getUsuarioId(String ID) {
		_current_user = _controllerUsuario.getUsuarioID(ID);
	}
	*/

}
