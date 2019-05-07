package Juego.Control;

public class LogroDTO {
	
	private String _name;
	private String _getMode;
	
	public LogroDTO(String name, String getMode) {
		_name = name;
		_getMode = getMode;
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
}
