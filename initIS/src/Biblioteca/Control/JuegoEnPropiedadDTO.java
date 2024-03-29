package Biblioteca.Control;

import org.json.JSONObject;

import Juego.Control.JuegoDTO;

public class JuegoEnPropiedadDTO extends JuegoDTO{
	private int _hoursPlayed;
	private int _actVersion;
	private String _lastEx;
	private boolean _installed;
	private JuegoEnPropiedadDAO _dao;
	
	public JuegoEnPropiedadDTO(JSONObject juegoEnPropiedad) {
		super(juegoEnPropiedad.getString("_gameId"));
		this._hoursPlayed =  juegoEnPropiedad.getInt("_hoursPlayed");
		this._lastEx = juegoEnPropiedad.getString("_lastEx");
		this._actVersion = juegoEnPropiedad.getInt("_actVers");
		this._installed = juegoEnPropiedad.getBoolean("_installed");
	}
	
	public JuegoEnPropiedadDTO(JuegoDTO game) {
		super(game.get_id());
		this._hoursPlayed = 0;
		this._actVersion = game.get_version();
		this._lastEx = "never";
		this._installed = false;
		
	}
	
	public JuegoEnPropiedadDTO(JuegoEnPropiedadDTO game) {
		super(game.get_id());
		_dao = new JuegoEnPropiedadDAOJSON();
		
		this._hoursPlayed = game.get_hoursPlayed();
		this._actVersion = game.get_actVersion();
		this._lastEx = game.get_lastEx();
		this._installed = game.is_installed();
		
	}
	public JSONObject JuegoEnPropiedadToJSON() {
		_dao = new JuegoEnPropiedadDAOJSON();
		
		return _dao.JuegoEnPropiedadToJSON(this);
	}
	
	public int get_hoursPlayed() {
		return _hoursPlayed;
	}

	public void set_hoursPlayed(int _hoursPlayed) {
		this._hoursPlayed = _hoursPlayed;
	}

	public int get_actVersion() {
		return _actVersion;
	}

	public void set_actVersion(int _actVersion) {
		this._actVersion = _actVersion;
	}

	public String get_lastEx() {
		return _lastEx;
	}

	public void set_lastEx(String _lastEx) {
		this._lastEx = _lastEx;
	}

	public boolean is_installed() {
		return _installed;
	}

	public void set_installed(boolean _installed) {
		this._installed = _installed;
	}
	
}
