package Biblioteca.Control;

import java.util.ArrayList;
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

	private BibliotecaDTO _dto;
	
	private Controller _controller;
	
	// Constructora sin par�metros -- Biblioteca gen�rica
	public BibliotecaControllerFacade(Controller cont) {
		
		_controller = cont;
		
	}
	
	// Constructora con par�metro user -- Biblioteca personal
	
	public BibliotecaControllerFacade(UsuarioDTO user, Controller cont) {
		_controller = cont;
		_dto = new BibliotecaDTO(getOwnedGames(user), user);
		
	}
	
	public List<JuegoEnPropiedadDTO> getOwnedGames(UsuarioDTO user){
		
		List<JuegoEnPropiedadDTO> games = new ArrayList<JuegoEnPropiedadDTO>();
		
		games = SingletonBibliotecaDAO.getInstance().getOwnedGames(user.get_user_id());
		
		return games;
	}

	public void anadirJuego(JuegoDTO juego) {
		JuegoEnPropiedadDTO newGame = new JuegoEnPropiedadDTO(juego);
		_dto.anadirJuego(newGame);
		SingletonBibliotecaDAO.getInstance().actualizarBiblioteca(newGame, _controller.getCurrentUser());
	}
	
	public JPanel getBibliotecaPanel() {
		
		_dto = new BibliotecaDTO(getOwnedGames(_controller.getCurrentUser()), _controller.getCurrentUser());
		
		return new MainViewBiblioteca(this, _controller.getCurrentUser(), _dto.get_juegosEnBiblioteca());
		
	}

	public List<BibliotecaDTO> getLibraries() {
		
		return SingletonBibliotecaDAO.getInstance().getLibraries();
	}

	public List<JuegoEnPropiedadDTO> getOwnedGames(JSONArray jsonArray) {
		
		return SingletonBibliotecaDAO.getInstance().getOwnedGames(jsonArray);
	}

	public List<JuegoEnPropiedadDTO> getOwnedGames(String _userId) {
		
		return SingletonBibliotecaDAO.getInstance().getOwnedGames(_userId);
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
			actualizarJuego(juego);
			break;
		case instalarJuego:
			instalarJuego(juego);
			
			break;
		case jugarJuego:
			if (!juego.is_installed()) {
            	JOptionPane.showMessageDialog(null, "Necesitas instalar el juego", "Instalar", JOptionPane.ERROR_MESSAGE);
            	instalarJuego(juego);
            	_controller.setPrincipalPanel(new MainViewBiblioteca(this, _controller.getCurrentUser(), _dto.get_juegosEnBiblioteca()));
			}
			else if (juego.get_actVersion() != juego.get_version()) {
            	JOptionPane.showMessageDialog(null, "Necesitas actualizar el juego", "Actualizar", JOptionPane.ERROR_MESSAGE);
            	actualizarJuego(juego);
            	_controller.setPrincipalPanel(new MainViewBiblioteca(this, _controller.getCurrentUser(), _dto.get_juegosEnBiblioteca()));
			}
			else
				new ViewEjecucion(juego, this);
			break;
		
		}
	}

	public void instalarJuego(JuegoEnPropiedadDTO juego) {
		juego.set_installed(true);
		SingletonBibliotecaDAO.getInstance().actualizarBiblioteca(juego, _controller.getCurrentUser());
	}
	
	public void actualizarJuego(JuegoEnPropiedadDTO juego) {
		juego.set_actVersion(juego.get_version());
		SingletonBibliotecaDAO.getInstance().actualizarBiblioteca(_dto.getJuego(juego), _controller.getCurrentUser());
	}
	
	public void ejecutarJuego(JuegoEnPropiedadDTO juego) {
		_dto.ejecutarJuego(juego);
		SingletonBibliotecaDAO.getInstance().actualizarBiblioteca(_dto.getJuego(juego), _controller.getCurrentUser());
	}

	public void comprarJuego(JuegoDTO j) {
		SingletonBibliotecaDAO.getInstance().insertarJuego(j, _controller.getCurrentUser());
	}

	public void eliminarJuego(String id) {
		SingletonBibliotecaDAO.getInstance().eliminarJuegos(id);
	}

	public void eliminarUsuario(UsuarioDTO user) {
		SingletonBibliotecaDAO.getInstance().eliminarBiblioteca(user.get_user_id());
	}
}
