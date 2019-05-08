package Biblioteca.Control;

import java.util.List;

import javax.swing.JPanel;

import Juego.Control.JuegoDTO;
import Usuario.Control.UsuarioDTO;
import common.Controller;

public class JuegoEnPropiedadController extends Controller {

	private JuegoEnPropiedadDAO _dao;
	private JuegoEnPropiedadDTO _dto;
	
	public JuegoEnPropiedadController(String id) {
		_dao = new JuegoEnPropiedadDAOJSON();
		_dto = new JuegoEnPropiedadDTO(getJuego(id));
	}
	
	@Override
	public JPanel getPanel(String panel, Object o, UsuarioDTO user) {
		// TODO Auto-generated method stub
		return null;
	}

	public void executeGame() {
		_dto.set_hoursPlayed(_dto.get_hoursPlayed() + 1);
		_dto.set_lastEx("now");
	}
	
	public void installGame() {
		_dto.set_installed(true);
		_dto.set_actVersion(_dto.get_version());
	}
	
	public void updateGame() {
		_dto.set_actVersion(_dto.get_version());
	}
	
	public void executeGame(JuegoEnPropiedadDTO game) {
		game.set_hoursPlayed(game.get_hoursPlayed() + 1);
		game.set_lastEx("now");
	}
	
	public void installGame(JuegoEnPropiedadDTO game) {
		game.set_installed(true);
		game.set_actVersion(game.get_version());
	}
	
	public void updateGame(JuegoEnPropiedadDTO game) {
		game.set_actVersion(game.get_version());
	}
	
	public JuegoDTO getJuego(String id) {
		List<JuegoEnPropiedadDTO> games = _dao.getJuegos();
		
		for (JuegoDTO g : games) {
			
			if (g.get_id().equals(id)) {
				return g;
			}
			
		}
		return null;
	}
}
