package Biblioteca.Control;

import java.util.List;

public class JuegoEnPropiedadControllerFacade {

	private JuegoEnPropiedadDAO _dao;
	private JuegoEnPropiedadDTO _dto;
	
	public JuegoEnPropiedadControllerFacade(String id) {
		_dao = new JuegoEnPropiedadDAOJSON();
		_dto = new JuegoEnPropiedadDTO(getJuego(id));
	}

	public String get_title() {
		return this._dto.get_title();
	}
	
	public String get_id() {
		return this._dto.get_id();
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
	
	public JuegoEnPropiedadDTO getJuego(String id) {
		List<JuegoEnPropiedadDTO> games = _dao.getJuegosID(id);
		
		for (JuegoEnPropiedadDTO g : games) {
			
			if (g.get_id().equals(id)) {
				return g;
			}
			
		}
		return null;
	}

	public boolean is_installed() {
		return this._dto.is_installed();
	}

	public int get_actVersion() {
		return this._dto.get_actVersion();
	}

	public int get_version() {
		return this._dto.get_version();
	}

	public void set_actVersion(int version) {
		this._dto.set_actVersion(version);		
	}

	public void set_installed(boolean b) {
		this._dto.set_installed(b);
	}
}
