package common;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Biblioteca.Control.BibliotecaController;
import Comunidad.View.ControllerComunidad;
import Formulario.Control.ControllerFormulario;
import IncidenciasMejoras.Control.ControllerIncidenciasMejoras;
import IncidenciasMejoras.View.MainViewDenunciasJugador;
import Juego.Control.ControllerJuego;
import Juego.Control.JuegoDTO;
import Usuario.Control.ControllerUsuario;
import Tienda.Control.TiendaController;
import Usuario.Control.UsuarioDTO;
import valoraciones.ControllerValoraciones;

public class Controller {
	//Poner vuestros controller aqui
	
	private ControllerJuego _controllerJuego;
	private ControllerIncidenciasMejoras _controllerIncidenciasMejoras;
	private ControllerComunidad _controllerComunidad;
	private ControllerFormulario _controllerFormulario;
	private ControllerValoraciones _controllerValoraciones;
	private ControllerUsuario _controllerUsuario;
	private TiendaController _tiendaControler;
	private BibliotecaController _bibliotecaController;
	
	private MainWindow _mw;
	
	private UsuarioDTO _current_user;
	
	public Controller() {
		
		_current_user = new UsuarioDTO("0000000000");
		
		_controllerJuego = new ControllerJuego(this);
		_controllerIncidenciasMejoras = new ControllerIncidenciasMejoras(this);
		_controllerComunidad = new ControllerComunidad(this);
		_controllerFormulario = new ControllerFormulario(this);
		_controllerValoraciones = new ControllerValoraciones(this);
		_controllerUsuario = new ControllerUsuario(this, _current_user);
		_tiendaControler = new TiendaController(this);
		_bibliotecaController = new BibliotecaController(this);
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				_mw = new MainWindow(Controller.this);
				_mw.reinicia(getTienda(), _current_user);
			}
		});
		
	}
	
	public void evento(EventoCommon e, JuegoDTO _juego, UsuarioDTO _user) {
		switch (e) {
		case Tienda:
			setPrincipalPanel(_tiendaControler.getTiendaPanel());
			break;
		case Biblioteca:
			setPrincipalPanel(_bibliotecaController.getBibliotecaPanel());
			break;
		case Comunidad:
			setPrincipalPanel(_controllerComunidad.getComunidadPanel(_current_user));
			break;
		case Soporte:
			setPrincipalPanel(_controllerIncidenciasMejoras.getIncidenciasMejorasPanel(_current_user));
			break;
		case Usuario:
			setPrincipalPanel(_controllerUsuario.getIconoPanel(_current_user));
			break;
		default:
			break;
		}
	}
	
	public void setPrincipalPanel(JPanel panel) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				_mw.reinicia(panel, _current_user);
			}
		});
	}
	
	public void setCurrentUser(UsuarioDTO user) {
		_current_user = user;
	}
	
	public boolean isRegistered() {
		return !_current_user.isUnregistered();
	}
	
	@SuppressWarnings("exports")
	public JPanel getComprarJuego(JuegoDTO _juego) {
		return _tiendaControler.getComprarJuego(_juego);
	}

	@SuppressWarnings("exports")
	public JPanel getTienda() {
		return _tiendaControler.getTiendaPanel();
	}
	
	public JPanel getBiblioteca() {
		return _bibliotecaController.getBibliotecaPanel();
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
	
	// Sergio
	
	public void anadirJuegoComprado(JuegoDTO j) {
		
		// sirve para anadir el juego que se compre a la bilio.
		
	}
	
	public UsuarioDTO getCurrentUser() {
		
		return _current_user;
		
	}
	
	public ControllerJuego getControllerJuego() {
		return _controllerJuego;
	}
	
	public TiendaController getControllerTienda() {
		return _tiendaControler;
	}

}
