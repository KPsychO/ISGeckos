package Biblioteca.Control;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.json.JSONArray;

import Biblioteca.View.MainViewBiblioteca;
import Biblioteca.View.ViewEjecucion;
import Juego.Control.JuegoDTO;
import Usuario.Control.UsuarioDTO;
import common.Controller;

public class BibliotecaControllerFacade{

	private BibliotecaSA _sa;
	
	private Controller _controller;
	
	public BibliotecaControllerFacade(Controller cont) {
		
		_controller = cont;
		_sa = new BibliotecaSA(_controller.getCurrentUser());
	}
		
	public List<JuegoEnPropiedadDTO> getOwnedGames(UsuarioDTO user){
		return _sa.getOwnedGames(user.get_user_id());
	}

	public void anadirJuego(JuegoDTO juego) {
		JuegoEnPropiedadDTO newGame = new JuegoEnPropiedadDTO(juego);
		_sa.anadirJuego(newGame, _controller.getCurrentUser());	
	}
	
	public JPanel getBibliotecaPanel() {
		_sa.newBibliotecaPanel(_controller.getCurrentUser());
		return new MainViewBiblioteca(this, _controller.getCurrentUser(), _sa.get_juegosEnBiblioteca());
	}

	public List<BibliotecaDTO> getLibraries() {
		return _sa.getLibraries();
	}

	public List<JuegoEnPropiedadDTO> getOwnedGames(JSONArray jsonArray) {
		return _sa.getOwnedGames(jsonArray);
	}

	public List<JuegoEnPropiedadDTO> getOwnedGames(String userId) {		
		return _sa.getOwnedGames(userId);
	}
	
	public void evento(EventoBiblioteca e, JuegoEnPropiedadDTO juego) {
		switch(e) {
		case valoraciones:
			_controller.valorar(juego);
			break;
		case incidencia:
			_controller.setPrincipalPanel(_controller.getIncidenciasJuego(juego));
			break;
		case denuncia:
			_controller.setPrincipalPanel(_controller.getDenunciasJuego(juego));
			break;
		case actualizarJuego:
			_sa.actualizarJuego(juego, _controller.getCurrentUser());
			break;
		case instalarJuego:
			_sa.instalarJuego(juego, _controller.getCurrentUser());
			break;
		case jugarJuego:
			jugarJuego(juego);
			break;
		case JuegoTienda:
			// Ver el juego en la tienda
			// Hay que llamar al controller
			break;
		}
	}

	public void jugarJuego(JuegoEnPropiedadDTO juego) {
		if (!juego.is_installed()) {
        	JOptionPane.showMessageDialog(null, "Necesitas instalar el juego", "Instalar", JOptionPane.ERROR_MESSAGE);
        	_sa.instalarJuego(juego, _controller.getCurrentUser());
        	_controller.setPrincipalPanel(new MainViewBiblioteca(this, _controller.getCurrentUser(), _sa.get_juegosEnBiblioteca()));
		}
		else if (juego.get_actVersion() != juego.get_version()) {
        	JOptionPane.showMessageDialog(null, "Necesitas actualizar el juego", "Actualizar", JOptionPane.ERROR_MESSAGE);
        	_sa.actualizarJuego(juego, _controller.getCurrentUser());
        	_controller.setPrincipalPanel(new MainViewBiblioteca(this, _controller.getCurrentUser(), _sa.get_juegosEnBiblioteca()));
		}
		else {
			new ViewEjecucion(juego, this);
			_sa.ejecutarJuego(juego, _controller.getCurrentUser());
		}
	}
	
	public void comprarJuego(JuegoDTO j) {
		_sa.comprarJuego(j, _controller.getCurrentUser());
	}

	public void eliminarJuego(String id) {
		_sa.eliminarJuego(id);
	}

	public void eliminarUsuario(UsuarioDTO user) {
		_sa.eliminarUsuario(user);
	}
}
