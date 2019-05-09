package common;

import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Biblioteca.Control.BibliotecaControllerFacade;
import Biblioteca.Control.JuegoEnPropiedadDTO;
import Comunidad.View.ComunidadControllerFacade;
import Formulario.Control.FormularioControllerFacade;
import Formulario.Control.FormularioDTO;
import IncidenciasMejoras.Control.IncidenciasMejorasControllerFacade;
import IncidenciasMejoras.View.MainViewDenunciasJuego;
import IncidenciasMejoras.View.MainViewDenunciasJugador;
import IncidenciasMejoras.View.MainViewIncidenciasJuego;
import Juego.Control.JuegoControllerFacade;
import Juego.Control.JuegoDTO;
import Tienda.Control.TiendaControllerFacade;
import Usuario.Control.UsuarioControllerFacade;
import Usuario.Control.UsuarioDTO;
import valoraciones.ValoracionesControllerFacade;

public class Controller {
	//Poner vuestros controller aqui
	
	private JuegoControllerFacade _controllerJuego;
	private IncidenciasMejorasControllerFacade _controllerIncidenciasMejoras;
	private ComunidadControllerFacade _controllerComunidad;
	private FormularioControllerFacade _controllerFormulario;
	private ValoracionesControllerFacade _controllerValoraciones;
	private UsuarioControllerFacade _controllerUsuario;
	private BibliotecaControllerFacade _bibliotecaController;
	private TiendaControllerFacade _tiendaControler;
	
	private MainWindow _mw;
	
	private UsuarioDTO _current_user;
	
	public Controller() {
		
		_current_user = new UsuarioDTO("5ccd515396f1e847f2e4eb0d");
		
		_controllerJuego = new JuegoControllerFacade(this);
		_controllerIncidenciasMejoras = new IncidenciasMejorasControllerFacade(this);
		_controllerComunidad = new ComunidadControllerFacade(this);
		_controllerFormulario = new FormularioControllerFacade(this);
		_controllerValoraciones = new ValoracionesControllerFacade(this);
		_controllerUsuario = new UsuarioControllerFacade(this, _current_user);
		_bibliotecaController = new BibliotecaControllerFacade(this);
		_tiendaControler = new TiendaControllerFacade(this);
		
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
			if (_current_user.isUnregistered())
            	JOptionPane.showMessageDialog(null, "Necesitas estas logeado para ver la biblioteca", "Error", JOptionPane.ERROR_MESSAGE);
			else
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

	public void anadirJuegoComprado(JuegoDTO j) {		
		_bibliotecaController.comprarJuego(j);
	}
	
	// Ignacio

	public void valorar(JuegoEnPropiedadDTO juego) {
		this.setPrincipalPanel(this._controllerValoraciones.getFormValoraciones(this._current_user, juego));
	}
	
	public JPanel getListValoraciones(JuegoDTO game) throws IOException {
		return this._controllerValoraciones.getPanelListValoracionesJuego(game, this._current_user);
	}	

	public UsuarioDTO getCurrentUser() {
		
		return _current_user;
		
	}
	
	public JuegoControllerFacade getControllerJuego() {
		return _controllerJuego;
	}
	
	public TiendaControllerFacade getControllerTienda() {
		return _tiendaControler;
	}

	public JPanel getFormularioPanel() {
		return _controllerFormulario.getFormularioPanel(_current_user);
	}

	public JPanel getPublicacionPanel() {
		return _controllerFormulario.getPublicacion(_current_user);
	}

	public JPanel getDesarrolladoraPanel() {
		return _controllerJuego.getDesarrolladoraPanel(_current_user);
	}

	public JPanel getRevMej() {
		if (_controllerIncidenciasMejoras.getIncidenciasMejoras() != 0) {
			return _controllerIncidenciasMejoras.getRevMejPanel();
		}
		else {
			JOptionPane.showMessageDialog(null, "No hay mas Incidencias/Denuncias", "Error", JOptionPane.ERROR_MESSAGE);
			return null;
		}	
	}
	
	public List<JuegoEnPropiedadDTO> getOwnedGames(){
		return _bibliotecaController.getOwnedGames(_current_user);
	}

	public JPanel getDenunciasJuego(JuegoEnPropiedadDTO juego) {
		return new MainViewDenunciasJuego(_current_user, juego, _controllerIncidenciasMejoras);
	}

	public JPanel getIncidenciasJuego(JuegoEnPropiedadDTO juego) {
		return new MainViewIncidenciasJuego(_current_user, juego, _controllerIncidenciasMejoras);
	}

	public void insertarFormulario(FormularioDTO dto, String type) {
		_controllerFormulario.insertarFormulario(dto, type);
	}

	public FormularioDTO crearFormulario(JuegoDTO dto, String type, String just) {
		return _controllerFormulario.crearFormulario(dto, type, just);
	}

	public void eliminarJuego(String id) {
		_controllerJuego.eliminarJuego(id);
		//_controllerBiblioteca.eliminarJuego(id);
		//_controllerFormulario.eliminarJuego(id);
		
	}
	
	public void quitaBalance(int b) {
		_controllerUsuario.quitaBalance(b, _current_user);
	}
	
	public void addBalance(int b) {
		_current_user.set_balance(_current_user.get_balance() + b);
	}
	
	public int get_ownedGames() {
		return _bibliotecaController.getOwnedGames(_current_user).size();
	}
	
	public ComunidadControllerFacade getComunControl() {
		return _controllerComunidad;
	}
	
}
