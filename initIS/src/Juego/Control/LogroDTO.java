package Juego.Control;

public class LogroDTO {
	
	private String _id;
	private String _name;
	private String _getMode;
	
	private LogroDAO _dao;
	
	public LogroDTO(String name, String getMode, String id) {
		
		_id = id;
		_name = name;
		_getMode = getMode;
		_dao = new LogroDAOJSON();
		
	}
	
	public LogroDTO(String id, JuegoDTO juego) {
		_dao = new LogroDAOJSON();
		
		for (LogroDTO l : juego.get_achievements()) {
			if (l.get_id().equals(id)) {
				_id = l.get_id();
				_name = l.get_name();
				_getMode = l.get_getMode();
			}
		}
		
	}

	public void updateName(String name) {
		if (name != null)
			_name = name;
	}
	
	public void updateObtencion(String getMode) {
		if (getMode != null)
			_getMode = getMode;
	}

	public String get_name() {
		return _name;
	}

	public void set_name(String _name) {
		this._name = _name;
	}

	public String get_getMode() {
		return _getMode;
	}

	public void set_getMode(String _getMode) {
		this._getMode = _getMode;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}
}
